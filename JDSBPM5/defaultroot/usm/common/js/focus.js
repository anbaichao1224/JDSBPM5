function initFocus() {
    var elements = document.getElementsByTagName("INPUT");
    for (var i = 0; i < elements.length; i++) {
        if (elements[i].type == "text") {
            elements[i].onkeydown = function doKeyDown() {
                if (13 == event.keyCode) {
                    var tabIndex = this.getAttribute("tabindex");
                    var tabIndexValue = parseInt(tabIndex, 10) + 1;
                    var tabElements = document.getElementsByTagName("INPUT");
                    var flag = false;
                    for (var j = 0; j < tabElements.length; j++) {
                        if (tabIndexValue == parseInt(tabElements[j].getAttribute("tabindex"), 10)) {
                            tabElements[j].focus();
                            flag = true;
                        }
                    }
                    if(!flag){
                        for (var j = 0; j < tabElements.length; j++) {
                        if (1 == parseInt(tabElements[j].getAttribute("tabindex"), 10)) {
                            tabElements[j].focus();
                            flag = true;
                        }
                    }
                    }
                }
            }
        }
    }
}




document.onload = shared_setfocus();

function shared_setfocus(){
	try	{
		if(document.forms[0]){
		    document.forms[0].elements[0].focus();
		}
	}
	catch (e) {}

}