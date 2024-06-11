// 장바구니에 담기
function insertItemsHandler(){
	let items = [];
	$('input:checked').each(function(index, element){
		items.push(element.value);
	});
	console.log(items);
	$.ajax({
		url: contextPath+'store/insert', 
		type: 'post', 
		data: {items : items}, 
		success: function(result){
			if(result == 1){
				alert('선택하신 상품이 장바구니에 담겼습니다.');
				location.reload();
			}else{
				alert('장바구니에 담는 중 오류가 발생했습니다.');
			}
		}, 
		error: ajaxErrorHandler 
	});
}