var num = 1;
var table_product_num = 0;
$(function () {
    $("#start_datepicker").datepicker({
        dateFormat: 'yy-mm-dd',
        prevText: '이전 달',
        nextText: '다음 달',
        minDate: 0,
        monthNames: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
        monthNamesShort: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
        dayNames: ['일', '월', '화', '수', '목', '금', '토'],
        dayNamesShort: ['일', '월', '화', '수', '목', '금', '토'],
        dayNamesMin: ['일', '월', '화', '수', '목', '금', '토'],
        showMonthAfterYear: true,
        changeMonth: true,
        changeYear: true,
        yearSuffix: '년',
        onClose: function (selectedDate) {
            $("#end_datepicker").datepicker("option", "minDate", selectedDate);
        }
    });
    $("#end_datepicker").datepicker({
        dateFormat: 'yy-mm-dd',
        prevText: '이전 달',
        nextText: '다음 달',
        monthNames: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
        monthNamesShort: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
        dayNames: ['일', '월', '화', '수', '목', '금', '토'],
        dayNamesShort: ['일', '월', '화', '수', '목', '금', '토'],
        dayNamesMin: ['일', '월', '화', '수', '목', '금', '토'],
        showMonthAfterYear: true,
        changeMonth: true,
        changeYear: true,
        yearSuffix: '년',
        onClose: function (selectedDate) {
            $("#start_datepicker").datepicker("option", "maxDate", selectedDate);
        }
    });
});

$(document).ready(function () {

    $("#content2").hide();
    $("#content3").hide();
    $("#content4").hide();
    $("#content5").hide();
    $("#content6").hide();
    $("#content7").hide();

    $("#end_datepicker").on("change", function () {
        var t1 = $('#start_datepicker').val().split("-");
        var t2 = $('#end_datepicker').val().split("-");
        var t1date = new Date(t1[0], t1[1], t1[2]);
        var t2date = new Date(t2[0], t2[1], t2[2]);
        var diff = t2date - t1date;
        var currDay = 24 * 60 * 60 * 1000;
        var day = parseInt(diff / currDay) + 1;

    })
});

$(document).ready(function () {
    $("input:radio[name=price_radio]").click(function () {
        if ($("input[name=price_radio]:checked").val() == "1") {
            $('#measured').attr("disabled", false);
            $('#bargain').attr("disabled", true);
        } else {
            $('#measured').attr("disabled", true);
            $('#bargain').attr("disabled", false);
        }
    });
});

function prevForm() {
    var elem = document.getElementById("percent");
    switch (num) {
        case 1:
            return;
        case 2:
            $("#content2").hide();
            $("#content1").show();
            $("#left_side").hide();
            elem.style.width = 0 + '%';
            $("#percent").html("&nbsp;&nbsp;&nbsp;0%");
            num--;
            return;
        case 3:
            $("#content2").show();
            $("#content3").hide();
            elem.style.width = 20 + '%';
            $("#percent").html("&nbsp;&nbsp;&nbsp;20%");
            num--;
            return;
        case 4:
            $("#content3").show();
            $("#content4").hide();
            elem.style.width = 40 + '%';
            $("#percent").html("&nbsp;&nbsp;&nbsp;40%");
            num--;
            return;
        case 5:
            $("#content4").show();
            $("#content5").hide();
            elem.style.width = 60 + '%';
            $("#percent").html("&nbsp;&nbsp;&nbsp;60%");
            num--;
            return;
        case 6:
            $("#content5").show();
            $("#content6").hide();
            $("#right_side").show();
            elem.style.width = 80 + '%';
            $("#percent").html("&nbsp;&nbsp;&nbsp;80%");
            num--;
            return;
        case 7:
            $("#content6").show();
            $("#content7").hide();
            num--;
            return;
    }
}

