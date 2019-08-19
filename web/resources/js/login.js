var win1;
var win2;
(function ($) {
    "use strict";
    var input = $('.validate-input .input100');

    $('.validate-form').on('submit', function () {
        var check = true;
        for (var i = 0; i < input.length; i++) {
            if (validate(input[i]) == false) {
                showValidate(input[i]);
                check = false;
            }
        }
        return check;
    });

    $('.validate-form .input100').each(function () {
        $(this).focus(function () {
            hideValidate(this);
        });
    });

    function validate(input) {
        if ($(input).attr('type') == 'email' || $(input).attr('name') == 'email') {
            if ($(input).val().trim().match(/^([a-zA-Z0-9_\-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([a-zA-Z0-9\-]+\.)+))([a-zA-Z]{1,5}|[0-9]{1,3})(\]?)$/) == null) {
                return false;
            }
        } else {
            if ($(input).val().trim() == '') {
                return false;
            }
        }
    }

    function showValidate(input) {
        var thisAlert = $(input).parent();

        $(thisAlert).addClass('alert-validate');
    }

    function hideValidate(input) {
        var thisAlert = $(input).parent();

        $(thisAlert).removeClass('alert-validate');
    }

    var showPass = 0;
    $('.btn-show-pass').on('click', function () {
        if (showPass == 0) {
            $(this).next('input').attr('type', 'text');
            $(this).find('i').removeClass('fa-eye');
            $(this).find('i').addClass('fa-eye-slash');
            showPass = 1;
        } else {
            $(this).next('input').attr('type', 'password');
            $(this).find('i').removeClass('fa-eye-slash');
            $(this).find('i').addClass('fa-eye');
            showPass = 0;
        }
    });
})(jQuery);

function searchId() {
    if(win1 != null){
        win1.close();
    }
    let width = 500;
    let height = 600;
    let popUpUrl = "/searchId";
    let popUpX = (window.screen.width / 2) - (width / 2);
    let popUpY = (window.screen.height / 2) - (height / 2);
    let popUpOption = "width=" + width + ", height=" + height + ", resizable=true, " +
        "scrollbars=yes, status=no, left=" + popUpX + ",top=" + popUpY + ";";

    win1 = window.open(popUpUrl, "", popUpOption);
    win1.focus();
}

function searchPassword() {
    if(win2 != null){
        win2.close();
    }
    let width = 500;
    let height = 600;
    let popUpUrl = "/searchPassword";
    let popUpX = (window.screen.width / 2) - (width / 2);
    let popUpY = (window.screen.height / 2) - (height / 2);
    let popUpOption = "width=" + width + ", height=" + height + ", resizable=no, " +
        "scrollbars=yes, status=no, left=" + popUpX + ",top=" + popUpY + ";";

    win2 = window.open(popUpUrl, "", popUpOption);
    win2.focus();
}

function submit() {
    if (!isValid()) {
        return;
    }

    $.ajax({});

    return true;
}

function isValid() {
    if (!isEmailValid()) {
        return false;
    }
    if (!isPasswordValid()) {
        return false;
    }
    return true;
}

function isEmailValid() {
    if ($('#email').val()==null || $('#email').val().trim()=="") {
        alert("이메일을 입력해주세요");
        $('#email').focus();
        return false;
    }
    return true;
}

function isPasswordValid() {
    if ($('#password').val()==null || $('#password').val().trim()=="") {
        alert("비밀번호를 입력해주세요");
        $('#password').focus();
        return false;
    }
    return true;
}

