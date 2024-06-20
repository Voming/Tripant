//체크박스 클릭
function spotCkBtnClickHandler(thisElement){
	 //체크된 리스트 반복 
	 $(".wrap-spotList").find('input:checked').each(function(index){
		var title = $(this).parent().find(".spot-name").attr("value");
		var mapx = $(this).parent().find(".spot-x").attr("value");
		var mapy = $(this).parent().find(".spot-y").attr("value");
		
		console.log(title + mapx + mapy);
		
		//calendarPlan.dateArr[i]
	 });
}
