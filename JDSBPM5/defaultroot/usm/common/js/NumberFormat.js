//add by arnold 2007-08-08
//modified by dengfasheng 2007-08-15

function NumberFormat()
{

}
NumberFormat.SPECIAL_CHARS = ["0",".","-",",","%","\u00A4","\u2030"];
/**
 * apply the pattern
 * param pattern
 */
NumberFormat.prototype.applyPattern = function(pattern)
{
    if (pattern == undefined)
    {
        pattern = "";
    }
    function contains(arr, char)
    {
        for (var i = 0; i < arr.length; i++)
        {
            if (arr[i] == char)
            {
                return true;
            }
        }
        return false;
    }
    for (var i = 0; i < pattern.length; i++)
    {
        if (!contains(NumberFormat.SPECIAL_CHARS, pattern.charAt(i)))
        {
            alert("error pattern in check: " + pattern);
        }
    }
    this.pattern = pattern;
};

/**
 * format the number
 * param number
 */
NumberFormat.prototype.format = function(number)
{
    if((number == null) || (String(number).length == 0)){
        return "";
    }
    if(null != number)
    {
        number = number.replace(/,/g,"");
    }
    if (isNaN(number))
    {
        alert("error number");
    }
    var pattern = this.pattern;
    if (pattern == "")
    {
        return number;
    }
    number = formatNumberData(number,6);
    var strNum = new String(number);
    var numNum = parseFloat(number);
    var isNegative = false;
    if (numNum < 0)
    {
        isNegative = true;
        strNum = strNum.substring(1, strNum.length);
        numNum = -numNum;
    }
    var dPos = pattern.indexOf(".");
    var commaPos = pattern.indexOf(",");
    var pPos = pattern.indexOf("%");


    if (commaPos > -1 && pPos > -1)
    {
        alert("pattern error: %, " + this.pattern);
    }
    if(commaPos > -1)
    {
        if(dPos != -1 && dPos < commaPos)
        {
            alert("pattern error: % " + this.pattern);
        }
        var groupNum;
        var sufixNum;
        if(dPos > -1)
        {
            groupNum = dPos - commaPos - 1;
            sufixNum = this.pattern.length - dPos - 1;
        }
        else
        {
            groupNum = this.pattern.length - commaPos - 1;
            sufixNum = 0;
        }
        return this.splitByComma(number,groupNum,sufixNum);
    }
    if (pPos > -1)
    {
        if (pPos != pattern.length - 1)
        {
            alert("pattern error: " + this.pattern);
        }
        pattern = pattern.substring(0, pattern.length - 1);
        numNum = parseFloat(number) * 100;
        strNum = new String(numNum);
        if (isNegative)
        {
            strNum = strNum.substring(1, strNum.length);
            numNum = -numNum;
        }
    }
    var dPosOfNum = strNum.indexOf(".");
    var result = "";
    if (dPos > -1)
    {
        if (dPosOfNum == -1)
        {
            dPosOfNum = strNum.length - 1;
        }
        var adStrLength = pattern.length - dPos;
        var snbFixed = new Number(parseFloat(strNum)).toFixed(adStrLength - 1);
        if (isNegative)
        {
            result = "-" + snbFixed;
        } else
        {
            result = snbFixed;
        }
    }
    else
    {
        if (dPosOfNum == -1)
        {
            dPosOfNum = strNum.length - 1;
        }
        var snbFixed = new Number(parseFloat(strNum)).toFixed();
        if (isNegative)
        {
            result = "-" + snbFixed;
        }
        else
        {
            result = snbFixed;
        }
    }
    if (pPos != -1)
    {
        result += "%";
    }
    return result;
};
NumberFormat.prototype.splitByComma = function(num,groupNum,sufixNum)
{
    var addFlag = false;
    num = num.replace(/,/g,"");
    var   dotArray = num.split(".");
    var   s = dotArray[0];
    var   sufix = "";
    var   consts = "00000000000";
    //var   re = ""/(\d+)(\d{3})/;
    var   re = new RegExp("(\\d+)(\\d{" + String(groupNum) + "})");
    var tarStr = "";
    while(re.test(s))
    {
          s = s.replace(re,"$1,$2");
    }
    if(sufixNum > 0)
    {
        if(s == null || s=="")
        {
            s = "0";
        }
        if(dotArray.length > 1)
        {
            sufix = dotArray[1];
        }
        sufix = sufix + consts;
        var cutBit = sufix.substring(sufixNum,sufixNum+1);
        if(cutBit != null && 1 == cutBit.length && cutBit>"4" && cutBit<="9"){
            addFlag= true;
        }
        sufix = sufix.substr(0,sufixNum);
        sufix = "." + sufix
    }
    tarStr = s + sufix;
//    if(addFlag){
//        tarStr = String(parseFloat(tarStr) + parseFloat(AddBitWei(tarStr,sufixNum)));
//    }
    return tarStr;
};

