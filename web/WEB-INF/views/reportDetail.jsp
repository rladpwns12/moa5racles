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
                <td class="title_left" >신고 대상</td>
                <td class="title_right">
                    ${info.reportVO.targetUserNick}
                </td>
            </tr>
            <tr>
                <td class="title_left">접수 날짜</td>
                <td class="title_right">
                    <fmt:formatDate value="${info.reportVO.reportTime}" pattern="yy-MM-dd" type="date"/>
                </td>
            </tr>
            <tr>
                <td class="title_left">접수 시간</td>
                <td class="title_right">
                    <fmt:formatDate value="${info.reportVO.reportTime}" pattern="HH:mm" type="time"/>
                </td>
            </tr>
            <tr>
                <td class="title_left">처리 상태</td>
                <td class="title_right">
                    <c:choose>
                        <c:when test="${info.reportVO.reportState == false}">처리 중</c:when>
                        <c:otherwise>처리 완료</c:otherwise>
                    </c:choose>
                </td>
            </tr>
            <c:if test="${info.reportResultVO ne null}">
                <tr>
                    <td class="title_left" >답변 시간</td>
                    <td class="title_right">
                        <fmt:formatDate value="${info.reportResultVO.resultTime}" pattern="yy-MM-dd HH:mm" type="both"/>
                    </td>
                </tr>
            </c:if>
        </table>
    </div>
    <c:choose>
        <c:when test="${info.reportResultVO eq null}">
            <div class="content">
                <textarea name="content" class="content_text" style="height:360px;"disabled>${info.reportVO.content}</textarea>
            </div>
        </c:when>
        <c:otherwise>
            <div class="content">
                <textarea name="content" class="content_text" disabled>${info.reportVO.content}</textarea>
            </div>
            <div class="title_left" style="margin-left: 30px;">답변 내용</div>
            <div class="content">
                <textarea name="content" class="content_text" disabled>${info.reportResultVO.content}</textarea>
            </div>
        </c:otherwise>
    </c:choose>
    <div class="bottom">
        <button class="confirm_btn">확인</button>
    </div>
</body>
</html>
