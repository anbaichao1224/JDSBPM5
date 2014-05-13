<%@ page contentType="text/html; charset=UTF-8"%>
<jsp:directive.page import="com.opensymphony.xwork.ActionContext"/>
<jsp:directive.page import="net.itjds.bpm.esb.ESBDateInit"/> 
<jsp:directive.page import="net.itjds.usm2.db.UsmService"/>
<jsp:directive.page import="net.itjds.usm2.UsmProxy"/>
<jsp:directive.page import="java.util.Map"/>
<jsp:directive.page import="java.util.HashMap"/>

<%
	String uuid = request.getParameter("uuid");
	String tableName = request.getParameter("tableName");
	UsmService usmService=(UsmService) ESBDateInit.getContextRootById("$"+tableName+"UsmService");
	UsmProxy usmProxy=usmService.getUsmProxyByKey(uuid);
	Map map=new HashMap();
	map.put("$curr"+tableName+"PopForm",usmProxy.getDAO());
	ActionContext.getContext().getValueStack().getRoot().push(map);
 %>
 <%@include file="/common/taglibs.jsp"%>
    <head>
        <title>考察路线表单</title>
        <meta content="text/html; charset=utf-8" http-equiv="Content-Type" />
        <link rel="stylesheet" type="text/css" href="/bsiirr/form/css/page.css" /><script type="text/javascript"> </script>
    </head>
    <body style="overflow-x: hidden; overflow: scroll">
        <table border="1" cellspacing="0" bordercolorlight="#c4d4eb" bordercolordark="#ffffff" cellpadding="0" width="980" align="center">
            <form id="form" method="post" action="" name="form">
                <tbody>
                    <tr class="trTitle">
                        <td class="tdClass" colspan="4">考察路线表单</td>
                    </tr>
                    <tr>
                        <td class="form_tl" width="15%">编号</td>
                        <td class="form_tv" width="35%" align="center"><input 	 id="bh" 	 class="text_s" 	 basearr="{basearr:{cnname:'编号',isInstProcess:'',readonly:true,disabled:'',defaultText:'',size:'',name:'$currKclxPopForm.bh',theoremexpression:'#bh=$currKclxPopForm.bh,(#bh==null||#bh==\'\') ? $kclxservice.bh:#bh'}}" 	 pid="component_input_0" 	 style="width: 80%" 	 name="$currKclxPopForm.bh" 	 type="text" 	 datatype="{TextType:{allowBlank:false,blankText:'编号不能为空',vtype:'',regexText:'',maxLength:'1000',maxLengthText:'',minLength:'0',minLengthText:''}}" 	 advance="undefined" 	 theoremexpression="#bh=$currKclxPopForm.bh,(#bh==null||#bh=='') ? $kclxservice.bh:#bh" 	 cnname="编号" 	 readonly="true"  custype="TextType"   value="<ww:property value="#bh=$currKclxPopForm.bh,(#bh==null||#bh=='') ? $kclxservice.bh:#bh"/>"  /></td>
                        <td class="form_tl" width="15%">考察期限</td>
                        <td class="form_tv" width="35%" align="center"><input 	 id="kcqx" 	 class="text_s" 	 basearr="{basearr:{cnname:'考察期限',isInstProcess:'',readonly:'',disabled:'',defaultText:'',size:'',name:'$currKclxPopForm.kcqx',theoremexpression:'$currKclxPopForm.kcqx'}}" 	 pid="component_input_1" 	 style="width: 80%" 	 name="$currKclxPopForm.kcqx" 	 type="text" 	 datatype="{NumberType:{allowBlank:'',blankText:'',scale:'2',align:'right',positiveNumberColor:'#000000',negtiveNumberColor:'#FF0000'}}" 	 theoremexpression="$currKclxPopForm.kcqx" 	 cnname="考察期限"  custype="NumberType"   value="<ww:property value="$currKclxPopForm.kcqx"/>"  /></td>
                    </tr>
                    <tr>
                        <td class="form_tl" width="15%">所在市州地</td>
                        <td class="form_tv" width="35%" align="center"><input 	 id="szd" 	 class="text_s" 	 basearr="{basearr:{cnname:'所在市州地',isInstProcess:'',readonly:'',disabled:'',defaultText:'',size:'',name:'$currKclxPopForm.szd',theoremexpression:'$currKclxPopForm.szd'}}" 	 pid="component_input_2" 	 style="width: 80%" 	 name="$currKclxPopForm.szd" 	 type="text" 	 theoremexpression="$currKclxPopForm.szd" 	 cnname="所在市州地"  custype="TextType"   value="<ww:property value="$currKclxPopForm.szd"/>"  /></td>
                        <td class="form_tl" width="15%">登记人</td>
                        <td class="form_tv" width="35%" align="center"><input 	 id="djr" 	 class="text_s" 	 basearr="{basearr:{cnname:'登记人',isInstProcess:'',readonly:'',disabled:'',defaultText:'',size:'',name:'$currKclxPopForm.djr',theoremexpression:'$currKclxPopForm.djr'}}" 	 pid="component_input_3" 	 style="width: 80%" 	 name="$currKclxPopForm.djr" 	 type="text" 	 theoremexpression="$currKclxPopForm.djr" 	 cnname="登记人"  custype="TextType"   value="<ww:property value="$currKclxPopForm.djr"/>"  /></td>
                    </tr>
                    <tr>
                        <td class="form_tl" width="15%">名称</td>
                        <td class="form_tv" colspan="3" align="center"><input 	 id="mc" 	 class="text_s" 	 basearr="{basearr:{cnname:'名称',isInstProcess:'',readonly:'',disabled:'',defaultText:'',size:'',name:'$currKclxPopForm.mc',theoremexpression:'$currKclxPopForm.mc'}}" 	 pid="component_input_4" 	 style="width: 92%" 	 name="$currKclxPopForm.mc" 	 type="text" 	 datatype="{TextType:{allowBlank:false,blankText:'',vtype:'',regexText:'',maxLength:'1000',maxLengthText:'',minLength:'0',minLengthText:''}}" 	 theoremexpression="$currKclxPopForm.mc" 	 cnname="名称"  custype="TextType"   value="<ww:property value="$currKclxPopForm.mc"/>"  /></td>
                    </tr>
                    <tr>
                        <td class="form_tl" width="15%">采集单位</td>
                        <td class="form_tv" width="35%" align="center"><input 	 id="cjdw" 	 class="text_s" 	 basearr="{basearr:{cnname:'采集单位',isInstProcess:'',readonly:'',disabled:'',defaultText:'',size:'',name:'$currKclxPopForm.cjdw',theoremexpression:'$currKclxPopForm.cjdw'}}" 	 pid="component_input_5" 	 style="width: 80%" 	 name="$currKclxPopForm.cjdw" 	 type="text" 	 theoremexpression="$currKclxPopForm.cjdw" 	 cnname="采集单位"  custype="TextType"   value="<ww:property value="$currKclxPopForm.cjdw"/>"  /></td>
                        <td class="form_tl" width="15%">采集人</td>
                        <td class="form_tv" width="35%" align="center"><input 	 id="cjr" 	 class="text_s" 	 basearr="{basearr:{cnname:'采集人',isInstProcess:'',readonly:'',disabled:'',defaultText:'',size:'',name:'$currKclxPopForm.cjr',theoremexpression:'$currKclxPopForm.cjr'}}" 	 pid="component_input_6" 	 style="width: 80%" 	 name="$currKclxPopForm.cjr" 	 type="text" 	 theoremexpression="$currKclxPopForm.cjr" 	 cnname="采集人"  custype="TextType"   value="<ww:property value="$currKclxPopForm.cjr"/>"  /></td>
                    </tr>
                    <tr>
                        <td class="form_tl" width="15%">考察典型</td>
                        <td class="form_tv" width="35%" align="center"><input type="hidden" name="hidden$currKclxPopForm.kcdx"	 basearr="{basearr:{cnname:'考察典型',isInstProcess:'',readonly:'',size:'',name:'$currKclxPopForm.kcdx',theoremexpression:'#{\'考察典型企业\':\'考察典型企业\',\'考察旅游资源\':\'考察旅游资源\'}'}}" 	 pid="component_select_0" 		 theoremexpression="#{'考察典型企业':'考察典型企业','考察旅游资源':'考察旅游资源'}" 	 cnname="考察典型"  custype= "select"  /><ww:select 	name="$currKclxPopForm.kcdx" list="#{'考察典型企业':'考察典型企业','考察旅游资源':'考察旅游资源'}"  	listKey="key"	listValue="value"/></td>
                        <td class="form_tl" width="15%">旅游景点</td>
                        <td class="form_tv" width="35%" align="center"><input type="hidden" name="hidden$currKclxPopForm.lyjd"		 pid="component_select_1" 	 basearr="{basearr:{cnname:'旅游景点',isInstProcess:'',readonly:'',size:'27',name:'$currKclxPopForm.lyjd',theoremexpression:'$kclxservice.lyjd'}}" 	 advance="#{}" 	 theoremexpression="$kclxservice.lyjd" 	 cnname="旅游景点" 	 size="27"  custype= "select"  /></td>
                    </tr>
                    <tr>
                        <td class="form_tl" width="15%">住宿宾馆</td>
                        <td class="form_tv" width="35%" align="center"><input 	 id="zsbg" 	 class="text_s" 	 basearr="{basearr:{cnname:'住宿宾馆',isInstProcess:'',readonly:'',disabled:'',defaultText:'',size:'',name:'$currKclxPopForm.zsbg',theoremexpression:'$currKclxPopForm.zsbg'}}" 	 pid="component_input_7" 	 style="width: 80%" 	 name="$currKclxPopForm.zsbg" 	 type="text" 	 theoremexpression="$currKclxPopForm.zsbg" 	 cnname="住宿宾馆"  custype="TextType"   value="<ww:property value="$currKclxPopForm.zsbg"/>"  /></td>
                        <td class="form_tl" width="15%">就餐点</td>
                        <td class="form_tv" width="35%" align="center"><input 	 id="jcd" 	 class="text_s" 	 basearr="{basearr:{cnname:'就餐点',isInstProcess:'',readonly:'',disabled:'',defaultText:'',size:'',name:'$currKclxPopForm.jcd',theoremexpression:'$currKclxPopForm.jcd'}}" 	 pid="component_input_8" 	 style="width: 80%" 	 name="$currKclxPopForm.jcd" 	 type="text" 	 theoremexpression="$currKclxPopForm.jcd" 	 cnname="就餐点"  custype="TextType"   value="<ww:property value="$currKclxPopForm.jcd"/>"  /></td>
                    </tr>
                    <tr>
                        <td class="form_tl" width="15%">活动内容</td>
                        <td class="form_tv" colspan="3" align="center"><textarea	 id="content" 	 rows="5" 	 basearr="{basearr:{cnname:'活动内容',isInstProcess:'',readonly:'',disabled:'',defaultText:'',size:'',name:'$currKclxPopForm.content',theoremexpression:'$currKclxPopForm.content'}}" 	 pid="component_textarea_0" 	 datatype="{HtmlEditType:{vType:'',allowBlank:'',minLength:'0',maxLength:'1000'}}" 	 style="width: 92%" 	 name="$currKclxPopForm.content" 	 theoremexpression="$currKclxPopForm.content" 	 cnname="活动内容"  custype="HtmlEditType"  ><ww:property value="$currKclxPopForm.content" escape="false"/></textarea></td>
                    </tr>
                    <tr>
                        <td class="form_tl" width="15%">导入日期</td>
                        <td class="form_tv" width="35%" align="center"><input 	 id="drrq" 	 class="tdate_s" 	 basearr="{basearr:{cnname:'导入日期',isInstProcess:'',readonly:'',disabled:'',defaultText:'',size:'',name:'$currKclxPopForm.drrq',theoremexpression:'$currKclxPopForm.drrq'}}" 	 pid="component_input_9" 	 datatype="{DateType:{allowBlank:'',blankText:'',maxValue:'',maxLengthText:'',minValue:'',minLengthText:'',format:'Y-m-d'}}" 	 style="width: 80%" 	 name="$currKclxPopForm.drrq" 	 type="text" 	 theoremexpression="$currKclxPopForm.drrq" 	 cnname="导入日期"  custype="DateType"   value="<ww:date  format="yyyy-MM-dd" name="$currKclxPopForm.drrq"/>"  /></td>
                        <td class="form_tl" width="15%">审核人</td>
                        <td class="form_tv" width="35%" align="center"><input 	 id="shr" 	 class="text_s" 	 basearr="{basearr:{cnname:'审核人',isInstProcess:'',readonly:'',disabled:'',defaultText:'',size:'',name:'$currKclxPopForm.shr',theoremexpression:'$currKclxPopForm.shr'}}" 	 pid="component_input_10" 	 style="width: 80%" 	 name="$currKclxPopForm.shr" 	 type="text" 	 theoremexpression="$currKclxPopForm.shr" 	 cnname="审核人"  custype="TextType"   value="<ww:property value="$currKclxPopForm.shr"/>"  /></td>
                    </tr>
                    <tr>
                        <td class="form_tl" width="15%">附件</td>
                        <td class="form_tv" colspan="3" align="center">
                          <ww:action  name="fileinclude" executeResult="true"/>
                        </td>
                    </tr>
                </tbody>
            </form>
        </table>
    </body>
</html>
