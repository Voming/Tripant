// 일정만들기에서 전체적으로 사용할 클래스
class CalendarDate {
	constructor(date, smalldate, day) {
		this.date = date; //2024.06.06
		this.smalldate = smalldate; //06/06
		this.day = day; //목요일
	}
	startTime; //10:00
	endTime;   //22:00
	stay;      //숙소명
}

class spot {
	constructor(id, title, mapx, mapy) {
		this.id = id;
		this.title = title;
		this.mapx = mapx;
		this.mapy = mapy;
	}
	spotTime;
}

class CalendarPlan {
	dateArr;
	spotArr;
	timeRange=0;
}

let calendarPlan = new CalendarPlan();
calendarPlan.dateArr = new Array(CalendarDate);
calendarPlan.spotArr = new Array(spot);

// 저장되기 전에만 방지 처리
var beforeSave = true;

//창닫기, 새로고침 시 확인 이벤트
$(window).bind("beforeunload", function(e) {
	if (beforeSave) {
		return "창을 닫으실래요?";
	}
});

//지역 코드
var areacode;

$(loadedHandler);
function loadedHandler() {
	//메인 화면 돌아가기
	$(".logo").on("click", function() {
		location.href = "/";
	});

	//달력 다시 열기
	$(".plan-priod").on("click", function() {
		beforeSave = false;
		if (confirm("기간을 다시 설정하면 작성한 내용이 없어집니다. 괜찮습니까?") == true) {
			location.reload(true);
			$(".modal").addClass("show");
		} else {
			event.preventDefault();
		}
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
			$(".main-wrapper .tab-content").css("width", "40%");
		}

		$(this).css("color", "#4BC9E5");
		return false;
	}).filter(':eq(0)').click();
}

