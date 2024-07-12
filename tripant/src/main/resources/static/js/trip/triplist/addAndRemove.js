function addAndRemoveHandler(){
	$('.btn.remove').off("click");
	$('.btn.add').off("click");
	//유저삭제
	$('.btn.remove').on('click',removeHandler);
	//유저추가
	$('.btn.add').on('click',addHandler);
}
//삭제
function removeHandler(){
	var removeNick =$(this).prev().text();
	var planId =$(this).parents(".trip-list.wfull").data('plan-id');
	$.ajax({
		beforeSend : csrfHandler,
		error : ajaxErrorHandler,
		url: contextPath+"trip/remove/nick",
		async:false,
		method:"post",
		context:this,//.btn.add
    	data: {planId:planId,removeNick: removeNick},
		success : function(result) {
			if(result >= 0){
				$(this).removeClass('remove');
				$(this).addClass('add');
				$(this).text('추가');
				addAndRemoveHandler();
			}else if (result == -2){
				Swal.fire({
					  title: "<h2> 이미 삭제된 유저입니다.</h2>",
					  showConfirmButton: false,
					  timer:1500,
					  icon: "info",
				   	  confirmButtonTextFont:"Binggrae"
					});					
				$(this).removeClass('remove');
				$(this).addClass('add');
				$(this).text('추가');
				addAndRemoveHandler();
			}
		}
	});
}
//추가
function addHandler(){
	var addNick =$(this).prev().text();
	var planId =$(this).parents(".trip-list.wfull").data('plan-id');
	$.ajax({
		beforeSend : csrfHandler,
		error : ajaxErrorHandler,
		url: contextPath+"trip/add/nick",
		async:false,
		method:"post",
		context:this,//.btn.add
    	data: {planId:planId,addNick: addNick},
		success : function(result) {
			if(result == 1){
				$(this).removeClass('add');
				$(this).addClass('remove');
				$(this).text('삭제');
				addAndRemoveHandler();
			} else if (result == -2){
				Swal.fire({
					  title: "<h2> 이미 추가된 유저입니다.</h2>",
					  showConfirmButton: false,
					  timer:1500,
					  icon: "info",
				   	  confirmButtonTextFont:"Binggrae"
					});				
				$(this).removeClass('add');
				$(this).addClass('remove');
				$(this).text('삭제');
				addAndRemoveHandler();
			} else if (result == -500){
				alert("re try")
			}
		}
	});
}