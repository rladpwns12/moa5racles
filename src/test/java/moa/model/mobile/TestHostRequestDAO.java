package moa.model.mobile;


import com.moa.mobile.model.dao.HostRequestDAO;
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
public class TestHostRequestDAO {
    @Autowired
    private HostRequestDAO hostRequestDAO;

    @Test
    public void TEST_호스트_요청목록(){
        System.out.println(hostRequestDAO.requestList(7));

    }
}
