

// 시간 설정 칸 열림
function timeRangeBtnClickHandler(thisElement) {
	$(thisElement).children(".timerange").addClass('hide');
	$(thisElement).parents('.spot-block').children(".timerange-modal").removeClass('hide');
}

// 시간 설정 완료
function timeDoneBtnClickHandler(thisElement) {
	$(thisElement).parents('.spot-block').children(".timerange").removeClass('hide');
	$(thisElement).parents(".timerange-modal").addClass('hide');
	var idx=$(thisElement).parents('.spot-block').data('i');
	var jdx=$(thisElement).parents('.spot-block').data('j');
	
	
	var hours = $(thisElement).parent().children('.spot-hours').val();
	var mins = $(thisElement).parent().children('.spot-mins').val();
	var key = $(thisElement).parents('.spot-block.draggable').data('sessionkey');
	
	var timeVal = hours*3600 + mins*60;
	var temp = JSON.parse(editStorage.getItem(key));
	
	detailListEditMode[idx].dayDetailInfoEntity[jdx].stayTime = timeVal;
	
	// 수정된 객체를 다시 JSON 문자열로 변환하여 sessionStorage에 저장
	editStorage.setItem(key, JSON.stringify(temp));
	
	displayEditModeAfterDragEnd();
	circleColorHandler();
}

//memo 이미지에서 마우스가 벗어났을 때
function memoNoHandler(){
	$(this).siblings('.memo').addClass('hide');
}

//memo 내용을 추가할 모달창 생성 memo관련 함수 async필수
async function memoClickHandler(el){
    // 'memo' 요소의 텍스트를 가져옵니다.
    memoText = $(el).siblings('.memo').text();
    console.log("text");
    console.log($(el));
    console.log($(el).next('.memo'));
    console.log(memoText);
    // Swal.fire 다이얼로그를 표시하고 사용자의 입력을 기다린다
    const { value: memo } = await Swal.fire({
        input: "textarea",
        inputLabel: "메모작성",
        inputValue: memoText,
        inputPlaceholder: "여행에 필요한 정보를 이곳에 작성해보세요. 최대 900자",
        inputAttributes: {
            maxlength: "900"
        },
        showCancelButton: true,
        confirmButtonColor: "#000000", 
        confirmButtonText: "확인"
    });
    if (memo && memo.trim().length > 0) {
        Swal.fire("<h1>저장완료</h1>");
    }
}



