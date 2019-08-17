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
    <link rel="stylesheet" href="/resources/css/mApproveInformation.css">
    <link href="https://fonts.googleapis.com/css?family=Nanum+Gothic&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.9.0/css/all.css">
    <title>MOA</title>
</head>
<body>
<%@ include file="mNavbar.jsp" %>
<div class="container">
    <button type="button" onclick="location.href='/admin/hostapprove/list'" class="btn btn-primary btn-sm top-btn"><i class="fas fa-angle-left"></i> 목록으로</button>
    <h2>호스트 승인요청 상세보기</h2>
    <fieldset>
        <legend>${nick}의 신청 내용</legend>

        <div class="form-group">
            <label for="email">이메일</label>
            <input readonly disabled  name="email"type="text" class="form-control" id="email" aria-describedby="emailHelp" value="${email}">
        </div>
        <div class="form-group">
            <label for="nick">닉네임</label>
            <input readonly disabled  type="text" name="nick"class="form-control input-my-width" id="nick" aria-describedby="emailHelp" value="${nick}">
        </div>
        <div class="form-group">
            <label for="name">이름</label>
            <input readonly disabled  type="text" name="name"class="form-control input-my-width" id="name" aria-describedby="emailHelp" value="${name}">
        </div>
        <div class="form-group">
            <label for="phoneNumber">휴대폰번호</label>
            <input readonly disabled type="text" name="phoneNumber"class="form-control" id="phoneNumber" aria-describedby="emailHelp" value="${phoneNumber}">
        </div>
        <div class="form-group">
            <label for="address">주소지(${type})</label>
            <textarea class="form-control" name="address" id="address" rows="3" placeholder="" readonly disabled>${baseAddress} ${detailAddress}</textarea>
        </div>

        <div class="form-group">
            <label for="content">승인/거절 사유</label>
            <textarea class="form-control" name="content" id="content" rows="10" placeholder="승인 혹은 거절 사유를 적어주세요."></textarea>
        </div>

    </fieldset>
    <div class="btn-position">
        <button type="submit" name="btnType" value="reject" class="btn btn-primary">거절</button>
        <button type="submit" name="btnType" value="approve" class="btn btn-primary">승인</button>
    </div>
</div>

</body>
</html>