var token = $("meta[name='_csrf']").attr("content");
var header = $("meta[name='_csrf_header']").attr("content");


function refuse(userId,url) {
    alert(url);
    var context = $('#content').val();
    //validation
    if(context == '' || context == null){
        alert('반드시 거절 사유를 적어주시기 바랍니다.');
        return;
    }
    //start of ajax
    $.ajax({
        url:"/admin/"+url+"/refuse?userId="+userId+"&context="+context,
        dataType:'json',
        success:function(result,url){
            if(result == true){
                alert("승인이 거절되었습니다.");
                location.href='/admin/'+url+'/list';
                return;
            }
            else{
                alert("승인 거절이 실패하였습니다. 이미 거절/승인 완료된 신청입니다.");
            }
        },
        error:function(error){
            alert("승인 거절이 실패하였습니다. 잠시 후 다시 이용해주세요.");
        }
    });//end of ajax
}


function approve(userId,url) {
    console.log(userId);
    var context = $('#content').val();
    //validation
    if(context == '' || context == null){
        context  = '특이사항 없음.';
    }
    //start of ajax
    $.ajax({
        url:"/admin/"+url+"/confirm?userId="+userId+"&context="+context,
        dataType:'json',
        success:function(result){
            if(result == true){
                alert("승인이 되었습니다.");
                location.href='/admin/'+url+'/list';
                return;
            }
            else{
                alert("승인이 실패하였습니다. 이미 거절/승인 완료된 신청입니다.");
            }
        },
        error:function(error){
            alert("승인이 실패하였습니다. 잠시 후 다시 이용해주세요.");
        }
    });//end of ajax

}
$(document).ready(function () {

});


