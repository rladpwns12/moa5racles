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
    $('#withdrawal_btn').on('click', function () {
        var password = $('#password').val();
        console.log(password);
        //유효성
        if(password ==='' || password ==null){
            alert("현재 비밀번호를 입력해주세요.");
            $('#password').focus();
            return;
        }
        if(!/^.*(?=.{5,20})(?=.*[0-9])(?=.*[a-zA-Z]).*$/.test(password)){
            alert('비밀번호는 숫자와 영문자 조합으로 5~20자리를 사용해야 합니다.');
            return;
        }
        if($("input:checkbox[id='agree_info_chk']").prop("checked") == false){
            alert("회원탈퇴 약관에 동의를 해주세요.");
            return;
        }
        if(confirm("정말로 회원탈퇴를 하시겠습니까?")){
            $.ajax({
                url:"/mypage/myinfo/withdrawal",
                type:"POST",
                data : {
                    password:password
                },
                beforeSend: function(xhr) {
                    xhr.setRequestHeader("AJAX", true);
                    xhr.setRequestHeader(header, token);
                },
                success:function(result) {
                    if (result == true) {
                        alert("비밀번호 변경이 완료되었습니다.");
                    }
                    else{
                        alert("현재 비밀번호가 올바르지 않습니다.");
                    }
                },
                error:function(request,status,error){
                    alert("회원탈퇴 처리중 오류가 발생했습니다. 잠시 후, 다시 시도해주세요");
                }
            });//--end of submit
        }
    });
});