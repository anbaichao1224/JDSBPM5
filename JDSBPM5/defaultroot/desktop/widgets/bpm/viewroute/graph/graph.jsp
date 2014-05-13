<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/struts-tags" prefix="ww" %>	
<%
response.setHeader("Pragma","No-cache"); 
response.setHeader("Cache-Control","no-cache"); 
response.setDateHeader("Expires", 0); 
String contextpath = request.getContextPath() + "/";
%> 	
<html>
<head>
<title>测试2</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf8" />

<!-- Sets the basepath for the library if not in same directory -->
<script type="text/javascript">
  var mxBasePath = '<%=contextpath %>desktop/widgets/bpm/viewroute/graph/resources/';
</script>
<script type="text/javascript" src="<%=contextpath %>desktop/widgets/bpm/viewroute/graph/resources/js/mxclient.js"></script>
<script type="text/javascript" src="<%=contextpath %>desktop/widgets/bpm/viewroute/graph/js/graph.js"></script>


<script type="text/javascript">

  function showGraph() {
    var div = document.body;
    var sg = new SimpleGraph(div);
    var g = sg.graph;

    addHandler(sg);
    g.isCellFoldable = function(cell, collapse) {
      return false;
    };

    var url="routelogViewData.action?activityInstId=<ww:property value="activityInstId"/>&activityInstHistoryId=<ww:property value="activityInstHistoryId"/>";
    var params="";
    sg.loadFromUrl(url,params);
    sg.initFlag();
    g.zoomActual();
    g.setEnabled(false);
    window.sGraph=sg;
  }

function addHandler(self) {

  self.graph.getTooltipForCell = function(cell) {
    return cell.message || self.graph.convertValueToString(cell)||"";
  }
  self.graph.setTooltips(true);
  self.graph.cellsEditable=false;

  mxEvent.addMouseWheelListener(function(evt, flag) {
    if(!evt.ctrlKey)return;
    if (flag) {
      self.graph.zoomIn();
    } else {
      self.graph.zoomOut();
    }
  });

  <%--function clickHandler(sender, evt) {--%>
    <%--var cell = evt.getArgAt(1);--%>
    <%--if (!cell)return;--%>
    <%--if(!cell.isVertex())return;--%>
    <%--var id = cell.getId();--%>
    <%--id = id.replace(/_sub$/, "");--%>
    <%--var subCell = this.getCell(id + "_sub");--%>

    <%--if (!subCell) {--%>
      <%--var url="routelogViewCellData.action";//取指定id的节点的子节点的xml描述url todo--%>
      <%--this.addSubLayerFormUrl(id, url,"activityDefId="+id+"&activityInstId=<ww:property value="activityInstId"/>");--%>

      <%--var c=this.getCell(id + "_sub");--%>
      <%--this.modVertexStyles(c.children,{fillColor:"#f00"});--%>
      <%--this.modEdgeStyles(c.children,{strokeColor:"#f00"});--%>

      <%--this.initFlag(c);--%>
      <%--this.horizontalTree(c);--%>
  <%--//    this.verticalTree(this.getCell(id+"_sub"));--%>
    <%--} else {--%>
      <%--this.setDisplay(id + "_sub", !this.isDisplay(id + "_sub"));--%>
    <%--}--%>
  <%--}--%>

  <%--self.graph.addListener(mxEvent.CLICK, function() {--%>
    <%--clickHandler.apply(self, arguments);--%>
  <%--});--%>

//  var listener = function(sender, evt) {
//    if (self.animate) {
//      mxUtils.animateChanges(self.graph, evt.getArgAt(0)/*changes*/);
//    }
//  };
//  this.graph.getModel().addListener(mxEvent.CHANGE, listener);

  //this.graph.getModel().removeListener(listener);
}
</script>
</head>
<body onload="showGraph(),document.getElementById('writ').style.display='none'">
<table border="0" height="100%" id="writ" align="center">
<tr><td height="40%">&nbsp;</td></tr>
<tr><td height="30%" ><span id="disp">页面加载中,请等待</span></td></tr>
<tr><td height="30%" ><img src="<%=contextpath %>desktop/widgets/mainpanel/images/05043119.gif"></td></tr>
</table>
</body>
</html>