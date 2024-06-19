$(document).ready(function() {

    // 초기화: 탭 전환
    $('.area-tab-nav a').click(function() {
        $('.diary-tab-content > div').hide().filter(this.hash).fadeIn();
        $('.area-tab-nav li').css("background-color", "white");
        $('.area-tab-nav a').removeClass('active');
        $(this).addClass('active');
        $(this).parent().css("background-color", "black");
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

    // 초기화: 더보기 기능
    var morecnt = 0; // 더보기 횟수 초기화

    $('.learn-more').on('click', function() {
        morecnt += 1;
        $.ajax({
            url: '${pageContext.request.contextPath}/more/List',
            method: 'post',
            data: { more: morecnt },
            dataType: 'json',
            success: function(result) {
                if (result != null) {
                    // 데이터를 화면에 출력하는 코드 작성
                    // 예시: display(result);
                }
            },
            error: function(err) {
                console.error('Error while loading more items:', err);
            }
        });
    });

    // display 함수 예시
    function display(data) {
        // 데이터를 적절히 화면에 출력하는 로직 작성
    }

});