package moa.model.dao;

import com.moa.model.dao.ReportDAO;
import com.moa.model.vo.ReportVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
//test 할 경우에는 tomcat이 구동되어 xml파일들이 연동되는 것이 아니기 때문에 따로 연동시켜야한다.
@ContextConfiguration(locations ={
        "file:web/WEB-INF/dispatcher-servlet.xml",
        "file:web/WEB-INF/mybatis-config.xml",
        "file:web/WEB-INF/security-context.xml"
})
public class TestReportDAO {
    @Autowired
    private ReportDAO reportDAO;

    @Test
    public void test_사용자_신고추가_성공(){
        ReportVO reportVO = new ReportVO();
        reportVO.setUserId(5);
        reportVO.setTargetType(0);
        reportVO.setTargetId(0);
        reportVO.setTargetUserNick("관악123");
        reportVO.setContent("신고해요 이분 프로필 사진을 리뷰남긴곳에서 봤는데...프로필...사진이 이상해요...기분이 너무 나쁘네요..");

        boolean res = reportDAO.insertReport(reportVO);
        assertTrue(res);
    }

    @Test
    public void test_사용자_신고추가_실패_해당닉네임없음(){
        ReportVO reportVO = new ReportVO();
        reportVO.setUserId(12);
        reportVO.setTargetType(0);
        reportVO.setTargetId(0);
        reportVO.setTargetUserNick("관악123");
        reportVO.setContent("신고해요 이분 프로필 사진을 리뷰남긴곳에서 봤는데...프로필...사진이 이상해요...기분이 너무 나쁘네요..");

        boolean res = reportDAO.insertReport(reportVO);
        assertFalse(res);
    }
    @Test
    public void TEST_관리자_신고목록보기(){
        System.out.println(reportDAO.selectHostConfirmList());
    }
    @Test
    public void TEST_관리자_신고목록_상세보기(){
        System.out.println(reportDAO.selectHostConfirm(1));
    }
    @Test
    public void TEST_관리자_신고목록_답변하기(){
        Map<String,Object> insertInfo = new HashMap<String, Object>();
        insertInfo.put("reportId",2);
        insertInfo.put("content","매너가 안좋아서 슬퍼요\n\n안녕하세요,상담사 최민성입니다. 먼저 고객님의 슬픔에 저희도 안타깝습니다. 고객님께서 상대방의 매너에 대하여" +
                "슬프다고 하였는데, 저희가 확인한 결과 신고접수고 5회 이상된 이용자로 확인이 되었습니다. 상대방은" +
                "이에 상대방에게 호스트 이용 자격을 박탈하는 제제를 주었습니다. 고객님께서 불편함을 겪음에 다시한번 사과드리고 저희" +
                "MOA를 이용해주셔서 항상 감사합니다. 좋은하루 되세요. 상담사 최민성이었습니다.");
        System.out.println(reportDAO.insertResultReport(insertInfo));
    }
}
