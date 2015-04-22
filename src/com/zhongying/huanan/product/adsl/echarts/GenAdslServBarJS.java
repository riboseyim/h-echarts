package com.zhongying.huanan.product.adsl.echarts;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.zhongying.huanan.product.adsl.echarts.util.DBConn;
import com.zhongying.huanan.product.adsl.echarts.util.EchartsConfig;
import com.zhongying.huanan.product.adsl.echarts.util.ToolUtil;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class GenAdslServBarJS {

	private static String EChartsEncode = "GBK";
	private static String EChartsEncodeT = "GBK";

	public static void main(String[] args) {
		UpdateAdslServEchartsBar();
	}

	public static void UpdateAdslServEchartsBar() {

		Connection con = null;
		try {
			System.err.println("-----0-----" + ToolUtil.getAccurateTime());

			DBConn db = new DBConn();
			con = db.getDirectConn();

			System.err.println("-----1-----" + ToolUtil.getAccurateTime());
			String TEMPLATEDIR = EchartsConfig.AdslEchartsAppDir + File.separator + "ftl";

			String TEMPLATENAME = "drawAdslServBarOption.ftl";
			String outpath = EchartsConfig.AdslEchartsAppDir;

			System.err.println("-----2-----" + ToolUtil.getAccurateTime());
			int lastdays = 10;
			String filepath = outpath + File.separator + "AdslServ-bar-option-10.js";
			Map<String, Object> paramMap = loadData(lastdays, con);

			System.err.println("-----3-----" + ToolUtil.getAccurateTime());

			execTemplate(paramMap, TEMPLATEDIR, TEMPLATENAME, filepath);

			System.err.println("-----4-----" + ToolUtil.getAccurateTime());

			lastdays = 30;
			filepath = outpath + File.separator + "AdslServ-bar-option-30.js";
			paramMap = loadData(lastdays, con);
			execTemplate(paramMap, TEMPLATEDIR, TEMPLATENAME, filepath);

			System.err.println("-----5-----" + ToolUtil.getAccurateTime());

			lastdays = 30;
			filepath = outpath + File.separator + "AdslServ-bar-item-option-30.js";
			TEMPLATENAME = "trackBarTotal30Option.ftl";
			paramMap = loadIndexUserTotalBarItem(lastdays, con);
			execTemplate(paramMap, TEMPLATEDIR, TEMPLATENAME, filepath);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConn.releaseConnection(con);// /释放
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

	public static Map<String, Object> loadIndexUserTotalBarItem(int lastdays, Connection con) throws Exception {
		Map<String, Object> paramMap = new HashMap<String, Object>();

		String index_user_bar_item_name = "专线自动施工成功率";
		String index_user_bar_item_usernames = "";
		String index_user_bar_item_keys = "";
		String index_user_bar_item_data = "";
		String index_user_bar_item_yAxis = "";

		index_user_bar_item_yAxis += "{";
		index_user_bar_item_yAxis += "type : 'value',";
		index_user_bar_item_yAxis += "name : '专线',";
		index_user_bar_item_yAxis += "axisLabel : {";
		index_user_bar_item_yAxis += "formatter : '{value} 条'";
		index_user_bar_item_yAxis += "}";
		index_user_bar_item_yAxis += "}";

		System.err.println("loadIndexUserTotalBarItem  query data begin ....");

		GenAdslServService service = new GenAdslServService();

		LinkedList traceList = new LinkedList();
		traceList.add("C");
		traceList.add("F");

		LinkedList traceNameList = new LinkedList();
		traceNameList.add("成功");
		traceNameList.add("回退");
		index_user_bar_item_keys = convertListToString(traceNameList, "'");

		List userList = service.queryExistRecordDays(lastdays, con);
		index_user_bar_item_usernames = convertListToString(userList, "'");// x:daynums

		HashMap userhoursMap = service.queryAdslTotalResult(lastdays, con);

		System.err.println("loadIndexUserTotalBarItem  query data finished ....");

		int rgba1 = 121;
		int rgba2 = 195;
		int rgba3 = 52;
		int rgba4 = 1;

		List<Map> lineList = new ArrayList<Map>();

		for (int j = 0; j < traceList.size(); j++) {

			String tracename = (String) traceList.get(j);

			index_user_bar_item_yAxis += ",{";
			index_user_bar_item_yAxis += "type : 'value',";
			index_user_bar_item_yAxis += "name : '数量',";
			index_user_bar_item_yAxis += "axisLabel : {";
			index_user_bar_item_yAxis += "formatter : '{value} 个'";
			index_user_bar_item_yAxis += "}";
			index_user_bar_item_yAxis += "}";

			String thisNodeData = "{ \n";

			thisNodeData += " name:'" + convertName(tracename) + "',";
			thisNodeData += " type:'bar',";
			thisNodeData += "   tooltip : {trigger: 'item'},";
			thisNodeData += "    stack: '总工时',";
			thisNodeData += "itemStyle : {";
			thisNodeData += "	normal : {";
			thisNodeData += "		color : 'rgba(" + rgba1 + "," + rgba2 + "," + rgba3 + "," + rgba4 + ")',";
			thisNodeData += "		label : {";
			// thisNodeData += "			show : true,position:'inside',";
			thisNodeData += "			show : false,";

			thisNodeData += "			textStyle : {";
			thisNodeData += "				color : '#27727B'";
			thisNodeData += "			}";
			thisNodeData += "		}";
			thisNodeData += "	}";
			thisNodeData += "},";

			rgba1 += 100;
			rgba2 += 20;
			rgba3 += 20;
			rgba4 += 20;

			thisNodeData += "   \n data:[";

			String lineDataValues = "";

			for (int i = 0; i < userList.size(); i++) {
				String username = (String) userList.get(i);

				String hours = (String) userhoursMap.get(username + tracename);
				if (hours == null || "".equals(hours)) {
					hours = "0";
				}

				// System.out.println(username+" "+tracename+":"+hours);

				thisNodeData += hours;

				if (i < userList.size() - 1) {
					thisNodeData += ",";
				}

			}

			thisNodeData += "] \n";
			thisNodeData += "}  \n";

			String lineData = "{";
			lineData += " name:'" + convertName(tracename) + "',";
			lineData += " type:'line',";
			lineData += "yAxisIndex: 1,";
			lineData += "data:[";
			lineData += lineDataValues;
			lineData += "]";
			lineData += "} \n";

			if (j < traceList.size() - 1) {
				thisNodeData += ",";

				lineData += ",";
			} else {
				lineData = "," + lineData;
			}
			index_user_bar_item_data += thisNodeData;
			// index_user_bar_item_data +=lineData;

		}

		System.err.println("title:" + index_user_bar_item_name);
		System.err.println("index_user_bar_item_keys:" + index_user_bar_item_keys);
		System.err.println("index_user_total_bar_item_usernames:" + index_user_bar_item_usernames);
		System.err.println("index_user_total_bar_item_data:" + index_user_bar_item_data);
		System.err.println("index_user_bar_item_yAxis:" + index_user_bar_item_yAxis);

		paramMap.put("index_user_total_bar_item_name", index_user_bar_item_name);
		paramMap.put("index_user_total_bar_item_keys", index_user_bar_item_keys);
		paramMap.put("index_user_total_bar_item_usernames", index_user_bar_item_usernames);

		paramMap.put("index_user_bar_item_yAxis", index_user_bar_item_yAxis);
		paramMap.put("index_user_total_bar_item_data", index_user_bar_item_data);

		return paramMap;
	}

	public static String convertName(String status) {
		if (status != null) {
			if (status.equals("C")) {
				status = "成功";
			}
			if (status.equals("F")) {
				status = "回退";
			}
		}
		return status;
	}

	// 加载数据
	public static Map<String, Object> loadData(int lastdays, Connection con) throws Exception {
		Map<String, Object> paramMap = new HashMap<String, Object>();

		String days = "";
		String addnums = "";
		String deactivenums = "";
		String activenums = "";
		String failednums = "";
		GenAdslServService service = new GenAdslServService();

		List daynumList = service.queryExistRecordDays(lastdays, con);

		List<HashMap> successList = service.queryAdslSuccessResult(lastdays, con);

		List<HashMap> failedList = service.queryAdslFailedResult(lastdays, con);

		days = service.queryDaysForChart(lastdays, con);

		System.err.println("-----5-----" + ToolUtil.getAccurateTime());

		for (int i = 0; i < daynumList.size(); i++) {
			String daynum = (String) daynumList.get(i);

			// System.err.println("==========day:"+daynum+"===============");

			HashMap obj = GetOneDayObj(daynum, successList);
			HashMap failedObj = GetOneDayFailedObj(daynum, failedList);

			addnums += convertMapObj(obj.get("addnum"));
			deactivenums += convertMapObj(obj.get("deactivenum"));
			activenums += convertMapObj(obj.get("activenum"));
			failednums += convertMapObj(failedObj.get("failednum"));

			if (i < daynumList.size() - 1) {
				addnums += ",";
				deactivenums += ",";
				activenums += ",";
				failednums += ",";
			}
		}

		paramMap.put("lastdays", lastdays);

		paramMap.put("days", days);
		paramMap.put("addnums", addnums);
		paramMap.put("deactivenums", deactivenums);
		paramMap.put("activenums", activenums);
		paramMap.put("failednums", failednums);

		return paramMap;
	}

	public static HashMap GetOneDayObj(String daynum, List<HashMap> successList) {
		HashMap obj = new HashMap();
		obj.put("addnum", 0);
		obj.put("deactivenum", 0);
		obj.put("activenum", 0);
		obj.put("failednum", 0);

		int failednum = 0;

		for (int j = 0; j < successList.size(); j++) {
			HashMap map = successList.get(j);
			String day = (String) map.get("daynum");

			if (day.equals(daynum)) {
				String opertype = (String) map.get("opertype");
				String status = (String) map.get("status");
				int num = Integer.parseInt((String) map.get("num"));

				if ("add".equals(opertype)) {
					if ("C".equals(status)) {
						obj.put("addnum", num);
					} else if ("F".equals(status)) {
						failednum += num;
					} else {
						// ------------no exec
					}

				} else if ("deactive".equals(opertype)) {
					if ("C".equals(status)) {
						obj.put("deactivenum", num);
					} else if ("F".equals(status)) {
						failednum += num;
					} else {
						// ------------no exec
					}
				} else if ("active".equals(opertype)) {
					if ("C".equals(status)) {
						obj.put("activenum", num);
					} else if ("F".equals(status)) {
						failednum += num;
					} else {
						// ------------no exec
					}

				} else {
					// -----other opertype
				}
			}
		}

		obj.put("failednum", failednum);

		// System.err.println(obj.get("addnum"));
		// System.err.println(obj.get("deactivenum"));
		// System.err.println(obj.get("activenum"));
		// System.err.println(obj.get("failednum"));

		return obj;
	}

	public static HashMap GetOneDayFailedObj(String daynum, List<HashMap> failedList) {
		HashMap obj = new HashMap();
		obj.put("failednum", 0);

		for (int j = 0; j < failedList.size(); j++) {
			HashMap map = failedList.get(j);
			String day = (String) map.get("daynum");

			if (day.equals(daynum)) {
				int num = Integer.parseInt((String) map.get("num"));
				obj.put("failednum", num);

			}
		}

		// System.err.println(obj.get("failednum"));

		return obj;
	}

	public static int convertMapObj(Object obj) {
		if (obj != null) {
			return (Integer) obj;
		}
		return 0;
	}

	public static String convertListToString(List list, String fixstr) {
		String str = "";
		for (int i = 0; i < list.size(); i++) {
			str += fixstr + list.get(i) + fixstr;
			if (i < list.size() - 1) {
				str += ",";
			}

		}

		// System.err.println("convertListToString:" + str);

		return str;
	}

}
