<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="ww"%>
<script type="text/javascript">	
sendNSCommand("ognl","logon('<ww:property value="subServerUrl" escape="false"/>/sublogin.action?personId=<ww:property value="person.ID" />&version=<ww:property value="version==null?'':version" />&clientUrl=<ww:property value="clientUrl==null?'':clientUrl" />&logid="+new Date()+"')");
</script>
