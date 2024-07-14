// 일정만들기에서 전체적으로 사용할 클래스
class PlanDate {
	constructor(date, smalldate, day) {
		this.date = date; //2024.06.06
		this.smalldate = smalldate; //06/06
		this.day = day; //목요일
	}
	startTime; //10:00
	endTime;   //22:00
	dateTimeRange; // 하루 사용 가능한 초
	stay = new Stay();  //숙소
}

class Spot {
	constructor(id, title, mapx, mapy, type) {
		this.id = id;
		this.title = title;
		this.mapx = mapx;
		this.mapy = mapy;
		this.type = type;
	}
	spotTime;
}

class Stay {
	constructor(id, title, mapx, mapy, img, type) {
		this.id = id;
		this.title = title;
		this.mapx = mapx;
		this.mapy = mapy;
		this.img = img;
		this.type = type;
	}
}

class CalendarPlan {
	dateArr;
	spotArr;
	timeRange = 0;
}

let calendarPlan = new CalendarPlan();
calendarPlan.dateArr = new Array(PlanDate);
calendarPlan.spotArr = new Array(Spot);

// 저장되기 전에만 방지 처리
var beforeSave = true;

// 시간테이블 최소값 체크
var checkLess = false;
// 시간테이블 부족 경고 str
let alertTtableStr = "하루에 2시간 이상으로 설정해주세요.";

// 장소 부족 경고 str
let alertSpotStr = "하루에 한 개 이상의 장소에 방문해야해요. 장소를 더 추가해주세요!";
// 장소 이용 시간 합 초과 경고 str
let alertSpotSumStr = "장소별 방문시간의 합이 총 이용가능 시간 보다 많을 수 없습니다. 다시 설정해주세요.";

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
		location.href = contextPath;
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

	var cls_name;
	//좌측 탭
	$('.tab-nav a').click(function() {
		var clickElement = this;
		function moveStep() {  // 클릭된 클래스에 active 설정
			$('.tab-nav a').removeClass('active');
			$(clickElement).addClass('active');
			$('.tab-content > div').hide().filter(clickElement.hash).fadeIn();
		}
		// click		
		var click_cls_name = $(clickElement).attr("class");
		// current
		var currentActive = $('.tab-nav .active').attr("class");
		if (click_cls_name == 'nav-1' && !currentActive) {  //1번으로 이동
			moveStep();
			$(".main-wrapper .tab-content").css("width", "25%");
		} else { //2 or 3으로 이동
			cls_name = currentActive.replace(' active', '');
			if (cls_name == 'nav-1') {  //현재 1=========
				saveTimeInfo(); //시간 정보 저장

				checkLess = false;   //시간 테이블 범위 체크
				timePerDateCheck();
				if (checkLess) {
					alert(alertTtableStr);
				} else {
					if (click_cls_name == 'nav-3') {  // 1 -> 3
						if (markersSpot.length < calendarPlan.dateArr.length) {
							alert(alertSpotStr);
						} else {
							moveStep();
							$(".main-wrapper .tab-content").css("width", "40%");
						}
					} else { // 1 -> 2
						moveStep();
						$(".main-wrapper .tab-content").css("width", "40%");
					}
				}
			} else if (cls_name == 'nav-2') { //현재 2=========
				saveSpotSecSum();
				if (click_cls_name == 'nav-3') { // 2 -> 3
					if (markersSpot.length < calendarPlan.dateArr.length) {
						alert(alertSpotStr);
					} else if (calendarPlan.timeRange < secSum) {
						alert(alertSpotSumStr);
					}
					else {
						moveStep();
						$(".main-wrapper .tab-content").css("width", "40%");
					}
				} else { // 2 -> 1
					moveStep();
					$(".main-wrapper .tab-content").css("width", "25%");
				}
			} else if (cls_name == 'nav-3') {  //현재 3=========
				if (click_cls_name == 'nav-2') { // 3 -> 2
					moveStep();
					$(".main-wrapper .tab-content").css("width", "40%");
				} else { // 3 -> 1 
					moveStep();
					$(".main-wrapper .tab-content").css("width", "25%");
				}
			}

		}
		return false;
	}).filter(':eq(0)').click();

	//다음 버튼
	$(".next.btn").on("click", function() {
		$(".tab-nav > li").each(function() {
			cls_name = $(this).find('a').attr("class");
			if (cls_name === 'nav-1 active') { //현재 1=========
				saveTimeInfo(); //시간 정보 저장

				checkLess = false;  //시간 테이블 범위 체크
				timePerDateCheck();
				if (checkLess) {
					alert(alertTtableStr);
				} else {
					//2번으로 이동
					$('.tab-nav a').removeClass('active');
					$('.nav-2').addClass('active');

					$('.tab-content > #tab01').hide();
					$('.tab-content > #tab02').show();
					$(".main-wrapper .tab-content").css("width", "40%");
				};
			} else if (cls_name === 'nav-2 active') { //현재 2=========
				saveSpotSecSum();
				// 장소가 최소 날짜 수 만큼
				if (markersSpot.length < calendarPlan.dateArr.length) {
					alert(alertSpotStr);
				} else if (calendarPlan.timeRange < secSum) {
					alert(alertSpotSumStr);
				} else {
					//3번으로 이동
					$('.tab-nav a').removeClass('active');
					$('.nav-3').addClass('active');

					$('.tab-content > #tab02').hide();
					$('.tab-content > #tab03').show();
					$(".main-wrapper .tab-content").css("width", "40%");
				}
				return false;
			} else if (cls_name === 'nav-3 active') { //현재 3=========
			console.log(calendarPlan);
				if (markersStay.length < calendarPlan.dateArr.length - 1) {
					alert("하루에 한 개 이상의 숙소에 방문해야해요. 숙소를 더 추가해주세요!");
				} else {
					//클릭막기
					//$(".main-wrapper").css("pointer-events", "none");
					//새로고침 알림 막기
					beforeSave = false;

					Swal.fire({
						title: "<h2>일정을 생성하시겠습니까?</h2>",
						text: "나만의 일정을 만들어보세요",
						icon: "question",
						showCancelButton: true,
						cancelButtonText: "취소",
						confirmButtonColor: "#000",
						cancelButtonColor: "#6E7881",
						confirmButtonText: "확인"
					}).then((result) => {
						if (result.isConfirmed) {
							doPlanning();
						}
					});
				}
			}
		});
	});
}

