
//유저 검색
function findNickHandler(){
	if($(this).siblings("[name=find]").val().trim().length==0){
		Swal.fire({
		  title: "공란",
		  text:"빈문자열은 검색할 수 없습니다.",
		  animation:false,
		  showConfirmButton:false,
		  timer:1200
		});		
	}else{
		//여행 아이디와 검색할 닉네임
		var planId = $(this).data('plan-id');
		var findNick=$(this).siblings('.wirte-nick').val()
		
		$.ajax({
			url: "/trip/search/nick",
			method:"post",
			context:this,
        	data: {planId:planId,findNick: findNick},
			success : function(nickList) {
				memlistHandler(nickList);
				$(this).parent().next().html(htmlVal);
			},
			error:ajaxErrorHandler
		});
	}
}
//검색목록  html에 출력하기 위해 함수에 담기
function memlistHandler(nickList){
	htmlVal='';
	for (var idx in nickList){
		var entity = nickList[idx];
		htmlVal+=`
			<div class="memNick flex" >${entity.memNick}<button type="button" class="btn 
			`;
		//추가되지 않은 맴버일 때	
		if(entity.planMemRole == 0){
			htmlVal+=`remove">삭제</button></div>`;
		//추가된 맴버일 때	
		}else if(entity.planMemRole == null){
			htmlVal+=`add">추가</button></div>`;
		}
	}
	return htmlVal;
}