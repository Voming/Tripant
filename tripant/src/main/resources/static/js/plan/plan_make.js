// 일정만들기에서 전체적으로 사용할 클래스
class CalendarDate {
	constructor(date, smalldate, day) {
		this.date = date;
		this.smalldate = smalldate;
		this.day = day;
	}
	startTime;
	endTime;
	stay;
}
class CalendarPlan {
	dateArr;
	spotArr;
}

let calendarPlan = new CalendarPlan();
calendarPlan.dateArr = new Array(CalendarDate);

// 저장되기 전에만 방지 처리
var beforeSave = true;

//창닫기, 새로고침 시 확인 이벤트
$(window).bind("beforeunload", function(e) {
	if (beforeSave) {
		return "창을 닫으실래요?";
	}
});


var areacode;  //지역 코드
var placetype; //type(1:관광지, 2:문화시설, 3:쇼핑, 4:음식점, 5:숙박, 6:캠핑장)

//장소 더보기 클릭
var clicknum = 0;
function placeMoreBtnClickHandler(thisElement) {
	clicknum += 1;

	$.ajax({
		url: contextPath + "plan/more"
		, method: "post"
		, context: this
		, data: {
			areaCode: areacode,
			placeType: placetype,
			clickNum : clicknum
		}
		, error: ajaxErrorHandler
	}).done(function(wrap_place) {
		$(".wrap-placeList").replaceWith(wrap_place);
	});
}

$(loadedHandler);

function loadedHandler() {
	var areacode = $(".plan-areacode").attr("value");

	//메인 화면 돌아가기
	$(".logo").on("click", function() {
		location.href = "/";
	});

	//좌측 탭
	$('.tab-nav a').click(function() {
		$('.tab-content > div').hide().filter(this.hash).fadeIn();
		$('.tab-nav a').css("color", "black");
		$('.tab-nav a').removeClass('active');
		$(this).addClass('active');

		var cls_name = $(this).attr("class");
		cls_name = cls_name.replace(' active', '');
		if (cls_name == 'nav-1') {
			$(".main-wrapper .tab-content").css("width", "25%");
		} else {
			$(".main-wrapper .tab-content").css("width", "50%");
		}


		$(this).css("color", "#4BC9E5");
		return false;
	}).filter(':eq(0)').click();
}