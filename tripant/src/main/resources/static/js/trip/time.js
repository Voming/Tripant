let dayEntityList_org;

function unescapeHtml(str) {
 if (str == null) {
  return "";
 }
 return str
   .replace(/&amp;/g, '&')

   .replace(/&lt;/g, '<')

   .replace(/&gt;/g, '>')

   .replace(/&quot;/g, '"')

   .replace(/&#039;/g, "'")

   .replace(/&#39;/g, "'");

}
/* 시간 관련 js 설정*/
function staytimeHandler(){
	console.log("=========2");
	console.log(dayEntityList_org);

	//시간 더하기
	function addTime(time, stayTime){
		//앞의 시간 11:00 을 :을 기준으로 시와 분으로 담기
		let[hours,minutes] = time.split(':').map(number);
		// 분단위로 바꿔서 도착시간과 머무는 시간 합하기
		let totalMinutes = hours*60 +minutes*1 +Math.floor(stayTime/60);addTime
		// 도착시간 + 머무는시간 = 떠나는 시간 구하기
		let finalHours = Math.floor(totalMinutes/60);
		let finalMinutes = totalMinutes%60;
		
		return `${String(finalHours).padStart(2, '0')}:${String(finalMinutes).padStart(2, '0')}`;
	}
	
var detailList=dayEntityList_org;
var htmlval = "";
for(var i=0; i<detailList.length; i++ ){
	details =  detailList[i];
	var count = i+1;
	//var dateStr = details.travelDate;
	htmlval += `
	<div class="column flex" data-columns="${count}" id="'#tab'+${count}">
		<div class="sub-title flex ">
			<h4 class="nday">${count}일차</h4>
			<h6  class="date">${details.travelDate}</h6>
		</div>
		`;
		for(var j=0; j< details.dayDetailInfoEntity.length; j++ ){
			info =  details.dayDetailInfoEntity[j];
			var infoCount = j+1;
			htmlval += `
		 	<div class="container flex wfull">
			 	<div class="spot grid wfull" data-spot-order="${infoCount}"  data-stay-time="${info.stayTime}">
			 		<div class="spot-number backimg"><p>${infoCount}</p></div>
			 		<div class="spot-staytime">10:00-11:00</div>
			 		<div class="spot-type">명소</div>
			 		<div class="spot-title wfull">${info.title}</div>
			 		<div class="spot-memo"><img class="img-memo" style="width: 20px;height:20px;" src="/images/icons/memoIcon.png" ><span th:text="${info.memo}" class="memo"></span></div>
			 		<div class="spot-img wfull hfull"><img class=" wfull hfull" src="${info.firstimage}" onerror="this.onerror=null; this.src='/images/icons/spot_sample.png';" ></div>
			 		<div class="spot-caricon"><img style="width:20px;height: 20px;" src="/images/icons/carIcon.png" /></div>
			 		<div class="spot-move"> 45분> </div>
			    </div>
		    </div> 
		    `;
	    }
	htmlval += `
	</div>	
	`;
}
$(".tourlist .wrap-detaillist.flex").html(htmlval);


}
