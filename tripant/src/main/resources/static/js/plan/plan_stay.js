$(document).ready(function() {
	$('.stay-tab-nav a').click(function() {
		$('.stay-tab-content > div').hide().filter(this.hash).fadeIn();
		$('.stay-tab-nav a').css("color", "var(--color_gray)");
		$('.stay-tab-nav li').css("background-color", "white");
		$('.stay-tab-nav a').removeClass('active');
		$(this).addClass('active');
		$(this).css("color", "white");
		$(this).parent().css("background-color", "var(--color_day9_blue)");
		
		//더보기 클릭 횟수 초기화
		clicknum = 0;

		areacode = $(".plan-areacode").attr("value");
		var placeTypeS = $(this).text();

		if (placeTypeS == '숙박') {
			placetype = 5;
		} else if (placeTypeS == '캠핑장') {
			placetype = 6;
		} 

		$.ajax({
			url: contextPath + "plan/box"
			, method: "post"
			, context: this
			, data: {
				areaCode: areacode,
				placeType: placetype
			}
			, error: ajaxErrorHandler
		}).done(function(wrap_place) {
			$(".wrap-placeList").replaceWith(wrap_place);
		});

		return false;
	}).filter(':eq(0)').click();
});

