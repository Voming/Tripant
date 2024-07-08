
let currentPage = 1;
let pick = null;
let search = null;
let sort = null;

/* 페이징 이동 함수 */
function goPageHandler(thisElement) {
			currentPage = $(thisElement).data("targetpage");
			$.ajax({
				beforeSend : csrfHandler,
				error : ajaxErrorHandler,
				url:contextPath+"admin/keyword"
				, method : "post"
				, data : {
					currentPage: currentPage, 
					pick: pick, 
					search: search, 
					sort: sort
				}
				// , dataType : "json"
				/*
				, success : function(result){
					LikeHandler(like);
				}
				*/
			}).done(function(a){
				if(a){
					$(".wrap-list").replaceWith(a);
				}
			});
	}


//좋아요수 정렬
function ClickLikeHandler(){
	// currentPage = $(thisElement).data('targetpage');
	sort = 'likes';
	$.ajax({
		beforeSend : csrfHandler,
		error : ajaxErrorHandler,
		url: contextPath+"admin/keyword",
		data:{
			currentPage: currentPage, 
			pick: pick, 
			search: search, 
			sort: sort
		},
		 method:"post",
	}).done(function(data){
		if(data){
			$('.wrap-list').replaceWith(data);
		}
	});
} 
function LikeHandler(like){
	var htmlVal = '';
	for (var idx in like){
		var memBoard = like[idx];
		htmlVal+=`
			<ul class="col list">
				<li>${memBoard.diaryId}</li>
				<li>${memBoard.diaryTitle}</li>
				<li>${memBoard.memNick}</li>
				<li>${memBoard.diaryDate}</li>
				<li>${memBoard.diaryViews}</li>
				<li>${memBoard.likes}</li>
				<li></li>
			</ul>
			`;
	}
	return htmlVal;
}

//조회수 정렬
function ClickViewHandler(){
	// currentPage = $(thisElement).data('targetpage');
	sort = 'view';
	$.ajax({
		beforeSend : csrfHandler,
		data:{
			currentPage: currentPage, 
			pick: pick, 
			search: search, 
			sort: sort
		},
		error : ajaxErrorHandler,
		url:contextPath+"admin/keyword",
		 method:"post",
	}).done(function(data){
		if(data){
			$('.wrap-list').replaceWith(data);
		}
	});
} 
function ViewHandler(view){
	var htmlVal = '';
	for (var idx in view){
		var memBoard = view[idx];
		htmlVal+=`
			<ul class="col list">
				<li>${memBoard.diaryId}</li>
				<li>${memBoard.diaryTitle}</li>
				<li>${memBoard.memNick}</li>
				<li>${memBoard.diaryDate}</li>
				<li>${memBoard.diaryViews}</li>
				<li>${memBoard.likes}</li>
				<li></li>
			</ul>
			`;
	}
	return htmlVal;
}

/* 페이징 이동 함수 */
/*
function goPageHandler() {
			var currentpage = $(this).data("targetpage");
			$.ajax({
				beforeSend : csrfHandler,
				error : ajaxErrorHandler,
				url:contextPath+"admin/keyword"
				, method : "get"
				, data : {
					currentPage: currentPage, 
					pick: pick, 
					search: search, 
					sort: sort
				}
				, dataType : "json"
				, success : function(result){
					if(result.search){
						$("[name=search]").val(result.search);
					}
					memListHandler(result);
				}
			});
	}
*/

// 검색
function searchBtnHandler(thisElement){
	pick=$("select[name=option] option:selected").val(); //선택한 option val값 
	search = $("[name=search]").val();  //input 값
	
	$.ajax({
		beforeSend : csrfHandler,
		error : ajaxErrorHandler,
		url:contextPath+"admin/keyword",
		method:"post",
		data: {
			currentPage: currentPage, 
			pick: pick, 
			search: search, 
			sort: sort
		},
		success : function(searchList) {
				$('.wrap-list').replaceWith(searchList);
			}
	});
}

function memListHandler(searchList){
	var htmlVal = '';
	for (var idx in searchList){
		var memBoard = searchList[idx];
		htmlVal+=`
			<ul class="col list">
				<li>${memBoard.diaryId}</li>
				<li>${memBoard.diaryTitle}</li>
				<li>${memBoard.memNick}</li>
				<li>${memBoard.diaryDate}</li>
				<li>${memBoard.diaryViews}</li>
				<li>${memBoard.likes}</li>
				<li></li>
			</ul>
			`;
	}
	return htmlVal;
}