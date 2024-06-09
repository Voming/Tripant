//비밀번호 재설정
function setPwdHandler(){
	$.ajax({
		url: contextPath+'pwd', 
		method: 'post', 
		data: $('#frm-pwd').serialize(), 
		success: function(result){
			if(result === 1){
				alert('비밀번호 재설정에 성공하였습니다. 로그인 페이지로 이동합니다.');
				location.href = '/login';
			}else {
				alert('비밀번호 재설정 중 오류가 발생했습니다. 관리자에게 문의주시기 바랍니다.');
			}
		}, 
		error: ajaxErrorHandler
	});
}