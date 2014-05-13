
var _mdown=false;
var moved=false;
var _selectedTd=[];
var _lastTd;
var idPat=/([A-Z])\[(\d+)\]/;
var _curCell;

/*
var _cellDiv=document.createElement("<div onselectstart='return false;' onmouseup='_mUp()'>");
_cellDiv.id="tabDiv";
_cellDiv.style.position="absolute";
_cellDiv.style.backgroundColor="transparent";
_cellDiv.style.border="1 #0000ff solid";
_cellDiv.style.left="-100";
_cellDiv.style.top="-100";
*/

var _cellDivL=document.createElement("<div onmouseup='_mUp()'>");
_cellDivL.id="tabDivL";
_cellDivL.style.position="absolute";
_cellDivL.style.borderLeft="1 #0000ff solid";
_cellDivL.style.width=1;
_cellDivL.style.height=1;

var _cellDivT=document.createElement("<div onmouseup='_mUp()'>");
_cellDivT.id="tabDivT";
_cellDivT.style.position="absolute";
_cellDivT.style.borderTop="1 #0000ff solid";
_cellDivT.style.width=1;
_cellDivT.style.height=1;

var _cellDivR=document.createElement("<div onmouseup='_mUp()'>");
_cellDivR.id="tabDivR";
_cellDivR.style.position="absolute";
_cellDivR.style.borderLeft="1 #0000ff solid";
_cellDivR.style.width=1;
_cellDivR.style.height=1;

var _cellDivB=document.createElement("<div onmouseup='_mUp()'>");
_cellDivB.id="tabDivB";
_cellDivB.style.position="absolute";
_cellDivB.style.borderTop="1 #0000ff solid";
_cellDivB.style.width=1;
_cellDivB.style.height=1;

//_cellDiv.style.display="none";


function _displayAllDiv(flag){
  if(flag){
    _cellDivL.style.display="";
    _cellDivT.style.display="";
    _cellDivR.style.display="";
    _cellDivB.style.display="";
  }else{
    _cellDivL.style.display="none";
    _cellDivT.style.display="none";
    _cellDivR.style.display="none";
    _cellDivB.style.display="none";
  }
}

function _setDivPos(left,top,width,height){
  _cellDivL.style.left=left;
  _cellDivL.style.top=top;
  _cellDivL.style.width=1;
  _cellDivL.style.height=height;
  
  _cellDivT.style.left=left;
  _cellDivT.style.top=top;
  _cellDivT.style.width=width;
  _cellDivT.style.height=1;
  
  _cellDivR.style.left=left+width;
  _cellDivR.style.top=top;
  _cellDivR.style.width=1;
  _cellDivR.style.height=height;
  
  _cellDivB.style.left=left;
  _cellDivB.style.top=top+height;
  _cellDivB.style.width=width;
  _cellDivB.style.height=1;
  
}


function _splitId(id){
  var rr="",cc="";
  if(idPat.test(id)){
    cc=RegExp.$1;
    rr=RegExp.$2;
  }
  var tmp={};
  tmp["r"]=rr;
  tmp["c"]=cc;
  return tmp;
}

function _getCurElement(){
  var obj=window.event.srcElement;

  if(obj.tagName=="IMG"||obj.tagName=="SPAN"){
    var tmp=obj.parentNode;
    if(tmp.tagName=="DIV"){
      obj=tmp.parentNode;
    }
  }
  return obj;
}

function _mDown(obj){
  //alert(obj.outerHTML);
  if(event.button!=1){
    _mdown=false;
    _selectedTd=[];
    //alert(_selectedTd[0]);
    moved=false;
    return;
  }
  //document.body.appendChild(_cellDiv);
  document.body.appendChild(_cellDivL);
  document.body.appendChild(_cellDivT);
  document.body.appendChild(_cellDivR);
  document.body.appendChild(_cellDivB);
  //alert(window.event.srcElement.tagName);
  _selectedTd=[];
  moved=false;
  var obj=_getCurElement();
  
  if(obj.tagName=="TD"){
    //var idPat=/([A-Z])\[(\d+)\]/;
    if(idPat.test(obj.id)){
      _mdown=true;
      _selectedTd[0]=obj;
      _curCell=null;
    }
  }
}

function _mUp(obj){
  _mdown=false;
  var obj=_getCurElement();
  //alert(obj.tagName+"==="+obj.id);
  if(obj.tagName=="DIV"){
    if(obj.id.indexOf("tabDiv")==0){
      obj=_lastTd;
    }
  }
  
  //alert(obj.tagName+"----"+obj.id);
  
  if((obj.tagName=="TD"||(obj.tagName=="DIV"&&obj.id.indexOf("tabDiv")==0))&&obj!=_selectedTd[0]){
    _selectedTd[1]=obj;
  }else{
    _selectedTd[1]=null;
    _displayAllDiv(false);
  }
  
  if(obj==_selectedTd[0]){
    if((obj.colSpan>1||obj.rowSpan>1)&&moved){
      _displayAllDiv(true);
      _curCell=obj;
    }else{
      _displayAllDiv(false);
    }
  }
  //_heBinCell();
}

