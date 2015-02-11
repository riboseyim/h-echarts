
function optionChinaMap(){
	// 自定义扩展图表类型：mapType = ChinaNormal
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

function optionTrackBar(){
	var option = {
	 	title : {
	        text : '跟踪标签（2014-11）'
	    },
	    tooltip : {
	        trigger: 'axis',
	        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
	            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
	        }
	    },
	    legend: {
	        data:['开发流程', '实施流程','CASE流程','内部流程']
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
	            data : ['严睿','何广生','刘本晖','吕坚伟','张伟','张兴元','张宁烽','张毅','李宇锋','李旭堂','杨济忠','林伟','林斌','池虎','滕承仁','董晨','郑龙','钟晓','高新强']
	        }
	    ],
	    series : [
	        {
	            name:'开发流程',
	            type:'bar',
	            stack: '工时',
	            itemStyle : { normal: {label : {show: true, position: 'insideRight'}}},
	            data:[49.5,18,110,76,0,0,0,0,16,0,58.5,0,16,47,2,38.5,0,0,0]
	        },
	        {
	            name:'实施流程',
	            type:'bar',
	            stack: '工时',
	            itemStyle : { normal: {label : {show: true, position: 'insideRight'}}},
	            data:[94.5,10,10,2.5,44,98,133,48,7,27,102.5,29,16,96,76.5,27.5,126,57,168]
	        },
	        {
	            name:'CASE流程',
	            type:'bar',
	            stack: '工时',
	            itemStyle : { normal: {label : {show: true, position: 'insideRight'}}},
	            data:[22.5,152,2,57,29,53,25.5,64,0,17,25,110,121,0,92.5,99.5,0,121,34]
	        },
	        {
	            name:'内部流程',
	            type:'bar',
	            stack: '工时',
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
			 text : '变化趋势图（9月-11月）'
		},
	    tooltip : {
	        trigger: 'axis'
	    },
	    legend: {
	        data:['总计工时','新开任务数','参与人数']
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
	            data : ['八月','九月','十月','十一月']
	        }
	    ],
	    yAxis : [
	        {
	            type : 'value'
	        }
	    ],
	    series : [
	        {
	            name:'总工时',
	            type:'line',
	            stack: '总量',
	            data:[120, 132, 101, 134, 90, 230, 210]
	        },
	        {
	            name:'新开任务数',
	            type:'line',
	            stack: '总量',
	            data:[220, 182, 191, 234, 290, 330, 310]
	        },
	        {
	            name:'参与人数',
	            type:'line',
	            stack: '总量',
	            data:[150, 232, 201, 154, 190, 330, 410]
	        }
	    ]
	}
	return option;
}

function optionProjectBar(){
	var option = {
		title : {
	        text : '项目耗时分布（2014-11）',
	        subtext: ''
	    },
	    tooltip : {
	        trigger: 'axis',
	        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
	            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
	        }
	    },
	    legend: {
	        data:['开发流程', '实施流程','CASE流程','内部流程']
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
	        data:['云南电信IP','广东电信IP','广西电信IP','广西电信PON','广西电信智能提速','甘肃电信DACS','甘肃电信IP','福建电信IPRAN','福建电信IP','重庆联通IP'] 
			}
	    ],
	    series : [
	        {
	            name:'开发流程',
	            type:'bar',
	            stack: '工时',
	            itemStyle : { normal: {label : {show: true, position: 'insideRight'}}},
	          data:[0,0,98.5,80,0,0,80.5,74.5,0,0] 
	        },
	        {
	            name:'实施流程',
	            type:'bar',
	            stack: '工时',
	            itemStyle : { normal: {label : {show: true, position: 'insideRight'}}},
	        	data:[136,151,136.5,178.5,0,128,103,0,90,71] 
	         },
	        {
	            name:'CASE流程',
	            type:'bar',
	            stack: '工时',
	            itemStyle : { normal: {label : {show: true, position: 'insideRight'}}},
	         	data:[61.5,55.5,123.5,139,70,0,55,0,198.5,0] 
	         },
	        {
	            name:'内部流程',
	            type:'bar',
	            stack: '工时',
	            itemStyle : { normal: {label : {show: true, position: 'insideRight'}}},
	            data:[0,0,0,0,0,0,0,0,0,0]
	        }
	    ]
	}
	return option;
}



