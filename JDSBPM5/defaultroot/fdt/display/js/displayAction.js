﻿﻿var dispwin;
//window对象
var JDSDesk;
var formTab;
var processDefInnerId;
var currActivityInstId;
var functionName;

function onpendio(_url){
  var ret = window.showModalDialog(_url,null,"dialogHeight: 600px; dialogWidth: 900px; dialogTop: px; dialogLeft: px; edge: Raised; center: Yes; help: No; resizable:Yes; status: Yes;");
} 


	

function showOfficeMsgById(activityInstId,processInstId,title,body){
		var desktop = getCurDesktop();
    	var notifyWin = desktop.showNotification({
			autoDestroy: false,
			hideDelay: 5000,
			iconCls: 'x-icon-waiting',
			html: '<a onclick=\"openWin(\'demodisplay.action?activityInstId='+activityInstId+'\',\''+activityInstId+'\')" >'+body+'</a>',
			title: title
		});
}



function showSystemMsgById(title,body){
	var desktop = JDSDesk.getDesktop();
    	var notifyWin = desktop.showNotification({
			autoDestroy: true,
			hideDelay: 5000,
			iconCls: 'x-icon-waiting',
			html: body,
			title: title
		});
}

function getSForm()
 {
 	try{
			 performWinId=EVAL.getWinId();
		}  catch(e){
			performWinId=getCurDesktop().getManager().getActive().id;
		}
 	var sForm = document[performWinId+$(performWinId+'mainFormId').value+'form'];
 	return sForm;
 }



function  getActivityInstId(){
	var activityInstId;
	
 	var sForm = getSForm();
 	try{
			activityInstId = sForm['activityInstId'].value;
		}  catch(e){
		}
	return  activityInstId ;            
	 }
function  getHistoryId(){
	var activityInstHistoryId;
	
 	var sForm = getSForm();;
 	try{
			activityInstHistoryId = sForm[formTab.getActiveTab().id+"his"].value;
		}  catch(e){
		}
	return  activityInstHistoryId ;            
	 } 
	 
function getActiveFormId()
{
  return formTab.getActiveTab().id+"001";
}

function showAccachWin (){
	var activityInstId = getActivityInstId();
	var addfile = "";
	var delfile = "";
	 if(getHistoryId() ==null || getHistoryId() == "")
		{
			addfile = '添加文件';
			delfile = '删除文件';
		}
	var accachGridCfg={
	  selfCfg:{
	    width:800,
	    height:200,
        id:getActiveFormId(),
			renderTo:getActiveFormId(),	
			 tbar: [{
	                    text:addfile,
	                   handler: function(){
	                   	var dia=new Ext.ux.UploadDialog.Dialog({upload_autostart:true,url:contextfile+'attachUpload.action',base_params:{'activityInstId':activityInstId,'formId':getActiveFormId(),'personId':personid}});
	                     dia.show();
	                    }
	                },{
	                    text:delfile,
	                   handler: function(){
         	deleteFile(Ext.getCmp(getActiveFormId()));
         	setTimeout("refGridById(getActiveFormId())",500);
         	alertMsg("删除成功");
         	
	                    }
	                }] 
	  },
	  metaData:{
	  	 hasChockbox:true,
	    dataType:"json",
	    hasRowNum:true,
	    paging:{
	      totalProperty:"totalCount",
	      root:"datas",
	      pageSize:5
	    },   
	    
	   cols:[
	       {text:"index",name:'index',isDisplay:'false'},
	       {text:"processInstId",name: 'processInstId',isDisplay:'false'},
	       {text:"activityInstId",name: 'activityInstId',isDisplay:'false'},
	        {text:"uuid",name: 'uuid',isDisplay:'false'},
	       {text:"文件名称",name: 'filename',width:300},
	       {text:"上传人",name: 'uploaduser',width:100},    
	       {text:"上传时间",name: 'uploadtime',sortable:true,width:150},
	       {text:"文件类型",name: 'filetype',width:100}
	          
	    ]
	  },
	  dataUrl:"fileList.action?activityInstId="+$('activityInstId').value+"&activityInstHistoryId="+ getHistoryId()+"&formId="+getActiveFormId()
	};
	
	createGridByData(accachGridCfg);

  }
  
  function deleteFile(grid){
	var selections=grid.getSelections();
	   if (selections.length==0){
	          alertMsg('请选择需要删除的文件');
	   return;
	   }
 
	var delAllList=new Array();
	  for(var i=0;i<selections.length;i++){
	   if (selections[i].get('uuid').length>0 ){     
	    delAllList[delAllList.length]= selections[i].get('uuid');    
	   }else{
	    Ext.getCmp(gridId).getSelectionModel().deselectRow(selections[i].get('index'));   
	   }
	  };
	   var fileList=delAllList.join();
	    var str="fileId="+fileList;
	  JDS.ajax.Connection.LoadJsonFromUrlByDeskTop (context+'filedel.action',null,str);
	  
//	   functionName="deleteFile";
//	   var url=getUrlByActionNameStr();
//	   var myAjax = getMyAjax('dd', url, str);	
	  
}



function openWinByPam(url,params){
      openWin(url,null,null,params);
   };
   
function openWin(url,winid,title,params){
	currActivityInstId=winid;
    popWin.callFn=callFn;
	
	
	winid= winid.replace(/-/g,''); 
    var obj=Ext.getCmp(winid);
	
    if (obj){
		try{
			obj=getCurDesktop().getWindow(winid);
			getCurDesktop().markActive(obj);
		}catch(e){
			
		}
    }else{
		  var cfg={
      id:winid,
     // parentId:winid,    		
      title:title,
      autoLoad:{url:url,params:params,callback:popWin.callFn}
    };

    var p= popWin.getHtmlPanel(cfg);
    popWin.p=p;
    p.render(Ext.get('evalwin'));  
  	return p;
		
	}
	
  
  }

  
