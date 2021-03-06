function dhtmlXGridFromTable(obj) {
    if (typeof (obj) != "object") {
		obj = document.getElementById(obj);
	}
	var newDIV = document.createElement("DIV");
	newDIV.setAttribute("width", obj.offsetWidth + "px");
	newDIV.setAttribute("height", obj.offsetHeight + "px");

	obj.parentNode.insertBefore(newDIV, obj);


	var formObj = obj.getAttribute("name") || ("name_" + (new Date()).valueOf());
	try{
	var windowf = new dhtmlXGridObject(newDIV);
	}catch(e){

	}

	var fristRow=obj.rows[0];
	var fristCell=fristRow.cells[0];

	fristCell.setAttribute("colSpan", fristCell.getAttribute("colSpan") + 1);
	var colIndex=fristCell.getAttribute("colSpan");
	var rowIndex=fristCell.getAttribute("rowSpan");
	windowf.colIndex=colIndex;
	windowf.rowIndex=rowIndex;

    var sumcol = 0;
    for (i = 0; i < fristRow.cells.length; i++) {
        sumcol = fristRow.cells[i].getAttribute("colspan") + sumcol;
    }


    for (k = 0; k < rowIndex + 1; k++) {
        var hrow = obj.rows[k];
        var headStr = "";
        for (i = 0; i < hrow.cells.length; i++) {
            var colspan = hrow.cells[i].getAttribute("colspan");
            if (hrow.cells[i].innerHTML.length == 0) {
                headStr += (headStr ? "," : "") + " ";
            } else {
                headStr += (headStr ? "," : "") + hrow.cells[i].innerHTML;
            }

            for (j = 0; colspan - 1 > j; j++) {
                headStr += (headStr ? "," : "") + "#cspan";
            }
        }

        if (k > 0) {
            for (h = 1; h < rowIndex; h++) {
                if (k >= h) {
                    var hhrow = obj.rows[k - h];
                    var sumhcolspan = 0;
                    for (var g = 0; g < hhrow.cells.length; g++) {
                        var hcolspan = hhrow.cells[g].getAttribute("colspan");
                        var hrowspan = hhrow.cells[g].getAttribute("rowspan");
                        if (hrowspan > h) {
                            if (hcolspan > 1) {
                                headStr = insterRowSpan(headStr, sumhcolspan);
                                sumhcolspan = sumhcolspan;
                            }
                            headStr = insterRowSpan(headStr, sumhcolspan);
                        }
                        sumhcolspan = hcolspan + sumhcolspan;
                    }
                }
            }
        }
        if (k == 0) {
            var headerKey = new Array();
            headerKey[0] = "0";
            for (var cc = 1; cc < sumcol; cc++) {
                var c = String.fromCharCode(64 + cc);
                headerKey[cc] = c;
            }
            var nullHeader = genArrayString("", sumcol);

            windowf.setHeader(nullHeader);

            windowf.attachHeader(headStr);
            //alert(headStr);
        } else {
            if (k < rowIndex) {
                windowf.attachHeader(headStr);
            }
        }
    }
    windowf.attachHeader(headerKey.join());

    //add rowIndex cell
    for (j = rowIndex; j < obj.rows.length; j++) {
        var rrow = obj.rows[j];
        var indexCell = document.createElement("TD");
        indexCell.innerHTML = j - rowIndex + 1;
        rrow.insertBefore(indexCell, rrow.cells[0]);
    }


    var _cellType = "";
    var _cellSort = "";
    var fristObjRow = obj.rows[rowIndex];
    for (i = colIndex - 1; i < fristObjRow.cells.length; i++) {
        if (fristObjRow.cells[i].getAttribute("readonlyTd") == 'true') {
            _cellType += (_cellType ? "," : "") + "ro";
        } else {
            _cellType += (_cellType ? "," : "") + (fristObjRow.cells[i].getAttribute("type") || "ed");
        }
        _cellSort += (_cellSort ? "," : "") + (fristObjRow.cells[i].getAttribute("sort") || "str");
    }
    windowf.setImagePath("../images/designer/report/");
    var avrwidth = avrWidth(newDIV.offsetWidth, sumcol);
    windowf.setInitWidths(avrwidth);
    windowf.selMultiRows = true;
    var colAlign = genArrayString("center", sumcol);
    windowf.setColAlign(colAlign);
    windowf.setColTypes(genArrayString("ro", colIndex) + "," + _cellType);
    windowf.enableAutoHeigth(true);
    windowf.enableDragAndDrop(true);
    windowf.setMultiLine(false);
    windowf.setColumnColor("#D4D0C8,,#d5f1ff");
    var lmn = obj.getAttribute("lightnavigation");
    windowf.enableAlterCss("even", "uneven");
    if (lmn) {
        windowf.enableLightMouseNavigation(lmn);
    }
    var evr = obj.getAttribute("evenrow");
    var uevr = obj.getAttribute("unevenrow");
    if (evr || uevr) {
        windowf.enableAlterCss(evr, uevr);
    }
    windowf.init();
    var n_l = obj.rows.length;
    for (var j = rowIndex; j < n_l; j++) {
        var r = obj.rows[rowIndex];
        if (!r.id) {
            r.id = (new Date()).valueOf();
        }

        windowf.rowsCol[windowf.rowsCol.length] = r;
        windowf.rowsAr[r.id] = r;
        r.idd = r.id;
       
        r.grid = windowf;
        if (evr) {
            if ((j % 2) == 1) {
                r.className = evr;
            } else {
                r.className = uevr;
            }
        }
        windowf.obj.rows[0].parentNode.appendChild(r);
        for (var x = 0; x < r.cells.length; x++) {
            var cIndex = x + (windowf.hdr.rows[0].cells.length - r.cells.length);
            var cell = r.cells[x];
            var value = cell.innerHTML;
            cell._cellIndex = cIndex;
            cell._cellType = windowf.cellType[cIndex];
            if (windowf.dragAndDropOff) {
                windowf.dragger.addDraggableItem(cell, windowf);
            }
            cell.align = windowf.cellAlign[cIndex];
            cell.style.verticalAlign = windowf.cellVAlign[cIndex];
            if (cIndex == 1 && x == 0) {
                cell.bgColor = windowf.columnColor[0];
            } else {
                cell.bgColor = windowf.columnColor[cIndex] || "";
            }
            if (cell.getAttribute("id") != null)
            {
                cell.cnId = cell.getAttribute("id");
            }

            if (cell.getAttribute("bgColor") != null)
            {
                cell.bgColor = cell.getAttribute("bgColor");

            }

            if (cell.getAttribute("description") != null) {
                cell.tit = cell.getAttribute("description");
            }


            if (cell.getAttribute("isRowHeader") != 'true') {
                if (cell.getAttribute("colType") != null && cell.getAttribute("colType").length > 0) {

                    if (cell.getAttribute("colType") == "number" || cell.getAttribute("colType") == "text" || cell.getAttribute("colType") == "percent") {
                        cell._cellType = "ed";
                    }
                    else if (cell.getAttribute("colType") == "sign") {
                        cell._cellType = "dyn";
                    } else {
                        cell._cellType = "ed";
                    }
                }
                //       alert(cell._cellType);
                if (cell.getAttribute("readonlyTd") == 'true') {
                    cell._cellType = "ro";
                } else if (cell._cellType == "ro") {
                    cell._cellType = "ed";
                }


            }

            if (cell.getAttribute("disabledTd") == 'true') {
                cell._backType = cell._cellType;
                cell._cellType = "dis";
            } else if (cell.getAttribute("_backType") != null) {
                cell._cellType = cell._backType;
            }
            //����
            if (cell._cellType == null) {
                cell._cellType = "ed";
            }
            if (x == 0) {
                cell.bgColor = windowf.columnColor[0];
            }

            cell.id = String.fromCharCode(64 + cIndex) + "[" + (j - rowIndex + 1) + "]";
            try {
                cell = windowf.cells4(cell);
                cell.setValue(value);
            }
            catch (e) {
                continue;
            }
        }
        //add rightMenu
        //aMenu.setContextZone(r, j);
        var cellsLength = r.cells.length;
        var cIndex = (windowf.hdr.rows[0].cells.length - cellsLength);
        if (_isFF) {
            for (var i = r.childNodes.length - 1; i >= 0; i--) {
                if (r.childNodes[i].nodeType != 1) {
                    r.removeChild(r.childNodes[i]);
                }
            }
        }
    }
    obj.parentNode.removeChild(obj);
    window[formObj] = windowf;
    return windowf;
}

