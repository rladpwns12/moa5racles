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
    <button type="button" onclick="location.href='/admin/hostapprove/list'" class="btn btn-primary btn-sm top-btn"><i class="fas fa-angle-left"></i> 목록으로</button>
    <h2>이용 내역 상세보기</h2>
    <fieldset>
        <legend>${requestInfo.NICK}의 이용 내역</legend>

        <div class="form-group">
            <label for="email">이메일</label>
            <input readonly disabled  name="email"type="text" class="form-control" id="email" aria-describedby="emailHelp" value="${requestInfo.EMAIL}">
        </div>
        <div class="form-group">
            <label for="nick">닉네임</label>
            <input readonly disabled  type="text" name="nick"class="form-control input-my-width" id="nick" aria-describedby="emailHelp" value="${requestInfo.NICK}">
        </div>
        <div class="form-group">
            <label for="name">이름</label>
            <input readonly disabled  type="text" name="name"class="form-control input-my-width" id="name" aria-describedby="emailHelp" value="${requestInfo.NAME}">
        </div>
        <c:if test="${requestInfo.STORAGE_TYPE == '회사' || requestInfo.STORAGE_TYPE == '상가'}">
            <div class="form-group">
                <label for="address">대표자 명 : ${requestInfo.BUSINESS_REPRESENTATIVE} / 사업자 번호(${requestInfo.BUSINESS_NAME})</label>
                <input readonly disabled type="text" name="businessLicenseNum"class="form-control" id="businessLicenseNum" aria-describedby="emailHelp" value="${requestInfo.BUSINESS_LICENSE_NUM}">
            </div>
        </c:if>
        <c:choose>
            <c:when test="${requestInfo.STORAGE_TYPE == '기타'}">
                <div class="form-group">
                    <label for="address">주소지(${requestInfo.STORAGE_TYPE} : ${requestInfo.OTHER_CONTENTS} )</label>
                    <textarea class="form-control" name="address" id="address2" rows="3" placeholder="" readonly disabled>${requestInfo.BASEADDRESS} ${requestInfo.DETAILADDRESS}</textarea>
                </div>
            </c:when>
            <c:otherwise>
                <div class="form-group">
                    <label for="address">주소지(${requestInfo.STORAGE_TYPE} )</label>
                    <textarea class="form-control" name="address" id="address" rows="3" placeholder="" readonly disabled>${requestInfo.BASEADDRESS} ${requestInfo.DETAILADDRESS}</textarea>
                </div>
            </c:otherwise>
        </c:choose>

        <div class="form-group">
            <label for="content">승인/거절 사유</label>
            <textarea class="form-control" name="content" id="content" rows="10" placeholder="승인 혹은 거절 사유를 적어주세요."></textarea>
        </div>
    </fieldset>
    <div class="btn-position">
        <button onclick="refuse(${requestInfo.USERID})" type="submit" name="btnType" value="reject" class="btn btn-primary">거절</button>
        <button onclick="approve(${requestInfo.USERID})" type="submit" name="btnType" value="approve" class="btn btn-primary">승인</button>
    </div>
</div>

</body>
</html>