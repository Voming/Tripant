
$(document).ready(function(){
	memebrChart();
})

function memebrChart(){
	
var ctx2 = document.getElementById('myChart2').getContext('2d');

let today = new Date();   

let year = today.getFullYear(); // 년도
let month = today.getMonth()+1 ;  // 7월
let month2 = today.getMonth() ;  // 6월
let month3 = today.getMonth()-1 ;  // 5월
let month4 = today.getMonth()-2 ;  // 4월
let month5 = today.getMonth()-3 ;  // 3월
let month6 = today.getMonth()-4 ;  // 2월
let month7 = today.getMonth()-5 ;  // 1월

var myChart2 = new Chart(ctx2, {
	type: 'line',
 	data: {
  		labels:[year+"/"+month7,year+"/"+month6, year+"/"+month5, year+"/"+month4, year+"/"+month3, year+"/"+month2, year+"/"+month],
	  	datasets: [{
			 label: '신규가입',
			 data: [count6,count5, count4, count3, count2, count1, count0],
			 fill: false,
			 borderColor: 'rgb(255, 99, 132)',
			 borderWidth:1,
			 tension: 0.1
	  },{
			 label: '일정생성수',
			 data: [plan6,plan5, plan4, plan3, plan2, plan1, plan0],
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