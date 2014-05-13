<%@ page import="java.util.Map" %>

<%
String contextpath = request.getContextPath() + "/";
  String personid=request.getParameter("personid");
  if(personid==null){
    personid=(String)request.getAttribute("personid");
  }
  if(personid==null){
    personid="6140";
  }
%>
<html>
  <head>
    <title></title>

    <script type="text/javascript">
      var context='<%=contextpath%>';
      var personid="<%=personid%>";
      var currUserName='';
    </script>
 
    <link rel="stylesheet" type="text/css" href="<%=contextpath%>js/ext/resources/css/ext-all.css" />
    <script type="text/javascript" src="<%=contextpath%>js/ext/adapter/ext/ext-base.js"></script>
    <script type="text/javascript" src="<%=contextpath%>js/ext/ext-all-debug.js"></script>
    <script type="text/javascript" src="<%=contextpath%>js/ext/locale/ext-lang-zh_CN.js"></script>
    <script type="text/javascript" src="<%=contextpath%>js/ext/message-box/msg-box.js"></script>

  
    <script type="text/javascript" src="<%=contextpath%>desktop/widgets/jame/js/imgPath.js"></script>
    <script type="text/javascript" src="<%=contextpath%>desktop/widgets/jame/js/conn.js"></script>
    <script type="text/javascript" src="<%=contextpath%>desktop/widgets/jame/js/action.js"></script>
    <script type="text/javascript" src="<%=contextpath%>desktop/widgets/jame/js/menu.js"></script>
    <script type="text/javascript" src="<%=contextpath%>desktop/widgets/jame/js/jameTree.js"></script>
    <script type="text/javascript" src="<%=contextpath%>desktop/widgets/jame/js/mainWin.js"></script>
    <script type="text/javascript" src="<%=contextpath%>desktop/widgets/jame/js/myJame.js"></script>

    <script type="text/javascript" src="<%=contextpath%>desktop/js/CreateGrid.js"></script>


    <script type="text/javascript" src="<%=contextpath%>desktop/widgets/jame/js/cust/ImgPalette.js"></script>
    <script type="text/javascript" src="<%=contextpath%>desktop/widgets/jame/js/cust/ImgMenu.js"></script>
    <script type="text/javascript" src="<%=contextpath%>desktop/widgets/jame/js/cust/ImgMenu2x.js"></script>
    <script type="text/javascript" src="<%=contextpath%>desktop/widgets/jame/js/cust/custBtn.js"></script>
    <script type="text/javascript" src="<%=contextpath%>desktop/widgets/jame/js/chatWin.js"></script>

    <script type="text/javascript">

    var extVer = new Number(Ext.version);
      var jameMainwin;
      function getMainWin(){
        return jameMainwin;
      }
      function mainInit(){
        jameMainwin=new Jame.ui.MainWin(personid,currUserName);
        jameMainwin.show();
      }

function createMainJameWin() {
  jameMainwin = new Jame.ui.MainWin(personid, currUserName);
  jameMainwin.show();
}

    </script>
    <script type="text/javascript">
      var testMenu1=new Ext.menu.Menu([
        {text:"11"},
        {text:"12"}
      ]);
      var testMenu2=new Ext.menu.Menu([
        {text:"21"},
        {text:"22"}
      ]);
      function init(){
        extInit();
        mainInit();
      }
      Ext.onReady(init);
    </script>

  </head>
  <body style="">

  </body>
</html>