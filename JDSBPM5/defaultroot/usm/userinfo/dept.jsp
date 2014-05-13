<%@ page language="java"  pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="ww"%>
<html >
<head>
<title>承办单位信息</title>
<style>
.wyxx_title{
	font-size:30px;
	font-weight:800;
	text-align: center;
	vertical-align: middle;
	border-style: solid;
	border-color: #000000;
	border-width: 0px;

}
.wyxx_top{
	font-size:14px;
	text-align: center;
	vertical-align: middle;
	border-top-width: 1px;
	border-right-width: 0px;
	border-bottom-width: 0px;
	border-left-width: 0px;
	border-style: solid;
	border-color: #000000;
}
.wyxx_left_top{
	font-size:14px;
	text-align: center;
	vertical-align: middle;
	border-top-width: 1px;
	border-right-width: 0px;
	border-bottom-width: 0px;
	border-left-width: 1px;
	border-style: solid;
	border-color: #000000;
}
.wyxx_left_top_ccleft{
	font-size:14px;
	text-align: left;
	vertical-align: middle;
	border-top-width: 1px;
	border-right-width: 0px;
	border-bottom-width: 0px;
	border-left-width: 1px;
	border-style: solid;
	border-color: #000000;
}
.wyxx_left_top_right{
	font-size:14px;
	text-align: left;
	vertical-align: middle;
	border-top-width: 1px;
	border-right-width: 1px;
	border-bottom-width: 0px;
	border-left-width: 1px;
	border-style: solid;
	border-color: #000000;
}
.wyxx_left_top_right_cccenter{
	font-size:14px;
	text-align: center;
	vertical-align: middle;
	border-top-width: 1px;
	border-right-width: 1px;
	border-bottom-width: 0px;
	border-left-width: 1px;
	border-style: solid;
	border-color: #000000;
}
.wyxx_left_top_bottom{
	font-size:14px;
	text-align: center;
	vertical-align: middle;
	border-top-width: 1px;
	border-right-width: 0px;
	border-bottom-width: 1px;
	border-left-width: 1px;
	border-style: solid;
	border-color: #000000;
}
.wyxx_all{
	font-size:14px;
	text-align: center;
	vertical-align: middle;
	border-width: 1px;
	border-style: solid;
	border-color: #000000;
}
.wyxx_footer{
	font-size:14px;
	text-align: left;
	vertical-align: top;
}
</style>
 <link rel="stylesheet" type="text/css" href="../js/ext/resources/css/ext-all.css" />
 <script type="text/javascript" src="../js/ext/adapter/ext/ext-base.js"></script>
 <script type="text/javascript" src="../js/ext/ext-all.js"></script>
 <script language="javascript" src="../js/ext/locale/ext-lang-zh_CN.js"></script> 
<script type="text/javascript">

