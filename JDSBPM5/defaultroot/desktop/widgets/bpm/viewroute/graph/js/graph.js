/*

function MyGraph(container)
{
mxGraph.call(this, container);
}

MyGraph.prototype = new mxGraph();
MyGraph.prototype.constructor = MyGraph;
 */
 
 
  //var mxBasePath = '../resources/';
  
/**
 * 对graph的操作的包装
 * 其 .graph即 mxGraph的一个实例
 */
var SimpleGraph = function(container) {
  this.container = container;
  this.graph = new mxGraph(container);
  this.init();
  this.animate=true;
  this.rootId = "0";
//  this.addHandler();
}

/**
 * 各状态图片的定义
 */
var statusImagePath=mxBasePath+"images/";
SimpleGraph.prototype.flags={
  warning:statusImagePath+"warning.gif",
  error:statusImagePath+"error.gif"

,

     "open":statusImagePath+"open.gif",
   "running":statusImagePath+"running.gif",
   "notRunning":statusImagePath+"drop-no.gif",
   "notStarted":statusImagePath+"drop-no.gif",
   "suspended":statusImagePath+"suspended.png",
   "closed":statusImagePath+"close2.gif",
   "aborted":statusImagePath+"error.gif",
   "terminated":statusImagePath+"error.gif",
   "completed":statusImagePath+"endread.gif",

    NORMAL:"", // 正常
  READ:statusImagePath+"read.gif",// 阅办
  processNotStarted:statusImagePath+"notstart.gif",// 草稿
  ENDREAD:statusImagePath+"endread.gif",// 阅闭
  DELAY:statusImagePath+"delay.gif",// 延期
  URGENCY:statusImagePath+"urgency.gif",// 催办
  ALERT:statusImagePath+"warning1.gif"// 报警
}

//SimpleGraph.prototype.addHandler=function() {
//  var self = this;
//
//  this.graph.getTooltipForCell = function(cell) {
//    return cell.message || self.graph.convertValueToString(cell)||"";
//  }
//  this.graph.setTooltips(true);
//  this.graph.cellsEditable=false;
//
//  function clickHandler(sender, evt) {
//    var cell = evt.getArgAt(1);
//    if (!cell)return;
//    if(!cell.isVertex())return;
//    var id = cell.getId();
//    id = id.replace(/_sub$/, "");
//    var subCell = this.getCell(id + "_sub");
//
//    if (!subCell) {
//      var url="abc2.xml";//取指定id的节点的子节点的xml描述url todo
//      this.addSubLayerFormUrl(id, url,"id="+id);
//
//      var c=this.getCell(id + "_sub");
//      this.modVertexStyles(c.children,{fillColor:"#f00"});
//      this.modEdgeStyles(c.children,{strokeColor:"#f00"});
//
//      this.initFlag(c);
//      this.horizontalTree(c);
//  //    this.verticalTree(this.getCell(id+"_sub"));
//    } else {
//      this.setDisplay(id + "_sub", !this.isDisplay(id + "_sub"));
//    }
//  }
//
//  this.graph.addListener(mxEvent.CLICK, function() {
//    clickHandler.apply(self, arguments);
//  });
//
////  var listener = function(sender, evt) {
////    if (self.animate) {
////      mxUtils.animateChanges(self.graph, evt.getArgAt(0)/*changes*/);
////    }
////  };
////  this.graph.getModel().addListener(mxEvent.CHANGE, listener);
//
//  //this.graph.getModel().removeListener(listener);
//}

