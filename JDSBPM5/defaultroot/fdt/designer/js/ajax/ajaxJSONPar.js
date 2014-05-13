
//显示单元格属性面板
function getDataAndExecFn(param,fn,fn2){
      Ext.Ajax.request({
        url: param.url,
        params:param.params,
        success: function(rs){
            fn(Ext.decode(rs.responseText));
        },
        failure: function(){if(fn2){fn2(arguments[0]);}}
      });
    }


//ext发送ajax请求
function extSendAjax(url,params,method,success,fail){
    Ext.Ajax.request(
		    {
		        url: url,
		        params:params,		       
		        method:method?method:"GET",
		        success: success,
		        failure: fail
		    });		
}

//取得各布局属性的值
function getLayoutValue(){
    var tabHeadEnd=windowf.headEndIndex||windowf.tmpLayoutTabHeadEnd||-1;
    var colHeadEnd=windowf.rowEndIndex||windowf.tmpLayoutColHeadEnd||-1;
    var colHeadStart=colHeadEnd==-1?-1:(tabHeadEnd==-1?1:tabHeadEnd+1);
    var footStart=windowf.footBeginIndex||windowf.tmpLayoutTabFootStart||windowf.tableRowEnd+1;
    var footEnd=footStart>windowf.tableRowEnd?footStart:windowf.tableRowEnd;
    var rowHeadEnd=windowf.colEndIndex||windowf.tmpLayoutRowHeadEnd||"@";
    var obj={
        tabHead:{start:(tabHeadEnd==-1?-1:1),end:tabHeadEnd},
        colHead:{start:colHeadStart,end:colHeadEnd},
        tabFoot:{start:footStart,end:footEnd},
        rowHead:{start:(rowHeadEnd==-1?"@":"A"),end:rowHeadEnd}
    };
    return obj;
}


//设置表头,列头,行头,表尾等
function sendTableAttCmd(params,params2,success,fail){
    var url=context+'design/fdtfileUpdate.action';
    var str="";
    for(var key in params){
       if(str.length>0){
           str+="&";
       }
       str+="accessorRequest.parameters['tableAttribute."+key+"']="+params[key];
    }
    params=str+"&formId="+$F('formId')+'&activityDefId='+$('activityDefId').value+"&accessorRequest.type=table&accessorRequest.id[0]="+windowfTableName;
    if(!success){
        success=function(rs){
                    if(rs.responseText=="OK"){
                        setLayoutStyle(params2);
                    }else{
                        var msg=rs.responseText;
                        if(msg.length>100){
                            msg=msg.substring(0,100);
                        }
                        alert("操作失败2:"+msg);
                    }
                };
    }
    if(!fail){
        fail=function(rs){alert('操作失败1');};
    }
    extSendAjax(url,params,"post",
                success,
                fail
                );
}

//设置表头等时,改变显示样式
function setLayoutStyle(p){
    var type=p.type;
    var start=p.start;
    var end=p.end;
    if(type=="tabHead"){
        if(windowf.oldTabHeadEnd&&!windowf.tmpLayoutColHeadEnd){
            if(windowf.oldTabHeadEnd>end){
                setWindowfRowStyle(end+1,windowf.oldTabHeadEnd,"");
            }
        }
        setWindowfRowStyle(start,end,"HEAD");
    }else if(type=="colHead"){
        if(windowf.oldColHeadEnd){
            if(windowf.oldColHeadEnd>end){
                setWindowfRowStyle(end+1,windowf.oldColHeadEnd,"");
            }
        }
        setWindowfRowStyle(start,end,"HEAD");
    }else if(type=="tabFoot"){
        if(windowf.oldTabFootStart){
            if(windowf.oldTabFootStart<start){
                setWindowfRowStyle(windowf.oldTabFootStart,start-1,"");
            }
        }
        setWindowfRowStyle(start,end,"FOOT");
    }else if(type=="rowHead"){
        if(windowf.oldRowHeadEnd){
            if(windowf.oldRowHeadEnd>end){
                setWindowfColStyle(end,windowf.oldRowHeadEnd,"");//end+1
            }
        }
        setWindowfColStyle(start,end,"HEAD");
    }else{
        alert("类型错误");
    }
}

