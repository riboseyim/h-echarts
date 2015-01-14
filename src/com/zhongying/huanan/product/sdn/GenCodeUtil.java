package com.zhongying.huanan.product.sdn;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class GenCodeUtil {
	public static String readTxt(String filePathAndName, String encoding)
			throws IOException {
		encoding = encoding.trim();
		StringBuffer str = new StringBuffer("");
		String st = "";
		try {
			FileInputStream fs = new FileInputStream(filePathAndName);
			InputStreamReader isr;
			if (encoding.equals(""))
				isr = new InputStreamReader(fs);
			else
				isr = new InputStreamReader(fs, encoding);
			BufferedReader br = new BufferedReader(isr);
			try {
				for (String data = ""; (data = br.readLine()) != null;)
					str.append((new StringBuilder(String.valueOf(data)))
							.append(" ").toString());

			} catch (Exception e) {
				str.append(e.toString());
			}
			st = str.toString();
		} catch (IOException es) {
			st = "";
		}
		return st;
	}
}
