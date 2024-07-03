$(loaededHandler);
function loaededHandler(){
	//찾기(검색) 버튼
	$('.btn-search').on("click",searchHandler);
	// 결제 취소 버튼
	$(".btn.cancel").on("click", payCancelHandler);
}

// 결제 취소
function payCancelHandler(){
	const buyId = $(this).parents(".col.list").data("id").toString();
	const memEmail = $(this).parents(".col.list").data("email");
	Swal.fire({
		title: "해당 주문번호의 모든 건이 취소됩니다.\n진행하시겠습니까?", 
		icon: "warning", 
		showCancelButton: true, 
		confirmButtonText: "결제취소", 
		confirmButtonColor: "#000000", 
		cancelButtonText: "돌아가기", 
		cancelButtonColor: "#ff0000"
	}).then((swal) => {
		if(swal.isConfirmed){
			$.ajax({
				url: contextPath + "admin/cancel", 
				type: "post", 
				data: {
					buyId: buyId, 
					memEmail: memEmail 
				}, 
				error: ajaxErrorHandler, 
				success: function(data){
					if(data > 0){
						Swal.fire({
							title: "결제 취소가 완료되었습니다.", 
							icon: "success", 
							confirmButtonColor: "#000000", 
							confirmButtonText: "확인"
						}).then((swal) => {
							if(swal.isConfirmed){
								location.reload();
							}
						});
					}else{
						Swal.fire({
							title: "결제 취소 중 오류가 발생했습니다.", 
							icon: "error", 
							confirmButtonColor: "#000000", 
							confirmButtonText: "확인"
						});
					}
				}
			});
		}
	});
}

//회원검색
function searchHandler(){
	var memNick = $("[name=search]").val().trim();
	$.ajax({
		url:"/admin/cancel/search",
		method:"post",
		data: {memNick:memNick},
		success : function(searchList) {
			 $('#list').html(memListHandler(searchList));
				},
	 	error : ajaxErrorHandler
	});
}

function memListHandler(searchList){
	var htmlVal = '';
	for (var idx in searchList){
		var map = searchList[idx];
		htmlVal+=`
				<ul class="col list" th:each="map : ${list}">
							<li>${map.buyId}</li>
							<li>${map.memNick}</li>
							<li>${map.memEmail}</li>
							<li>${map.itemName}</li>
							<li>${map.buyDate}</li>
							<li>
								<button type="button" class="btn cancel">결제취소</button>
								<input type="hidden" value="${map.itemCode}">
							</li>
						</ul>
			`;
	}
	return htmlVal;
} 