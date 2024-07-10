
$(document).ready(function(){
	memebrChart();
})

function memebrChart(){
	
var ctx2 = document.getElementById('myChart2').getContext('2d');

var myChart2 = new Chart(ctx2, {
	type: 'line',
 	data: {
  		labels:["5달전", "4달전", "3달전", "2달전", "1달전", "이번달"],
	  	datasets: [{
			 label: '신규가입회원수',
			 data: [count5, count4, count3, count2, count1, count0],
			 fill: false,
			 borderColor: 'rgb(255, 99, 132)',
			 borderWidth:1,
			 tension: 0.1
	  },{
			 label: '일정생성수',
			 data: [plan5, plan4, plan3, plan2, plan1, plan0],
			 fill: false,
			 borderColor: '#4B76E5',
			 borderWidth:1,
			 tension: 0.1
	  }
	  ],
	},
 	options: {
	    responsive: true,
	    plugins: {
		    legend: {
		    	position: 'top',
		      },
		     title: {
			     display: true,
			      text: '회원통계'
		     }
	   }
  },
 });
}