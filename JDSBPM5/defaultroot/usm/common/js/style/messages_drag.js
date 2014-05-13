

function messages_drag_off(){
	window.messages_drag.style.display = "none";
}

function messages_drag_open(mess, buttonNum) {
    var showDiv = window.messages_drag;
    window.messages_drag.style.display = "block";
    mess = isEmpty(mess) ? "" : mess;
    window.messages_drag_content.innerHTML = mess;
    if (buttonNum && 2 == parseInt(buttonNum)) {
        window.messages_drag_button1.style.display = "none";
        window.messages_drag_button2.style.display = "block";
    } else {
        window.messages_drag_button1.style.display = "block";
        window.messages_drag_button2.style.display = "none";
    }
    var windowLeft = document.documentElement.clientWidth;
    var windowHeight = document.documentElement.clientHeight;
    showDiv.style.posLeft = (windowLeft - 450)/2;
    showDiv.style.posTop = (windowHeight - 200)/2;
}



var ey=0,ex=0,lx=0,ly=0,canDrg=false,thiso=null;//
var x, y,rw,rh;
    var divs=document.getElementsByTagName("div");
    for (var i=0;i<divs.length;i++){
      if(divs[i].getAttribute("rel")=="drag"){
        divs[i].onmousemove=function(){
           thismove(this);
        }
      /*
          var c=1;if(document.all)c=0;
        divs[i].childNodes[c].onmousedown=function(){
            dargit(this,event);
        }
        */
        //divs[i].onmousedown=function(){
             // st(this);
        //}  
      }
    }

function thismove(o){
    rw=parseInt(x)-parseInt(o.style.left);
    rh=parseInt(y)-parseInt(o.style.top);
    document.title=rw+"=wenben="+rh;
    if(rh<=20 && rw>=180)document.title=rw+"||20*20||"+rh;
    if(rh<=20 && rw<180 )document.title=rw+"||kexuan||"+rh;
}
function dargit(o,e){
thiso = o;
canDrg = true;
 if(!document.all){
      lx = e.clientX; ly = e.clientY;
      }else{
        lx = event.x; ly = event.y;
      }
 if(document.all) thiso.setCapture();

    st(o);

}

document.onmousemove = function(e){
if(!document.all){ x = e.clientX; y = e.clientY; }else{ x = event.x; y = event.y; }
if(canDrg){
    //if(rh<=20 && rw<180 ){
    var ofsx = x - lx;
    thiso.style.right = parseInt(thiso.style.right) - ofsx;
    lx = x;
    var ofsy = y - ly;
    thiso.style.top = parseInt(thiso.style.top) + ofsy;
    ly = y;
    //}else{canDrg=false;}
}
}

document.onmouseup=function(){
        canDrg=false;
        if(document.all && thiso != null){
         thiso.releaseCapture();
         thiso = null;
   }
}


function set(obj){
     obj=obj.parentNode.parentNode;
     if(obj.getAttribute("rel"));
       //obj.style.zIndex=1;

}
function st(o){

var p = o.parentNode;
if(p.lastChild != o){
  p.appendChild(o);
}

if(rh<=20 && rw>=180){
canDrg=false;

          window.status=rw+"|"+rh;
          if(p.firstChild == o) return;
          p.insertBefore(o, p.firstChild);
        }
}



//


function killErrors() {
return true;
}
window.onerror = killErrors;


function open_right_messages()

{
	window.right_messages.style.display = "block"


}
function kill_right_messages()

{
	window.right_messages.style.display = "none"


}
function open_help_message() {
    var showDiv = window.help_messages;
    var divWidth = 400;
    var obj = event.srcElement;
    var startLeft = event.clientX;
    var startTop = event.clientY;

    var windowLeft = document.documentElement.clientWidth;
    var scrollTop = document.documentElement.scrollTop;
    startTop = startTop + scrollTop;
    var scrollLeft = document.documentElement.scrollLeft;
    if (startLeft + divWidth > windowLeft) {
        startLeft = startLeft - divWidth;
        startLeft = startLeft < 0 ? 0 : startLeft;
    }
    startLeft = startLeft + scrollLeft;
    if (showDiv) {
        showDiv.style.display = "block";
        showDiv.style.posLeft = startLeft;
        showDiv.style.posTop = startTop;

        var messageContent = window.help_messages_content;
        if (messageContent) {
            messageContent.innerHTML = "";
            for (var i = 0; i < arguments.length; i++) {
                if (0 == i) {
                    messageContent.innerHTML = arguments[i];
                } else {
                    messageContent.innerHTML = messageContent.innerHTML + "<br>" + arguments[i];
                }
            }
        }
    }
}
function close_help_messages() {
	window.help_messages.style.display = "none"
}