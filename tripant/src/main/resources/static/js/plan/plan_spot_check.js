//체크박스 클릭
function spotCkBtnClickHandler(thisElement) {
	console.log(thisElement);
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


// 바로 이동
function setCenter() {
	// 이동할 위도 경도 위치를 생성합니다 
	var moveLatLon = new kakao.maps.LatLng(37.5670686420, 127.0101708978);

	// 지도 중심을 이동 시킵니다
	map.setCenter(moveLatLon);
}
// 부드럽게 이동
function panTo() {
	// 이동할 위도 경도 위치를 생성합니다 
	var moveLatLon = new kakao.maps.LatLng(33.450580, 126.574942);

	// 지도 중심을 부드럽게 이동시킵니다
	// 만약 이동할 거리가 지도 화면보다 크면 부드러운 효과 없이 이동합니다
	map.panTo(moveLatLon);
}  