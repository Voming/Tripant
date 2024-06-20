$(loaededHandler);
function loaededHandler(){
	//찾기 버튼
	$('.btn-search').on("click",searchHandler);
	// 결제 취소 버튼
	$(".btn.cancel").on("click", payCancelHandler);
}

// 결제 취소
async function payCancelHandler(){
	const buyId = $(this).parents(".col.list").data("id").toString();
	const memEmail = $(this).parents(".col.list").data("email");
	const payCancel = await Swal.fire({
		title: "해당 주문번호의 모든 건이 취소됩니다.\n진행하시겠습니까?", 
		icon: "warning", 
		showCancelButton: true, 
		confirmButtonText: "결제 취소", 
		confirmButtonColor: "#ff0000", 
		cancelButtonText: "돌아가기", 
		cancelButtonColor: "#000000"
	});
	if(payCancel.isConfirmed){
		$.ajax({
			url: contextPath + "admin/cancel", 
			type: "post", 
			data: {
				buyId: buyId, 
				memEmail: memEmail 
			}, 
			error: ajaxErrorHandler, 
			success: async function(data){
				if(data > 0){
					const payCancelSuccess = await Swal.fire({
						title: "결제 취소가 완료되었습니다.", 
						icon: "success", 
						confirmButtonColor: "#000000", 
						confirmButtonText: "확인"
					});
					if(payCancelSuccess.isConfirmed){
						location.reload();
					}
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
}

//회원검색
function searchHandler(){
	var searchMem = $("[name=search]").val().trim();
	$.ajax({
		url:"/admin/member/search",
		method:"post",
		data: {searchMem:searchMem},
		success : function(result) {
			 memListHandler(result)
				},
	 	error : ajaxErrorHandler
	});
}

function memListHandler(searchList){
	var htmlVal = '';
	for (var idx in searchList){
		var map = searchList[idx];
		htmlVal+=`
				<ul class="col list" th:each="map : ${list}" th:data-id="${map.BUY_ID}" th:data-email="${map.MEM_EMAIL}">
							<li th:text="${map.BUY_ID}"></li>
							<li th:text="${map.MEM_NICK}"></li>
							<li th:text="${map.MEM_EMAIL}"></li>
							<li th:text="${map.ITEM_NAME}"></li>
							<li th:text="${map.BUY_DATE}"></li>
							<li>
								<button type="button" class="btn cancel">결제취소</button>
								<input type="hidden" th:value="${map.ITEM_CODE}">
							</li>
						</ul>
			`;
	}
} 