
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="ww"%>

<html>
<style>
.top{
	text-align:left;
	vertical-align: middle;
	border-top-width: 2px;
	border-top-style: solid;
	border-top-color: #000000;
}
.middle{
	text-align:left;
	vertical-align: middle;
	border-top-width: 1px;
	border-top-style: solid;
	border-top-color: #000000;
}
.bottom_left{
	text-align:left;
	border-top-width: 2px;
	border-top-style: solid;
	vertical-align: middle;
	border-top-color: #000000;
}
.bottom_right{
	text-align:left;
	border-top-width: 2px;
	border-top-style: solid;
	vertical-align: middle;
	border-top-color: #000000;
}
.context{
	text-align:center;
	font-family: "宋体";
	font-size: medium;

}
.context_b{
	text-align:left;
	font-family: "宋体";
	font-size: medium;
	font-weight: bold;

}
.title{
	text-align:center;
	vertical-align: middle;
	font-family: "宋体";
	font-size: 36px;
	font-weight: bold;

}
</style>

没有发现该流程或活动指定的表单请先添加表单<br>


<input type=button onclick="winPop('/explorer/main.action?username=haodongjin')" value="添加表单"></input>


</html>
