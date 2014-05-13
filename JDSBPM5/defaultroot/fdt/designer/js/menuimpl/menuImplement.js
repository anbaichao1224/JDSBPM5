var btn2AttNameMap={
    font_select:"font-family",
    fontsize_select:"font-size",
    fontB:"font-weight",
    fontI:"font-style",
    fontU:"text-decoration",
    alignCenter:"text-align",
    alignRight:"text-align",
    alignLeft:"text-align"
};



function saveAndGo(url){
	 var fileName=$F('formId');
    if(!fileName){
        alert("没有文件可保存");
        return ;
    }

		try{
			 Ext.MessageBox.alert("正在保存请稍候...","正在保存请稍候...<br><img src='/desktop/resources/images/login.jpg'>");
		}catch(e){
			
		}
		 
    Ext.Ajax.request(
    {
        url: context+'design/fdtfileSave.action',
        params:'&fileName='+$F('fileName')+'&formId=' + $F('formId') + '&random=' + new Date()+'&activityDefId='+$('activityDefId').value,
       
	    success: function(rs){
            if(rs.responseText=="OK"){
				 window.location.href=url;
            }
        },
        failure: function(){alert("get file error!");}
    });
}

function CellOnClick(cell)
{
    try{
        setBtnStatusByCell(cell);
    }catch(e){}
    try
    {
        if(TreeData.tree){
            var id=cell.pid.replace(/\[(\d+)\]/,"$1");
            selectNodeById(id);
        }
        
    }catch(e){}
    return true;
}
function CellDbOnClick(cell)
{
    //var id = cell.id.replace('[','').replace(']','');
    var id = cell.pid;
    editAllAtt(id,"cell");
    return true;
}



function selectbolck(){
    if (windowf._selectionObj){
        windowf.disableBlockSelection();
        windowf._selectionObj=null;
    } else{
    windowf.enableBlockSelection();
    }
}

function getLayOutTree()
{
    Ext.Ajax.request(
    {
        url: context+'design/fileLayout.action',
        params:'&fileName=' + $F('currentOpenPage') + '&random=' + new Date(),
        success: function(rs){
            alert('success');
        },
        failure: function(){alert("get file error!");}
    });
}




function menu_edittmp()
{

     window.open ($("editurl").value, '', 'height=740, width=1000,  toolbar=no,resizable=yes, menubar=no')  ;
}

function menu_editjspurl()
{
	//alert($("runurl").value);
     window.open ($("runurl").value, '', 'height=740, width=1000,  toolbar=no,resizable=yes, menubar=no')  ;
}
function menu_clear()
{
     alert("暂未实现请先刷新页面刷新缓存");
}
function menu_copy()
{
     alert("暂未实现打开源码目录手工复制");
}

//菜单和工具栏事件处理
function toolbarclickeventhander(itemId,itemValue)
{ 
   
  
	eval("(" + itemId +"(itemId,itemValue))");
}
function menuclickeventhander(itemId,itemValue)
{
	
    eval("menu_" + itemId +"()");
}

/******************菜单事件响应************************/
function menu_save()
{
    save();
}


function menu_open(){
    openFileListWin();
}
function menu_createEmptyLayoutTree(){
    createEmptyLayoutTree();
}
function menu_resetLayoutMenu(){
    resetLayoutBtn();
}
function menu_createLayoutTree(){
    createLayoutTree();
}
function menu_generateCode(){
    generateCode();
}



/*****************************工具栏按钮事件响应****************************/
//保存当前报表状态(持久化)
function save(){

    var fileName=$F('formId');
    if(!fileName){
        alert("没有文件可保存");
        return ;
    }
	
		// alert('&fileName='+$F('fileName'));
		 
    Ext.Ajax.request(
    {
        url: context+'design/fdtfileSave.action',
        params:'&fileName='+$F('fileName')+'&formId=' + $F('formId') + '&random=' + new Date()+'&activityDefId='+$('activityDefId').value,
       
	    success: function(rs){
            if(rs.responseText=="OK"){
                alert("保存成功");
            }
        },
        failure: function(){alert("get file error!");}
    });
}

//根据布局生成报表模型
function generateCode()
{        
    if(TreeData && TreeData.tree)
    {
	    var obj = TreeData.getJsonObj();	    
	  	obj = Ext.encode(obj);
		//alert(obj);
	  	if(obj)
	  	{
		  Ext.Ajax.request(
		    {
		        url: context+'manage/reportcodegen.action',
		        params:{formId:'root', reset:true, jsonStr:obj},		       
		        method:'post',
		        //jsonData:{jsonData:obj},
		        success: function(rs){
		            alert('The Report Data Model have been generated successfully!');
		        },
		        failure: function(){
		        	alert("The Report Data Model have been generated failure!");
		        }
		    });		  
	    }
	    else
	    {
	    	alert("Error: Cant't take <Layout> node");
	    }
    }
    else
    {
    	alert("请先 生成布局树!");
    }
}



