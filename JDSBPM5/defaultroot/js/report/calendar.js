//origin from help.js
document.onload = helpInit();
document.write('<iframe style="position:absolute;top:-2000;left:-2000;z-index:9" id="help_frm" border="0" frameborder="0"></iframe>');

function helpInit() {
    var list = document.all.tags("BUTTON");
    for (var i = 0; i < list.length; i++) {
        if (list[i].getAttribute("target") != null) {
            var target = document.getElementById(list[i].getAttribute("target"));
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
            if (!document.getElementById(getAttribute("target"))) return;
            var width = 240;
            var height = 208;
            var offset = 0;
            if (document.body.clientWidth < ( 260 + util.getElementLeft(event.srcElement) )) {
                offset = util.getElementLeft(event.srcElement) - width;
            } else {
                offset = util.getElementLeft(event.srcElement);
            }
            switch (getAttribute("pattern")) {
                case "date":
                    help_date(width, height, offset, util.getElementTop(event.srcElement), getAttribute("target"), "yyyy-MM-dd");
                    break;
                case "date_time":
                    help_date(width, height, offset, util.getElementTop(event.srcElement), getAttribute("target"), "yyyy-MM-dd HH:mm:ss");
                    break;
                case "time":
                    help_date(width, height, offset, util.getElementTop(event.srcElement), getAttribute("target"), "HH:mm:ss");
                    break;
                case "year_month":
                    help_date(width, height, offset, util.getElementTop(event.srcElement), getAttribute("target"), "yyyy-MM");
                    break;
                case "hour_minute":
                    help_date(width, height, offset, util.getElementTop(event.srcElement), getAttribute("target"), "HH:mm");
                    break;
                case "minute":
                    help_date(width, height, offset, util.getElementTop(event.srcElement), getAttribute("target"), "yyyy-MM-dd HH:mm");
                    break;
            }
        }
    }
    catch (e) {
    }
}
/*	作用：日期输入对话框
 *
 */
