// ��һ������ת��Ϊ����
function var_to_obj(val)
{
	this.value=val;
}
// �ж��Ƿ����ĳ����
function is_greater(field,limit)
{
	var Ret = (is_numeric(field,-1) ) ? (field.value > parseInt(limit) )  : false;
	return(Ret);
}
// �ж��Ƿ�С��ĳ����
function is_less(field,limit)
{
	var Ret = (is_numeric(field,-1) ) ? (field.value < parseInt(limit) )  : false;
	return(Ret);
}

function is_numeric(field)
{
	var Ret = true;
	var NumStr="0123456789";
	var decUsed=false;
	var chr;
	if(field.value.length<1)
		return false;
    if (field.value == ".")
    {
        Ret = false;
    }
	for (i=0;i<field.value.length;++i)
	{
		chr=field.value.charAt(i);
		if (NumStr.indexOf(chr,0)==-1)
		{
			if ( (!decUsed) && chr==".")
			{
				decUsed=true;
			}
			else
			{
				Ret=false;
			}
		}
	}
	return(Ret);
} 
 // �ж��Ƿ��Ǽ۸�
function is_price(field)
{
	var Ret = true;
	var NumStr="0123456789";
	var decUsed=false;
	var chr;
    if (field.value == ".")
    {
        Ret = false;
    }
	for (i=0;i<field.value.length;++i)
	{
		chr=field.value.charAt(i);
		if (NumStr.indexOf(chr,0)==-1)
		{
			if ( (!decUsed) && chr==".")
			{
				decUsed=true;
			}
			else
			{
				Ret=false;
			}
		}
	}
	if(Ret)
	{
		if(decUsed&&(field.value.length-field.value.indexOf('.')<4))
		;
		else if(decUsed)
			Ret=false;
	}
	return(Ret);
} 

 // �ж��Ƿ��Ǽ۸�
function isPrice(field,crit,msg)
{
	var Ret = true;
	var NumStr="0123456789";
	var decUsed=false;
	var chr;
	var tempStr = field.value;
	if ( tempStr.charAt(0) == "-" )
	{ 
	    tempStr = field.value.substring(1);
	}
    if (tempStr == ".")
    {
        Ret=false;
    }
	for (i=0;i<tempStr.length;++i)
	{
		chr=tempStr.charAt(i);
		if (NumStr.indexOf(chr,0)==-1)
		{
			if ( (!decUsed) && chr==".")
			{
				decUsed=true;
			}
			else
			{
				Ret=false;
			}
		}
	}
	if(Ret)
	{
		if(decUsed&&(tempStr.length-tempStr.indexOf('.')<4)) {
		    if ((tempStr-1)>(99999999999.99-1)) {
                Ret=false;
			}
		} else if(decUsed) {
			Ret=false;
		} else if ((tempStr-1)>(99999999999.99-1)) {
		    Ret=false;
	    }
	}
	if (!Ret)
		doMSG(field,crit,msg);
	return(Ret);
} 
 // �ж��Ƿ�������
function isDecimal(field,crit,msg)
{
	var Ret = true;
	var NumStr="0123456789";
	var decUsed=false;
	var chr;
	var tempStr = field.value;
	if ( tempStr.charAt(0) == "-" )
	{ 
	    tempStr = tempStr.substring(1);
	}
    if (tempStr == ".")
    {
        Ret=false;
    }
	for (i=0;i<tempStr.length;++i)
	{
		chr=tempStr.charAt(i);
		if (NumStr.indexOf(chr,0)==-1)
		{
			if ( (!decUsed) && chr==".")
			{
				decUsed=true;
			}
			else
			{
				Ret=false;
			}
		}
	}
	if(Ret)
	{
		if(decUsed&&(tempStr.length-tempStr.indexOf('.')<5)) {
		    if( (tempStr-1+1) > (9999999999.999-1+1) ) {
                 Ret=false;
			}
		} else if(decUsed) {
			Ret=false;
		} else if( (tempStr-1+1) > (9999999999.999-1+1) ) 
		    Ret=false;
	}
	if (!Ret)
		doMSG(field,crit,msg);
	return(Ret);
} 

function is_decimal(field,crit,msg)
{
	var Ret = true;
	var NumStr="0123456789";
	var decUsed=false;
	var chr;
    if (field.value == ".")
    {
        Ret=false;
    }
	for (i=0;i<field.value.length;++i)
	{
		chr=field.value.charAt(i);
		if (NumStr.indexOf(chr,0)==-1)
		{
			if ( (!decUsed) && chr==".")
			{
				decUsed=true;
			}
			else
			{
				Ret=false;
			}
		}
	}
	if(Ret)
	{
		if(decUsed&&(field.value.length-field.value.indexOf('.')<5)) {
		    if( (field.value-1+1) > (9999999999.999-1+1) ) {
                 Ret=false;
			}
		} else if(decUsed) {
			Ret=false;
		} else if( (field.value-1+1) > (9999999999.999-1+1) ) 
		    Ret=false;
	}
	if (!Ret)
		doMSG(field,crit,msg);
	return(Ret);
} 


 // �ж��Ƿ��ǿ�
function Is_Null(field)
{
	Text=""+field.value;
	if(Text.length)
	{
		for(var i=0;i<Text.length;i++)
		if(Text.charAt(i)!=" ")
		break;
		if(i>=Text.length){
		    Ret=true;
		} else { 
		    Ret=false;
		}
	}
	else
		Ret=true;
	return(Ret);
}

function IsNull(field,crit,msg)
{
	Text=""+field.value;
	if(Text.length)
	{
		for(var i=0;i<Text.length;i++)
		if(Text.charAt(i)!=" ")
		break;
		if(i>=Text.length)
			Ret=true;
		else
			Ret=false;
	}
	else
		Ret=true;
	if (Ret)
		doMSG(field,crit,msg);  
	return(Ret);
}
function doMSG(field,crit,msg)
{
	if ( (-1!=crit) )
	{
		alert(msg)
		if (crit==1)
		{
			field.focus();  // focus does not work on certain netscape versions
			field.select();
		}
	}
}
function IsSpace(field)
{
	var Text=""+field.value;
	if(Text.length)
	{
		for(var i=0;i<Text.length;i++)
			if(Text.charAt(i)!=" ")
				break;
		if(i>=Text.length)
			field.value="";
	}
}
 
// �ж��Ƿ�������
function is_int(field){
	var Ret = true;
	var NumStr="0123456789";
	var chr;

	for (i=0;i<field.length;++i)
	{
		chr=field.charAt(i);
		if (NumStr.indexOf(chr,0)==-1)
		{
			Ret=false;
		}
	}
    if (Number(field) > 2147483647) {
        Ret = false;
    }
	return(Ret);
}
// �ж��Ƿ�������
function is_date_old(field){
	var Ret = false;
	var mark1;
	var mark2;

	if(field.value=="")
		return true;
	cd=new Date();

	if ( (mark1 = field.value.indexOf('-'))==-1)
		mark1=field.value.indexOf('-')
	if (mark1>-1)
	{
		if ( (mark2 = field.value.indexOf('-',mark1+1)) ==-1)
			mark2=field.value.indexOf('-',mark1+1);
		if ((mark2>-1)&&(mark2+1<field.value.length) )
		{
			year = new var_to_obj(field.value.substring(0,mark1));
			month = new var_to_obj(field.value.substring(mark1+1,mark2));
			day = new var_to_obj(field.value.substring(mark2+1,field.value.length));
			if (
				(is_greater(day,0))&&(is_less(day,32))&&
				(is_greater(month,0))&&(is_less(month,13))&&
				(is_greater(year,1900))&&(is_less(year,2500))
				)
				Ret=true;
		}
	}
	return(Ret);
}

