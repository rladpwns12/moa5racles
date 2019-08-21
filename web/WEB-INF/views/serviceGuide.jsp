<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type"  charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link href="/resources/css/agency.min.css" rel="stylesheet">
    <link href="/resources/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Nanum+Gothic&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="/resources/css/serviceGuide.css">


    <script src="/resources/js/jquery-3.4.1.min.js"></script>
    <title>MOA-service guide</title>
</head>
<body>

<%@ include file="navbar.jsp" %>
<div class="body-container">

    <div id="introduce-section" class="section">
        <section class="bg-light page-section introduce-section" id="services">
            <div class="container ">
                <div class="row">
                    <div class="col-lg-12 text-center">
                        <h1 class="section-heading text-uppercase">MOA?</h1>
                        <h4 class="section-subheading text-muted">개인과 개인의 물품 보관 중개 사이트입니다.</h4>
                    </div>
                </div>
            </div>
        </section>
    </div>

    <div id="service-guide-section" class="section">
        <section class="page-section" id="services">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12 text-center">
                        <h5 class="section-subheading text-uppercase fa-purple-color">summary</h5>
                        <h3 class="section-heading text-uppercase">어떻게 사용하나요?</h3>

                    </div>
                </div>
                <div class="row text-center">
                    <div class="col-md-4">
          <span class="fa-stack fa-4x fa-circle-margin">
              <h6 class="section-subheading text-uppercase fa-purple-color">step1</h6>
            <i class="fas fa-circle fa-stack-2x fa-purple-color"></i>
              <i class="fas fa-store-alt fa-stack-1x fa-inverse"></i>
          </span>
                        <h4 class="service-heading">맡길 보관소 선택하기</h4>
                        <p class="text-muted">계절 지난 옷,가구,침구,여행 중 캐리어,등의 물건을 맡길 보관소를 찾아 선택합니다.</p>
                    </div>
                    <div class="col-md-4">
          <span class="fa-stack fa-4x fa-circle-margin">
               <h6 class="section-subheading text-uppercase fa-purple-color">step2</h6>
            <i class="fas fa-circle fa-stack-2x fa-purple-color"></i>
            <i class="fas fa-people-carry fa-stack-1x fa-inverse"></i>
          </span>
                        <h4 class="service-heading">물품 맡기기</h4>
                        <p class="text-muted">이용자는 호스트에게 물건을 맡깁니다. 물건을 맡기는 방식은 택배/직거래 방식이 있습니다.</p>
                    </div>
                    <div class="col-md-4">
          <span class="fa-stack fa-4x fa-circle-margin">
               <h6 class="section-subheading text-uppercase fa-purple-color">step3</h6>
            <i class="fas fa-circle fa-stack-2x fa-purple-color"></i>
              <i class="fas fa-boxes fa-stack-1x fa-inverse"></i>
          </span>

                        <h4 class="service-heading">물품 찾기</h4>
                        <p class="text-muted">기한이 만료된 물품을 이용자가 호스트로부터 물건을 찾아갑니다. 물건을 찾아가는 방식은 마찬가지로 택배/직거래 방식이 있습니다.</p>
                    </div>
                </div>
            </div>
        </section>
    </div>

    <div id="service-guide1-section" class="section">
        <section class="bg-light page-section" id="services">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12 text-center">
                        <h5 class="section-subheading text-uppercase fa-purple-color">step 1</h5>
                        <h3 class="section-heading text-uppercase">맡길 보관소 선택하기</h3>
                        <h6 class="section-subheading text-muted"></h6>
                    </div>
                </div>
                <div class="row text-center">
                    <div class="col-md-4">
          <span class="fa-stack fa-4x fa-margin">
              <i class="fas fa-box-open fa-stack-1x fa-purple-color"></i>
          </span>
                        <h4 class="service-heading">말길 물품 선택</h4>
                        <p class="text-muted">계절 지난 옷,가구,침구,여행 중 캐리어,등의 자신이 맡길 물건을 선택합니다.</p>
                    </div>
                    <div class="col-md-4">
          <span class="fa-stack fa-4x fa-margin">
              <i class="fas fa-search-location fa-stack-1x fa-purple-color"></i>
          </span>

                        <h4 class="service-heading">보관소 찾기</h4>
                        <p class="text-muted">이용자는 자신의 물품을 맡아줄수 있는 적합한 보관소를 찾습니다.</p>
                    </div>
                    <div class="col-md-4">
          <span class="fa-stack fa-4x fa-margin">
            <i class="fas fa-hand-holding fa-stack-1x fa-purple-color"></i>
          </span>
                        <h4 class="service-heading">요청하기</h4>
                        <p class="text-muted">보관소를 찾은 이용자는 해당 보관소의 호스트에게 맡기기를 요청합니다.</p>
                    </div>
                </div>
            </div>
        </section>
    </div>

    <div id="service-guide2-section" class="section">
        <section class="bg-light page-section" id="services">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12 text-center">
                        <h5 class="section-subheading text-uppercase fa-purple-color">step 2</h5>
                        <h3 class="section-heading text-uppercase">물품 맡기기</h3>
                        <h6 class="section-subheading text-muted"></h6>
                    </div>
                </div>
                <div class="row text-center">
                    <div class="col-md-6">
          <span class="fa-stack fa-4x fa-margin">
            <i class="fas fa-handshake fa-stack-1x fa-purple-color"></i>
          </span>
                        <h4 class="service-heading">요청수락/결제완료</h4>
                        <p class="text-muted">호스트가 이용자의 요청을 수락하면 이용자는 금액에 맞게 결제를 합니다.</p>
                    </div>
                    <div class="col-md-6 ">
          <span class="fa-stack fa-4x fa-margin">
            <i class="fas fa-people-carry fa-stack-1x fa-purple-color"></i>
          </span>
                        <h4 class="service-heading">물건 맡기기</h4>
                        <p class="text-muted">택배/직거래 중 선택하여 물품을 호스트에게 전달합니다.</p>
                    </div>
                </div>
            </div>
        </section>
    </div>

    <div id="service-guide3-section" class="section">
        <section class="bg-light page-section" id="services">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12 text-center">
                        <h5 class="section-subheading text-uppercase fa-purple-color">step 3</h5>
                        <h3 class="section-heading text-uppercase">물품 찾기</h3>
                        <h6 class="section-subheading text-muted"></h6>
                    </div>
                </div>
                <div class="row text-center">
                    <div class="col-md-4">
          <span class="fa-stack fa-4x fa-margin">
            <i class="fas fa-envelope-open-text fa-stack-1x fa-purple-color"></i>
          </span>
                        <h4 class="service-heading">보관만료 물품 안내</h4>
                        <p class="text-muted">보관이 만료된 물품이 있으면 호스트는 이용자에게 만료 3일~7일 전에 알려줍니다.</p>
                    </div>
                    <div class="col-md-4">
          <span class="fa-stack fa-4x fa-margin">
            <i class="fas fa-people-carry fa-stack-1x fa-purple-color"></i>
          </span>
                        <h4 class="service-heading">물품 찾기</h4>
                        <p class="text-muted">이용자는 호스트로부터 물품을 찾습니다. 이때 물품을 맡기는 방법과 마찬가지로 택배/직거래가 있습니다.</p>
                    </div>
                    <div class="col-md-4">
          <span class="fa-stack fa-4x fa-margin">
            <i class="fas fa-user-edit fa-stack-1x fa-purple-color"></i>
          </span>
                        <h4 class="service-heading">후기 작성</h4>
                        <p class="text-muted">물품을 되찾은 이용자는 자신이 맡긴 보관소에 후기를 작성합니다.</p>
                    </div>
                </div>
            </div>
        </section>
    </div>

    <div id="service-strength-section" class="section">
        <section class="page-section" id="services">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12 text-center">
                        <h5 class="section-subheading text-uppercase fa-purple-color">strength</h5>
                        <h1 class="section-heading text-uppercase">저렴하고 제약이 없습니다.</h1>
                        <h6 class="section-subheading text-muted"></h6>
                    </div>
                </div>
                <div class="row text-center">
                    <div class="col-md-3">
                      <span class="fa-stack fa-4x fa-margin">
                        <i class="far fa-square fa-stack-2x fa-another-purple-color"></i>
                        <i class="fas fa-won-sign fa-stack-1x fa-another-purple-color"></i>
                      </span>
                        <h4 class="service-heading">저렴한 가격</h4>
                        <p class="text-muted">호스트 간의 경쟁을 통해 다른 보관소 업체보다 훨씬 저렴한 가격에 이용 할 수 있습니다.</p>
                    </div>
                    <div class="col-md-3">
                      <span class="fa-stack fa-4x fa-margin">
                        <i class="far fa-square fa-stack-2x fa-another-purple-color"></i>
                        <i class="fas fa-user-shield fa-stack-1x fa-another-purple-color"></i>
                      </span>
                        <h4 class="service-heading">엄격한 호스트 관리</h4>
                        <p class="text-muted">호스트 신청을 하면 MOA 관리자가 호스트로써의 자격이 충분한지 면밀히 검토후 승인을 합니다.</p>
                    </div>
                    <div class="col-md-3">
                      <span class="fa-stack fa-4x fa-margin">
                        <i class="far fa-square fa-stack-2x fa-another-purple-color"></i>
                        <i class="fas fa-search-location fa-stack-1x fa-another-purple-color"></i>
                      </span>
                        <h4 class="service-heading">다양한 선택지</h4>
                        <p class="text-muted">다양한 보관소들을 확인하실 수 있고, 평소 다른 보관소에서 맡기기 힘들었던 물품도 맡아줄 보관소를 찾을 수 있습니다.</p>
                    </div>
                    <div class="col-md-3">
                      <span class="fa-stack fa-4x fa-margin">
                        <i class="far fa-square fa-stack-2x fa-another-purple-color"></i>
                        <i class="far fa-clock fa-stack-1x fa-another-purple-color"></i>
                      </span>
                        <h4 class="service-heading">폭넓은 시간</h4>
                        <p class="text-muted">9~18시 회사에 있을 시간에 운영되었던 보관소와 달리 호스트와의 협의를 통해 자신의 물품을 맡길 시간을 선택 할 수 있습니다.</p>
                    </div>
                </div>
            </div>
        </section>
    </div>

    <div id="picture-section" class="section">
        <header class="masthead">
            <div class="container">
                <div class="intro-text">
                    <div class="intro-lead-in"></div>
                    <div class="intro-heading text-uppercase"><br><br></div>
                    <a class="btn btn-primary btn-xl text-uppercase js-scroll-trigger" href="/registhost">호스트 알아보기/신청하기</a>
                </div>
            </div>
        </header>
    </div>
</div>
<%@ include file="footer.jsp" %>
</body>
</html>
