/*
 * 功能描述:ID738(Deploy-E8业务开通-20080416),新增历史数据处理程序:文件(配置文件)操作相关的业务类
 * 创建日期:2008-02-25
 * 创建人:李书亮
 * 文档编号：设计变更(2008022101)
 * 任务号/bug号：ID641
 */
package com.zhongying.huanan.product.adsl.echarts.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ReadFile {

	private static ReadFile instance;

	/**
	 * 基本功能：构造函数 方法名称: ReadFile()
	 * <p>
	 * 
	 * <pre>
	 * 类    型：公共函数
	 * 数据库表： 无
	 * 修    改：李书亮
	 * 修改日期： 2008-04-22
	 * 修改内容： ID738(Deploy-E8业务开通-20080416),新增历史数据处理程序:文件(配置文件)操作相关的业务类-构造函数
	 * </pre>
	 */
	private ReadFile() {
	}

	/**
	 * 基本功能：获取类的实例 方法名称: getInstance()
	 * <p>
	 * 
	 * <pre>
	 * 类    型：公共函数
	 * 数据库表： 无
	 * 修    改：李书亮
	 * 修改日期： 2008-02-28
	 * 修改内容： ID641/关于业务告警增加接入点信息的变更(2008022101),新增历史数据处理程序:文件(配置文件)操作相关的业务类-获取com.zhongying.vpn.hisdatadeal.ReadFile的实例
	 * </pre>
	 */
	public static ReadFile getInstance() {
		return new ReadFile();
	}

	/**
	 * 基本功能：获取配置文件信息 方法名称: getConfigFile(String FileName)
	 * <p>
	 * 
	 * <pre>
	 * 类    型：公共函数
	 * 数据库表： 无
	 * 修    改：李书亮
	 * 修改日期： 2008-04-22
	 * 修改内容： ID738(Deploy-E8业务开通-20080416),新增历史数据处理程序:文件(配置文件)操作相关的业务类-获取配置文件信息
	 * </pre>
	 * 
	 * @param FileName
	 *            文件路径+文件名
	 * @return Properties
	 */
	public Properties getConfigFile(String FileName) {
		Properties config = new Properties();
		java.io.InputStream is = null;
		try {
			// 本地测试用
			// is=new FileInputStream("./" + FileName);
			is = new FileInputStream(FileName);
			config.load(is);
			is.close();
			is = null;
		} catch (IOException e) {
			System.out.println("getConfigFile出错:" + e.getMessage());
			e.printStackTrace();
		}
		return config;
	}

}
