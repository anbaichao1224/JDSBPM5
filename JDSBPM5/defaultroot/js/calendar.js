/*----------------------------------------------------------------------------------------------------------------------
 * Author: Surfer
 * Date: 2004-05-01
 *--------------------------------------------------------------------------------------------------------------------*/




var dateTimeValue = "";


var newWINwidth = 210 + 4 + 18;

function fPopUpCalendarDlg(ctrlobj,path) {
    var showx = event.screenX - event.offsetX - 4 - 200 ; // + deltaX;
    var showy = event.screenY - event.offsetY + 20; // + deltaY;
	dateTimeValue = ctrlobj.value;
    rtn = window.showModalDialog( path+"CalendarDlg.htm?time="+dateTimeValue, "", "dialogWidth:190px; dialogHeight:250px; dialogLeft:"+showx+"px; dialogTop:"+showy+"px; status:no; directories:yes;scrollbars:no;Resizable=no; "  );
    if (rtn != null) ctrlobj.value = rtn;
}


function fPopUpCalendarOfDateDlgByID(ctrlobjId, path) {
    var ctrlobj = document.all.getElementById(ctrlobjId);
    fPopUpCalendarOfDateDlg(ctrlobj,path);
}
function fPopUpCalendarOfDateDlg(ctrlobj,path) {
    var showx = event.screenX - event.offsetX ; // + deltaX;
    var showy = event.screenY - event.offsetY + 20; // + deltaY;
    dateTimeValue = ctrlobj.value;
    rtn = window.showModalDialog( path+"CalendarOfDateDlg.htm?time="+dateTimeValue, "", "dialogWidth:190px; dialogHeight:210px; dialogLeft:"+showx+"px; dialogTop:"+showy+"px; status:no; directories:yes;scrollbars:no;Resizable=no; "  );
    if (rtn != null) ctrlobj.value = rtn;
}


function fPopUpCalendarOfMonthDlg(ctrlobj,path) {
    var showx = event.screenX - event.offsetX - 4 - 200 ; // + deltaX;
    var showy = event.screenY - event.offsetY + 20; // + deltaY;
    dateTimeValue = ctrlobj.value;
    rtn = window.showModalDialog( path+"CalendarOfMonthDlg.htm?time="+dateTimeValue, "", "dialogWidth:200px; dialogHeight:46px; dialogLeft:"+showx+"px; dialogTop:"+showy+"px; status:no; directories:yes;scrollbars:no;Resizable=no;"  );
    if (rtn != null) ctrlobj.value = rtn;
}


function fPopUpCalendarOfYearDlg(ctrlobj,path) {
    var showx = event.screenX - event.offsetX - 4 - 200 ; // + deltaX;
    var showy = event.screenY - event.offsetY + 20; // + deltaY;
    dateTimeValue = ctrlobj.value;
    rtn = window.showModalDialog( path+"CalendarOfYearDlg.htm?time="+dateTimeValue, "", "dialogWidth:190px; dialogHeight:250px; dialogLeft:"+showx+"px; dialogTop:"+showy+"px; status:no; directories:yes;scrollbars:no;Resizable=no; "  );
    if (rtn != null) ctrlobj.value = rtn;
}

//Add for get a full timestamp 2004-06-18
function fullTimestamp(inTime) {
    var colonSplit1 = inTime.split(" ");
    var colonSplit2 = inTime.split(":");
    var colonSplit3 = inTime.split(".");
    if (colonSplit1.length == 1) {
        inTime = inTime + " 0:0:0.0";
    } else  if (colonSplit1.length == 2 && colonSplit2.length == 1 && colonSplit3.length == 1) {
        inTime = inTime + ":0:0.0";
    } else  if (colonSplit1.length == 2 && colonSplit2.length == 2 && colonSplit3.length == 1) {
        inTime = inTime + ":0.0";
    } else  if (colonSplit1.length == 2 && colonSplit2.length == 3 && colonSplit3.length == 1) {
        inTime = inTime + ".0";
    }
    //alert(inTime);
    return inTime;
}

function setTimestampEndOfMinute(fullTimestamp) {

    if (fullTimestamp.indexOf(".") > 0) {
        var colonSplit = fullTimestamp.split(":");
        return colonSplit[0] + ":" + colonSplit[1];
    }
    return fullTimestamp;
}


function setTimestampEndOfDate(fullTimestamp) {

    if (fullTimestamp.indexOf(".") > 0) {
        var colonSplit = fullTimestamp.split(" ");
        return colonSplit[0];
    }
    return fullTimestamp;
}
