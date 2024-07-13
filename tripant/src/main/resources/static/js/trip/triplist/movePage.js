$(document).ready(function() {
	//일정상세보기로 이동 
	$('.trip-list').on('click', function(event) {
		var isMiniModal = $(event.target).closest('.info.btn').length > 0;

		if (!isMiniModal) {
			var planId = $(this).data('plan-id');
			window.location.href = contextPath + 'trip/detail/' + planId;
		}
	});
	
	//편집화면으로 바로 이동
	$('.show-detail').on('click', function() {
		var planId = $(this).parents('.trip-list').data('plan-id');
		window.location.href = contextPath + 'trip/detail/' + planId + '#edit';
	});

	// 일행추가 모달의 부모 요소들에게 click 이벤트 전달 X
	$('.wrap-modal').on('click', function(e) {
		e.stopPropagation();
	})
});

