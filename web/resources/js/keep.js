var num = 0;
var table_product_num = 0;
var vtrade_type_answer='transactionType';
var vcctv_answer= 'securityList';
var vtime_answer = 'storagePeriodType';


$(document).ready(function () {

    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");

    $("button[type='submit']").on("click", function (e) {


        var textArea = $('#post_contents').val();
        textArea = textArea.replace(/(?:\r\n|\r|\n)/g, '<br/>');
        $('#post_contents').val(textArea);
        
        var prices = document.getElementsByClassName('i_price');
        for (var price of prices) {
            var priceCom = price.value;
            priceCom = removeCommas(priceCom);
            $(price).val(priceCom);
        }

        var formObj = $("form[role='form']");
        var formObjClone = $(formObj).clone();
        e.preventDefault();
        if (!$("#submit_check").prop("checked")) {
            alert("체크박스를 체크하세요");
            return;
        }



        var str = "";



        $(".uploadResult ul li").each(function (i, obj) {
            var jobj = $(obj);

            str += "<input type='hidden' name='attachList[" + i + "].fileName' value='" + jobj.data("filename") + "'>";
            str += "<input type='hidden' name='attachList[" + i + "].uuid' value='" + jobj.data("uuid") + "'>";
            str += "<input type='hidden' name='attachList[" + i + "].uploadPath' value='" + jobj.data("path") + "'>";
            str += "<input type='hidden' name='attachList[" + i + "].fileType' value='" + jobj.data("type") + "'>";
        });
        formObjClone.append(str);
        $.ajax({
            type: "POST",
            url: location.pathname,
            data: formObjClone.serialize(),
            dataType:'json',
            beforeSend: function (xhr) {
                xhr.setRequestHeader("AJAX", true);
                xhr.setRequestHeader(header, token);
            },
            success: function (result) {
                if (result) {
                    $("#regForm").hide();
                    $("#content7").show();
                    $("#exit_btn").hide();
                    $("#left_side").hide();
                    $("#right_side").hide();
                } else {
                    alert("잘못된 정보가 입력되었습니다, 다시 시도해 주세요.");
                }
            },
            error: function (request, status, error) {
                alert("서버에 일시적 문제가 생겼습니다, 다시 시도해 주세요.");
                console.log("code:" + request.status + "\n" + "message:" + request.responseText + "\n" + "error:" + error);
            }
        })
    });

    $("input:radio[name=pet_radio]").click(function () {
        if ($("input[name=pet_radio]:checked").val() == "1") {
            $("#pet_text").attr("disabled", false);
        } else {
            $("#pet_text").attr("disabled", true);
        }
    });
});

function prevForm() {
    var elem = document.getElementById("percent");
    switch (num) {
        case 0:
            return;
        case 1:
            $("#content1").hide();
            $("#content0").show();
            $("#left_side").hide();
            elem.style.width = 0 + '%';
            $("#percent").html("&nbsp;&nbsp;&nbsp;0%");
            num--;
            return;
        case 2:
            $("#content2").hide();
            $("#content1").show();
            elem.style.width = 10 + '%';
            $("#percent").html("&nbsp;&nbsp;&nbsp;10%");
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
    let i;
    switch (num) {
        case 0:
            if($("#storage_address option:selected").val() == 0) {
                alert("보관 장소를 선택해주세요.");
                return;
            }
            $("#content0").hide();
            $("#content1").show();
            $("#left_side").show();
            elem.style.width = 10 + '%';
            $("#percent").html("&nbsp;&nbsp;&nbsp;10%");
            num++;
            return;
        case 1:
            if (!isValid(vtrade_type_answer))
                return;
            if (!isValid("pet_radio"))
                return;
            if (!isValid(vcctv_answer))
                return;
            $("#content1").hide();
            $("#content2").show();
            elem.style.width = 20 + '%';
            $("#percent").html("&nbsp;&nbsp;&nbsp;20%");
            num++;
            return;
        case 2:
            for (i = table_product_num; i >= 0; i--) {
                let productName = document.getElementsByName("forbiddenProductList[" + i + "].product");
                if (productName[0].value == null || productName[0].value.trim() == "")
                    productName[0].closest("tr").remove();
            }
            $("#content2").hide();
            $("#content3").show();
            elem.style.width = 40 + '%';
            $("#percent").html("&nbsp;&nbsp;&nbsp;40%");
            num++;
            return;
        case 3:
            if (!isValid(vtime_answer))
                return;
            if (!isPriceValid())
                return;
            $("#content3").hide();
            $("#content4").show();
            elem.style.width = 60 + '%';
            $("#percent").html("&nbsp;&nbsp;&nbsp;60%");
            num++;
            return;
        case 4:
            if (!isPhotoValid())
                return;
            $("#content4").hide();
            $("#content5").show();
            elem.style.width = 80 + '%';
            $("#percent").html("&nbsp;&nbsp;&nbsp;80%");
            num++;
            return;
        case 5:
            if ($('#post_title').val() == null || $('#post_title').val().trim() == "") {
                $('#post_title').val("");
                $('#post_title').focus();
                alert("제목을 입력해주세요.");
                return;
            }
            if ($('#post_contents').val() == null || $('#post_contents').val().trim() == "") {
                $('#post_contents').val("");
                $('#post_contents').focus();
                alert("내용을 입력해주세요.");
                return;
            }
            $("#right_side").hide();
            $("#content5").hide();
            $("#content6").show();
            elem.style.width = 100 + '%';
            $("#percent").html("&nbsp;&nbsp;&nbsp;100%");
            num++;
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

function isValid(input) {
    let i;
    let checkValid = document.getElementsByName(input);
    for (i = 0; i < checkValid.length; i++) {
        if (checkValid[i].checked == true)
            break;
    }
    if (i == checkValid.length) {
        switch (input) {
            case vtrade_type_answer:
                alert("선호 거래 방식을 선택해주세요.");
                return false;
            case "pet_radio":
                alert("반려 동물 정보를 입력해주세요.");
                return false;
            case vcctv_answer:
                alert("CCTV 여부를 선택해주세요.");
                return false;
            case vtime_answer:
                alert("보관 기간을 선택해주세요.");
                return false;
            default:
                alert("아무것도 해당되지 않음.");
        }
    }
    if ($("input[name=pet_radio]:checked").val() == "1") {
        if ($('#pet_text').val() == null || $('#pet_text').val().trim() == "") {
            alert("반려 동물의 종류를 입력해주세요.");
            $('#pet_text').val('');
            $('#pet_text').focus();
            return false;
        }
    }
    return true;
}

$(document).ready(function () {
    $("input:text[numberOnly]").on("focus", function () {
        // $(".i_price").on("focus", function () {
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

function isPriceValid() {
    var prices = document.getElementsByClassName('i_price');
    for (var price of prices) {
        if (price.value == null || price.value.trim() == "") {
            alert("보관 가격을 입력해주세요.");
            price.focus();
            return false;
        }
    }
    return true;
}

function isPhotoValid() {
    var fileCnt = document.getElementsByClassName("btn");
    if (fileCnt.length < 2) {
        alert("보관할 물품 사진을 최소 2장 이상 추가해야 합니다.");
        return false;
    }
    return true;
}
