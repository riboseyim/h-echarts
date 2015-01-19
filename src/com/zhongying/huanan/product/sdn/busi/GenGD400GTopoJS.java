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
import com.zhongying.huanan.product.sdn.util.GenCodeUtil;

import junit.framework.TestCase;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class GenGD400GTopoJS extends TestCase {
	private static String APPDIR = "E:\\project\\huanan-echarts\\WebRoot\\huanan\\nos\\sdn";
	
	public static void main(String[] args) {
		try {
			String TEMPLATEDIR=APPDIR+"\\ftl";

			String TEMPLATENAME="drawDevByNode.ftl";
			String outpath=APPDIR + "/"+"GD400GTopoDraw.js";
			
//			Map<String, Object> paramMap = loadData();
			Map<String, Object> paramMap = loadData2();
			execTemplate(paramMap,TEMPLATEDIR,TEMPLATENAME,outpath);

			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void execTemplate(Map<String, Object> paramMap,String templatedir,String templatename,String outpath) {
		Configuration cfg = Configuration.getDefaultConfiguration();
		try {
			
			// 从哪里加载模板文件
			cfg.setDirectoryForTemplateLoading(new File(templatedir));
			// 通过freemarker解释模板，首先需要获得Template对象
			Template template = cfg.getTemplate(templatename);

			// 定义模板解释完成之后的输出
			PrintWriter writer = new PrintWriter(new BufferedWriter(
					new FileWriter(outpath)));
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
	
	//version 设备之间画线条                     
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
		//version 1:设备到设备画线
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
	
	//version2:云之间画线条
	public static Map<String, Object>  loadData2() throws IOException{
		Map<String, Object> paramMap = new HashMap<String, Object>();
		
		
		String devdata=GenCodeUtil.readTxt(APPDIR+"\\data\\devdata.txt", "GBK");
		String cirdata=GenCodeUtil.readTxt(APPDIR+"\\data\\cirdata.txt", "GBK");
		
		//System.out.println(devdata);
		//System.out.println(cirdata);
		
	    List<String> nameList = new ArrayList<String>();  
		List<Map> devMapList = new ArrayList<Map>(); 
		List<Map> cirMapList = new ArrayList<Map>(); 
		
		Map devNodeHash=new HashMap();
		
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
				
				devNodeHash.put(devinfo[2], devinfo[1]);
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
					cir.put("deviceA",devNodeHash.get(cirinfo[1]));
					cir.put("deviceB",devNodeHash.get(cirinfo[2]));
					
					System.out.println("node A:"+devNodeHash.get(cirinfo[1])+"----"+"node B:"+devNodeHash.get(cirinfo[2]));;
					
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