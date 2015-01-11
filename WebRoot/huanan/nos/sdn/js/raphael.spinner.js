Raphael.fn.spinner = function (R1, R2, count, stroke_width, colour){
	var pWidth = this.width,
		pHeight = this.height;
	var sectorsCount = count || 12,
	    color = colour || "#fff",
	    width = stroke_width || 15,
	    r1 = Math.min(R1, R2) || 35,
	    r2 = Math.max(R1, R2) || 60,
	    cx = pWidth/2,
	    cy = pHeight/2,
	    
	    sectors = [],
	    opacity = [],
	    beta = 2 * Math.PI / sectorsCount,
	
	    pathParams = {stroke: color, "stroke-width": width, "stroke-linecap": "round"};
	    Raphael.getColor.reset();
	for (var i = 0; i < sectorsCount; i++) {
	    var alpha = beta * i - Math.PI / 2,
	        cos = Math.cos(alpha),
	        sin = Math.sin(alpha);
	    opacity[i] = 1 / sectorsCount * i;
	    sectors[i] = this.path([["M", cx + r1 * cos, cy + r1 * sin], ["L", cx + r2 * cos, cy + r2 * sin]]).attr(pathParams);
	    if (color == "rainbow") {
	        sectors[i].attr("stroke", Raphael.getColor());
	    }
	}
	var tick;
	(function ticker() {
	    opacity.unshift(opacity.pop());
	    for (var i = 0; i < sectorsCount; i++) {
	        sectors[i].attr("opacity", opacity[i]);
	    }
	    tick = setTimeout(ticker, 1000 / sectorsCount);
	})();
	return {
		tick:tick,
		sectors:sectors
	};
}
Raphael.fn.removeSpinner = function (spinner){
	var tick = spinner.tick,
		sectors = spinner.sectors;
	clearTimeout(tick);
    for(var i=0,len=sectors.length;i<len;i++){
    	sectors[i].remove();
    }
}