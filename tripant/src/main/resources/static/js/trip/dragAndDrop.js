//드래그 앤 드랍
function dragAndDrop(){
	const draggables = document.querySelectorAll(".draggable");
	const containers = document.querySelectorAll(".container");
	
	draggables.each(function() {
        $(this).on('dragstart', function() {
            $(this).addClass('dragging');
        });

        $(this).on('dragend', function() {
            $(this).removeClass('dragging');
        });
    });
    
    containers.each(function() {
        $(this).on('dragover', function(e) {
    	//드래그 앤 드랍 시 동그라미 색깔, 순서, 
            e.preventDefault();
            const afterElement = getDragAfterElement(this, e.originalEvent.clientY);
            const draggable = $('.dragging')[0];
            if (afterElement == null) {
                $(this).append(draggable);
            } else {
                $(draggable).insertBefore(afterElement);
            }
        });
    });
function getDragAfterElement(container,x){
	
}

	/*
    const draggables = $('.draggable');
    const containers = $('.container');

    draggables.each(function() {
        $(this).on('dragstart', function() {
            $(this).addClass('dragging');
        });

        $(this).on('dragend', function() {
            $(this).removeClass('dragging');
        });
    });

    function getDragAfterElement(container, y) {
        const draggableElements = $(container).find('.draggable').not('.dragging').toArray();

        return draggableElements.reduce((closest, child) => {
            const box = child.getBoundingClientRect();
            const offset = y - box.top - box.height / 2;
            if (offset < 0 && offset > closest.offset) {
                return { offset: offset, element: child };
            } else {
                return closest;
            }
        }, { offset: Number.NEGATIVE_INFINITY }).element;
    }

    containers.each(function() {
        $(this).on('dragover', function(e) {
    	//드래그 앤 드랍 시 동그라미 색깔, 순서, 
            e.preventDefault();
            const afterElement = getDragAfterElement(this, e.originalEvent.clientY);
            const draggable = $('.dragging')[0];
            if (afterElement == null) {
                $(this).append(draggable);
            } else {
                $(draggable).insertBefore(afterElement);
            }
        });
    });*/
}