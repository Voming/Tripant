
$(document).ready(function(){
	memebrChart();
})
function memebrChart(){
var ctx2 = document.getElementById('myChart2').getContext('2d');
var myChart2 = new Chart(ctx2, {
		  type: 'bubble',
		  data: {        
				labels: ["1월", "2월", "3월", "4월", "5월", "6월"],        
				datasets: [{           
					label: '회원수',       
					 data: [{x:3,y:5,r:7},{x:5,y:7,r:7},{x:4,y:4,r:8}
					 ,{x:6,y:3,r:5},{x:8,y:3,r:6},{x:9,y:9,r:9}],            
					backgroundColor: [                
						'rgba(255, 99, 132, 0.2)',               
						'rgba(54, 162, 235, 0.2)',                
						'rgba(255, 206, 86, 0.2)',                
						'rgba(75, 192, 192, 0.2)',                
						'rgba(153, 102, 255, 0.2)',               
						'rgba(255, 159, 64, 0.2)'           
						],            
							},{
					label: '일정생성수',  
					 data: [{x:1,y:5,r:7},{x:7,y:7,r:7},{x:9,y:3,r:8}
					 ,{x:7,y:9,r:5},{x:3,y:3,r:6},{x:5,y:9,r:9}],            
					backgroundColor: [                
						'rgba(255, 99, 132, 0.8)',               
						'rgba(54, 162, 235, 0.8)',                
						'rgba(255, 206, 86, 0.8)',                
						'rgba(75, 192, 192, 0.8)',                
						'rgba(153, 102, 255, 0.8)',               
						'rgba(255, 159, 64, 0.8)'           
						],
							}]  
						},
		  options: {
		    responsive: true,
		    plugins: {
		    	legend: {
		    	position: 'top',
		      },
		    title: {
		    	display: true,
		    	text: '월 가입자수/일정생성 통계'
		      }
		    }
		  },
		});
}