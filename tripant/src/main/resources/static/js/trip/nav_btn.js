 /*편집 취소 저장 */
 //편집
function editHandler(){
	
	//편집페이지 정보 로드	
	displayEditMode(); 
	// ejkim

	//일차별 동그라미 색 변경
	circleColorHandler();
	//드래그 앤 드랍
	dragAndDrop();
	//편집 취소
	$(".cancel").click(cancelHandler);
	//편집 저장
	$(".save").click(saveHandler);
	
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
	//일정 목록보여주기
	$(this).parents().find('.tourlist').addClass('hide');
	$(this).parents().find('.edit-tourlist').removeClass('hide');
	$(this).parents().find('.spot-basket').removeClass('hide');
	setBounds();//지도 범위 재설정
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
	//담기 숨김
	$('#add-btn').addClass('hide');
	
	//일정 목록보여주기
	$(this).parents().find('.tourlist').removeClass('hide');
	$(this).parents().find('.edit-tourlist').addClass('hide');
	$(this).parents().find('.spot-basket').addClass('hide');
	//spot-check
	if($('#tab02').hasClass('hide')===false){
		$('#tab02').addClass('hide');
	}
	setBounds();//지도 범위 재설정
}
//저장
function saveHandler(){
	
	var planTitle=$('.plan-title').text();
	Swal.fire({
	  title: "<h2>"+planTitle+"</h2>",
	  text: "저장하시겠습니까?",
	  showCancelButton: true,
	  confirmButtonColor: "#000000",
	  cancelButtonColor: "#d33",
	  confirmButtonText: "저장",
	  cancelButtonText: "취소",
   	  confirmButtonTextFont:"Binggrae"
	}).then((result) => {
	  if (result.isConfirmed) {
			//DB이동
			saveChanges();
	  }//if
	});
	
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

// 수정된 일정 DB에 저장하기
function saveChanges(){
		//안 쓰는 데이터 빼내기
		//삭제할 key : durationMin, endTime, startTime
		for(var i = 0 ; i < detailListEditMode.length;i++){
			editmode = detailListEditMode[i];
			
			for(var j = 0 ;j < editmode.dayDetailInfoEntity.length; j++){
				item = editmode.dayDetailInfoEntity[j];
				
				delete item.durationMin;
				delete item.endTime;
				delete item.startTime;
				delete item.placeCat;
				//변경된 방문순서 key에 넣어주기
				item.travelOrder = j + 1; 
			}
		}
		saveData = JSON.stringify(detailListEditMode);
		//jjoggan ***
		$.ajax({
			beforeSend : csrfHandler,
			error : ajaxErrorHandler,
			url: contextPath+"trip/save/changes",
			method:"post",
			data:{saveData : saveData ,planId:planId},
			success : function(result) {
				Swal.fire({
				  icon: "success",
				  title: "저장되었습니다.",
				  showConfirmButton: false,
				  timer: 1500
				}).then(() => {
					location.reload();
				});
	        }
	});

}