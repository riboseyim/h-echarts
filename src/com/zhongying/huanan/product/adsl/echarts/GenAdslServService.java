package com.zhongying.huanan.product.adsl.echarts;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import com.zhongying.huanan.product.adsl.echarts.util.DBConn;

public class GenAdslServService {

	public List queryExistRecordDays(int days,Connection conn) {
		String sql = "select distinct to_char(createtime,'yyyyMMDD') daynum from worksheet ";
		sql += " where servtypeid='IPSL' and floor(to_number(sysdate-createtime)) <"+days;
		sql += " order by daynum";

		return queryDayList(sql,conn);
	}

	public String queryDaysForChart(int days,Connection conn) {
		String daynums="";
		String sql = "select distinct to_char(createtime,'MMDD') daynum from worksheet ";
		sql += " where servtypeid='IPSL' and floor(to_number(sysdate-createtime)) <"+days;
		sql += " order by daynum";

		List list= queryDayList(sql,conn);
		
		for (int i = 0; i < list.size(); i++) {
			String num=(String)list.get(i);
			
			if(i<list.size()-1){
				daynums=daynums+"'"+num+"'"+",";
			}else{
				daynums=daynums+"'"+num+"'";
			}
		}
		
		return daynums;
	}

	public List<HashMap> queryAdslSuccessResult(int days,Connection con) {
		String sql = " select to_char(createtime,'RRRRMMDD') daynum,opertype,status,";
		sql += " count(distinct wsnbr) as num";
		sql += " from worksheet w where w.servtypeid='IPSL'";
		sql += " and floor(to_number(sysdate-createtime)) <"+days;
		sql += " and status='C' ";
		sql += " group by  to_char(createtime,'RRRRMMDD'), opertype,status";
		sql += " order by daynum,opertype";

		return queryAdslDeployResult(sql,con);
	}

	public List<HashMap> queryAdslFailedResult(int days,Connection con) {
		String sql = " select to_char(createtime,'RRRRMMDD') daynum,";
		sql += " count(distinct wsnbr) as num";
		sql += " from worksheet w where w.servtypeid='IPSL'";
		sql += " and floor(to_number(sysdate-createtime)) <"+days;
		sql += " and status='F' ";
		sql += " group by  to_char(createtime,'RRRRMMDD')";
		sql += " order by daynum";

		return queryAdslFailedResult(sql,con);
	}

	public List<HashMap> queryAdslDeployResult(String sql,Connection con) {
		List<HashMap> mapList = new ArrayList<HashMap>();

		PreparedStatement psd = null;
		ResultSet rs = null;
		try {

			psd = con.prepareStatement(sql);
			rs = psd.executeQuery();

			while (rs.next()) {
				HashMap map = new HashMap();
				String daynum = rs.getString("daynum");
				String opertype = rs.getString("opertype");
				String status = rs.getString("status");
				String num = rs.getString("num");

				map.put("daynum", daynum);
				map.put("opertype", opertype);
				map.put("status", status);
				map.put("num", num);

				mapList.add(map);

			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConn.cleanPre(psd);
		}

		return mapList;
	}
	
	public List<HashMap> queryAdslFailedResult(String sql,Connection con) {
		List<HashMap> mapList = new ArrayList<HashMap>();

		PreparedStatement psd = null;
		ResultSet rs = null;
		try {
			DBConn db = new DBConn();
			con = db.getDirectConn();

			psd = con.prepareStatement(sql);
			rs = psd.executeQuery();

			while (rs.next()) {
				HashMap map = new HashMap();
				String daynum = rs.getString("daynum");
				String num = rs.getString("num");

				map.put("daynum", daynum);
				map.put("num", num);

				mapList.add(map);

			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConn.cleanPre(psd);
		}

		return mapList;
	}



	public List queryDayList(String sql,Connection con) {
		List list = new ArrayList();

		PreparedStatement psd = null;
		ResultSet rs = null;
		try {

			psd = con.prepareStatement(sql);
			rs = psd.executeQuery();

			while (rs.next()) {
				HashMap map = new HashMap();
				String daynum = rs.getString("daynum");

				list.add(daynum);
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConn.cleanPre(psd);
		}

		return list;
	}
}
