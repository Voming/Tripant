$(loadedHanlder);

function loadedHanlder(){
	var url = window.location.pathname;
	var param = window.location.search;
	console.log("==??"+url);
	$.ajax({
		url: url+"/info",
		method:"post",
		dataType:"json",
		success : function(dayEntityList) {
	        dayEntityList_org = dayEntityList;
			setEvent();
        }
	});
}

function setEvent(){
	//화면에 뿌릴 장소 정보 백틱으로 한번에 담아오기
	displayInfo();

	//장소별 머무는 시간 출력
	staytimeHandler();

	//map 화면 출력
	displayMap();
	//setBounds();
	
	//일차별 동그라미 색 변경
	circleColorHandler();
	


	//드래그 앤 드랍
	dragAndDrop();

	//편집
	$(".edit").click(editHandler);
	//편집 취소
	$(".cancel").click(cancelHandler);
	//편집 저장
	$(".save").click(saveHandler);
	//hover 시 메모내용 표시
	$(".img-memo").hover(memoHandler,memoNoHandler);

	//좌측 탭
	//TODO 안 됨
	$(".dayn").click(function(){
		$('.dayn a').css("color", "black");
		$('.dayn a').removeClass('active');
		$(this).addClass('active');
		
		$(this).css("color", "#4BC9E5");
		return false;
	}).filter(':eq(0)').click();
	
	
	//test
	$(".test-btn").click(durationHandler);
}
