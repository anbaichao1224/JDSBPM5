<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="ww"%>
{folder :[ 
	<ww:iterator value="folderList" status="row">
	{ 
	 iconCls			:'<ww:property value="node.entityType" escape="false" />',
	 id					:'<ww:property value="key" escape="false"/>',
	 DateLastModified	:'<ww:property value="modiftime" escape="false"/>' ,
	 name				:'<ww:property value="name" />',
	 path				:'<ww:property value="key" escape="false"/>',
	 pid				:'<ww:property value="pid" escape="false"/>',
	 type				:'',
	 menu				:'<ww:property value="node.menu"/>',
	 allowDrop			:<ww:property value="node.allowDrag"/>,
	 fileORfolder		:'<ww:property value="fileORfolder" escape="false"/>',
	 isDelete	:<ww:property value="node.delete"/>,
	 group				:'<ww:property value="node.group"/>',
	 <ww:if test="node.url != null">
	  url		:'<ww:property value="node.url" />',
	 </ww:if>
	 <ww:if test="parentId != null">
	  parentId		:'<ww:property value="parentId" />',
	 </ww:if>
	 <ww:if test="node.uuid != null">
	  uuid		:'<ww:property value="node.uuid" />',
	 </ww:if>
	<ww:if test="node.icon != null">
	 icon		:'<ww:property value="node.icon" />',
	 </ww:if>
	<ww:if test="node.thumb != null">
	 thumb		:'<ww:property value="node.thumb" />',
	 </ww:if>
	 size:'<ww:property value="size" />'
	 }
	 <ww:if test="#row.count < folderList.size">
	 ,
	 </ww:if>
	</ww:iterator>
]}