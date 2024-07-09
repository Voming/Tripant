// 장바구니에 담기
function insertItemsHandler(){
	let items = [];
	$('input:checked').each(function(index, element){
		items.push(element.value);
	});
	$.ajax({
		beforeSend : csrfHandler,
		error : ajaxErrorHandler,
		url: contextPath+'store/insert', 
		type: 'post', 
		data: {
			items : items
		}, 
		success: function(data){
			console.log(data);
			if(data === "1"){
				Swal.fire({
					html: "선택하신 상품이 장바구니에 담겼습니다.<br>장바구니로 이동하시겠습니까?", 
					icon: "question", 
					showCancelButton: true, 
					confirmButtonText: "이동하기", 
					confirmButtonColor: "#000000", 
					cancelButtonText: `<p style="color: black;">돌아가기</p>`, 
					cancelButtonColor: "#fff"
				}).then((swal) => {
					if(swal.isConfirmed){
						location.href = contextPath + "store/cart";
					}else{
						location.reload();
					}
				});
			}else if(data === "-1"){
				Swal.fire({
					html: "로그인이 필요한 기능입니다.<br>로그인 페이지로 이동합니다.", 
					icon: "info", 
					confirmButtonColor: "#000000", 
					confirmButtonText: "확인"
				}).then((swal) => {
					if(swal.isConfirmed){
						location.href = contextPath + "login";
					}
				});
			}else{
				Swal.fire({
					html: "장바구니에 상품을 담는 중 오류가 발생했습니다.<br>관리자에게 문의해주시기 바랍니다.", 
					icon: "error", 
					confirmButtonColor: "#000000", 
					confirmButtonText: "확인"
				});
			}
		}
	});
}