function help_date(WndWidth, WndHeight, WndLeft, WndTop, target, format, self) {
    var sep = "\r\n";

    var script = "<script language=\"JavaScript\">"
            + sep + "function Initial() {"
            + sep + "try {"
            + sep + "	var myDate = new Date();"
            + sep + "	if(parent.validateDateEx(parent.document.getElementById(\"" + target + "\").value,\"yyyy-MM-dd\")) {"
            + sep + "		myDate = parent.newDate(parent.document.getElementById(\"" + target + "\").value,\"yyyy-MM-dd\");"
            + sep + " }"
            + sep + "	myYear.value = myDate.getFullYear() + \"年\";"
            + sep + "	var coll = myMonth.children;"
            + sep + "	for(var i = 0;i < coll.length;i++) {"
            + sep + "		if(coll[i].value == myDate.getMonth() + 1) {"
            + sep + "			coll[i].selected = \"true\";"
            + sep + "			break;"
            + sep + "		}"
            + sep + "	}"
            + sep + "	sel_day = myDate.getDate();"
            + sep + "	setCalendar();"
            + sep + "	setTime();"
            + sep + "}catch(e){}"
            + sep + "}"
            + sep + "var timer;"
            + sep + "function setTime() {"
            + sep + "	var Now = new Date();"
            + sep + "	myHour.value = parent.hour(Now);"
            + sep + "	myMinute.value = parent.minute(Now);"
            + sep + "	mySecond.value = parent.second(Now);"
            + sep + "	timer = window.setTimeout(setTime,1000);"
            + sep + "}"
            + sep + "function setCalendar() {"
            + sep + "	if(!checkYear()) return;"
            + sep + "	for(var i = 0;i < 42;i++) {"
            + sep + "		document.getElementById(\"day_\" + i).innerText = String.fromCharCode(32);"
            + sep + "	}"
            + sep + "	var myDate = new Date(document.getElementById(\"myYear\").value.substring(0,4),parseInt(document.getElementById(\"myMonth\").value) - 1,1);"
            + sep + "	var day_count = parent.getDays(myDate);"
            + sep + "	for(var i = 1;i <= day_count;i++) {"
            + sep + "		document.getElementById(\"day_\" + (myDate.getDay() + i - 1)).innerText = i;"
            + sep + "	}"
            + sep + "	if(sel_day > day_count) sel_day = day_count;"
            + sep + "	setDay(document.getElementById(\"day_\" + (parseInt(sel_day) + myDate.getDay() - 1)));"
            + sep + "}"
            + sep + "var sel_id = \"day_0\";"
            + sep + "var sel_day = 1;"
            + sep + "function setDay(obj) {"
            + sep + "	if(isNaN(obj.innerText) || obj.innerText == \" \") return;"
            + sep + "	document.getElementById(sel_id).style.border = \"1px solid white\";"
            + sep + "	document.getElementById(sel_id).style.background = \"white\";"
            + sep + "	obj.style.border = \"1px solid black\";"
            + sep + "	obj.style.background = \"#EEEEEE\";"
            + sep + "	sel_id = obj.id;"
            + sep + "	sel_day = obj.innerText;"
            + sep + "}"
            + sep + "function help_date_cancel() {"
            + sep + "	window.clearTimeout(timer);"
            + sep + "	parent.help_cancel(\'" + target + "\',0," + self + ")"
            + sep + "}"
            + sep + "var util = new parent.Utility()"
            + sep + "function help_date_ok() {"
            + sep + "	window.clearTimeout(timer);"
            + sep + "	if(!checkYear()) {"
            + sep + "		alert(\"年份输入错误，请修改！\");"
            + sep + "		myYear.focus();"
            + sep + "		return;"
            + sep + "	}"
            + sep + "	if(!util.isNum(myHour.value,0,23)) {"
            + sep + "		alert(\"时间输入错误，请修改！\");"
            + sep + "		myHour.focus();"
            + sep + "		return;"
            + sep + "	}"
            + sep + "	if(!util.isNum(myMinute.value,0,59)) {"
            + sep + "		alert(\"时间输入错误，请修改！\");"
            + sep + "		myMinute.focus();"
            + sep + "		return;"
            + sep + "	}"
            + sep + "	if(!util.isNum(mySecond.value,0,59)) {"
            + sep + "		alert(\"时间输入错误，请修改！\");"
            + sep + "		mySecond.focus();"
            + sep + "		return;"
            + sep + "	}"
            + sep + "	var myDate = new Date(document.getElementById(\"myYear\").value.substring(0,4),parseInt(document.getElementById(\"myMonth\").value) - 1,1);"
            + sep + "	myDate.setDate(parseInt(sel_id.split(\"_\")[1]) - myDate.getDay() + 1);"
            + sep + "	myDate.setHours(parseInt(myHour.value),parseInt(myMinute.value),parseInt(mySecond.value));"
            + sep + "	parent.document.getElementById(\"" + target + "\").value = parent.formatDate(myDate,\"" + format + "\");"
            + sep + "   try {"
            + sep + "	    parent.document.getElementById(\"" + target + "_LBL\").innerText = parent.formatDate(myDate,\"" + format + "\");"
            + sep + "   } catch(e) {}"
            + sep + "	parent.help_cancel(\"" + target + "\",1," + self + ");"
            + sep + "}"
            + sep + "function checkYear() {"
            + sep + "	if(document.getElementById(\"myYear\").value.length < 4) return false;"
            + sep + "	if(isNaN(document.getElementById(\"myYear\").value.substring(0,4))) return false;"
            + sep + "	if(parseInt(document.getElementById(\"myYear\").value.substring(0,4)) < 1900) return false;"
            + sep + "	if(parseInt(document.getElementById(\"myYear\").value.substring(0,4)) > 2100) return false;"
            + sep + "	return true;"
            + sep + "}"
            + sep + "function addYear() {"
            + sep + "	if(!checkYear()) return;"
            + sep + "	document.getElementById(\"myYear\").value = parseInt(document.getElementById(\"myYear\").value.substring(0,4)) + 1 + \"年\";"
            + sep + "}"
            + sep + "function decYear() {"
            + sep + "	if(!checkYear()) return;"
            + sep + "	document.getElementById(\"myYear\").value = parseInt(document.getElementById(\"myYear\").value.substring(0,4)) - 1 + \"年\";"
            + sep + "}"
            + sep + "var time_span = null;"
            + sep + "function setTimeSpan(obj) {"
            + sep + "	window.clearTimeout(timer);"
            + sep + "	time_span = obj;"
            + sep + "}"
            + sep + "function addTime() {"
            + sep + "	window.clearTimeout(timer);"
            + sep + "	if(time_span) {"
            + sep + "		var type = \"\";"
            + sep + "		switch(time_span.id) {"
            + sep + "			case \"myHour\":"
            + sep + "				type = \"hour\";"
            + sep + "				break;"
            + sep + "			case \"myMinute\":"
            + sep + "				type = \"minute\";"
            + sep + "				break;"
            + sep + "			case \"mySecond\":"
            + sep + "				type = \"second\";"
            + sep + "				break;"
            + sep + "		}"
            + sep + "		time_span.value = parent.addTime(time_span.value,type);"
            + sep + "	}"
            + sep + "}"
            + sep + "function decTime() {"
            + sep + "	window.clearTimeout(timer);"
            + sep + "	if(time_span) {"
            + sep + "		var type = \"\";"
            + sep + "		switch(time_span.id) {"
            + sep + "			case \"myHour\":"
            + sep + "				type = \"hour\";"
            + sep + "				break;"
            + sep + "			case \"myMinute\":"
            + sep + "				type = \"minute\";"
            + sep + "				break;"
            + sep + "			case \"mySecond\":"
            + sep + "				type = \"second\";"
            + sep + "				break;"
            + sep + "		}"
            + sep + "		time_span.value = parent.decTime(time_span.value,type);"
            + sep + "	}"
            + sep + "}"
            + sep + "Initial();"
            + sep + "var _dx,_dy;	//	鼠标起始坐标"
            + sep + "var _ox,_oy;	//	层起始坐标"
            + sep + "function Drag() {"
            + sep + "	with(event.srcElement) {"
            + sep + "		attachEvent(\"onmousemove\", doDrag);"
            + sep + "		attachEvent(\"onmouseup\", endDrag);"
            + sep + "		attachEvent(\"onmouseout\", endDrag);"
            + sep + "		attachEvent(\"onlosecapture\", endDrag);"
            + sep + "		setCapture();"
            + sep + "	}"
            + sep + "	window.event.cancelBubble = true;"
            + sep + "	_dx = event.screenX;"
            + sep + "	_dy = event.screenY;"
            + sep + "	with(parent.document.getElementById(\"help_frm\")) {"
            + sep + "		_ox = parseInt(style.left);"
            + sep + "		_oy = parseInt(style.top);"
            + sep + "	}"
            + sep + "}"
            + sep + "function doDrag() {"
            + sep + "	with(parent.document.getElementById(\"help_frm\")) {"
            + sep + "		style.left = (event.screenX - _dx + _ox);"
            + sep + "		style.top = (event.screenY - _dy + _oy);"
            + sep + "	}"
            + sep + "}"
            + sep + "function endDrag() {"
            + sep + "	with(event.srcElement) {"
            + sep + "		detachEvent(\"onmousemove\", doDrag);"
            + sep + "		detachEvent(\"onmouseup\", endDrag);"
            + sep + "		detachEvent(\"onmouseout\", endDrag);"
            + sep + "		detachEvent(\"onlosecapture\", endDrag);"
            + sep + "		releaseCapture();"
            + sep + "	}"
            + sep + "}"
            + sep + "</script>";

    var html = "<html>"
            + sep + "<head>"
            + sep + "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=gb2312\">"
            + sep + "<style>"
            + sep + "td{font-family:\"宋体\";font-size:9pt}"
            + sep + "font{font-family:\"宋体\";font-size:9pt}"
            + sep + "span{font-family:\"宋体\";font-size:9pt}"
            + sep + "input{font-family:\"宋体\";font-size:9pt;padding:1px 0px 0px 2px}"
            + sep + "select{font-family:\"宋体\";font-size:9pt}"
            + sep + "button{font-family:\"宋体\";font-size:9pt;padding:0px 0px 0px 0px;height:19px}"
            + sep + ".box1{background:#EEEEEE;border-style:none solid solid none;border-color:black;border-width:1px;padding:3px 0px 0px 0px}"
            + sep + ".box2{background:#EEEEEE;border-style:none none  solid none;border-color:black;border-width:1px;padding:3px 0px 0px 0px}"
            + sep + ".box3{background:#FFFFFF;border:1px solid white;padding:0px 0px 0px 0px;cursor:hand}"
            + sep + ".boxA{height:12px;border:0px;padding:0px;text-align:center}"
            + sep + "</style>"
            + sep + "</head>"
            + sep + "<body bgcolor=\"#D4D0C8\" text=\"#000000\" scroll=\"no\" style=\"border:2px outset;margin-top:0px\">"
            + sep + "<span style=\"padding:0px 2px 0px 1px;z-index:2;position:relative;top:10px;left:3px;background:#D4D0C8\">日期/时间</span><span style=\"width:120px;cursor:default\" onmousedown=\"Drag()\">&nbsp;</span>"
            + sep + "<table cellspacing=\"2\" cellpadding=\"2\" width=\"100%\" border=\"0\" style=\"border:2px groove;\">"
            + sep + "	<tr><td height=\"5\"></td></tr>"
            + sep + "	<tr>"
            + sep + "		<td>"
            + sep + "			<table cellspacing=\"2\" cellpadding=\"0\" width=\"100%\" border=\"0\">"
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
            + sep + "					<td><div><button  TABINDEX=\"-1\" onclick=\"addTime()\" style=\"height:9px;width:16px\">^</button></div><div><button  TABINDEX=\"-1\" onclick=\"decTime()\" style=\"height:9px;width:16px\">^</button></div></td>"
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
            + sep + "<center><button style=\"position:relative;top:6px\" onclick=\"help_date_ok()\">确定</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<button style=\"position:relative;top:6px\" onclick=\"help_date_cancel()\">取消</button></center>"
            + sep + "</body>"
            + sep + "</html>"
            + sep + script;
    try {
        help_frm.document.body.innerHTML = "";
        var coll = help_frm.document.scripts;
        for (var i = 0; i < coll.length; i++) {
            coll[i].text = "";
        }
        help_frm.document.write(html);
        help_frm.resizeTo(WndWidth, WndHeight);
        help_frm.moveTo(WndLeft, WndTop);

    } catch(e) {
    }

}
function help_cancel(target, move) {
    help_frm.moveTo(-2000, -2000);
    var obj1 = document.getElementById(target);
    var obj2 = document.getElementById(target.split("_")[0] + "_" + (parseInt(target.split("_")[1], 10) + move));
    if (obj2) obj2.focus();
    else if (obj1) obj1.focus();
    else KeyXp_setFocus();
}

