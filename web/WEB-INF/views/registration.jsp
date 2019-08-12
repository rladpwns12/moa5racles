<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<%--<html lang="en">--%>
<html>
<head>
    <!-- Required meta tags-->
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="Colorlib Templates">
    <meta name="author" content="Colorlib">
    <meta name="keywords" content="Colorlib Templates">

    <!-- Title Page-->
    <title>Registration</title>

    <!-- Icons font CSS-->
    <%--<link href="/resources/vendor/mdi-font/css/material-design-iconic-font.min.css" rel="stylesheet" media="all">
    <link href="/resources/vendor/font-awesome-4.7/css/font-awesome.min.css" rel="stylesheet" media="all">--%>
    <!-- Font special for pages-->
    <%--<link href="https://fonts.googleapis.com/css?family=Poppins:100,100i,200,200i,300,300i,400,400i,500,500i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">--%>

    <!-- Vendor CSS-->
    <%--<link href="/resources/vendor/select2/select2.min.css" rel="stylesheet" media="all">
    <link href="/resources/vendor/datepicker/daterangepicker.css" rel="stylesheet" media="all">--%>

    <!-- Main CSS-->
    <script src="/resources/js/jquery-3.4.1.min.js"></script>
    <script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
    <link href="/resources/css/registration.css" rel="stylesheet" media="all">
</head>

<body>
<%@ include file="navbar.jsp" %>
<div class="page-wrapper bg-gra-02 p-t-130 p-b-100 font-poppins">
    <img src="/resources/image/main/slide3.jpg"/>
    <div class="wrapper wrapper--w680">

        <div class="card card-4">
            <div class="card-body">
                <h2 class="title">Registration</h2>
                <form method="POST">
                    <div class="row row-space">
                        <div class="col-2">
                            <div class="input-group">
                                <label class="label">Name</label>
                                <input id="name" class="input--style-4" type="text" name="name">
                            </div>
                        </div>
                        <div class="col-2">
                            <div class="input-group">
                                <label class="label">Nickname</label>
                                <input id="nickname" class="input--style-4" type="text" name="nickname">
                            </div>
                        </div>
                    </div>
                    <div class="row row-space">
                        <div class="col-2">
                            <div class="input-group">
                                <label class="label">Email</label>
                                <div class="input-group-icon">
                                    <input id="email" class=" email input--style-4 js-datepicker" type="text" name="email">
                                    <i class="zmdi zmdi-calendar-note input-icon js-btn-calendar"></i>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row row-space">
                        <div class="col-2">
                            <div class="input-group">
                                <label class="label">Password</label>
                                <input id="password" class="input--style-4" type="password" name="password">
                            </div>
                        </div>
                        <div class="col-2">
                            <div class="input-group">
                                <label class="label">Repeat password</label>
                                <input id="password2" class="input--style-4" type="password" name="password">
                            </div>
                        </div>
                    </div>
                    <div class="row row-space">
                        <div class="col-2">
                            <div class="input-group">
                                <label class="label">Phone Number</label>
                                <input id="phone" class="input--style-4" type="text" name="phone">
                            </div>
                        </div>
                        <div class="col-2">
                            <div class="input-group">
                                <label class="label">Identification</label>
                                <button id="identification" class="btn btn--radius-2 btn--purple" type="button">
                                    identification
                                </button>
                            </div>
                        </div>
                    </div>
                    <div class="row row-space">
                        <div class="col-2">
                            <div class="input-group">
                                <div class="search_address_btn">
                                    <label class="label">PostCode</label>
                                    <input type="text" id="postcode" class="input--style-4" name="postcode"
                                           placeholder="우편번호를 입력하세요.">
                                </div>
                            </div>
                        </div>
                        <div class="col-2">
                            <div class="input-group">
                                <div class="search_address_btn">
                                    <label class="label">Address Number</label>
                                    <button type="button" id="search_address_btn" class="btn btn--radius-2 btn--purple"
                                            onclick="execDaumPostcode()" value="우편번호 찾기" > Search Number </button>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row row-space">
                        <div class="col-2">
                            <div class="input-group">
                                <div class="search_address_btn">
                                    <input type="text" id="address" class="input--style-4" name="address"
                                           placeholder="주소를 입력하세요.">
                                    <input type="text" id="detailAddress" class="input--style-4" name="detailAddress"
                                           placeholder="상세주소를 입력하세요.">
                                    <input type="hidden" id="latitude" class="input--style-4" name="latitude">
                                    <input type="hidden" id="longitude" class="input--style-4" name="longitude">
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="p-t-15">
                        <button class="btn btn--radius-2 btn--purple" type="submit" onclick="submit()">Submit</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<!-- Jquery JS-->
<%--<script src="/resources/vendor/jquery/jquery.min.js"></script>--%>
<!-- Vendor JS-->
<%--<script src="/resources/vendor/select2/select2.min.js"></script>
<script src="/resources/vendor/datepicker/moment.min.js"></script>
<script src="/resources/vendor/datepicker/daterangepicker.js"></script>--%>

<!-- Main JS-->
<script src="/resources/js/registration.js"></script>
<%--<script src="/resources/js/jquery-3.4.1.min.js"></script>--%>
<%@ include file="footer.jsp" %>
</body><!-- This templates was made by Colorlib (https://colorlib.com) -->

</html>
<!-- end document-->
