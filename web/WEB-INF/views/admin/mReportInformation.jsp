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
    <script src="/resources/js/admin/mApproveInformation.js"></script>
    <link rel="stylesheet" href="/resources/css/admin/mApproveInformation.css">
    <link href="https://fonts.googleapis.com/css?family=Nanum+Gothic&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.9.0/css/all.css">
    <sec:csrfMetaTags/>
    <title>MOA</title>
</head>
<body>
<%@ include file="mNavbar.jsp" %>
<div class="container">
    <button type="button" onclick="location.href='/admin/report/list'" class="btn btn-primary btn-sm top-btn"><i class="fas fa-angle-left"></i> 목록으로</button>
    <h2>신고 내역 상세보기</h2>
    <fieldset>
        <legend>${requestInfo.NICK}의 신고 내용</legend>
        <div class="form-group">
            <label for="reportDate">작성 일자</label>
            <input readonly disabled  type="text" name="reportDate"class="form-control input-my-width" id="reportDate" aria-describedby="emailHelp" value="${requestInfo.REPORTDATE}">
        </div>
        <div class="form-group">
            <label for="targetEmail">신고 대상</label>
            <input readonly disabled  name="targetEmail"type="text" class="form-control" id="targetEmail" aria-describedby="emailHelp" value="${requestInfo.TARGETEMAIL}">
        </div>
        <div class="form-group">
            <label for="targetType">신고 유형</label>
            <input readonly disabled  type="text" name="targetType"class="form-control input-my-width" id="targetType" aria-describedby="emailHelp" value="${requestInfo.TARGETTYPE}">
        </div>
        <div class="form-group">
            <label for="phoneNumber">휴대폰번호</label>
            <input readonly disabled type="text" name="phoneNumber"class="form-control" id="phoneNumber" aria-describedby="emailHelp" value="${requestInfo.PHONENUMBER}">
        </div>
        <div class="form-group">
            <label for="content">신고 내역</label>
            <textarea class="form-control" name="content" id="content" rows="10" placeholder="신고 내용입니다."></textarea>
        </div>
    </fieldset>
    <div class="btn-position">
        <button onclick="refuse(${requestInfo.USERID},'report')" type="submit" name="btnType" value="reject" class="btn btn-primary">취소하기</button>
        <button onclick="approve(${requestInfo.USERID},'report')" type="submit" name="btnType" value="approve" class="btn btn-primary">답변하기</button>
    </div>
</div>

</body>
</html>