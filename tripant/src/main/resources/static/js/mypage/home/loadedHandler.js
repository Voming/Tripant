$(loadedHandler);
function loadedHandler(){
	//버튼 호버 css
	$('.btn').hover(function(){
		$(this).css('background-color', 'lightgray');
		$(this).css('color', 'black');
	}, function(){
		$(this).css('background-color', 'black');
		$(this).css('color', 'white');
	});
	//닉네임 변경 페이지로 이동
	$('.btn.chNick').on('click', function(){
		location.href = '/my/nick';
	});
	//비밀번호 변경 페이지로 이동
	$('.btn.chPwd').on('click', function(){
		location.href = '/my/pwd';
	});
}