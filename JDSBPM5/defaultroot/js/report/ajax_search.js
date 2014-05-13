//Gets the browser specific XmlHttpRequest Object
function getXmlHttpRequestObject() {
    if (window.XMLHttpRequest) {
        return new XMLHttpRequest();
    } else if (window.ActiveXObject) {
        return new ActiveXObject("Microsoft.XMLHTTP");
    } else {
        alert("Your Browser Sucks!\nIt's about time to upgrade don't you think?");
    }
}
function createAjaxObj() {
    var httprequest = false
    if (window.XMLHttpRequest)
    { // if Mozilla, Safari etc
        httprequest = new XMLHttpRequest()
        if (httprequest.overrideMimeType)
            httprequest.overrideMimeType('text/xml')
    }
    else if (window.ActiveXObject)
    { // if IE
        try {
            httprequest = new ActiveXObject("Msxml2.XMLHTTP");
        }
        catch (e) {
            try {
                httprequest = new ActiveXObject("Microsoft.XMLHTTP");
            }
            catch (e) {
            }
        }
    }
    return httprequest
}
//Our XmlHttpRequest object to get the auto suggest
var searchReq = createAjaxObj();

//Called from keyup on the search textbox.
//Starts the AJAX request.
function searchSuggest() {
    txtInput = document.getElementById('txtSearch');
    //    alert("====" + txtInput.value);
    if (txtInput.value != null && txtInput.value != '') {
        var lastChar = txtInput.value.substr(txtInput.value.length - 1, txtInput.value.length);
        //                alert("====" + lastChar);
        if (lastChar == ".") {
            //            alert("====" + lastChar);
            if (searchReq.readyState == 4 || searchReq.readyState == 0) {
                var txtSearch = escape(document.getElementById('txtSearch').value);
                //                        alert('txtSearch===' + txtSearch);
                if (txtSearch == "")
                {
                    document.getElementById('search_suggest').style.display = '';
                    return;
                } else {
                    document.getElementById('search_suggest').style.display = 'none';
                }
                searchReq.open("GET", 'http://localhost:7001/search?search=' + txtSearch, true);
                searchReq.onreadystatechange = handleSearchSuggest;
                //构造完数据后执行
                searchReq.send(null);
            }
        }
    }

    var width = txtInput.offsetWidth;
    var left = txtInput.offsetLeft;
    var top = txtInput.offsetTop + txtInput.offsetHeight;
    //    window.showModalDialog('demo/testOpen.jsp', 'newwindow', 'height=100, width=' + width + ', top=' + top + ', left=' + left + ', toolbar=no, menubar=no, scrollbars=no, resizable=no,location=no, status=no')
    //    window.open('demo/testOpen.jsp?str="123,123"', '获取人员', 'toolbar=no, directories=no, location=no, status=no, menubar=no, resizable=no,titlebar=no, scrollbars=yes,width=250, height=350');
}