//初始化默认风格等,目前用default-style.xml里的配置
SimpleGraph.prototype.init = function() {
  var node = mxUtils.load(mxBasePath+'default-style.xml').getDocumentElement();
  var dec = new mxCodec(node.ownerDocument);
  dec.decode(node, this.graph.getStylesheet());
  return;

//
  //    var graph = this.graph;
  //    graph.setCellsResizable(true);
  //    graph.setResizeContainer(true);
  //    graph.minimumContainerSize = new mxRectangle(0, 0, 500, 380);
  //    graph.setBorder(60);
  //
  ////			graph.setTooltips(true);
  //    //			graph.setEnabled(false);
  //
  //    graph.isCellEditable = function(cell) {
  //      return !this.getModel().isEdge(cell);
  //    };
  //
  //    var style = graph.getStylesheet().getDefaultVertexStyle();
  //    style[mxConstants.STYLE_STROKECOLOR] = 'gray';
  //    style[mxConstants.STYLE_ROUNDED] = true;
  //    style[mxConstants.STYLE_SHADOW] = true;
  //    style[mxConstants.STYLE_FILLCOLOR] = '#DFDFDF';
  //    style[mxConstants.STYLE_GRADIENTCOLOR] = 'white';
  //    style[mxConstants.STYLE_FONTCOLOR] = 'black';
  //    style[mxConstants.STYLE_FONTSIZE] = '12';
  //    style[mxConstants.STYLE_SPACING] = 4;
  ////    style[mxConstants.STYLE_SHAPE] = mxConstants.SHAPE_RHOMBUS;
  //    window.testStyle = style;
  //
  //    style = graph.getStylesheet().getDefaultEdgeStyle();
  //    style[mxConstants.STYLE_STROKECOLOR] = '#0C0C0C';
  //    style[mxConstants.STYLE_LABEL_BACKGROUNDCOLOR] = 'white';
  //    style[mxConstants.STYLE_EDGE] = mxEdgeStyle.ElbowConnector;
  //    style[mxConstants.STYLE_ROUNDED] = true;
  //    style[mxConstants.STYLE_FONTCOLOR] = 'black';
  //    style[mxConstants.STYLE_FONTSIZE] = '10';
}

/**
 * 改变节点的样式
 * @param cell 节点
 * @param newStyle 样式:格式为{名称:值,...}
 */
SimpleGraph.prototype.modStyle=function(cell,newStyle){
//  fillColor:"#f00",
//gradientColor:"#00f"
  //style[mxConstants.STYLE_GRADIENT_DIRECTION] = mxConstants.DIRECTION_EAST;
  var graph=this.graph;
  for(var k in newStyle){
    graph.setCellStyles(k,newStyle[k],[cell]);
  }
}
SimpleGraph.prototype.modStyles=function(cells,newStyle){
  var graph=this.graph;
  for(var k in newStyle){
    graph.setCellStyles(k,newStyle[k],cells);
  }
}
SimpleGraph.prototype.modVertexStyles=function(cells,newStyle){
  var graph=this.graph;
  var cs=[];
  for(var k in cells){
    var c=cells[k];
    if(c.isVertex()){
      cs.push(c);
    }
  }
  for(var k in newStyle){
    graph.setCellStyles(k,newStyle[k],cs);
  }
}
SimpleGraph.prototype.modEdgeStyles=function(cells,newStyle){
  var graph=this.graph;
  var cs=[];
  for(var k in cells){
    var c=cells[k];
    if(c.isEdge()){
      cs.push(c);
    }
  }
  for(var k in newStyle){
    graph.setCellStyles(k,newStyle[k],cs);
  }
}
//SimpleGraph.prototype.setStyle=function(cell,style){
//  cell.setStyle(style);
//}

/**
 * 取得根节点,即 0 节点
 */
SimpleGraph.prototype.getRoot=function(){
  return this.graph.getModel().getRoot();
}

/**
 * 取得所有的层级节点,即根的子节点
 */
SimpleGraph.prototype.getLayers=function(){
  var cell=this.graph.getModel().getRoot();
  //var count=cell.getChildCount();
  var cs=cell.children;
  return cs||[];
}

/**
 * 添加一层
 * @param id
 */
SimpleGraph.prototype.addLayer = function(id) {
//  var graph = this.graph;
//  var parent = this.getRoot();
//
//  graph.getModel().beginUpdate();
//  try {
//    var cell = new mxCell();
//    if (id) {
//      cell.setId(id);
//    }
//    var v0 = graph.addCell(cell, parent);
//    id = cell.getId();
//  } finally {
//
//    try {
//      graph.getModel().endUpdate();
//    } catch(e) {
//      //nothing
//    }
//  }
  var root=this.getRoot();
   var cell = new mxCell();
    if (id) {
      cell.setId(id);
    }
  root.insert(cell);
  id=cell.getId();
  this.graph.getModel().cells[id]=cell;
  this.graph.setDefaultParent(cell);
  return id;
}

/**
 * 设置指定的层的显示状态
 * @param id
 * @param isDisplay
 */
