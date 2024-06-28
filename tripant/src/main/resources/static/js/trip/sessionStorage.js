// 배열을 초기화
function initializeSessionArr() {
    sessionArr = [];
}
//insertion sort - 삽입정렬
function insertionSort(arr ,leng){
	var i, j ,key;
	
	for(var i = 1 ; i<leng ; i++){
		key=arr[i];

		for(var j = i-1; j>=0 && arr[j]>key ;j--){
			arr[j+1 ] = arr[j];
		}
		arr[j+1] = key;
	}
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

//sessionStorage값 display하기 - 편집 클릭했을 때 화면
function displayEditInfo(){
	
	saveSessionArr();//sessionStorage값 가져오기
	console.log("sessionArr");
	console.log(sessionArr);
	var htmlval = "";
	
	//이동시간 변수
	var duration ="";
	var prevDuration ="";
	
	for(var i=0; i<sessionArr.length; i++ ){
		//값 정리해서 넣기
		htmlval+=`
				<div class="column flex" data-columns="${i+1}" th:id="'#tab'+${sessionArr[i].tripIdx+1}">
					<div class="sub-title flex ">
						<h4 th:text="${i+1} + '일차'" class="nday"></h4>
						<h6 th:text="${sessionArr[i].travelDate}" class="date"></h6>
					</div>
				 	<div  class="container flex wfull">
				 	`;
				 	
				 	//장소별 정보 넣기
				 	for(var j=0; j< sessionArr[i].length; j++ ){
						
						spot = sessionArr[i][j];
						//머무는 시간 바꾸기
						let[hours,minutes] =secToHoursAndMin(spot.stayTime);
						
						//다음 장소로 이동시간(sec), 분단위로 변환하여 변수에 담기 
						var durationMin; //이동시간 추후 사용
						if((spot.jdx+1) < sessionArr[i].length){
							duration = durationHandler(spot.lng,spot.lat,sessionArr[i][j+1].lng,sessionArr[i][j+1].lat);
							durationMin=Math.ceil(duration/60);
						}	
						
						//머무는 시간 계산하기 ex) 10:00 - 11:00
						//1번째 장소
						if(j == 0){
							startTime =sessionArr[i][j].travelStart;
							endTime = addTime(startTime,spot.stayTime);
						//2~n-1번째 장소	
						}else if(0 < j && j < sessionArr[i].length-1){
							startTime =  addTime(endTime,prevDuration);
							endTime = addTime(startTime,spot.stayTime);
						//n번째 장소(숙소)	
						}else{
							startTime =  addTime(endTime,prevDuration);
							endTime = 	sessionArr[i][j].travelEnd;
						}
						
						//j번째 장소에서 다음 장소(j+1)로 이동하는데 걸리는 시간 변수에 담기 
						//prevDuration은 j+1의 도착시각을 계산할 때 사용됨 ex) 11:30-12:00에서 11:30 부분
						prevDuration = duration;

						
						htmlval+=`
						<div class="spot-block draggable"  draggable ="true" data-idx="${i}" data-jdx="${j}" data-sessionKey="${i}-${j}">
							<div class="timerange-modal hide" >
								<div><p style="margin-left:30px; font-weight: bold; padding: 10px 0;">머무는 시간 설정</p></div>
								<div class="flex ">
									<input class="spot-hours" type='number' value=${hours} min="0" max="23"></input>
									<p>시간</p>
									<input class="spot-mins" type='number' value=${minutes} min="0" max="55" step="5"></input>
									<p>분</p>
									<p class="timerange-done" onclick="timeDoneBtnClickHandler(this);">완료</p>
								</div>
							</div>`;
							
							
						//spot div 정제해서 넣기 //장소정보	
						htmlval+=`
						 	<div class="spot grid wfull" >
						 		<div class="spot-number backimg"><p>${j+1}</p></div>
						 		<div class="spot-staytime" onclick="timeRangeBtnClickHandler(this);" data-startTime="${startTime}" data-endTime="${endTime}">
						 			<p class="timerange">${startTime} - ${endTime}</p>
						 		</div>
						 		
						 		<div class="spot-type">명소</div>
						 		<div class="spot-title wfull"> ${spot.title}</div>
						 		<div class="spot-memo"><img class="img-memo" style="width: 20px;height:20px;" src="/images/icons/memoIcon.png" ><span th:text="${info.memo}" class="memo"></span></div>
						 		
						 		<!-- 이미지 X-->
						 		
						 		<div class="spot-caricon"><img style="width:20px;height: 20px;" src="/images/icons/carIcon.png" /></div>`;
						
						//마지막  상소일 경우 이동시간 hide
						if(j+1 < sessionArr[i].length){
						
						htmlval+=`<div class="spot-move"> ${durationMin}분> </div>`;
						
						}else{
						
						htmlval+=`<div class="spot-move hide"> ${durationMin}분> </div>`;
						}
						
						htmlval+=`<!-- x 버튼 -->
								<button class="spot-setting" > 
									<img class="wfull img trash-bin "  src="/images/icons/cancel.png"/>
								</button>
						    </div><!-- spot  -->
						`;	
							
						//spot-block 닫기	
						htmlval+=`</div> <!--spot-block  -->`;
					}
					
		htmlval+=`	
					</div> <!-- container  -->
				</div>	<!-- column -->
				`;
	}//반복문 종료(i)
	
	//장소정보 넣기
	$(".edit-tourlist .wrap-detaillist.flex").html(htmlval);
}






