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
                <div class="box__description-title">500 ERROR<br/></div>
                <div class="box__description-text">서버 오류입니다.<br/>관리자에게 문의하세요.<br/><br/></div>
            </div>

            <a href="/main" target="_blank" class="box__button">모아 홈으로 가기</a>
        </div>
    </div>
</div>
<%@ include file="footer.jsp" %>
</body>
</html>
