<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.Map,com.opensymphony.xwork2.util.OgnlValueStack"%>
<%@ taglib uri="/struts-tags" prefix="ww"%>
<%   String contextpath = request.getContextPath()+ "/" ; %>
//流程列表基础配置
function getBaseGridCfg(){
	var baseGridCfg={
		selfCfg:{
		title:"",
		width:725,
		enableColumnHide:true,
		enableHdMenu:true,
		frame:true,
		stripeRows:true,
		loadMask :{msg:'正在读取数据请稍后'},
		height:380
		},
	    refGrid:true,
	metaData:{
		dataType:"json",
		hasChockbox:true,
		hasRowNum:true,
    paging:{
      totalProperty:"totalCount",
      root:"datas",
      pageSize:15
    },
    cols:[
       {text:"index",name: 'index',isDisplay:'false'},
       {text:"rowdblclick",name: 'rowdblclick',isDisplay:'false'},
       {text:"processInstId",name: 'processInstId',isDisplay:'false'},
       {text:"当前办理步骤",name: 'activityDefName'},
       {text:"标题",name: 'fileTitle',sortable:true,width:100},
       {text:"当前办理人",name: 'currPerPerson',width:150},
       {text:"提按时间",name: 'startTime',width:50}
	    ]
	  }
	};
	return baseGridCfg;
}

//所有待办
function getmyWorkYitiList(){
	var myWorkYiti=new Ext.apply({},getBaseGridCfg());
	myWorkYiti.selfCfg.id="myWorkYiti";
	myWorkYiti.dataUrl="statList.action?flowType=resolution&state=4,6&type=1&act=1";
	var myWorkYitiList ={
		title:"未交办的提案",
		buttonAlign:'center',
		items:[
			createGridByData(myWorkYiti)
		]
	};
	return myWorkYitiList;
}	
function EVAL.getPanel(){
	return getmyWorkYitiList();
}
