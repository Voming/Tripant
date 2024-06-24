var clicknum = 0; // 값초기화

function moreBtnClickHandler(thisElement){
	clicknum += 1;
	
	$.ajax({
		url:"/my/diary/more"
		, method: "post"
		, data: {
				clickNum: clicknum
			}
			, context: this
			, error: ajaxErrorHandler
		}).done(function(wrap_content) {
			//$(".wrap-d-content").replaceWith(wrap_content);
			$(".board-list").append(wrap_content);
		})
	}
	/*
11:53:29.018 [33;1m- WARN[m [36m.h.AbstractHandlerExceptionResolver[m:Resolved 
[org.springframework.web.method.annotation.MethodArgumentTypeMismatchException: 
Failed to convert value of type 'java.lang.String' to required type 'java.lang.Integer'; 
For input string: "NaN"]
*/