//origin from validateEx.js
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


//origin from time.js
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
function addTime(time, type) {
    var limit = 60;
    if (type == "hour") limit = 24;
    time = parseInt(time, 10) + 1;
    if (time < 10) time = "0" + time;
    if (time == limit) time = "00";
    return time;
}
function decTime(time, type) {
    var limit = 60;
    if (type == "hour") limit = 24;
    time = parseInt(time, 10) - 1;
    if (time < 0) time = limit - 1;
    if (time < 10) time = "0" + time;
    return time;
}


//origin from utility.js

Utility = function() {
}

/**
 *  得到[obj]的绝对位置（top）
 */
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

/**
 *  得到[obj]的绝对位置（left）
 */
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

/**
 *  判断数字[num]是否在[lower]和[upper]之间
 */
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

/**
 *  判断数字[num]是否是整数
 */
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

/**
 *  得到[obj]的标签为[tagName]的节点
 */
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

/**
 *  改变节点[node]的属性[attr]的序号，序号以[div]分割，增加[num]
 */
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

/**
 *  在[obj]的[where]旁插入[node]，并改变节点[node]的[ary]中的属性的序号，序号以[div]分割，增加[num]
 */
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

/**
 *  在表不少于[i]行时，删除[obj]所在的行
 */
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

/**
 *  在表[table]中[where]增加一行，并改变此行的[ary]中的属性的序号，序号以[div]分割，增加[num]，根据[del]判断是否删除指定标签
 */
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

