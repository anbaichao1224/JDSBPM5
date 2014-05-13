<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2009-11-19
  Time: 11:36:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head><title></title>

    <link rel="stylesheet" type="text/css" href="/js/ext/resources/css/ext-all.css" />

    <script type="text/javascript" src="/js/ext/adapter/ext/ext-base.js"></script>
    <script type="text/javascript" src="/js/ext/ext-all-debug.js"></script>


    <!--<script type="text/javascript" src="/desktop/widgets/chart/js/chart/ext-base-debug.js"></script>-->
    <!--<script type="text/javascript" src="/desktop/widgets/chart/js/chart/ext-all-debug.js"></script>-->

    <script type="text/javascript" src="/js/ext/locale/ext-lang-zh_CN.js"></script>

    <!--<script type="text/javascript" src="/chart/js/chart/pack.js"></script>-->
    <!---->
    <!--<script type="text/javascript" src="/chart/js/chart/EventProxy.js"></script>-->
    <!--<script type="text/javascript" src="/chart/js/chart/swfobject.js"></script>-->
    <!--<script type="text/javascript" src="/chart/js/chart/FlashComponent.js"></script>-->
    <!--<script type="text/javascript" src="/chart/js/chart/Chart.js"></script>-->

    <!--<script type="text/javascript" src="/chart/js/mychart.js"></script>-->
    <script type="text/javascript">
      var context="/";
    </script>
    <script type="text/javascript" src="/desktop/widgets/chart/js/chartAll.js"></script>


    <script type="text/javascript" src="/desktop/widgets/chart/js/testchart.js"></script>

    <!--<script type="text/javascript" src="/desktop/widgets/chart/js/showChart.js"></script>-->

<script type="text/javascript">
   function extInit() {
     Ext.BLANK_IMAGE_URL = '/js/ext/resources/images/default/s.gif';
     Ext.form.Field.prototype.msgTarget = 'side';
     Ext.QuickTips.init();
   }
  Ext.onReady(extInit);
</script>
    <script type="text/javascript">
      function test(){
    //柱状图
    var cfg={
      panelCfg:{height:600,width:800,seriesShowOrHide:true},
      chartCfg:{
        chartType:"columnchart",
        keyName:"name",
        texts:["测试1","测试2"],
        valuesName:["val1","val2"],
//        data:testData
        url:'/desktop/widgets/chart/data.jsp'
        ,params:{param:'aabbc'}
      }
    };


      var panel = null;
        var i=1;
      if (cfg.chartCfg) {
            panel= JDS.chart.create.getChartPanel(cfg);
      } else {
        panel = JDS.chart.create.getSimpleChart(cfg);
      }
      var win = new Ext.Window({
//        id:"chartWin"+i,
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
      }

      /////////////////-----------------------------
      function createPanelWin(panel) {
        var win = new Ext.Window({
          title:"测试窗口",
          width:820,
          height:650,
          items:[
            panel
          ]
        });
        win.panel = panel;
        win.on("resize",function(obj,w,h){
//          panel.setSize(w-10,h-30);
          panel.setSize(obj.getInnerWidth(),obj.getInnerHeight());
        })
        win.show();
      }

      var data=[{"name":"1月","val3":"130","val4":"140","val1":"110","val2":"120"},
        {"name":"2月","val3":"230","val4":"240","val1":"210","val2":"220"},
        {"name":"3月","val3":"330","val4":"340","val1":"310","val2":"320"},
        {"name":"4月","val3":"430","val4":"440","val1":"410","val2":"420"}
      ];

      function test2(){
        var cfg={
          width:800,
          height:600,
          keyName:"name",
//        texts:["统计1","显示2","显示3","数据啊"],
//        valuesName:["val1","val2","val3","val4"],

          
        texts:["统计1","显示2"],
        valuesName:["val1","val2"],
        url:'/desktop/widgets/chart/data.jsp'
//          ,data:data
        };

cfg.yAxis="a1",
    cfg.xAxis="a2";
        cfg.seriesShowOrHide=true;

        cfg={"texts":["公司1","公司2"],"id":"id1266981286258","chartType":"linechart","keyName":"ckeyname","textPosition":"bottom","valuesName":["cvalpre_0","cvalpre_1"],"data":[{"ckeyname":"1月","cvalpre_0":"4958","cvalpre_1":"7226"},{"ckeyname":"2月","cvalpre_0":"5757","cvalpre_1":"6975"}],"seriesShowOrHide":false}

//        var panel = JDS.chart.create.getLineChart(cfg);
//        createPanelWin(panel);
//        var panel2 = JDS.chart.create.getColumnChart(cfg);
//        var panel2 = JDS.chart.create.getPiechart(cfg);
//        var panel2 = JDS.chart.create.getBarChart(cfg);

        var panel2=JDS.chart.create.getSimpleChart(cfg);
        panel2.chart.on("itemclick",function(e){
          alert(e.index+"=="+e.seriesIndex)
        });

        window.curobj=panel2;
        createPanelWin(panel2);



//        var cfg6={
//      panelCfg:{height:600,width:800},
//      chartCfg:{
//        chartType:"stackedcolumnchart",
//        keyName:"ckeyname",
//        texts:["测试1","测试2"],
//"seriesStyle":[{"color":"#FFFF00"},{"color":"#FFCC00"},{"color":"#FF9900"},{"color":"#FF6600"},{"color":"#FF3300"},{"color":"#FF0000"}],
//          "valuesName":["cvalpre_0","cvalpre_1"],
//          "data":[{"ckeyname":"1月","cvalpre_0":"100","cvalpre_1":"200"},
//                    {"ckeyname":"2月","cvalpre_0":"200","cvalpre_1":"100"},{"ckeyname":"3月","cvalpre_0":"300","cvalpre_1":"400"}]
//,"yAxis":"数据"
//        ,"seriesShowOrHide":true,"xAxis":"月份"
//      }
//    };
//        var panel= JDS.chart.create.getChartPanel(cfg6);
//        createPanelWin(panel)
      }
      function test3(){
        var id="abc"+new Date().getTime();


//          var win = new Ext.Window({
//          title:"测试窗口",
//          width:400,
//          height:300,
//          resizable :false
//        });
//        win.show();
        //        JDS.showChart.showChartById(id,win);

        var div=document.createElement("div");
        div.id = "div_"+id;
        div.style.width=400;
        div.style.height=300;
//        document.body.appendChild(div);
        div.style.styleFloat="left";
        div.style.styleFloat="left";
        document.getElementById("div1").appendChild(div)
        JDS.showChart.showChartByObj({test:true},div.id);

      }
    </script>
  </head>
  <body>
  <input type="button" value="ksfjdslkfj" onclick="test3()">
<div id="div1" style=""></div>
  </body>
</html>