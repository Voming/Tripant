// 지도에 표시된 마커 객체를 가지고 있을 배열입니다
var markers = [];  //아이디 , 마커 담김 
function spotCkBtnClickHandler(thisElement) {
	var latx = $(thisElement).parent().find(".spot-x").attr("value");
	var lngy = $(thisElement).parent().find(".spot-y").attr("value");
	var title = $(thisElement).parent().find(".spot-name").attr("value");
	var img = $(thisElement).parent().find(".spot-img").attr("src");
	var addr = $(thisElement).parent().find(".spot-address").attr("value");
	//선택된 id 저장
	var id = $(thisElement).attr("id");

	var moveLatLon = new kakao.maps.LatLng(lngy, latx); // 맵 위치로 변환
	// 확대 크기 변경
	map.setLevel(6);
	// 지도 이동 [setCenter()(바로 이동) -  panTo()(부드럽게 이동)]
	map.panTo(moveLatLon);


	// 체크박스 해제
	if ($(thisElement).is(":checked") == false) {
		//마커 삭제
		$.each(markers, function(idx, element) {
			if (element.id == id) {
				markers[idx].marker.setMap(null);
				markers.splice(idx, 1);
				return false;
			}
		});
		//console.log(markers);
		// 장소 정보 삭제
		$.each(calendarPlan.spotArr, function(idx, element) {  // 체크박스 선택
			if (element.id == id) {
				calendarPlan.spotArr.splice(idx, 1);
				return false;
			}
		});
		//console.log(calendarPlan);
		//화면 리스트 삭제
		$(".selected-spot-box." + id).remove();
		$(".count-sum").html(markers.length);
		
		// 번호 다시 붙이기
		$(".selected-spot-box").each(function(idx, thisElement) {
			$(thisElement).find(".selected-spot-number").children().text(idx+1);
		});
		
	} else { //체크박스 선택
		calendarPlan.spotArr[markers.length] = new spot(id, title, latx, lngy);  //전체 일정 만들기 장소 정보 저장
		addMarker(new kakao.maps.LatLng(lngy, latx), title, $(thisElement).attr("id")); // 마커 추가
		setMarkers(map); // 마커 지도에 표시하기

		//화면 리스트 추가
		var htmlVal = "";
		htmlVal += `
			<div class="selected-spot-box ${id}">
				<span class="box-id" value="${id}" style ="display:none"></span> 
				<div class="wrap-box flex">
					<div class="selected-spot-number">
						<p>${markers.length}</p>
					</div>
	
					<img class="selected-spot-img" src="${img}">
					<div class="selected-spot-txt">
						<span>${title}</span>
						<span>${addr}</span>
					</div>
					<div>
						<p class="time ${markers.length}" onclick="timeRangeBtnClickHandler(this);">2시간 0분</p>
					</div>
				</div>
				<div class="timerange-modal flex" style="display:none;">
					<p style="margin-left:30px; font-weight: bold;">머무는 시간 설정</p>
					<input class="spot-hours" type='number' value="2"></input>
					<p>시간</p>
					<input class="spot-mins" type='number' value="0"></input>
					<p>분</p>
					<p class="timerange-done" onclick="timeDoneBtnClickHandler(this);">완료</p>
				</div>
			</div>`;
		$(".selected-spot-list").append(htmlVal);

		// 장소 설정 정보 부분 업데이트
		$(".count-sum").html(markers.length);

		var calcHouers;
		var calcMins;
		var secSum = 0;
		$(".timerange-modal").each(function() {
			var hours = parseInt($(this).children('.spot-hours').attr("value"));
			var mins = parseInt($(this).children('.spot-mins').attr("value"));
			secSum += (hours * 60 * 60) + (mins * 60); //초로 변환하기

			//초 -> 시간, 분
			calcHouers = Math.floor(secSum / 3600);
			calcMins = Math.floor((secSum - calcHouers * 3600) / 60);
		});

		var timeVal = calcHouers + "시간 " + calcMins + "분 / 총 가능 시간";
		$(".time-sum").html(timeVal);
	}
}

//시간 설정 모달 열림
function timeRangeBtnClickHandler(thisElement) {
	$(thisElement).parents('.wrap-box').hide();
	$(thisElement).parents('.wrap-box').next().show();
}
// 시간 설정 완료
function timeDoneBtnClickHandler(thisElement) {
	$(thisElement).parents('.timerange-modal').prev().show();
	$(thisElement).parents('.timerange-modal').hide();

	$(".timerange-modal").each(function() {
		console.log($(this));
		var id = $(this).prev().prev().attr("value");
		hours += parseInt($(this).children('.spot-hours').attr("value"));
		mins += parseInt($(this).children('.spot-mins').attr("value"));
		console.log(hours + ":" + mins);
		//초로 변환하기
		var secSum = (hours * 60 * 60) + (mins * 60);
		console.log(id + ":" + secSum + "초");
		//장소 정보 업데이트
		$.each(calendarPlan.spotArr, function(idx, element) {  // 체크박스 선택
			if (element.id == id) {
				calendarPlan.spotArr[idx].timeSecSum = secSum;
			}
		});
		console.log(calendarPlan);
	});

}

// 장소 설정 초기화
function spotResetBtnClickHandler(thisElement) {
	$(".selected-spot-box").remove();
	calendarPlan.spotArr.length = 0;
	//체크박스 해제
	$(".spotcheck").prop("checked", false);
	//마커 삭제
	setMarkers(null);
	markers.length = 0;
	// 장소 설정 정보 삭제
	$(".time-sum").html("");
	$(".count-sum").html(0);
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
