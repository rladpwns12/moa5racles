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
    <script src="${pageContext.request.contextPath}/resources/js/changePassword.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/changePassword.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/requestStoreInfo.css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.9.0/css/all.css">
    <link rel="stylesheet" type="text/css" href="/resources/css/login_util.css">
    <link rel="stylesheet" type="text/css" href="/resources/css/login.css">
    <link href="https://fonts.googleapis.com/css?family=Nanum+Gothic&display=swap" rel="stylesheet">
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
                    <li class="current_menu" onclick="location.href='/mypage/myinfo'">개인정보</li>
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
                        <th id="table_title1" onclick="location.href='/mypage/myinfo'">회원 정보</th>
                        <th id="table_title2" onclick="location.href='/mypage/myinfo/changepassword'">비밀번호 변경</th>

                        <th id="table_space">
                            <button id="withdrawal_btn"type="button" name="button" onclick="location.href='/mypage/myinfo/withdrawal'">회원탈퇴</button>
                        </th>
                    </tr>
                </table>
            </div>

            <div class="main_content">
                <div class="limiter">
                    <div class="container-login100">
                        <div class="wrap-login100 p-l-85 p-r-85 p-t-55 p-b-55">
                            <div class="login100-form validate-form flex-sb flex-w">
                                <span class="txt1 p-b-11">현재 비밀번호</span>
                                <div class="wrap-input100 validate-input space" data-validate="비밀번호를 입력하세요">
                                      <span class="btn-show-pass">
                                         <i class="fa fa-eye"></i>
                                      </span>
                                    <input type="password" id="password" class="l_i l_m input100" name="password">
                                    <span class="focus-input100"></span>
                                </div>
                                <span class="txt1 p-b-11">새 비밀번호</span>
                                <div class="wrap-input100 validate-input space" data-validate="비밀번호를 입력하세요">
                                    <span class="btn-show-pass">
                                        <i class="fa fa-eye"></i>
                                    </span>
                                    <input type="password" id="new_password1" class="l_i l_m input100" name="password">
                                    <span class="focus-input100"></span>
                                </div>
                                <span class="txt1 p-b-11">새 비밀번호 확인</span>
                                <div class="wrap-input100 validate-input space" data-validate="비밀번호를 입력하세요">
                                    <span class="btn-show-pass">
                                        <i class="fa fa-eye"></i>
                                     </span>
                                    <input type="password" id="new_password2" class="l_i l_m input100" name="password">
                                    <span class="focus-input100"></span>
                                </div>
                                <div class="container-login100-form-btn">
                                    <button class="l_b login100-form-btn" id="change_password_btn">비밀번호 변경</button>
                                </div>
                            </div>
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