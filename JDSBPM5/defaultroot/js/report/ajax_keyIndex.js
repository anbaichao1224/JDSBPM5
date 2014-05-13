

funciton 

var myAjax = new Ajax.Updater(
'dd',
url,
{
 method: 'post',
  parameters: str,
  evalScripts: true
});


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
function keyLister() {
    var keyCode = event.keyCode;

    if (keyCode == 40 || keyCode == 38) { //обио
        var isUp = false ;
        if (keyCode == 40) isUp = true;
        chageSelection(isUp);
    }
    if (keyCode == 13) {
        outSelection(selectedIndex);
    }
}
function outSelection(Index) {
    var ss = document.getElementById("search_suggest");
    document.getElementById("txtSearch").value = document.getElementById("txtSearch").value + ss.children[Index].innerText;
    ss.style.display = 'none';
}
var selectedIndex = -1;
var downkey=false;


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
var txtInput;
function initPar()
{
    txtInput = document.getElementById("txtSearch");
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