function ajaxrequest(){	
	var params = new Array(53); 
	params[0] = Ext.get('personinfo.uuid').dom.value;
	params[1] = Ext.get('personinfo.personid').dom.value;
	params[2] = Ext.get('personid').dom.value;
	params[3] = Ext.get('personinfo.enname').dom.value;
	params[4] = Ext.get('personaccount.personid').dom.value;
	params[5] = Ext.get('personinfo.idnum').dom.value;
	params[6] = Ext.get('personinfo.idtype').dom.value;
	params[7] = Ext.get('personinfo.marry').dom.value;
	params[8] = Ext.get('personinfo.officefax').dom.value;
	params[9] = Ext.get('personinfo.email2').dom.value;
	params[10] = Ext.get('personinfo.lastdegree').dom.value;
	params[11] = Ext.get('personinfo.school').dom.value;
	params[12] = Ext.get('personinfo.major').dom.value;
	params[13] = Ext.get('personinfo.xueli').dom.value;
	params[14] = Ext.get('personinfo.type').dom.value;
	params[15] = Ext.get('personinfo.indextype').dom.value;
	params[16] = Ext.get('personinfo.househao').dom.value;
	params[17] = Ext.get('personinfo.position').dom.value;
	params[18] = Ext.get('personaccount.uuid').dom.value;
	params[19] = Ext.get('personaccount.personid').dom.value;
	params[20] = Ext.get('personaccount.flag').dom.value;
	params[21] = Ext.get('personaccount.userid').dom.value;
	params[22] = Ext.get('personaccount.password').dom.value;
	params[23] = Ext.get('personaccount.accountstat').dom.value;
	params[24] = Ext.get('personaccount.loginfailnum').dom.value;
	params[25] = Ext.get('personaccount.lastloginip').dom.value;
	params[26] = Ext.get('personaccount.lastlogindate').dom.value;
	params[27] = Ext.get('personaccount.passquestion').dom.value;
	params[28] = Ext.get('personaccount.passanswer').dom.value;
	params[29] = Ext.get('personaccount.accountttl').dom.value;
	params[30] = Ext.get('personaccount.createtime').dom.value;
	params[31] = Ext.get('personinfo.photo').dom.value;
	params[32] = Ext.get('personinfo.cnname').dom.value;
	params[33] = Ext.get('personinfo.sex').dom.value;
	params[34] = Ext.get('personinfo.birthday').dom.value;
	params[35] = Ext.get('personinfo.nation').dom.value;
	params[36] = Ext.get('personinfo.lastedulevel').dom.value;
	params[37] = Ext.get('personinfo.politicalstat').dom.value;
	params[38] = Ext.get('personinfo.duty').dom.value;
	params[39] = Ext.get('personinfo.officeadd').dom.value;
	params[40] = Ext.get('personinfo.job').dom.value;
	params[41] = Ext.get('personinfo.connectaddr').dom.value;
	params[42] = Ext.get('personinfo.nativeplace').dom.value;
	params[43] = Ext.get('personinfo.officetel').dom.value;
	params[44] = Ext.get('personinfo.mobile').dom.value;
	params[45] = Ext.get('personinfo.homeadd').dom.value;
	params[46] = Ext.get('personinfo.zip').dom.value;
	params[47] = Ext.get('personinfo.hometel').dom.value;
	params[48] = Ext.get('personinfo.city').dom.value;
	params[49] = Ext.get('personinfo.homefax').dom.value;	
	params[50] = Ext.get('personinfo.email1').dom.value;
	params[51] = Ext.get('personinfo.dome').dom.value;
	for(var i = 0;i<52;i++){
		params[i] = encodeURIComponent(params[i]);
	}	
	var action = 'personInfo.do?personinfo.uuid='+params[0]+'&personinfo.personid='+params[1]+			'&personid='+params[2]+
				 '&personinfo.enname ='+params[3]+			'&personaccount.personid='+params[4]+		'&personinfo.idnum='+params[5]+
				 '&personinfo.idtype ='+params[6]+			'&personinfo.marry='+params[7]+				'&personinfo.officefax='+params[8]+
				 '&personinfo.email2='+params[9]+			'&personinfo.lastdegree='+params[10]+		'&personinfo.school='+params[11]+
				 '&personinfo.major='+params[12]+			'&personinfo.xueli='+params[13]+			'&personinfo.type='+params[14]+
				 '&personinfo.indextype='+params[15]+		'&personinfo.househao='+params[16]+			'&personinfo.position='+params[17]+
				 '&personaccount.uuid='+params[18]+			'&personaccount.personid='+params[19]+		'&personaccount.flag='+params[20]+
				 '&personaccount.userid='+params[21]+		'&personaccount.password='+params[22]+		'&personaccount.accountstat='+params[23]+
				 '&personaccount.loginfailnum='+params[24]+	'&personaccount.lastloginip='+params[25]+	'&personaccount.lastlogindate='+params[26]+
				 '&personaccount.passquestion='+params[27]+	'&personaccount.passanswer='+params[28]+	'&personaccount.accountttl='+params[29]+
				 '&personaccount.createtime='+params[30]+	'&personinfo.photo='+params[31]+			'&personinfo.cnname='+params[32]+
				 '&personinfo.sex='+params[33]+				'&personinfo.birthday='+params[34]+			'&personinfo.nation='+params[35]+
				 '&personinfo.lastedulevel='+params[36]+	'&personinfo.politicalstat='+params[37]+	'&personinfo.duty='+params[38]+
				 '&personinfo.officeadd='+params[39]+		'&personinfo.job='+params[40]+				'&personinfo.connectaddr='+params[41]+
				 '&personinfo.nativeplace='+params[42]+		'&personinfo.officetel='+params[43]+		'&personinfo.mobile='+params[44]+
				 '&personinfo.homeadd='+params[45]+			'&personinfo.zip='+params[46]+				'&personinfo.hometel='+params[47]+
				 '&personinfo.city='+params[48]+			'&personinfo.homefax='+params[49]+			'&personinfo.email1='+params[50]+
				 '&personinfo.dome='+params[51]; 
	//alert(action);
    Ext.Ajax.request({
     	url:action,
     	method:'post',
    	success:function(req){
       		if(req.responseText==1){  
       			Ext.Msg.alert('成功','您的信息修改成功!');
       		}else{ 
       			Ext.Msg.alert('警告','您的信息修改失败!');
       		}
    	}
  	});	
}
</script>
</head>
<body>
<form action="personInfo.do" id="personalinfo" name="personalinfo" method="post">
<input type="hidden" id="personinfo.homefax" name="personinfo.homefax" value="<ww:property value="personinfo.homefax"></ww:property>" />
<input type="hidden" id="personinfo.zip" name="personinfo.zip" value="<ww:property value="personinfo.zip"></ww:property>" />
<input type="hidden" id="personinfo.nativeplace" name="personinfo.nativeplace" value="<ww:property value="personinfo.nativeplace"></ww:property>" />
<input type="hidden" id="personinfo.birthday" name="personinfo.birthday" value="<ww:property value="personinfo.birthday"></ww:property>" />

