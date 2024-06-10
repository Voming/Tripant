$(loadedHandler);
function loadedHandler(){
	// 버튼 호버 css
	$('.btn').hover(function(){
		$(this).css('background-color', 'black');
		$(this).css('color', 'white');
	}, function(){
		$(this).css('background-color', 'var(--color_light_gray)');
		$(this).css('color', 'black');
	});
	
	// 상품 선택 시 개수 표현
	$('input').on('click', itemNumHandler);
	
	// 폰트 모달 띄우기
	$('.btn.font').on('click', modalOpenHandler);
	
	// 폰트 모달 닫기
	$('.btn.modal-close').on('click', modalCloseHandler);
}