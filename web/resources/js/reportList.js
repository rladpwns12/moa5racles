$(document).ready(function(){
	var win;
	$('.rows').click(function(){
		var tr = $(this);
		var reportId = tr.attr('value');
		console.log('js reportId : ' + reportId);
		popupOpen(reportId);
	});

	function popupOpen(reportId){
		if(win != null) win.close();
		var popUrl = "/report/detail/"+reportId;
		var popupX = (window.screen.width / 2) - (200 / 2);
		var popupY= (window.screen.height / 2) - (300 / 2);

		var popOption = "width=500, height=562, resizable=no, " +
			"scrollbars=yes, status=no, " +
			"left="+popupX+",top="+popupY+";";

		win = window.open(popUrl,"",popOption);
	}
});
