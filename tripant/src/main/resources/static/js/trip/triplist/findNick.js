
//유저 검색
function findNickHandler(){
	if($(this).siblings("[name=find]").val().trim().length==0){
		var planId =$(this).parents(".trip-list.wfull").data('plan-id');
		$.ajax({
			url: "/trip/share/nick",
			beforeSend : function(xhr){
				/* 데이터를 전송하기 전에 헤더에 csrf값을 설정 */
				xhr.setRequestHeader(header,token);},
			method:"post",
			context:this,
	    	data: {planId:planId}
		}).done( function(shareList) {
	        $(this).parents(".trip-list.wfull").find(".memlist.flex").replaceWith(shareList);
			addAndRemoveHandler();
		});	
	}else{
		//여행 아이디와 검색할 닉네임
		var planId =$(this).parents(".trip-list.wfull").data('plan-id');
		var findNick=$(this).siblings('.wirte-nick').val()
		
		$.ajax({
			url: "/trip/search/nick",
			beforeSend : function(xhr){
				/* 데이터를 전송하기 전에 헤더에 csrf값을 설정 */
				xhr.setRequestHeader(header,token);},
			method:"post",
			context:this,
        	data: {planId:planId,findNick: findNick}
		}).done( function(shareList) {
	        $(this).parents(".trip-list.wfull").find(".memlist.flex").replaceWith(shareList);
			addAndRemoveHandler();
		});
	}
}
