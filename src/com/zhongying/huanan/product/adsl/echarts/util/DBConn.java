/*
 * ��������:ID738(Deploy-E8ҵ��ͨ-20080416),������ʷ���ݴ������:���ݿ������ص�ҵ����
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
	 * �������ܣ���ȡ���ݿ����� ��������: getDirectConn()
	 * <p>
	 * 
	 * <pre>
	 * ��    �ͣ���������
	 * ���ݿ�� ��
	 * ��    �ģ�������
	 * �޸����ڣ� 2008-03-12
	 * �޸����ݣ� ID738(Deploy-E8ҵ��ͨ-20080416)
	 * </pre>
	 * 
	 * @return ��
	 * @throws ��
	 */
	public Connection getDirectConn() throws Exception {
		Connection conn = null;
		try {
			// ����oracle��jdbc����
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
	 * �������ܣ��ͷ����ݿ����� ��������: releaseConnection(Connection conn)
	 * <p>
	 * 
	 * <pre>
	 * ��    �ͣ���������
	 * ���ݿ�� ��
	 * ��    �ģ�������
	 * �޸����ڣ� 2008-04-22
	 * �޸����ݣ� ID738(Deploy-E8ҵ��ͨ-20080416),������ʷ���ݴ������:���ݿ������ص�ҵ����-�ͷ����ݿ�����
	 * </pre>
	 * 
	 * @param con
	 * @return ��
	 * @throws ��
	 */
	public static void releaseConnection(Connection conn) {
		try {
			// �ر�Connection����
			if (conn != null)
				conn.close();
		} catch (Exception e) {
			System.out.println("connection close faild :" + e);
		}
	}

	/**
	 * �������ܣ��ͷŲ�ѯ����� ��������: clearRs(ResultSet rs)
	 * <p>
	 * 
	 * <pre>
	 * ��    �ͣ���������
	 * ���ݿ�� ��
	 * ��    �ģ�������
	 * �޸����ڣ� 2008-04-22
	 * �޸����ݣ� ID738(Deploy-E8ҵ��ͨ-20080416),������ʷ���ݴ������:���ݿ������ص�ҵ����-�ͷŲ�ѯ�����
	 * </pre>
	 * 
	 * @param rs
	 * @return ��
	 * @throws ��
	 */
	public static void clearRs(ResultSet rs) {
		try {
			// �ر�ResultSet����
			if (rs != null)
				rs.close();
		} catch (Exception e) {
			System.out.println("�رս����ʧ��:" + e);
		}
	}

	/**
	 * �������ܣ��ر�Statement ��������: cleanStmt(Statement stmt)
	 * <p>
	 * 
	 * <pre>
	 * ��    �ͣ���������
	 * ���ݿ�� ��
	 * ��    �ģ�������
	 * �޸����ڣ� 2008-04-22
	 * �޸����ݣ� ID738(Deploy-E8ҵ��ͨ-20080416),������ʷ���ݴ������:���ݿ������ص�ҵ����-�ر�Statement
	 * </pre>
	 * 
	 * @param stmt
	 * @return ��
	 * @throws ��
	 */
	public static void cleanStmt(Statement stmt) {
		try {
			// �ر�Statement����
			if (stmt != null)
				stmt.close();
		} catch (Exception e) {
			System.out.println("�ر���伯ʧ��:" + e);
		}
	}

	/**
	 * �������ܣ��ر�Ԥ������� ��������: cleanPre(PreparedStatement ps)
	 * <p>
	 * 
	 * <pre>
	 * ��    �ͣ���������
	 * ���ݿ�� ��
	 * ��    �ģ�������
	 * �޸����ڣ� 2008-04-22
	 * �޸����ݣ� ID738(Deploy-E8ҵ��ͨ-20080416),������ʷ���ݴ������:���ݿ������ص�ҵ����-�ر�Ԥ�������
	 * </pre>
	 * 
	 * @param ps
	 * @return ��
	 * @throws ��
	 */
	public static void cleanPre(PreparedStatement ps) {
		try {
			// �ر�PrepareStatement����
			if (ps != null)
				ps.close();
		} catch (Exception e) {
			System.out.println("�ر�Ԥ����ʧ��:" + e);
		}
	}

	/**
	 * �������ܣ����ݿ���������ύ ��������: commit(Connection con)
	 * <p>
	 * 
	 * <pre>
	 * ��    �ͣ���������
	 * ���ݿ�� ��
	 * ��    �ģ�������
	 * �޸����ڣ� 2008-04-22
	 * �޸����ݣ� ID738(Deploy-E8ҵ��ͨ-20080416),������ʷ���ݴ������:���ݿ������ص�ҵ����-�����ύ
	 * </pre>
	 * 
	 * @param con
	 * @return ��
	 * @throws ��
	 */
	public static void commit(Connection con) {
		if (con != null) {
			try {
				// �����ύ
				con.commit();
			} catch (SQLException e) {
				System.out.println("�ύʧ��:" + e);
			}
		}
	}

}
