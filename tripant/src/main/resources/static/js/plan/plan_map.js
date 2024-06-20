// 지도 세팅
$(document).ready(function() {
	$.ajax({
		url: contextPath + "plan/area"
		, method: "post"
		, data: {
			areaCode: area
		}
		, dataType: 'json'
		, success: function(result) {
			if (result != null) {
				/*console.log(result);*/
				displayAreaInfo(result);
			}
		}
	});

	var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
		mapOption = {
			center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표 (y값, x값)  
			level: 9 // 지도의 확대 레벨
		};

	var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다
});

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