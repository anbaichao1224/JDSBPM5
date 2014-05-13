function showCalendar(fieldValue, showx, showy) {
    var ret;
	showx = event.screenX - event.offsetX - 190 ; // + deltaX;
	showy = event.screenY - event.offsetY + 5; // + deltaY;
    //ret = window.showModalDialog("/mca/include/cal.htm", "", "dialogWidth:150px; dialogHeight:165px; dialogLeft:"+showx+"px; dialogTop:"+showy+"px; center:yes; help:no; status:no; directories:no;scrollbars:no;Resizable=no; ");
	ret = window.showModalDialog("/bpm/admin/include/js/date.htm", "", "dialogWidth:197px; dialogHeight:210px; dialogLeft:"+showx+"px; dialogTop:"+showy+"px; status:no; directories:yes;scrollbars:no;Resizable=no; "  );
    if (ret == null) {
        ret = fieldValue;
    }
    return ret;
}


function showCalendar(fieldValue) {
    var ret;
	showx = event.screenX - event.offsetX  ; // + deltaX;
	showy = event.screenY - event.offsetY +20; // + deltaY;
    //ret = window.showModalDialog("/mca/include/cal.htm", "", "dialogWidth:150px; dialogHeight:165px; dialogLeft:"+showx+"px; dialogTop:"+showy+"px; center:yes; help:no; status:no; directories:no;scrollbars:no;Resizable=no; ");
	ret = window.showModalDialog("/bpm/admin/include/js/date.htm", "", "dialogWidth:197px; dialogHeight:210px; dialogLeft:"+showx+"px; dialogTop:"+showy+"px; status:no; directories:yes;scrollbars:no;Resizable=no; "  );
    if (ret == null) {
        ret = fieldValue;
    }
    return ret;
}