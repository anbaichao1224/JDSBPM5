<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/struts-tags" prefix="ww" %>	
<%
response.setHeader("Pragma","No-cache"); 
response.setHeader("Cache-Control","no-cache"); 
response.setDateHeader("Expires", 0); 
String contextpath = request.getContextPath() + "/";
%> 	
function EVAL.getPanel(){


	var routeLogGridCfg={
	  selfCfg:{
	    title:"查看流程日志",
	    width:600,
	    id:"routeLogGridCfg"
	  },
	  metaData:{
	    dataType:"json",
	    hasRowNum:true,
	    paging:{
	      totalProperty:"totalCount",
	      root:"datas",
	      pageSize:10
	    },   
	    
	   cols:[
	       {text:"index",name:'index',isDisplay:'false'},
	       {text:"processInstId",name: 'processInstId',isDisplay:'false'},
	       {text:"办理人 / 所属部门",name: 'performPerson',width:150},
	       {text:"办理步骤",name: 'activityInstName',sortable:true,width:100},    

	       {text:"开始时间",name: 'startName',width:100},
	       {text:"结束时间",name: 'endTime',width:100},
	       {text:"查看", name: 'action'}    
	    ]
	  },
	  dataUrl:"routelog.action?activityInstId="+$('activityInstId').value
	};
	//查看流程日志
	var routelogwin = new Ext.Window({
	        title: '查看流程日志',
	        collapsible:true,
	        width:640,
	        height:380,
	        id:'routelogwin',
	        shim:false,
	        animCollapse:false,
	        constrainHeader:true,
	        maximizable: true,       
	        items:createGridByData(routeLogGridCfg) ,   
	         buttons: [{
	                    text:'确定',
	                   handler: function(){
	                      routelogwin.close();
	                    }
	                }]      
	    });

  routelogwin.show();
  return routelogwin;
 
}  





 
