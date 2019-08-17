package moa.model.service;

import com.moa.model.service.HostConfirmService;
import com.moa.model.vo.AdminHostSimpleVO;
import com.moa.model.vo.SimpleHostRequestVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "file:web/WEB-INF/dispatcher-servlet.xml",
        "file:web/WEB-INF/mybatis-config.xml",
        "file:web/WEB-INF/security-context.xml"
})
@WebAppConfiguration
public class TestHostConfirmService{
    @Autowired
    private HostConfirmService hostConfirmService;

    @Test
    public void test_호스트신청_목록보기(){
        List<AdminHostSimpleVO> list = hostConfirmService.searchHostConfirmList();
        System.out.println(list);

        assertNotNull(list);
    }

    @Test
    public void test_호스트신청_상세보기_집(){
        int userId = 60;
        String storageType = "집";

        Map<String, Object> map = hostConfirmService.searchRequestInfo(userId, storageType);
        System.out.println(map);

        assertNotNull(map);
    }

    @Test
    public void test_호스트신청_상세보기_상가_회사(){
        int userId = 62;
        String storageType = "회사";

        Map<String, Object> map = hostConfirmService.searchRequestInfo(userId, storageType);
        System.out.println(map);
        System.out.println(map.get("storageType"));
        System.out.println(map.get("phoneNumber"));
        System.out.println(map.get("PHONENUMBER"));

        assertNotNull(map);
    }

    @Test
    public void test_호스트신청_상세보기_기타(){
        int userId = 73;
        String storageType = "기타";

        Map<String, Object> map = hostConfirmService.searchRequestInfo(userId, storageType);
        System.out.println(map);


        assertNotNull(map);
    }

    @Test
    public void test_호스트신청_승인(){
        int userId = 63;
        boolean result;

        result = hostConfirmService.processConfirm(userId, "특이사항이 없습니다.");

        assertTrue(result);
    }

    @Test
    public void test_호스트신청_거절(){
        int userId = 62;
        boolean result;

        result = hostConfirmService.processRefuse(userId, "존재하지 않는 회사입니다.");

        assertTrue(result);
    }
}
