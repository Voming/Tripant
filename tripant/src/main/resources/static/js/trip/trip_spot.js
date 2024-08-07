var clickspotnum = 0;
var clickspotfindnum = 0;
var spottype;
var spotboxCount;
var placeTypeS;
//검색명
var findArea;
var spotId = "";
var areacode = $('.schedule-wrapper').data('area-code');

//장소추가 버튼
function openSpot(){
	$('#tab02').removeClass('hide');
	$('#edit-tourlist').addClass('hide');
	$('#spot-basket').addClass('hide');
	$('#add-btn').removeClass('hide');
}

// jjoggan 담기 이벤트
function includeHandler(){
	var htmlVal = "";
	if(spotArr.length != 0){
		$.each(spotArr, function(idx, element) {
			console.log("idx");
			console.log(idx);
			//element.id;
			htmlVal += `
				<div class="include-spot flex wfull draggable"  draggable ="true" data-i="99" data-j="${idx}" data-id="${element.id}">
			 		<div class="spot-img "><img src="${element.img}" style="width:70px ; height:70px;"/></div>
			 		<div class="flex" style=" row-gap: 5px;">
			 			<div class="spot-title wfull"> ${element.title}</div>
			 			<div class="spot-type" >${element.placeCat}</div>
			 		</div>
			 		<img class="spot-trashcan" onclick="deleteSpotHandler(this);" src="${contextPath}images/icons/trashcan.png" style="width:20px;height: 20px;" data-id="${element.id}">
				</div>
			`; 
		});
	}
	$('#spot-basket .wrap-basket').html(htmlVal);
	dragAndDrop();//백틱을 html에 넣은 후 dragAndDrop을 실행해야 node인식이 가능
	$('#tab02').addClass('hide'); // 장소 검색 감추기
	$('#add-btn').addClass('hide'); //담기버튼
	$('#edit-tourlist').removeClass('hide');
	$('#spot-basket').removeClass('hide');
	setBounds();//지도 범위 재설정
	
	// jjoggan ***
	console.log("spotArr");
	console.log(spotArr);
	console.log(detailListEditMode);
}

//=================================================================
//더보기
function spotMoreBtnClickHandler(thisElement) {
	// 클릭횟수 증가
	clickspotnum += 1;

	$.ajax({
		beforeSend : csrfHandler,
		error : ajaxErrorHandler,
		url: contextPath + "trip/spot"
		, method: "post"
		, context: this
		, data: {
			areaCode: areacode,
			spotType: spottype,
			clickSpotNum: clickspotnum
		}
	}).done(function(wrap_spot) {
		$(spotId).replaceWith(wrap_spot);

		//더보기 계속 붙일건지
		spotboxCount = $(spotId).find(".spot-box").length;
		if (spotboxCount < 20 * (clickspotnum + 1)) { //20개 보다 적게 나온경우
			$(".spot_more_btn").remove();
		}

		// 미리 클릭해 둔 리스트 다시 활성화
		listCheckSpot();
	});
}

//탭 눌렀을때 기본 리스트 뿌리기
$(document).ready(function() {
	$('.spot-tab-nav a').click(function() {
		//검색했던게 있으면 지우기
		if ($("#find-spot").val().trim().length != 0) {
			$("#find-spot").val("");
		}

		$('.spot-tab-content > div').hide().filter(this.hash).fadeIn();
		$('.spot-tab-nav a').css("color", "var(--color_gray)");
		$('.spot-tab-nav li').css("background-color", "white");
		$('.spot-tab-nav a').removeClass('active');
		$(this).addClass('active');
		$(this).css("color", "white");
		$(this).parent().css("background-color", "var(--color_day9_blue)");

		//더보기 클릭 횟수 초기화
		clickspotnum = 0;

		areacode = $(".schedule-wrapper").data("area-code");
		//var 
		placeTypeS = $(this).text();


		if (placeTypeS == '관광지') {
			spottype = 1;
		} else if (placeTypeS == '문화시설') {
			spottype = 2;
		} else if (placeTypeS == '쇼핑') {
			spottype = 3;
		} else if (placeTypeS == '음식점') {
			spottype = 4;
		}else if (placeTypeS == '숙소') {
			spottype = 5;
		}else if (placeTypeS == '캠핑장') {
			spottype = 6;
		}
		spotId = "#spot-tab0" + spottype + " .wrap-spotList" //필요한 탭 content만 값 넣기

		$.ajax({
			beforeSend : csrfHandler,
			error : ajaxErrorHandler,	
			url: contextPath + "trip/spot"
			, method: "post"
			, context: this
			, data: {
				areaCode: areacode,
				spotType: spottype,
				clickSpotNum: clickspotnum
			}
		}).done(function(wrap_spot) {
			$(spotId).replaceWith(wrap_spot);

			//결과값 null 체크
			spotboxCount = $(spotId).find(".spot-box").length;

			if (spotboxCount == 0) { //결과 없음
				$(".spot_more_btn").remove();
				var htmlVal = '<p style="text-align: center;">결과가 없습니다.</p>';
				$(".resultSpotCheck").html(htmlVal);
			} else if (spotboxCount < 20) { //20개 보다 적게 나온경우
				$(".spot_more_btn").remove();
			}

			// jjoggan 미리 클릭해 둔 리스트 다시 활성화
			listCheckSpot();
		});

		return false;
	}).filter(':eq(0)').click();

	//검색
	$(".btn.find-spot").on("click", btnSpotFindClickHandler);
});

