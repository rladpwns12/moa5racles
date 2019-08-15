
$(document).ready(function () {
    $('#selectAll').click(function () {
        if($('#selectAll').prop("checked")){
            $("input[name=chk]").prop("checked",true);
        }
        else{
            $("input[name=chk]").prop("checked",false);
        }
    });

    $('#delete_btn').click(function () {
        if(confirm("정말로 삭제하시겠습니가?")){
            var checkArray = new Array();
            $('input[name=chk]:checked').each(function (i) {
                checkArray.push($(this).val());
            })
            console.log(checkArray);
            $.ajax({
                url:"/mypage/message/send/delete",
                type:"POST",
                contentType:"application/json",
                dataType:"json",
                data :JSON.stringify(checkArray),
                success:function(result){
                    if(result == true){
                        alert("삭제가 완료되었습니다.");
                        location.reload();
                        return;
                    }
                    else{
                        alert("삭제에 실패하였습니다.");
                    }
                },
                error:function(error){
                    alert("삭제에 실패하였습니다.");
                }
            });
        }
    });
});
function popupOpen(num){
    var popUrl = "/mypage/message/send/detail/"+num;	//팝업창에 출력될 페이지 URL
    var popupX = (window.screen.width / 2) - (200 / 2);
// 만들 팝업창 좌우 크기의 1/2 만큼 보정값으로 빼주었음

    var popupY= (window.screen.height / 2) - (300 / 2);

// 만들 팝업창 상하 크기의 1/2 만큼 보정값으로 빼주었음
    var popOption = "width=500, height=482, resizable=no, " +
        "scrollbars=yes, status=no, " +
        "left="+popupX+",top="+popupY+";";    //팝업창 옵션(optoin)
    window.open(popUrl,"",popOption);

}


function sendPopup(){
    var popUrl = "/mypage/message/submit";	//팝업창에 출력될 페이지 URL
    var popupX = (window.screen.width / 2) - (500 / 2);
// 만들 팝업창 좌우 크기의 1/2 만큼 보정값으로 빼주었음

    var popupY= (window.screen.height / 2) - (482 / 2);

// 만들 팝업창 상하 크기의 1/2 만큼 보정값으로 빼주었음
    var popOption = "width=500, height=482, resizable=no, " +
        "scrollbars=yes, status=no, " +
        "left="+popupX+",top="+popupY+";";    //팝업창 옵션(optoin)
    window.open(popUrl,"",popOption);
}
