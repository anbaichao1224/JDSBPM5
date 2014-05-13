<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="ww"%>
<%@ page import="java.util.Map,com.opensymphony.xwork2.util.OgnlValueStack,com.opensymphony.xwork2.ActionContext,net.itjds.fdt.define.designer.metadata.bean.DocumentBean,net.itjds.j2ee.util.UUID"%>
<%
	String path = request.getContextPath();
String basePath = request.getScheme() + "://"
+ request.getServerName() + ":" + request.getServerPort()
+ path+"/";
	DocumentBean docbean = (DocumentBean) ActionContext.getContext().getValueStack().findValue("$docInject.getDocBean()");
	String updatedoc = docbean.getDocbasic().getOpen(); 
	String uid = new UUID().toString();
%>
<script type="text/javascript">
	
</script>
	<ww:if test="disname == null ||  disname">
	<ww:if test="cnname != null && cnname != 'null'">
				<ww:property value="cnname" />
		</ww:if>
	</ww:if>
	<div>

	<ww:property value="filename" />
<table>
	<tr>
	  <td valign="top">	
		<ww:if test="filenum<=0">
		<iframe id="iframeup"  width="600" marginheight="1 1" scrolling="no" frameborder=0 src="desktop/widgets/bpm/form/document/updocument.jsp?processInstId='<ww:property value="processInst.processInstId"/>'&activityInstId='<ww:property value="activityInstId"/>'&formId='<ww:property value="formID"/>'"></iframe>
		</ww:if>
		<ww:if test="filenum>0">
			<iframe style="display:none" id="iframeup"  width="470" scrolling="no" frameborder=0 height="44" src="desktop/widgets/bpm/form/document/updocument.jsp?processInstId='<ww:property value="processInst.processInstId"/>'&activityInstId='<ww:property value="activityInstId"/>'"></iframe>
		</ww:if>
	  </td>		
		<!-- 2011-11-12 用于查看已办理隐藏上传 卜更改开始  -->
		 <script>
		 var upyibanli = Ext.get("yibanli"+'<ww:property value="activityInstId"/>').dom.value;
		 if(upyibanli == 'y')
		 {
				document.getElementById("iframeup").style.display="none";
		 }
		 </script>
		 <input type="hidden" name="docid" value='<ww:property value="docid"/>'/> 
		 <!-- 2011-11-12 卜更改结束 -->
		<td valign="top">	
		<ww:if test="disicon == null || disicon">
			<ww:if test="filenum > 0">
				<ww:if test="iconD != null && iconD != 'null'">
					<img align="center" id='docimg' src='<%=path%><ww:property value="iconD"/>'
				</ww:if>
				<ww:else>
					<ww:iterator value="dpnList" id="dpn" status="st">
						<a href=javascript:openFormWin('<%=path%>/selfdocumentAction.action','','','activityInstHistoryId=<ww:property value="activityInstHistoryId"/>&activityInstId=<ww:property value="activityInstId"/>&docid=<ww:property value="docuuid"/>&formID=<ww:property value="formID"/>','<ww:property value="filenum"/>','<%=updatedoc%>','<ww:property value="activityInstId"/>','<ww:property value="activityInstHistoryId"/>');>
						<ww:property value="displayName" /></a><br>
					</ww:iterator>	
				</ww:else>
			</ww:if>

			<ww:else>
				<ww:if test="iconN != null && iconN != 'null'">
					<img align="center" id='docimg' src='<%=path%><ww:property value="iconN"/>'
				</ww:if>
				<ww:else>
						<a href=javascript:openFormWin('<%=path%>/selfdocumentAction.action','','','activityInstHistoryId=<ww:property value="activityInstHistoryId"/>&activityInstId=<ww:property value="activityInstId"/>&docid=<%=uid%>&formID=<ww:property value="formID"/>','<ww:property value="filenum"/>','<%=updatedoc%>','<ww:property value="activityInstId"/>','<ww:property value="activityInstHistoryId"/>');>
						<img align="center" id='docimg1' alt='<www:property value="formID"/>'
							src='<%=path%>/desktop/widgets/bpm/form/document/img/new.jpg'
							title=''</img></a>
				</ww:else>
			</ww:else>
			<ww:if test="iconW != null && iconW != ''"> 
				width="<ww:property value="iconW" />" 
			</ww:if>

			<ww:if test="iconH != null && iconH != ''"> 
				height="<ww:property value="iconH" />"
			</ww:if>
		</ww:if>
		</td>
	</tr>
</table>
		<ww:if test="fjnum > 0">
			<ww:action name="zwfileinclude" executeResult="true"></ww:action>
		</ww:if>

<ww:if test="taoHong==true">
		<input type="button" value="pdf套头" onclick="viewDocPdftH('<ww:property value="docid"/>','<%=basePath %>')"/>
		</ww:if>
		<ww:if test="disabled=='true'">
		<img align="center" id='docimgsdf'  onclick="opendocth('<ww:property value="docid"/>','<%=basePath %>');" alt='<www:property value="formID"/>'
						src='<%=path%>/desktop/widgets/bpm/form/document/img/newtaohong.jpg'>
		<!-- <input type="button" align="top" value="查看成文文件" onclick="opendocth('<ww:property value="docid"/>','<%=basePath %>');"/> -->
		
	    </ww:if>
	

</div>