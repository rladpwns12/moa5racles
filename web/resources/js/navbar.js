$(document).ready(function() {
  $(".sidehide").on('click',function(){
    $('.doc').toggleClass("show")
    $('.doc').toggleClass("blur")
  });
  $(".hide").on('click',function(){
    $('.doc').toggleClass("show")
    $('.doc').toggleClass("blur")
  });
  $(".logo_img").on('click',function(){
	  location.href="/main";
  });
});
var win;

function reportPopup(){

    if(win != null){
        win.close();
    }

    var popUrl = "/report/send";	//팝업창에 출력될 페이지 URL
    var popupX = (window.screen.width / 2) - (500 / 2);
    // 만들 팝업창 좌우 크기의 1/2 만큼 보정값으로 빼주었음

    var popupY= (window.screen.height / 2) - (482 / 2);

    // 만들 팝업창 상하 크기의 1/2 만큼 보정값으로 빼주었음
    var popOption = "width=500, height=482, resizable=no, " +
        "scrollbars=yes, status=no, " +
        "left="+popupX+",top="+popupY+";";
    win = window.open(popUrl,"",popOption);
    // popup.resizeTo(popupX, popupY);
    // popup.resizeBy(-100, -100);
}