$(loadedHandler);

function loadedHandler() {
	$(".logo").on("click", btnLogoClickHandler);
	$(".plan-priod").on("click", btnPriodClickHandler);

	//좌측 탭
	$('.tab-nav a').click(function() {
		$('.tab-content > div').hide().filter(this.hash).fadeIn();
		$('.tab-nav a').css("color", "black");
		$('.tab-nav a').removeClass('active');
		$(this).addClass('active');

		$(this).css("color", "#4BC9E5");
		return false;
	}).filter(':eq(0)').click();
}

//F5로 새로고침 방지하기
document.onkeydown = fkey;
var wasPressed = false;

function fkey(e) {
	e = e || window.event;
	if (wasPressed) return;
	if (e.keyCode == 116) {
		if (confirm("지금 새로고침하면 작업한 내용이 없어집니다. 괜찮습니까?") == true) {
			location.reload(true);
		} else {
			event.preventDefault();
		}
	}
}

function btnLogoClickHandler() {
	if (confirm("지금 나가면 작업한 내용이 없어집니다. 괜찮습니까?") == true) {
		location.href = "/";
	}
}

function btnPriodClickHandler() {
	if (confirm("기간을 다시 설정하면 작성한 내용이 없어집니다. 괜찮습니까?") == true) {
		location.reload(true);
		$(".modal").addClass("show");
	} else {
		event.preventDefault();
	}
}


