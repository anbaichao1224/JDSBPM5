<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="ww"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<html>
  <head>
    
    <title>窗口业务受理明细表</title>
	<style type="text/css">
		<!--
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
		
					       
		function saveCode(obj) {  
  			var winname = window.open('', '_blank', 'top=10000');  
  			var strHTML = document.all.tableExcel.innerHTML;  
  			winname.document.open('text/html', 'replace');  
  			winname.document.writeln(strHTML);  
  			winname.document.execCommand('saveas','','excel.xls');  
  			winname.close();  
		}
	</script>
  </head>
  
  <body>
  		<ww:form action="chuangKouMingXiTJ" theme="simple">
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
  	<div id="tableExcel">
    	<div align="center" style="font-size:28">
			“窗&nbsp;口”&nbsp;业&nbsp;务&nbsp;受&nbsp;理&nbsp;明&nbsp;细&nbsp;表
		</div>		
  		<table width="100%" align="center" border="1" bordercolor="#000000" cellpadding="0" cellspacing="0">
		    <tr>
		      <td width="160" class="tdClass" align="center">单位</td>
		      <td width="80" class="tdClass" align="center">接单数</td>
		      <td width="300" class="tdClass" align="center">接单内容</td>
		      <td width="80" class="tdClass" align="center">办结数</td>
		      <td width="300" class="tdClass" align="center">办结内容</td>
		      <td width="80" class="tdClass" align="center">咨询数</td>
		      <td width="150" class="tdClass" align="center">咨询内容</td>
		    </tr>
		    <ww:iterator value="mingxiList" id="mingxi" status="st">
		    <tr>
		      <td class="tdClass" align="center"><ww:property value="dw"/></td>
		      <td class="tdClass" align="center"><ww:property value="jds"/></td>
		      <td class="tdClass">&nbsp;
		      	<ww:iterator value="%{#mingxi.jdnr}" id="jdnr" status="st">
		      		<ww:property value="%{jdnr[#st.index]}"/><br>
		      	</ww:iterator>
			  </td>
		      <td class="tdClass" align="center"><ww:property value="bjs"/></td>
		      <td class="tdClass">&nbsp;
		      	<ww:iterator value="%{#mingxi.bjnr}" id="bjnr" status="st">
		      		<ww:property value="%{bjnr[#st.index]}"/><br>
		      	</ww:iterator>
			  </td>
		      <td class="tdClass" align="center"><ww:property value="zxs"/></td>
		      <td class="tdClass" align="center">&nbsp;</td>
		    </tr>
		    </ww:iterator>
		    <tr>
		      <td class="tdClass" align="center">总计</td>
		      <td class="tdClass" align="center">&nbsp;<ww:if test="mingxiBean.jds==null">0</ww:if><ww:else><ww:property value="mingxiBean.jds"/></ww:else></td>
		      <td class="tdClass" align="center">&nbsp;</td>
		      <td class="tdClass" align="center">&nbsp;<ww:if test="mingxiBean.bjs==null">0</ww:if><ww:else><ww:property value="mingxiBean.bjs"/></ww:else></td>
		      <td class="tdClass" align="center">&nbsp;</td>
		      <td class="tdClass" align="center">&nbsp;<ww:if test="mingxiBean.zxs==null">0</ww:if><ww:else><ww:property value="mingxiBean.zxs"/></ww:else></td>
		      <td class="tdClass" align="center">&nbsp;</td>
		    </tr>
		  </table>
     </div>  
	 <div align="center"><input type="button" value="导出Excel" onclick="saveCode(tableExcel)">  </div>
  </body>
</html>
