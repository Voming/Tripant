//좌측 탭
$(function() {
	$('.tab-nav a').click(function() {
		$('.tab-content > div').hide().filter(this.hash).fadeIn();
		$('.tab-nav li').css("background-color","white");
		$('.tab-nav a').removeClass('active');
		$(this).addClass('active');
		
		console.log($(this).parent());
		$(this).parent().css("background-color", "#C9EFF7");
		return false;
	}).filter(':eq(0)').click();
});