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
    <sec:csrfMetaTags/>
    <title>Title</title>
</head>
<body>
    <div class="container">
        <p>휴대폰 인증</p>
        <input onKeyup="this.value=this.value.replace(/[^0-9]/g,'');"id="phone-number"class="input-phone" type="text" name="phoneNumber" autocomplete="off" placeholder="-,공백을 제외한 휴대폰 번호를 입력해주세요." class="main_input" style='IME-MODE: disabled' />
        <button id="submit-btn">인증번호 발송</button>
        <input oninput="maxLengthCheck(this)" maxlength="4" onKeyup="this.value=this.value.replace(/[^0-9]/g,'');"class="input-phone" type="text" name="phoneNumber" id="check-number"autocomplete="off" placeholder="연락처로 전송받은 4자리 숫자를 입력해주세요." class="main_input" style='IME-MODE: disabled' />
        <button  id="check-btn">인증번호 확인</button>
    </div>
</body>
</html>
