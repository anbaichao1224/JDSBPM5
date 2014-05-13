/**
 * 对Fusion charts的封装,目前只有仪表盘图
 */

Ext.ns("JDS.fchart");

/*
图表配置说明
{
  panelCfg:{  //包含chart对象,其实就是flash对象的panel的配置,请参考ext的相关说明
              其中 seriesShowOrHide  属性定义是否添加对各子图形的显隐控制
    id:"",title:"",width:200,height:200.....
  },
  chartCfg:{   //自定义的chart的配置
    id:"",            //chart的id,如果有,要全局id唯一,可以省略
    chartType:"",     //字符串,图表类型,目前只有 f_gaugechart
    xmlStr:""   //图表数据
  }
}

 */
JDS.fchart.typeMap={
  gauge:"f_gaugechart"
};
JDS.fchart.types=["f_gaugechart"];

JDS.fchart.exportImg=function(){
  var divId="fchartExport_div";
  var div=document.getElementById(divId);
  var expComp;
  if(!div){
//    return ;//todo 目前有问题
    div=document.createElement("div");
    div.id=divId;
    div.style.width = 80;
    div.style.height=40;
    div.style.position="absolute";
    div.style.display="none";
    div.style.zIndex="99999";
    Ext.onReady(function(){
      document.body.appendChild(div);
      var expSwf = "/desktop/widgets/chart/fusionchart/swf/FCExporter.swf";
      expComp = new FusionChartsExportObject("fcExporter1", expSwf,
      {width:80,height:40,
        btndisabledtitle:"保存.",btnsavetitle:"单击保存",
        btnwidth:80
      }
          );
      expComp.srcObj.setTransparent();
      expComp.Render(divId);
    });
  }

  var getOffsetValue=function(obj,att){
    var n=0;
    while(obj&&obj.tagName!="BODY"){
      n+=obj[att];
      obj=obj.parentNode;
    }
    return n;
  }
  var getObjXY=function(obj){
    var x=getOffsetValue(obj,"offsetLeft");
    var y=getOffsetValue(obj,"offsetTop");
    return {x:x,y:y};
  };
  var dataReady=function(cId){
    var sObj=document.getElementById(cId);
//    alert(cId+"=="+sObj)
    if(sObj){
div.style.display="";
      var pObj=getChartFromId(cId).panel;
      var xy=getObjXY(pObj.getEl().dom);
      var w=pObj.getSize().width;
//      var h=pObj.getSize().height;
      div.style.display="";
      div.style.left = xy.x+w-div.offsetWidth;
      div.style.top = xy.y;
//      div.style.left = xy.x+w/2-div.offsetWidth/2;
//      div.style.top = xy.y+h/2-div.offsetHeight/2;
    }else{
      alert("导出的数据对象错误"+cId);
    }
  };
  var expImgEnd=function(obj){
    div.style.top = -1000;
    div.style.left = -1000;
    expComp.update();
//    setTimeout(function(){
//      div.style.display="none";
//    },1000);
  }
  return{
//    expObj:myExportComponent,
    dataReady:dataReady,
    expImgEnd:expImgEnd
  }
}();

function FC_ExportReady(cId){
//  alert(cId)
  JDS.fchart.exportImg.dataReady(cId);
}

/**
 * 创建chart面板的方法定义,单例
 */
JDS.fchart.create = function() {
  var swfBase="/desktop/widgets/chart/fusionchart/swf/"; //swf文件的位置
  var swfUrls={
    "f_gaugechart":swfBase+"AngularGauge.swf"
  };

  //默认panel的配置
  var defaultPanelCfg = {
    title:"",
    width: 500,
    height: 300,
    layout:'fit'
  };

  var showChart = function(p) {
    setTimeout(function() {
      var chart = new FusionCharts(p.chartCfg.url, p.chartCfg.id, p.getInnerWidth(), p.getInnerHeight(),"0","1");
      chart.setDataXML(p.chartCfg.xmlStr);
      chart.setTransparent();
      chart.render(p.body.dom);
      p.chart = chart;

      getChartFromId(p.chartCfg.id).panel=p;
    }, 1);
  };

  var getCfgFromSimpleCfg=function(cfg){

    var c = {};
    var pc = {};
    var cc = {};
    cfg=Ext.apply({},cfg);
    pc.width = cfg.width||defaultPanelCfg.width;
    pc.height = cfg.height||defaultPanelCfg.height;
    pc.id = cfg.id;
    pc.title=cfg.title||"";

    delete cfg.width;
    delete cfg.height;
    delete cfg.id;
    delete cfg.title;

    Ext.apply(cc, cfg);
    cc.chartType=cfg.chartType||"f_gaugechart";
    c.panelCfg = pc;
    c.chartCfg = cc;
    return c;
  }
  return {
    /**
     * 生成fchart面板,传入的参数中包括panelCfg和chartCfg
     * @param cfg
     */
    getChartPanel:function(cfg) {
      var chartCfg = cfg.chartCfg;
      if (!chartCfg) {
        return;
      }

      var pCfg = Ext.apply({}, cfg.panelCfg || {}, defaultPanelCfg);
      var sUrl = swfUrls[chartCfg.chartType];
      var xmlStr = chartCfg.chartData;
      var id = (pCfg.id || ("Fgchart"+Ext.id()))+"_fchart";

      var panel = new Ext.Panel(pCfg);
      var cc={url:sUrl,id:id,xmlStr:xmlStr};
      panel.chartCfg = cc;
      panel.on("render", showChart);
      return panel;
    },
    getSimpleChart:function(cfg){
      var c=getCfgFromSimpleCfg(cfg);
      return this.getChartPanel(c);
    }
  }
}();
