//origin from utility.js

Utility = function() {
}

Utility.prototype.getElementTop = function(obj) {
    try {
        var t = obj.offsetTop;
        while (obj = obj.offsetParent) t += obj.offsetTop;
        return t;
    }
    catch(e) {
        alert("Err Utility 0001:\r\n" + e);
        return 0;
    }
}


Utility.prototype.getElementLeft = function(obj) {
    try {
        var l = obj.offsetLeft;
        while (obj = obj.offsetParent) l += obj.offsetLeft;
        return l;
    }
    catch(e) {
        alert("Err Utility 0002:\r\n" + e);
        return 0;
    }
}


Utility.prototype.isNum = function(num, lower, upper) {
    try {
        if (isNaN(num)) return false;
        if (num < lower) return false;
        if (num > upper) return false;
        return true;
    }
    catch(e) {
        alert("Err Utility 1001:\r\n" + e);
        return false;
    }
}


Utility.prototype.isInt = function(num) {
    try {
        if (parseInt(num, 10) == num) return true;
        return false;
    }
    catch(e) {
        alert("Err Utility 1002:\r\n" + e);
        return false;
    }
}

Utility.prototype.getParent = function(obj, tagName) {
    try {
        var Elm = obj;
        while (Elm && Elm.tagName != tagName) {
            Elm = Elm.parentElement;
        }
        return Elm;
    }
    catch(e) {
        alert("Err Utility 2001:\r\n" + e);
        return null;
    }
}


Utility.prototype.changeAttr = function(node, attr, div, num) {
    try {
        var oColl_1 = attr.split(".");
        var oColl_2 = node.getAttribute(oColl_1[0]).split(div);

        if (node.getAttribute(oColl_1[0]).search(div) == -1) return;

        if (oColl_1.length > 1) {
            for (var i = 1; i < oColl_1.length; i++) {
                for (var j = 0; j < oColl_2.length; j++) {
                    if (this.isInt(oColl_1[i]) && this.isInt(oColl_2[j]) && oColl_1[i] == j) {
                        oColl_2[j] = parseInt(oColl_2[j], 10) + parseInt(num, 10);
                    }
                }
            }
            node.setAttribute(oColl_1[0], oColl_2.join(div));
            return;
        }
        var prefix = oColl_2[0];
        var postfix = oColl_2[1];
        if (!this.isInt(postfix)) return;
        postfix = parseInt(postfix, 10) + parseInt(num, 10);
        if (node.tagName == "A" && attr == "name") {
            var oA = document.createElement("<A NAME='" + prefix + "_" + postfix + "'></A>");
            node.outerHTML = oA.outerHTML;
        }
        if (attr == "href") {
            prefix = prefix.substring(prefix.search("#"), prefix.length);
            prefix = location.href.substring(0, location.href.search("#")) + prefix;
        }
        node.setAttribute(attr, prefix + div + postfix);
    } catch(e) {
    }
}


Utility.prototype.insertNode = function(obj, where, node, ary, div, num, clear) {
    var oColl = node.all;

    try {
        for (var k = 0; k < oColl.length; k++) {
            if (clear && oColl[k].tagName == "INPUT" && oColl[k].getAttribute("type").toLowerCase() != "button") {
                oColl[k].value = "";
            }
        }
    } catch(e) {
    }

    for (var i = 0; i < ary.length; i++) {
        try {
            this.changeAttr(node, ary[i], div, num);
        } catch(e) {
        }
        for (var j = 0; j < oColl.length; j++) {
            try {
                this.changeAttr(oColl[j], ary[i], div, num);
            } catch(e) {
            }
        }
    }
    obj.insertAdjacentElement(where, node);
}


Utility.prototype.delRow = function(obj, i) {
    try {
        var Elm = obj;
        while (Elm && Elm.tagName != "TR") {
            Elm = Elm.parentElement;
        }
        if (Elm.parentElement.rows.length <= i) {
            alert("无法删除！");
            return;
        }
        Elm.parentElement.deleteRow(Elm.rowIndex);
    } catch(e) {
        alert("Err Utility 5001:\r\n" + e);
    }
}


