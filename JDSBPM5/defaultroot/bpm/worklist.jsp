<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page
	import="java.util.Map,
	com.opensymphony.xwork2.ActionContext,
	java.util.List,net.itjds.bpm.data.FormClassBean,
	com.opensymphony.xwork2.util.OgnlValueStack"
	%>
<%@ taglib uri="/struts-tags" prefix="ww"%>


<%
String actionurl = request.getParameter("action");
String contextpath = request.getContextPath() + "/";
%>
	<link rel="stylesheet" type="text/css" href="<%=contextpath%>js/ext/resources/css/menus.css" />
	<link rel="stylesheet" type="text/css" href="<%=contextpath%>js/ext/resources/css/ext-all.css" />
    <SCRIPT LANGUAGE="JavaScript" type="text/javascript">
	var context='<%=contextpath%>';
	var personid='<ww:property value="$currPerson.ID"/>';
	var currUserName='<ww:property value="$currPerson.name"/>';
    </SCRIPT>
		<script type="text/javascript" src="<%=contextpath%>js/extAll.js"></script>
	    <script  src="<%=contextpath%>js/JDS.js" type="text/javascript"></script>
	  	<script  src="<%=contextpath%>desktop/js/CreateGrid.js"></script>
<html>

<script type="text/javascript">
 var viewport;
function winResizeHandler  (win, width, height){

    win.items.get(0).setWidth(width);
   win.items.get(0).setHeight(height);
} ;


   var callback=function(txt){
			 Ext.namespace("EVAL");
			
                    eval(txt);
                     
					var cfg = {
					        width: 800,
					        height: 600,
					        shim: false,
					        animCollapse: false,
					        border: false,
					        constrainHeader: true,
					        layout: 'border',
					        listeners: {
					            resize: function(){
					                    winResizeHandler.apply(this, arguments);
					            }
					        }
					    };
					                    
                    
			 var returnCfg=EVAL.getPanel(); 
			  returnCfg = Ext.apply( cfg,returnCfg)
			  new Ext.Viewport(returnCfg);
		    }
		  
	   JDS.ajax.Connection.LoadJsonFromUrl("bpmView.action?type=AllWaitList&param['state']=5&param['usmType']=allmyWorkList",callback);


Ext.onReady(function(){

});
	
	</script>
	</html>