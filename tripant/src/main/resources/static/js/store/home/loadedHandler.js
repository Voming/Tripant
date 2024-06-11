$(loadedHandler);
function loadedHandler(){
	// 버튼 호버 css
	$('.btn').hover(function(){
		$(this).css('background-color', 'var(--color_light_gray)');
		$(this).css('color', 'black');
	}, function(){
		$(this).css('background-color', 'black');
		$(this).css('color', 'white');
	});
	
	// 상품 선택 시 개수 표현
	$('input').on('click', itemNumHandler);
	
	// 폰트 모달 띄우기
	$('.btn.font').on('click', modalOpenHandler);
	
	// 폰트 모달 닫기
	$('.btn.modal-close').on('click', modalCloseHandler);
	
	// 장바구니에 담기
	$('.btn.cart').on('click', insertItemsHandler);
}