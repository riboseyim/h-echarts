function raphael_tooltip(tPager,elem,text,fx,fy,fattr,tattr){
    var tip,frame;
    var topt={
	    fill:"#fff",font: '12px Î¢ÈíÑÅºÚ',opacity:1,'text-anchor':'start',cursor:"default"
    },
    fopt={
        fill: "#222", "fill-opacity": .7,stroke: "#555", "stroke-width": 1,opacity:1
    };
	if(fattr){
		for(var fr in fattr){
			fopt[fr]=fattr[fr];
		}
	}
	if(tattr){
		for(var tr in tattr){
			topt[tr]=tattr[tr];
		}
	}
	elem.hover(function(e){
		var ty = fy-10;
		var side = "top";
		var anim = Raphael.animation({opacity:1},300);
	    tip = tPager.text(0, 0, text).attr(topt);
	    if(ty-tip.getBBox().height<20)
	    {
	    	side = "bottom";
	    	ty+=30;
	    }
	    if(fx-tip.getBBox().width/2<0)
	    	side = "right";
	    if(fx+tip.getBBox().width/2>tPager.width)
	    	side = "left";
	    	
	  	frame = tPager.popup(fx,ty,tip,side).attr(fopt);
	  	frame.stop().animate(anim);
	    tip.stop().animateWith(frame, anim, {opacity:1}, 300);
	},function(){
	    if(frame&&tip){
	      tip.stop().remove();
	      frame.stop().remove();
	      tip=undefined;
	      frame=undefined;
	    }
	});
}