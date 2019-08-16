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
    <button type="button" class="btn btn-primary btn-sm top-btn"><i class="fas fa-angle-left"></i> 목록으로</button>
    <h2>호스트 승인요청 상세보기</h2>
    <fieldset>
        <legend>호스트민서니님의 신청 내용</legend>

        <div class="form-group">
            <label for="exampleInputEmail1">이메일</label>
            <input readonly disabled  type="text" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" value="cms3136@gmail.com">
        </div>
        <div class="form-group">
            <label for="exampleInputEmail1">닉네임</label>
            <input readonly disabled  type="text" class="form-control input-my-width" id="exampleInputEmail12" aria-describedby="emailHelp" value="호스트민서니">
        </div>
        <div class="form-group">
            <label for="exampleInputEmail1">이름</label>
            <input readonly disabled  type="text" class="form-control input-my-width" id="exampleInputEmail13" aria-describedby="emailHelp" value="최민성">
        </div>
        <div class="form-group">
            <label for="exampleInputEmail1">휴대폰번호</label>
            <input readonly disabled type="text" class="form-control" id="exampleInputEmail14" aria-describedby="emailHelp" value="010-8757-1048">
        </div>
        <div class="form-group">
            <label for="exampleTextarea">주소지(회사)</label>
            <textarea class="form-control" id="exampleTextarea2" rows="3" placeholder="" readonly disabled>경기 성남시 분당구 불정로 6 서버개발팀</textarea>
        </div>

        <div class="form-group">
            <label for="exampleTextarea">승인/거절 사유</label>
            <textarea class="form-control" id="exampleTextarea" rows="10" placeholder="승인 혹은 거절 사유를 적어주세요."></textarea>
        </div>

    </fieldset>
    <div class="btn-position">
        <button type="submit" class="btn btn-primary">거절</button>
        <button type="submit" class="btn btn-primary">승인</button>
    </div>
</div>

</body>
</html>