package com.moa.task;

import com.moa.message.PathMessage;
import com.moa.model.dao.AttachDAO;
import com.moa.model.vo.AttachFileVO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class FileCheckTask {
    private Log log= LogFactory.getLog(FileCheckTask.class);
    @Autowired
    private AttachDAO attachDAO;

    private String getFolderYesterDay(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE,-1);
        String str = sdf.format(cal.getTime());
        return str.replace("-", File.separator);
    }

    @Scheduled(cron = "0 0 2 * * * ") //cron이라는 속성을 부여해 주기를 제어 매일 새벽 2시
    //초, 분, 시, 일, 월, 주, 년(옵션)
    //* 모든수, ? 제외, - 기간, ' 특정 시간, / 시작 시간과 반복시간, L 마지막, W 가까운 평일
    public void checkFiles() throws Exception{
        log.warn("File Check Task run...........");
        log.warn(new Date());
        List<AttachFileVO> fileList = attachDAO.getOldFiles();

        List<Path> fileListPaths = fileList.stream().map(vo ->
                Paths.get(PathMessage.UPLOAD_FOLDER,vo.getUploadPath(),
                        vo.getUuid()+"_"+vo.getFileName())).collect(Collectors.toList());

        //thumbnail delete
        fileList.stream().filter(vo -> vo.isFileType()==true)
                .map(vo -> Paths.get(PathMessage.UPLOAD_FOLDER,vo.getUploadPath(),PathMessage.THUMBNAIL+
                        vo.getUuid()+"_"+vo.getFileName())).forEach(p -> fileListPaths.add(p));
        log.warn("======================================");

        fileListPaths.forEach(p -> log.warn(p));

        //files in yesterday directory
        File targetDir = Paths.get(PathMessage.UPLOAD_FOLDER,getFolderYesterDay()).toFile();
        File[] removeFiles = targetDir.listFiles(file -> fileListPaths.contains(file.toPath())== false);
        log.warn("-------------------------------------");
        for(File file : removeFiles){
            log.warn(file.getAbsolutePath());
            file.delete();
        }
    }
}
