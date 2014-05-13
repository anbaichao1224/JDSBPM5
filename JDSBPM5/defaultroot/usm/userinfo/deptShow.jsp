<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="ww"%>
<%
String path = request.getContextPath()+"/";
%>
<html>
<head>
<title>区长管理</title>
<style>
.STYLE1 {
	font-family: "宋体";
	font-size:20px;
	font-weight: bold;
	border-top-width: 0px;
	border-right-width: 0px;
	border-bottom-width: 0px;
	border-left-width: 0px;
	border-style: solid;
	border-color: #FFFFFF;
}
.td_style{
	font-size:12px;
	text-align: center;
	vertical-align: middle;
	border-top-width: 1px;
	border-right-width: 0px;
	border-bottom-width: 0px;
	border-left-width: 1px;
	border-style: solid;
	border-color: #000000;
}
.xs_left_top{
	font-size:12px;
	text-align: center;
	vertical-align: middle;
	border-top-width: 1px;
	border-right-width: 0px;
	border-bottom-width: 0px;
	border-left-width: 1px;
	border-style: solid;
	border-color: #000000;
}
.xs_left_top_right{
	font-size:12px;
	text-align: center;
	vertical-align: middle;
	border-top-width: 1px;
	border-right-width: 1px;
	border-bottom-width: 0px;
	border-left-width: 1px;
	border-style: solid;
	border-color: #000000;
}
.xs_left_top_bottom{
	font-size:12px;
	text-align: center;
	vertical-align: middle;
	border-top-width: 1px;
	border-right-width: 0px;
	border-bottom-width: 1px;
	border-left-width: 1px;
	border-style: solid;
	border-color: #000000;
}
.xs_all{
	font-size:12px;
	text-align: left;
	vertical-align: middle;
	border-width: 1px;
	border-style: solid;
	border-color: #000000;
}
.STYLE9 {font-size: 14; font-weight: bold; color: #FFFFFF; }
</style>
<link rel="stylesheet" type="text/css" href="../js/ext/resources/css/ext-all.css" />
 <script type="text/javascript" src="../js/ext/adapter/ext/ext-base.js"></script>
 <script type="text/javascript" src="../js/ext/ext-all.js"></script>
 <script language="javascript" src="../js/ext/locale/ext-lang-zh_CN.js"></script>	
<script type="text/javascript">
function ow(owurl){ 	 
	var win =new Ext.Window({
			layout:'fit',
			width:860,
			height:350,
			closeAction:'close',
			html:'<iframe src="<%=path %>'+owurl+'" width="840" height="345"></iframe>'
		});    	
   	win.show();
}
</script>
</head>
<body>
<input type="hidden" name="personId" id="personId" value="<ww:property value="personId"/>" />
<br>
<table width="500" align="center" border="1" cellpadding="0" cellspacing="0">
  <!--DWLayoutTable-->
  <tr>
    <td height="31" colspan="2" align="center" valign="middle" class="STYLE1" >办理单位查看</td>
  </tr>
  <tr><th width="350" background="/cwzx/cwzxclient/IMAGES/xin-tiao-5.jpg" class="td_style" height="30"><span class="STYLE9">办理单位</span></th>
	<th width="150" background="/cwzx/cwzxclient/IMAGES/xin-tiao-5.jpg" class="td_style" height="30"><span class="STYLE9">办件数量</span></th>
</tr>
<ww:iterator value="mOrg" status="rows">
<tr>
    <td width="350" height="20" align="left" valign="middle" class="xs_left_top">
	<ww:property value="oname"/>
	
	</td>
	<td width="150" align="center" valign="middle" class="xs_left_top_right">
	<ww:iterator value="stat" status="rows">
	<ww:if test="orgId == uuid">
	<a href="javascript:ow('<ww:property value="url"/>')">
	<ww:property value="num"/>
	</a>件	
	</ww:if>
	<ww:else>&nbsp;</ww:else>
	</ww:iterator>
	</td>
</tr>
</ww:iterator> 
  <tr>
    <td width="350" height="20" align="left" valign="middle" class="xs_left_top_bottom"><!--DWLayoutEmptyCell-->&nbsp;</td>
    <td width="150" valign="top" class="xs_all"><!--DWLayoutEmptyCell-->&nbsp;</td>
  </tr>
</table>
</body>
</html>