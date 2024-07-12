
function enterkey() {
	if (window.event.keyCode == 13) {
    	// 엔터키가 눌렸을 때
    	searchBtnHandler();
    }
}
var currentPage = 1;
let searchMem = null;
let sort = null;
let count = 1;


/* 페이징 이동 함수 */
function goPageHandler(thisElement) {
			var targetPage = $(thisElement).data("targetpage");
			var sortCount =  count % 2;
			$.ajax({
				beforeSend : csrfHandler,
				error : ajaxErrorHandler,
				url:contextPath+"admin/member/search"
				, method : "post"
				, data : {
						searchMem : searchMem,
						page : targetPage,
						sort:sort,
						sortCount:sortCount
						}
				}).done(function(a){
				if(a){
					$(".wrap-list").replaceWith(a);
				}
			});
	}

/*검색+페이징1*/
function searchBtnHandler(){
	var targetPage = $().data('targetpage');
	var searchMem = $("[name=search]").val().trim();
	var sortCount =  count % 2;
	$.ajax({
		beforeSend : csrfHandler,
		error : ajaxErrorHandler,
		url:contextPath+"admin/member/search",
		 method:"post",
		 data: {
			searchMem:searchMem
		 	, page: targetPage, 
		 	sort:sort,
		 	sortCount:sortCount
			 },
		 success : function(searchList) {
			$('.wrap-list').replaceWith(searchList);
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
/* 닉네임 정렬*/
function ClickNickHandler(){
	sort='nick'
	var searchMem = $("[name=search]").val().trim();
	var sortCount =  count % 2;
	$.ajax({
		beforeSend : csrfHandler,
		error : ajaxErrorHandler,
		url: contextPath+"admin/member/search",
		data:{
			page: currentPage, 
			searchMem: searchMem, 
			sort: sort,
			sortCount:sortCount
		},
		 method:"post",
	}).done(function(data){
		if(data){
			$('.wrap-list').replaceWith(data);
			count += 1;
		}
	});
} 


