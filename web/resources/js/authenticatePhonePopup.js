function onlyNumberInput2( Ev )
{
    console.log(Ev);
        if (window.event) // IE코드
           var code = window.event.keyCode;
        else // 타브라우저
            var code = Ev.which;

        if ((code > 34 && code < 41) || (code > 47 && code < 58) || (code > 95 && code < 106) )
            {
                window.event.returnValue = true;
               return;
            }

        if (window.event)
             window.event.returnValue = false;
    else
        Ev.preventDefault();
}
$(document).ready(function () {
    $("#submit-btn").click(function authenticate(){
        alert("인증하시겠습니까?");
        console.log("ddd");
        var options = {
            headers: {
                Authorization: '',
                'Content-Type': 'application/json'
            },
            body: {
                message: {
                    to: '01087571048',
                    from: '01087571048',
                    text: 'MOA 인증번호\n본인확인 인증번호[8204]를 화면에 입력해주세요.'
                }
            },
            method: 'POST',
            url: 'http://api.solapi.com/messages/v4/send'
        };

        $.ajax(options).done(function(response) {
            console.log(response);
        });
    });

});
