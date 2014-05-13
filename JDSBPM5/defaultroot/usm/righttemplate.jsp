<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%
String contextpath = request.getContextPath() + "/";
%>
<html>
	<head>
		<title>Grid</title>
		<link rel="stylesheet" type="text/css"
			href="<%=contextpath%>js/ext/resources/css/ext-all.css" />
		<script src="<%=contextpath%>js/ext/adapter/ext/ext-base.js"></script>
		<script src='<%=contextpath%>js/ext/ext-all.js'></script>
		<script type="text/javascript" src="/usm/js/righttemplate.js"
			defer="defer"></script>
		<style type="text/css">
.search {
	background-image: url(usm/images/plugin.gif) !important;
}

.remove {
	background-image: url(usm/images/delete.gif) !important;
}

.edit {
	background-image: url(usm/images/edit.gif) !important;
}

.add {
	background-image: url(usm/images/add.gif) !important;
}

.preview {
	background-image: url(usm/images/preview.png) !important;
}
</style>
	</head>

	<body>
		<script type="text/javascript">
       var maxCols = 4;
       var ttemplate = '<s:property value="template"/>';
       var tsysid = '<s:property value="sysid"/>';
       var tnodeid = '<s:property value="nodeid"/>';
 	   var gwjh=[
		<s:iterator id="li" value="gwjh" status="rowstatus">
			 {boxLabel: '<s:property value="#li.Cnname"/>',name: 'txtCheckValue',inputValue: '<s:property value="#li.moduleid"/>'
		        ,checked:<s:property value="#li.checked"/>}
				  <s:if test ="#rowstatus.count < gwjh.size">
			      ,
		     	 </s:if>
		</s:iterator>
 	  ];
 	   var gwjhcnt = '<s:property value="gwjh.size"/>';
      if (gwjhcnt>maxCols){
        gwjhcnt = maxCols;
      }
 	   var gwgl=[
		<s:iterator id="li" value="gwgl" status="rowstatus">
			 {boxLabel: '<s:property value="#li.Cnname"/>',name: 'txtCheckValue',inputValue: '<s:property value="#li.moduleid"/>'
		          ,checked:<s:property value="#li.checked"/>}
				  <s:if test ="#rowstatus.count < gwgl.size">
			      ,
		     	 </s:if>
		</s:iterator>
 	  ];
 	   var gwglcnt = '<s:property value="gwjh.size"/>';
      if (gwglcnt>maxCols){
        gwglcnt = maxCols;
      }
 	   var fwbl=[
		<s:iterator id="li" value="fwbl" status="rowstatus">
			 {boxLabel: '<s:property value="#li.Cnname"/>',name: 'txtCheckValue',inputValue: '<s:property value="#li.moduleid"/>'
		        ,checked:<s:property value="#li.checked"/>}
				  <s:if test ="#rowstatus.count < fwbl.size">
			      ,
		     	 </s:if>
		</s:iterator>
 	  ];
 	   var fwblcnt = '<s:property value="gwjh.size"/>';
      if (fwblcnt>maxCols){
        fwblcnt = maxCols;
      }
 	   var swbl=[
		<s:iterator id="li" value="swbl" status="rowstatus">
			 {boxLabel: '<s:property value="#li.Cnname"/>',name: 'txtCheckValue',inputValue: '<s:property value="#li.moduleid"/>'
		          ,checked:<s:property value="#li.checked"/>}
				  <s:if test ="#rowstatus.count < swbl.size">
			      ,
		     	 </s:if>
		</s:iterator>
 	  ];
 	   var swblcnt = '<s:property value="swbl.size"/>';
      if (swblcnt>maxCols){
        swblcnt = maxCols;
      }
 	   var hqfw=[
		<s:iterator id="li" value="hqfw" status="rowstatus">
			 {boxLabel: '<s:property value="#li.Cnname"/>',name: 'txtCheckValue',inputValue: '<s:property value="#li.moduleid"/>'
		          ,checked:<s:property value="#li.checked"/>}
				  <s:if test ="#rowstatus.count < hqfw.size">
			      ,
		     	 </s:if>
		</s:iterator>
 	  ];
 	   var hqfwcnt = '<s:property value="hqfw.size"/>';
      if (hqfwcnt>maxCols){
        hqfwcnt = maxCols;
      }
 	   var hqsw=[
		<s:iterator id="li" value="hqsw" status="rowstatus">
			 {boxLabel: '<s:property value="#li.Cnname"/>',name: 'txtCheckValue',inputValue: '<s:property value="#li.moduleid"/>'
		          ,checked:<s:property value="#li.checked"/>}
				  <s:if test ="#rowstatus.count < hqsw.size">
			      ,
		     	 </s:if>
		</s:iterator>
 	  ];
 	   var hqswcnt = '<s:property value="hqsw.size"/>';
      if (hqswcnt>maxCols){
        hqswcnt = maxCols;
      }
 	   var swdj=[
		<s:iterator id="li" value="swdj" status="rowstatus">
			 {boxLabel: '<s:property value="#li.Cnname"/>',name: 'txtCheckValue',inputValue: '<s:property value="#li.moduleid"/>'
		          ,checked:<s:property value="#li.checked"/>}
				  <s:if test ="#rowstatus.count < swdj.size">
			      ,
		     	 </s:if>
		</s:iterator>
 	  ];
 	   var swdjcnt = '<s:property value="swdj.size"/>';
      if (swdjcnt>maxCols){
        swdjcnt = maxCols;
      }
 	   var nbwjcs=[
		<s:iterator id="li" value="nbwjcs" status="rowstatus">
			 {boxLabel: '<s:property value="#li.Cnname"/>',name: 'txtCheckValue',inputValue: '<s:property value="#li.moduleid"/>'
		          ,checked:<s:property value="#li.checked"/>}
				  <s:if test ="#rowstatus.count < nbwjcs.size">
			      ,
		     	 </s:if>
		</s:iterator>
 	  ];
 	   var nbwjcscnt = '<s:property value="nbwjcs.size"/>';
      if (nbwjcscnt>maxCols){
        nbwjcscnt = maxCols;
      }
 	   var tzgg=[
		<s:iterator id="li" value="tzgg" status="rowstatus">
			 {boxLabel: '<s:property value="#li.Cnname"/>',name: 'txtCheckValue',inputValue: '<s:property value="#li.moduleid"/>'
		        ,checked:<s:property value="#li.checked"/>}
				  <s:if test ="#rowstatus.count < tzgg.size">
			      ,
		     	 </s:if>
		</s:iterator>
 	  ];
 	   var tzggcnt = '<s:property value="tzgg.size"/>';
      if (tzggcnt>maxCols){
        tzggcnt = maxCols;
      }
 	   var bhgl=[
		<s:iterator id="li" value="bhgl" status="rowstatus">
			 {boxLabel: '<s:property value="#li.Cnname"/>',name: 'txtCheckValue',inputValue: '<s:property value="#li.moduleid"/>'
		         ,checked:<s:property value="#li.checked"/>}
				  <s:if test ="#rowstatus.count < bhgl.size">
			      ,
		     	 </s:if>
		</s:iterator>
 	  ];
 	   var bhglcnt = '<s:property value="bhgl.size"/>';
      if (bhglcnt>maxCols){
        bhglcnt = maxCols;
      }
 	   var wjzlk=[
		<s:iterator id="li" value="wjzlk" status="rowstatus">
			 {boxLabel: '<s:property value="#li.Cnname"/>',name: 'txtCheckValue',inputValue: '<s:property value="#li.moduleid"/>'
		           ,checked:<s:property value="#li.checked"/>}
				  <s:if test ="#rowstatus.count < wjzlk.size">
			      ,
		     	 </s:if>
		</s:iterator>
 	  ];
 	   var wjzlkcnt = '<s:property value="wjzlk.size"/>';
      if (wjzlkcnt>maxCols){
        wjzlkcnt = maxCols;
      }
 	   var gngl=[
		<s:iterator id="li" value="gngl" status="rowstatus">
			 {boxLabel: '<s:property value="#li.Cnname"/>',name: 'txtCheckValue',inputValue: '<s:property value="#li.moduleid"/>'
		         ,checked:<s:property value="#li.checked"/>}
				  <s:if test ="#rowstatus.count < gngl.size">
			      ,
		     	 </s:if>
		</s:iterator>
 	  ];
 	
 	   var gnglcnt = '<s:property value="gngl.size"/>';
      if (gnglcnt>maxCols){
        gnglcnt = maxCols;
      }
       var rlgl=[
		<s:iterator id="li" value="publiclist" status="rowstatus">
			 {boxLabel: '<s:property value="#li.Cnname"/>',name: 'jiben',inputValue: '<s:property value="#li.Cnname"/>'
		         ,checked:true}
				  <s:if test ="#rowstatus.count < publiclist.size">
			      ,
		     	 </s:if>
		</s:iterator>
 	  ];
 	
 	   var rlglcnt = '<s:property value="publiclist.size"/>';
      if (rlglcnt>maxCols){
        rlglcnt = maxCols;
      }
      var str=[
	    <s:if test ="gwjh.size>0">
      {xtype: 'checkboxgroup',columns:gwjhcnt,fieldLabel: '公文交换',items:gwjh}
      ,
	   </s:if>
	    <s:if test ="gwgl.size>0">
      {xtype: 'checkboxgroup',columns:gwglcnt,fieldLabel: '公文管理',items:gwgl}
      ,
	   </s:if>
	    <s:if test ="fwbl.size>0">
      {xtype: 'checkboxgroup',columns:fwblcnt,fieldLabel: '发文办理',items:fwbl}
      ,
	   </s:if>
	    <s:if test ="swbl.size>0">
      {xtype: 'checkboxgroup',columns:swblcnt,fieldLabel: '收文办理',items:swbl}
      ,
	   </s:if>
	    <s:if test ="hqfw.size>0">
      {xtype: 'checkboxgroup',columns:hqfwcnt,fieldLabel: '会签发文',items:hqfw}
      ,
	   </s:if>
	    <s:if test ="hqsw.size>0">
      {xtype: 'checkboxgroup',columns:hqswcnt,fieldLabel: '会签收文',items:hqsw}
      ,
	   </s:if>
	    <s:if test ="swdj.size>0">
      {xtype: 'checkboxgroup',columns:swdjcnt,fieldLabel: '收文登记',items:swdj}
      ,
	   </s:if>
	    <s:if test ="nbwjcs.size>0">
      {xtype: 'checkboxgroup',columns:nbwjcscnt,fieldLabel: '内部文件传送',items:nbwjcs}
      ,
	   </s:if>
	    <s:if test ="tzgg.size>0">
      {xtype: 'checkboxgroup',columns:tzggcnt,fieldLabel: '通知公告',items:tzgg}
      ,
	   </s:if>
	    <s:if test ="bhgl.size>0">
      {xtype: 'checkboxgroup',columns:bhglcnt,fieldLabel: '编号管理',items:bhgl}
      ,
	   </s:if>
	      <s:if test ="wjzlk.size>0">
      {xtype: 'checkboxgroup',columns:wjzlkcnt,fieldLabel: '文件资料库',items:wjzlk}
      ,
	   </s:if>
	     <s:if test ="gngl.size>0">
      {xtype: 'checkboxgroup',columns:bhglcnt,fieldLabel: '功能管理',items:gngl}
      ,
	   </s:if>
	    <s:if test ="publiclist.size>0">
      {xtype: 'checkboxgroup',columns:bhglcnt,fieldLabel: '基本权限：    日历管理',items:rlgl}
      ,
	   </s:if>
      new Ext.form.Hidden({name:'sysid',value:'<s:property value="sysid"/>'})
      ,
      new Ext.form.Hidden({name:'nodeid',value:'<s:property value="nodeid"/>'})
      ,
      new Ext.form.Hidden({name:'template',value:'<s:property value="template"/>'})
      ];
 
</script>

		<div id="form-ct"></div>
	</body>
</html>
