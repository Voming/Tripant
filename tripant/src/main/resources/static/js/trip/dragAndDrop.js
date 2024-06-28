//드래그 앤 드랍
function dragAndDrop(){
/**
 * [x] 엘리먼트의 .draggable, .container의 배열로 선택자를 지정합니다.
 * [x] draggables를 전체를 루프하면서 dragstart, dragend를 이벤트를 발생시킵니다.
 * [x] dragstart, dragend 이벤트를 발생할때 .dragging라는 클래스를 토글시킨다.
 * [x] dragover 이벤트가 발생하는 동안 마우스 드래그하고 마지막 위치해놓은 Element를 리턴하는 함수를 만듭니다.
 */

 	//$자체에 함수? 선언 $() 안의 선택자를 가진 모든 요소들을 선택함
    //const $ = (select) => document.querySelectorAll(select);
    const draggables = document.querySelectorAll('.draggable');
    const containers = document.querySelectorAll('.container');

    draggables.forEach(el => {
        el.addEventListener('dragstart', () => {
			console.log("dragstart");
            el.classList.add('dragging');
        });

        el.addEventListener('dragend', () => {
			console.log("dragend");
            el.classList.remove('dragging')
            //---------------------console.log
            console.log("************el");
            console.log(el);
            //console.log($(el));
            console.log("--------- prev");
            //console.log($(el).prev());
            //일차별 동그라미 색 변경
			//circleColorHandler();
			//displayEditInfo();
/*			console.log(editStorage.getItem(0));
			console.log(editStorage.getItem(1));
			console.log(dayEntityList_org);*/
        });
    });

    containers.forEach(container => {
        container.addEventListener('dragover', e => {
            console.log("dragover");
            e.preventDefault()
            const afterElement = getDragAfterElement(container, e.clientY);
            const draggable = document.querySelector('.dragging')
            // container.appendChild(draggable)
            console.log(draggable);
            console.log(afterElement);
            container.insertBefore(draggable, afterElement)
        })
    });
    
    function getDragAfterElement(container, y) {
        const draggableElements = [...container.querySelectorAll('.draggable:not(.dragging)')]
        

        return draggableElements.reduce((closest, child) => {
          const box = child.getBoundingClientRect() //해당 엘리먼트에 top값, height값 담겨져 있는 메소드를 호출해 box변수에 할당
          const offset = y - box.top - box.height / 2 //수직 좌표 - top값 - height값 / 2의 연산을 통해서 offset변수에 할당
          if (offset < 0 && offset > closest.offset) { // (예외 처리) 0 이하 와, 음의 무한대 사이에 조건
            return { offset: offset, element: child } // Element를 리턴
          } else {
            return closest
          }

        }, { offset: Number.NEGATIVE_INFINITY }).element
    };
}