//回调函数将表单展现回调  
   function callFn(){  	
      var body=arguments[0];
      var text=arguments[2].responseText;
      Ext.namespace("EVAL");
	  //alert(text);
      eval(text);
	  if (currGrid){
	  	refGridById(currGrid.id);
		}
      var p=EVAL.getPanel();
      var winid=popWin.p.parentId;
     
      if (!winid && p.parentId){
        winid=p.parentId;
      };
      var title=popWin.p.title;
      popWin.p.destroy();
      if (!title){
      title="业务办理";
      }
      var wincfg={
            title:title,
		    autoScroll :true,
      		id:winid,
      		width:835,
      		height:500,     		
      		items:[p]     		
      		};  
		if 	(getCurDesktop()){
         dispwin = getCurDesktop().createWindow(wincfg);    
		}else{
	     dispwin = new Ext.Window(wincfg);		
		}
   	 dispwin.add(p);
	 if (p.parentId){
	 	dispwin.id=p.parentId;
	 }
   	 dispwin.show();   
	
   } 
  
   

function proformWork(activityInstId){
  openWin(context+"demodisplay.action?ActivityInstId="+activityInstId, activityInstId);
 }

 function proformWorkEnd(activityInstHistoryId){
  openWin(context+"demohisdisplay.action?activityInstHistoryId="+activityInstHistoryId, activityInstHistoryId);
 }
 
 function completedAll(){	
   alert('此功能需要二次开发支持');
 }
 
  

//新建流程
 function newProcess(processDefId){
    processDefInnerId=processDefId;
   var url=context+"demoInsert.action";
   var parms="processDefId="+processDefInnerId+"&processName="+encodeURIComponent("流程名称");
   openWin(url,processDefInnerId,'新建流程',parms);
 
  };
  
 function newProcessByInstName(btn,processName){ 
  if (btn=='yes'  || btn=='ok'){  
   var url=context+"demoInsert.action";
   var parms="processDefId="+processDefInnerId+"&processName="+encodeURIComponent(processName);
   openWinByPam(url,parms);
   }
 }


//流程结束处理
function BPM_routeToEnd(activityDefId) {  
 var performWinId;
		try{
			 performWinId=EVAL.getWinId();
		}  catch(e){
			performWinId=getCurDesktop().getManager().getActive().id;
		}
   if (activityDefId==null || activityDefId==''){
     activityDefId='LAST';
    }
     messageHandBoxProgress('正在提交数据','正在提交数据','正在提交数据');
    $(performWinId+'BPM_UpdateType').value='routetoend';
    $(performWinId+'BPM_RouteToActivityDefIds').value=activityDefId;	
	 functionName = "routeToEnd";	
	 submitForm();  	
    return true;
}
//发送处理
function BPM_routeTo(routeId,el) {
   messageHandBoxProgress('正在读取用户信息','正在读取用户信息','正在读取用户信息',el); 
	var evalJs = function(o){
			   Ext.namespace("EVAL");
               eval(o);
			   EVAL.getPanel();
			    doOk();
	}
  JDS.ajax.Connection.LoadJsonFromUrlByDeskTop( 
	 context+"SelectPersonJSDEF.action",
	 evalJs,
	 $H({
	 	activityInstId:$('activityInstId').value,
		routeDefId:routeId
		})
	)    
    return true;
} 









//提交数据处理
function submitForm(){
	
  var performWinId;
		try{
			 performWinId=EVAL.getWinId();
		}  catch(e){
			performWinId=getCurDesktop().getManager().getActive().id;
		}
  if (currGrid){
	  	refGridById(currGrid.id);
	}
        doOk();
		functionName = "saveMapDAO";	
		  var str="";			
		try{		
		  //循环个表单值组合为STRING
		 for(var i=0;formTab.items.length>i;i++ )	{
				
			str=formTab.getComponent(i).form.getValues();
		   var sForm = document[performWinId+formTab.getComponent(i).id+'form'];
				   if (sForm) {
				   	  str=Form.serializeElements(Form.getElements(sForm), false)+"&"+str; 
					messageHandBoxProgress('开始保存数据['+formTab.getComponent(0).title+']','','校验表单['+formTab.getComponent(0).title+']成功');
				   }else{
				   	formTab.activate(formTab.getComponent(i)); 
					messageHandBoxProgress('开始保存数据','校验表单['+formTab.getComponent(i).title+']','开始校验表单['+formTab.getComponent(i).title+']');
					return;
			}
				   
				   
			try{	
				 if (!formTab.getComponent(i).form.isValid() ){
					 warningMsg('校验失败请检查表单['+formTab.getComponent(i).title+']');
					return ;
				 };
		        }catch(e){		
			      
		       }
				
              }	  	
		}catch(e){		
		       Ext.MessageBox.hide();		   
		       alert('表单数据序列化错误，请检查表单定义是否正确！');
		      return false;
		   }

 var url=getUrlByActionNameStr(context+'demoupdate.action');	
	    functionName = "submitForm";	
  var myAjax = getMyAjax('routelog', url, str);
}

function BPM_showRouteLog(activityInstId) {
	messageHandBoxProgress('正在读取历程列表','正在读取历程列表','正在读取历程列表');	 
	 functionName = "showRouteLog";	
	 str='activityInstId='+activityInstId;
    var myAjax = getMyAjax('routelog', context+'routelogJSDEF.action?', str);
	
}






//收回重新办理里
function BPM_TackBackNONE(activityInstId) {    
       messageHandBoxProgress('正在提交数据','正在提交数据','正在提交数据');
	   
	    if (currGrid){
	  	refGridById(currGrid.id);
		}
        str='activityInstId='+activityInstId;
        var myAjax = getMyAjax('routelog', context+'demotakebackNONE.action?', str);
	    functionName = "TackBack";		
}
//收回重新办理里
function BPM_TackBack(activityInstId) {    
 messageHandBoxProgress('正在提交数据','正在提交数据','正在提交数据');
  if (currGrid){
	  	refGridById(currGrid.id);
		}
        str='activityInstId='+activityInstId;
        var myAjax = getMyAjax('routelog', context+'demotakebackNONE.action?', str);
	    functionName = "TackBack";		
}




//发出退回操作
function BPM_routeBack(avtivityInstId,activityInstHisId) {

	 messageHandBoxProgress('正在提交数据','正在提交数据','正在提交数据');
	  if (currGrid){
	  	refGridById(currGrid.id);
		}
     str="activityInstId="+avtivityInstId+"&toActivityInstHistoryId="+activityInstHisId;
	    functionName = "RouteBack";	
        var myAjax = getMyAjax('routelog', context+'demorouteback.action?', str);

}

