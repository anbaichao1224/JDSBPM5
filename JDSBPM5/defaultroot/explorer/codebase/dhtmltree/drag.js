var mouseX = 0;
var mouseY = 0;
var Xdist = 0;
var Ydist = 0;
var tmpCursor="";
var objIdToMove="";

if (!ie) document.captureEvents(Event.MOUSEMOVE)
document.onmousemove = getMouseXY;

function getMouseXY(e) {
  if (ie) { 
    mouseX = event.clientX + document.body.scrollLeft
    mouseY = event.clientY + document.body.scrollTop
  } else { 
    mouseX = e.pageX
    mouseY = e.pageY
  }  
  if (mouseX < 0){mouseX = 0}
  if (mouseY < 0){mouseY = 0}  
  if (objIdToMove != "") {
	var obj=document.getElementById(objIdToMove);
	obj.style.left=(mouseX-Xdist)+"px";
	obj.style.top=(mouseY-Ydist)+"px";  
  }
  return true
}

function moveBox(id) {
	var obj=document.getElementById(id);
	var pos=findPos(id);
	if (objIdToMove == "") {
		Xdist=mouseX-pos[0];
		Ydist=mouseY-pos[1];
		tmpCursor=obj.style.cursor;
		if (tmpCursor=="" || typeof(tmpCursor)=="undefined") tmpCursor="default";
		obj.style.cursor="move";
		objIdToMove=id;
	} else {
		objIdToMove="";
		obj.style.cursor=tmpCursor;
	} 
}

function stopMoveBox(obj) {
	obj.style.cursor=tmpCursor;
	Xdist = "undefined";
	Ydist = "undefined";
	tmpCursor="undefined";	
}
