<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="ww"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<html>
  <head>
    <title>汇总统计按年，季度，月</title>
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
  	<ww:form action="findAllByYue" theme="simple">
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
           				<button onClick="search();">查询</button>
           			</td>
  				</tr>
  			</table>
  		</div>
  	</ww:form>
  	<div id="tableExcel">
  		<div align="center" style="font-size:28">
			潮州市行政服务中心办事服务窗口办件统计表
		</div>
  		
        <table width="1200" align="center" border="1" bordercolor="#000000" cellpadding="0" cellspacing="0">
          <tr>
            <td width="150" rowspan="3" class="tdClass" align="center">
            	<p>窗口</p>
            	<p>部门</p>
            </td>
            <td width="50" rowspan="3" class="tdClass" align="center">
            	<p>咨询</p>
            	<p>件数</p>
            </td>
            <td colspan="16" class="tdClass" align="center">
              	受&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
              	理&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
              	件&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;数
            </td>
            <td width="150" colspan="3" rowspan="2" class="tdClass" align="center">
            	<p>本年度累计总数</p>
            	<p>（截至本月）</p>
            </td>
          </tr>
          <tr>
            <td class="tdClass" align="center">即办件</td>
            <td class="tdClass" align="center">退回件</td>
            <td class="tdClass" align="center" colspan="2">补办件</td>
            <td class="tdClass" align="center" colspan="2">承诺件</td>
            <td class="tdClass" align="center" colspan="2">联办件</td>
            <td class="tdClass" align="center" colspan="2">同步审批件</td>
            <td class="tdClass" align="center" colspan="2">快速审批件</td>
            <td class="tdClass" align="center" colspan="2">上报件</td>
            <td class="tdClass" align="center" colspan="2">合计</td>
          </tr>
          <tr>
            <td width="50" class="tdClass" align="center">收件数</td>
            <td width="50" class="tdClass" align="center">退件数</td>
            <td width="50" class="tdClass" align="center">收件数</td>
            <td width="50" class="tdClass" align="center">办结数</td>
            <td width="50" class="tdClass" align="center">收件数</td>
            <td width="50" class="tdClass" align="center">办结数</td>
            <td width="50" class="tdClass" align="center">收件数</td>
            <td width="50" class="tdClass" align="center">办结数</td>
            <td width="50" class="tdClass" align="center">收件数</td>
            <td width="50" class="tdClass" align="center">办结数</td>
            <td width="50" class="tdClass" align="center">收件数</td>
            <td width="50" class="tdClass" align="center">办结数</td>
            <td width="50" class="tdClass" align="center">收件数</td>
            <td width="50" class="tdClass" align="center">办结数</td>
            <td width="50" class="tdClass" align="center">收件数</td>
            <td width="50" class="tdClass" align="center">办结数</td>
            <td width="50" class="tdClass" align="center">咨询件</td>
            <td width="50" class="tdClass" align="center">收件数</td>
            <td width="50" class="tdClass" align="center">结办数</td>
          </tr>
          <ww:iterator value="yueduList" id="yuedu">
	          <tr>
	            <td class="tdClass" align="center"><ww:property value="tjdw"/> </td>
	            <td class="tdClass" align="center"><ww:if test="%{#yuedu.zxjs==0}">&nbsp;</ww:if><ww:else><ww:property value="zxjs"/></ww:else></td>
	            <td class="tdClass" align="center"><ww:if test="%{#yuedu.jbj==0}">&nbsp;</ww:if><ww:else><ww:property value="jbj"/></ww:else></td>
	            <td class="tdClass" align="center"><ww:if test="%{#yuedu.thj==0}">&nbsp;</ww:if><ww:else><ww:property value="thj"/></ww:else></td>
	            <td class="tdClass" align="center"><ww:if test="%{#yuedu.bbjsjs==0}">&nbsp;</ww:if><ww:else><ww:property value="bbjsjs"/></ww:else></td>
	            <td class="tdClass" align="center"><ww:if test="%{#yuedu.bbjbjs==0}">&nbsp;</ww:if><ww:else><ww:property value="bbjbjs"/></ww:else></td>
	            <td class="tdClass" align="center"><ww:if test="%{#yuedu.cnjsjs==0}">&nbsp;</ww:if><ww:else><ww:property value="cnjsjs"/></ww:else></td>
	            <td class="tdClass" align="center"><ww:if test="%{#yuedu.cnjbjs==0}">&nbsp;</ww:if><ww:else><ww:property value="cnjbjs"/></ww:else></td>
	            <td class="tdClass" align="center"><ww:if test="%{#yuedu.lbjsjs==0}">&nbsp;</ww:if><ww:else><ww:property value="lbjsjs"/></ww:else></td>
	            <td class="tdClass" align="center"><ww:if test="%{#yuedu.lbjbjs==0}">&nbsp;</ww:if><ww:else><ww:property value="lbjbjs"/></ww:else></td>
	            <td class="tdClass" align="center"><ww:if test="%{#yuedu.tbspjsjs==0}">&nbsp;</ww:if><ww:else><ww:property value="tbspjsjs"/></ww:else></td>
	            <td class="tdClass" align="center"><ww:if test="%{#yuedu.tbspjbjs==0}">&nbsp;</ww:if><ww:else><ww:property value="tbspjbjs"/></ww:else></td>
	            <td class="tdClass" align="center"><ww:if test="%{#yuedu.ksspjsjs==0}">&nbsp;</ww:if><ww:else><ww:property value="ksspjsjs"/></ww:else></td>
	            <td class="tdClass" align="center"><ww:if test="%{#yuedu.ksspjbjs==0}">&nbsp;</ww:if><ww:else><ww:property value="ksspjbjs"/></ww:else></td>
	            <td class="tdClass" align="center"><ww:if test="%{#yuedu.sbjsjs==0}">&nbsp;</ww:if><ww:else><ww:property value="sbjsjs"/></ww:else></td>
	            <td class="tdClass" align="center"><ww:if test="%{#yuedu.sbjbjs==0}">&nbsp;</ww:if><ww:else><ww:property value="sbjbjs"/></ww:else></td>
	            <td class="tdClass" align="center"><ww:property value="hjsjs"/></td>
	            <td class="tdClass" align="center"><ww:property value="hjbjs"/></td>
	            <td class="tdClass" align="center"><ww:property value="ndjzbyzxj"/></td>
	            <td class="tdClass" align="center"><ww:property value="ndjzbysjs"/></td>
	            <td class="tdClass" align="center"><ww:property value="ndjzbybjs"/></td>
	          </tr>
          </ww:iterator>
	          <tr>
	            <td class="tdClass" align="center">合计</td>
	            <td class="tdClass" align="center">&nbsp;<ww:property value="yueduhejiBean.zxjs"/></td>
	            <td class="tdClass" align="center">&nbsp;<ww:property value="yueduhejiBean.jbj"/></td>
	            <td class="tdClass" align="center">&nbsp;<ww:property value="yueduhejiBean.thj"/></td>
	            <td class="tdClass" align="center">&nbsp;<ww:property value="yueduhejiBean.bbjsjs"/></td>
	            <td class="tdClass" align="center">&nbsp;<ww:property value="yueduhejiBean.bbjbjs"/></td>
	            <td class="tdClass" align="center">&nbsp;<ww:property value="yueduhejiBean.cnjsjs"/></td>
	            <td class="tdClass" align="center">&nbsp;<ww:property value="yueduhejiBean.cnjbjs"/></td>
	            <td class="tdClass" align="center">&nbsp;<ww:property value="yueduhejiBean.lbjsjs"/></td>
	            <td class="tdClass" align="center">&nbsp;<ww:property value="yueduhejiBean.lbjbjs"/></td>
	            <td class="tdClass" align="center">&nbsp;<ww:property value="yueduhejiBean.tbspjsjs"/></td>
	            <td class="tdClass" align="center">&nbsp;<ww:property value="yueduhejiBean.tbspjbjs"/></td>
	            <td class="tdClass" align="center">&nbsp;<ww:property value="yueduhejiBean.ksspjsjs"/></td>
	            <td class="tdClass" align="center">&nbsp;<ww:property value="yueduhejiBean.ksspjbjs"/></td>
	            <td class="tdClass" align="center">&nbsp;<ww:property value="yueduhejiBean.sbjsjs"/></td>
	            <td class="tdClass" align="center">&nbsp;<ww:property value="yueduhejiBean.sbjbjs"/></td>
	            <td class="tdClass" align="center">&nbsp;<ww:property value="yueduhejiBean.hjsjs"/></td>
	            <td class="tdClass" align="center">&nbsp;<ww:property value="yueduhejiBean.hjbjs"/></td>
	            <td class="tdClass" align="center">&nbsp;<ww:property value="yueduhejiBean.ndjzbyzxj"/></td>
	            <td class="tdClass" align="center">&nbsp;<ww:property value="yueduhejiBean.ndjzbysjs"/></td>
	            <td class="tdClass" align="center">&nbsp;<ww:property value="yueduhejiBean.ndjzbybjs"/></td>
	          </tr>
        </table>
     </div>  
	 <div align="center"><input type="button" value="导出Excel" onclick="saveCode(tableExcel)">  </div>        
  </body>
</html>
