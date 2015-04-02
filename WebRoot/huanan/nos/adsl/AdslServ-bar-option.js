
function optionTrackBar(){
	var option = {
			title:{
				text:'专线成功率'
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
		    calculable : true,
		    legend: {
		        data:['新装','停机','复机','回退单']
		    },
		    xAxis : [
		        {
		            type : 'category',
		            data : ['0318','0319','0320','0321','0322','0323','0324','0325','0326','0327','0328','0329','0330','0331','0401','0402']
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
		            data:[10,0,0,0,0,0,0,0,0,0,0,0,20,12,19,3]
		        },
		        {
		            name:'停机',
		            type:'bar',
		            data:[5,2,6,1,3,10,2,2,5,6,0,4,19,8,4,0]
		        },
		        {
		            name:'复机',
		            type:'bar',
		            data:[14,6,8,0,2,18,3,9,6,7,1,3,14,12,6,2]
		        },
		        {
		            name:'回退单',
		            type:'line',
		            yAxisIndex: 1,
		            data:[1,2,3,0,1,2,0,1,0,1,0,0,1,4,1,0]
		        }
		    ]
		};            
	return option;
}