
function optionChinaMap(){
	// �Զ�����չͼ�����ͣ�mapType = ChinaNormal
	require('echarts/util/mapData/params').params.ChinaNormal = {
	    getGeoJson: function (callback) {
	        $.getJSON('../geoJson/china_geo.json',callback);
	    }
	}
	var option = {
		    title : {
		        text : '',
		        subtext: ''
		    },
		    tooltip : {
		        trigger: 'item',
		        formatter: '{b}<br/>{c} ()'
		    },
		    toolbox: {
		        show : true,
		        orient : 'vertical',
		        x: 'right',
		        y: 'center',
		        feature : {
		            mark : {show: true},
		            dataView : {show: true, readOnly: false},
		            restore : {show: true},
		            saveAsImage : {show: true}
		        }
		    },
		    dataRange: {
		        min: 0,
		        max: 1200,
		        text:['High','Low'],
		        realtime: false,
		        calculable : true,
		        color: ['orangered','yellow','lightskyblue']
		    },
		    series : [
		        {
		            name: 'Redime',
		            type: 'map',
		            mapType: 'ChinaNormal', // �Զ�����չͼ������
		            itemStyle:{
		                normal:{label:{show:true}},
		                emphasis:{label:{show:true}}
		            },
		            data:[
		                {name: '����', value: 0},
		                {name: '����', value: 0},
		                {name: '����', value: 127},
		                {name: '����', value: 525.5},
		                {name: '����', value: 373.5},
		                {name: '�㶫', value: 336.5},
		                {name: '����', value: 910},
		                {name: '����', value: 0},
		                {name: '����', value: 0},
		                {name: '�ӱ�', value: 0},
		                {name: '������', value: 0},
		                {name: '����', value: 0},
		                {name: '����', value: 0},
		                {name: '����', value: 0},
		                {name: '����', value: 0},
		                {name: '����', value: 0},
		                {name: '����', value: 0},
		                {name: '����', value: 0},
		                {name: '���ɹ�', value: 0},
		                {name: '����', value: 49},
		                {name: '��ɳ', value: 0},
		                {name: '�ຣ', value: 0},
		                {name: 'ɽ��', value: 0},
		                {name: 'ɽ��', value: 0},
		                {name: '�Ϻ�', value: 0},
		                {name: '����', value: 137},
		                {name: '�Ĵ�', value: 32},
		                {name: '���', value: 0},
		                {name: '�½�', value: 0},
		                {name: '����', value: 0},
		                {name: '����', value: 223.5},
		                {name: '�㽭', value: 0}
		            ]
		        }
		    ]
		}
	return option;
}

function optionTrackBar(){
	var option = {
	 	title : {
	        text : '���ٱ�ǩ��2014-11��'
	    },
	    tooltip : {
	        trigger: 'axis',
	        axisPointer : {            // ������ָʾ���������ᴥ����Ч
	            type : 'shadow'        // Ĭ��Ϊֱ�ߣ���ѡΪ��'line' | 'shadow'
	        }
	    },
	    legend: {
	        data:['��������', 'ʵʩ����','CASE����','�ڲ�����']
	    },
	    toolbox: {
	        show : true,
	        feature : {
	            mark : {show: true},
	            dataView : {show: true, readOnly: false},
	            magicType : {show: true, type: ['line', 'bar', 'stack', 'tiled']},
	            restore : {show: true},
	            saveAsImage : {show: true}
	        }
	    },
	    calculable : true,
	    xAxis : [
	        {
	            type : 'value'
	        }
	    ],
	    yAxis : [
	        {
	            type : 'category',
	            data : ['���','�ι���','������','����ΰ','��ΰ','����Ԫ','������','����','�����','������','�����','��ΰ','�ֱ�','�ػ�','������','����','֣��','����','����ǿ']
	        }
	    ],
	    series : [
	        {
	            name:'��������',
	            type:'bar',
	            stack: '��ʱ',
	            itemStyle : { normal: {label : {show: true, position: 'insideRight'}}},
	            data:[49.5,18,110,76,0,0,0,0,16,0,58.5,0,16,47,2,38.5,0,0,0]
	        },
	        {
	            name:'ʵʩ����',
	            type:'bar',
	            stack: '��ʱ',
	            itemStyle : { normal: {label : {show: true, position: 'insideRight'}}},
	            data:[94.5,10,10,2.5,44,98,133,48,7,27,102.5,29,16,96,76.5,27.5,126,57,168]
	        },
	        {
	            name:'CASE����',
	            type:'bar',
	            stack: '��ʱ',
	            itemStyle : { normal: {label : {show: true, position: 'insideRight'}}},
	            data:[22.5,152,2,57,29,53,25.5,64,0,17,25,110,121,0,92.5,99.5,0,121,34]
	        },
	        {
	            name:'�ڲ�����',
	            type:'bar',
	            stack: '��ʱ',
	            itemStyle : { normal: {label : {show: true, position: 'insideRight'}}},
	            data:[0,6,0,4.5,18,11,0,25,0,0,0,30,7,14,0,3,0,10,0]
	        }
	    ]
	}
	return option;	            	
}