/**
 *  在[path]中输出[str]
 */
Utility.prototype.output = function(path, str) {
    try {
        var fso = new ActiveXObject("Scripting.FileSystemObject");
        var a = fso.CreateTextFile(path, true);
        a.WriteLine(str);
        a.Close();
    } catch(e) {
    }
}

/**
 *  定位下拉框[oSel]的值[value]
 */
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

/**
 *  定位下拉框[oSel]的文字[text]
 */
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
//	鼠标起始坐标
var _ox,_oy;
//	对象起始坐标
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

//本月第一天,传入的参数为cdate，格式为：yyyy-mm-dd
function showMonthFirstDay(cdate)
{
    //var cdate="2004-01-02";
    var year = cdate.substring(0, 4);
    var month = cdate.substring(5, 7) - 1;
    var day = cdate.substring(8, 10);
    var Nowdate = new Date(year, month, day);
    currentmonth = 0
    var MonthFirstDay = new Date(Nowdate.getYear(), Nowdate.getMonth(), 1);
    //alert("MonthFirstDay="+MonthFirstDay);
    return  date_prototype_toString(MonthFirstDay);
}

//本月最后一天,传入的参数为cdate，格式为：yyyy-mm-dd
function showMonthLastDay(cdate)
{
    //var cdate="2004-01-02";
    var year = cdate.substring(0, 4);
    var month = cdate.substring(5, 7) - 1;
    var day = cdate.substring(8, 10);
    var Nowdate = new Date(year, month, day);
    currentmonth = 0
    var MonthNextFirstDay = new Date(Nowdate.getYear(), Nowdate.getMonth() + 1 + currentmonth, 1);
    var MonthLastDay = new Date(MonthNextFirstDay - 86400000);
    return date_prototype_toString(MonthLastDay);

}
//指定日期的下月第一天,传入的参数为cdate，格式为：yyyy-mm-dd
function showMonthNextFirstDay(cdate)
{
    //var cdate="2004-01-02";
    var year = cdate.substring(0, 4);
    var month = cdate.substring(5, 7) - 1;
    var day = cdate.substring(8, 10);
    var Nowdate = new Date(year, month, day);
    currentmonth = 0
    var MonthNextFirstDay = new Date(Nowdate.getYear(), Nowdate.getMonth() + 1 + currentmonth, 1);
    return date_prototype_toString(MonthNextFirstDay);
}
//将日期型装化为字符串型,返回格式为yyyy-m-d
function date_prototype_toString(cdate) {
    var thedate = cdate.toLocaleString()
    var year = thedate.substring(0, thedate.indexOf("年"));
    var month = thedate.substring(thedate.indexOf("年") + 1, thedate.indexOf("月"));
    var day = thedate.substring(thedate.indexOf("月") + 1, thedate.indexOf("日"));
    if (month.length < 2) {
        month = "0" + month;
    }
    if (day.length < 2) {
        day = "0" + day;
    }
    thedate = year + "-" + month + "-" + day;
    return thedate;
}

