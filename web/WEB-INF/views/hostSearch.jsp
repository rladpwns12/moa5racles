<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, user-scalable=no">
<title>보관해주세요</title>
<script src="/resources/js/jquery-3.4.1.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js" ></script>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=f3520184da1c100939d7dde66edf0534&libraries=services"></script>
<link rel="stylesheet" href="/resources/css/navbar.css">
<link href="https://fonts.googleapis.com/css?family=Nanum+Gothic&display=swap" rel="stylesheet">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.9.0/css/all.css">
<link rel="stylesheet" href="/resources/css/hostSearch.css">
	<sec:csrfMetaTags/>
<script>
	var markers = [];
	var infowindows = [];
	var container = document.getElementById('map');
	function initMap() {
		var container = document.getElementById('map');
						//맵 api 세팅
		var options = {
			center: new kakao.maps.LatLng(37.484061, 126.955548),
			level: 3
		}

		map = new kakao.maps.Map(container, options);	//맵 기본 세팅
		var mapTypeControl = new kakao.maps.MapTypeControl();
		map.addControl(mapTypeControl, kakao.maps.ControlPosition.TOPRIGHT);
		var zoomControl = new kakao.maps.ZoomControl();
		map.addControl(zoomControl, kakao.maps.ControlPosition.RIGHT);
	}
	function removeMarkerInfo() {
		for ( var i = 0; i < markers.length; i++ ) {
			markers[i].setMap(null);
		}
		for ( var i = 0; i < infowindows.length; i++ ) {
			infowindows[i].close();
		}
		markers = [];
		infowindows=[];
	}
	function searchBtn(){ 			//검색 버튼 클릭시
		removeMarkerInfo();
		let address=$(".search_input").val();	//검색 키워드
		if(address ==""){
			alert("검색어를 입력해주세요");
			return;
		}
		$.ajax									//카카오 api get
		({
			url: "https://dapi.kakao.com/v2/local/search/keyword.json?query="+address,
			headers: { 'Authorization': 'KakaoAK ea031870cc4a7a31182ea665a1eb62fc'},
			type: 'GET',
			beforeSend: function (xhr) {
				xhr.setRequestHeader("Accept", "application/json; odata=verbose");
			}
		}).done(function(data)
		{
			if(data.documents=="")		//만일 검색결과가 없을시
			{
				alert("검색된 정보가 없습니다");
				return;
			}
			else
			{
				var lan=data.documents[0].y;
				var log=data.documents[0].x;
				var coords = new kakao.maps.LatLng(lan, log);
				var marker = new kakao.maps.Marker
				({
					map: map,
					position: coords
				});
				markers.push(marker);
			}
			map.setCenter(coords);
			search(lan,log);

		});
	}
	function search(lan,log){//검색 함수
		let catAry = new Array();
		let i = 0;
		for(let iv=1 ; iv<11;iv++) { 				//카테고리 체크
			if($('#ct'+iv).prop('checked'))
				catAry[i++]=($('#ct'+iv).val());
		}
		jQuery.ajaxSettings.traditional = true;
		let form={
			category:catAry,
			distance:($("#range-slider__range").val()),
			filter:($("#filter").val()),
			storageType:($("#storageType").val()),
			transactionType : ($("#transactionType").val()),
			storagePeriodType:($("#storagePeriodType").val()),
			securityFacility:($("#securityFacility").val()),
			petFlag:($("#petFlag").val()),
			latitude:lan,
			longitude:log
		}
		$('#selection_content_id1').empty();
		var token = $("meta[name='_csrf']").attr("content");
		var header = $("meta[name='_csrf_header']").attr("content");
		$.ajax("storeboard/Search",{
			type:"GET",
			data : form,
			beforeSend: function (xhr) {
				xhr.setRequestHeader("AJAX", true);
				xhr.setRequestHeader(header, token);
				xhr.setRequestHeader("Accept", "application/json; odata=verbose");
			}

		}).then(function(data,status){
			if(status=="success"){
				if(data.length==0){
					var noContent = '<div class="noContent">' +
							'<i class="fas fa-search-minus" style="font-size:45px; margin-bottom: 20px;"></i>' +
							' <br>조건에 맞는 방이 없습니다.<br>조건을 바꿔 검색해보세요.</div>';
					($('#selection_content_id1').append(noContent));
				}
				var positions = new Array();
				for(let i=0;i<data.length;i++){
					let div = $('<div />', {id:"article"+data[i].articleNum,class : 'room_select',onclick:"roomSelect("+data[i].articleNum+");"}).appendTo($('#selection_content_id1'));

					var profile=data[i].profile;
					var fileCallPath = encodeURIComponent(profile.uploadPath + "/" + profile.uuid +"_"+profile.fileName);
					$('<img>', {
						src: '/display?fileName=/' + fileCallPath,
						alt: 'profile',
						onerror : 'this.src="/resources/image/loading.gif"'
					}).css({
						marginBottom : '5px'
					}).appendTo(div);

					$('<span/>',{id:'title',style: "font-size:18px;font-weight:normal;",text:data[i].storageType+"        "}).appendTo(div);
					for(let j=0;j<parseInt(data[i].starPointAvg.toFixed(0));j++) {
						$('<i/>', {class: 'fas fa-star', style: 'font-size:15px; float: right;' +
									'margin-top: 10px; margin-right: 5px;'}).appendTo(div);
					}
					/*$('<span/>',{text:" : "+parseInt(data[i].starPointAvg.toFixed(0))+" 개"}).appendTo(div);*/
					$('<br>').appendTo(div);
					$('<i/>',{class:'fas fa-coins',style:'color:#423257;'}).appendTo(div);
					$('<span/>',{id:'title', text:" "+data[i].detailPrice+"원"}).appendTo(div);
					$('</div>').appendTo(div);
					$('<br>').appendTo(div);
					/*$('<div>',{id:'word'}).appendTo(div);*/
					$('<span/>',{id:'word',class:'distance',text:data[i].distanceResult+"km 이내 "}).appendTo(div);
					$('<br>').appendTo(div);
					$('<i/>',{class:'far fa-comment-dots',style:'color: #423257;'}).appendTo(div);
					$('<span/>',{id:'word',text:" "+data[i].totReviewCnt+" 개 "}).appendTo(div);

					$('<i/>',{class:'far fa-calendar-alt',style:'color: #423257;margin-left:15px'}).appendTo(div);
					$('<span/>',{id:'word', text:" "+data[i].storagePeriodTypeId}).appendTo(div);
					$('<br>').appendTo(div);
					$('<i/>',{class:'fas fa-user',style:'color: #423257;'}).appendTo(div);
					$('<span/>',{id:'word',text:" "+data[i].nickName}).appendTo(div);

					positions ={ title:'클릭시 이동합니다.',latlng: new kakao.maps.LatLng(data[i].latitude,data[i].longitude) }
					var imageSrc = "http://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png";
					// 마커 이미지의 이미지 크기 입니다
					var imageSize = new kakao.maps.Size(24, 35);
					// 마커 이미지를 생성합니다
					var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize);
					// 마커를 생성합니다
					var marker = new kakao.maps.Marker({
						map: map, // 마커를 표시할 지도
						position: positions.latlng, // 마커를 표시할 위치
						title : positions.title, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
						image : markerImage // 마커 이미지
					});
					markers.push(marker);

					// 마커에 커서가 오버됐을 때 마커 위에 표시할 인포윈도우를 생성합니다
					var iwContent = '<div style="padding:8px;' +
							'    font-family: \'나눔스퀘어 \', sans-serif;">'+data[i].nickName+"님의 보관소"+'</div>'; // 인포윈도우에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다

					// 인포윈도우를 생성합니다
					var infowindow = new kakao.maps.InfoWindow({
						content : iwContent
					});
					infowindows.push(infowindow);
					// 마커에 마우스오버 이벤트를 등록합니다
					kakao.maps.event.addListener(marker, 'mouseover', function() {
						// 마커에 마우스오버 이벤트가 발생하면 인포윈도우를 마커위에 표시합니다
						infowindows[i+1].open(map, markers[i+1]);
					});
					// 마커에 마우스아웃 이벤트를 등록합니다
					kakao.maps.event.addListener(marker, 'mouseout', function() {
						// 마커에 마우스아웃 이벤트가 발생하면 인포윈도우를 제거합니다
						infowindows[i+1].close();
					});
					kakao.maps.event.addListener(marker, 'click', function() {
						// 마커에 마우스오버 이벤트가 발생하면 인포윈도우를 마커위에 표시합니다
						roomSelect(data[i].articleNum);
					});
				}
				// 마커 이미지의 이미지 주소입니다
				/*var imageSrc = "http://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png";
				for (var i = 0; i < positions.length; i ++) {
					// 마커 이미지의 이미지 크기 입니다
					var imageSize = new kakao.maps.Size(24, 35);
					// 마커 이미지를 생성합니다
					var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize);
					// 마커를 생성합니다
					var marker = new kakao.maps.Marker({
						map: map, // 마커를 표시할 지도
						position: positions[i].latlng, // 마커를 표시할 위치
						title : positions[i].title, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
						image : markerImage // 마커 이미지
					});
					markers.push(marker);

					// 마커에 커서가 오버됐을 때 마커 위에 표시할 인포윈도우를 생성합니다
					var iwContent = '<div style="padding:5px;"></div>'; // 인포윈도우에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다

					// 인포윈도우를 생성합니다
					var infowindow = new kakao.maps.InfoWindow({
						content : iwContent
					});
					infowindows.push(infowindow);
					// 마커에 마우스오버 이벤트를 등록합니다
					kakao.maps.event.addListener(marker, 'mouseover', function() {
						// 마커에 마우스오버 이벤트가 발생하면 인포윈도우를 마커위에 표시합니다
						infowindow.open(map, marker);
					});
					// 마커에 마우스아웃 이벤트를 등록합니다
					kakao.maps.event.addListener(marker, 'mouseout', function() {
						// 마커에 마우스아웃 이벤트가 발생하면 인포윈도우를 제거합니다
						infowindow.close();
					});
				}*/

			}
			else{
				alert("지도 오류가 발생했습니다.");
			}
		});
	}   //검색 실행
	function roomSelect(articleNum) {    //상세보기 버튼 클릭 이벤트
		var form = document.createElement("form");
		var article = document.getElementById("article"+articleNum);
		var distance = $(article).children('.distance')[0];
		var distanceV= distance.innerHTML;
		form.setAttribute("charset", "UTF-8");
		form.setAttribute("method", "GET"); // Get 또는 Post 입력
		form.setAttribute("action", "/storeboard/"+articleNum);
		var hiddenField = document.createElement("input");
		hiddenField.setAttribute("type", "hidden");
		hiddenField.setAttribute("name", "distance");
		hiddenField.setAttribute("value", distanceV);
		form.appendChild(hiddenField);
		document.body.appendChild(form);
		form.submit();
	}  //방 버튼 클릭
	function enterkey(){
	if (window.event.keyCode === 13) {
		searchBtn();
	}
	}     //엔터버튼 클릭
	function mapResize() {						//맵 화면 크기 조정 이벤트
		let container = document.getElementById('map');
		let mapWrapper = $('.selection_wrapper');
		let mapSize = document.querySelector(".selection_wrapper").style.width;
		if( mapSize == "1230px") {
			mapWrapper.width("640px");
			$(".map_wrapper").css("left", "640px");
			container.style.width = "1280px";
		}
		else{
			mapWrapper.width("1230px");
			$(".map_wrapper").css("left", "1230px");
			container.style.width = "690px";
		}
		map.relayout();
	}
