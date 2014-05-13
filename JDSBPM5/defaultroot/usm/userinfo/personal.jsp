<%@ page language="java"  pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="ww"%>
<html >
<head>
<title>委员基本信息</title>
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
	text-align: left;
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
<input type="hidden" id="personinfo.uuid" name="personinfo.uuid" value="<ww:property value="personinfo.uuid"></ww:property>" />
<input type="hidden" id="personinfo.personid" name="personinfo.personid" value="<ww:property value="personinfo.personid"></ww:property>" />
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
<input type="hidden" id="personinfo.househao" name="personinfo.househao" value="<ww:property value="personinfo.househao"></ww:property>" />
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
<table align="center" width="750" border="0" cellpadding="0" cellspacing="0">
  <!--DWLayoutTable-->
  <tr><td colspan="5" class="wyxx_title">个人信息维护</td></tr>
  <tr><td colspan="4"></td><td align="right"><input type="button" value="修改" onclick="ajaxrequest()"/></td></tr>
  <tr>   
    <td width="112" rowspan="4" valign="top" class="wyxx_left_top"><img id="personinfo.photo" name="personinfo.photo" src="<ww:property value="personinfo.photo"></ww:property>"   /></td>
    <td width="133" height="35" valign="top" class="wyxx_left_top">姓&nbsp;&nbsp;&nbsp;&nbsp;名</td>
    <td width="176" valign="top" class="wyxx_left_top_ccleft"><input type="text" id="personinfo.cnname" name="personinfo.cnname" size="15" value="<ww:property value="personinfo.cnname"></ww:property>" /></td>
    <td width="130" valign="top" class="wyxx_left_top">性&nbsp;&nbsp;&nbsp;&nbsp;别</td>
    <td width="199" valign="top" class="wyxx_left_top_right">
    <select id="personinfo.sex" name="personinfo.sex" >
    	<option value="保密" <ww:if test="personinfo.sex == 0"> selected="selected" </ww:if> >保密</option>
    	<option value="男" <ww:if test="personinfo.sex == 1"> selected="selected" </ww:if> >男</option>
		<option value="女" <ww:if test="personinfo.sex == 2"> selected="selected" </ww:if> >女</option>
    </select>
    </td>
  </tr> 
  <tr>
    <td height="35" valign="top" class="wyxx_left_top">出生年月</td>
    <td valign="top" class="wyxx_left_top_ccleft"><input type="text" id="personinfo.birthday" name="personinfo.birthday" size="15" value="<ww:property value="personinfo.birthday"></ww:property>" /></td>
    <td valign="top" class="wyxx_left_top">民&nbsp;&nbsp;&nbsp;&nbsp;族</td>
    <td valign="top" class="wyxx_left_top_right"><input type="text" id="personinfo.nation" name="personinfo.nation" size="25" value="<ww:property value="personinfo.nation"></ww:property>" /></td>
    </tr>
  <tr>
    <td height="35" valign="top" class="wyxx_left_top">文化程度</td>
    <td valign="top" class="wyxx_left_top_ccleft">
	<select id="personinfo.lastedulevel" name="personinfo.lastedulevel" >
    	<option value="无" <ww:if test="personinfo.lastedulevel == 0"> selected="selected" </ww:if> >无</option>
    	<option value="小学" <ww:if test="personinfo.lastedulevel == 1"> selected="selected" </ww:if> >小学</option>
		<option value="初中" <ww:if test="personinfo.lastedulevel == 2"> selected="selected" </ww:if> >初中</option>
		<option value="高中(中专、技校)" <ww:if test="personinfo.lastedulevel == 3"> selected="selected" </ww:if> >高中(职业高中)</option>
		<option value="大专" <ww:if test="personinfo.lastedulevel == 4"> selected="selected" </ww:if> >大专</option>
		<option value="本科" <ww:if test="personinfo.lastedulevel == 5"> selected="selected" </ww:if> >本科</option>
		<option value="研究生" <ww:if test="personinfo.lastedulevel == 6"> selected="selected" </ww:if> >研究生</option>
		<option value="博士" <ww:if test="personinfo.lastedulevel == 7"> selected="selected" </ww:if> >博士</option>
    </select>
	</td>
    <td valign="top" class="wyxx_left_top">政治面目</td>
    <td valign="top" class="wyxx_left_top_right">
    <select id="personinfo.politicalstat" name="personinfo.politicalstat" >
    	<option value="群众" <ww:if test="personinfo.politicalstat == 0"> selected="selected" </ww:if> >群众</option>
    	<option value="团员" <ww:if test="personinfo.politicalstat == 1"> selected="selected" </ww:if> >团员</option>
		<option value="党员" <ww:if test="personinfo.politicalstat == 2"> selected="selected" </ww:if> >党员</option>
		<option value="其它" <ww:if test="personinfo.politicalstat == 3"> selected="selected" </ww:if> >其它</option>
    </select>
    </td>
    </tr>
  <tr>
    <td height="35" colspan="2" valign="top" class="wyxx_left_top">党派、团体职务</td>
    <td colspan="2" valign="top" class="wyxx_left_top_right"><input type="text" id="personinfo.duty" name="personinfo.duty" size="45" value="<ww:property value="personinfo.duty"></ww:property>" /></td>
    </tr>
  <tr>
    <td height="35" valign="top" class="wyxx_left_top">工作单位</td>
    <td colspan="2" valign="top" class="wyxx_left_top_ccleft"><input type="text" id="personinfo.officeadd" name="personinfo.officeadd" size="40" value="<ww:property value="personinfo.officeadd"></ww:property>" /></td>
    <td valign="top" class="wyxx_left_top">职务名称</td>
    <td valign="top" class="wyxx_left_top_right"><input type="text" id="personinfo.job" name="personinfo.job" size="25" value="<ww:property value="personinfo.job"></ww:property>" /></td>
  </tr>
  <tr>
    <td height="35" valign="top" class="wyxx_left_top">单位地址</td>
    <td colspan="2" valign="top" class="wyxx_left_top_ccleft"><input type="text" id="personinfo.connectaddr" name="personinfo.connectaddr" size="40" value="<ww:property value="personinfo.connectaddr"></ww:property>" /></td>
    <td valign="top" class="wyxx_left_top">邮&nbsp;&nbsp;&nbsp;&nbsp;编</td>
    <td valign="top" class="wyxx_left_top_right"><input type="text" id="personinfo.nativeplace" name="personinfo.nativeplace" size="25" value="<ww:property value="personinfo.nativeplace"></ww:property>" /></td>
  </tr>
  <tr>
    <td height="35" valign="top" class="wyxx_left_top">单位电话</td>
    <td colspan="2" valign="top" class="wyxx_left_top_ccleft"><input type="text" id="personinfo.officetel" name="personinfo.officetel" size="40" value="<ww:property value="personinfo.officetel"></ww:property>" /></td>
    <td valign="top" class="wyxx_left_top">手机号码</td>
    <td valign="top" class="wyxx_left_top_right"><input type="text" id="personinfo.mobile" name="personinfo.mobile" size="25" value="<ww:property value="personinfo.mobile"></ww:property>" /></td>
  </tr>
  <tr>
    <td height="35" valign="top" class="wyxx_left_top">家庭住址</td>
    <td colspan="4" valign="top" class="wyxx_left_top_right"><input type="text" id="personinfo.homeadd" name="personinfo.homeadd" size="90" value="<ww:property value="personinfo.homeadd"></ww:property>" /></td>
  </tr>
  <tr>
    <td height="35" valign="top" class="wyxx_left_top">住址邮编</td>
    <td colspan="2" valign="top" class="wyxx_left_top_ccleft"><input type="text" id="personinfo.zip" name="personinfo.zip" size="40" value="<ww:property value="personinfo.zip"></ww:property>" /></td>
    <td valign="top" class="wyxx_left_top">住宅电话</td>
    <td valign="top" class="wyxx_left_top_right"><input type="text" id="personinfo.hometel" name="personinfo.hometel" size="25" value="<ww:property value="personinfo.hometel"></ww:property>" /></td>
  </tr>
  <tr>
    <td height="35" valign="top" class="wyxx_left_top">通信地址</td>
    <td colspan="2" valign="top" class="wyxx_left_top_ccleft"><input type="text" id="personinfo.city" name="personinfo.city" size="40" value="<ww:property value="personinfo.city"></ww:property>" /></td>
    <td valign="top" class="wyxx_left_top">邮&nbsp;&nbsp;&nbsp;&nbsp;编</td>
    <td valign="top" class="wyxx_left_top_right"><input type="text" id="personinfo.homefax" name="personinfo.homefax" size="25" value="<ww:property value="personinfo.homefax"></ww:property>" /></td>
  </tr>
  <tr>
    <td height="35" valign="top" class="wyxx_left_top">邮&nbsp;&nbsp;&nbsp;&nbsp;箱</td>
    <td colspan="4" valign="top" class="wyxx_left_top_right"><input type="text" id="personinfo.email1" name="personinfo.email1" size="90" value="<ww:property value="personinfo.email1"></ww:property>" /></td>
  </tr>
  <tr>
    <td height="85" colspan="5" valign="top" class="wyxx_all">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;备注:
      <br />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<textarea name="bz" cols="80" rows="2" id="personinfo.dome" name="personinfo.dome" ><ww:property value="personinfo.dome"></ww:property></textarea></td>
  </tr>
</table>
</form>

</body>
</html>