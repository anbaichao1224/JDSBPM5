
JDS.bpm.WorkList=function(grid){
	this.grid=grid;
	this.selections= function getGirdSelections(){
		var selections=grid.getSelections();
		   if (selections.length==0){
		          alertMsg('没有任何选择项');
		   return;
		   }
		var gridIdList=new Array();
		  for(var i=0;i<selections.length;i++){
		   if (selections[i].get('processInstId').length>0 ){     
		    gridIdList[gridIdList.length]= selections[i].get('processInstId');    
		   }else{
		    Ext.getCmp(gridId).getSelectionModel().deselectRow(selections[i].get('index'));   
		   }
		  };
		  
		return gridIdList;
	}
	
	//批量删除
	this.deleteAll=function deleteAll(){
		var gridIdList=getGirdSelections(grid);
		var evalJs = function(o){
			   Ext.namespace("EVAL");
	           //eval(o);
				refGridById(currGrid.id);
	            refGridAll();
	          alertMsg('删除完毕!'+'共删除'+selections.length+'件公文');
		}	  
		  JDS.ajax.Connection.LoadJsonFromUrlByDeskTop (context+'demodelete.action',evalJs,$H({processInstIds:gridIdList.join()}));
	}
	
	
	//批量中止
	this.abortAll=function abortAll(grid){
		var gridIdList=getGirdSelections(grid);
	  
		   	var evalJs = function(o){
				refGridById(currGrid.id);
	            refGridAll();
				doOk();
	          alertMsg('中止完毕!'+'中止完毕'+selections.length+'件公文');
		}	  
		  JDS.ajax.Connection.LoadJsonFromUrlByDeskTop (context+'demoAbort.action',evalJs,$H({processInstIds:gridIdList.join()}));
	}
	
	
	 //批量签收
	this.signAll=function signAll(currGrid){
			var gridIdList=getGirdSelections(grid);
	  
		   messageHandBoxProgress('签收操作','正处理签收操作','签收操作');
			   var evalJs = function(o){
				Ext.namespace("EVAL");
	           eval(o);
				refGridById(currGrid.id);
	            refGridAll();
				 doOk();
		}	  
		    JDS.ajax.Connection.LoadJsonFromUrlByDeskTop (context+'demosignfeceive.action',evalJs,$H({signActionIds:gridIdList.join()}));
	}

}
  
  
  
