package moa.model.dao;

import com.moa.model.dao.AttachDAO;
import com.moa.model.dao.CategoryDAO;
import com.moa.model.vo.AttachFileVO;
import com.moa.model.vo.StoreBoardAttachFileVO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations ={
        "file:web/WEB-INF/dispatcher-servlet.xml",
        "file:web/WEB-INF/mybatis-config.xml",
        "file:web/WEB-INF/security-context.xml"
})
@WebAppConfiguration
public class TestAttachDAO {
    private Log log= LogFactory.getLog(TestAttachDAO.class);
    @Autowired
    private AttachDAO attachDAO;
    @Autowired
    private CategoryDAO categoryDAO;

    @Test
    public void 첨부파일추가(){
        AttachFileVO attachFileVO=new StoreBoardAttachFileVO("132wlfh12fk12gek",new Long(3),"2019/08/15","asgqej.png",false);
        assertTrue("첨부파일 추가 실패", attachDAO.insertAttach(attachFileVO));
    }
    @Test
    public void 첨부파일가져오기(){
        List<AttachFileVO> storeBoardAttachFileVOList = attachDAO.searchByArticleSB(new Long(3));
        System.out.println(storeBoardAttachFileVOList);
    }
    @Test
    public void 카테고리가져오기(){
        List<String> categoryList=categoryDAO.getCategoryList();
        System.out.println(categoryList);
        assertNotNull("카테고리 가져오기 실패",categoryList);
    }
    @Test
    public void 오래된파일가져오기(){
        List<AttachFileVO> attachFileList=attachDAO.getOldFiles();
        log.info(attachFileList);
        assertNotNull("오래된 파일 가져오기 실패",attachFileList);
    }
}
