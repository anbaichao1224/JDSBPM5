<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.*,kzxd.documentmodel.entity.KZXDDocumentModel,kzxd.ttinfo.dao.TtInfoDAO" %>
<%@ taglib uri="/struts-tags" prefix="ww"%>
<html>
<head>
<script type="text/javascript">
function getimgeUrl(){

	var temp;
	var imgeRadio = document.getElementById("modelname");
	if(imgeRadio.length>0){
		for(var i=0;i<imgeRadio.length;i++){
			if(imgeRadio[i].checked){
				temp = imgeRadio[i];
				return temp;
			}
		}
	}
}


</script>
</head>
<body>
		<table width="100%" border="1" bordercolor="#FFFFFF">
			<tr>
				<td>
					
					<%
					
						String wenzhong = (String)request.getAttribute("wenzhongQ");
						String path = request.getContextPath();
						List<TtInfoDAO> mlists = (List)request.getAttribute("tlist");	
						for(int i = 0; i < mlists.size(); i++){
							TtInfoDAO km = (TtInfoDAO)mlists.get(i);
							if(km.getWenzhong()!=null &&  km.getWenzhong().equals(wenzhong)){
								
							
					%>
					<div stype="width:20%">
							<input type="radio" id="modelname" checked="checked" name="modelname" value="<%=km.getUuid()%>.<%=km.getFiletype() %>"/><%=km.getTtmc() %>
					</div>
					<%		
							}else{
					%>
					<div stype="width:20%">
							<input type="radio" id="modelname"  name="modelname" value="<%=km.getUuid()%>.<%=km.getFiletype() %>"/><%=km.getTtmc() %>
					</div>	
								
					<% 
							}
						}
					%>
					
				</td>
			</tr>
		</table>
</body>
</html>