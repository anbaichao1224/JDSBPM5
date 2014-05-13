var _targetObj=document.documentElement;
function _setHeadAndFootWidth(left){
  var hObj=document.getElementById("header");
  var fObj=document.getElementById("footer");
  var nObj=document.getElementById("navigator");
    var bObj=document.getElementById("buttons");

  var w=hObj.offsetWidth;
  var all=document.getElementById("page");
  if(left+w>all.offsetWidth){
    left=all.offsetWidth-w;
  }
  fObj.style.width=w;
  nObj.style.width=w;
  
  hObj.style.left=left;
  fObj.style.left=left;
  nObj.style.left=left;
    bObj.style.left=left;
}

function _resetDivSize(){
  _setHeadAndFootWidth(_targetObj.scrollLeft);
}
function _resetHeadSize(){
  var sTop=_targetObj.scrollTop;
  var sLeft=_targetObj.scrollLeft;
  _setHeadAndFootWidth(sLeft);
}

function _setDivInitSize(){
  var all=document.getElementById("page");
  var allH=all.offsetHeight;
  var allW=all.offsetWidth;
  
  var mainTab=document.getElementById("main").childNodes[0];
  
  var sWidth=document.documentElement.clientWidth;
  var sHeight=document.documentElement.clientHeight;

  if(sHeight<=0){
    sWidth=document.body.clientWidth;
    sHeight=document.body.clientHeight;
    _targetObj=document.body;
  }
  _targetObj.onscroll=_resetHeadSize;
  
  var dH=allH-mainTab.offsetHeight;
  
  mainTab.style.height=Math.max(sHeight,allH)-dH;
  
  _setHeadAndFootWidth(0);
}
