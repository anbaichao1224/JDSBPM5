//用于根据id取指定chart的配置数据
Ext.ns("JDS.showChart");

JDS.showChart=function(){
  var curCharts={};
  var hasChart=function(id){
    return curCharts[id]?true:false;
  }
  
  var url="/desktop/widgets/chart/getExtChartData.jsp";
  var requestUrl=function(cfg) {
    var url = cfg.url;
    var param = cfg.param || "";
    var method = cfg.method || "POST";
    var okFn = cfg.okFn || function(rs) {
      if(rs){
        var msg=Ext.decode(rs.responseText);
        if(!msg){
          alert("操作成功");//??
          return;
        }
        if(msg.ok = "ok"||(!msg.error)){
          alert("操作成功");
        }else{
          alert(msg.error)
        }
      }
    };
    var errorFn = cfg.errorFn || function() {
      alert("操作失败");
    }
    Ext.Ajax.request(
    {
      url: url,
      params:param,
      method:method,
      success:okFn ,
      failure:errorFn
    });
  };

  var createChartPanelCfg=function(chartCfg){
    //panelCfg
  };

  var isFchart=function(type){
    return type.indexOf("f_")==0;
  }
  var isSimple=true;
  var showChartByObj=function(params,pObj,replace){
    if(params.test){
      params.id="cctest"+Ext.id();
    }
    var id=params.id;

    if(hasChart(id)){
      alert("已有此id的图表");
      return;
    }else{
      curCharts[id]=true;
    }
    var param=params;
    var fn = function(rs) {
      if (rs && rs.responseText) {
        var val = rs.responseText.trim();
        if (val) {
          var cCfg=Ext.decode(val);
          var p=null;
          var pType="";
          if(typeof(pObj)=="string"){
            pType="div";
          }else if(typeof(pObj)=="function"){
            pType="fn";
          }else{
            pType="obj";
          }
          if(isFchart(cCfg.chartType)){//f chart
              if(pType=="div"){
                var obj=document.getElementById(pObj);
                var w=obj.offsetWidth;
                var h=obj.offsetHeight;
                if(w<200)w=200;
                if(h<200)h=200;
                cCfg.width=w;
                cCfg.height=h;
              }
            p=JDS.fchart.create.getSimpleChart(cCfg);
          }else{ //ext chart
            if(isSimple){//简单应用,panel属性默认
              if(pType=="div"){
                var obj=document.getElementById(pObj);
                var w=obj.offsetWidth;
                var h=obj.offsetHeight;
                if(w<200)w=200;
                if(h<200)h=200;
                cCfg.width=w;
                cCfg.height=h;
              }
              p=JDS.chart.create.getSimpleChart(cCfg);
            }else{//定制panel属性,目前没用到
              var cfg=createChartPanelCfg(cCfg);
              cfg.panelCfg.seriesShowOrHide=cCfg.seriesShowOrHide;
              p=JDS.chart.create.getChartPanel(cfg);
            }
          }
          
          if(pType=="div"){
            if(replace){
              document.getElementById(pObj).innerHTML="";
            }
            p.render(pObj);
          }else if(pType=="fn"){
            pObj(p);
          }else{
            if(pObj.getInnerWidth){
              var w=pObj.getInnerWidth();
              var h=pObj.getInnerHeight();
              p.setWidth(w);
              p.setHeight(h);
            }
            pObj.add(p);
            pObj.doLayout();
          }
        }
      }
    };
    requestUrl({url:url,param:param,okFn:fn});
  };

  //id为生成的jsp中的div id ,其属性为要生成的报表的参数
  var showChartByDiv=function(id){
    var div=document.getElementById(id);
    if(!div){
      alert("未找到div对象:"+id);
      return;
    }

    var cType=div.custype||"";
    var exp=div.theoremexpression||"";
    exp=exp.replace(/\\'/g,"'");
    var title=div.title||"";
    var cid=id+"_chartP";
    var p={id:cid,
      chartType:cType,
      title:title,
      exp:exp
    }
//    p.test=true;
    showChartByObj(p,id);
  }
  return{
//    hasChart:hasChart,
    showChartByDiv:showChartByDiv,
    showChartByObj:showChartByObj
  }
}();