//重设布局
function resetLayout(){
    var params={
        headBeginIndex:"",
        headEndIndex:"",
        rowHeadBeginIndex:"",
        rowEndIndex:"",
        footBeginIndex:"",
        footEndIndex:"",
        colHeadBeginIndex:"",
        colEndIndex:""
    };
    sendTableAttCmd(params,null,function(rs){
                        if(rs.responseText=="OK"){
                            resetLayoutStyle();
                            resetLayoutValue();
                        }else{
                            var msg=rs.responseText;
                            if(msg.length>100){
                                msg=msg.substring(0,100);
                            }
                            alert("操作失败3:"+msg);
                        }
                    },null);

}

//重设各布局变量
function resetLayoutValue(){
    windowf.layoutSaved=false;   
    windowf.headEndIndex=null;
    windowf.tmpLayoutTabHeadEnd=null;
    
    windowf.rowEndIndex=null;
    windowf.tmpLayoutColHeadEnd=null;
    
    windowf.footBeginIndex=null;
    windowf.tmpLayoutTabFootStart=null;
    
    windowf.colEndIndex=null;
    windowf.tmpLayoutRowHeadEnd=null;
}

//把表头等的样式取消掉
function resetLayoutStyle(){
    var val=getLayoutValue();
    setWindowfRowStyle(1,val.colHead.end,"");
    setWindowfRowStyle(val.tabFoot.start,val.tabFoot.end,"");
    setWindowfColStyle(val.rowHead.start,val.rowHead.end,"");
}

//设置windowf的行的显示样式
function setWindowfRowStyle(start,end,className){
    if(windowf.tableRowEnd>=end&&end>=start&&start>0){
        var rows=windowf.obj.rows;
        for(var i=start;i<=end;i++){
            var row=rows[i];
            for(var j=1;j<row.cells.length;j++){
                var cell=row.cells[j];
                var c=cell.id.substring(0,1);
                if(c>windowf.tableColEnd){
                    break;
                }else{
                    cell.className=className;
                }
            }
        }
    }
}

//设置windowf的列的显示样式
function setWindowfColStyle(start,end,className){
    var value=getLayoutValue();
    var rStart=value.colHead.end;
    var rEnd=value.tabFoot.start;
    var s=start.charCodeAt(0);
    var e=end.charCodeAt(0);
    var rows=windowf.obj.rows;
    for(var i=s;i<=e;i++){
        for(var j=rStart+1;j<rEnd;j++){
            var cell=rows[j].cells[i-64];
            if(cell.id.substring(0,1)>end){
                break;
            }else{
                cell.className=className;
            }
        }
    }


//文件保存成功后的操作,目前只有对是否已布局的判断
function fileSaveSuccessHandler(){	
    if(windowf && !windowf.layoutSaved){
        if(windowf.tmpLayoutTabHeadEnd||windowf.tmpLayoutColHeadEnd||windowf.tmpLayoutTabFootStart||windowf.tmpLayoutRowHeadEnd){
            windowf.layoutSaved=true;
        }
    }
	}
	

}

//取单元格,行,列等各属性值,显示属性编辑面板
//type:cell,table,row,column
function editAllAtt(id,type){
	try{
    var ids=[];
    if(! (id instanceof Array)){
        ids=[id];
    }else{
        ids=id;
    }
    var idStr="";

    for(var i=0;i<ids.length;i++){
        idStr+="&accessorRequest.id["+i+"]="+ids[i];
    }
    var param = {url:context+'design/fdtfileToJson.action',params:'&formId='+ $('formId').value+'&fileName='+$F('fileName')+'&activityDefId='+$('activityDefId').value + '&accessorRequest.type='+type+idStr + '&radom=' + new Date};
		Ext.getCmp("attShowPanel").expand();
	}catch(e){
		alert(e);
	}
	

    getDataAndExecFn(param,createAttPanel,function(){alert('get attributes error');});
}

//点击工具栏,菜单等设置表格属性时的缓存保存和样式同步
function saveAndSetAttValue(name,value,ids){
  
	if(!ids){
        ids=getCurrentObjIds();
    }
    var url = context+'design/fdtfileUpdate.action';
    var pre="cellAttribute.style.";
    var par=keyEncode(pre+(name.replace(/\-/g,"_")))+"="+encodeURIComponent(value);
    for(var i=0;i<ids.length;i++){
        var id=ids[i];
        id=id.replace(/\[|\]/g,"");
        par+="&accessorRequest.id["+i+"]="+id;
    }
    par = par + "&formId=" + $F('formId')+'&activityDefId='+$('activityDefId').value ;
    par = par + "&accessorRequest.method=post";	
    extSendAjax(url,par,"post",function (rs){if(rs.responseText=="OK"){btnAndMenuStyleHandler(name,value,ids);}else{alert("操作失败4");}},function (){alert("操作失败5");});
}