//生成并显示当前表格的布局树
function createLayoutTree(){

    //openExpressionEditor();
    //getLayOutTree();
//    if(!windowf.layoutSaved){
//        alert("请先 设置并保存布局!");
//        return;
//    }
//    
   

    if($F('formId')){
	   if(TreeData.tree){
	        resetObjDiv(TreeData.tree,"layoutTree");
	       TreeData.tree=null;
	   }
	   var layoutDataUrl=context+"design/fdtfileLayout.action?formId=" + $F('formId')+'&activityDefId='+$('activityDefId').value;
	   
	   
	//     var layoutDataUrl=context+'SelectPerformerJAON.action?routeDefId=4AAC53E0-6428-11DD-93E0-9B53336D46F4&activityInstId=610CC790-7B3A-11DD-A383-B509D5D387A4';
	
	   constructLayoutTree(layoutDataUrl);
	   var obj=Ext.getCmp('layoutTreePanel');
	   //obj.collapse();
       obj.expand();
    }else{
        alert("请先打开文件");
    }
}

//生成一个基本的布局树,暂时没用
function createEmptyLayoutTree(){
    if(TreeData.tree){
	  resetObjDiv(TreeData.tree,"layoutTree");
	  TreeData.tree=null;
	}
    constructLayoutTree();
    var obj=Ext.getCmp('layoutTreePanel');
    obj.expand();
}

//重设布局按钮单击处理
function resetLayoutBtn(){
    if(windowf.layoutSaved){
        resetLayout();
        if(TreeData.tree){
            resetObjDiv(TreeData.tree,"layoutTree");
    	    TreeData.tree=null;
        }
    }else{
        alert("布局未保存,不必重置");
    }
}

function open(){
    menu_open();
}

/* ******************以下是style的工具栏快捷处理********** */

function font_select(itemId,itemValue){
    var tmp=btn2AttNameMap[itemId];
    saveAndSetAttValue(tmp,itemValue);
}
function fontsize_select(itemId,itemValue){
    var tmp=btn2AttNameMap[itemId];
    saveAndSetAttValue(tmp,itemValue);
}

function fontB(itemId,itemValue){
    var tmp=btn2AttNameMap[itemId];
    var s=atableTool.getItem(itemId).getState();
    var str="";
    if(s==0){
        str="normal";
    }else{
        str="bold"
    }
    saveAndSetAttValue(tmp,str);
}
function fontI(itemId,itemValue){
    var tmp=btn2AttNameMap[itemId];
    var s=atableTool.getItem(itemId).getState();
    var str="";
    if(s==0){
        str="normal";
    }else{
        str="italic"
    }
    saveAndSetAttValue(tmp,str);
}

function fontU(itemId,itemValue){
    var tmp=btn2AttNameMap[itemId];
    var s=atableTool.getItem(itemId).getState();
    var str="";
    if(s==0){
        str="none";
    }else{
        str="underline"
    }
    saveAndSetAttValue(tmp,str);
}
function alignCenter(itemId,itemValue){
    atableTool.getItem("alignLeft").setState(0);
    atableTool.getItem("alignRight").setState(0);
    
    var tmp=btn2AttNameMap[itemId];
    var s=atableTool.getItem(itemId).getState();
    var str="";
    if(s==0){
        str="";
    }else{
        str="center"
    }
    saveAndSetAttValue(tmp,str);
}
function alignRight(itemId,itemValue){
    atableTool.getItem("alignLeft").setState(0);
    atableTool.getItem("alignCenter").setState(0);
    
    var tmp=btn2AttNameMap[itemId];
    var s=atableTool.getItem(itemId).getState();
    var str="";
    if(s==0){
        str="";
    }else{
        str="right"
    }
    saveAndSetAttValue(tmp,str);
}
function alignLeft(itemId,itemValue){
    atableTool.getItem("alignCenter").setState(0);
    atableTool.getItem("alignRight").setState(0);
    
    var tmp=btn2AttNameMap[itemId];
    var s=atableTool.getItem(itemId).getState();
    var str="";
    if(s==0){
        str="";
    }else{
        str="left"
    }
    saveAndSetAttValue(tmp,str);
}
/* ******************以上是style的工具栏快捷处理********** */
//目前做为测试用
function debugExpression(){
    //openExpressionEditor({},"kdsfj");
    //return;
    
}
// 测试用
function searchExpression(){
//    var obj=Ext.get("processorTreePanel");
//    alert(obj.getEl().dom.outerHTML);
  //var obj=document.getElementById("processorTreePanel");
 // alert(obj.outerHTML);

}


