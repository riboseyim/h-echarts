package com.zhongying.huanan.product.sdn.busi;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.zhongying.huanan.product.sdn.GenCodeUtil;

import junit.framework.TestCase;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class GenGD400GTopoJS extends TestCase {
	
	public static void main(String[] args) {
		Map<String, Object> paramMap;
		try {
			paramMap = loadData();
			testFreemarker(paramMap);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	private static String APPDIR = "E:\\project\\huanan-echarts\\WebRoot\\huanan\\nos\\sdn";
	private static String FTLDIR=APPDIR+"\\ftl";
	private static String TEMPLATENAME="drawDevByNode.ftl";
	private static String TARGETNAME="GD400GTopoDraw.js";
	
	public static void testFreemarker(Map<String, Object> paramMap) {
		Configuration cfg = Configuration.getDefaultConfiguration();

		try {
			// 从哪里加载模板文件
			cfg.setDirectoryForTemplateLoading(new File(APPDIR+"\\ftl"));

			
			// 通过freemarker解释模板，首先需要获得Template对象
			Template template = cfg.getTemplate(FTLDIR+"\\"+TEMPLATENAME);

			// 定义模板解释完成之后的输出
			PrintWriter writer = new PrintWriter(new BufferedWriter(
					new FileWriter(APPDIR + "/"+TARGETNAME)));

			try {
				// 解释模板
				template.process(paramMap, writer);
				System.out.println("-------------template process success--------\n\n");
				writer.flush();
				
				String templateout=GenCodeUtil.readTxt(APPDIR+"/"+TARGETNAME, "GBK");
				System.out.println(templateout);
			} catch (TemplateException e) {
				e.printStackTrace();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static Map<String, Object>  loadData() throws IOException{
		Map<String, Object> paramMap = new HashMap<String, Object>();
		
		
		String devdata=GenCodeUtil.readTxt(APPDIR+"\\data\\devdata.txt", "GBK");
		String cirdata=GenCodeUtil.readTxt(APPDIR+"\\data\\cirdata.txt", "GBK");
		
		//System.out.println(devdata);
		//System.out.println(cirdata);
		
	    List<String> nameList = new ArrayList<String>();  
		List<Map> devMapList = new ArrayList<Map>(); 
		List<Map> cirMapList = new ArrayList<Map>(); 
		
		String[] devTxt=devdata.split("::");
		
		for (int i = 0; i < devTxt.length; i++) {
			String devcol=devTxt[i];
			String[] devinfo=devcol.split("#####");
			if(devinfo!=null&&devinfo.length>1){
				Map dev=new HashMap();
				
				//System.out.println(devinfo[1]);
				dev.put("deviceid",devinfo[2]);
				dev.put("devicename",devinfo[3]);
				dev.put("node",devinfo[1]);
				
				devMapList.add(dev);
				nameList.add(devinfo[2]);
			}
		}
		
		String[] cirTxt=cirdata.split("::");
		
		for (int j = 0; j < cirTxt.length; j++) {
			String circol=cirTxt[j];
			if(circol!=null){
				String[] cirinfo=circol.split("#####");
				if(cirinfo!=null&&cirinfo.length>1){
					Map cir=new HashMap();
					cir.put("key",j);
					cir.put("deviceA",cirinfo[1]);
					cir.put("deviceB",cirinfo[2]);
					
					cirMapList.add(cir);
				}
			}
		}
		
		paramMap.put("devMaplist",devMapList);
		paramMap.put("cirMaplist",cirMapList);
		
		paramMap.put("devlist",nameList);
		
		return paramMap;
	}
	
}


class Device{
	public String deviceid;
	public String devicename;
	public String node;
	
	public String getDeviceid() {
		return deviceid;
	}
	public void setDeviceid(String deviceid) {
		this.deviceid = deviceid;
	}
	public String getDevicename() {
		return devicename;
	}
	public void setDevicename(String devicename) {
		this.devicename = devicename;
	}
	public String getNode() {
		return node;
	}
	public void setNode(String node) {
		this.node = node;
	}
	
	
	
	
}