Utility.prototype.addRow = function(table, where, ary, div, num, del) {
    try {
        if (table.tagName != "TABLE") alert("Err Utility 5002");
        var oList = table.children;
        var oTBODY;
        for (var i = 0; i < oList.length; i++) {
            if (oList[i].tagName == "TBODY") {
                oTBODY = oList[i];
                break;
            }
        }
        var oTR = oTBODY.lastChild;
        var newTR = oTR.cloneNode(true);
        if (del) {
            oList = oTR.all;
            for (var i = 0; i < oList.length; i++) {
                if (oList[i].getAttribute("del") == "true") {
                    oList[i].outerHTML = "";
                }
            }
        }
        this.insertNode(oTBODY, where, newTR, ary, div, num, true)
    } catch(e) {
        alert("Err Utility 5002:\r\n" + e);
    }
}


Utility.prototype.output = function(path, str) {
    try {
        var fso = new ActiveXObject("Scripting.FileSystemObject");
        var a = fso.CreateTextFile(path, true);
        a.WriteLine(str);
        a.Close();
    } catch(e) {
    }
}


Utility.prototype.setSelValue = function(oSel, value) {
    try {
        var opts = oSel.options;
        for (var i = 0; i < opts.length; i++) {
            if (opts[i].value == value) {
                oSel.selectedIndex = opts[i].index;
                return;
            }
        }
    } catch(e) {
    }
}


Utility.prototype.setSelText = function(oSel, text) {
    try {
        var opts = oSel.options;
        for (var i = 0; i < opts.length; i++) {
            if (opts[i].text == text) {
                oSel.selectedIndex = opts[i].index;
                return;
            }
        }
    } catch(e) {
    }
}


var util = new Utility();
//origin from drag.js
var _dx,_dy;
var _ox,_oy;
function Drag() {
    if (event.ctrlKey) {
        with (event.srcElement) {
            attachEvent("onmousemove", doDrag);
            attachEvent("onmouseup", endDrag);
            attachEvent("onmouseout", endDrag);
            attachEvent("onlosecapture", endDrag);
            setCapture();
            style.cursor = "hand";
            if (style.position == "absolute") {
                _ox = parseInt(style.left, 10);
                _oy = parseInt(style.top, 10);
            }
            else {
                _ox = getLeftEx(event.srcElement);
                _oy = getTopEx(event.srcElement);
            }
        }
        window.event.cancelBubble = true;
        _dx = event.screenX;
        _dy = event.screenY;
    }
}
function doDrag() {
    with (event.srcElement) {
        style.position = "absolute";
        style.left = (event.screenX - _dx + _ox);
        style.top = (event.screenY - _dy + _oy);
    }
}
function endDrag() {
    with (event.srcElement) {
        detachEvent("onmousemove", doDrag);
        detachEvent("onmouseup", endDrag);
        detachEvent("onmouseout", endDrag);
        detachEvent("onlosecapture", endDrag);
        releaseCapture();
        style.cursor = "auto";
    }
}
function getTopEx(obj) {
    var t = obj.offsetTop;
    while (obj = obj.offsetParent) t += obj.offsetTop;
    return t;
}
function getLeftEx(obj) {
    var l = obj.offsetLeft;
    while (obj = obj.offsetParent) l += obj.offsetLeft;
    return l;
}

document.onload = helpInit();
document.write('<div style="display:none;" id="help_frm"></div>');

function helpInit() {
    var list = document.getElementsByTagName("BUTTON");
    for (var i = 0; i < list.length; i++) {
        if (list[i].getAttribute("target") != null) {
//            var target = document.getElementById(list[i].getAttribute("target"));
            var target = list[i].previousSibling;//todo
            if (!target || target.getAttribute("type") == "hidden") {
                list[i].style.display = "none";
                continue;
            }
            list[i].attachEvent("onclick", helper);
            var _id = list[i].getAttribute("id");
            var targetName = _id.substring(0, _id.length - 4);
            if (list[i].getAttribute("multi") == "done") {
                var _ls2 = document.all(_id);
                for (var j = 0; j < _ls2.length; j++) {
                    _ls2[j].setAttribute("multi", "yes");
                    _ls2[j].setAttribute("target", targetName);
                }
                var _ls1 = document.all.tags("INPUT");
                for (var j = 0; j < _ls1.length; j++) {
                    if (_ls1[j].getAttribute("id").substring(0, targetName.length) == targetName) {
                        _ls1[j].setAttribute("id", targetName);
                    }
                }
            }
            if (list[i].getAttribute("multi") == "yes") {
                var ls1 = document.all(targetName);
                var ls2 = document.all(_id);
                for (var j = 0; j < ls1.length; j++) {
                    ls1[j].setAttribute("id", targetName + "_" + j);
                }
                for (var j = 0; j < ls2.length; j++) {
                    ls2[j].setAttribute("multi", "done");
                    ls2[j].setAttribute("target", targetName + "_" + j);
                }
            }
        }
    }
}

