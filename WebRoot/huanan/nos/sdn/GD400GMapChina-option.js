
function optionChinaMap(){
	// 自定义扩展图表类型：mapType = ChinaNormal_geo
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
	            mapType: 'ChinaNormal', // 自定义扩展图表类型
	            itemStyle:{
	                normal:{label:{show:true}},
	                emphasis:{label:{show:true}}
	            },
	           data:[
	                 {name: '安徽', value: 0},
	                 {name: '北京', value: 0},
	                 {name: '重庆', value: 0},
	                 {name: '福建', value: 10},
	                 {name: '甘肃', value: 10},
	                 {name: '广东', value: 100},
	                 {name: '广西', value: 20},
	                 {name: '贵州', value: 0},
	                 {name: '海南', value: 0},
	                 {name: '河北', value: 0},
	                 {name: '黑龙江', value: 0},
	                 {name: '河南', value: 0},
	                 {name: '湖北', value: 0},
	                 {name: '湖南', value: 0},
	                 {name: '江苏', value: 0},
	                 {name: '江西', value: 0},
	                 {name: '吉林', value: 0},
	                 {name: '辽宁', value: 0},
	                 {name: '内蒙古', value: 0},
	                 {name: '宁夏', value: 20},
	                 {name: '西沙', value: 0},
	                 {name: '青海', value: 0},
	                 {name: '山西', value: 0},
	                 {name: '山东', value: 30},
	                 {name: '上海', value: 0},
	                 {name: '陕西', value: 40},
	                 {name: '四川', value: 32},
	                 {name: '天津', value: 0},
	                 {name: '新疆', value: 0},
	                 {name: '西藏', value: 0},
	                 {name: '云南', value: 20},
	                 {name: '浙江', value: 0}
	                ],
	            // 自定义名称映射
	            nameMap: {
	                'Anhui':'安徽',
	                'Beijing':'北京',
	                'Chongqing':'重庆',
	                'Fujian':'福建',
	                'Gansu':'甘肃',
	                'Guangdong':'广东',
	                'Guangxi':'广西',
	                'Guizhou':'贵州',
	                'Hainan':'海南',
	                'Hebei':'河北',
	                'Heilongjiang':'黑龙江',
	                'Henan':'河南',
	                'Hubei':'湖北',
	                'Hunan':'湖南',
	                'Jiangsu':'江苏',
	                'Jiangxi':'江西',
	                'Jilin':'吉林',
	                'Liaoning':'辽宁',
	                'Nei Mongol':'内蒙古',
	                'Ningxia':'宁夏',
	                'Paracel Islands':'南海诸岛',
	                'Qinghai':'青海',
	                'Shandong':'山东',
	                'Shanxi':'山西',
	                'Shanghai':'上海',
	                'Shaanxi':'陕西',
	                'Sichuan':'四川',
	                'Tianjin':'天津',
	                'Xinjiang':'新疆',
	                'Xizang':'西藏',
	                'Yunnan':'云南',
	                'Zhejiang':'浙江'
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