$(document).ready(function() {			//검색 화면 실행시
	initMap();
	$('#categoryAllBtn').click(function() { //카테고리 전체 클릭 이벤트
		$('input[name="category"]').prop('checked', !$('input[name="category"]').prop('checked'));
	});         //카테고리 전체 클릭시
	$('input[name="category"]').click(function() { //카테고리 전체 클릭 이벤트
		for(let iv=1 ; iv<11;iv++) {
			if($('#ct'+iv).prop('checked')==false)
				$('#ct0').prop('checked', false);
		}
	});  //카테고리 버튼 클릭시
	$('#categoryBtn').click(function() {		//카테고리 버튼 클릭이벤트
		var con =  document.getElementById('category');
		if(con.style.display=='none'){
			con.style.display = 'block';
			//con.style.backgroundColor ='BLUE';
		}else{
			con.style.display = 'none';
		}
	});
	$('#rangeBtn').click(function() {			//거리 버튼 클릭 이벤트
		var con =  document.getElementById('range-slider');
		if(con.style.display=='none'){
			con.style.display = 'block';
		}else{
			con.style.display = 'none';
		}

	});
	var rangeSlider = function(){			//슬라이더 변경 이벤트
		var slider = $('#range-slider'),
				range = $('#range-slider__range'),
				value = $('#range-slider__value');

		slider.each(function(){

			value.each(function(){
				var value = $(this).prev().attr('value');
				$(this).html(value+"km 이내");
			});

			range.on('input', function(){
				$(this).next(value).html(this.value+"km 이내");
			});
		});
	};
	rangeSlider();




	/*var container = document.getElementById('map');					//맵 api 세팅
	var options = {
		center: new kakao.maps.LatLng(37.484061, 126.955548),
		level: 3
	}*/
	if (navigator.geolocation) {

	    // GeoLocation을 이용해서 접속 위치를 얻어옵니다
	    navigator.geolocation.getCurrentPosition(function(position) {

	         lat = position.coords.latitude; // 위도
	         lon = position.coords.longitude; //

	        var locPosition = new kakao.maps.LatLng(lat, lon), // 마커가 표시될 위치를 geolocation으로 얻어온 좌표로 생성합니다
	            message = '<div style="padding:5px; border-radius: 4px;">현재 위치</div>'; // 인포윈도우에 표시될 내용입니다
	        // 마커와 인포윈도우를 표시합니다
	        displayMarker(locPosition, message);

	});
	}else { // HTML5의 GeoLocation을 사용할 수 없을때 마커 표시 위치와 인포윈도우 내용을 설정합니다
		var lat = 37.484061;
        var lon = 126.955548;
	    var locPosition = new kakao.maps.LatLng(lat,lon),
	        message = 'geolocation을 사용할수 없어요..'

	    displayMarker(locPosition, message);
	}
	function displayMarker(locPosition, message) {

	    // 마커를 생성합니다
	    var marker = new kakao.maps.Marker({
	        map: map,
	        position: locPosition
	    });
	    markers.push(marker);
	    var iwContent = message, // 인포윈도우에 표시할 내용
	        iwRemoveable = true;
	    // 인포윈도우를 생성합니다
	    var infowindow = new kakao.maps.InfoWindow({
	        content : iwContent,
	        removable : iwRemoveable
	    });
	    infowindows.push(infowindow);
	    // 인포윈도우를 마커위에 표시합니다
	    infowindow.open(map, marker);
	    // 지도 중심좌표를 접속위치로 변경합니다
	    map.setCenter(locPosition);

	}
	search(37.484224, 126.955759);
	//요주의!!!!!!!
	/*map = new kakao.maps.Map(container, options);	//맵 기본 세팅
	var mapTypeControl = new kakao.maps.MapTypeControl();
	map.addControl(mapTypeControl, kakao.maps.ControlPosition.TOPRIGHT);
	var zoomControl = new kakao.maps.ZoomControl();
	map.addControl(zoomControl, kakao.maps.ControlPosition.RIGHT);*/



});


