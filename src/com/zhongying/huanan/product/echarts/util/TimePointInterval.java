package com.zhongying.huanan.product.echarts.util;

public class TimePointInterval {
	public static void main(String[] args) {
		String beginDate="201508140100";
		String endDate="201508140300";
		
		sumMinsInterval(beginDate,endDate);
	}
	
	
	public static void sumMinsInterval(String beginDate,String endDate){
		double mins=Double.parseDouble(endDate)-Double.parseDouble(beginDate);
		double hours=mins/60;
		double pointInterval =hours*60;
		System.out.println(pointInterval+"--"+pointInterval);
	}
	
}
