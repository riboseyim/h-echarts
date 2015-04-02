package com.zhongying.huanan.product.adsl.echarts;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zhongying.huanan.product.adsl.echarts.util.DBConn;
import com.zhongying.huanan.product.adsl.echarts.util.EchartsConfig;
import com.zhongying.huanan.product.adsl.echarts.util.ToolUtil;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class GenAdslServBarJS  {
	
	public static void main(String[] args) {
		UpdateAdslServEchartsBar();
	}
	
	public static void UpdateAdslServEchartsBar(){
		try {
			Connection con = null;
			PreparedStatement psd = null;
			ResultSet rs = null;
		
				DBConn db = new DBConn();
				con = db.getDirectConn();
				
			System.err.println("-----1-----"+ToolUtil.getAccurateTime());
			String TEMPLATEDIR = EchartsConfig.AdslEchartsAppDir + File.separator+"ftl";

			String TEMPLATENAME = "drawAdslServBarOption.ftl";
			String outpath = EchartsConfig.AdslEchartsAppDir +File.separator+ "AdslServ-bar-option.js";
			
			System.err.println("-----2-----"+ToolUtil.getAccurateTime());
			Map<String, Object> paramMap = loadData(con);
			
			System.err.println("-----3-----"+ToolUtil.getAccurateTime());
			
			execTemplate(paramMap, TEMPLATEDIR, TEMPLATENAME, outpath);

			System.err.println("-----4-----"+ToolUtil.getAccurateTime());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void execTemplate(Map<String, Object> paramMap, String templatedir, String templatename, String outpath) {
		Configuration cfg = Configuration.getDefaultConfiguration();
		try {

			// 从哪里加载模板文件
			cfg.setDirectoryForTemplateLoading(new File(templatedir));
			// 通过freemarker解释模板，首先需要获得Template对象
			Template template = cfg.getTemplate(templatename);

			// 定义模板解释完成之后的输出
			PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(outpath)));
			try {
				// 解释模板
				template.process(paramMap, writer);
				writer.flush();
			} catch (TemplateException e) {
				e.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 加载数据
	public static Map<String, Object> loadData(Connection con) throws Exception {
		Map<String, Object> paramMap = new HashMap<String, Object>();

		String days = "";
		String addnums = "";
		String deactivenums = "";
		String activenums = "";
		String failednums = "";
		GenAdslServService service = new GenAdslServService();
		
		
		List daynumList = service.queryLast30Daynum(con);
		
		List<HashMap> successList = service.queryLast30AdslSuccessResult(con);
		
		List<HashMap> failedList = service.queryLast30AdslFailedResult(con);
		
		days = service.queryLast30DaynumForChart(con);
		
	
		DBConn.releaseConnection(con);///释放

		
		for (int i = 0; i < daynumList.size(); i++) {
			String daynum=(String)daynumList.get(i);
			
		//	System.err.println("==========day:"+daynum+"===============");

			HashMap obj=GetOneDayObj(daynum,successList);
			HashMap failedObj=GetOneDayFailedObj(daynum,failedList);
			
			addnums+=convertMapObj(obj.get("addnum"));
			deactivenums+=convertMapObj(obj.get("deactivenum"));
			activenums+=convertMapObj(obj.get("activenum"));
			failednums+=convertMapObj(failedObj.get("failednum"));
			
			
			if(i<daynumList.size()-1){
				addnums+=",";
				deactivenums+=",";
				activenums+=",";
				failednums+=",";	
			}
		}

		paramMap.put("days", days);
		paramMap.put("addnums", addnums);
		paramMap.put("deactivenums", deactivenums);
		paramMap.put("activenums", activenums);
		paramMap.put("failednums", failednums);
		
		
		return paramMap;
	}
	
	
	public static HashMap GetOneDayObj(String daynum,List<HashMap> successList){
		HashMap obj =new HashMap();
		obj.put("addnum", 0);
		obj.put("deactivenum", 0);
		obj.put("activenum", 0);
		obj.put("failednum", 0);
		
		int failednum=0;

		for (int j = 0; j < successList.size(); j++) {
			HashMap map=successList.get(j);
			String day=(String)map.get("daynum");

			if(day.equals(daynum)){
				String opertype=(String)map.get("opertype");
				String status=(String)map.get("status");
				int num=Integer.parseInt((String)map.get("num"));
				
				if("add".equals(opertype)){
					if("C".equals(status)){
						obj.put("addnum", num);
					}else if("F".equals(status)){
						failednum+=num;
					}else{
						//------------no exec
					}
					
				}
				else if("deactive".equals(opertype)){
					if("C".equals(status)){
						obj.put("deactivenum", num);
					}else if("F".equals(status)){
						failednum+=num;
					}else{
						//------------no exec
					}
				}
				else if("active".equals(opertype)){
					if("C".equals(status)){
						obj.put("activenum", num);
					}else if("F".equals(status)){
						failednum+=num;
					}else{
						//------------no exec
					}
					
				}else{
					//-----other opertype
				}	
			}
		}
		
		obj.put("failednum", failednum);
		
//		System.err.println(obj.get("addnum"));
//		System.err.println(obj.get("deactivenum"));
//		System.err.println(obj.get("activenum"));
//		System.err.println(obj.get("failednum"));
		
		return obj;
	}
	
	public static HashMap GetOneDayFailedObj(String daynum,List<HashMap> failedList){
		HashMap obj =new HashMap();
		obj.put("failednum", 0);

		for (int j = 0; j < failedList.size(); j++) {
			HashMap map=failedList.get(j);
			String day=(String)map.get("daynum");

			if(day.equals(daynum)){
				int num=Integer.parseInt((String)map.get("num"));
				obj.put("failednum", num);	
				
			}
		}
		
		
	//	System.err.println(obj.get("failednum"));
		
		return obj;
	}
	
	
	public static int convertMapObj(Object obj){
		if(obj!=null){
			return (Integer)obj;
		}
		return 0;
	}

}
