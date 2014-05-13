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
    width:800,
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
      pageSize:12
    },
    cols:[
       {text:"index",name: 'index',isDisplay:'false'},
       {text:"rowdblclick",name: 'rowdblclick',isDisplay:'false'},
       {text:"processInstId",name: 'processInstId',isDisplay:'false'},
       {text:"signList",name: 'signList',isDisplay:'false'},
       {text:"performList",name: 'performList',isDisplay:'false'},
       {text:"紧急程度",name: 'urgencyDegree',sortable:true,width:60},
       {text:"状态",name: 'readState',sortable:true,width:70},
       {text:"时限",name: 'timeLimit',sortable:true,width:80},
       {text:"公文标题",name: 'fileTitle',sortable:true,width:120},
       {text:"流程名称",name: 'priprocessDefNamece',width:120},
       {text:"拟稿人",name: 'startName' ,isDisplay:'true',width:70},
       {text:"当前办理步骤",name: 'activityDefName',isDisplay:'false'},
       {text:"当前办理人",name: 'activityDefName',isDisplay:'false'},
       {text:"拟稿时间",name: 'startTime',width:110},
       {text:"可执行操作 ",name: 'process',isDisplay:'false'}
    ]
  }
};
return baseGridCfg;
}


function getendReadWorkList (){
		//已完成工作箱
	var  endReadWork=new Ext.apply({},getBaseGridCfg());
	endReadWork.selfCfg.id="endReadWork";
	//completedWork.selfCfg.width=800;
	endReadWork.dataUrl="defaultWorklist.action?rtnType=jsondata&intType=7";
		defaultWork.parentWin=this;
     var endReadWorkList={
       title:"已完成工作箱",
	  type:'grid',
      x:300,
      y:200,
	  type:'grid',
      buttons: [{
                text:'批量归档',
                handler: function(){
                        completedAll(Ext.getCmp(endReadWork.selfCfg.id));
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
         }],
      items:[
       createGridByData(endReadWork)
     
      ]
    };
	
return endReadWorkList;

}




function EVAL.getPanel(){
	return getendReadWorkList();
 }



 
