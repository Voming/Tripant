document.addEventListener("DOMContentLoaded", function() {
	var diaries = 4; /* 일기 데이터 배열 */
	var batchSize = 1; // 한번에 보여줄 일기의 개수
	var currentIndex = 4; // 현재 보여지고 있는 일기의 인덱스

	// 일기를 렌더링하는 함수
	function renderDiaries(startIndex, count) {
		var boardList = document.querySelector(".board-list");
		for (var i = startIndex; i < startIndex + count; i++) {
			var diary = diaries[i];
			if (diary) {
				var diaryElement = document.createElement("div");
				diaryElement.classList.add("wrap-diary");
				diaryElement.innerHTML = `
                        <div class="diary-img">
                            <img src="https://dummyimage.com/200x200/000/fff" alt="대표 이미지">
                        </div>
                        <div class="wrap-content">
                            <div class="diary-content">
                                <a th:href="@{|/diary/read/${diary.diaryId}|}">
                                    <p class="diary-title" th:text="${diary.diaryTitle}"></p>
                                </a>
                                <p class="diary-date" th:text="${diary.diaryDate}"></p>
                                <p class="diary-open" th:text="${diary.diaryOpen}"></p>
                            </div>
                        </div>
                    `;
				boardList.appendChild(diaryElement);
			}
		}
	}

	// 더보기 버튼 클릭 이벤트 핸들러
	document.querySelector(".btn-more .learn-more").addEventListener("click", function() {
		renderDiaries(currentIndex, batchSize); // batchSize만큼 일기를 추가로 렌더링
		currentIndex += batchSize; // currentIndex 업데이트
	});

	// 초기에 일기 렌더링
	renderDiaries(0, 4); // 초기에 4개의 일기를 보여줌
});