function helper() {
    try {
        with (event.srcElement) {
//            if (!document.getElementById(getAttribute("target"))) return;
            var targetObj = event.srcElement.previousSibling;//todo
            if (!targetObj) return;
            var width = 240;
            var height = 208;
//            var offset = util.getElementLeft(event.srcElement);
            var offset = 0;//todo
            if ( document.body.clientWidth < ( 260 + util.getElementLeft(event.srcElement) ) ) {
				offset = util.getElementLeft(event.srcElement)-width;
			} else {
				offset = util.getElementLeft(event.srcElement);
			}
            switch (getAttribute("pattern")) {
                case "date":
                    help_date(width, height, offset, util.getElementTop(event.srcElement), targetObj, "yyyy-MM-dd");
                    break;
                case "date_time":
                    help_date(width, height, offset, util.getElementTop(event.srcElement), targetObj, "yyyy-MM-dd HH:mm:ss");
                    break;
                case "time":
                    help_date(width, height, offset, util.getElementTop(event.srcElement), targetObj, "HH:mm:ss");
                    break;
                case "year_month":
                    help_date(width, height, offset, util.getElementTop(event.srcElement), targetObj, "yyyy-MM");
                    break;
                case "hour_minute":
                    help_date(width, height, offset, util.getElementTop(event.srcElement), targetObj, "HH:mm");
                    break;
                case "minute":
                    help_date(width, height, offset, util.getElementTop(event.srcElement), targetObj, "yyyy-MM-dd HH:mm");
                    break;
            }
        }
    }
    catch (e) {
    }
}


