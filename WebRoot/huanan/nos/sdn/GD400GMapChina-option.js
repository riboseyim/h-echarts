
function optionChinaMap(){
	var ecConfig = require('echarts/config');
	// �Զ�����չͼ�����ͣ�mapType = ChinaNormal_geo
	require('echarts/util/mapData/params').params.ChinaNormal = {
	    getGeoJson: function (callback) {
	        $.getJSON('../geoJson/china_geo.json',callback);
	    }
	}
	option = {
	    title : {
	        text : 'ChinaTopo',
	        subtext: ''
	    },
	    tooltip : {
	        trigger: 'item',
	        formatter: '{b}<br/>{c}'
	    },
	    toolbox: {
	        show : true,
	        orient : 'vertical',
	        x: 'right',
	        y: 'center',
	        feature : {
	            mark : {show: false},
	            dataView : {show: false, readOnly: true},
	            restore : {show: true},
	            saveAsImage : {show: false}
	        }
	    },
	    dataRange: {
	        min: 0,
	        max: 100,
	        text:['High','Low'],
	        realtime: false,
	        calculable : true,
	        color: ['orangered','yellow','lightskyblue']
	    },
	    series : [
	        {
	            name: 'ChinaNormal',
	            type: 'map',
	            mapType: 'ChinaNormal', // �Զ�����չͼ������
	            selectedMode : 'single',
	            itemStyle:{
	                normal:{label:{show:true}},
	                emphasis:{label:{show:true}}
	            },
	           data:[
	                 {name: '����', value: 0},
	                 {name: '����', value: 50},
	                 {name: '����', value: 0},
	                 {name: '����', value: 10},
	                 {name: '����', value: 10},
	                 {name: '�㶫', value: 100},
	                 {name: '����', value: 20},
	                 {name: '����', value: 0},
	                 {name: '����', value: 0},
	                 {name: '�ӱ�', value: 0},
	                 {name: '������', value: 0},
	                 {name: '����', value: 0},
	                 {name: '����', value: 30},
	                 {name: '����', value: 0},
	                 {name: '����', value: 40},
	                 {name: '����', value: 0},
	                 {name: '����', value: 0},
	                 {name: '����', value: 0},
	                 {name: '���ɹ�', value: 0},
	                 {name: '����', value: 20},
	                 {name: '��ɳ', value: 0},
	                 {name: '�ຣ', value: 0},
	                 {name: 'ɽ��', value: 0},
	                 {name: 'ɽ��', value: 30},
	                 {name: '�Ϻ�', value: 50},
	                 {name: '����', value: 40},
	                 {name: '�Ĵ�', value: 32},
	                 {name: '���', value: 0},
	                 {name: '�½�', value: 0},
	                 {name: '����', value: 0},
	                 {name: '����', value: 20},
	                 {name: '�㽭', value: 45},
	                 {name: '̨��', value: 0}
	                ]
	        }
	    ]
	};
	return option;
}