SimpleGraph.prototype.setDisplay = function(id, isDisplay) {
  var model = this.graph.getModel();
  model.beginUpdate();
  try {
    var layer = this.getCell(id);
    model.setVisible(layer, isDisplay);

    this.graph.view.invalidate();
  } finally {
    model.endUpdate();
  }
}

SimpleGraph.prototype.isDisplay = function(id) {
  var model = this.graph.getModel();
  var layer = this.getCell(id);
  return model.isVisible(layer);
}

/**
 * 初始化各节点的右下角的标志
 */
SimpleGraph.prototype.initFlag=function(parent){
  var cells=null;
  if (parent) {
     cells= parent.children;
  } else {
    cells = this.getCells();
  }
  for (var k in cells) {
    var status = cells[k].status;
    if (status) {
      this.addFlag(cells[k], this.flags[status], cells[k].statusMessage||status);
    }
  }
}

/**
 * 在右下角加一个小标志
 * @param cell  在哪个节点上加
 * @param image image对象或图片路径,如果未定义,刚默认为警告图片
 * @param text  点击后显示的文本
 */
SimpleGraph.prototype.addFlag = function(cell, image, text) {
  if (image) {
    if (typeof image == "string") {
      image = new mxImage(image, 16, 16);//根据img的url生成image对象
    }
  } else {
    //image = this.graph.warningImage;
    return;
  }
  this.graph.addCellOverlay(cell, this.createOverlay(image, text || ""));
}

/**
 * 删除所加的标记
 * @param cell
 */
SimpleGraph.prototype.deleteFlag = function(cell) {
  this.graph.removeCellOverlays(cell);
}

//加标记用
SimpleGraph.prototype.createOverlay = function(image, tooltip, fn) {
  var overlay = new mxCellOverlay(image, tooltip);
//  fn = fn || function(sender, evt) {
//    mxUtils.alert(tooltip + '\n' + 'Last update: ' + new Date());
//  };
//  overlay.addListener(mxEvent.CLICK, fn);
  return overlay;
}

/**
 * 从指定路径加载xml数据
 * @param url
 * @param params
 */
SimpleGraph.prototype.getXmlFromUrl=function(url,params){
  //  var req = mxUtils.load(filename); //get方法取数据
  var req = new mxXmlRequest(url, params, 'POST', false);
  req.send();
  var root = req.getDocumentElement();
  return root.ownerDocument;
}
/**
 * 从指定路径加载xml数据,生成(更新)graph的内容
 * @param url
 * @param params
 */
SimpleGraph.prototype.loadFromUrl = function (url, params) {
  this.updateByXml(this.getXmlFromUrl(url,params));
}

/**
 * 直接用xml格式的字符串生成(更新)graph的内容
 * @param xmlStr
 */
SimpleGraph.prototype.updateByXmlStr = function (xmlStr) {
  var doc = mxUtils.parseXml(xmlStr);
  this.updateByXml(doc);
}

/**
 * 直接用xml对象生成(更新)graph的内容
 * @param xml
 */
SimpleGraph.prototype.updateByXml = function(xml) {
  var codec = new mxCodec(xml);
  codec.decode(xml.documentElement, this.graph.getModel());
  this.rootId=this.getRoot().getId();
    //  g.setCellStyles(mxConstants.STYLE_SHAPE, mxConstants.SHAPE_ACTOR, [cell]);
}

/**
 * 把当前的graph的内容转成对应的xml字符串
 */
SimpleGraph.prototype.getXmlStr = function () {
  var encoder = new mxCodec();
  var node = encoder.encode(this.graph.getModel());
  return mxUtils.getPrettyXml(node);
}

/**
 * 把指定cell的内容转成对应的xml字符串
 * @param cell
 */
SimpleGraph.prototype.getXmlFromCell = function (cell) {
  var encoder = new mxCodec();
  var node=encoder.document.createElement("mxGraphModel");
  encoder.encodeCell(cell,node);
  return mxUtils.getPrettyXml(node);
}

/**
 * 改变为不同的布局
 * @param layout  布局的实例
 * @param animate 是否动画显示
 * @param ignoreChildCount  忽略子节点
 */
SimpleGraph.prototype.executeLayout = function(layout,cell, animate, ignoreChildCount) {
  var graph = this.graph;
  if(!cell){
    cell = graph.getSelectionCell();
  }

//  if (cell == null || (!ignoreChildCount && graph.getModel().getChildCount(cell) == 0)) {
//    cell = graph.getDefaultParent();
//  }
  if(!cell){
    cell=this.getLayers();
  }

  if(cell instanceof Array){
    for(var i=0;i<cell.length;i++){
      layout.execute(cell[i]);
    }
  }else{
    layout.execute(cell);
  }
}

