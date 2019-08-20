<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"  %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>마이페이지</title>
    <link href="https://fonts.googleapis.com/css?family=Nanum+Gothic&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.9.0/css/all.css">
    <link rel="stylesheet" href="/resources/css/reportList.css">
    <script src="/resources/js/jquery-3.4.1.min.js"></script>
    <script src="/resources/js/reportList.js"></script>
</head>

<body>
<%@ include file="navbar.jsp" %>
<div class="popup_wrapper"></div>
<div class="container">
    <div class="header">
    </div>
    <div class="content">
        <div class="mypage_menubar">
            <div class="menubar_title">
                <h1>마이 페이지</h1>
            </div>
            <div class="menubar_subtitle">
                <h3>MY PAGE</h3>
            </div>
            <div class="menubar_list">
                <ul>
                    <li onclick="location.href='/mypage/myinfo'">개인정보</li>
                    <li onclick="location.href='/mypage/message'">메세지함</li>
                    <li onclick="alert('일시적으로 서비스가 중단되었습니다.');">거래내역</li>
                    <li onclick="location.href='/mypage/requestlist/1'">보관해주세요 신청 목록</li>
                    <li onclick="alert('일시적으로 서비스가 중단되었습니다.');">최근 본 보관소</li>
                    <li onclick="alert('일시적으로 서비스가 중단되었습니다.');">즐겨찾는 보관소</li>
                    <li class="current_menu" onclick="location.href='/report/list'">신고 내역</li>
                </ul>
            </div>
        </div>
        <div class="main">
            <div class="main_wrapper">
                <div class="main_title">
                    <h3>신고 내역</h3>
                </div>
                <div class="main_content">

                    <table id="listTable">
                        <tr>
                            <th id="title_target_nick">신고 대상 닉네임</th>
                            <th id="title_content">신고 내용</th>
                            <th id="title_report_date">접수 일자</th>
                            <th id="title_report_time">접수 시간</th>
                            <th id="title_report_state">상태</th>
                        </tr>

                        <c:choose>
                            <c:when test="${fn:length(info.reportList) != 0}">
                                <c:forEach var="reportVO" items="${info.reportList}" begin="0" varStatus="loopCount">
                                    <tr class="rows" value="${reportVO.reportId}"}>
                                        <td>${reportVO.targetUserNick}</td>
                                        <td style="text-align: left;padding-left: 60px;">
                                            <c:choose>
                                                <c:when test="${fn:length(reportVO.content) > 35}">
                                                    ${fn:substring(reportVO.content,0,35).concat("...")}
                                                </c:when>
                                                <c:otherwise>
                                                    ${reportVO.content}
                                                </c:otherwise>
                                            </c:choose>
                                        <td><fmt:formatDate value="${reportVO.reportTime}" pattern="yy-MM-dd" type="date"/></td>
                                        <td><fmt:formatDate value="${reportVO.reportTime}" type="time"/></td>
                                        <td>
                                            <c:choose>
                                                <c:when test="${reportVO.reportState == false}">처리 중</c:when>
                                                <c:otherwise>처리 완료</c:otherwise>
                                            </c:choose>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </c:when>
                            <c:otherwise>
                                <tr>
                                    <td colspan="5">
                                        <div align="center">신고 내역이 존재 하지 않습니다.</div>
                                    </td>
                                </tr>
                            </c:otherwise>
                        </c:choose>
                    </table>
                </div>

                <div class="main_paging">
                    <c:choose>
                        <c:when test="${fn:length(info.reportList) != 0}">
                            <c:choose>
                                <c:when test="${curPage > 1}">
                                    <a href="/report/list/${curPage - 1}"><i class="fas fa-angle-left" style="color:black"></i></a>
                                </c:when>
                                <c:otherwise>
                                    <i class="fas fa-angle-left" style="color:black"></i>
                                </c:otherwise>
                            </c:choose>

                            <c:forEach var="page" begin="1" end="${info.totPageCnt}" step="1">
                                <c:choose>
                                    <c:when test="${curPage ne page}">
                                        <a style="color:black" class="not_cur_page" href="/report/list/${page}">${page}</a>
                                    </c:when>
                                    <c:otherwise>
                                        <a style="color:black" class="cur_page">${page}</a>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                            <c:choose>
                                <c:when test="${curPage < info.totPageCnt}">
                                    <a style="color:black" href="/report/list/${curPage+1}"><i class="fas fa-angle-right"></i></a>
                                </c:when>
                                <c:otherwise>
                                    <i style="color:black" class="fas fa-angle-right"></i>
                                </c:otherwise>
                            </c:choose>
                        </c:when>
                    </c:choose>

                </div>
            </div>
        </div>
    </div>
</div>
<%@ include file="footer.jsp" %>
</div>
</body>
</html>