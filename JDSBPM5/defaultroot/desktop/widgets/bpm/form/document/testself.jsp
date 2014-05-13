<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.Map,com.opensymphony.xwork2.util.OgnlValueStack,net.itjds.common.CommonConfig;"%>
<%@ taglib uri="/struts-tags" prefix="ww"%>

function EVAL.getPanel(){

 var panel={
		title:"ÕýÎÄ",
		//width:740,
		//height:600,
		//layout:'fit',
			manager: getCurDesktop().getManager(),
                minimizable: true,
                maximizable: true,
		
		html:'<iframe id="framedoc" name="framedoc" src="<%=request.getContextPath() %>/selfopenAction.action?yiban=<ww:property value="yiban"/>&taoHongId=<ww:property value="taoHongId"/>&filenum=<ww:property value="filenum"/>&activityInstHistoryId=<ww:property value="activityInstHistoryId"/>&docuuid=<ww:property value="docuuid"/>&formID=<ww:property value="formID"/>&activityInstId=<ww:property value="activityInstId"/>&docid=<ww:property value="docid"/>" style="width:100%;height:100%"></iframe>'
		};
		
	return panel;										
 }

 						
													