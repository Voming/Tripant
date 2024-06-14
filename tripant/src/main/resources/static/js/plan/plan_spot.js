//장소 탭
$(document).ready(function() {
	$('.spot-tab-nav a').click(function() {
		$('.spot-tab-content > div').hide().filter(this.hash).fadeIn();
		$('.spot-tab-nav a').css("color", "black");
		$('.spot-tab-nav li').css("background-color", "white");
		$('.spot-tab-nav a').removeClass('active');
		$(this).addClass('active');
		$(this).css("color", "white");
		$(this).parent().css("background-color", "var(--color_day9_blue)")
		return false;
	}).filter(':eq(0)').click();
});