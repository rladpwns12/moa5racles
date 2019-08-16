<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:set var="contextPath" value="/resource" />
<html>
<head>
    <meta name="viewport" content="width=device-width, user-scalable=no">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <link rel="stylesheet" href="/resources/css/mLogin.css">
    <link href="https://fonts.googleapis.com/css?family=Nanum+Gothic&display=swap" rel="stylesheet">
    <title>MOA</title>
</head>
<body>
<%@ include file="mNavbar.jsp" %>
<div class="container">
    <form class="login" action="/login" method="post">
        <fieldset>
            <legend>로그인</legend>

            <div class="form-group">
                <label for="exampleInputEmail1">아이디</label>
                <input type="text" class="form-control" name="username" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="관리자 아이디">
            </div>
            <div class="form-group">
                <label for="exampleInputPassword1">비밀번호</label>
                <input type="password" name="password" class="form-control" id="exampleInputPassword1" placeholder="비밀번호">
            </div>

            </fieldset>
            <button type="submit" class="btn btn-primary">로그인</button>

    </form>
</div>
<%--<%@ include file="footer.jsp" %>--%>
</body>
</html>