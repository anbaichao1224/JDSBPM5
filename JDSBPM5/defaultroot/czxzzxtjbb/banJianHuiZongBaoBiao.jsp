<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="ww"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<html>
  <head>
    
    <title>办件汇总报表</title>
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
		<script type="text/javascript">
		function search(){
        	var frmObj=document.forms[0];
        	frmObj.submit();
        }
        
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
  		<ww:form action="findAllByNian" theme="simple">
		<div>
  			<table>
  				<tr>
  					<td align="right" width="180">窗口部门:</td>
  					<td>
              			<ww:select name="bumen" 
          					list="types"
          					listKey="code"
          					listValue="name"
      					></ww:select>
           			</td>
           			<td>年度:</td>
  					<td>
              			<ww:select name="niandu" list="#{'2012':'2012',
              											'2013':'2013',
              											'2014':'2014',
              											'2015':'2015',
              											'2016':'2016',
              											'2017':'2017',
              											'2018':'2018',
              											'2019':'2019',
              											'2020':'2020',
              											'2021':'2021',
              											'2022':'2022',
              											'2023':'2023',
              											'2024':'2024',
              											'2025':'2025',
              											'2026':'2026',
              											'2027':'2027',
              											'2028':'2028',
              											'2029':'2029',
              											'2030':'2030'}"></ww:select>
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
			潮州市行政服务中心办事服务窗口办件汇总报表
		</div>
		<p></p>

        <table width="100%" border="1" bordercolor="#000000" align="center" cellpadding="0" cellspacing="0">
          <tr>
            <td class="tdClass" align="center" rowspan="3">窗口部门</td>
            <td class="tdClass" align="center" rowspan="3">日期</td>
            <td class="tdClass" align="center" rowspan="3">
            	<p>咨询</p>
            	<p>件数</p>            </td>
            <td class="tdClass" align="center" colspan="16">
            	受&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
              	理&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
              	件&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;数            </td>
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
            <td class="tdClass" align="center">收件数</td>
            <td class="tdClass" align="center">退件数</td>
            <td class="tdClass" align="center">收件数</td>
            <td class="tdClass" align="center">办结数</td>
            <td class="tdClass" align="center">收件数</td>
            <td class="tdClass" align="center">办结数</td>
            <td class="tdClass" align="center">收件数</td>
            <td class="tdClass" align="center">办结数</td>
            <td class="tdClass" align="center">收件数</td>
            <td class="tdClass" align="center">办结数</td>
            <td class="tdClass" align="center">收件数</td>
            <td class="tdClass" align="center">办结数</td>
            <td class="tdClass" align="center">收件数</td>
            <td class="tdClass" align="center">办结数</td>
            <td class="tdClass" align="center">收件数</td>
            <td class="tdClass" align="center">办结数</td>
          </tr>
          <ww:iterator value="nianList" id="nian" status="st">
          <tr>
            <td align="center" class="tdClass"><ww:property value="tjdw"/></td>
            <td class="tdClass" align="center"><ww:property value="#st.index+1"/>月</td>
            <td class="tdClass" align="center"><ww:if test="%{#nian.zxjs==0}">&nbsp;</ww:if><ww:else><ww:property value="zxjs"/></ww:else></td>
            <td class="tdClass" align="center"><ww:if test="%{#nian.jbj==0}">&nbsp;</ww:if><ww:else><ww:property value="jbj"/></ww:else></td>
            <td class="tdClass" align="center"><ww:if test="%{#nian.thj==0}">&nbsp;</ww:if><ww:else><ww:property value="thj"/></ww:else></td>
            <td class="tdClass" align="center"><ww:if test="%{#nian.bbjsjs==0}">&nbsp;</ww:if><ww:else><ww:property value="bbjsjs"/></ww:else></td>
            <td class="tdClass" align="center"><ww:if test="%{#nian.bbjbjs==0}">&nbsp;</ww:if><ww:else><ww:property value="bbjbjs"/></ww:else></td>
            <td class="tdClass" align="center"><ww:if test="%{#nian.cnjsjs==0}">&nbsp;</ww:if><ww:else><ww:property value="cnjsjs"/></ww:else></td>
            <td class="tdClass" align="center"><ww:if test="%{#nian.cnjbjs==0}">&nbsp;</ww:if><ww:else><ww:property value="cnjbjs"/></ww:else></td>
            <td class="tdClass" align="center"><ww:if test="%{#nian.lbjsjs==0}">&nbsp;</ww:if><ww:else><ww:property value="lbjsjs"/></ww:else></td>
            <td class="tdClass" align="center"><ww:if test="%{#nian.lbjbjs==0}">&nbsp;</ww:if><ww:else><ww:property value="lbjbjs"/></ww:else></td>
            <td class="tdClass" align="center"><ww:if test="%{#nian.tbspjsjs==0}">&nbsp;</ww:if><ww:else><ww:property value="tbspjsjs"/></ww:else></td>
            <td class="tdClass" align="center"><ww:if test="%{#nian.tbspjbjs==0}">&nbsp;</ww:if><ww:else><ww:property value="tbspjbjs"/></ww:else></td>
            <td class="tdClass" align="center"><ww:if test="%{#nian.ksspjsjs==0}">&nbsp;</ww:if><ww:else><ww:property value="ksspjsjs"/></ww:else></td>
            <td class="tdClass" align="center"><ww:if test="%{#nian.ksspjbjs==0}">&nbsp;</ww:if><ww:else><ww:property value="ksspjbjs"/></ww:else></td>
            <td class="tdClass" align="center"><ww:if test="%{#nian.sbjsjs==0}">&nbsp;</ww:if><ww:else><ww:property value="sbjsjs"/></ww:else></td>
            <td class="tdClass" align="center"><ww:if test="%{#nian.sbjbjs==0}">&nbsp;</ww:if><ww:else><ww:property value="sbjbjs"/></ww:else></td>
            <td class="tdClass" align="center"><ww:property value="hjsjs"/></td>
            <td class="tdClass" align="center"><ww:property value="hjbjs"/></td>

          </tr>
          </ww:iterator>
		  <tr>
            <td align="center" class="tdClass" colspan="2">整年总计</td>
            <td class="tdClass" align="center">&nbsp;<ww:property value="nianduhejiBean.zxjs"/></td>
            <td class="tdClass" align="center">&nbsp;<ww:property value="nianduhejiBean.jbj"/></td>
            <td class="tdClass" align="center">&nbsp;<ww:property value="nianduhejiBean.thj"/></td>
            <td class="tdClass" align="center">&nbsp;<ww:property value="nianduhejiBean.bbjsjs"/></td>
            <td class="tdClass" align="center">&nbsp;<ww:property value="nianduhejiBean.bbjbjs"/></td>
            <td class="tdClass" align="center">&nbsp;<ww:property value="nianduhejiBean.cnjsjs"/></td>
            <td class="tdClass" align="center">&nbsp;<ww:property value="nianduhejiBean.cnjbjs"/></td>
            <td class="tdClass" align="center">&nbsp;<ww:property value="nianduhejiBean.lbjsjs"/></td>
            <td class="tdClass" align="center">&nbsp;<ww:property value="nianduhejiBean.lbjbjs"/></td>
            <td class="tdClass" align="center">&nbsp;<ww:property value="nianduhejiBean.tbspjsjs"/></td>
            <td class="tdClass" align="center">&nbsp;<ww:property value="nianduhejiBean.tbspjbjs"/></td>
            <td class="tdClass" align="center">&nbsp;<ww:property value="nianduhejiBean.ksspjsjs"/></td>
            <td class="tdClass" align="center">&nbsp;<ww:property value="nianduhejiBean.ksspjbjs"/></td>
            <td class="tdClass" align="center">&nbsp;<ww:property value="nianduhejiBean.sbjsjs"/></td>
            <td class="tdClass" align="center">&nbsp;<ww:property value="nianduhejiBean.sbjbjs"/></td>
            <td class="tdClass" align="center">&nbsp;<ww:property value="nianduhejiBean.hjsjs"/></td>
            <td class="tdClass" align="center">&nbsp;<ww:property value="nianduhejiBean.hjbjs"/></td>
          </tr>
        </table>
        </div>  
	 <div align="center"><input type="button" value="导出Excel" onclick="saveCode(tableExcel)">  </div>
  </body>
</html>
