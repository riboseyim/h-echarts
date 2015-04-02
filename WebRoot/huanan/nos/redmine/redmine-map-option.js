
function optionChinaMap(){
	// 自定义扩展图表类型：mapType = ChinaNormal
	require('echarts/util/mapData/params').params.ChinaNormal = {
	    getGeoJson: function (callback) {
	        $.getJSON('../geoJson/china_geo.json',callback);
	    }
	}
	var option = {
		    title : {
		        text : 'Redmine应用分布图',
		        subtext: ''
		    },
		    tooltip : {
		        trigger: 'item',
		        formatter: '{b}<br/>{c} (标准工时)'
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
		            mapType: 'ChinaNormal', // 自定义扩展图表类型
		            itemStyle:{
		                normal:{label:{show:true}},
		                emphasis:{label:{show:true}}
		            },
		            data:[
		                {name: '安徽', value: 0},
		                {name: '北京', value: 0},
		                {name: '重庆', value: 127},
		                {name: '福建', value: 525.5},
		                {name: '甘肃', value: 373.5},
		                {name: '广东', value: 336.5},
		                {name: '广西', value: 910},
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
		                {name: '宁夏', value: 49},
		                {name: '西沙', value: 0},
		                {name: '青海', value: 0},
		                {name: '山西', value: 0},
		                {name: '山东', value: 0},
		                {name: '上海', value: 0},
		                {name: '陕西', value: 137},
		                {name: '四川', value: 32},
		                {name: '天津', value: 0},
		                {name: '新疆', value: 0},
		                {name: '西藏', value: 0},
		                {name: '云南', value: 223.5},
		                {name: '浙江', value: 0}
		            ]
		        }
		    ]
		}
	return option;
}


