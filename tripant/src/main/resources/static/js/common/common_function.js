//ajax error 부분
function ajaxErrorHandler(request, status, error) {
	alert("code: " + request.status + "\n" + "message: "
		+ request.responseText + "\n"
		+ "error: " + error);
	Swal.fire({
		text: "알 수 없는 오류가 발생했습니다. 관리자에게 문의해주시기 바랍니다.",
		icon: "error",
		confirmButtonColor: "#000000",
		confirmButtonText: "확인"
	});
}
const token = $("meta[name='_csrf']").attr("content");
const header = $("meta[name='_csrf_header']").attr("content");
//csrf
function csrfHandler(xhr) {
	xhr.setRequestHeader(header, token);
}
