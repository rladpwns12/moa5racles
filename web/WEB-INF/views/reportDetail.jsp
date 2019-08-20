<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <script src="/resources/js/jquery-3.4.1.min.js"></script>
    <script src="/resources/js/reportDetail.js" ></script>
    <link href="https://fonts.googleapis.com/css?family=Nanum+Gothic&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="/resources/css/reportDetail.css"></head>
    <title>신고내역</title>
</head>
<body>
    <div class ="title">
        <table>
            <tr>
                <td class="title_left" id ="title_nick">신고 대상</td>
                <td id="userNick" class="title_right">
                    ${reportVO.targetUserNick}
                </td>
            </tr>
            <tr>
                <td class="title_left">접수 날짜</td>
                <td class="title_right">
                    <fmt:formatDate value="${reportVO.reportTime}" pattern="yy-MM-dd" type="date"/>
                </td>
            </tr>
            <tr>
                <td class="title_left">접수 시간</td>
                <td class="title_right">
                    <fmt:formatDate value="${reportVO.reportTime}" type="time"/>
                </td>
            </tr>
            <tr>
                <td class="title_left">처리 상태</td>
                <td class="title_right">
                    <c:choose>
                        <c:when test="${reportVO.reportState == false}">처리 중</c:when>
                        <c:otherwise>처리 완료</c:otherwise>
                    </c:choose>
                </td>
            </tr>
        </table>
    </div>
    <div class="content">
        <textarea  id="content" name="content" class="content_text" disabled>${reportVO.content}</textarea>
    </div>
    <div class="bottom">
        <button class="confirm_btn">확인</button>
    </div>
</body>
</html>