function nextForm() {
    var elem = document.getElementById("percent");

    switch (num) {
        case 1:
            let productName = document.getElementsByName("productList[0].product");
            let productCnt = document.getElementsByName("productList[0].productCnt");
            if (productName[0].value == null || productName[0].value.trim() == "") {
                alert("물건명을 최소 하나 이상 입력해주세요.");
                productName[0].value = "";
                productName[0].focus();
                return;
            }
            if (productCnt[0].value == 0) {
                alert("물건 개수는 최소 1개 이상이어야 합니다.");
                return;
            }
            for (i = table_product_num; i > 0; i--) {
                let productName = document.getElementsByName("productList["+i+"].product");
                let productCnt = document.getElementsByName("productList["+i+"].productCnt");
                if (productName[0].value == null || productName[0].value.trim() == "" || productCnt[0].value == 0)
                    productName[0].closest("tr").remove();
            }
            $("#content1").hide();
            $("#content2").show();
            $("#left_side").show();
            elem.style.width = 20 + '%';
            $("#percent").html("&nbsp;&nbsp;&nbsp;20%");
            num++;
            return;
        case 2:
            for(i=0;i<3;i++){
                let size = document.getElementsByClassName("productSize["+i+"]");
                if (size.value != 0)
                    break;
            }
            if (i == 3) {
                alert("크기를 하나 이상 입력해주세요.");
                return;
            }
            $("#content2").hide();
            $("#content3").show();
            elem.style.width = 40 + '%';
            $("#percent").html("&nbsp;&nbsp;&nbsp;40%");
            num++;
            return;
        case 3:
            let startDate = document.getElementById("start_datepicker");
            let endDate = document.getElementById("end_datepicker");
            if (startDate.value == "" || endDate.value == "") {
                alert("맡길 기간을 입력해주세요");
                return;
            }
            $("#content3").hide();
            $("#content4").show();
            elem.style.width = 60 + '%';
            $("#percent").html("&nbsp;&nbsp;&nbsp;60%");
            num++;
            return;
        case 4:
            let checkValid = document.getElementsByName("transactionWay");
            for (i = 0; i < checkValid.length; i++) {
                if (checkValid[i].checked == true)
                    break;
            }
            if (i == checkValid.length) {
                alert("원하는 거래 방식을 선택해주세요.");
                return;
            }
            if ($('#post_contents').val() == null || $('#post_contents').val().trim() == "") {
                $('#post_contents').focus();
                alert("내용을 입력해주세요.");
                return;
            }
            $("#content4").hide();
            $("#content5").show();
            elem.style.width = 80 + '%';
            $("#percent").html("&nbsp;&nbsp;&nbsp;80%");
            num++;
            return;
        case 5:
            var inputFile = $("input[name = 'uploadFile']");
            var files = inputFile[0].files;
            console.log(files.length);
            if(files.length<2) {
                alert("보관할 물품 사진을 최소 2장 이상 추가해야 합니다.");
                return;
            }
            $("#content5").hide();
            $("#content6").show();
            $("#right_side").hide();
            elem.style.width = 100 + '%';
            $("#percent").html("&nbsp;&nbsp;&nbsp;100%");
            num++;
            return;
        case 6:
            $("#content6").hide();
            $("#content7").show();
            num++;
            return;
        case 7:
            $("#left_side").hide();
            $("#right_side").hide();
            return;
    }
}

function exit() {
    if (confirm("작성한 내용은 저장되지 않습니다. 정말로 나가시겠습니까?")) {
        $('#regForm')[0].reset();
        location.href = "/main";
    }
}

function finished() {
    $('#regForm')[0].reset();
    location.href = "/main";
}

var formObj= $("form[role='form']");
var token = $("meta[name='_csrf']").attr("content");
var header = $("meta[name='_csrf_header']").attr("content");

$("button[type='submit']").on("click",function (e) {
    e.preventDefault();
    if (!$("#submit_check").prop("checked")) {
        alert("체크박스를 체크하세요");
        return;
    }
    alert("물건을 맡깁니다.");
    var str="";

    $(".uploadResult ul li").each(function (i,obj) {
        var jobj= $(obj);
        console.dir(jobj);

        str+="<input type='hidden' name='attachList["+i+"].fileName' value='"+jobj.data("filename")+"'>";
        str+="<input type='hidden' name='attachList["+i+"].uuid' value='"+jobj.data("uuid")+"'>";
        str+="<input type='hidden' name='attachList["+i+"].uploadPath' value='"+jobj.data("path")+"'>";
        str+="<input type='hidden' name='attachList["+i+"].fileType' value='"+jobj.data("type")+"'>";
    });
    formObj.append(str);
    alert(formObj);
    $.ajax({
        type:"POST",
        url:location.pathname,
        data:formObj.serialize(),
        beforeSend: function (xhr) {
            xhr.setRequestHeader("AJAX", true);
            xhr.setRequestHeader(header, token);
        },
        success: function (result) {
            console.log(result);
            console.log(result.value);
            console.log(result==true);
            console.log((result.value==true));
            if(result) {
                $("#content6").hide();
                $("#content7").show();
                $("#left_side").hide();
                $("#right_side").hide();
            }else{
                alert("서버에 일시적 문제가 생겼습니다, 다시 시도해 주세요.");
            }
        },
        error:function(request,status,error){
            alert("서버에 일시적 문제가 생겼습니다, 다시 시도해 주세요.");
            console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
        }
    })
});
