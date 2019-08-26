var token = $("meta[name='_csrf']").attr("content");
var header = $("meta[name='_csrf_header']").attr("content");
var thumbnail = 'thumbnail_';
function deleteStorage(storageNum){
	var flag=confirm("정말로 삭제하시겠습니까?");
	if(flag){
		$.ajax({
			url:'/storeboard/'+storageNum+'delete',
			type:'POST',
			data : realUserNick,
			contentType : 'application/json; charset = utf-8',
			beforeSend: function (xhr) {
				xhr.setRequestHeader("AJAX", true);
				xhr.setRequestHeader(header, token);
			},
			success:function (data) {
				if(data==true) {
					alert("삭제가 완료되었습니다.");
					location.reload();
				}
				else
					alert("삭제에 실패하였습니다.");
			},
			error:function () {
				alert('통신에 장애가 생겼습니다, 잠시뒤 시도해주세요.');
			}
		})
	}
};
$(document).ready(function() {
	$.storageList(1);
	 $("#request_btn").on('click',function(){
		  location.href="/hostpage/confirmyet";
	});
	$("#my_storage_btn").on('click',function(){
	  location.href="/hostpage/mystorage";
	});
});
function getContextPath(){
	   var hostIndex = location.href.indexOf(location.host) + location.host.length;
	   var contextPath = location.href.substring(hostIndex, location.href.indexOf('/', hostIndex + 1));
	   return contextPath;
	}

$(document).on('click',".storage_detail",function(){
	location.href="/storeboard/" +event.target.id;
	});



$.storageList = function(curPage){
	$.ajax({
		url:"/hostpage/mystorage/list",
		type:"get",
		contentType:"application/json",
		dataType:"json",
		data:{
			curPage:curPage
		},
		success:function(result){

			//리스트
			$('.main_content>table').remove('table');
			for(let i = result.pagination.curListCnt-1; i >= 0; i--){
				//row1
				let table = $('<table/>').appendTo('.main_content');
				let tr1 = $('<tr/>').appendTo(table);
				let td11 = $('<td/>',{class:'table_top',colspan:2}).appendTo(tr1);
				let i1 =$('<i />',{class:'far fa-star',text:result.list[i].starPoint+"\u00A0\u00A0\u00A0\u00A0"}).appendTo(td11);
				$('<i/>',{class:'far fa-heart',text:result.list[i].favoriteCnt}).appendTo(td11);
				$('<td/>',{class:'table_top storage_num', text:result.list[i].articleNum}).appendTo(tr1);
				//row2
				let tr2 = $('<tr/>').appendTo(table);
				let td21	 = $('<td/>',{class:'table_left table_img storage_detail',rowspan:4}).appendTo(tr2);

				var profile = result.list[i].boardAttach;
				var fileCallPath = encodeURIComponent(profile.uploadPath + "/" + thumbnail
					+ profile.uuid + "_" + profile.fileName);

				$('<img/>', {
					src: '/display?fileName=/' + fileCallPath,
					alt: 'profile',
					id:result.list[i].articleNum,
					onerror: 'this.src="/resources/image/loading.gif"'
				}).appendTo(td21);

				let td22 = $('<td/>',{class:'table_middle table_title', text:'제 목'}).appendTo(tr2);
				let td23 = $('<td/>',{class:'table_right storage_detail', id:result.list[i].articleNum ,text:result.list[i].title}).appendTo(tr2);
				//row3
				let tr3 = $('<tr/>').appendTo(table);
				let td31 = $('<td/>',{class:'table_middle table_title', text:'보관중인 물건'}).appendTo(tr3);
				let td32 = $('<td/>',{class:'table_right', text:result.list[i].storeProductCnt}).appendTo(tr3);
				//row4
				let tr4 = $('<tr/>').appendTo(table);
				let td41 = $('<td/>',{class:'table_middle table_title', text:'보관만료된 물건'}).appendTo(tr4);
				let td42 = $('<td/>',{class:'table_right', text:result.list[i].expiredProductCnt}).appendTo(tr4);
				//row5
				let tr5 = $('<tr/>',{class:'table_bottom'}).appendTo(table);
				let td51 = $('<td/>',{colspan:'2'}).appendTo(tr5);
				$('<button/>',{text:'수정'}).appendTo(td51);
				$('<button/>',{text:'삭제', onclick:'deleteStorage('+result.list[i].articleNum+')'}).appendTo(td51);
			}

			//페이징처리
			$('.main_paging>span').remove('span');
			$('.main_paging>i').remove('i');
			$('.main_paging>img').remove('img');

			if(result.pagination.endPage == 0){
				$('<span/>',{text : "등록된 보관소가 없습니다.",style:'font-weight:bold;'}).appendTo('.main_paging');
				return;
			}
			if(curPage == 1){
				$('<i/>',{class:"fas fa-angle-left",text:"\u00A0\u00A0"}).appendTo('.main_paging');
			}
			else{
				$('<i/>',{class:"fas fa-angle-left",text:"\u00A0\u00A0", style:"cursor:pointer;"}).click(function(e){
					$.storageList(curPage-1);
				}).appendTo('.main_paging');
			}

			for(let i = result.pagination.startPage; i <= result.pagination.endPage; i++ ){
				if(i == curPage){
					$('<span/>',{text : i+"\u00A0\u00A0",style:'font-weight:bold;'}).click(function(e){
						$.storageList(i);
					}).appendTo('.main_paging');
				}
				else{
					$('<span/>',{text : i+"\u00A0\u00A0",style:"cursor:pointer;"}).click(function(e){
						$.storageList(i);
					}).appendTo('.main_paging');
				}

			}
			if(result.pagination.pageCnt == curPage){
				$('<i/>',{class:"fas fa-angle-right"}).appendTo('.main_paging');
			}
			else{
				$('<i/>',{class:"fas fa-angle-right", style:"cursor:pointer;"}).click(function(e){
					$.storageList(curPage+1);
				}).appendTo('.main_paging');
			}


		},
		error:function(request,status,error){
			alert("목록 불러오기 실패");
			console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
		}
	});
};