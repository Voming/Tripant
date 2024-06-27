//초기 숙소 들어갈 부분 세팅(plan_calendar.js에서 호출)
function displayStayBox() {
	console.log(calendarPlan);

	var range = calendarPlan.dateArr.length - 1;
	// 장소 설정 초기화
	$(".count-stay").html(0);
	$(".time-stay").html("0일 / " + range + "일");


	for (var i = 0; i < calendarPlan.dateArr.length - 1; i++) {
		var start = calendarPlan.dateArr[i].smalldate;
		var end = calendarPlan.dateArr[i + 1].smalldate;
		//화면 리스트 추가
		var htmlVal = "";
		htmlVal += `
			<div class="selected-stay-box">
				<span class="box-id" value="" style ="display:none"></span> 
				<div class="wrap-box flex">
					<div class="selected-stay-number" style="background-color:var(--color_gray);">
						<p>${i + 1}</p>
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

		// 모달 날짜별 + 탭
		var modalVal = "";
		modalVal = `
		<div class="wrap-stay-tab">
			<p class="stay-tab-day">${start}</p>
			<img class="stay-tab-img" src="/images/plan/stay_plus.png" onclick="stayTabBtnClickHandler(this);">
			<p class="stay-tab-name">호텔 선택</p>
		</div>
		`;
		$(".modal-stay-list").append(modalVal);
	}
}

// 외부영역 클릭 시 모달 닫기
$(document).mouseup(function(e) {
	var LayerModal = $(".stay-modal");
	if (LayerModal.has(e.target).length === 0) {
		LayerModal.removeClass("show");
	}
});

//===========================================실제 숙소 체크박스==========================================
class StayBefor {
	constructor(id, title, mapx, mapy, img) {
		this.id = id;
		this.title = title;
		this.mapx = mapx;
		this.mapy = mapy;
		this.img = img;
	}
}
var stayBefor; // 저장 완료 전까지 담고있을 객체


// 숙소 + 탭 클릭
function stayTabBtnClickHandler(thisElement) {
	addMarkerStay(new kakao.maps.LatLng(stayBefor.mapy, stayBefor.mapx), stayBefor.title, stayBefor.id, markersStay.length); // 마커 추가
	setMarkersStay(map); // 마커 지도에 표시하기

	//화면 변경
	$(thisElement).attr("src", stayBefor.img);
	$(thisElement).next().text(stayBefor.title);
}

function stayModalDoneBtnClickHandler() {
	stayBefor = null; //비워놓기

	//모달 닫기
	$(".stay-modal").removeClass("show");
	//화면에 있던 정보 저장(stay-tab 개수 만큼 반복문)

	// 장소 설정 정보 부분 업데이트
	$(".count-stay").html(markersStay.length);
}


// 숙소 삭제
function stayDeleteBtnClickHandler(thisElement) {
	console.log("stayDeleteBtnClickHandler");
}


// 지도에 표시된 마커 객체를 가지고 있을 배열입니다
var markersStay = [];  //아이디 , 마커 담김 
// 숙소 체크박스 클릭
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


	} else { //=====================================체크박스 선택=========================================
		$(".stay-modal").addClass("show");
		stayBefor = new StayBefor(id, title, latx, lngy, img);

		//모달 숙소 이름 변경
		$(".modal-stay-name").html(title);
		//모달 리스트 추가
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
	}
}

// 숙소 설정 초기화
function stayResetBtnClickHandler() {
	$(".selected-stay-box").remove();
	//체크박스 해제
	$(".staycheck").prop("checked", false);
	//마커 삭제
	setMarkersStay(null);
	markersStay.length = 0;

	displayStayBox();
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


