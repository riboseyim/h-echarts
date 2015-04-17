

function optionTrackBar10(){
	
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
		            data : ['0406','0407','0408','0409','0410','0411','0412','0413','0414','0415','0416']
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
		            data:[0,13,7,7,7,0,0,21,14,13,1]
		        },
		        {
		            name:'ͣ��',
		            type:'bar',
		            data:[5,1,4,9,2,2,2,6,2,1,0]
		        },
		        {
		            name:'����',
		            type:'bar',
		            data:[0,8,2,8,2,1,1,5,6,6,1]
		        },
		        {
		            name:'���˵�',
		            type:'line',
		            yAxisIndex: 1,
		            data:[3,3,0,2,1,1,1,5,8,0,1]
		        }
		    ]
		};
		                    
	return option;
}


