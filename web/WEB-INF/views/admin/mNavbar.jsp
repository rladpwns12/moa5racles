<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>
<script src="https://code.jquery.com/jquery-3.2.1.min.js" integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4=" crossorigin="anonymous"></script>
<script src="/resources/js/admin/bootstrap.min.js" ></script>
<script src="/resources/js/admin/mNavbar.js"></script>
<link rel="stylesheet" href="/resources/css/admin/bootstrap.css">
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="#"><div class="logo_img"></div></a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarColor03" aria-controls="navbarColor03" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarColor03">
        <ul class="navbar-nav mr-auto"></ul>
        <div class="form-inline my-2 my-lg-0">
            <ul class="navbar-nav mr-auto">
                <sec:authorize access="isAuthenticated()">
                    <form id="logout" class=".logout" action="/admin/logout" method="post">
                    </form>
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                        <li class="nav-item" onclick="">
                            <a onclick="formSubmit()" href="#" class="nav-link">로그아웃</a>
                        </li>

                </sec:authorize>
                <li class="nav-item">
                    <a id="" class="nav-link" href="#">호스트 인증 관리<span class="sr-only">(current)</span></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">신고 관리</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">이용 내역 관리</a>
                </li>
            </ul>
         </div>
    </div>
</nav>