package moa.model.dao;


import com.moa.model.dao.StoreRequestDAO;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static junit.framework.TestCase.assertNotNull;
import static org.springframework.test.util.AssertionErrors.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations ={
        "file:web/WEB-INF/dispatcher-servlet.xml",
        "file:web/WEB-INF/mybatis-config.xml",
        "file:web/WEB-INF/security-context.xml"
})
@WebAppConfiguration
public class TestStoreRequestDAO {
    @Autowired
    private StoreRequestDAO dao;

    @Test
    public void TEST_요청삭제(){
        TestCase.assertEquals(1,dao.deleteConfrimdoneRequest(2));
    }
}
