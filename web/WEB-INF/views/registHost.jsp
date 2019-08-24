<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>호스트 신청 페이지</title>
    <script src="/resources/js/jquery-3.4.1.min.js"></script>
    <script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
    <script type="text/javascript"
            src="//dapi.kakao.com/v2/maps/sdk.js?appkey=f3520184da1c100939d7dde66edf0534&libraries=services"></script>
    <script src="https://code.jquery.com/jquery-3.2.1.min.js"
            integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4=" crossorigin="anonymous"></script>
    <script src="/resources/js/registHost.js"></script>


    <link href="https://fonts.googleapis.com/css?family=Nanum+Gothic&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="/resources/css/serviceGuide.css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.9.0/css/all.css">

    <link href="/resources/css/agency.min.css" rel="stylesheet">
    <link href="/resources/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="/resources/css/registHost.css">
    <sec:csrfMetaTags/>
</head>
<body>
<%@ include file="navbar.jsp" %>
<div class="bg-light wrapper">

    <div class=" wrapper-container">
        <div class="content">
            <div class="top_component">
                <img src="/resources/image/box_bar.jpg" class="box">
                <div class="box_text">
                    <h3>당신의 장소에 빈 공간이 있나요 ?</h3>
                </div>
            </div>
            <div id="host-guide-section" class="section">
                <section class="page-section" id="services">
                    <div class="container">
                        <div class="row">
                            <div class="col-lg-12 text-center">
                                <h5 class="section-subheading text-uppercase fa-purple-color">host</h5>
                                <h3 class="section-heading text-uppercase">어떻게 사용하나요?</h3>

                            </div>
                        </div>
                        <div class="row text-center">
                            <div class="col-md-4">
          <span class="fa-stack fa-4x fa-circle-margin">
              <h6 class="section-subheading text-uppercase fa-purple-color">step1</h6>
            <i class="fas fa-circle fa-stack-2x fa-purple-color"></i>
              <i class="fas fa-file-signature fa-stack-1x fa-inverse"></i>
          </span>
                                <h4 class="service-heading">호스트 신청하기</h4>
                                <p class="text-muted">호스트 신청 양식에 맞게 작성하여 호스트를 관리자에게 신청합니다.</p>
                            </div>
                            <div class="col-md-4">
          <span class="fa-stack fa-4x fa-circle-margin">
               <h6 class="section-subheading text-uppercase fa-purple-color">step2</h6>
            <i class="fas fa-circle fa-stack-2x fa-purple-color"></i>
            <i class="fas fa-user-edit fa-stack-1x fa-inverse"></i>
          </span>
                                <h4 class="service-heading">글 등록하기</h4>
                                <p class="text-muted">호스트 신청이 완료된 호스트는 양식에 맞게 자신의 보관소 글을 작성합니다.</p>
                            </div>
                            <div class="col-md-4">
          <span class="fa-stack fa-4x fa-circle-margin">
               <h6 class="section-subheading text-uppercase fa-purple-color">step3</h6>
            <i class="fas fa-circle fa-stack-2x fa-purple-color"></i>
              <i class="fas fa-user-check fa-stack-1x fa-inverse"></i>
          </span>

                                <h4 class="service-heading">요청 승인하기</h4>
                                <p class="text-muted">글 등록을 완료한 호스트는 물품보관 요청이 왔을 때 조건에 부합하면 요청을 승인하고 거래를 진행합니다.</p>
                            </div>
                        </div>
                    </div>
                </section>
            </div>
            <div class="title">
                <h1>호스트 신청하기</h1>
            </div>
            <div class="main">


                <form class="form" method="post" action="/registhost">

                    <div class="storage_type">
                        <h4>보관 형태</h4>
                        <div class="storage_type_contents">
                            <ul>
                                <li>
                                    <input type="radio" id="home" name="storage_type_answer" value="home">
                                    <label for="home" class="select"> &nbsp;&nbsp;집</label>
                                </li>
                                <li>
                                    <input type="radio" id="store" name="storage_type_answer" value="store">
                                    <label for="store" class="select"> &nbsp;&nbsp;상가</label>
                                </li>
                                <li>
                                    <input type="radio" id="company" name="storage_type_answer" value="company">
                                    <label for="company" class="select"> &nbsp;&nbsp;회사</label>
                                </li>
                                <li>
                                    <input type="radio" id="other" name="storage_type_answer" value="other">
                                    <label for="other" class="select"> &nbsp;&nbsp;기타</label>
                                    <input type="text" id="other_text" name="other_text" placeholder=" 보관 형태를 입력하세요."
                                           disabled>
                                </li>
                            </ul>
                        </div>
                    </div>
                    <div class="address">
                        <h4>주소</h4>
                        <div class="origin_or_new">
                            <ul>
                                <li>
                                    <input type="radio" id="origin" name="origin_or_new" value="origin">
                                    <label for="origin" class="select"> &nbsp;&nbsp;기존주소</label>
                                </li>
                                <li>
                                    <input type="radio" id="new" name="origin_or_new" value="new">
                                    <label for="new" class="select"> &nbsp;&nbsp;신규주소</label>
                                </li>
                            </ul>
                        </div>
                        <div class="address_combo">
                            <select class="address_combo_box" name="address_combo">
                                <option value="${addressId}">${address}</option>
                            </select>
                        </div>
                        <div class="search_address_btn">
                            <input type="text" id="postcode" name="postcode" placeholder="우편번호를 입력하세요." disabled/>
                            <input type="button" id="search_address_btn" value="우편번호 찾기"/>
                            <input type="text" id="address" name="address" placeholder="주소를 입력하세요." disabled/>
                            <input type="text" id="detailAddress" name="detailAddress" placeholder="상세주소를 입력하세요.">
                        </div>
                    </div>
                    <div class="company_info">
                        <table>
                            <tr>
                                <td><h4>상호명</h4></td>
                                <td><input type="text" class="td" id="company_name" name="company_name"
                                           placeholder=" 상호명를 입력하세요.">
                                </td>
                            </tr>
                            <tr>
                                <td><h4>사업자등록번호</h4></td>
                                <td><input type="text" class="td" id="company_registration_name"
                                           name="company_registration_name"
                                           placeholder=" 사업자등록번호를 입력하세요."></td>
                            </tr>
                            <tr>
                                <td><h4>대표자명</h4></td>
                                <td><input type="text" class="td" id="company_representative_name"
                                           name="company_representative_name"
                                           placeholder=" 대표자명을 입력하세요."></td>
                            </tr>
                        </table>
                    </div>
                </form>
                <div class="apply_btn">
                    <input type="button" id="host_apply_btn" value="호스트 신청하기"/>
                </div>

            </div>

        </div>

    </div>
        <%@ include file="footer.jsp" %>
</div>
</body>
</html>