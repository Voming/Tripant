//체크박스 클릭
function spotCkBtnClickHandler(thisElement) {
	var latx = $(thisElement).parent().find(".spot-x").attr("value");
	var lngy = $(thisElement).parent().find(".spot-y").attr("value");
	console.log(latx + ":" + lngy);

	var moveLatLon = new kakao.maps.LatLng(lngy, latx); 
	map.setCenter(moveLatLon);

	//체크된 리스트 반복 
	$(".wrap-spotList").find('input:checked').each(function(index) {
		var title = $(this).parent().find(".spot-name").attr("value");
		var mapx = $(this).parent().find(".spot-x").attr("value");
		var mapy = $(this).parent().find(".spot-y").attr("value");

		calendarPlan.dateArr[index] = new spot(title, mapx, mapy);
	});

	console.log(calendarPlan.dateArr);
}


