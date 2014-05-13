<%@ page contentType="text/html; charset=GB2312" %>
<%@ page import="java.util.*"%>
<%@ page import="net.itjds.bpm.client.*"%>
<%@ page import="net.itjds.common.org.base.*"%>
<%@ page import="net.itjds.common.org.util.*"%>
<%@ page import="net.itjds.bpm.engine.*"%>
<%@ page import="net.itjds.oa.OAUtil"%>


<%
    response.setHeader("Pragma", "No-cache");
	response.setHeader("Cache-Control", "no-cache");
	response.setDateHeader("Expires", 0);
    
	try
	{ 
		OrgManager orgManager = OrgManagerFactory.getOrgManager();
		OrgHTMLTree orgTree = new OrgHTMLTree(orgManager.getOrgs(), true, false, true);
		String activityInstId = request.getParameter("activityInstId");
		
		ProcessInst processInst = null;
		ActivityInst activityInst = null;
		WorkflowClientService client = null;
		AdminService admin = null;
		List activityDefs = new ArrayList();
		try {
			client = OAUtil.getClient(request);
			admin = OAUtil.getAdmin(client);
			activityInst = admin.getActivityInst(activityInstId);
			processInst = activityInst.getProcessInst();
			activityDefs = admin.getAllActivityDefs(activityInstId);
		} catch (Exception e) {
			request.setAttribute("Throwable", e);
			request.getRequestDispatcher("/bpm/oa/error.jsp").forward(request, response);
			return;
		} finally {
		}

		int activityDefCount = activityDefs.size();
		String[] performTypes = new String[activityDefCount];
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
//	sp_selected = "sp_performer";
	PERFORMTYPE_SINGLE = "<%=OARightConstants.PERFORMTYPE_SINGLE%>";
	PERFORMTYPE_MULTIPLE = "<%=OARightConstants.PERFORMTYPE_MULTIPLE%>";
	PERFORMTYPE_NOSELECT = "<%=OARightConstants.PERFORMTYPE_NOSELECT%>";
	PERFORMTYPE_JOINTSIGN = "<%=OARightConstants.PERFORMTYPE_JOINTSIGN%>";
	performTypeArray = new Array();
	//路由标识（现在存的是序号）数组
	idArray = new Array();
	//相应的活动定义的ID
	activityDefIdArray = new Array();
	//当前的办理方式
	currentPerformType = "";
	//当前选择的部门名称和ID
	currentOrgName = "";
	currentOrgId = "";
	currentParentOrgName = "";
	currentForm = "";

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
	currentForm = frmIn;
	
    var _url = "/bpm/admin/common/GetPersonServlet?orgid=" + id
    oDownload.startDownload(_url, finish);
}

function finish(s)
{
	if (currentPerformType == PERFORMTYPE_JOINTSIGN) {
		return;
	}

    var list = currentForm.personlist;
    list.options.length = 0;

    var array = s.split("\n");
    if(array[array.length-1] == "GetPerson=true")
    {
        for(var i=0; i<array.length-1; i++)
        {
            var tmp = array[i].split("|");
            var name = tmp[0];
            var id = tmp[1];
            var o = new Option(name, id);
            list.options[i] = o;
        }
    }
    else
    {
        alert("读取此机构下人员失败！");
    }
}

//数据初始化
function init()
{
<% 
for(int ii=0; ii<activityDefCount;ii++){
	ActivityDef activityDef = (ActivityDef)activityDefs.get(ii);
	if (activityDef == null) {
		continue;
	}
	
	String performType = (String) client.getActivityDefRightAttribute(activityDef.getActivityDefId(),
		 OARightConstants.ACTIVITYDEF_RIGHT_ATT_PERFORMTYPE, null);
	if (performType == null) {
		performType = OARightConstants.PERFORMTYPE_SINGLE;
	}
	if (performType.equals(OARightConstants.PERFORMTYPE_NOSELECT)) {
		performType = OARightConstants.PERFORMTYPE_MULTIPLE;
	}
	performTypes[ii] = performType;
%>
		/*idArray begin*/
	idArray[<%=ii%>] = "<%=ii%>";
	activityDefIdArray[<%=ii%>] = "<%=activityDef.getActivityDefId()%>";
	performTypeArray[<%=ii%>] = "<%=performType%>";
		/*idArray end*/

<%	
}//end of top for
%>
}

