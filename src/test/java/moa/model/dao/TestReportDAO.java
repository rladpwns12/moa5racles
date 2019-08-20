package moa.model.dao;

import com.moa.model.dao.ReportDAO;
import com.moa.model.vo.ReportVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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
}
