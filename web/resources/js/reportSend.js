$(document).ready(function(){
    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");

    $('.submit_btn').click(function(){
       if(!isValid()){
           return;
       }

       if(!confirm('정말로 신고하시겠습니까?')) return;

       var userId = $('#userId').val();
       var content = $('#content').val();
       var targetUserNick = $('#targetNick').val();
       var targetId = 0;
       var targetType = 0;

       var formData = {
           userId: userId,
           content: content,
           targetUserNick: targetUserNick,
           targetId: targetId,
           targetType: targetType
       }
       var jsonData = JSON.stringify(formData);
       $.ajax({
           url:"/report/send",
           type:"POST",
           contentType:"application/json; charset=utf-8",
           data:jsonData,
           cache: false,
           beforeSend: function(xhr){
               xhr.setRequestHeader("AJAX", true);
               xhr.setRequestHeader(header, token);
           },
           success: function(result){
               switch (result) {
                   case OK :
                       alert('신고가 정상적으로 완료되었습니다.');
                       window.close();
                       break;
                   case MISMATCH:
                       alert("전송에 실패하였습니다.\n로그인을 다시 해주세요.");
                       break;
                   case FAIL :
                       alert("전송에 실패하였습니다.\n신고 대상 닉네임을 다시 한번 확인해주세요.");
                       break;
                   case TARGET|TOOLONG:
                       alert("전송에 실패하였습니다.\n신고 대상 닉네임을 다시 한번 확인해주세요.");
                       break;
                   case MESSAGE_CONTENT|TOOLONG:
                       alert("메세지는 최대 3000 글자까지 보낼 수 있습니다.");
                       break;
                   case SELF:
                       alert("자기 자신은 신고 할 수 없습니다");
                       break;
               }
           },
           error:function(request,status,error){
               console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
               alert('신고가 정상적으로 완료되지 않았습니다.\n 잠시뒤 시도 해 주세요.');
           }
       });
    });

    $('.cancel_btn').click(function(){
        var result;
        result = confirm('작성한 내용은 저장되지 않습니다. 신고하기 창을 닫으시겠습니까?');
        if(result == true){
            window.close();
        }
    });

    function isValid(){
        if($('#receiverId').val() == "") {
            alert('신고 대상을 입력해 주세요.')
            return false;
        }
        if($('#content').val() == ""){
            alert('내용을 입력해 주세요.');
            return false;
        }
        return true;
    }
});