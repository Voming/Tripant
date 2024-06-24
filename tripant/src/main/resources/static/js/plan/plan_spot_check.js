//체크박스 클릭
function spotCkBtnClickHandler(thisElement) {
	var latx = $(thisElement).parent().find(".spot-x").attr("value");
	var lngy = $(thisElement).parent().find(".spot-y").attr("value");

	var moveLatLon = new kakao.maps.LatLng(lngy, latx);
	// 확대 크기 변경
	//map.setLevel(6);
	// 지도 이동 setCenter()바로 이동) -  panTo()(부드럽게 이동)
	map.setCenter(moveLatLon);

	//마커가 담길 배열
	var positions = [];
	//체크된 리스트 반복 
	$(".wrap-spotList").find('input:checked').each(function(index) {
		var title = $(this).parent().find(".spot-name").attr("value");
		var mapx = $(this).parent().find(".spot-x").attr("value");
		var mapy = $(this).parent().find(".spot-y").attr("value");
		calendarPlan.spotArr[index] = new spot(title, mapx, mapy);  //전체 일정 만들기 정보 저장
		positions.push({ latlng: new kakao.maps.LatLng(mapy, mapx) }); //마커 표시할 위치 저장
	});
	//console.log(calendarPlan);

	// 마커 이미지의 이미지 주소입니다
	var imageSrc = "/images/loacation/location5.png";
	for (var i = 0; i < positions.length; i++) {
		// 마커 이미지의 이미지 크기 입니다
		var imageSize = new kakao.maps.Size(24, 26);
		// 마커 이미지를 생성합니다    
		var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize);
		// 마커를 생성합니다
		var marker = new kakao.maps.Marker({
			map: map, // 마커를 표시할 지도
			position: positions[i].latlng, // 마커를 표시할 위치
			title: positions[i].title, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
			image: markerImage // 마커 이미지 
		});
	}
}