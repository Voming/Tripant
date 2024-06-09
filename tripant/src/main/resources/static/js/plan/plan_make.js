$(loadedHandler);

function loadedHandler() {
	$(".logo").on("click", btnLogoClickHandler);
	
	//좌측 탭
	$('.tab-nav a').click(function() {
		$('.tab-content > div').hide().filter(this.hash).fadeIn();
		$('.tab-nav a').css("color","black");
		$('.tab-nav a').removeClass('active');
		$(this).addClass('active');
	
		$(this).css("color", "#4BC9E5");
		return false;
	}).filter(':eq(0)').click();
}

function btnLogoClickHandler(){
	if( confirm("지금 나가면 작업한 내용이 없어집니다. 괜찮습니까?") ==true){
		location.href = "/";
	}
}