function displayEditMode(){
	
//	saveSessionArr(); //sessionStorage값 가져오기
//	console.log("sessionArr");
//	console.log(sessionArr);
	var htmlval = "";
	
	//이동시간 변수
//	var duration ="";
//	var prevDuration ="";
	
	console.log("========1e");
	console.log(detailList);
 	detailListEditMode= JSON.parse(JSON.stringify(detailList));
	
	for(var i=0; i<detailListEditMode.length; i++ ){
		//DayEntity를 list에 담기
		details =  detailListEditMode[i];
		// <<<<<<<<<<<<<<< 백틱
		//좌측 탭 태그 넣기
		htmlval+=`
				<div class="column flex" data-columns="${i+1}" id="tab${i+1}">
					<div class="sub-title flex ">
						<h4 class="nday">${i+1}일차</h4>
						<h6  class="date">${details.travelDate}</h6>
					</div>
				 	<div  class="container flex wfull">
				 	`;
		// >>>>>>>>>>>>>>>>>> 백틱
		
	 	//장소별 정보 넣기
		var daylength = details.dayDetailInfoEntity.length;
		var points = new Array(daylength); // 하루동안 장소들 지도에 표시될 위치 // 초기화
	 	for(var j=0; j< daylength; j++ ){
			
			info =  details.dayDetailInfoEntity[j];
			
			//머무는 시간 바꾸기
			let[hours, minutes] = secToHoursAndMin(info.stayTime);
			/*
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
*/
			
			// <<<<<<<<<<<<<<< 백틱
			//백틱에 값 넣기
			htmlval+=`
			<div class="spot-block draggable"  draggable ="true" data-i="${i}", data-j="${j}">
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
				
				
			//info spot div 정제해서 넣기 //장소정보	
			htmlval+=`
			 	<div class="spot grid wfull" >
			 		<div class="spot-number backimg"><p>${j+1}</p></div>
			 		<div class="spot-staytime" onclick="timeRangeBtnClickHandler(this);" data-startTime="${info.startTime}" data-endTime="${info.endTime}">
			 			<p class="timerange">${info.startTime} - ${info.endTime}</p>
			 		</div>
			 		
			 		<div class="spot-type">명소</div>
			 		<div class="spot-title wfull"> ${info.title}</div>
			 		<div class="spot-memo"><img class="img-memo" onclick="memoClickHandler(this);" style="width: 20px;height:20px;" src="/images/icons/memoIcon.png" ><span  class="memo">${info.memo}</span></div>
			 		
			 		<!-- 이미지 X-->
			 		
			 		<div class="spot-caricon"><img style="width:20px;height: 20px;" src="/images/icons/carIcon.png" /></div>`;
			
			//이동시간 표시 및 자동차 아이콘 표시 
			//마지막  상소일 경우 이동시간 hide
			if( (j+1) < daylength){
				htmlval+=`<div class="spot-move"> ${info.durationMin}분> </div>`;
			}else{
				htmlval+=`<div class="spot-move hide"> ${info.durationMin}분> </div>`;
			}
			
			htmlval+=`<!-- x 버튼 -->
					<button class="spot-setting" > 
						<img class="wfull img trash-bin "  src="/images/icons/cancel.png"/>
					</button>
			    </div><!-- spot  -->
			`;	
				
			//spot-block 닫기	
			htmlval+=`</div> <!--spot-block  -->`;
			// >>>>>>>>>>>>>>>>> 백틱
		} /* 반복문 종료(j)*/
					
		htmlval+=`	
					</div> <!-- container  -->
				</div>	<!-- column -->
				`;
		// >>>>>>>>>>>>>>>>> 백틱
	}//반복문 종료(i)
	
	//장소정보 넣기
	$(".edit-tourlist .wrap-detaillist.flex").html(htmlval);
}


function displayEditModeAfterDragEnd(){
	
//	saveSessionArr(); //sessionStorage값 가져오기
//	console.log("sessionArr");
//	console.log(sessionArr);
	var htmlval = "";
	
	//이동시간 변수
//	var duration ="";
//	var prevDuration ="";
	
	console.log("========1e");
	console.log(detailListEditMode);
	
	for(var i=0; i<detailListEditMode.length; i++ ){
		//DayEntity를 list에 담기
		details =  detailListEditMode[i];
		// <<<<<<<<<<<<<<< 백틱
		//좌측 탭 태그 넣기
		htmlval+=`
				<div class="column flex" data-columns="${i+1}" id="tab${i+1}">
					<div class="sub-title flex ">
						<h4 class="nday">${i+1}일차</h4>
						<h6  class="date">${details.travelDate}</h6>
					</div>
				 	<div  class="container flex wfull">
				 	`;
		// >>>>>>>>>>>>>>>>>> 백틱
		
	 	//장소별 정보 넣기
		var daylength = details.dayDetailInfoEntity.length;
		var points = new Array(daylength); // 하루동안 장소들 지도에 표시될 위치 // 초기화
	 	for(var j=0; j< daylength; j++ ){
			
			info =  details.dayDetailInfoEntity[j];
			
			//머무는 시간 바꾸기
			let[hours, minutes] = secToHoursAndMin(info.stayTime);
			/*
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
*/
			
			// <<<<<<<<<<<<<<< 백틱
			//백틱에 값 넣기
			htmlval+=`
			<div class="spot-block draggable"  draggable ="true" data-i="${i}", data-j="${j}">
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
				
				
			//info spot div 정제해서 넣기 //장소정보	
			htmlval+=`
			 	<div class="spot grid wfull" >
			 		<div class="spot-number backimg"><p>${j+1}</p></div>
			 		<div class="spot-staytime" onclick="timeRangeBtnClickHandler(this);" data-startTime="${info.startTime}" data-endTime="${info.endTime}">
			 			<p class="timerange">${info.startTime} - ${info.endTime}</p>
			 		</div>
			 		
			 		<div class="spot-type">명소</div>
			 		<div class="spot-title wfull"> ${info.title}</div>
			 		<div class="spot-memo"><img class="img-memo" style="width: 20px;height:20px;" src="/images/icons/memoIcon.png" ><span class="memo">${info.memo}</span></div>
			 		
			 		<!-- 이미지 X-->
			 		
			 		<div class="spot-caricon"><img onclick="memoClickHandler(this);"  style="width:20px;height: 20px;" src="/images/icons/carIcon.png" /></div>`;
			
			//이동시간 표시 및 자동차 아이콘 표시 
			//마지막  상소일 경우 이동시간 hide
			if( (j+1) < daylength){
				htmlval+=`<div class="spot-move"> ${info.durationMin}분> </div>`;
			}else{
				htmlval+=`<div class="spot-move hide"> ${info.durationMin}분> </div>`;
			}
			
			htmlval+=`<!-- x 버튼 -->
					<button class="spot-setting" > 
						<img class="wfull img trash-bin "  src="/images/icons/cancel.png"/>
					</button>
			    </div><!-- spot  -->
			`;	
				
			//spot-block 닫기	
			htmlval+=`</div> <!--spot-block  -->`;
			// >>>>>>>>>>>>>>>>> 백틱
		} /* 반복문 종료(j)*/
					
		htmlval+=`	
					</div> <!-- container  -->
				</div>	<!-- column -->
				`;
		// >>>>>>>>>>>>>>>>> 백틱
	}//반복문 종료(i)
	
	//장소정보 넣기
	$(".edit-tourlist .wrap-detaillist.flex").html(htmlval);
}