//表单数据保存

function save() {
		  var performWinId;
		try{
			 performWinId=EVAL.getWinId();
		}  catch(e){
			performWinId=getCurDesktop().getManager().getActive().id;
		}
		doOk();
		  functionName = "saveMapDAO";	
		   
		$(performWinId+'BPM_UpdateType').value='save';
		  var str="";			
		 try{		
		
		  //循环个表单值组合为STRING
			for(var i=0;formTab.items.length>i;i++ )	{
				
		   var sForm = document[performWinId+formTab.getComponent(i).id+'form'];
				   if (sForm) {
				   	  str=Form.serializeElements(Form.getElements(sForm), false)+"&"+str; 
					  messageHandBoxProgress('开始保存数据['+formTab.getComponent(0).title+']','','校验表单['+formTab.getComponent(0).title+']成功');
				   }else{
				   	formTab.activate(formTab.getComponent(i)); 
					messageHandBoxProgress('开始保存数据','校验表单['+formTab.getComponent(i).title+']','开始校验表单['+formTab.getComponent(i).title+']');
					return;
				   }
			 try{	
				 if (!formTab.getComponent(i).form.isValid() ){
					 warningMsg('校验失败请检查表单['+formTab.getComponent(i).title+']');
					return ;
				};
		    }catch(e){		
			      
		       }
				
              }	  	
		}catch(e){		
		       Ext.MessageBox.hide();		   
		       alert('表单数据序列化错误，请检查表单定义是否正确！');
		      return false;
		   }

	      var url=getUrlByActionNameStr(context+'MapDAOUpdateAction.action');
		  alert(str);
	      var myAjax = getMyAjax('dd', url, str);	
		  
		   
}

function loadMapDAOOncomplete(){	
doOk();
}
function selectPersonAjaxOnsuccess(){
 doOk();
}
function selectPersonAjaxonfailure(){
 doOk();
}

//保存
function saveMapDAOOnsuccess(){	
 Ext.MessageBox.hide();
  doOk();
};

//批量删除
function deleteAll(grid){
	var selections=grid.getSelections();
	   if (selections.length==0){
	          alertMsg('请选择需要删除的公文');
	   return;
	   }
 
	var delAllList=new Array();
	  for(var i=0;i<selections.length;i++){
	   if (selections[i].get('processInstId').length>0 ){     
	    delAllList[delAllList.length]= selections[i].get('processInstId');    
	   }else{
	    Ext.getCmp(gridId).getSelectionModel().deselectRow(selections[i].get('index'));   
	   }
	  };
	   var processInstIdList=delAllList.join();
	   var str="processInstIds="+processInstIdList;
	   functionName="delAll";
	   var url=getUrlByActionNameStr(context+'demodelete.action');
	   var myAjax = getMyAjax('dd', url, str);	
}


//批量中止
function abortAll(grid){
	var selections=grid.getSelections();
	   if (selections.length==0){
	          alertMsg('请选择需要中止的内容');
	   return;
	   }
 
	var abortAllList=new Array();
	  for(var i=0;i<selections.length;i++){
	   if (selections[i].get('processInstId').length>0 ){     
	    abortAllList[abortAllList.length]= selections[i].get('processInstId');    
	   }else{
	    Ext.getCmp(gridId).getSelectionModel().deselectRow(selections[i].get('index'));   
	   }
	  };
	   var processInstIdList=abortAllList.join();
	   var str="processInstIds="+processInstIdList;
	   functionName="delAll";
	   var url=getUrlByActionNameStr(context+'demoAbort.action');
	   var myAjax = getMyAjax('dd', url, str);	
}


//删除
function abort1(processInstId){
	   var str="processInstIds="+processInstId;
	   functionName="delAll";
	   var url=getUrlByActionNameStr(context+'demoAbort.action');
	   var myAjax = getMyAjax('dd', url, str);	
}




 //阅必
function BPM_EndRead(activityInstId){
	   messageHandBoxProgress('正在执行操作','正在执行操作','正在执行操作');
	    if (currGrid){
	  	refGridById(currGrid.id);
		}
	   functionName="endRead";
	   str="activityInstId="+activityInstId;
	   var url=getUrlByActionNameStr(context+'endRead.action');
	   var myAjax = getMyAjax('dd', url, str);	
}



 //批量签收
function signAll(currGrid){
	 var selections=currGrid.getSelections();	 
	   if (selections.length==0){
	   warningMsg('请选择需要签收的公文');
	   return;
	   }	  
	 var signList=new Array();
	  for(var i=0;i<selections.length;i++){
	   if (selections[i].get('signList').length>0 ){     
	    signList[signList.length]= selections[i].get('signList');    
	   }else{
	    currGrid.getSelectionModel().deselectRow(selections[i].get('index'));   
	   }
	  };
	   
	    var selections=currGrid.getSelections();
	    if (selections.length==0){    
	         warningMsg('在所选的列表中没有需要签收的公文');	     
	     return;
	   }	  
	   messageHandBoxProgress('签收操作','正处理签收操作','签收操作');
	   var signActivityInstIdList=signList.join();
	   var str="signActionIds="+signActivityInstIdList;
	   functionName="signall";
	   var url=getUrlByActionNameStr(context+'demosignfeceive.action');
	   var myAjax = getMyAjax('dd', url, str);	
}
//批量办理
function perFormAll(currGrid){
	  var selections=currGrid.getSelections();
	  if (selections.length==0){
	       alertMsg('请选择需要办理的公文');
	    return;
	   }
	  for(var i=0;i<selections.length;i++){
	   if (selections[i].get('performList').length==0 ){  
	     currGrid.getSelectionModel().deselectRow(selections[i].get('index'));     
	   }
	  };
	  
	    var selections=currGrid.getSelections();
	    if (selections.length==0){    
	      warningMsg('在所选的列表中没有需要办理的公文');
	    
	   return;
	   }
	  var activityInstId=selections[0].get('performList');   
	  var nextUrl = context+"demodisplay.action?ActivityInstId="+activityInstId;  
	  openWin(nextUrl,activityInstId); 
}

