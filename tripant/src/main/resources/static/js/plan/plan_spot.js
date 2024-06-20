var clickspotnum = 0;
var clickspotfindnum = 0;
var spottype;
var findArea;
//더보기
function spotMoreBtnClickHandler(thisElement) {
	clickspotnum += 1;

	$.ajax({
		url: contextPath + "plan/spot/more"
		, method: "post"
		, context: this
		, data: {
			areaCode: areacode,
			spotType: spottype,
			clickSpotNum: clickspotnum
		}
		, error: ajaxErrorHandler
	}).done(function(wrap_spot) {
		$(".wrap-spotList").replaceWith(wrap_spot);
	});
}
//검색 더보기
function spotFindMoreBtnClickHandler(thisElement) {
	clickspotfindnum += 1;

	$.ajax({
		url: contextPath + "plan/spot/find/more"
		, method: "post"
		, context: this
		, data: {
			areaCode: areacode,
			findArea: findArea,
			clickSpotFindNum: clickspotfindnum
		}
		, error: ajaxErrorHandler
	}).done(function(wrap_spot) {
		$(".wrap-spotList").replaceWith(wrap_spot);
		
		//결과값 null 체크
		var boxCount = $(".spot-box").length;
		console.log(boxCount);
		if (boxCount == 0) {
			var htmlVal = '<p style="text-align: center;">결과가 없습니다.</p>';
			$(".resultCheck").html(htmlVal);
		} else if (boxCount >= 40*clickspotfindnum) {
			var htmlVal = `
				<button type="button" onclick="spotFindMoreBtnClickHandler(this);"
				class="spot_find_more_btn">더보기</button>`;
			$(".resultCheck").html(htmlVal);
		} 
		$(".spot_more_btn").css('display', 'none');
	});
}


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
		clicknum = 0;

		areacode = $(".plan-areacode").attr("value");
		var placeTypeS = $(this).text();

		if (placeTypeS == '관광지') {
			spottype = 1;
		} else if (placeTypeS == '문화시설') {
			spottype = 2;
		} else if (placeTypeS == '쇼핑') {
			spottype = 3;
		} else if (placeTypeS == '음식점') {
			spottype = 4;
		}

		$.ajax({
			url: contextPath + "plan/spot"
			, method: "post"
			, context: this
			, data: {
				areaCode: areacode,
				spotType: spottype
			}
			, error: ajaxErrorHandler
		}).done(function(wrap_spot) {
			$(".wrap-spotList").replaceWith(wrap_spot);

			//결과값 null 체크
			var spotname = $(".spot-name").text();
			if (spotname.length == 0) {
				$(".spot_more_btn").css('display', 'none');

				var htmlVal = '<p style="text-align: center;">결과가 없습니다.</p>';
				$(".resultCheck").html(htmlVal);
			}
		});

		return false;
	}).filter(':eq(0)').click();

	//검색
	$(".btn.find-spot").on("click", btnFindClickHandler);
});

function btnFindClickHandler() {
	//버튼 클릭 초기화
	clickspotfindnum = 0;
	findArea = $("input[name=find-spot]").val().trim();


	if (findArea.length == 0) {
		alert("빈문자열만 입력할 수 없습니다. 검색할 장소명을 입력해주세요.");
		return;
	}

	//타입 전체 선택 해제
	$('.spot-tab-nav a').css("color", "var(--color_gray)");
	$('.spot-tab-nav li').css("background-color", "white");
	$('.spot-tab-nav a').removeClass('active');

	$.ajax({
		url: "/plan/spot/find"
		, method: "post"
		, context: this
		, data: {
			findArea: findArea,
			areaCode: areacode,
		}
		, error: ajaxErrorHandler
	}).done(function(wrap_spot) {
		$(".wrap-spotList").replaceWith(wrap_spot);
		
		//결과값 null 체크
		var boxCount = $(".spot-box").length;
		console.log(boxCount);
		if (boxCount == 0) {
			var htmlVal = '<p style="text-align: center;">결과가 없습니다.</p>';
			$(".resultCheck").html(htmlVal);
		} else if (boxCount >= 80) {
			var htmlVal = `
				<button type="button" onclick="spotFindMoreBtnClickHandler(this);"
				class="spot_find_more_btn">더보기</button>`;
			$(".resultCheck").html(htmlVal);
		} 
		$(".spot_more_btn").css('display', 'none');

	});
}

