

  var curRange;//当前选中区域
  var selectedIndex2=-1;//当前选中的提示索引


  var inited = false;
  var bodyTag="<head><style type=\"text/css\">body {font-size:12pt;line-height:16px;margin-top:0}</style><meta http-equiv=Content-Type content=\"text/html; charset=gb2312\"></head><body bgcolor=\"#FFFFFF\" MONOSPACE></body></html>";
  
  function iframeInit(){
  	//createTdIframe();
  	iframeDocInit();
  }
  
//  function createTdIframe(){
//  	var iframe=document.createElement('iframe');
////  	iframe.attchEvent("onload",addScroll)
//    iframe.name="HtmlEdit";
//    iframe.id="HtmlEdit";
//    iframe.MARGINHEIGHT="1";
//    iframe.MARGINWIDTH="1";
//    iframe.width="800";
//    iframe.height="350";
//    iframe.frameborder="0";
//    iframe.style.border="1px solid gray";
//    document.getElementById("iframetd").appendChild(iframe);
//
//	iframe=Ext.get(iframe);
//    //iframe.setStyle("border","1px solid gray");
//	iframe.on("load",addScroll);
//  }
  
  /*
  iframetd  <iframe  onblur="" onload="addScroll()"
                        name="HtmlEdit" id="HtmlEdit" MARGINHEIGHT="1" MARGINWIDTH="1"
                        width="800" height="350" frameborder="0"
                        style="border:1px solid gray">
                    </iframe>
  
  */
  
  /*
  文档加载时执行初始化操作,生成一些层,将编辑器置为可用
  */
  
  //document.onreadystatechange=function(){
  var iframeDocInit=function(){
    createDiv("HtmlEdit");//生成层

    if (!inited){
      //下面是初始化编辑器
      inited = true;

      HtmlEdit.document.open();
      HtmlEdit.document.write(bodyTag);
      HtmlEdit.document.close();
      HtmlEdit.document.designMode="On";
      
      HtmlEdit.document.onkeydown=function(){
        return keyDownHandler(HtmlEdit);
      };
      
      HtmlEdit.document.onkeyup=function(){
        return keyUpHandler(HtmlEdit);
      }
    }
  }
  
      
  function addScroll(){
    HtmlEdit.document.body.onscroll=function(){ 
      scrollHandler(HtmlEdit);
    }
  }
  
  
  
  
  
  function scrollHandler(obj){
    document.getElementById("linenum").scrollTop=obj.document.body.scrollTop;
  }
  
  
  
  
  
  
  /*
    编辑器的key按下事件,用来处理回车时的<p>问题及按向下键时显示提示菜单
  */
  function keyDownHandler(obj){
    var k=obj.event.keyCode;
    if(k==13){
      if(obj.event.ctrlKey){//查询
        doSearch();
        return false;
      }
      if(divIsView()){
        outSelection2(obj);
      }else{
        insertNewLine();
        hightColor();
      }
      return false;
    }else if(k==40||k==38){
      return chageSelection2(k);
    }
    else
    {
        //setPosition2mouse(null,obj.event);
    }
      
    return true;
  }
  
  /*
    编辑器的key释放事件,用来处理当前应对哪些内容进行提示
  */
  function keyUpHandler(obj){
    var k=HtmlEdit.event.keyCode;
    var shift=HtmlEdit.event.shiftKey;
    if(k==16||k==17||k==18){//shift,ctrl,alt键弹起时直接返回
      return true;
    }
    if(HtmlEdit.event.ctrlKey){//按ctrl键时,如果不是ctrl+v直接返回
      if(k!=86){
        return true;
      }
    }
 	if(shift){//按shift键选中时不处理直接返回
      if(k>=33&&k<=40){
        return true;
      }
 	}
    if (k==27||getCurText() == '') {//按esc键或内容为空时隐藏提示层
      hideDiv(true);
      return true;
    }
    curRange=document.selection.createRange();

    if(k!=13&&k!=40&&k!=38){
      loadtestAjax2(obj);
      if((k<33||k>40)){
        hightColor();
      }
    }
    if(k==38){
      if(!isSelectDiv()){
        loadtestAjax2(obj);
      }
    }

    scrollHandler(HtmlEdit);//处理编辑框中有滚动条时,删除数据行号不回滚的问题

    return true;
  }
  
  /*
    在此做提示信息层的处理
  */
  function chageSelection2(k){
    if(isSelectDiv()){
      keyUpOrDown(k);
      return false;
    }else if(k==40){//光标键向下
      if(divIsView()){
        //alert("down");
        keyUpOrDown(k);
        return false;
      }
    }
    return true;
  }

  /*
    取编辑器的当前光标前的内容
  */
  function getCurText(){
    var r = HtmlEdit.document.selection.createRange();
    var r1=r.duplicate();
    HtmlEdit.document.execCommand("selectall");
    var r2=HtmlEdit.document.selection.createRange();
    HtmlEdit.document.execCommand("unselect");
    r.setEndPoint("StartToStart",r2);
    r1.select();
    return r.text;
  }
  
  
  /*
    取编辑器的当前光标前的html内容
  */
  function getCurHtml(){
    var r = HtmlEdit.document.selection.createRange();
    var r1=r.duplicate();
    HtmlEdit.document.execCommand("selectall");
    var r2=HtmlEdit.document.selection.createRange();
    HtmlEdit.document.execCommand("unselect");
    r.setEndPoint("StartToStart",r2);
    r1.select();
    return r.htmlText;
  }
  
  /*
    取编辑器的当前光标后的内容
  */
  function getCurRightText(){
    var r = HtmlEdit.document.selection.createRange();
    var r1=r.duplicate();
    HtmlEdit.document.execCommand("selectall");
    var r2=HtmlEdit.document.selection.createRange();
    HtmlEdit.document.execCommand("unselect");
    r.setEndPoint("StartToEnd",r2);
    r1.select();
    return r.text;
  }
  
  /*
    取编辑器的当前光标后的html内容
  */
  function getCurRightHtml(){
    var r = HtmlEdit.document.selection.createRange();
    var r1=r.duplicate();
    HtmlEdit.document.execCommand("selectall");
    var r2=HtmlEdit.document.selection.createRange();
    HtmlEdit.document.execCommand("unselect");
    r.setEndPoint("StartToEnd",r2);
    r1.select();
    return r.htmlText;
  }
  
  
  /*
    取得编辑器中所有的内容
  */
  function getAllText(){
    return HtmlEdit.document.body.innerText;
  }
  
  /*
    取得编辑器中所有的内容的html文本
  */
  function getAllHtml(){
  	return HtmlEdit.document.body.innerHTML;
  }
  
  /*
    隐藏提示div
  */
  function hideDiv(flag){
    if(flag){
      document.getElementById('search_suggest').style.display = 'none';
    }else{
      document.getElementById('search_suggest').style.display = '';
    }
  }
  
  /*
  提示层是否是显示中
  */
  function divIsView(){
    return document.getElementById('search_suggest').style.display!="none";
  }
  
  /*
  当前是否是对提示层在操作(上下移动等)
  */
  function isSelectDiv(){
    if(divIsView()){
      if(selectedIndex2>-1){
        return true;
      }
    }
    return false;
  }
  
  //在编辑框中插入一个新行
  function insertNewLine(){
    insertHtml("<br>");
    insertDivRowNum();
  }
  
  /*
    在编辑器的当前光标位置插入一段html代码,现用来处理<p>问题的<br>
  */
  function insertHtml(str){
    var sel= HtmlEdit.document.selection.createRange();
    if(sel){
      sel.pasteHTML(str);
      sel.select();
    }
  }
  
  /*
  当编辑器中有新行时,如果行号小于编辑器中的行数则加行号,编辑器中的自动换行不考虑
  */
  function insertDivRowNum(){
    var div=document.getElementById("linenum");
    var h=div.innerHTML;
    var c=getAllRowCount();
    var m=h.match(/<br>/ig);
    var divLineCount=0;

    if(h){
      divLineCount=m.length;
    }
    while(divLineCount<c+1){
      divLineCount++;
      h+=(divLineCount)+"<br>";
    }
    div.innerHTML=h;
  }
  
  //取得编辑器中的行数
  function getAllRowCount(){
    var str=getAllHtml();
  	var s=str.match(/(<br>)/ig);
  	var c=1;
  	if(s){
  	  c+= s.length;
  	}
  	return c;
  }
