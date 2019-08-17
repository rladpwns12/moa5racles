
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>
<html>
<head>
    <title>로그인</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script src="/resources/js/jquery-3.4.1.min.js"></script>
    <link rel="stylesheet" type="text/css" href="/resources/css/login_util.css">
    <link rel="stylesheet" type="text/css" href="/resources/css/login.css">
    <sec:csrfMetaTags/>

</head>

<body>
<%@ include file="navbar.jsp" %>
<div class="limiter">
    <div class="container-login100">
        <div class="wrap-login100 p-l-85 p-r-85 p-t-55 p-b-55">
            <div class="login_form">
            <form class="login100-form validate-form flex-sb flex-w" action="/user-login" method="post">
					<span class="login100-form-title p-b-32">
						로그인
					</span>
                <span class="txt1 p-b-11">
						이메일
					</span>
                <div class="wrap-input100 validate-input m-b-36" data-validate="이메일을 입력하세요">
                    <input type="text" id="email" class="l_i input100" name="username" value="${username}">
                    <span class="focus-input100"></span>
                </div>
                <span class="txt1 p-b-11">
						비밀번호
					</span>
                <div class="wrap-input100 validate-input space" data-validate="비밀번호를 입력하세요">
						<span class="btn-show-pass">
							<i class="fa fa-eye"></i>
						</span>
                    <input type="password" id="password" class="l_i l_m input100" name="password">
                    <span class="focus-input100"></span>
                </div>
                <div class="flex-sb-m w-full p-b-48">
                    <div class="contact100-form-checkbox">
                        <input class="l_i" id="maintain1" type="checkbox" name="remember-me">
                        <label for="maintain1" class="l_l maintain">
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
                <c:if test="${not empty ERRORMSG}">
                    <div style="color:red">
                        <p> ${ERRORMSG }</p>
                    </div>
                </c:if>
                <div class="container-login100-form-btn">
                    <input type="submit" class="l_b login100-form-btn" value="로그인" />
                </div>
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            </form>
            </div>
        </div>
    </div>
</div>
<div id="dropDownSelect1"></div>

<script src="/resources/js/login.js"></script>
</body>
<%@ include file="footer.jsp" %>
</html>