<%@ page language="java" contentType="text/html; charset=GB2312" pageEncoding="GB2312" %>
<%@ page import="java.util.*,java.lang.reflect.*" %>

<%@ page import="com.zhongying.huanan.product.sdn.busi.TopoSDNBean" %>
<%
  String methodName = request.getParameter("methodName");
  try{
      Class[] argsClass = new Class[2]; 
      argsClass[0] = HttpServletRequest.class;
      argsClass[1] = HttpServletResponse.class;
      Class cls = this.getClass();   
      Method method = cls.getMethod(methodName, argsClass);   
      Object[] args = new Object[2];
      args[0] = request;
      args[1] = response;   
      BeforeInvoke(methodName);
      method.invoke(this, args);
      AfterInvoke(methodName);
  } catch(Exception e){
      response.reset();
      response.getWriter().write(e.getMessage());
  } finally{
      AfterInvoke(methodName);
  }
%>

<%!
//ptaccessview begin
public void queryPTCityAccessInfoData(HttpServletRequest request,HttpServletResponse response) throws Exception{
    TopoSDNBean bean = new TopoSDNBean();
    String ptCode = request.getParameter("ptCode");
    String cityName = request.getParameter("cityName");
    String result = bean.queryPTCityAccessInfoData(ptCode, cityName, null);
    response.getWriter().write(result);
}
public void queryPTCityAccessTopoInfoData(HttpServletRequest request,HttpServletResponse response) throws Exception{
    TopoSDNBean bean = new TopoSDNBean();
    String ptCode = request.getParameter("ptCode");
    String cityName = request.getParameter("cityName");
    String result = bean.queryPTCityAccessTopoInfoData(ptCode, cityName, null);
    response.getWriter().write(result);
}
public void queryAccessDeviceAlarmListData(HttpServletRequest request,HttpServletResponse response) throws Exception{
    TopoSDNBean bean = new TopoSDNBean();
    String ptCode = request.getParameter("ptCode");
    String cityName = request.getParameter("cityName");
    String deviceId = request.getParameter("deviceId");
    String rows = request.getParameter("rows");
    String page = request.getParameter("page");
    String sort = request.getParameter("sort");
    String order = request.getParameter("order");
    String result = bean.queryAccessDeviceAlarmListData(ptCode, cityName, deviceId, rows, page, sort, order,null);
    System.out.println(result);
    response.getWriter().write(result);
}
//ptaccessview end

protected void BeforeInvoke(String methodName){
  System.out.println(methodName+" is called! ");
  System.out.println("前置任务：权限管理什么的。。。。");
}

protected void AfterInvoke(String methodName){
  System.out.println(methodName+" is called! ");
  System.out.println("后置任务：日志记账什么的。。。。");
}


%>