<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="ww"%>

<%@ page
		import= "java.util.Date"
		import ="java.text.SimpleDateFormat"
%>
<%
	String contextpath = request.getContextPath() + "/";
	String sendid = request.getParameter("uuid");
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
						<td height="57">
						  <div align="center">
								<span class="STYLE4">已发人</span>
							</div>
						</td>
						<td colspan="4" align="center">
						    <textarea  name="deptnames" readOnly ="true"
						type="text" id="deptnames" onkeydown="return false;" cols="75" rows="3"><ww:property value="sandrange"/></textarea>
						<input type="hidden" name="sentid" id="sentid" value='<ww:property value="sandrange"/>' />
						</td>
					</tr>
					<tr>
						<td align="center">
						 <div align="center">
							<span class="STYLE4">补发</span>
						 </div>
						</td>
						<td colspan="3" align="center">
							<textarea size="70" name="bufadeptnames" type="text"
								id="bufadeptnames" onkeydown="return false;" cols="65" rows="3" ></textarea>
							<input type="hidden" name="id" id="id" />
							<input type="hidden" name="yfid" id="yfid"
								value='<ww:property value="yfid"/>' />
							<input name="choice" size="70" type="button" id="choice"
								value="选择" onclick="createPersonPositionWindow('5015','')" />
							&nbsp;&nbsp;<input type="button" name="savematter" onclick="save()"
								value="发送" />
							
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

     function save(){

     	var sendid='<%=sendid%>';

        var inceptdept=document.addSend.bufadeptnames.value
           if(inceptdept==""){
              alert("发送人不能为空");
              return false;
           }
			 Ext.Ajax.request({
			 	url:'nbgwjhAction_extrasave.action',
			 	method:'post',
			 	params:{
			 	'uuid':sendid
			 	},
			 	form:'addSend',
			 	success:function(){
			 		alert("发送成功");
			 	},failure:function(){
			 	   alert("发送失败");
			 	}
			 })
	}
	
	function goback(){
	   window.location.href="/desktop/widgets/nbgwjh/hassentlist.jsp";
	}
	
  </script>
</HTML>