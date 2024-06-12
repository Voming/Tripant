$(document).ready(function(){
	//공유-모달창
	$('.share-plan.modal').on("click",shareModalHandler);
	$('.confimed').on("click",closeModalHandler)
	
	//케밥 아이콘 이벤트
	$('.info').on("click",miniModalBtnHandler);
});



//공유 창의 확인 버튼 - 화면 리로드
function closeModalHandler(){
	location.reload();
}

//외부영역 클릭 시 모달 닫기
$(document).mouseup(function(e) {
	var LayerModal = $(".wrap-modal");

	// 클릭된 대상이 LayerModal의 자식 요소가 아닐경우
	if(LayerModal.has(e.target).length === 0 ){
		LayerModal.css("display","none");
		$('.mini-modal').addClass('hide');
	}
	
});

//케밥창 하나만 열리게 설정
function miniModalBtnHandler(){
	var mbtn = $(this).children('.mini-modal');
	$('.mini-modal').addClass('hide');
	$(this).children('.mini-modal').removeClass("hide");
}

//공유 열기
function shareModalHandler(){
	$(this).parent().parent().siblings(".wrap-modal").css("display","block");
}

