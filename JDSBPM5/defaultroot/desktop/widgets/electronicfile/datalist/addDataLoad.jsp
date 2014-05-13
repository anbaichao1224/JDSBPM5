<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="ww"%>
<%
	String path = request.getContextPath();

	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String wordname = request.getParameter("wordname");
	String activityInstId = request.getParameter("activityInstId");
	String personId = request.getParameter("personId");
	String formId = request.getParameter("formId");
	if(personId==null){
		personId = request.getSession().getAttribute("personId").toString();
	}
%>
<html>
	<head>
		<base href="<%=basePath%>"></base>
		<script>var context='<%=path%>';</script>

		<meta http-equiv="pragma" content="no-cache" />
		<meta http-equiv="cache-control" content="no-cache" />

		<script type="text/javascript" src="<%=path%>js/extAll.js"></script>
		<script type="text/javascript">
			Ext.onReady(function(){
				var win = new Ext.Panel(
			{
				id :'positionWin',
//				title :'报送信息',
				bodyStyle:'width:100%',
				height:Ext.getBody().getHeight()-10,
				renderTo: Ext.getBody(),
				closeAction :'close',
				closable :true,
				collapsible :true,
				autoScroll :false,
				tbar:new Ext.Toolbar({
					style: 'background-color:#F8F8FF; background-image:url();', 
		          items:[{
			          text:"归  档",
			          tooltip:"文件归档",
			          handler:function(){
			         //window.hide();
			          window.close();
			          	//this.Close();
			                //alert("go 保存");
			                //var str="";
			                // Ext.get('biaodan').dom.contentWindow.saveDataInfo();
			                 
							//window.frames("biaodan").document.forms("addData").saveDataInfo();
			          },
			          iconCls: 'saveButton'
		         }]
		        }),//desktop/widgets/electronicfile/datalist/addData1.jsp
		        html:'<iframe id="biaodan" align="center" name="biaodan" frameborder="0" src="/data_returnaddData.action?wordname=<%=wordname%>&activityInstId=<%=activityInstId%>&personId=<%=personId%>&formId=<%=formId%>" height="100%" width="100%"></iframe>'
				

			});
				
			})
		</script>
	</head>
	<body>
	<div id="grid"></div>
	</body>
</html>