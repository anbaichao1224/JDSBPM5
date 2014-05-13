<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="ww"%>

<%
String contextpath = request.getContextPath() + "/";
String urlContext=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+contextpath;
%>


<html>

	<head>
		<title></title>
		<link rel="STYLESHEET" type="text/css"
			href="<%=contextpath%>fdt/designer/css/Context.css">
		<link rel="STYLESHEET" type="text/css"
			href="<%=contextpath%>fdt/designer/css/search.css">
		<link rel="stylesheet" type="text/css"
			href="<%=contextpath%>js/ext/resources/css/menus.css" />
		<link rel="stylesheet" type="text/css"
			href="<%=contextpath%>js/ext/resources/css/ext-all.css" />
        <style type="text/css">

        </style>

        <SCRIPT LANGUAGE="JavaScript" type="text/javascript">
            _js_prefix='<%=contextpath%>js/';
            context='<%=contextpath%>';
            var _urlContext="<%=urlContext%>";

        </SCRIPT>
        <script  src="<%=contextpath%>js/prototype.js" type="text/javascript"></script>
        <script type="text/javascript" src="<%=contextpath%>js/extAll.js"></script>
          <script language="javascript" src="<%=contextpath%>js/dhtmlXAll.js"></script>
		<script type="text/javascript" src="<%=contextpath%>js/JDS.js"></script>
        <script language="javascript" src="<%=contextpath%>js/ajax/ajaxJSONPar.js"></script>
            
        <script language="javascript" src="<%=contextpath%>fdt/designer/js/ajax/indexAjax.js"></script>
        <script language="javascript" src="<%=contextpath%>fdt/designer/js/itemarr/itemarr.js"></script>
        <script language="javascript" src="<%=contextpath%>fdt/designer/js/ajax/ajaxJSONPar.js"></script>
 
      <script language="javascript" src="<%=contextpath%>fdt/designer/js/expresstree.js"></script>
		<script type="text/javascript">
	
    //公式编辑窗口层
    var expressionEditorWin;
    var hasTree=<ww:property value="$R('hasTree')" default="true"/>;
    var expStr='<ww:property value="$R('expStr')"/>';
  
    var expressionTreesCfg=[
      <ww:iterator value="$esbBeanMap" status="routs">
      <ww:if test="value.type!='map'">
        {
        cnname:'<ww:property value="value.cnname"/>',
        type:'<ww:property value="key"/>'
        } <ww:if test="!#routs.last">,</ww:if>
      </ww:if>
      </ww:iterator>
    ];

        </script>
	</head>

	<body onload="allInit()" >

        <div id="north"></div>
          <div id="dd" style="POSITION: relative;"></div>
            <div id="dd" style="POSITION: relative;"></div>
        <div id="attsPanel" style="POSITION: relative;display:none;">
         <div id="div2"></div>
        </div>
        <table height="350" width="100%" id="expression" border="0" align="left" style="display:none;">
            <tr>
                <td width=30 align="right" valign="top">
                    <div id="linenum"
                        style="POSITION: relative;left:0;text-align:right;align:right;valign:top;font-size:12pt;font-weight:bolder;color:#0000ff;margin-top:2;line-height:16px;width:100%;height:100%;overflow:hidden">
                        <script language="javascript">
                            var tmpLineStr="";
                            for(var i=1;i<=200;i++)
                            {
                                tmpLineStr+=i+"<br>";
                            }
                            document.write(tmpLineStr);
                        </script>
                    </div>
                </td>
                <td valign="top" id="iframetd">
                   <iframe  onblur="" onload="addScroll()"
                        name="HtmlEdit" id="HtmlEdit" MARGINHEIGHT="1" MARGINWIDTH="1"
                        width="100%" height="350" frameborder="0"
                        style="border:1px solid gray">
                    </iframe>
                </td>
            </tr>
        </table>
  </body>
<script>
    //初始化操作
    Ext.onReady(extInit);
      
    function allInit() {
        iframeInit();
      openExpressionEditor(expStr)
    }

</script>
</html>
