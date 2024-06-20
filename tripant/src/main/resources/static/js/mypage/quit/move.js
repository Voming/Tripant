//마이페이지 돌아가기
function backClickHandler(){
	location.href = contextPath+'my/home';
}
//회원 탈퇴
async function quitClickHandler(){
	const memPassword = $('#memPassword').val();
	const quitReal = await Swal.fire({
		title: "정말 탈퇴하시겠습니까?", 
		icon: "question", 
		showCancelButton: true, 
		confirmButtonText: "탈퇴하기", 
		confirmButtonColor: "#ff0000", 
		cancelButtonText: "돌아가기", 
		cancelButtonColor: "#000000"
	});
	if(quitReal.isConfirmed){
		$.ajax({
			url: contextPath+'my/quit', 
			type: 'post', 
			data: {memPassword: memPassword}, 
			success: async function(result){
				if(result > 0){
					const quitSuccess = await Swal.fire({
						title: "탈퇴 처리되었습니다.\n메인 페이지로 이동합니다.", 
						icon: "success", 
						confirmButtonColor: "#000000", 
						confirmButtonText: "확인"
					});
					if(quitSuccess.isConfirmed){
						let form = document.getElementById('frm-quit');
						form.action = contextPath+'logout';
						form.method = 'POST';
						form.submit();
					}
				}else{
					Swal.fire({
						title: "회원 탈퇴 중 오류가 발생했습니다.\n관리자에게 문의해주시기 바랍니다.", 
						icon: "error", 
						confirmButtonColor: "#000000", 
						confirmButtonText: "확인"
					});
				}
			}, 
			error: ajaxErrorHandler
		});
	}
}













//마이페이지 돌아가기
function backClickHandler(){
	location.href = contextPath+'my/home';
}
//회원 탈퇴
function quitClickHandler(){
	const memPassword = $('#memPassword').val();
	$.ajax({
		url: contextPath+'my/quit', 
		type: 'post', 
		data: {memPassword: memPassword}, 
		success: async function(result){
			if(result == 1){
				const quitReal = await Swal.fire({
					title: "정말 탈퇴하시겠습니까?", 
					icon: "question", 
					showCancelButton: true, 
					confirmButtonText: "탈퇴하기", 
					confirmButtonColor: "#ff0000", 
					cancelButtonText: "돌아가기", 
					cancelButtonColor: "#000000"
				});
				if(quitReal.isConfirmed){
					const quitSuccess = await Swal.fire({
						title: "탈퇴 처리되었습니다.\n메인 페이지로 이동합니다.", 
						icon: "success", 
						confirmButtonColor: "#000000", 
						confirmButtonText: "확인"
					});
					if(quitSuccess.isConfirmed){
						let form = document.getElementById('frm-quit');
						form.action = contextPath+'logout';
						form.method = 'POST';
						form.submit();
					}
				}
			}else{
				Swal.fire({
					title: "회원 탈퇴 중 오류가 발생했습니다.\n관리자에게 문의해주시기 바랍니다.", 
					icon: "error", 
					confirmButtonColor: "#000000", 
					confirmButtonText: "확인"
				});
			}
		}, 
		error: ajaxErrorHandler
	});
}