function initActivity() {
	/*activity init begin*/
	if (<%=activityDefCount%> > 1) {
		eval("document.all.cb_activity" + idArray[0]).click();
	} else {
		window.dialogWidth = "465px";
	}
	/*activity init end*/
}
    
function _add(type)
{
	var performType = currentPerformType;
	if (performType == PERFORMTYPE_JOINTSIGN) {
		if (currentOrgName == "") {
			return;
		}
	    var selected  = eval("document.all." + type + ".personselected");
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
	
    var list = eval("document.all." + type + ".personlist");
    var selected  = eval("document.all." + type + ".personselected");

    //单人办理。
    if (performType == PERFORMTYPE_SINGLE && type == "performer") {
    	selected.options.length = 0;
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
			    if (performType == PERFORMTYPE_SINGLE && type == "performer") {
			    	return;
			    }
            }
        }
    }
}

function _addall(type)
{
	var performType = currentPerformType;
    var list = eval("document.all." + type + ".personlist");
    var selected  = eval("document.all." + type + ".personselected");
    //单人办理、办理人。
    if (performType == PERFORMTYPE_SINGLE && type == "performer") {
    	if (selected.options.length != 0) {
	    	return;
    	}
    }
    
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
		    if (performType == PERFORMTYPE_SINGLE && selected.options.length == 1 && type == "performer") {
		    	return;
		    }
        }
    }
}

function _del(type)
{
    var selected  = eval("document.all." + type + ".personselected");
    for(var i=selected.options.length-1; i>=0; i--)
    {
        if(selected.options[i].selected == true)
        {
            selected.options[i] = null;
        }
    }
}

function _delall(type)
{
    var selected  = eval("document.all." + type + ".personselected");
    selected.options.length = 0;
}

//选择办理人
function selectPerformer() {
	var frmIn = eval("document.all.switcher.all");
	frmIn.td_performer.bgColor="#666699";
	frmIn.sp_performer.style.color="white";
	frmIn.sp_performer.style.cursor="default";

	frmIn.td_reader.bgColor = "#efefef";
	frmIn.sp_reader.style.color = "black";
	frmIn.sp_reader.style.cursor="hand";

	var o = eval("document.all.tb_performer");
	o.style.display="";
	o = eval("document.all.tb_reader");
	o.style.display="none";
}

//选择读者
function selectReader() {
	var frmIn = eval("document.all.switcher.all");
	frmIn.td_reader.bgColor="#666699";
	frmIn.sp_reader.style.color="white";
	frmIn.sp_reader.style.cursor="default";

	frmIn.td_performer.bgColor = "#efefef";
	frmIn.sp_performer.style.color = "black";
	frmIn.sp_performer.style.cursor="hand";

	var o = eval("document.all.tb_performer");
	o.style.display="none";
	o = eval("document.all.tb_reader");
	o.style.display="";
}

//选择活动
function selectActivity(ii) {
	currentPerformType = performTypeArray[ii];
	document.all.td_reader.style.display = "";
	document.all.td_reader1.style.display = "";
    document.all.performer.personselected.options.length = 0;
	
	//会签
	if (currentPerformType == PERFORMTYPE_JOINTSIGN) {
		document.all.td_reader.style.display = "none";
		document.all.td_reader1.style.display = "none";
		document.all.sp_performer.click();
		document.all.performer.personlist.options.length=0;
	}
	//多人办理
	if (currentPerformType == PERFORMTYPE_MULTIPLE) {
		document.all.performer.all.personlist.multiple = true;
	} else {
		document.all.performer.all.personlist.multiple = false;
	}
	
	var o;
	if (typeof activityop != "undefined") {
		activityop.style.backgroundColor = activitybg;
		activityop.style.border = "none";
	}
	var os = eval("document.all.activity" + ii);
	activitybg = os.style.backgroundColor;
	os.style.backgroundColor = "ccddee";
	os.style.border = "0px solid #666666";
	activityop = os;
}

function checkActivity(o, ii) {
	if (o.checked) {
		selectActivity(ii);
	} else {
		o.checked = false;
	}
}

