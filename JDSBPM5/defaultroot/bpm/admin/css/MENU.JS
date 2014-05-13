NS4 = (document.layers) ? 1 : 0;
IE4 = (document.all) ? 1 : 0;
ver4 = (NS4 || IE4) ? 1 : 0;

if (ver4) {
    with (document) {
        write("<STYLE TYPE='text/css'>");
        if (NS4) {
            write(".parent  {position:absolute; visibility:visible}");
            write(".child   {position:absolute; visibility:visible}");
            write(".regular {position:absolute; visibility:visible}")
        }
        else {
            write(".child {display:block}")
        }
        write("</STYLE>");
    }
}

function getIndex(el) {
    ind = null;
    for (i=0; i<document.layers.length; i++) {
        whichEl = document.layers[i];
        if (whichEl.id == el) {
            ind = i;
            break;
        }
    }
    return ind;
}

function arrange() {
    nextY = document.layers[firstInd].pageY +document.layers[firstInd].document.height;
    for (i=firstInd+1; i<document.layers.length; i++) {
        whichEl = document.layers[i];
        if (whichEl.visibility != "hide") {
            whichEl.pageY = nextY;
            nextY += whichEl.document.height;
        }
    }
}

function initIt(){
    if (!ver4) return;
    if (NS4) {
        for (i=0; i<document.layers.length; i++) {
            whichEl = document.layers[i];
            if (whichEl.id.indexOf("Child") != -1) whichEl.visibility = "block";
       }
        arrange();
    }
    else {
        divColl = document.all.tags("DIV");
        for (i=0; i<divColl.length; i++) {
            whichEl = divColl(i);
            if (whichEl.className == "child") whichEl.style.display = "block";
        }
    }
}

function expandIt(el) {
    if (!ver4) return;
    if (IE4) {
        whichEl   = eval(el + "Child");
        whickLogo = eval(el + "logo");
        if (whichEl.style.display == "block") 
        { 
          whichEl.style.display = "none";
          whickLogo.src="images/tree01.jpg";
        }
        else
        { 
          whichEl.style.display = "block";
          whickLogo.src="images/tree02.jpg";
        }
    }
    else {
        whichEl   = eval("document." + el + "Child");
        whichLogo = eval("document." + el + "logo");
        if (whichEl.visibility == "show")
        {
          whichEl.visibility = "none";
          whickLogo.src="images/tree01.jpg";
        }
        else 
        {
          whichEl.visibility = "block";
          whickLogo.src="images/tree02.jpg";
        }
        arrange();
    }
}
onload = initIt;