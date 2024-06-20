$(loaededHandler);
function loaededHandler(){
	//검색
	$('.btn-search').on("click",searchHandler);
}

//검색
function searchHandler(){
	var searchItem = $("[name=search]").val().trim();
	$.ajax({
		url:"/admin/goods/search",
		 method:"post",
		 data: {searchItem:searchItem},
		 success : function(result) {
			 ItemListHandler(result)
				},
	 error : function(request, status, error) {
				alert("code: " + request.status + "\n"
						+ "message: " + request.responseText + "\n"
						+ "error: " + error);
			}
		
	});
}

function ItemListHandler(searchList){
	var htmlVal = '';
	for (var idx in searchList){
		var item = searchList[idx];
		htmlVal+=`
			<ul th:each="item : ${itemList}" class="col list" th:data-item-code="${item.ITEM_CODE}">
				<li th:text="${item.ITEM_CODE}" ></li>
				<li th:text="${item.ITEM_NAME}"></li>
				<li th:text="${item.ITEM_PRICE_CHAR}"></li>
				<li th:text="${item.ITEM_DUR}" th:if="${item.ITEM_DUR} != null"></li>
				<li th:if="${item.ITEM_DUR} == null"></li>
				<li th:text="${item.ITEM_SALE}" th:if="${item.ITEM_SALE} != null"></li>
				<li th:if="${item.ITEM_SALE} == null"></li>
				<li><button type="button" class="btn update">수정</button> <button type="button" class="btn delete">삭제</button> </li>
			</ul>
			`;
	}
}