var target;
var format;
function help_date(WndWidth, WndHeight, WndLeft, WndTop, targeting, formating, self) {
    target = targeting;
    format = formating;
    var sep = "\r\n";//todo
    var html = "<iframe style=\"position:absolute;width:242;height:222;z-index:-10;\" id=\"help_frm_b\" border=\"0\" frameborder=\"0\" src=\"calendarbgcolor.htm\"></iframe>"
            + sep + "<span class=\"calendar\" style=\"padding:0px 2px 0px 1px;z-index:2;position:relative;top:10px;left:3px;background:#D4D0C8\">日期/时间</span><span style=\"width:120px;cursor:default\" onmousedown=\"Drag()\">&nbsp;</span>"
            + sep + "<table class=\"calendar\" cellspacing=\"2\" cellpadding=\"2\" width=\"100%\" border=\"0\" style=\"border:2px groove;\">"
            + sep + "	<tr><td height=\"5\"></td></tr>"
            + sep + "	<tr>"
            + sep + "		<td>"
            + sep + "			<table class=\"calendar\" cellspacing=\"2\" cellpadding=\"0\" width=\"100%\" border=\"0\">"
            + sep + "				<tr>"
            + sep + "					<td><input TABINDEX=\"-1\" id=\"myYear\" size=\"6\" maxlength=\"5\" onpropertychange=\"setCalendar()\"></td>"
            + sep + "					<td><div><button  TABINDEX=\"-1\" onclick=\"addYear()\" style=\"height:9px;width:16px\">^</button></div><div><button  TABINDEX=\"-1\" onclick=\"decYear()\" style=\"height:9px;width:16px\">^</button></div></td>"
            + sep + "					<td width=\"100%\">"
            + sep + "						<select  TABINDEX=\"-1\" id=\"myMonth\" onchange=\"setCalendar()\">"
            + sep + "							<option value=\"1\">1月</option>"
            + sep + "							<option value=\"2\">2月</option>"
            + sep + "							<option value=\"3\">3月</option>"
            + sep + "							<option value=\"4\">4月</option>"
            + sep + "							<option value=\"5\">5月</option>"
            + sep + "							<option value=\"6\">6月</option>"
            + sep + "							<option value=\"7\">7月</option>"
            + sep + "							<option value=\"8\">8月</option>"
            + sep + "							<option value=\"9\">9月</option>"
            + sep + "							<option value=\"10\">10月</option>"
            + sep + "							<option value=\"11\">11月</option>"
            + sep + "							<option value=\"12\">12月</option>"
            + sep + "						</select>"
            + sep + "					</td>"
            + sep + "					<td><div align=\"center\" style=\"margin:0px;padding:0px;height:18px;width:62px;border:2px inset;background:white\"><input  TABINDEX=\"-1\" class=\"boxA\" id=\"myHour\" size=\"1\" maxlength=\"2\" onfocus=\"setTimeSpan(this)\"><font style=\"font-size:8pt\">:</font><input  TABINDEX=\"-1\" class=\"boxA\" id=\"myMinute\" size=\"1\" maxlength=\"2\" onfocus=\"setTimeSpan(this)\"><font style=\"font-size:8pt\">:</font><input  TABINDEX=\"-1\" class=\"boxA\" id=\"mySecond\" size=\"1\" maxlength=\"2\" onfocus=\"setTimeSpan(this)\"></div></td>"
            + sep + "					<td><div><button  TABINDEX=\"-1\" onclick=\"addTime();\" style=\"height:9px;width:16px\">^</button></div><div><button  TABINDEX=\"-1\" onclick=\"decTime();\" style=\"height:9px;width:16px\">^</button></div></td>"
            + sep + "				</tr>"
            + sep + "				<tr><td></td></tr>"
            + sep + "				<tr>"
            + sep + "					<td colspan=\"5\" align=\"center\">"
            + sep + "						<table cellspacing=\"0\" cellpadding=\"0\" width=\"99%\" style=\"border:1px solid black\">"
            + sep + "							<tr align=\"center\"><td class=\"box1\"><font color=red>日</font></td><td class=\"box1\">一</td><td class=\"box1\">二</td><td class=\"box1\">三</td><td class=\"box1\">四</td><td class=\"box1\">五</td><td class=\"box2\"><font color=green>六</font></td></tr>"
            + sep + "							<tr height=\"2\" bgcolor=\"white\"><td colspan=\"7\"></td></tr>";
            for (var i = 0; i < 6; i++) {
                html += "<tr align=\"center\">";
                for (var j = 0; j < 7; j++) {
                    html += "<td id=\"day_" + (i * 7 + j) + "\" class=\"box3\" onclick=\"setDay(this)\">&nbsp;</td>";
                }
                html += "</tr>";
            }
            html +=
            sep + "						</table>"
            + sep + "					</td>"
            + sep + "				</tr>"
            + sep + "			</table>"
            + sep + "		</td>"
            + sep + "	</tr>"
            + sep + "</table>"
            + sep + "<center><button style=\"position:relative;top:0px\" onclick=\"help_date_ok()\">确定</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<button style=\"position:relative;top:0px\" onclick=\"help_date_cancel()\">取消</button></center>"
    try {
        help_frm.innerHTML = "";
        help_frm.innerHTML = html;
        help_frm.style.display = "block";
        help_frm.style.height = WndHeight;
        help_frm.style.width = WndWidth;
        help_frm.style.posLeft = WndLeft;
        help_frm.style.posTop = WndTop;
        Initial();
    } catch(e) {
        alert("e");
    }

}

function Initial() {
    try {
        var myDate = new Date();
        myYear.value = myDate.getFullYear() + "年";
        var coll = myMonth.children;
        for (var i = 0; i < coll.length; i++) {
            if (coll[i].value == myDate.getMonth() + 1) {
                coll[i].selected = "true";
                break;
            }
        }
        sel_day = myDate.getDate();
        setCalendar();
        setTime();
    } catch(e) {

    }
}
var timer;
function setTime() {
    var Now = new Date();
    myHour.value = hour(Now);
    myMinute.value = minute(Now);
    mySecond.value = second(Now);
    timer = window.setTimeout(setTime, 1000);
}
var sel_id = "day_0";
var sel_day = 1;

function setCalendar() {
    if (!checkYear()) return;
    for (var i = 0; i < 42; i++) {
        document.getElementById("day_" + i).innerText = String.fromCharCode(32);
    }
    var myDate = new Date(document.getElementById("myYear").value.substring(0, 4), parseInt(document.getElementById("myMonth").value) - 1, 1);
    var day_count = getDays(myDate);
    for (var i = 1; i <= day_count; i++) {
        document.getElementById("day_" + (myDate.getDay() + i - 1)).innerText = i;
    }
    if (sel_day > day_count) sel_day = day_count;
    setDay(document.getElementById("day_" + (parseInt(sel_day) + myDate.getDay() - 1)));
}


