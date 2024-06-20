//마이페이지 돌아가기
function backClickHandler(){
	location.href = contextPath+'my/home';
}
//닉네임 저장
function saveClickHandler(){
	$.ajax({
		url: contextPath+'my/nick', 
		type: 'post', 
		data: $('#memNick'), 
		success: async function(result){
			if(result == 1){
				const chNickSuccess = await Swal.fire({
					title: "닉네임이 변경되었습니다.", 
					icon: "success", 
					confirmButtonColor: "#000000", 
					confirmButtonText: "확인"
				});
				if(chNickSuccess.isConfirmed){
					location.href = contextPath+'my/home';
				}
			}else{
				Swal.fire({
					title: "닉네임 변경 중 오류가 발생했습니다.\n관리자에게 문의해주시기 바랍니다.", 
					icon: "error", 
					confirmButtonColor: "#000000", 
					confirmButtonText: "확인"
				});
			}
		}, 
		error: ajaxErrorHandler
	});
}