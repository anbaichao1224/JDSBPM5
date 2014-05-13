<%@ page contentType="text/html; charset=utf-8"%>
<%@ page
	import="java.util.*,net.itjds.common.org.base.*"%>
<%@page import="kzxd.ttinfo.dao.TtInfoDAO;"%>
<%@ taglib uri="/struts-tags" prefix="ww"%>
<%
String path = request.getContextPath();
%>
<html>
	<head>
		<style type="text/css">
			.divstyle {
				float: left;
				width: 80px;
				font-size: 14px;
				padding-top: 8px;
				padding-left: 3px;
				height: 120px;
				margin: 0px;
			}
		</style>
		<script type="text/javascript">
	var context="/";
</script>
 
<script type="text/javascript" src="/js/extAll.js"></script> 
		<script type="text/javascript">
			function docsubmitup(){
				var fileName = document.getElementById("upfile").value;
				var modelname = document.getElementById("ttmc").value;
      			if(fileName == ""){
	      			alert("请选择需要上传的word模版");
					return false;
    	 		}
    	 		if(modelname==''){
    	 			alert("请填写模板名称");
					return false;
    	 		}
				
				var str = fileName.split(".");
				return true;
				
			}
			var fvalues="";
			function docshanchu(){
				var fileName = document.getElementsByName("uid");
				
				for(var i=0;i<fileName.length;i++){
					if(fileName[i].checked){
						
						if(i==fileName.length-1){
							fvalues+=fileName[i].value;
							continue;
						}
						fvalues+=fileName[i].value+",";
					}
					if(i==fileName.length-1){
						if(fvalues==""){
						alert("请选择需要删除的word模版");
						return false;
						}
					}
					
				}
      			  
			}
			
			function shangchuan(){
				if(docsubmitup()){
					document.fileform.action="ttManagerAction.action";
					return true;
				}else{
				    return false;
				}
			}
			
			function shanchu(){
				docshanchu();
				
				location.href="ttManagerAction_delete.action?ids="+fvalues;
				//document.fileform.submit();
			}
			
			function openOffice(modelId){
	var win = new Ext.Window({
		title:'在线编辑模版',
		width:700,
		height:Ext.getBody().getHeight(),
		items:new Ext.Panel({
			        title: '',        
				  	height:550,
				  	width:650,
					html:'<iframe width="600" height="550" src="<%=path%>/desktop/widgets/ttmanager/createModel.jsp?modelId='+modelId+'"></iframe>'
		})
	});
	win.show();
}
		</script>
	</head>
	<body>
		<table width="100%" border="1" bordercolor="#FFFFFF">
			<tr>
			
			
				<td style="border-bottom-color: #000000; valign:center">
					<form  enctype="multipart/form-data" method="post" name="fileform">
					模板名称：<input type="text" name="ttmc" />  
				     <input type="file" id="upfile" name="file"/>
				     <select name="wenzhong">
				        <!-- <option value="wu">无</option>
				     	<option value="neiwangzi">内网字</option>
				     	<option value="neiguomijufamidian">内国密局发密电</option>
				     	<option value="neiwangbanfa">内网办发</option>
				     	<option value="neijizi">内机字</option>
				     	<option value="neijifa">内机发</option>
				     	<option value="neimibanfa">内密办发</option>
				     	<option value="neimifa">内密发</option>
				     	<option value="neidangbanxinxibanfa">内党办信息办发</option>
				     	<option value="neiguomijufa">内国密局发</option>
				     	<option value="neiguomifa">内国密发</option>
				     	<option value="neiwangfa">内网发</option> -->
				     	<ww:iterator value="wzlist" status="rowstatus">
				     		<option value='<ww:property value="uuid"/>'><ww:property value="wzname"/></option>
				     	</ww:iterator>
				     </select>
				     <input type="submit" value="上传模板" onclick="return shangchuan()" />
				     <input type="button" value="删除模板" onclick="shanchu()" />
				  
					</form>
				</td>			
								
			</tr>
			
			    
			<tr>
				<td>
					
					<ww:iterator value="tlist" status="rowstatus">
					<div stype="width:20%" class="divstyle">
					        <input type='checkbox'  name='uid' value='<ww:property value="uuid"/>'>
							<a href="javascript:void(0)" onclick="window.top.openUrlWin('/desktop/widgets/ttmanager/createModel.jsp?modelId=<ww:property value="uuid"/>','红头模版编辑')"><img align="center" src="<%=path%>/desktop/widgets/bpm/form/document/img/word.bmp"/>	
							<ww:property value="ttmc"/></a>
							
					</div>
					</ww:iterator>
					
				</td>
			</tr>
		</table>
	</body>
</html>