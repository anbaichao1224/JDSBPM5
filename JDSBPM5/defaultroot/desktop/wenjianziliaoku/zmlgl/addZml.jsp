<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="ww"%>
<%
	String path = request.getContextPath();

	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";	
	
	String catalogUuid = (String)request.getAttribute("catalogUuid");
%>
<html>
	<head>
		<base href="<%=basePath%>"></base>
		<script>var context='<%=path%>';</script>
		
		<style type="text/css">
		<!--
		body {font-family: "宋体", "Arial"; font-size: 9pt;background-color:#dfe8f6}
		td {font-family: "宋体", "Arial"; font-size: 9pt} 
		.check{color:#FF0000}
		-->
		<!--
		.STYLE1 {font-size: 24px}
		.STYLE2 {font-size: 18px}
		-->
		.tableClass{
		 border-collapse: collapse; 
		} 
		.tdClass{
		    border:1px solid #000000;
		}
		
		</style>
		
		<meta http-equiv="pragma" content="no-cache" />
		<meta http-equiv="cache-control" content="no-cache" />

		<script type="text/javascript" src="<%=path%>js/extAll.js"></script>
		<script type="text/javascript" src="<%=path%>/js/ext/TabCloseMenu.js"></script>
		<script type="text/javascript" src="/desktop/wenjianziliaoku/mlgl/notice.js" defer="defer"></script>
		<script type="text/javascript"
			src="<%=path%>desktop/js/CreateGrid.js"></script>
		<script src="/js/JDS.js" type="text/javascript"></script>
		<script type="text/javascript">
			
		</script>
	</head>
<body>
	<form id="addZml" name="addZml" action="wjzlCatalogAction_zhiXingAddZml.action?catalogUuid=<%=catalogUuid%>" method="post">
	<table  width="490" height="365" border="1"  align="center" cellpadding="0" cellspacing="0">
	   	<tr>
	      <td height="64" colspan="2"><div align="center" class="STYLE1" >文 件 资 料 登 记</div></td>
      	</tr>
	    <tr>
	      <td width="106" height="66" align="center"><span class="STYLE2">目录名称 </span></td>
	      <td width="378" height="66" colspan="-2"><span class="STYLE2">
          <input name="dao.catalogName" type="text" id="catalogName" size="36"  />
          </span></td>
      </tr>
       <tr>
	      <td width="106" height="66" align="center"><span class="STYLE2">排序序号 </span></td>
	      <td width="378" height="66" colspan="-2"><span class="STYLE2">
          <input name="dao.catalogOrder" type="text" id="catalogOrder" onblur="this.a();" onkeyup="(this.a=function(){this.value=this.value.replace(/[^0-9-]+/,'');}).call(this)" size="36"  />
          </span></td>
      </tr>
	      <tr>
	      <td align="center" height="177"><span class="STYLE2">目录描述</span></td>
	      <td width="378" height="177" colspan="-2" align="left" valign="middle"><span class="STYLE2">
	        <textarea id="dao.catalogDesc" name="dao.catalogDesc" cols="40" rows="8"></textarea>
          </span></td>
	      </tr>
      </table>
</form>
	</body>
</html>