$(loadedHandler);
function loadedHandler(){
	//버튼 호버 css
	$('.btn').hover(function(){
		$(this).css('background-color', 'black');
		$(this).css('color', 'white');
	}, function(){
		$(this).css('background-color', 'lightgrey');
		$(this).css('color', 'black');
	});
}