
$(document).ready(function () {
  $.reply = function(reportId){
    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");
    var content = $('#replyContent').val();


    if(content === replyContent || content === null || content === ''){
      alert("답변을 입력해주세요.");
      return;
    }

    if(confirm("답변을 하시겠습니까?")){
      $.ajax({
        url:"/admin/report/reply",
        type:"POST",

        dataType:"json",
        data :{reportId, content},
        beforeSend: function(xhr) {
          xhr.setRequestHeader("AJAX", true);
          xhr.setRequestHeader(header, token);
        },
        success:function(result){
          if(result === true){
            alert("정상적으로 답변이 등록되었습니다.");
            location.href = "/admin/report/list";
            return;
          }
          else{
            alert("답변이 등록되지 않았습니다. 잠시 후 다시 등록해주세요.");
            return;
          }
        },
        error:function(error){
          console.log(error.responseText);
          alert("예기치 못한 오류가 발생하였습니다. 잠시 후 다시 등록해주세요.");
          return;
        }
      });
    }

  };
});


