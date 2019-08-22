$(document).ready(function () {
    var showPass = 0;
    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");
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

    $('#change_password_btn').on('click', function () {
        var password = $('#password').val();
        var newPassword1 = $('#new_password1').val();
        var newPassword2 = $('#new_password2').val();

        //유효성
        if (password === '' || password == null) {
            alert("현재 비밀번호를 입력해주세요.");
            $('#password').focus();
            return;
        }
        if (newPassword1 === '' || newPassword1 == null) {
            alert("새 비밀번호를 입력해주세요.");
            $('#new_password1').focus();
            return;
        }
        if (!/^.*(?=.{5,20})(?=.*[0-9])(?=.*[a-zA-Z]).*$/.test(newPassword1)) {
            alert('비밀번호는 숫자와 영문자 조합으로 5~20자리를 사용해야 합니다.');
            return;
        }
        if (newPassword2 === '' || newPassword2 == null) {
            alert("새 비밀번호 확인을 입력해주세요.");
            $('#new_password2').focus();
            return;
        }
        //4.새비밀번호 두개가 일치하는지
        if (newPassword1 !== newPassword2) {
            alert('변경할 비밀번호가 일치하지 않습니다.');
            return;
        }
        //최종 업데이트(false인 경우 비밀번호가 현재 비밀번호와 틀린거다.)
        if (confirm("비밀번호를 변경하시겠습니까?")) {
            $.ajax({
                url: "/mypage/myinfo/changepassword",
                type: "POST",
                data: {
                    password: password,
                    newPassword: newPassword1
                },
                dataType: 'json',
                beforeSend: function (xhr) {
                    xhr.setRequestHeader("AJAX", true);
                    xhr.setRequestHeader(header, token);
                },
                success: function (result) {
                    if (result == true) {
                        alert("비밀번호 변경이 완료되었습니다.");
                        location.reload();
                    } else {
                        alert("현재 비밀번호가 올바르지 않습니다.");
                    }
                },
                error: function (request, status, error) {
                    alert("데이터 전송 실패");
                }
            });//--end of submit
        }
    });
});