<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:set var="contextPath" value="/resources"/>
<% request.setCharacterEncoding("UTF-8"); %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, user-scalable=no">
    <title>보관해주세요 글 작성 페이지</title>

    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.9.0/css/all.css">
    <link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <link rel="stylesheet" type="text/css" href="${contextPath}/css/entrust.css"/>
    <link rel="stylesheet" type="text/css" href="/resources/css/keep.css"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="//netdna.bootstrapcdn.com/bootstrap/3.0.1/js/bootstrap.min.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
    <script type="text/javascript">
        $(document).on("click", "i[name=add_row_btn]", function () {
            table_product_num++;
            if (table_product_num > 99) {
                alert('더이상 늘릴수 없습니다.');
                table_product_num = 99;
                return;
            }
            var addStaffText = '<tr name="stuff">'
                + '<td class="table_data">' + table_product_num + '</td>'
                + '<td class="table_data">'
                + '<select class="category_list" name="productList[' + table_product_num + '].category">';
            <c:forEach var="category" items="${categoryList}" varStatus="cnt">
            addStaffText += ('<option class="category_list" value="${category}">${category}</option>');
            </c:forEach>
            addStaffText += '</select>' + '</td>';
            addStaffText += '<td class="table_data"><input type="text" class="product_name" name="productList[' + table_product_num + '].product"></td>';
            addStaffText += '<td class="table_data"><input type="number" class="product_cnt" name="productList[' + table_product_num + '].productCnt" min=0 max=100 value=0></td>' + '</tr>';
            var trHtml = $("tr[name=stuff]:last"); //last를 사용하여 trStaff라는 명을 가진 마지막 태그 호출
            trHtml.before(addStaffText); //마지막 trStaff명 뒤에 붙인다
        });
    </script>
    <sec:csrfMetaTags/>
</head>

