<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.Map,com.opensymphony.xwork2.ActionContext,com.opensymphony.xwork2.util.OgnlValueStack"%>
<%@ taglib uri="/struts-tags" prefix="ww"%>
<%
String contextpath = request.getContextPath() + "/";
 %>
<HTML>
<HEAD>

<TITLE>新增会议</TITLE>
<style type="text/css">
body{width:800px;background-color:#dfe8f6;height:100%}

</style>
<SCRIPT LANGUAGE="JavaScript" type="text/javascript">
	var context='<%=contextpath%>';
	var cc = '<ww:property value="mtype" />';
</SCRIPT>
<script type="text/javascript" src="<%=contextpath%>js/extAll.js"></script>
<script type="text/javascript" src="<%=contextpath%>desktop/widgets/metting/js/mettingAll.js" defer="defer"></script>
<script type="text/javascript">
	var obj;
	
	function wclose(){
		document.getElementById("addmatter").style.display="none";
	}
	function showmatter(){
		var mtype = document.getElementById("hylx").value;
		//alert(mtype);
		Ext.Ajax.request({
			url: "bpmmatter.action",
			params: {mtype:mtype},
			method: "post",
			success: function(resp,opts){
				var respText = Ext.util.JSON.decode(resp.responseText);
				//alert(respText.length);
				
				var showdiv = document.getElementById("cymatter");
				showdiv.innerHTML="";
				for(var i=0;i<respText.length;i++){
					showdiv.innerHTML +="<a href=javascript:void(0) onclick=\"matterupdate(this,'"+respText[i].sxmc+"','"+respText[i].sxssjd+"','"+respText[i].creator+"');return false;\">"+respText[i].sxmc+"</a><br/>";
				}
								
				
			}
		});
	}

function mettingbj(){

	var isbj = document.getElementById("isbj").value;
			if(isbj =="no"){
			alert("还有事项没有办结，办结完后才可办结会议");
				return false;	
			}
			var mid = document.getElementById("childOrgId").value;
			//window.open("metting_mettingBj.action?childOrgId="+mid);
			Ext.Ajax.request({
			url: "metting_mettingBj.action",
			params: {childOrgId:mid},
			method: "post",
			success: function(resp,opts){		
				parent.Ext.getCmp('forum-tree').root.reload();
				parent.Ext.getCmp('topic-grid').hide();
			}
		});
			return true;
		
}
</script>
</HEAD>
<BODY>
<center>
<form name="updateMettingForm" id="updateMettingForm"  method="post" action="savemetting.action"  enctype="multipart/form-data" >
		<input type="hidden" name="childOrgId" id="childOrgId" value='<ww:property value="bmetting.uuid"/>'/>
		<input type="hidden" name="matterids" id="matterids"/>
		<input type="hidden" name="isbj" id="isbj"/>
		<div align="center" id="toolbarObj" style="width:100%;"></div>
		<table width="100%" border="0" align="center" style="line-height:30px" borderColorDark="#ffffff" borderColorLight="#999999">
			<tr>
				<td colspan="4" align="center" style="line-height:40pxs"><font size="5">会议信息</font></td>
			</tr>
			<tr class="trf">
				<td align="right" class="tdl" width="14%">起草人：</td>
				<td class="tdv" width="36%" ><input type="text" name="creator" id="creator" disabled="disabled"  value='<ww:property value="bmetting.creator"/>' /></td>
				<td align="right" class="tdl" width="14%">起草时间：</td>
				<td class="tdv" width="36%">
						<!--<input type="text" name="createDate" id="createDate" value="" />-->
					<input type="text" value="<ww:date  format="yyyy-MM-dd" name="$CurrTime.currDate"/>" disabled="disabled"/>						
				</td>
			</tr>
			<tr class="trf">
				<td align="right" class="tdl" width="14%">会议开始时间：</td>
				<td class="tdv" width="36%" ><input type="text" name="hykssj" id="hykssj" value='<ww:property value="bmetting.hykssj"/>' disabled="disabled"/></td>
				<td align="right" class="tdl" width="14%">会议结束时间：</td>
				<td class="tdv" width="36%" ><input type="text" name="hyjssj" id="hyjssj" value='<ww:property value="bmetting.hyjssj"/>' disabled="disabled" /></td>
			</tr>
	        <tr class="trf">
				<td align="right" class="tdl" width="14%">会议名称：</td>
				<td colspan="3" class="tdv" >
						<input name="hymc" type="text" id="hymc" size="100" value='<ww:property value="bmetting.hymc"/>' disabled="disabled" /></td>
			</tr>
			<tr class="trf">
				<td align="right" class="tdl" width="14%">会议类型：</td>
				<td colspan="3" class="tdv" >
								<select name="hylx" id="hylx" onchange="showmatter()" disabled="disabled">
					  <option value="市委会议">市委会议</option>
					  <option value="常委会议">常委会议</option>
					  <option value="市全体会议">市全体会议</option>
					  </select>					</td>
			</tr>
				<tr class="trf">
					<td width="14%" rowspan="2" align="right" valign="top">事项列表：</td>
					<td class="tdv" colspan="3" style="line-height:30px;"><div id="cymatter">
					<ww:iterator value="matterinfolist" status="rowstatus">
						<a href='bpmmatter_matterInfoById.action?uuid=<ww:property value="uuid"/>&<ww:if test="bmetting.hyzt==2">sxstatus=5</ww:if><ww:else>sxstatus=1</ww:else>'><ww:property value="sxmc"/></a><br/>
						<ww:if test="blstatus!=2">
						<script type="text/javascript">
							document.getElementById("isbj").value="no";
						</script>
						</ww:if>
					</ww:iterator>
					</div>
					
					</td>
				</tr>
			
				
				<ww:if test="bmetting.hyzt!=2">
			<tr>
					 <td class="tdv" colspan="3" style="padding-left:300px"><input type="button" id="bjbtn" value="办结" onclick="return mettingbj()"/></td> 
					
				</tr>	
				</ww:if>
		</table>
	
	<!-- 意见输入 -->
</form>
</center>
</BODY>
</HTML>