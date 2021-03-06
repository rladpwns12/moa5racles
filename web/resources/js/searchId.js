var win;
var phoneFlag = false;
function setChildValue(phoneNumber){
    document.getElementById("phone").value = phoneNumber;
    if(phoneNumber != ''){
        phoneFlag = true;
    }
}

function openAuthenticatePhone() {
    if(win != null){
        win.close();
    }
    var popUrl = "/authenticatePhone";	//팝업창에 출력될 페이지 URL
    var popupX = (window.screen.width / 2) - (500 / 2);
// 만들 팝업창 좌우 크기의 1/2 만큼 보정값으로 빼주었음
    var popupY = (window.screen.height / 2) - (280 / 2);

// 만들 팝업창 상하 크기의 1/2 만큼 보정값으로 빼주었음
    var popOption = "width=500, height=280, resizable=no, " +
        "scrollbars=yes, status=no, " +
        "left=" + popupX + ",top=" + popupY + ";";//팝업창 옵션(optoin)
    win = window.open(popUrl, "", popOption);
    win.focus();

}


function submit() {
    if (!isValid())
        return;
    if(phoneFlag === false){
        alert("휴대폰 인증을 해주세요.");
        return;
    }
    let name = $('#name').val();
    let phone = $('#phone').val();

    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");
    $.ajax({
        type: "POST",
        url: "/searchId",
        data: {name, phone},
        cache: false,
        beforeSend: function (xhr) {
            xhr.setRequestHeader("AJAX", true);
            xhr.setRequestHeader(header, token);
        },
        success(data) {
            if (data != "") {
                $('#searchedId').val(data);
                $('#content1').hide();
                $('#content2').show();
            } else {
                alert("회원정보를 잘못 입력하셨습니다");
            }
        }, error: function (request, status, error) {
            console.log("전송 오류");
        }
    });
}

function isValid() {
    let result;
    if (!isNameValid()) {
        result =  false;
    }
    if (!isPhoneValid()) {
        result = false;
    }
    if(result == false) {
        return false;
    }
    return true;
}

function isNameValid() {
    let name = $('#name').val();
    if (name == null || name.trim() == "") {
        $('#name').css("color", "#DD0000");
        $('#name').css("font-size", "11pt");
        $('#name').val("이름을 입력해주세요");
        return false;
    }
    if (name == "이름을 입력해주세요")
        return false;
    return true;
}

function isPhoneValid() {
    let phone = $('#phone').val();
    if (phone == null || phone.trim() == "") {
        $('#phone').css("color", "#DD0000");
        $('#phone').css("font-size", "11pt");
        $('#phone').val('휴대폰 인증 버튼을 눌러주세요');
        return false;
    }
    if (phone == "휴대폰 인증 버튼을 눌러주세요")
        return false;
    /*let phoneValid = /^\d{3}-\d{3,4}-\d{4}$/;
    if (!phoneValid.test(phone)) {
        alert("01X-XXXX-XXXX 형식으로 입력해주세요");
        return false;
    }*/
    return true;
}

function emptyName() {
    if ($('#name').val() == "이름을 입력해주세요") {
        $('#name').val("");
        $('#name').css("color", "#555555");
        $('#name').css("font-size", "14pt");
    }
}

function emptyPhone() {
    if ($('#phone').val() == "휴대폰 인증 버튼을 눌러주세요") {
        $('#phone').val("");
        $('#phone').css("color", "#555555");
        $('#phone').css("font-size", "14pt");
    }
}

function smsCheck() {

    let width = 500;
    let height = 600;
    let popUpUrl = "https://www.accountkit.com/v1.0/basic/dialog/sms_login/" +
        "?app_id=2291269470991007&redirect=http%3A%2F%2Flocalhost%3A8089%2Fexit&" +
        "state=112133&fbAppEventsEnabled=true&debug=true";	//팝업창에 출력될 페이지 URL
    let popUpX = (window.screen.width / 2) - (width / 2);
    let popUpY = (window.screen.height / 2) - (height / 2);
    let popUpOption = "width=" + width + ", height=" + height + ", resizable=no, " +
        "scrollbars=yes, status=no, left=" + popUpX + ",top=" + popUpY + ";";

    window.open(popUpUrl, "", popUpOption);
}

function exit() {
    window.close();
}