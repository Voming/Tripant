var clickstaynum = 0;
var clickstayfindnum = 0;
var staytype;
var stayboxCount;
//검색명
var findArea;
var stayId = "";

// 더보기
function stayMoreBtnClickHandler(thisElement) {
	clickstaynum += 1;

	$.ajax({
		url: contextPath + "plan/stay"
		, method: "post"
		, context: this
		, data: {
			areaCode: areacode,
			stayType: staytype,
			clickStayNum: clickstaynum
		}
		, error: ajaxErrorHandler
	}).done(function(wrap_stay) {
		$(stayId).replaceWith(wrap_stay);

		//더보기 계속 붙일건지
		stayboxCount = $(".stay-box").length;
		if (stayboxCount.length - (20 * clickstaynum) < 20) { //20개 보다 적게 나온경우
			$(".stay_more_btn").remove();
		}

		displayStayCheckList();
	});
}

$(document).ready(function() {
	$('.stay-tab-nav a').click(function() {
		//검색했던게 있으면 지우기
		if ($("#find-stay").val().trim().length != 0) {
			$("#find-stay").val("");
		}

		$('.stay-tab-content > div').hide().filter(this.hash).fadeIn();
		$('.stay-tab-nav a').css("color", "var(--color_gray)");
		$('.stay-tab-nav li').css("background-color", "white");
		$('.stay-tab-nav a').removeClass('active');
		$(this).addClass('active');
		$(this).css("color", "white");
		$(this).parent().css("background-color", "var(--color_day9_blue)");

		//더보기 클릭 횟수 초기화
		clickstaynum = 0;

		areacode = $(".plan-areacode").attr("value");
		var placeTypeS = $(this).text();

		var tabtype;
		if (placeTypeS == '숙박') {
			staytype = 5;
			tabtype = 1;
		} else if (placeTypeS == '캠핑장') {
			staytype = 6;
			tabtype = 2;
		}
		stayId = "#stay-tab0" + tabtype + " .wrap-stayList" //필요한 탭 content만 값 넣기

		$.ajax({
			url: contextPath + "plan/stay"
			, method: "post"
			, context: this
			, data: {
				areaCode: areacode,
				stayType: staytype,
				clickStayNum: clickstaynum
			}
			, error: ajaxErrorHandler
		}).done(function(wrap_stay) {
			$(stayId).replaceWith(wrap_stay);

			//결과값 null 체크
			var stayboxCount = $(".stay-box").length;
			if (stayboxCount.length == 0) { //결과 없음
				$(".stay_more_btn").css('display', 'none');
				var htmlVal = '<p style="text-align: center;">결과가 없습니다.</p>';
				$(".resultStayCheck").html(htmlVal);
			} else if (stayboxCount.length < 20) { //20개 보다 적게 나온경우
				$(".stay_more_btn").css('display', 'none');
			}

			// 미리 클릭해 둔 리스트 다시 활성화
			displayStayCheckList();
		});

		return false;
	}).filter(':eq(0)').click();

	//검색
	$(".btn.find-stay").on("click", btnStayFindClickHandler);
});

//검색
function btnStayFindClickHandler() {
	//버튼 클릭 초기화
	clickstayfindnum = 0;
	//타입 전체 선택 해제
	$('.stay-tab-nav a').css("color", "var(--color_gray)");
	$('.stay-tab-nav li').css("background-color", "white");
	$('.stay-tab-nav a').removeClass('active');

	findArea = $("input[name=find-stay]").val().trim();
	if (findArea.length == 0) {
		alert("빈문자열만 입력할 수 없습니다. 검색할 장소명을 입력해주세요.");
		return;
	}

	$.ajax({
		url: "/plan/stay/find"
		, method: "post"
		, context: this
		, data: {
			findArea: findArea,
			areaCode: areacode,
			clickStayFindNum: clickstayfindnum
		}
		, error: ajaxErrorHandler
	}).done(function(wrap_stay) {
		$("#stay-tab01 .wrap-stayList").replaceWith(wrap_stay);

		//결과값 null(검색 결과 더보기) 체크
		var stayboxCount = $(".stay-box").length;
		console.log(stayboxCount);
		var htmlVal;
		if (stayboxCount == 0) { //결과 없음
			htmlVal = '<p style="text-align: center;">결과가 없습니다.</p>';
		} else if (stayboxCount >= 20) { //더보기 필요
			htmlVal = `
				<button type="button" onclick="stayFindMoreBtnClickHandler(this);"
				class="stay_find_more_btn">더보기</button>`;
		}
		$(".resultStayCheck").html(htmlVal);
		$(".stay_more_btn").css('display', 'none'); //검색아닌 더보기 지우기

		displayStayCheckList();
	});
}

//검색 더보기
function stayFindMoreBtnClickHandler(thisElement) {
	clickstayfindnum += 1;

	$.ajax({
		url: contextPath + "plan/stay/find"
		, method: "post"
		, context: this
		, data: {
			areaCode: areacode,
			findArea: findArea,
			clickStayFindNum: clickstayfindnum
		}
		, error: ajaxErrorHandler
	}).done(function(wrap_stay) {
		$("#stay-tab01 .wrap-stayList").replaceWith(wrap_stay);

		//결과값 null(검색 결과 더보기) 체크
		var stayboxCount = $(".stay-box").length;

		if (stayboxCount >= 30 * clickstayfindnum) {
			var htmlVal = `
				<button type="button" onclick="stayFindMoreBtnClickHandler(this);"
				class="stay_find_more_btn">더보기</button>`;
			$(".resultStayCheck").html(htmlVal);
		} else { //20개 보다 적게 나온경우
			$(".stay_find_more_btn").css('display', 'none');
		}

		$(".stay_more_btn").css('display', 'none'); //검색아닌 더보기 지우기

		displayStayCheckList();
	});
}
