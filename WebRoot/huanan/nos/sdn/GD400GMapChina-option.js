
function optionChinaMap(){
	// �Զ�����չͼ�����ͣ�mapType = ChinaNormal_geo
	require('echarts/util/mapData/params').params.ChinaNormal = {
	    getGeoJson: function (callback) {
	        $.getJSON('../geoJson/ChinaNormal_geo.json',callback);
	    }
	}
	option = {
	    title : {
	        text : 'ChinaTopo',
	        subtext: 'author'
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
	            itemStyle:{
	                normal:{label:{show:true}},
	                emphasis:{label:{show:true}}
	            },
	           data:[
	                 {name: '����', value: 0},
	                 {name: '����', value: 0},
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
	                 {name: '����', value: 0},
	                 {name: '����', value: 0},
	                 {name: '����', value: 0},
	                 {name: '����', value: 0},
	                 {name: '����', value: 0},
	                 {name: '����', value: 0},
	                 {name: '���ɹ�', value: 0},
	                 {name: '����', value: 20},
	                 {name: '��ɳ', value: 0},
	                 {name: '�ຣ', value: 0},
	                 {name: 'ɽ��', value: 0},
	                 {name: 'ɽ��', value: 30},
	                 {name: '�Ϻ�', value: 0},
	                 {name: '����', value: 40},
	                 {name: '�Ĵ�', value: 32},
	                 {name: '���', value: 0},
	                 {name: '�½�', value: 0},
	                 {name: '����', value: 0},
	                 {name: '����', value: 20},
	                 {name: '�㽭', value: 0}
	                ],
	            // �Զ�������ӳ��
	            nameMap: {
	                'Anhui':'����',
	                'Beijing':'����',
	                'Chongqing':'����',
	                'Fujian':'����',
	                'Gansu':'����',
	                'Guangdong':'�㶫',
	                'Guangxi':'����',
	                'Guizhou':'����',
	                'Hainan':'����',
	                'Hebei':'�ӱ�',
	                'Heilongjiang':'������',
	                'Henan':'����',
	                'Hubei':'����',
	                'Hunan':'����',
	                'Jiangsu':'����',
	                'Jiangxi':'����',
	                'Jilin':'����',
	                'Liaoning':'����',
	                'Nei Mongol':'���ɹ�',
	                'Ningxia':'����',
	                'Paracel Islands':'�Ϻ��',
	                'Qinghai':'�ຣ',
	                'Shandong':'ɽ��',
	                'Shanxi':'ɽ��',
	                'Shanghai':'�Ϻ�',
	                'Shaanxi':'����',
	                'Sichuan':'�Ĵ�',
	                'Tianjin':'���',
	                'Xinjiang':'�½�',
	                'Xizang':'����',
	                'Yunnan':'����',
	                'Zhejiang':'�㽭'
	            },
	            // �ı�λ������
	            textFixed : {
	                'Yau Tsim Mong' : [-10, 0]
	            },
	            // �ı�ֱ�Ӿ�γ�ȶ�λ
	            geoCoord : {
	                'Islands' : [112.80, 21.57]
	            }
	        }
	    ]
	};
	return option;
}

