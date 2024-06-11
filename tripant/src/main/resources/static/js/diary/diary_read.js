$(loaededHandler);
function loaededHandler(){
	//공유-모달창
	//$('.share-plan.modal').on("click",shareModalHandler);
	
	//신고하기
	$('.report').on("click",reportHandler);
	
	//케밥 아이콘 이벤트
	$('.info').on("click",miniModalBtnHandler);
    $(document).on('click', function(event) {
        // 클릭한 요소가 '.info' 클래스의 버튼 내부 요소나 '.mini-modal' 클래스가 아닌 경우에만 실행
        if (!$(event.target).closest('.info').length && !$(event.target).closest('.mini-modal').length) {
            $('.mini-modal').addClass('hide');
        }
    });	
}

function reportHandler(){
	var reportId = $(this).data('report');
	console.log(reportId);
		Swal.fire({
		  title: "정말로 이 글을 신고하시겠습니까?",
		  text: "신고를 취소하고 싶으신 경우, 고객센터에 문의해주시길 바립니다.",
		  showCancelButton: true,
		  confirmButtonColor: "#3085d6",
		  cancelButtonColor: "#d33",
		  confirmButtonText: "확인",
		  cancelButtonText: "취소",
	   	  confirmButtonTextFont:"Binggrae",
		}).then((result) => {
		  if (result.isConfirmed) {
	         $.ajax({
        	 url:"/diary/report",
        	 method:"post",
        	 data: {reportId:reportId},
			 //success: 1이면 업데이트 완료 0이면 실패
			 success : function(result) {
				if(result == 1){
						Swal.fire({
				     	title: "성공",
				      	text: "신고되었습니다",
		                confirmButtonText: 'Ok'		      	
				    }).then(() => {
						location.reload();
					});
				}
			 },
			 error : ajaxErrorHandler
         	});//ajax
		  }//if
		});
}