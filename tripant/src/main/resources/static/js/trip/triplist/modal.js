
//공유 창의 확인 버튼 - 화면 리로드
function closeModalHandler(){
		$(".wrap-modal").css("display","none");
		$('.mini-modal').addClass('hide');
		
		//모든 검색란 초기화
		document.querySelectorAll("input[type=text]").forEach(input => input.value = '');
}

//외부영역 클릭 시 모달 닫기
$(document).mouseup(function(e) {
	var LayerModal = $(".wrap-modal");

	// 클릭된 대상이 LayerModal의 자식 요소가 아닐경우
	if(LayerModal.has(e.target).length === 0 ){
		LayerModal.css("display","none");
		$('.mini-modal').addClass('hide');
		document.querySelectorAll("input[type=text]").forEach(input => input.value = '');
	}
	
});

//케밥창 하나만 열리게 설정
function miniModalBtnHandler(){
	var mbtn = $(this).children('.mini-modal');
	$('.mini-modal').addClass('hide');
	$(this).children('.mini-modal').removeClass("hide");
}


//공유 중인 유저 리스트 출력
function shareModalHandler(){
	//미니모달 닫기
	$(this).parents().siblings(".wrap-modal").css("display","block");
	
	var planId =$(this).parents(".trip-list.wfull").data('plan-id');
	$.ajax({
		url: "/trip/share/nick",
		method:"post",
		context:this,
    	data: {planId:planId}
	}).done( function(shareList) {
        $(this).parents(".trip-list.wfull").find(".memlist.flex").replaceWith(shareList);
		addAndRemoveHandler();
	});
}
