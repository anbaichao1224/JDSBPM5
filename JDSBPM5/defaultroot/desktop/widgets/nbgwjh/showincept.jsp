<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="ww"%>
<%@page import="net.itjds.j2ee.util.UUID"%>
<%@ page
		import= "java.util.Date"
		import ="java.text.SimpleDateFormat"
%>
<%
	String contextpath = request.getContextPath() + "/";
	String uuid = request.getParameter("uuid");
	String sendid = request.getParameter("id");
	String status = request.getParameter("status");
	System.out.println("status=" + status);
	String signDate="";
	Date date1=(Date)request.getAttribute("from");
	System.out.println(date1+"date111");
	if(date1!=null){
	  SimpleDateFormat sfd = new SimpleDateFormat("yyyy-MM-dd");
	  signDate = sfd.format(date1);
	  System.out.println(signDate+"signDate");
	
	}
%>
<HTML>
	<HEAD>

<style type="text/css">
<!--
body {font-family: "宋体", "Arial";background-color:#dfe8f6}
.STYLE3 {font-size: 24px}
.STYLE4 {font-size: 20px}
-->
</style>

<script type="text/javascript">
	var context="/";
</script>

		<script type="text/javascript" src="/js/extAll.js"></script>
		<script type="text/javascript"
			src="/desktop/widgets/nbgwjh/js/bufadeparttree.js" defer="defer"></script>
		<script type="text/javascript"
			src="<%=contextpath%>desktop/js/CreateGrid.js"></script>
		<script src="/js/JDS.js" type="text/javascript"></script>


	</HEAD>
	<BODY>
<script type="text/javascript">
 var uuid = '<%=uuid%>';
var sendid='<%=sendid%>';
var status = '<%=status%>';
var signTime='<%=signDate%>';
function getInceptid(){
  return uuid;
}

function getSendid(){
  return sendid;
}
		  var backflag=<ww:property value="backflag"/>;
		  if(backflag==1){
		    document.all.back.disabled='true';
		  }
	
});
	
	function goback(){
	  var status = <%=status%>;
	  
	  if(status==0){
	   window.location.href="/desktop/widgets/nbgwjh/unreceivelist.jsp";
	   }else{
	   window.location.href="/desktop/widgets/nbgwjh/receivelist.jsp";
	   }
	
	}
	
	function bback(){
	  var inceptid='<%=uuid%>';
      var _width = 900;
		var _height = Ext.getBody().getHeight()-40;
		var addwin = new Ext.Window({
					title : '拒签理由',
					layout : 'fit',
					width : _width,
					minWidth : 350,
					height : _height,
					minHeight : 200,
					y:0,
					html:"<iframe id='djiframe1' name='djiframe1' width='" + (_width-10)  + "' height='" + (_height - 50) + "' src='nbgwjhAction_addbackpage.action?uuid="+ getInceptid()+"&id="+getSendid()+"'></iframe>",
					tbar:[
						{
							id : 'djsave',
							icon : "/usm/img/save.gif",
							text : '确定',
							cls : "x-btn-text-icon",
							handler : function(){
						  		  var fn = Ext.get('djiframe1').dom.contentWindow.document.getElementById("addBack");
									fn.submit();
									alert("拒签成功");
									addwin.close();
						      
						}
						},{
							id : 'djreset',
							icon : "/usm/img/depart.gif",
							text : '重  置',
							cls : "x-btn-text-icon",
							handler : function(){
								var fn = Ext.get('djiframe1').dom.contentWindow.document.getElementById("addBack");
								fn.reset();
							}
						}
					]
			});
			addwin.show();
	 
	   
	}
	function addHz1(){
		var _width = 850;
		var _height = Ext.getBody().getHeight()-50;
		var addwin = new Ext.Window({
					title : '添加回执信息',
					layout : 'fit',
					width : _width,
					minWidth : 350,
					height : _height,
					minHeight : 200,
					y:0,
					html:"<iframe id='djiframe2' name='djiframe2' width='" + (_width-10)  + "' height='" + (_height - 50) + "' src='nbgwjhAction_addpage.action?uuid="+ getInceptid()+"&id="+getSendid()+"'></iframe>",
					tbar:[
						{
							id : 'djsave',
							icon : "/usm/img/save.gif",
							text : '回复',
							cls : "x-btn-text-icon",
							handler : function(){
								var fn = Ext.get('djiframe2').dom.contentWindow.document.getElementById("addHz");
								fn.submit();
								alert("回复成功");
								addwin.close();
							}
						},{
							id : 'djreset',
							icon : "/usm/img/depart.gif",
							text : '重  置',
							cls : "x-btn-text-icon",
							handler : function(){
								var fn = Ext.get('djiframe2').dom.contentWindow.document.getElementById("addHz");
								fn.reset();
							}
						}
					]
			});
			addwin.show();
  }
</script>
		<center>
			<form name="addSend" id="addSend">
				<table align="center" width="750" height="530" border="1"
				       cellpadding="0" cellspacing="0">
					<input type="hidden" name="parentid" id="parentid"
						value='<%=request.getParameter("parentid")%>' />
					<input type="hidden" name="tid" id="tid" value='<%=sendid%>' />
					<input type="hidden" name="optionnum" id="optionnum" value='1' />

					<input type="hidden" name="mettinguuid" id="mettinguuid"
						value='<%=request.getParameter("mettinguuid")%>' />
					<input type="hidden" name="adddirection" id="adddirection"
						value='2' />
					<input type="hidden" name="liststatus" id="liststatus"
						value='<%=request.getParameter("liststatus")%>' />
						
				<tr>
					<td width="65" height="46">
						<div align="center">
							<span class="STYLE4">发文人</span>
						</div>
					</td>
					<td width="65" align="center">
						<input name="sendbean.sendor" type="text" readOnly="true" value='<ww:property value="dao.sendor"/>'></input>
					</td>
					<td width="80">
						<div align="center">
							<span class="STYLE4">发文单位</span>
						</div>
					</td>
					<td width="99" align="center">
						<input name="sendbean.senddept" type="text" readOnly="true" value='<ww:property value="dao.senddept"/>'></input>
					</td>
					
				</tr>
				<tr>
					<td height="57">
						<div align="center">
							<span class="STYLE4">标 题</span>
						</div>
					</td>
					<td colspan="4" align="center">
						<textarea name="sendbean.sendTitle" id="sendTitle" cols="75" rows="5"><ww:property value="dao.sendTitle"/></textarea>
					</td>
				</tr>		
					<tr>
					<td height="57">
						<div align="center">
							<span class="STYLE4">备 注 </span>
						</div>
					</td>
					<td colspan="4" align="center">
						<textarea name="sendbean.sendTitle1" cols="75" rows="5"><ww:property value="dao.sendTitle1"/></textarea>
					</td>
				</tr>	
						
				<tr>
					<td height="150" align="center" class="tdClass">
						<div align="center">
							<span class="STYLE4">附 件</span>
							
						</div>
				  </td>
					<td colspan="5" style="width:50px" class="tdClass">
					
						<ww:action name="sdocexchangefileinclude" executeResult="true"></ww:action>
					
					</td>
				</tr>
			
				</table>
			</form>
		</center>
	</BODY>
	<script type="text/javascript">

	
	function winClose(){
		window.close();
	}
	

	
 </script>
</HTML>