function PageNumberControl()
{

}
PageNumberControl.prototype.formatValue = function()
{
    var ls = document.getElementsByTagName("INPUT");
    if(ls.length > 0)
    {
        var nf = new NumberFormat()
        for(var i = 0; i < ls.length;i++)
        {
            if(ls[i].getAttribute("number_pattern") != null)
            {
                nf.applyPattern(ls[i].getAttribute("number_pattern"));
                ls[i].value = nf.format(ls[i].value);
                var eventStr = "onchange";
//                if(ls[i].getEvent("onblur") == null)
//                {
//                    eventStr = "onblur";
//                }
//                else if(ls[i].getEvent("onchange") == null)
//                {
//                    eventStr = "onchange";
//                }
                if(ls[i].attachEvent)
                {
                    ls[i].attachEvent("onblur",INFEX);
                    ls[i].attachEvent("onkeypress",onkeypressFun);
                    ls[i].attachEvent("onpaste",onpasteFun);
                    ls[i].attachEvent("ondragenter",ondragenterFun);
                }
                else
                {
                    ls[i].addEventListener("onblur",INFEX);
                    ls[i].addEventListener("onkeypress",onkeypressFun);
                    ls[i].addEventListener("onpaste",onpasteFun);
                    ls[i].addEventListener("ondragenter",ondragenterFun);
                }
            }
        }
    }
};
PageNumberControl.prototype.cleanValue = function()
{
    var ls = document.getElementsByTagName("INPUT");
    if(ls.length > 0)
    {
        var nf = new NumberFormat()
        for(var i = 0; i < ls.length;i++)
        {
            if(ls[i].getAttribute("number_pattern") != null && ls[i].value != null)
            {
                ls[i].value = ls[i].value.replace(/,/g,"");
                ls[i].value = ls[i].value.replace(/\%/g,"");
            }
        }
    }
};
PageNumberControl.prototype.check = function ()
{
    var ls = document.getElementsByTagName("INPUT");
    if(ls.length > 0)
    {
        var nf = new NumberFormat()
        for(var i = 0; i < ls.length;i++)
        {
            if(ls[i].getAttribute("check_pattern") != null && ls[i].getAttribute("number_pattern") != null)
            {
                try
                {
                    ls[i].value = ls[i].value.replace(/,/g,"");
                    ls[i].value = ls[i].value.replace(/\%/g,"");
                }catch(e){}
                var nullAble = ls[i].getAttribute("check_pattern") == "null";
                return this.checkNumber(ls[i].getAttribute("id"),ls[i].getAttribute("number_pattern"),nullAble);
            }
        }
    }

};
PageNumberControl.prototype.checkNumber = function(obj,pattern,nullAbleFlag)
{
    if(nullAbleFlag)
    {
        if(isWhitespace($F(obj))) return true;
    }
    else
    {
        if(!isNotNull($F(obj))) return false;
    }
    if(null == pattern || "" == pattern)
    {
        return true;
    }
    if(pattern.indexOf(",") > -1)
    {
        if(pattern.indexOf(".") > -1)
        {
            return is152(obj,"",",");
        }
        else
        {
            return isIntegerEx(obj,"",",");
        }
    }
};
function NF(num)
{
    var numberFormat = new NumberFormat();
    numberFormat.applyPattern("0,000.00");
    return numberFormat.format(num);
}
var INF =  function(obj)
{
    return function(obj)
    {
        INFEX(obj);
    }
};
function INFEX()
{
    var obj = event.srcElement;
    var numberFormat = new NumberFormat();
    numberFormat.applyPattern(obj.getAttribute("number_pattern"));
    obj.value = numberFormat.format(obj.value);
}
function $init()
{
    var pc = new PageNumberControl();
    pc.formatValue();
}
$init();
function $clean()
{
    var pc = new PageNumberControl();
    pc.cleanValue();
}
function $cleanSingleCell(str){
    return null != str && str.length>0?str.replace(/,/g,""):"";
}
function $check()
{
    var pc = new PageNumberControl();
    return pc.checkNumber();
}
function formatNumberData(inVal,dotLen){
    var str = "";
    if((inVal == null) || (String(inVal).length == 0)){
        return "";
    } else{
        str = String(inVal);
    }
    var f = "";
    if(str.substring(0,1) == "-"){
        f = "-";
        str = str.substring(1);
    }
    var val= str;
    var idxOfe = val.indexOf('E');
    var idxOfp = val.indexOf('.');
    var idxOfF = val.indexOf('-');
    if(!(-1==idxOfp) && !(-1==idxOfe)){
        var tp = parseFloat(val.substring(0,idxOfe));
        var multFlag = true;
        if(!(-1==idxOfF)){
            multFlag = false;
            var ti = parseInt(val.substring(idxOfe+2,val.length));
        }else{
            multFlag = true;
            var ti = parseInt(val.substring(idxOfe+1,val.length));
        }
        for(var i=0;i<ti;i++){
            if(!(multFlag)){
                tp=tp/10;
            }else{
                tp=tp*10;
            }
        }
        str = String(tp);
    }
    else if(!(-1==idxOfp)){
        str = String(parseFloat(val));
    }
    var formatVal = String(str);
    if(formatVal.indexOf(".")!= -1 && formatVal.substring(formatVal.indexOf(".")).length>dotLen){
        var cutBit = formatVal.substring(formatVal.indexOf(".")+dotLen+1,formatVal.indexOf(".")+dotLen+2);
        formatVal = formatVal.substring(0,formatVal.indexOf(".")+dotLen+1);
        if(cutBit != null && 1 == cutBit.length && cutBit>"4" && cutBit<="9"){
            formatVal = String(parseFloat(formatVal) + parseFloat(AddBitWei(formatVal,dotLen)));
        }
        while("0" == formatVal.substring(formatVal.length-1)){
            formatVal = formatVal.substring(0,formatVal.length-1);
        }
        str = String(parseFloat(formatVal));
    }
    return String(f + str);
}
function AddBitWei(numStr,sufixNum){
    var f = numStr != null && String(numStr).length>0 && String(numStr).substring(0,1) == "-" ? "-":"";
    var len = parseInt(sufixNum);
    var addVal = f + "0.";
    for(var i=1;i<=len;i++){
        if(i == len){
            addVal = addVal + "1";
        }else{
            addVal = addVal + "0";
        }
    }
    return f + addVal;
}
function onkeypressFun()
{
    var str = String.fromCharCode(event.keyCode);
    if(!(str>="0" && str<="9") && !(str == "-") && !(str == ".") && !(str == ",")){
        event.keyCode = 0;
    }
}
function onpasteFun()
{
    return !clipboardData.getData('text').match(/\D/);
}
function ondragenterFun()
{
    return false;
}