<input type="hidden" id="personinfo.email1" name="personinfo.email1" value="<ww:property value="personinfo.email1"></ww:property>" />

<input type="hidden" id="personinfo.politicalstat" name="personinfo.politicalstat" value="<ww:property value="personinfo.politicalstat"></ww:property>" />
<input type="hidden" id="personinfo.lastedulevel" name="personinfo.lastedulevel" value="<ww:property value="personinfo.lastedulevel"></ww:property>" />
<input type="hidden" id="personinfo.sex" name="personinfo.sex" value="<ww:property value="personinfo.sex"></ww:property>" />

<input type="hidden" id="personinfo.uuid" name="personinfo.uuid" value="<ww:property value="personinfo.uuid"></ww:property>" />
<input type="hidden" id="personinfo.personid" name="personinfo.personid" value="<ww:property value="personinfo.personid"></ww:property>" />
<input type="hidden" id="personinfo.photo" name="personinfo.photo" value="<ww:property value="personinfo.photo"></ww:property>" />
<input type="hidden" id="personid" name="personid" value="<ww:property value="personinfo.personid"></ww:property>" />
<input type="hidden" id="personinfo.enname" name="personinfo.enname" value="<ww:property value="personinfo.enname"></ww:property>" />
<input type="hidden" id="personinfo.idnum" name="personinfo.idnum" value="<ww:property value="personinfo.idnum"></ww:property>" />
<input type="hidden" id="personinfo.idtype" name="personinfo.idtype" value="<ww:property value="personinfo.idtype"></ww:property>" />
<input type="hidden" id="personinfo.marry" name="personinfo.marry" value="<ww:property value="personinfo.marry"></ww:property>" />
<input type="hidden" id="personinfo.officefax" name="personinfo.officefax" value="<ww:property value="personinfo.officefax"></ww:property>" />
<input type="hidden" id="personinfo.email2" name="personinfo.email2" value="<ww:property value="personinfo.email2"></ww:property>" />
<input type="hidden" id="personinfo.lastdegree" name="personinfo.lastdegree" value="<ww:property value="personinfo.lastdegree"></ww:property>" />
<input type="hidden" id="personinfo.school" name="personinfo.school" value="<ww:property value="personinfo.school"></ww:property>" />
<input type="hidden" id="personinfo.major" name="personinfo.major" value="<ww:property value="personinfo.major"></ww:property>" />
<input type="hidden" id="personinfo.xueli" name="personinfo.xueli" value="<ww:property value="personinfo.xueli"></ww:property>" />
<input type="hidden" id="personinfo.type" name="personinfo.type" value="<ww:property value="personinfo.type"></ww:property>" />
<input type="hidden" id="personinfo.indextype" name="personinfo.indextype" value="<ww:property value="personinfo.indextype"></ww:property>" />

