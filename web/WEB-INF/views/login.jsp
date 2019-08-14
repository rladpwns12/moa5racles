<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>로그인</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script src="/resources/js/jquery-3.4.1.min.js"></script>
    <link rel="stylesheet" type="text/css" href="/resources/css/login_util.css">
    <link rel="stylesheet" type="text/css" href="/resources/css/login.css">
</head>

<body>
<%@ include file="navbar.jsp" %>
<div class="limiter">
    <div class="container-login100">
        <div class="wrap-login100 p-l-85 p-r-85 p-t-55 p-b-55">
            <form class="login100-form validate-form flex-sb flex-w">
					<span class="login100-form-title p-b-32">
						로그인
					</span>
                <span class="txt1 p-b-11">
						이메일
					</span>
                <div class="wrap-input100 validate-input m-b-36" data-validate="이메일을 입력하세요">
                    <input class="l_i input100" type="text" name="username">
                    <span class="focus-input100"></span>
                </div>
                <span class="txt1 p-b-11">
						비밀번호
					</span>
                <div class="wrap-input100 validate-input" data-validate="비밀번호를 입력하세요">
						<span class="btn-show-pass">
							<i class="fa fa-eye"></i>
						</span>
                    <input class="l_i input100" type="password" name="pass">
                    <span class="focus-input100"></span>
                </div>
                <div class="flex-sb-m w-full p-b-48">
                    <div class="contact100-form-checkbox">
                        <input class="l_i input-checkbox100" id="ckb1" type="checkbox" name="remember-me">
                        <label class="l_l label-checkbox100" for="ckb1">
                            로그인 상태 유지
                        </label>
                    </div>
                    <div>
                        <a class="l_a" href="#" onclick="searchId(); return false;" class="txt3">
                            <%--return false: 링크를 눌렀을 때 페이지 맨 위로 스크롤 되는걸 방지--%>
                            아이디 찾기
                        </a>
                        /
                        <a class="l_a" href="#" onclick="searchPassword(); return false;" class="txt3">
                            비밀번호 찾기
                        </a>
                        /
                        <a class="l_a" href="/registration" class="txt3">
                            회원가입
                        </a>
                    </div>
                </div>
                <div class="container-login100-form-btn">
                    <button class="l_b login100-form-btn">
                        로그인
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>
<div id="dropDownSelect1"></div>
<script src="/resources/js/login.js"></script>
</body>
<%@ include file="footer.jsp" %>
</html>