
function optionChinaMap(){
	var ecConfig = require('echarts/config');
	// 自定义扩展图表类型：mapType = ChinaNormal_geo
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
	            mapType: 'ChinaNormal', // 自定义扩展图表类型
	            selectedMode : 'single',
	            itemStyle:{
	                normal:{label:{show:true}},
	                emphasis:{label:{show:true}}
	            },
	           data:[
	                 {name: '安徽', value: 0},
	                 {name: '北京', value: 50},
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
	                 {name: '湖北', value: 30},
	                 {name: '湖南', value: 0},
	                 {name: '江苏', value: 40},
	                 {name: '江西', value: 0},
	                 {name: '吉林', value: 0},
	                 {name: '辽宁', value: 0},
	                 {name: '内蒙古', value: 0},
	                 {name: '宁夏', value: 20},
	                 {name: '西沙', value: 0},
	                 {name: '青海', value: 0},
	                 {name: '山西', value: 0},
	                 {name: '山东', value: 30},
	                 {name: '上海', value: 50},
	                 {name: '陕西', value: 40},
	                 {name: '四川', value: 32},
	                 {name: '天津', value: 0},
	                 {name: '新疆', value: 0},
	                 {name: '西藏', value: 0},
	                 {name: '云南', value: 20},
	                 {name: '浙江', value: 45},
	                 {name: '台湾', value: 0}
	                ]
	        }
	    ]
	};
	return option;
}

