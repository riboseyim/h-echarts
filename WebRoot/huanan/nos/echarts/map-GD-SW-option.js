
function optionGDSW(){
	// 自定义扩展图表类型：mapType = Guangdong
	require('echarts/util/mapData/params').params.Guangdong = {
	    getGeoJson: function (callback) {
	        $.getJSON('../geoJson/Guangdong3_geo.json',callback);
	    }
	}
	var option = {
		    title : {
		        text : '交换机纳入集中管理进度表 ',
		        subtext: 'Data From 中盈IP综合网管',
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
		        text:['完成','开始'],
		        realtime: false,
		        calculable : true,
		        color: ['orangered','yellow','lightskyblue']
		    },
		    series : [
		        {
		            name: '广东电信',
		            type: 'map',
		            mapType: 'Guangdong', // 自定义扩展图表类型
		            itemStyle:{
		                normal:{label:{show:true}},
		                emphasis:{label:{show:true}}
		            },
		            data:[
			                {name: '广州', value: 17},
			                {name: '深圳', value: 95},
			                {name: '东莞', value: 0},
			                {name: '佛山', value: 95},
			                {name: '惠州', value: 92},
			                {name: '韶关', value: 95},
			                {name: '清远', value: 150},
			                {name: '河源', value: 95},
			                {name: '梅州', value: 100},
			                {name: '湛江', value: 100},
			                {name: '茂名', value: 100},
			                {name: '阳江', value: 15},
			                {name: '肇庆', value: 95},
			                {name: '云浮', value: 100},
			                {name: '潮州', value: 100},
			                {name: '汕头', value: 95},
			                {name: '汕尾', value: 100},
			                {name: '揭阳', value: 40},
			                {name: '中山', value: 100},
			                {name: '珠海', value: 150},
			                {name: '江门', value: 100}
			           
			            ],
		            // 自定义名称映射
		            nameMap: {
		                'Guangzhou':'广州',
		                'Shenzhen':'深圳',
		                'Dongguan':'东莞',
		                'Foshan':'佛山',
		                'Huizhou':'惠州',
		                'Shaoguan':'韶关',
		                'Qingyuan':'清远',
		                'Heyuan':'河源',
		                'Meizhou':'梅州',
		                'Zhanjiang':'湛江',
		                'Maoming':'茂名',
		                'Yangjiang':'阳江',
		                'Zhaoqing':'肇庆',
		                'Yunfu':'云浮',
		                'Chaozhou':'潮州',
		                'Shantou':'汕头',
		                'Shanwei':'汕尾',
		                'Jieyang':'揭阳',
		                'Zhongshan':'中山',
		                'Zhuhai':'珠海',
		                'Jiangmen':'江门'
		                
		            },
		            // 文本位置修正
		            textFixed : {
		                'Yau Tsim Mong' : [-10, 0]
		            },
		            // 文本直接经纬度定位
		            geoCoord : {
		                'Islands' : [112.80, 21.57]
		            }
		        }
		    ]
		};
	return option;
}

