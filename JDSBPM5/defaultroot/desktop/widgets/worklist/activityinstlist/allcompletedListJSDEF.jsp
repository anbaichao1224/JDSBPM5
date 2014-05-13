<%@ page contentType="text/html; charset=UTF-8"%>

<%@ page
	import="java.util.Map,com.opensymphony.xwork2.util.OgnlValueStack"%>

<%@ taglib uri="/struts-tags" prefix="ww"%>



<%   String contextpath = request.getContextPath()+ "/" ; %>

//流程列表基础配置
function getBaseGridCfg(){
var baseGridCfg={
  selfCfg:{
    title:"",
    width:725,
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
       {text:"signList",name: 'signList',isDisplay:'false'},
       {text:"performList",name: 'performList',isDisplay:'false'},
       {text:"紧急程度",name: 'urgencyDegree',sortable:true,width:60},
       {text:"状态",name: 'readState',sortable:true,width:40},
       {text:"时限",name: 'timeLimit',sortable:true,width:40},
       {text:"标题",name: 'fileTitle',sortable:true,width:150},
       {text:"拟稿人",name: 'startName' ,isDisplay:'true'},
       {text:"拟稿时间",name: 'startTime',width:120},
       {text:"流程名称",name: 'priprocessDefNamece',width:80},
       {text:"当前办理步骤",name: 'activityDefName',isDisplay:'false'},
       {text:"可执行操作 ",name: 'process',isDisplay:'false'}
    ]
  }
};
return baseGridCfg;
}

//所有待办
function getmyWorkYitiList(){

//我的工作箱 （包括待办和正在办理的）
	var myWorkYiti=new Ext.apply({},getBaseGridCfg());
	myWorkYiti.selfCfg.id="myWorkYiti";
	//myWorkYiti.selfCfg.width=800;
	myWorkYiti.dataUrl="defaultWorklist.action?rtnType=jsondata&intType=3";
	

     var myWorkYitiList ={
      title:"我的工作",
	  type:'grid',
      x:10,
      y:100,
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

 
