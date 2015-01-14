
//广东电信研究院400G平台拓扑
//本代码有drawDevByNode.ftl生成


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
			
			var xPIDC = 380, yPIDC = 250;
			var PIDC = R.image("css/images/cloud.png",xPIDC,yPIDC,cloudWidth-xIDCloud,cloudWidth-yIDCloud);
			var PIDCTxt = R.text(xPIDC+(cloudWidth-xIDCloud)/2,yPIDC+(cloudWidth-yIDCloud)/2,"省IDC").attr({"fill":"#888","font-size":14,"font-weight":"bold"});

			var xGZIDC = 550, yGZIDC = 250;
			var GZIDC = R.image("css/images/cloud.png",xGZIDC,yGZIDC,cloudWidth-xIDCloud,cloudWidth-yIDCloud);
			var GZIDCTxt = R.text(xGZIDC+(cloudWidth-xIDCloud)/2,yGZIDC+(cloudWidth-yIDCloud)/2,"广州IDC").attr({"fill":"#888","font-size":14,"font-weight":"bold"});
			
			var xSZIDC = 700, ySZIDC = 250;
			var SZIDC = R.image("css/images/cloud.png",xSZIDC,yGZIDC,cloudWidth-xIDCloud,cloudWidth-yIDCloud);
			var SZIDCTxt = R.text(xSZIDC+(cloudWidth-xIDCloud)/2,yGZIDC+(cloudWidth-yIDCloud)/2,"深圳IDC").attr({"fill":"#888","font-size":14,"font-weight":"bold"});
			
			//----C/D
			var xGZC = 100, yGZC = 150;
			var GZC = R.image("css/images/cloud.png",xGZC,yGZC,cloudWidth,cloudWidth);
			var testGZC = R.text(xGZC+cloudWidth/2,yGZC+cloudWidth/2,"C").attr({"fill":"#888","font-size":14,"font-weight":"bold"});
			
			var xSZD = 100, ySZD = 350;
			var SZD = R.image("css/images/cloud.png",xSZD,ySZD,cloudWidth,cloudWidth);
			var SZDTxt = R.text(xSZD+cloudWidth/2,ySZD+cloudWidth/2,"D").attr({"fill":"#888","font-size":14,"font-weight":"bold"});
			
			//----转发平台
			var xEX = 900, yEX = 250;
			var EX = R.image("css/images/cloud.png",xEX,yEX,cloudWidth,cloudWidth);
			var EXTxt = R.text(xEX+cloudWidth/2,yEX+cloudWidth/2,"转发平台").attr({"fill":"#888","font-size":14,"font-weight":"bold"});
			
			
		 	var DEV00527  = R.image("css/images/router_normal.png",xDG-(30+routerClear),yDG+(0*30+routerClear),routerWidth,routerWidth);
			var DEV00527Txt="DG-HF-CR-1.MAN.TXP4-3D";
			raphael_tooltip(R,DEV00527,DEV00527Txt,xDG+20,yDG-10);
		
			var DEV00526  = R.image("css/images/router_normal.png",xDG+(30),yDG+(0*30+routerClear),routerWidth,routerWidth);
			var DEV00526Txt="DG-GC-CR-1.MAN.NE5000E";
			raphael_tooltip(R,DEV00526,DEV00526Txt,xDG+20,yDG-10);
		
		 	var DEV00051  = R.image("css/images/router_normal.png",xFS-(30+routerClear),yFS+(0*30+routerClear),routerWidth,routerWidth);
			var DEV00051Txt="FS-SDNQ-CR-1.MAN.TX.RE1";
			raphael_tooltip(R,DEV00051,DEV00051Txt,xFS+20,yFS-10);
		
			var DEV00050  = R.image("css/images/router_normal.png",xFS+(30),yFS+(0*30+routerClear),routerWidth,routerWidth);
			var DEV00050Txt="FS-NHGJ-CR-1.MAN.TX.RE1";
			raphael_tooltip(R,DEV00050,DEV00050Txt,xFS+20,yFS-10);
		
		 	var DEV007v7  = R.image("css/images/router_normal.png",xGZ-(30+routerClear),yGZ+(0*30+routerClear),routerWidth,routerWidth);
			var DEV007v7Txt="SFC0-GZ-JCX-CR-1.MAN.TXP4.RE1";
			raphael_tooltip(R,DEV007v7,DEV007v7Txt,xGZ+20,yGZ-10);
		
			var DEV007pn  = R.image("css/images/router_normal.png",xGZ+(30),yGZ+(0*30+routerClear),routerWidth,routerWidth);
			var DEV007pnTxt="GZ-DXDS-CR-1.MAN.CRS";
			raphael_tooltip(R,DEV007pn,DEV007pnTxt,xGZ+20,yGZ-10);
		
		 	var DEV005yc  = R.image("css/images/router_normal.png",xGZC-(30+routerClear),yGZC+(0*30+routerClear),routerWidth,routerWidth);
			var DEV005ycTxt="GZ-PYXDG-CR-2.MAN.NE5000E";
			raphael_tooltip(R,DEV005yc,DEV005ycTxt,xGZC+20,yGZC-10);
		
			var DEV002h0  = R.image("css/images/router_normal.png",xGZC+(30),yGZC+(0*30+routerClear),routerWidth,routerWidth);
			var DEV002h0Txt="GD-GZ-TH-CR-1.gd";
			raphael_tooltip(R,DEV002h0,DEV002h0Txt,xGZC+20,yGZC-10);
		
		 	var DEV002h1  = R.image("css/images/router_normal.png",xGZC-(30+routerClear),yGZC+(1*30+routerClear),routerWidth,routerWidth);
			var DEV002h1Txt="GD-GZ-JCX-CR-1.gd";
			raphael_tooltip(R,DEV002h1,DEV002h1Txt,xGZC+20,yGZC-10);
		
			var DEV0045b  = R.image("css/images/router_normal.png",xGZC+(30),yGZC+(1*30+routerClear),routerWidth,routerWidth);
			var DEV0045bTxt="GD-GZ-TH-C-3.163";
			raphael_tooltip(R,DEV0045b,DEV0045bTxt,xGZC+20,yGZC-10);
		
		 	var DEV0055f  = R.image("css/images/router_normal.png",xGZC-(30+routerClear),yGZC+(2*30+routerClear),routerWidth,routerWidth);
			var DEV0055fTxt="GD-GZ-JCX-C-1.163.cn.net";
			raphael_tooltip(R,DEV0055f,DEV0055fTxt,xGZC+20,yGZC-10);
		
			var DEV005yb  = R.image("css/images/router_normal.png",xGZC+(30),yGZC+(2*30+routerClear),routerWidth,routerWidth);
			var DEV005ybTxt="GZ-PYXDG-CR-1.MAN.NE5000E";
			raphael_tooltip(R,DEV005yb,DEV005ybTxt,xGZC+20,yGZC-10);
		
		 	var DEV01863  = R.image("css/images/router_normal.png",xGZC-(30+routerClear),yGZC+(3*30+routerClear),routerWidth,routerWidth);
			var DEV01863Txt="GD-GZ-TH-C-4.163";
			raphael_tooltip(R,DEV01863,DEV01863Txt,xGZC+20,yGZC-10);
		
			var DEV01862  = R.image("css/images/router_normal.png",xGZC+(30),yGZC+(3*30+routerClear),routerWidth,routerWidth);
			var DEV01862Txt="GD-GZ-JCX-C-2.163";
			raphael_tooltip(R,DEV01862,DEV01862Txt,xGZC+20,yGZC-10);
		
		 	var DEV002gt  = R.image("css/images/router_normal.png",xGZIDC-(30+routerClear),yGZIDC+(0*30+routerClear),routerWidth,routerWidth);
			var DEV002gtTxt="GZ-JCX-CR-1.I.CRS";
			raphael_tooltip(R,DEV002gt,DEV002gtTxt,xGZIDC+20,yGZIDC-10);
		
			var DEV002gu  = R.image("css/images/router_normal.png",xGZIDC+(30),yGZIDC+(0*30+routerClear),routerWidth,routerWidth);
			var DEV002guTxt="GZ-RJY-CR-1.I.CRS";
			raphael_tooltip(R,DEV002gu,DEV002guTxt,xGZIDC+20,yGZIDC-10);
		
		 	var DEV0056f  = R.image("css/images/router_normal.png",xPIDC-(30+routerClear),yPIDC+(0*30+routerClear),routerWidth,routerWidth);
			var DEV0056fTxt="GD-SZ-SN-CR-1.I.gd";
			raphael_tooltip(R,DEV0056f,DEV0056fTxt,xPIDC+20,yPIDC-10);
		
			var DEV0056g  = R.image("css/images/router_normal.png",xPIDC+(30),yPIDC+(0*30+routerClear),routerWidth,routerWidth);
			var DEV0056gTxt="GD-SZ-XNT-CR-1.I.gd";
			raphael_tooltip(R,DEV0056g,DEV0056gTxt,xPIDC+20,yPIDC-10);
		
		 	var DEV0056d  = R.image("css/images/router_normal.png",xPIDC-(30+routerClear),yPIDC+(1*30+routerClear),routerWidth,routerWidth);
			var DEV0056dTxt="GD-GZ-JCX-CR-1.I.gd";
			raphael_tooltip(R,DEV0056d,DEV0056dTxt,xPIDC+20,yPIDC-10);
		
			var DEV0056e  = R.image("css/images/router_normal.png",xPIDC+(30),yPIDC+(1*30+routerClear),routerWidth,routerWidth);
			var DEV0056eTxt="GD-GZ-TH-CR-1.I.gd";
			raphael_tooltip(R,DEV0056e,DEV0056eTxt,xPIDC+20,yPIDC-10);
		
		 	var DEV00f5f  = R.image("css/images/router_normal.png",xSZ-(30+routerClear),ySZ+(0*30+routerClear),routerWidth,routerWidth);
			var DEV00f5fTxt="SZ-XNT-CR-1.MAN.NE5000E";
			raphael_tooltip(R,DEV00f5f,DEV00f5fTxt,xSZ+20,ySZ-10);
		
			var DEV0063b  = R.image("css/images/router_normal.png",xSZ+(30),ySZ+(0*30+routerClear),routerWidth,routerWidth);
			var DEV0063bTxt="SZ-SNL-CR-1.MAN.NE5000E";
			raphael_tooltip(R,DEV0063b,DEV0063bTxt,xSZ+20,ySZ-10);
		
		 	var DEV01286  = R.image("css/images/router_normal.png",xSZD-(30+routerClear),ySZD+(0*30+routerClear),routerWidth,routerWidth);
			var DEV01286Txt="GD-SZ-XNT-D-4.163";
			raphael_tooltip(R,DEV01286,DEV01286Txt,xSZD+20,ySZD-10);
		
			var DEV01285  = R.image("css/images/router_normal.png",xSZD+(30),ySZD+(0*30+routerClear),routerWidth,routerWidth);
			var DEV01285Txt="GD-SZ-SNL-D-3.163";
			raphael_tooltip(R,DEV01285,DEV01285Txt,xSZD+20,ySZD-10);
		
		 	var DEV008hz  = R.image("css/images/router_normal.png",xSZD-(30+routerClear),ySZD+(1*30+routerClear),routerWidth,routerWidth);
			var DEV008hzTxt="GD-SZ-XNT-D-2.163.RE0";
			raphael_tooltip(R,DEV008hz,DEV008hzTxt,xSZD+20,ySZD-10);
		
			var DEV008nj  = R.image("css/images/router_normal.png",xSZD+(30),ySZD+(1*30+routerClear),routerWidth,routerWidth);
			var DEV008njTxt="GD-SZ-SNL-D-1.163.RE0";
			raphael_tooltip(R,DEV008nj,DEV008njTxt,xSZD+20,ySZD-10);
		
		 	var DEV0079k  = R.image("css/images/router_normal.png",xSZIDC-(30+routerClear),ySZIDC+(0*30+routerClear),routerWidth,routerWidth);
			var DEV0079kTxt="SZ-XNT-CR-1.IDC.NE5000E";
			raphael_tooltip(R,DEV0079k,DEV0079kTxt,xSZIDC+20,ySZIDC-10);
		
			var DEV0079j  = R.image("css/images/router_normal.png",xSZIDC+(30),ySZIDC+(0*30+routerClear),routerWidth,routerWidth);
			var DEV0079jTxt="SZ-SN-CR-1.IDC.NE5000E";
			raphael_tooltip(R,DEV0079j,DEV0079jTxt,xSZIDC+20,ySZIDC-10);
		
		 	var DEV004uc  = R.image("css/images/router_normal.png",xYB-(30+routerClear),yYB+(0*30+routerClear),routerWidth,routerWidth);
			var DEV004ucTxt="SZ-XNT-CR-1.MAN.YB";
			raphael_tooltip(R,DEV004uc,DEV004ucTxt,xYB+20,yYB-10);
		
			var DEV004ub  = R.image("css/images/router_normal.png",xYB+(30),yYB+(0*30+routerClear),routerWidth,routerWidth);
			var DEV004ubTxt="GZ-TH-CR-1.MAN.YB";
			raphael_tooltip(R,DEV004ub,DEV004ubTxt,xYB+20,yYB-10);
		
		 	var DEV0053c  = R.image("css/images/router_normal.png",xYD-(30+routerClear),yYD+(0*30+routerClear),routerWidth,routerWidth);
			var DEV0053cTxt="SZ-XNT-CR-1.MAN.YD";
			raphael_tooltip(R,DEV0053c,DEV0053cTxt,xYD+20,yYD-10);
		
			var DEV0053a  = R.image("css/images/router_normal.png",xYD+(30),yYD+(0*30+routerClear),routerWidth,routerWidth);
			var DEV0053aTxt="GZ-TH-CR-1.MAN.YD";
			raphael_tooltip(R,DEV0053a,DEV0053aTxt,xYD+20,yYD-10);
		
		 	var DEV005ev  = R.image("css/images/router_normal.png",xYX-(30+routerClear),yYX+(0*30+routerClear),routerWidth,routerWidth);
			var DEV005evTxt="GZ-TH-CR-1.MAN.YX";
			raphael_tooltip(R,DEV005ev,DEV005evTxt,xYX+20,yYX-10);
		
			var DEV005ew  = R.image("css/images/router_normal.png",xYX+(30),yYX+(0*30+routerClear),routerWidth,routerWidth);
			var DEV005ewTxt="SZ-XNT-CR-1.MAN.YX";
			raphael_tooltip(R,DEV005ew,DEV005ewTxt,xYX+20,yYX-10);
		
		 	var DEV0022w  = R.image("css/images/router_normal.png",xZSJ-(30+routerClear),yZSJ+(0*30+routerClear),routerWidth,routerWidth);
			var DEV0022wTxt="SZ-XNT-CR-1.MAN.ZSJ";
			raphael_tooltip(R,DEV0022w,DEV0022wTxt,xZSJ+20,yZSJ-10);
		
			var DEV0022v  = R.image("css/images/router_normal.png",xZSJ+(30),yZSJ+(0*30+routerClear),routerWidth,routerWidth);
			var DEV0022vTxt="GZ-TH-CR-1.MAN.ZSJ.TXP-3D";
			raphael_tooltip(R,DEV0022v,DEV0022vTxt,xZSJ+20,yZSJ-10);
		

