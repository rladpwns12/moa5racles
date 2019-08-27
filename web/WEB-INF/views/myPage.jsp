<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta name="viewport" content="width=device-width, user-scalable=no">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>마이페이지</title>
<link href="https://fonts.googleapis.com/css?family=Nanum+Gothic&display=swap" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@8"></script>
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.9.0/css/all.css">
<link rel="stylesheet" href="/resources/css/myPage.css">
<script src="/resources/js/jquery-3.4.1.min.js"></script>
<script src="/resources/js/myPage.js"></script>
  <script src="/resources/js/upload.js"></script>
  <sec:csrfMetaTags/>
</head>
 <body>
 <%@ include file="navbar.jsp" %>
    <div class="container">
      <div class="header">
      </div>
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
              <li onclick="location.href='/mypage/myinfo'">개인정보</li>
              <li onclick="location.href='/mypage/message'">메세지함</li>
              <li onclick="moveToTransaction();">거래내역</li>
              <li onclick="location.href='/mypage/requestlist/1'">보관해주세요 신청 목록</li>
              <li onclick="moveToLatest();">최근 본 보관소</li>
              <li onclick="moveToFavorite();">즐겨찾는 보관소</li>
              <li class="current_menu" onclick="location.href='/report/list'">신고 내역</li>
            </ul>
          </div>
        </div>
        <div class="main">
          <div class="main_wrapper">
            <div class="main_title">
              <h3>마이 페이지</h3>
            </div>
            <div class="main_context">
              <div class="left_context">
                <div class="profile_image">
                  <div class="uploadDiv">
                    <input type="file" id="user" name="uploadFile" accept="image/*" accept-charset="UTF-8" style="display: none">
                  </div>
                  <img src="" id="profile" style="cursor: pointer">
                </div>
              </div>
              <div class="middle_context">
                <div class="userinfo">
                  <div class="username">
                    <h3>${userName}(<sec:authentication property="principal.loginVO.nick"/>)</h3>
                  </div>
                  <div class="useremail">
                    <h4>${userEmail}</h4>
                  </div>
                </div>
                <div class="update">
                  <button type="button" id="update_btn" name="update_btn"  onclick="location.href='/mypage/myinfo'">수정하기</button>
                </div>

                <div class="request_entrust_list"  onclick="location.href='/mypage/requestlist/1'">
                  <h3>보관 요청 목록 ></h3>
                  <h3 id="request_entrust_list_cnt">${requestCnt}개</h3>
                </div>
              </div>
              <div class="right_context">
                <div class="not_read_message" onclick="location.href='/mypage/message'">
                  <h3>안읽은 메세지 ></h3>
                  <h3 id="not_read_message_cnt">${notReadMessageCnt}개</h3>
                </div>
                <div class="using_storage" onclick="usingStorage();">
                  <h3>이용중인 보관소 ></h3>
                  <h3 id="using_storage_cnt">${usingStorageCnt}개</h3>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>

	 <%@ include file="footer.jsp" %>
      <script>
        function replaceAll(str, searchStr, replaceStr) {
          return str.split(searchStr).join(replaceStr);
        }

        $(document).ready(function(){
          var fileCallPath = encodeURIComponent("${profile.uploadPath}" + "/" + "${profile.uuid}"
                  + "_" + "${profile.fileName}");
          var imgSrc=$('.profile_image')[0].lastElementChild;
            fileCallPath=replaceAll(fileCallPath,"%0", "%5c");
            var str = "/display?fileName=/" + fileCallPath;
          $(imgSrc).attr("src", str);

           imgSrc.addEventListener("error", myFunction);
            function myFunction() {
                var str='/resources/image/navbar/profile.png';
                $(imgSrc).attr("src", str);
            }
        });
      </script>
    </div>
  </body>
</html>