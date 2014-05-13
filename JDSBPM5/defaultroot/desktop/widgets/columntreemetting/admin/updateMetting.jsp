<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.Map,com.opensymphony.xwork2.ActionContext,com.opensymphony.xwork2.util.OgnlValueStack,net.itjds.common.org.base.Person"%>
<%@ taglib uri="/struts-tags" prefix="ww"%>
<%
String contextpath = request.getContextPath() + "/";
Person person = (Person)ActionContext.getContext().getValueStack().findValue("$currPerson");
 %>
<HTML>
<HEAD>

<TITLE>新增会议</TITLE>
<style type="text/css">
body{width:800px;background-color:#dfe8f6;height:60%}

</style>
<SCRIPT LANGUAGE="JavaScript" type="text/javascript">
	var context='<%=contextpath%>';
</SCRIPT>
<script type="text/javascript" src="<%=contextpath%>js/extAll.js"></script>
<script type="text/javascript" src="<%=contextpath%>desktop/widgets/columntreemetting/js/mettingAll.js" defer="defer"></script>
<script type="text/javascript">
function mettingbj(){

			if('<ww:property value="count"/>'>0){
				alert("还有事项没有办结，办结完后才可办结会议");
				return false;	
			}
			var mid = document.getElementById("childOrgId").value;
			//alert(mid);
			//window.open("metting_mettingBj.action?childOrgId="+mid);
			Ext.Ajax.request({
			url: 'mettingbl_matterBj.action',
			params:{tid:mid,blstatus:'2'},
			method:'post',
			success: function(){		
				//parent.Ext.getCmp('form-columntree').root.reload();
				alert("操作成功");
				document.getElementById("bjbtn").style.display="none";
			}
		});
			return true;
		
}
</script>
</HEAD>
<BODY>
<center>
<form name="updateMettingForm" id="updateMettingForm"  method="post" action="savemetting.action"  enctype="multipart/form-data" >
		<input type="hidden" name="tid" id="childOrgId" value='<ww:property value="mettingbean.tid"/>'/>
		
		<input type="hidden" name="adddirection" id="adddirection" value='<ww:property value="adddirection"/>'/>
		<div align="center" id="toolbarObj" style="width:100%;"></div>
		<table width="100%" border="0" align="center" style="line-height:30px" borderColorDark="#ffffff" borderColorLight="#999999">
			<tr>
				<td colspan="4" align="center" style="line-height:40pxs"><font size="5">会议信息</font></td>
			</tr>
			<tr class="trf">
				<td align="right" class="tdl" width="14%">起草人：</td>
				<td class="tdv" width="36%" ><input type="text" name="creator"  onkeydown="return false;"  id="creator" disabled="disabled"  value='<ww:property value="mettingbean.creator"/>' /></td>
				<td align="right" class="tdl" width="14%">起草时间：</td>
				<td class="tdv" width="36%">
						<!--<input type="text" name="createDate" id="createDate" value="" />-->
					<input type="text" value='<ww:property value="mettingbean.createdate"/>'  onkeydown="return false;"  disabled="disabled"/>						
				</td>
			</tr>
			<tr class="trf">
				<td align="right" class="tdl" width="14%">会议开始时间：</td>
				<td class="tdv" width="36%" ><input type="text" name="kssj" id="hykssj"  onkeydown="return false;"  value='<ww:property value="mettingbean.kssj"/>' disabled="disabled"/></td>
				<td align="right" class="tdl" width="14%">会议结束时间：</td>
				<td class="tdv" width="36%" ><input type="text" name="jssj" id="hyjssj"  onkeydown="return false;"  value='<ww:property value="mettingbean.jssj"/>' disabled="disabled" /></td>
			</tr>
	        <tr class="trf">
				<td align="right" class="tdl" width="14%">会议名称：</td>
				<td colspan="3" class="tdv" >
						<input name="xxname" type="text" id="hymc" size="80" value='<ww:property value="mettingbean.name"/>' disabled="disabled" /></td>
			</tr>
			<ww:if test="adddirection==3">
			<tr class="trf">
				<td align="right" class="tdl" width="14%">会议类型：</td>
				<td colspan="3" class="tdv" >
								<select name="hylxid" id="hylxid" disabled="disabled">
					 			<ww:iterator value="mtbeanlist" status="rowstatus">
					  	
					  		<option value='<ww:property value="lxmc"/>'><ww:property value="lxmc"/></option>
					  	
					  </ww:iterator>
					  </select>	
					  <script type="text/javascript">
					  
					  	document.getElementById("hylxid").value='<ww:property value="mettingbean.hylxid"/>';
					  </script>
					  				</td>
			</tr>
			<tr class="trf">
				<td align="right" class="tdl" width="14%">是否开始：</td>
				<td colspan="3" class="tdv" >
								<select name="isopen" id="isopen" disabled="disabled">
					 			 <option value="否">否</option>
					 <option value="是">是</option>
					  </select>	
					  <script type="text/javascript">
					  	document.getElementById("isopen").value='<ww:property value="mettingbean.isopen"/>';
					  </script>
					  				</td>
			</tr>
			
			</ww:if>
				
				<ww:if test="mettingbean.blstatus!=2&&liststatus>3">
					<ww:if test="adddirection==3">
			<tr>
					 <td class="tdv" colspan="3" style="padding-left:300px">&nbsp;&nbsp;<input type="button" id="bjbtn" value="办结" onclick="return mettingbj()"/></td> 
					
				</tr>
				</ww:if>	
				</ww:if>
		</table>
	
	<!-- 意见输入 -->
</form>
</center>
</BODY>
</HTML>