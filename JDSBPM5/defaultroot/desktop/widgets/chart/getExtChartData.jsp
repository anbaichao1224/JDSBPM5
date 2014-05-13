<%@ page import="net.itjds.chart.ExtChart" %>
<%@ page import="net.itjds.chart.Chart" %>
<%@ page import="net.itjds.chart.FusionGaugeChart" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2010-2-24
  Time: 11:17:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
//  id:cid,
//      chartType:cType,
//      title:title,
//      exp:exp
  String chartId=request.getParameter("id");
  String chartType=request.getParameter("chartType");
  String title=request.getParameter("title");
  String exp=request.getParameter("exp");
  
  String test=request.getParameter("test");

  Chart c = null;
  if (test != null) {
    //chartType="f_gaugechart";
    c = Chart.getTestChartObj(chartType);
  } else {
    if (chartId != null) {
      c = Chart.getChartByParam(chartId, chartType, title, exp);
    }else{
      //todo 没id,暂时不处理
    }
  }
  out.println(c.toJsonStr());
%>