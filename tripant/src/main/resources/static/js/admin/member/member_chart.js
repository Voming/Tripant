
$(document).ready(function(){
	memebrChart();
})

function memebrChart(){
	
var ctx2 = document.getElementById('myChart2').getContext('2d');

console.log(count + "==========================");

//let labels = Utils.months({count: 6});
var myChart2 = new Chart(ctx2, {
	type: 'line',
 	data: {
  		labels:["1월", "2월", "3월", "4월", "5월", "6월"],
	  	datasets: [{
			 label: '회원수',
			 data: [65, 59, 80, 81, 56, count],
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