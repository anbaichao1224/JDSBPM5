<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%
String contextpath = request.getContextPath() + "/";
%>
<html>
<head>
    <title>Grid</title>
     <link rel="stylesheet" type="text/css" href="<%=contextpath%>js/ext/resources/css/ext-all.css" />
     <script src="<%=contextpath%>js/ext/adapter/ext/ext-base.js"></script>
     <script src='<%=contextpath%>js/ext/ext-all.js'></script>
    <script type="text/javascript" src="/usm/js/personrighttemplate.js" defer="defer"></script>
    <script type="text/javascript">
    
        Ext.BLANK_IMAGE_URL = '/usm/resources/images/default/s.gif';
    </script>
  
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

   //所有模块集合
    var maxCols = 4; 
    var ttemplate = '<s:property value="template"/>';
    var tsysid = '<s:property value="sysid"/>';
    var tnodeid = '<s:property value="nodeid"/>'; 
    var strarr=[
		<s:iterator id="li" value="modulelist" status="rowstatus">
			 {
			 boxLabel: '<s:property value="#li[11]"/>',
			 name: 'txtCheckValue',
			 inputValue: '<s:property value="#li[1]"/>' 
	     	 }
			 <s:if test ="#rowstatus.count < modulelist.size">
		      , 
		     </s:if>
		</s:iterator>
 	];
    var cnt =  '<s:property value="modulelist.size"/>';
 	//过滤后的模块集合
    var strarr2=[
		<s:iterator id="li" value="listright" status="rowstatus">
			 {boxLabel: '<s:property value="#li.Name"/>',name:'checkboxs',checked:true,inputValue: '<s:property value="#li.ID"/>'
	     	 }
			  <s:if test ="#rowstatus.count < listright.size">
		      , 
		      </s:if>
		</s:iterator>
 	];
    var cnt2 = '<s:property value="listright.size"/>';
    if (cnt>maxCols){
       cnt = maxCols;
    }
    if (cnt2>maxCols){
       cnt2 = maxCols;
    }
	if(strarr=="" && strarr2==""){
 	bool=true;
 	var str=[new Ext.form.Hidden({name:'sysid',value:'<s:property value="sysid"/>'}),new Ext.form.Hidden({name:'nodeid',value:'<s:property value="nodeid"/>'}),new Ext.form.Hidden({name:'template',value:'<s:property value="template"/>'})];
 	}else{
 	bool=false;
 	if(strarr==""){
 		var str=[{xtype: 'checkboxgroup',columns:cnt2,fieldLabel: '所有权限模块列表<br>(部门,角色,职务...)',items:strarr2},new Ext.form.Hidden({name:'sysid',value:'<s:property value="sysid"/>'}),new Ext.form.Hidden({name:'nodeid',value:'<s:property value="nodeid"/>'}),new Ext.form.Hidden({name:'template',value:'<s:property value="template"/>'})];
 	}else if(strarr2==""){
 		var str=[{xtype: 'checkboxgroup',columns:cnt,fieldLabel: '单独授权模块列表',items:strarr},new Ext.form.Hidden({name:'sysid',value:'<s:property value="sysid"/>'}),new Ext.form.Hidden({name:'nodeid',value:'<s:property value="nodeid"/>'}),new Ext.form.Hidden({name:'template',value:'<s:property value="template"/>'})];
 	}else{
 		var str=[
 		{xtype: 'checkboxgroup',columns:cnt,fieldLabel: '单独授权模块列表',items:strarr}
 		,{xtype: 'checkboxgroup',columns:cnt2,fieldLabel: '所有权限模块列表<br>(部门,角色,职务...)',items:strarr2}
 		,new Ext.form.Hidden({name:'sysid',value:'<s:property value="sysid"/>'})
 		,new Ext.form.Hidden({name:'nodeid',value:'<s:property value="nodeid"/>'})
 		,new Ext.form.Hidden({name:'template',value:'<s:property value="template"/>'})
 		];
 	}
 	}
 
</script>

    <div id="form-ct"></div>  
</body>
</html>
