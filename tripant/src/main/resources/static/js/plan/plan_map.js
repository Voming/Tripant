

/*--------------MAP-----------------------------------------------------------------------*/
/* 1. 지도 생성*/
var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
    mapOption = { 
	center: new kakao.maps.LatLng(33.511111, 126.492778), // 지도의 중심좌표 :  제주공항 여행 지역 별 중심좌표 차등 부여
        level: 9// 지도의 확대 레벨 큰 숫자 : 큰 범위
    };
//지도를 생성합니다
var map = new kakao.maps.Map(mapContainer, mapOption);  


//버튼을 클릭하면 아래 배열의 좌표들이 모두 보이게 지도 범위를 재설정합니다 

/* 추가한 일정들 객체화하여 좌표 넣기 */
var points = [ //지도에 표시될 위치 (일정 추가일 때) - 제주
	new kakao.maps.LatLng(33.5063870002, 126.4639059537),
    new kakao.maps.LatLng(33.4843271774, 126.3911377809),
    new kakao.maps.LatLng(33.5187500203, 126.4953529380)
];
//지도를 재설정할 범위정보를 가지고 있을 LatLngBounds 객체를 생성합니다
var bounds = new kakao.maps.LatLngBounds();   

/*마커 추가 및 생성*/
var i, marker;
var imageSrc = '/images/loacation/location1.png', // 마커이미지의 주소입니다    
	imageSize = new kakao.maps.Size(24, 26); // 마커이미지의 크기입니다
//var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize);
var markerImage = new kakao.maps.MarkerImage(imageSrc,imageSize);

	
for (i = 0; i < points.length; i++) {
//    // 배열의 좌표들이 잘 보이게 마커를 지도에 추가합니다
//    marker = new kakao.maps.Marker(
//    	{position : points[i],
//	    image : markerImage });

     // customOverlay 생성 - 마커위에 숫자 올리기
    var content = `       
	    <div class="custom-marker" th:fragment="markernum(i)">
	        <img src="${imageSrc}" style="width: 30px; height: 32px;">
	        <span>${i + 1}</span>
	    </div>`;
    var customOverlay = new kakao.maps.CustomOverlay({
        position: points[i],
        content: content,
        yAnchor: 1
    });
	    
    //marker.setMap(map); //지도 위에 마커 표시
    customOverlay.setMap(map); //지도 위에 마커 표시
    
 	/*지도에 표시할 선을 생성합니다*/ 
    var polyline = new kakao.maps.Polyline({
        path: points, // 선을 구성하는 좌표배열 입니다
        strokeWeight: 2, // 선의 두께 입니다
        strokeColor: '#E54B4B', // 선의 색깔입니다
        strokeOpacity: 0.7, // 선의 불투명도 입니다 1에서 0 사이의 값이며 0에 가까울수록 투명합니다
        strokeStyle: 'shortdash' // 선의 스타일입니다
    });
    
 	// 지도에 선을 표시합니다 
    polyline.setMap(map);  
    
    // LatLngBounds 객체에 좌표를 추가합니다
    bounds.extend(points[i]);
}

/* 3. 지도 범위 재설정 */
function setBounds() {
    // LatLngBounds 객체에 추가된 좌표들을 기준으로 지도의 범위를 재설정합니다
    // 이때 지도의 중심좌표와 레벨이 변경될 수 있습니다
    map.setBounds(bounds);
}

/* 지도 중심 이동하기*/
//이동할 좌표 입력
function panTo() {
	//x좌표 위도 100번대
	var latx= $(this).parent().prev().prev().children(".mapx").val();
	//y좌표 경도 33번대
	var lngy= $(this).parent().prev().children(".mapy").val();
	console.log("check!!!!!!!!!!!!!!!!!!!!");
	console.log(latx);
    // 이동할 위도 경도 위치를 생성합니다 
    var moveLatLon = new kakao.maps.LatLng(lngy,latx);
    
    // 지도 중심을 부드럽게 이동시킵니다
    // 만약 이동할 거리가 지도 화면보다 크면 부드러운 효과 없이 이동합니다
    map.panTo(moveLatLon);       
}
