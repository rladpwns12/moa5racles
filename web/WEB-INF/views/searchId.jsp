<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>아이디 찾기</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="Colorlib Templates">
    <meta name="author" content="Colorlib">
    <meta name="keywords" content="Colorlib Templates">
    <script src="/resources/js/jquery-3.4.1.min.js"></script>
    <script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
    <script src="https://sdk.accountkit.com/en_US/sdk.js"></script>
    <link href="/resources/css/registration.css" rel="stylesheet" media="all">
</head>

<body>
<div class="page-wrapper bg-gra-02 font-poppins">
    <div class="wrapper wrapper--w680">


        <div class="card_t card-4">


            <div class="add_margin card-body">
                <h2 class="title">아이디 찾기</h2>
                    <div class="row row-space">
                        <div class="col-2">
                            <label class="label">이름을 입력하세요</label>
                            <div class="input-group">

                                <input id="name" class="short2 input100 input--style-4" type="text" name="name">

                                <span class="focus-input100"></span>
                            </div>
                        </div>
                    </div>
                    <div cla ss="row row-space">
                        <div class="col-2">

                            <label class="short label">휴대폰 번호를 입력하세요</label>
                            <div class="input-group">
                                <input id="phone" class="short2 input100 input--style-4" type="text" name="phone">
                                <span class="focus-input100"></span>
                            </div>
                        </div>

                        <div class="col-2">
                            <div class="input-group">

                                <form method="get" action="https://www.accountkit.com/v1.0/basic/dialog/sms_login/">
                                    <input type="hidden" name="app_id" value="2291269470991007">
                                    <input type="hidden" name="redirect" value="http://localhost:8089/searchId">
                                    <input type="hidden" name="state" value="112133">
                                    <input type="hidden" name="fbAppEventsEnabled" value=true>
                                    <input type="hidden" name="debug" value=true>
                                <button id="identification" class="btn btn--radius-2 btn--purple" type="submit">
                                    휴대폰 인증
                                </button>
                                </form>
                            </div>
                        </div>

                    </div>
                <div class="p-t-15">
                    <button class="short btn btn--radius-2 btn--purple" type="submit">아이디 찾기</button>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<script src="/resources/js/registration.js"></script>
</html>