//得到点击按钮或菜单等时的当前操作对象的id等,如当前选中单元格等
function getCurrentObjIds(){
    var rtn=[];
    var colIdx=windowf.getSelectedCellIndex();
    var rowId=windowf.getSelectedId();
    if(rowId){
        var tmp=rowId.replace(/r(\d+)/,"$1");
        if(colIdx!=-1){
            if(colIdx==0){
                var row=windowf.obj.rows[tmp];
                for(var i=1;i<row.cells.length;i++){
                    var id=row.cells[i].id;
                    if(id.substring(0,1)<=windowf.tableColEnd){
                       rtn.push(id);
                    }else{
                        break;
                    }
                }
            }else{
				
                rtn.push(String.fromCharCode(colIdx+64)+"["+tmp+"]");
            }
        }else{
            alert("出错了");
        }
    }else if(blockSelectCells.length>0){
        for(var i=0;i<blockSelectCells.length;i++){
            rtn.push(blockSelectCells[i].id);
        }
    }
    return rtn;
}

//设置表格的属性,主要是风格等,用作显示同步
function setAttValue(styles,ids){
    var str="";
    for(var a in styles){
        str+=a+":"+styles[a]+";";
    }
    if(str!=""){
        for(var i=0;i<ids.length;i++){
            var id=ids[i];
            id=id.replace(/(\w)\[?(\d+)\]?/,"$1[$2]");
            var obj=document.getElementById(id);
            if(obj){
                var cssText=obj.runtimeStyle.cssText;
                if(cssText==""){
                    cssText=obj.style.cssText;
                }
                obj.runtimeStyle.cssText=cssText+";"+str;
                //alert(obj.runtimeStyle.cssText);
            }
        }
    }
}

//属性面板值改变时的风格同步处理
function attPanelStyleHandler(data,ids){
    var pat=/\w+\.style\.(.+)$/i;
    var styles={};
    for(var a in data){
        if(pat.test(a)){
            var n=RegExp.$1;
            n=n.replace(/[_]/g,"-");
            styles[n]=data[a];
        }
    }
	
    setAttValue(styles,ids);
}

//属性或菜单栏点击时的风格同步处理
function btnAndMenuStyleHandler(name,value,ids){
    var style={};
    style[name]=value;
    setAttValue(style,ids);
}

//点击单元格时设置相应的工具栏按钮状态
function setBtnStatusByCell(cell){
    var style=cell.currentStyle;
    var value=style.fontWeight;
    
    if(value==900||value==700){
        atableTool.getItem("fontB").setState(1);
    }else{
        atableTool.getItem("fontB").setState(0);
    }
    value=style.fontStyle.toLowerCase();
    if(value=="italic"||value=="oblique"){
        atableTool.getItem("fontI").setState(1);
    }else{
        atableTool.getItem("fontI").setState(0);
    }
    
    value=style.textDecoration.toLowerCase();
    if(value=="underline"){
        atableTool.getItem("fontU").setState(1);
    }else{
        atableTool.getItem("fontU").setState(0);
    }
    
    value=style.fontFamily.toLowerCase();
    atableTool.getItem("font_select").setSelected(value);
    value=style.fontSize;
    value=parseInt(value);
    atableTool.getItem("fontsize_select").setSelected(value);
    
    value=style.textAlign.toLowerCase();
    atableTool.getItem("alignLeft").setState(0);
    atableTool.getItem("alignCenter").setState(0);
    atableTool.getItem("alignRight").setState(0);
    if(value=="left"){
        atableTool.getItem("alignLeft").setState(1);
    }else if(value=="center"){
        atableTool.getItem("alignCenter").setState(1);
    }else if(value=="right"){
        atableTool.getItem("alignRight").setState(1);
    }
}

function colWidthChangeHandler(obj,cell){
    var id="col_"+(cell.innerText.replace(/\s+/g,"").charCodeAt(0)-64);
    var url = context+'design/fdtfileUpdate.action';
    var pre="cellAttribute.style.";
    var par=keyEncode(pre+"width")+"="+encodeURIComponent(cell.offsetWidth);
    par+="&accessorRequest.id[0]="+id;
    par+="&accessorRequest.type=columncell";
    par = par + "&formId=" + $F('formId')+'&activityDefId='+$('activityDefId').value ;
    par = par + "&accessorRequest.method=post";
    extSendAjax(url,par,"post",function (rs){if(rs.responseText=="OK"){}else{alert("操作失败6");}},function (){alert("操作失败7");});
}






