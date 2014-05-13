<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="ww"%>
<%
String path = request.getContextPath();
String contextpath = request.getContextPath() + "/";
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <SCRIPT LANGUAGE="JavaScript" type="text/javascript">
	var context='<%=contextpath%>';
	</script>
    <title>My JSP 'xxkgzInfo.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="<%=path%>js/extAll.js"></script>
  </head>
  <script type="text/javascript">
  Ext.onReady(function(){
  var kssj = new Ext.form.DateField({
		applyTo:'kaishishijian',
		fieldLabel:'日期',
		emptyText:'请选择',
		format:'Y-m-d',
		value:'<ww:property value="kaishishijian"/>'	
	});

  var jssj = new Ext.form.DateField({
		applyTo:'jieshushijian',
		fieldLabel:'日期',
		emptyText:'请选择',
		format:'Y-m-d',
		value:'<ww:property value="jieshushijian"/>'
	});

  });
  Ext.QuickTips.init();
  </script>
  <body><form action="xxkgzSaveAndUpdateAction.do" method="post">
    <table width="923" height="104" border="1" cellpadding="0" cellspacing="0">
      <tr>
        <td>规则名称</td>
        <td><input type="text" name="name" value="<ww:property value='xxgzInfo.name'/>"/></td>
        <td>分数</td>
        <td><input type="text" name="fenshu" value="<ww:property value='xxgzInfo.fenshu'/>"/></td>
      </tr>
      <tr>
        <td>应用开始时间</td>
        <td><input type="text" id="kaishishijian" name="kaishishijian"  value="<ww:property value='kaishishijian'/>"/></td>
        <td>应用结束时间</td>
        <td><input type="text" id="jieshushijian" name="jieshushijian"  value="<ww:property value='jieshushijian'/>"/></td>
      </tr>
      <tr>
      	 <td colspan="4"> 
      	 <input type="hidden" name="banbenhao" value="<ww:property value='xxgzInfo.banbenhao'/>"/>
      	 <input type="hidden" name="biaohao" value="<ww:property value='xxgzInfo.biaohao'/>"/>
      	 <input type="hidden" name="uuid" value="<ww:property value='xxgzInfo.uuid'/>"/>
      	 <input type="submit" value="保存"/>
      	 
      	 </td>
      </tr>
    </table>
    </form>
    <br/>
    <ww:iterator value="gzListBean" status="rowstatus">
     <table width="923" height="104" border="1" cellpadding="0" cellspacing="0">
      <tr>
        <td>规则名称</td>
        <td><ww:property value="name"/></td>
        <td>分数</td>
        <td><ww:property value="fenshu"/></td>
      </tr>
      <tr>
        <td>应用开始时间</td>
        <td><ww:property value="kaishishijian"/></td>
        <td>应用结束时间</td>
        <td><ww:property value="jieshushijian"/>
     
      	 <input type="hidden" name="banbenhao" value="<ww:property value='banbenhao'/>"/>
      	 <input type="hidden" name="biaohao" value="<ww:property value='biaohao'/>"/>
      	 <input type="hidden" name="uuid" value="<ww:property value='uuid'/>"/>
      </td>
      </tr>
    </table><br/>
   </ww:iterator> 
    
  </body>
</html>
