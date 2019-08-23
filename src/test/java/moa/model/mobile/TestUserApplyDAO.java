package moa.model.mobile;


import com.moa.mobile.model.dao.HostRequestDAO;
import com.moa.mobile.model.dao.UserApplyDAO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations ={
        "file:web/WEB-INF/dispatcher-servlet.xml",
        "file:web/WEB-INF/mybatis-config.xml",
        "file:web/WEB-INF/security-context.xml"
})
@WebAppConfiguration
public class TestUserApplyDAO {
    @Autowired
    private UserApplyDAO userApplyDAO;

    @Test
    public void TEST_호스트_요청목록(){
        System.out.println(userApplyDAO.userApplyList(1));

    }
}
