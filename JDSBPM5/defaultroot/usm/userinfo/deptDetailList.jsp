<%@ page language="java"   pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="ww"%>
 
<%

String path = request.getContextPath()+"/";

int index=1;



%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<style>
.STYLE1 {
	font-family: "宋体";
	font-size: 36px;
	font-weight: bold;
}
.xs_left_top{
	font-size:14px;
	text-align: center;
	vertical-align: middle;
	border-top-width: 1px;
	border-right-width: 0px;
	border-bottom-width: 0px;
	border-left-width: 1px;
	border-style: solid;
	border-color: #000000;
}
.xs_left_top_right{
	font-size:14px;
	text-align: center;
	vertical-align: middle;
	border-top-width: 1px;
	border-right-width: 1px;
	border-bottom-width: 0px;
	border-left-width: 1px;
	border-style: solid;
	border-color: #000000;
}
.xs_left_top_bottom{
	font-size:14px;
	text-align: center;
	vertical-align: middle;
	border-top-width: 1px;
	border-right-width: 0px;
	border-bottom-width: 1px;
	border-left-width: 1px;
	border-style: solid;
	border-color: #000000;
}
.xs_all{
	font-size:14px;
	text-align: left;
	vertical-align: middle;
	border-width: 1px;
	border-style: solid;
	border-color: #000000;
}
</style>
		
<script type="text/javascript">
function ow(owurl){ 
var tmp=window.open("about:blank","","fullscreen=1")
var widthh = 850;
tmp.resizeTo(widthh,screen.height-200);
tmp.focus();
tmp.location=owurl;
}
function saveExcel(){
	document.getElementById('report').submit();
}
</script>
</head>

<body>
<h1 align="center"><span class="STYLE1">提案查询统计结果</span></h1>
<form id="report" action="jsp2excel.do" method="post">

<ww:iterator value="processList" status="rows">
<input type="hidden" name="pnumber" id="pnumber" value="<ww:property value="number" />" />
<input type="hidden" name="pname" id="pname" value="<ww:property value="name" />" />
<input type="hidden" name="ptitle" id="ptitle" value="<ww:property value="title" />" />
<input type="hidden" name="psort" id="psort" value="<ww:property value="sort" />" />
<input type="hidden" name="pdept" id="pdept" value="<ww:property value="dept" />" />
<input type="hidden" name="pcondition" id="pcondition" value="<ww:property value="condition" />" />
<input type="hidden" name="popinion" id="popinion" value="<ww:property value="opinion" />" />
</ww:iterator>

<table width="750"  align="center" border="0" cellpadding="0" cellspacing="0">
  <!--DWLayoutTable-->
  <tr>
    <td width="44" height="35" valign="top" class="xs_left_top">序号</td>
    <td width="69" valign="top" class="xs_left_top">案号</td>
    <td width="88" valign="top" class="xs_left_top">提案人</td>
    <td width="155" valign="top" class="xs_left_top">案由</td>
    <td width="92" valign="top" class="xs_left_top">办理类型</td>
    <td width="119" valign="top" class="xs_left_top">承办单位</td>
    <td width="94" valign="top" class="xs_left_top">办理情况</td>
    <td width="89" valign="top" class="xs_left_top_right">委员意见</td>
  </tr>
  <ww:iterator value="processList" status="rows">
  <tr>
    <td height="35" valign="top" class="xs_left_top"><%=index++ %></td>
    <td valign="top" class="xs_left_top"><ww:if test="number != null "><ww:property value="number"/></ww:if><ww:else>未填写</ww:else></td>
    <td valign="top" class="xs_left_top"><ww:if test="name != null "><ww:property value="name"/></ww:if><ww:else>未填写</ww:else></td>
    <td valign="top" class="xs_left_top"><ww:if test="title != null "><a href="javascript:openWin('<%=path %>demodisplay.action?activityInstId=<ww:property value="processId"/>')"><ww:property value="title"/></a></ww:if><ww:else>未填写</ww:else></td>
    <td valign="top" class="xs_left_top"><ww:if test="sort != null "><ww:property value="sort"/></ww:if><ww:else>未填写</ww:else></td>
    <td valign="top" class="xs_left_top"><ww:if test="dept != null "><ww:property value="dept"/></ww:if><ww:else>未填写</ww:else></td>
    <td valign="top" class="xs_left_top"><ww:if test="condition != null "><ww:property value="condition"/></ww:if><ww:else>未填写</ww:else></td>
    <td valign="top" class="xs_left_top_right"><ww:if test="opinion != null "><ww:property value="opinion"/></ww:if><ww:else>未填写</ww:else></td>    
  </tr>
  </ww:iterator>
  <tr>
    <td height="35" valign="top" class="xs_left_top_bottom"><!--DWLayoutEmptyCell-->&nbsp;</td>
    <td valign="top" class="xs_left_top_bottom"><!--DWLayoutEmptyCell-->&nbsp;</td>
    <td valign="top" class="xs_left_top_bottom"><!--DWLayoutEmptyCell-->&nbsp;</td>
    <td valign="top" class="xs_left_top_bottom"><!--DWLayoutEmptyCell-->&nbsp;</td>
    <td valign="top" class="xs_left_top_bottom"><!--DWLayoutEmptyCell-->&nbsp;</td>
    <td valign="top" class="xs_left_top_bottom"><!--DWLayoutEmptyCell-->&nbsp;</td>
    <td valign="top" class="xs_left_top_bottom"><!--DWLayoutEmptyCell-->&nbsp;</td>
    <td valign="top" class="xs_all"><!--DWLayoutEmptyCell-->&nbsp;</td>
  </tr>
</table>
<center>
<a href="javascript:saveExcel()" >导出Excel</a>
</center>
</form>
</body>
</html>