//办理成功
function submitFormOnsuccess(){		
	if (Ext.getCmp('tabExtPersonformwin')){
     Ext.getCmp('tabExtPersonformwin').close();
     }
	 
	
CloseWinAndRerefGrid();
 alertMsg("发送成功");
//
//var performWinId=getCurDesktop().getManager().getActive().id;
// var sForm = document[performWinId+$(performWinId+'mainFormId').value+'form'];	 
// 
//	if (currGrid){		
//		var selections=currGrid.getSelections();
//		for(var i=0;i<selections.length;i++){
//		   if (selections[i].get('performList')==sForm['activityInstId'].value){       
//		     currGrid.getSelectionModel().deselectRow(selections[i].get('index'));     
//		   }
//		 };
//		 var selections=currGrid.getSelections();
//		 
//		if (selections.length>0){
//		  var activityInstId=selections[0].get('performList');
//		  var nextUrl = context+"demodisplay.action?ActivityInstId="+activityInstId;  
//		  Ext.MessageBox.show({
//		           title:"办理完毕",
//		           msg:"办理完毕",
//		           width:300,
//		           buttons : {           
//		           yes : "继续办理",
//		           no : "返回列表"
//		        },
//		           fn: fnbtn,
//		           value:activityInstId+";"+nextUrl,
//		           animEl:document.body,
//		           icon: Ext.MessageBox.QUESTION
//		       });
//		             
//		 }else{
//		   refGridById(currGrid.id);
//		 }
//	}//end currGrid
//	if (Ext.getCmp('tabExtPersonformwin')){
//     Ext.getCmp('tabExtPersonformwin').close();
//     }
//		
//		  var p=Ext.getCmp(sForm['activityInstId'].value);		 
//		   p.close();
//		   doOk();
//		  if (!currGrid || selections.length==0){
//		     	refGridAll();
//		        alertMsg("发送成功");
//		     }
};




//阅毕完成
function endReadOnsuccess(){
	functionName = "";	
	 CloseWinAndRerefGrid();
};
//退回完成
function RouteBackOnsuccess(){	
functionName = "";
	 CloseWinAndRerefGrid();
};
//收回完成
function TackBackOnsuccess(){
	functionName = "";	
	 CloseWinAndRerefGrid();
};





//执行动作操作后的出来
function CloseWinAndRerefGrid(){	
     getCurDesktop().getManager().getActive().close();
		   doOk();	   
		 if (!currGrid){
		 	
		 	 refGridById(currGrid.id);
		}else{
		
			 refGridAll();
		   //  alertMsg("操作成功");
		    }
};


function alertMsg(msg){
 Ext.MessageBox.show({
           title: '提示',
           msg: msg,
           width:300,
           buttons:  {           
           yes : "确定"
        },
           animEl: currGrid,
           icon: Ext.MessageBox.INFO
       });
}

function warningMsg(msg){
 Ext.MessageBox.show({
           title:"系统警告",
           width:300,
           msg: msg,
           buttons:  {           
           yes : "确定"
           },
          // animEl:currGrid,
          icon: Ext.MessageBox.WARNING
       });
 }
 
function errorMsg(msg){
 Ext.MessageBox.show({
           title: "系统错误",
            width:300,
           msg: msg,
           buttons:  {           
           yes : "确定"
           },
           animEl:currGrid,
           icon: Ext.MessageBox.ERROR     });
 }
 

 

function signallOnsuccess(){
 var selections=currGrid.getSelections();
 doOk();
 refGridAll(); 
 refGridById(currGrid.id);
 alertMsg('签收完毕!'+'共签收'+selections.length+'件未签收公文'); 
}


function delAllOnsuccess(){
 var selections=currGrid.getSelections();
  refGridById(currGrid.id);
  refGridAll();
 alertMsg('删除完毕!'+'共删除'+selections.length+'件公文'); 

}

function fnbtn(btn,nextUrl){
 if (btn=="yes"){ 
 var url=nextUrl.substring(nextUrl.indexOf(";")+1,nextUrl.length);
 var winid=nextUrl.substring(0,nextUrl.indexOf(";"));
 openWin(url,winid);  
 }else if (btn=="no"){
  refGridAll();
 }
}




function BPM_doRouteBack() {
		if (fdt_checkValid()==true){
	var sForm = document.frm;
	sForm.submit();
		}
}
function fdt_checkValid(){
   return true;
}




 function doOk(){
   Ext.MessageBox.hide();
   return true;
 }



  function doOk(msg,str){
   Ext.MessageBox.hide();
    return true;
 }
 
     
 function getUrlByActionNameStr(action) {
    var s = window.location.host;
     try {
        context = $F('serverAddr');
    } catch(e) {
    }
    var url = "http://" + s +action;
	

    return url;
}


//正在处理
function onloading() {

   try{
    eval(functionName + "Onloading()");
     }catch(e){
   //  doOk();
     //refMsgError();
     }
   
}
//装载完毕
function onloaded() {
    try{
    eval(functionName + "Onloaded()");
       }catch(e){
    // doOk();
    // refMsgError();
     }
}
//开始激活
function oninteractive() {
   try{
    eval(functionName + "Oninteractive()");
       }catch(e){
     //doOk();
    // refMsgError();
     }
}
//调用结束
function oncomplete() {
		
    eval(functionName + "Oncomplete()");
	
}
//数据成功返回
function onsuccess() {
     try{
	
    eval(functionName + "Onsuccess()");
       }catch(e){
     //doOk();
     
    // errorMsg('刷新当前页面失败请与管理员联系');
     }
}
//返回失败
function onfailure() {
 errorMsg('数据读取失败');
 eval(functionName + "Onfailure()");
}




function getMyAjax(mydiv, url, str) {
 
    var myAjax = new Ajax.Updater(
            mydiv,
            url,
    {
        method: 'post',
        parameters: str,
        evalScripts: true,
        onLoading:onloading,
        onLoaded: onloaded,
        onInteractive:oninteractive,
        onComplete:oncomplete,
        onSuccess:onsuccess,
        onFailure:onfailure
    });
    return myAjax;
}



