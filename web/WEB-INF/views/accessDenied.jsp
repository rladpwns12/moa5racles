<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<spring:message code="accessDenied.host" var="host"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <script src="/resources/js/jquery-3.4.1.min.js"></script>
    <link href="https://fonts.googleapis.com/css?family=Nanum+Gothic&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="/resources/css/errorPage.css">
    <meta name="viewport" content="width=device-width, user-scalable=no">
    <script src="/resources/js/errorPage.js"></script>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>에러페이지</title>
</head>
<body>

<%@ include file="navbar.jsp" %>
<div class="back_box">
    <div class="box">
        <div class="box__ghost">
            <div class="symbol"></div>
            <div class="symbol"></div>
            <div class="symbol"></div>
            <div class="symbol"></div>
            <div class="symbol"></div>
            <div class="symbol"></div>

            <div class="box__ghost-container">
                <div class="box__ghost-eyes">
                    <div class="box__eye-left"></div>
                    <div class="box__eye-right"></div>
                </div>
                <div class="box__ghost-bottom">
                    <div></div>
                    <div></div>
                    <div></div>
                    <div></div>
                    <div></div>
                </div>
            </div>
            <div class="box__ghost-shadow"></div>
        </div>

        <div class="box__description">
            <div class="box__description-container">
                <div class="box__description-title">접근 권한이 없습니다.</div>
                <div class="box__description-text">
                    <c:choose>
                        <c:when test="${errorMessage ne null}">
                            <c:out value="${errorMessage}"/>
                        </c:when>
                        <c:otherwise>
                            <spring:message code="accessDenied.default"/>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
            <a href="/main" class="box__button">모아 홈으로 가기</a>
            <c:if test="${errorMessage eq host}">
                <a href="/registhost" class="box__button">호스트 신청하기</a>
            </c:if>
        </div>
    </div>
</div>
<%@ include file="footer.jsp" %>
</body>
</html>