<input type="hidden" id="personinfo.position" name="personinfo.position" value="<ww:property value="personinfo.position"></ww:property>" />
<input type="hidden" id="personaccount.uuid" name="personaccount.uuid" value="<ww:property value="personaccount.uuid"></ww:property>" />
<input type="hidden" id="personaccount.personid" name="personaccount.personid" value="<ww:property value="personaccount.personid"></ww:property>" />
<input type="hidden" id="personaccount.flag" name="personaccount.flag" value="<ww:property value="personaccount.flag"></ww:property>" />
<input type="hidden" id="personaccount.userid" name="personaccount.userid" value="<ww:property value="personaccount.userid"></ww:property>" />
<input type="hidden" id="personaccount.password" name="personaccount.password" value="<ww:property value="personaccount.password"></ww:property>" />
<input type="hidden" id="personaccount.accountstat" name="personaccount.accountstat" value="<ww:property value="personaccount.accountstat"></ww:property>" />
<input type="hidden" id="personaccount.loginfailnum" name="personaccount.loginfailnum" value="<ww:property value="personaccount.loginfailnum"></ww:property>" />
<input type="hidden" id="personaccount.lastloginip" name="personaccount.lastloginip" value="<ww:property value="personaccount.lastloginip"></ww:property>" />
<input type="hidden" id="personaccount.lastlogindate" name="personaccount.lastlogindate" value="<ww:property value="personaccount.lastlogindate"></ww:property>" />
<input type="hidden" id="personaccount.passquestion" name="personaccount.passquestion" value="<ww:property value="personaccount.passquestion"></ww:property>" />
<input type="hidden" id="personaccount.passanswer" name="personaccount.passanswer" value="<ww:property value="personaccount.passanswer"></ww:property>" />
<input type="hidden" id="personaccount.accountttl" name="personaccount.accountttl" value="<ww:property value="personaccount.accountttl"></ww:property>" />
<input type="hidden" id="personaccount.createtime" name="personaccount.createtime" value="<ww:property value="personaccount.createtime"></ww:property>" />
<br>

