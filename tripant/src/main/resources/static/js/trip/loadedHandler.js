$(loadedHanlder);
function loadedHanlder(){
	//편집
	$(".edit").click(editHandler);
	//편집 취소
	$(".cancel").click(cancelHandler);
	//편집 저장
	$(".save").click(saveHandler);
	//hover 시 메모내용 표시
	$(".img-memo").hover(memoHandler,memoNoHandler);
	//드래그 앤 드랍
	dragAndDrop();
	//일차별 동그라미 색 변경
	circleColorHandler();
	//test용
	$(".mapbtn").click(panTo);
	$(".test-btn").click(durationHandler);
}