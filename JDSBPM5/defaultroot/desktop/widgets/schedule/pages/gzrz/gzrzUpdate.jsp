<%@ page language="java" contentType="text/html;charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/desktop/widgets/schedule/taglibs.jsp"%> 
<%@ taglib prefix="s" uri="/struts-tags" %>    
<html>
<head>
<title>列表</title>
<%@include file="/desktop/widgets/schedule/link_script_meta.jsp" %>
<link rel="stylesheet" href="<%=request.getContextPath() %>/syspage.css" type="text/css" />
<script language="JavaScript" src="<%=request.getContextPath() %>/js/isSelectAll.js"></script>
<script language="JavaScript">
	//校验textarea
	function textCounter(field,maxlimit) {
		if (field.value.length > maxlimit) 
		// if too long...trim it!
		field.value = field.value.substring(0, maxlimit);
	}
	
	function save(){
			//统一校验开始
			var result=valid.validate();
			if(result){
				document.form.action = "<%=request.getContextPath() %>/GrbgGzrz_save.action";
				if(confirm("您确定要保存吗？")){
					document.form.submit();				
				}
			}else{
				alert("请填写页面必填项信息。");
			}
	}
</script>
</head>
  
<body style="overflow:scroll;overflow-x:hidden">
<div class="divBodyContainer"  >


<form name="form" id="form"  method="post" action="${contextPath}/GrbgGzrz_save.action"  enctype="multipart/form-data" >
	<input type="hidden" name="grbgGzrz.creatorid" value="<s:property value="grbgGzrz.creatorid"/>" >
	<input type="hidden" name="grbgGzrz.rzId" value="<s:property value="grbgGzrz.rzId"/>" >
	<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" pborderColorDark="#ffffff" borderColorLight="#777777">
			<tr>
					<td class="path"> 当前位置：个人办公 >> 工作日志 >> 更新</td>
			</tr>
	</table>
	<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" borderColorDark="#ffffff" borderColorLight="#999999">
		<tr class="trf">
			<td colspan="4" class="tdb" align="right"><input type="button" class="button" value="保存" onclick="save();"><input type="reset" class="button" value="重置"><input type="button" value="返回" class="button" onclick="history.back();"></td>
		</tr>
	</table>
	
	<table width="100%" border="1" align="center" cellpadding="0" cellspacing="0" borderColorDark="#ffffff" borderColorLight="#999999">
		<tr class="header">
			<td colspan="4" class="tdh">工作日志</td>
		</tr>
		<tr class="trf">
			<td width="18%" class="tdl">开始时间</td>
			<td width="32%" class="tdv"><input type="text" readonly name="grbgGzrz.dateFrom" id="dateFrom" title="开始时间" class="required date-lt-dateTo" value="<fmt:formatDate value="${grbgGzrz.dateFrom}" pattern="yyyy-MM-dd HH:mm"/>"><button pattern="minute" target="dateFrom">选择</button>
			<font color="red">*</font></td>	
			<td width="18%" class="tdl">截止时间</td>
			<td width="32%" class="tdv"><input type="text" readonly name="grbgGzrz.dateTo" id="dateTo" title="截止时间" class="required date-gt-dateFrom" value="<fmt:formatDate value="${grbgGzrz.dateTo}" pattern="yyyy-MM-dd HH:mm"/>" ><button pattern="minute" target="dateTo">选择</button>
			<font color="red">*</font></td>	
		</tr>
		<tr class="trf">
			<td width="18%" class="tdl">日志类型</td>
			<td colspan="3" class="tdv"><input type="text" name="grbgGzrz.rzType" class="required" style="width:80%" value="<s:property value="grbgGzrz.rzType"/>" maxlength="32"/><font color="red">*</font></td>
		</tr>
		<tr class="trf">
			<td align="center" class="tdl" width="14%">工作内容</td>
			<td class="tdv" width="36%" colspan="3">	
				<textarea name="grbgGzrz.rzContent" rows="12" id="grbgGzrz.rzContent" style="width:90%" class="required"  onkeydown="textCounter(this,480);" onkeyup="textCounter(this,480);" ><s:property value="grbgGzrz.rzContent"/></textarea><font color="red">*</font>
			</td>	
		</tr>
	</table>
	
</form>
<script language="JavaScript" src="<%=request.getContextPath() %>/js/calendar.js"></script>
<script type="text/javascript">
	function formCallback(result, form) {
		window.status = "valiation callback for form '" + form.id + "': result = " + result;
	}
	var valid = new Validation('form', {immediate : true, onFormValidate : formCallback});
</script>
</div>
</body>
</html>