function setDay(obj) {
    if (isNaN(obj.innerText) || obj.innerText == "") return;
    document.getElementById(sel_id).style.border = "1px solid white";
    document.getElementById(sel_id).style.background = "white";
    obj.style.border = "1px solid black";
    obj.style.background = "#EEEEEE";
    sel_id = obj.id;
    sel_day = obj.innerText;
}

function help_date_cancel() {
    window.clearTimeout(timer);
    help_cancel(target, self);
}

function help_date_ok() {
    window.clearTimeout(timer);
    if (!checkYear()) {
        alert("年份输入错误，请修改！");
        myYear.focus();
        return;
    }
    if (!util.isNum(myHour.value, 0, 23)) {
        alert("时间输入错误，请修改！");
        myHour.focus();
        return;
    }
    if (!util.isNum(myMinute.value, 0, 59)) {
        alert("时间输入错误，请修改！");
        myMinute.focus();
        return;
    }
    if (!util.isNum(mySecond.value, 0, 59)) {
        alert("时间输入错误，请修改！");
        mySecond.focus();
        return;
    }
    var myDate = new Date(document.getElementById("myYear").value.substring(0, 4), parseInt(document.getElementById("myMonth").value) - 1, 1);
    myDate.setDate(parseInt(sel_id.split("_")[1]) - myDate.getDay() + 1);
    myDate.setHours(parseInt(myHour.value), parseInt(myMinute.value), parseInt(mySecond.value));
//    document.getElementById(target).value = formatDate(myDate, format);
    target.value = formatDate(myDate, format);
    try {
        document.getElementById(target + "_LBL").innerText = formatDate(myDate, format);
    } catch(e) {
    }
    try{
        target.onchange();
    }catch(e) {
    }

    help_cancel(target, self);
}

function checkYear() {
    if (document.getElementById("myYear").value.length < 4) return false;
    if (isNaN(document.getElementById("myYear").value.substring(0, 4))) return false;
    if (parseInt(document.getElementById("myYear").value.substring(0, 4)) < 1900) return false;
    if (parseInt(document.getElementById("myYear").value.substring(0, 4)) > 2100) return false;
    return true;
}

function addYear() {
    if (!checkYear()) return;
    document.getElementById("myYear").value = parseInt(document.getElementById("myYear").value.substring(0, 4)) + 1 + "年";
}

function decYear() {
    if (!checkYear()) return;
    document.getElementById("myYear").value = parseInt(document.getElementById("myYear").value.substring(0, 4)) - 1 + "年";
}

var time_span = null;

function setTimeSpan(obj) {
    window.clearTimeout(timer);
    time_span = obj;
}

function addTime() {
    window.clearTimeout(timer);
    if (time_span) {
        var type = "";
        switch (time_span.id) {
            case "myHour":
                type = "hour";
                break;
            case "myMinute":
                type = "minute";
                break;
            case "mySecond":
                type = "second";
                break;
        }
        time_span.value = addTimeType(time_span.value, type);
    }
}
function decTime() {
    window.clearTimeout(timer);
    if (time_span) {
        var type = "";
        switch (time_span.id) {
            case "myHour":
                type = "hour";
                break;
            case "myMinute":
                type = "minute";
                break;
            case "mySecond":
                type = "second";
                break;
        }
        time_span.value = decTimeType(time_span.value, type);
    }
}

function help_cancel(target, move) {
    var help_frm = document.getElementById("help_frm");
    help_frm.style.display = "none";
//    var obj1 = document.getElementById(target);
    var obj1 = target;//todo
    var obj2 = document.getElementById(target.split("_")[0] + "_" + (parseInt(target.split("_")[1], 10) + move));
    if (obj2) obj2.focus();
    else if (obj1) obj1.focus();
    else KeyXp_setFocus();
}

function isNaNEx(str) {
    if (str == "") return true;
    if (isNaN(str)) return true;
    if (str.indexOf(".") != -1) return true;
    return false;
}

function SearchEx(source, pattern, str) {
    var index = pattern.indexOf(str);
    if (index == -1) return "error";
    return source.substring(index, index + str.length);
}

