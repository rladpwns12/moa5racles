<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type"  charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
	<script src="${pageContext.request.contextPath}/resources/js/jquery-3.4.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/main.js"></script>

    <link href="/resources/css/agency.min.css" rel="stylesheet">
    <link href="/resources/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Nanum+Gothic&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="/resources/css/serviceGuide.css">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/main.css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.9.0/css/all.css">
<title>welcome to MOA</title>
</head>
<body>
	<%@ include file="navbar.jsp" %>
    <div class="body-container">
	<div class="wrapper">
  	<div class="image-container">
          <div class="slidershow middle">
            <div class="left_paging"> 
              <i class="fas fa-angle-left"></i>
            </div>
            <div class="right_paging">
              <i class="fas fa-angle-right"></i>
            </div>
            <div class="slides">
                <input type="radio" name="r" id="r1" disable>
                <input type="radio" name="r" id="r2" disable >
                <input type="radio" name="r" id="r3" disable>
              <div class="slide s1">
                  <img src="/resources/image/main/slide1.jpg" alt="">
              </div>
              <div class="slide">
                  <img src="/resources/image/main/slide2.jpg" alt="">
              </div>
              <div class="slide">
                  <img src="/resources/image/main/slide3.jpg" alt="">
              </div>
            </div>

            <div class="navigation">
              <label id ="l1" class="bar" ></label>
              <label id ="l2" class="bar" ></label>
              <label id ="l3" class="bar" ></label>
            </div>
          </div>
        </div>

        </div>
        <div id="service-guide-section" class="section">
            <section class="page-section" id="services">
                <div class="container">
                    <div class="row">
                        <div class="col-lg-12 text-center">
                            <h5 class="section-subheading text-uppercase fa-purple-color">inroduce</h5>
                            <h3 class="section-heading text-uppercase">MOA SERVICE</h3>

                        </div>
                    </div>
                    <div class="row text-center">
                        <div onclick="location.href='/serviceguide'" class="col-md-4">
          <span class="fa-stack fa-4x fa-circle-margin">
              <h6 class="section-subheading text-uppercase fa-purple-color">service guide</h6>
            <i class="fas fa-circle fa-stack-2x fa-purple-color"></i>
              <i class="fas fa-question fa-stack-1x fa-inverse"></i>
          </span>
                            <h4 class="service-heading">서비스 가이드<br></h4>
                            <p class="text-muted">모아가 처음이신가요? 저희 서비스에 대하여 친절히 설명해드리겠습니다.<br> <br></p>
                        </div>
                        <div onclick="location.href='/storeboard'" class="col-md-4">
          <span class="fa-stack fa-4x fa-circle-margin">
               <h6 class="section-subheading text-uppercase fa-purple-color">search storage</h6>
            <i class="fas fa-circle fa-stack-2x fa-purple-color"></i>
            <i class="fas fa-search-location fa-stack-1x fa-inverse"></i>
          </span>
                            <h4 class="service-heading">보관소 찾기</h4>
                            <p class="text-muted">보관소를 찾고 계신가요? 지금 당장 근처 보관소를 찾아드리겠습니다.</p>
                        </div>
                        <div onclick="location.href='/registhost'" class="col-md-4">
          <span class="fa-stack fa-4x fa-circle-margin">
               <h6 class="section-subheading text-uppercase fa-purple-color">register host</h6>
            <i class="fas fa-circle fa-stack-2x fa-purple-color"></i>
              <i class="fas fa-user-plus fa-stack-1x fa-inverse"></i>
          </span>

                            <h4 class="service-heading">호스트 신청하기</h4>
                            <p class="text-muted">집에 공간이 남으신가요? 지금 당장 호스트 신청을해서 용돈 벌이를 해보아요!</p>
                        </div>
                    </div>
                </div>
            </section>
        </div>

    </div>



        <%@ include file="footer.jsp" %>
      
</body>
</html>