var digits = "0123456789";
var lowercaseLetters = "abcdefghijklmnopqrstuvwxyz"
var uppercaseLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
var whitespace = " \t\n\r";
var defaultEmptyOK = true


function isEmpty(s) {
    return ((s == null) || (s.length == 0) )
}
function isWhitespace(s) {
    var i;
    if (isEmpty(s)) return true;
    for (i = 0; i < s.length; i++) {
        var c = s.charAt(i);
        if (whitespace.indexOf(c) == -1) return false;
    }
    return true;
}
function isDigit(c) {
    return ((c >= "0") && (c <= "9"))
}
function isInteger(s)
{
    var i;
    if (isEmpty(s))
        if (isInteger.arguments.length == 1) return defaultEmptyOK;
        else return (isInteger.arguments[1] == true);
    for (i = 0; i < s.length; i++) {
        var c = s.charAt(i);
        if (!isDigit(c)) return false;
    }
    return true;
}

function toValidateDate(str, format) {
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
        if (parseInt(hour, 10) < 0 || parseInt(hour, 10) > 23) return false;
        format = format.replace(/HH/, hour);
    }

    if (format.indexOf("mm") != -1) {
        if (isNaNEx(minute = SearchEx(str, format, "mm"))) return false;
        if (parseInt(minute, 10) < 0 || parseInt(minute, 10) > 59) return false;
        format = format.replace(/mm/, minute);
    }

    if (format.indexOf("ss") != -1) {
        if (isNaNEx(second = SearchEx(str, format, "ss"))) return false;
        if (parseInt(second, 10) < 0 || parseInt(second, 10) > 59) return false;
        format = format.replace(/ss/, second);
    }

    if (format != str) return false;

    return isValidDate(year, month, day);
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

function isFloat (s){
    var i;
    var seenDecimalPoint = false;
    if (isEmpty(s))
       if (isFloat.arguments.length == 1) return defaultEmptyOK;
       else return (isFloat.arguments[1] == true);
    if (s == ".") return false;
    if ( (s.charAt(0) == "-")||(s.charAt(0) == "+")){
        s = s.substring(1);
    }
    for (i = 0; i < s.length; i++)
    {
        var c = s.charAt(i);
        if ((c == ".") && !seenDecimalPoint) seenDecimalPoint = true;
        else if (!isDigit(c)) return false;
    }
    return true;
}

function isDecimal(s,m,n){
	if(!isFloat(s)) return false;
	if(String(parseInt(s,10)).length > m-n) return false;
	var ss = String(parseFloat(s));
	if(ss.indexOf(".")>=0 && ss.substring( ss.indexOf(".") + 1, ss.length).length > n ) return false;
	return true;
}

function formatNumber(srcStr,nAfterDot){
	var srcStr,nAfterDot;
    var resultStr,nTen;
  srcStr = ""+srcStr+"";
	nTen =1;
for(j=0;j<nAfterDot;j++){
nTen = nTen*10;
}
	srcStr = Math.round(parseFloat(srcStr)*nTen)/nTen;
	srcStr = ""+srcStr + "";
	strLen = srcStr.length;
	dotPos = srcStr.indexOf(".",0);
	if (dotPos == -1){
		resultStr = srcStr+".";
for (i=0;i<nAfterDot;i++){
resultStr = resultStr+"0";
}
return resultStr;
}
else{
resultStr = srcStr;
	  for (j=0;j<(nAfterDot-(strLen-dotPos-1));j++){
resultStr = resultStr+"0";
}
	  return resultStr;
}
}


function $NF(expr, decplaces)
{
    var str = '' + Math.round(eval(expr) * Math.pow(10, decplaces))
    while (str.length <= decplaces)
    {
        str = '0' + str
    }
    var decpoint = str.length - decplaces
    return str.substring(0, decpoint) + '.' + str.substring(decpoint, str.length);
}
function isMoney(s,m,n)
{
    if(!isFloat(s)) return false;
    var ss = String(parseFloat(s));
    if(ss.indexOf("-") == 0)
    {
        if(String(parseInt(s,10)).length > m - n ) return false;
    }
    else
    {
        if(String(parseInt(s,10)).length > m - n - 1) return false;
    }
    if(ss.indexOf(".")>=0 && ss.substring( ss.indexOf(".") + 1, ss.length).length > n ) return false;
    return true;
}
//add by arnold


//add by arnold
function is152ONull(obj,objChinaName)
{
    var temp = $F(obj);
    if(isWhitespace(temp))
    {
        return true;
    }
    if(!isMoney(temp,15,2))
    {
        alert(objChinaName + "只能为整数部分最大长度12位，小数部分最大长度2位的数字或为空，请检查！");
        try
        {
            $(obj).focus();
        }catch(e){}
        return false;
    }
    return true;
}

function is152(obj,objChinaName)
{
    var temp = $F(obj);
    var flag = true;
    if(isWhitespace(temp))
    {
        alert(objChinaName + "不能为空，请输入！");
        flag = false;
    }
    if(!isMoney(temp,15,2) && flag)// isSignedFloat
    {
        alert(objChinaName + "只能为整数部分最大长度12位，小数部分最大长度2位的数字，请检查！");
        flag = false;
    }
    if(!flag)
    {
        try
        {
            $(obj).focus();
        }catch(e){}
    }
    if(!flag)
    {
        return false;
    }
    return true;
}
function isNotNull(obj,objChinaName)
{
    var temp = $F(obj);
    var flag = true;
    if(isWhitespace(temp))
    {
        alert(objChinaName + "不能为空，请输入！")
        flag = false;
    }
    if(!flag)
    {
        try
        {
            $(obj).focus();
        }catch(e){}
        return false;
    }
    return true;
}
/*
add by dengfasheng
charge the string whether letter or number
 */
function isAlphanumeric (s)

{   var i;

    if (isEmpty(s))
       if (isAlphanumeric.arguments.length == 1) return defaultEmptyOK;
       else return (isAlphanumeric.arguments[1] == true);

    for (i = 0; i < s.length; i++)
    {
        var c = s.charAt(i);

        if (! (isLetter(c) || isDigit(c) ) )
        return false;
    }

    return true;
}
//letter add by dengfasheng
function isLetter (c)
{   return ( ((c >= "a") && (c <= "z")) || ((c >= "A") && (c <= "Z")) )
}
//charge identify number add by dengfasheng
function isResidentID (s){
	if(s.length!=15 && s.length!=18) return false;
	if(!isAlphanumeric(s)) return false;
	var birthday;
	if(s.length==18){
		if( !isInteger(s.substring(0,s.length-1)) ) return false;
		birthday = s.substring(6,14);
	}
	if(s.length==15){
		if( !isInteger(s) ) return false;
		birthday = "19"+s.substring(6,12);
	}
	if(!isValidDate(  birthday.substring(0,4), birthday.substring(4,6), birthday.substring(6,8)  )) return false;
	return true;

}
//charge string whether excel column number
function isExcelColNum(s) {
    if (!isEmpty(s) && s.length < 3) {
        var flag = true;
        for (var i = 0; i < s.length; i++) {
            var c = s.charAt(i);
            if (!isLetter(c)) {
                flag = false;
                break;
            }
        }
        return flag;
    }
    return false;
}