var clicknum;
var areaname;
$(document).ready(function() {
	clicknum = 0;


	// 초기화: 탭 전환
	$('.area-tab-nav a').click(tabMenuClickHandler).filter(':eq(0)').click(); // 첫 번째 탭 활성화
});

function tabMenuClickHandler() {
	clicknum = 0; // 페이지 초기화
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
	const token = $("meta[name='_csrf']").attr("content");
	const header= $("meta[name='_csrf_header']").attr("content");
	// ajax 요청
	$.ajax({
		url: contextPath + "diary" + "/popular/" + $("#sortOption").val()
		,beforeSend : function(xhr){
			/* 데이터를 전송하기 전에 헤더에 csrf값을 설정 */
			xhr.setRequestHeader(header,token);
			}
		, method: "post"
		, data: {
			areaname: areaname,
			clickNum: clicknum
		}
		, context: this
		, error: ajaxErrorHandler
	}).done(function(wrap_content) {
		$(".wrap-diary").replaceWith(wrap_content);
		//TODO with voming
		$.each($(".diary-preview"), function(idx, thisElement) {
			var contentElement = $(thisElement).html();
			console.log(thisElement);
			console.log(idx);
		});
	})

	return false;
}
// 좋아요 아이콘 설정 및 해제
function btnLikeClickHandler(thisElement, diaryId) {
	console.log("btnLikeClickHandler 눌림");
	console.log(diaryId);
	const token = $("meta[name='_csrf']").attr("content");
	const header= $("meta[name='_csrf_header']").attr("content");
	/*	console.log(thisElement);*/
	if ($(thisElement).attr('src') === '/images/diary/diary_like_none.png') {
		// 현재 이미지가 '좋아요 없음' 이미지라면 '좋아요 있음' 이미지로 변경
		
		// ajax 요청
		$.ajax({
			url: contextPath + "my/diary/like/" + diaryId
			,beforeSend : function(xhr){
			/* 데이터를 전송하기 전에 헤더에 csrf값을 설정 */
			xhr.setRequestHeader(header,token);
			}
			, method: "post"
			, error: ajaxErrorHandler
		}).done(function(result) {
			if(result > 0)
			$(thisElement).attr('src', '/images/diary/diary_like_icon.png');
			window.location.herf='/diary/read'+diaryId;	
		})
	} else {
		// 현재 이미지가 '좋아요 있음' 이미지라면 '좋아요 없음' 이미지로 변경
		// ajax 요청
		$.ajax({
			url: contextPath + "my/diary/unlike/" + diaryId
			,beforeSend : function(xhr){
			/* 데이터를 전송하기 전에 헤더에 csrf값을 설정 */
			xhr.setRequestHeader(header,token);
			}
			, method: "post"
			, error: ajaxErrorHandler
		}).done(function(result) {
			if(result>0)
			$(thisElement).attr('src', '/images/diary/diary_like_none.png');
			window.location.herf='/diary/read'+diaryId;	
		})
	}
}

function moreBtnClickHandler(thisElement) {
	clicknum += 1;
	const token = $("meta[name='_csrf']").attr("content");
	const header= $("meta[name='_csrf_header']").attr("content");
	// ajax 요청
	$.ajax({
		url: contextPath + "diary" + "/popular/" + $("#sortOption").val()
		,beforeSend : function(xhr){
			/* 데이터를 전송하기 전에 헤더에 csrf값을 설정 */
			xhr.setRequestHeader(header,token);
			}
		, method: "post"
		, data: {
			areaname: areaname,
			clickNum: clicknum
		}
		, context: this
		, error: ajaxErrorHandler
	}).done(function(wrap_content) {
		$(".wrap-diary").replaceWith(wrap_content);
		
		$.each($(".diary-preview"), function(idx, thisElement) {
			var contentElement = $(thisElement).html();
			//console.log($(thisElement).text());
			//console.log(contentElement);
		});
	});

}

function sortOptionChangeHandler(thisElement) {
	//console.log(thisElement.value);
	clicknum = 0; // 페이지 초기화
	const token = $("meta[name='_csrf']").attr("content");
	const header= $("meta[name='_csrf_header']").attr("content");
	// ajax 요청
	$.ajax({
		url: contextPath + "diary" + "/popular/" + thisElement.value
		,beforeSend : function(xhr){
			/* 데이터를 전송하기 전에 헤더에 csrf값을 설정 */
			xhr.setRequestHeader(header,token);
			}
		, method: "post"
		, data : {
			areaname: areaname,
			clickNum: clicknum
		}
		, error: ajaxErrorHandler
	}).done(function(wrap_content) {
		//$('.area-tab-nav a').filter(':eq(0)').click(); // 첫 번째 탭 활성화
		$(".wrap-diary").replaceWith(wrap_content);
	})

}

