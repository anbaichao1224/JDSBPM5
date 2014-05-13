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
<script type="text/javascript">
var processid = '<ww:property value="matterInfoListBean.processdefid"/>';
/*function showPersonPosition(){
			createPersonPositionWindow('5015','');
        
   }*/
    
    
    function sxlxchange(){
 		if(document.getElementById("sxlx").value=="1"){
 			document.getElementById("processtr").style.display="block";
 			document.getElementById("sxfj").style.display="none";
 		}else{
 			document.getElementById("processtr").style.display="none";
 			document.getElementById("sxfj").style.display="block";
 		}
 		document.getElementById("personname").value="";
 		document.getElementById("personid").value="";
 	} 
 	Ext.onReady(function(){
 		var cdate = new Ext.form.DateField({
		applyTo:'createdate',
		value:new Date(),
		fieldLabel:'日期',
		emptyText:'请选择',
		format:'Y-m-d'
		});
	
		var sxksdate = new Ext.form.DateField({
		applyTo:'sxkssj',
		fieldLabel:'日期',
		format:'Y-m-d'
		});
	
		var sxjsdate = new Ext.form.DateField({
		applyTo:'sxjssj',
		fieldLabel:'日期',
		format:'Y-m-d'
		});
 		var sxlx = document.getElementById("sxlx").value;
		if(sxlx=="1"){
			document.getElementById("processtr").style.display="block";
			document.getElementById("sxfj").style.display="none";
			queryProcess();
		}
		
		var blstatus = '<ww:property value="matterInfoListBean.blstatus"/>';
		var sxstatus = '<ww:property value="sxstatus"/>';
		if(blstatus!=0){
			if(sxlx=="1"){
				document.getElementById("detailbl").style.display="";
			}
			if(sxstatus!=5){
				document.getElementById("savematter").style.display="none";
				getformitems('updateForm','');
			}
		}
 	});
 	function getformitems(formname,dis)
 	{   
 		
 		var v="";
  		 for(var   i=0;i<eval("document."+formname+".length");i++)  
   		{ 
   			 v = eval("document."+formname+".item(i).name"); 
   			 if(eval("document."+formname+".item(i).type")!="button"){
   			 	eval("document."+formname+"."+v+".disabled='disabled'");
   			 	if(dis=='false'){
   			 		eval("document."+formname+"."+v+".disabled=false");
   			 	}
  			 }
    	}
  	}
  	
  	function matterbl(){
  		document.getElementById("rebl").style.display="none";
  		document.getElementById("savematter").style.display="";
  		document.getElementById("savematter").value="提交";
  		document.updateForm.action="matterinfo_matterrebl.action";
  		getformitems("updateForm","false");
  		
  	} 
function detail(){
 		window.top.prodetail('<ww:property value="matterInfoListBean.processinstid"/>','<ww:property value="matterInfoListBean.activityinstid"/>');
   }
</script>
</HEAD>
<BODY>
<center>
<form action="matterinfo_updateInfo.action" method="post" name="updateForm">
<input type="hidden" name="blstatus" value='<ww:property value="matterInfoListBean.blstatus"/>'/>
<input type="hidden" name="sxhyid" id="sxhyid" value='<ww:property value="matterInfoListBean.sxhyid"/>'/><input type="hidden" name="uuid" id="uuid" value='<ww:property value="matterInfoListBean.uuid"/>'/>
<table width="100%" height="327" align="center" style="border:1px solid #99bbe8;line-height:30px;" bgcolor="#dfe8f6">
   <tr style="line-height:35px">
   	<td colspan="4" align="center"><font size="5">事项信息</font></td>
   </tr>
    <tr>
    <td width="144" align="right">起草人：</td>
    <td width="184"><input name="creator" type="text" id="creator" value='<ww:property value="matterInfoListBean.creator"/>' /></td>
    <td width="96" align="right">起草时间：</td>
    <td width="272"><input name="createdate" type="text" id="createdate" value='<ww:property value="matterInfoListBean.createdate"/>' /></td>
  </tr>
  <tr>
    <td align="right">所属阶段：</td>
    <td><select name="sxjd" id="sxjd">
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
    <td><select name="sxlx" id="sxlx" onchange="sxlxchange()">
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
    <td><input type="text" name="sxkssj" value='<ww:property value="matterInfoListBean.sxkssj"/>' /></td>
    <td align="right">结束时间：</td>
    <td><input type="text" name="sxjssj" value='<ww:property value="matterInfoListBean.sxjssj"/>' /></td>
  </tr>
  <tr>
    <td align="right">事项名称：</td>
    <td colspan="3"><input name="sxname" type="text" id="sxname" size="75" value='<ww:property value="matterInfoListBean.sxmc" />'/></td>
  </tr>
  <tr>
    <td align="right">办理人：</td>
    <td colspan="3"><input name="personname" type="text" id="personname" size="50" value='<ww:property value="matterInfoListBean.personname"/>'/><input type="hidden" name="personid" id="personid" value='<ww:property value="matterInfoListBean.personid"/>'/>
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
    <td colspan="3"><textarea name="sxcontent" cols="75" rows="10" id="sxcontent"><ww:property value="matterInfoListBean.sxnr"/></textarea></td>
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
    <ww:if test="matterInfoListBean.blstatus!=2"><input type="button" name="bj" id="bj" value="办结" onclick="banjie()"/></ww:if><ww:else><input type="button" name="Submit2" id="rebl" value="重新办理" onclick="matterbl()" /></ww:else>
    <input type="button" name="Submit3" id="detailbl" style="display:none" value="查看办理情况" onclick="detail()" />
  </tr>
  </ww:if>
</table>
</form>
</center>
</BODY>
</HTML>