/**
 * 垂直树状布局
 * @param animate
 * @param ignoreChildCount
 */
SimpleGraph.prototype.verticalTree = function(cell,animate, ignoreChildCount) {
  var layout = new mxCompactTreeLayout(this.graph, false);
  animate = animate == undefined ? true : animate;
  this.executeLayout(layout,cell, animate, ignoreChildCount)
}

/**
 * 水平树状布局
 * @param animate
 * @param ignoreChildCount
 */
SimpleGraph.prototype.horizontalTree = function(cell,animate, ignoreChildCount) {
  var layout = new mxCompactTreeLayout(this.graph);
  animate = animate == undefined ? true : animate;
  this.executeLayout(layout,cell, animate, ignoreChildCount)
}

/**
 * 圆形
 * @param animate
 * @param ignoreChildCount
 */
SimpleGraph.prototype.circleLayout = function(cell,animate, ignoreChildCount) {
  var layout = new mxCircleLayout(this.graph);
  animate = animate == undefined ? true : animate;
  this.executeLayout(layout,cell, animate, ignoreChildCount)
}

/**
 * 有机?分组?不清楚
 * @param animate
 * @param ignoreChildCount
 */
SimpleGraph.prototype.organicLayout = function(cell,animate, ignoreChildCount) {
  var layout = new mxFastOrganicLayout(this.graph);
  layout.forceConstant = 80;
  animate = animate == undefined ? true : animate;
  this.executeLayout(layout,cell, animate, ignoreChildCount)
}

//取得指定cell的位置
SimpleGraph.prototype.getCellXY=function(cell){
  var geo=cell.getGeometry();
  return {x:geo.x,y:geo.y};
}

/**
 * 移动cell
 * @param cells cell数组
 * @param dx x方向上要移动的距离
 * @param dy y方向上要移动的距离
 */
SimpleGraph.prototype.moveCells=function(cells,dx,dy){
  this.graph.moveCells(cells,dx,dy);
}

/**
 * 把cell移动到指定的位置
 * @param cell
 * @param x
 * @param y
 */
SimpleGraph.prototype.moveCellTo=function(cell,x,y){
  var xy=this.getCellXY(cell);
  var dx=x-xy.x;
  var dy=y-xy.y;
  this.graph.moveCells([cell],dx,dy);
}

/**
 * 根据id取得节点
 * @param id
 */
SimpleGraph.prototype.getCell=function(id){
  return this.graph.getModel().getCell(id);
}

/**
 * 取得所有节点
 * @param id
 */
SimpleGraph.prototype.getCells=function(id){
  return this.graph.getModel().cells;
}

/**
 * 从url加载xml数据,增加节点
 * @param url
 * @param params
 */
SimpleGraph.prototype.addCellsFormUrl=function(url,params){
  this.addCellsByXml(this.getXmlFromUrl(url,params));
}

/**
 * 根据xml数据,增加相应的节点
 * @param xml
 */
SimpleGraph.prototype.addCellsByXml=function(xml){
//  var xml = mxUtils.parseXml(xmlStr);
  var nodes=xml.documentElement.firstChild.childNodes;
  var cells = [];
  var codec = new mxCodec(xml);
  var edges = [];
  for (var i = 0,n = nodes.length; i < n; i++) {
    var pId=nodes[i].getAttribute("parent");
    if(pId=="0"){
      this.addLayer(nodes[i].getAttribute("id"));

    }else{
      var cell = codec.decodeCell(nodes[i]);
      cells.push(cell);
//    var id = cell.id;
//    var c = this.getCell(id);
//    if (c) {
//      var es1 = this.graph.getIncomingEdges(c);
//      var es2 = this.graph.getOutgoingEdges(c);

//      this.graph.removeCells([c], false);
//      edges.push({id:id,ins:es1,outs:es2});
//    }
    }
  }
  this.graph.addCells(cells);
//  this.resetEdges(edges);
}

/**
 * 为指定id的节点增加一个独立的子节点层
 * @param pId
 * @param url
 * @param params
 */
