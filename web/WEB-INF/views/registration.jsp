<%@ page contentType="text/html; charset=UTF-8" language="java" %>
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
    <script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
    <link href="/resources/css/registration.css" rel="stylesheet" media="all">
</head>

<body>
<%@ include file="navbar.jsp" %>
<div class="page-wrapper bg-gra-02 p-t-130 p-b-100 font-poppins">
    <div class="wrapper wrapper--w680">
        <div class="card card-4">
            <div class="card-body">
                <h2 class="title">회원가입</h2>
                <form id="regForm" method="POST">
                    <div class="row row-space">
                        <div class="col-2">
                            <label class="label">이름</label>
                            <div class="input-group">
                                <input id="name" class="input100 input--style-4" type="text" name="name">
                                <span class="focus-input100"></span>
                            </div>
                        </div>
                        <div class="col-2">
                            <label class="label">닉네임</label>
                            <div class="input-group">
                                <input id="nickname" class="input100 input--style-4" type="text" name="nickname">
                                <span class="focus-input100"></span>
                            </div>
                        </div>
                    </div>
                    <div class="row row-space">
                        <div class="col-2">
                            <div class="input-group">
                                <label class="label">이메일</label>
                                <div class="input-group-icon">
                                    <input id="email" class="email input100 input--style-4 js-datepicker" type="text"
                                           name="email">
                                    <span class="long focus-input100"></span>
                                    <i class="zmdi zmdi-calendar-note input-icon js-btn-calendar"></i>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row row-space">
                        <div class="col-2">
                            <label class="label">비밀번호</label>
                            <div class="input-group">
                                <input id="password" class="input100 input--style-4" type="password" name="password">
                                <span class="focus-input100"></span>
                            </div>
                        </div>
                        <div class="col-2">
                            <label class="label">비밀번호 확인</label>
                            <div class="input-group">
                                <input id="password2" class="input100 input--style-4" type="password" name="password">
                                <span class="focus-input100"></span>
                            </div>
                        </div>
                    </div>
                    <div class="row row-space">
                        <div class="col-2">
                            <label class="label">휴대폰 번호</label>
                            <div class="input-group">
                                <input id="phone" class="input100 input--style-4" type="text" name="phone">
                                <span class="focus-input100"></span>
                            </div>
                        </div>
                        <div class="col-2">
                            <div class="input-group">
                                <label class="label">휴대폰 인증</label>
                                <button id="identification" class="btn btn--radius-2 btn--purple" type="button">
                                    휴대폰 인증
                                </button>
                            </div>
                        </div>
                    </div>
                    <div class="row row-space">
                        <div class="col-2">
                            <div class="input-group">
                                <div class="search_address_btn">
                                    <label class="label">우편번호</label>
                                    <input type="text" id="postcode" class="input--style-4" name="postcode"
                                           readonly disabled>
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
                                           disabled readonly>
                                </div>
                                <div class="long search_address_btn">
                                    <input type="text" id="detailAddress" class="input100 input--style-4"
                                           name="detailAddress"
                                           placeholder="상세주소를 입력하세요">
                                    <span class="move long focus-input100"></span>
                                </div>
                                <input type="hidden" id="latitude" class="input--style-4" name="latitude">
                                <input type="hidden" id="longitude" class="input--style-4" name="longitude">
                            </div>
                        </div>
                    </div>
                </form>
                <div class="p-t-15">
                    <button class="btn btn--radius-2 btn--purple" type="submit" onclick="submit()">회원가입</button>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<script src="/resources/js/registration.js"></script>
<%@ include file="footer.jsp" %>
</html>