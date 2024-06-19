//동그라미 이미지 색 변경
function circleColorHandler(){

	var circleCss;
	$('.column.flex').each(function(){  //e : element
		//현재 고른 	$('.column.flex') 각각에 아래 함수를 적용
		//현재 고른 $('.column.flex')것에 적용해야하니 this사용! $('.column.flex')를 사용하면 무조건 첫번째 태그만 가져옴 this의 중요성!!!
		circleCss = $(this).data('columns'); //type이 number이므로 case에 값 넣을 때 작은따옴표 X
		
		switch(circleCss){
		case 1:
			$(this).children().find('.spot-number.backimg').css('background-image',"url('/images/loacation/circle1_red.png')");
			console.log("$(this).children().find('.spot-number.backimg')");
			console.log($(this).children().find('.spot-number.backimg'));
			break;
		case 2:
			$(this).children().find('.spot-number.backimg').attr('style', "background-image:url('/images/loacation/circle2_purple.png');")
			break;
		case 3:
			$(this).children().find('.spot-number.backimg').css('background-image',"url('/images/loacation/circle3_pink.png')");
			break;
		case 4:
			$(this).children().find('.spot-number.backimg').css('background-image',"url('/images/loacation/circle4_orange.png')");
			break;
		case 5:
			$(this).children().find('.spot-number.backimg').css('background-image',"url('/images/loacation/circle5_skyblue.png')");
			break;
		case 6:
			$(this).children().find('.spot-number.backimg').css('background-image',"url('/images/loacation/circle6_yellow.png')");
			break;
		case 7:
			$(this).children().find('.spot-number.backimg').css('background-image',"url('/images/loacation/circle7_lime.png')");
			break;
		case 8:
			$(this).children().find('.spot-number.backimg').css('background-image',"url('/images/loacation/circle8_green.png')");
			break;
		case 9:
			$(this).children().find('.spot-number.backimg').css('background-image',"url('/images/loacation/circle9_blue.png')");
			break;
		case 10:
			$(this).children().find('.spot-number.backimg').css('background-image',"url('/images/loacation/circle10_navy.png')");
			break;
		default:
			console.log($(this).children().find('.spot-number.backimg'));
			console.log('>>>>>backimg 실패 확인해보세요');
		}
	});
}