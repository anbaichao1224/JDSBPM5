<%@include file="../inc/App_Variable.jsp"%>
BODY
{
	font-family: Verdana;
	cursor:default;
	font-size: 12px;
}

.deeptree
{
	padding:4px 4px;
	behavior:url(<%=SYS_BASE_PATH%>treeview/shared/htc/treeview.htc);
}

span
{
	padding:3px 3px 1px 3px;
	position:relative;
	display:inline; 
	top:-1px;
	height:16px;
	border: solid 1px #f1f1f1;
}



span.clsLabel
{

}
SPAN.clsSpace
{
	font-family:verdana;
	position:relative;
	padding:3px 2px;
	top:0px;
	width:17px;
	margin:0px;
	cursor:hand;
	overflow:hidden;
}

span.clsSpace span
{
	position:relative;
	width:11px;
	height:11px;
	border:solid 1px black;
	background-color:#ffffff;
}

SPAN.clsCollapse
{
	line-height:6px;
	font-size:9px;
	overflow:hidden;
	padding:1px;
}

SPAN.clsExpand
{
	padding-left:1px;
	overflow:hidden;
	line-height:3px;
	font-size:12px;
	padding-top:3px;
}

SPAN.clsLeaf
{
	overflow:visible;
	font-size:9px;
	line-height:3px;
	padding: 1px 0px 0px 3px;
}

SPAN.clsMouseOver
{
	background-color:#CCCCCC;
	border:1px solid #999999;
}

SPAN.clsMouseDown
{
	background-color:#999999;
	border:1px solid #999999;
}

SPAN.clsCurrentHasFocus
{
	background-color:#FFFFFF;
	border:1px solid #999999;
}

SPAN.clsCurrentNoFocus
{
	background-color:#F1F1F1;
	border:1px solid #999999;
}


A
{
	color:black;
	text-decoration:none;
	
}

span.clsUnavailable
{
	height:0px;
	padding:0px;
	top:0px;
	border:none;
	color:#888888;
}

.hide
{
	display:none;
}

.shown
{
	display:block;
	margin-left:15px;
}

.deeptree IMG
{
	position:relative;
	cursor:hand;
	top:-2px;
	margin:0px;
	padding:0px;
}

.treelabel
{
	font-family: verdana;
	font-size:12px;
	color:white;
}
