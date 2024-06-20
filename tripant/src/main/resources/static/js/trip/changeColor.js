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
//맵의 마커 이미지 변경
function mapCircleHandler(idx){

		switch(idx){
		case 1:
			imageSrc = '/images/loacation/location1.png';
			break;
		case 2:
			imageSrc = '/images/loacation/location2.png';
			break;
		case 3:
			imageSrc = '/images/loacation/location3.png';
			break;
		case 4:
			imageSrc = '/images/loacation/location4.png';
			break;
		case 5:
			imageSrc = '/images/loacation/location5.png';
			break;
		case 6:
			imageSrc = '/images/loacation/location6.png';
			break;
		case 7:
			imageSrc = '/images/loacation/location7.png';
			break;
		case 8:
			imageSrc = '/images/loacation/location8.png';
			break;
		case 9:
			imageSrc = '/images/loacation/location9.png';
			break;
		case 10:
			imageSrc = '/images/loacation/location10.png';
			break;
		default:
			console.log('>>>>>map image 실패 확인해보세요');
		}
		return imageSrc;
}

function mapLineHandler(idx){
	
	switch(idx){
		case 1:
			lineColor = '#E54B4B';
			break;
		case 2:
			lineColor = '#C943CC';
			break;
		case 3:
			lineColor = '#FFA7C5';
			break;
		case 4:
			lineColor = '#E5794B';
			break;
		case 5:
			lineColor = '#4BC9E5';
			break;
		case 6:
			lineColor = '#E5C34B';
			break;
		case 7:
			lineColor = '#CDF263';
			break;
		case 8:
			lineColor = '#2EB67D';
			break;
		case 9:
			lineColor = '#4B76E5';
			break;
		case 10:
			lineColor = '#13358F';
			break;
		default:
			console.log('>>>>>map lineColor 실패 확인해보세요');
		}
		return lineColor;
}
