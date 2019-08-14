<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.9.0/css/all.css">
<script src="${pageContext.request.contextPath}/resources/js/navbar.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/navbar.css">
<div class="navbar">
    <div class="logo_img">
    </div>
    <div class="navbar_left ">
        <a href="/storeboard" id="take_charge_btn">보관소 찾기</a>
        <a href="javascript:void(0)">|</a>
        <a href="/hostpage" id="leave_btn">호스트 페이지</a>
      </div>
      <div class="menu navber_right">
          <sec:authorize access="isAuthenticated()">
              <form action="/userlogout" method="post">
                  <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                  <button id="loginout_btn">로그아웃</button>
              </form>
          </sec:authorize>
          <sec:authorize access="isAnonymous()">
              <a href="/login">로그인</a>
          </sec:authorize>
        <a href="javascript:void(0)">|</a>
        <a href="/mypage" id="signup_mypage_btn">마이페이지</a>
        <a class="fas fa-align-justify hide" id="sidebar_btn"></a>
    </div>
</div>
<div class="doc">
    <div class="sidebar">
      <div class="sidehide">
        <i class="fa fa-times" aria-hidden="true"></i>
      </div>
      <div class="author">
          <sec:authorize access="isAuthenticated()">
        <li>
          <a href="/mypage">
            <div class="author_profile">
              <img src="/resources/image/navbar/profile.png" alt="">
            </div>
            <div class="author_name">

                    <sec:authentication property="principal.loginVO.name"/>   <br/>
              <span>
                    <sec:authentication property="principal.loginVO.email"/>
              </span>
            </div>
          </a>
        </li>
          </sec:authorize>
          <sec:authorize access="isAnonymous()">
              <li>
                  <a href="/login">
                      <div class="author_profile">
                          <img src="/resources/image/navbar/profile.png" alt="">
                      </div>
                      <div class="author_name">
                          로그인<br/>
                          <span>아이디가 없으시다면 회원가입을 해주세요.</span>
                      </div>
                  </a>
              </li>
          </sec:authorize>
      </div>

        <div class="main_menu">
            <ul>
                <li><a href="/registhost">호스트 신청하기</a></li>
                <li><a href="/main">홈으로</a></li>
                <li><a href="/storeboard">보관소 찾기</a></li>
                <li><a href="/mypage">마이페이지</a></li>
                <li><a href="">즐겨찾기</a></li>
                <li><a href="">최근 본 장소</a></li>
            </ul>
        </div>
        <div class="sub_menu">
            <ul>
                <li><a href="/hostpage">호스트 페이지</a></li>
                <li><a href="">서비스 가이드</a></li>
                <li><a href="">공지사항</a></li>
                <li><a href="">고객센터</a></li>
            </ul>
        </div>
    </div>
</div>