window.onload = function(){
	  crear_select();
	}
	var li = new Array();
	function crear_select(){
	var div_cont_select = document.querySelectorAll("[data-mate-select='active']");
	var select_ = '';
	for (var e = 0; e < div_cont_select.length; e++) {
	div_cont_select[e].setAttribute('data-indx-select',e);
	div_cont_select[e].setAttribute('data-selec-open','false');
	var ul_cont = document.querySelectorAll("[data-indx-select='"+e+"'] > .cont_list_select_mate > ul");
	 select_ = document.querySelectorAll("[data-indx-select='"+e+"'] >select")[0];


	var select_optiones = select_.options;
	document.querySelectorAll("[data-indx-select='"+e+"']  > .selecionado_opcion ")[0].setAttribute('data-n-select',e);
	document.querySelectorAll("[data-indx-select='"+e+"']  > .icon_select_mate ")[0].setAttribute('data-n-select',e);
	for (var i = 0; i < select_optiones.length; i++) {
	li[i] = document.createElement('li');
	if (select_optiones[i].selected == true || select_.value == select_optiones[i].innerHTML ) {
	li[i].className = 'active';
	document.querySelector("[data-indx-select='"+e+"']  > .selecionado_opcion ").innerHTML = select_optiones[i].innerHTML;
	};
	li[i].setAttribute('data-index',i);
	li[i].setAttribute('data-selec-index',e);
	// funcion click al selecionar
	li[i].addEventListener( 'click', function(){  _select_option(this.getAttribute('data-index'),this.getAttribute('data-selec-index')); });
	li[i].innerHTML = select_optiones[i].innerHTML;
	ul_cont[0].appendChild(li[i]);

	    }; // Fin For select_optiones
	  }; // fin for divs_cont_select
	} // Fin Function
	//검색조건
	var cont_slc = 0;
	function open_select(idx){
	var idx1 =  idx.getAttribute('data-n-select');
	  var ul_cont_li = document.querySelectorAll("[data-indx-select='"+idx1+"'] .cont_select_int > li");
	var hg = 0;
	var slect_open = document.querySelectorAll("[data-indx-select='"+idx1+"']")[0].getAttribute('data-selec-open');
	var slect_element_open = document.querySelectorAll("[data-indx-select='"+idx1+"'] select")[0];
	 if (false) {
	  if (window.document.createEvent) { // All
	  var evt = window.document.createEvent("MouseEvents");
	  evt.initMouseEvent("mousedown", false, true, window, 0, 0, 0, 0, 0, false, false, false, false, 0, null);
		slect_element_open.dispatchEvent(evt);
	}
	else if (slect_element_open.fireEvent) { // IE
	  slect_element_open.fireEvent("onmousedown");
	}else {
	  slect_element_open.click();
	}
	}
	else{


	  for (var i = 0; i < ul_cont_li.length; i++) {
	hg += ul_cont_li[i].offsetHeight;
	};
	 if (slect_open == 'false') {
	 document.querySelectorAll("[data-indx-select='"+idx1+"']")[0].setAttribute('data-selec-open','true');
	 document.querySelectorAll("[data-indx-select='"+idx1+"'] > .cont_list_select_mate > ul")[0].style.height = hg+"px";
	 document.querySelectorAll("[data-indx-select='"+idx1+"'] > .icon_select_mate")[0].style.transform = 'rotate(180deg)';
	}else{
	 document.querySelectorAll("[data-indx-select='"+idx1+"']")[0].setAttribute('data-selec-open','false');
	 document.querySelectorAll("[data-indx-select='"+idx1+"'] > .icon_select_mate")[0].style.transform = 'rotate(0deg)';
	 document.querySelectorAll("[data-indx-select='"+idx1+"'] > .cont_list_select_mate > ul")[0].style.height = "0px";
	 }
	}

	} // fin function open_select
	function salir_select(indx){
	var select_ = document.querySelectorAll("[data-indx-select='"+indx+"'] > select")[0];
	 document.querySelectorAll("[data-indx-select='"+indx+"'] > .cont_list_select_mate > ul")[0].style.height = "0px";
	document.querySelector("[data-indx-select='"+indx+"'] > .icon_select_mate").style.transform = 'rotate(0deg)';
	 document.querySelectorAll("[data-indx-select='"+indx+"']")[0].setAttribute('data-selec-open','false');
	}
	function _select_option(indx,selc){
	/*  if (isMobileDevice()) {
	selc = selc -1;
	} */
	    var select_ = document.querySelectorAll("[data-indx-select='"+selc+"'] > select")[0];

	  var li_s = document.querySelectorAll("[data-indx-select='"+selc+"'] .cont_select_int > li");
	  var p_act = document.querySelectorAll("[data-indx-select='"+selc+"'] > .selecionado_opcion")[0].innerHTML = li_s[indx].innerHTML;
	var select_optiones = document.querySelectorAll("[data-indx-select='"+selc+"'] > select > option");
	for (var i = 0; i < li_s.length; i++) {
	if (li_s[i].className == 'active') {
	li_s[i].className = '';
	};
	li_s[indx].className = 'active';

	};
	select_optiones[indx].selected = true;
	  select_.selectedIndex = indx;
	  select_.onchange();
	  salir_select(selc);
	}


