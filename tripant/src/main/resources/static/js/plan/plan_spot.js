var clickspotnum = 0;
var areacode;
var spottype;

function spotMoreBtnClickHandler(thisElement) {
	clickspotnum += 1;
	console.log(clickspotnum);
	console.log("들어옴" +areacode + spottype);


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
		clickspotnum = 0;

		areacode = $(".plan-areacode").attr("value");
		var spotTypeS = $(this).text();

		// type(1:관광지, 2:문화시설, 3:쇼핑, 4:음식점, 5:숙박, 6:캠핑장)
		if (spotTypeS == '관광지') {
			spottype = 1;
		} else if (spotTypeS == '문화시설') {
			spottype = 2;
		} else if (spotTypeS == '쇼핑') {
			spottype = 3;
		} else if (spotTypeS == '음식점') {
			spottype = 4;
		}

		console.log(areacode + spottype);

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