SimpleGraph.prototype.addSubLayerFormUrl=function(pId,url,params){
  this.addSubLayerByXml(pId,this.getXmlFromUrl(url,params));
}
SimpleGraph.prototype.addSubLayerByXml = function(pId, xml) {
  if(!xml)return;
  var nodes = xml.documentElement.firstChild.childNodes;
  var layerId = pId + "_sub";
  this.addLayer(layerId);
  var cells = [];
  var codec = new mxCodec(xml);
  var edges = [];
  for (var i = 0,n = nodes.length; i < n; i++) {
    var node = nodes[i];
    var id = node.getAttribute("id");
    var tmpId = nodes[i].getAttribute("parent");
    if (id == this.rootId || tmpId == this.rootId) {
      continue;
    }
    node.setAttribute("parent", layerId);
    var cell = codec.decodeCell(nodes[i]);
    cells.push(cell);
  }
  this.graph.addCells(cells);
}

/**
 * 重新连接上一条边
 * @param edge 要连接的边
 * @param id   重新连接的节点的id
 * @param inOrOut 是'in' 或 'out'
 */
SimpleGraph.prototype.resetEdge=function(edge,id,inOrOut){
  var cell=this.getCell(id);
  var isIn=false;
  if(inOrOut=="in"){
    isIn=true;
  }else{
    isIn=false;
  }
  if(cell){
    if(isIn){
      edge.target=cell;
    }else{
      edge.source=cell;
    }
    this.graph.cellConnected(edge, edge.source, true);
    this.graph.cellConnected(edge, edge.target, false);
  }
}

/**
 * 重新连接指定的边
 * @param edges 要连接的对象的数组
 * 格式为:
 * {
 *  id:要连接的节点的id
 *  ins:id对应节点的incoming的边数组
 *  outs:id对应节点的outgoing的边数组
 */
SimpleGraph.prototype.resetEdges=function(edges){
  alert(edges.length)
  for(var i=0,n=edges.length;i<n;i++){
    var obj=edges[i];
    var ins=obj.ins;
    alert(ins.length)
    for(var j=0,c=ins.length;j<c;j++){
      this.resetEdge(ins[j],obj.id,"in");
    }
    var outs=obj.outs;
    for(var j=0,c=outs.length;j<c;j++){
      this.resetEdge(ins[j],obj.id,"out");
    }
  }
}
SimpleGraph.prototype.test=function(){

  var xmlStr='<mxGraphModel>'+
//'  <root>'+

//'<mxCell id="a3" value="aaaaa11111aaaaa!" parent="a1" vertex="1">'+
//'  <mxGeometry x="630" y="270" width="80" height="40" as="geometry"/>'+
//'</mxCell>'+
'<mxCell id="a5" value="abcddefsfd!" parent="a1" vertex="1">'+
'  <mxGeometry x="630" y="370" width="80" height="40" as="geometry"/>'+
'</mxCell>'+
'<mxCell id="a7" value="77777!" parent="a1" vertex="1">'+
'  <mxGeometry x="630" y="470" width="80" height="40" as="geometry"/>'+
'</mxCell>'+
'<mxCell id="a6" value="aaaaaaaaaaaaa!" parent="a3" source="a3" target="a5" edge="1">'+
'  <mxGeometry relative="1" as="geometry"/>'+
//
//        '<mxPoint x="670" y="310" as="sourcePoint"/>'+
//        '<mxPoint x="670" y="350" as="targetPoint"/>'+
'</mxCell>'+
'<mxCell id="a8" value="bbbbb!" parent="a6" source="a7" target="a5" edge="1">'+
'  <mxGeometry relative="1" as="geometry"/>'+
//
//        '<mxPoint x="670" y="310" as="sourcePoint"/>'+
//        '<mxPoint x="670" y="350" as="targetPoint"/>'+
'</mxCell>'+
//'  </root>'+
'</mxGraphModel>';

  var xml = mxUtils.parseXml(xmlStr);
  this.addCellsByXml(xml);
}
SimpleGraph.prototype.test2=function(){

  var cell=this.graph.getSelectionCell();
if(!cell){
  cell=this.getRoot();
}
  var encoder = new mxCodec();
  var node=encoder.document.createElement("mxGraphModel");
//  cell=this.getRoots()[0];
encoder.encodeCell(cell,node);
  alert(mxUtils.getPrettyXml(node));
}
