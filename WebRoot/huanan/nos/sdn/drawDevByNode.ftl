<#--- 
广东电信研究院400G平台拓扑
create:2015-01-08
-->

//广东电信研究院400G平台拓扑
//本代码有drawDevByNode.ftl生成

<#list devlist as dev>  
<#--	${dev}  -->
</#list>

<#--foreach map
<#list devMap?keys as key>  
		${key}：${devMap[key]} 
	</#list>
-->
var imageWidth = 64;
var cloudWidth = 60;

var routerWidth= 70;
var routerClear= 15;
//查询平台地市接入拓扑信息
function loadGDCityTopoInfo(para){
		//清除loading
		R.removeSpinner(loading);
		
			var	r = 300;//画布
			
			var margin = 40;//
			
			
			var rWidth = $("#topoHolder").width();
			var rHeight = $("#topoHolder").height()<2*margin+2*r+imageWidth?2*margin+2*r+imageWidth:$("#topoHolder").height();
			
			$("#topoHolder").height(rHeight);
			R.setSize(rWidth,rHeight);
			
			var xGZ = 150, yGZ = 30;
			var GZ = R.image("css/images/cloud.png",xGZ,yGZ,cloudWidth,cloudWidth);
			var GZTxt = R.text(xGZ+cloudWidth/2,yGZ+cloudWidth/2,"广州").attr({"fill":"#888","font-size":14,"font-weight":"bold"});
			
			
			var xSZ = 350, ySZ = 30;
			var SZ = R.image("css/images/cloud.png",xSZ,ySZ,cloudWidth,cloudWidth);
			var SZTxt = R.text(xSZ+cloudWidth/2,ySZ+cloudWidth/2,"深圳").attr({"fill":"#888","font-size":14,"font-weight":"bold"});
			
			var xDG = 600, yDG = 30;
			var DG = R.image("css/images/cloud.png",xDG,yDG,cloudWidth,cloudWidth);
			var DGTxt = R.text(xDG+cloudWidth/2,yDG+cloudWidth/2,"东莞").attr({"fill":"#888","font-size":14,"font-weight":"bold"});
			
			var xFS = 850, yFS = 30;
			var FS = R.image("css/images/cloud.png",xFS,yFS,cloudWidth,cloudWidth);
			var FSTxt = R.text(xFS+cloudWidth/2,yFS+cloudWidth/2,"佛山").attr({"fill":"#888","font-size":14,"font-weight":"bold"});
			
			//----
			var xZSJ = 150, yZSJ = 500;
			var ZSJ = R.image("css/images/cloud.png",xZSJ,yZSJ,cloudWidth,cloudWidth);
			var ZSJTxt = R.text(xZSJ+cloudWidth/2,yZSJ+cloudWidth/2,"珠三角").attr({"fill":"#888","font-size":14,"font-weight":"bold"});
			
			var xYD = 350, yYD = 500;
			var YD = R.image("css/images/cloud.png",xYD,yYD,cloudWidth,cloudWidth);
			var YDTxt = R.text(xYD+cloudWidth/2,yYD+cloudWidth/2,"粤东").attr({"fill":"#888","font-size":14,"font-weight":"bold"});

			
			var xYX = 600, yYX = 500;
			var YX = R.image("css/images/cloud.png",xYX,yYX,cloudWidth,cloudWidth);
			var YXTxt = R.text(xYX+cloudWidth/2,yYX+cloudWidth/2,"粤西").attr({"fill":"#888","font-size":14,"font-weight":"bold"});
			
			var xYB = 850, yYB = 500;
			var YB = R.image("css/images/cloud.png",xYB,yYB,cloudWidth,cloudWidth);
			var YBTxt = R.text(xYB+cloudWidth/2,yYB+cloudWidth/2,"粤北").attr({"fill":"#888","font-size":14,"font-weight":"bold"});
			

				
			//-------------------
			var xIDCloud=5,yIDCloud=10;
			
			var xPIDC = 350, yPIDC = 250;
			var GZIDC = R.image("css/images/cloud.png",xPIDC,yPIDC,cloudWidth-xIDCloud,cloudWidth-yIDCloud);
			var PIDCTxt = R.text(xPIDC+(cloudWidth-xIDCloud)/2,yPIDC+(cloudWidth-yIDCloud)/2,"省IDC").attr({"fill":"#888","font-size":14,"font-weight":"bold"});

			var xGZIDC = 550, yGZIDC = 250;
			var GZIDC = R.image("css/images/cloud.png",xGZIDC,yGZIDC,cloudWidth-xIDCloud,cloudWidth-yIDCloud);
			var GZIDCTxt = R.text(xGZIDC+(cloudWidth-xIDCloud)/2,yGZIDC+(cloudWidth-yIDCloud)/2,"广州IDC").attr({"fill":"#888","font-size":14,"font-weight":"bold"});
			
			var xSZIDC = 700, ySZIDC = 250;
			var SZIDC = R.image("css/images/cloud.png",xSZIDC,yGZIDC,cloudWidth-xIDCloud,cloudWidth-yIDCloud);
			var SZIDCTxt = R.text(xSZIDC+(cloudWidth-xIDCloud)/2,yGZIDC+(cloudWidth-yIDCloud)/2,"深圳IDC").attr({"fill":"#888","font-size":14,"font-weight":"bold"});
			
			//----C/D
			var xGZC = 100, yGZC = 150;
			var nodeGZC = R.image("css/images/cloud.png",xGZC,yGZC,cloudWidth,cloudWidth);
			var testGZC = R.text(xGZC+cloudWidth/2,yGZC+cloudWidth/2,"C").attr({"fill":"#888","font-size":14,"font-weight":"bold"});
			var xSZD = 100, ySZD = 350;
			var SZD = R.image("css/images/cloud.png",xSZD,ySZD,cloudWidth,cloudWidth);
			var SZDTxt = R.text(xSZD+cloudWidth/2,ySZD+cloudWidth/2,"D").attr({"fill":"#888","font-size":14,"font-weight":"bold"});
			
			//----转发平台
			var xEX = 900, yEX = 250;
			var EX = R.image("css/images/cloud.png",xEX,yEX,cloudWidth,cloudWidth);
			var EXTxt = R.text(xEX+cloudWidth/2,yEX+cloudWidth/2,"转发平台").attr({"fill":"#888","font-size":14,"font-weight":"bold"});
			
			<#assign prenode="" />
			<#assign sugx=0 />
			<#assign sugy=0 />
			<#assign sugcount=0 />
			<#assign oddcount=0 />
			<#assign ncount=0 />
			
<#list devMaplist as devMap>  
		<#assign node=devMap.node />
		<#if node = prenode>
			<#assign sugcount=sugcount+1 />
		<#else>  
			<#assign sugcount=1 />
			<#assign oddcount=0 />
			<#assign ncount=0 />
		</#if>  
		<#assign sugx=30 />
		 <#if (sugcount%2!=0)>
		 	var ${devMap["deviceid"]}  = R.image("css/images/router_normal.png",x${devMap["node"]}-(${sugx}+routerClear),y${devMap["node"]}+(${oddcount}*${sugx}+routerClear),routerWidth,routerWidth);
			var ${devMap["deviceid"]}Txt="${devMap["devicename"]}";
			raphael_tooltip(R,${devMap["deviceid"]},${devMap["deviceid"]}Txt,x${devMap["node"]}+50,y${devMap["node"]}-30-10);
			<#assign oddcount=oddcount+1 />
		</#if>
		 <#if (sugcount%2==0)>
			var ${devMap["deviceid"]}  = R.image("css/images/router_normal.png",x${devMap["node"]}+(${sugx}),y${devMap["node"]}+(${ncount}*${sugx}+routerClear),routerWidth,routerWidth);
			var ${devMap["deviceid"]}Txt="${devMap["devicename"]}";
			raphael_tooltip(R,${devMap["deviceid"]},${devMap["deviceid"]}Txt,x${devMap["node"]}+50,y${devMap["node"]}-30-10);
			<#assign ncount=ncount+1 />
		</#if>
		
		<#assign prenode=devMap.node />
</#list>

<#list cirMaplist as cirMap> 
R.line(${cirMap["deviceA"]},${cirMap["deviceB"]},0);
</#list>				
}