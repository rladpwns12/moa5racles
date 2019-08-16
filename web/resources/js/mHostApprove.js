
$(document).ready(function () {


    $('#delete_btn').click(function () {
        var checkArray = new Array();
        $('input[name=chk]:checked').each(function (i) {
            checkArray.push($(this).val());
        })
        if(checkArray.length === 0){
            alert("선택된 메시지가 없습니다.");
            return;
        }
        if(confirm("정말로 삭제하시겠습니가?")){

        }
    });
});
function goToInformation(id,type){
    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");
    id = JSON.stringify(id);
    type = JSON.stringify(type);

    $.ajax({
        url:"/admin/hostapprove/info",
        type:"get",
        contentType:"application/json",
        dataType:"json",
        data :{
            id,type
        },
        beforeSend: function(xhr) {
            xhr.setRequestHeader("AJAX", true);
            xhr.setRequestHeader(header, token);
        },
        success:function(result){

        },
        error:function(error){
            alert("정보를 불러 올 수 없습니다. 잠시 후 다시 이용해주세요.");
            console.log(error);
        }
    });
}

