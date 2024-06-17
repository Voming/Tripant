//마이페이지 돌아가기
function backClickHandler(){
	location.href = contextPath+'my/home';
}
//비밀번호 변경
function saveClickHandler(){
	$.ajax({
		url: '/save/pwd', 
		type: 'post', 
		data: $('#memPassword'), 
		success: function(result){
			if(result == 1){
				alert('비밀번호가 변경되었습니다.\n새로운 비밀번호로 로그인 해주시기 바랍니다.');
				let form = document.getElementById('frm-chPwd');
				form.action = contextPath+'logout';
				form.method = 'POST';
				form.submit();
			}else{
				alert('비밀번호 변경 중 오류가 발생했습니다.\n관리자에게 문의해주시기 바랍니다.');
			}
		}, 
		error: ajaxErrorHandler
	});
}