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
var processid = '<ww:property value="mettingbean.processdefid"/>';
</script>
<script type="text/javascript" src="/js/extAll.js"></script>
<script type="text/javascript" src="<%=contextpath %>desktop/js/CreateGrid.js"></script>
<script  src="/js/JDS.js" type="text/javascript"></script>
<script type="text/javascript" src="<%=contextpath %>desktop/widgets/columntreemetting/js/mettingAll.js" defer="defer"></script>
<script type="text/javascript">
function showPersonPosition(){
			createPersonPositionWindow('5015','');
    }
   
   Ext.onReady(function(){
 		var sxlx = document.getElementById("sxlxvalue").value;
		if(sxlx=="1"){
			document.getElementById("bldetail").style.display="block";
			document.getElementById("processtr").style.display="block";
			document.getElementById("sxfj").style.display="none";
			queryProcess();
			var state = '<ww:property value="processInst.state"/>';
			if(state=="running"&&'<ww:property value="mettingbean.blstatus"/>'!="2"){
				document.getElementById("startpro").style.display="none";
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
<form action="saveBlInfo.action" method="post" name="updateForm">
<input type="hidden" name="tid" id="tid" value='<ww:property value="mettingbean.tid"/>'/>
<input type="hidden" name="sxlx" id="sxlx" value='<ww:property value="mettingbean.sxlx"/>'/>
<table width="100%" height="327" align="center" style="border:1px solid #99bbe8;line-height:30px;" bgcolor="#dfe8f6">
    <tr>
    <td width="144" align="right">起草人：</td>
    <td width="184"><input name="creator" type="text" id="creator" value='<ww:property value="mettingbean.creator"/>'  disabled="disabled"/></td>
    <td width="96" align="right">起草时间：</td>
    <td width="272"><input name="createdate" type="text" id="createdate" value='<ww:property value="mettingbean.createdate"/>' disabled="disabled" /></td>
  </tr>
    <td align="right">事项类型：</td>
    <td colspan="3">
     <select name="sxlxvalue" id="sxlxvalue" disabled="disabled">
      <option value="0">普通</option>
      <option value="1" >走流程</option>
    </select>    
     <script type="text/javascript">
    	document.getElementById("sxlxvalue").value='<ww:property value="mettingbean.sxlx"/>';
    </script>
    </td>
  </tr>
  <tr>
    <td align="right">开始时间：</td>
    <td><input type="text" name="kssj" value='<ww:property value="mettingbean.kssj"/>' disabled="disabled" /></td>
    <td align="right">结束时间：</td>
    <td><input type="text" name="jssj" value='<ww:property value="mettingbean.jssj"/>' disabled="disabled" /></td>
  </tr>
  <tr>
    <td align="right">事项名称：</td>
    <td colspan="3"><input name="xxname" type="text" id="sxname" size="75" value='<ww:property value="mettingbean.name" />' disabled="disabled"/></td>
  </tr>
  <tr>
    <td align="right">办理人：</td>
    <td colspan="3"><input name="personname" type="text" id="personname" size="50" value='<ww:property value="mettingbean.blrmc"/>' disabled="disabled"/><input type="hidden" name="personid" id="personid" value='<ww:property value="mettingbean.blrid"/>'/>
    <input name="choice" type="button" id="choice" value="选择" onclick="showPersonPosition()" disabled="disabled" /></td>
  </tr>
  <tr id="processtr" style="display:none">
  	<td align="right">流程列表：</td>
  	<td><select name="processdefid" id="processdefid" disabled="disabled">
  	</select></td>
  </tr>
  <tr>
    <td align="right" valign="top">事项内容：</td>
    <td colspan="3"><textarea name="sxnr" cols="75" rows="10" id="sxcontent" disabled="disabled"><ww:property value="mettingbean.sxnr"/></textarea></td>
  </tr>
  <ww:if test="mettingbean.sxlx==0">
  <tr>
    <td align="right" valign="top">办理情况：</td>
    <td colspan="3">
    <table>
    <ww:iterator value="blinfoList" status="rowstatus">
    	<tr>
    		<td><ww:property value="hfcontent"/></td>
    	</tr>
    	<tr>
    		<td align="center"><ww:property value="personname"/>&nbsp;&nbsp;<ww:property value="bldate"/></td>
    	</tr>
    	<tr><td><hr/></td></tr>
    </ww:iterator>
    <ww:if test="mettingbean.blstatus!=2">
    <tr>
    <td>
    <textarea name="sxblqk" cols="75" rows="10" id="sxblqk"><ww:property value="mettingbean.blqk"/></textarea>
    </td>
    </tr>
    </ww:if>
    
    </table>
    </td>
  </tr>
  </ww:if>
  <tr id="sxfj">
                    <td align="right" valign="top">附&nbsp;&nbsp;&nbsp;&nbsp;件</td>
                    <td colspan="3">
                    	<ww:action  name="mfileinclude" executeResult="true"/>
                    </td>
                </tr>
  
  <tr>
    <td colspan="4">
    
    <input type="hidden" name="liststatus" value='<ww:property value="liststatus"/>'/>
    <div style="float:left;display:inline;padding-left:300px;">
    <ww:if test="mettingbean.blstatus!=2">
    <ww:if test="mettingbean.sxlx==0"><input type="submit" name="savematter" value="提交"/></ww:if><ww:else><input type="button" name="startpro" id="startpro" value="开始办理" onclick="startprocess()"/></ww:else></div></ww:if><div style="float:left;display:inline;"><input type="button" id="bldetail" style="display:none" value="查看办理情况" onclick="detail()"/></div>
    </td>
  </tr>
  
</table>
</form>
</center>
</BODY>
</HTML>