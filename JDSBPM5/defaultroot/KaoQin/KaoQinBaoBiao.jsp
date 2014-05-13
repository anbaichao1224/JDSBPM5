<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="ww"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<html>
  <head>
    
    <title>窗口考勤统计</title>
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
  		<ww:form action="kaoQinBaoBiao" theme="simple">
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
			潮&nbsp;州&nbsp;市&nbsp;行&nbsp;政&nbsp;服&nbsp;务&nbsp;中&nbsp;心&nbsp;各&nbsp;行&nbsp;政&nbsp;服&nbsp;务&nbsp;窗&nbsp;口&nbsp;考&nbsp;勤&nbsp;统&nbsp;计&nbsp;表
		</div>
  		<table width="100%" align="center" border="1" bordercolor="#000000" cellpadding="0" cellspacing="0">
          <tr>
            <td rowspan="2" class="tdClass" align="center">
            	序号
            </td>
            <td rowspan="2" colspan="2" class="tdClass" align="center">
            	“窗口”名称
            </td>
            <td rowspan="2" class="tdClass" align="center">
            	派驻<br>
            	人数<br>
            	(人)
            </td>
            <td rowspan="2" class="tdClass" align="center">
            	实际到<br>
            	岗人数<br>
            	(人)
            </td>
            <td colspan="2" class="tdClass" align="center">
            	请假
            </td>
            <td rowspan="2" class="tdClass" align="center">
            	缺勤
            </td>
            <td rowspan="2" class="tdClass" align="center">
            	迟到<br>
            	(次)
            </td>
            <td rowspan="2" class="tdClass" align="center">
            	早退<br>
            	(次)
            </td>
            <td rowspan="2" class="tdClass" align="center">
            	备注
            </td>
          </tr>
          <tr>
            <td class="tdClass" align="center">公假</td>
            <td class="tdClass" align="center">
            	病(事)<br>
            	假
            </td>
          </tr>
          <ww:iterator value="kaoqinbaobiaoList" id="kaoqin" status="st">
	          <tr>
	            <td class="tdClass" align="center"><ww:property value="#st.index+1"/></td>
	            <td class="tdClass" align="center" colspan="2"><ww:property value="ckmc"/></td>
	            <td class="tdClass" align="center">&nbsp;</td>
	            <td class="tdClass" align="center">&nbsp;</td>
	            <td class="tdClass" align="center"><ww:if test="%{#kaoqin.gj==0}">&nbsp;</ww:if><ww:else><ww:property value="gj"/></ww:else></td>
	            <td class="tdClass" align="center"><ww:if test="%{#kaoqin.bsj==0}">&nbsp;</ww:if><ww:else><ww:property value="bsj"/></ww:else></td>
	            <td class="tdClass" align="center"><ww:if test="%{#kaoqin.qq==0}">&nbsp;</ww:if><ww:else><ww:property value="qq"/></ww:else></td>
	            <td class="tdClass" align="center"><ww:if test="%{#kaoqin.cd==0}">&nbsp;</ww:if><ww:else><ww:property value="cd"/></ww:else></td>
	            <td class="tdClass" align="center"><ww:if test="%{#kaoqin.zt==0}">&nbsp;</ww:if><ww:else><ww:property value="zt"/></ww:else></td>
	            <td class="tdClass" align="center">&nbsp;</td>
	          </tr>
          </ww:iterator>
	          <tr>
	            <td class="tdClass" align="center">&nbsp;</td>
	            <td class="tdClass" align="center" colspan="2">合计</td>
				<td class="tdClass" align="center">&nbsp;</td>
				<td class="tdClass" align="center">&nbsp;</td>
				<td class="tdClass" align="center">&nbsp;<ww:property value="kqbbBean.gjhj"/></td>
				<td class="tdClass" align="center">&nbsp;<ww:property value="kqbbBean.bsjhj"/></td>
				<td class="tdClass" align="center">&nbsp;<ww:property value="kqbbBean.qqhj"/></td>
				<td class="tdClass" align="center">&nbsp;<ww:property value="kqbbBean.cdhj"/></td>
				<td class="tdClass" align="center">&nbsp;<ww:property value="kqbbBean.zthj"/></td>
				<td class="tdClass" align="center">&nbsp;</td>
	          </tr>
        </table>
     </div>  
	 <div align="center"><input type="button" value="导出Excel" onclick="saveCode(tableExcel)">  </div>
  </body>
</html>
