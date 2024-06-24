// 지도에 표시된 마커 객체를 가지고 있을 배열입니다
var markers = [];  //아이디 , 마커 담김 
function spotCkBtnClickHandler(thisElement) {
	var latx = $(thisElement).parent().find(".spot-x").attr("value");
	var lngy = $(thisElement).parent().find(".spot-y").attr("value");
	var title = $(thisElement).parent().find(".spot-name").attr("value");

	var moveLatLon = new kakao.maps.LatLng(lngy, latx);
	// 확대 크기 변경
	map.setLevel(6);
	// 지도 이동 [setCenter()(바로 이동) -  panTo()(부드럽게 이동)]
	map.panTo(moveLatLon);
	
	//선택된 id 저장
	var id = $(thisElement).attr("id"); 
	
	if ($(thisElement).is(":checked") == false) {  // 체크박스 해제
		//마커 지우기
		$.each(markers, function(idx, element) {
			if (element.id == id) {
				markers[idx].marker.setMap(null);
				markers.splice(idx, 1);
				return false;
			}
		});
		//console.log(markers);
		// 장소 정보 지우기
		$.each(calendarPlan.spotArr, function(idx, element) {  // 체크박스 선택
			if (element.id == id) {
				calendarPlan.spotArr.splice(idx, 1);
				return false;
			}
		});
		//console.log(calendarPlan);
	} else {
		calendarPlan.spotArr[markers.length] = new spot(id, title, latx, lngy);  //전체 일정 만들기 장소 정보 저장
		addMarker(new kakao.maps.LatLng(lngy, latx), title, $(thisElement).attr("id"));
	}
	// 마커 지도에 표시하기
	setMarkers(map);
}

// 마커를 생성하고 지도위에 표시하는 함수입니다
function addMarker(position, title, id) {
	var imageSrc = "/images/loacation/location5.png";// 마커 이미지의 이미지 주소입니다
	var imageSize = new kakao.maps.Size(24, 26);// 마커 이미지의 이미지 크기 입니다
	var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize);// 마커 이미지를 생성합니다  

	// 마커를 생성합니다
	var marker = new kakao.maps.Marker({
		position: position,
		title: title,
		image: markerImage // 마커 이미지 
	});

	// 마커가 지도 위에 표시되도록 설정합니다
	marker.setMap(map);
	// 생성된 마커를 배열에 추가합니다 
	markers.push({ id: id, marker: marker });
}

// 배열에 추가된 마커들을 지도에 표시하거나 삭제하는 함수입니다
function setMarkers(map) {
	for (var i = 0; i < markers.length; i++) {
		markers[i].marker.setMap(map);
	}
}
