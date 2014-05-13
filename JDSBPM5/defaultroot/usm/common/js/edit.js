var tableNames = new Array();
var _deleted = new Array();
var _lineIndex = new Array();


function getTbIndex(tableName) {
    var oTable = document.getElementById(tableName);
    var s = 1;
    if (oTable.getAttribute("_indexNum") != null) {
        s = parseInt(oTable.getAttribute("_indexNum"), 10) + 1;
    }
    return s;
}

function _makeIndexEx() {
    for (var j = 0; j < arguments.length; j++) {
        var tableName = arguments[j];
        var oTable = document.getElementById(tableName);
        var trs = oTable.rows;
        var n = 1;
        if (oTable.getAttribute("_startNum") != null) n = parseInt(oTable.getAttribute("_startNum"));
        for (var i = 0; i < trs.length; i++) {
            if (i < getTbIndex(tableName)) continue;
            if (trs[i].style.display == "none") continue;
            trs[i].firstChild.style.background = "white";
            trs[i].firstChild.style.color = "black";
            trs[i].cells[0].innerText = n++;
        }
    }
}

function pageInit() {
    pageInitEx("detail");
}

function pageInitEx() {
    try {
        for (var i = 0; i < arguments.length; i++) {
            var tableName = arguments[i];
            if (document.getElementById(tableName)) {
                tableNames[i] = tableName;
                _deleted[i] = new Array();
                _lineIndex[i] = getTbIndex(tableName);
                var oTable = document.getElementById(tableName);
                _makeIndexEx(tableName);
                _makeNameIndex(tableName);
                _setFocus(oTable.rows(getTbIndex(tableName)));
            }
        }
        var objs = document.all;
        for (var i = 0; i < objs.length; i++) {
            if (objs[i].tagName == "INPUT") {
                objs[i].attachEvent("onmouseover", _showTip);
                objs[i].attachEvent("onmouseout", hideTip);
            }
        }
    } catch(e) {
    }
}

function getArgIndex(tableName) {
    for (var i = 0; i < tableNames.length; i++) {
        if (tableName == tableNames[i]) return i;
    }
}


function _setFocus(obj) {
    try {
        while (obj && obj.tagName != "TR") {
            obj = obj.parentElement;
        }
        _lineIndex[getArgIndex(obj.parentElement.parentElement.id)] = obj.rowIndex;
        var olist = obj.parentElement.rows;
        for (var i = getTbIndex(obj.parentElement.parentElement.id); i < olist.length; i++) {
            olist[i].firstChild.style.background = "white";
            olist[i].firstChild.style.color = "black";
        }
        obj.firstChild.style.background = "#8899BB";
        obj.firstChild.style.color = "white";
    } catch(e) {
    }
}

function _undoDel() {
    _undoDelEx("detail");
}


function _undoDelEx(tableName) {
    try {
        var argIndex = getArgIndex(tableName);
        var obj = _deleted[argIndex].pop();
        if (obj) {
            var oList = document.getElementById(tableName).children;
            var oTBODY;
            for (var i = 0; i < oList.length; i++) {
                if (oList[i].tagName == "TBODY") {
                    oTBODY = oList[i];
                    break;
                }
            }
            oTBODY.insertAdjacentElement("beforeEnd", obj);
            obj = oTBODY.insertAdjacentElement("beforeEnd", obj);
            _makeIndexEx(tableName);
            _makeNameIndex(tableName);
            _setFocus(obj);
        }
    } catch(e) {
    }
}

function _delLine(alertdel) {
    _delLineEx("detail",alertdel);
}

