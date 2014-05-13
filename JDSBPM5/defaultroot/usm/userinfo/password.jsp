<%@ page language="java"  pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="ww"%>
<html>
<head>
<style>
.pas_left{border-color:#000000;border-left:solid;font-size: 12px;text-align: center;
	vertical-align: middle;}
.pas_right{border-color:#000000;border-left:solid;border-right:solid;font-size: 12px;text-align: center;
	vertical-align: middle;}
.pas_bottom{border-color:#000000;border-left:solid; border-bottom:solid;font-size: 12px;text-align: center;
	vertical-align: middle;}
.pas_all{border-color:#000000;border-left:solid; border-right:solid;border-bottom:solid;font-size: 12px;text-align:center;
	vertical-align: middle;}
.STYLE1 {
	color: #FFFFFF;
	font-weight: bold;
	font-size: 14px;
}
</style>
<script type="text/javascript">

function modifypassword(){
	if(Ext.get('personaccount.password').dom.value == Ext.get('pasw').dom.value )
		ajaxrequest();
	else
		Ext.Msg.alert('提示','请核对确认密码');
}
function ajaxrequest(){
	
	var a4 = Ext.get('personaccount.uuid').dom.value;
	var a5 = Ext.get('personaccount.personid').dom.value;
	var a1 = Ext.get('cnname').dom.value;
	var a2 = Ext.get('enname').dom.value;
	var a3 = Ext.get('personaccount.password').dom.value;
	
	var action = 'personmodify.do?personaccount.uuid='+a4+'&personaccount.personid='+a5+'&personaccount.password='+a3;
     Ext.Ajax.request({
     	url:action,
     	method:'post',
    	success:function(req){
       		if(req.responseText==1)
         	{  Ext.Msg.alert('成功','您的密码修改成功!');}
       		else
         	{ Ext.Msg.alert('警告','您的密码修改失败!'+req.responseText);}
    	}
  	});

     
	
}
</script>
 <link rel="stylesheet" type="text/css" href="../js/ext/resources/css/ext-all.css" />
 <script type="text/javascript" src="../js/ext/adapter/ext/ext-base.js"></script>
 <script type="text/javascript" src="../js/ext/ext-all.js"></script>
 <script language="javascript" src="../js/ext/locale/ext-lang-zh_CN.js"></script>
</head>
<body>
<form action="personmodify.do" id="pasm" name="pasm" method="post">
<input type="hidden" id="personaccount.uuid" name="personaccount.uuid" value="<ww:property 

value="personaccount.uuid"></ww:property>" />
<input type="hidden" id="personaccount.personid" name="personaccount.personid" value="<ww:property 

value="personaccount.personid"></ww:property>" />
<br>
<table width="250"  align="center" border="0" cellpadding="0" cellspacing="0">
  <!--DWLayoutTable-->
  <tr>
    <td height="35" colspan="2" align="center" valign="middle" background="/cwzx/cwzxclient/IMAGES/xin-tiao-5.jpg"><span 

class="STYLE1">委员密码修改</span></td>
  </tr>
  <tr>
    <td width="89" height="25" valign="top" class="pas_left">姓名</td>
    <td width="161" valign="top" class="pas_right"><input type="text" id="cnname" name="cnname" size="12" 

value="<ww:property value="personinfo.cnname"></ww:property>" /></td>
  </tr>
  <tr>
    <td height="25" valign="top" class="pas_left">登录帐号</td>
    <td valign="top" class="pas_right"><input type="text" id="enname" name="enname" size="12" value="<ww:property 

value="personaccount.userid"></ww:property>" /></td>
  </tr>
  
  
  <tr>
    <td height="25" valign="top" class="pas_left">现在密码</td>
    <td valign="top" class="pas_right"><input type="text" id="personaccount.password" name="personaccount.password" 

size="12" value="<ww:property value="personaccount.password"></ww:property>" /></td>
  </tr>
  <tr>
    <td height="25" valign="top" class="pas_bottom">确认密码</td>
    <td valign="top" class="pas_all"><input type="text" id="pasw" name="pasw" size="12"/></td>
  </tr>
  <tr>
    <td height="25" colspan="2" align="right" valign="top"><input type="button" value="修改" onclick="modifypassword()"/></td>
  </tr>
</table>
</form>
</body>
</html>