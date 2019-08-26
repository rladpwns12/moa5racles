package com.moa.controller;

import com.moa.model.init.AttachVOFactory;
import com.moa.model.service.AttachService;
import com.moa.model.vo.AttachFileVO;
import com.moa.model.vo.CustomUser;
import net.coobird.thumbnailator.Thumbnails;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Controller
public class UploadController {
    private static final String IMAGE = "image";
    private static final String UPLOAD_FOLDER = "/uploads";
    private static final String THUMBNAIL="thumbnail_";
    private static final int WIDTH = 100;
    private static final int HEIGHT = 100;
    private static final int MAX_SIZE = 5242880;
    private Log log = LogFactory.getLog(UploadController.class);
    private Lock lock= new ReentrantLock();

    @Autowired
    private AttachService attachService;
    @RequestMapping(value = "/uploadAjax", method = RequestMethod.GET)
    public String uploadAjax() {
        log.info("upload ajax");
        return "upload";
    }

    @ResponseBody                         //produces 추가
    @RequestMapping(value = "/uploadAjax/{typeFlag}",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            method = RequestMethod.POST)
    public ResponseEntity<List<AttachFileVO>> uploadAjaxPost(
            @PathVariable("typeFlag") String typeFlag, MultipartFile[] uploadFile, Authentication auth, HttpServletRequest request) {
        int fileCnt=0;
        HttpSession session = request.getSession();
        if(!typeFlag.equals(AttachVOFactory.USER)) {
            if (session.getAttribute("fileCnt") == null) {
                session.setAttribute("fileCnt", 0);
            }
            fileCnt =(int)session.getAttribute("fileCnt");
            log.warn("========hi");
            log.warn(fileCnt);
            log.warn("========hi");
            if(fileCnt + uploadFile.length >6)
                return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
        }
        log.info("update upload ajax post");
        List<AttachFileVO> list=new ArrayList<AttachFileVO>();
        CustomUser customUser = (CustomUser) auth.getPrincipal();
        Long userId=Long.parseLong(customUser.getLoginVO().getUserId());

        String uploadFolderPath = getFolder();
        //make folder
        File uploadPath = new File(getRealUploadPath(request),uploadFolderPath);
        log.info("upload path : " + uploadPath.getAbsolutePath());
        if (uploadPath.exists() == false)
            uploadPath.mkdirs(); // yyyy/MM/dd 폴더 생성 ex ) 2019 아래 08 아래 14 폴더

        for (MultipartFile multipartFile : uploadFile) {
            log.info("-----------------");
            log.info("upload file name : " + multipartFile.getOriginalFilename());
            log.info("upload file size : " + multipartFile.getSize());
            if(multipartFile.getSize()>MAX_SIZE)
                return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);

            AttachFileVO attachFileVO = AttachVOFactory.init(typeFlag);
            attachFileVO.setId(userId);
            String uploadFileName =new String(multipartFile.getOriginalFilename().getBytes(StandardCharsets.UTF_8),StandardCharsets.UTF_8);

            //IE has file path
            uploadFileName = uploadFileName.substring(uploadFileName.lastIndexOf("\\") + 1);
            attachFileVO.setFileName(uploadFileName);
            log.info("only file name : " + uploadFileName);

            UUID uuid = UUID.randomUUID();
            uploadFileName = uuid.toString() + "_" + uploadFileName;

            try {

                lock.lock();

                File saveFile = new File(uploadPath, uploadFileName);
                log.info("saveFile : "+saveFile.getAbsolutePath());
                multipartFile.transferTo(saveFile); // 업로드되는 파일을 간단히 저장하는 방법

                attachFileVO.setUuid(uuid.toString());
                uploadFolderPath = uploadFolderPath.replaceAll("[\\\\]","/");
                attachFileVO.setUploadPath(uploadFolderPath);

                //check Image
                if (checkImageType(saveFile)) {
                    attachFileVO.setFileType(true);
                    log.info("This is image file");
                    Thumbnails.of(saveFile).size(WIDTH,HEIGHT).toFile(new File(uploadPath,THUMBNAIL+uploadFileName));
                    //썸네일이 안만들어지는 경우 project structure artifacts에서 추가 했는지 확인하자.
                }
                log.info(attachFileVO);
                list.add(attachFileVO);
            } catch (IOException e) {
                e.printStackTrace();
                log.error(e.getMessage());
                return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
            }finally {
                lock.unlock();
            }
            if(!typeFlag.equals(AttachVOFactory.USER))
                fileCnt++;
        }
        if(typeFlag.equals(AttachVOFactory.USER)){
            if(!attachService.insertAttach(list))
                return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
            customUser.getLoginVO().setProfile(list.get(0));
        }
        if(!typeFlag.equals(AttachVOFactory.USER))
            session.setAttribute("fileCnt", fileCnt);
        return new ResponseEntity<List<AttachFileVO>>(list, HttpStatus.OK); //JSON 반환
    }

    @RequestMapping("/display")
    @ResponseBody
    //Parameter fileName : 파일 경로가 포함된 이미지 경로
    //return byte[] : 썸네일 이미지 데이터, probeContentType()을 이용해 MIME타입 데이터 -> Http 헤더 메시지 포함
    public ResponseEntity<byte[]> getFile(String fileName, HttpServletRequest request){
        log.info("fileName : "+ fileName);
        File file =new File(getRealUploadPath(request).getAbsolutePath()+fileName);
        log.info("file : " + file);
        ResponseEntity<byte[]> result = null;

        HttpHeaders header =new HttpHeaders();
        try {
            header.add("Content-Type", Files.probeContentType(file.toPath()));
            result = new ResponseEntity<byte[]>(FileCopyUtils.copyToByteArray(file),header, HttpStatus.OK);
        } catch (IOException e) {
            log.warn(e.getMessage());
        }
        return result;
    }

    private String getFolder() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String str = sdf.format(date);
        return str.replace("-", File.separator);
    }

    private boolean checkImageType(File file) {
        try {
            String contentType = Files.probeContentType(file.toPath());
            return contentType.startsWith("image"); // 이미지 일경우 true
        } catch (IOException e) {
            log.warn(e.getMessage());
        }
        return false;
    }

    @RequestMapping(value = "/uploadForm", method = RequestMethod.GET)
    public void uploadForm() {
        log.info("upload Form");
    }

    @RequestMapping(value = "/uploadForm", method = RequestMethod.POST)
    public void uploadFormPost(MultipartFile[] uploadFile, Model model) {
        for (MultipartFile multipartFile : uploadFile) {
            log.info("-----------------");
            log.info("upload file name : " + multipartFile.getOriginalFilename());
            log.info("upload file size : " + multipartFile.getSize());

            File saveFile = new File(UPLOAD_FOLDER, multipartFile.getOriginalFilename());
            try {
                multipartFile.transferTo(saveFile); // 업로드되는 파일을 간단히 저장하는 방법
            } catch (IOException e) {
                log.error(e.getMessage());
            }
        }
    }

    //byte[] 말고 Resource를 사용해 좀더 간단히 처리
    //APPLICATION_OCTET_STREAM_VALUE를 사용해 다운로드 가능 처리
    @RequestMapping(value = "/download", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE, method = RequestMethod.GET)
    public ResponseEntity<Resource> downloadFile(@RequestHeader("User-Agent") String userAgent, String fileName){
        log.info("download file : " + fileName);

        Resource resource = new FileSystemResource(UPLOAD_FOLDER +"\\"+fileName );

        log.info("resource : "+ resource);

        if(resource.exists()==false)
            return new ResponseEntity<Resource>(HttpStatus.NOT_FOUND);

        String resourceName = resource.getFilename();
        //remove UUID
        String resourceOriginalName = resourceName.substring(resourceName.indexOf("_")+1);

        HttpHeaders headers = new HttpHeaders();

        String downloadName =null;
        log.info("userAgent : " + userAgent);
        if(userAgent.contains("Edge")){
            log.info("Edge browser");
            try {
                downloadName= URLEncoder.encode(resourceOriginalName,StandardCharsets.UTF_8.toString());
            } catch (UnsupportedEncodingException e) {
                log.error(e.getMessage());
            }
        }else {
            log.info("Chrome browser or IE browser");
            try {
                downloadName= URLEncoder.encode(resourceOriginalName, StandardCharsets.UTF_8.toString()).replaceAll("\\+"," ");
            } catch (UnsupportedEncodingException e) {
                log.error(e.getMessage());
            }
        }
        //Content-Disposition를 이용해 다운로드시 저장되는 이름 설정 -> 한글인 경우 깨지는 문제 방지
        headers.add("Content-Disposition", "attachment; filename=" + downloadName);

        return new ResponseEntity<Resource>(resource,headers, HttpStatus.OK);
    }

    @RequestMapping(value = "/deleteFile", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> deleteFile(String fileName, String type, String nick, Authentication auth, HttpServletRequest request){
        CustomUser customUser = (CustomUser)auth.getPrincipal();
        if(!nick.equals(customUser.getLoginVO().getNick()))
            return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
        HttpSession session = request.getSession();
        if(session.getAttribute("fileCnt") !=null){
            int fileCnt = (int)session.getAttribute("fileCnt") ;
            if(fileCnt>0)
                session.setAttribute("fileCnt",--fileCnt);
        }
        log.info("deleteFile : "+ fileName + " / type : "+ type);
        File file;

        try {
            file=new File(UPLOAD_FOLDER+"\\"+URLDecoder.decode(fileName,StandardCharsets.UTF_8.toString()));
            file.delete();
            if(type.equals(IMAGE)){
                String largeFileName = file.getAbsolutePath().replace(THUMBNAIL,"");
                file=new File(largeFileName);
                file.delete();
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<String>("delete", HttpStatus.OK);
    }

    public File getRealUploadPath(HttpServletRequest request){
        File upDir = new File(request.getServletContext().getRealPath("/")).getParentFile();
        File realupDir = new File(upDir,UPLOAD_FOLDER);
        if(realupDir.exists() == false)
            realupDir.mkdir();
        return realupDir;
    }

}
