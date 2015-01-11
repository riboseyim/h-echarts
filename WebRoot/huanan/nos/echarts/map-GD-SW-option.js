
function optionGDSW(){
	// �Զ�����չͼ�����ͣ�mapType = Guangdong
	require('echarts/util/mapData/params').params.Guangdong = {
	    getGeoJson: function (callback) {
	        $.getJSON('../geoJson/Guangdong3_geo.json',callback);
	    }
	}
	var option = {
		    title : {
		        text : '���������뼯�й�����ȱ� ',
		        subtext: 'Data From ��ӯIP�ۺ�����',
		        sublink:""
		        
		    },
		    tooltip : {
		        trigger: 'item',
		        formatter: '{b}<br/>{c} %'
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
		        max: 200,
		        text:['���','��ʼ'],
		        realtime: false,
		        calculable : true,
		        color: ['orangered','yellow','lightskyblue']
		    },
		    series : [
		        {
		            name: '�㶫����',
		            type: 'map',
		            mapType: 'Guangdong', // �Զ�����չͼ������
		            itemStyle:{
		                normal:{label:{show:true}},
		                emphasis:{label:{show:true}}
		            },
		            data:[
			                {name: '����', value: 17},
			                {name: '����', value: 95},
			                {name: '��ݸ', value: 0},
			                {name: '��ɽ', value: 95},
			                {name: '����', value: 92},
			                {name: '�ع�', value: 95},
			                {name: '��Զ', value: 150},
			                {name: '��Դ', value: 95},
			                {name: '÷��', value: 100},
			                {name: 'տ��', value: 100},
			                {name: 'ï��', value: 100},
			                {name: '����', value: 15},
			                {name: '����', value: 95},
			                {name: '�Ƹ�', value: 100},
			                {name: '����', value: 100},
			                {name: '��ͷ', value: 95},
			                {name: '��β', value: 100},
			                {name: '����', value: 40},
			                {name: '��ɽ', value: 100},
			                {name: '�麣', value: 150},
			                {name: '����', value: 100}
			           
			            ],
		            // �Զ�������ӳ��
		            nameMap: {
		                'Guangzhou':'����',
		                'Shenzhen':'����',
		                'Dongguan':'��ݸ',
		                'Foshan':'��ɽ',
		                'Huizhou':'����',
		                'Shaoguan':'�ع�',
		                'Qingyuan':'��Զ',
		                'Heyuan':'��Դ',
		                'Meizhou':'÷��',
		                'Zhanjiang':'տ��',
		                'Maoming':'ï��',
		                'Yangjiang':'����',
		                'Zhaoqing':'����',
		                'Yunfu':'�Ƹ�',
		                'Chaozhou':'����',
		                'Shantou':'��ͷ',
		                'Shanwei':'��β',
		                'Jieyang':'����',
		                'Zhongshan':'��ɽ',
		                'Zhuhai':'�麣',
		                'Jiangmen':'����'
		                
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