//검색
function btnSpotFindClickHandler() {
	//버튼 클릭 초기화
	clickspotfindnum = 0;
	//타입 전체 선택 해제
	$('.spot-tab-nav a').css("color", "var(--color_gray)");
	$('.spot-tab-nav li').css("background-color", "white");
	$('.spot-tab-nav a').removeClass('active');

	findArea = $("input[name=find-spot]").val().trim();
	if (findArea.length == 0) {
		alert("빈문자열만 입력할 수 없습니다. 검색할 장소명을 입력해주세요.");
		return;
	}

	$.ajax({
		beforeSend : csrfHandler,
		error : ajaxErrorHandler,
		url:contextPath + "trip/spot/find"
		, method: "post"
		, context: this
		, data: {
			findArea: findArea,
			areaCode: areacode,
			clickSpotFindNum: clickspotfindnum
		}
	}).done(function(wrap_spot) {
		$(spotId).replaceWith(wrap_spot);

		//결과값 null(검색 결과 더보기) 체크
		spotboxCount = $(spotId).find(".spot-box").length;
		var htmlVal;

		if (spotboxCount == 0) { //결과 없음
			htmlVal = '<p style="text-align: center;">결과가 없습니다.</p>';
		} else if (spotboxCount >= 20) { //더보기 필요
			htmlVal = `
				<button type="button" onclick="spotFindMoreBtnClickHandler(this);"
				class="spot_find_more_btn">더보기</button>`;
		}
		$(".resultSpotCheck").html(htmlVal);
		$(".spot_more_btn").remove(); //검색아닌 더보기 지우기

		// jjoggan TODO 미리 클릭해 둔 리스트 다시 활성화
		listCheckSpot();
	});
}

//검색 더보기
function spotFindMoreBtnClickHandler(thisElement) {
	clickspotfindnum += 1;

	$.ajax({
		beforeSend : csrfHandler,
		error : ajaxErrorHandler,
		url: contextPath + "spot/find"
		, method: "post"
		, context: this
		, data: {
			areaCode: areacode,
			findArea: findArea,
			clickSpotFindNum: clickspotfindnum
		}
	}).done(function(wrap_spot) {
		$(spotId).replaceWith(wrap_spot);

		//결과값 null(검색 결과 더보기) 체크
		spotboxCount = $(spotId).find(".spot-box").length;;

		if (spotboxCount >= 20 * (clickspotfindnum + 1)) {
			var htmlVal = `
				<button type="button" onclick="spotFindMoreBtnClickHandler(this);"
				class="spot_find_more_btn">더보기</button>`;
			$(".resultSpotCheck").html(htmlVal);
		} else { //20개 보다 적게 나온경우
			$(".spot_find_more_btn").remove();
		}

		$(".spot_more_btn").remove(); //검색아닌 더보기 지우기

		// jjoggan TODO 미리 클릭해 둔 리스트 다시 활성화
		listCheckSpot();
	});
}

// 미리 클릭해 둔 리스트 다시 활성화
function listCheckSpot() {
	$.each(spotArr, function(idx, element) {
		var checkId = "#" + element.id;
		$(checkId).attr("checked", true);
	});
}
