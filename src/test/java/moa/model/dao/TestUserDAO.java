package moa.model.dao;

import com.moa.model.dao.UserDAO;
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


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations ={
        "file:web/WEB-INF/dispatcher-servlet.xml",
        "file:web/WEB-INF/mybatis-config.xml"
})
@WebAppConfiguration
public class TestUserDAO {
    @Autowired
    UserDAO dao;

    @Test
    public void TEST_로그인(){
        Map<String,Object> testMap = new HashMap<String, Object>();
        testMap.put("email","asda216@naver.com");
        testMap.put("password","$2a$10$AXW7Bb69e5byb7QmkSUIleApBrFSYZvmbikvo2zQKX0euECWak3IW");

        System.out.println(dao.checkLogin(testMap));
    }
    @Test
    public void TEST_회원가입(){
        UserVO userVO = new UserVO();
        userVO.setEmail("tt6@naver.com");
        userVO.setPassword("45pw1234");
        userVO.setPhoneNumber("010-5764-0006");
        userVO.setNick("테스트닉6");
        userVO.setName("테스트");

        AddressVO addressVO = new AddressVO();
        addressVO.setBaseAddress("상도로 12-19길");
        addressVO.setDetailAddress("5101호");
        addressVO.setPostCode("95898");
        addressVO.setLat(37.163165);
        addressVO.setLng(126.37995);

        Map<String,Object> testMap = new HashMap<String, Object>();
        testMap.put("UserVO",userVO);
        testMap.put("AddressVO",addressVO);
        int a= 0;
        testMap.put("res",a);


        System.out.println(dao.signUpUser(testMap));
    }

    @Test
    public void TEST_이메일_찾기_성공(){
        Map<String,Object> testMap = new HashMap<String, Object>();
        testMap.put("phoneNumber","010-1111-0113");
        testMap.put("name","김지호");

        assertEquals("jiho9478@naver.com",dao.findEmail(testMap));
    }
    @Test
    public void TEST_이메일_찾기_실패(){
        Map<String,Object> testMap = new HashMap<String, Object>();
        testMap.put("phoneNumber","010-1111-0113");
        testMap.put("name","김1호");
        assertNull(dao.findEmail(testMap));
    }
    @Test
    public void TEST_비밀번호_업데이트_성공(){
        Map<String,Object> testMap = new HashMap<String, Object>();
        testMap.put("password","FB72A905A57F81E8358E432B7C699FF6987200697366167E4BA962953B072868");
        testMap.put("email","jiho9478@naver.com");

        assertEquals(1,dao.updatePassword(testMap));
    }
    @Test
    public void TEST_비밀번호_업데이트_실패(){
        Map<String,Object> testMap = new HashMap<String, Object>();
        testMap.put("password","FB72A905A57F81E8358E432B7C699FF6987200697366167E4BA962953B072868");
        testMap.put("email","jiho94238@naver.com");

        assertEquals(0,dao.updatePassword(testMap));
    }
    @Test
    public void TEST_회원정보_업데이트_성공(){
        UserVO userVO = new UserVO();
        userVO.setEmail("jiho9478@naver.com");
        userVO.setPassword("$2a$10$AXW7Bb69e5byb7QmkSUIleApBrFSYZvmbikvo2zQKX0euECWak3IW");
        userVO.setPhoneNumber("010-0000-0001");
        userVO.setName("김지호");
        userVO.setProfile("test.png");

        AddressVO addressVO = new AddressVO();
        addressVO.setBaseAddress("서울특별시 관악구 봉천동 791-6");
        addressVO.setDetailAddress("호삼빌딩 6층 IT전문 교육학원 렉토피아");
        addressVO.setPostCode("151050");
        addressVO.setLat(37.483280);
        addressVO.setLng(126.953770);

        Map<String,Object> testMap = new HashMap<String, Object>();
        testMap.put("UserVO",userVO);
        testMap.put("AddressVO",addressVO);
        int a= 0;
        testMap.put("res",a);

        assertEquals(1,dao.updateUser(testMap));
    }
    @Test
    public void TEST_회원정보_업데이트_실패(){
        UserVO userVO = new UserVO();
        userVO.setEmail("jiho9asd478@naver.com");
        userVO.setPassword("$2a$10$AXW7Bb69e5byb7QmkSUIleApBrFSYZvmbikvo2zQKX0euECWak3IW");
        userVO.setPhoneNumber("010-0000-0001");
        userVO.setName("김지호");
        userVO.setProfile("test.png");

        AddressVO addressVO = new AddressVO();
        addressVO.setBaseAddress("서울특별시 관악구 봉천동 791-6");
        addressVO.setDetailAddress("호삼빌딩 6층 IT전문 교육학원 렉토피아");
        addressVO.setPostCode("151050");
        addressVO.setLat(37.483280);
        addressVO.setLng(126.953770);

        Map<String,Object> testMap = new HashMap<String, Object>();
        testMap.put("UserVO",userVO);
        testMap.put("AddressVO",addressVO);
        int a= 0;
        testMap.put("res",a);

        assertEquals(0,dao.updateUser(testMap));


    @Test
    public void TEST_닉네임_중복체크_중복있음(){
        Map<String,Object> testMap = new HashMap<String, Object>();
        testMap.put("nick","테스트닉5");

        assertTrue(dao.signUpDuplicationCheck(testMap));
    }

    @Test
    public void TEST_닉네임_중복체크_중복없음(){
        Map<String,Object> testMap = new HashMap<String, Object>();
        testMap.put("nick","테스트닉500");

        assertFalse(dao.signUpDuplicationCheck(testMap));
    }

    @Test
    public void TEST_이메일_중복체크_중복있음(){
        Map<String,Object> testMap = new HashMap<String, Object>();
        testMap.put("email","cms3136@gmail.com");

        assertTrue(dao.signUpDuplicationCheck(testMap));
    }

    @Test
    public void TEST_이메일_중복체크_중복없음(){
        Map<String,Object> testMap = new HashMap<String, Object>();
        testMap.put("email","cmsasdas@gmail.com");

        assertFalse(dao.signUpDuplicationCheck(testMap));
    }
    @Test
    public void TEST_이메일_닉네임_중복체크_성공(){
        Map<String,Object> testMap = new HashMap<String, Object>();
        testMap.put("email","tt1@naver.com");
        testMap.put("nick","테스트닉1");

        assertTrue(dao.signUpDuplicationCheck(testMap));
    }
    @Test
    public void TEST_이메일_닉네임_중복체크_실패01(){
        Map<String,Object> testMap = new HashMap<String, Object>();
        testMap.put("email","cmsasdas3136@gmail.com");
        testMap.put("nick","테스트닉1");

        assertFalse(dao.signUpDuplicationCheck(testMap));
    }
    @Test
    public void TEST_이메일_닉네임_중복체크_실패02(){
        Map<String,Object> testMap = new HashMap<String, Object>();
        testMap.put("email","cmsasdas3136@gmail.com");
        testMap.put("nick","테스트닉1231231");

        assertFalse(dao.signUpDuplicationCheck(testMap));
    }
 }
