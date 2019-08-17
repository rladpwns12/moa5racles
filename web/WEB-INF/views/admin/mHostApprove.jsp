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

    <link rel="stylesheet" href="/resources/css/admin/mLogin.css">
    <link href="https://fonts.googleapis.com/css?family=Nanum+Gothic&display=swap" rel="stylesheet">
    <title>MOA</title>
</head>
<body>
<%@ include file="mNavbar.jsp" %>
<div class="container">
    <h2>호스트 신청 목록</h2>
    <table class="table table-hover">
        <thead>
        <tr>
            <th scope="col">닉네임</th>
            <th scope="col">신청날짜</th>
        </tr>
        </thead>
        <tbody>
<%--        <c:if test="${list.length == 0}">--%>
<%--            호스트 신청이 존재하지 않습니다.--%>
<%--        </c:if>--%>
<%--        <c:forEach var="${AdminHostSimpleVO}" begin="0" end="${list.length}" step="1" items="${list}">--%>
<%--            <tr onclick="goToInformation(${AdminHostSimpleVO.userId},${AdminHostSimpleVO.storageType})">--%>
<%--                <td>${AdminHostSimpleVO.nick}</td>--%>
<%--                <td>${AdminHostSimpleVO.applicationDate}<br>${AdminHostSimpleVO.applicationTime}</td>--%>
<%--            </tr>--%>
<%--        </c:forEach>--%>
        <tr onclick="location.href='/admin/hostapprove/info'">
            <td>테스트계정</td>
            <td>19-08-16</td>
        </tr>
        <tr onclick="location.href='/admin/hostapprove/info'">
            <td>테스트계정</td>
            <td>19-08-16</td>
        </tr>
        <tr onclick="location.href='/admin/hostapprove/info'">
            <td>테스트계정</td>
            <td>19-08-16<br>13:00:01</td>
        </tr>
        <tr onclick="location.href='/admin/hostapprove/info'">
            <td>테스트계정</td>
            <td>19-08-16</td>
        </tr>
        <tr onclick="location.href='/admin/hostapprove/info'">
            <td>테스트계정</td>
            <td>19-08-16</td>
        </tr>
        <tr onclick="location.href='/admin/hostapprove/info'">
            <td>테스트계정</td>
            <td>19-08-16</td>
        </tr>
        <tr onclick="location.href='/admin/hostapprove/info'">
            <td>테스트계정</td>
            <td>19-08-16</td>
        </tr>
        <tr onclick="location.href='/admin/hostapprove/info'">
            <td>테스트계정</td>
            <td>19-08-16</td>
        </tr>
        <tr onclick="location.href='/admin/hostapprove/info'">
            <td>테스트계정</td>
            <td>19-08-16</td>
        </tr>
        <tr onclick="location.href='/admin/hostapprove/info'">
            <td>테스트계정</td>
            <td>19-08-16</td>
        </tr>
        <tr onclick="location.href='/admin/hostapprove/info'">
            <td>테스트계정</td>
            <td>19-08-16</td>
        </tr>
        <tr onclick="location.href='/admin/hostapprove/info'">
            <td>테스트계정</td>
            <td>19-08-16</td>
        </tr>
        <tr onclick="location.href='/admin/hostapprove/info'">
            <td>테스트계정</td>
            <td>19-08-16</td>
        </tr>
        <tr onclick="location.href='/admin/hostapprove/info'">
            <td>테스트계정</td>
            <td>19-08-16</td>
        </tr>
        <tr onclick="location.href='/admin/hostapprove/info'">
            <td>테스트계정</td>
            <td>19-08-16</td>
        </tr>
        <tr onclick="location.href='/admin/hostapprove/info'">
            <td>테스트계정</td>
            <td>19-08-16</td>
        </tr>

        <tr onclick="location.href='/admin/hostapprove/info'">
            <td>ABCDEFGHIJKLMNOPQRST</td>
            <td>19-08-16</td>
        </tr>



        </tbody>
    </table>
</div>

</body>
</html>