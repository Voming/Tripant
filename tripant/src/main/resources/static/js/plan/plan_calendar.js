$(function() {
	// TODO 바로 열리게 하는 방법 있는지 찾아보기
	$('#daterange').daterangepicker({
		opens: "center",
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
});

$('#daterange').on('apply.daterangepicker', function(ev, picker) {
	//console.log(picker.startDate.format('YYYY-MM-DD'));
	//console.log(picker.endDate.format('YYYY-MM-DD'));

	let diff = Math.abs(picker.endDate- picker.startDate);
	diff = Math.ceil(diff / (1000 * 60 * 60 * 24));
	//console.log(diff);

	if (diff > 10) {
		alert("기간이 너무 큽니다. 기간을 다시 입력해주세요.");
	} else {
		$(".modal").removeClass("show");
	}
});