function closewin(btn,nextUrl){
 if (btn=="yes"){ 
     var closewin="window.parent.location.replace('"+context+"clear.action?personId="+personid+"&event=reboot');"
	
   messageBoxprogress("正在退出系统请稍后...","正在退出系统请稍后...","正在退出系统请稍后...",'5','',closewin);
  
 }else if (btn=="ok"){
   var closewin="window.parent.location.replace('"+context+"clear.action?personId="+personid+"&event=shutdown');";
    messageBoxprogress("正在关闭系统请稍后...","正在关闭系统请稍后...","正在关闭系统请稍后...",'5','',closewin);
 }else {
   
 }
}
  
  
  
    
    
/******************************************************************form2ext**************************************/


var expressArr=[];

function  updateCell(cell){
	 var performWinId;
		try{
			 performWinId=EVAL.getWinId();
		}  catch(e){
			performWinId=getCurDesktop().getManager().getActive().id;
		}
	  var sForm = document[performWinId+$(performWinId+'mainFormId').value+'form'];	 
	  
	  var celldom;
	  if (cell.dom){
	  	celldom=cell.dom;
	  	 str = "rawUpdataList[0]="+cell.dom.name+"&"+cell.dom.name+"="+cell.dom.value+"&activityInstId="+sForm['activityInstId'].value	 ;
		    if (celldom.isInstProcess && celldom.isInstProcess =='true'){
	  	      str =str+ "&rawUpdataProcessList[0]="+cell.dom.name;
	      }
	  }else{
	    celldom=cell.getEl().dom;
		 str = "rawUpdataList[0]="+cell.getName()+"&"+cell.getName()+"="+cell.getValue()+"&activityInstId="+sForm['activityInstId'].value;
		   if (celldom.isInstProcess && celldom.isInstProcess =='true'){
		   	
	  	    str =str+ "&rawUpdataProcessList[0]="+cell.getName();
			
	      }
	  }
	
	  
	
      var url = context + "updatacell.action";
      var myAjax = getMyAjax('routelog', url, str);
  }

 
 function setDivInline(node){
      if(node.style.display!="none"){
	    node.style.display="inline"
      }
 }   
 
  //加载数据配置页面后的回调函数
function callFormFn(){
      var body=arguments[0];
	  var text=arguments[2].responseText
     try{
      text.evalScripts();
      }catch(e){
      }
	  var expressArr=[];
	  handelForm(arguments[0].child("form"),'',expressArr);
	  
	  if (functionName =="saveMapDAO"){
	  	setTimeout("save()",1000)
	  }
	  
	  functionName = "putexpress";
	  var performWinId;
		try{
			 performWinId=EVAL.getWinId();
		}  catch(e){
			performWinId=getCurDesktop().getManager().getActive().id;
		}
   
	  var sForm = document[performWinId+$(performWinId+'mainFormId').value+'form'];	 
	  str = expressArr.join("&")+"&activityInstId="+sForm['activityInstId'].value;

      var url = context + "putexpressAction.action";
	
      var myAjax = getMyAjax('routelog', url, str);
	  
	  if (Ext.get(getActiveFormId())){
	  	//var attachid = Ext.get("attach").dom.parentNode.pid;
		showAccachWin();
	  };
	  setTimeout("setFormTabHeight()",2000);
	  //alert(formTab.getActiveTab().getInnerHeight());
	  //formTab.getActiveTab().setHeight(formTab.getActiveTab().getFrameHeight()) 
	
	  

}

