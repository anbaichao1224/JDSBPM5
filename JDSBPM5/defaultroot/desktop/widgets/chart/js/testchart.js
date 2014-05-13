//测试


Ext.onReady(function(){
  return;
  setTimeout(function(){
//    return;

    var testData=[
            {name:'测试 07', val3: 245000, val1: 3000000,val2:3500000},
            {name:'Aug 07', val3: 240000, val1: 3500000,val2:3800000},
            {name:'Sep 07', val3: 355000, val1: 4000000,val2:3500000},
            {name:'Oct 07', val3: 375000, val1: 4200000,val2:3200000},
            {name:'Nov 07', val3: 490000, val1: 4500000,val2:3500000},
            {name:'Dec 07', val3: 495000, val1: 5800000,val2:3700000},
            {name:'Jan 08', val3: 520000, val1: 6000000,val2:3100000},
            {name:'Feb 08', val3: 620000, val1: 7500000,val2:3900000}
        ];

    var dataUrl='/desktop/widgets/chart/data.jsp';
    // 折线图:
    var cfg1={
      panelCfg:{height:600,width:800},
      chartCfg:{
        chartType:"linechart",
        keyName:"name",
        texts:["测试1","测试2","测试3"],
        valuesName:["val1","val2","val3"],
//        data:testData
        url:dataUrl
      }
    };

    //横条图
    var cfg2={
      panelCfg:{height:600,width:800},
      chartCfg:{
        chartType:"barchart",
        keyName:"name",
        texts:["测试1","测试2"],
        valuesName:["val1","val2"],
//        data:testData
        url:dataUrl


        ,seriesStyle: [  //数组,和series的数量对应
          {
//            visibility:"hidden",
            color:0xff0000,
            size:8
          },{
            size:8
          }
        ]
      }
    };

    //柱状图
    var cfg3={
      panelCfg:{height:600,width:800},
      chartCfg:{
        chartType:"columnchart",
        keyName:"name",
        texts:["测试1","测试2"],
        valuesName:["val1","val2"],
//        data:testData
        url:dataUrl
      }
    };

    //饼形图
    var cfg4={
      panelCfg:{height:600,width:800},
      chartCfg:{
        chartType:"piechart",
        keyName:"name",
        texts:["数据1","数据2","数据3"],
        valuesName:["val1","val2","val3"],
//        data:testData
        url:dataUrl



        ,textPosition:"right",
        seriesStyle:[
          {colors:['#FFFF00','#FFCC00','#FF9900','#FF6600','#FF3300','#FF0000','#660000','#663300',"#666600","#669900"]},
          {colors:['#00FFFF','#00CCFF','#0099FF','#0066FF','#0033FF','#0000FF','#000066','#003366',"#006666","#009966"]}
        ]
      }
    };

    //堆叠条形图
    var cfg5={
      panelCfg:{height:600,width:800},
      chartCfg:{
        chartType:"stackedbarchart",
        keyName:"name",
        texts:["测试1","测试2"],
        valuesName:["val1","val2"],
//        data:testData
        url:dataUrl
      }
    };

    //堆叠柱状图
    var cfg6={
      panelCfg:{height:600,width:800},
      chartCfg:{
        chartType:"stackedcolumnchart",
        keyName:"name",
        texts:["测试1","测试2"],
        valuesName:["val1","val2"],
//        data:testData
        url:dataUrl
      }
    };

    //柱状图和折线图混合
    var cfg7={
      panelCfg:{height:600,width:800},
      chartCfg:{
        chartType:"columnchart",//或 "linechart"
        series:["line","column"],
        keyName:"name",
        texts:["测试1","测试2","测试3"],
        valuesName:["val1","val2","val3"],
//        data:testData
        url:dataUrl
      }
    };

    /*
    可以加图例显示:
    cfg.chartCfg.textPosition="right"或 "left","top","bottom"

    多图时可以设置每个图的颜色大小等,如:
    cfg.chartCfg.seriesStyle=[  //数组,和series的数量对应
      {
        color:0xff0000,
        size:8
      },{
        size:8
      }
    ];

    整个chart的风格:比如横轴的字 竖着显示:
    cfg.chartCfg.chartStyle={
        xAxis:  {
	        labelRotation:-90
        }
      }
*/

    var tmp={
        chartType:"linechart",
        keyName:"name",
        texts:["测试1","测试2","测试3"],
        valuesName:["val1","val2","val3"],
//        data:testData
        url:dataUrl
    };
//    var cfgs=[cfg1,cfg2,cfg3,cfg4,cfg5,cfg6,cfg7];
    var cfgs = [cfg1,cfg4];
//    var cfgs = [cfg2,cfg1];
    for (var i = 1; i <= cfgs.length; i++) {
      var cfg = cfgs[i - 1];

      var panel = null;
      if (cfg.chartCfg) {
            panel= JDS.chart.create.getChartPanel(cfg);
      } else {
        panel = JDS.chart.create.getSimpleChart(cfg);
      }
      var win = new Ext.Window({
        id:"chartWin"+i,
        x:300 + i * 20,
        y:50 + i * 20,
        title:"测试窗口" + i,
        width:820,
        height:650,
        items:[
          panel
        ]
      });
      win.panel=panel;
      win.show();
//      alert(panel.chart.swf.setSeriesStylesByIndex)
    }
},1);
});

//
//cfg.yAxis=new Ext.chart.NumericAxis({title: '哈哈'});
//cfg.xAxis=new Ext.chart.CategoryAxis({title: '哈哈2'});
//        cfg.chartStyle={
//            yAxis:
//            {
//                titleRotation:-90
//            }
//        }