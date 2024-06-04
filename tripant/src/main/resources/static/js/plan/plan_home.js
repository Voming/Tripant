$(loadedHandler);

function loadedHandler() {
	$(".btn.find").on("click", btnFindClickHandler);

	$(".btn.make").on("click", btnMakeClickHandler);
	$("#selectbox").on("click", changeSelectAreaHandler);
}

//모달 열기 ========================
function btnMakeClickHandler() {
	$(".modal").addClass("show");

	$(".keep_btn").attr("disabled", true);
	//일정 계속 만들기 활성화
	$("#planForm input").on('input', function() {
		if ($("#planForm input").val() == '' ||  $("#selectbox option:selected").text()=='--일정을 생성할 지역을 선택하세요--')
			$(".keep_btn").attr("disabled", true);
		else
			$(".keep_btn").attr("disabled", false);
	});
	//일정 계속 만들기 
	$(".keep_btn").on("click", btnKeepClickHandler);
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
	var area = $("#selectbox option:selected").text();
	console.log(area);

	$.ajax({
		url: "make/area"
		, method: "post"
		, data: {
			areaName: area
		}
		, dataType: 'json'
		, success: function(result) {
			if (result != null) {
				/*console.log(result);*/
				displayAreaInfo(result);
			}
		}
		, error: ajaxErrorHandler
	});
}

// 지역 선택시 값 넣기
function displayAreaInfo(datalist) {
	if (datalist[0] != null) {
		var areaDto = datalist[0];
		var aName = areaDto.areaEngName;
		$("#planForm h2").text(aName);
		var aExplain = areaDto.areaExplain;
		$("#planForm h4").text(aExplain);
		$("#infoImg")[0].src = "/images/area/" + areaDto.areaFileName;
	}
}

//일정 게속 만들기
function btnKeepClickHandler(){
	var area = $("#selectbox option:selected").text();
	console.log(area);
	var title =  $(this).parent().find("input[name=plane_title]").val();
	console.log(title);
	
	$.ajax({
		url: "make/keep"
		, method: "post"
		, data: {
			areaName: area,
			planTitle : title
		}
		, success: function(result) {
			if(result != null){
				location.href = result;
				console.log("이동");
			}
		}
		, error: ajaxErrorHandler
	});
}



//지역 검색 ================================
function btnFindClickHandler() {
	var findArea = $("[name=find]").val().trim();

	if (findArea.length == 0) {
		alert("빈문자열만 입력할 수 없습니다. 검색할 지역명을 입력해주세요.");
		return;
	}

	$.ajax({
		url: "find/area"
		, method: "post"
		, data: { findArea: findArea }
		, dataType: 'json'
		, success: function(result) {
			console.log(result);
			displayFindArea(result)
		}
		, error: ajaxErrorHandler
	});
}

//지역 검색 결과 리스트 출력
function displayFindArea(datalist) {
	var htmlVal = '';
	for (var idx in datalist) {
		var findDto = datalist[idx];
		htmlVal += `
				<li>
					<div class="area_btn">
						<img id="findArea-${idx}"  alt="지역">
						<div class="description">
							<p style="font-size: var(--font3);">${findDto.areaName}</p>
							<p style="font-size: var(--font5);">${findDto.areaEngName}</p>
						</div>
					</div>
				</li>`;

	}
	$(".area-box").html(htmlVal);
	//이미지 재배치
	for (var idx in datalist) {
		$("#findArea-" + idx)[0].src = "/images/area/" + datalist[idx].areaFileName;
	}
}