function setFormTabHeight(){
	var height=formTab.getActiveTab().getFrameHeight();
	
	//formTab.getActiveTab().setHeight(height);
	//formTab.getActiveTab().setHeight(formTab.getActiveTab().getInnerHeight());
	//alert(formTab.getActiveTab().getInnerHeight());
	formTab.setHeight(formTab.getActiveTab().getInnerHeight()+100);
	
}


	
//把form中的元素替换成ext的对应元素
function handelForm(form,cfg,expressArr){
 
  if(!form){
    return;
  }
  var els=form.select("input,select,textarea");
  var len=els.getCount();
 
  var newFieldsCfg=[];
  var hasFile=false;
  for(var i=0;i<len;i++){
  
    var node=els.item(i);
    var dom=node.dom;
    if(!dom.type || dom.type!="hidden"){
		//alert(dom.outerHTML);
      dom.id=Ext.id();
    }
    var nothandle=dom.nothandle;
    if(!nothandle||nothandle!="true"){
      var type=dom.cusType?dom.cusType:(dom.tagName=="INPUT"?dom.type:dom.tagName.toLowerCase());
     
	  if(type=="file"){
        hasFile=true;
      }
      if(type && type!="br" && type!="p" && type!="div"){
        var tmp={};
        tmp.id=dom.id;
		tmp.pid=dom.pid;
		tmp.theoremexpression=dom.theoremexpression;
		tmp.datatype=dom.datatype;
		tmp.value=dom.value;
        tmp.name=dom.name;
        tmp.type=type;
        tmp.node=node;
        tmp.xy=node.getXY();
        tmp.w=node.dom.offsetWidth;
        tmp.h=node.dom.offsetHeight;		
        newFieldsCfg.push(tmp);
      }
    }
  }
  
  

  var fm2 = new Ext.form.BasicForm(form.id,{fileUpload:hasFile});
  formTab.getActiveTab().form=fm2;

  for(var i=0;i<newFieldsCfg.length;i++){
    var innerConfig=newFieldsCfg[i];
    var theoremexpression=innerConfig.theoremexpression;
    var type=innerConfig.type;
	var pid=innerConfig.pid;
	var datatype=innerConfig.datatype;
	var value=innerConfig.value;
	
    var fId=innerConfig.id;
	//  var fId=Ext.id;
	
    var name=innerConfig.name;
    var nod=innerConfig.node;
    var xy=innerConfig.xy;
    var w=innerConfig.w;
    var h=innerConfig.h;
    var newField=null;
    var c={id:name,pid:pid,value:value,msgTarget:'qtip'};
    var isInline=false;
	
    if(cfg){    
      if(cfg.nameCfg[name]){
        c=cfg.nameCfg[name];
      }else if(cfg.idCfg[name]){
        c=cfg.idCfg[name];
      }
	  
    }
	
	var tempc={};
	
	if (datatype){		  
	     eval("Ext.applyIf(tempc,"+datatype+") ")  ;
		if (tempc.HtmlEditType){
	  	   type="htmleditor";
	    }else if (tempc.TextareaType){
	  	   type="textarea";
		}else if (tempc.OpinionType){
	  	   type="opinion";
	    }else if(tempc.DateType){
		type="date";
		}else if(tempc.TextType){
		type="text";
		 }else if(tempc.TimeType){
		type="time";
	    }else if(tempc.NumberType){
		type="number";
		}	
	  }
	 
	
			 //准备后台公式	
	if (theoremexpression && name){
		var name=name;
		if (name.indexOf("hidden")==0){
			name=name.substring(6,name.lenght);
			type="combo";
		}
		var expressStr="cellList["+expressArr.length+"].type="+type+"&cellList["+expressArr.length+"].key="+name+"&cellList["+expressArr.length+"].express="+encodeURIComponent(theoremexpression);
	     expressArr.push(expressStr);
	}
	 
	
	try{
	 
	   if(type=="htmleditor"){
		 	if(tempc.HtmlEditType){
				Ext.apply(c,tempc.HtmlEditType);
			}	
		  	newField=new Ext.form.HtmlEditor(Ext.apply(c,{applyTo:fId}));
	
	    }else if(type=="number"){
		 	if(tempc.NumberType){
				Ext.apply(c,tempc.NumberType);
			}
		  	newField=new  Ext.form.NumberField(Ext.apply(c,{applyTo:fId}));
		 }else if(type=="opinion"){
		 	if(tempc.OpinionType){
				Ext.apply(c,tempc.OpinionType);
			}
		   newField=new Ext.form.TextArea(Ext.apply(c,{applyTo:fId}));
		   
	    }else if(type=="time"){
		 	if(tempc.TimeType){
				Ext.apply(c,tempc.TimeType);
			}			
		newField=new Ext.form.TimeField(Ext.apply(c,{applyTo:fId}));
			
	    }else if(type=="text"||type=="password"){
			if(tempc.TextType){
				Ext.apply(c,tempc.TextType);
			}	
			 newField=new Ext.form.TextField(Ext.apply(c,{applyTo:fId}));
			          
	    }else if(type=="date"){
			if(tempc.DateType){
				Ext.apply(c,tempc.DateType);
			}
	      newField=new Ext.form.DateField(Ext.apply(c,{applyTo:fId}));
	      setDivInline(newField.getEl().dom.parentNode);
	    }else if(type=="select"){
			//处理下拉框
			var select=Ext.get(fId).dom;	
			var selectArr=[];
			for (var l = 0; l < select.length; l++) {
				var combo={};
				Ext.apply(combo,{value:select[l].value});
				Ext.apply(combo,{text:select[l].text});
				selectArr.push(combo);
				}
			 var jsonStore = new Ext.data.SimpleStore({
			    fields: [{name: 'text', mapping:'text'},{name: 'value', mapping:'value'}],
			    data: selectArr
		       });
		    
			  jsonStore.loadData(selectArr);
			  if (select.multiple || select.multiple=='true' ){
			  newField = new Ext.ux.ItemSelector(Ext.apply(c,{
					transform:fId,
					disabled:select.disabled ? select.disabled:false,
					xtype:"itemselector",
				    dataFields:["code", "desc"],
					msWidth:300,
					msHeight:150,
					valueField:"code",
					displayField:"desc",
					imagePath:context+"js/ext/multiselect",
					toLegend:"已选项",
					fromLegend:"待选项"
					
				 }));
			
			  }else{
			   newField = new Ext.form.ComboBox(Ext.apply(c,{  
			   transform:fId,
			   disabled:select.disabled ? select.disabled:false,
		       store: jsonStore,
		       displayField: 'text',
		       valueField: 'value',
			   width:select.offsetWidth>50?select.offsetWidth:50,
		       mode: 'local',
		       typeAhead: true, //自动将第一个搜索到的选项补全输入
		       triggerAction: 'all',
		       emptyText: '全部',
		       selectOnFocus: true,
		       forceSelection: true
		     }));
			 
			 
		     }
			  setDivInline(newField.getEl().dom.parentNode);
			  setDivInline(newField.getEl().dom.parentNode.parentNode);
			  setDivInline(newField.getEl().dom.parentNode.parentNode.parentNode);
			  setDivInline(newField.getEl().dom.parentNode.parentNode.parentNode.parentNode);
		 
	    }else if(type=="radio"){
	      newField=new Ext.get(fId);
	      setDivInline(newField.dom.parentNode);
		  newField.on("click",function (){
				updateCell(this);
			}); 
			continue;
	    }else if(type=="checkbox"){
	          newField=new Ext.get(fId);
			    newField.on("click",function (){
				updateCell(this);
			}); 
			continue;
	          setDivInline(newField.dom.parentNode);
			  
	    //}else if(type=="password"){
	    //  newField=new Ext.form.TextField({applyTo:fId});
	      //setDivInline(newField.getEl().dom.parentNode);
	    }else if(type=="button"||type=="submit"||type=="reset"){
	      /*
	      var tmpId=fId+"-btnDiv";
	      var div=document.createElement("<div>");
	      div.id=tmpId;
	      nod.dom.parentNode.appendChild(div);
	      var tpl=null;
	      var btn=new Ext.Button({template:tpl,id:fId+"-btn",text:nod.dom.value,applyTo:tmpId,handler:function(){var id=this.id.replace(/-btn$/,"");document.getElementById(id).click();}});
	      nod.dom.style.display="none";
	      */
	    }else if(type=="file"){
	      //fm2.fileUpload=true;
	    }else if(type=="textarea"){
			if(tempc.TextareaType){
				Ext.apply(c,tempc.TextareaType);
			}	
	      newField=new Ext.form.TextArea(Ext.apply(c,{applyTo:fId}));
	    }		
	    if(newField){
				
			if (type=="htmleditor"){
				
				newField.on("sync",function (){
				checkHtmlLength(this);
			  }); 
				
			}
				
			if (type=="opinion"){
				newField=new Ext.get(fId);
						 
//			   if(newField.dom.value==""){
//				newField.dom.value="";
//				}
				
				var name=newField.dom.name;
				var parent=newField.dom.parentNode;
				var div_name=name+"-div";
				var el=Ext.fly(parent,'hidden');
				
				newField.hide();
				if (!newField.dom.readOnly || newField.dom.readOnly=='false'){
					el.insertHtml('beforeEnd',"<img src='+context+'/desktop/resources/images/user/bi.gif' width=25 height=25 onclick=javascript:_createComments(\""+name+"\")><div id="+div_name+">"+newField.dom.value+"</div>");	
				}else{
				    el.insertHtml('beforeEnd',"<div id="+div_name+">"+newField.dom.value+"</div>");	

				}
			}else{
				newField.on("change",
						function (){
						updateCell(this);
					    }
				  ); 
				  
				newField.width=w;
	            newField.height=h;
	            fm2.add(newField);

			}
			
		
	  
	    }
	
    }catch(e){
		
			//newField.regexText='转换出错请检查定义';
	}
	
  }

}
  
      function checkHtmlLength(newField){
	 		//alert(newField.getValue().length);
				if (newField.getValue().length>4000){
					 alertMsg('信息内容太大请使用附件上传');
					 newField.setValue(newField.getValue().substring(0,3000));
				}
		
	        }
  
	
	function returnHtml(name,html){
		//  newField=new Ext.get(fid);
		//  var parent=newField.dom.parentNode;
		var divname=name+"-div";
	      document.getElementById(divname).innerHTML=html;
	}
	
	

