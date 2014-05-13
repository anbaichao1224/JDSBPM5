<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="ww"%>
folder = [
<ww:iterator value="folderList" status="row">
	{ 
	 text		:'<ww:property value="name" />',
	 id			:'<ww:property value="key" />',
	 modifdate	:'<ww:property value="node.modiftime" />' ,
	 allowDrag 	:<ww:property value="node.allowDrag" /> , 
	 allowDrop 	:<ww:property value="node.allowDrop" /> , 
	 menu		:'<ww:property value="node.menu"/>',
	 icon		:'<ww:property value="node.icon" />',
	 iconCls	:'iconNode',
	 isDelete	:<ww:property value="node.delete"/>,
	 group		:'<ww:property value="node.group"/>',
	 <ww:if test="node.url != null">
	  url		:'<ww:property value="node.url" />',
	 </ww:if>
	 <ww:if test="parentId != null">
	  parentId		:'<ww:property value="parentId" />',
	 </ww:if>
	 <ww:if test="node.uuid != null">
	  uuid		:'<ww:property value="node.uuid" />',
	 </ww:if>
	<ww:if test="node.thumb != null">
	 thumb		:'<ww:property value="node.thumb" />',
	 </ww:if>
	 leaf:false
	 }
	 <ww:if test="#row.count < folderList.size">
	 ,
	 </ww:if>
</ww:iterator>
]
