<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="ww"%>
<%@ page
	import="java.util.Map,com.opensymphony.xwork2.util.OgnlValueStack,com.opensymphony.xwork2.ActionContext,net.itjds.fdt.define.designer.metadata.bean.DocumentBean"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<html>
  <head>
	<style type="text/css">
		<!--
		.STYLE3 {font-size: 36px}
		.STYLE4 {font-size: 20px}
		-->
		.tableClass{
		 border-collapse: collapse; 
		} 
		.tdClass{
		    border:1px solid #000000;
		}	
	</style>
	<script type="text/javascript">var context='/';</script>
	<script type="text/javascript" src="/js/extAll.js"></script>
	<script type="text/javascript">	
		Ext.onReady(function(){
			var sbdate = new Ext.form.DateField({
				applyTo:'sbsj',
				fieldLabel:'日期',
				emptyText:'',
				format:'Y-m-d'	
			});
			       
		}); 		
	</script>
  </head>
  
  <body>
	<form action="tjbbAdd2.action" id="my_form" method="post">
		<div align="center" class="STYLE3">
			行政服务窗口办件咨询上报
		</div>
		<p></p>
		<table align="center" width="950" class="tableClass">
			<tr>
				<td width="200" height="50" class="tdClass">
					<div align="center">
						<span class="STYLE4">统计单位</span>
					</div>
				</td>
				<td width="750" align="center" class="tdClass">
					<input name="tjdw" id="tjdw" type="text" readonly value='<ww:property value="$currPerson.name"/>'></input>
				</td>
			</tr>
			<tr>
				<td width="200" height="50" class="tdClass">
					<div align="center">
						<span class="STYLE4">咨询单位</span>
					</div>
				</td>
				<td width="750" align="center" class="tdClass">
					<input name="sbdw" id="sbdw" type="text" ></input>
				</td>
			</tr>
			<tr>
				<td width="200" height="50" class="tdClass">
					<div align="center">
						<span class="STYLE4">咨询事项</span>
					</div>
				</td>
				<td width="750" align="center" class="tdClass">
					<ww:select name="zxsx" 
          				list="types"
          				listKey="code"
          				listValue="name"
      				></ww:select>			
				</td>
			</tr>
			<tr>
				<td width="200" height="50" class="tdClass">
					<div align="center">
						<span class="STYLE4">咨询件数</span>
					</div>
				</td>
				<td width="750" align="center" class="tdClass">
					<input name="zxjs" id="zxjs" type="text"></input>
				</td>
			</tr>
			<tr>
				<td width="200" height="50" class="tdClass">
					<div align="center">
						<span class="STYLE4">上报时间</span>
					</div>
				</td>
				<td width="750" align="center" class="tdClass">
					<input name="sbsj" id="sbsj" type="text"></input>
				</td>
			</tr>
			<tr>
				<td width="200"></td>
				<td align="center"><input type="submit" value="上报"></input></td>
			</tr>
		</table>
	</form>
	<br>
	<br>
	<br>
  </body>
</html>
