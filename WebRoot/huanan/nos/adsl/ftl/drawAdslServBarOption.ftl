<#--- 
广东电信--专线自动施工-业务发展
create:2015-04-01
-->


function optionTrackBar${lastdays}(){
	
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
		            data : [${days}]
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
		            data:[${addnums}]
		        },
		        {
		            name:'停机',
		            type:'bar',
		            data:[${deactivenums}]
		        },
		        {
		            name:'复机',
		            type:'bar',
		            data:[${activenums}]
		        },
		        {
		            name:'回退单',
		            type:'line',
		            yAxisIndex: 1,
		            data:[${failednums}]
		        }
		    ]
		};
		                    
	return option;
}


