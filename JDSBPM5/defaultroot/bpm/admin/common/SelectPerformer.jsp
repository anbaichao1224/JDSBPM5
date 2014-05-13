<%@ page contentType="text/html; charset=GB2312" %>
<%@ page import="java.util.*"%>
<%@ page import="net.itjds.bpm.client.*"%>
<%@ page import="net.itjds.common.org.base.*"%>
<%@ page import="net.itjds.common.org.util.*"%>
<%@ page import="net.itjds.bpm.engine.WorkflowClientService"%>
<%@ page import="net.itjds.bpm.engine.OARightConstants"%>
<%@ page import="net.itjds.oa.OAUtil"%>
<%@ page import="net.itjds.oa.Surrogate"%>

<!-- Header begin --> 
<%@ include file="/bpm/oa/include/jsp/OA_Header_Empty.jsp" %>
<!-- Header end --> 
 
<%
    response.setHeader("Pragma", "No-cache");
	response.setHeader("Cache-Control", "no-cache");
	response.setDateHeader("Expires", 0);
    
	try
	{
	    Map allPerformers = (Map) request.getAttribute("allPerformers");
	    Map allReaders = (Map) request.getAttribute("allReaders");
		List routeDefs = (List) request.getAttribute("routeDefs");
		int routeDefCount = routeDefs.size();
		String[] performTypes = new String[routeDefCount];
		ActivityInst _activityInst = (ActivityInst) request.getAttribute("activityInst");
		ActivityDef _fromActivityDef = _activityInst.getActivityDef();
		String split = null;
		if (_fromActivityDef != null) {
			split = _fromActivityDef.getSplit();
		}
		if (split == null) {
			split = ActivityDef.SPLIT_XOR;
		}
		WorkflowClientService _client = OAUtil.getClient(request);
%>

<style>
.table_line_1 {
 border-top-width: 1px;
 border-top-color: #000000; 

 border-bottom-style: dashed;
 border-bottom-width: 1px;
 border-bottom-color: #000000;
 
 border-left-width: 1px;
 border-left-color: #000000;
 
 border-right-style: dashed;
 border-right-width: 1px;
 border-right-color: #000000;
 
}

.table_line_2 {
 border-top-style: dashed;
 border-top-width: 1px;
 border-top-color: #000000;

 border-bottom-width: 1px;
 border-bottom-color: #0362af;
 
 border-left-width: 0px;
 border-left-color: #0362af;
 
 border-right-width: 1px;
 border-right-color: #0362af;
 
}

div 
{
    scrollbar-arrow-color:#444f59; 
    scrollbar-base-color:#FFFBE1; 
    scrollbar-shadow-color:#FFFBE1;
    scrollbar-3dlight-color:#808080;
    scrollbar-highlight-color:#F1EEE3;
    SCROLLBAR-DARKSHADOW-COLOR:#808080;
　} 
</style>

<SCRIPT LANGUAGE="JavaScript">
<!--
	//全局变量
	sp_selected = "sp_performer";
	PERFORMTYPE_SINGLE = "<%=OARightConstants.PERFORMTYPE_SINGLE%>";
	PERFORMTYPE_MULTIPLE = "<%=OARightConstants.PERFORMTYPE_MULTIPLE%>";
	PERFORMTYPE_NOSELECT = "<%=OARightConstants.PERFORMTYPE_NOSELECT%>";
	PERFORMTYPE_JOINTSIGN = "<%=OARightConstants.PERFORMTYPE_JOINTSIGN%>";
	performTypeArray = new Array();
	//路由标识（现在存的是序号）数组
	routeIdArray = new Array();
	//相应的活动定义的ID
	activityDefIdArray = new Array();
	//当前的办理方式
	currentPerformType = "";
	//当前选择的部门名称和ID
	currentOrgName = "";
	currentOrgId = "";
	currentParentOrgName = "";

//选择组织后的动作
function selectorg(o, id)
{
	//如果当前选择的路由是会签的情况，直接返回
	if (currentPerformType == PERFORMTYPE_JOINTSIGN) {
		if(id != "<%=OrgHTMLTree.ALL_PERSON%>"){
		  currentOrgName = o.innerText;
		  try {
			var po = o.parentNode.parentNode.parentNode.parentNode.parentNode.parentNode.parentNode.parentNode.previousSibling.firstChild.firstChild.firstChild.nextSibling.firstChild;
			currentParentOrgName = po.innerText;
			if (currentParentOrgName == "undefined" || currentParentOrgName == null) {
				currentParentOrgName = "";
			}
		  } catch(e) {
			  currentParentOrgName = "";
		  }
		  currentOrgId = id;
		} else {
		  currentOrgName = "";
		  currentParentOrgName = "";
		  currentOrgId = "";
		}
		return;
	}

	var frmIn=null;
	for(var i=0; i<document.forms.length; i++) {
		var frm = document.forms(i);
		for(var j = 0; j < frm.all.length; j++){
			if (frm.all(j) == o) {
			   frmIn = frm;
			   break;
			}
		}
		if (frmIn != null) {
			break;
		}
	}
	
    var list = frmIn.personlist;
	var personArray = eval(frmIn.name + "Array");

	list.options.length=0;
	if (id == "<%=OrgHTMLTree.ALL_PERSON%>") {
		for (var i=0; i<personArray.length; i++) {
			list.options[i] = new Option(personArray[i][1], personArray[i][0]);
		}
		return;
	}

	var array, k=0;
	for (var i=0; i<personArray.length; i++) {
		array = personArray[i][2];
		for(var j=0; j<array.length; j++) {
			if(id == array[j]) {
				list.options[k++] = new Option(personArray[i][1], personArray[i][0]);
				break;
			}
		}
	}
}

//数据初始化
function init()
{
<% 

Surrogate surrogate = OAUtil.getSurrogate();
for(int ii=0; ii<routeDefCount;ii++){
	RouteDef routeDef = (RouteDef)routeDefs.get(ii);
	if (routeDef == null) {
		continue;
	}
	
	String performType = (String)_client.getActivityDefRightAttribute(routeDef.getToActivityDefId(),
		 OARightConstants.ACTIVITYDEF_RIGHT_ATT_PERFORMTYPE, null);
	if (performType == null) {
		performType = OARightConstants.PERFORMTYPE_SINGLE;
	}
	performTypes[ii] = performType;
%>
		/*routeIdArray begin*/
	routeIdArray[<%=ii%>] = "<%=ii%>";
	activityDefIdArray[<%=ii%>] = "<%=routeDef.getToActivityDefId()%>";
	/*routeIdArray end*/

<%

	if (performType.equals(OARightConstants.PERFORMTYPE_JOINTSIGN)) {
		//System.out.print("uuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuu");

		continue;
	}

	String routeDefOrginalId = routeDef.getRouteDefId();
	List performers = (List)allPerformers.get(routeDefOrginalId);
	List readers = (List)allReaders.get(routeDefOrginalId);

%>
	performer<%=ii%>Array = new Array();
	reader<%=ii%>Array = new Array();

/*reader begin*/
    var personlist = document.all.reader<%=ii%>.personlist;
	var i, id, name, o, orgId;
<%

    for (int i=0, n=readers.size(); i<n; i++) {
		Person person = (Person)readers.get(i);
		

%>
		reader<%=ii%>Array[<%=i%>] = new Array();
		i = <%=i%>;
		id = "<%=person.getID()%>";
		name = "<%=person.getName()%>";
		reader<%=ii%>Array[i][0] = id;
		reader<%=ii%>Array[i][1] = name;
		
		reader<%=ii%>Array[i][2] = new Array();
<%
		Org[] orgs = person.getOrgs();
		if (orgs!=null) {
			for(int j=0, m=orgs.length; j<m; j++) {
%>
				reader<%=ii%>Array[i][2][<%=j%>] = "<%=orgs[j].getID()%>";
<%
				}
			}
%>
        o = new Option(name, id);
		personlist.options[i] = o;
<%
		}
%>
/*reader end*/

/*performer begin*/
    var personlist = document.all.performer<%=ii%>.personlist;
	var i, id, name, o, orgId;
<%
	int n = performers.size();
	List surrogatedNameList = new ArrayList();
	List surrogatedIdList = new ArrayList();
    for (int i=0; i<performers.size(); i++) {
		Person person = (Person)performers.get(i); 
%> 
		performer<%=ii%>Array[<%=i%>] = new Array();
		i = <%=i%>;
		id = "<%=person.getID()%>";
<%

		if (surrogate != null && surrogate.isOut(person.getID())) { // 判断是否外出
			Person[] surrogates = surrogate.getSurrogates(person.getID());
			for (int k=0; k<surrogates.length; k++) {
				Person p = surrogates[k];
//				int index = -1;
//				if ((index = performers.indexOf(p)) != -1) {
//					performers.remove(index);
//					n--;
//				}
				surrogatedIdList.add(person.getID());
				surrogatedNameList.add("（代" + person.getName() + "）");
				performers.add(p);
			}
			//代办人
			if (i>=n) {
%>
				name = '<%=(person.getName() + "（外出）" + surrogatedNameList.get(i-n))%>'
<%
			} else {
%>
				name = '<%=(person.getName() + "（外出）")%>'
<%		
			}// eof if (i>=n)
		} else { // elseof if (surrogate.isOut(person.getID()))
			//代办人
			if (i>=n) {
%>
				id = id + ":" + "<%=surrogatedIdList.get(i-n)%>";
				name = "<%=person.getName() + surrogatedNameList.get(i-n)%>";
<%
			} else {
%>
				name = "<%=person.getName()%>";
<%
			} // eof if (i>=n)
		} // eof if (surrogate.isOut(person.getID()))
%>
		performer<%=ii%>Array[i][0] = id;
		performer<%=ii%>Array[i][1] = name;
		
		performer<%=ii%>Array[i][2] = new Array();
<%
		Org[] orgs = person.getOrgs();
		if (orgs!=null) {
			for(int j=0, m=orgs.length; j<m; j++) {
%>
				performer<%=ii%>Array[i][2][<%=j%>] = "<%=orgs[j].getID()%>";
<%
			}
		}
%>
        o = new Option(name, id);
		personlist.options[i] = o;
<%

		}

%>
/*performer end*/
<%	
}
//end of top for
%>
}

function initRoute() {
	/*route init begin*/
	if (<%=routeDefCount%> > 1) {
		eval("document.all.cb_route" + routeIdArray[0]).click();
	} else {
		window.dialogWidth = "465px";
	}
	/*route init end*/
}
    
function _add(ii, type, performType)
{
	if (performType == PERFORMTYPE_JOINTSIGN) {
		if (currentOrgName == "") {
			return;
		}
	    var selected  = eval("document.all." + type + ii + ".personselected");
	    var text = currentOrgName;
	    if (currentParentOrgName != "") {
	    	text = text + "（" + currentParentOrgName + "）";
	    }
	    var value = currentOrgId;
	    var flag = true;
	    for(var j=0; j<selected.options.length; j++)
	    {
	        if(value == selected.options[j].value)
	        {
	            alert("已经选中的组织：" + text);
	            flag = false;
	            break;
	        }
	    }
	    if(flag == true)
	    {
	        var o = new Option(text, value);
	        selected.options[selected.options.length] = o;
	    }	
	    return;
	}
	
    var list = eval("document.all." + type + ii + ".personlist");
    var selected  = eval("document.all." + type + ii + ".personselected");

	if (performType == PERFORMTYPE_SINGLE) {
		selected.options.length=0;
	}

    for(var i=0; i<list.options.length; i++)
    {
        if(list.options[i].selected == true)
        {
            var text = list.options[i].text;
            var value = list.options[i].value;
            var flag = true;
            for(var j=0; j<selected.options.length; j++)
            {
                if(value == selected.options[j].value)
                {
                    alert("已经选中的人员：" + text);
                    flag = false;
                    break;
                }
            }
            if(flag == true)
            {
                var o = new Option(text, value);
                selected.options[selected.options.length] = o;
            }
        }
    }
}

function _addall(ii, type, performType)
{
//alert("ii="+ii+"&&type="+type+"&&perfromType="+performType);
    var list = eval("document.all." + type + ii + ".personlist");
    var selected  = eval("document.all." + type + ii + ".personselected");
    for(var i=0; i<list.options.length; i++)
    {
        var text = list.options[i].text;
        var value = list.options[i].value;
        var flag = true;
        for(var j=0; j<selected.options.length; j++)
        {
            if(value == selected.options[j].value)
            {
                flag = false;
                break;
            }
        }
        if(flag == true)
        {
            var o = new Option(text, value);
            selected.options[selected.options.length] = o;
        }
    }
}

function _del(ii, type)
{
    var selected  = eval("document.all." + type + ii + ".personselected");
    for(var i=selected.options.length-1; i>=0; i--)
    {
        if(selected.options[i].selected == true)
        {
            selected.options[i] = null;
        }
    }
}

function _delall(ii, type)
{
    var selected  = eval("document.all." + type + ii + ".personselected");
    selected.options.length = 0;
}

//选择办理人
function selectPerformer(ii) {
	sp_selected = "sp_performer" + ii;
	var frmIn = eval("document.all.switcher" + ii + ".all");
	frmIn.td_performer.bgColor="#666699";
	frmIn.sp_performer.style.color="white";
	frmIn.sp_performer.style.cursor="default";

	frmIn.td_reader.bgColor = "#efefef";
	frmIn.sp_reader.style.color = "black";
	frmIn.sp_reader.style.cursor="hand";

	var o = eval("document.all.tb_performer" + ii);
	o.style.display="";
	o = eval("document.all.tb_reader" + ii);
	o.style.display="none";
}

//选择读者
function selectReader(ii) {
	sp_selected = "sp_reader" + ii;
	var frmIn = eval("document.all.switcher" + ii + ".all");
	frmIn.td_reader.bgColor="#666699";
	frmIn.sp_reader.style.color="white";
	frmIn.sp_reader.style.cursor="default";

	frmIn.td_performer.bgColor = "#efefef";
	frmIn.sp_performer.style.color = "black";
	frmIn.sp_performer.style.cursor="hand";

	var o = eval("document.all.tb_performer" + ii);
	o.style.display="none";
	o = eval("document.all.tb_reader" + ii);
	o.style.display="";
}

//选择路由
function selectRoute(ii) {
	currentPerformType = performTypeArray[ii];
	var o;
	for (var i=0; i<routeIdArray.length; i++) {
		o = eval("document.all.tb_" + routeIdArray[i]);
		if (routeIdArray[i] == ii) {
			o.style.display="";
		} else {
			o.style.display="none";
		}
	}
	
	if (typeof routeop != "undefined") {
		routeop.style.backgroundColor = routebg;
		routeop.style.border = "none";
	}
	var os = eval("document.all.route" + ii);
	routebg = os.style.backgroundColor;
	os.style.backgroundColor = "ccddee";
	os.style.border = "0px solid #666666";
	routeop = os;
}

function checkRoute(o, ii) {
	if (o.checked) {
		selectRoute(ii);
	} else {
		o.checked = false;
	}
}

function showText(o) {
	var text = o.options[o.selectedIndex].text;
	if(text.length > 8) {
		document.all.sp_showtext.innerText = text;
	} else {
		document.all.sp_showtext.innerText = "";
	}
}

function _ok()
{

	var ret = new Array();
	var routeIndex = 0;
	for (var i=0; i<routeIdArray.length; i++) {
		if (routeIdArray.length > 1) {
			var cb_o = eval("document.all.cb_route" + routeIdArray[i]);
			if (!(cb_o.checked)) {
				continue;
			}
		}
		ret[routeIndex] = new Object();
		var routeId = routeIdArray[i];
		//activityDefId
		ret[routeIndex].activityDefId = activityDefIdArray[i];
		ret[routeIndex].performType = performTypeArray[i];
		
		var readers = new Array();
		ret[routeIndex].readers = readers;
		//会签情况，忽略读者
		var readerCount = 0;
		if (performTypeArray[i] != PERFORMTYPE_JOINTSIGN) {
			//readers
			var o = eval("document.forms.reader" + routeId);
			var objSelect = o.personselected;
			readerCount = objSelect.length;
			for(var j=0; j<readerCount; j++)
			{
				readers[j] = new Object();
				readers[j].id = objSelect.options[j].value;
			}
		}
		
		//performers
		var o = eval("document.forms.performer" + routeId);
		var objSelect = o.personselected;
		if (objSelect.length == 0) {
			alert("请选择办理人或办理部门！");
			if (routeIdArray.length >1) {
				selectRoute(routeId);
			}
			if (performTypeArray[i] != PERFORMTYPE_JOINTSIGN) {
				selectPerformer(routeId);
			}
			return;
		}
		var performers = new Array();
		ret[routeIndex].performers = performers;
		for(var j=0; j<objSelect.length; j++)
		{
			performers[j] = new Object();
			var performerId = objSelect.options[j].value;
			//代办人的情况，将被代办人加入到读者。
			var index = -1;
			if ((index = performerId.indexOf(":")) != -1) {
				performers[j].id = performerId.substring(0, index);
				var readerExist = false;
				var readerId = performerId.substring(index+1, performerId.length);
				//判断读者是否已经存在
				for(var k=0; k<readerCount; k++) {
					if (readerId == readers[k].id) {
						readerExist = true;
						break;
					}
				}
				if (!readerExist) {
					readers[readerCount] = new Object();
					readers[readerCount].id = readerId;
					readerCount++;
				}
			} else { //非代办
				performers[j].id = performerId;
			}
			
		}

		routeIndex++;
	}
	if (routeIndex == 0) {
		alert("您还未选择路由，请选择！");
		return;
	}
	window.returnValue = ret;
    window.close();
}

//-->
</SCRIPT>
<SCRIPT LANGUAGE="JavaScript">
<!--
	window.returnValue = null;
	window.document.title = "人员选择";
//-->
</SCRIPT>
<table width="100%" align="center" border="0" cellspacing="0" cellpadding="0">
  <tr>
<% 
	if (routeDefCount > 1) { 
%> 
    <td width="120" align="center" valign="middle">
	  <table width="90%">
	    <tr>
		  <td>请选择流转↓</td>
	    </tr> 
	  </table> 
	  <table width="98%" height="100%" border="0" cellspacing="0" cellpadding="0">
<% 
	for(int ii=0; ii<routeDefCount;ii++){
		RouteDef routeDef = (RouteDef)routeDefs.get(ii);
		if(routeDef == null) {
			continue;
		}
		
		String routeDefName = routeDef.getName();
		if (routeDefName == null) {routeDefName = "null";}
%>
  	    <tr> 
		  <td width="<%=(routeDefName.length()*20+23)%>" align="left">
		    <table width="100%">
			  <tr>
				<td width="21"><input type='<%=(split.equals(ActivityDef.SPLIT_AND)?"checkbox":"radio")%>' id="cb_route<%=ii%>" onclick='checkRoute(this, "<%=ii%>")' name="routeCheck"></td>
			    <td class=treeview_ob_td align="left" id="route<%=ii%>"><%=routeDefName%></td>
			  </tr>
			</table>
		  </td>
	    </tr>
<%}%>
	  </table>
	</td>
<%}%>

    <td width="450" align="center" valign="middle">
<!--persons begin-->
<% for(int ii=0; ii<routeDefCount;ii++){

	RouteDef routeDef = (RouteDef)routeDefs.get(ii);
	if(routeDef == null) {
		continue;
	}
	String routeDefOrginalId = routeDef.getRouteDefId();
	List performers = (List)allPerformers.get(routeDefOrginalId);
	List readers = (List)allReaders.get(routeDefOrginalId);

	OrgHTMLTree performerTree = null;
	OrgHTMLTree readerTree = null;
	String performType = performTypes[ii];
	if (performType.equals(OARightConstants.PERFORMTYPE_JOINTSIGN)) {
		/*
		*performerTree = new OrgHTMLTree((Org[])performers.toArray(new Org[0]), false, true, false);
		*修复原来在部门会签时空指针错误
		*/
		performerTree = new OrgHTMLTree((Person[])performers.toArray(new Person[0]));
	} else {
		performerTree = new OrgHTMLTree((Person[])performers.toArray(new Person[0]));
		readerTree = new OrgHTMLTree((Person[])readers.toArray(new Person[0]));

	}
%>
<SCRIPT LANGUAGE="JavaScript">
<!--
	performTypeArray[<%=ii%>] = "<%=performType%>";
//-->
</SCRIPT>

<!--tree script here-->
<% if (ii == 0) {
		out.print(performerTree.getScript());
		
	}
	
	//会签情况
	if (performType.equals(OARightConstants.PERFORMTYPE_JOINTSIGN)) {
		
//if (performType ==(OARightConstants.PERFORMTYPE_JOINTSIGN)) {
%>

<table id="tb_<%=ii%>" width="100%" border="0" cellspacing="0" cellpadding="0" style='display:<%=(ii==0?"":"none")%>'>
<tr>
<td>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
<tr align=left>
  <td height="10"> 
	<table cellspacing=0 cellpadding=0 width="1%" border="0" cellspacing="0" cellpadding="0">
	  <tr height="20">
		<form name="switcher<%=ii%>">
		<td nowrap width="25%" bgcolor=#666699 align="center" id="td_performer">
		  <span id="sp_performer" class="A_1" style="color:white" onclick="selectPerformer('<%=ii%>')">&nbsp;选择办理人&nbsp;</span>
		</td>
		<td width=1 bgcolor=#808080><img height=1 alt="" width=1></td>
		</form>
	  </tr>
	</table>
  </td>
</tr>
</table>

<table align="center" width="450" border="0" cellpadding="0" cellspacing="0" id="tb_performer<%=ii%>">
  <tr>
    <td height="1"> </td>
  </tr>
  <tr>
<form name="performer<%=ii%>">
    <td align="center" valign="top">
      <table align="center" width="450" border="1" width="100%" cellspacing="0" cellpadding="0" bordercolordark="#FFFFFF" bordercolorlight="#0362af" bgcolor="#f9f9e6" rules="none">
        <tr>
          <td align="left" valign="top" width="145">
	       <fieldset style="width:145px;height:100%" align="center" id=listset> 
	       <legend><span color='#0C0C0C'> 选择组织机构 </span></legend>
            <div style="overflow:auto;width:140;height:200">
            <%=performerTree.getRootHtml()%>
            </div>
           </fieldset>
          </td>
          <td width="110" align="left" style="display:none">
	       <fieldset style="width:110;height:100%" align="center" id=listset> 
	       <legend><span color='#0C0C0C'> 选择人员 </span></legend>
           <div style="overflow-y:auto;width:110;height:200" align="center">
            <select id="personlist" <%=(performType.equals(OARightConstants.PERFORMTYPE_SINGLE)?"":"multiple")%> size=13 ondblclick="_add('<%=ii%>', 'performer', '<%=performType%>')" style="width:110">
            </select>
           </div>
           </fieldset>
          </td>
          <td width="50" align="left">
           <table>
            <tr>
             <td>
              <input type=button value=' >  ' class="inputbutton" onclick="_add('<%=ii%>', 'performer', '<%=performType%>')" <%=(performType.equals(OARightConstants.PERFORMTYPE_NOSELECT)?"disabled":"")%>><br>
           
			 </td>
            </tr>
            <tr>
             <td>
			 
              <input type=button value=' >> ' class="inputbutton" onclick="_addall('<%=ii%>', 'performer', '<%=performType%>')" <%=((performType.equals(OARightConstants.PERFORMTYPE_NOSELECT) || performType.equals(OARightConstants.PERFORMTYPE_SINGLE))?"disabled":"")%>><br>
            <script>
			var addall_ok=_addall('<%=ii%>', 'performer', '<%=performType%>')
			</script>
			 </td>
            </tr>
            <tr height="10">
             <td>
             </td>
            </tr>
            <tr>
             <td>
              <input type=button value=' <  ' class="inputbutton" onclick="_del('<%=ii%>', 'performer')" <%=(performType.equals(OARightConstants.PERFORMTYPE_NOSELECT)?"disabled":"")%>><br>
             </td>
            </tr>
            <tr>
             <td>
              <input type=button value=' << ' class="inputbutton" onclick="_delall('<%=ii%>', 'performer')" <%=(performType.equals(OARightConstants.PERFORMTYPE_NOSELECT)?"disabled":"")%>><br>
             </td>
            </tr>
           </table>
          </td>
          <td width="150" align="left">
	       <fieldset style="width:110;height:100%" align="center" id=listset> 
	       <legend><span color='#0C0C0C'> 已选中部门 </span></legend>
           <div style="overflow-y:auto;width:150;height:200" align="center">
            <select id="personselected" multiple size=13 style="width:150">
            </select>
           </div>
           </fieldset>
          </td>
        </tr>
		<tr>
		  <td>&nbsp;</td><td colspan="3">&nbsp;</td>
		</tr>
      </table>
	</td>
</form>
  </tr>
</table>
</td>
</tr>
</table> 

<%
	} else {
%>
<table id="tb_<%=ii%>" width="100%" border="0" cellspacing="0" cellpadding="0" style='display:<%=(ii==0?"":"none")%>'>
<tr>
<td>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
<tr align=left>
  <td height="10"> 
	<table cellspacing=0 cellpadding=0 width="1%" border="0" cellspacing="0" cellpadding="0">
	  <tr height="20">
		<form name="switcher<%=ii%>">
		<td nowrap width="25%" bgcolor=#666699 align="center" id="td_performer">
		  <span id="sp_performer" class="A_1" style="color:white" onclick="selectPerformer('<%=ii%>')">&nbsp;选择办理人&nbsp;</span>
		</td>
		<td width=1 bgcolor=#808080><img height=1 alt="" width=1></td>
		<td width=1 bgcolor=#ffffff><img height=1 alt="" width=1></td>
		<td nowrap width="25%" bgcolor=#efefef align="center" id="td_reader">
		  <span id="sp_reader" class="A_1" onclick="selectReader('<%=ii%>')" style="cursor:hand">&nbsp;选择传阅人&nbsp;</span>
		</td>
		<td width=1 bgcolor=#808080><img height=1 alt="" width=1></td>
		</form>
	  </tr>
	</table>
  </td>
</tr>
</table>

<table align="center" width="450" border="0" cellpadding="0" cellspacing="0" id="tb_performer<%=ii%>">
  <tr>
    <td height="1"> </td>
  </tr>
  <tr>
<form name="performer<%=ii%>">
    <td align="center" valign="top">
      <table align="center" width="450" border="1" width="100%" cellspacing="0" cellpadding="0" bordercolordark="#FFFFFF" bordercolorlight="#0362af" bgcolor="#f9f9e6" rules="none">
        <tr>
          <td align="left" valign="top" width="145">
	       <fieldset style="width:145px;height:100%" align="center" id=listset> 
	       <legend><span color='#0C0C0C'> 选择组织机构 </span></legend>
            <div style="overflow:auto;width:140;height:200">
            <%=performerTree.getRootHtml()%>
            </div>
           </fieldset>
          </td>
          <td width="110" align="left">
	       <fieldset style="width:110;height:100%" align="center" id=listset> 
	       <legend><span color='#0C0C0C'> 选择人员 </span></legend>
           <div style="overflow-y:auto;width:110;height:200" align="center">
            <select id="personlist" <%=(performType.equals(OARightConstants.PERFORMTYPE_SINGLE)?"":"multiple")%> size=13 ondblclick="_add('<%=ii%>', 'performer', '<%=performType%>')" style="width:110" onchange="showText(this)">
            </select>
           </div>
           </fieldset>
          </td>
          <td width="50" align="left">
           <table>
            <tr>
             <td>
              <input type=button value=' >  ' class="inputbutton" onclick="_add('<%=ii%>', 'performer', '<%=performType%>')" <%=(performType.equals(OARightConstants.PERFORMTYPE_NOSELECT)?"disabled":"")%>><br>
             </td>
            </tr>
            <tr>
             <td>
              <input type=button value=' >> ' class="inputbutton" onclick="_addall('<%=ii%>', 'performer', '<%=performType%>')" <%=((performType.equals(OARightConstants.PERFORMTYPE_NOSELECT) || performType.equals(OARightConstants.PERFORMTYPE_SINGLE))?"disabled":"")%>><br>
            <input type="hidden" value='<%=performType%>' name="perFormType" id="perFormType">
			 </td>
            </tr>
            <tr height="10">
             <td>
             </td>
            </tr>
            <tr>
             <td>
              <input type=button value=' <  ' class="inputbutton" onclick="_del('<%=ii%>', 'performer')" <%=(performType.equals(OARightConstants.PERFORMTYPE_NOSELECT)?"disabled":"")%>><br>
             </td>
            </tr>
            <tr>
             <td>
              <input type=button value=' << ' class="inputbutton" onclick="_delall('<%=ii%>', 'performer')" <%=(performType.equals(OARightConstants.PERFORMTYPE_NOSELECT)?"disabled":"")%>><br>
             </td>
            </tr>
           </table>
          </td>
          <td width="110" align="left">
	       <fieldset style="width:110;height:100%" align="center" id=listset> 
	       <legend><span color='#0C0C0C'> 已选中人员 </span></legend>
           <div style="overflow-y:auto;width:110;height:200" align="center">
            <select id="personselected" multiple size=13 style="width:110" onchange="showText(this)">
              
			</select>
           </div>
           </fieldset>
          </td>
        </tr>
      </table>
	</td>
</form>
  </tr>
</table>

<table align="center" width="450" border="0" cellpadding="0" cellspacing="0" id="tb_reader<%=ii%>" style="display:none">
  <tr>
    <td height="1"> </td>
  </tr>
  <tr>
<form name="reader<%=ii%>">
    <td align="center" valign="top">
      <table align="center" width="450" border="1" width="100%" cellspacing="0" cellpadding="0" bordercolordark="#FFFFFF" bordercolorlight="#0362af" bgcolor="#f9f9e6" rules="none">
        <tr>
          <td align="left" valign="top" width="145">
	       <fieldset style="width:145px;height:100%" align="center" id=listset> 
	       <legend><span color='#0C0C0C'> 选择组织机构 </span></legend>
            <div style="overflow:auto;width:140;height:200">
            <%=readerTree.getRootHtml()%>
            </div>
           </fieldset>
          </td>
          <td width="110" align="left">
	       <fieldset style="width:110;height:100%" align="center" id=listset> 
	       <legend><span color='#0C0C0C'> 选择人员 </span></legend>
           <div style="overflow-y:auto;width:110;height:200" align="center">
            <select id="personlist" <%=(performType.equals(OARightConstants.PERFORMTYPE_SINGLE)?"":"multiple")%> size=13 ondblclick="_add('<%=ii%>', 'reader', '<%=performType%>')" style="width:110" onchange="showText(this)">
            </select>
           </div>
           </fieldset>
          </td>
          <td width="50" align="left">
           <table>
            <tr>
             <td>
              <input type=button value=' >  ' class="inputbutton" onclick="_add('<%=ii%>', 'reader', '<%=performType%>')"><br>
             </td>
            </tr>
            <tr>
             <td>
              <input type=button value=' >> ' class="inputbutton" onclick="_addall('<%=ii%>', 'reader', '<%=performType%>')"><br>
             </td>
            </tr>
            <tr height="10">
             <td>
             </td>
            </tr>
            <tr>
             <td>
              <input type=button value=' <  ' class="inputbutton" onclick="_del('<%=ii%>', 'reader')"><br>
             </td>
            </tr>
            <tr>
             <td>
              <input type=button value=' << ' class="inputbutton" onclick="_delall('<%=ii%>', 'reader')"><br>
             </td>
            </tr>
           </table>
          </td>
          <td width="110" align="left">
	       <fieldset style="width:110;height:100%" align="center" id=listset> 
	       <legend><span color='#0C0C0C'> 已选中人员 </span></legend>
           <div style="overflow-y:auto;width:110;height:200" align="center">
            <select id="personselected" multiple size=13 style="width:110" onchange="showText(this)">
            </select>
           </div>
           </fieldset>
          </td>
        </tr>
      </table>
	</td>
	</form>
  </tr>
</table>
</td>
</tr>
</table> 
<%
	} //else of if (performType.equals(OARightConstants.PERFORMTYPE_JOINTSIGN)) 
}//end of for
%>
<!--persons end-->
<table align="center" width="450" border="0" cellpadding="0" cellspacing="0">
  <tr height="20">
    <td colspan="4">&nbsp;&nbsp;<span id="sp_showtext">&nbsp;</span></td>
  </tr>
</table>
<table align="center" width="450" border="0" cellpadding="0" cellspacing="0">
  <tr>
	<td width="100%">
	  <table width="100%">
        <tr height="25">
          <td align="center" width="100%">
            <input type=button value=' 确 定 ' class="inputbutton" onclick="_ok();">&nbsp;&nbsp;&nbsp;&nbsp;
            <input type=button value=' 关 闭 ' class="inputbutton" onclick="window.close();">
          </td>
        </tr>
      </table>
	</td>
  </tr>
</table>
	</td>
  </tr>
</table>

</body>
<SCRIPT LANGUAGE="JavaScript">
<!--
init();
initRoute();
currentPerformType = performTypeArray[0];
for(var i=0; i<performTypeArray.length; i++) {
	//办理人无需选择的情况
	if (performTypeArray[i] == PERFORMTYPE_NOSELECT) {
		_addall(routeIdArray[i], "performer", "");
	}
	//不是会签的情况
	if (performTypeArray[i] != PERFORMTYPE_JOINTSIGN) {
		//reader 默认全选。
		_addall(routeIdArray[i], "reader", "");
	}
}
//-->
//_addall('0', 'performer',document.all.perFormType.value);
//_ok();
</SCRIPT>

</html>
<%
}
catch(Exception e)
{
	e.printStackTrace();
	response.sendRedirect("/bpm/oa/error.jsp");
}
%>