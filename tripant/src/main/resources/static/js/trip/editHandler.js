

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
/*	$('.spot-block').each(function (){
		
		
		
	});*/
	
}

//memo 이미지에서 마우스가 벗어났을 때
function memoNoHandler(){
	$(this).siblings('.memo').addClass('hide');
}

//memo 내용을 추가할 모달창 생성 memo관련 함수 async필수
async function memoClickHandler(el){
    // 'memo' 요소의 텍스트를 가져옵니다.
    memoText = $(el).siblings('.memo').text();
    
    // Swal.fire 다이얼로그를 표시하고 사용자의 입력을 기다린다
    const { value: memo } = await Swal.fire({
        input: "textarea",
        inputLabel: "메모작성",
        inputValue: memoText,
        inputPlaceholder: "여행에 필요한 정보를 이곳에 작성해보세요. 최대 900자",
        inputAttributes: {
            maxlength: "900"
        },
        showCancelButton: true,
        confirmButtonColor: "#000000", 
        confirmButtonText: "확인"
    });
    if (memo && memo.trim().length > 0) {
        Swal.fire("<h1>저장완료</h1>");
    }
}