/*****************************************************************************/
  //选中当前提示信息
  function outSelection2(o){
    if(selectedIndex2<0){
      selectedIndex2=0;
    }
	
//    var obj = document.getElementById("search_suggest");
//    var retval = obj.children[selectedIndex2].children(0).cells(0).innerText;
	
	var retval = document.getElementById("id"+selectedIndex2).value;
    setCurEditText(retval);
  }
  
  /*
  点击提示信息时调用
  */
  function setSearch(obj1, obj2) {
 
    setCurEditText(obj2.value);
  }

  /*
  将指定提示信息在编辑框中显示,在用鼠标选中赋值后,会不再响应键盘事件,须用鼠标单击编辑器后才好用,不知为何,可能是浏览器本身的事件响应问题
  */
  function setCurEditText(retval){
  	var str=getCurText();
  	var right=getCurRightText();
  	var p=/^((?:.|\s)*\W)(?:\w*)$/;
//  	var p=/^((?:.|\s)*(?:(?!=|['"])\W|(?:\w(?==))))(?:(?:=+['"]?)?\w*)$/;

    var equalEndPat=/=['"=]?\w*$/;
    var equalPat=/((?:.|\s)+?)=+['"]?[^'"{}=]*?$/;
  	var inEndPat=/\s+in\s*\{([^{}]*)$/;
  	var inPat=/^((?:.|\s)+\s+in\s*\{)(?:((?:.+?,)*\s*)([^{}]*))?$/;//in时 选中的替换
 
  	var h="";
  	if(str!=""){
  	  if(equalEndPat.test(str)){//先简单测试
  	    if(equalPat.test(str)){//处理=的情况
  	      h=RegExp.$1;
  	    }
  	  }else if(inEndPat.test(str)){//由于大量字符时的性能问题,先用简单正则简单测试一下
  	    if(inPat.test(str)){//处理in{的情况
  	      h=RegExp.$1+RegExp.$2;
  	      if(retval.substring(0,2)=="=="){
  	        retval=retval.substring(2);
  	      }
  	    }
  	  }else{
        if(p.test(str)){
  	      h=RegExp.$1;
  	    }
  	  }
  	}
  	if(h.substring(h.length-1)=="|"){
  	  h=h.substring(0,h.length-1);
  	}

    HtmlEdit.document.body.innerText=h+retval+right;
    
    var pos=0;
    pos=(h?h.length:0)+(retval?retval.length:0);

    pos=pos-getBrCount(h+retval);//在有换行时,光标的位置移动会出错,此为纠正
    
    document.getElementById("search_suggest").style.display = 'none';
    selectedIndex2 = -1;

	curRange.moveStart('character',pos)
    curRange.collapse(true);
    curRange.select();
    
    hightColor();//高亮
  }
  
  /*
  	取指定字符中的\r\n的个数,也就是windows的换行数
  */
  function getBrCount(str){
  	var p=/(\r\n)/g;
  	var s=str.match(p);
  	var c=0;
  	if(s&&s.length){
      c=s.length;
  	}
  	return c;
  }
  
  /*
  处理提示层里的上下移动
  */
  function keyUpOrDown(k){
    var obj = document.getElementById("search_suggest");
    if (obj.style.display == 'none') {
      return;
    } //alert('aa');
    var count=obj.children.length;
    var maxIndex = count - 1;
    
    if(k==40){
      selectedIndex2++;
    }else if(k==38){
      selectedIndex2--;
    }

    if (selectedIndex2 < 0) {
      selectedIndex2 = 0;
    }
    if (selectedIndex2 > maxIndex) {
      selectedIndex2 = maxIndex;
    }
    
    for (var intTmp = 0; intTmp <= maxIndex; intTmp++) {
      if (intTmp == selectedIndex2) {
        obj.children[intTmp].className = 'suggest_link_over';
      } else {
        obj.children[intTmp].className = 'suggest_link';
      }
    }
    if(selectedIndex2==0){
      obj.scrollTop=0;
    }
    if (k==40) {
      if (selectedIndex2 > 5) {
        obj.scrollTop += obj.scrollHeight/count;
      }
    }else {
      obj.scrollTop -= obj.scrollHeight/count;
    }
  }
  
  /*
  查询提示信息
  */
  function loadtestAjax2(obj){
    selectedIndex2=-1;

    var s = window.location.host;
    var url = getUrlStr4keyIndex();
    //alert(url);
    var value=getAllText();
    
    var leftKeyLength=getCurText().length;
    
    //value=escape(value);
    //value=encodeURI(value);
    
    value=encodeURIComponent(value);

    var txtSearch = "txtSearch=" + value;
    var objName = "objName=" + obj.name;
	
    var crrentIndex = "crrentIndex=" +  leftKeyLength;
    var str = txtSearch + "&" + objName + "&" +crrentIndex +"&esbKeyList="+esbKeyList+"&flowType="+flowType;
     //alert(obj.name);
    //alert($('keyindexdefalutHtmlEdit'));
    var myAjax = new Ajax.Updater(
          'keyindexdefalut' + obj.name,
          url,
          {
            method: 'post',
            parameters: str,
            evalScripts: true
          });
    return;
  }
  
/*****************************************************************************/

  function suggestOver(div_value) {}

  function suggestOut(div_value) {}
  
  var sum = "10";
  
  function oninit(objsum, name){
      sum = objsum;
      if(!name){
      	name="HtmlEdit";
      }

      var txtSearch = window[name].document.body.innerText;
      if (txtSearch == ""){
        document.getElementById('search_suggest').style.display = 'none';
        return;
      } else {
        document.getElementById('search_suggest').style.display = '';
        setPosition2(name)
      }
  }
  
  //提示层的位置
  function setPosition2(name){
    var obj=document.getElementById(name);
    var width = obj.offsetWidth;
    var left = getLength(obj,"offsetLeft");
    var top = getLength(obj,"offsetTop") + obj.offsetHeight;
    document.getElementById('search_suggest').style.left = left + 2 + "px";
    document.getElementById('search_suggest').style.top = top + "px";
    document.getElementById('search_suggest').style.width = width + "px";
    document.getElementById('search_suggest').style.border ="1px solid #000000";
  }

  function setPosition2mouse(name,event){
    if(!name)
    {
        name = "HtmlEdit";
    }
    var obj=document.getElementById(name);
    var width = obj.offsetWidth;
    var left = getLength(obj,"offsetLeft");
    var top = getLength(obj,"offsetTop") + obj.offsetHeight;
    document.getElementById('search_suggest').style.left = left + 2 + "px"; //alert(event.y);
    document.getElementById('search_suggest').style.top = e.y  + "px";
    document.getElementById('search_suggest').style.width = width + "px";
    document.getElementById('search_suggest').style.border ="1px solid #000000";
  }


  function getLength(obj,attr){
    var offset = 0;
    var item = obj;
    while (item){
      offset += item[attr];
      item = item.offsetParent;
    }
    return offset;
  }

/*****************************************************************************/

  var divCreated=false;
  
  function createDiv(obj){
    if(divCreated){
      return;
    }
    divCreated=true;
    var div = document.createElement("<DIV style='z-index:10000000'>");
    div.id = "search_suggest";
    div.style.position = "absolute";
    div.style.top="200";
    div.style.left="100";
    div.style.display="none";

    var divdefault = document.createElement("DIV");
    divdefault.id = "keyindexdefalut" + obj;

    var node = document.getElementById(obj);
    //node.parentNode.appendChild(div);
    //node.parentNode.appendChild(divdefault);
    document.body.appendChild(div);
    document.body.appendChild(divdefault);  
  }

/*****************************************************************************/

  //各需高亮的模式和颜色
  var keyPatterns=[
                   /\b(\d+)\b(?!'|")/g,  //数字
                   /((?:#this\b)|(?:&amp;){2}|(?:\|{2})|(?:\b(?:sum|iif|in|and|or|null|not)\b)|(?:\bm\([^)]*\)))/ig, //关键字
                   /((?:[$~]\w+)|(?:#\w+[.]))/g,         //根
                   /((?:'[^']*')|(?:"[^"]*"))/g,       //字符串
                   /((?:\}(?:&nbsp;)+\.(?:(?:&nbsp;)+\{)?)|(?:\.(?:&nbsp;)+\{))/g //}.或.{间有空格时,提示错误
                  ];
  var colors=[
              "#ff00ff",
              "#0000ff",
              "#ff0000",
              "#aa22ff",
              "#002f2f"
             ];
             
  //高亮显示
  function hightColor(){
    var txt=getAllText();
    txt=replaceToHtml(txt);

    for(var i=0;i<keyPatterns.length;i++){
      txt=txt.replace(keyPatterns[i],"<font color="+colors[i]+"><b>$1</b></font>");
    }
    setCurEditAllHTML(txt,true);
  }
  
  //将字符替换成html代码
  function replaceToHtml(txt){
    txt=txt.replace(/&/g,"&amp;");
    txt=txt.replace(/ /g,"&nbsp;");
    txt=txt.replace(/</g,"&lt;");
    txt=txt.replace(/>/g,"&gt;");
    txt=txt.replace(/\r\n/g,"<br>");
    return txt;
  }
  
  //将编辑框的内容设成指定内容,光标定位到原位置
  function setCurEditAllHTML(h,isHightColor){

    if (curRange){
    var left=getCurText();
    var right=getCurRightText();
      var leftHtml=getCurHtml();
    var reg=/.*?((?:<br>)+)(?:<[^>]+>)*$/i;//判断当前光标前是否是换行
    var rightBrCount=0;
    if(reg.test(leftHtml)){
      rightBrCount=getBrCount(RegExp.$1.replace(/<br>/ig,"\r\n"));
    }
    if(rightBrCount>0&&right==""){//如果是在最后按回车,则不替换,直接返回
      return;
    }
    var brCount=getBrCount(left);
    HtmlEdit.document.body.innerHTML=h;
    var pos=left.length-brCount+rightBrCount;//修正定位问题
 
    curRange.moveStart('character',pos);
    curRange.collapse(true);
    curRange.select();
    }else{
		 HtmlEdit.document.body.innerHTML=h;
    }
    if(!isHightColor){
      hightColor();
    }
  }

function getUrlStr4keyIndex() {
    var s = window.location.host;
    var namespace = "tools"
    //var context = '/express';
//    try {
//        context = $F('serverAddr');
//    } catch(e) {
//    }
    var url = "http://" + s + context + "/keyIndexAction.action";
    //alert(url)
    return url;
}


    function doSearch(reload)
    {
      var r = HtmlEdit.document.selection.createRange();
      var str=r.text;
      if(!str){
        str=HtmlEdit.document.body.innerText;
      }
        $('search_suggest').style.display = 'none';
        loadAjaxSearch(str,reload);
    }