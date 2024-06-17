$(loadedHanlder);
function loadedHanlder(){
	$(".edit").click(editHandler);
	$(".cancel").click(cancelHandler);
	$(".save").click(saveHandler);
	$(".mapbtn").click(panTo);
	$(".test-btn").click(durationHandler);
	$(".img-memo").hover(memoHandler,memoNoHandler);
	dragAndDrop();
	circleColorHandler();
}