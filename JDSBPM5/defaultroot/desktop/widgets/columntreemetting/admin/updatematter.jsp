<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="ww"%>
<%
String contextpath = request.getContextPath() + "/";
 %>
<HTML>
<HEAD>

<TITLE>新增会议</TITLE>
<script type="text/javascript">
	var context="/";
</script>
<script type="text/javascript" src="/js/extAll.js"></script>
<script type="text/javascript" src="<%=contextpath %>desktop/js/CreateGrid.js"></script>
<script  src="/js/JDS.js" type="text/javascript"></script>
<script type="text/javascript" src="<%=contextpath %>desktop/widgets/columntreemetting/js/mettingAll.js" defer="defer"></script>
<script type="text/javascript" src="<%=contextpath %>desktop/widgets/metting/js/mettingcheck.js" defer="defer"></script>
<script type="text/javascript">
var processid = '<ww:property value="mettingbean.processdefid"/>';
 	Ext.onReady(function(){
 		var cdate = new Ext.form.DateField({
		applyTo:'createdate',
		fieldLabel:'日期',
		emptyText:'请选择',
		format:'Y-m-d'
		});
	
		var sxksdate = new Ext.form.DateField({
		applyTo:'kssj',
		fieldLabel:'日期',
		format:'Y-m-d'
		});
	
		var sxjsdate = new Ext.form.DateField({
		applyTo:'jssj',
		fieldLabel:'日期',
		format:'Y-m-d'
		});
		if('<ww:property value="mettingbean.blstatus"/>'!='0'){
			document.getElementById("savematter").style.display="none";
		}
 		var sxlx = document.getElementById("sxlx").value;
		if(sxlx=="1"){
			document.getElementById("processdefid").disabled=false;
			document.getElementById("processtr").style.display="block";
			document.getElementById("sxfj").style.display="none";
			document.getElementById("detailbl").style.display="";
			queryProcess();
		}
		
		var blstatus = '<ww:property value="mettingbean.blstatus"/>';
		var sxstatus = '<ww:property value="sxstatus"/>';
		if(blstatus!=0){
			if(sxlx=="1"){
				document.getElementById("detailbl").style.display="";
			}
			if(sxstatus!=5){
				document.getElementById("savematter").style.display="none";
			}
		}
 	});  	
function detail(){
 		window.top.prodetail('<ww:property value="mettingbean.processinstid"/>','<ww:property value="mettingbean.activityinstid"/>');
   }
</script>
</HEAD>
<BODY style="background-color:#dfe8f6">
<center>
<form action="mettingbl_updateInfo.action" method="post" id="updateForm" name="updateForm">
<input type="hidden" name="tid" id="tid" value='<ww:property value="mettingbean.tid"/>'/>
<input type="hidden" name="parentid" id="parentid" value='<ww:property value="mettingbean.parentid"/>'/>
<input type="hidden" name="mettingid" id="mettingid" value='<ww:property value="mettingbean.mettingid"/>'/>
<input type="hidden" name="blstatus" value='<ww:property value="mettingbean.blstatus"/>'/>
<table width="100%" height="327" align="center" style="border:1px solid #99bbe8;line-height:30px;" bgcolor="#dfe8f6">
   <tr style="line-height:35px">
   	<td colspan="4" align="center"><font size="5">事项信息</font></td>
   </tr>
    <tr>
    <td width="144" align="right">起草人：</td>
    <td width="184"><input name="creator" type="text"  onkeydown="return false;"  id="creator" value='<ww:property value="mettingbean.creator"/>' /></td>
    <td width="96" align="right">起草时间：</td>
    <td width="272"><input name="createdate" type="text"  onkeydown="return false;"  id="createdate" value='<ww:property value="mettingbean.createdate"/>' /></td>
  </tr>
    <td align="right">事项类型：</td>
    <td colspan="3"><select name="sxlx" id="sxlx" onchange="sxlxchange()">
      <option value="0">普通</option>
      <option value="1">走流程</option>
    </select>    
     <script type="text/javascript">
    	var sxlx = document.getElementById("sxlx");
		var datasxlx = '<ww:property value="mettingbean.sxlx"/>';
		for(var i=0;i<sxlx.options.length;i++){
			if(sxlx.options[i].value==datasxlx){
				sxlx.options[i].selected='selected';
				break;
			}
		}
    </script>
    </td>
  </tr>
  <tr>
    <td align="right">开始时间：</td>
    <td><input type="text" name="kssj"  onkeydown="return false;"  value='<ww:property value="mettingbean.kssj"/>' /></td>
    <td align="right">结束时间：</td>
    <td><input type="text" name="jssj"  onkeydown="return false;"  value='<ww:property value="mettingbean.jssj"/>' /></td>
  </tr>
  <tr>
    <td align="right">事项名称：</td>
    <td colspan="3"><input name="xxname" type="text" id="xxname" size="75" value='<ww:property value="mettingbean.name" />'/></td>
  </tr>
  <tr>
    <td align="right">办理人：</td>
    <td colspan="3"><input name="personname" type="text" id="personname" onkeydown="return false;"  size="50" value='<ww:property value="mettingbean.blrmc"/>'/><input type="hidden" name="personid" id="personid" value='<ww:property value="mettingbean.blrid"/>'/>
    <input name="choice" type="button" id="choice" value="选择" onclick="createPersonPositionWindow('5015','')" /></td>
  </tr>
   <tr id="processtr" style="display:none">
  	<td align="right">流程列表</td>
  	<td><select name="processdefid" id="processdefid">
  	</select></td>
  </tr>
  <script type="text/javascript">
  		
    </script>
  <tr>
    <td align="right" valign="top">事项内容：</td>
    <td colspan="3"><textarea name="sxnr" cols="75" rows="10" id="sxcontent"><ww:property value="mettingbean.sxnr"/></textarea></td>
  </tr>
    <td align="right" valign="top">办理情况：</td>
    <td colspan="3">
    <table width="80%">
    <ww:if test="blinfoList.size==0"><tr>历史办理记录为空<td></td></tr></ww:if>
    <ww:iterator value="blinfoList" status="rowstatus">
    	<tr>
    		<td><ww:property value="hfcontent"/></td>
    	</tr>
    	<tr>
    		<td align="center"><ww:property value="personname"/>&nbsp;&nbsp;<ww:property value="bldate"/></td>
    	</tr>
    	<tr><td><hr/></td></tr>
    </ww:iterator>
    </table>
    </td>
  </tr>
  <tr id="sxfj">
    <td align="right" valign="top">附 &nbsp;&nbsp;&nbsp;&nbsp;件：</td>
    <td colspan="3"><ww:action name="mfileinclude"  executeResult="true"></ww:action></td>
  </tr>
  <ww:if test="sxstatus!=5">
  <tr>
    <td colspan="4" align="center"><input type="submit" name="savematter" id="savematter" value="修改" />
    <ww:if test="mettingbean.blstatus!=2"><input type="button" name="bj" id="bj" value="办结" onclick="banjie()"/></ww:if><ww:elseif test="liststatus!=5"><input type="submit" name="Submit2" id="rebl" value="重新办理" /></ww:elseif>
    <input type="button" name="Submit3" id="detailbl" style="display:none" value="查看办理情况" onclick="detail()" />
  </tr>
  </ww:if>
</table>
</form>
</center>
</BODY>
</HTML>