/**
 * @class Ext.ux.TreeCheckNodeUI
 * @extends Ext.tree.TreeNodeUI
 * 
 * 对 Ext.tree.TreeNodeUI 进行checkbox功能的扩展,后台返回的结点信息不用非要包含checked属性
 * 
 * 扩展的功能点有：
 * 一、支持只对树的叶子进行选择
 *    只有当返回的树结点属性leaf = true 时，结点才有checkbox可选
 * 	  使用时，只需在声明树时，加上属性 onlyLeafCheckable: true 既可，默认是false
 * 
 * 二、支持对树的单选
 *    只允许选择一个结点
 * 	  使用时，只需在声明树时，加上属性 checkModel: "single" 既可
 * 
 * 二、支持对树的级联多选 
 *    当选择结点时，自动选择该结点下的所有子结点，或该结点的所有父结点（根结点除外），特别是支持异步，当子结点还没显示时，会从后台取得子结点，然后将其选中/取消选中
 *    使用时，只需在声明树时，加上属性 checkModel: "cascade" 或"parentCascade"或"childCascade"既可
 * 
 * 三、添加"check"事件
 *    该事件会在树结点的checkbox发生改变时触发
 *    使用时，只需给树注册事件,如：
 *    tree.on("check",function(node,checked){...});
 * 
 * 默认情况下，checkModel为'multiple'，也就是多选，onlyLeafCheckable为false，所有结点都可选
 * 
 * 使用方法：在loader里加上 baseAttrs:{uiProvider:Ext.ux.TreeCheckNodeUI} 既可.
 * 例如：
 *   var tree = new Ext.tree.TreePanel({
 *		el:'tree-ct',
 *		width:568,
 *		height:300,
 *		checkModel: 'cascade',   //对树的级联多选
 *		onlyLeafCheckable: false,//对树所有结点都可选
 *		animate: false,
 *		rootVisible: false,
 *		autoScroll:true,
 *		loader: new Ext.tree.DWRTreeLoader({
 *			dwrCall:Tmplt.getTmpltTree,
 *			baseAttrs: { uiProvider: Ext.ux.TreeCheckNodeUI } //添加 uiProvider 属性
 *		}),
 *		root: new Ext.tree.AsyncTreeNode({ id:'0' })
 *	});
 *	tree.on("check",function(node,checked){alert(node.text+" = "+checked)}); //注册"check"事件
 *	tree.render();
 * 
 */

Ext.ux.TreeCheckNodeUI = function() {
	//多选: 'multiple'(默认)
	//单选: 'single'
	//级联多选: 'cascade'(同时选父和子);'parentCascade'(选父);'childCascade'(选子)
	this.checkModel = 'multiple';
	
	//only leaf can checked
	this.onlyLeafCheckable = false;
	
	Ext.ux.TreeCheckNodeUI.superclass.constructor.apply(this, arguments);
};

