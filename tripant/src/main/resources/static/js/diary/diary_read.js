$(loaededHandler);
function loaededHandler(){
	//공유-모달창
	$('.share').on("click",shareHandler);
	//신고하기
	$('.report').on("click",reportHandler);
	// 삭제하기
	$('.delete').on("click",deleteHandler);
	
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
		  title: "이 글을 신고하시겠습니까?",
		  text: "신고를 취소하고 싶으신 경우, 고객센터에 문의해주시길 바립니다.",
		  showCancelButton: true,
		  confirmButtonColor: "#000000",
		  cancelButtonColor: "#d33",
		  confirmButtonText: "확인",
		  cancelButtonText: "취소",
	   	  confirmButtonTextFont:"Binggrae",
	   	   animation:false
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
// 공유하기
function shareHandler() {
    var shareId = $(this).data('share');
    console.log(shareId);
    // 공유하기 모달 표시
    Swal.fire({
        title: "나의 여행기 공유하기",
        html: "<p>여행기를 공유하시겠습니까?</p><div class='share-links'><a href='#' class='facebook-link'>페이스북</a><a href='#' class='twitter-link'>트위터</a></div>",
        showCancelButton: true,
        confirmButtonColor: "#000000",
        cancelButtonColor: "#d33",
        confirmButtonText: "확인",
        cancelButtonText: "취소",
        confirmButtonTextFont: "Binggrae",
        animation:false
    }).then((result) => {
        if (result.isConfirmed) {
            // 여행기 공유 처리를 수행하는 코드 추가
        }
    });

    // 페이스북으로 공유 링크 클릭 시
    $('.facebook-link').click(function() {
        // 여행기를 페이스북으로 공유하는 기능 추가
        var shareUrl = "https://www.facebook.com/sharer/sharer.php?u=" + encodeURIComponent(window.location.href);
        window.open(shareUrl, '_blank');
    });

    // 트위터로 공유 링크 클릭 시
    $('.twitter-link').click(function() {
        // 여행기를 트위터로 공유하는 기능 추가
        var shareUrl = "https://twitter.com/intent/tweet?url=" + encodeURIComponent(window.location.href);
        window.open(shareUrl, '_blank');
    });
}

/*좋아요 누르기  */
function btnLikeClickHandler(thisElement){
	console.log("눌림");
/*	console.log(thisElement);*/
     // 현재 이미지가 '좋아요 없음' 이미지라면 '좋아요 있음' 이미지로 변경
        if ($(thisElement).attr('src') === '/images/diary/diary_like_none.png') {
            $(thisElement).attr('src', '/images/diary/diary_like_icon.png');
        } else {
            // 현재 이미지가 '좋아요 있음' 이미지라면 '좋아요 없음' 이미지로 변경
            $(thisElement).attr('src', '/images/diary/diary_like_none.png');
        }
}
// 삭제하기
function deleteHandler(){
	var deleteId = $(this).data('"delete"');
	console.log(deleteId);
		Swal.fire({
		  title: "이 글을 삭제하시겠습니까?",
		  text: "삭제하실 경우 되돌릴 수 없습니다.",
		  showCancelButton: true,
		  confirmButtonColor: "#000000",
		  cancelButtonColor: "#d33",
		  confirmButtonText: "확인",
		  cancelButtonText: "취소",
	   	  confirmButtonTextFont:"Binggrae",
	   	   animation:false
		}).then((result) => {
		  if (result.isConfirmed) {
	         $.ajax({
        	 url:"/diary/delete",
        	 method:"post",
        	 data: {deleteId:deleteId},
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
				}
			 },
			 error : ajaxErrorHandler
         	});//ajax
		  }//if
		});
}
