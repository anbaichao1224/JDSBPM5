<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="ww"%>
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
			var ksrq = new Ext.form.DateField({       
				applyTo : 'ksrq',       
				fieldLabel:'日期',       
				format: 'Y-m-d'        
			});
			var jsrq = new Ext.form.DateField({
				applyTo:'jsrq',
				fieldLabel:'日期',       
				format: 'Y-m-d' 	
			});
			var swqdfield = new Ext.form.TimeField({
				applyTo:'swqdsj',
				fieldLabel:'时间',
				minValue:'07:30',
				maxValue:'09:30',
				increment:5,
				format:'H:i:s'	
			});
			var swqtfield = new Ext.form.TimeField({
				applyTo:'swqtsj',
				fieldLabel:'时间',
				minValue:'10:30',
				maxValue:'12:30',
				increment:5,
				format:'H:i:s'	
			});
			var xwqdfield = new Ext.form.TimeField({
				applyTo:'xwqdsj',
				fieldLabel:'时间',
				minValue:'13:30',
				maxValue:'15:30',
				increment:5,
				format:'H:i:s'	
			});
			var xwqtfield = new Ext.form.TimeField({
				applyTo:'xwqtsj',
				fieldLabel:'时间',
				minValue:'16:30',
				maxValue:'18:30',
				increment:5,
				format:'H:i:s'	
			});
			       
		});
	</script>
    <title>考勤时间设定添加页面</title>

  </head>
  
  <body>
<form action="kaoQinTimeSheDingAdd.action" id="my_form" method="post">
		<div align="center" class="STYLE3">
			考勤时间设定添加
		</div>
		<p></p>
		<table align="center" width="950" class="tableClass">
			<tr>
				<td width="200" height="50" class="tdClass">
					<div align="center">
						<span class="STYLE4">开始日期</span>
					</div>
				</td>
				<td width="750" align="center" class="tdClass">
					<input name="ksrq" id="ksrq" type="text"></input>
				</td>
			</tr>
			<tr>
				<td width="200" height="50" class="tdClass">
					<div align="center">
						<span class="STYLE4">结束日期</span>
					</div>
				</td>
				<td width="750" align="center" class="tdClass">
					<input name="jsrq" id="jsrq" type="text" ></input>
				</td>
			</tr>
			<tr>
				<td width="200" height="50" class="tdClass">
					<div align="center">
						<span class="STYLE4">上午签到时间</span>
					</div>
				</td>
				<td width="750" align="center" class="tdClass">
					<input name="swqdsj" id="swqdsj" type="text" ></input>			
				</td>
			</tr>
			<tr>
				<td width="200" height="50" class="tdClass">
					<div align="center">
						<span class="STYLE4">上午签退时间</span>
					</div>
				</td>
				<td width="750" align="center" class="tdClass">
					<input name="swqtsj" id="swqtsj" type="text"></input>
				</td>
			</tr>
			<tr>
				<td width="200" height="50" class="tdClass">
					<div align="center">
						<span class="STYLE4">下午签到时间</span>
					</div>
				</td>
				<td width="750" align="center" class="tdClass">
					<input name="xwqdsj" id="xwqdsj" type="text"></input>
				</td>
			</tr>
			<tr>
				<td width="200" height="50" class="tdClass">
					<div align="center">
						<span class="STYLE4">下午签退时间</span>
					</div>
				</td>
				<td width="750" align="center" class="tdClass">
					<input name="xwqtsj" id="xwqtsj" type="text"></input>
				</td>
			</tr>
			<tr>
				<td width="200"></td>
				<td align="center"><input type="submit" value="设定"></input></td>
			</tr>
		</table>
	</form> 
  </body>
</html>
