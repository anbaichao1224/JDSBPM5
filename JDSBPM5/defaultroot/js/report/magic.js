var eSrc, shield, alertFram;
function magicAlert(content, isDymnic) {
    eSrc = arguments[1];
    shield = document.createElement("div");
    shield.id = "shield";
    shield.style.position = "absolute";
    shield.style.left = "0px";
    shield.style.top = "0px";
    shield.style.width = "100%";
    // shield.style.height = document.body.scrollHeight+"px";
    shield.style.height = "100%";
    shield.style.background = "#333";
    shield.style.textAlign = "center";
    shield.style.zIndex = "10000";
    shield.style.filter = "alpha(opacity=0)";
    shield.style.opacity = 0;

    alertFram = document.createElement("div");
    alertFram.id = "alertFram";
    alertFram.style.position = "absolute";
    alertFram.style.left = "50%";
    alertFram.style.top = "50%";
    alertFram.style.marginLeft = "-225px";
    alertFram.style.marginTop = -75 + document.documentElement.scrollTop + "px";
    alertFram.style.width = "450px";
    alertFram.style.height = "150px";
    alertFram.style.background = "";
    alertFram.style.textAlign = "center";
    alertFram.style.lineHeight = "150px";
    alertFram.style.zIndex = "10001";
    if (isDymnic) {
        var m1 = document.all.item("message_one").value;
        var m2 = document.all.item("message_two").value;
        var m3 = document.all.item("message_three").value;
//        setTimeout(getRandomInt(), 1000);
        //stepOne(5);
    }
    alertFram.innerHTML = content;
    document.body.appendChild(alertFram);
    document.body.appendChild(shield);
    this.setOpacity = function(obj, opacity) {
        if (opacity >= 1)opacity = opacity / 100;
        try {
            obj.style.opacity = opacity;
        } catch(e) {
        }
        try {
            if (obj.filters.length > 0 && obj.filters("alpha")) {
                obj.filters("alpha").opacity = opacity * 100;
            } else {
                obj.style.filter = "alpha(opacity=\"" + (opacity * 100) + "\")";
            }
        } catch(e) {
        }
    }
    var c = 0;
    this.doAlpha = function() {
        if (++c > 20) {
            clearInterval(ad);
            return 0;
        }
        setOpacity(shield, c);
    }
    var ad = setInterval("doAlpha()", 1);

//    document.getElementById("do_OK").focus();
    eSrc.blur();
    document.body.onselectstart = function() {
        return false;
    }
    document.body.oncontextmenu = function() {
        return false;
    }
}

function magicDone() {
    document.body.removeChild(alertFram);
    document.body.removeChild(shield);
    eSrc.focus();
    document.body.onselectstart = function() {
        return true;
    }
    document.body.oncontextmenu = function() {
        return true;
    }
}

function getRandomInt() {
    var now = new Date();
//    setTimeout();
//    alert(num);
    return now.getSeconds() % 43;
}

//显示的信息
var c = 0
function stepOne(b) {
    c += 1;
    var ss = setTimeout("stepOne(" + b + ")", "500")

    if (c == b) {
        clearTimeout(ss);
        document.getElementById('message_td').innerHTML = "<b>1111</b>";
        stepTwo(5)
    }
}

var f = 0
function stepTwo(e) {
    f += 1;
    var sss = setTimeout("stepTwo(" + e + ")", "500")

    if (f == e) {
        clearTimeout(sss);
        document.getElementById('message_td').innerHTML = "<b>2222</b>";
        stepThree(5)
    }
}

var i = 0
function stepThree(h) {
    i += 1;
    var ssss = setTimeout("stepThree(" + h + ")", "500")

    if (i == h) {
        clearTimeout(ssss);
        document.getElementById('message_td').innerHTML = "<b>3333</b>";
    }
}