function dhx_init_grids() {
    var z = document.getElementsByTagName("table");
    for (var a = 0; a < z.length; a++) {
        if (z[a].className == "dhtmlxGrid") {
            dhtmlXGridFromTable(z[a]);
        }
    }
}

if (window.addEventListener) {
    window.addEventListener("load", dhx_init_grids, false);
} else {
    if (window.attachEvent) {
        window.attachEvent("onload", dhx_init_grids);
    }
}

function insterRowSpan(headString, i) {
    var headArry = headString.split(",");
    var ruturnStr = "";
    if (i == 0) {
        ruturnStr = "#rspan," + headArry.join();
    } else {
        var hArry = headArry.slice(0, i);
        var eArry = headArry.slice(i, headArry.length);
        ruturnStr = hArry.join() + ",#rspan," + eArry.join();
    }
    return ruturnStr;
}
function avrWidth(k, l) {
    var widthArry = new Array();
    for (j = 0; j < l; j++) {
        widthArry[j] = k / l;
    }
    return widthArry.join();
}
function defaultWidth(widthString, k, l) {
    var widthArry = widthString.split(",");
    for (j = 0; j < widthArry.length; j++) {
        // widthArry[j]=(widthArry[j]-3)*(k/l);
        widthArry[j] = l / widthArry.length;
    }
    return widthArry.join();
}
function genArrayString(str, sumcol) {
    var cch = new Array();
    for (var ch = 0; ch < sumcol; ch++) {
        cch[ch] = str;
    }
    return cch.join();
}
;




