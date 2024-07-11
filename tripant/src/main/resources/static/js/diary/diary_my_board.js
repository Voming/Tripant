var clicknum = 0; // 값초기화
// 클라이언트 측 변수 설정
var diarymoreCount;
var diaryId = "";

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
			// 서버에서 받아온 wrap_content가 비어 있지 않은 경우에만 추가
//        if (wrap_content == "nomore") {
//			// wrap_content가 빈 문자열인 경우 더보기 버튼을 제거
//          $(".mydiary_more_btn").remove();
//          console.log(".mydiary_more_btn");
//        } else {
			 // wrap_content를 .board-list 요소에 추가
            $(".board-list").append(wrap_content);
             console.log("board-list");
             //더보기 비활성화
             // 추가된 항목의 개수를 diarymoreCount에 저장
             // diarymoreCount = $(".wrap_content").find().length; 
//        }
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
    
    