function _delLineEx(tableName,alertdel) {
    try {
        var deltype = "";
        var oTable = document.getElementById(tableName);
        var argIndex = getArgIndex(tableName);
        var lineIndex = _lineIndex[argIndex];

        if (oTable.rows.length <= getTbIndex(tableName) + 1) {
            alert(alertdel);
            return;
        }

        var selectedIndexArray = new Array();
        var oList = oTable.rows(lineIndex).all;
        for (var i = 1; i < oList.length; i++) {
            if (oList[i].tagName == "SELECT") {
                selectedIndexArray[i] = oList[i].selectedIndex;
            }
        }
        var newTR = oTable.rows(lineIndex).cloneNode(true);
        oList = newTR.all;
        for (var i = 1; i < oList.length; i++) {
            if (oList[i].tagName == "SELECT") {
                oList[i].selectedIndex = selectedIndexArray[i];
            }
        }

        _deleted[argIndex].push(newTR);
        oTable.deleteRow(lineIndex);
        _makeIndexEx(tableName);
        _makeNameIndex(tableName);

        var rows = oTable.rows;
        var count = 0;
        for (var i = 0; i < rows.length; i++) {
            if (rows(i).style.display != "none") {
                count++;
            }
        }
        if (count <= getTbIndex(tableName)) {
            return;
        }
        if (lineIndex == oTable.rows.length) lineIndex--;
        _setFocus(oTable.rows(lineIndex));
        _lineIndex[argIndex] = lineIndex;
    } catch(e) {
    }
}

function _addLine() {
    _addLineEx("detail", false);
}

function _addLineEx(tableName, copyFlag) {
    try {
        var oList = document.getElementById(tableName).children;
        var oTBODY;
        for (var i = 0; i < oList.length; i++) {
            if (oList[i].tagName == "TBODY") {
                oTBODY = oList[i];
                break;
            }
        }

        var selectedIndexArray = new Array();
        oList = oTBODY.lastChild.all;
        for (var i = 1; i < oList.length; i++) {
            if (oList[i].tagName == "SELECT") {
                selectedIndexArray[i] = oList[i].selectedIndex;
            }
        }

        var newTR = oTBODY.lastChild.cloneNode(true);
        newTR.style.display = "block";
        oList = newTR.all;
        if (!copyFlag) {
            for (var i = 1; i < oList.length; i++) {
                if (oList[i].tagName == "SELECT") {
                    oList[i].selectedIndex = selectedIndexArray[i];
                }
            }
        } else {
            for (var i = 1; i < oList.length; i++) {
                if (oList[i].tagName == "TD" && oList[i].all.length == 0 && oList[i].getAttribute("_clear") != "false") {
                    oList[i].innerText = "";
                }
                if (oList[i].tagName == "INPUT" && oList[i].getAttribute("_clear") != "false") {
                    oList[i].value = "";
                }
                if (oList[i].tagName == "SELECT" && oList[i].getAttribute("_clear") == "false") {
                    oList[i].selectedIndex = selectedIndexArray[i];
                }
            }
        }
        oTBODY.insertAdjacentElement("beforeEnd", newTR);

        _makeIndexEx(tableName);
        _makeNameIndex(tableName);
        _setFocus(newTR);
    } catch (e) {
    }
}

function _makeNameIndex(tableName) {    
    var index = 0;
    var trs = document.getElementById(tableName).rows;
    for (var i = getTbIndex(tableName); i < trs.length; i++) {
        var oList = trs[i].all;
        for (var j = 0; j < oList.length; j++) {
            if (oList[j].tagName == "INPUT" || oList[j].tagName == "SELECT") {
                oList[j].name = oList[j].name.replace(/\[(\d)+\]/g, "[" + index + "]");
                oList[j].id = oList[j].id.replace(/\[(\d)+\]/g, "[" + index + "]");            
            } else {
                if (oList[j].tagName == "BUTTON" && oList[j].target != null) {
                    var indexS = "[" + index + "]";
                    if (oList[j - 1].name.indexOf(indexS) != -1) {
                        oList[j - 1].id = oList[j - 1].name;
                    } else {
                        oList[j - 1].id = oList[j - 1].name + indexS;
                    }
                    oList[j].target = oList[j - 1].id;
                }
            }
        }
        index++;
    }
}

