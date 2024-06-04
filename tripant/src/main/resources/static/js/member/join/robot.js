//구글 리캡챠 v2
function robotHandler() {
	let memberEntity = $("#frm-join").serialize();
	memberEntity = memberEntity + "&recaptcha=" + $("#g-recaptcha-response").val();
	let todayVal = new Date();
	let memBirth = $('#memBirth').val();
	let today = todayVal.getFullYear()
				+'-'
				+((todayVal.getMonth()+1)<9?'0'+(todayVal.getMonth()+1):(todayVal.getMonth()+1))
				+'-'
				+((todayVal.getDate())<9?'0'+(todayVal.getDate()):(todayVal.getDate()));
	if(memBirth > today){
		alert('생일은 미래일 수 없습니다.');
		return false;
	}
	var captcha = 0;
	$.ajax({
		url: '/join',
		type: 'post',
		data: memberEntity,
		success: function(data) {
			switch (data) {
				case 1:
					alert('회원가입이 완료되었습니다.\n로그인 창으로 이동합니다.');
					console.log("자동 가입 방지 봇 통과");
					captcha = 1;
					location.href = "/login";
					break;
				case 0:
					alert("자동 가입 방지 봇을 확인 한뒤 진행 해 주세요.");
					break;
				default:
					alert("자동 가입 방지 봇을 실행 하던 중 오류가 발생 했습니다.\n[Error Code : " + Number(data) + "]");
					break;
			}
		}, 
		error: ajaxErrorHandler
	});
	if (captcha != 1) {
		return false;
	}
}