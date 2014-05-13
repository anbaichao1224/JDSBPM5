<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="/struts-tags" prefix="ww"%>
<%@page import="net.itjds.j2ee.util.UUID" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<title>机构树</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">

		<link rel="stylesheet"
			href="<c:url value='/css/zTreeStyle/zTreeStyle.css'/>"
			type="text/css"></link>
		<script type="text/javascript"
			src="<c:url value='/js/ztree/jquery-1.4.4.min.js'/>">
		</script>
		<script type="text/javascript"
			src="<c:url value='/js/ztree/jquery.ztree.core-3.3.js'/>">
		</script>
		<script type="text/javascript"
			src="<c:url value='/js/ztree/jquery.ztree.excheck-3.3.js'/>">
		</script>

		<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
		<script type="text/javascript">

function setChecked(){
	
	var ids = '<ww:property value="checked"/>';
	if(ids == null || ids.length<1)
		return;
	var zTree = $.fn.zTree.getZTreeObj("treeDemo");
	var idarray = ids.split(',');
	for (var i=0; i<idarray.length;i++){
		var node = zTree.getNodeByParam("id", parseInt(idarray[i]));
		if(node != null){
			zTree.checkNode(node, true, true, true);
			node.checkedOld = true;
		}	
	}	
}

function setDisableChecked(){
	
	var ids = '<ww:property value="disabled"/>';
	//console.log('disabled : ' + ids);
	if(ids == null || ids.length<1)
		return;
	var zTree = $.fn.zTree.getZTreeObj("treeDemo");
	var idarray = ids.split(',');
	for (var i=0; i<idarray.length;i++){
		var node = zTree.getNodeByParam("id", parseInt(idarray[i]));
		if(node != null){
			zTree.setChkDisabled(node, true);
		}	
	}	
}

function resetChecked(){
	var zTree = $.fn.zTree.getZTreeObj("treeDemo");
	var nodes = zTree.getChangeCheckedNodes();
	for(var i=0; i<nodes.length; i++){
		var node = nodes[i];
		if(node.isParent)
			continue;
		zTree.checkNode(node, node.checkedOld, true,true);
	}
	
}
		
function getCheckedNames(){
	var zTree = $.fn.zTree.getZTreeObj("treeDemo");
	var names = '';
	var ids = '';
	var nodes = zTree.getCheckedNodes(true);
	for(var i=0; i<nodes.length; i++){
		if(nodes[i].isParent)
			continue;
		names +=nodes[i].name + ',';
		ids += nodes[i].id + ',';
	}
	names = names.substring(0, names.length-1);
	ids = ids.substring(0, ids.length-1);
	var retValue = {names : names, ids : ids};
	//console.log(names);
	//console.log(ids);
	return retValue;

}

$(document).ready(function(){

	//console.log("start download data");
	var treedata;
	var resp = $.ajax( {
		url : 'lcTzWjzlkAction_findCatalogByPersonId.action',
		type : 'post',
		async : true,
		dataType : 'json',
		timeout : 1000,
		
		error : function() {
			//console.log('Error loading jso document');
		},
		success : function(json) {
		
			// do something with xml
			var setting = {
				
				check: {
					enable: true,
					chkStyle: "radio", 
		            radioType: "all"
				}
			};
		
			$.fn.zTree.init($("#treeDemo"), setting, json);	
			var zTree = $.fn.zTree.getZTreeObj("treeDemo");	
			zTree.expandAll(true);	
			setChecked();	
			setDisableChecked();
			
		}
	});
});

</script>

	</head>

	<body>
		<div id="orgtree">
			<ul id="treeDemo" class="ztree"></ul>
		</div>
		<input type="hidden" name="orgnames" , id="orgnames" />
		<input type="hidden" name="orgids" id="orgids" />
	</body>
</html>
