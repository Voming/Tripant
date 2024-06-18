/* 시간 관련 js 설정*/
function staytimeHandler(){
	
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
	
	
	
	$('.spot.grid.wfull').each(function(){
		console.log(">>>>>> $(this).data('spot-order')");
		console.log($(this).data('spot-order'));
		console.log(">>>>>> [[${info}]]");
		console.log($(this).data('info'));
		const memoMatch = $(this).data('info').match(/stayTime=([^,]+)/);
		console.log(memoMatch[1]);
	
		let spotOrder = $(this);
		
		if(spotOrder.data('spot-order') ==1){
			let startTime = spotOrder;
			//let endTime = addTime();
		}
	});
}