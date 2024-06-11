$(loaededHandler);
function loaededHandler(){
	//공유-모달창
	$('.share-plan.modal').on("click",shareModalHandler);
	
	//삭제
	$('.delete-plan').on("click",deleteHandler);
	
	//케밥 아이콘 이벤트
	$('.info').on("click",miniModalBtnHandler);
    $(document).on('click', function(event) {
        // 클릭한 요소가 '.info' 클래스의 버튼 내부 요소나 '.mini-modal' 클래스가 아닌 경우에만 실행
        if (!$(event.target).closest('.info').length && !$(event.target).closest('.mini-modal').length) {
            $('.mini-modal').addClass('hide');
        }
    });	
}

//친구공유 모달
function shareModalHandler(){
	$(".wrap-modal").css("display","block");

}
//친구공유 모달 닫기
$(document).mouseup(function(e) {
	var LayerModal = $(".wrap-modal");
	if (LayerModal.has(e.target).length === 0) {
		LayerModal.css("display","none");
	}
	$('.mini-modal').removeClass('active');
	$(this).parent().addClass('active');
});

//미니 모달창
function miniModalBtnHandler(){
	var mbtn = $(this).children('.mini-modal');
	$('.mini-modal').addClass('hide');
	$(this).children('.mini-modal').removeClass("hide");
	
}

//일정삭제
function deleteHandler() {
	//TODO data ajax로 넘기기
	Swal.fire({
		  title: "[제목]",
		  text: "삭제하시겠습니까?",
		  //icon: "warning",
		  showCancelButton: true,
		  confirmButtonColor: "#3085d6",
		  cancelButtonColor: "#d33",
		  confirmButtonText: "삭제",
		  cancelButtonText: "취소"
		}).then((result) => {
		  if (result.isConfirmed) {
		    Swal.fire({
		      title: "삭제완료",
		      text: "[제목]삭제가 완료되었습니다",
		      //icon: "success"
		    });
		  }
		});
}