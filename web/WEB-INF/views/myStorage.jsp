<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<c:set var="contextPath" value="/resource" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <script src="https://code.jquery.com/jquery-3.2.1.min.js" integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4=" crossorigin="anonymous"></script>
    <script src="${pageContext.request.contextPath}/resources/js/myStorage.js"></script>
    <link href="https://fonts.googleapis.com/css?family=Nanum+Gothic&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/myStorage.css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.9.0/css/all.css">
    <sec:csrfMetaTags/>
<title>MOA</title>
</head>
<body>
<%@ include file="navbar.jsp" %>
  <div class="container">
      <div class="content">
        <div class="hostpage_menubar">
          <div class="menubar_title">
            <h1>호스트 페이지</h1>
          </div>
          <div class="menubar_subtitle">
            <h3>HOST PAGE</h3>
          </div>
          <div class="menubar_list">
            <ul>
              <li id="request_btn">요청목록</li>
              <li class="current_menu"  id="my_storage_btn">나의 보관소</li>
            </ul>
          </div>
        </div>

        <div class="main">
            <div class="main_title">
              <table>
                <tr>
                  <th id="table_title1">나의 보관소</th>
                  <th id="table_space"></th>
                  <th id="table_btn">
                    <button id="add_address_btn" type="button" name="button">
                      주소지 추가+
                    </button>
                    <button id="add_storage_btn" type="button" name="button" onclick="location.href='${contextPath}/storeboard/keep'">
                      보관소 추가+
                    </button>
                  </th>
                </tr>
              </table>
            </div>
            <div class="main_content">
              
            </div>
            <div class="main_bottom">
                <div class="main_paging">
                    <img src="/resources/image/loading.gif">
                </div>
            </div>

        </div>

      </div>
    </div>
    <%@ include file="footer.jsp" %>
</body>
</html>