package moa.model.dao;

import com.moa.model.dao.StoreBoardDAO;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.moa.valid.CollectionValidator;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations ={
        "file:web/WEB-INF/dispatcher-servlet.xml",
        "file:web/WEB-INF/mybatis-config.xml",
        "file:web/WEB-INF/security-context.xml"
})
public class TestStoreBoardDAO {
    @Autowired
    private StoreBoardDAO storeBoardDAO;
    @Autowired
    private CollectionValidator collectionValidator;

    @Test
    public void TEST_해당게시물정보가져오기() {
        TestCase.assertEquals(null, storeBoardDAO.searchOne(199));
    }
}
