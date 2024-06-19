var clickspotnum = 0;
var spottype;

function spotMoreBtnClickHandler(thisElement) {
	clickspotnum += 1;

	$.ajax({
		url: contextPath + "plan/spot/more"
		, method: "post"
		, context: this
		, data: {
			areaCode: areacode,
			spotType: spottype,
			clickSpotNum : clickspotnum
		}
		, error: ajaxErrorHandler
	}).done(function(wrap_spot) {
		$(".wrap-spotList").replaceWith(wrap_spot);
	});
}

$(document).ready(function() {
	$('.spot-tab-nav a').click(function() {
		$('.spot-tab-content > div').hide().filter(this.hash).fadeIn();
		$('.spot-tab-nav a').css("color", "var(--color_gray)");
		$('.spot-tab-nav li').css("background-color", "white");
		$('.spot-tab-nav a').removeClass('active');
		$(this).addClass('active');
		$(this).css("color", "white");
		$(this).parent().css("background-color", "var(--color_day9_blue)");
		
		//더보기 클릭 횟수 초기화
		clicknum = 0;

		areacode = $(".plan-areacode").attr("value");
		var placeTypeS = $(this).text();

		if (placeTypeS == '관광지') {
			spottype = 1;
		} else if (placeTypeS == '문화시설') {
			spottype = 2;
		} else if (placeTypeS == '쇼핑') {
			spottype = 3;
		} else if (placeTypeS == '음식점') {
			spottype = 4;
		}

		$.ajax({
			url: contextPath + "plan/spot"
			, method: "post"
			, context: this
			, data: {
				areaCode: areacode,
				spotType: spottype
			}
			, error: ajaxErrorHandler
		}).done(function(wrap_spot) {
			$(".wrap-spotList").replaceWith(wrap_spot);
		});

		return false;
	}).filter(':eq(0)').click();
});

