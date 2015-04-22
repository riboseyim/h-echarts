
function optionTrackBarTotal30() {

	var option = {
		title : {
			text : '专线自动施工成功率'
		},
		tooltip : {
			trigger : 'axis'
		},
		legend : {
			data : [ '成功', '回退' ]
		},
		toolbox : {
			show : true,
			feature : {
				mark : {
					show : true
				},
				dataView : {
					show : true,
					readOnly : false
				},
				magicType : {
					show : true,
					type : [ 'line', 'bar' ]
				},
				restore : {
					show : true
				},
				saveAsImage : {
					show : true
				}
			}
		},
		calculable : true,
		grid : {
			x : 45,
			y : 45,
			x2 : 30,
			y2 : 40
		},
		xAxis : [
				{
					type : 'category',
					data : [ '0323', '0324', '0325', '0326', '0327', '0328',
							'0329', '0330', '0331', '0401', '0402', '0403',
							'0404', '0405', '0406', '0407', '0408', '0409',
							'0410', '0411', '0412', '0413', '0414', '0415',
							'0416', '0417', '0418', '0419', '0420', '0421',
							'0422' ]
				}, {
					type : 'category',
					axisLine : {
						show : false
					},
					axisTick : {
						show : false
					},
					axisLabel : {
						show : false
					},
					splitArea : {
						show : false
					},
					splitLine : {
						show : false
					},
					data : [ 'Line', 'Bar', 'Scatter', 'K', 'Map' ]
				} ],
		yAxis : [ {
			type : 'value',
			name : '专线',
			axisLabel : {
				formatter : '{value} 条'
			}
		}, {
			type : 'value',
			name : '数量',
			axisLabel : {
				formatter : '{value} 个'
			}
		}, {
			type : 'value',
			name : '数量',
			axisLabel : {
				formatter : '{value} 个'
			}
		} ],
		series : [
				{
					name : '成功',
					type : 'bar',
					tooltip : {
						trigger : 'item'
					},
					stack : '总工时',
					itemStyle : {
						normal : {
							color : 'rgba(121,195,52,1)',
							label : {
								show : false,
								textStyle : {
									color : '#27727B'
								}
							}
						}
					},
					data : [ 18, 5, 11, 11, 13, 1, 7, 53, 32, 29, 16, 21, 4, 3,
							5, 22, 13, 24, 11, 3, 3, 32, 22, 20, 17, 30, 5, 7,
							410, 90, 23 ]
				},
				{
					name : '回退',
					type : 'bar',
					tooltip : {
						trigger : 'item'
					},
					stack : '总工时',
					itemStyle : {
						normal : {
							color : 'rgba(221,215,72,21)',
							label : {
								show : false,
								textStyle : {
									color : '#27727B'
								}
							}
						}
					},
					data : [ 0, 0, 1, 0, 1, 0, 0, 1, 4, 1, 5, 4, 0, 0, 3, 3, 0,
							2, 1, 1, 1, 5, 9, 0, 2, 3, 1, 1, 16, 0, 5 ]
				} ]
	};

	return option;
}
