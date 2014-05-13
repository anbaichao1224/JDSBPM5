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
	function create(){
		eval("self.location='<%=request.getContextPath() %>/pages/gzrz/gzrzCreate.jsp'");
	}
	function checksel(){
		var isChecked = false;
		if(document.form.selectId==null)
			return false;
		if(document.form.selectId.checked){
			isChecked = true;
		}else{
			for(var i = 0;i<document.form.selectId.length;i++){
				if(document.form.selectId[i].checked){
					isChecked = true;
					break;
				}
			}
		}
		return isChecked;
	}				
 	function deletes(){
		if(checksel()){
			if(confirm("确定要删除所选择的条目吗？"))
			{
				document.form.action = "<%=request.getContextPath() %>/GrbgGzrz_delete.action";
				document.form.submit();
			}
		}else{
			alert("请选择要删除的条目！");
		}
	} 
	function list(){
			document.form.action = "<%=request.getContextPath() %>/GrbgGzrz_list.action";
			document.form.submit();
	}
	
	function exportExcel(){
		var queryForm = $('queryForm');
		queryForm.action = "${pageContext.request.contextPath}/GrbgGzrz_exportXLS.action";
		queryForm.submit();
		  
	}
</script>
</head>
  
<body style="overflow:scroll;overflow-x:hidden">
<div class="divBodyContainer"  >
	<s:action  name="dictionary" id="dictionary" namespace="/dict"  executeResult="false" />
    <form id="queryForm" name="form" method="post" action="<%=request.getContextPath() %>/HdapHdap_findAll.action">
	<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" borderColorDark="#ffffff" borderColorLight="#777777">
			<tr>
					<td class="path"> 当前位置：个人办公 >> 工作日志</td>
			</tr>
	</table>
	<table width="100%" border="1" align="center" cellpadding="0" cellspacing="0" borderColorDark="#ffffff" borderColorLight="#999999">
	<tr class="trf">
		<td colspan="3" class="tdb">
			日期<input type="text" readonly name="fromDate" size="15" id="fromDate" value="<fmt:formatDate value="${fromDate}" pattern="yyyy-MM-dd HH:mm"/>" />
			<button pattern="minute" target="fromDate"  class="button">选择</button>
			到<input type="text" readonly name="toDate" size="15" id="toDate" value="<fmt:formatDate value="${toDate}" pattern="yyyy-MM-dd HH:mm"/>" />
			<button pattern="minute" target="toDate" class="button">选择</button>
			日志类型 <input type="text" name="rzType" size="8" value="${rzType }"/>
		      日志内容<input type="text" name="content" size="8" value="${content }"/></td>
		<td colspan="3" class="tdb" align="right">
			<input type="button" class="button" value="查询" onClick="list();"/>
			<input type="button" class="button" value="新建" onClick="create();">
			<input type="button" class="button" value="删除" onClick="deletes();">
			<input type="button" class="button" value="导出" onClick="exportExcel()" />
		</td>
	</tr>
	</table>
	<table width="100%" border="1" align="center" cellpadding="0" cellspacing="0" borderColorDark="#ffffff" borderColorLight="#999999">
	<input type="hidden" name="pageNo" value="${pageNo}"/>
		<tr class="trlist">
			<td width="5%" class="tdlist"><input type=button value='全选' onclick='s(this,this.form)' name='cc' /></td>
			<td width="15%" class="tdlist">起始时间</td>
			<td width="15%" class="tdlist">截止时间</td>
			<td width="15%" class="tdlist">日志类型</td>
			<td width="50%" class="tdlist">工作内容</td>
		</tr>
		<s:iterator id="pageObj" value="pageObj.pageElements" status="stat">
		
			<tr class=fandian_one onMouseOver="this.className='f_two'" onmouseout="this.className='f_one'">
				<td align="center">
					<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" borderColorDark="#ffffff" borderColorLight="#999999">
						<tr>
							<td width="50%" align="left">
								<input type="checkbox" name="selectId" value="<s:property value="rzId"/>">
							</td>
							<td width="50%" height="26" align="left">${(pageNo-1)*20+(stat.index + 1) }</td>
						</tr>
					</table>
				</td>
				<td align="center"><fmt:formatDate value="${dateFrom }" pattern="yyyy-MM-dd HH:mm"/>&nbsp;</td>
				<td align="center"><fmt:formatDate value="${dateTo }" pattern="yyyy-MM-dd HH:mm"/>&nbsp;</td>
				<td align="center">${rzType } &nbsp;</td>
				<s:url action="/GrbgGzrz_update.action" id="urlId">
					<s:param name="rzId" value="rzId" ></s:param>
				</s:url>
				<td title="${rzContent}"><s:a href="%{urlId}"  >${oa:subString(rzContent, 28)}</s:a> &nbsp;</td>
			</tr>
		</s:iterator>
	</table>
	<jsp:include page="/desktop/widgets/schedule/pager.jsp" flush="false"/>
	</form>
	<script language="JavaScript" src="<%=request.getContextPath() %>/js/calendar.js"></script>	
	
</div>
</body>
</html>
