
$(document).ready(function(){
	memebrChart();
})

function memebrChart(){
	
var ctx2 = document.getElementById('myChart2').getContext('2d');

console.log(count + "==========================");

var myChart2 = new Chart(ctx2, {
	type: 'line',
 	data: {
  		labels:["5달전", "4달전", "3달전", "2달전", "1달전", "이번달"],
	  	datasets: [{
			 label: '회원수',
			 data: [10, 30, 27, 11, count2, count],
			 fill: false,
			 borderColor: 'rgb(255, 99, 132)',
			 borderWidth:1,
			 tension: 0.1
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
			      text: '회원통계'
		     }
	   }
  },
 });
}