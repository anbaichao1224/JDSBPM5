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
    width:700,
    loadMask :{msg:'正在读取数据请稍后'},
    height:370
  },
 refGrid:true,
  metaData:{
    dataType:"json",
    hasChockbox:true,
    hasRowNum:true,
    paging:{
      totalProperty:"totalCount",
      root:"datas",
      pageSize:100
    },
    cols:[
       {text:"index",name: 'index',isDisplay:'false'},
       {text:"rowdblclick",name: 'rowdblclick',isDisplay:'false'},
       {text:"processInstId",name: 'processInstId',isDisplay:'false'},
       {text:"signList",name: 'signList',isDisplay:'false'},
       {text:"performList",name: 'performList',isDisplay:'false'},
       {text:"紧急程度",name: 'urgencyDegree',sortable:true,width:60,isDisplay:'false'},
       {text:"公文标题",name: 'fileTitle',sortable:true,width:120},
       {text:"流程名称",name: 'priprocessDefNamece',width:120},
       {text:"拟稿人",name: 'startName' ,isDisplay:'false'},
       {text:"拟稿时间",name: 'startTime',width:140},
       {text:"当前办理步骤",name: 'activityDefName',isDisplay:'false'},
       {text:"可执行操作 ",name: 'process',isDisplay:'false'}
    ]
  }
};
return baseGridCfg;
}


//草稿箱

function getdefaultWorkList(){

	var defaultWork=new Ext.apply({}, getBaseGridCfg());
		defaultWork.selfCfg.id="defaultWork";
		defaultWork.selfCfg.width=500;
		defaultWork.selfCfg.height=370;
		defaultWork.dataUrl="defaultWorklist.action?rtnType=jsondata&intType=0";
		defaultWork.parentWin=this;
		
	var defaultWorkList={
		   title:"我的工作",
	  type:'grid',
      x:500,
      y:100,
	      buttons: [{
	                 text:'批量删除',
	                   handler: function(){
	                   worklist= new JDS.bpm.WorkList(Ext.getCmp(defaultWork.selfCfg.id));
	                      worklist.deleteAll();
	                   }
	                 }
	                 ],
	        buttonAlign:'center',
	      tbar:[{
	         text:'起草公文',
	         tooltip:'起草公文',
	           handler:function(){
              openWinById('processDefList');
         },
	         iconCls:'add'
	         }, '-', {
	         text:'公文检索',
	         tooltip:'公文检索',
	         iconCls:'option'
	         },'-',{
	         text:'删除公文',
	         tooltip:'删除公文',
	         iconCls:'remove'
	
	                        }],
	      items:[
	      createGridByData(defaultWork)
	      ]
	    };
	    
return defaultWorkList;
}

function EVAL.getPanel(){
	return getdefaultWorkList();
 }



 
