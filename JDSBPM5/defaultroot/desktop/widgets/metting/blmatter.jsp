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
<script type="text/javascript" src="<%=contextpath %>desktop/widgets/metting/js/mettingAll.js" defer="defer"></script>
<script type="text/javascript" src="<%=contextpath %>desktop/widgets/metting/js/matterAll.js" defer="defer"></script>
<script type="text/javascript">
var processid = '<ww:property value="matterInfoListBean.processdefid"/>';
function showPersonPosition(){
	   		alert("in function");
			createPersonPositionWindow('5015','');
			//createMettingWindow();  //会议树形结构显示
	         //store.reload();
        
    }
   
   Ext.onReady(function(){
 		var sxlx = document.getElementById("sxlx").value;
		if(sxlx=="1"){
			document.getElementById("bldetail").style.display="block";
			document.getElementById("processtr").style.display="block";
			document.getElementById("sxfj").style.display="none";
			queryProcess();
			var state = '<ww:property value="processInst.state"/>';
			if(state=="running"&&'<ww:property value="sxstatus"/>'!="2"){
				document.getElementById("startpro").style.display="none";
			}
		}
		
		
 	});
 	
 	function getformitems(formname)
 	{   
 		
 		var v="";
  		 for(var   i=0;i<eval("document."+formname+".length");i++)  
   		{ 
   			 v = eval("document."+formname+".item(i).name"); 
   			 if(eval("document."+formname+".item(i).type")!="button"){
   			 	eval("document."+formname+"."+v+".disabled='disabled'");
   			 	if(eval("document."+formname+".item(i).id")=="sxblqk"){
   			 		document.getElementById("sxblqk").disabled=false;
   			 	}
  			 }
    	}
  	}
 	
   function detail(){
 		window.top.prodetail('<ww:property value="matterInfoListBean.processinstid"/>','<ww:property value="matterInfoListBean.activityinstid"/>');
   }
</script>
</HEAD>
<BODY>
<center>
<form action="saveBlInfo.action" method="post" name="updateForm">
<table width="100%" height="327" align="center" style="border:1px solid #99bbe8;line-height:30px;" bgcolor="#dfe8f6">
    <tr>
    <td width="144" align="right">起草人：</td>
    <td width="184"><input name="creator" type="text" id="creator" value='<ww:property value="matterInfoListBean.creator"/>'  disabled="disabled"/></td>
    <td width="96" align="right">起草时间：</td>
    <td width="272"><input name="createdate" type="text" id="createdate" value='<ww:property value="matterInfoListBean.createdate"/>' disabled="disabled" /></td>
  </tr>
  <tr>
    <td align="right">所属阶段：</td>
    <td><select name="sxjd" id="sxjd" disabled="disabled">
      <option value="会前协调工作">会前协调工作</option>
      <option value="会前准备工作">会前准备工作</option>
      <option value="会议期间工作">会议期间工作</option>
      <option value="会后工作">会后工作</option>
    </select>
    <script type="text/javascript">
    	var sxjd = document.getElementById("sxjd");
		//alert(sxjd.options.length);
		var datasxjd = '<ww:property value="matterInfoListBean.sxssjd"/>';
		for(var i=0;i<sxjd.options.length;i++){
			if(sxjd.options[i].value==datasxjd){
				sxjd.options[i].selected='selected';
				break;
			}
		}
    </script>
    </td>
    <td align="right">事项类型：</td>
    <td><select name="sxlx" id="sxlx">
      <option value="0">普通</option>
      <option value="1">走流程</option>
    </select>    
     <script type="text/javascript">
    	var sxlx = document.getElementById("sxlx");
		var datasxlx = '<ww:property value="matterInfoListBean.sxlx"/>';
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
    <td><input type="text" name="sxkssj" value='<ww:property value="matterInfoListBean.sxkssj"/>' disabled="disabled" /></td>
    <td align="right">结束时间：</td>
    <td><input type="text" name="sxjssj" value='<ww:property value="matterInfoListBean.sxjssj"/>' disabled="disabled" /></td>
  </tr>
  <tr>
    <td align="right">事项名称：</td>
    <td colspan="3"><input name="sxname" type="text" id="sxname" size="75" value='<ww:property value="matterInfoListBean.sxmc" />' disabled="disabled"/></td>
  </tr>
  <tr>
    <td align="right">办理人：</td>
    <td colspan="3"><input name="personname" type="text" id="personname" size="50" value='<ww:property value="matterInfoListBean.personname"/>' disabled="disabled"/><input type="hidden" name="personid" id="personid" value='<ww:property value="matterInfoListBean.personid"/>'/>
    <input name="choice" type="button" id="choice" value="选择" onclick="showPersonPosition()" disabled="disabled" /></td>
  </tr>
  <tr id="processtr" style="display:none">
  	<td align="right">流程列表：</td>
  	<td><select name="processdefid" id="processdefid" disabled="disabled">
  	</select></td>
  </tr>
  <tr>
    <td align="right" valign="top">事项内容：</td>
    <td colspan="3"><textarea name="sxcontent" cols="75" rows="10" id="sxcontent" disabled="disabled"><ww:property value="matterInfoListBean.sxnr"/></textarea></td>
  </tr>
  <ww:if test="matterInfoListBean.sxlx==0">
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
    <ww:if test="sxstatus==0||sxstatus==1">
    <tr>
    <td>
    <textarea name="sxblqk" cols="75" rows="10" id="sxblqk"><ww:property value="matterInfoListBean.blqk"/></textarea>
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
    
    <ww:if test="sxstatus==0||sxstatus==1"><input type="hidden" name="sxstatus" value='<ww:property value="sxstatus"/>'/><input type="hidden" name="sxxxid" id="sxxxid" value='<ww:property value="matterInfoListBean.uuid"/>'/>
    <div style="float:left;display:inline;padding-left:300px;"><ww:if test="matterInfoListBean.sxlx==0"><input type="submit" name="savematter" value="提交"/></ww:if><ww:else><input type="button" name="startpro" id="startpro" value="开始办理" onclick="startprocess()"/></ww:else></div></ww:if><div style="float:left;display:inline;"><input type="button" id="bldetail" style="display:none" value="查看办理情况" onclick="detail()"/></div>
    </td>
  </tr>
  
</table>
</form>
</center>
</BODY>
</HTML>