<table width="600" align="center" border="0" cellpadding="0" cellspacing="0">
  <!--DWLayoutTable-->
   <tr><td colspan="4" class="wyxx_title">单位信息维护</td></tr>
  <tr><td colspan="4"></td><td align="right"><input type="button" value="修改" onclick="ajaxrequest()"/></td></tr>
  <tr>
    <td width="150" rowspan="2" valign="top" class="wyxx_left_top">单位名称</td>
    <td width="150" rowspan="2" valign="top" class="wyxx_left_top"><input type="text" id="personinfo.cnname" name="personinfo.cnname" size="15" value="<ww:property value="personinfo.cnname"></ww:property>" /><!--DWLayoutEmptyCell-->&nbsp;</td>
    <td width="150" height="33" valign="top" class="wyxx_left_top">办公电话</td>
    <td width="150" valign="top" class="wyxx_left_top_right_cccenter"><input type="text" id="personinfo.officetel" name="personinfo.officetel" size="15" value="<ww:property value="personinfo.officetel"></ww:property>" /><!--DWLayoutEmptyCell-->&nbsp;</td>
  </tr>
  <tr>
    <td height="33" valign="top" class="wyxx_left_top">传真电话</td>
    <td valign="top" class="wyxx_left_top_right_cccenter"><input type="text" id="personinfo.mobile" name="personinfo.mobile" size="15" value="<ww:property value="personinfo.mobile"></ww:property>" /><!--DWLayoutEmptyCell-->&nbsp;</td>
  </tr>
  
  <tr>
    <td height="33" valign="top" class="wyxx_left_top">通讯地址</td>
    <td colspan="3" valign="top" class="wyxx_left_top_right">&nbsp;&nbsp;&nbsp;<input type="text" id="personinfo.connectaddr" name="personinfo.connectaddr" size="50" value="<ww:property value="personinfo.connectaddr"></ww:property>" /><!--DWLayoutEmptyCell-->&nbsp;</td>
  </tr>
  <tr>
    <td height="33" valign="top" class="wyxx_left_top">人员</td>
    <td valign="top" class="wyxx_left_top">姓名</td>
    <td valign="top" class="wyxx_left_top">职务</td>
    <td valign="top" class="wyxx_left_top_right_cccenter">联系电话</td>
  </tr>
  <tr>
    <td height="33" valign="top" class="wyxx_left_top">主管领导</td>
    <td valign="top" class="wyxx_left_top"><input type="text" id="personinfo.officeadd" name="personinfo.officeadd" size="15" value="<ww:property value="personinfo.officeadd"></ww:property>" /><!--DWLayoutEmptyCell-->&nbsp;</td>
    <td valign="top" class="wyxx_left_top"><input type="text" id="personinfo.job" name="personinfo.job" size="15" value="<ww:property value="personinfo.job"></ww:property>" /><!--DWLayoutEmptyCell-->&nbsp;</td>
    <td valign="top" class="wyxx_left_top_right_cccenter"><input type="text" id="personinfo.hometel" name="personinfo.hometel" size="15" value="<ww:property value="personinfo.hometel"></ww:property>" /><!--DWLayoutEmptyCell-->&nbsp;</td>
  </tr>
  <tr>
    <td height="33" valign="top" class="wyxx_left_top">办公室主任</td>
    <td valign="top" class="wyxx_left_top"><input type="text" id="personinfo.homeadd" name="personinfo.homeadd" size="15" value="<ww:property value="personinfo.homeadd"></ww:property>" /><!--DWLayoutEmptyCell-->&nbsp;</td>
    <td valign="top" class="wyxx_left_top"><input type="text" id="personinfo.duty" name="personinfo.duty" size="15" value="<ww:property value="personinfo.duty"></ww:property>" /><!--DWLayoutEmptyCell-->&nbsp;</td>
    <td valign="top" class="wyxx_left_top_right_cccenter"><input  type="text" id="personinfo.dome" name="personinfo.dome" size="15" value="<ww:property value="personinfo.dome"></ww:property>" /><!--DWLayoutEmptyCell-->&nbsp;</td>
  </tr>
  <tr>
    <td height="33" valign="top" class="wyxx_left_top_bottom">提案办理人员</td>
    <td valign="top" class="wyxx_left_top_bottom"><input type="text" id="personinfo.city" name="personinfo.city" size="15" value="<ww:property value="personinfo.city"></ww:property>" /><!--DWLayoutEmptyCell-->&nbsp;</td>
    <td valign="top" class="wyxx_left_top_bottom"><input type="text" id="personinfo.nation" name="personinfo.nation" size="15" value="<ww:property value="personinfo.nation"></ww:property>" /><!--DWLayoutEmptyCell-->&nbsp;</td>
    <td valign="top" class="wyxx_all"><input type="text" id="personinfo.househao" name="personinfo.househao" size="15" value="<ww:property value="personinfo.househao"></ww:property>" /><!--DWLayoutEmptyCell-->&nbsp;</td>
  </tr>
  <tr>
    <td height="33" valign="top"><!--DWLayoutEmptyCell-->&nbsp;</td>
    <td valign="top"><!--DWLayoutEmptyCell-->&nbsp;</td>
    <td valign="top"><!--DWLayoutEmptyCell-->&nbsp;</td>
    <td valign="top"><!--DWLayoutEmptyCell-->&nbsp;</td>
  </tr>
</table>
</form>

</body>
</html>