function validateDateEx(str, format) {
    if (str.length != format.length) return false;

    var year = 2000;
    var month = 1;
    var day = 1;
    var hour = 0;
    var minute = 0;
    var second = 0;

    if (format.indexOf("yyyy") != -1) {
        if (isNaNEx(year = SearchEx(str, format, "yyyy"))) return false;
        format = format.replace(/yyyy/, year);
    }

    if (format.indexOf("MM") != -1) {
        if (isNaNEx(month = SearchEx(str, format, "MM"))) return false;
        format = format.replace(/MM/, month);
    }

    if (format.indexOf("dd") != -1) {
        if (isNaNEx(day = SearchEx(str, format, "dd"))) return false;
        format = format.replace(/dd/, day);
    }

    if (format.indexOf("HH") != -1) {
        if (isNaNEx(hour = SearchEx(str, format, "HH"))) return false;
        if (parseInt(hour) < 0 || parseInt(hour) > 23) return false;
        format = format.replace(/HH/, hour);
    }

    if (format.indexOf("mm") != -1) {
        if (isNaNEx(minute = SearchEx(str, format, "mm"))) return false;
        if (parseInt(minute) < 0 || parseInt(minute) > 59) return false;
        format = format.replace(/mm/, minute);
    }

    if (format.indexOf("ss") != -1) {
        if (isNaNEx(second = SearchEx(str, format, "ss"))) return false;
        if (parseInt(second) < 0 || parseInt(second) > 59) return false;
        format = format.replace(/ss/, second);
    }

    if (format != str) return false;

    return isValidDate(year, month, day);
}

function isValidDate(year, month, day) {
    month = parseInt(month, 10);
    day = parseInt(day, 10);

    if (month < 1 || month > 12) return false;
    if (day < 1 || day > 31) return false;
    if ((month == 4 || month == 6 || month == 9 || month == 11) && (day == 31)) return false;
    if (month == 2) {
        var leap = (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0));
        if (day > 29 || (day == 29 && !leap)) return false;
    }
    return true;
}


function newDate(str, format) {
    switch (format) {
        case "yyyy-MM-dd":
            var l = str.split("-")
            return new Date(l[0], parseInt(l[1], 10) - 1, l[2]);
    }
}

