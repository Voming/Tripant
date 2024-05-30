//인증번호 발송 및 입력창 표시
function codeSendHandler(){
	$(".inputbtn.check").show();
	var userEmail = $("#email").val();
	$.ajax({
		url: "/code/send", 
		type: "post", 
		async: false, 
		data: {userEmail: userEmail}, 
		success: function(result){
			$("#tempCode").val(result);
			if(result == null){
				alert('인증번호 발송 중 오류가 발생했습니다.');
			}else{
				console.log(result);
				alert("인증번호가 발송되었습니다.");
			}
		},  
		error: function (request, status, error){
			alert("code: "+request.status + "\n" + "message: " 
					+ request.responseText + "\n"
					+ "error: "+error);
		}
	});
}

//인증번호 확인
function codeCheckHandler(){
	var inputCode = $("#code").val();
	var sendCode = $("#tempCode").val();
	$.ajax({
		url: "/code/check", 
		type: "post", 
		async: false, 
		data: {inputCode: inputCode, sendCode: sendCode}, 
		success: function(result){
			if(result == 1){
				alert("인증 성공하였습니다.");
				$(".inputbtn.check").hide();
				$(".btn.sendCode").hide();
				$("#email").attr("disabled", true);
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