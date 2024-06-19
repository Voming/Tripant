//장소 탭
$(document).ready(function() {
	$('.stay-tab-nav a').click(function() {
		$('.stay-tab-content > div').hide().filter(this.hash).fadeIn();
		$('.stay-tab-nav a').css("color", "var(--color_gray)");
		$('.stay-tab-nav li').css("background-color", "white");
		$('.stay-tab-nav a').removeClass('active');
		$(this).addClass('active');
		$(this).css("color", "white");
		$(this).parent().css("background-color", "var(--color_day9_blue)");


		var areacode = $(".plan-areacode").attr("value");
		var stayTypeS = $(this).text();

		// type(1:관광지, 2:문화시설, 3:쇼핑, 4:음식점, 5:숙박, 6:캠핑장)
		var staytype;
		if (stayTypeS == '숙박') {
			staytype = 5;
		} else if (stayTypeS == '캠핑장') {
			staytype = 6;
		}

		console.log(areacode + staytype);

		$.ajax({
			url: contextPath + "plan/stay"
			, method: "post"
			, context: this
			, data: {
				areaCode: areacode,
				stayType: staytype
			}
			, error: ajaxErrorHandler
		}).done(function(wrap_stay) {
			$(".wrap-stayList").replaceWith(wrap_stay);
		});

		return false;
	}).filter(':eq(0)').click();
});