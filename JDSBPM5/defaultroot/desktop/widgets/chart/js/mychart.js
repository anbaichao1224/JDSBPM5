/**
 * 对ext charts的封装
 * 使用时需导入 js/chart目录下的文件,
 * 其中的pack.js文件是在使用ext2.x时要导入的
 * 其它文件都是基于ext3.0的chart的js文件做了一定的修改,以适应现在用的swf文件
 * 3.0中的chart.swf文件和现在用的不一样,现在用的是更高版本的swf,
 * 原swf文件的功能不足
 */

Ext.ns("JDS.chart");

/*
图表配置说明
{
  panelCfg:{  //包含chart对象,其实就是flash对象的panel的配置,请参考ext的相关说明
              其中 seriesShowOrHide  属性定义是否添加对各子图形的显隐控制
    id:"",title:"",width:200,height:200.....
  },
  chartCfg:{   //自定义的chart的配置
    id:"",            //chart的id,如果有,要全局id唯一,可以省略
    chartType:"",     //字符串,图表类型,其取值为 JDS.chart.typeMap 中的值,如果省略,默认值为 "linechart"
    keyName:"",       //字符串,提供的数据中的 对比基准的索引名,比如要显示的是12个月的数据 ,                           必须
                          如对应数据[{month:"1月"},{month:"2月"}],此值即为 "month"
    texts:[],         //字符串数组,数组长度为要对比的数据分组数,其值为分组的可读名称,比如 显示 ["子公司1","子公司2"]  必须
    valuesName:[],    //字符串数组,其长度和texts对应,其值为要显示的对应分组在提供的数据中的索引,根据其数据生成图形    必须
                          如对应上面名称的2条数据[{val1:100,val2:200},{val1:150,val2:220}],此值即为["val1","val2"]
    series:[],        //字符串数组,一个图表里包括多个对比数据分组时,定义不同图形的类型,可以是 line,colum等,默认为chartType的类型
                          其数组索引对应texts中的对应值,所有未定义值全取默认值,如["","line","colum"]
    url:"",           //提供数据的url,返回的数据格式必须和下面的data的格式一致
    params:{},        //对url请求的提交参数,对象类型 如没有参数可以省略
    data:[],          //静态数据,如提供此值,则忽略url,params等值,格式为:[{},{}...]
    textPosition:""   //图例显示位置,如不定义则不显示图例,取值 "right"或 "left","top","bottom"

    seriesStyle:[],   //多分组时不同分组的图形的风格,可以定义不同的颜色,宽度等, 可以省略
    chartStyle:{}     //整体图表的风格, 可以省略

    ,yAxis:
    ,xAxis:
  }
}

例:显示1到3月份的 公司1和公司2的收入柱状图,其实数据如下
  [
    {month:"一月",num1:10000,num2:20000},
    {month:"二月",num1:15000,num2:22000},
    {month:"三月",num1:20000,num2:28000}
  ]
  其中 num1的是公司1的收入,num2是公司2的收入,month是几月

  chartCfg可如下配置
  {
    chartType:"columnchart",  //柱状图
    keyName:"month",
    texts:["公司1","公司2"],
    valuesName:["num1","num2"],
    url:"abc.jsp"

    ,textPosition:"bottom"   //如果有此值 则在图的下方显示 :"公司1" "公司2"
  }
  其中 abc.jsp所返回的值,要与上面的示例数据格式一致

具体例子可以看 testchart.js
 */
JDS.chart.typeMap={
  line:"linechart",
  bar:"barchart",
  column:"columnchart",
  stackedbar:"stackedbarchart",
  stackedcolumn:"stackedcolumnchart",
  pie:"piechart"
};
JDS.chart.types=["linechart","barchart","columnchart","stackedbarchart","stackedcolumnchart","piechart"];

/**
 * 创建chart面板的方法定义,单例
 */
