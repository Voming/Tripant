$(loadedHanlder);


//장소 정보를 담을 객체 생성
//constructor : 값이 null이어도 해당 key의 공간을 확보해둠
/*class SpotInfo {
    constructor() {
        this.planId = null;
        this.day = null;
        this.stayTime = null;
        this.travelOrder = null;
        this.title = null;
        this.memo = null;
        this.lat = null;
        this.lng = null;
        this.startTime = null;
        this.endTime = null;
    }
}*/


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

	//map 화면 출력
	displayMap();
	
	//편집페이지 정보 로드	
	//displayEditInfo();
	//드래그 앤 드랍
	//dragAndDrop();
	//편집 취소
	//$(".cancel").click(cancelHandler);
	//편집 저장
	//$(".save").click(saveHandler);

	
	//일차별 동그라미 색 변경
	circleColorHandler();
	
	//좌측 탭 이벤트 설정
	navHandler();
	

	//편집
	$(".edit").click(editHandler);
	//hover 시 메모내용 표시
	$(".img-memo").hover(memoHandler,memoNoHandler);

}