Ext.extend(Ext.ux.TreeCheckNodeUI, Ext.tree.TreeNodeUI, {

    renderElements : function(n, a, targetNode, bulkRender){
    	var tree = n.getOwnerTree();
		this.checkModel = tree.checkModel || this.checkModel;
		this.onlyLeafCheckable = tree.onlyLeafCheckable || false;
    	
        // add some indent caching, this helps performance when rendering a large tree
        this.indentMarkup = n.parentNode ? n.parentNode.ui.getChildIndent() : '';

        //var cb = typeof a.checked == 'boolean';
		var cb = (!this.onlyLeafCheckable || a.leaf);
        var href = a.href ? a.href : Ext.isGecko ? "" : "#";
        var buf = ['<li class="x-tree-node"><div ext:tree-node-id="',n.id,'" class="x-tree-node-el x-tree-node-leaf x-unselectable ', a.cls,'" unselectable="on">',
            '<span class="x-tree-node-indent">',this.indentMarkup,"</span>",
            '<img src="', this.emptyIcon, '" class="x-tree-ec-icon x-tree-elbow" />',
            '<img src="', a.icon || this.emptyIcon, '" class="x-tree-node-icon',(a.icon ? " x-tree-node-inline-icon" : ""),(a.iconCls ? " "+a.iconCls : ""),'" unselectable="on" />',
            cb ? ('<input class="x-tree-node-cb" type="checkbox" ' + (a.checked ? 'checked="checked" />' : '/>')) : '',
            '<a hidefocus="on" class="x-tree-node-anchor" href="',href,'" tabIndex="1" ',
             a.hrefTarget ? ' target="'+a.hrefTarget+'"' : "", '><span unselectable="on">',n.text,"</span></a></div>",
            '<ul class="x-tree-node-ct" style="display:none;"></ul>',
            "</li>"].join('');

        var nel;
        if(bulkRender !== true && n.nextSibling && (nel = n.nextSibling.ui.getEl())){
            this.wrap = Ext.DomHelper.insertHtml("beforeBegin", nel, buf);
        }else{
            this.wrap = Ext.DomHelper.insertHtml("beforeEnd", targetNode, buf);
        }
        
        this.elNode = this.wrap.childNodes[0];
        this.ctNode = this.wrap.childNodes[1];
        var cs = this.elNode.childNodes;
        this.indentNode = cs[0];
        this.ecNode = cs[1];
        this.iconNode = cs[2];
        var index = 3;
        if(cb){
            this.checkbox = cs[3];
            Ext.fly(this.checkbox).on('click', this.check.createDelegate(this,[null]));
            index++;
        }
        this.anchor = cs[index];
        this.textNode = cs[index].firstChild;
    },
    
    // private
    check : function(checked){
        var n = this.node;
		var tree = n.getOwnerTree();
		this.checkModel = tree.checkModel || this.checkModel;
		
		if( checked === null ) {
			checked = this.checkbox.checked;
		} else {
			this.checkbox.checked = checked;
		}
		
		n.attributes.checked = checked;
		tree.fireEvent('check', n, checked);
		
		if(this.checkModel == 'single'){
			var checkedNodes = tree.getChecked();
			for(var i=0;i<checkedNodes.length;i++){
				var node = checkedNodes[i];
				if(node.id != n.id){
					node.getUI().checkbox.checked = false;
					node.attributes.checked = false;
					tree.fireEvent('check', node, false);
				}
			}
		} else if(!this.onlyLeafCheckable){
			if(this.checkModel == 'cascade' || this.checkModel == 'parentCascade'){
				var parentNode = n.parentNode;
				if(parentNode !== null) {
					this.parentCheck(parentNode,checked);
				}
			}
			if(this.checkModel == 'cascade' || this.checkModel == 'childCascade'){
				if( !n.expanded && !n.childrenRendered ) {
					n.expand(false,false,this.childCheck);
				}else {
					this.childCheck(n);  
				}
			}
		}
	},

    
    // private
	childCheck : function(node){
		var a = node.attributes;
		if(!a.leaf) {
			var cs = node.childNodes;
			var csui;
			for(var i = 0; i < cs.length; i++) {
				csui = cs[i].getUI();
				if(csui.checkbox.checked ^ a.checked)
					csui.check(a.checked);
			}
		}
	},
	
	// private
	parentCheck : function(node ,checked){
		var checkbox = node.getUI().checkbox;
		if(typeof checkbox == 'undefined')return ;
		if(!(checked ^ checkbox.checked))return;
		if(!checked && this.childHasChecked(node))return;
		checkbox.checked = checked;
		node.attributes.checked = checked;
		node.getOwnerTree().fireEvent('check', node, checked);
		
		var parentNode = node.parentNode;
		if( parentNode !== null){
			this.parentCheck(parentNode,checked);
		}
	},
	
	// private
	childHasChecked : function(node){
		var childNodes = node.childNodes;
		if(childNodes || childNodes.length>0){
			for(var i=0;i<childNodes.length;i++){
				if(childNodes[i].getUI().checkbox.checked)
					return true;
			}
		}
		return false;
	},
	
    toggleCheck : function(value){
    	var cb = this.checkbox;
        if(cb){
            var checked = (value === undefined ? !cb.checked : value);
            this.check(checked);
        }
    }
});















//	调用示例
//  addbuddy = context+"jame/addPerson.action",//后台相应地址

//	addGroup : function(f,a){
//		var url = addbuddy;
//		var form=f.ownerCt.form;	

//		var evalJs = function(o){//定义回调方法
//			   Ext.namespace("EVAL");
//               eval(o);
//			   EVAL.addGroup();
//			}
//			JDS.ajax.Connection.LoadJsonFromUrl(url,evalJs,$H({name:form.findField('name').getValue()}));
//	}
//	
var JDS={
	ajax:{
		
	}
}	

JDS.ajax.Connection={
	
	/**
	 * Ajax调用链接参考注释示例
	 * @param {Object} _url//后台相应地址
	 * @param {Object} callback//定义回调方法
	 * @param {Object} args//POST 参数
	 */
	LoadJsonFromUrl : function(_url,callback,args){
			if(!args)
				args = new Hash();
	
			new Ajax.Request(_url, {
				method:"post",
				parameters : args.toQueryString(),
			  	onComplete: function(o) {
					if(o && o.responseText ){
						if (callback){
							callback(o.responseText);
						}
						
					}
			  	},
			  	onFailure : function(transport) {
					Ext.Msg.show({
				 		title:'服务器错误',
				 		msg: '与服务器通讯失败!',
				 		buttons: Ext.MessageBox.OK,
				 		fn:callback,
						icon:Ext.MessageBox.ERROR
					});
			  	}
			});
		},
		
	
	
		
	/**
	 * 在桌面环境下调用AJAX方法 集成了saveComplete提示！
	 * @param {Object} _url
	 * @param {Object} callback
	 * @param {Object} args
	 * @param {Object} desktop 
	 */
	LoadJsonFromUrlByDeskTop : function(_url,callback,args,desktop){
		if (!desktop){
			desktop=getCurDesktop();
		}
    	var notifyWin = desktop.showNotification({
			html: '正在处理'
			, title: '请等候'
		});
			if(!args && !(typeof(args)=='String') ){
					args = new Hash();
			}
		

		new Ajax.Request(_url, {
			method:"post",
			parameters : typeof(args)=='String'?args:args.toQueryString(),
			onComplete: function(o) {
					if(o && o.responseText){
						saveComplete(' 完成', '操作成功',o.responseText);
					}
			  	},
		    success: function(o){
			    if(o && o.responseText && Ext.decode(o.responseText).success){
					
					saveComplete(' 完成', '操作成功',o.responseText);
				}else{
					saveComplete('失败', '无法联系到服务器保存失败',o.responseText);
				}
			},
			failure: function(){
				saveComplete('失败', '无法联系到服务器保存失败',o.responseText);
			},
			scope: this
		});
		
		function saveComplete(title, msg,callbackScope){
			notifyWin.setIconClass('x-icon-done');
			notifyWin.setTitle(title);
			notifyWin.setMessage(msg);
			desktop.hideNotification(notifyWin);
			if(callback){
				callback(callbackScope);
			}
		}	
	}
		
		
}

	







