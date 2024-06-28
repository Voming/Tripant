

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
	var key = $(thisElement).parents('.spot-block.draggable').data('sessionkey');
	
	var timeVal = hours*3600 + mins*60;
	var temp = JSON.parse(editStorage.getItem(key));
	
	temp.stayTime = timeVal;
	
	// 수정된 객체를 다시 JSON 문자열로 변환하여 sessionStorage에 저장
	editStorage.setItem(key, JSON.stringify(temp));
	
	displayEditInfo();
	circleColorHandler();
}

//spot -index
function spotIndxHanlder(thisElement){
	$('.spot-block').each(function (){
		
		
		
	});
	
}
