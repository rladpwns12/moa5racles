function submit() {
    if (!isValid()) {
        return;
    }

    let name = $('#name').val();
    let email = $('#email').val();
    let phone = $('#phone').val();

    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");
    $.ajax({
        type: "POST",
        url: "searchPassword",
        data: {name, email, phone},
        cache: false,
        beforeSend: function (xhr) {
            xhr.setRequestHeader("AJAX", true);
            xhr.setRequestHeader(header, token);
        },
        success(data) {
            alert(data);
            if (data == "success") {
                alert("Password found");
            } else {
                alert("비밀번호 찾기에 실패하셨습니다");
            }
        }, error() {
            console.log("전송 오류");
        }

    })

    //전송 구현 필요
}

function isValid() {
    if (!isNameValid()) {
        return false;
    }
    if (!isEmailValid()) {
        return false;
    }
    if (!isPhoneValid()) {
        return false;
    }
    return true;
}

function isNameValid() {
    let name = $('#name').val();
    if (name == null || name == "") {
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
    if (email == null || email == "") {
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
    if (phone == null || phone == "") {
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
