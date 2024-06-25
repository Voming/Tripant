 /* 편집 취소 저장 */
 //편집
function editHandler(){
	//전체일정 선택 후 편집창 띄우기
	navHandler();
	//취소 버튼 활성화
	$(this).next().show(); 
	//n일차 btn 숨김
	$(this).parent().prevAll().find('.dayn').not(':first').hide(); 
	//편집 숨김
	$(this).hide();
	//저장btn 활성화 css변경
	$(this).siblings('.save').attr('disabled',false);
	//드래그 활성화
	$(this).parents().find('.spot-block').attr('draggable',true);
	$(this).parents().find('.draggable').css('cursor','move');
	//일정 목록보여주기
	$(this).parents().find('.change-list').addClass('edit-tourlist');
	$(this).parents().find('.change-list').removeClass('tourlist');
}
//취소
function cancelHandler(){
	//전체일정 클릭
	navHandler();
	//취소 숨김
	$(this).hide(); 
	//편집 보여줌
	$(this).prev().show(); 
	//저장 비활성화
	$(this).siblings('.save').attr('disabled',true); 
	//n일차 btn 보여줌
	$(this).parent().prevAll().find('.dayn').show(); 
	
	//드래그 비활성화
	$(this).parents().find('.spot-block').attr('draggable',false);
	$(this).parents().find('.draggable').css('cursor','Default');
	//일정 목록보여주기
	$(this).parents().find('.change-list').removeClass('edit-tourlist');
	$(this).parents().find('.change-list').addClass('tourlist');
}
//저장
function saveHandler(){
	//전체일정 클릭
	navHandler();
	$(this).siblings('.cancel').hide(); //취소 숨김
	$(this).siblings('.edit').show(); //편집 보여줌
	$(this).attr('disabled',true); //저장 비활성화
	$(this).parent().prevAll().find('.dayn').show(); //n일차 btn 보여줌

	//드래그 비활성화
	$(this).parents().find('.spot-block').attr('draggable',false);
	$(this).parents().find('.draggable').css('cursor','Default');
	//일정 목록보여주기
	$(this).parents().find('.change-list').removeClass('edit-tourlist');
	$(this).parents().find('.change-list').addClass('tourlist');
	//DB이동 ajax
}
//좌측 탭
function navHandler(){
	//좌측 탭
	$('.dayn a').click(function() {
		$('.dayn a').css("color", "black");
		//클릭한 버튼만 css 변경
		$(this).css("color", "#4BC9E5");
		
		//전체일정일 때, 일일 버튼 클릭할 때에 따른 일정 display
		hash = $(this).prop('hash');
		if(hash == '#tab0'){
			$('.column.flex').removeClass('hide');
		}
		else{
			$('.column.flex').addClass('hide');
			$(hash).removeClass('hide');
		}

		return false;
	}).filter(':eq(0)').click();
}