// �ж��Ƿ�������
function is_date(field){

	var Ret = false;
	var mark1;
	var mark2;
	var month_n;
	var year_n;
	
	if(field.value=="")
		return true;
		
	vDate1 = field.value.split("-")

	 if (vDate1[0].length != 4) {
	     return false;
	 }
	 if (vDate1[1].length != 2) {
	     return false;
	 }
	 if (vDate1[2].length != 2) {
	     return false;
	 }
	 
	cd=new Date();

	if ( (mark1 = field.value.indexOf('-'))==-1)
		mark1=field.value.indexOf('-')
	if (mark1>-1)
	{
		if ( (mark2 = field.value.indexOf('-',mark1+1)) ==-1)
			mark2=field.value.indexOf('-',mark1+1);
		if ((mark2>-1)&&(mark2+1<field.value.length) )
		{
			year_n=field.value.substring(0,mark1);
			year = new var_to_obj(year_n);
			month_n=field.value.substring(mark1+1,mark2);
			month = new var_to_obj(month_n);
			day = new var_to_obj(field.value.substring(mark2+1,field.value.length));
			if ((is_greater(day,0))&&(is_less(day,32))&&(is_greater(month,0))&&(is_less(month,13))&&(is_greater(year,1900))&&(is_less(year,2500))){
			
			month_n = month_n.replace("0", " ");
			month_n = Trim(month_n);

			     	  switch(month_n){
			    		case "4":
			   		case "6":
			   		case "9":
			   		case "11":
			   			if(is_less(day,31))
			   			    Ret=true;
							
			   			break;		
			   				
			   		case "2":
			   			if((year_n%4==0 && year_n%100!=0)||(year_n%400==0)){
			   			    if(is_less(day,30))
			   			    	 Ret=true;
						}else{
						   if(is_less(day,29))
			   			    	 Ret=true;   		
						}			   			       			    
						 break;	
					default:
						Ret=true;
				  }  
			     }
		        
		}
	}
    return(Ret);
}

