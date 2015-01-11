Raphael.fn.line = function (obj1, obj2, delay,line){
	var lineCss = {
			stroke:"#39FB04",
			fill:"none",
			"stroke-width":4,
			opacity:0.8,
			cursor:"pointer"
		}; 
	if(!obj1||!obj2)
		return false;
	if(undefined==delay)
		delay = 1500;
	if(undefined!=line)
		$.extend(lineCss,line);
    var bb1 = obj1.getBBox(),
        bb2 = obj2.getBBox(),
        x1=bb1.x+bb1.width/2,
        y1=bb1.y+bb1.height/2,
        x2=bb2.x+bb2.width/2,
        y2=bb2.y+bb2.height/2;
    var path = ["M", x1.toFixed(3), y1.toFixed(3), "L",x2.toFixed(3),y2.toFixed(3)].join(",");
    var temPath=["M", x1.toFixed(3), y1.toFixed(3), "L",x1.toFixed(3), y1.toFixed(3)].join(",");
    
	var bgPath=this.path(path).hide(),//基线
	    linePath=this.path(temPath).attr(lineCss),//主线
	    len=bgPath.getTotalLength(),
	    spoint = bgPath.getPointAtLength(obj1.getBBox().width/2),
	    epoint = bgPath.getPointAtLength(len-obj2.getBBox().width/2);
	linePath.cx = (x1+x2)/2;
	linePath.cy = (y1+y2)/2;
    //位置初始化
    linePath.attr(along("s",spoint,epoint));
    //划线开始
 	linePath.stop().animate(along("e",spoint,epoint), delay,function(){
       obj2.stop().animate({opacity:1},500);//显示末端
       if(undefined!=obj2.text&&null!=obj2.text)
    	   obj2.text.animate({opacity:1},500);//显示名称
    });
    return {
        line: linePath,
        path: path,
        from: obj1,
        to: obj2
    };
};
Raphael.fn.curves = function(start,end,data){
	var curves = new Array();
	var lineCss = {
			stroke:"#39FB04",
			fill:"none",
			"stroke-width":4,
			opacity:0.8,
			cursor:"pointer"
		}; 
	if(!start||!end)
		return ;
	var sBB = start.getBBox(),
		eBB = end.getBBox(),
		sX = sBB.x + sBB.width/2,
		sY = sBB.y + sBB.height/2,
		eX = eBB.x + eBB.width/2,
		eY = eBB.y + eBB.height/2,
		cX = (sX + eX)/2,
		cY = (eY + eY)/2;
	var cx1,cx2,cy1,cy2;
	if((eX-sX)==0||1<=Math.abs((eY-sY)/(eX-sX))){
		for(var i=0,len=data.length;i<len;i++){
			if(len%2==0){
				if(i%2==0){
					cx1 = cX - 20*(i/2+1);
					cy1 = cY - 20;
					cx2 = cX - 20*(i/2+1);
					cy2 = cY + 20;
				}else{
					cx1 = cX + 20*(Math.floor(i/2)+1);
					cy1 = cY - 20;
					cx2 = cX + 20*(Math.floor(i/2)+1);
					cy2 = cY + 20;
				}
			}else{
				if(i==0){
					cx1 = cX;
					cy1 = cY;
					cx2 = cX;
					cy2 = cY;
	    		}else if(i%2==0){
	    			cx1 = cX - 20*(i/2);
	    			cy1 = cY - 20;
	    			cx2 = cX - 20*(i/2);
	    			cy2 = cY + 20;
	    		}else{
	    			cx1 = cX + 20*Math.ceil(i/2);
	    			cy1 = cY - 20;
	    			cx2 = cX + 20*Math.ceil(i/2);
	    			cy2 = cY + 20;
	    		}
			}
			var alarmColor = "#39FB04";
			if(5==data[i][2])
				alarmColor = "yellow";
			else if(4==data[i][2])
				alarmColor = "orange";
			else if(5==data[i][2])
				alarmColor = "red";
			$.extend(lineCss, {stroke:alarmColor});
			var curvePath = ["M",sX, sY, "C",cx1,cy1,cx2,cy2 ,eX,eY].join(",");
			var curve = this.path(curvePath).attr(lineCss);
			curve.toBack();
			curves.push(curve);
		}
	}else if(1>Math.abs((eY-sY)/(eX-sX))){
		for(var i=0,len=data.length;i<len;i++){
			if(len%2==0){
				if(i%2==0){
					cx1 = cX - 20;
					cy1 = cY - 20*(i/2+1);
					cx2 = cX + 20;
					cy2 = cY - 20*(i/2+1);
				}else{
					cx1 = cX - 20;
					cy1 = cY + 20*(Math.floor(i/2)+1);
					cx2 = cX + 20;
					cy2 = cY + 20*(Math.floor(i/2)+1);
				}
			}else{
				if(i==0){
					cx1 = cX;
					cy1 = cY;
					cx2 = cX;
					cy2 = cY;
	    		}else if(i%2==0){
	    			cx1 = cX - 20;
	    			cy1 = cY - 20*(i/2);
	    			cx2 = cX + 20;
	    			cy2 = cY - 20*(i/2);
	    		}else{
	    			cx1 = cX - 20;
	    			cy1 = cY + 20*Math.ceil(i/2);
	    			cx2 = cX + 20;
	    			cy2 = cY + 20*Math.ceil(i/2);
	    		}
			}
			var alarmColor = "#39FB04";
			if(5==data[i][2])
				alarmColor = "yellow";
			else if(4==data[i][2])
				alarmColor = "orange";
			else if(5==data[i][2])
				alarmColor = "red";
			$.extend(lineCss, {stroke:alarmColor});
			var curvePath = ["M",sX, sY, "C",cx1,cy1,cx2,cy2 ,eX,eY].join(",");
			var curve = this.path(curvePath).attr(lineCss);
			curve.toBack();
			curves.push(curve);
		}
	}
	return curves;
}
function along(ff,spoint,epoint) {
    if(ff=="s")
        return { path:["M",spoint.x.toFixed(3), spoint.y.toFixed(3), "L",spoint.x.toFixed(3),spoint.y.toFixed(3)].join(",")};
    if(ff=="e")
        return { path:["M",spoint.x.toFixed(3), spoint.y.toFixed(3), "L",epoint.x.toFixed(3),epoint.y.toFixed(3)].join(",")};
}