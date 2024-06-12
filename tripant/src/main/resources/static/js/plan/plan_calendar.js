$(document).ready(function() {
	// TODO 바로 열리게 하는 방법 있는지 찾아보기
	$('#daterange').daterangepicker({
		opens: "center",
		alwaysOpen: true,
		"locale": {
			"format": "YYYY-MM-DD",
			"separator": " ~ ",
			"applyLabel": "확인",
			"cancelLabel": "취소",
			"fromLabel": "From",
			"toLabel": "To",
			"customRangeLabel": "Custom",
			"weekLabel": "W",
			"daysOfWeek": ["일", "월", "화", "수", "목", "금", "토"],
			"monthNames": ["1월", "2월", "3월", "4월", "5월", "6월", "7월", "8월", "9월", "10월", "11월", "12월"],
		},
		"startDate": new Date(),
		"endDate": new Date(),
		"drops": "auto"
	}, function(start, end, label) {
		console.log('New date range selected: ' + start.format('YYYY-MM-DD') + ' to ' + end.format('YYYY-MM-DD') + ' (predefined range: ' + label + ')');
	});

	$("#daterange").focus();
});

$('#daterange').on('apply.daterangepicker', function(ev, picker) {
	//console.log(picker.startDate.format('YYYY-MM-DD'));
	//console.log(picker.endDate.format('YYYY-MM-DD'));

	let diff = Math.abs(picker.endDate - picker.startDate);
	diff = Math.ceil(diff / (1000 * 60 * 60 * 24));
	//console.log(diff);

	if (diff > 10) {
		alert("기간이 너무 큽니다. 기간을 다시 입력해주세요.");
	} else {
		var start = picker.startDate.format('YYYY/MM/DD');
		var end = picker.endDate.format('YYYY/MM/DD');
		$(".modal").removeClass("show");
		const period = start + " ~ " + end;
		console.log(period);
		$(".plan-priod").html(period);

		var htmlVal = "입력하신 여행 기간이 여행지 도착날짜와 여행지 출발 날짜가 맞는지 확인해주세요. 기본 설정 시간은 오전 10시~오후 10시 총 12시간 입니다.";
		$(".plan-timeEx").html(htmlVal);

		// 일정 시작, 끝 시간 테이블 만들기
		var days = [];
		for (let i = 0; i < diff; i++) {
			var dateS = new Date(start);
			var oneday = new Date(dateS.setDate(dateS.getDate() + i));

			var month = ('0' + (oneday.getMonth() + 1)).slice(-2);
			var day = ('0' + oneday.getDate()).slice(-2);

			var onedayFormat = month + '/' + day;

			days.push(onedayFormat);
			console.log(onedayFormat);
		}

		displayDayTable(days);
	}
});

function displayDayTable(days) {
	console.log(days);
}

