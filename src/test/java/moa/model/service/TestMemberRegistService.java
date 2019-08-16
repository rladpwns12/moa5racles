package moa.model.service;

import com.moa.model.service.MemberRegistServiceImpl;
import com.moa.model.vo.AddressVO;
import com.moa.model.vo.UserVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.HashMap;
import java.util.Map;

import static junit.framework.TestCase.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "file:web/WEB-INF/dispatcher-servlet.xml",
        "file:web/WEB-INF/mybatis-config.xml",
        "file:web/WEB-INF/security-context.xml"
})
@WebAppConfiguration
public class TestMemberRegistService {
    @Autowired
    MemberRegistServiceImpl service;

    @Test
    public void TEST_no_parameter() {
        assertFalse(service.addMember(null, null));
    }

    @Test
    public void TEST_첫번째파라미터가_null() {
        Map<String, Object> userInfo = null;

        Map<String, Object> duplicationInfo = new HashMap<>();
        duplicationInfo.put("email", "email");
        duplicationInfo.put("nick", "nickname");

        assertFalse(service.addMember(userInfo, duplicationInfo));
    }

    @Test
    public void TEST_두번째파라미터가_null() {
        UserVO userVO = new UserVO("email", "password", "phone", "nickname", "name", null);
        AddressVO addressVO = new AddressVO("address", "detailAddress", "postcode", 37.5, 36.9);
        Map<String, Object> userInfo = new HashMap<>();
        userInfo.put("UserVO", userVO);
        userInfo.put("AddressVO", addressVO);
        userInfo.put("res", 0);

        Map<String, Object> duplicationInfo = null;

        assertFalse(service.addMember(userInfo, duplicationInfo));
    }

    @Test
    public void TEST_이메일_중복() {
        UserVO userVO = new UserVO();
        userVO.setEmail("jiho9478@naver.com");
        userVO.setNick("nickNotExist");
        userVO.setPassword("password");
        userVO.setPhoneNumber("010-0000-0002");
        userVO.setName("김지호");
        userVO.setProfile("test.png");

        AddressVO addressVO = new AddressVO();
        addressVO.setBaseAddress("baseAddress");
        addressVO.setDetailAddress("detailAddress");
        addressVO.setPostCode("151050");
        addressVO.setLat(37.483281);
        addressVO.setLng(126.953771);

        Map<String,Object> userInfo = new HashMap<>();
        userInfo.put("UserVO",userVO);
        userInfo.put("AddressVO",addressVO);
        int a = 0;
        userInfo.put("res", a);

        Map<String, Object> duplicationInfo = new HashMap<>();
        duplicationInfo.put("nick", userVO.getNick());
        duplicationInfo.put("email", userVO.getEmail());

        assertTrue(service.addMember(userInfo, duplicationInfo));
    }

    @Test
    public void TEST_닉네임_중복() {
        UserVO userVO = new UserVO();
        userVO.setEmail("jiho9asd478@naver.com");
        userVO.setNick("nicknameNotExist");
        userVO.setPassword("notExist");
        userVO.setPhoneNumber("010-0000-0001");
        userVO.setName("김지호");
        userVO.setProfile("test.png");

        AddressVO addressVO = new AddressVO();
        addressVO.setBaseAddress("baseAddress");
        addressVO.setDetailAddress("detailAddress");
        addressVO.setPostCode("151050");
        addressVO.setLat(37.483280);
        addressVO.setLng(126.953770);

        Map<String,Object> userInfo = new HashMap<>();
        userInfo.put("UserVO",userVO);
        userInfo.put("AddressVO",addressVO);
        userInfo.put("res", 0);

        Map<String, Object> duplicationInfo = new HashMap<>();
        duplicationInfo.put("nick", "nicknameNotExist");
        duplicationInfo.put("name", userVO.getName());

        assertFalse(service.addMember(userInfo, duplicationInfo));
    }

    @Test
    public void TEST_이메일_닉네임_중복() {
        UserVO userVO = new UserVO();
        userVO.setEmail("jiho9478@naver.com");
        userVO.setNick("JIHOO");

        userVO.setPassword("notExist");
        userVO.setPhoneNumber("010-0000-0000");
        userVO.setName("김지호");
        userVO.setProfile("test.png");

        AddressVO addressVO = new AddressVO();
        addressVO.setBaseAddress("baseAddress");
        addressVO.setDetailAddress("detailAddress");
        addressVO.setPostCode("151050");
        addressVO.setLat(37.483280);
        addressVO.setLng(126.953770);

        Map<String,Object> userInfo = new HashMap<>();
        userInfo.put("UserVO",userVO);
        userInfo.put("AddressVO",addressVO);
        userInfo.put("res", 0);

        Map<String, Object> duplicationInfo = new HashMap<>();
        duplicationInfo.put("nick", userVO.getNick());
        duplicationInfo.put("email", userVO.getEmail());

        assertFalse(service.addMember(userInfo, duplicationInfo));
    }
}
