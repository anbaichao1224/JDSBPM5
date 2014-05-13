<%@ page contentType="text/html; charset=utf-8"%>
<%@ page
	import="java.util.*,net.itjds.common.org.base.*,kzxd.documentmodel.entity.KZXDDocumentModel"%>
<%@ taglib uri="/struts-tags" prefix="ww"%>
<html>
	<head>
		<style type="text/css">
			div {
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
			function docsubmitup(){
				var fileName = document.getElementById("upfile").value;
      			if(fileName == ""){
	      			alert("请选择需要上传的word模版");
					return false;
    	 		}
				
				var str = fileName.split(".");
				var l=str.length-1;
				if("doc" == str[l] || "docx" == str[l]){
					return true;
				}else{
					alert("仅支持doc/docx模版上传");
					return false;
				}
				
			}
			var fvalues="";
			function docshanchu(){
				var fileName = document.getElementsByName("orgname1");
				
				for(var i=0;i<fileName.length;i++){
					if(fileName[i].checked){
						fvalues+=fileName[i].value+",";
						if(i==fileName.length-1){
							fvalues+=fileName[i].value;
						}
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
					document.fileform.action="docupload.action";
					return true;
				}else{
				    return false;
				}
			}
			
			function shanchu(){
				docshanchu();
				document.fileform.action="docupload_delete.action?checkbox="+fvalues;
			}
			
			
		</script>
	</head>
	<body>
		<table width="100%" border="1" bordercolor="#FFFFFF">
			<tr>
			
			
				<td style="border-bottom-color: #000000; valign:center">
					<form  enctype="multipart/form-data" method="post" name="fileform">  
				     <input type="file" id="upfile" name="file"/>
				     <input type="submit" value="上传模板" onclick="return shangchuan()" />
				     <input type="submit" value="删除模板" onclick="shanchu()" />
					<%
						String path = request.getContextPath();
						List<Org> orgs = (List)request.getAttribute("orgs");
						List<KZXDDocumentModel> mlists = (List)request.getAttribute("mlists");	
						for(int i = 0; i < orgs.size(); i++) {
							Org org = orgs.get(i);
							if(i == 0){
					%>
							<input type='checkbox' name='orgname' value='<%=org.getName()%>' checked><%=org.getName()%>
					<%
							}else {
					%>
							<input type='checkbox' name='orgname' value='<%=org.getName()%>'><%=org.getName()%>
					<%
							
							}
						}
					%>
					
					</form>
				</td>			
								
			</tr>
			
			    
			<tr>
				<td>
					
					<%
						for(int i = 0; i < mlists.size(); i++){
							KZXDDocumentModel km = (KZXDDocumentModel)mlists.get(i);
					%>
					<div stype="width:20%">
					        <input type='checkbox'  name='orgname1' value='<%=km.getUUID()%>'>
							<img align="center" src="<%=path%>/desktop/widgets/bpm/form/document/img/word.bmp"/>	
							<%=km.getModelName() %>
							
					</div>
					<%
						}
					%>
					
				</td>
			</tr>
		</table>
	</body>
</html>