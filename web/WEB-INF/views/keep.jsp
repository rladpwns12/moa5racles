<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:set var="contextPath" value="/resources"/>
<% request.setCharacterEncoding("UTF-8"); %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta name="viewport" content="width=device-width, user-scalable=no">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<sec:csrfMetaTags/>
	<title>보관해드립니다 글 등록 페이지</title>
	<link rel="stylesheet" type="text/css" href="/resources/css/keep.css"/>
	<link rel="stylesheet" type="text/css" href="/resources/css/content.css"/>
	<link rel="stylesheet" href="/resources/css/upload.css">
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.9.0/css/all.css">
	<script src="/resources/js/jquery-3.4.1.min.js"></script>
	<script src="/resources/js/upload.js"></script>
	<script src="/resources/js/keep.js"></script>
	<script>
		$(document).on("click", "i[name=add_row_btn]", function () {
			table_product_num++;
			if (table_product_num > 99) {
				alert('더이상 늘릴수 없습니다.');
				table_product_num = 99;
				return;
			}

			var addStaffText = '<tr name="stuff">'
					+ '<td class="table_data">' + (table_product_num+1) + '</td>'
					+ '<td class="table_data">'
					+ '<select class="category_list" name="forbiddenProductList[' + table_product_num + '].category">';
			<c:forEach var="category" items="${map.category}" varStatus="cnt">
			addStaffText += ('<option class="category_list" value="${category}">${category}</option>');
			</c:forEach>

			addStaffText += '</select>' + '</td>';
			addStaffText += '<td class="table_data"><input type="text" class="product_name" name="forbiddenProductList[' + table_product_num + '].product"></td>' + '</tr>';
			var trHtml = $("tr[name=stuff]:last"); //last를 사용하여 trStaff라는 명을 가진 마지막 태그 호출
			trHtml.before(addStaffText); //마지막 trStaff명 뒤에 붙인다
		});
	</script>
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
				<button type="button" class="exit_btn" id="exit_btn" name="exit_btn" onClick="exit()">나가기</button>
			</div>

			<form id="regForm" role="form" class="regForm">
				<div class="content0" id="content0">
					<h2 class="head_0">보관 장소를 선택해주세요</h2>
					<div class="storage_type">
						<select id="storage_address" class="storage_select" name="storageId">
							<c:forEach var="storage" items="${map.storageAddress}" varStatus="cnt">
								<option class="storage"
										value="${storage.storageId}">${storage.baseAddress} ${storage.detailAddress}</option>
							</c:forEach>
						</select>
					</div>
				</div>

				<div class="content1" id="content1" style="display: none;">
					<h2 class="head_1">선호 거래 방식을 선택해주세요</h2>
					<div class="deliver_type">
						<c:forEach var="transaction" items="${map.transaction}" varStatus="cnt">
							<div class="deliver">
								<input type="radio" id="${transaction}" class="radio_btn" name="transactionType"
									   value="${transaction}"/> <label
									for="${transaction}">&nbsp;&nbsp;${transaction}</label>
							</div>
						</c:forEach>
					</div>

					<h2 class="head_1">반려 동물 여부를 선택해주세요</h2>
					<div class="pet_type">
						<div class="pet">
							<input type="radio" id="pet_true" class="radio_btn, c_btn" name="pet_radio" value="1"/>
							<label for="pet_true">&nbsp;유</label>
							<input type="text" id="pet_text" class="pet_text" name="pet" placeholder="종류를 입력하세요"
								   disabled/>
							<input type="radio" id="pet_false" class="radio_btn, c_btn" name="pet_radio" value="0"/>
							<label for="pet_false">&nbsp;무</label>
						</div>
					</div>

					<h2 class="head_1">CCTV 여부를 선택해주세요</h2>
					<div class="cctv_type">
						<div class="cctv">
							<input type="radio" id="CCTV" class="radio_btn, c_btn" name="securityList" value="CCTV"/>
							<label for="CCTV">&nbsp;유</label>
							<input type="radio" id="없음" class="cctv_r radio_btn, c_btn" name="securityList" value="없음"/>
							<label for="없음">&nbsp;무</label>
						</div>
					</div>
				</div>

				<div class="content2" id="content2" style="display:none">
					<h2 class="head_1">맡을 수 없는 물건을 입력해주세요</h2>
					<table class='entrust_product_tb'>
						<thead>
						<tr class="table_row">
							<th class="table_head">번호</th>
							<th class="table_head">카테고리</th>
							<th class="table_head">물건명</th>
						</tr>
						</thead>
						<tr name='stuff'>
							<td class="table_data">1</td>
							<td class="table_data">
								<select class="category_list" name="forbiddenProductList[0].category">
									<c:forEach var="category" items="${map.category}" varStatus="cnt">
										<option class="category_list" value="${category}">${category}</option>
									</c:forEach>
								</select>
							</td>
							<td class="table_data"><input type="text" class="product_name" name="forbiddenProductList[0].product"></td>
						</tr>
						<tr id="stuff" name='stuff'>
							<td colspan="4"><i class="fas fa-plus-circle" name='add_row_btn'></i></td>
						</tr>
					</table>
				</div>

				<div class="content3" id="content3" style="display: none;">
					<h2 class="head_3">보관 기간을 입력해주세요</h2>
					<div class="time_type">
						<c:forEach var="period" items="${map.period}" varStatus="cnt">
							<div class="time">
								<input type="radio" id="${period}" class="radio_btn" name="storagePeriodType"
									   value="${period}"/> <label for="${period}">&nbsp;${period} </label>
							</div>
						</c:forEach>
					</div>

					<h2 class="head_3">보관 가격(5호 박스 기준)을 입력해주세요</h2>
					<div class="price_type">
						<c:forEach var="price" items="${map.price}" varStatus="status">
							<div class="prices">
								<ul class="price_ul">
									<li class="price price_li">${price} 가격</li>
									<li class="price_li"><input type="text" class="i_price" name="detailPrice[${status.index}]" placeholder="금액 입력"
											   numberOnly="true"/> 원
									</li>
								</ul>
							</div>
						</c:forEach>
					</div>
				</div>

				<div class="content4" id="content4" style="display: none;">
					<h2 class="head_4">보관 장소의 사진(최소 2장)을 첨부해주세요</h2>
					<h3 class="head_4_1" style="text-align: center">
						<label for="storeBoard"> <img src="/resources/image/navbar/profile.jpg"/> </label>
						<input type="file" name="uploadFile" id="storeBoard" multiple style="display:none">
					</h3>

					<div class="row">
						<div class="col-lg-12">
							<div class="panel panel-default">
								<div class="panel-body">
									<div class="uploadResult">
										<ul>

										</ul>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>

				<div class="content5" id="content5" style="display: none;">
					<h2 class="head_5">제목을 입력해주세요</h2>
					<div>
						<input type="text" class="post_title" id="post_title" name="title"
							   placeholder="제목을 20자리 이내로 작성하세요" maxlength="20">
					</div>
					<h2 class="head_5">상세 설명을 입력해주세요</h2>
					<textarea class="post_contents" id="post_contents" name="content" rows="14" cols="60"
							  placeholder="맡길 물건에 대한 간단한 설명을 입력해주세요" maxlength="1333"></textarea>
				</div>

				<div class="content6" id="content6" style="display: none;">
					<h2 class="head_6">아래 내용을 확인하시고 등록하기 버튼을 눌러주세요</h2>
					<h3 class="head_6_1">주의하세요!</h3>
					<textarea class="caution" name="caution" rows="5" cols="60" disabled readonly="0">반드시 사이트를 통해 결제를 해야 하며 물건이 훼손될 경우, 호스트에게 책임이 있습니다. 사용자가 시간을 준수하지 않은 경우, 금전적인 책임은 사용자에게 있으며 호스트는 물건에 대한 책임을 지지 않습니다. 물건 보관 만료 일주일 전에 알림 문자가 발송됩니다.</textarea>
					<div class="confirm">
						<ul class="content6_ul">
							<li class="content6_li"><input id="submit_check" type="checkbox" class="check_btn"/></li>
							<li class="content6_li">위 내용을 확인했습니다.</li>
						</ul>
					</div>
					<button type="submit" class="register_btn">맡아주기 글 등록하기</button>
				</div>
			</form>

			<div class="content7" id="content7" style="display: none;">
				<h2 class="head_7"></h2>
				<h4 class="head_7_1">'보관해드립니다' 글 등록이 료되었습니다</h4>
				<button class="finished" onclick="finished()">확인</button>
			</div>

			<div id="bar">
				<div id="percent" style="height: 40px; width: 0%">&nbsp;&nbsp;&nbsp;0%</div>
			</div>
		</div>
	</div>
</div>
<%@ include file="footer.jsp" %>
</body>
</html>
