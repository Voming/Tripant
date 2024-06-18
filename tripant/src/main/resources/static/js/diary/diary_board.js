/* 더보기 */
/*$(loadedHandler);
let morecnt = 0;

function loadedHandler(){
	$('.learn-more'),on("click", btnMoreclickHanlder);
}
function btnMoreclickHanlder(){
	morecnt +=1;
	$.ajax({
		url:"${pageContext.request.contextPath}/more/List"
		,method:"post"
		,data:{
			more:morecnt
		}
		,dataType:'json'
		,succes:function(result){
			if(result != null){
				dispaly
			}
		}
		});
}*/
/*탭 전환 시키기  */
$(document).ready(function(){
$('.area-tab-nav a').click(function() {
	$('.diary-tab-content > div').hide().filter(this.hash).fadeIn();
	$('.area-tab-nav li').css("background-color", "white");
	$('.area-tab-nav a').removeClass('active');
	$(this).addClass('active');
	$(this).parent().css("background-color", "black");
	return false;
}).filter(':eq(0)').click();

/*좋아요 누르기  */

$(document).ready(function() {
			var itemsPerPage = 8; // 페이지에 8개씩 뿌리기
			var currentPage = 0;
			var currentTap = '#tab01'; // 초기 탭은 전체

			$('.diary-like .like-icon')
				.on(
					'click',
					function() {
						if ($(this).attr('src') === '/images/diary/diary_like_none.png') {
							$(this)
								.attr('src',
									'/images/diary/diary_like_icon.png');
						} else {
							$(this)
								.attr('src',
									'/images/diary/diary_like_none.png');
						}
					});
		});

});

document.addEventListener('DOMContentLoaded', function() {
    const loadMoreButton = document.getElementById('#load-more');
    const diaryContainer = document.querySelector('.wrap-d-content');

    let startIndex = 8; // 처음에 보여질 항목 개수

    loadMoreButton.addEventListener('click', function() {
        // 예시로 간단히 추가할 다이어리 항목 생성
        for (let i = startIndex; i < startIndex + 4; i++) {
            const newDiaryItem = document.createElement('div');
            newDiaryItem.classList.add('wrap-content');
            // 새로운 다이어리 항목의 내용을 생성하여 추가
            // 예: newDiaryItem.innerHTML = '새로운 다이어리 항목 내용';

            diaryContainer.appendChild(newDiaryItem);
        }

        startIndex += 4; // 다음에 로드할 항목의 시작 인덱스 업데이트
    });
});


