//인증코드 발송 및 입력창 표시
function codeSendHandler(){
	var memEmail = $("#memEmail").val();
	$.ajax({
		url: "/code/send", 
		type: "post", 
		async: false, 
		data: {memEmail: memEmail}, 
		success: function(result){
			if(result === "1"){
				alert("인증번호가 발송되었습니다.");
				$(".inputbtn.check").removeClass('hide');
			}else if(result === "0"){
				alert('인증번호 발송 중 오류가 발생했습니다.');
			}else if(result === "-1"){
				alert('이미 가입된 회원입니다.');
			}
		},  
		error: function (request, status, error){
			alert("code: "+request.status + "\n" + "message: " 
					+ request.responseText + "\n"
					+ "error: "+error);
		}
	});
}
//인증코드 확인
function codeCheckHandler(){
	clearTimeout(time_id);
	var inputCode = $("#code").val();
	$.ajax({
		url: "/code/check", 
		type: "post", 
		async: false, 
		data: {inputCode: inputCode}, 
		success: function(result){
			if(result == 1){
				alert("이메일 인증에 성공하였습니다.");
				$(".inputbtn.check").addClass('hide');
				$(".btn.sendCode").addClass('hide');
				$("#memEmail").attr("readonly", true);
				joinActive();
			}else{
				alert('인증번호가 일치하지 않습니다.');
			}
		}, 
		error: function (request, status, error){
			alert("code: "+request.status + "\n" + "message: " 
					+ request.responseText + "\n"
					+ "error: "+error);
		}
	});
}