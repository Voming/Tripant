$(document).ready(function(){
	boardChart();
})
let delay =50;
let timer = null;
$(window).on('resize', function(){
	clearTimeout(timer);
	timer = setTimeout(function(){
        location.reload();
	}, delay);
});

function boardChart(){
var ctx = document.getElementById('myChart').getContext('2d');
var myChart = new Chart(ctx, {
		  type: 'polarArea',
		  data: {   
		  labels: ['서울','부산','대구','인천','광주','대전','울산','세종','경기','충북','충남','전북','전남','경북','경남','제주','강원'],      
				datasets: [{           
					           
					 data:[seoul,busan ,daegu,incheon,gwangju,daejeon,ulsan,sejong,gyeonggi,Chungbuk,Chungnam,Jeonbuk,Jeonam,Gyeongbuk,Gyeongnam,jeju,gangwon],            
					backgroundColor: [                
						'rgba(255, 99, 132, 0.2)',                
						'rgba(54, 162, 235, 0.2)',                
						'rgba(255, 206, 86, 0.2)',                
						'rgba(75, 192, 192, 0.2)',                
						'rgba(153, 102, 255, 0.2)',               
						'rgba(255, 159, 64, 0.3)',            
						'#CC333359', 
						'#C943CC33' ,          
						'#CCCCCC73' ,     
						'#CCCCFF' ,
						'#CCCCFF59' ,
						'#4B76E559' ,
						'#4B76E599' ,
						'#E5C34BB3',
						'#E5C34B80',
						'#FF990073',
						'#0066FF59'
						],            
							}]    
						}
		 ,
		  		/*backgroundColor: [                
						'rgba(255, 99, 132, 0.2)',                
						'rgba(54, 162, 235, 0.2)',                
						'rgba(255, 206, 86, 0.2)',                
						'rgba(75, 192, 192, 0.2)',                
						'rgba(153, 102, 255, 0.2)',               
						'rgba(255, 159, 64, 0.3)',            
						'#CC333359', 
						'#C943CC33' ,          
						'#CCCCCC73' ,     
						'#CCCCFF' ,
						'#CCCCFF59' ,
						'#4B76E559' ,
						'#4B76E599' ,
						'#6699CCB3',
						'#E5C34B80',
						'#FF990073',
						'#0066FF59'         
				],   */ 
		  options: {
		    responsive: false,
		    plugins: {
		      legend: {
		        position: 'top',
		      },
		      title: {
		        display: true,
		        text: '게시글 지역 통계'
		      }
		    }
		  },
		});

/*const DATA_COUNT = 6;
const NUMBER_CFG = {count: DATA_COUNT, min: 0, max: 100};

const labels = ['Red', 'Orange', 'Yellow', 'Green', 'Blue','grey'];
const data = {
  labels: labels,
  datasets: [
    {
      label: 'Dataset 1',
      data: Utils.numbers(NUMBER_CFG),
      backgroundColor: [
        Utils.transparentize(Utils.CHART_COLORS.red, 0.5),
        Utils.transparentize(Utils.CHART_COLORS.orange, 0.5),
        Utils.transparentize(Utils.CHART_COLORS.yellow, 0.5),
        Utils.transparentize(Utils.CHART_COLORS.green, 0.5),
        Utils.transparentize(Utils.CHART_COLORS.blue, 0.5),
        Utils.transparentize(Utils.CHART_COLORS.grey, 0.5),
      ]
    }
  ]
};*/
}
