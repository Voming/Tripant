var clicknum;

function moreBtnClickHandler(thisElement){
	clicknum += 1;
	
	$.ajax({
		url: contextPath + "my"+"diary"
		, method: "post"
		, data: {
				
				clickNum: clicknum
			}
			, context: this
			, error: ajaxErrorHandler
		}).done(function(wrap_content) {
			$(".wrap-d-content").replaceWith(wrap_content);
		})
	}
