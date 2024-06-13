/* 더보기 */
$(loadedHandler);
let morecnt = 0;
function loadedHandler(){
	$('.learn-more'),on("click", btnMoreclickHanlder);
}
function btnMoreclickHanlder(){
	morecnt +=1;
	$.ajax({
		url:""
	});
}
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
		
/*글 클릭하기*/
$(document).ready(function(){
	
});
});