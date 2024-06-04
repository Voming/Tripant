//마이페이지 돌아가기
function backClickHandler(){
	location.href = '/my/home';
}
//회원 탈퇴
function quitClickHandler(){
	const memPassword = $('#memPassword').val();
	$.ajax({
		url: '/my/quit', 
		type: 'post', 
		data: {memPassword: memPassword}, 
		success: function(result){
			if(result == 1){
				if(confirm('정말 탈퇴하시겠습니까?')){
					alert('탈퇴 완료되었습니다.\n메인 페이지로 이동합니다.');
					let form = document.getElementById('frm-quit');
					form.action = '/logout';
					form.method = 'POST';
					form.submit();
				}
			}else{
				alert('회원 탈퇴 중 오류가 발생했습니다.\n관리자에게 문의해주시기 바랍니다.');
			}
		}, 
		error: ajaxErrorHandler
	});
}