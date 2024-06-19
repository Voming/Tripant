$(document).ready(function() {

    // 초기화: 탭 전환
    $('.area-tab-nav a').click(function() {
        $('.diary-tab-content > div').hide().filter(this.hash).fadeIn();
        $('.area-tab-nav li').css("background-color", "white");
        $('.area-tab-nav a').removeClass('active');
        $(this).addClass('active');
        console.log($(this).text());
        $(this).parent().css("background-color", "black");
        
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


    //더보기 기능
/*        const diaries = [
            { diaryId: 1, diaryTitle: '첫 번째 일기', diaryDate: '2024-06-20', memNick: 'user1', diaryContent: '첫 번째 일기 내용입니다.', diaryViews: 100 },
            { diaryId: 2, diaryTitle: '두 번째 일기', diaryDate: '2024-06-21', memNick: 'user2', diaryContent: '두 번째 일기 내용입니다.', diaryViews: 150 },
            { diaryId: 3, diaryTitle: '세 번째 일기', diaryDate: '2024-06-22', memNick: 'user3', diaryContent: '세 번째 일기 내용입니다.', diaryViews: 120 },
            { diaryId: 4, diaryTitle: '네 번째 일기', diaryDate: '2024-06-23', memNick: 'user4', diaryContent: '네 번째 일기 내용입니다.', diaryViews: 180 },
            { diaryId: 5, diaryTitle: '다섯 번째 일기', diaryDate: '2024-06-24', memNick: 'user5', diaryContent: '다섯 번째 일기 내용입니다.', diaryViews: 130 },
            { diaryId: 6, diaryTitle: '여섯 번째 일기', diaryDate: '2024-06-25', memNick: 'user6', diaryContent: '여섯 번째 일기 내용입니다.', diaryViews: 110 },
            { diaryId: 7, diaryTitle: '일곱 번째 일기', diaryDate: '2024-06-26', memNick: 'user7', diaryContent: '일곱 번째 일기 내용입니다.', diaryViews: 140 },
            { diaryId: 8, diaryTitle: '여덟 번째 일기', diaryDate: '2024-06-27', memNick: 'user8', diaryContent: '여덟 번째 일기 내용입니다.', diaryViews: 160 },
            { diaryId: 9, diaryTitle: '아홉 번째 일기', diaryDate: '2024-06-28', memNick: 'user9', diaryContent: '아홉 번째 일기 내용입니다.', diaryViews: 170 },
            { diaryId: 10, diaryTitle: '열 번째 일기', diaryDate: '2024-06-29', memNick: 'user10', diaryContent: '열 번째 일기 내용입니다.', diaryViews: 200 }
        ];*/

        const diaryList = document.getElementById('diaryList');
        const loadMoreBtn = document.getElementById('loadMoreBtn');
        let startIndex = 0;
        const batchSize = 4; // 한 번에 로드할 게시글 수

        // 초기에 처음 일부 게시글 표시
        function displayDiaries() {
            for (let i = startIndex; i < Math.min(startIndex + batchSize, diaries.length); i++) {
                const diary = diaries[i];
                const diaryDiv = document.createElement('div');
                diaryDiv.classList.add('wrap-d-content');
                diaryDiv.innerHTML = `
                    <div class="diary-img">
                        <img src="/images/diary/diary_empty_image.png" alt="대표 이미지">
                    </div>
                    <div class="wrap-content">
                        <div class="diary-content">
                            <div>
                                <p class="diary-title" style="font-size: var(--font3);">
                                    <a href="/diary/read/${diary.diaryId}">${diary.diaryTitle}</a>
                                </p>
                                <p class="diary-date" style="font-size: var(--font4);">${diary.diaryDate}</p>
                                <p class="diary-nickname" style="font-size: var(--font5);">${diary.memNick}</p>
                                <p class="diary-preview" style="font-size: var(--font5);">${diary.diaryContent}</p>
                            </div>
                        </div>
                        <div class="diary-like">
                            <img src="/images/diary/diary_like_none.png" alt="하트아이콘" class="like-icon">
                            <div>
                                <p style="font-size: var(--font6);">view</p>
                                <p class="diary-diaryViews" style="font-size: var(--font6);">${diary.diaryViews}</p>
                            </div>
                        </div>
                    </div>
                `;
                diaryList.appendChild(diaryDiv);
            }
            startIndex += batchSize;
        }

    

        // '더보기' 버튼 클릭 시 추가 게시글 로드
        loadMoreBtn.addEventListener('click', () => {
            displayDiaries();
            // 모든 게시글을 로드했을 경우 더보기 버튼 숨기기 예시
            if (startIndex >= diaries.length) {
                loadMoreBtn.style.display = 'none';
            }
        });
});