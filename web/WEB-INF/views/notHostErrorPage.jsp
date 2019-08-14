<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <script src="/resources/js/jquery-3.4.1.min.js"></script>
    <link href="https://fonts.googleapis.com/css?family=Nanum+Gothic&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="/resources/css/errorPage.css">
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
                <div class="box__description-text">호스트 전용 페이지에 <br/>접근하기 위해서는 호스트 신청을<br/> 하여야 합니다.</div>
            </div>

            <a href="/main" target="_blank" class="box__button">모아 홈으로 가기</a>
        </div>
    </div>
</div>
<%@ include file="footer.jsp" %>
</body>
</html>
