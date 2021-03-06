var num = 1;
var table_product_num = 0;
var i = 0;
$(function () {
    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");


    $("button[type='submit']").on("click", function (e) {
        $("button[type='submit']").attr('disabled', true);
        var measured = $('#measured').val();
        measured = removeCommas(measured);
        $('#measured').val(measured);

        var bargain = $('#bargain').val();
        bargain = removeCommas(bargain);
        $('#bargain').val(bargain);

        var textArea = $('#post_contents').val();
        textArea = textArea.replace(/(?:\r\n|\r|\n)/g, '<br/>');
        $('#post_contents').val(textArea);

        var formObj = $("form[role='form']");
        // var formObjClone = $(formObj).clone();
        e.preventDefault();
        if (!$("#submit_check").prop("checked")) {
            alert("체크박스를 체크하세요");
            return;
        }
        var str = "";

        $(".uploadResult ul li").each(function (i, obj) {
            var jobj = $(obj);
            console.dir(jobj);

            str += "<input type='hidden' name='attachList[" + i + "].fileName' value='" + jobj.data("filename") + "'>";
            str += "<input type='hidden' name='attachList[" + i + "].uuid' value='" + jobj.data("uuid") + "'>";
            str += "<input type='hidden' name='attachList[" + i + "].uploadPath' value='" + jobj.data("path") + "'>";
            str += "<input type='hidden' name='attachList[" + i + "].fileType' value='" + jobj.data("type") + "'>";
        });
        // formObjClone.append(str);
        formObj.append(str);

        $.ajax({
            type: "POST",
            url: location.pathname,
            // data: formObjClone.serialize(),
            data: formObj.serialize(),
            dataType: 'json',
            beforeSend: function (xhr) {
                xhr.setRequestHeader("AJAX", true);
                xhr.setRequestHeader(header, token);
            },
            success: function (result) {
                if (result) {
                    $("#content6").hide();
                    $("#content7").show();
                    $("#left_side").hide();
                    $("#right_side").hide();
                    $("#exit_btn").hide();
                } else {
                    alert("잘못된 정보가 입력되었습니다, 다시 시도해 주세요.");
                    var textArea = $('#post_contents').val();
                    textArea = textArea.replace('<br/>', '\r\n');
                    $('#post_contents').val(textArea);
                    $("button[type='submit']").attr('disabled', false);
                }
            },
            error: function (request, status, error) {
                alert("서버에 일시적 문제가 생겼습니다, 다시 시도해 주세요.");
                console.log("code:" + request.status + "\n" + "message:" + request.responseText + "\n" + "error:" + error);
                var textArea = $('#post_contents').val();
                textArea = textArea.replace('<br/>', '\r\n');
                $('#post_contents').val(textArea);
                $("button[type='submit']").attr('disabled', false);
            }
        })
    });

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
            measuredPriceSetting();
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
            measuredPriceSetting();
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
    $("input:radio[name='price.selectPrice']").click(function () {
        if ($("input[name='price.selectPrice']:checked").val() == "measuredPrice") {
            $('#measured').css("background", "#ffffff");
            $('#bargain').css("background", "#EBEBE4");
            $('#bargain').attr("readonly", true);
        } else {
            $('#measured').css("background", "#EBEBE4");
            $('#bargain').css("background", "#ffffff");
            $('#bargain').attr("readonly", false);
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
            var productName = document.getElementsByName("productList[0].product");
            var productCnt = document.getElementsByName("productList[0].productCnt");
            if (productName[0].value == null || productName[0].value.trim() == "") {
                alert("첫번째 칸의 물건명은 반드시 입력하셔야 합니다");
                productName[0].value = "";
                productName[0].focus();
                return;
            }
            if (productCnt[0].value == 0) {
                alert("첫번째 칸의 물건 개수는 반드시 입력하셔야 합니다");
                return;
            }

            $("#content1").hide();
            $("#content2").show();
            $("#left_side").show();
            elem.style.width = 20 + '%';
            $("#percent").html("&nbsp;&nbsp;&nbsp;20%");
            num++;
            return;
        case 2:
            if ($('#box').val() < 0) {
                alert("5호 박스 개수가 음수가 될 수 없습니다.");
                $('#box').val(0);
                $('#box').focus();
                return;
            }
            if ($('#bicycle').val() < 0) {
                alert("자전거 이상 크기가 음수가 될 수 없습니다.");
                $('#bicycle').val(0);
                $('#bicycle').focus();
                return;
            }
            if ($('#bed').val() < 0) {
                alert("싱글 침대 이상 크기가 음수가 될 수 없습니다.");
                $('#bed').val(0);
                $('#bed').focus();
                return;
            }
            for (i = 0; i < 3; i++) {
                var size = document.getElementsByClassName("sizeCnt");
                if (size[i].value != 0)
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
            var startDate = $('#start_datepicker').val();
            var endDate = $('#end_datepicker').val();
            if (startDate == "" || endDate == "") {
                alert("맡길 기간을 입력해주세요");
                return;
            }
            if ($('#bargain_price').prop("checked") && $('#bargain').val() == 0) {
                alert("흥정가격을 입력하거나 측정 가격를 체크해주세요");
                return;
            }
            if ($('#bargain_price').prop("checked") && $('#bargain').val() < 100) {
                alert("흥정가격은 최소 100원 이상 입력해야 합니다");
                $('#bargain').focus();
                return;
            }

            $("#content3").hide();
            $("#content4").show();
            elem.style.width = 60 + '%';
            $("#percent").html("&nbsp;&nbsp;&nbsp;60%");
            num++;
            return;
        case 4:
            var checkValid = document.getElementsByName("transactionWay");
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
            var fileCnt = document.getElementsByClassName("btn-deleteFile");
            if (fileCnt.length < 2) {
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
        location.href = "/storeboard";
    }
}

function finished() {
    $('#regForm')[0].reset();
    location.href = "/storeboard";
}


//보관 기간 총 몇일인지 구하는 알고리즘
function duration(startDate, endDate) {
    var month = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31];
    var period = 0;
    var sdate = 0, edate = 0;
    var check;

    var start = startDate.split('-');
    var end = endDate.split('-');

    // 그 해 1월 1일부터 시작날짜까지의 총 일수
    for (i = 0; i < start[1] - 1; i++)
        sdate += month[i];
    if (year_check(start[0]) == 1 && start[1] > 2)
        sdate++;
    sdate += (start[2] / 1);

    // 그 해 1월 1일부터 종료날짜까지의 총 일수
    for (i = 0; i < end[1] - 1; i++)
        edate += month[i];
    if (year_check(end[0]) == 1 && end[1] > 2)
        edate++;
    edate += (end[2] / 1);

    for (i = start[0]; i < end[0]; i++) {
        check = year_check(i);
        check == 1 ? period += 366 : period += 365;
    }
    period += (edate - sdate + 1);
    console.log("period: " + period);
    return period;
}

//윤년 체크
function year_check(year) {
    var check;
    if (year % 4 == 0) {
        if (year % 100 == 0) {
            if (year % 400 == 0)
                check = 1;
            else
                check = 0;
        } else
            check = 1;
    } else
        check = 0;
    return check;
}

function measuredPriceSetting() {
    var startDate = $('#start_datepicker').val();
    var endDate = $('#end_datepicker').val();
    if (startDate == "" || endDate == "" || startDate == null || endDate == null || ($('#box').val() == 0) && $('#bicycle').val() == 0 && $('#bed').val() == 0) {
        $('#measured').val("0");
        return;
    }

    var period = duration(startDate, endDate);    // 날짜를 선택하지 않으면 리턴
    var measuredPrice;
    var selectedPrice;
    var multiplePrice = $('#firstMultiple').val() * $('#box').val() + $('#secondMultiple').val() * $('#bicycle').val() + $('#thirdMultiple').val() * $('#bed').val();

    if (period < 7) {
        selectedPrice = $('#day').val();
        measuredPrice = selectedPrice * multiplePrice * (period / 1);
    } else if (period < 30) {
        selectedPrice = $('#week').val();
        measuredPrice = selectedPrice * multiplePrice * (period / 7);
    } else if (period < 180) {
        selectedPrice = $('#month').val();
        measuredPrice = selectedPrice * multiplePrice * (period / 30);
    } else if (period < 365) {
        selectedPrice = $('#halfYear').val();
        measuredPrice = selectedPrice * multiplePrice * (period / 180);
    } else {
        selectedPrice = $('#year').val();
        measuredPrice = selectedPrice * multiplePrice * (period / 365);
    }
    $('#measured').val(measuredPrice - measuredPrice % 100);

    // 3자리 단위로 , 찍기
    var x = $('#measured').val();
    if (x && x.length > 0) {
        if (!$.isNumeric(x)) {
            x = x.replace(/[^0-9]/g, "");
        }
        x = addCommas(x);
        $('#measured').val(x);
    }
}

$(document).ready(function () {
    $("#bargain").on("focus", function () {
        var x = $(this).val();
        x = removeCommas(x);
        $(this).val(x);
    }).on("focusout", function () {
        var x = $(this).val();
        if (x && x.length > 0) {
            if (!$.isNumeric(x)) {
                x = x.replace(/[^0-9]/g, "");
            }
            x = addCommas(x);
            $(this).val(x);
        }
    }).on("keyup", function () {
        $(this).val($(this).val().replace(/[^0-9]/g, ""));
    });
})

function addCommas(x) {
    return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
}

function removeCommas(x) {
    if (!x || x.length == 0) return "";
    else return x.split(",").join("");
}

$(document).ready(function () {
    $('#bargain').on("focusout", function () {
        if ($('#bargain').val() == "")
            $('#bargain').val(0);
    })
})
//
// $("#box").change(function () {
//     measuredPriceSetting();
// });
// $("#bicycle").change(function () {
//     measuredPriceSetting();
// });
// $('#bed').change(function () {
//     measuredPriceSetting();
// });
