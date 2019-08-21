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
    <script src="https://code.jquery.com/jquery-3.2.1.min.js" integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4=" crossorigin="anonymous"></script>
    <script src="/resources/js/admin/mHostApprove.js"></script>
    <link rel="stylesheet" href="/resources/css/admin/mLogin.css">
    <link href="https://fonts.googleapis.com/css?family=Nanum+Gothic&display=swap" rel="stylesheet">
    <sec:csrfMetaTags/>
    <title>MOA</title>
</head>
<body>
<%@ include file="mNavbar.jsp" %>
<div class="container">
    <h2>신고 목록</h2>
    <table class="table table-hover">
        <thead>
        <tr>
            <th scope="col">닉네임</th>
            <th scope="col">신고날짜</th>
        </tr>
        </thead>
        <tbody>
            <c:if test="${reportList == null}">
                신고 요청이 존재하지 않습니다.
            </c:if>
            <c:forEach var="vo" begin="0" end="${reportList.size()}" step="1" items="${reportList}">
                <tr onclick="location.href='/admin/report/info?reportId=${vo.reportId}'">
                    <td>${vo.userNick}</td>
                    <td>${vo.reportDate}<br>${vo.reportTime}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>

</body>
</html>