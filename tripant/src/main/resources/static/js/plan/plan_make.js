//좌측 탭
$(function() {
	$('.tab-nav a').click(function() {
		$('.tab-content > div').hide().filter(this.hash).fadeIn();
		$('.tab-nav a').css("color","black");
		$('.tab-nav a').removeClass('active');
		$(this).addClass('active');
		
		console.log($(this).parent());
		$(this).css("color", "#4BC9E5");
		return false;
	}).filter(':eq(0)').click();
});