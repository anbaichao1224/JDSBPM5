<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<%
String contextpath = request.getContextPath() + "/";
%>
<html>
<head>
    <title>Grid</title>
     <link rel="stylesheet" type="text/css" href="<%=contextpath%>js/ext/resources/css/ext-all.css" />
     <script src="<%=contextpath%>js/ext/adapter/ext/ext-base.js"></script>
     <script src='<%=contextpath%>js/ext/ext-all.js'></script>
    <script type="text/javascript" src="/usm/js/righttemplate1.js" defer="defer"></script>
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
    
       var strarr=[
       
		<s:iterator id="li" value="Cnnamelist" status="rowstatus">
		
			 {boxLabel: '<s:property value="#li.Cnname"/>',name: 'Cnname',inputValue: '<s:property value="#li.Cnname"/>'
		        ,checked:true
	     	 }
			  <s:if test ="#rowstatus.count < Cnnamelist.size">
		      , 
		      </s:if>
		</s:iterator>
 	  ];
 	   var strarrModule=[
		<s:iterator id="li" value="publiclist" status="rowstatus">
			 {boxLabel: '<s:property value="#li.Cnname"/>',name: 'Cnname',inputValue: '<s:property value="#li.Cnname"/>'
		        ,checked:false
	     	 }
			  <s:if test ="#rowstatus.count < publiclist.size">
		      , 
		      </s:if>
		</s:iterator>
 	  ];
      var cnt = '<s:property value="Cnnamelist.size"/>';
      if (cnt>maxCols){
        cnt = maxCols;
      }
      var cntmodule = '<s:property value="publiclist.size"/>';
      if (cntmodule>maxCols){
        cntmodule = maxCols;
      }
      var str=[
      <s:if test ="publiclist.size>-1">
      {xtype: 'checkboxgroup',columns:cntmodule,fieldLabel: '所有角色',items:strarrModule}
      ,
	   </s:if>
       <s:if test ="Cnnamelist.size>-1">
	   {xtype: 'checkboxgroup',columns:cnt,fieldLabel: '分配角色',items:strarr}
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
