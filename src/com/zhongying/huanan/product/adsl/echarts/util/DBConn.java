/*
 * 功能描述:ID738(Deploy-E8业务开通-20080416),新增历史数据处理程序:数据库操作相关的业务类
 */
package com.zhongying.huanan.product.adsl.echarts.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;




public class DBConn {
	private ReadFile config = ReadFile.getInstance();

	/**
	 * 基本功能：获取数据库连接 方法名称: getDirectConn()
	 * <p>
	 * 
	 * <pre>
	 * 类    型：公共函数
	 * 数据库表： 无
	 * 修    改：李书亮
	 * 修改日期： 2008-03-12
	 * 修改内容： ID738(Deploy-E8业务开通-20080416)
	 * </pre>
	 * 
	 * @return 无
	 * @throws 无
	 */
	public Connection getDirectConn() throws Exception {
		Connection conn = null;
		try {
			// 加载oracle的jdbc驱动
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(config.getConfigFile(EchartsConfig.configFile).getProperty("DBUrl"),
					config.getConfigFile(EchartsConfig.configFile).getProperty("DBUser"),
					config.getConfigFile(EchartsConfig.configFile).getProperty("DBPass"));
		} catch (Exception e) {
			System.out.println("connnection faild :" + e);
			throw e;
		}
		return conn;
	}

	/**
	 * 基本功能：释放数据库连接 方法名称: releaseConnection(Connection conn)
	 * <p>
	 * 
	 * <pre>
	 * 类    型：公共函数
	 * 数据库表： 无
	 * 修    改：李书亮
	 * 修改日期： 2008-04-22
	 * 修改内容： ID738(Deploy-E8业务开通-20080416),新增历史数据处理程序:数据库操作相关的业务类-释放数据库连接
	 * </pre>
	 * 
	 * @param con
	 * @return 无
	 * @throws 无
	 */
	public static void releaseConnection(Connection conn) {
		try {
			// 关闭Connection对象
			if (conn != null)
				conn.close();
		} catch (Exception e) {
			System.out.println("connection close faild :" + e);
		}
	}

	/**
	 * 基本功能：释放查询结果集 方法名称: clearRs(ResultSet rs)
	 * <p>
	 * 
	 * <pre>
	 * 类    型：公共函数
	 * 数据库表： 无
	 * 修    改：李书亮
	 * 修改日期： 2008-04-22
	 * 修改内容： ID738(Deploy-E8业务开通-20080416),新增历史数据处理程序:数据库操作相关的业务类-释放查询结果集
	 * </pre>
	 * 
	 * @param rs
	 * @return 无
	 * @throws 无
	 */
	public static void clearRs(ResultSet rs) {
		try {
			// 关闭ResultSet对象
			if (rs != null)
				rs.close();
		} catch (Exception e) {
			System.out.println("关闭结果集失败:" + e);
		}
	}

	/**
	 * 基本功能：关闭Statement 方法名称: cleanStmt(Statement stmt)
	 * <p>
	 * 
	 * <pre>
	 * 类    型：公共函数
	 * 数据库表： 无
	 * 修    改：李书亮
	 * 修改日期： 2008-04-22
	 * 修改内容： ID738(Deploy-E8业务开通-20080416),新增历史数据处理程序:数据库操作相关的业务类-关闭Statement
	 * </pre>
	 * 
	 * @param stmt
	 * @return 无
	 * @throws 无
	 */
	public static void cleanStmt(Statement stmt) {
		try {
			// 关闭Statement对象
			if (stmt != null)
				stmt.close();
		} catch (Exception e) {
			System.out.println("关闭语句集失败:" + e);
		}
	}

	/**
	 * 基本功能：关闭预编译语句 方法名称: cleanPre(PreparedStatement ps)
	 * <p>
	 * 
	 * <pre>
	 * 类    型：公共函数
	 * 数据库表： 无
	 * 修    改：李书亮
	 * 修改日期： 2008-04-22
	 * 修改内容： ID738(Deploy-E8业务开通-20080416),新增历史数据处理程序:数据库操作相关的业务类-关闭预编译语句
	 * </pre>
	 * 
	 * @param ps
	 * @return 无
	 * @throws 无
	 */
	public static void cleanPre(PreparedStatement ps) {
		try {
			// 关闭PrepareStatement对象
			if (ps != null)
				ps.close();
		} catch (Exception e) {
			System.out.println("关闭预编译失败:" + e);
		}
	}

	/**
	 * 基本功能：数据库操作事务提交 方法名称: commit(Connection con)
	 * <p>
	 * 
	 * <pre>
	 * 类    型：公共函数
	 * 数据库表： 无
	 * 修    改：李书亮
	 * 修改日期： 2008-04-22
	 * 修改内容： ID738(Deploy-E8业务开通-20080416),新增历史数据处理程序:数据库操作相关的业务类-事务提交
	 * </pre>
	 * 
	 * @param con
	 * @return 无
	 * @throws 无
	 */
	public static void commit(Connection con) {
		if (con != null) {
			try {
				// 事务提交
				con.commit();
			} catch (SQLException e) {
				System.out.println("提交失败:" + e);
			}
		}
	}

}
