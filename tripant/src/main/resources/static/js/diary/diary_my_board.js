document.addEventListener("DOMContentLoaded", function() {
	var diaries =8; /* 일기 데이터 배열 */
	
	var batchSize = 8; //한번에 보여줄 일기의 개수
	var currentIndex = 0; // 현재 보여지고 있는 일기의 인덱스
	
	//일기를 렌더링 하는 함수
	function renderDiaries(startIndex, count){
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
                            <p class="diary-title">${diary.diaryTitle}</p>
                            <p class="diary-date">${diary.diaryDate}</p>
                            <p class="diary-open">${diary.diaryOpen}</p>
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
    renderDiaries(currentIndex, batchSize);
    currentIndex += batchSize; // 초기 인덱스 설정
});