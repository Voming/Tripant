$(loadedHandler);

function loadedHandler() {
	console.log("열림");
	$(".btn.find").on("click", btnFindClickHandler);
}


//지역 검색
function btnFindClickHandler() {
	var findArea = $("[name=find]").val().trim();
	
	if (findArea.length == 0) {
		alert("빈문자열만 입력할 수 없습니다. 검색할 지역명을 입력해주세요.");
		return;
	}

	$.ajax({
		url: "find"
		, method: "post"
		, data: {findArea : findArea}
		, dataType: 'json'
		, success: function(result) {
			console.log(result);
		}
		, error: ajaxErrorHandler
	});
}