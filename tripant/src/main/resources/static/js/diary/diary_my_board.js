var clicknum = 0; // 값초기화

function moreBtnClickHandler(thisElement){
	clicknum += 1;
	const token = $("meta[name='_csrf']").attr("content");
	const header= $("meta[name='_csrf_header']").attr("content");
	$.ajax({
		url: contextPath + "/my/diary/more"
		,beforeSend : function(xhr){
			/* 데이터를 전송하기 전에 헤더에 csrf값을 설정 */
			xhr.setRequestHeader(header,token);
			}
		, method: "post"
		, data: {
				clickNum: clicknum
			}
			, context: this
			, error: ajaxErrorHandler
		}).done(function(wrap_content) {
			//$(".wrap-diary").replaceWith(wrap_content);
			$(".board-list").append(wrap_content);
		})
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