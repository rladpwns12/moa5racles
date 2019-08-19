$(document).ready(function(){
    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");

    $('.submit_btn').click(function(){
       if(!isValid()){
           return;
       }

       var userId = $('#userId').val();
       var content = $('#content').val();
       var targetUserNick = $('#targetNick').val();
       var targetId = -1;
       var targetType = 0;

       $.ajax({
           url:"/report/send",
           type:"POST",
           contentType:"application/json",
           dataType:"json",
           data:{
               userId: userId,
               content: content,
               targetUserNick: targetUserNick,
               targetId: targetId,
               targetType: targetType
           },
           beforeSend: function(xhr){
               xhr.setRequestHeader("AJAX", true);
               xhr.setRequestHeader(header, token);
           },
           success: function(result){
               if(result == true){
                   alert('신고가 정상적으로 완료되었습니다.');
                   window.close();
                   return;
               }
               alert('해당 닉네임이 존재하지 않습니다.');
               return;
           },
           error: function (error) {
               alert('신고가 정상적으로 완료되지 않았습니다.\n고객센터로 문의해 주세요.');
               return;
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