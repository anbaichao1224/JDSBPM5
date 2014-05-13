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
	
		function fun_1(){   
			document.getElementById("my_form").action="tjbbSave.action";   
			document.getElementById("my_form").submit();   
		}
	
		Ext.onReady(function(){       
			var bjdate = new Ext.form.DateField({
				applyTo:'bjsj',
				fieldLabel:'日期',
				emptyText:'',
				format:'Y-m-d'	
			});
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
	<form action="tjbbAdd.action" id="my_form" method="post">
		<div align="center" class="STYLE3">
			行政服务窗口办件日报表
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
						<span class="STYLE4">申报单位</span>
					</div>
				</td>
				<td width="750" align="center" class="tdClass">
					<input name="sbdw" id="sbdw" type="text" ></input>
				</td>
			</tr>
			<tr>
				<td width="200" height="50" class="tdClass">
					<div align="center">
						<span class="STYLE4">申报事项</span>
					</div>
				</td>
				<td width="750" align="center" class="tdClass">
					<ww:select name="sbsx" 
          				list="types"
          				listKey="code"
          				listValue="name"
      				></ww:select>			
				</td>
			</tr>
			<tr>
				<td width="200" height="50" class="tdClass">
					<div align="center">
						<span class="STYLE4">受理件数</span>
					</div>
				</td>
				<td width="750" align="center" class="tdClass">
					<input name="sljs" id="sljs" type="text"></input>
				</td>
			</tr>
			<tr>
				<td width="200" height="50" class="tdClass">
					<div align="center">
						<span class="STYLE4">项目办理列级</span>
					</div>
				</td>
				<td width="750" align="center" class="tdClass">
					<select id="xmbllj" name="xmbllj">
							<option value="即办件">即办件</option>
							<option value="退办件">退办件</option>
							<option value="补办件">补办件</option>
							<option value="承诺件">承诺件</option>
							<option value="联办件">联办件</option>
							<option value="同审件">同审件</option>
							<option value="快办件">快办件</option>
							<option value="上报件">上报件</option>
					</select>
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
				<td width="200" height="50" class="tdClass">
					<div align="center">
						<span class="STYLE4">承诺时间</span>
					</div>
				</td>
				<td width="750" align="center" class="tdClass">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input name="cnsj" id="cnsj" type="text"></input>天
				</td>
			</tr>
			<tr>
				<td width="200" height="50" class="tdClass">
					<div align="center">
						<span class="STYLE4">办结时间</span>
					</div>
				</td>
				<td width="750" align="center" class="tdClass">
					<input name="bjsj" id="bjsj" type="text"></input>
				</td>
			</tr>
			<tr>
				<td width="200" align="center"><input type="button" value="保存" onclick="fun_1()" /></td>
				<td align="center"><input type="submit" value="上报"></input></td>
			</tr>
		</table>
	</form>
	<br>
	<br>
	<br>
  </body>
</html>
