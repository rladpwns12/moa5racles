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
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="Colorlib Templates">
    <meta name="author" content="Colorlib">
    <meta name="keywords" content="Colorlib Templates">
    <script src="/resources/js/jquery-3.4.1.min.js"></script>
    <script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
    <script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=f3520184da1c100939d7dde66edf0534&libraries=services"></script>
    <script src="https://code.jquery.com/jquery-3.2.1.min.js" integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4=" crossorigin="anonymous"></script>
    <script src="${pageContext.request.contextPath}/resources/js/myInfo.js"></script>
    <link href="https://fonts.googleapis.com/css?family=Nanum+Gothic&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/myInfo.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/requestStoreInfo.css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.9.0/css/all.css">
    <link href="/resources/css/registration.css" rel="stylesheet" media="all">
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
                    <li onclick="location.href='/report/list'">신고 내역</li>
                </ul>
            </div>
        </div>

        <div class="main">
            <div class="main_title">
                <table>
                    <tr>
                        <th id="table_title2" onclick="location.href='/mypage/myinfo'">회원 정보</th>
                        <th id="table_title1" onclick="location.href='/mypage/myinfo/changepassword'">비밀번호 변경</th>
                        <th id="table_space">
                            <button id="withdrawal_btn"type="button" name="button" onclick="location.href='/mypage/myinfo/withdrawal'">회원탈퇴</button>
                        </th>
                    </tr>
                </table>
            </div>
            <sec:authorize access="isAuthenticated()">
            <div class="main_content">
                <div class="page-wrapper bg-gra-02 p-t-130 p-b-100 font-poppins">
                    <div class="wrapper wrapper--w680">
                        <div class="card card-4">
                            <div class="card-body">
                                <form id="regForm">
<%--                                    <div class="row row-space">--%>
<%--                                        <div class="col-2">--%>
<%--                                            <label for="photo_main1">--%>
<%--                                                <img id="main_1" name="profile_img" class="photo_main" src="/resources/image/profile.png"/>--%>
<%--                                            </label>--%>
<%--                                            <input type="file" id="photo_main1" class="input_p" name="photo" accept=".jpg, .png"/>--%>
<%--                                        </div>--%>
<%--                                    </div>--%>
                                    <div class="row row-space">
                                        <div class="col-2">
                                            <label class="label">이름</label>
                                            <div class="input-group">
                                                <input id="name" class="input100 input--style-4" type="text" name="name"
                                                value="<sec:authentication property="principal.loginVO.name"/>" readonly disabled>
                                                <span class="focus-input100" ></span>
                                            </div>
                                        </div>
                                        <div class="col-2">
                                            <label class="label">닉네임</label>
                                            <div class="input-group">
                                                <input id="nickname" class="input100 input--style-4" type="text" name="nickname"
                                                value="<sec:authentication property="principal.loginVO.nick"/>" readonly disabled>
                                                <span class="focus-input100"></span>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row row-space">
                                        <div class="col-2">
                                            <label class="label">이메일</label>
                                            <div class="input-group">
                                                <div class="input-group">
                                                    <input id="email" class="input100 input100 input--style-4 js-datepicker" type="text" name="email"
                                                    value="<sec:authentication property="principal.loginVO.email"/>" readonly disabled>
                                                    <span class="long focus-input100"></span>
                                                    <i class="zmdi zmdi-calendar-note input-icon js-btn-calendar"></i>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-2">
                                            <label class="label">비밀번호</label>
                                            <div class="input-group">
                                                <input id="password" class="input100 input--style-4" type="password" name="password">
                                                <span class="focus-input100"></span>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row row-space">
                                        <div class="col-2">
                                            <label class="label">휴대폰 번호</label>
                                            <div class="input-group">
                                                <input id="phone" class="input100 input--style-4" type="text" name="phone"
                                                value="<sec:authentication property="principal.loginVO.phoneNumber"/>" readonly disabled>
                                                <span class="focus-input100"></span>
                                            </div>
                                        </div>
                                        <div class="col-2">
                                            <div class="input-group">
                                                <label class="label">휴대폰 인증</label>
                                                <button id="identification" class="btn btn--radius-2 btn--purple" onclick="openAuthenticatePhone()" type="button">
                                                    휴대폰 번호 변경
                                                </button>
                                            </div>
                                        </div>

                                    </div>
                                    <div class="row row-space">
                                        <div class="col-2">
                                            <div class="input-group">
                                                <div class="search_address_btn">
                                                    <label class="label">우편번호</label>
                                                    <input type="text" id="postcode" class="input100 input--style-4" name="postcode"
                                                           value="${address.postCode}" readonly disabled>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="col-2">
                                            <div class="input-group">
                                                <div class="search_address_btn">
                                                    <label class="label">우편번호 찾기</label>
                                                    <button type="button" id="search_address_btn" class="btn btn--radius-2 btn--purple"
                                                            onclick="execDaumPostcode()" value="우편번호 찾기"> 우편번호 찾기
                                                    </button>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row row-space">
                                        <div class="col-2">
                                            <div class="input-group">
                                                <div class="search_address_btn">
                                                    <input type="text" id="address" class="input--style-4" name="address"
                                                           value="${address.baseAddress}" disabled readonly>
                                                </div>
                                            </div>

                                            <div class="input-group">
                                                <div class="search_address_btn">
                                                    <input type="text" id="detailAddress" class="input--style-4 input100 detailAddress"
                                                           name="detailAddress" value="${address.detailAddress}">
                                                    <span class="move long focus-input100"></span>
                                                </div>
                                                <input type="hidden" id="latitude" class="input--style-4" name="latitude">
                                                <input type="hidden" id="longitude" class="input--style-4" name="longitude">
                                            </div>
                                        </div>
                                    </div>
                                </form>
                                <div class="p-t-15">
                                    <button id="submit_btn"class="btn btn--radius-2 btn--purple" type="button">수정완료</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            </sec:authorize>

        </div>

    </div>
</div>
<%@ include file="footer.jsp" %>
</body>
</html>