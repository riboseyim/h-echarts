///**
// * update 1.4,过滤首页告警接入点个数查询，1.城域网侧接入的端口或者板卡告警；2.非接入设备的设备脱网告警，可直接关联告警类型(DS0103)
// * update 1.3,删除接入点整体告警数查询，修改接入设备告警数查询（关联上联拓扑的设备和电路的告警）
// * update 1.2,修改查询上联topo方法，提前汇总好设备的上联拓扑信息
// * update 1.1,查询接入告警去除端口的过滤，查询设备相关的所有告警
// * update 1.0,方法：queryPTCityAccessTopoInfoData，修改接入点查询，原为展示接入点修改为接入处设备展示
// */
//package com.zhongying.huanan.product.sdn.busi;
//
//import java.sql.Connection;
//import java.text.DecimalFormat;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Calendar;
//import java.util.Date;
//import java.util.HashMap;
//
//import com.ctsi.common.BaseBean;
//import com.zhongying.report.ReportDBUtil;
//
//public class TopoSDNBean extends BaseBean{
//    public TopoSDNBean(){
//        super("com.zhongying.huanan.product.sdn.busi.TopoSDNBean");
//    }
//    /**
//     * 查询自营平台列表
//     * @param con
//     * @return
//     */
//    public String queryPTListData(Connection con){
//        ArrayList<Object[]> result = new ArrayList<Object[]>();
//        String sql = "select ptcode,ptname from ir_platform order by ptname";
//        ReportDBUtil dbUtil = new ReportDBUtil();
//        result = dbUtil.queryExec(sql, 2, con);
//        //转换成json字符串
//        StringBuffer sb = new StringBuffer("[");
//        for(int i=0,size=result.size();i<size;i++){
//            Object[] temp = result.get(i);
//            if(i!=0)
//                sb.append(",");
//            sb.append("[\""+temp[0]+"\",\""+temp[1]+"\"]");
//        }
//        sb.append("]");
//        return sb.toString();
//    }
//
//    /**
//     * 查询平台列表、接入点数量、告警接入点数量
//     * update 1.4 过滤首页告警接入点个数查询，1.城域网侧接入的端口或者板卡告警；2.非接入设备的设备脱网告警，可直接关联告警类型(DS0103)
//     * add by update 1.3
//     * @param con
//     * @return
//     */
//    public String queryPTListAllDataJSON(Connection con)
//    {
//        StringBuffer sb = new StringBuffer("[");
//        ArrayList<Object[]> orgList = new ArrayList<Object[]>();
//        ReportDBUtil dbUtil = new ReportDBUtil();
//        
//        String sql = "select p.ptcode,p.ptname,count(pi.deviceid||pi.ppdesc) from ir_platform p,ir_platformaccesspointinfo pi" +
//        		" where pi.cityname !='其它' and p.ptcode = pi.ptcode group by p.ptcode,p.ptname";
//        orgList = dbUtil.queryExec(sql, 3, con);
//        
//        //修改过滤
//        ArrayList<Object[]> ptAlarmAccessNumList = new ArrayList<Object[]>();
//        String alarmSql = "select ptcode,count(deviceid||ppdesc) from (" +
//        		" select pi.ptcode,pi.deviceid,pi.ppdesc from ir_platformaccesspointinfo pi ,alarmsummary a" +
//        		" where pi.cityname !='其它'and pi.deviceid = a.resid and pi.ppdesc = a.respara" +
//        		" union" +
//        		" select pi.ptcode,pi.deviceid,pi.ppdesc from ir_platformaccesspointinfo pi,ppinfo p,cardinfo c,alarmsummary a" +
//        		" where pi.cityname !='其它' and pi.deviceid = p.deviceid and pi.ppdesc = p.ppdescr and pi.deviceid = c.deviceid" +
//        		" and p.cardindex = c.cardindex and pi.deviceid = a.resid and c.slotcode = a.respara" +
//        		" union" +
//        		" select pi.ptcode,pi.deviceid,pi.ppdesc from ir_platformaccesspointinfo pi,ir_topodevice d,alarmsummary a" +
//        		" where pi.cityname !='其它' and pi.deviceid = d.topoflag and d.deviceid != pi.deviceid" +
//        		" and d.deviceid = a.resid and a.alarmtypeid = 'DS0103'" +
//        		" ) group by ptcode";
//        ptAlarmAccessNumList = dbUtil.queryExec(alarmSql, 2, con);
//        HashMap<Object,Object> ptAlarmAccessNumMap = new HashMap<Object,Object>();
//        for(Object[] temp:ptAlarmAccessNumList)
//        {
//            ptAlarmAccessNumMap.put(temp[0], temp[1]);
//        }
//        
//        Object alarmNum = 0;
//        for(int i=0,size=orgList.size();i<size;i++)
//        {
//            if(i!=0)
//                sb.append(",");
//            Object[] temp = orgList.get(i);
//            alarmNum = ptAlarmAccessNumMap.get(temp[0])==null?0:ptAlarmAccessNumMap.get(temp[0]);
//            sb.append("[\""+temp[0]+"\",\""+temp[1]+"\","+temp[2]+","+alarmNum+"]");
//        }
//        sb.append("]");
//        return sb.toString();
//    }
//    
//    /**
//     * 查询有告警的接入点列表
//     * @param ptCode
//     * @param rows
//     * @param page
//     * @param sort
//     * @param order
//     * @param con
//     * @return
//     * @throws InterruptedException 
//     */
//    public String queryAlarmAccessPListData(String ptCode,String rows,String page,String sort,String order,Connection con)
//    {
//        StringBuffer sb = new StringBuffer();
//        int start = Integer.parseInt(rows)*(Integer.parseInt(page)-1);
//        int end = Integer.parseInt(rows)*Integer.parseInt(page);
//        if(null==sort){
//            sort = "1";
//            order = "";
//        }
//        ArrayList<Object[]> orgList = new ArrayList<Object[]>();
//        ReportDBUtil dbUtil = new ReportDBUtil();
//        
//        String sql = "select ptcode,ptname,cityname,accesspointname,deviceid,devicename,ppdesc from (" +
//        		" select pi.ptcode,ip.ptname,pi.cityname,pi.accesspointname, pi.deviceid,pi.devicename, pi.ppdesc" +
//        		" from ir_platformaccesspointinfo pi ,alarmsummary a,ir_platform ip" +
//                " where pi.cityname !='其它' and pi.ptcode = '"+ptCode+"' and pi.ptcode = ip.ptcode" +
//                " and pi.deviceid = a.resid and pi.ppdesc = a.respara " +
//                " union" +
//                " select pi.ptcode,ip.ptname,pi.cityname,pi.accesspointname, pi.deviceid,pi.devicename, pi.ppdesc" +
//                " from ir_platformaccesspointinfo pi,ppinfo p,cardinfo c,alarmsummary a,ir_platform ip" +
//                " where pi.cityname !='其它' and pi.ptcode = '"+ptCode+"' and pi.ptcode = ip.ptcode" +
//                " and pi.deviceid = p.deviceid and pi.ppdesc = p.ppdescr and pi.deviceid = c.deviceid" +
//                " and p.cardindex = c.cardindex and pi.deviceid = a.resid and c.slotcode = a.respara" +
//                " union" +
//                " select pi.ptcode,ip.ptname,pi.cityname,pi.accesspointname, pi.deviceid,pi.devicename, pi.ppdesc" +
//                " from ir_platformaccesspointinfo pi,ir_topodevice d,alarmsummary a,ir_platform ip" +
//                " where pi.cityname !='其它' and pi.ptcode = '"+ptCode+"' and pi.ptcode = ip.ptcode" +
//                " and pi.deviceid = d.topoflag and d.deviceid != pi.deviceid" +
//                " and d.deviceid = a.resid and a.alarmtypeid = 'DS0103')" ;
//        orgList = dbUtil.queryExec(sql, 7, con);
//        int total = orgList.size();
//        
//        if(0==total){
//            sb.append("{\"total\":0,\"rows\":[{\"ptname\":\"无数据！\"}]}");
//        }else{
//            sb.append("{\"total\":"+total+",\"rows\":[");
//            for(int i=start,size=total;i<size&&i<end;i++){
//                Object[] temp = orgList.get(i);
//                if(i!=start)
//                    sb.append(",");
//                sb.append("{\"ptname\":\""+temp[1]+"\",\"cityname\":\""+temp[2]+"\",\"accesspointname\":\""+temp[3]+"\"," +
//                        "\"devicename\":\""+temp[5]+"\",\"ppdesc\":\""+temp[6]+"\",\"infod\":\"<a style='text-decoration:underline' onclick=viewUpTopo('"+temp[0]+"','"+temp[1]+"','"+temp[2]+"','"+temp[4]+"')>查看</a>\"}");
//            }
//            sb.append("]}");
//        }
//        
//        return sb.toString();
//    }
//    
//    /**
//     * 查询广东各地市指定平台接入点个数
//     * @param ptCode
//     * @param con
//     * @return
//     */
//    public String queryGDCityPTAccessPointNumData(String ptCode,Connection con){
//        ArrayList<Object[]> result = new ArrayList<Object[]>();
//        String sql = "select cityname,count(1) from ir_platformaccesspointinfo where ptcode = '"+ptCode+"' and cityname != '其它' group by cityname";
//        ReportDBUtil dbUtil = new ReportDBUtil();
//        result = dbUtil.queryExec(sql, 2, con);
//        int max = 0,min = Integer.MAX_VALUE;
//        int acvalue = 0;
//        StringBuffer sb = new StringBuffer("{");
//        for(int i=0,size=result.size();i<size;i++){
//            Object[] temp = result.get(i);
//            acvalue = Integer.parseInt(temp[1].toString());
//            max = acvalue>max?acvalue:max;
//            min = acvalue<min?acvalue:min;
//            if(i!=0)
//                sb.append(",");
//            sb.append("\""+temp[0]+"\":"+acvalue);
//        }
//        sb.append(",\"max\":"+max+",\"min\":"+min+"}");
//        return sb.toString();
//    }
//    /**
//     * 查询各平台各地市接入点个数表格数据
//     * @param con
//     * @return
//     */
//    public String queryPTAccessPointNumTableData(Connection con){
//        ArrayList<Object[]> result = new ArrayList<Object[]>();
//        String sql = "select cityname,ptcode,count(1) from ir_platformaccesspointinfo where cityname != '其它' group by cityname,ptcode order by cityname ";
//        ReportDBUtil dbUtil = new ReportDBUtil();
//        result = dbUtil.queryExec(sql, 3, con);
//        StringBuffer sb = new StringBuffer("{");
//        ArrayList<Object> cityNameList = new ArrayList<Object>();
//        HashMap<Object,Object> cityName_ptCodeAPNumMap = new HashMap<Object,Object>();
//        for(int i=0,size=result.size();i<size;i++){
//            Object[] temp = result.get(i);
//            if(!cityNameList.contains(temp[0]))
//                cityNameList.add(temp[0]);
//            cityName_ptCodeAPNumMap.put(temp[0]+"_"+temp[1], temp[2]);
//        }
//        sb.append("\"total\":"+cityNameList.size()+",\"rows\":[");
//        Object value = 0;
//        Object cityName = "";
//        for(int i=0,size=cityNameList.size();i<size;i++){
//            cityName = cityNameList.get(i);
//            if(i!=0)
//                sb.append(",");
//            sb.append("{\"city\":\""+cityName+"\"");
//            value = cityName_ptCodeAPNumMap.get(cityName+"_haobai")==null?0:cityName_ptCodeAPNumMap.get(cityName+"_haobai");
//            sb.append(",\"haobai\":\""+value+"\"");
//            value = cityName_ptCodeAPNumMap.get(cityName+"_shengpt")==null?0:cityName_ptCodeAPNumMap.get(cityName+"_shengpt");
//            sb.append(",\"shengpt\":\""+value+"\"");
//            value = cityName_ptCodeAPNumMap.get(cityName+"_iptv")==null?0:cityName_ptCodeAPNumMap.get(cityName+"_iptv");
//            sb.append(",\"iptv\":\""+value+"\"");
//            value = cityName_ptCodeAPNumMap.get(cityName+"_nocpt")==null?0:cityName_ptCodeAPNumMap.get(cityName+"_nocpt");
//            sb.append(",\"nocpt\":\""+value+"\"");
//            value = cityName_ptCodeAPNumMap.get(cityName+"_dns")==null?0:cityName_ptCodeAPNumMap.get(cityName+"_dns");
//            sb.append(",\"dns\":\""+value+"\"");
//            value = cityName_ptCodeAPNumMap.get(cityName+"_ngn")==null?0:cityName_ptCodeAPNumMap.get(cityName+"_ngn");
//            sb.append(",\"ngn\":\""+value+"\"");
//            value = cityName_ptCodeAPNumMap.get(cityName+"_bac")==null?0:cityName_ptCodeAPNumMap.get(cityName+"_bac");
//            sb.append(",\"bac\":\""+value+"\"");
//            value = cityName_ptCodeAPNumMap.get(cityName+"_itms")==null?0:cityName_ptCodeAPNumMap.get(cityName+"_itms");
//            sb.append(",\"itms\":\""+value+"\"");
//            value = cityName_ptCodeAPNumMap.get(cityName+"_apac")==null?0:cityName_ptCodeAPNumMap.get(cityName+"_apac");
//            sb.append(",\"apac\":\""+value+"\"");
//            value = cityName_ptCodeAPNumMap.get(cityName+"_ims")==null?0:cityName_ptCodeAPNumMap.get(cityName+"_ims");
//            sb.append(",\"ims\":\""+value+"\"}");
//        }
//        sb.append("]}");
//        return sb.toString();
//    }
//    /**
//     * 查询平台地市接入信息
//     * @param ptCode
//     * @param cityName
//     * @param con
//     * @return
//     */
//    public String queryPTCityAccessInfoData(String ptCode,String cityName,Connection con){
//        StringBuffer sb = new StringBuffer("{");
//        ArrayList<Object[]> orgList = new ArrayList<Object[]>();
//        ReportDBUtil dbUtil = new ReportDBUtil();
//        //1.查询平台名称信息
//        String ptNameSql = "select ptname from ir_platform where ptcode = '"+ptCode+"'";
//        orgList = dbUtil.queryExec(ptNameSql, 1, con);
//        Object ptName = "";
//        if(orgList.size()>0)
//            ptName = orgList.get(0)[0];
//        sb.append("\"ptname\":\""+ptName+"\"");
//        //2.查询接入电路数和带宽
//        String ptAccessCircuitNumSql = "select c.circuitid,c.bandwidth/1000 from ir_platformaccesspointinfo p,circuit c" +
//        		" where p.ptcode = '"+ptCode+"' and p.cityname = '"+cityName+"' and c.changetype = 0 and p.deviceid = c.adeviceid and p.ppdesc = c.aintdescr";
//        orgList = dbUtil.queryExec(ptAccessCircuitNumSql, 2, con);
//        int accessCircuitNum = orgList.size();
//        sb.append(",\"accesscircuitnum\":\""+accessCircuitNum+"条\"");
//        Double bandWidth = 0d;
//        String unit = "Mbps";
//        String bandWidthShow = "";
//        for(int i=0;i<accessCircuitNum;i++){
//            Object[] temp = orgList.get(i);
//            bandWidth = Double.parseDouble(temp[1].toString()) + bandWidth;
//        }
//        if(1000<bandWidth){
//            bandWidth = bandWidth/1000;
//            unit = "Gbps";
//        }
//        DecimalFormat df=new DecimalFormat("0.00");
//        if(Double.parseDouble(bandWidth.intValue()+"")==bandWidth)
//            bandWidthShow = bandWidth.intValue()+unit;
//        else
//            bandWidthShow = df.format(bandWidth)+unit;
//        sb.append(",\"bandwidth\":\""+bandWidthShow+"\"");
//        //3.告警查询，相关设备的整机告警、端口告警和电路告警
////        String alarmSql = "select a.sumalarmid from ir_platformaccesspointinfo p,alarmsummary a" +
////        		" where p.ptcode = '"+ptCode+"' and p.cityname = '"+cityName+"' and p.deviceid = a.resid" +
////        		" union" +
////        		" select a.sumalarmid from ir_platformaccesspointinfo p,circuit c,alarmsummary a" +
////        		" where p.ptcode = '"+ptCode+"' and p.cityname = '"+cityName+"' and c.changetype = 0" +
////        		" and p.deviceid = c.adeviceid and p.ppdesc = c.aintdescr and a.resid = c.circuitid";
////        orgList = dbUtil.queryExec(alarmSql, 1, con);
////        int alarmNum = 0;
////        alarmNum = orgList.size();
////        sb.append(",\"alarmnum\":\""+alarmNum+"个\"");
//        //4.查询利用率
//        String flowRateSql = "select c.circuitid,round(f.inavgvec*8/1000/c.bandwidth*100,2),round(f.outavgvec*8/1000/c.bandwidth*100,2)" +
//        		" from ir_platformaccesspointinfo p, circuit c,fluxmonicurinfo f where p.ptcode = '"+ptCode+"' and p.cityname = '"+cityName+"'" +
//        		" and c.changetype = 0 and p.deviceid = c.adeviceid and p.ppdesc = c.aintdescr and f.circuitid = c.circuitid";
//        orgList = dbUtil.queryExec(flowRateSql, 3, con);
//        Double inFlowRate = 0d , outFlowRate = 0d;
//        String inFlowRateShow = "" , outFlowRateShow = "";
//        if(0==orgList.size()){
//            inFlowRateShow = "0%";
//            outFlowRateShow = "0%";
//        }else{
//            for(int i=0,size=orgList.size();i<size;i++){
//                Object[] temp = orgList.get(i);
//                inFlowRate += Double.parseDouble(temp[1].toString());
//                outFlowRate += Double.parseDouble(temp[2].toString());
//            }
//            inFlowRate = inFlowRate/orgList.size();
//            outFlowRate = outFlowRate/orgList.size();
//            if(Double.parseDouble(inFlowRate.intValue()+"")==inFlowRate)
//                inFlowRateShow = inFlowRate.intValue()+"%";
//            else
//                inFlowRateShow = df.format(inFlowRate)+"%";
//            if(Double.parseDouble(outFlowRate.intValue()+"")==outFlowRate)
//                outFlowRateShow = outFlowRate.intValue()+"%";
//            else
//                outFlowRateShow = df.format(outFlowRate)+"%";
//        }
//        sb.append(",\"influxrate\":\""+inFlowRateShow+"\"");
//        sb.append(",\"outfluxrate\":\""+outFlowRateShow+"\"");
//        sb.append("}");
//        return sb.toString();
//    }
//    /**
//     * 查询平台地市接入拓扑信息
//     * @param ptCode
//     * @param cityName
//     * @param con
//     * @return
//     */
//    public String queryPTCityAccessTopoInfoData(String ptCode,String cityName,Connection con){
//        StringBuffer sb = new StringBuffer("[");
//        ArrayList<Object[]> orgList = new ArrayList<Object[]>();
//        ReportDBUtil dbUtil = new ReportDBUtil();
//        String accessPointSql = "select deviceid,devicename,accesspointname,ppdesc from ir_platformaccesspointinfo" +
//        		" where ptcode = '"+ptCode+"' and cityname = '"+cityName+"'";
//        orgList = dbUtil.queryExec(accessPointSql, 4, con);
//        ArrayList<Object> deviceIdList = new ArrayList<Object>();
//        ArrayList<Object[]> deviceList = new ArrayList<Object[]>();
//        HashMap<Object,ArrayList<Object[]>> deviceAccessMap = new HashMap<Object,ArrayList<Object[]>>();
//        for(int i=0,size=orgList.size();i<size;i++){
//            Object[] temp = orgList.get(i);
//            if(!deviceIdList.contains(temp[0]))
//            {
//                deviceIdList.add(temp[0]);
//                deviceList.add(new Object[]{temp[0],temp[1]});
//            }
//            ArrayList<Object[]> accessList = deviceAccessMap.get(temp[0]);
//            if(null==accessList)
//            {
//                deviceAccessMap.put(temp[0], accessList=new ArrayList<Object[]>());
//            }
//            accessList.add(new Object[]{temp[2],temp[3]});
//        }
//        //查询相关告警
//        HashMap<Object,Object> deviceMaxAlarmLevelMap = new HashMap<Object,Object>();
//        HashMap<Object,Object> deviceAlarmNumMap = new HashMap<Object,Object>();
//        //update by 1.3 修改接入设备告警数查询（关联上联拓扑的设备和电路的告警）
//        String alarmSql = "select deviceid, count(sumalarmid), max(alarmlevel) from (" +
//        		" select i.deviceid,a.sumalarmid,a.alarmlevel from ir_platformaccesspointinfo i ,circuit c,alarmsummary a" +
//        		" where i.ptcode = '"+ptCode+"' and i.cityname = '"+cityName+"' and i.deviceid = c.adeviceid and i.ppdesc = c.aintdescr" +
//        		" and c.circuitid = a.resid " +
//        		" union" +
//        		" select i.deviceid, a.sumalarmid, a.alarmlevel from ir_platformaccesspointinfo i, ir_topodevice td, alarmsummary a" +
//        		" where i.ptcode = '"+ptCode+"' and i.cityname = '"+cityName+"' and i.deviceid = td.topoflag and td.deviceid = a.resid" +
//        		" union" +
//        		" select i.deviceid, a.sumalarmid, a.alarmlevel from ir_platformaccesspointinfo i, ir_topocircuit tc, alarmsummary a" +
//        		" where i.ptcode = '"+ptCode+"' and i.cityname = '"+cityName+"' and i.deviceid = tc.topoflag and tc.circuitid = a.resid" +
//        		" ) group by deviceid";
//        orgList = dbUtil.queryExec(alarmSql, 3, con);
//        for(int i=0,size=orgList.size();i<size;i++)
//        {
//            Object[] temp = orgList.get(i);
//            deviceAlarmNumMap.put(temp[0], temp[1]);
//            deviceMaxAlarmLevelMap.put(temp[0], temp[2]);
//        }
//        Object maxAlarmLevel,alarmNum;
//        for(int i=0,size=deviceList.size();i<size;i++)
//        {
//            Object[] temp = deviceList.get(i);
//            maxAlarmLevel = deviceMaxAlarmLevelMap.get(temp[0])==null?0:deviceMaxAlarmLevelMap.get(temp[0]);
//            alarmNum = deviceAlarmNumMap.get(temp[0])==null?0:deviceAlarmNumMap.get(temp[0]);
//            if(i!=0)
//                sb.append(",");
//            sb.append("[\""+temp[0]+"\",\""+temp[1]+"\",[");
//            ArrayList<Object[]> accessList = deviceAccessMap.get(temp[0]);
//            for(int j=0,jSize=accessList.size();j<jSize;j++)
//            {
//                if(j!=0)
//                    sb.append(",");
//                Object[] access = accessList.get(j);
//                sb.append("[\""+access[0]+"\",\""+access[1]+"\"]");
//            }
//            sb.append("],"+maxAlarmLevel+","+alarmNum+"]");
//        }
//        sb.append("]");
//        return sb.toString();
//    }
//    /**
//     * 查询接入设备相关告警
//     * @param ptCode
//     * @param cityName
//     * @param deviceId
//     * @param con
//     * @return
//     */
//    public String queryAccessDeviceAlarmListData(String ptCode,String cityName,String deviceId,String rows,String page,String sort,String order,Connection con)
//    {
//        StringBuffer sb = new StringBuffer();
//        int start = Integer.parseInt(rows)*(Integer.parseInt(page)-1);
//        int end = Integer.parseInt(rows)*Integer.parseInt(page);
//        if(null==sort){
//            sort = "1";
//            order = "";
//        }
//        ArrayList<Object[]> orgList = new ArrayList<Object[]>();
//        ReportDBUtil dbUtil = new ReportDBUtil();
//        String alarmSql = "select alarmsource,starttime,alarmlevelname,alarmtypedesc,summary from (" +
//        		" select r.resname ||' '||decode(a.respara,'-1','',a.respara) alarmsource,a.starttime,al.alarmlevelname,t.alarmtypedesc,a.summary" +
//        		" from ir_platformaccesspointinfo i,res r,alarmsummary a,alarmlevel al,alarmtype t" +
//        		" where i.ptcode = '"+ptCode+"' and i.cityname = '"+cityName+"' and i.deviceid = '"+deviceId+"' and r.resid = i.deviceid" +
//        		" and r.resid = a.resid and a.alarmlevel = al.alarmlevel and a.alarmtypeid = t.alarmtypeid" +
//        		" union" +
//        		" select c.circuitname ||' '||decode(a.respara,'-1','',a.respara) alarmsource,a.starttime,al.alarmlevelname,t.alarmtypedesc,a.summary" +
//        		" from ir_platformaccesspointinfo i,circuit c,alarmsummary a,alarmlevel al,alarmtype t" +
//        		" where i.ptcode = '"+ptCode+"' and i.cityname = '"+cityName+"' and i.deviceid = '"+deviceId+"' and c.adeviceid = i.deviceid" +
//        		" and c.aintdescr = i.ppdesc and c.circuitid = a.resid and a.alarmlevel = al.alarmlevel and a.alarmtypeid = t.alarmtypeid)" +
//        		" order by 1";
//        orgList = dbUtil.queryExec(alarmSql, 5, con);
//        int total = orgList.size();
//        if(0==total){
//            sb.append("{\"total\":0,\"rows\":[{\"alarmsource\":\"当前无告警！\"}]}");
//        }else{
//            sb.append("{\"total\":"+total+",\"rows\":[");
//            for(int i=start,size=total;i<size&&i<end;i++){
//                Object[] temp = orgList.get(i);
//                if(i!=start)
//                    sb.append(",");
//                sb.append("{\"alarmsource\":\""+temp[0]+"\",\"starttime\":\""+temp[1]+"\",\"alarmlevelname\":\""+temp[2]+"\"," +
//                        "\"alarmtypedesc\":\""+temp[3]+"\",\"summary\":\""+formatJSONStr(temp[4].toString())+"\"}");
//            }
//            sb.append("]}");
//        }
//        return sb.toString();
//    }
//    /**
//     * 查询设备上联拓扑信息
//     * after 1.2
//     * @param ptCode
//     * @param cityName
//     * @param deviceId
//     * @param con
//     * @return
//     */
//    public String queryDeviceUpTopoInfoJsonData(String ptCode,String cityName,String deviceId,Connection con)
//    {
//        StringBuffer device_sb = new StringBuffer("[");
//        StringBuffer circuit_sb = new StringBuffer("[");
//        
//        ArrayList<Object[]> deviceList = new ArrayList<Object[]>();
//        ArrayList<Object[]> circuitList = new ArrayList<Object[]>();
//        
//        ArrayList<Object[]> resAlarmList = new ArrayList<Object[]>();
//        
//        ArrayList<Object[]> orgList = new ArrayList<Object[]>();
//        ReportDBUtil dbUtil = new ReportDBUtil();
//        
//        //接入侧电路
//        String accessCircuit_sql = "select 'accessPT',p.deviceid,c.circuitid,c.circuitname,p.deviceid,p.ppdesc,'--','--' from ir_platformaccesspointinfo p,circuit c" +
//        		" where p.ptcode = '"+ptCode+"' and p.cityname = '"+cityName+"' and p.deviceid = '"+deviceId+"' and p.deviceid = c.adeviceid" +
//        		" and p.ppdesc = c.aintdescr";
//        orgList = dbUtil.queryExec(accessCircuit_sql, 8, con);
//        circuitList.addAll(orgList);
//        
//        String accessCircuitAlarm_sql = "select c.circuitid,max(a.alarmlevel),count(a.sumalarmid) from ir_platformaccesspointinfo p,circuit c,alarmsummary a" +
//        		" where p.ptcode = '"+ptCode+"' and p.cityname = '"+cityName+"' and p.deviceid = '"+deviceId+"' and p.deviceid = c.adeviceid" +
//        		" and p.ppdesc = c.aintdescr and c.circuitid = a.resid group by c.circuitid";
//        orgList = dbUtil.queryExec(accessCircuitAlarm_sql, 3, con);
//        resAlarmList.addAll(orgList);
//        
//        //上联拓扑设备
//        String upTopoDevice_sql = "select td.devicelevel,td.deviceid,d.devicename,d.loopaddress,dm.devicemodelname,dp.devicepropname,d.osversion" +
//        		" from ir_topodevice td,device d,devicemodel dm,deviceprop dp where td.topoflag = '"+deviceId+"' and td.deviceid = d.deviceid" +
//        		" and d.devicemodelcode = dm.devicemodelcode and d.devicepropcode = dp.devicepropcode order by td.devicelevel";
//        deviceList = dbUtil.queryExec(upTopoDevice_sql, 7, con);
//        
//        String upTopoDeviceAlarm_sql = "select td.deviceid,max(a.alarmlevel),count(a.sumalarmid) from ir_topodevice td,alarmsummary a" +
//        		" where td.topoflag = '"+deviceId+"' and td.deviceid = a.resid group by td.deviceid";
//        orgList = dbUtil.queryExec(upTopoDeviceAlarm_sql, 3, con);
//        resAlarmList.addAll(orgList);
//        
//        //上联拓扑电路
//        String upTopoCircuit_sql = "select tc.sdeviceid,tc.edeviceid,tc.circuitid,c.circuitname,ad.devicename,c.aintdescr,bd.devicename,c.bintdescr" +
//        		" from ir_topocircuit tc ,circuit c, device ad, device bd where tc.topoflag = '"+deviceId+"' and tc.circuitid = c.circuitid" +
//        		" and c.adeviceid = ad.deviceid(+) and c.bdeviceid = bd.deviceid(+) order by tc.sdeviceid,tc.edeviceid";
//        orgList = dbUtil.queryExec(upTopoCircuit_sql, 8, con);
//        circuitList.addAll(orgList);
//        
//        String upTopoCircuitALarm_sql = "select tc.circuitid,max(a.alarmlevel),count(a.sumalarmid) from ir_topocircuit tc,alarmsummary a" +
//        		" where tc.topoflag = '"+deviceId+"' and tc.circuitid = a.resid group by tc.circuitid";
//        orgList = dbUtil.queryExec(upTopoCircuitALarm_sql, 3, con);
//        resAlarmList.addAll(orgList);
//        
//        //资源告警映射
//        HashMap<Object,Object> resMaxAlarmLevelMap = new HashMap<Object,Object>();
//        HashMap<Object,Object> resAlarmNumMap = new HashMap<Object,Object>();
//        for(Object[] temp:resAlarmList)
//        {
//            resMaxAlarmLevelMap.put(temp[0], temp[1]);
//            resAlarmNumMap.put(temp[0], temp[2]);
//        }
//        
//        //添加拓扑设备
//        Object deviceLevel = 0;
//        ArrayList<ArrayList<Object[]>> devicesList = new ArrayList<ArrayList<Object[]>>();
//        int maxAlarmLevel = 0, alarmNum = 0;
//        for(int i=0,size=deviceList.size();i<size;i++)
//        {
//            Object[] temp = deviceList.get(i);
//            ArrayList<Object[]> devices = new ArrayList<Object[]>();
//            if(deviceLevel.equals(temp[0]))
//            {
//                devices = devicesList.get(devicesList.size()-1);
//            }
//            else
//            {
//                devicesList.add(devices);
//                deviceLevel = temp[0];
//            }
//            maxAlarmLevel = resMaxAlarmLevelMap.get(temp[1])==null?0:Integer.parseInt(resMaxAlarmLevelMap.get(temp[1]).toString());
//            alarmNum = resAlarmNumMap.get(temp[1])==null?0:Integer.parseInt(resAlarmNumMap.get(temp[1]).toString());
//            devices.add(new Object[]{temp[1],temp[2],maxAlarmLevel,alarmNum,temp[3],temp[4],temp[5],temp[6]});
//        }
//        for(int i=0,size=devicesList.size();i<size;i++)
//        {
//            if(i!=0)
//                device_sb.append(",");
//            device_sb.append("[");
//            ArrayList<Object[]> devices = devicesList.get(i);
//            for(int j=0,jSize=devices.size();j<jSize;j++)
//            {
//                if(j!=0)
//                    device_sb.append(",");
//                Object[] device = devices.get(j);
//                device_sb.append("[\""+device[0]+"\",\""+device[1]+"\","+device[2]+","+device[3]+",\""+device[4]
//                        +"\",\""+device[5]+"\",\""+device[6]+"\",\""+device[7]+"\"]");
//                
//            }
//            device_sb.append("]");
//        }
//        device_sb.append("]");
//        
//        //添加电路拓扑
//        Object sDevice = "" , eDevice = "";
//        ArrayList<Object[]> devicessList = new ArrayList<Object[]>();
//        ArrayList<ArrayList<Object[]>> circuitsList = new ArrayList<ArrayList<Object[]>>();
//        for(int i=0,size=circuitList.size();i<size;i++)
//        {
//            Object[] temp = circuitList.get(i);
//            ArrayList<Object[]> circuits = new ArrayList<Object[]>();
//            if(!sDevice.equals(temp[0])||!eDevice.equals(temp[1]))
//            {
//                circuitsList.add(circuits);
//                sDevice = temp[0];
//                eDevice = temp[1];
//                devicessList.add(new Object[]{sDevice,eDevice});
//            }
//            else
//            {
//                circuits = circuitsList.get(circuitsList.size()-1);
//            }
//            maxAlarmLevel = resMaxAlarmLevelMap.get(temp[2])==null?0:Integer.parseInt(resMaxAlarmLevelMap.get(temp[2]).toString());
//            alarmNum = resAlarmNumMap.get(temp[2])==null?0:Integer.parseInt(resAlarmNumMap.get(temp[2]).toString());
//            circuits.add(new Object[]{temp[2],temp[3],maxAlarmLevel,alarmNum,temp[4],temp[5],temp[6],temp[7]});
//        }
//        for(int i=0,size=devicessList.size();i<size;i++)
//        {
//            if(i!=0)
//                circuit_sb.append(",");
//            circuit_sb.append("[");
//            Object[] devicess = devicessList.get(i);
//            circuit_sb.append("\""+devicess[0]+"\",\""+devicess[1]+"\",[");
//            ArrayList<Object[]> circuits = circuitsList.get(i);
//            for(int j=0,jSize=circuits.size();j<jSize;j++)
//            {
//                if(j!=0)
//                    circuit_sb.append(",");
//                Object[] circuit = circuits.get(j);
//                circuit_sb.append("[\""+circuit[0]+"\",\""+circuit[1]+"\","+circuit[2]+","+circuit[3]+",\""+circuit[4]+
//                        "\",\""+circuit[5]+"\",\""+circuit[6]+"\",\""+circuit[7]+"\"]");
//            }
//            circuit_sb.append("]");
//            circuit_sb.append("]");
//        }
//        circuit_sb.append("]");
//        return "{\"device\":"+device_sb.toString()+",\"circuit\":"+circuit_sb.toString()+"}";
//    }
//    /**
//     * 查询设备上联拓扑信息
//     * @deprecated after 1.2
//     * @param deviceId
//     * @param con
//     * @return
//     */
//    public String queryDeviceUpTopoInfoData(String ptCode,String cityName,String deviceId,Connection con){
//        //设备
//        StringBuffer device_sb = new StringBuffer("{");
//        //电路
//        StringBuffer circuit_sb = new StringBuffer("[");
//        //结果集临时变量
//        ArrayList<Object[]> orgList = new ArrayList<Object[]>();
//        //数据操作公共类
//        ReportDBUtil dbUtil = new ReportDBUtil();
//        //设备型号ID和名称映射关系
//        HashMap<Object,Object> deviceModelMap = new HashMap<Object,Object>();
//        this.queryDeviceModelMap(deviceModelMap, con);
//        //设备告警信息映射
//        HashMap<Object,Object> deviceMaxAlarmLevelMap = new HashMap<Object,Object>();
//        HashMap<Object,Object> deviceAlarmNumMap = new HashMap<Object,Object>();
//        //电路告警信息映射
//        HashMap<Object,Object> circuitMaxAlarmLevelMap = new HashMap<Object,Object>();
//        HashMap<Object,Object> circuitAlarmNumMap = new HashMap<Object,Object>();
//        //电路端口信息映射
//        HashMap<Object,Object> circuitADeviceNameMap = new HashMap<Object,Object>();
//        HashMap<Object,Object> circuitAPortNameMap = new HashMap<Object,Object>();
//        HashMap<Object,Object> circuitBDeviceNameMap = new HashMap<Object,Object>();
//        HashMap<Object,Object> circuitBPortNameMap = new HashMap<Object,Object>();
//        //查询并添加接入侧电路
//        queryAccessCircuitAndAddIt(circuit_sb,ptCode,cityName,deviceId,con);
//        //查询接入设备信息
//        String deviceInfoSql = "select d.devicename,dp.devicepropname,d.loopaddress,d.devicemodelcode,d.osversion from device d,deviceprop dp" +
//        		" where d.deviceid = '"+deviceId+"' and d.devicepropcode = dp.devicepropcode";
//        orgList = dbUtil.queryExec(deviceInfoSql, 5, con);
//        //接入设备ID,名称,属性
//        String deviceName = "" , deviceProp = "", deviceLoopAddress = "", deviceModel = "", deviceOSVersion = "";
//        if(0<orgList.size()){
//            deviceName = (String)orgList.get(0)[0];
//            deviceProp = (String)orgList.get(0)[1];
//            deviceLoopAddress = (String)orgList.get(0)[2];
//            deviceModel = (String)deviceModelMap.get(orgList.get(0)[3]);
//            deviceOSVersion = (String)orgList.get(0)[4];
//        }
//        //查询接入设备告警信息
//        String deviceAlarmSql = "select max(a.alarmlevel),count(1) from alarmsummary a where a.resid = '"+deviceId+"'";
//        orgList = dbUtil.queryExec(deviceAlarmSql, 2, con);
//        int maxAlarmLevel = 0, alarmNum = 0;
//        if(0<orgList.size()){
//            maxAlarmLevel = Integer.parseInt(orgList.get(0)[0]==null?"0":orgList.get(0)[0].toString());
//            alarmNum = Integer.parseInt(orgList.get(0)[1].toString());
//        }
//        device_sb.append("\""+deviceProp+"\":[[\""+deviceId+"\",\""+deviceName+"\","+maxAlarmLevel+","+alarmNum+",\""+deviceLoopAddress+"\"" +
//        		",\""+deviceModel+"\",\""+deviceProp+"\",\""+deviceOSVersion+"\"]]");
//        
//        //分三种情况，接入设备属性为BR路由器、接入设备属性为SR路由器和BAS和接入设备属性为CR路由器的
//        if("BR路由器".equals(deviceProp)){
//            //CR层相关设备ID列表
//            ArrayList<Object> crDeviceIdList = new ArrayList<Object>();
//            //CR层相关设备列表
//            ArrayList<Object[]> crDeviceList = new ArrayList<Object[]>();
//            //BR-CR设备电路映射
//            HashMap<Object,ArrayList<Object[]>> deviceCircuitListMap = new HashMap<Object,ArrayList<Object[]>>();
//            //BR-CR电路ID列表
//            ArrayList<Object> br2crCircuitIdList = new ArrayList<Object>();
//            //查询相连的CR层信息
//            this.queryDeivceAndCircuitInfoByAccessDeviceId(crDeviceIdList, crDeviceList, deviceCircuitListMap, br2crCircuitIdList
//                    , deviceId, "CR路由器", deviceModelMap, con);
//            //CR设备查询条件
//            String crConSql = this.getSqlCon(crDeviceIdList);
//            //查询CR设备告警
//            this.queryResAlarmMap(deviceMaxAlarmLevelMap, deviceAlarmNumMap, crConSql, con);
//            //br2cr电路查询条件
//            String br2crConSql = this.getSqlCon(br2crCircuitIdList);
//            //查询BR到CR电路告警信息
//            this.queryResAlarmMap(circuitMaxAlarmLevelMap, circuitAlarmNumMap, br2crConSql, con);
//            //查询BR到CR电路端口信息
//            this.queryCircuitDevicePortMap(circuitADeviceNameMap, circuitAPortNameMap, circuitBDeviceNameMap, circuitBPortNameMap, br2crConSql, con);
//            //添加设备
//            this.appendDeviceSb(device_sb, crDeviceList, "CR路由器", deviceMaxAlarmLevelMap, deviceAlarmNumMap);
//            //添加电路
//            this.appendCircuitSb(circuit_sb, deviceId, crDeviceIdList, deviceCircuitListMap, circuitMaxAlarmLevelMap, circuitAlarmNumMap
//                    , circuitADeviceNameMap, circuitAPortNameMap, circuitBDeviceNameMap, circuitBPortNameMap);
//            //查询CR设备之间的互联电路
//            ArrayList<Object> hulianList = new ArrayList<Object>();
//            HashMap<Object,ArrayList<Object[]>> hulianCircuitListMap = new HashMap<Object,ArrayList<Object[]>>();
//            ArrayList<Object> crHulianCircuitIdList = new ArrayList<Object>();
//            this.queryHulianCircuitInfo(hulianList, crHulianCircuitIdList, hulianCircuitListMap, crConSql, con);
//            //cr互联电路查询条件
//            String crHulianConSql = this.getSqlCon(crHulianCircuitIdList);
//            //查询CR互联电路告警信息
//            this.queryResAlarmMap(circuitMaxAlarmLevelMap, circuitAlarmNumMap, crHulianConSql, con);
//            //查询CR互联电路端口信息
//            this.queryCircuitDevicePortMap(circuitADeviceNameMap, circuitAPortNameMap, circuitBDeviceNameMap, circuitBPortNameMap, crHulianConSql, con);
//            //添加CR互联电路
//            this.appendCircuitSb(circuit_sb, hulianList, hulianCircuitListMap, circuitMaxAlarmLevelMap, circuitAlarmNumMap, circuitADeviceNameMap
//                    , circuitAPortNameMap, circuitBDeviceNameMap, circuitBPortNameMap);
//        }else if("SR路由器".equals(deviceProp)||"BAS".equals(deviceProp)){
//            //BR层相关设备ID列表
//            ArrayList<Object> brDeviceIdList = new ArrayList<Object>();
//            //BR层相关设备列表
//            ArrayList<Object[]> brDeviceList = new ArrayList<Object[]>();
//            //BAS/SR-BR设备电路映射
//            HashMap<Object,ArrayList<Object[]>> deviceCircuitListMap = new HashMap<Object,ArrayList<Object[]>>();
//            //BAS/SR-BR电路ID列表
//            ArrayList<Object> basorsr2brCircuitIdList = new ArrayList<Object>();
//            //查询相连的BR层信息
//            this.queryDeivceAndCircuitInfoByAccessDeviceId(brDeviceIdList, brDeviceList, deviceCircuitListMap, basorsr2brCircuitIdList
//                    , deviceId, "BR路由器", deviceModelMap, con);
//            //BR设备查询条件
//            String brConSql = this.getSqlCon(brDeviceIdList);
//            //查询BR设备告警
//            this.queryResAlarmMap(deviceMaxAlarmLevelMap, deviceAlarmNumMap, brConSql, con);
//            //BAS/SR到BR电路查询条件
//            String basorsr2brConSql = this.getSqlCon(basorsr2brCircuitIdList);
//            //查询BAS/SR到BR电路告警信息
//            this.queryResAlarmMap(circuitMaxAlarmLevelMap, circuitAlarmNumMap, basorsr2brConSql, con);
//            //查询BAS/SR到BR电路端口信息
//            this.queryCircuitDevicePortMap(circuitADeviceNameMap, circuitAPortNameMap, circuitBDeviceNameMap, circuitBPortNameMap, basorsr2brConSql, con);
//            //添加设备
//            this.appendDeviceSb(device_sb, brDeviceList, "BR路由器", deviceMaxAlarmLevelMap, deviceAlarmNumMap);
//            //添加电路
//            this.appendCircuitSb(circuit_sb, deviceId, brDeviceIdList, deviceCircuitListMap, circuitMaxAlarmLevelMap, circuitAlarmNumMap, circuitADeviceNameMap
//                    , circuitAPortNameMap, circuitBDeviceNameMap, circuitBPortNameMap);
//            //查询BR设备之间的互联电路
//            ArrayList<Object> brHulianList = new ArrayList<Object>();
//            ArrayList<Object> brHulianCircuitIdList = new ArrayList<Object>();
//            HashMap<Object,ArrayList<Object[]>> brHulianCircuitListMap = new HashMap<Object,ArrayList<Object[]>>();
//            this.queryHulianCircuitInfo(brHulianList, brHulianCircuitIdList, brHulianCircuitListMap, brConSql, con);
//            //BR互联电路查询条件
//            String brHulianConSql = this.getSqlCon(brHulianCircuitIdList);
//            //查询BR互联电路告警信息
//            this.queryResAlarmMap(circuitMaxAlarmLevelMap, circuitAlarmNumMap, brHulianConSql, con);
//            //查询BR互联电路端口信息
//            this.queryCircuitDevicePortMap(circuitADeviceNameMap, circuitAPortNameMap, circuitBDeviceNameMap, circuitBPortNameMap, brHulianConSql, con);
//            //添加BR互联电路
//            this.appendCircuitSb(circuit_sb, brHulianList, brHulianCircuitListMap, circuitMaxAlarmLevelMap, circuitAlarmNumMap, circuitADeviceNameMap
//                    , circuitAPortNameMap, circuitBDeviceNameMap, circuitBPortNameMap);
//            //查询与BR相连的CR信息
//            //CR设备ID列表
//            ArrayList<Object> crDeviceIdList = new ArrayList<Object>();
//            //CR设备列表
//            ArrayList<Object[]> crDeviceList = new ArrayList<Object[]>();
//            //br-cr设备ID列表
//            ArrayList<Object> deviceBRCRList = new ArrayList<Object>();
//            //br-cr设备ID电路列表映射关系
//            HashMap<Object,ArrayList<Object[]>> deviceBRCRCircuitMap = new HashMap<Object,ArrayList<Object[]>>();
//            //br-cr电路ID列表
//            ArrayList<Object> br2crCircuitIdList = new ArrayList<Object>();
//            this.queryDeviceAndCircuitInfoByDevicesAndPropName(crDeviceIdList, crDeviceList, deviceBRCRList, deviceBRCRCircuitMap, br2crCircuitIdList, deviceModelMap, brConSql, "CR路由器", con);
//            //CR设备查询条件
//            String crConSql = this.getSqlCon(crDeviceIdList);
//            //查询CR设备告警
//            this.queryResAlarmMap(deviceMaxAlarmLevelMap, deviceAlarmNumMap, crConSql, con);
//            //添加cr设备
//            this.appendDeviceSb(device_sb, crDeviceList, "CR路由器", deviceMaxAlarmLevelMap, deviceAlarmNumMap);
//            //br2cr电路查询条件
//            String br2crConSql = this.getSqlCon(br2crCircuitIdList);
//            //查询BR到CR电路告警信息
//            this.queryResAlarmMap(circuitMaxAlarmLevelMap, circuitAlarmNumMap, br2crConSql, con);
//            //查询BR到CR电路端口信息
//            this.queryCircuitDevicePortMap(circuitADeviceNameMap, circuitAPortNameMap, circuitBDeviceNameMap, circuitBPortNameMap, br2crConSql, con);
//            //添加br-cr间电路
//            this.appendCircuitSb(circuit_sb, deviceBRCRList, deviceBRCRCircuitMap, circuitMaxAlarmLevelMap, circuitAlarmNumMap, circuitADeviceNameMap
//                    , circuitAPortNameMap, circuitBDeviceNameMap, circuitBPortNameMap);
//            //查询cr设备之间的互联电路
//            ArrayList<Object> crHulianList = new ArrayList<Object>();
//            HashMap<Object,ArrayList<Object[]>> crHulianCircuitListMap = new HashMap<Object,ArrayList<Object[]>>();
//            ArrayList<Object> crHulianCircuitIdList = new ArrayList<Object>();
//            this.queryHulianCircuitInfo(crHulianList, crHulianCircuitIdList, crHulianCircuitListMap, crConSql, con);
//            //cr互联电路查询条件
//            String crHulianConSql = this.getSqlCon(crHulianCircuitIdList);
//            //查询CR互联电路告警信息
//            this.queryResAlarmMap(circuitMaxAlarmLevelMap, circuitAlarmNumMap, crHulianConSql, con);
//            //查询CR互联电路端口信息
//            this.queryCircuitDevicePortMap(circuitADeviceNameMap, circuitAPortNameMap, circuitBDeviceNameMap, circuitBPortNameMap, crHulianConSql, con);
//            //添加CR互联电路
//            this.appendCircuitSb(circuit_sb, crHulianList, crHulianCircuitListMap, circuitMaxAlarmLevelMap, circuitAlarmNumMap, circuitADeviceNameMap, circuitAPortNameMap
//                    , circuitBDeviceNameMap, circuitBPortNameMap);
//        } 
//        device_sb.append("}");
//        circuit_sb.append("]");
//        return "{\"device\":"+device_sb.toString()+",\"circuit\":"+circuit_sb.toString()+"}";
//    }
//    /**
//     * 查询并添加接入侧电路
//     * @deprecated after 1.2
//     * @param circuit_sb
//     * @param ptCode
//     * @param cityName
//     * @param deviceId
//     * @param con
//     */
//    public void queryAccessCircuitAndAddIt(StringBuffer circuit_sb,String ptCode,String cityName,String deviceId,Connection con)
//    {
//        ArrayList<Object[]> orgList = new ArrayList<Object[]>();
//        ReportDBUtil dbUtil = new ReportDBUtil();
//        String sql = "select c.circuitid,c.circuitname,i.devicename,i.ppdesc from ir_platformaccesspointinfo i,circuit c" +
//        		" where i.ptcode = '"+ptCode+"' and i.cityname = '"+cityName+"' and i.deviceid = '"+deviceId+"'" +
//        		" and c.adeviceid = i.deviceid and c.aintdescr = i.ppdesc";
//        orgList = dbUtil.queryExec(sql, 4, con);
//        ArrayList<Object> circuitIdList = new ArrayList<Object>();
//        ArrayList<Object[]> circuitList = new ArrayList<Object[]>();
//        for(int i=0,size=orgList.size();i<size;i++){
//            Object[] temp = orgList.get(i);
//            circuitIdList.add(temp[0]);
//            circuitList.add(temp);
//        }
//        String circuitConSql = getSqlCon(circuitIdList);
//        String alarmSql = "select a.resid,count(a.sumalarmid),max(a.alarmlevel) from alarmsummary a where a.resid in ("+circuitConSql+") group by a.resid";
//        orgList = dbUtil.queryExec(alarmSql, 3, con);
//        HashMap<Object,Object> circuitMaxAlarmLevelMap = new HashMap<Object,Object>();
//        HashMap<Object,Object> circuitAlarmNumMap = new HashMap<Object,Object>();
//        for(int i=0,size=orgList.size();i<size;i++){
//            Object[] temp = orgList.get(i);
//            circuitAlarmNumMap.put(temp[0], temp[1]);
//            circuitMaxAlarmLevelMap.put(temp[0], temp[2]);
//        }
//        int maxAlarmLevel = 0, alarmNum = 0;
//        circuit_sb.append("[\""+deviceId+"\",\"accessPT\",[");
//        for(int j=0,jSize=circuitList.size();j<jSize;j++){
//            if(j!=0)
//                circuit_sb.append(",");
//            maxAlarmLevel = circuitMaxAlarmLevelMap.get(circuitList.get(j)[0])==null?0:Integer.parseInt(circuitMaxAlarmLevelMap.get(circuitList.get(j)[0]).toString());
//            alarmNum = circuitAlarmNumMap.get(circuitList.get(j)[0])==null?0:Integer.parseInt(circuitAlarmNumMap.get(circuitList.get(j)[0]).toString());
//            circuit_sb.append("[\""+circuitList.get(j)[0]+"\",\""+circuitList.get(j)[1]+"\","+maxAlarmLevel+","+alarmNum+"" +
//                    ",\""+circuitList.get(j)[2]+"\",\""+circuitList.get(j)[3]+"\",\"\",\"\"]");
//        }
//        circuit_sb.append("]]");
//
//    }
//    /**
//     * 查询设备属性ID名称映射关系
//     * @deprecated after 1.2
//     * @param deviceModelMap
//     * @param con
//     */
//    public void queryDeviceModelMap(HashMap<Object,Object> deviceModelMap,Connection con){
//        ArrayList<Object[]> orgList = new ArrayList<Object[]>();
//        //数据操作公共类
//        ReportDBUtil dbUtil = new ReportDBUtil();
//        String deviceModelMapSql = "select devicemodelcode,devicemodelname from devicemodel";
//        orgList = dbUtil.queryExec(deviceModelMapSql, 2, con);
//        for(int i=0,size=orgList.size();i<size;i++){
//            Object[] temp = orgList.get(i);
//            deviceModelMap.put(temp[0], temp[1]);
//        }
//    }
//    /**
//     * 通过接入设备ID查询上联设备电路信息
//     * @deprecated after 1.2
//     * @param deviceIdList
//     * @param deviceList
//     * @param deviceCircuitListMap
//     * @param circuitIdList
//     * @param deviceId
//     * @param devicePropName
//     * @param deviceModelMap
//     * @param con
//     */
//    public void queryDeivceAndCircuitInfoByAccessDeviceId(ArrayList<Object> deviceIdList,ArrayList<Object[]> deviceList
//            ,HashMap<Object,ArrayList<Object[]>> deviceCircuitListMap,ArrayList<Object> circuitIdList,String deviceId
//            ,String devicePropName,HashMap<Object,Object> deviceModelMap,Connection con){
//        //原始结果集
//        ArrayList<Object[]> orgList = new ArrayList<Object[]>();
//        //数据操作公共类
//        ReportDBUtil dbUtil = new ReportDBUtil();
//        String sql = "select c.circuitid, c.circuitname, d.deviceid, d.devicename, d.loopaddress, d.devicemodelcode, d.osversion" +
//                " from circuit c, device d, deviceprop dp" +
//                " where c.changetype = 0 and c.adeviceid ='"+deviceId+"' and c.bdeviceid = d.deviceid and d.changetype = 0" +
//                " and d.devicepropcode = dp.devicepropcode and dp.devicepropname = '"+devicePropName+"'" +
//                " union" +
//                " select c.circuitid, c.circuitname, d.deviceid, d.devicename, d.loopaddress, d.devicemodelcode, d.osversion" +
//                " from circuit c, device d, deviceprop dp" +
//                " where c.changetype = 0 and c.bdeviceid = '"+deviceId+"' and c.adeviceid = d.deviceid and d.changetype = 0" +
//                " and d.devicepropcode = dp.devicepropcode and dp.devicepropname = '"+devicePropName+"'";
//        orgList = dbUtil.queryExec(sql, 7, con);
//        for(int i=0,size=orgList.size();i<size;i++){
//            Object[] temp = orgList.get(i);
//            Object[] device = {temp[2],temp[3],temp[4],deviceModelMap.get(temp[5]),devicePropName,temp[6]};
//            if(!deviceIdList.contains(temp[2])){
//                deviceIdList.add(temp[2]);
//                deviceList.add(device);
//            }
//            ArrayList<Object[]> circuitList = deviceCircuitListMap.get(temp[2]);
//            if(null==circuitList){
//                circuitList = new ArrayList<Object[]>();
//                deviceCircuitListMap.put(temp[2], circuitList);
//            }
//            circuitList.add(new Object[]{temp[0],temp[1]});
//            circuitIdList.add(temp[0]);
//        }
//    }
//    /**
//     * 查询设备电路信息
//     * @deprecated after 1.2
//     * @param deviceIdList
//     * @param deviceList
//     * @param devicesList
//     * @param devicesCircuitMap
//     * @param circuitIdList
//     * @param deviceModelMap
//     * @param sqlCon
//     * @param devicePropName
//     * @param con
//     */
//    public void queryDeviceAndCircuitInfoByDevicesAndPropName(ArrayList<Object> deviceIdList,ArrayList<Object[]> deviceList, ArrayList<Object> devicesList
//            , HashMap<Object,ArrayList<Object[]>> devicesCircuitMap,ArrayList<Object> circuitIdList,HashMap<Object,Object> deviceModelMap,String sqlCon,String devicePropName,Connection con){
//        //原始结果集
//        ArrayList<Object[]> orgList = new ArrayList<Object[]>();
//        //数据操作公共类
//        ReportDBUtil dbUtil = new ReportDBUtil();
//        String crSql = "select c.circuitid, c.circuitname, ad.deviceid, ad.devicename,bd.deviceid,bd.devicename," +
//                " bd.loopaddress, bd.devicemodelcode, bd.osversion" +
//                " from circuit c, device ad, device bd, deviceprop dp where c.changetype = 0" +
//                " and c.adeviceid in ("+sqlCon+") and c.adeviceid = ad.deviceid" +
//                " and c.bdeviceid = bd.deviceid and bd.changetype = 0 and bd.devicepropcode = dp.devicepropcode" +
//                " and dp.devicepropname = '"+devicePropName+"'" +
//                " union" +
//                " select c.circuitid, c.circuitname, bd.deviceid, bd.devicename,ad.deviceid,ad.devicename," +
//                " ad.loopaddress, ad.devicemodelcode, ad.osversion" +
//                " from circuit c, device ad, device bd, deviceprop dp where c.changetype = 0" +
//                " and c.bdeviceid in ("+sqlCon+") and c.bdeviceid = bd.deviceid" +
//                " and c.adeviceid = ad.deviceid and ad.changetype = 0 and ad.devicepropcode = dp.devicepropcode" +
//                " and dp.devicepropname = '"+devicePropName+"'";
//        orgList = dbUtil.queryExec(crSql, 9, con);
//        
//        for(int i=0,size=orgList.size();i<size;i++){
//            Object[] temp = orgList.get(i);
//            if(!deviceIdList.contains(temp[4])){
//                deviceIdList.add(temp[4]);
//                deviceList.add(new Object[]{temp[4],temp[5],temp[6],deviceModelMap.get(temp[7]),devicePropName,temp[8]});
//            }
//            if(!devicesList.contains(temp[2]+"_"+temp[4]))
//                devicesList.add(temp[2]+"_"+temp[4]);
//            ArrayList<Object[]> circuitList = devicesCircuitMap.get(temp[2]+"_"+temp[4]);
//            if(null==circuitList){
//                circuitList = new ArrayList<Object[]>();
//                devicesCircuitMap.put(temp[2]+"_"+temp[4], circuitList);
//            }
//            circuitList.add(new Object[]{temp[0],temp[1]});
//            circuitIdList.add(temp[0]);
//        }
//    }
//    /**
//     * 获取sql格式
//     * @deprecated after 1.2
//     * @param idList
//     * @return
//     */
//    public String getSqlCon(ArrayList<Object> idList){
//        String sqlCon = "";
//        for(int i=0,size=idList.size();i<size;i++){
//            if(i!=0)
//                sqlCon += ",";
//            sqlCon += "'"+idList.get(i)+"'";
//        }
//        return sqlCon;
//    }
//    /**
//     * 查询互联电路信息
//     * @deprecated after 1.2
//     * @param hulianList
//     * @param hulianCircuitIdList
//     * @param hulianCircuitListMap
//     * @param sqlCon
//     * @param con
//     */
//    public void queryHulianCircuitInfo(ArrayList<Object> hulianList,ArrayList<Object> hulianCircuitIdList,HashMap<Object,ArrayList<Object[]>> hulianCircuitListMap,String sqlCon,Connection con){
//        //原始结果集
//        ArrayList<Object[]> orgList = new ArrayList<Object[]>();
//        //数据操作公共类
//        ReportDBUtil dbUtil = new ReportDBUtil();
//        String brHulianSql = "select c.circuitid,c.circuitname,c.adeviceid,c.bdeviceid from circuit c" +
//                " where c.adeviceid in ("+sqlCon+") and c.bdeviceid in ("+sqlCon+") and c.changetype = 0";
//        orgList = dbUtil.queryExec(brHulianSql, 4, con);
//        for(int i=0,size=orgList.size();i<size;i++){
//            Object[] temp = orgList.get(i);
//            if(!hulianList.contains(temp[2]+"_"+temp[3])&&!hulianList.contains(temp[3]+"_"+temp[2]))
//                hulianList.add(temp[2]+"_"+temp[3]);
//            Object key = hulianList.contains(temp[2]+"_"+temp[3])?temp[2]+"_"+temp[3]:temp[3]+"_"+temp[2];
//            ArrayList<Object[]> circuitList = hulianCircuitListMap.get(key);
//            if(null==circuitList){
//                circuitList = new ArrayList<Object[]>();
//                hulianCircuitListMap.put(key, circuitList);
//            }
//            circuitList.add(new Object[]{temp[0],temp[1]});
//            hulianCircuitIdList.add(temp[0]);
//        }
//    }
//    /**
//     * 添加设备信息
//     * @deprecated after 1.2
//     * @param device_sb
//     * @param deviceList
//     * @param devicePropName
//     * @param deviceMaxAlarmLevelMap
//     * @param deviceAlarmNumMap
//     */
//    public void appendDeviceSb(StringBuffer device_sb,ArrayList<Object[]> deviceList,String devicePropName,HashMap<Object,Object> deviceMaxAlarmLevelMap
//            ,HashMap<Object,Object> deviceAlarmNumMap){
//        
//        device_sb.append(",\""+devicePropName+"\":[");
//        int maxAlarmLevel = 0, alarmNum = 0;
//        for(int i=0,size=deviceList.size();i<size;i++){
//            Object[] temp = deviceList.get(i);
//            if(i!=0)
//                device_sb.append(",");
//            maxAlarmLevel = deviceMaxAlarmLevelMap.get(temp[0])==null?0:Integer.parseInt(deviceMaxAlarmLevelMap.get(temp[0]).toString());
//            alarmNum = deviceAlarmNumMap.get(temp[0])==null?0:Integer.parseInt(deviceAlarmNumMap.get(temp[0]).toString());
//            device_sb.append("[\""+temp[0]+"\",\""+temp[1]+"\","+maxAlarmLevel+","+alarmNum+",\""+temp[2]+"\",\""+temp[3]+"\"" +
//                    ",\""+temp[4]+"\",\""+temp[5]+"\"]");
//        }
//        device_sb.append("]");
//    }
//    /**
//     * 添加电路信息
//     * @deprecated after 1.2
//     * @param circuit_sb
//     * @param deviceId
//     * @param deviceIdList
//     * @param deviceCircuitListMap
//     * @param circuitMaxAlarmLevelMap
//     * @param circuitAlarmNumMap
//     * @param circuitADeviceNameMap
//     * @param circuitAPortNameMap
//     * @param circuitBDeviceNameMap
//     * @param circuitBPortNameMap
//     */
//    public void appendCircuitSb(StringBuffer circuit_sb,String deviceId,ArrayList<Object> deviceIdList,HashMap<Object,ArrayList<Object[]>> deviceCircuitListMap
//            ,HashMap<Object,Object> circuitMaxAlarmLevelMap,HashMap<Object,Object> circuitAlarmNumMap,HashMap<Object,Object> circuitADeviceNameMap
//            ,HashMap<Object,Object> circuitAPortNameMap,HashMap<Object,Object> circuitBDeviceNameMap,HashMap<Object,Object> circuitBPortNameMap){
//        int maxAlarmLevel = 0, alarmNum = 0;
//        for(int i=0,size=deviceIdList.size();i<size;i++){
//            Object temp = deviceIdList.get(i);
//            circuit_sb.append(",");
//            circuit_sb.append("[\""+deviceId+"\",\""+temp+"\",[");
//            ArrayList<Object[]> circuitList = deviceCircuitListMap.get(temp);
//            for(int j=0,jSize=circuitList.size();j<jSize;j++){
//                if(j!=0)
//                    circuit_sb.append(",");
//                maxAlarmLevel = circuitMaxAlarmLevelMap.get(circuitList.get(j)[0])==null?0:Integer.parseInt(circuitMaxAlarmLevelMap.get(circuitList.get(j)[0]).toString());
//                alarmNum = circuitAlarmNumMap.get(circuitList.get(j)[0])==null?0:Integer.parseInt(circuitAlarmNumMap.get(circuitList.get(j)[0]).toString());
//                circuit_sb.append("[\""+circuitList.get(j)[0]+"\",\""+circuitList.get(j)[1]+"\","+maxAlarmLevel+","+alarmNum+"" +
//                        ",\""+circuitADeviceNameMap.get(circuitList.get(j)[0])+"\",\""+circuitAPortNameMap.get(circuitList.get(j)[0])+"\"" +
//                        ",\""+circuitBDeviceNameMap.get(circuitList.get(j)[0])+"\",\""+circuitBPortNameMap.get(circuitList.get(j)[0])+"\"]");
//            }
//            circuit_sb.append("]]");
//        }
//    }
//    /**
//     * 添加电路信息
//     * @deprecated after 1.2
//     * @param circuit_sb
//     * @param devicesList
//     * @param devicesCircuitListMap
//     * @param circuitMaxAlarmLevelMap
//     * @param circuitAlarmNumMap
//     * @param circuitADeviceNameMap
//     * @param circuitAPortNameMap
//     * @param circuitBDeviceNameMap
//     * @param circuitBPortNameMap
//     */
//    public void appendCircuitSb(StringBuffer circuit_sb,ArrayList<Object> devicesList,HashMap<Object,ArrayList<Object[]>> devicesCircuitListMap
//            ,HashMap<Object,Object> circuitMaxAlarmLevelMap,HashMap<Object,Object> circuitAlarmNumMap,HashMap<Object,Object> circuitADeviceNameMap
//            ,HashMap<Object,Object> circuitAPortNameMap,HashMap<Object,Object> circuitBDeviceNameMap,HashMap<Object,Object> circuitBPortNameMap){
//        int maxAlarmLevel = 0, alarmNum = 0;
//        for(int i=0,size=devicesList.size();i<size;i++){
//            circuit_sb.append(",");
//            String temp = (String)devicesList.get(i);
//            String[] tArray = temp.split("_");
//            circuit_sb.append("[\""+tArray[0]+"\",\""+tArray[1]+"\",[");
//            ArrayList<Object[]> circuitList = devicesCircuitListMap.get(temp);
//            for(int j=0,jSize=circuitList.size();j<jSize;j++){
//                if(j!=0)
//                    circuit_sb.append(",");
//                maxAlarmLevel = circuitMaxAlarmLevelMap.get(circuitList.get(j)[0])==null?0:Integer.parseInt(circuitMaxAlarmLevelMap.get(circuitList.get(j)[0]).toString());
//                alarmNum = circuitAlarmNumMap.get(circuitList.get(j)[0])==null?0:Integer.parseInt(circuitAlarmNumMap.get(circuitList.get(j)[0]).toString());
//                circuit_sb.append("[\""+circuitList.get(j)[0]+"\",\""+circuitList.get(j)[1]+"\","+maxAlarmLevel+","+alarmNum+"" +
//                        ",\""+circuitADeviceNameMap.get(circuitList.get(j)[0])+"\",\""+circuitAPortNameMap.get(circuitList.get(j)[0])+"\"" +
//                        ",\""+circuitBDeviceNameMap.get(circuitList.get(j)[0])+"\",\""+circuitBPortNameMap.get(circuitList.get(j)[0])+"\"]");
//            }
//            circuit_sb.append("]]");
//        }
//    }
//    /**
//     * 查询资源告警
//     * @deprecated after 1.2
//     * @param resMaxAlarmLevelMap
//     * @param resAlarmNumMap
//     * @param sqlCon
//     * @param con
//     */
//    public void queryResAlarmMap(HashMap<Object,Object> resMaxAlarmLevelMap,HashMap<Object,Object> resAlarmNumMap,String sqlCon,Connection con){
//        ArrayList<Object[]> orgList = new ArrayList<Object[]>();
//        //数据操作公共类
//        ReportDBUtil dbUtil = new ReportDBUtil();
//        String sql = "select a.resid,max(a.alarmlevel),count(1) from alarmsummary a where a.resid in ("+sqlCon+") group by a.resid";
//        orgList = dbUtil.queryExec(sql, 3, con);
//        for(int i=0,size=orgList.size();i<size;i++){
//            Object[] temp = orgList.get(i);
//            resMaxAlarmLevelMap.put(temp[0], temp[1]);
//            resAlarmNumMap.put(temp[0], temp[2]);
//        }
//    }
//    /**
//     * 查询电路端口信息
//     * @deprecated after 1.2
//     * @param circuitADeviceNameMap
//     * @param circuitAPortNameMap
//     * @param circuitBDeviceNameMap
//     * @param circuitBPortNameMap
//     * @param circuitSqlCon
//     * @param con
//     */
//    public void queryCircuitDevicePortMap(HashMap<Object,Object> circuitADeviceNameMap,HashMap<Object,Object> circuitAPortNameMap
//            ,HashMap<Object,Object> circuitBDeviceNameMap,HashMap<Object,Object> circuitBPortNameMap,String circuitSqlCon,Connection con){
//        ArrayList<Object[]> orgList = new ArrayList<Object[]>();
//        //数据操作公共类
//        ReportDBUtil dbUtil = new ReportDBUtil();
//        String sql = "select c.circuitid,ad.devicename,c.aintdescr,bd.devicename,c.bintdescr" +
//                    " from circuit c,device ad,device bd where c.circuitid in ("+circuitSqlCon+")" +
//                    " and c.adeviceid = ad.deviceid and c.bdeviceid = bd.deviceid";
//        orgList = dbUtil.queryExec(sql, 5, con);
//        for(int i=0,size=orgList.size();i<size;i++){
//            Object[] temp = orgList.get(i);
//            circuitADeviceNameMap.put(temp[0], temp[1]);
//            circuitAPortNameMap.put(temp[0], temp[2]);
//            circuitBDeviceNameMap.put(temp[0], temp[3]);
//            circuitBPortNameMap.put(temp[0], temp[4]);
//        }
//    }
//    /**
//     * 查询资源告警信息
//     * @param deviceId
//     * @param rows
//     * @param page
//     * @param sort
//     * @param order
//     * @param con
//     * @return
//     */
//    public String queryResAlarmListData(String resId, String rows, String page, String sort, String order, Connection con){
//        StringBuffer sb = new StringBuffer();
//        int start = Integer.parseInt(rows)*(Integer.parseInt(page)-1);
//        int end = Integer.parseInt(rows)*Integer.parseInt(page);
//        if(null==sort){
//            sort = "1";
//            order = "";
//        }
//        String sql = "select r.resname ||' '||decode(a.respara,'-1','',a.respara) alarmsource,a.starttime,al.alarmlevelname," +
//                        "t.alarmtypedesc,a.summary from res r,alarmsummary a,alarmlevel al,alarmtype t" +
//                        " where r.resid = '"+resId+"' and r.resid = a.resid and a.alarmlevel = al.alarmlevel" +
//                        " and a.alarmtypeid = t.alarmtypeid order by "+sort+" "+order;
//        ArrayList<Object[]> orgList = new ArrayList<Object[]>();
//        ReportDBUtil dbUtil = new ReportDBUtil();
//        orgList = dbUtil.queryExec(sql, 5, con);
//        int total = orgList.size();
//        if(0==total){
//            sb.append("{\"total\":0,\"rows\":[{\"alarmsource\":\"当前无告警！\"}]}");
//        }else{
//            sb.append("{\"total\":"+total+",\"rows\":[");
//            for(int i=start,size=total;i<size&&i<end;i++){
//                Object[] temp = orgList.get(i);
//                if(i!=start)
//                    sb.append(",");
//                sb.append("{\"alarmsource\":\""+temp[0]+"\",\"starttime\":\""+temp[1]+"\",\"alarmlevelname\":\""+temp[2]+"\"," +
//                        "\"alarmtypedesc\":\""+temp[3]+"\",\"summary\":\""+formatJSONStr(temp[4].toString())+"\"}");
//            }
//            sb.append("]}");
//        }
//        return sb.toString();
//    }
//    /**
//     * 查询电路流量
//     * @param circuitId
//     * @param timeType
//     * @param con
//     * @return
//     */
//    public String queryCircuitFluxData(String circuitId,String timeType,Connection con){
//        String result = "";
//        String sql = "";
//        if("D".equals(timeType)){
//            sql = "select f.fluxtime,f.inavgvec*8/1000,f.outavgvec*8/1000 from flux f where" +
//                    " f.circuitid = '"+circuitId+"' and f.fluxtime >= to_char(sysdate-1,'yyyymmddhh24mi') order by f.fluxtime";
//        }else if("M".equals(timeType)){
//            sql = "select f.fluxtime,f.inavgvec*8/1000,f.outavgvec*8/1000 from fluxh f where" +
//                    " f.circuitid = '"+circuitId+"' and f.fluxtime >= to_char(sysdate-31,'yyyymmddhh24') order by f.fluxtime";
//        }else if("Y".equals(timeType)){
//            sql = "select f.fluxtime,f.inavgvec*8/1000,f.outavgvec*8/1000 from fluxd f where" +
//                    " f.circuitid = '"+circuitId+"' and f.fluxtime >= to_char(sysdate-366,'yyyymmdd') order by f.fluxtime";
//        }
//        ArrayList<Object[]> orgList = new ArrayList<Object[]>();
//        ReportDBUtil dbUtil = new ReportDBUtil();
//        orgList = dbUtil.queryExec(sql, 3, con);
//        String time = "",inAvgVec = "",outAvgVec = "";
//        HashMap<String,String> inMap = new HashMap<String,String>();
//        HashMap<String,String> outMap = new HashMap<String,String>();
//        String pointStart = "";
//        for(int i=0,size=orgList.size();i<size;i++){
//            Object[] temp = orgList.get(i);
//            if(i==0)
//                pointStart = (String)temp[0];
//            inAvgVec = formatDouble(temp[1]==null?null:Double.parseDouble(temp[1].toString()));
//            outAvgVec = formatDouble(temp[2]==null?null:Double.parseDouble(temp[2].toString()));
//            inMap.put((String)temp[0], inAvgVec);
//            outMap.put((String)temp[0], outAvgVec);
//        }
//        Date date = new Date();
//        ArrayList<String> categoriesList = new ArrayList<String>();
//        if("D".equals(timeType)){
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm");
//            Calendar cal = Calendar.getInstance();
//            cal.set(Integer.parseInt(pointStart.substring(0,4)),Integer.parseInt(pointStart.substring(4,6))-1,
//                    Integer.parseInt(pointStart.substring(6,8)),Integer.parseInt(pointStart.substring(8,10))
//                    ,Integer.parseInt(pointStart.substring(10,12)));
//            date = cal.getTime();
//            for(int i=0;i<12*24;i++){
//                  categoriesList.add(sdf.format(date));
//                  date.setTime(date.getTime()+5*60*1000);
//            }
//        }else if("M".equals(timeType)){
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHH");
//            Calendar cal = Calendar.getInstance();
//            cal.set(Integer.parseInt(pointStart.substring(0,4)),Integer.parseInt(pointStart.substring(4,6))-1,
//                    Integer.parseInt(pointStart.substring(6,8)),Integer.parseInt(pointStart.substring(8,10)),0);
//            date = cal.getTime();
//            for(int i=0;i<31*24;i++){
//                categoriesList.add(sdf.format(date));
//                date.setTime(date.getTime()+60*60*1000);
//            }
//        }else if("Y".equals(timeType)){
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
//            Calendar cal = Calendar.getInstance();
//            cal.set(Integer.parseInt(pointStart.substring(0,4)),Integer.parseInt(pointStart.substring(4,6))-1,
//                    Integer.parseInt(pointStart.substring(6,8)));
//            date = cal.getTime();
//            for(int i=0;i<366;i++){
//                categoriesList.add(sdf.format(date));
//                date.setTime(date.getTime()+24*60*60*1000);
//            }
//        }
//        String categories = "[",inSeriesData = "[",outSeriesData = "[";
//        for(int i=0,size=categoriesList.size();i<size;i++){
//            if(i!=0){
//                categories += ",";
//                inSeriesData += ",";
//                outSeriesData += ",";   
//            }
//            time = categoriesList.get(i);
//            categories += "\""+time+"\"";
//            inSeriesData += inMap.get(time);
//            outSeriesData += outMap.get(time);
//        }
//        categories += "]";
//        inSeriesData += "]";
//        outSeriesData += "]";
//        result = "{\"categories\":"+categories+",\"pointStart\":\""+pointStart+"\",\"inSeriesData\":"+inSeriesData+",\"outSeriesData\":"+outSeriesData+"}";
//        return result;
//    }
//    /**
//     * 查询电路时延和丢包数据
//     * @param circuitId
//     * @param timeType
//     * @param con
//     * @return
//     */
//    public String queryCircuitDelayAndDropData(String circuitId,String timeType,Connection con){
//        String result = "";
//        String sql = "";
//        if("D".equals(timeType)){
//            
//        }else if("M".equals(timeType)){
//            sql = "select rd.hourid,rd.deriveditemcode,rd.value from resmonihourlyinfo rd" +
//                    " where rd.resid = '"+circuitId+"' and (rd.deriveditemcode = 'S_ICMPDelay_COM_AVG' or" +
//                    " rd.deriveditemcode = 'S_ICMPDiscardsR_COM_AVG') and rd.hourid >= to_char(sysdate-31,'yyyymmddhh24') order by rd.hourid";
//        }else if("Y".equals(timeType)){
//            
//        }
//        ArrayList<Object[]> orgList = new ArrayList<Object[]>();
//        ReportDBUtil dbUtil = new ReportDBUtil();
//        orgList = dbUtil.queryExec(sql, 3, con);
//        String time = "";
//        HashMap<String,Double> delayMap = new HashMap<String,Double>();
//        HashMap<String,Double> dropMap = new HashMap<String,Double>();
//        String pointStart = "", pointEnd = "";
//        for(int i=0,size=orgList.size();i<size;i++){
//            Object[] temp = orgList.get(i);
//            if(i==0)
//                pointStart = (String)temp[0];
//            pointEnd = (String)temp[0];
//            if("S_ICMPDelay_COM_AVG".equals(temp[1])){
//                delayMap.put((String)temp[0], temp[2]==null?null:Double.parseDouble(temp[2].toString()));
//            }else if("S_ICMPDiscardsR_COM_AVG".equals(temp[1])){
//                dropMap.put((String)temp[0], temp[2]==null?null:Double.parseDouble(temp[2].toString()));
//            }
//        }
//        Date date = new Date();
//        ArrayList<String> categoriesList = new ArrayList<String>();
//        if("M".equals(timeType)){
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHH");
//            Calendar cal = Calendar.getInstance();
//            if(!"".equals(pointEnd)){
//                cal.set(Integer.parseInt(pointEnd.substring(0,4)),Integer.parseInt(pointEnd.substring(4,6))-1,
//                    Integer.parseInt(pointEnd.substring(6,8)),Integer.parseInt(pointEnd.substring(8,10)),0);
//            }
//            date = cal.getTime();
//            date.setTime(date.getTime()-31*24*60*60*1000l);
//            pointStart = sdf.format(date);
//            for(int i=0;i<=31*24;i++){
//                categoriesList.add(sdf.format(date));
//                date.setTime(date.getTime()+60*60*1000);
//            }
//        }
//        String categories = "[",delaySeriesData = "[",dropSeriesData = "[";
//        for(int i=0,size=categoriesList.size();i<size;i++){
//            if(i!=0){
//                categories += ",";
//                delaySeriesData += ",";
//                dropSeriesData += ",";   
//            }
//            time = categoriesList.get(i);
//            categories += "\""+time+"\"";
//            delaySeriesData += formatDouble(delayMap.get(time));
//            dropSeriesData += formatDouble(dropMap.get(time));
//        }
//        categories += "]";
//        delaySeriesData += "]";
//        dropSeriesData += "]";
//        result = "{\"categories\":"+categories+",\"pointStart\":\""+pointStart+"\",\"delaySeriesData\":"+delaySeriesData+",\"dropSeriesData\":"+dropSeriesData+"}";
//        return result;
//    }
//    /**
//     * 格式化数字
//     * @param d
//     * @return
//     */
//    public String formatDouble(Double d){
//        String result = "";
//        DecimalFormat df=new DecimalFormat("0.00");
//        if(null==d){
//            result = "null";
//        }else{
//            result = df.format(d);  
//        }
//        return result;
//    }
//    /**
//     * 格式化json字符串
//     * @param str
//     * @return
//     */
//    public String formatJSONStr(String str){
//        return str.replaceAll(":", "：").replaceAll("\\s"," ");
//    }
//    //编译
//    public static void main(String[] args){
//        System.out.println("编译成功！");
//    }
//}
