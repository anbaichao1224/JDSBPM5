<%@ page  contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/desktop/widgets/schedule/taglibs.jsp"%>

 <%
  response.setHeader("Cache-Control","no-cache");
  response.setHeader("Pragma","no-cache");
  response.setDateHeader("Expires",0);
%>



 <STYLE   type=text/css>
  A:link   { COLOR:   blue;   FONT-FAMILY:   "宋体";   TEXT-DECORATION:   none}
  A:visited   { COLOR:   blue;   FONT-FAMILY:   "宋体";   TEXT-DECORATION:   none}
  A:active   { COLOR:   blue;   FONT-FAMILY:   "宋体";   TEXT-DECORATION:   none}
  </STYLE>

<c:set var="skincss" value="${sessionScope.currentUser.skincss}" scope="page"/>
<c:set var="contextPath" value="${pageContext.request.contextPath}"  scope="page"  />
<c:set var="fullContextPath" value="http://${pageContext.request.localAddr}:${pageContext.request.localPort}${contextPath}"  scope="page"  />

<c:set var="currentStylePath" value="${contextPath}/themes/styles/${skincss}" scope="page" />
<c:set var="currentSkinCss" value="${currentStylePath}/${skincss}menu.css" scope="page" />
<c:if test="${ not empty param.singleParamValue }">
   <c:set var="singleParamValues" value="${fn:split(param.singleParamValue,'#')}" />
   <c:set var="singleValues" value="${fn:split(singleParamValues[0],',')}" />
   <c:set var="paramStringValue" value="${singleValues[2]}" />
</c:if>

<jsp:useBean id="currentDate" scope="page" class="java.util.Date" />


<fmt:formatDate var="dateFormat1"
				value="${currentDate}"
				pattern="yyyy-MM-dd HH:mm" scope="page"
/>

 <!-- HTTP 1.1 -->
<meta http-equiv="Cache-Control" content="no-store"/>
<!-- HTTP 1.0 -->
<meta http-equiv="Pragma" content="no-cache"/>
<!-- Prevents caching at the Proxy Server -->
<meta http-equiv="Expires" content="0"/>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>


<script type="text/javascript" src="${contextPath}/js/gongwen/common.js"></script>
<script type="text/javascript" src="${contextPath}/js/prototype.js"></script>



<script type="text/javascript">
		var contextPath = "${contextPath}";
		var fullContextPath = "http://${pageContext.request.localAddr}:${pageContext.request.localPort}";
	function delCol() {
		document.all.fujianTable.deleteRow();
	}

</script>




<link href="css/extremecomponents.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" type="text/javascript" src="${pageContext.request.contextPath}/js/date-picker.js"></script>
<link rel="stylesheet" href="<%=request.getContextPath() %>/syspage.css" type="text/css" />
<script language="JavaScript" src="<%=request.getContextPath() %>/js/tools.js"></script>
<script language="JavaScript" src="<%=request.getContextPath() %>/js/op.js"></script>

<!-- 引入有次序问题，提醒注意 -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/selectOrg.js" ></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/Mask.js" ></script>
<script language="JavaScript" src="${contextPath}/js/gongwen/gwgl.js" > </script>

<!-- 引入有次序问题，提醒注意 -->

<!-- 统一校验 -->
<script src="${pageContext.request.contextPath}/js/validation/effects.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/validation/validation_cn.js" type="text/javascript" ></script>
<link  href="${pageContext.request.contextPath}/js/validation/validationstyle.css" rel="stylesheet" type="text/css"/>



<script type='text/javascript' src='${pageContext.request.contextPath}/dwr/interface/ideaManager.js'></script>
<script type='text/javascript' src='${pageContext.request.contextPath}/dwr/engine.js'></script>
<script type='text/javascript' src='${pageContext.request.contextPath}/dwr/util.js'></script>

<script src="${contextPath}/dhtmlxToolbar/dhtmlxcommon.js"></script>
<script src="${contextPath}/dhtmlxToolbar/dhtmlxtoolbar.js"></script>
<link rel="stylesheet" type="text/css" href="${contextPath}/dhtmlxToolbar/skins/dhtmlxtoolbar_dhx_skyblue.css"></link>