//����˵��:�ж��Ƿ������ڣ�
//����˵����sValue����Ҫ�жϵ��ַ���
//         iType������������
//             0����YYYY-MM-DD
//             1����YYYY-MM-DD HH
//             2����YYYY-MM-DD HH:MI
//             3����YYYY-MM-DD HH:MI:SS
//             4����YYYY��MM��DD��
//����ֵ��
//       ���Ϊ�շ��ؿ�
//       ������ͷ�����false
//       ����Ϸ�������iTypeָ�������ڸ�ʽ��������
function is_Date(sValue, iType) {
	var sDateRange1 = "0123456789-/. :"//�����ַ���Χ
	var sDateRange2 = "0123456789"//�����ַ���Χ

    sValue = Trim(sValue);

	if (sValue == "") {
		return sValue;
	}

	if (iType == null) {
		iType = 0;
	}

	tempValue = sValue.replace(/\//g,"");
	tempValue = tempValue.replace(/\./g,"");
	tempValue = tempValue.replace(/\-/g,"");
	tempValue = tempValue.replace(/\ /g,"");
	tempValue = tempValue.replace(/\:/g,"");

	if (tempValue.length > 14) {//�ַ������Ȳ��ܳ���14
		return false;
	} 
	
	var IFExistDigit = false;
	for (i = 0; i < sValue.length; i++) {
		var _tempStr = sValue.charAt(i);
		if (sDateRange1.indexOf(_tempStr, 0) == -1) {//�����Ƿ��ַ�
			return false;
			break;
		}

		if (sDateRange2.indexOf(_tempStr, 0) > -1) {
			IFExistDigit = true;
		}
	}

	if (IFExistDigit == false) {//����������
		return false;
	}

	sValue = sValue.replace(/\//g,"-");
	sValue = sValue.replace(/\./g,"-");

	if (sValue.split("-").length > 3) {//��-���ַ��ĸ�������Ϊ����3
		return false;
	}
	if (sValue.split(":").length > 3) {//��:���ַ��ĸ�������Ϊ����3
		return false;
	}
	if (sValue.split(" ").length > 2) {//�� ���ַ��ĸ�������Ϊ����2
		return false;
	}
	if (sValue.split("-").length == 1 && sValue.split(":").length > 1) {//ֻ�С������ޡ�-�������
		return false;
	}

	var aValue = sValue.split("-");
	var iYear = null;
	var iMonth = null;
	var iDay = null;
	var iHours = null;
	var iMinutes = null;
	var iSeconds = null;

	if (!is_int(aValue[0])) {//��ݲ��ǷǸ�����
		return false;
	}
	iYear = aValue[0] - 1 + 1;
	if (iYear < 1900 || iYear > 2050) {//���Ӧ��1950����2050֮��
		return false;
	}

	if (aValue.length > 1) {//�·�
		if (!is_int(aValue[1])) {//�·ݲ��ǷǸ�����
			return false;
		}
		iMonth = aValue[1] - 1 + 1;
		if (iMonth < 0 || iMonth > 12) {//�·���1-12֮��
			return false;
		}
	}

	if (aValue.length == 3) {//����
		var aValue1 = aValue[2].split(" ");
		if (!is_int(aValue1[0])) {//���ڲ��ǷǸ�����
			return false;
		}
		iDay = aValue1[0] - 1 + 1;
		switch (iMonth) {
			case 0:
				if (iDay != 0) {
					return false;
				}
				break;
			case 1:
				if (iDay < 0 || iDay > 31) return false;
				break;
			case 2:
				if ((iDay < 0) || (iYear % 4 == 0 && iDay > 29) || (iYear % 4 != 0 && iDay > 28)) return false;
				break;
			case 3:
				if (iDay < 0 || iDay > 31) return false;
				break;
			case 4:
				if (iDay < 0 || iDay > 30) return false;
				break;
			case 5:
				if (iDay < 0 || iDay > 31) return false;
				break;
			case 6:
				if (iDay < 0 || iDay > 30) return false;
				break;
			case 7:
				if (iDay < 0 || iDay > 31) return false;
				break;
			case 8:
				if (iDay < 0 || iDay > 31) return false;
				break;
			case 9:
				if (iDay < 0 || iDay > 30) return false;
				break;
			case 10:
				if (iDay < 0 || iDay > 31) return false;
				break;
			case 11:
				if (iDay < 0 || iDay > 30) return false;
				break;
			case 12:
				if (iDay < 0 || iDay > 31) return false;
				break;
		}
		
		if (aValue1.length == 2) {
			var aValue2 = aValue1[1].split(":");
			if (!is_int(aValue2[0])) {//Сʱ���ǷǸ�����
				return false;
			} 
			iHours = aValue2[0] - 1 + 1;
			if ((iMonth == 0 || iDay == 0) && iHours != 0) {
				return false;
			}

			if (iHours < 0 || iHours > 23) {//СʱӦ��0��23֮��
				return false;
			}

			if (aValue2.length > 1) {
				if (!is_int(aValue2[1])) {//�ֲ��ǷǸ�����
					return false;
				}
				
				iMinutes = aValue2[1] - 1 + 1;
				if ((iMonth == 0 || iDay == 0) && iMinutes != 0) {
					return false;
				}
				if (iMinutes < 0 || iMinutes > 59) {//��Ӧ��0��59֮��
					return false;
				}
			}

			if (aValue2.length == 3) {
				if (!is_int(aValue2[2])) {//�벻�ǷǸ�����
					return false;
				}
				
				iSeconds = aValue2[2] - 1 + 1;
				if ((iMonth == 0 || iDay == 0) && iSeconds != 0) {
					return false;
				}
				if (iSeconds < 0 || iSeconds > 59) {//��Ӧ��0��59֮��
					return false;
				}
			}
		}
	}

	if (iYear == null) {
		return false;
	}

	sReturn = "" + iYear;
	if (iMonth == null) {
		sReturn = sReturn + "00"
	} else {
		if (iMonth < 10) {
			sReturn = sReturn + "0" + iMonth;
		} else {
			sReturn = sReturn + iMonth;
		}
	}
	if (iDay == null) {
		sReturn = sReturn + "00"
	} else {
		if (iDay < 10) {
			sReturn = sReturn + "0" + iDay;
		} else {
			sReturn = sReturn + iDay;
		}
	}
	if (iHours == null) {
		sReturn = sReturn + "00"
	} else {
		if (iHours < 10) {
			sReturn = sReturn + "0" + iHours;
		} else {
			sReturn = sReturn + iHours;
		}
	}
	if (iMinutes == null) {
		sReturn = sReturn + "00"
	} else {
		if (iMinutes < 10) {
			sReturn = sReturn + "0" + iMinutes;
		} else {
			sReturn = sReturn + iMinutes;
		}
	}

	if (iSeconds == null) {
		sReturn = sReturn + "00"
	} else {
		if (iSeconds < 10) {
			sReturn = sReturn + "0" + iSeconds;
		} else {
			sReturn = sReturn + iSeconds;
		}
	}

	switch (iType) {
		case 0:
			sReturn = sReturn.substring(0, 4) + "-" + sReturn.substring(4, 6) + "-" + sReturn.substring(6, 8);
			break;
		case 1:
			sReturn = sReturn.substring(0, 4) + "-" + sReturn.substring(4, 6) + "-" + sReturn.substring(6, 8) + " " + sReturn.substring(8, 10);
			break;
		case 2:
			sReturn = sReturn.substring(0, 4) + "-" + sReturn.substring(4, 6) + "-" + sReturn.substring(6, 8) + " " + sReturn.substring(8, 10) + ":" + sReturn.substring(10, 12);
			break;
		case 3:
			sReturn = sReturn.substring(0, 4) + "-" + sReturn.substring(4, 6) + "-" + sReturn.substring(6, 8) + " " + sReturn.substring(8, 10) + ":" + sReturn.substring(10, 12) + ":" + sReturn.substring(12, 14);
			break;
		case 4:
			sReturn = sReturn.substring(0, 4) + "��" + sReturn.substring(4, 6) + "��" + sReturn.substring(6, 8) + "��";
			break;
	}

	return sReturn;
}
  
// �ж��Ƿ�����Ч���ݱ�ѡ��
function IsSelected(field)
{
	value=""+field.options[field.selectedIndex].value;
	if(value=="0")
		Ret=false;
	else
		Ret=true;
	return(Ret);
}

function isLoginIDCharacter(cCharacter) {

	var sFormat = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789_";
    if (sFormat.indexOf(cCharacter, 0) == -1) {
	    return false;
    }
	return true;
}

function isLoginID(sValue) {
	if (sValue == null)	{
		return false;
	}
	
	for (var i=0;i<sValue.length;i++) {
		var cCharacter = sValue.charAt( i );
		if (isLoginIDCharacter(cCharacter) == false) {
			return false;
		}
	}
	
	return true;
}

// ����Ƿ����ַ�
// cCharacter������ֵ
function isCharacter( cCharacter )
{
	var sFormat = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
	
	if( sFormat.indexOf( cCharacter, 0 ) == -1 )
	{
		return false;
	}
	
	return true;
}

// ����Ƿ��������ַ�
// cCharacter������ֵ
function isDigitalCharacter( cCharacter )
{
	var sFormat = "0123456789";
	
	if( sFormat.indexOf( cCharacter, 0 ) == -1 )
	{
		return false;
	}
	
	return true;
}

// ����Ƿ����������������Ƶ��ַ�
// cCharacter������ֵ
function isOtherNameCharacter( cCharacter )
{
	var sFormat = "_";
	
	if( sFormat.indexOf( cCharacter, 0 ) == -1 )
	{
		return false;
	}
	
	return true;
}

// ����Ƿ��ǿ��������Ƶ��ַ�
// sValue������ֵ
function isNameCharacter( sValue ) {
/*
	if( sValue == null )
	{
		return false;
	}
	
	for( i = 0; i < sValue.length; i ++ )
	{
		var cCharacter = sValue.charAt( i );
		if( isCharacter( cCharacter ) == false && isOtherNameCharacter( cCharacter ) == false )
		{
			return false;
		}
	}
*/	
	return true;
}

// ����Ƿ�������ַ�������
// sValue������ֵ
function isCharacterAndDigital( sValue ) {

/*
	if( sValue == null )
	{
		return false;
	}
	
	for( i = 0; i < sValue.length; i ++ )
	{
		var cCharacter = sValue.charAt( i );
		if( isCharacter( cCharacter ) == false && isDigitalCharacter( cCharacter ) == false )
		{
			return false;
		}
	}
*/	
	return true;
}

function isCharacterAndDigitalAndSpaceCharacter(cCharacter) {
/*
	var sFormat = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789  ";
    if (sFormat.indexOf(cCharacter, 0) == -1) {
	    return false;
    }
*/
	return true;
}

function isCharacterAndDigitalAndSpace(sValue) {

/*	
	if (sValue == null)	{
		return false;
	}
	
	for (var i=0;i<sValue.length;i++) {
		var cCharacter = sValue.charAt( i );
		if (isCharacterAndDigitalAndSpaceCharacter(cCharacter) == false) {
			return false;
		}
	}
*/	
	return true;
}

// ����Ƿ���Email
// sValue������ֵ���Ϸ���ʽΪa@b.c.d������ʽ
function isEmail( sValue )
{
	var iFirstIndex = 0;
	var iSecondIndex = sValue.indexOf( '@' );
	if( iSecondIndex == -1 )
	{
		return false;
	}

	var sTemp = sValue.substring( iFirstIndex, iSecondIndex );
	// Modified by Zhenghao: 01-03-22 To eliminate treat number and Chinese Character as invalid character
	//if( isNameCharacter( sTemp ) == false )
	//{
	//	return false;
	//}
	// Modified end
	
	iSecondIndex = sValue.indexOf( '.' );
	if( iSecondIndex == -1 || sValue.substring( sValue.length-1, sValue.length ) == '.' )
	{
		return false;
	}
	else if(  sTemp.length == sValue.length - 2 )	// The last two characters are '@' and '.'
	{
		return false;
	}
	else
	{
		var sTempValue = sValue;
		iSecondIndex = sValue.indexOf( '@' );
		while( iSecondIndex != -1 )
		{
			iFirstIndex = iSecondIndex + 1;
			sTempValue = sTempValue.substring( iFirstIndex, sTempValue.length );	// The right section of value
			iSecondIndex = sTempValue.indexOf( '.' );
			// document.write( "sTempValue=" + sTempValue + "<br>" );
			sTemp = sTempValue.substring( 0, iSecondIndex );
			// document.write( "sTemp=" + sTemp + "<br>" );
			for( i = 0; i < sTemp.length; i ++ )
			{
				var cCharacter = sTemp.charAt( i );
				if( isCharacter( cCharacter ) == false && isOtherNameCharacter( cCharacter ) == false && isDigitalCharacter( cCharacter ) == false )
				{
					return false;
				}
			}
		}
		
		for( i = 0; i < sTempValue.length; i ++ )
		{
			var cCharacter = sTempValue.charAt( i );
			if( isCharacter( cCharacter ) == false && isOtherNameCharacter( cCharacter ) == false	&& isDigitalCharacter( cCharacter ) == false )
			{
				return false;
			}
		}
	}
	
	return true;
}

// ����Ƿ����ʱ�
// sValue������ֵ���Ϸ���ʽΪ��λ����
function isZIP( sValue ) {
/*
	if( sValue == null )
	{
		return false;
	}
	
	if( sValue.length != 6 )
	{
		return false;
	}
	else
	{
		for( i = 0; i < 6; i ++ )
		{
			if( isDigital( sValue.charAt( i ) ) == false )
			{
				return false;
			}
		}
	}
*/	
	return true;
} 

// ����Ƿ��������ַ���
// sValue������ֵ
function isDigitalString( sValue )
{
	if( sValue == null )
	{
		return false;
	}

	for( i = 0; i < sValue.length; i ++ )
	{
		if( isDigital( sValue.charAt( i ) ) == false )
		{
			return false;
		}
	}
}


//IsEmpty�����ж�һ���ַ����Ƿ�Ϊ��
function IsEmpty(his)
{
   flag = true;
   for(var i=0;i<his.length;i++)
   {
      if(his.charAt(i)!=" ")
      { 
         flag = false; 
         break;
      }
   }
   return flag;
}
//Trim����ȥ��һ�ַ������ߵĿո�
function Trim(his)
{
   //�ҵ��ַ�����ʼλ��
   Pos_Start = -1;
   for(var i=0;i<his.length;i++)
   {
     if(his.charAt(i)!=" ")
      {
         Pos_Start = i;
         break; 
      }
   }
   //�ҵ��ַ�������λ��
   Pos_End = -1;
   for(var i=his.length-1;i>=0;i--)
   {
     if(his.charAt(i)!=" ")
      {
         Pos_End = i; 
         break; 
      }
   }
   //���ص��ַ���
   Str_Return = ""
   if(Pos_Start!=-1 && Pos_End!=-1)
   {   
		for(var i=Pos_Start;i<=Pos_End;i++)
		{
			   Str_Return = Str_Return + his.charAt(i); 
		}
   }
   return Str_Return;
}  
//IsDigital�����ж�һ���ַ����Ƿ�������(int or long)��� 
function isDigital(str)   
{
  for(ilen=0;ilen<str.length;ilen++)
  {
    if(str.charAt(ilen) < '0' || str.charAt(ilen) > '9' )
    {
       return false;
    }   
  }
  return true;
}
//IsFloat�����ж�һ���ַ����Ƿ�������(int or long or float)��� 
function IsFloat(str)   
{
  flag_Dec = 0
  for(ilen=0;ilen<str.length;ilen++)
  {
    if(str.charAt(ilen) == '.')
    {
       flag_Dec++;
	   if(flag_Dec > 1)
          return false;
       else
          continue;
    }         
    if(str.charAt(ilen) < '0' || str.charAt(ilen) > '9' )
    {
       return false;
    }   
  }
  return true;
}
//IsTelephone�����ж�һ���ַ����Ƿ������ֻ�'-','*'��� 
function IsTelephone(str) {
/*
  for(ilen=0;ilen<str.length;ilen++)
  {
    if(str.charAt(ilen) < '0' || str.charAt(ilen) > '9' )
    {
	if((str.charAt(ilen)!='-')&&(str.charAt(ilen)!='*')&&(str.charAt(ilen)!='(')&&(str.charAt(ilen)!=')'))
        return false;
    }   
  }
*/
  return true;
}

// The following added by liyin
// 2000-09-29
//is_IDCard�����ж�һ���ַ����Ƿ�������֤�ĳ������ƺ��������� 
function is_IDCard(str)   
{
  if((str.length!=15)&&(str.length!=18))
  	return false;
  for(ilen=0;ilen<str.length;ilen++)
  {
    if(str.charAt(ilen) < '0' || str.charAt(ilen) > '9' )
    {
	if((str.charAt(ilen)!='-')&&(str.charAt(ilen)!='*'))
        return false;
    }   
  }
  return true;
}


//The following code is for input validate
//Code by James Wang
//2000-07-22
function doCritCode(item) {
	if (Trim(item[3])!="") window.alert(item[3]);	
	eval(item[1]).focus();
	eval(item[1]).select();  
}

//The paramater parss looks like
// var paras = [
//  ["T","form1.f1","NotNull","f1 not null"],
//  ["T","form1.f2","IsZip","f2 is a zip code"],
//  ["T","form1.f3","IsDate","f3 is a date"],
//  ["T","form1.f4","IsEMail","f4 is a email"],
//  ["T","form1.f5","IsNumber","f5 is a number"]
//  ] 
//
//˵����
//��һ������Ϊ�������ͣ�
//   T  ��ʾΪType=input
//   
//�ڶ���������ʾ����������	
//   
//   ����Form.fieldName��ʽ��ʾ
//������������ʾ���ݿ��Լ��
//1������T�͵�����
//   NotNull   ��ʾ�������Ϊ�ǿ�
//   IsEMail   ��ʾ���������
//   IsNumber  ��ʾ�������Ϊһ������
//   IsInt     ��ʾ�������Ϊһ������
//   IsDate    ��ʾ��������һ������
//   IsZip     ��ʾ��������һ��ZipCode
//   IsTelephone ��ʾ��������һ���绰����
//   IsIDCard ��ʾ��������һ�����֤����
//   IsInLengthLimition ��ʾ�����Ƿ������ݿ�ĳ�������֮��
//   HowLength=##  ��ʾ�ж���ֵ�Ƿ񳬳� ����##��ʾ�ֶ������󳤶� 
//���ĸ�������ʾ������ݲ��ϸ���ʾ����Ϣ�����Ϊ���ڲ���ʾ
//
function inputValidate(paras) {
	for ( var i=0; i<paras.length; i++ ) {
		if ( paras[i][0] =="T" ) {
			//����������ȷ��ĳ��ǿ�
   		if (paras[i][2] == "NotNull" ) {
	   		if ( Trim(eval (paras[i][1]+".value")) =="" ) {
					doCritCode(paras[i]);
					return false;
				}
			}
			//����������ȷ��ĳ��ΪEmail
   		if (paras[i][2] == "IsEMail" ) {
	   		if ( (Trim(eval (paras[i][1]+".value")) !="") && ! isEmail(eval (paras[i][1]+".value")) ) {
					doCritCode(paras[i]);
					return false;
				}
			}
			//����������ȷ��ĳ��Ϊ����
   		if (paras[i][2] == "IsNumber" ) {
	   		if ( (Trim(eval (paras[i][1]+".value")) !="") &&  ! IsFloat(eval (paras[i][1]+".value")) ) {
					doCritCode(paras[i]);
					return false;
				}
			}
			//����������ȷ��ĳ��Ϊ����
   		if (paras[i][2] == "IsInt" ) {
	   		if ( (Trim(eval (paras[i][1]+".value")) !="") &&  ! is_int(eval (paras[i][1]+".value")) ) {
					doCritCode(paras[i]);
					return false;
				}
			}
			//����������ȷ��ĳ��Ϊ���֤����
   		if (paras[i][2] == "IsIDCard" ) {
   			if ( (Trim(eval (paras[i][1]+".value")) !="") &&  ! is_IDCard(eval (paras[i][1]+".value")) ) {
					doCritCode(paras[i]);
					return false;
				}
			}	
			//����������ȷ��ĳ��Ϊ����
   		if (paras[i][2] == "IsDate" ) {
	   		if ( (Trim(eval (paras[i][1]+".value")) !="") && ! is_date(eval (paras[i][1])) ) {
					doCritCode(paras[i]);
					return false;
				}
			}
			//����������ȷ��ĳ��Ϊ����
   		if (paras[i][2] == "IsDigital" ) {
	   		if ( (Trim(eval (paras[i][1]+".value")) !="") && ! isDigital(eval (paras[i][1]+".value")) ) {
					doCritCode(paras[i]);
					return false;
				}
			}
			
			//����������ȷ��ĳ��Ϊ��������
   		if (paras[i][2] == "IsZip" ) {
	   		if ( (Trim(eval (paras[i][1]+".value")) !="") && ! isZIP(eval (paras[i][1]+".value")) ) {
					doCritCode(paras[i]);
					return false;
				}
			}
			//����������ȷ��ĳ��Ϊ�绰����
   		if (paras[i][2] == "IsTelephone" ) {
	   		if ( (Trim(eval (paras[i][1]+".value")) !="") && ! IsTelephone(eval (paras[i][1]+".value")) ) {
					doCritCode(paras[i]);
					return false;
				}
			}
			//����������ȷ��ĳ��Ϊ���֤
   		if (paras[i][2] == "IsIDCard" ) {
	   		if ( (Trim(eval (paras[i][1]+".value")) !="") && ! is_IDCard(eval (paras[i][1]+".value")) ) {
					doCritCode(paras[i]);
					return false;
				}
			}
			//�ж��Ƿ񳤶ȴ���Ҫ�����С����
		if (paras[i][2].substring(0,9) == "MinLength" ) {
				if ( (Trim(eval (paras[i][1]+".value")) !="") && ! Is_InLengthMin(paras[i][2].substring(10),eval (paras[i][1]+".value")) ) {
					doCritCode(paras[i]);
					return false;
				}
			}
					
		//�ж���Ӣ�Ļ�����ֶγ���  function Is_InLengthLimit(iLimition,field,crit,msg)		
		if (paras[i][2].substring(0,9) == "HowLength" ) {
				if ( (Trim(eval (paras[i][1]+".value")) !="") && ! Is_InLengthLimit(paras[i][2].substring(10),eval (paras[i][1]+".value")) ) {
					doCritCode(paras[i]);
					return false;
				}
			}	

		//�ж��Ƿ��ΪӢ����ĸ������		
		if (paras[i][2] == "IsCharacterAndDigital" ) {
				if ( (Trim(eval (paras[i][1]+".value")) !="") && ! isCharacterAndDigital(eval (paras[i][1]+".value")) ) {
					doCritCode(paras[i]);
					return false;
				}
			}	
		}
	}
	
	return true;
}

// The following added by Zhenghao
// 2000-09-29
//���ж�һ���ַ��Ƿ���ASCIIֵ
//cValue������ֵ
function isASCII( cValue )
{
	var sFormat = " !\"#$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_`abcdefghijklmnopqrstuvwxyz{|}~";
	var iLocation = sFormat.indexOf( cValue );
	return( iLocation != -1 );
}

//��һ���ַ����еĺ��ּ�Ϊ2���ַ������������ݿ�����ȷ����
//sString�����������ַ���
function calcRealLength( sString )
{
	var iLength = 0;	// ��ʵ���ȼ�����
	for( i = 0; i < sString.length; i ++ )
	{
		if( isASCII( sString.charAt( i ) ) )
		{
			iLength += 1;
		}
		else
		{
			iLength += 2;
		}
	}
	
	return( iLength );
}

//�жϳ����Ƿ�������ݿ�ĳ���Ҫ��
//iLimition����������
//field�������ֶ�����
//crit���Ƿ���ʾ��ʾ��Ϣ���ûؽ���
//msg����ʾ��Ϣ
function Is_InLengthLimit(iLimition,Fieldvalue)
{	  	
	if( calcRealLength( Fieldvalue ) > iLimition )
		return false;
	return true;
}

function Is_OverLength_ex(iLimition,field,crit,msg)
{
    Text = "" + field.value;
    if ( calcRealLength( Text ) > iLimition  ){
	    doMSG(field,crit,msg);  
	    return true;
	}
	return false;
}


function Is_InLengthMin(iLimition,Fieldvalue)
{	  	
	if( calcRealLength( Fieldvalue ) < iLimition )
		return false;
	return true;
}

//function Is_InLengthLimit(iLimition,Fieldvalue)
//{	  
//	alert( "Flag=" + isInLengthLimit( iLimition,Fieldvalue ) );
//}

//���ַ���ASCIIֵ�������ֵ���ַ�
//iFlag��1ʱ������ַ���ASCIIֵ��0ʱ�����ֵ��ASCII�ַ�
//cValue������ֵ
function asciiXvalue( iFlag, cValue )
{
	var sFormat = " !\"#$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_`abcdefghijklmnopqrstuvwxyz{|}~";
	if( iFlag == 1 )
	{
		var iLocation = sFormat.indexOf( cValue ) + 32;
		//document.write( "ASCII Value=" + iLocation );
		return iLocation;
	}
	else if( iFlag == 0 )
	{
		if( cValue > 126 || cValue < 32 )
		{
			document.write( "����ASCIIֵ������32��126֮�䣡" );
		}
		else
		{
			var cChar = sFormat.charAt( cValue - 32 );
			//document.write( "ASCII Code=" + cChar );
			return cChar;
		}
	}
	else
	{
		document.write( "�Ƿ�������" );
	}
}

//���ܽ���ת��
//iFlag��1ʱ������ַ���ASCIIֵ��0ʱ�����ֵ��ASCII�ַ�
//sSource������ֵ
function changePassword( iFlag, sSource )
{
	var sSourceFormat = " !\"#$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_`abcdefghijklmnopqrstuvwxyz{|}~";
	var sTargetFormat = " #$!\"'%&+,)*/0-.1423(58679:;<A?@B=>EFCJDHQGIPLMKUNTOWS[RZVXY\\]^_`abefciqdgomvhjkn~lzut{rpysx|w}";
	var sTarget = "";
	if( iFlag == 1 )
	{
		for( i = 0; i < sSource.length; i ++ )
		{
			sTarget += sTargetFormat.charAt( sSourceFormat.indexOf( sSource.charAt( i ) ) );
		}
		return sTarget;
	}
	else if( iFlag == 0 )
	{
		for( i = 0; i < sSource.length; i ++ )
		{
			sTarget += sSourceFormat.charAt( sTargetFormat.indexOf( sSource.charAt( i ) ) );
		}
		return sTarget;
	}
	else
	{
		document.write( "�Ƿ�������" );
	}
}

// Compare two date
// ���_Date2����_Date1������true
function _compareTwoDate(_Date1, _Date2) {
     vDate1 = _Date1.value.split("-")
	 vDate2 = _Date2.value.split("-")
	 
	 _Year1 = parseInt(vDate1[0]-0)
	 _Month1 = parseInt(vDate1[1]-0)
	 _Day1 = parseInt(vDate1[2]-0)

	 _Year2 = parseInt(vDate2[0]-0)
	 _Month2 = parseInt(vDate2[1]-0)
	 _Day2 = parseInt(vDate2[2]-0)

     if (_Year1 > _Year2) {
	    return false
	 }

	 if ((_Year1 == _Year2) && (_Month1 > _Month2)) {
	    return false
	 }

	 if ((_Year1 == _Year2) && (_Month1 == _Month2) && (_Day1 > _Day2)) {
	    return false
	 }

	 return true
}

function _compareTwoDate_ex(_Date1, _Hour1, _Min1, _Date2, _Hour2, _Min2) {
     vDate1 = _Date1.value.split("-")
	 vDate2 = _Date2.value.split("-")
	 
	 _Year1 = parseInt(vDate1[0]-0)
	 _Month1 = parseInt(vDate1[1]-0)
	 _Day1 = parseInt(vDate1[2]-0)

	 _Year2 = parseInt(vDate2[0]-0)
	 _Month2 = parseInt(vDate2[1]-0)
	 _Day2 = parseInt(vDate2[2]-0)

     H1 = parseInt(_Hour1.value-0)
	 H2 = parseInt(_Hour2.value-0)
	 M1 = parseInt(_Min1.value-0)
     M2 = parseInt(_Min2.value-0)
	 
	 if (_Year1 > _Year2) {
	    return false
	 }

	 if ((_Year1 == _Year2) && (_Month1 > _Month2)) {
	    return false
	 }

	 if ((_Year1 == _Year2) && (_Month1 == _Month2) && (_Day1 > _Day2)) {
	    return false
	 }

	 if ((_Year1 == _Year2) && (_Month1 == _Month2) && (_Day1 == _Day2) && (H1>H2)) {
	    return false
	 }

	 if ((_Year1 == _Year2) && (_Month1 == _Month2) && (_Day1 == _Day2) && (H1 == H2) && (M1>M2) ) {
	    return false
	 }

	 return true
}


function _compareTwoDateForString(_Date1, _Date2) {
     vDate1 = _Date1.split("-")
	 vDate2 = _Date2.split("-")
	 _Year1 = parseInt(vDate1[0]-0)
	 _Month1 = parseInt(vDate1[1]-0)
	 _Day1 = parseInt(vDate1[2]-0)

	 _Year2 = parseInt(vDate2[0]-0)
	 _Month2 = parseInt(vDate2[1]-0)
	 _Day2 = parseInt(vDate2[2]-0)

     if (_Year1 > _Year2) {
	    return false
	 }

	 if ((_Year1 == _Year2) && (_Month1 > _Month2)) {
	    return false
	 }

	 if ((_Year1 == _Year2) && (_Month1 == _Month2) && (_Day1 > _Day2)) {
	    return false
	 }

	 return true
}

// get current date
function _getCurrentDate() {
     _newDate = new Date()
	 _Year = _newDate.getYear()
	 _Month = 1 + _newDate.getMonth()
	 _Day = _newDate.getDate()

	 if (_Month.toString().length == 1) {
	     _Month = "0" + _Month;
     }

	 if (_Day.toString().length == 1) {
	     _Day = "0" + _Day;
	 }

	 _sDate = _Year + "-" + _Month + "-" + _Day
	 return _sDate
     }

// compare date with current date
function _biggerThanToday(_Date2) {
     vDate1 = _getCurrentDate().split("-")
	 vDate2 = _Date2.value.split("-")

	 _Year1 = parseInt(vDate1[0]-0)
	 _Month1 = parseInt(vDate1[1]-0)
	 _Day1 = parseInt(vDate1[2]-0)

	 _Year2 = parseInt(vDate2[0]-0)
	 _Month2 = parseInt(vDate2[1]-0)
	 _Day2 = parseInt(vDate2[2]-0)

         if (_Year1 > _Year2) {
	    return false
	 }

	 if ((_Year1 == _Year2) && (_Month1 > _Month2)) {
	    return false
	 }

	 if ((_Year1 == _Year2) && (_Month1 == _Month2) && (_Day1 >= _Day2)) {
	    return false
	 }

	 return true

}

// compare date with current year&&month
function _biggerThanTodayMonth(_Date2) {
     vDate1 = _getCurrentDate().split("-")
	 vDate2 = _Date2.value.split("-")

	 _Year1 = parseInt(vDate1[0]-0)
	 _Month1 = parseInt(vDate1[1]-0)
	 _Day1 = parseInt(vDate1[2]-0)

	 _Year2 = parseInt(vDate2[0]-0)
	 _Month2 = parseInt(vDate2[1]-0)
	 _Day2 = parseInt(vDate2[2]-0)

         if (_Year1 > _Year2) {
	    return false
	 }

	 if ((_Year1 == _Year2) && (_Month1 > _Month2)) {
	    return false
	 }

     return true

}

function _getDate(periodType, year, period) {

	var dates = new Array(_getCurrentDate(), _getCurrentDate());

	// Validate
	if (periodType != 0 && periodType != 1) {
		// Error period type
		alert("Error period type.\nPeriod type(0-1):" + periodType);
		return dates;
	}

	var intYear = 2000;
	if (isNaN(year) == true) {
		alert("Invalid year.\nYear:" + year);
		return dates;
	}
	intYear = parseInt(year);
	if (intYear < 1900 || intYear > 2100) {
		alert("Invalid year.\nYear(1900-2100):" + year);
		return dates;
	}

	var intPeriod = 1;
	if (isNaN(period) == true) {
	    alert("Invalid period.\nPeriod:" + period);
		return dates;
    }
	intPeriod = parseInt(period);
	if (periodType == 0) {
		// Season
		if (intPeriod < 1 || intPeriod > 4) {
			alert("Invalid season.\nSeason(1-4):" + period);
			return dates;
		}
	} else {
		// Month
		if (intPeriod < 1 || intPeriod > 12) {
			alert("Invalid month.\nMonth(1-12):" + period);
			return dates;
		}
	}

	if (periodType == 0) {
		// Season
		switch (intPeriod) {
			case 1 :
				dates[0] = year + "-1-1";
				dates[1] = year + "-3-31";
				break;
			case 2 :
				dates[0] = year + "-4-1";
				dates[1] = year + "-6-30";
				break;
			case 3 :
				dates[0] = year + "-7-1";
				dates[1] = year + "-9-30";
				break;
			case 4 :
				dates[0] = year + "-10-1";
				dates[1] = year + "-12-31";
				break;
		}
	} else {
		// Month
		switch (intPeriod) {
			case 1 :
				dates[0] = year + "-1-1";
				dates[1] = year + "-1-31";
				break;
			case 2 :
				dates[0] = year + "-2-1";
				if ((intYear % 400 == 0) || ((intYear % 4 == 0) && (intYear % 100 != 0))) {
					dates[1] = year + "-2-29";
				} else {
					dates[1] = year + "-2-28";
				}
				break;
			case 3 :
				dates[0] = year + "-3-1";
				dates[1] = year + "-3-31";
				break;
			case 4 :
				dates[0] = year + "-4-1";
				dates[1] = year + "-4-30";
				break;
			case 5 :
				dates[0] = year + "-5-1";
				dates[1] = year + "-5-31";
				break;
			case 6 :
				dates[0] = year + "-6-1";
				dates[1] = year + "-6-30";
				break;
			case 7 :
				dates[0] = year + "-7-1";
				dates[1] = year + "-7-31";
				break;
			case 8 :
				dates[0] = year + "-8-1";
				dates[1] = year + "-8-31";
				break;
			case 9 :
				dates[0] = year + "-9-1";
				dates[1] = year + "-9-30";
				break;
			case 10 :
				dates[0] = year + "-10-1";
				dates[1] = year + "-10-31";
				break;
			case 11 :
				dates[0] = year + "-11-1";
				dates[1] = year + "-11-30";
				break;
			case 12 :
				dates[0] = year + "-12-1";
				dates[1] = year + "-12-31";
				break;
		}
	}

	// Return
	return dates;
}

function _getFinanceDate(financeDay, periodType, year, period) {

	var dates = new Array(_getCurrentDate(), _getCurrentDate());

	// Validate
	if (financeDay < 0 || financeDay > 28) {
		// Error finance type
		alert("Error finance day.\nFinance type(0-28):" + financeDay);
		return dates;
	}

	if (periodType != 0 && periodType != 1) {
		// Error period type
		alert("Error period type.\nPeriod type(0-1):" + periodType);
		return dates;
	}

	var intYear = 2000;
	if (isNaN(year) == true) {
	    alert("Invalid year.\nYear:" + year);
		return dates;
	}
	intYear = parseInt(year);
	if (intYear < 1900 || intYear > 2100) {
	    alert("Invalid year.\nYear(1900-2100):" + year);
		return dates;
	}

	var intPeriod = 1;
	if (isNaN(period) == true) {
	    alert("Invalid period.\nPeriod:" + period);
		return dates;
	}
	intPeriod = parseInt(period);
	if (periodType == 0) {
		// Season
		if (intPeriod < 1 || intPeriod > 4) {
		    alert("Invalid season.\nSeason(1-4):" + period);
			return dates;
		}
	} else {
		// Month
		if (intPeriod < 1 || intPeriod > 12) {
		    alert("Invalid month.\nMonth(1-12):" + period);
			return dates;
		}
	}

	if (financeDay == 0) {
		return _getDate(periodType, year, period);
	}

	if (periodType == 0) {
		// Season
		switch (intPeriod) {
			case 1 :
				dates[0] = year + "-1-1";
				dates[1] = year + "-3-" + financeDay;
				break;
			case 2 :
				dates[0] = year + "-3-" + (financeDay + 1);
				dates[1] = year + "-6-" + financeDay;
				break;
			case 3 :
				dates[0] = year + "-6-" + (financeDay + 1);
				dates[1] = year + "-9-" + financeDay;
				break;
			case 4 :
				dates[0] = year + "-9-" + (financeDay + 1);
				dates[1] = year + "-12-31";
				break;
		}
	} else {
		// Month
		switch (intPeriod) {
			case 1 :
				dates[0] = year + "-1-1";
				dates[1] = year + "-1-" + financeDay;
				break;
			case 2 :
				dates[0] = year + "-1-" + (financeDay + 1);
				dates[1] = year + "-2-" + financeDay;
				break;
			case 3 :
				if (((intYear % 400 == 0) || ((intYear % 4 == 0) && (intYear % 100 != 0)))
					&& (financeDay == 28)) {
					dates[0] = year + "-2-" + (financeDay + 1);
				} else {
					dates[0] = year + "-3-1";
				}
				dates[1] = year + "-3-" + financeDay;
				break;
			case 4 :
				dates[0] = year + "-3-" + (financeDay + 1);
				dates[1] = year + "-4-" + financeDay;
				break;
			case 5 :
				dates[0] = year + "-4-" + (financeDay + 1);
				dates[1] = year + "-5-" + financeDay;
				break;
			case 6 :
				dates[0] = year + "-5-" + (financeDay + 1);
				dates[1] = year + "-6-" + financeDay;
				break;
			case 7 :
				dates[0] = year + "-6-" + (financeDay + 1);
				dates[1] = year + "-7-" + financeDay;
				break;
			case 8 :
				dates[0] = year + "-7-" + (financeDay + 1);
				dates[1] = year + "-8-" + financeDay;
				break;
			case 9 :
				dates[0] = year + "-8-" + (financeDay + 1);
				dates[1] = year + "-9-" + financeDay;
				break;
			case 10 :
				dates[0] = year + "-9-" + (financeDay + 1);
				dates[1] = year + "-10-" + financeDay;
				break;
			case 11 :
				dates[0] = year + "-10-" + (financeDay + 1);
				dates[1] = year + "-11-" + financeDay;
				break;
			case 12 :
				dates[0] = year + "-11-" + (financeDay + 1);
				dates[1] = year + "-12-31";
				break;
		}
	}

	// Return
	return dates;
}


function _formatNumber(myField){
    if ( myField.value != "") {
    	if ( myField.value.indexOf(".") == -1) {
    	    myField.value = myField.value + ".000";	
    	} else if ( (myField.value.length-myField.value.indexOf("."))<4 )  {
    	    for (var i=0;i<4-(myField.value.length-myField.value.indexOf("."));i++) {
    	    	alert(i);
    	    	myField.value += "0";
    	    }    	
    	}
    }
}
	
function _compareDate(sDate1, sDate2) {

     var vDate1 = sDate1.split("-")
	 var vDate2 = sDate2.split("-")
	 
	 _Year1 = parseInt(vDate1[0]-0)
	 _Month1 = parseInt(vDate1[1]-0)
	 _Day1 = parseInt(vDate1[2]-0)

	 _Year2 = parseInt(vDate2[0]-0)
	 _Month2 = parseInt(vDate2[1]-0)
	 _Day2 = parseInt(vDate2[2]-0)

	var date1 = new Date(_Year1, _Month1, _Day1);
	var date2 = new Date(_Year2, _Month2, _Day2);

	var dif = 0;

	var lDate2 = date2.getTime();
	var lDate1 = date1.getTime();

	dif = (lDate2 - lDate1) / 1000 / 60 / 60 / 24;

	return dif;
}

/**
 * Format number
 * parameter:
 *     _express: double
 *     iSize:    int
 *
 * _changeNumber(12.3456, 3) = 12.346
 */
function _changeNumber(_express, iSize) {
	_express = _express - 1 + 1;
	iSize = iSize - 1 + 1;
	
	iKey1 = Math.pow(10, 12);
	dTemp = Math.round(_express * iKey1);
	sTemp = "" + dTemp;
	iEndNum = sTemp.substring(sTemp.length - 1, sTemp.length) - 1 + 1
	if (iEndNum = 9) {
		dTemp = dTemp + 1;
	} else {
		dTemp = dTemp + 2;
	}
	
	dTemp = dTemp / iKey1;

	iKey = Math.pow(10, iSize);
	dTemp = Math.round(dTemp * iKey);
	
	return dTemp / iKey;
}

//format float data as:*****.**
//decplaces:С��λ��
function FloatFormat(expr,decplaces) {

	// judge if it is a valid number
	if (isNaN(expr)) {
		alert("Invalid number:" + expr);
		return 0;
	}

	// change to number
	nExpr = Number(expr);

	// multiple
	nExpr = nExpr * Math.pow(10, decplaces);

	// round
	nExpr = Math.round(nExpr);

	// division
	nExpr = nExpr / Math.pow(10, decplaces);

	var sExpr = nExpr.toString();

	// decimal point location
	var pointIndex = sExpr.indexOf('.');

	// patch zero
	var loop;
	if (pointIndex == -1) {
		sExpr = sExpr + ".";
		loop = decplaces - 1;
	} else {
		loop = decplaces - (sExpr.length - pointIndex);
	}
	for (var i=0;i<=loop;i++)	{
		sExpr = sExpr + "0";
	}

	// return
	return sExpr;
}

//��ʽ����ѯʱ��
function getFormatTime(iYear, sTime, iKey) {
    //iYear---2001
    //sTime---1,2,3,4
	//iKey---1:����;2:�·�

	var _str = null;
    var aTime = new Array();
	var iFlag = true;
	aTime = sTime.split(",")

	if (aTime.length < 2) {
	    iFlag = false;
	} else {
		for (i = 0; i < aTime.length - 1; i++) {
			 iPre = aTime[i] - 1 + 1;
			 iLast = aTime[i + 1] - 1 + 1;
			 if (iLast - iPre != 1) {
				 iFlag = false;
				 break;
			 }
		}
	}
	if (iFlag == true){
	    _str = iYear + "��" + aTime[0] + "-" + aTime[aTime.length - 1];
	} else {
	    _str = iYear + "��" + sTime;
	}

	if (iKey == 1) {
	    _str = _str + "����";
	} else {
	    _str = _str + "��";
	}

	return _str;
}

// ��������
function windowOpener(url, title, width, height)
{       
	window.open(url,title,"toolbar,resizable,scrollbars,dependent,width=" + var_to_obj(width) + ",height=" + var_to_obj(height));
}
function windowOpener(url, title)
{       
	window.open(url,title,"toobar=no,location=no,directories=no,status=no,scrollbars=yes,resizable=no,copyhistory=no,width=400,height=280");
}

//����Ƿ���radio��ѡ�� field:form1.appid
function IsChecked(field,msg)
{
       l = field.length;
       if(l == null){
	 if(!field.checked){
	  alert(msg);
	  return false
	 }
	 else{
	  return true
	 }
       }
       else{
         flag=0;
         for(i=0;i<l;i++){
	   if(field[i].checked==true){
	   flag++;
	   }
         }

         if(flag==0){
	   alert(msg);
	   return false
         }
         else{
	   return true
         }
       }
}

//ѡ��select��ѡ����
function selectItem(formItem,itemValue)
{
	n = parseFloat(formItem.length);
	for(i=0;i<n;i++){
		if(formItem.options[i].value == itemValue){
			formItem.options[i].selected = true;
			formItem.options[i].defaultSelected = true;
		}
	}
}

//ѡ��radio��ѡ��
function radioItem(formItem,itemValue)
{
	n = parseFloat(formItem.length);
	for(i=0;i<n;i++){
		if(formItem[i].value == itemValue){
			formItem[i].checked = true;
		}
	}
}

//formid��ʾ:���form�ǵڼ���form����form���� from��ʾ:������ѡ����Ŀ��select��������
//to��ʾ:�г���ѡ����Ŀ��select�������� limit��ʾ:����ѡ��ֵ,NotremoveFrom�Ƿ�ɾ��from��
//��ֵ,NotAddTo����ֵ�ӵ�to��
//��ѡ��Ԫ�ص�textname��������
function copyToList(formid,from,to,limit,NotRemoveFrom,NotAddTo)
{

	fromList = eval('document.forms[' + formid + '].' + from);
	toList = eval('document.forms[' + formid + '].' + to);
//	if(toList.options.length > 0 && toList.options[0].value == '0')
//	{
//		toList.options.length = 0;
//	}
	var sel = false;
	for (i=0;i<fromList.options.length;i++)
	{
		var current = fromList.options[i];
		if(current.selected)
		{
			sel = true;
			if(current.value == '0' || current.value == limit)
			{
				alert (current.text+" can not be selected!");
//				return;
			}
			else
			{
				txt = current.text;
				val = current.value;
        addFlag = true;
        for(m=0;m<toList.options.length;m++)
        {
          if(txt == toList.options[m].text)
          {
            addFlag = false;
            break;
          }
        }
        if(addFlag)
  				if(!NotAddTo) toList.options[toList.length] = new Option(txt,val);
  				if(!NotRemoveFrom){
  					fromList.options[i] = null;
					i--;
				}
			}
		}
	}
	if(!sel) alert ('Please select at least an item first!');
}

//formid��ʾ:form���ƻ�˳�� from��ʾ:��ҪɾԪ�صĶ���
//to��ʾ:ɾ������Ļ���վ limit��ʾ:ɾ��Ԫ��value���������
function deleteFromList(formid,from,to,limit)
{
	fromList = eval('document.forms[' + formid + '].' + from);
	toList = eval('document.forms[' + formid + '].' + to);
	for (i=0;i<fromList.options.length;i++)
	{
		if(fromList.options[i].value == limit){
			var current = fromList.options[i];
			sel = true;
			txt = current.text;
			val = current.value;
			toList.options[toList.length] = new Option(txt,val);
			fromList.options[i] = null;
			i--;
		}
	}
}

//���ǵ��û������ύ��ťʱ�����г�ѡ���select����ִ��ȫѡ�������õݽ����ĺ�̨������ȡ���������
//formid��ʾ:form����; item��ʾ:selectԪ������; pro��ʾ:���ص��ַ���ֵ����Դvalue��text;
function allSelect(formid,item,pro,sign)
{
	List = eval('document.forms[' + formid + '].' + item);
	str = "";
//	if (List.length && List.options[0].value == '0') return;
	for (i=0;i<List.length;i++)
	{
		if(List.options[i].value!=0)
		{
			List.options[i].selected = true;
			str += sign + eval('List.options[i].' + pro) + sign + ',';
		}
		else
		{
			List.options[i].selected = false;
		}
	}
	if(str.length > 0)
		str = str.substring(0,str.length-1);
	return str;
}

function allSelected(formid,item,pro,sign)
{
	List = eval('document.forms[' + formid + '].' + item);
	str = "";
//	if (List.length && List.options[0].value == '0') return;
	for (i=0;i<List.length;i++)
	{
		if(List.options[i].selected)
		{
			str += sign + eval('List.options[i].' + pro) + sign + ',';
		}
	}
	if(str.length > 0)
		str = str.substring(0,str.length-1);
	return str;
}

function deSelectAll(formid,item)
{
	List = eval('document.forms[' + formid + '].' + item);

	for (i=0;i<List.length;i++)
	{
		if(List.options[i].selected = true)
		{
			List.options[i].selected = false;
		}
	}
}

//����ѡ�б���е�Ԫ�ؽ�������,formidΪҳ���е�id����,movenameΪ�б��������ַ���
function upmove(formid,movename)
{
 movelist = eval('document.forms[' + formid + '].' + movename);
 var sel = false;
	for (i=0;i<movelist.options.length;i++)
	{
		var current = movelist.options[i];
		if(current.selected)
		{
    		sel = true;
    		if(i==0 || movelist.options[i].value=="0")
    		{
    			alert ('Can not move up!');
    			return;
    		}
    		txt = current.text;
    		val = current.value;
    		uptxt=movelist.options[i-1].text;
    		upval=movelist.options[i-1].value;
    		movelist.options[i-1].value = val;
    		movelist.options[i-1].text=txt;
    		movelist.options[i].value=upval;
    		movelist.options[i].text=uptxt;
    		movelist.options[i-1].selected=true;
    		movelist.options[i].selected=false;
		}
	}
	if(!sel) alert ('Please select one element.');
}
//����ѡ�б���е�Ԫ�ؽ�������,formidΪҳ���е�id����,movenameΪ�б��������ַ���
function downmove(formid,movename)
{
 movelist = eval('document.forms[' + formid + '].' + movename);
 var sel = false;
	for (i=(movelist.options.length-1);i>=0;i--)
	{
		var current = movelist.options[i];
		if(current.selected)
		{
    		sel = true;
    		if(i==(movelist.options.length-1) || movelist.options[i].value=="0")
    		{
    			alert ('Can not move down!');
    			return;
    		}
    		txt = current.text;
    		val = current.value;
    		downtxt=movelist.options[i+1].text;
    		downval=movelist.options[i+1].value;
    		movelist.options[i+1].value = val;
    		movelist.options[i+1].text=txt;
    		movelist.options[i].value=downval;
    		movelist.options[i].text=downtxt;
    		movelist.options[i+1].selected=true;
    		movelist.options[i].selected=false;
		}
	}
	if(!sel) alert ('Please select one element.');
}

function displayOrNot(disp){
    if(disp.style.display == "none"){
        disp.style.display = "";
    }else{
        disp.style.display = "none";
    }
}

// facilitate validate
function validfield(field, limit, msg)  {
		if ( Trim(eval (field+".value")) =="no" ) {
					return true;
			}
   		if (limit == "NotNull" ) {
			var paras = [
						 ["T",field,"NotNull",msg]
				    ]
			if(!inputValidate(paras)) return false;
			}
   		if (limit == "IsEMail" ) {
			var paras = [
						 ["T",field,"IsEMail",msg]
				    ]
			if(!inputValidate(paras)) return false;
			}
			//����������ȷ��ĳ��Ϊ����
   		if (limit == "IsNumber" ) {
			var paras = [
						 ["T",field,"IsNumber",msg]
				    ]
			if(!inputValidate(paras)) return false;
			}
			//����������ȷ��ĳ��Ϊ����
   		if (limit == "IsInt" ) {
			var paras = [
						 ["T",field,"IsInt",msg]
				    ]
			if(!inputValidate(paras)) return false;
			}
			//����������ȷ��ĳ��Ϊ���֤����
   		if (limit == "IsIDCard" ) {
			var paras = [
						 ["T",field,"IsIDCard",msg]
				    ]
			if(!inputValidate(paras)) return false;
			}	
			//����������ȷ��ĳ��Ϊ����
   		if (limit == "IsDate" ) {
			var paras = [
						 ["T",field,"IsDate",msg]
				    ]
			if(!inputValidate(paras)) return false;
			}
			//����������ȷ��ĳ��Ϊ����
   		if (limit == "IsDigital" ) {
			var paras = [
						 ["T",field,"IsDigital",msg]
				    ]
			if(!inputValidate(paras)) return false;
			}
			
			//����������ȷ��ĳ��Ϊ��������
   		if (limit == "IsZip" ) {
			var paras = [
						 ["T",field,"IsZip",msg]
				    ]
			if(!inputValidate(paras)) return false;
			}
			//����������ȷ��ĳ��Ϊ�绰����
   		if (limit == "IsTelephone" ) {
			var paras = [
						 ["T",field,"IsTelephone",msg]
				    ]
			if(!inputValidate(paras)) return false;
			}
   		if (limit == "IsFax" ) {
			var paras = [
						 ["T",field,"IsFax",msg]
				    ]
			if(!inputValidate(paras)) return false;
			}
		return true;
}

//���ҳ������Ӣ�Ļ���ַ�����ʵ�ʳ��ȣ�strΪ�ַ�����ֵ
function getLength(str) {
    l=str.length
    all=0;
    for(i=0;i<l;i++){
        aim=str.charAt(i);
        aim=escape(aim);
        if ((aim.length==3)||(aim.length==1)){
            all=all+1;
        }
        if (aim.length==6){
            all=all+2;
        }
    }
    return all
}

// ȷ����֤
function _confirm(url, message)  {
	if(confirm(message))  {
		window.location.href=url;
	}
}

//ȫѡcheckbox
var checkflag = "false";
function check(field,select,deselcet) {
    if(field) {
    	if (checkflag == "false") {
    	    if(field.length) {
        		for (i = 0; i < field.length; i++) {
        			field[i].checked = true;
        		}
    	    }
    	    else {
    	        field.checked = true;
    	    }
    	    checkflag = "true";
        	return deselcet; 
    	}
    	else {
    	    if(field.length) {
        		for (i = 0; i < field.length; i++) {
        			field[i].checked = false; 
        		}
            }
            else {
                field.checked = false;
            }
    	    checkflag = "false";
    	    return select; 
    	}
    }
    else {
        return select;
    }
}