function _heBinCell(){
    //alert(_selectedTd[0].outerHTML+_selectedTd[1].id) ;
  if(_selectedTd[0]&&_selectedTd[1]){
    var id1=_selectedTd[0].id;
    var id2=_selectedTd[1].id;
    if(id1!=id2){
      
      //var idPat=/([A-Z])\[(\d+)\]/;
      var r1="",c1="",r2="",c2="";
      var rc=_splitId(id1);
      r1=rc.r;
      c1=rc.c;
      
      rc=_splitId(id2);
      r2=rc.r;
      c2=rc.c;
        
      var colspan=1,rowspan=1;
      var start="",end="",startC="",endC="";
      if(c1<=c2){
        startC=c1;
        endC=c2;
      }else{
        startC=c2;
        endC=c1;
      }
      
      
      var startR=Math.min(parseInt(r1),parseInt(r2));
      var endR=Math.max(parseInt(r1),parseInt(r2));
      
      start=startC+"["+startR+"]";
      end=endC+"["+endR+"]";
      
      var sObj=document.getElementById(start);
      var eObj=document.getElementById(end);
      if(eObj){
        if(startC.charCodeAt(0)+sObj.colSpan-1>endC.charCodeAt(0)+eObj.colSpan-1){
          endC=String.fromCharCode(startC.charCodeAt(0)+sObj.colSpan-1);
        }
        if(startR+sObj.rowSpan-1>endR+eObj.rowSpan-1){
          endR=startR+sObj.rowSpan-1;
        }
        end=endC+"["+endR+"]";
        
        
       //alert(start+"------"+end+"---"+document.getElementById(end));
      
        eObj=document.getElementById(end);
        colspan=Math.abs(start.charCodeAt(0)-end.charCodeAt(0))+eObj.colSpan;
        rowspan=endR-startR+eObj.rowSpan;
        
        sObj.colSpan=colspan;
        sObj.rowSpan=rowspan;
        
        for(var i=start.charCodeAt(0);i<start.charCodeAt(0)+colspan;i++){
          for(var j=startR;j<startR+rowspan;j++){
            var id=String.fromCharCode(i)+"["+j+"]";
            if(id!=start){
              var tmp=document.getElementById(id);//.style.display="none";
              if(tmp){
                tmp.parentNode.removeChild(tmp);
              }
              //document.getElementById(id).colSpan=1;
              //document.getElementById(id).rowSpan=1;
            }
          }
        }
      //  alert(sObj.id);
        _modMap(sObj.id,rowspan,colspan,"add");
      }
     
      //alert("cols=="+colspan+",rowspan==="+rowspan+" ,,,start=="+start+",end=="+end);
      //alert(document.getElementById("tab1").innerHTML);
    }
    
    _displayAllDiv(false);
  }else{
    alert("请选择需合并的多个单元格");
  }
}

function getAllParentValue(obj,att){
  var o=obj;
  var n=0;
  do{
    o=o.offsetParent;
    if(o){
      n+=o[att];
    }
  }while(o&&o.tagName!="BODY");
  return n;
}
function _mMove(obj){
  if(_mdown &&_selectedTd[0]){
  //alert(_selectedTd[0]);
    var obj=_getCurElement();
    
    if(obj.tagName=="TD"){
      moved=true;
      _lastTd=obj;
      var o=_selectedTd[0];
      
      var rootY=getAllParentValue(obj.parentNode.parentNode,"offsetTop");
      var rootX=getAllParentValue(obj.parentNode.parentNode,"offsetLeft");
      var x1,y1,x2,y2;
    
      if(o){
        x1=o.offsetLeft;
        y1=o.offsetTop;
        x2=obj.offsetLeft;
        y2=obj.offsetTop;
        
        var width,height,top,left;
        if(x1<x2){
          width=x2+obj.offsetWidth-x1;
          left=x1;
        }else{
          width=x1+o.offsetWidth-x2;
          left=x2;
        }
        
        if(y1<y2){
          height=y2+obj.offsetHeight-y1;
          top=y1;
        }else{
          height=y1+o.offsetHeight-y2;
          top=y2;
        }
        _setDivPos(left+rootX,top+rootY,width,height);
        _displayAllDiv(true);
        /*
        _cellDiv.style.width=width;
        _cellDiv.style.height=height;
        _cellDiv.style.top=top+rootY;
        _cellDiv.style.left=left+rootX;
        _cellDiv.style.display="";
        */
      }
    }
  }
}

