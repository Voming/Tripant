$(loaededHandler);
function loaededHandler(){
	$('.btn-search').on("click",searchHandler);
}

function searchHandler(){
	var searchMem = $("[name=search]").val().trim();
	$.ajax({
		url:"/admin/member/search",
		 method:"post",
		 data: {searchMem:searchMem},
		 success : function(searchList) {
			 $('#list').html(memListHandler(searchList));
			 
				},
	 error : function(request, status, error) {
				alert("code: " + request.status + "\n"
						+ "message: " + request.responseText + "\n"
						+ "error: " + error);
			}
		
	});
}

function memListHandler(searchList){
	var htmlVal = '';
	for (var idx in searchList){
		var memList = searchList[idx];
		htmlVal+=`
			<ul class="col list">
				<li>${memList.memNick}</li>
				<li>${memList.memEmail}</li>
				<li>${memList.memJoinDate}</li>
				<li>${memList.memQuitDate}</li>
				<li>${memList.memRole}</li>
				<li><button type="button" class="btn">편집</button></li>
			</ul>
			`;
	}
	return htmlVal;
} 