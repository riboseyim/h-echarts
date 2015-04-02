package com.zhongying.huanan.product.adsl.echarts;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import com.zhongying.huanan.product.adsl.echarts.util.DBConn;

public class GenAdslServService {

	public List queryLast30Daynum() {
		String sql = "select distinct to_char(createtime,'yyyyMMDD') daynum from worksheet ";
		sql += " where servtypeid='IPSL' and floor(to_number(sysdate-createtime)) <15";
		sql += " order by daynum";

		return queryDayList(sql);
	}

	public String queryLast30DaynumForChart() {
		String daynums="";
		String sql = "select distinct to_char(createtime,'MMDD') daynum from worksheet ";
		sql += " where servtypeid='IPSL' and floor(to_number(sysdate-createtime)) <15";
		sql += " order by daynum";

		List list= queryDayList(sql);
		
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

	public List<HashMap> queryLast30AdslSuccessResult() {
		String sql = " select to_char(createtime,'RRRRMMDD') daynum,opertype,status,";
		sql += " count(distinct wsnbr) as num";
		sql += " from worksheet w where w.servtypeid='IPSL'";
		sql += " and floor(to_number(sysdate-createtime)) <15";
		sql += " and status='C' ";
		sql += " group by  to_char(createtime,'RRRRMMDD'), opertype,status";
		sql += " order by daynum,opertype";

		return queryAdslDeployResult(sql);
	}

	public List<HashMap> queryLast30AdslFailedResult() {
		String sql = " select to_char(createtime,'RRRRMMDD') daynum,";
		sql += " count(distinct wsnbr) as num";
		sql += " from worksheet w where w.servtypeid='IPSL'";
		sql += " and floor(to_number(sysdate-createtime)) <15";
		sql += " and status='F' ";
		sql += " group by  to_char(createtime,'RRRRMMDD')";
		sql += " order by daynum";

		return queryAdslFailedResult(sql);
	}

	public List<HashMap> queryAdslDeployResult(String sql) {
		List<HashMap> mapList = new ArrayList<HashMap>();

		Connection con = null;
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
			DBConn.releaseConnection(con);
		}

		return mapList;
	}
	
	public List<HashMap> queryAdslFailedResult(String sql) {
		List<HashMap> mapList = new ArrayList<HashMap>();

		Connection con = null;
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
			DBConn.releaseConnection(con);
		}

		return mapList;
	}



	public List queryDayList(String sql) {
		List list = new ArrayList();

		Connection con = null;
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

				list.add(daynum);
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConn.cleanPre(psd);
			DBConn.releaseConnection(con);
		}

		return list;
	}
}
