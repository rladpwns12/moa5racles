<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
    <title>회원가입</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="Colorlib Templates">
    <meta name="author" content="Colorlib">
    <meta name="keywords" content="Colorlib Templates">
    <script src="/resources/js/jquery-3.4.1.min.js"></script>
    <script src="https://sdk.accountkit.com/en_US/sdk.js"></script>
    <script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
    <script type="text/javascript"
            src="//dapi.kakao.com/v2/maps/sdk.js?appkey=f3520184da1c100939d7dde66edf0534&libraries=services"></script>
    <link href="/resources/css/registration.css" rel="stylesheet" media="all">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.9.0/css/all.css">
    <sec:csrfMetaTags/>
</head>

<body>
<%@ include file="navbar.jsp" %>
<div class="page-wrapper bg-gra-02 p-b-100 font-poppins">
    <div class="wrapper wrapper--w680">
        <div class="card card-4">
            <div class="card-body">
                <h2 class="title">회원가입</h2>
                <div class="row row-space">
                    <div class="col-2">
                        <label class="label">이름을 입력해주세요</label>
                        <div class="input-group">
                            <input id="name" class="input100 input--style-4" type="text" name="name">
                            <span class="focus-input100"></span>
                        </div>
                    </div>
                    <div class="col-2">
                        <label class="label">닉네임을 입력해주세요</label>
                        <div class="input-group">
                            <input id="nickname" class="input100 input--style-4" type="text" name="nickname"
                                   onfocus="emptyNickname()">
                            <span class="focus-input100"></span>
                        </div>
                    </div>
                </div>
                <div class="row row-space">
                    <div class="col-2">
                        <div class="input-group">
                            <label class="label">이메일을 입력해주세요</label>
                            <div class="input-group-icon">
                                <input type="text" id="email" class="email input100 input--style-4"
                                       name="email" onfocus="emptyEmail()">
                                <span class="long focus-input100"></span>
                                <i class="zmdi zmdi-calendar-note input-icon js-btn-calendar"></i>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row row-space">
                    <div class="col-2">
                        <label class="label">비밀번호를 입력해주세요</label>
                        <div class="input-group">
                            <input id="password" class="input100 input--style-4" type="password"
                                   onfocus="emptyEmail()">
                            <span class="focus-input100"></span>
                        </div>
                    </div>
                    <div class="col-2">
                        <label class="label">비밀번호를 다시 한번 입력해주세요</label>
                        <div class="input-group">
                            <input id="password2" class="input100 input--style-4" type="password" name="password">
                            <span class="focus-input100"></span>
                        </div>
                    </div>
                </div>
                <div class="row row-space">
                    <div class="col-2">
                        <label class="label">휴대폰 인증 버튼을 눌려주세요</label>
                        <div class="input-group">
                            <input id="phone" class="input100 input--style-4" type="text" name="phone">
                            <span class="focus-input100"></span>
                        </div>
                    </div>
                    <div class="col-2">
                        <label class="label">&nbsp;</label>
                        <div class="input-group">
                            <%--<form method="get" action="https://www.accountkit.com/v1.0/basic/dialog/sms_login/">
                                <input type="hidden" name="app_id" value="2291269470991007">
                                <input type="hidden" name="redirect" value="http://localhost:8089/registration">
                                <input type="hidden" name="state" value="112133">
                                <input type="hidden" name="fbAppEventsEnabled" value=true>
                                <input type="hidden" name="debug" value=true>--%>
                            <button id="identification"
                                    onclick="smsCheck()" class="btn btn--radius-2 btn--purple" type="submit">
                                휴대폰 인증
                            </button>
                            </form>
                        </div>
                    </div>
                </div>
                <div class="row row-space">
                    <div class="col-2">
                        <div class="input-group">
                            <div class="search_address_btn">
                                <label class="label">우편번호 찾기 버튼을 눌러주세요</label>
                                <input type="text" id="postcode" class="input100 input--style-4" name="postcode"
                                       readonly>
                            </div>
                        </div>
                    </div>
                    <div class="col-2">
                        <label class="label">&nbsp;</label>
                        <div class="input-group">
                            <div class="search_address_btn">
                                <button type="button" id="search_address_btn" class="btn btn--radius-2 btn--purple"
                                        onclick="execDaumPostcode()" value="우편번호 찾기"> 우편번호 찾기
                                </button>
                            </div>
                        </div>
                    </div>
                    <div class="col-2">
                        <div class="input-group">
                            <div class="search_address_btn">
                                <input type="text" id="address" class="input--style-4" name="address"
                                       readonly>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row row-space">
                    <div class="col-2">
                        <div class="input-group">
                            <label class="label">상세 주소를 입력해주세요</label>
                            <div class="input-group-icon">
                                <input type="text" id="detailAddress" class="detailAddress input100 input--style-4"
                                       name="detailAddress" placeholder="상세주소를 입력하세요">
                                <span class="long focus-input100"></span>
                            </div>
                            <input type="hidden" id="lat" class="input--style-4" name="latitude">
                            <input type="hidden" id="lng" class="input--style-4" name="longitude">
                        </div>
                    </div>
                </div>
                <div class="add_m_t row row-space">
                    <input type="checkbox" id="confirm1"/>
                    <label for="confirm1" class="confirm">&nbsp;&nbsp;MOA 이용약관 동의</label>
                    <a href="/termsOfService" class="agree"> [이용약관 보기] </a>
                </div>
                <div class="add_m_t row row-space">
                    <input type="checkbox" id="confirm2"/>
                    <label for="confirm2" class="confirm">&nbsp;&nbsp;위치정보 이용약관 동의</label>
                    <a href="termsOfService" class="agree"> [이용약관 보기] </a>
                </div>
                <div class="p-t-15">
                    <button class="btn btn--radius-2 btn--purple" type="button" onclick="submit()">회원가입</button>
                </div>
            </div>
        </div>
    </div>
</div>
</div>
</body>
<script src="/resources/js/registration.js"></script>
<%@ include file="footer.jsp" %>
</html>
