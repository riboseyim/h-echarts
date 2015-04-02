/*
 * ��������:ID738(Deploy-E8ҵ��ͨ-20080416),������ʷ���ݴ������:�ļ�(�����ļ�)������ص�ҵ����
 * ��������:2008-02-25
 * ������:������
 * �ĵ���ţ���Ʊ��(2008022101)
 * �����/bug�ţ�ID641
 */
package com.zhongying.huanan.product.adsl.echarts.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ReadFile {

	private static ReadFile instance;

	/**
	 * �������ܣ����캯�� ��������: ReadFile()
	 * <p>
	 * 
	 * <pre>
	 * ��    �ͣ���������
	 * ���ݿ�� ��
	 * ��    �ģ�������
	 * �޸����ڣ� 2008-04-22
	 * �޸����ݣ� ID738(Deploy-E8ҵ��ͨ-20080416),������ʷ���ݴ������:�ļ�(�����ļ�)������ص�ҵ����-���캯��
	 * </pre>
	 */
	private ReadFile() {
	}

	/**
	 * �������ܣ���ȡ���ʵ�� ��������: getInstance()
	 * <p>
	 * 
	 * <pre>
	 * ��    �ͣ���������
	 * ���ݿ�� ��
	 * ��    �ģ�������
	 * �޸����ڣ� 2008-02-28
	 * �޸����ݣ� ID641/����ҵ��澯���ӽ������Ϣ�ı��(2008022101),������ʷ���ݴ������:�ļ�(�����ļ�)������ص�ҵ����-��ȡcom.zhongying.vpn.hisdatadeal.ReadFile��ʵ��
	 * </pre>
	 */
	public static ReadFile getInstance() {
		return new ReadFile();
	}

	/**
	 * �������ܣ���ȡ�����ļ���Ϣ ��������: getConfigFile(String FileName)
	 * <p>
	 * 
	 * <pre>
	 * ��    �ͣ���������
	 * ���ݿ�� ��
	 * ��    �ģ�������
	 * �޸����ڣ� 2008-04-22
	 * �޸����ݣ� ID738(Deploy-E8ҵ��ͨ-20080416),������ʷ���ݴ������:�ļ�(�����ļ�)������ص�ҵ����-��ȡ�����ļ���Ϣ
	 * </pre>
	 * 
	 * @param FileName
	 *            �ļ�·��+�ļ���
	 * @return Properties
	 */
	public Properties getConfigFile(String FileName) {
		Properties config = new Properties();
		java.io.InputStream is = null;
		try {
			// ���ز�����
			// is=new FileInputStream("./" + FileName);
			is = new FileInputStream(FileName);
			config.load(is);
			is.close();
			is = null;
		} catch (IOException e) {
			System.out.println("getConfigFile����:" + e.getMessage());
			e.printStackTrace();
		}
		return config;
	}

}
