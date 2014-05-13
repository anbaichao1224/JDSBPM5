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
    
    <title>My JSP 'zihaocreate.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<SCRIPT LANGUAGE="JavaScript" type="text/javascript">
	var context='<%=contextpath%>';
	</script>
		<script type="text/javascript" src="<%=path%>js/extAll.js"></script>
		<script type="text/javascript"
			src="<%=contextpath%>desktop/js/CreateGrid.js"></script>
		<script src="/js/JDS.js" type="text/javascript"></script>
<script type="text/javascript">
	Ext.onReady(function(){
  		document.getElementById("wenzhong").value = '<ww:property value="zihao.wenzhongid"/>';
  		document.getElementById("zihao").value='<ww:property value="zihao.zihao"/>';
      });
     var maxzihao = '<ww:property value="zihao.zihao"/>';
     var uuid = '<ww:property value="zihao.uuid"/>';
     var wenzhong = "";
     var actid = '<ww:property value="zihao.actid"/>';
     function selectwenzhong(){
     var select = document.getElementById("wenzhong");
     var index = select.selectedIndex;
     //var text = select.options[index].text;
     var value = select.options[index].value;
     
     var selecty = document.getElementById("year");
     var index1 = selecty.selectedIndex;
     var valuey = selecty.options[index1].value;
     
    
	Ext.Ajax.request({
		url: "getzihaobywenzhong.do",
		params: {wenzhong:value,
		         uuid:uuid,
		         actid:actid,
		         year:valuey},
		method: "post",
		success: function(resp,opts){
			
			var respText = Ext.util.JSON.decode(resp.responseText);
			maxzihao = respText.data.zihao;
			document.getElementById("zihao").value=respText.data.zihao;
			
		}
	});
}
function zihaosave(){
	 var select = document.getElementById("wenzhong");
     var index = select.selectedIndex;
     var text = select.options[index].text;
     var value = select.options[index].value;
     var selecty = document.getElementById("year");
     var index1 = selecty.selectedIndex;
     var valuey = selecty.options[index1].value;
     var zi= document.getElementById("zihao").value;
	Ext.Ajax.request({
		url: "savezihao.do",
		params: {wenzhong:value,
		         uuid:uuid,
		         actid:actid,
		         maxzihao:maxzihao,
		         zi:zi,
		         year:valuey
		         },
		method: "post",
		success: function(resp,opts){
			
			var respText = Ext.util.JSON.decode(resp.responseText);
			if(respText.success == true){
				maxzihao = zi;
			}
			alert(respText.data.message);
		}
	});
	
}
</script>
  </head>
  
  <body>
    <select name="wenzhong" id="wenzhong" onchange="selectwenzhong()">
				        <!--<option value="wu">无</option>
				     	<option value="neiwangzi">内网字</option>
				     	<option value="neiguomijufamidian">内国密局发密电</option>
				     	<option value="neiwangbanfa">内网办法</option>
				     	<option value="neijizi">内机字</option>
				     	<option value="neijifa">内机发</option>
				     	<option value="neimibanfa">内密办法</option>
				     	<option value="neimifa">内密发</option>
				     	<option value="neidangbanxinxibanfa">内党办信息办发</option>
				     	<option value="neiguomijufa">内国密局发</option>
				     	<option value="neiguomifa">内国密发</option>
				     	<option value="neiwangfa">内网发</option>  -->
				     	<ww:iterator value="wzlist" status="rowstatus">
				     		<option value='<ww:property value="uuid"/>'><ww:property value="wzname"/></option>
				     	</ww:iterator>
				     </select>
	<select name="year" id="year" onchange="selectwenzhong()">
		<option value='<ww:property value="year"/>'><ww:property value="year"/></option>
		<option value='<ww:property value="lastyear"/>'><ww:property value="lastyear"/></option>		     	
	</select>			     
				     
				     <input id="zihao" type="text" value="<ww:property value="zihao.zihao"/>"/>
    <input value="保存字号" type="button" onclick="zihaosave()"/>
  </body>
</html>
