<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.Map,com.opensymphony.xwork2.util.OgnlValueStack"%>
<%@ taglib uri="/struts-tags" prefix="ww"%>
<%   String contextpath = request.getContextPath()+ "/" ; %>
<html>
<head>
	<title></title>
	<script type="text/javascript">
		function ceshi(){
			alert("ceshi");
			
			//demodisplay.action?ActivityInstId=3E759180-D9E7-11E0-9180-B525066AF9A7\', \'3E759180-D9E7-11E0-9180-B525066AF9A7\')'
			window.top.openWin('demodisplay.action?ActivityInstId=3E759180-D9E7-11E0-9180-B525066AF9A7','3E759180-D9E7-11E0-9180-B525066AF9A7');
		}
	</script>
</head>
<body>

 <table>
 	<tr>
 		<td width="10%">序号</td>
 		<td width="20%">办理人</td>
 		<td width="10%">办理步骤</td>
 		<td width="15%">开始时间</td>
 		<td width="15%">结束时间</td>
 		<td>当前状态</td>
 	</tr>
   <ww:iterator value="historyWrpList" status="historystatus">
      <tr><td><ww:property value="#historystatus.index"/></td>
      
      <td> <ww:iterator value="performers" status="performersstatus"><ww:property value="name"/> / <ww:property value="orgs[0].name"/></ww:iterator></td>
      <td><ww:property value="activityDef.name"/></td>
      <td><ww:property value="arrivedTime"/></td>
      <td><ww:property value="endTime"/></td>
      <td>已办理</td>
      <td><input type="button" style='border:1 ;height:15' name='action' value="查看"  onclick='ceshi()'/></td>
      </tr>
      </ww:iterator>   
     
      <ww:iterator value="currentWrpList" status="rowstatus"> 
      <tr><td><ww:property value="#rowstatus.index"/></td>
      
      <td> <ww:iterator value="performers" status="performersstatus"><ww:property value="name"/> / <ww:property value="orgs[0].name"/></ww:iterator></td>
      <td><ww:property value="activityDef.name"/></td>
      <td><ww:property value="arrivedTime"/></td>
      <td><ww:property value="arrivedTime"/></td>
      <td><ww:property value="'正在办理'"/></td>
      <td><input type='button' style='border:1 ;height:15' name='action' value='查 看 '  onClick='openWin('<%=contextpath %>demodisplay.action?ActivityInstId=<ww:property value="activityInstId"/>', '<ww:property value="activityInstId"/>')'/></td>          
   		</tr>
        
       </ww:iterator>
</table>
</body>
</html>



 
