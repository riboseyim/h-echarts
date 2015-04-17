

function optionTrackBar30(){
	
	var option = {
			title:{
				text:'专线自动施工成功率'
			},
		    tooltip : {
		        trigger: 'axis'
		    },
		    toolbox: {
		        show : true,
		        feature : {
		            mark : {show: true},
		            dataView : {show: true, readOnly: false},
		            magicType: {show: true, type: ['line', 'bar']},
		            restore : {show: true},
		            saveAsImage : {show: true}
		        }
		    },
		    grid:{
		    	x:45,
		    	y:45,
		    	x2:30,
		    	y2:40
		    },
		    calculable : true,
		    legend: {
		        data:['新装','停机','复机','回退单']
		    },
		    xAxis : [
		        {
		            type : 'category',
		            data : ['0317','0318','0319','0320','0321','0322','0323','0324','0325','0326','0327','0328','0329','0330','0331','0401','0402','0403','0404','0405','0406','0407','0408','0409','0410','0411','0412','0413','0414','0415','0416']
		        }
		    ],
		    yAxis : [
		        {
		            type : 'value',
		            name : '专线',
		            axisLabel : {
		                formatter: '{value} 条'
		            }
		        },
		        {
		            type : 'value',
		            name : '回退单',
		            axisLabel : {
		                formatter: '{value} 条'
		            }
		        }
		    ],
		    series : [

		        {
		            name:'新装',
		            type:'bar',
		            data:[7,10,0,0,0,0,0,0,0,0,0,0,0,20,12,19,10,16,0,0,0,13,7,7,7,0,0,21,14,13,1]
		        },
		        {
		            name:'停机',
		            type:'bar',
		            data:[149,5,2,6,1,3,10,2,2,5,6,0,4,19,8,4,4,2,3,2,5,1,4,9,2,2,2,6,2,1,0]
		        },
		        {
		            name:'复机',
		            type:'bar',
		            data:[18,49,6,8,0,2,18,3,9,6,7,1,3,14,12,6,2,3,1,1,0,8,2,8,2,1,1,5,6,6,1]
		        },
		        {
		            name:'回退单',
		            type:'line',
		            yAxisIndex: 1,
		            data:[4,1,2,3,0,1,2,0,1,0,1,0,0,1,4,1,5,4,0,0,3,3,0,2,1,1,1,5,8,0,1]
		        }
		    ]
		};
		                    
	return option;
}


