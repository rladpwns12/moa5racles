<%--
  Created by IntelliJ IDEA.
  User: lctopia1
  Date: 2019-08-12
  Time: 오후 8:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <script src="https://code.jquery.com/jquery-3.2.1.min.js" integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4=" crossorigin="anonymous"></script>
    <script src="/resources/js/authenticatePhonePopup.js"></script>
    <link rel="stylesheet" href="/resources/css/authenticatePhonePopup.css">
    <title>Title</title>
</head>
<body>
    <p>휴대폰 인증</p>
    <input id="input-phone" type="number" name="phoneNumber" autocomplete="off" placeholder="-,공백을 제외한 휴대폰 번호를 입력해주세요." class="main_input" style='IME-MODE: disabled' />
    <button id="submit-btn">인증번호 발송</button>
</body>
</html>
