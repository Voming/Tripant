// 배열을 초기화
function initializeSessionArr() {
    sessionArr = [];
}
//sessionStorage값 배열에 담기
function saveSessionArr(){
	//세션스토리지 길이
	sessionLength = editStorage.length;
	
	for(var i = 0 ; i<sessionLength ; i++){
		let obj = JSON.parse(editStorage.getItem(i));
		
		idx = obj.tripIdx;
		jdx = obj.jdx ;
		sessionArr[idx][jdx] = obj;
	}
}

//sessionStorage값 display하기
function displayEditInfo(){
	
	saveSessionArr();
	var htmlval = "";
	
	for(var i=0; i<sessionArr.length; i++ ){
	
		htmlval=`
				<div th:each="details,daylist:${detailList}" class="column flex" th:data-columns="${daylist.count}" th:id="'#tab'+${daylist.count}" th:with="jdx=0">
					<div class="sub-title flex ">
						<h4 th:text="${daylist.count} + '일차'" class="nday"></h4>
						<h6 th:text="${details.travelDate}" class="date"></h6>
					</div>
				 	<div  class="container flex wfull">
				 	`;
				 	
				 	for(var j=0; j< sessionArr[i].length; j++ ){
						
					}
					
		htmlval=`
					</div> <!-- container  -->
				</div>	<!-- column -->
				`;
	}/*반복문 종료(i)*/
	
	//장소정보 넣기
	$(".edit-tourlist .wrap-detaillist.flex").html(htmlval);
}






