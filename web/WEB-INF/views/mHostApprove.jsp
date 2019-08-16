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

    <link rel="stylesheet" href="/resources/css/mMain.css">
    <link href="https://fonts.googleapis.com/css?family=Nanum+Gothic&display=swap" rel="stylesheet">
    <title>MOA</title>
</head>
<body>
<%@ include file="mNavbar.jsp" %>
<div class="container">
    <table class="table table-hover">
        <thead>
        <tr>
            <th scope="col">아이디</th>
            <th scope="col">전화번호</th>
            <th scope="col">ㅅ</th>
        </tr>
        </thead>
        <tbody>
        <tr class="table-active">
            <th scope="row">Active</th>
            <td>Column content</td>
            <td>Column content</td>

        </tr>
        <tr>
            <th scope="row">Default</th>
            <td>Column content</td>
            <td>Column content</td>

        </tr>
        <tr class="table-primary">
            <th scope="row">Primary</th>
            <td>Column content</td>
            <td>Column content</td>

        </tr>
        <tr class="table-secondary">
            <th scope="row">Secondary</th>
            <td>Column content</td>
            <td>Column content</td>

        </tr>
        <tr class="table-success">
            <th scope="row">Success</th>
            <td>Column content</td>
            <td>Column content</td>

        </tr>
        <tr class="table-danger">
            <th scope="row">Danger</th>
            <td>Column content</td>
            <td>Column content</td>

        </tr>
        <tr class="table-warning">
            <th scope="row">Warning</th>
            <td>Column content</td>
            <td>Column content</td>

        </tr>
        <tr class="table-info">
            <th scope="row">Info</th>
            <td>Column content</td>
            <td>Column content</td>

        </tr>
        <tr class="table-light">
            <th scope="row">Light</th>
            <td>Column content</td>
            <td>Column content</td>

        </tr>
        <tr class="table-dark">
            <th scope="row">Dark</th>
            <td>Column content</td>
            <td>Column content</td>

        </tr>
        </tbody>
    </table>
</div>

</body>
</html>