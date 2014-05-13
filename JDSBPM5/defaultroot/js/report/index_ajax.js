var sum = "10";
var keyHead = '';
var keyFoot = '';
var index = 0;
function oninit(objsum, key, keyHeadobj, keyFootobj, indexobj)
{
    sum = objsum;
    //    alert(keyHead);
    //    alert(keyFoot);
    keyHead = keyHeadobj;
    keyFoot = keyFootobj;
    if (indexobj != null && '' != indexobj && undefined != indexobj) {
        index = indexobj;
    }
    var txtSearch = escape(document.getElementById(key).value);
    if (txtSearch == "")
    {
        document.getElementById('search_suggest').style.display = 'none';
        return;
    } else {
        document.getElementById('search_suggest').style.display = '';
    }
    initPar(key);

}
function initPar(key)
{
    txtInput = document.getElementById(key);
    setPosition();
}
function setPosition()
{
    var width = txtInput.offsetWidth;
    var left = getLength("offsetLeft");
    var top = getLength("offsetTop") + txtInput.offsetHeight;

    document.getElementById('search_suggest').style.left = left + "px";
    document.getElementById('search_suggest').style.top = top + "px";
    document.getElementById('search_suggest').style.width = width + "px";
}

function getScroll() {
    var t, l, w, h;
    if (document.documentElement && document.documentElement.scrollTop) {
        t = document.documentElement.scrollTop;
        l = document.documentElement.scrollLeft;
        w = document.documentElement.scrollWidth;
        h = document.documentElement.scrollHeight;
    } else if (document.body) {
        t = document.body.scrollTop;
        l = document.body.scrollLeft;
        w = document.body.scrollWidth;
        h = document.body.scrollHeight;
    }

    return { t: t, l: l, w: w, h: h };
}
var keyOpratorArr = new Array('+', '-', '*', '/', '%',' ');
var keyOpratorAndDotArr = new Array("+", "-", "*", "/", "%", ".", "(", ")"," ");