function optionHistoryLine(){
	var option = {
		title :{
			 text : '�仯����ͼ��9��-11�£�'
		},
	    tooltip : {
	        trigger: 'axis'
	    },
	    legend: {
	        data:['�ܼƹ�ʱ','�¿�������','��������']
	    },
	    toolbox: {
	        show : true,
	        feature : {
	            mark : {show: true},
	            dataView : {show: true, readOnly: false},
	            magicType : {show: true, type: ['line', 'bar', 'stack', 'tiled']},
	            restore : {show: true},
	            saveAsImage : {show: true}
	        }
	    },
	    calculable : true,
	    xAxis : [
	        {
	            type : 'category',
	            boundaryGap : false,
	            data : ['����','����','ʮ��','ʮһ��']
	        }
	    ],
	    yAxis : [
	        {
	            type : 'value'
	        }
	    ],
	    series : [
	        {
	            name:'�ܹ�ʱ',
	            type:'line',
	            stack: '����',
	            data:[120, 132, 101, 134, 90, 230, 210]
	        },
	        {
	            name:'�¿�������',
	            type:'line',
	            stack: '����',
	            data:[220, 182, 191, 234, 290, 330, 310]
	        },
	        {
	            name:'��������',
	            type:'line',
	            stack: '����',
	            data:[150, 232, 201, 154, 190, 330, 410]
	        }
	    ]
	}
	return option;
}

function optionProjectBar(){
	var option = {
		title : {
	        text : '��Ŀ��ʱ�ֲ���2014-11��',
	        subtext: ''
	    },
	    tooltip : {
	        trigger: 'axis',
	        axisPointer : {            // ������ָʾ���������ᴥ����Ч
	            type : 'shadow'        // Ĭ��Ϊֱ�ߣ���ѡΪ��'line' | 'shadow'
	        }
	    },
	    legend: {
	        data:['��������', 'ʵʩ����','CASE����','�ڲ�����']
	    },
	    toolbox: {
	        show : true,
	        feature : {
	            mark : {show: true},
	            dataView : {show: true, readOnly: false},
	            magicType : {show: true, type: ['line', 'bar', 'stack', 'tiled']},
	            restore : {show: true},
	            saveAsImage : {show: true}
	        }
	    },
	    calculable : true,
	    xAxis : [
	        {
	            type : 'value'
	        }
	    ],
	    yAxis : [
	        {
	            type : 'category',
	        data:['���ϵ���IP','�㶫����IP','��������IP','��������PON','����������������','�������DACS','�������IP','��������IPRAN','��������IP','������ͨIP'] 
			}
	    ],
	    series : [
	        {
	            name:'��������',
	            type:'bar',
	            stack: '��ʱ',
	            itemStyle : { normal: {label : {show: true, position: 'insideRight'}}},
	          data:[0,0,98.5,80,0,0,80.5,74.5,0,0] 
	        },
	        {
	            name:'ʵʩ����',
	            type:'bar',
	            stack: '��ʱ',
	            itemStyle : { normal: {label : {show: true, position: 'insideRight'}}},
	        	data:[136,151,136.5,178.5,0,128,103,0,90,71] 
	         },
	        {
	            name:'CASE����',
	            type:'bar',
	            stack: '��ʱ',
	            itemStyle : { normal: {label : {show: true, position: 'insideRight'}}},
	         	data:[61.5,55.5,123.5,139,70,0,55,0,198.5,0] 
	         },
	        {
	            name:'�ڲ�����',
	            type:'bar',
	            stack: '��ʱ',
	            itemStyle : { normal: {label : {show: true, position: 'insideRight'}}},
	            data:[0,0,0,0,0,0,0,0,0,0]
	        }
	    ]
	}
	return option;
}



