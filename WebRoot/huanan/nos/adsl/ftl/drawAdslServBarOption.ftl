<#--- 
�㶫����--ר���Զ�ʩ��-ҵ��չ
create:2015-04-01
-->


function optionTrackBar${lastdays}(){
	
	var option = {
			title:{
				text:'ר���Զ�ʩ���ɹ���'
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
		        data:['��װ','ͣ��','����','���˵�']
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
		            name : 'ר��',
		            axisLabel : {
		                formatter: '{value} ��'
		            }
		        },
		        {
		            type : 'value',
		            name : '���˵�',
		            axisLabel : {
		                formatter: '{value} ��'
		            }
		        }
		    ],
		    series : [

		        {
		            name:'��װ',
		            type:'bar',
		            data:[${addnums}]
		        },
		        {
		            name:'ͣ��',
		            type:'bar',
		            data:[${deactivenums}]
		        },
		        {
		            name:'����',
		            type:'bar',
		            data:[${activenums}]
		        },
		        {
		            name:'���˵�',
		            type:'line',
		            yAxisIndex: 1,
		            data:[${failednums}]
		        }
		    ]
		};
		                    
	return option;
}


