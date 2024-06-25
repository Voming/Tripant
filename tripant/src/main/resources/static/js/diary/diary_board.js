var clicknum;
var areaname;
$(document).ready(function() {
	clicknum = 0;
	// 좋아요 아이콘 클릭 이벤트

	// 초기화: 탭 전환
	$('.area-tab-nav a').click(function() {
		//모든 탭 콘텐츠 숨기고 클릭한 탭 콘텐츠만 표시
		$('.diary-tab-content > div').hide().filter(this.hash).fadeIn();
		// 모든 탭 배경색을 흰색으로 설정
		$('.area-tab-nav li').css("background-color", "white");
		// 모든 탭에서 active 클래스 제거

		$('.area-tab-nav a').removeClass('active');
		//모든 탭에서 active 클래스 추가
		$(this).addClass('active');
		console.log($(this).text());
		// 클릭한 탭 배경색을 검정으로 설정
		$(this).parent().css("background-color", "black");

		// ajax로 지역 태그 활성화
		console.log(contextPath + "diary");
		areaname = $(this).text().trim();
		areaname = (areaname == "전체" ? "" : areaname);

		// ajax 요청
		$.ajax({
			url: contextPath + "diary"
			, method: "post"
			, data: {
				areaname: areaname,
				clickNum: clicknum
			}
			, context: this
			, error: ajaxErrorHandler
		}).done(function(wrap_content) {
			$(".wrap-d-content").replaceWith(wrap_content);
			//TODO with voming
			$.each($(".diary-preview"), function(idx, thisElement) {
				var contentElement = $(thisElement).html();
				console.log(thisElement);
				console.log(idx);
			});
		})

		return false;
	}).filter(':eq(0)').click(); // 첫 번째 탭 활성화

	/*좋아요 누르기  */
});

function btnLikeClickHandler(thisElement) {
	console.log("눌림");
	/*	console.log(thisElement);*/
	// 현재 이미지가 '좋아요 없음' 이미지라면 '좋아요 있음' 이미지로 변경
	if ($(thisElement).attr('src') === '/images/diary/diary_like_none.png') {
		$(thisElement).attr('src', '/images/diary/diary_like_icon.png');
	} else {
		// 현재 이미지가 '좋아요 있음' 이미지라면 '좋아요 없음' 이미지로 변경
		$(thisElement).attr('src', '/images/diary/diary_like_none.png');
	}
}

function moreBtnClickHandler(thisElement) {
	clicknum += 1;
	// ajax 요청
	$.ajax({
		url: contextPath + "diary"
		, method: "post"
		, data: {
			areaname: areaname,
			clickNum: clicknum
		}
		, context: this
		, error: ajaxErrorHandler
	}).done(function(wrap_content) {
		$(".wrap-d-content").replaceWith(wrap_content);
		//TODO with voming
		$.each($(".diary-preview"), function(idx, thisElement) {
			var contentElement = $(thisElement).html();
			console.log($(thisElement).text());
			console.log(contentElement);
		});
	})

}

