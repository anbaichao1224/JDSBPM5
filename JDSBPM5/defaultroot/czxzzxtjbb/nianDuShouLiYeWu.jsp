<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="ww"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<html>
  <head>
    
    <title>窗口业务各年度受理情况</title>
	<style type="text/css">
		<!--
		.STYLE1 {
			font-size: 18px;
			margin-left: 70px;
		}
		.STYLE2 {
			font-size: 18px;
			margin-left: 110px;
		}
		.STYLE3 {font-size: 28px}
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
		function search(){
        	var frmObj=document.forms[0];
        	frmObj.submit();     
        };
	
		Ext.onReady(function(){       
			var sdate = new Ext.form.DateField({
				applyTo:'sdate',
				fieldLabel:'日期',
				emptyText:'',
				format:'Y-m-d'	
			});
			var edate = new Ext.form.DateField({
				applyTo:'edate',
				fieldLabel:'日期',
				emptyText:'',
				format:'Y-m-d'	
			});
			       
		});
							   
		function AllAreaWord(){ 
			var oWD = new ActiveXObject("Word.Application"); 
			var oDC = oWD.Documents.Add("",0,1); 
			var oRange =oDC.Range(0,1); 
			var sel = document.body.createTextRange(); 
			sel.moveToElementText(PrintA); 
			sel.select(); 
			sel.execCommand("Copy"); 
			oRange.Paste(); 
			oWD.Application.Visible = true; 
		}
	</script>
  </head>
  
  <body>
  		<ww:form action="nianDuShouLiYeWu" theme="simple">
  		<div>
  			<table>
  				<tr>
  					<td align="right" width="100">时间:</td>
  					<td>
					<input name="sdate" id="sdate" type="text"></input>
           			</td>
           			<td>至:</td>
  					<td>
					<input name="edate" id="edate" type="text"></input>
           			</td>
           			<td>
           				<button onclick="search();">查询</button>
           			</td>
  				</tr>
  			</table>
  		</div>
  		</ww:form>
  		<div id="PrintA">
    	<div align="center" class="STYLE3">
			潮&nbsp;州&nbsp;市&nbsp;行&nbsp;政&nbsp;服&nbsp;务&nbsp;中&nbsp;心&nbsp;受&nbsp;理&nbsp;各&nbsp;年&nbsp;度&nbsp;业&nbsp;务&nbsp;情&nbsp;况
		</div>
		
  		<div class="STYLE1">
  		自<ww:property value="sdate"/>至<ww:property value="edate"/><br>
  		受理件：<ww:property value="niandushouliyewuBean.sjs"/>件&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;其中：年审<ww:property value="niandushouliyewuBean.nss"/>件<br>
  		办结数：<ww:property value="niandushouliyewuBean.bjs"/>件&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;办结率：<ww:property value="niandushouliyewuBean.bjl"/>%<br>
  		咨询件：<ww:property value="niandushouliyewuBean.zxj"/>件</div><br>
  		<div class="STYLE1">其中：</div>
  		<ww:iterator value="niandushouliyewuList" id="niandushouliyewu" status="st">
  			<div class="STYLE2"><ww:property value="nian"/>年</div><br>
  			<div class="STYLE2">
  			受理件：<ww:property value="sjs"/>件&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;其中：年审<ww:property value="nss"/>件<br>
  			办结数：<ww:property value="bjs"/>件&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;办结率：<ww:property value="bjl"/>%<br>
  			咨询件：<ww:property value="zxj"/>件</div><br>
    	</ww:iterator>
    </div>
    <div align="center"><input type="button" onclick="javascript:AllAreaWord();" value="导出Word"> </div>
  </body>
</html>
