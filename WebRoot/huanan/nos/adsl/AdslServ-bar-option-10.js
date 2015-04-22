

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
		            data : ['0412','0413','0414','0415','0416','0417','0418','0419','0420','0421','0422']
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
		            data:[0,21,14,13,12,13,0,0,21,17,14]
		        },
		        {
		            name:'ͣ��',
		            type:'bar',
		            data:[1,6,2,1,1,9,5,6,288,1,2]
		        },
		        {
		            name:'����',
		            type:'bar',
		            data:[0,5,6,6,4,8,0,1,101,72,7]
		        },
		        {
		            name:'���˵�',
		            type:'line',
		            yAxisIndex: 1,
		            data:[1,5,9,0,2,3,1,1,16,0,5]
		        }
		    ]
		};
		                    
	return option;
}