/****************表格行右键菜单响应***************************/

//设置表头
function rowCxtMenu_setTableHead(idx,itemId,itemValue){
    if(windowf.headEndIndex){
        alert("请先重设布局");
        return;
    }
    if(windowf.tmpLayoutTabHeadEnd){
        windowf.oldTabHeadEnd=windowf.tmpLayoutTabHeadEnd;
    }
    windowf.tmpLayoutTabHeadEnd=idx;
    var params={headBeginIndex:1,headEndIndex:idx};
    var params2={start:1,end:idx,type:"tabHead"};
    sendTableAttCmd(params,params2);
}

//设置列头
function rowCxtMenu_setColHead(idx,itemId,itemValue){
    if(windowf.rowEndIndex){
        alert("请先重设布局");
        return;
    }
    if(windowf.tmpLayoutTabHeadEnd){
        if(idx>windowf.tmpLayoutTabHeadEnd){
            if(windowf.tmpLayoutColHeadEnd){
                windowf.oldColHeadEnd=windowf.tmpLayoutColHeadEnd;
            }
            windowf.tmpLayoutColHeadEnd=idx;
            var params={rowHeadBeginIndex:(windowf.tmpLayoutTabHeadEnd+1),rowEndIndex:idx};
            var params2={start:(windowf.tmpLayoutTabHeadEnd+1),end:idx,type:"colHead"};
            sendTableAttCmd(params,params2);
        }else{
            alert("列头不能在表头内");
        }
    }else{
        alert("请先设置表头");
    }
}

//设置表尾
function rowCxtMenu_setTableFoot(idx,itemId,itemValue){
    if(windowf.footBeginIndex){
        alert("请先重设布局");
        return;
    }
    if(windowf.tmpLayoutColHeadEnd){
        if(idx>windowf.tmpLayoutColHeadEnd){
            if(windowf.tmpLayoutTabFootStart){
                windowf.oldTabFootStart=windowf.tmpLayoutTabFootStart;
            }
            windowf.tmpLayoutTabFootStart=idx;
            var params={footBeginIndex:idx,footEndIndex:windowf.tableRowEnd};
            var params2={start:idx,end:windowf.tableRowEnd,type:"tabFoot"};
            sendTableAttCmd(params,params2);
        }else{
            alert("表尾不能在表头或列头内");
        }
    }else{
        alert("请先设置列头");
    }
}

//编辑行属性
function rowCxtMenu_rowProperty(idx,itemId,itemValue){
    var id="row_"+idx;
    editAllAtt(id,"row");
}

//编辑行单元格属性
function rowCxtMenu_rowCellProperty(idx,itemId,itemValue){
    var id="row_"+idx;
    editAllAtt(id,"rowcell");
}

/****************表格列右键菜单响应***************************/
//设置行头
function colCxtMenu_setRowHead(idx,itemId,itemValue){
    if(windowf.colEndIndex){
        alert("请先重设布局");
        return;
    }
    if(windowf.tmpLayoutColHeadEnd){
        if(windowf.tmpLayoutRowHeadEnd){
            windowf.oldRowHeadEnd=windowf.tmpLayoutRowHeadEnd;
        }
        //idx=String.fromCharCode(parseInt(idx)+64);//转为字母
        //windowf.tmpLayoutRowHeadEnd=idx;
        windowf.tmpLayoutRowHeadEnd=String.fromCharCode(parseInt(idx)+64);
        var params={colHeadBeginIndex:1,colEndIndex:idx};
        var params2={start:"A",end:windowf.tmpLayoutRowHeadEnd,type:"rowHead"};
        sendTableAttCmd(params,params2);
    }else{
        alert("请先设置表头,列头,表尾");
    }
}

//编辑列属性
function colCxtMenu_colProperty(idx,itemId,itemValue){
    var id="col_"+idx;
    editAllAtt(id,"column");
}

//编辑列单元格属性
function colCxtMenu_colCellProperty(idx,itemId,itemValue){
    var id="col_"+idx;
    editAllAtt(id,"columncell");
}

/****************表格单元格右键菜单响应***************************/
//编辑单元格属性
function cellCxtMenu_contextEditProperty(cellStr,itemId,itemValue){
    editAllAtt(cellStr,"cell");
}
 

