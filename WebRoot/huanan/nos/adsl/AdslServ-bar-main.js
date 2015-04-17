//Remine应用视图
//create:2015-01-14
//author:yanrui

function requireCallback (ec, defaultTheme) {
    curTheme = themeSelector ? defaultTheme : {};
    echarts = ec;
    refresh();
}

function refresh(isBtnRefresh){
    if (isBtnRefresh) {
        needRefresh = true;
        focusGraphic();
        return;
    }
    needRefresh = false;
    
    loadTrackBar('10');
    loadTrackBar('30');   
    loadTrackBar('total30');   
}

var myTrackBarChart10;
var myTrackBarChart30;
var myTrackBarCharttotal30;

function loadTrackBar(lastdays){
	var div="trackBarDiv"+lastdays;
	var char="myTrackBarChart"+lastdays;
	var div= document.getElementById('track-bar-div-'+lastdays);
	 if (char && char.dispose) {
		 char.dispose();
	 }
	// alert(div);
	 char = echarts.init(div, curTheme);  
	 
	 if(lastdays=="10"){
		 char.setOption(optionTrackBar10()); 
	 }
	 
	 if(lastdays=="30"){
		 char.setOption(optionTrackBar30()); 
	 }
	 
	 if(lastdays=="total30"){
		 var option=optionTrackBarTotal30();
		// alert(char+"----"+option);
		 char.setOption(option); 
	 }
	
}

function _loadTrackBar(){
	var trackBarDiv= document.getElementById('track-bar-div');
	 if (myTrackBarChart && myTrackBarChart.dispose) {
		 myTrackBarChart.dispose();
	 }
	 myTrackBarChart = echarts.init(trackBarDiv, curTheme);  
	 myTrackBarChart.setOption(optionTrackBar());
}

function saveTrackBarImage(){
	var myTrackBarChartData=myTrackBarChart.getDataURL("jpeg");
	var url = "AdslServ-charts-service.jsp";
	 $.ajax({
         type:"POST",
         url:url,
         data:{
        	 methodName:"saveAdslServBar",
        	 imageName:"AdslServ-bar.jpeg",
        	 imageData:myTrackBarChartData,
        	 rand:Math.random()
        	},
         datatype: "text",             
         success:function(data){    
        	// alert("success data:"+data);
          } 
      });
}



var domCode = document.getElementById('sidebar-code');
var domGraphic = document.getElementById('graphic');
var domMessage = document.getElementById('wrong-message');
var iconResize = document.getElementById('icon-resize');
var needRefresh = false;

var enVersion = location.hash.indexOf('-en') != -1;
var hash = location.hash.replace('-en','');
hash = hash.replace('#','') || (needMap() ? 'default' : 'macarons');
hash += enVersion ? '-en' : '';

var curTheme;


function needMap() {
    var href = location.href;
    return href.indexOf('map') != -1||href.indexOf('Map') != -1
           || href.indexOf('mix3') != -1
           || href.indexOf('mix5') != -1
           || href.indexOf('dataRange') != -1;

}

var echarts;
var developMode = false;

if (developMode) {
    // for develop
    require.config({
        packages: [
            {
                name: 'echarts',
                location: '../../src',
                main: 'echarts'
            },
            {
                name: 'zrender',
                //location: 'http://ecomfe.github.io/zrender/src',
                location: '../../../zrender/src',
                main: 'zrender'
            }
        ]
    });
}
else {
    // for echarts online home page
    require.config({
        paths: {
            echarts: './www/js'
        }
    });
}

// 按需加载
require(
    [
        'echarts',
        'theme/' + hash.replace('-en', ''),
         //'echarts/chart/map',
         'echarts/chart/line',
         'echarts/chart/bar',
         //'echarts/chart/scatter',
         //'echarts/chart/k',
         //'echarts/chart/pie',
         //'echarts/chart/radar',
    ],
    requireCallback
);


var themeSelector = $('#theme-select');
if (themeSelector) {
    themeSelector.html(
        '<option selected="true" name="macarons">macarons</option>' 
        + '<option name="infographic">infographic</option>'
        + '<option name="shine">shine</option>'
        + '<option name="dark">dark</option>'
        + '<option name="blue">blue</option>'
        + '<option name="green">green</option>'
        + '<option name="red">red</option>'
        + '<option name="gray">gray</option>'
        + '<option name="helianthus">helianthus</option>'
        + '<option name="default">default</option>'
    );
    
    $(themeSelector).on('change', function(){
        selectChange($(this).val());
    });
    
    
    function selectChange(value){
        var theme = value;

     myChinaMapChart.showLoading();
     
        $(themeSelector).val(theme);
        if (theme != 'default') {
            window.location.hash = value + (enVersion ? '-en' : '');
            require(['theme/' + theme], function(tarTheme){
                curTheme = tarTheme;
                setTimeout(refreshTheme, 500);
            })
        }
        else {
            window.location.hash = enVersion ? '-en' : '';
            curTheme = {};
            setTimeout(refreshTheme, 500);
        }
    }
    
    function refreshTheme(){

    	myChinaMapChart.hideLoading();
    	myChinaMapChart.setTheme(curTheme);
    }
    
    if ($(themeSelector).val(hash.replace('-en', '')).val() != hash.replace('-en', '')) {
        $(themeSelector).val('macarons');
        hash = 'macarons' + enVersion ? '-en' : '';
        window.location.hash = hash;
    }
}

function autoResize() {
    if ($(iconResize).hasClass('glyphicon-resize-full')) {
        focusCode();
        iconResize.className = 'glyphicon glyphicon-resize-small';
    }
    else {
        focusGraphic();
        iconResize.className = 'glyphicon glyphicon-resize-full';
    }
}

function focusCode() {
    domGraphic.className = 'col-md-4 ani';
}

function focusGraphic() {
    domGraphic.className = 'col-md-8 ani';
    if (needRefresh) {
    	myChinaMapChart.showLoading();
    	myTrackBarChart.showLoading();
        setTimeout(refresh, 1000);
    }
}




function _backup(){
	
	var myTrackBarChartData=myTrackBarChart.getDataURL("jpeg");
	
	var url = "AdslServ-charts-service.jsp";
	
	 $.ajax({
         //提交数据的类型 POST GET
         type:"POST",
         //提交的网址
         url:url,
         //提交的数据
         data:{
        	 methodName:"saveAdslServBar",
        	 imageName:"AdslServ-bar.jpeg",
        	 imageData:myTrackBarChartData,
        	 rand:Math.random()
        	},
         //返回数据的格式
         datatype: "text",//"xml", "html", "script", "json", "jsonp", "text".
         //在请求之前调用的函数
         beforeSend:function(){
        	// $("#msg").html("logining");
         },
         //成功返回之后调用的函数             
         success:function(data){
        	 //	$("#msg").html(decodeURI(data));      
        	// alert("success data:"+data);
          }   ,
         //调用执行后调用的函数
         complete: function(XMLHttpRequest, textStatus){
            //alert(XMLHttpRequest.responseText);
           // alert(textStatus);
             //HideLoading();
         },
         //调用出错执行的函数
         error: function(){
             //请求出错处理
         }         
      });
	
}

