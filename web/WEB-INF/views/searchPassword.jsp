<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>비밀번호 찾기</title>
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
<div class="page-wrapper bg-gra-02 font-poppins">
    <div class="wrapper wrapper--w680">
        <div class="card_t card-4">
            <div class="add_margin card-body">
                <h2 class="title">비밀번호 찾기</h2>
                <form id="regForm" method="POST">
                    <div class="row row-space">
                        <div class="col-2">
                            <label class="label">이름을 입력해주세요</label>
                            <div class="input-group">
                                <input id="name" class="short2 input100 input--style-4" type="text" name="name" onfocus="emptyName()">
                                <span class="focus-input100"></span>
                            </div>
                        </div>
                    </div>
                    <div class="row row-space">
                        <div class="col-2">
                            <label class="label">이메일을 입력해주세요</label>
                            <div class="input-group">
                                <input id="email" class="short2 input100 input--style-4" type="text" name="email" onfocus="emptyEmail()">
                                <span class="focus-input100"></span>
                            </div>
                        </div>
                    </div>
                    <div cla ss="row row-space">
                        <div class="col-2">

                            <label class="short label">휴대폰 인증 버튼을 눌러주세요</label>
                            <div class="input-group">
                                <input id="phone" class="short2 input100 input--style-4" type="text" name="phone" readonly>
                            </div>
                        </div>
                        <div class="col-2">
                            <div class="input-group">
                                <span class="focus-input100"></span>
                                <button id="identification" class="btn btn--radius-2 btn--purple" type="button">
                                    휴대폰 인증
                                </button>
                            </div>
                        </div>
                    </div>
                </form>
                <div class="p-t-15">
                    <button class="short btn btn--radius-2 btn--purple" type="button" onclick="submit()">확인</button>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<script src="/resources/js/searchPassword.js"></script>
</html>