//Called when the AJAX response is returned.
function handleSearchSuggest() {
    if (searchReq.readyState == 4) {
        oninit();
        var ss = document.getElementById('search_suggest');
        //显示的DIV对象
        //                alert('==='+ss.value);
        ss.innerHTML = '';
        //        alert('==='+searchReq.responseText);

        var str = searchReq.responseText.split("\n");
        //                alert('==='+str );
        if (str == "")
        {
            document.getElementById('search_suggest').style.display = 'none';
            return;
        } else {
            document.getElementById('search_suggest').style.display = '';
        }

        for (i = 0; i < str.length - 1; i++) {
            //Build our element string.  This is cleaner using the DOM, but
            //IE doesn't support dynamically added attributes.
            var suggest = "";
            suggest = '<div style="OVERFLOW: auto;clip:rect();" onmouseover="javascript:suggestOver(this);" ';
            suggest += 'onmouseout="javascript:suggestOut(this);" ';
            suggest += 'onclick="javascript:setSearch(this.innerHTML);" ';
            suggest += 'onmousedown="javascript:setSearch(this.innerHTML);" ';
            suggest += 'class="suggest_link">' + str[i] + '</div>';
            ss.innerHTML += suggest;
        }
    }
}
// 说明：用 Javascript 获取滚动条位置等信息 // 来源 ：ThickBox 2.1  // 整理 ：CodeBit.cn ( http://www.CodeBit.cn )
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
//    alert("scrollTop=="+t);
//    alert("scrollLeft=="+l);
//    alert("scrollWidth=="+w);
//    alert("scrollHeight=="+h);
    return { t: t, l: l, w: w, h: h };
}
function keyLister() {
    //***********添加
    var keyCode = event.keyCode;
    if (keyCode == 40 || keyCode == 38) { //下上
        var isUp = false ;
        if (keyCode == 40) isUp = true;
        chageSelection(isUp);
    }
    if (keyCode == 13) {//回车
        //        alert("in==");
        outSelection(selectedIndex);
    }
}
function outSelection(Index) {
    //    alert("selectedIndex=="+selectedIndex);
    var ss = document.getElementById("search_suggest");
    document.getElementById("txtSearch").value = document.getElementById("txtSearch").value + ss.children[Index].innerText;
    ss.style.display = 'none';
}
var selectedIndex = -1;
var downkey=false;
function chageSelection(isUp) {

    //    alert('selectedIndex===' + selectedIndex);
    var ss = document.getElementById("search_suggest");
    if (ss.style.display == 'none') {
    } else {
        if (isUp) {
            selectedIndex++;
        }
        else
            selectedIndex--;
    }
    //    alert('selectedIndex======='+selectedIndex  );
    var maxIndex = ss.children.length - 1;
    if (selectedIndex < 0) {
        selectedIndex = 0;
    }
    if (selectedIndex > maxIndex) {
        selectedIndex = maxIndex;
    }
    //    alert('selectedIndex======='+selectedIndex  );
    for (var intTmp = 0; intTmp <= maxIndex; intTmp++) {
        if (intTmp == selectedIndex) {
            ss.children[intTmp].className = 'suggest_link_over';
            //            alert('selectedIndex['+intTmp+']'+selectedIndex  );
        } else {
            ss.children[intTmp].className = 'suggest_link';
            //            alert('selectedIndex['+intTmp+']'+selectedIndex  );
        }
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
function setSearch(value) {
    document.getElementById('txtSearch').value = document.getElementById('txtSearch').value + value;
    document.getElementById('search_suggest').innerHTML = '';
    document.getElementById('search_suggest').style.display = 'none';
}
//********************
//初始化参数,和下拉表位置
var txtInput;
function initPar()
{
    txtInput = document.getElementById("txtSearch");
    //设置下拉表 相对于 文本输入框的位置
    setPosition();
}

//设置下拉表 相对于 文本输入框的位置
function setPosition()
{
    var width = txtInput.offsetWidth;
    var left = getLength("offsetLeft");
    var top = getLength("offsetTop") + txtInput.offsetHeight;

    document.getElementById('search_suggest').style.left = left + "px";
    document.getElementById('search_suggest').style.top = top + "px";
    document.getElementById('search_suggest').style.width = width + "px";
}
//获取对应属性的长度
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
function oninit()
{

    var txtSearch = escape(document.getElementById('txtSearch').value);
    //    txtSearch.attachEvent('onchange',  function(){
    //        opinion=_cell.innerText=opinionItem.value;
    //        item.value=_tb.innerText;
    //        opinionItem.style.display='none'
    //    })
    //    alert('txtSearch===' + txtSearch);
    if (txtSearch == "")
    {
        document.getElementById('search_suggest').style.display = 'none';
        return;
    } else {
        document.getElementById('search_suggest').style.display = '';
    }
    initPar();

}

function missdisplay(obj)
{
    document.getElementById('search_suggest').style.display = 'none';
}
