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
	//data로 id와 title받아오기
	var planId = $(this).data('plan-id');
	var planTitle = $(this).data('title');
	//TODO data ajax로 넘기기
	Swal.fire({
		  title: "<h2>"+planTitle+"</h2>",
		  text: "삭제하시겠습니까?",
		  showCancelButton: true,
		  confirmButtonColor: "#3085d6",
		  cancelButtonColor: "#d33",
		  confirmButtonText: "삭제",
		  cancelButtonText: "취소",
	   	  confirmButtonTextFont:"Binggrae",
		}).then((result) => {
		  if (result.isConfirmed) {
	         $.ajax({
        	 url:"/trip/list/delete",
        	 method:"post",
        	 data: {planId:planId},
			 //success: 1이면 업데이트 완료 0이면 실패
			 success : function(result) {
				if(result == 1){
						Swal.fire({
				     	title: "성공",
				      	text: "삭제되었습니다",
		                confirmButtonText: 'Ok'		      	
				    }).then(() => {
						location.reload();
					});
				}else if(result == 0){
						Swal.fire({
				     	title: "실패",
				      	text: "삭제에 실패했습니다. 다시 한 번 확인해주세요",
		                confirmButtonText: 'Ok'		      	
				    })
				}
			 },
			 error : ajaxErrorHandler
         	});//ajax
		  }//if
		});
}