/*
function mDblClick(){
  var obj=window.event.srcElement;
  _splitCell(obj);
}
*/

function _splitCell(){
  var obj=_curCell;
  if(!obj){
    alert("没有选中需拆分的单元格");
    return;
  }
  if(obj.tagName=="TD"){
    var id=obj.id;
    var col,row;
    var rc=_splitId(id);
    col=rc.c;
    row=rc.r;
  
    var colspan=obj.colSpan;
    var rowspan=obj.rowSpan;
    obj.colSpan=1;
    obj.rowSpan=1;
    
    _modMap(id,rowspan,colspan,"del");
    
    var rowAdded=false;
    for(var j=0;j<rowspan;j++){
      if(j>0){
        if(col=="A"){//是从第一列合并行的
          var o=obj.parentNode.nextSibling.childNodes[0];
          if(o){
            var td=o.cloneNode(true);
            td.colSpan=1;
            td.rowSpan=1;
            td.id=col+"["+row+"]";
            //td.innerText='abc-'+td.id;
            
            o.parentNode.insertBefore(td,o);
            obj=td;
          }else{//是整行合并的
            var curRow=obj.parentNode;
            for(var n=1;n<rowspan;n++){
              var nextRow=curRow.nextSibling;
              
              for(var k=0;k<colspan;k++){
                var tmpTd=obj.cloneNode(true);
                tmpTd.id=String.fromCharCode(col.charCodeAt(0)+k)+"["+(parseInt(row)+n-1)+"]";
                //tmpTd.innerText='abc-'+tmpTd.id;
                nextRow.appendChild(tmpTd);
              }
              curRow=nextRow;
            }
            break;
          }
        }else{
          var nodes=obj.parentNode.nextSibling.childNodes;
          var o=null;
          for(var n=0;n<nodes.length;n++){
            if(nodes[n].id.charCodeAt(0)>col.charCodeAt(0)){
              o=nodes[n];
              break;
            }
          }
          var td=obj.cloneNode(true);
          td.colSpan=1;
          td.rowSpan=1;
          td.id=col+"["+row+"]";
          //td.innerText='abc-'+td.id;
          
          if(o){
            o.parentNode.insertBefore(td,o);
          }else{
            obj.parentNode.nextSibling.appendChild(td);
          }
          obj=td;
        }
      }
      
      for(var i=1;i<colspan;i++){
        var td=obj.cloneNode(true);
        td.colSpan=1;
        td.rowSpan=1;
        td.id=String.fromCharCode(col.charCodeAt(0)+i)+"["+row+"]";
        //td.innerText='abc-'+td.id;
        
        var tmp=obj.nextSibling;
        if(tmp){
          obj.parentNode.insertBefore(td,tmp);
        }else{
          obj.parentNode.appendChild(td);
        }
        obj=td;
        //obj.insertAdjacentElement("afterEnd",td);
        //obj.parentNode.insertBefore(td,obj);
        //obj.parentNode.appendChild(td);
      }
      row=parseInt(row)+1;
    }
  }
  _displayAllDiv(false);
  _curCell=null;
}
function tabSelect(){
  if(_mdown)
    return false;
  else 
    return true;
}

/*
document.onmouseup=_mUp;
document.onmousedown=mDown;
document.onmousemove=_mMove;
*/

var _cellSpanMap={};
function _initCellSpanMap(){
  var tab=document.getElementById("tab1");
  var rows=tab.rows;
  for(var i=0;i<rows.length;i++){
    var cells=rows[i].cells;
    for(var j=0;j<cells.length;j++){
      if(cells[j].rowSpan>1||cells[j].colSpan>1){
        _modMap(cells[j].id,cells[j].rowSpan,cells[j].colSpan,"add");
      }
    }
  }
}
function _modMap(id,rowspan,colspan,flag){
  var r,c;
  var rc=_splitId(id);
  r=parseInt(rc.r);
  c=rc.c;
  rowspan=parseInt(rowspan);
  colspan=parseInt(colspan);
  for(var i=0;i<rowspan;i++){
    for(var j=0;j<colspan;j++){
      var tmpId=String.fromCharCode(c.charCodeAt(0)+j)+"["+(r+i)+"]";
      if(flag=="add"){
        _cellSpanMap[tmpId]=id;
      }else if(flag=="del"){
        _cellSpanMap[tmpId]=null;//=tmpId???
      }
    }
  }
}



/*
var _tabId=document.getElementById("formId").value;
var _oldTab=document.getElementById(_tabId).outerHTML;
var windowf = new dhtmlXGridFromTable(_tabId);
*/

function resetAll(){
  if(_oldTab){
    document.getElementById(windowf.entBox.id).outerHTML=_oldTab;
    windowf = new dhtmlXGridFromTable(_tabId);
  }
}