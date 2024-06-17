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
				if(confirm('선택하신 상품이 장바구니에 담겼습니다.\n장바구니로 이동하시겠습니까?')){
					location.href = contextPath + "store/cart";
				}else{
					location.reload();					
				}
			}else{
				alert('장바구니에 담는 중 오류가 발생했습니다.\n관리자에게 문의해주시기 바랍니다.');
			}
		}, 
		error: ajaxErrorHandler 
	});
}