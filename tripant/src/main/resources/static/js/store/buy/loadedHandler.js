$(loadedHandler);
function loadedHandler(){
	//버튼:hover css
	$('.btn').hover(function(){
		if(!$(this).prop('disabled')){
			$(this).css('background-color', 'lightgray');
			$(this).css('color', 'black');
		}
	}, function(){
		if(!$(this).prop('disabled')){
			$(this).css('background-color', 'black');
			$(this).css('color', 'white');
		}
	});
	
	// 버튼 활성화
	$('input[name=buy-pick]').on('click', btnActiveHandler);
}

// 버튼 활성화
function btnActiveHandler(){
	let checkNum = $('input[name=buy-pick]:checked').length;
	if(checkNum == 0){
		$('.btn').prop('disabled', true);
	}else{
		$('.btn').prop('disabled', false);
	}
}