JDS.chart.create = function() {
  var swfUrl='/desktop/widgets/chart/swf/charts.swf'; //swf文件的位置
//  var swfUrl="http:/"+"/yui.yahooapis.com/2.8.0r4/build/charts/assets/charts.swf";

  var defaultChart={
    xtype:JDS.chart.typeMap.line
  };

  //默认panel的配置
  var defaultPanelCfg = {
    title:"",
    width: 500,
    height: 300,
    seriesShowOrHide:false,
    layout:'fit'
  };

  var seriesTypes={
    "linechart":"line",
    "barchart":"bar",
    "columnchart":"column"
  }

  /**
   * 生成chart各项的详细配置,私有方法
   * @param c
   * @param cCfg
   */
  function getRealCfg(c,cCfg){
    if(JDS.chart.types.indexOf(c.xtype)<0){
      return null;
    }
    var keyField=null;
    var valueField=null;
    if(c.xtype=="barchart"||c.xtype=="stackedbarchart"){
      keyField="yField";
      valueField="xField";
    }else{
      keyField="xField";
      valueField="yField";
    }

    if(cCfg.yAxis){
      if(typeof(cCfg.yAxis)=="string"){
        var tmp=null;
        if(valueField=="yField"){
          tmp=new Ext.chart.NumericAxis({title: cCfg.yAxis});
        }else{
          tmp=new Ext.chart.CategoryAxis({title:cCfg.yAxis});
        }
        c.yAxis=tmp;
      }else{
        c.yAxis=cCfg.yAxis;
      }
    }
    if(cCfg.xAxis){
      if(typeof(cCfg.xAxis)=="string"){
        var tmp=null;
        if(valueField=="yField"){
          tmp=new Ext.chart.CategoryAxis({title: cCfg.xAxis});
        }else{
          tmp=new Ext.chart.NumericAxis({title:cCfg.xAxis});
        }
        c.xAxis=tmp;
      }else{
        c.xAxis=cCfg.xAxis;
      }
    }



    c[keyField]=cCfg.keyName;
    c.series=[];
    for (var i = 0; i < cCfg.texts.length; i++) {
      var tmp = {};
      tmp.displayName = cCfg.texts[i];
      if (c.xtype == "piechart") {
        tmp.dataField = cCfg.valuesName[i];
        tmp.categoryField = cCfg.keyName;
      } else {
        if (cCfg.series) {
          tmp.type = cCfg.series[i] || seriesTypes[c.xtype];
        } else {
         // tmp.type = seriesTypes[c.xtype];
        }
        if (!tmp.type) {
          delete tmp.type;
        }
        tmp[valueField] = cCfg.valuesName[i];
      }

      if (cCfg.seriesStyle && cCfg.seriesStyle[i]) {
        tmp.style = cCfg.seriesStyle[i];
      }
      c.series.push(tmp);
    }
    return c;
  }

  /**
   * 根据参数取得chart的整体配置,私有
   * @param cCfg
   */
  function getChartCfg(cCfg){
    var fields=[];
    fields.push(cCfg.keyName);
    fields=fields.concat(cCfg.valuesName);
    var storeCfg={fields:fields};
    if(cCfg.data){
      storeCfg.data=cCfg.data;
    }else{
      var kParam={};
      kParam.keysName=fields.join(",");
      storeCfg.url = cCfg.url;
//      storeCfg.baseParams=cCfg.params||{};
      storeCfg.baseParams=Ext.apply(kParam,cCfg.params||{});
    }
    var store = new Ext.data.JsonStore(storeCfg);
    var c={};
    c.xtype=cCfg.chartType||defaultChart.xtype;
    if(cCfg.id){
      c.id=cCfg.id;
    }
    c.store=store;
    c.url=cCfg.swfUrl||swfUrl;

    c=getRealCfg(c,cCfg);
    if(!c){
      alert("不支持的chart类型:"+cCfg.chartType);
      return null;
    }
    
    if(cCfg.chartStyle){
      c.chartStyle=cCfg.chartStyle;
    }
    c.extraStyle = {
      legend:{
        display: cCfg.textPosition || "bottom",
        padding: 5,
        font:{
          family: 'Tahoma',
          size: 13
        }
      }
    }
    c.polling=2000;
    return c;
  }

  /**
   * 为简单调用而定义
   */
  function getCfgFromSimpleCfg(cfg,type) {
    var c = {};
    var pc = {};
    var cc = {};
    cfg=Ext.apply({},cfg);
    pc.width = cfg.width||defaultPanelCfg.width;
    pc.height = cfg.height||defaultPanelCfg.height;
    pc.id = cfg.id;
    pc.title=cfg.title||"";
    pc.seriesShowOrHide=(cfg.seriesShowOrHide===true);

    delete cfg.width;
    delete cfg.height;
    delete cfg.id;
    delete cfg.title;
    delete cfg.seriesShowOrHide;
    
    Ext.apply(cc, cfg);
    cc.chartType=type||"linechart";
    c.panelCfg = pc;
    c.chartCfg = cc;
    return c;
  }

  return {
    /**
     * 生成chart面板,传入的参数中包括panelCfg和chartCfg
     * @param cfg
     */
    getChartPanel:function(cfg) {
      var chartCfg = getChartCfg(cfg.chartCfg);
      if(!chartCfg){
        return;
      }
      
      var chart = Ext.ComponentMgr.create(chartCfg, chartCfg.xtype);

      var pCfg = Ext.apply({}, cfg.panelCfg || {},defaultPanelCfg);
//    pCfg.items=chartCfg;
      pCfg.items = chart;
      if(chart.xtype!="piechart"&&(pCfg.seriesShowOrHide===true)){
        var bbar = [];
        bbar.push("->");
        var texts = cfg.chartCfg.texts;
        for (var i = 0; i < texts.length; i++) {
          bbar.push(" ");
          bbar.push(" ");
          bbar.push(" ");
          bbar.push(" ");
          bbar.push(texts[i] + ":");
          var check = new Ext.form.Checkbox({
            checked:true
          });
          check.idx = i;
          bbar.push(check);

          check.on("check", function(box, checked) {
            chart.showSeries(box.idx, checked);
          })
        }

        bbar.push("->");
        pCfg.bbar = bbar;
      }

      var panel = new Ext.Panel(pCfg);
      panel.store = chartCfg.store;
      panel.chart = chart;
//      panel.on("resize",function(obj, adjWidth,adjHeight,rawWidth,rawHeight){
//        obj.chart.setSize(adjWidth,adjHeight);
//      });
      return panel;
    },


    /**
     * 生成chart面板,传入的参数只是chartCfg,panel的配置取默认值
     * @param cfg
     */
    getSimpleChart:function(cfg){
      var c=getCfgFromSimpleCfg(cfg,cfg.chartType);
      return this.getChartPanel(c);
    },


    /**
     * 下面是为使用方便生成简单图形的简化方法 其参数包涵panel的w,h,及chart的基本参数等
     * @param cfg
     * id:id
     * width: panel的宽度
     * height:panel的高度
     * title:panel的标题
     * seriesShowOrHide:是否添加对各子图形的显隐控制
     *
     * keyName:"", 对比基准索引名
     * texts:[],   显示名数组
     * valuesName:[], 生成图形的数据的索引名
     * url:"",        查询数据的url
     * params:{},     查询时的参数
     * data:[],       生成图形的数据,如有此值,则忽略url,params等参数,格式为[{},{}...]
     */

//  line:"linechart",
//  bar:"barchart",
//  column:"columnchart",
//  stackedbar:"stackedbarchart",
//  stackedcolumn:"stackedcolumnchart",
//  pie:"piechart"
    getLineChart:function(cfg){
      var c=getCfgFromSimpleCfg(cfg,"linechart");
      return this.getChartPanel(c);
    },
    getBarChart:function(cfg){
      var c=getCfgFromSimpleCfg(cfg,"barchart");
      return this.getChartPanel(c);
    },
    getColumnChart:function(cfg){
      var c=getCfgFromSimpleCfg(cfg,"columnchart");
      return this.getChartPanel(c);
    },
    getPiechart:function(cfg){
      var c=getCfgFromSimpleCfg(cfg,"piechart");
      return this.getChartPanel(c);
    }
  }
}();
