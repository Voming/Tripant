function addAndRemoveHandler(){
	//유저삭제
	$('.btn.remove').on('click',removeHandler);
	//유저추가
	$('.add').on('click',addHandler);
}
//삭제
function removeHandler(){
	console.log('>>>this');
	console.log($(this));
}
//추가
function addHandler(){
	console.log("!!!!add this");
	console.log($(this));
}