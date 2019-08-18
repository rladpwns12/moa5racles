var randNum = '';
var countFlag = false;
var phoneRegExp = /^\d{3}\d{3,4}\d{4}$/;
var numberRegExp = /^\d{4}$/;
var userPhone;


function maxLengthCheck(object){
    if (object.value.length > object.maxLength){
        object.value = object.value.slice(0, object.maxLength);
    }
}
$(document).ready(function () {
    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");

    $("#submit-btn").click(function submit(){
        userPhone = $('#phone-number').val();

        //-- start of validation
        if(userPhone === '' || userPhone === null){
            alert("휴대폰 번호를 입력해주세요.");
            return;
        }
        else if(userPhone.length < 10 || userPhone.length>11){
            alert("휴대폰 번호는 9글자 이하, 12글자 이상이 될 수 없습니다.");
            return;
        }
        if(!phoneRegExp.test(userPhone)){
            alert("올바른 휴대폰 형식이 아닙니다.\n-,공백을 제외한 휴대폰 번호를 입력해주세요.")
            return;
        }
        if(countFlag == true){
            if(confirm("이미 인증번호가 발송되었습니다. 다시 발송하시겠습니까?")){

            }else{
                return;
            }

        }
        //-- end of validation
        //-- start of creat randomNumber
        randNum = ''+Math.floor(Math.random() * 9)
            +Math.floor(Math.random() * 9)
            +Math.floor(Math.random() * 9)
            +Math.floor(Math.random() * 9);

        //-- end of create randomNumber

        //-- start of ajax for submit phone message 입력한 휴대폰 번호에 랜덤 번호 전송(ajax-post)
        var phoneNumber = userPhone;
        var randomNumber = randNum;
        var data = {phoneNumber,randomNumber};
        $.ajax({
            url:"/send/phonemessage",
            type:"POST",
            contentType:"application/json",
            dataType:"json",
            data :JSON.stringify(data),
            beforeSend: function(xhr) {
                xhr.setRequestHeader("AJAX", true);
                xhr.setRequestHeader(header, token);
            },
            success:function(result){
                if(result == true){
                    countFlag = true;
                    alert("인증번호가 발송되었습니다. ");
                    return;
                }
                else{
                    alert("인증번호 발송에 실패하였습니다. 잠시후 다시 이용해주세요.");
                }
            },
            error:function(error){
                alert("인증번호 발송에 실패하였습니다. 잠시후 다시 이용해주세요.");
            }
        });
        //-- end of ajax for submit phone message
    });

    //-- start of check ranadNubmer
    $("#check-btn").click(function check(){
        var inputNubmer = $('#check-number').val();
        //-- start of validation
        if(randNum === '' || randNum === null){
            alert("인증번호 발송을 해주세요.");
            return;
        }
        if(inputNubmer === '' || inputNubmer === null){
            alert("인증번호를 입력해주세요.");
            return;
        }
        if(!numberRegExp.test(inputNubmer)){
            alert("인증번호는 4자리의 숫자로 구성되어있습니다.");
            return;
        }
        //-- end of validation

        //-- start of check
        if(randNum === inputNubmer){
            alert("휴대폰 인증에 성공하셨습니다.");
            opener.setChildValue(userPhone);
            //부모창 js에 다음과 같은 코드 입력
            //function setChildValue(userPhone){
            //window.opener.document.getElementById("휴대폰번호를 입력받을 id").value = userPhone;}
            window.close();
        }
        else{
            alert("인증번호가 맞지않습니다. 다시 한번 확인해주세요.");
            return;
        }
        //-- end of check
    });
});
/* 부모창 js
var win;
function setChildValue(phoneNumber){
    document.getElementById("phone").value = phoneNumber;
}

function openAuthenticatePhone() {
    if(win != null){
        win.close();
    }
    var popUrl = "/authenticatePhone";	//팝업창에 출력될 페이지 URL
    var popupX = (window.screen.width / 2) - (200 / 2);
// 만들 팝업창 좌우 크기의 1/2 만큼 보정값으로 빼주었음
    var popupY = (window.screen.height / 2) - (300 / 2);

// 만들 팝업창 상하 크기의 1/2 만큼 보정값으로 빼주었음
    var popOption = "width=500, height=482, resizable=no, " +
        "scrollbars=yes, status=no, " +
        "left=" + popupX + ",top=" + popupY + ";";//팝업창 옵션(optoin)
    win = window.open(popUrl, "", popOption);
    win.focus();

}
*/