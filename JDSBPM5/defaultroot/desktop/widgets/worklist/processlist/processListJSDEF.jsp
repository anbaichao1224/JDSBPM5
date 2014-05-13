<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page
	import="java.util.Map,com.opensymphony.xwork2.util.OgnlValueStack"%>
<%@ taglib uri="/struts-tags" prefix="ww"%>
<%   String contextpath = request.getContextPath()+ "/" ; %>

<script>



//流程列表基础配置
function getBaseGridCfg(){
var baseGridCfg={
  selfCfg:{
    title:"",
    width:720,
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
      pageSize:10
    },
    cols:[
       {text:"index",name: 'index',isDisplay:'false'},
       {text:"rowdblclick",name: 'rowdblclick',isDisplay:'false'},
       {text:"processInstId",name: 'processInstId',isDisplay:'false'},
       {text:"signList",name: 'signList',isDisplay:'false'},
       {text:"performList",name: 'performList',isDisplay:'false'},
       {text:"公文标题",name: 'fileTitle',sortable:true,width:120},
       {text:"流程名称",name: 'priprocessDefNamece'},
       {text:"拟稿人",name: 'startName'},
       {text:"拟稿时间",name: 'startTime' ,width:120},
       {text:"当前办理步骤     可执行操作 ",name: 'process',width:200}
    ]
  }
};
return baseGridCfg;
}


//草稿箱

function getdefaultWorkList(){

	var defaultWork=new Ext.apply({}, getBaseGridCfg());
		defaultWork.selfCfg.id="defaultWork";
		defaultWork.selfCfg.width=720;
		defaultWork.selfCfg.height=380;
		defaultWork.dataUrl="demolist.action?rtnType=jsondata&intType=0";
		
	var defaultWorkList={
		  type:'grid',
	      buttons: [{
	                 text:'批量删除',
	                   handler: function(){
	                       deleteAll(Ext.getCmp(defaultWork.selfCfg.id));
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



//待办箱
function getwaitedWorkList(){

	var waitedWork=new Ext.apply({}, getBaseGridCfg());
	waitedWork.selfCfg.id="waitedWork";
	//waitedWork.selfCfg.width=800;
	waitedWork.dataUrl="demolist.action?rtnType=jsondata&intType=1";
    var waitedWorkList={
     // title:"待办工作列表",
	  type:'grid',
      buttons: [{
                text:'批量处理',
                handler: function(){
                        perFormAll(Ext.getCmp(waitedWork.selfCfg.id));
                   }
                 },{
                 text:'批量签收',
                   handler: function(){
                   signAll(Ext.getCmp(waitedWork.selfCfg.id));
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
      createGridByData(waitedWork)
	 
      
      ]
    };
    
    return waitedWorkList;
}


function getcurrWorkList(){
	//正在办理工作箱
	var currWork=new Ext.apply({},getBaseGridCfg());
	currWork.selfCfg.id="currWork";
	currWork.dataUrl="demolist.action?rtnType=jsondata&intType=2";
	
	
    var currWorkList={
      //title:"正在办理的工作",
	  type:'grid',
      buttons: [{
                text:'批量处理',
                handler: function(){
                        perFormAll(Ext.getCmp(currWorkGrid.selfCfg.id));
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
         createGridByData(currWork)
      ]
    };
	return currWorkList;
}




function getmyWorkList(){

//我的工作箱 （包括待办和正在办理的）
	var myWork=new Ext.apply({},getBaseGridCfg());
	myWork.selfCfg.id="myWork";
	//myWork.selfCfg.width=800;
	myWork.dataUrl="demolist.action?rtnType=jsondata&intType=5";
	

     var myWorkList ={
      //title:"我的工作",
	  type:'grid',
      x:10,
      y:100,
      buttons: [{
                text:'批量处理',
                handler: function(){
                        perFormAll(Ext.getCmp(myWork.selfCfg.id));
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
       createGridByData(myWork)
	 

      ]
    };
	return myWorkList;
}	






function getcompletedWorkList (){
	
	
	//已完成工作箱
	var  completedWork=new Ext.apply({},getBaseGridCfg());
	completedWork.selfCfg.id="completedWork";
	//completedWork.selfCfg.width=800;
	completedWork.dataUrl="demolist.action?rtnType=jsondata&intType=3";
	
     var completedWorkList={
	  type:'grid',
      buttons: [{
                text:'批量归档',
                handler: function(){
                        completedAll(Ext.getCmp(completedWork.selfCfg.id));
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
       createGridByData(completedWork)
     
      ]
    };
	
return completedWorkList;

}


function getreadWorkList(){

//我的工作箱 （包括待办和正在办理的）
	var myWork=new Ext.apply({},getBaseGridCfg());
	myWork.selfCfg.id="readWork";
	//myWork.selfCfg.width=800;
	myWork.dataUrl="demolist.action?rtnType=jsondata&intType=6";
	

	
     var myWorkList ={
      //title:"我的工作",
	  type:'grid',
      x:10,
      y:100,
      buttons: [{
                text:'批量处理',
                handler: function(){
                        perFormAll(Ext.getCmp(myWork.selfCfg.id));
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
       createGridByData(myWork)
	 

      ]
    };
	return myWorkList;
}	






function getendReadWorkList (){
		//已完成工作箱
	var  completedWork=new Ext.apply({},getBaseGridCfg());
	completedWork.selfCfg.id="completedWork";
	//completedWork.selfCfg.width=800;
	completedWork.dataUrl="demolist.action?rtnType=jsondata&intType=7";
	
     var completedWorkList={
     // title:"已完成的工作",
	  type:'grid',
      buttons: [{
                text:'批量归档',
                handler: function(){
                        completedAll(Ext.getCmp(completedWork.selfCfg.id));
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
       createGridByData(completedWork)
     
      ]
    };
	
return completedWorkList;

}



function EVAL.complete(){
  var winId='<ww:property value="winId"/>';	
  var winStr=winId.substring(0,winId.length-4);
  var win=eval('get'+winStr+'()');
  var pwin=Ext.getCmp(winId);
  pwin.add(win);
	return win
}

</script>

 