R.line(ZSJ,SZD,0);
R.line(ZSJ,SZD,0);
R.line(ZSJ,GZC,0);
R.line(ZSJ,GZC,0);
R.line(YX,SZD,0);
R.line(YX,SZD,0);
R.line(YX,GZC,0);
R.line(YX,GZC,0);
R.line(YD,SZD,0);
R.line(YD,SZD,0);
R.line(YD,GZC,0);
R.line(YD,GZC,0);
R.line(YB,SZD,0);
R.line(YB,SZD,0);
R.line(YB,GZC,0);
R.line(YB,GZC,0);
R.line(SZ,SZD,0);
R.line(SZ,SZD,0);
R.line(SZ,GZC,0);
R.line(SZ,GZC,0);
R.line(SZIDC,SZD,0);
R.line(SZIDC,SZD,0);
R.line(SZIDC,GZC,0);
R.line(SZIDC,GZC,0);
R.line(SZ,SZD,0);
R.line(SZ,SZD,0);
R.line(SZ,GZC,0);
R.line(SZ,GZC,0);
R.line(SZIDC,SZD,0);
R.line(SZIDC,SZD,0);
R.line(SZIDC,GZC,0);
R.line(SZIDC,GZC,0);
R.line(GZ,SZD,0);
R.line(GZ,SZD,0);
R.line(GZ,GZC,0);
R.line(GZ,GZC,0);
R.line(ZSJ,SZD,0);
R.line(ZSJ,SZD,0);
R.line(ZSJ,GZC,0);
R.line(ZSJ,GZC,0);
R.line(YX,SZD,0);
R.line(YX,SZD,0);
R.line(YX,GZC,0);
R.line(YX,GZC,0);
R.line(YD,SZD,0);
R.line(YD,SZD,0);
R.line(YD,GZC,0);
R.line(YD,GZC,0);
R.line(YB,SZD,0);
R.line(YB,SZD,0);
R.line(YB,GZC,0);
R.line(YB,GZC,0);
R.line(GZIDC,SZD,0);
R.line(GZIDC,SZD,0);
R.line(GZIDC,GZC,0);
R.line(GZIDC,GZC,0);
R.line(GZC,GZC,0);
R.line(GZC,GZC,0);
R.line(GZIDC,SZD,0);
R.line(GZIDC,SZD,0);
R.line(GZIDC,GZC,0);
R.line(GZIDC,GZC,0);
R.line(GZ,SZD,0);
R.line(GZ,SZD,0);
R.line(GZ,SZD,0);
R.line(GZ,GZC,0);
R.line(GZ,GZC,0);
R.line(PIDC,SZD,0);
R.line(PIDC,SZD,0);
R.line(PIDC,SZD,0);
R.line(PIDC,SZD,0);
R.line(GZC,SZD,0);
R.line(GZC,GZC,0);
R.line(GZC,GZC,0);
R.line(PIDC,GZC,0);
R.line(PIDC,GZC,0);
R.line(GZC,SZD,0);
R.line(GZC,GZC,0);
R.line(GZC,GZC,0);
R.line(PIDC,GZC,0);
R.line(PIDC,GZC,0);
R.line(FS,SZD,0);
R.line(FS,SZD,0);
R.line(FS,GZC,0);
R.line(FS,GZC,0);
R.line(FS,SZD,0);
R.line(FS,SZD,0);
R.line(FS,GZC,0);
R.line(FS,GZC,0);
R.line(DG,SZD,0);
R.line(DG,SZD,0);
R.line(DG,GZC,0);
R.line(DG,GZC,0);
R.line(DG,SZD,0);
R.line(DG,SZD,0);
R.line(DG,GZC,0);
R.line(DG,GZC,0);
}