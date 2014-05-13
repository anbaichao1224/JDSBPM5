<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.*,kzxd.documentmodel.entity.KZXDDocumentModel" %>
<%@ taglib uri="/struts-tags" prefix="ww"%>
<html>
<head>
</head>
<body>
		<table width="100%" border="1" bordercolor="#FFFFFF">
			<tr>
				<td>
					
					<%
						String path = request.getContextPath();
						List<KZXDDocumentModel> mlists = (List)request.getAttribute("mlists");	
						for(int i = 0; i < mlists.size(); i++){
							KZXDDocumentModel km = (KZXDDocumentModel)mlists.get(i);
					%>
					<div stype="width:20%">
							<input type="radio" id="modelname" name="modelname" value="<%=km.getUUID()%>"/><%=km.getModelName() %>
					</div>
					<%
						}
					%>
					
				</td>
			</tr>
		</table>
</body>
</html>