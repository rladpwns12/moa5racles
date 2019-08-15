<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script src="/resources/js/jquery-3.4.1.min.js"></script>
    <script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
    <script src="https://code.jquery.com/jquery-3.2.1.min.js" integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4=" crossorigin="anonymous"></script>
    <script src="${pageContext.request.contextPath}/resources/js/withdrawal.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/withdrawal.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/requestStoreInfo.css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.9.0/css/all.css">
    <link rel="stylesheet" type="text/css" href="/resources/css/login_util.css">
    <link rel="stylesheet" type="text/css" href="/resources/css/login.css">
    <sec:csrfMetaTags/>
    <title>MOA</title>
</head>
<body>
<%@ include file="navbar.jsp" %>
<div class="popup_wrapper"></div>
<div class="container">
    <div class="content">
        <div class="mypage_menubar">
            <div class="menubar_title">
                <h1>마이 페이지</h1>
            </div>
            <div class="menubar_subtitle">
                <h3>MY PAGE</h3>
            </div>
            <div class="menubar_list">
                <ul>
                    <li onclick="location.href='/mypage/myinfo'">개인정보</li>
                    <li onclick="location.href='/mypage/message'">메세지함</li>
                    <li onclick="moveToTransaction();">거래내역</li>
                    <li onclick="location.href='/mypage/requestlist/1'">보관해주세요 신청 목록</li>
                    <li onclick="moveToLatest();">최근 본 보관소</li>
                    <li onclick="moveToFavorite();">즐겨찾는 보관소</li>
                </ul>
            </div>
        </div>

        <div class="main">
            <div class="main_title">
                <table>
                    <tr>
                        <th id="table_title1" onclick="location.href='/mypage/myinfo'">회원 탈퇴</th>
                        <th id="table_space"></th>
                    </tr>
                </table>
            </div>

            <div class="main_content">
                <div class="limiter">
                    <div class="container-login100">
                        <div class="wrap-login100 p-l-85 p-r-85 p-t-55 p-b-55">
                            <class class="login100-form validate-form flex-sb flex-w" method="post" action="/login">

                                <span class="txt1 p-b-11">현재 비밀번호</span>
                                <div class="wrap-input100 validate-input space" data-validate="비밀번호를 입력하세요">
                                      <span class="btn-show-pass">
                                         <i class="fa fa-eye"></i>
                                      </span>
                                    <input type="password" id="password" class="l_i l_m input100" name="password">
                                    <span class="focus-input100"></span>
                                </div>
                                <p class="txt1 p-b-11">회원탈퇴 약관 동의<input id="agree_info_chk"type="checkbox"></p>
                                <textarea rows="" cols="">
                                    회원 탈퇴 안내
[회원탈퇴 약관]

회원탈퇴 신청 전 안내 사항을 확인 해 주세요.
회원탈퇴를 신청하시면 현재 로그인 된 아이디는 사용하실 수 없습니다.
회원탈퇴를 하더라도, 서비스 약관 및 개인정보 취급방침 동의하에 따라 일정 기간동안 회원 개인정보를 보관합니다.

- 회원 정보
- 대금 결제에 관한 기록
- 소비자 불만 또는 처리 과정에 관한 기록
- 게시판 작성 및 리뷰에 관한 기록

※ 상세한 내용은 사이트 내 개인정보 취급방침을 참고 해 주세요.
                                </textarea><br>

                                <div class="container-login100-form-btn">
                                    <button id="withdrawal_btn" class="l_b login100-form-btn ">회원 탈퇴</button>
                                </div>


                            </class>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </div>
</div>
<%@ include file="footer.jsp" %>
</body>
</html>