function year(date) {
    var y = date.getFullYear();
    return y;
}
function month(date) {
    var m = date.getMonth() + 1;
    if (m < 10) m = "0" + m;
    return m;
}
function week(date) {
    switch (date.getDay()) {
        case 0:        return "星期日";
        case 1:        return "星期一";
        case 2:        return "星期二";
        case 3:        return "星期三";
        case 4:        return "星期四";
        case 5:        return "星期五";
        case 6:        return "星期六";
        default:    return "null";
    }
}
function day(date) {
    var d = date.getDate();
    if (d < 10) d = "0" + d;
    return d;
}
function hour(date) {
    var h = date.getHours();
    if (h < 10) h = "0" + h;
    return h;
}
function minute(date) {
    var m = date.getMinutes();
    if (m < 10) m = "0" + m;
    return m;
}
function second(date) {
    var s = date.getSeconds();
    if (s < 10) s = "0" + s;
    return s;
}
function formatDate(date, format) {
    format = format.replace("yyyy", year(date));
    format = format.replace("yy", year(date).toString().substring(2, 4));
    format = format.replace("MM", month(date));
    format = format.replace("dd", day(date));
    format = format.replace("HH", hour(date));
    format = format.replace("mm", minute(date));
    format = format.replace("ss", second(date));
    return format;
}
function getDays(date) {
    var date_this = new Date(date.getFullYear(), date.getMonth(), 1);
    var date_next = new Date(date.getFullYear(), date.getMonth() + 1, 1);
    var days = (date_next.getTime() - date_this.getTime()) / 1000 / 3600 / 24;
    return days;
}
function AutoDate(obj, format) {
    switch (format) {
        case "yyyy/MM/dd HH:mm":
            switch (obj.value.length) {
                case 4:case 7:
                obj.value += "/";return;
                case 10:
                    obj.value += " ";return;
                case 13:
                    obj.value += ":";return;
            }
            break;
        case "MM/dd HH:mm":
            switch (obj.value.length) {
                case 2:
                    obj.value += "/";return;
                case 5:
                    obj.value += " ";return;
                case 8:
                    obj.value += ":";return;
            }
            break;
        case "yyyy/MM/dd":
            switch (obj.value.length) {
                case 4:case 7:
                obj.value += "/";return;
            }
            break;
        case "yyyy-MM-dd":
            switch (obj.value.length) {
                case 4:case 7:
                obj.value += "-";return;
                case 10:
                    if (!isDate(obj.value, "yyyy-MM-dd")) {
                        alert("日期类型错误!");
                        srcElm.value = "";
                        srcElm.focus();
                        return;
                    }
            }
            break;
        case "yyyy/MM":
            switch (obj.value.length) {
                case 4:
                    obj.value += "/";return;
            }
            break;
    }
}
function isDate(str, format) {
    if (str.length != format.length) return false;
    var reg;
    switch (format) {
        case "yyyy/MM/dd HH:mm":
            reg = /((200\d\/((01|03|05|07|08|10|12)\/(0[1-9]|1\d|2\d|3[0-1]))|((04|06|09|11)\/(0[1-9]|1\d|2\d|30))|(02\/(0[1-9]|1\d|2[0-8])))|(200(?:0|4)\/02\/29)) (0\d|1\d|2[0-3]):[0-5]\d/;
            break;
        case "yyyy/MM/dd":
            reg = /((200\d\/((01|03|05|07|08|10|12)\/(0[1-9]|1\d|2\d|3[0-1]))|((04|06|09|11)\/(0[1-9]|1\d|2\d|30))|(02\-(0[1-9]|1\d|2[0-8])))|(200(?:0|4)\/02\/29))/;
            break;
        case "yyyy-MM-dd":
            reg = /(200\d-((01|03|05|07|08|10|12)-(0[1-9]|1\d|2\d|3[0-1]))|((04|06|09|11)-(0[1-9]|1\d|2\d|30))|(02-(0[1-9]|1\d|2[0-8])))|(200(?:0|4)-02-29)/;
            break;
    }
    if (str.search(reg) != -1) return true;
    return false;
}
function isDateEx(str, format) {
    if (str.length != format.length) return false;
    var reg;
    switch (format) {
        case "yyyy/MM/dd HH:mm":
            reg = /((200\d\/((01|03|05|07|08|10|12)\/(0[1-9]|1\d|2\d|3[0-1]))|((04|06|09|11)\/(0[1-9]|1\d|2\d|30))|(02\/(0[1-9]|1\d|2[0-8])))|(200(?:0|4)\/02\/29)) (0\d|1\d|2[0-3]):[0-5]\d/;
            break;
        case "yyyy/MM/dd":
            reg = /((200\d\/((01|03|05|07|08|10|12)\/(0[1-9]|1\d|2\d|3[0-1]))|((04|06|09|11)\/(0[1-9]|1\d|2\d|30))|(02\-(0[1-9]|1\d|2[0-8])))|(200(?:0|4)\/02\/29))/;
            break;
        case "yyyy-MM-dd":
            reg = /(200\d-((01|03|05|07|08|10|12)-(0[1-9]|1\d|2\d|3[0-1]))|((04|06|09|11)-(0[1-9]|1\d|2\d|30))|(02-(0[1-9]|1\d|2[0-8])))|(200(?:0|4)-02-29)/;
            break;
    }
    if (str.search(reg) != -1) return true;
    return false;
}
function addTimeType(time, type) {
    var limit = 60;
    if (type == "hour") limit = 24;
    time = parseInt(time, 10) + 1;
    if (time < 10) time = "0" + time;
    if (time == limit) time = "00";
    return time;
}
function decTimeType(time, type) {
    var limit = 60;
    if (type == "hour") limit = 24;
    time = parseInt(time, 10) - 1;
    if (time < 0) time = limit - 1;
    if (time < 10) time = "0" + time;
    return time;
}

//format yyyy-MM-dd HH:mm:ss
function compareTimestamp(first,second){
    var first_year = first.substring(0,4);
    var first_month = first.substring(5,7);
    var first_day = first.substring(8,10);
    var first_hour = first.substring(11,13);
    var first_min = first.substring(14,16);
    var first_second = first.substring(17,19);
    var second_year = second.substring(0,4);
    var second_month = second.substring(5,7);
    var second_day = second.substring(8,10);
    var second_hour = second.substring(11,13);
    var second_min = second.substring(14,16);
    var second_second = second.substring(17,19);

    return 
}






