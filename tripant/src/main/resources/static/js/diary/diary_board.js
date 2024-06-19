$(document).ready(function() {

    // 초기화: 탭 전환
    $('.area-tab-nav a').click(function() {
        $('.diary-tab-content > div').hide().filter(this.hash).fadeIn();
        $('.area-tab-nav li').css("background-color", "white");
        $('.area-tab-nav a').removeClass('active');
        $(this).addClass('active');
        console.log($(this).text());
        $(this).parent().css("background-color", "black");
        
        // ajax로 지역 태그 활성화
        console.log(contextPath + "diary");
        var areaname = $(this).text().trim();
        areaname = (areaname == "전체" ? "":areaname);
        // ajax
		$.ajax({
			url: contextPath + "diary"
			, method: "post"
			, data : {areaname : areaname}
			, context: this
			, error: ajaxErrorHandler
		}).done(function(wrap_content) {
			$(".wrap-d-content").replaceWith(wrap_content);
		})
        
        return false;
    }).filter(':eq(0)').click(); // 첫 번째 탭 활성화

    // 초기화: 좋아요 기능
    $('.diary-like .like-icon').on('click', function() {
        var $likeIcon = $(this);
        var diaryId = $likeIcon.closest('.diary-like').data('diary-id'); // 일기의 ID를 가져오기, 데이터에 따라 적절히 변경

        // AJAX 요청을 통해 좋아요 처리
        $.ajax({
            type: 'POST',
            url: '${pageContext.request.contextPath}/like', // 실제 서버의 API 엔드포인트 URL
            data: { diaryId: diaryId },
            dataType: 'json',
            success: function(result) {
                if (result.success) {
                    if ($likeIcon.attr('src') === '/images/diary/diary_like_none.png') {
                        $likeIcon.attr('src', '/images/diary/diary_like_icon.png');
                    } else {
                        $likeIcon.attr('src', '/images/diary/diary_like_none.png');
                    }
                } else {
                    console.error('Failed to like diary.');
                }
            },
            error: function(err) {
                console.error('Error while liking diary:', err);
            }
        });
    });
});

//장소 더보기 클릭
var clicknum = 0;
function MoreBtnClickHandler(thisElement) {
	clicknum += 1;

	$.ajax({
		url: contextPath + "diary/more"
		, method: "post"
		, context: this
		, data: {
			areaCode: areacode,
			placeType: placetype,
			clickNum : clicknum
		}
		, error: ajaxErrorHandler
	}).done(function(wrap_place) {
		$(".wrap-placeList").replaceWith(wrap_place);
	});
}