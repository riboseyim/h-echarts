package com.zhongying.huanan.product.adsl.echarts;

import java.text.DecimalFormat;

import com.zhongying.huanan.product.echarts.util.Base64Image;

/**
 * HTMLͳ��ͼ���Զ�����
 * @author ���
 * @since 2015-04
 * 
 * */

public class GenAdslServChartsBean {
	
	public String saveAdslServBar(String imgFilePath, String imageData){
		Base64Image.ConvertEChartsImage(imageData, imgFilePath);
		return "SUCCESS";
	}
	
	public static String getSqlStrOfArray(String array[]) {
		if (array.length == 0)
			return "";
		String str = "";
		for (int i = 0; i < array.length; i++){
			if (i == array.length - 1){
				str = (new StringBuilder(String.valueOf(str))).append(array[i])
				.toString();
			}else{
				str = (new StringBuilder(String.valueOf(str))).append(array[i]).append(",").toString();
			}			
		}
		return str;
	}

	
	 /**
     * ��ʽ��json�ַ���
     * @param str
     * @return
     */
    public String formatJSONStr(String str){
        return str.replaceAll(":", "��").replaceAll("\\s"," ");
    }
    /**
     * ��ʽ������
     * @param d
     * @return
     */
    public String formatDouble(Double d){
        String result = "";
        DecimalFormat df=new DecimalFormat("0.00");
        if(null==d){
            result = "null";
        }else{
            result = df.format(d);  
        }
        return result;
    }

	


}
