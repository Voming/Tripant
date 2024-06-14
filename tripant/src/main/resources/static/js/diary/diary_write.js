$(document).ready(function() {
    // CKEditor 초기화
    ClassicEditor
        .create(document.querySelector('#editor'))
        .catch(error => {
            console.error(error);
        });

	//폼 제출 시 처리
	$('#diaryForm').submit(function(event) {
		event.preventDefault(); //폼의 기본 제출 동작을 막음

		//필수 입력값 검사
		var diaryPlanId = $("select[name=diaryPlanId]").val();
		var diaryId = $("select[name=diaryId]").val();
		var diaryTitle = $("input[name=diaryTitle]").val().trim();
		var diaryDate = $("input[name=diaryDate]").val();
		var diaryTheme = $("select[name=diaryTheme]").val();
		var diaryOpen = $("input[name=diaryOpen]:checked").val();
		var diaryContent = CKEditor.instances.editor.getData().trim(); // CKEditor에서 내용 가져오기

		if (diaryPlanId === "") {
			alert("일정을 선택해주세요.");
			return;
		}
		if (diaryTitle === "") {
			alert("제목을 입력해주세요.");
			return;
		}
		
		if (diaryContent === "") {
			alert("내용을 입력해주세요.");
			return;
		}
		// 서버로 데이터 전송 (spring MVC 예시)
		var url = "/write/post";
		$.ajax({
			type: "post",
			url: url,
			contentType: "application/json",
			data: JSON.stringify({
				diaryPlanId: diaryPlanId,
				diaryDiaryId: diaryId,
				diaryTitle: diaryTitle,
				diaryDate: diaryDate,
				diaryTheme: diaryTheme,
				diaryOpen: diaryOpen,
				diaryContent: diaryContent
			}),
			success: function(response) {
				//서버로 부터 응답을 받았을 때 처리 (예: 성공 메시지 출력 등)
				console.log("글 등록 성공:", response);
				alert("글이 성공적으로 등록되었습니다.");
				//필요한 후속 작업 추가
			},
			error: function(xhr, status, error) {
				//오류 발생 시 처리
				console.error("글 등록 오류:", status, error);
				alert("글 등록 중 오류가 발생했습니다. 다시 시도해 주세요");
			}
		});
	});

