// 시간 설정 모달 열림
function timeRangeBtnClickHandler(thisElement) {
	$(thisElement).children(".timerange").addClass('hide');
	$(thisElement).parents('.spot-block').children(".timerange-modal").removeClass('hide');
}

// 시간 설정 완료
function timeDoneBtnClickHandler(thisElement) {
	$(thisElement).parents('.spot-block').children(".timerange").removeClass('hide');
	$(thisElement).parents(".timerange-modal").addClass('hide');

	var hours = $(thisElement).parent().children('.spot-hours').val();
	var mins = $(thisElement).parent().children('.spot-mins').val();
	
	var timeVal = hours + "시간 " + mins + "분";
	console.log(timeVal);
	
	$(thisElement).parents('.spot-staytime').children('.timerange').text(timeVal);
	//timeInfoUpdate(); // 총 시간 업데이트
}

//초단위로 변경
function changeInSecHandler(){
	
	var seconds;
	
	return seconds;
}
