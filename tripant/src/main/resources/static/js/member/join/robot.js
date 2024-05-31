//구글 리캡챠 v2
function robotHandler(){
	let memberEntity = $("#frm-join").serialize();
	memberEntity = memberEntity+"&recaptcha="+$("#g-recaptcha-response").val();
	var captcha = 0;
	$.ajax({
           url: '/join',
           type: 'post',
           data: memberEntity,
           success: function(data) {
               switch (data) {
                   case 1:
                   	alert('회원가입이 완료되었습니다. 로그인 창으로 이동합니다.');
                       console.log("자동 가입 방지 봇 통과");
                       captcha = 1;
                       location.href="/login";
               		break;
                   case 0:
                       alert("자동 가입 방지 봇을 확인 한뒤 진행 해 주세요.");
                       break;
                   default:
                       alert("자동 가입 방지 봇을 실행 하던 중 오류가 발생 했습니다. [Error Code : " + Number(data) + "]");
                  		break;
               }
           }
       });
	if(captcha != 1) {
		return false;
	}
}