<body>
<%@ include file="navbar.jsp" %>
<div class="wrap">
    <div id="left_side" class="left_side" style="display: none;">
        <i class="fas fa-angle-left fa-5x" onclick="prevForm()"></i>
    </div>
    <div id="right_side" class="right_side">
        <i class="fas fa-angle-right fa-5x" onclick="nextForm()"></i>
    </div>

    <div class="main">
        <div class="main_board">
            <div id="exit_button">
                <button type="button" class="exit_btn" id="exit_btn" name="exit_btn" onClick="exit()">
                    나가기
                </button>
            </div>
            <form id="regForm" role="form" class="regForm">
                <div class="content1" id="content1">
                    <h2 class="head_1">맡기실 물건의 물품명과 개수를 입력해주세요.</h2>
                    <table class='entrust_product_tb'>
                        <thead>
                        <tr class="table_row">
                            <th class="table_head1">번호</th>
                            <th class="table_head2">카테고리</th>
                            <th class="table_head3">물건명</th>
                            <th class="table_head4">물건 개수</th>
                        </tr>
                        </thead>
                        <tr name='stuff'>
                            <td class="table_data">1</td>
                            <td class="table_data">
                                <select class="category_list" name="productList[0].category">
                                    <c:forEach var="category" items="${categoryList}" varStatus="cnt">
                                        <option class="category_list" value="${category}">${category}</option>
                                    </c:forEach>
                                </select>
                            </td>
                            <td class="table_data"><input type="text" class="product_name"
                                                          name="productList[0].product"></td>
                            <td class="table_data"><input type="number" class="product_cnt"
                                                          name="productList[0].productCnt" min=0 max=100 value=0></td>
                        </tr>
                        <tr name='stuff'>
                            <td colspan="4"><i class="fas fa-plus-circle" name='add_row_btn'></i></td>
                        </tr>
                    </table>
                </div>
                <div class="content2" id="content2" style="display: none;">
                    <div class="content2_header"> 예상 5호 박스 사용 개수를 입력해주세요.
                        <i class="far fa-question-circle"><span><img src="${contextPath}/image/5호박스.png"/></span></i>
                    </div>
                    <div class="product_size_1">
                        5호 박스 개수 <input type="number" id="box" class="sizeCnt" name="productSize[0]" min=0 max=100
                                        value=0>개
                    </div>
                    <input type="hidden" id="firstMultiple" value="${multiplePriceList[0]}">

                    <div class="content2_header">박스에 들어가지 않는 물건이라면 이곳에 입력해주세요.</div>
                    <div class="product_size_2">
                        자전거 이상 크기 <input type="number" id="bicycle" class="sizeCnt" name="productSize[1]" min=0 max=100
                                         value=0>개
                        <input type="hidden" id="secondMultiple" value="${multiplePriceList[1]}">
                    </div>
                    <div class="product_size_3">
                        싱글 침대 이상 크기 <input type="number" id="bed" class="sizeCnt" name="productSize[2]" min=0 max=100
                                           value=0>개
                        <input type="hidden" id="thirdMultiple" value="${multiplePriceList[2]}">
                    </div>
                </div>
                <div class="content3" id="content3" style="display: none;">
                    <div class="date_class">
                        <h2 class="head_3">맡길 기간을 입력해주세요.</h2>
                        <input type="date" id="start_datepicker" name="startDate" placeholder="시작 일자">
                        <input type="date" id="end_datepicker" name="endDate" placeholder="종료 일자">
                    </div>
                    <h2 class="head_3">흥정을 하고싶다면 직접 가격을 입력해주세요.</h2>
                    <div class="price_class">
                        <div class="price_e1">
                            <input type="radio" class="radio_btn selectPrice" id="expected_price"
                                   name="price.selectPrice" value="measuredPrice" checked="checked"/>
                            <label for="expected_price"> 측정 가격 </label>
                            <input type="text" id="measured" class="i_price" name="price.measuredPrice" value="0"
                                   readonly numberOnly="true">
                            <input type="hidden" id="day" value="${detailPriceList[0]}">
                            <input type="hidden" id="week" value="${detailPriceList[1]}">
                            <input type="hidden" id="month" value="${detailPriceList[2]}">
                            <input type="hidden" id="halfYeaer" value="${detailPriceList[3]}">
                            <input type="hidden" id="year" value="${detailPriceList[4]}">
                        </div>
                        <div class="price_e2">
                            <input type="radio" class="radio_btn selectPrice" id="bargain_price"
                                   name="price.selectPrice" value="bargainPrice"/>
                            <label for="bargain_price"> 흥정 가격 </label>
                            <input type="text" id="bargain" class="i_price" name="price.bargainPrice" value="0" readonly
                                   numberOnly="true" style="background: #EBEBE4">
                        </div>
                    </div>
                </div>
                <div class="content4" id="content4" style="display: none;">
                    <h2 class="head_5">원하는 거래방식을 선택해주세요</h2>
                    <div class="deals">
                        <div class="deal_1">
                            <input type="radio" class="radio_btn" id="direct_deal" name="transactionWay" value="직거래"/>
                            <label for="direct_deal"> 직거래 </label>
                        </div>
                        <div class="deal_2">
                            <input type="radio" class="radio_btn" id="delivery" name="transactionWay"
                                   value="택배"/>
                            <label for="delivery"> 택배 </label>
                        </div>
                    </div>
                    <h2 class="head_5">추가로 전달할 내용을 입력해주세요</h2>
                    <textarea id="post_contents" class="post_contents" name="content" rows="12" cols="60"
                              placeholder="맡길 물건에 대한 간단한 설명을 입력해주세요" maxlength="1000"></textarea>
                </div>
                <div class="content5" id="content5" style="display: none;">
                    <jsp:include page="upload.jsp"/>
                </div>
                <div class="content6" id="content6" style="display: none;">
                    <h2 class="head_6">아래 내용을 확인하시고 등록하기 버튼을 눌러주세요</h2>
                    <h3 class="head_6_1">주의하세요!</h3>
                    <textarea class="caution" rows="5" cols="60" disabled readonly="0">호스트와 직접 거래를 하시지 마시고 반드시 사이트를 통해 결제를 하시길 바랍니다. 또한 물건 보관 연장이 필요할 경우, 반드시 사이트를 통해 연장을 하시길 바랍니다. 물품 보관 만료 일주일전에 문자를 발송해 드립니다. </textarea>
                    <div class="confirm">
                        <ul class="content6_ul">
                            <li class="content6_li"><input id="submit_check" type="checkbox" class="check_btn"/></li>
                            <li class="content6_li">위 내용을 확인했습니다.</li>
                        </ul>
                    </div>
                    <button type="submit" class="register_btn">물품 보관 요청하기</button>
                </div>
            </form>
            <div class="content7" id="content7" style="display: none;">
                <h2 class="head_7"></h2>
                <h4 class="head_7_1">물건 맡기기 신청을 완료하였습니다.<br> 호스트가 확인하는대로 알림을 드리겠습니다</h4>
                <button class="finished" onClick="finished()">확인</button>
            </div>
            <div id="bar">
                <div id="percent" style="height: 40px; width: 0%">&nbsp;&nbsp;&nbsp;0%</div>
            </div>
        </div>
    </div>
</div>
<script src="/resources/js/entrust.js"></script>
<%@ include file="footer.jsp" %>
</body>
</html>