function doPlanning(){
	Swal.fire({
		icon: "success",
		title: "<h3>일정 생성중입니다! \n 생성이 완료되면 나의 일정 페이지로 이동합니다.</h3>",
		showConfirmButton: false,
		allowOutsideClick: false
	});

	// 일정 만들기 알고리즘 돌리기
	const jsonString = JSON.stringify(calendarPlan);
	$.ajax({
		beforeSend: csrfHandler,
		error: ajaxErrorHandler,
		url: contextPath + "plan/planning",
		method: "post",
		contentType: "application/json",
		data: jsonString,
		//async: false,
		traditional: true, //필수
		success: function(data) {
			window.location.href = contextPath + data;
		}
	});
}

//장소별 시간 저장
function saveSpotSecSum() {
	$(".selected-spot-box").each(function(index) {
		var id = $(this).find(".box-id").attr("value");
		var hours = $(this).find(".spot-hours").val();
		var mins = $(this).find(".spot-mins").val();

		var secSum = (hours * 60 * 60) + (mins * 60); //초로 변환하기

		$.each(calendarPlan.spotArr, function(idx, element) {
			if (element.id == id) {
				element.spotTime = secSum;
				return false;
			}
		});
	});
}

//시간 테이블 범위 체크
function timePerDateCheck() {
	$.each(calendarPlan.dateArr, function(idx, element) {
		if (Number(element.dateTimeRange) < 7200) {
			checkLess = true;
			return false;
		}
	});
}