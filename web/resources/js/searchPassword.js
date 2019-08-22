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
    if (!isValid()) {
        return;
    }
    if(phoneFlag === false){
        alert("휴대폰 인증을 해주세요.");
        return;
    }

    let name = $('#name').val().trim();
    let email = $('#email').val().trim();
    let phone = $('#phone').val().trim();

    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");
    $.ajax({
        type: "POST",
        url: "/searchPassword",
        data: {name, email, phone},
        dataType:"json",
        cache: false,
        beforeSend: function (xhr) {
            xhr.setRequestHeader("AJAX", true);
            xhr.setRequestHeader(header, token);
        },
        success(data) {
            if (data) {
                $('#content1').hide();
                $('#content2').show();
            } else {
                alert("회원 정보를 잘못 입력하셨습니다");
            }
        }, error() {
            console.log("전송 오류");
        }
    });
}

function submitPassword() {
    let name = $('#name').val();
    let email = $('#email').val();
    let password = $('#password').val();
    let password2 = $('#password2').val();
    if (!isPasswordValid(password)) {
        return;
    }
    if (!isPassword2Valid(password, password2)) {
        return;
    }

    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");
    $.ajax({
        type: "POST",
        url: "/updatePassword",
        data: {email, name, password},
        dataType:'json',
        cache: false,
        beforeSend: function (xhr) {
            xhr.setRequestHeader("AJAX", true);
            xhr.setRequestHeader(header, token);
        },
        success(data) {
            if (data) {
                window.alert("비밀번호가 변경되었습니다");
                window.close();
            } else {
                alert("비밀번호 수정에 실패하였습니다");
            }
        }, error: function (request, status, error) {
            console.log("전송 오류");
        }
    });
}

function isValid() {
    var result;
    if (!isNameValid()) {
        result = false;
    }
    if (!isEmailValid()) {
        result = false;
    }
    if (!isPhoneValid()) {
        result = false;
    }
    if (result == false) {
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

function isEmailValid() {
    let email = $('#email').val();
    if (email == null || email.trim() == "") {
        $('#email').css("color", "#DD0000");
        $('#email').css("font-size", "11pt");
        $('#email').val("이메일을 입력해주세요");
        return false;
    }
    if (email == "이메일을 입력해주세요")
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
    return true;
}

function emptyName() {
    if ($('#name').val() == "이름을 입력해주세요") {
        $('#name').val("");
        $('#name').css("color", "#555555");
        $('#name').css("font-size", "14pt");
    }
}

function emptyEmail() {
    if ($('#email').val() == "이메일을 입력해주세요") {
        $('#email').val("");
        $('#email').css("color", "#555555");
        $('#email').css("font-size", "14pt");
    }
}

function emptyPhone() {
    if ($('#phone').val() == "휴대폰 인증 버튼을 눌러주세요") {
        $('#phone').val("");
        $('#phone').css("color", "#555555");
        $('#phone').css("font-size", "14pt");
    }
}

function isPasswordValid(input) {
    if (input == null || input == "") {
        $('#password').css("border", "solid 0.2px red");
        alert("비밀번호를 입력해주세요");
        $('#password').val("");
        $('#password').focus();
        return false;
    }
    if (input.length < 5) {
        alert("비밀번호가 너무 짧습니다.");
        $('#password').css("border", "solid 0.2px red");
        $('#password').val("");
        $('#password').focus();
        return false;
    }
    if (input.length > 20) {
        alert("비밀번호가 너무 깁니다.");
        $('#password').css("border", "solid 0.2px red");
        $('#password').val("");
        $('#password').focus();
        return false;
    }
    return true;
}

function isPassword2Valid(input1, input2) {
    if (input2 == null || input2 == "") {
        $('#password2').css("border", "solid 0.2px red");
        alert("비밀번호 확인을 입력해주세요");
        $('#password2').val("");
        $('#password2').focus();
        return false;
    } else if (input1 != input2) {
        alert("비밀번호가 일치하지 않습니다.");
        $('#password2').val("");
        $('#password2').focus();
        return false;
    }
    return true;
}

function resetPassword() {
    $('#password').css('border', 'solid 0.2px #D6D6D6');
    $('#password').val('');
}

function resetPassword2() {
    $('#password2').css('border', 'solid 0.2px #D6D6D6');
    $('#password2').val('');
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