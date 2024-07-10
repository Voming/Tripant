var clicknum = 0; // 값초기화
// 클라이언트 측 변수 설정

function moreBtnClickHandler(thisElement){

	clicknum += 1;
	$.ajax({
		beforeSend : csrfHandler,
		error : ajaxErrorHandler,
		url: contextPath + "my/diary/more"
		, method: "post"
		, data: {
				clickNum: clicknum
			}
		, context: this
		}).done(function(wrap_content) {
			// 서버에서 받아온 wrap_content가 비어 있지 않은 경우에만 추가합니다.
        if (wrap_content.trim() !== "") {
            $(".board-list").append(wrap_content);
             console.log("board-list");
        } else {
            // 추가할 내용이 없으면 아무 작업도 하지 않습니다.
            
            console.log("No more content to add.");
          
        }
    });
	}
	// 이미지 꺼내기
var jImgElement = $(".ck.ck-editor__main").find("img");
			$(jImgElement).each(function(idx, thisElement){
				if(idx>0){ 
					return false;// each 더 이상 안돌게 return false 함
				}// img 태그 1개만 꺼내서 넣고 2번째 each 더 이상 안돌게 return true 함
				var imgSrc = $(thisElement).prop("src");
				diaryImage = imgSrc;
			});
			
   /* 클릭 범위 넓혀서 글 들어가기*/
    $(document).ready(function() {
        $(".wrap-diary").click(function() {
            var diaryId = $(this).data("diaryid");
            window.location.href = contextPath + 'diary/read/' + diaryId;
        });
    });