$(document).ready(function() {
	$('#diaryForm').on('submit', function(event) {
		event.preventDefault(); // 폼 기본 제출 방지
		const formData = $(this).serialize(); // 폼 데이터를 직렬화

		$.ajax({
			url: '/my/write', // 서버의 엔드포인트 URL
			type: 'post',
			data: formData,
			success: function(data) {
				alert('작성완료');
				window.location.href = '/my/diary'; // 성공 시 리디렉션
			},
			error: function(error) {
				console.error('Error:', error);
				alert('오류가 발생했습니다.');
			}
		});
	});
});