$(loadedHandler);

function loadedHandler() {
	$(".btn.find").on("click", btnFindClickHandler);

	$(".btn.make").on("click", btnMakeClickHandler);
	$("#selectbox").on("click", changeSelectAreaHandler);
}

//모달 열기 ========================
function btnMakeClickHandler() {
	var auth = $(".auth").attr("value");

	if (!auth) {
		alert("로그인을 한 후에 이용해주세요.");
	} else  {
		$(".modal").addClass("show");

		$(".keep_btn").attr("disabled", true);
		//일정 계속 만들기 활성화
		$("#planForm input").on('input', function() {
			if ($("#planForm input").val() == '')
				$(".keep_btn").prop("disabled", true);
			else
				$(".keep_btn").prop("disabled", false);
		});
		//일정 계속 만들기 
		$(".keep_btn").on("click", btnKeepClickHandler);
	}
}


// 외부영역 클릭 시 모달 닫기
$(document).mouseup(function(e) {
	var LayerModal = $(".modal");
	if (LayerModal.has(e.target).length === 0) {
		LayerModal.removeClass("show");
	}
});

// 지역 선택시 값 변경-------------------------
function changeSelectAreaHandler() {
	var area = $("#selectbox option:selected").attr('value');
	
	if (area.length < 3) {
		$.ajax({
			url: contextPath + "plan/area"
			, method: "post"
			, data: {
				areaCode: area
			}
			, dataType: 'json'
			, success: function(result) {
				if (result != null) {
					/*console.log(result);*/
					displayAreaInfo(result);
				}
			}
		});
	}
}

// 지역 선택시 값 넣기 TODO
function displayAreaInfo(datalist) {
	if (datalist[0] != null) {
		var areaDto = datalist[0];
		var aName = areaDto.areaEngName;
		$("#planForm h2").text(aName);
		var aExplain = areaDto.areaExplain;
		$("#planForm h4").text(aExplain);
		$("#infoImg")[0].src = contextPath + "images/area/" + areaDto.areaFileName;
	}
}

//일정 게속 만들기
function btnKeepClickHandler(){
	/*var area = $("#selectbox option:selected").text();
	console.log(area);
	var title =  $(this).parent().find("input[name=planTitle]").val();
	console.log(title);*/
	planForm.action= contextPath +"plan/keep";
	planForm.method="post";
	planForm.submit();
}



//지역 검색 ================================
function btnFindClickHandler() {
	var findArea = $("[name=find]").val().trim();

	if (findArea.length == 0) {
		alert("빈문자열만 입력할 수 없습니다. 검색할 지역명을 입력해주세요.");
		return;
	}

	$.ajax({
		url: contextPath + "find/area"
		, method: "post"
		, context:this
		, data: { findArea: findArea }
		//, dataType: 'json'
		, error: ajaxErrorHandler
	}).done(function(wrap_area) {
		 $(this).parents(".wrap-area").find(".wrap-areaList").replaceWith(wrap_area);
	});
}