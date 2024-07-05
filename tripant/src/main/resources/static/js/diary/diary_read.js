$(loaededHandler);
function loaededHandler() {
	//공유-모달창
	$('.share').on("click", shareHandler);
	//신고하기
	$('.report').on("click", reportHandler);
	// 삭제하기
	$('.delete').on("click", deleteHandler);

	//케밥 아이콘 이벤트
	/*$('.menu-menu').on("click", miniModalBtnHandler);
	$(document).on('click', function(event) {
		// 클릭한 요소가 '.info' 클래스의 버튼 내부 요소나 '.mini-modal' 클래스가 아닌 경우에만 실행
		if (!$(event.target).closest('.menu-menu').length && !$(event.target).closest('.mini-modal').length) {
			$('.mini-modal').addClass('hide');
		}
	});*/
}

function reportHandler() {
	var diaryId = $(this).data('diary-id');
	
	console.log(diaryId);
	 console.log("report ID: ", diaryId);
	 	const token = $("meta[name='_csrf']").attr("content");
		const header= $("meta[name='_csrf_header']").attr("content");

	Swal.fire({
		title: "이 글을 신고하시겠습니까?",
		text: "신고를 취소하고 싶으신 경우, 고객센터에 문의해주시길 바립니다.",
		showCancelButton: true,
		confirmButtonColor: "#000000",
		cancelButtonColor: "#d33",
		confirmButtonText: "확인",
		cancelButtonText: "취소",
		animation: false
	}).then((result) => {
		if (result.isConfirmed) {
			$.ajax({
				url: contextPath + "/my/diary/report/" + diaryId
				,beforeSend : function(xhr){
				/* 데이터를 전송하기 전에 헤더에 csrf값을 설정 */
				xhr.setRequestHeader(header,token);
				}
				, method: "post"
				, success: function(result) {
					if (result == 1) {
						Swal.fire({
							title: "성공",
							text: "신고되었습니다",
							confirmButtonText: 'Ok'
						}).then(() => {
							location.href = "/diary";
						});
					} else  if(result == -1) {
						Swal.fire({
							title: "오류",
							text: "신고된 글입니다.",
							confirmButtonText: 'Ok'
						});
					}else {
						Swal.fire({
							title: "오류",
							text: "오류가 발생하여 신고하지 못했습니다.",
							confirmButtonText: 'Ok'
						});
					}
				},
				error: ajaxErrorHandler
			});//ajax
		}//if
	});
}
// 삭제하기 

function deleteHandler() {
	// 현재 요소의 data 속석에서 'delete' 값을 가져옴
	var diaryId = $(this).data('diary-id');
	console.log(diaryId);
	console.log("delete ID: ", diaryId);
		const token = $("meta[name='_csrf']").attr("content");
		const header= $("meta[name='_csrf_header']").attr("content");

	//sweetAlert2를 사용하여 확인 다이얼 로그를 표시
	Swal.fire({
		title: "이 글을 삭제하시겠습니까?",
		text: "삭제하실 경우 되돌릴 수 없습니다.",
		showCancelButton: true,
		confirmButtonColor: "#000000",
		cancelButtonColor: "#d33",
		confirmButtonText: "확인",
		cancelButtonText: "취소",
		animation: false
	}).then((result) => {
		// '확인' 버튼이 클릭된 경우
		if (result.isConfirmed) {
			// ajax요청을 사용하여 서버에 삭제요청을 보냄
			$.ajax({
				url:contextPath +  "/my/diary/delete/"+diaryId,
				beforeSend : function(xhr){
				/* 데이터를 전송하기 전에 헤더에 csrf값을 설정 */
				xhr.setRequestHeader(header,token);
				},
				method: "post",
				//success: 1이면 업데이트 완료 0이면 실패
				success: function(result) {
					if (result == 1) {
						Swal.fire({
							title: "성공",
							text: "삭제되었습니다",
							confirmButtonText: 'Ok'
						}).then(() => {
							location.href = "/diary";
						});
					} else {
						Swal.fire({
							title: "실패",
							text: "삭제에 실패하였습니다.",
							confirmButtonText: 'Ok'
						});
					}
				},
				error: ajaxErrorHandler
			});

		}
	});
}
// 공유하기
function shareHandler() {
	var shareId = $(this).data('share');
	console.log(shareId);
		const token = $("meta[name='_csrf']").attr("content");
		const header= $("meta[name='_csrf_header']").attr("content");
	// 공유하기 모달 표시
	Swal.fire({
		title: "나의 여행기 공유하기",
		html: "<p>여행기를 공유하시겠습니까?</p><div class='share-links'><a href='#' class='facebook-link'>페이스북</a><a href='#' class='twitter-link'>트위터</a></div>",
		showCancelButton: true,
		confirmButtonColor: "#000000",
		cancelButtonColor: "#d33",
		confirmButtonText: "확인",
		cancelButtonText: "취소",
	
		animation: false
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
function btnLikeClickHandler(thisElement, diaryId) {

	console.log("btnLikeClickHandler 눌림");
	console.log(diaryId);
		const token = $("meta[name='_csrf']").attr("content");
		const header= $("meta[name='_csrf_header']").attr("content");
	/*	console.log(thisElement);*/
	if ($(thisElement).attr('src') === '/images/diary/diary_like_none.png') {
		// 현재 이미지가 '좋아요 없음' 이미지라면 '좋아요 있음' 이미지로 변경
		// ajax 요청
		$.ajax({
			url: contextPath + "my/diary/like/" + diaryId
			,beforeSend : function(xhr){
			/* 데이터를 전송하기 전에 헤더에 csrf값을 설정 */
			xhr.setRequestHeader(header,token);
			}
			, method: "post"
			, error: ajaxErrorHandler
		}).done(function(result) {
			if (result > 0)
				$(thisElement).attr('src', '/images/diary/diary_like_icon.png');
		})
	} else {
		// 현재 이미지가 '좋아요 있음' 이미지라면 '좋아요 없음' 이미지로 변경
		// ajax 요청
		$.ajax({
			url: contextPath + "my/diary/unlike/" + diaryId
			,beforeSend : function(xhr){
			/* 데이터를 전송하기 전에 헤더에 csrf값을 설정 */
			xhr.setRequestHeader(header,token);
			}
			, method: "post"
			, error: ajaxErrorHandler
		}).done(function(result) {
			if (result > 0)
				$(thisElement).attr('src', '/images/diary/diary_like_none.png');
		})
	}
}
