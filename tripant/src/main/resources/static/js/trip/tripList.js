$(loaededHandler);
function loaededHandler(){
	//공유-모달창
	$('.share-plan.modal').on("click",shareModalHandler);
	
	//삭제
	$('.delete-plan').on("click",deleteHandler);
	
	//케밥 아이콘 이벤트
	$('.info').on("click",miniModalBtnHandler);
}

//친구공유 모달
function shareModalHandler(){
	$(this).parent().parent().siblings(".wrap-modal").css("display","block");
}
//모든 모달 닫기
$(document).mouseup(function(e) {
	var LayerModal = $(".wrap-modal");
	
	// 클릭된 대상이 LayerModal의 자식 요소가 아니거나, confimed 클래스를 가진 버튼인지 확인
	if (LayerModal.has(e.target).length === 0 || $(e.target).closest('.confimed').length > 0) {
		LayerModal.css("display","none");
		$('.mini-modal').addClass('hide');
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
	//data ajax로 넘기기
	Swal.fire({
		  title: "<h2>"+planTitle+"</h2>",
		  text: "삭제하시겠습니까?",
		  showCancelButton: true,
		  confirmButtonColor: "#000000",
		  cancelButtonColor: "#d33",
		  confirmButtonText: "삭제",
		  cancelButtonText: "취소",
	   	  confirmButtonTextFont:"Binggrae",
	   	  animation:false
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