function keyLister(obj) {
    if ($(obj).value == '') {
        document.getElementById('search_suggest').style.display = 'none';
        return;
    }
    var leftKeyLength = getOffsetPointer($(obj)).length;
    if (leftKeyLength >= 0) {
        for (var m = 0; m < keyOpratorAndDotArr.length; m++) {
            var temp = $(obj).value.substring(0, leftKeyLength);
            if (keyOpratorAndDotArr[m] == temp || temp == '') {
                return;
            }
        }
    }
	index=leftKeyLength;
    var keyCode = event.keyCode;
    if (keyCode == 40 || keyCode == 38) {
        var isUp = false ;
        if (keyCode == 40) isUp = true;
        chageSelection(isUp);
    } else if (keyCode == 13) {
        outSelection(selectedIndex, obj);
    }
    else {
        loadtestAjax(obj, leftKeyLength);
    }
}
function getSearchLastKeyDot(txtSearch, crrentIndex) {
    var indexdot = crrentIndex;
    var lastOperator = indexdot - 1;
    if (lastOperator > 0) {
        for (var i = indexdot - 1; i >= 0; i--) {
            if (isOperator(txtSearch.charAt(i))) {
                lastOperator++;
                break;
            }
            lastOperator--;
        }
    } else {
        return txtSearch.substring(0, indexdot);
    }
    return txtSearch.substring(lastOperator, indexdot);
}
function getSearchLastKeyNotDot(txtSearch, crrentIndex) {
    var indexdot = crrentIndex;
    var lastOperator = indexdot;
    for (var i = indexdot; i > 0; i--) {
        if (isOperator(txtSearch.charAt(i))) {
            break;
        }
        lastOperator--;
    }
    return txtSearch.substring(lastOperator, indexdot);
}
function isOperator(operator) {
    if (operator == '+' || operator == '-'
            || operator == '*' || operator == '/'
            || operator == '(' || operator == ')' || operator == '.' || operator == ' ')
        return true;
    else
        return false;
}
function getOffsetPointer(_mOffset) {
    var txb = _mOffset;
    //根据ID获得对象
    var pos = 0;
    //设置初始位置
    txb.focus();
    //输入框获得焦点,这句也不能少,不然后面会出错,血的教训啦.
    var s = txb.scrollTop;
    //获得滚动条的位置
    var r = document.selection.createRange();
    //创建文档选择对象
    var t = txb.createTextRange();
    //创建输入框文本对象
    t.collapse(true);
    //将光标移到头
    t.select();
    //显示光标,这个不能少,不然的话,光标没有移到头.当时我不知道,搞了十几分钟
    var j = document.selection.createRange();
    //为新的光标位置创建文档选择对象
    r.setEndPoint("StartToStart", j);
    //在以前的文档选择对象和新的对象之间创建对象,妈的,不好解释,我表达能力不算太好.有兴趣自己去看msdn的资料
    var str = r.text;
    //获得对象的文本
    var re = new RegExp("\r\n", "g");
    //过滤掉换行符,不然你的文字会有问题,会比你的文字实际长度要长一些.搞死我了.我说我得到的数字怎么总比我的实际长度要长.
    str = str.replace(re, "");
    //过滤
    pos = str.length;
    //获得长度.也就是光标的位置
    r.collapse(false);
    r.select();
    //把光标恢复到以前的位置
    txb.scrollTop = s;
    //把滚动条恢复到以前的位置
    return str;
}
function outSelection(Index, obj) {
    var ss = document.getElementById("search_suggest");
    var retval = ss.children[Index].children(0).cells(0).innerText;
    var tempStr = document.getElementById(obj).value;
    var temp = tempStr.substring(0, tempStr.length - 2);
    var last_char = temp.charAt(temp.length - 1);

    document.getElementById(obj).value = keyHead + retval + keyFoot;

    ss.style.display = 'none';
    selectedIndex = -1;
    var keyHeadLength=0;
    if(keyHead!=undefined&&keyHead!=''){
        keyHeadLength=parseInt(keyHead.length);
    }
    var keyLength=0;
    if(retval!=null&&retval!=''){
        keyLength=parseInt(retval.length);
    }
    var pos = keyHeadLength + keyLength;
    recoverCur(obj, pos);
}
var selectedIndex = -1;
var downkey = false;
function chageSelection(isUp) {

    var ss = document.getElementById("search_suggest");
    if (ss.style.display == 'none') {
    } else {
        if (isUp) {
            selectedIndex++;
        }
        else
            selectedIndex--;
    }
    var maxIndex = ss.children.length - 1;
    if (selectedIndex < 0) {
        selectedIndex = 0;
    }
    if (selectedIndex > maxIndex) {
        selectedIndex = maxIndex;
    }
    for (var intTmp = 0; intTmp <= maxIndex; intTmp++) {
        if (intTmp == selectedIndex) {
            ss.children[intTmp].className = 'suggest_link_over';
        } else {
            ss.children[intTmp].className = 'suggest_link';

        }
    }
    var obj = document.getElementById("search_suggest");
    if (isUp) {
        if (selectedIndex > 5) {
            obj.scrollTop += obj.scrollHeight / sum;
        }
    } else {
        obj.scrollTop -= obj.scrollHeight / sum;
    }

}
//Mouse over function
function suggestOver(div_value) {
    //    div_value.className = 'suggest_link_over';
}
//Mouse out function
function suggestOut(div_value) {
    //    div_value.className = 'suggest_link';
}
//Click function
function setSearch(obj1, obj2) {

    var ss = document.getElementById("search_suggest");
    var tempStr = $(obj1).value;

    var temp = tempStr.substring(0, tempStr.length);
    $(obj1).value = keyHead + obj2.value + keyFoot;
    ss.style.display = 'none';
    selectedIndex = -1;

    var pos = parseInt(index) + parseInt(obj2.value.length)+1;
    recoverCur(obj1, pos);

}
function recoverCur(id, pos)
{
    var textbox = document.all(id);
    var r = textbox.createTextRange();
    r.collapse(true);
    r.moveStart('character', pos);
    r.select();
}
//********************
var txtInput;
function setPosition()
{
    var width = txtInput.offsetWidth;
    var left = getLength("offsetLeft");
    var top = getLength("offsetTop") + txtInput.offsetHeight;
    document.getElementById('search_suggest').style.left = left + "px";
    document.getElementById('search_suggest').style.top = top + "px";
    document.getElementById('search_suggest').style.width = width + "px";
    document.getElementById('search_suggest').style.border ="1px solid #000000";
}
function getLength(attr)
{
    var offset = 0;
    var item = txtInput;
    while (item)
    {
        offset += item[attr];
        item = item.offsetParent;
    }
    return offset;
}
function missdisplay(obj)
{
    document.getElementById('search_suggest').style.display = 'none';
}
function loadtestAjax(obj, leftKeyLength)
{      
    var s = window.location.host;

    var keyCode = event.keyCode;
    var key = $(obj);
    var url = "http://"+ s + $F('serverAddr') + "/keyIndexAction.action";
//    alert(url);
    var pars = new Array();
    var expression = new Array();
        var lastChar = key.value.substr(key.value.length - 1, key.value.length);
         var value=escape(key.value);
            var txtSearch = "txtSearch=" + value;
            var objName = "objName=" + obj;

            var crrentIndex = "crrentIndex=" +  leftKeyLength;
            var str = txtSearch + "&" + objName + "&" +crrentIndex ;

         //  alert( encodeURI(str));
            var myAjax = new Ajax.Updater(
                    'keyindexdefalut' + obj,
                    url,
            {
                method: 'post',
                parameters: str,
                evalScripts: true
            }
                    );

   // }
}
var key;
function keyIndex(obj) {
    var div = document.createElement("DIV");
    div.id = "search_suggest";
    div.style.position = "absolute";
    var divdefault = document.createElement("DIV");
    divdefault.id = "keyindexdefalut" + obj;
    if (key == undefined) {
        key == $(obj);
    }
    var node = $(obj);
    node.parentNode.appendChild(div);
    node.parentNode.appendChild(divdefault);
    node.onkeyup = function(event) {
        keyLister(obj);
    }
}