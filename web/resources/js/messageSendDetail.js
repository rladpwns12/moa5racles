var MISMATCH_SENDER="mismathSender";
var NOT_EXISTS="notExists";
var  OK="ok";
var FAIL="fail";
var TOOLONG="tooLong";
$(document).ready(function () {
    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");
    $(".cancel_btn").click(function close(){
        window.close();
    });
    $(".submit_btn").click(function () {
       if(confirm("정말로 전송하시겠습니까?")){
           if($("#receiverId").val()=="" || $("#content").val()==""){
               alert("받는 사람 혹은 내용을 입력해주세요.");
               return;
           }
           var messageData={};
           messageData["receiverNick"] = $("#receiverId").val();
           messageData["senderNick"] = $("#senderId").text();
           messageData["content"] = $("#content").val();
           $.ajax({
               url:"/mypage/message/sendmessage",
               type:"POST",
               contentType:"application/json; charset=UTF-8",
               data :JSON.stringify(messageData),
               beforeSend: function(xhr) {
                   xhr.setRequestHeader("AJAX", true);
                   xhr.setRequestHeader(header, token);
               },
               success:function(result){
                   switch (result) {
                       case OK :
                           alert("전송되었습니다.");
                           window.close();
                           break;
                       case MISMATCH_SENDER:
                           alert("전송에 실패하였습니다.\n로그인을 다시 해주세요.");
                           break;
                       case NOT_EXISTS :
                           alert("전송에 실패하였습니다.\n받는 사람 닉네임을 다시 한번 확인해주세요.");
                           break;
                       case FAIL :
                           alert("전송에 실패하였습니다.\n 잠시뒤 시도해 주세요.");
                           break;
                       case TOOLONG:
                           alert("메세지는 최대 3000 글자까지 보낼 수 있습니다.");
                           break;
                   }
               },
               error:function(request,status,error){
                   console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
                   alert("전송에 실패하였습니다.");
               }
           });
       }
       else{
           alert("전송이 취소되었습니다.")
       }
    });
});