function _ok()
{
	var ret = new Array();
	var activityIndex = 0;
	for (var i=0; i<idArray.length; i++) {
		if (idArray.length > 1) {
			var cb_o = eval("document.all.cb_activity" + idArray[i]);
			if (!(cb_o.checked)) {
				continue;
			}
		}
		ret[activityIndex] = new Object();
		var activityId = idArray[i];
		//activityDefId
		ret[activityIndex].activityDefId = activityDefIdArray[i];
		ret[activityIndex].performType = performTypeArray[i];
		
		var readers = new Array();
		ret[activityIndex].readers = readers;
		//会签情况，忽略读者
		if (performTypeArray[i] != PERFORMTYPE_JOINTSIGN) {
			//readers
			var o = eval("document.forms.reader");
			var objSelect = o.personselected;
			for(var j=0; j<objSelect.length; j++)
			{
				readers[j] = new Object();
				readers[j].id = objSelect.options[j].value;
				readers[j].name = objSelect.options[j].text;
			}
		}
		
		//performers
		var o = eval("document.forms.performer");
		var objSelect = o.personselected;
		if (objSelect.length == 0) {
			alert("请选择办理人或办理部门！");
			if (idArray.length >1) {
				selectActivity(activityId);
			}
			if (performTypeArray[i] != PERFORMTYPE_JOINTSIGN) {
				selectPerformer();
			}
			return;
		}
		var performers = new Array();
		ret[activityIndex].performers = performers;
		for(var j=0; j<objSelect.length; j++)
		{
			performers[j] = new Object();
			performers[j].id = objSelect.options[j].value;
			performers[j].name = objSelect.options[j].text;
		}

		activityIndex++;
	}
	if (activityIndex == 0) {
		alert("您还未选择活动，请选择！");
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
<!--tree script here-->
<%=orgTree.getScript()%>
<IE:DOWNLOAD ID="oDownload" STYLE="behavior:url(#default#download)" />
<table width="100%" align="center" border="0" cellspacing="0" cellpadding="0">
  <tr>
<% 
	if (activityDefCount > 1) { 
%> 
    <td width="120" align="center" valign="middle">
	  <table width="90%">
	    <tr>
		  <td>请选择路由↓</td>
	    </tr> 
	  </table> 
	  <table width="98%" height="100%" border="0" cellspacing="0" cellpadding="0">
<% 
	for(int ii=0; ii<activityDefCount;ii++){
		ActivityDef activityDef = (ActivityDef) activityDefs.get(ii);
		if(activityDef == null) {
			continue;
		}
		
		String activityDefName = activityDef.getName();
		if (activityDefName == null) {activityDefName = "null";}
%>
  	    <tr> 
		  <td width="<%=(activityDefName.length()*20+23)%>" align="left">
		    <table width="100%">
			  <tr>
				<td width="21"><input type='radio' id="cb_activity<%=ii%>" onclick='checkActivity(this, "<%=ii%>")' name="activityCheck"></td>
			    <td class=treeview_ob_td align="left" id="activity<%=ii%>"><%=activityDefName%></td>
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
<table id="tb" width="100%" border="0" cellspacing="0" cellpadding="0">
<tr>
<td>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
<tr align=left>
  <td height="10"> 
	<table cellspacing=0 cellpadding=0 width="1%" border="0" cellspacing="0" cellpadding="0">
	  <tr height="20">
		<form name="switcher">
		<td nowrap width="25%" bgcolor=#666699 align="center" id="td_performer">
		  <span id="sp_performer" class="A_1" style="color:white" onclick="selectPerformer()">&nbsp;选择办理人&nbsp;</span>
		</td>
		<td width=1 bgcolor=#808080><img height=1 alt="" width=1></td>
		<td width=1 bgcolor=#ffffff><img height=1 alt="" width=1></td>
		<td nowrap width="25%" bgcolor=#efefef align="center" id="td_reader">
		  <span id="sp_reader" class="A_1" onclick="selectReader()" style="cursor:hand">&nbsp;选择传阅人&nbsp;</span>
		</td>
		<td width=1 bgcolor=#808080><img height=1 alt="" width=1 id="td_reader1"></td>
		</form>
	  </tr>
	</table>
  </td>
</tr>
</table>

<table align="center" width="450" border="0" cellpadding="0" cellspacing="0" id="tb_performer">
  <tr>
    <td height="1"> </td>
  </tr>
  <tr>
<form name="performer">
    <td align="center" valign="top">
      <table align="center" width="450" border="1" width="100%" cellspacing="0" cellpadding="0" bordercolordark="#FFFFFF" bordercolorlight="#0362af" bgcolor="#f9f9e6" rules="none">
        <tr>
          <td align="left" valign="top" width="145">
	       <fieldset style="width:145px;height:100%" align="center" id=listset> 
	       <legend><span color='#0C0C0C'> 选择组织机构 </span></legend>
            <div style="overflow:auto;width:140;height:200">
            <%=orgTree.getRootHtml()%>
            </div>
           </fieldset>
          </td>
          <td width="110" align="left">
	       <fieldset style="width:110;height:100%" align="center" id=listset> 
	       <legend><span color='#0C0C0C'> 选择人员 </span></legend>
           <div style="overflow-y:auto;width:110;height:200" align="center">
            <select id="personlist" size=12 ondblclick="_add('performer')" style="width:110">
            </select>
           </div>
           </fieldset>
          </td>
          <td width="50" align="left">
           <table>
            <tr>
             <td>
              <input type=button value=' >  ' class="inputbutton" onclick="_add('performer')"><br>
             </td>
            </tr>
            <tr>
             <td>
              <input type=button value=' >> ' class="inputbutton" onclick="_addall('performer')"><br>
             </td>
            </tr>
            <tr height="10">
             <td>
             </td>
            </tr>
            <tr>
             <td>
              <input type=button value=' <  ' class="inputbutton" onclick="_del('performer')"><br>
             </td>
            </tr>
            <tr>
             <td>
              <input type=button value=' << ' class="inputbutton" onclick="_delall('performer')"><br>
             </td>
            </tr>
           </table>
          </td>
          <td width="110" align="left">
	       <fieldset style="width:110;height:100%" align="center" id=listset> 
	       <legend><span color='#0C0C0C'> 已选中人员 </span></legend>
           <div style="overflow-y:auto;width:110;height:200" align="center">
            <select id="personselected" multiple size=12 style="width:110">
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

<table align="center" width="450" border="0" cellpadding="0" cellspacing="0" id="tb_reader" style="display:none">
  <tr>
    <td height="1"> </td>
  </tr>
  <tr>
<form name="reader">
    <td align="center" valign="top">
      <table align="center" width="450" border="1" width="100%" cellspacing="0" cellpadding="0" bordercolordark="#FFFFFF" bordercolorlight="#0362af" bgcolor="#f9f9e6" rules="none">
        <tr>
          <td align="left" valign="top" width="145">
	       <fieldset style="width:145px;height:100%" align="center" id=listset> 
	       <legend><span color='#0C0C0C'> 选择组织机构 </span></legend>
            <div style="overflow:auto;width:140;height:200">
            <%=orgTree.getRootHtml()%>
            </div>
           </fieldset>
          </td>
          <td width="110" align="left">
	       <fieldset style="width:110;height:100%" align="center" id=listset> 
	       <legend><span color='#0C0C0C'> 选择人员 </span></legend>
           <div style="overflow-y:auto;width:110;height:200" align="center">
            <select id="personlist" size=12 ondblclick="_add('reader')" multiple style="width:110">
            </select>
           </div>
           </fieldset>
          </td>
          <td width="50" align="left">
           <table>
            <tr>
             <td>
              <input type=button value=' >  ' class="inputbutton" onclick="_add('reader')"><br>
             </td>
            </tr>
            <tr>
             <td>
              <input type=button value=' >> ' class="inputbutton" onclick="_addall('reader')"><br>
             </td>
            </tr>
            <tr height="10">
             <td>
             </td>
            </tr>
            <tr>
             <td>
              <input type=button value=' <  ' class="inputbutton" onclick="_del('reader')"><br>
             </td>
            </tr>
            <tr>
             <td>
              <input type=button value=' << ' class="inputbutton" onclick="_delall('reader')"><br>
             </td>
            </tr>
           </table>
          </td>
          <td width="110" align="left">
	       <fieldset style="width:110;height:100%" align="center" id=listset> 
	       <legend><span color='#0C0C0C'> 已选中人员 </span></legend>
           <div style="overflow-y:auto;width:110;height:200" align="center">
            <select id="personselected" multiple size=12 style="width:110">
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
<!--persons end-->

<table align="center" width="450" border="0" cellpadding="0" cellspacing="0">
  <tr>
	<td width="100%">
	  <table width="100%">
		<tr height="5"><td></td></tr>
        <tr height="40">
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
initActivity();
currentPerformType = performTypeArray[0];
//-->
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