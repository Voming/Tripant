//초기 숙소 들어갈 부분 세팅(plan_calendar.js에서 호출)
function displayStayBox() {
	console.log(calendarPlan);
	for(var i = 0; i < calendarPlan.dateArr.length - 1; i++){
		var start = calendarPlan.dateArr[i].smalldate;
		var end = calendarPlan.dateArr[i+1].smalldate;
		//화면 리스트 추가
		var htmlVal = "";
		htmlVal += `
			<div class="selected-stay-box">
				<span class="box-id" value="" style ="display:none"></span> 
				<div class="wrap-box flex">
					<div class="selected-stay-number">
						<p>${i+1}</p>
					</div>
					<img class="selected-stay-img" src="/images/plan/stay_plus.png">
					<div class="selected-stay-txt">
						<span style="font-size:var(--font5); color:var(--color_gray);">${start} ~ ${end}</span>
						<span style="font-size:var(--font6); color:pink;">숙소를 추가해주세요</span>
					</div>
					<div>
						<img class="delete btn" src="/images/icons/trashcan.png" onclick="stayDeleteBtnClickHandler(this);">
					</div>
				</div>
			</div>`;
		$(".selected-stay-list").append(htmlVal);
	}
}


// 지도에 표시된 마커 객체를 가지고 있을 배열입니다
var markersStay = [];  //아이디 , 마커 담김 
function stayCkBtnClickHandler(thisElement) {
	var latx = $(thisElement).parent().find(".stay-x").attr("value");
	var lngy = $(thisElement).parent().find(".stay-y").attr("value");
	var title = $(thisElement).parent().find(".stay-name").attr("value");
	var img = $(thisElement).parent().find(".stay-img").attr("src");
	var addr = $(thisElement).parent().find(".stay-address").attr("value");
	//선택된 id 저장
	var id = $(thisElement).attr("id");

	var moveLatLon = new kakao.maps.LatLng(lngy, latx); // 맵 위치로 변환
	// 확대 크기 변경
	map.setLevel(6);
	// 지도 이동 [setCenter()(바로 이동) -  panTo()(부드럽게 이동)]
	map.panTo(moveLatLon);


	//=======================================체크박스 해제=========================================
	if ($(thisElement).is(":checked") == false) {
		/*//마커 삭제
		setMarkersStay(null);
		markersStay.length = 0;
		//마커 다시 붙이기
		$(".wrap-stayList").find('input:checked').each(function(index) {
			var title = $(this).parent().find(".stay-name").attr("value");
			var mapx = $(this).parent().find(".stay-x").attr("value");
			var mapy = $(this).parent().find(".stay-y").attr("value");
			addMarkerStay(new kakao.maps.LatLng(mapy, mapx), title, $(this).attr("id"), index); // 마커 추가
			setMarkersStay(map); // 마커 지도에 표시하기
		});

		// 장소 정보 삭제
		$.each(calendarPlan.stayArr, function(idx, element) {
			if (element.id == id) {
				calendarPlan.stayArr.splice(idx, 1);
				return false;
			}
		});
		//console.log(calendarPlan);
		// 박스 리스트 삭제
		$(".selected-stay-box." + id).remove();
		$(".count-stay").html(markers.length);

		// 박스 번호 다시 붙이기
		$(".selected-stay-box").each(function(idx, thisElement) {
			$(thisElement).find(".selected-stay-number").children().text(idx + 1);
		});

		// 장소 설정 정보 부분 업데이트
		$(".count-stay").html(markersStay.length);
		timeInfoUpdate();// 총 시간 업데이트*/

	} else { //=====================================체크박스 선택=========================================
		//calendarPlan.stayArr[markersStay.length] = new stay(id, title, latx, lngy);  //전체 일정 만들기 장소 정보 저장
		addMarkerStay(new kakao.maps.LatLng(lngy, latx), title, $(thisElement).attr("id"), markersStay.length); // 마커 추가
		setMarkersStay(map); // 마커 지도에 표시하기

		//화면 리스트 추가
		var htmlVal = "";
		htmlVal += `
			<div class="selected-stay-box ${id}">
				<span class="box-id" value="${id}" style ="display:none"></span> 
				<div class="wrap-box flex">
					<div class="selected-stay-number">
						<p>${markersStay.length}</p>
					</div>
					<img class="selected-stay-img" src="${img}">
					<div class="selected-stay-txt">
						<span>${title}</span>
						<span>${addr}</span>
					</div>
					<div>
						<img class="delete btn" src="/images/icons/trashcan.png" onclick="stayDeleteBtnClickHandler(this);">
					</div>
				</div>
			</div>`;
		$(".selected-stay-list").append(htmlVal);

		// 장소 설정 정보 부분 업데이트
		$(".count-stay").html(markersStay.length);
	}
}

// 숙소 삭제
function stayDeleteBtnClickHandler(thisElement) {
}

// 숙소 설정 초기화
function stayResetBtnClickHandler() {
	$(".selected-stay-box").remove();
	/*calendarPlan.stayArr.length = 0;
	//체크박스 해제
	$(".staycheck").prop("checked", false);
	//마커 삭제
	setMarkersStay(null);
	markersStay.length = 0;
	// 장소 설정 정보 삭제
	$(".time-stay").html("");
	$(".count-stay").html(0);*/
}



// 마커를 생성하고 지도위에 표시하는 함수입니다
function addMarkerStay(position, title, id, index) {
	var imageSrc = "/images/loacation/location3.png";// 마커 이미지의 이미지 주소입니다
	var content = `       
		    <div class="custom-marker" th:fragment="markernum(i)">
		        <img src="${imageSrc}" style="width: 30px; height: 32px;">
		        <span>${index + 1}</span>
		    </div>`;
	// 마커를 생성합니다
	var marker = new kakao.maps.CustomOverlay({
		position: position,
		content: content,
		title: title,
		yAnchor: 1
	});
	// 마커가 지도 위에 표시되도록 설정합니다
	marker.setMap(map);
	// 생성된 마커를 배열에 추가합니다 
	markersStay.push({ id: id, marker: marker });
}

// 배열에 추가된 마커들을 지도에 표시하거나 삭제하는 함수입니다
function setMarkersStay(map) {
	for (var i = 0; i < markersSpot.length; i++) {
		markersStay[i].marker.setMap(map);
	}
}