</script>
</head>
<body>
 <%@ include file="navbar.jsp" %>




	<div class=container>
		<div class=map_header>
			<!-- 검색창 --> 
			<!--  <form class=map_searchbar>-->
			<div class="search_wrapper">
			<input class="search_input" type="search" placeholder="원하시는 장소를 검색하세요" onkeyup="enterkey()"/>
			<button class="search_btn" value="검색" onclick="searchBtn();">검색
			</button>
			</div>
			
				
				
			<div class="select_wrapper">
		  	<!-- 카테고리 -->
				<button name="전체"  id="categoryBtn" value="전체">카테고리
					<i class="fas fa-angle-down" style="margin-left: 50px; font-size: 19px;"></i></button>
				<div class="category"  id="category" style="display:none;">
					<div class="category2" id="categoryAllBtn">
						<label for="ct0" style="display:contents">
							<input type="checkbox" name="category" value="%" id="ct0">
							전체</label>
					</div>
					<div class="category2" >
						<label for="ct1"><input type="checkbox" name="category" value="1" id="ct1" >
							의류</label>
					</div>
					<div class="category2">
						<label for="ct2"><input type="checkbox" name="category" value="2" id="ct2" >
							도서</label>
					</div>
					<div class="category2">
						<label for="ct3"><input type="checkbox" name="category" value="3" id="ct3" >
							패션잡화</label>
					</div>
					<div class="category2">
						<label for="ct4"><input type="checkbox" name="category" value="4" id="ct4" >
							페브릭</label>
					</div>
					<div class="category2">
						<label for="ct5"><input type="checkbox" name="category" value="5" id="ct5" >
							소형가전</label>
					</div>
					<div class="category2">
						<label for="ct6"><input type="checkbox" name="category" value="6" id="ct6" >
							취미용품</label>
					</div>
					<div class="category2">
						<label for="ct7"><input type="checkbox" name="category" value="7" id="ct7" >
							캠핑용품</label>
					</div>
					<div class="category2">
						<label for="ct8"><input type="checkbox" name="category" value="8" id="ct8" >
							유아용품</label>
					</div>
					<div class="category2">
						<label for="ct9"><input type="checkbox" name="category" value="9" id="ct9" >
							음반/DVD</label>
					</div>
					<div class="category2">
						<label for="ct10"><input type="checkbox" name="category" value="10" id="ct10">
							기타</label>
					</div>
				</div>
			<!-- 거리조건  -->
				<button name="전체"  id="rangeBtn" value="전체">거리
					<i class="fas fa-angle-down" style="margin-left: 50px; font-size: 19px;"></i></button>

				<div class="category" id="range-slider" style="display:none;">
					<input id="range-slider__range" type="range" value="30" min="1" max="50">
					<span id="range-slider__value">30km</span>
				</div>
		  	<!-- 검색조건 --> 
			<div class="select_mate" data-mate-select="active" >
				<select name="" onchange="" onclick="return false;" id="filter">
				<option value="거리 가까운 순">거리 가까운 순 </option>
				<option value="리뷰 많은 순">리뷰 많은 순</option>
				<option value="가격 낮은 순" >가격 낮은 순</option>
				<option value="가격 높은 순" >가격 높은 순</option>
				<option value="별점 높은 순" >별점 높은 순</option>
				</select>
				<p class="selecionado_opcion"  onclick="open_select(this)" ></p><span onclick="open_select(this)" class="icon_select_mate" ><svg fill="#000000" height="24" viewBox="0 0 24 24" width="24" xmlns="http://www.w3.org/2000/svg">
				    <path d="M7.41 7.84L12 12.42l4.59-4.58L18 9.25l-6 6-6-6z"/>
				    <path d="M0-.75h24v24H0z" fill="none"/>
				</svg></span>
				<div class="cont_list_select_mate">
				  <ul class="cont_select_int">  </ul> 
				</div>
			</div>
			<!-- 검색조건 끝 -->
			<!-- 보관 방법 --> 
			<div class="select_mate" data-mate-select="active" >
				<select name="" onchange="" onclick="return false;" id="storageType">
				<option value="%"  >전체 </option>
				<option value="집">집</option>
				<option value="상가">상가</option>
				<option value="회사">회사</option>

				</select>
				<p class="selecionado_opcion"  onclick="open_select(this)" ></p><span onclick="open_select(this)" class="icon_select_mate" ><svg fill="#000000" height="24" viewBox="0 0 24 24" width="24" xmlns="http://www.w3.org/2000/svg">
				    <path d="M7.41 7.84L12 12.42l4.59-4.58L18 9.25l-6 6-6-6z"/>
				    <path d="M0-.75h24v24H0z" fill="none"/>
				</svg></span>
				<div class="cont_list_select_mate">
				  <ul class="cont_select_int">  </ul> 
				</div>
			</div>
			<!-- 반려동물 끝 -->
			
			<!-- 거래방법 --> 
			<div class="select_mate" data-mate-select="active" >
				<select name="" onchange="" onclick="return false;" id="transactionType">
				<option value="%" >전체 </option>
				<option value="1">택배</option>
				<option value="2" >직거래</option>
				</select>
				<p class="selecionado_opcion"  onclick="open_select(this)" ></p><span onclick="open_select(this)" class="icon_select_mate" ><svg fill="#000000" height="24" viewBox="0 0 24 24" width="24" xmlns="http://www.w3.org/2000/svg">
				    <path d="M7.41 7.84L12 12.42l4.59-4.58L18 9.25l-6 6-6-6z"/>
				    <path d="M0-.75h24v24H0z" fill="none"/>
				</svg></span>
				<div class="cont_list_select_mate">
				  <ul class="cont_select_int"> </ul> 
				</div>
			 </div>
		  	<!-- 거래방법 끝 -->
		  	<!-- 보관기간 --> 
			<div class="select_mate" data-mate-select="active" >
				<select name="" onchange="" onclick="return false;" id="storagePeriodType">
				<option value="%" >전체 </option>
				<option value="2">장기</option>
				<option value="3" >단기</option>
				</select>
				<p class="selecionado_opcion"  onclick="open_select(this)" ></p><span onclick="open_select(this)" class="icon_select_mate" ><svg fill="#000000" height="24" viewBox="0 0 24 24" width="24" xmlns="http://www.w3.org/2000/svg">
				    <path d="M7.41 7.84L12 12.42l4.59-4.58L18 9.25l-6 6-6-6z"/>
				    <path d="M0-.75h24v24H0z" fill="none"/>
				</svg></span>
				<div class="cont_list_select_mate">
				  <ul class="cont_select_int"> </ul> 
				</div>
			 </div>
		  	<!-- 보관기간 끝 -->
		    <!-- CCTV --> 
			<div class="select_mate" data-mate-select="active" >
				<select name="" onchange="" onclick="return false;" id="securityFacility">
				<option value="%"  >전체</option>
				<option value="2">CCTV</option>
				</select>
				<p class="selecionado_opcion"  onclick="open_select(this)" ></p><span onclick="open_select(this)" class="icon_select_mate" ><svg fill="#000000" height="24" viewBox="0 0 24 24" width="24" xmlns="http://www.w3.org/2000/svg">
				    <path d="M7.41 7.84L12 12.42l4.59-4.58L18 9.25l-6 6-6-6z"/>
				    <path d="M0-.75h24v24H0z" fill="none"/>
				</svg></span>
				<div class="cont_list_select_mate">
				  <ul class="cont_select_int">  </ul> 
				</div>
			</div>
			<!-- CCTV 끝 -->
			<!-- 반려동물 --> 
			<div class="select_mate" data-mate-select="active" >
				<select name="" onchange="" onclick="return false;" id="petFlag">
				<option value="%">반려동물 무관 </option>
				<option value="0">반려동물 없음</option>

				</select>
				<p class="selecionado_opcion"  onclick="open_select(this)" ></p><span onclick="open_select(this)" class="icon_select_mate" ><svg fill="#000000" height="24" viewBox="0 0 24 24" width="24" xmlns="http://www.w3.org/2000/svg">
				    <path d="M7.41 7.84L12 12.42l4.59-4.58L18 9.25l-6 6-6-6z"/>
				    <path d="M0-.75h24v24H0z" fill="none"/>
				</svg></span>
				<div class="cont_list_select_mate">
				  <ul class="cont_select_int">  </ul> 
				</div>
			</div>
			<!-- 반려동물 끝 --> 
			</div>
			<div class="keep">
    			<button class="keepBtn" onclick="location.href='/storeboard/keep'">보관소 등록</button>
			</div>
			
			<div class="mapSize">
    			<button class="mapBtn" onclick="mapResize();">지도 크기</button>
			</div>
		</div>
		
		<div class=content>
			<div class="selection_wrapper" style="width:1230px" >
				<div class="selection_content" id="selection_content_id1"<%-- onclick="location.href='${pageContext.request.contextPath}/moa/search/1'"--%>>
				</div>
			
			</div>
		</div>
			<div class="map_wrapper">
				<div id="map" style="width:690px;height:797px;"></div>
			</div>
		</div>

	</div>
	
</body>
</html>
