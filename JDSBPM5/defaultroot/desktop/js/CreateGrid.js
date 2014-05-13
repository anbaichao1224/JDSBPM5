
/*
  grid数据格式
  {
    selfCfg:{
      id:id,
      width:,
      height:
      ...
    }
    metaData://grid中的数据的元数据定义
    {
      hasChockbox:,//列首是否加选择框,
      hasRowNum:,//是否有行数显示
      isSingleSelect:,//是否单选
      dataType:,//得到的grid中数据是以数组还是对象形式组织的 array或json
      cols://各列的元数据信息
      [
        {
          text:,//列头显示值,必须
          name:,//数据的内部名称,列间唯一,必须
          type:,//数据类型,如:float,date等
          dateFormat:,//日期类数据的输入格式
          displayFormat:,//日期类型的显示时的格式
          renderer,//其它类型显示显示前的处理
          isDisplay:,//是否显示,默认true
          width:,//宽度
          sortable是否可排序
        },{
          ...//下一列
        }
      ]
    },
    paging://分页选项,如果有分页,则数据格式必须为json,如没有此项为不分页
    {
      totalProperty:,//data中的总数据量key,必须
      root:,//数据项数据的对应key,必须
      id:,//数据项中每条的唯一标识,可选
      pageSize://每页记录数
    },
    dataUrl:,//如果数据是动态取得的,此为url,其返回结果格式同data,如果有此项,则忽略data项
    data://grid中要显示的数据,如果有url项,则忽略此项,此数据可以是array或json对象类型
  }
  
  data 格式
    array类型:
    [ ["值1","值2"],[["值11","值22"] ]
    json对象类型
    有分页时
    {
      totalProperty:,//总数据量,具体key名字对应metaData中的值
      root://数据数组名,具体key名字对应metaData中的值
      [{
        id:,//如果metaData中设置了id,可选,具体key名字对应metaData中的值
        key1:value1,//此处的各key须和metaData中的cols下的name相对应
        key2:value2
      },{id:,
        key1:value11,
        key2:value22
      }]
    }
    无分页时
    [
      {id:"",key1:value1,key2:value2},
      {id:"",key1:value11,key2:value22}
    ]
*/

/*****例如****

Ext.grid.dummyData = [
    ['   3m Co',71.72,0.02,0.03,'9/1 12:00am', 'Manufacturing'],
    ['Alcoa Inc',29.01,0.42,1.47,'9/1 12:00am', 'Manufacturing'],
    ['Altria Group Inc',83.81,0.28,0.34,'9/1 12:00am', 'Manufacturing']
];

Ext.grid.dummyData2 = {
    count:3,
    rows:[
      {price:71.72,company:'  3m 123Co',change:0.02,pctChange:0.03,lastChange:'9/1 13:00am', industry:'Manufacturing'},
      {company:'Alcoa Inc',price:29.01,change:0.42,pctChange:1.47,lastChange:'9/1 12:00am', industry:'Manufacturing'},
      {company:'Altria Group Inc',price:83.81,change:0.28,pctChange:0.34,lastChange:'9/1 12:00am', industry:'Manufacturing'}
    ]};

var mydata1={
  selfCfg:{
      id:"id111",
      width:400,
      height:300
    }
  metaData:{
    dataType:"array",
    //hasChockbox:true,
    isSingleSelect:true,
    hasRowNum:true,
    cols:[
       {text:"companyaaa",name: 'company',renderer:Ext.util.Format.trim},
       {text:"priceaaa",name: 'price', type: 'float'},
       {text:"change",name: 'change', type: 'float'},
       {text:"pctChangesssss",name: 'pctChange', type: 'float'},
       {text:"lastChangessdd",name: 'lastChange', type: 'date', dateFormat: 'n/j h:ia',displayFormat:"Y-m-d"},
       {text:"industrysds",name: 'industry'},
       {text:"descsas",name: 'desc',isDisplay:"false"}
    ]
  },
  data:Ext.grid.dummyData
};


var mydata={
  metaData:{
    dataType:"json",
    //hasChockbox:true,
    isSingleSelect:true,
    hasRowNum:true,
    paging:{
      totalProperty:"count",
      root:"rows",
      pageSize:20
    },
    cols:[
       {text:"companyaaa",name: 'company',sortable:false},
       {text:"priceaaa",name: 'price', type: 'float'},
       {text:"change",name: 'change', type: 'float'},
       {text:"pctChangesssss",name: 'pctChange', type: 'float'},
       {text:"lastChangessdd",name: 'lastChange', type: 'date', dateFormat: 'n/j h:ia',displayFormat:"Y-m-d"},
       {text:"industrysds",name: 'industry'},
       {text:"descsas",name: 'desc',isDisplay:"false"}
    ]
  },
  //dataUrl:"abc.htm",
  data:Ext.grid.dummyData2
};

**************/

var currGrid;
var popWin={
	getHtmlPanel:function(cfg){return createHtmlPanel(cfg);},
	getIframePanel:function(cfg){return createIframePanel(cfg);},
	getBaseWin:function(cfg){return createBaseWin(cfg);},
	callFn:{}
};
function refGridById(id){
  var obj=Ext.getCmp(id);
  if(obj){
    try{
      obj.getStore().reload();
    }catch (e) {
      //alert(e) ;
    }
  }
};

function refGridAll(){
   
  for (var k=0;k<Ext.ComponentMgr.all.getCount();k++){
    var item=Ext.ComponentMgr.all.itemAt(k);
    if (item.refGrid ){
		if  (!currGrid || item!=currGrid){
			refGridById(item.id);
		}
    
    }
}
}


function getGridCfg(gData){
  var xg = Ext.grid;
  var cols=[]; 
  var mCols=gData.metaData.cols;
  for(var i=0;i<mCols.length;i++){
    var tmpO={};
    for(var a in mCols[i]){
      if(a!="text"&&a!="isDisplay"&&a!="width"&&a!="sortable"){
        tmpO[a]=mCols[i][a];
      }
    }
    cols.push(tmpO);
  }
  
  //reader
  var reader=null;
  if(gData.metaData.dataType=="array"){
    reader = new Ext.data.ArrayReader({},cols);
  }else if(gData.metaData.dataType=="json"){
    var tmpO={};
    if(gData.metaData.paging){
      tmpO.totalProperty=gData.metaData.paging.totalProperty;
      tmpO.root=gData.metaData.paging.root;
      if(gData.metaData.paging.id){
        tmpO.id=gData.metaData.paging.id;
      }
    }
    reader=new Ext.data.JsonReader(tmpO,cols);
  }

  
  //sm
  var sm =null;
  var isSing=gData.metaData.isSingleSelect;
  if(!isSing){
    isSing=false;
  }
  if(gData.metaData.hasChockbox+""=="true"){
    sm=new xg.CheckboxSelectionModel({
        singleSelect:isSing
      }
    );
  }else{
	
    sm=new xg.RowSelectionModel({singleSelect:isSing});
  }

  //store
  var store=null;
  if(gData.dataUrl){
    store=new Ext.data.Store({
        reader:reader,
        proxy: new Ext.data.HttpProxy({
          url: gData.dataUrl
        })
    });
  }else{
    store=new Ext.data.Store({
        reader:reader,
        data:gData.data
      });
  }
 
  //cm
  var cmCfg=[];
  if(gData.metaData.hasRowNum+""=="true"){
    cmCfg.push(new xg.RowNumberer());
  }
  if(sm&& sm instanceof xg.CheckboxSelectionModel){
    cmCfg.push(sm);
  }
  for(var i=0;i<mCols.length;i++){
    var tmpO={};
    tmpO.header=mCols[i].text;
    tmpO.dataIndex=mCols[i].name;
    if(mCols[i].width){
      tmpO.width=mCols[i].width;
    }else{
      tmpO.width=80;
    }
    //tmpO.width = null;

    if(mCols[i].sortable!=undefined){
      tmpO.sortable=mCols[i].sortable;
    }else{
      tmpO.sortable=true;
    }
    if(mCols[i].isDisplay=="false"){
      tmpO.hidden="true";
    }
    if(mCols[i].type=="date"&&mCols[i].displayFormat){
      tmpO.renderer=Ext.util.Format.dateRenderer(mCols[i].displayFormat);
    }else if(mCols[i].renderer){
      tmpO.renderer=mCols[i].renderer;
    }
    tmpO.align=mCols[i].align||"left";
    tmpO.tooltip=mCols[i].tooltip ;
    cmCfg.push(tmpO);
  }
  var cm=new xg.ColumnModel(cmCfg);

  //bbar
  var bbar=null;
  if(gData.metaData.paging){
    var size=gData.metaData.paging.pageSize;
    bbar= new Ext.PagingToolbar({
            pageSize: size,
            store: store,
            displayInfo: true,
            displayMsg: '共 {2} 条 当前列表中显示 {0} - {1} 条',
            emptyMsg: "没有工作"

      ,y:-27
        })
  };
  var id=gData.id||Ext.id("","-gridpanel");
  
  var c={
      store:store,
      cm:cm,
      sm:sm,
      bbar:bbar
    };
  c=Ext.apply(c,gData.selfCfg,
         {width:600,
          height:300,
          frame:true,
          enableColumnHide :false,
        //  title:'grid panel',
          iconCls:'icon-grid'

        });
		
		
  return c;
}



//根据给定数据生成grid panel
function createGridByData(gData,noResizeWidth){
  var c=getGridCfg(gData);
  var grid=new Ext.grid.GridPanel(c);
    if (gData.refGrid==true ||gData.refGrid=='true'){
    currGrid=grid;
    grid.refGrid=true;
  } 
  if(c.bbar){
    try{
      c.store.load({params:{start:0, limit:gData.metaData.paging.pageSize}});
    }catch(e){}
  }
    grid.shadow=true;
	grid.shadowOffset =10;
    grid.on("rowclick",function (currgrid,index,event){
      var rowdblclickstr=currgrid.getStore().getAt(index).get('rowdblclick'); 
       if (rowdblclickstr){
        eval(rowdblclickstr); 
        }
    });

  grid.on("render",function(){
    var bb = grid.getBottomToolbar();
    var dH=0;
    var dW=7;
    if (bb) {
      dH=bb.getSize().height;
    }
    var win=grid.findParentByType("window");
    if (win) {
      win.gridResizeFn = function(obj, w, h) {
        grid.setSize(w-dW,h-dH);
      }
      grid.setSize(win.getSize().width-dW, win.getSize().height-dH);
    }
  });

  grid.on("resize", function (g,aw,ah,rw,rh) {
    if(!noResizeWidth){
      var cm = g.getColumnModel();
      var tw = cm.getTotalWidth();
      var count = cm.getColumnCount();
      var t=0;
      if(count>0){
        var n=0;
        for(var i=0 ;i<count;i++){
          if(!cm.isHidden(i)){
            n++;
            t+=cm.getColumnWidth(i)
          }
        }
        var r=(rw-80)/t;    //80为预留常数

        for (var i = 2; i < count-1; i++) {
          if(!cm.isHidden(i)){
          var id = cm.getColumnId(i);
          var tmpW=cm.getColumnWidth(i);
          cm.setColumnWidth(i,tmpW*r);
          }
        }
      }
    }

    var bb = grid.getBottomToolbar();
    if (bb) {
      bb.setPosition(0, 0);
      bb.hide();
      bb.show();
    }
  });
  return grid;
}

function createEditGridByData(gData){
  var c=getGridCfg(gData);  
  var cm=c.cm;
  for(var i=0;i<cm.length;i++){
    var m=cm[i];
    if(m.header){
      var e=null;
      if(m.type){
        if(m.type=="select"){
          e=new Ext.form.ComboBox({
               typeAhead: true,
               triggerAction: 'all',
               transform:e.dataIndex,
               lazyRender:true,
               listClass: 'x-combo-list-small'
            });
        }else if(m.type=="date"){
          e=new fm.DateField({
                format: m.format||'Y-m-d'

            });
        }
        
      }
      if(e==null){
        e=new Ext.form.TextField();
      }
      m.editor=e;
    }
  }
  c.cm=cm;
  c=Ext.apply(c,{
                clicksToEdit:1
              });
  var grid=new Ext.grid.GridPanel(c);
  if(c.bbar){
    try{
      c.store.load({params:{start:0, limit:gData.metaData.paging.pageSize}});
    }catch(e){}
  }
  return grid;
}

function createGridPanel(cfg){
  return createGridByData(cfg);
}
  

//生成一个带iframe的panel,iframe的src为参数的url
function createIframePanel(cfg){
  var c=Ext.apply({},cfg,{
                  title:"iframePanel",
                  width:830,
                  height:400});
  delete c.url;
  var frameId="pIframe"+Ext.id();
 
  c.html='<div style="width:100%;height:100%"><iframe id="'+frameId+'" src="'+cfg.url+'" style="width:100%;height:100%"></iframe>';
  var p=new Ext.Panel(c);
  p.frameId=frameId;
  return p;
}

function createHtmlPanel(cfg){
  var c=Ext.apply({},cfg,{
                  title:"htmlPanel",
                  width:830,
                  height:500});
  var p=new Ext.Panel(c);

  return p;
}

function createTabPanel(cfg){
 var p = new Ext.TabPanel({
  renderTo: document.body,
        activeTab: 0,
        width:830,
        id:'formtab',
        height:550,
        plain:true,   
        defaults:{autoScroll: true},
        items:cfg
        
 });
  return p;
}


function createBaseWin(cfg){
  var winParam=Ext.apply({},cfg,{
        title: '窗口',
        closable:true,
        autoScroll:true,
        maximizable:true,
        minimizable:true,
        width:600,
        height:450,
        plain:true,
        items: [{}]
      
  	});
  return new Ext.Window(winParam);
  }
  
  
 /**
  * 
  * @param {Object} cfg
  * cfg={
  * id:'test',（必选）
  * url:'demo.action',（必选）
  * fields:['id', 'name', 'pathtothumbnail', 'pathtofile'],（可选）
  * typ:new Ext.XTemplate()(可选)
  * title:（必选）
  * 
  * }
  */ 
  
 function creatDataView(cfg){

	var store = new Ext.data.JsonStore({
	    url: cfg.url,
	    root: 'images',
	   	fields: cfg.fields?cfg.fields:['id', 'name', 'pathtothumbnail', 'pathtofile']
	});
	store.load();

	var defalutTpl = new Ext.XTemplate(
			'<tpl for=".">',
				'<div class="pref-view-thumb-wrap" id="theme-{id}">',
					'<div class="pref-view-thumb"><img src="{pathtothumbnail}" title="{name}" /></div>',
				'<span>{shortName}</span></div>',
			'</tpl>',
			'<div class="x-clear"></div>'
		);
	
	
	var view = new Ext.DataView({
			autoHeight:true,
			cls: 'pref-thumnail-view',
			emptyText: '没有',
			itemSelector:'div.pref-view-thumb-wrap',
			loadingText: '正在读取。。。',
			singleSelect: true,
			overClass:'x-view-over',
			prepareData: function(data){
				data.shortName = Ext.util.Format.ellipsis(data.name, 17);
				return data;
			},
			store: store,
			tpl: cfg.tpl?cfg.tpl:defalutTpl
		});
	view.on('selectionchange', onSelectionChange, this);
	
	
	function onSelectionChange(view, sel){		
		var	r = view.getRecord(sel[0]),
		d = r.data;
		alert(d.id);
			newProcess(d.id);
	}
	
	var defaults = new Ext.Panel({
		animCollapse: true,
		baseCls:'collapse-group',
		cls:'pref-thumbnail-viewer',
		border: false,
		collapsible: true,
		hideCollapseTool: false,
		id: cfg.id+'pref-theme-view',
		items: view,
		title: cfg.title,
		titleCollapse: true
	});
	
		var themes = new Ext.Panel({
		autoScroll: true,
		bodyStyle: 'padding:10px',
		border: true,
		cls: 'pref-card-subpanel',
		id: cfg.id+'themes',
		items: defaults,
		margins: '10 15 0 15',
		region: 'center'
	});
	


	var panel = new Ext.Panel({
	        autoScroll: true,
			bodyStyle: 'padding:10px',
			border: true,
			cls: 'pref-card',
			id: cfg.id,
			margins: '10 15 0 15',
			region: 'center',
	    items: [
				themes
			]
	});

	return panel;
}	  
  
  function evalJsOncomplete(){
  		EVAL.complete();
		
  }
  
  //获取指定window的配置,参数为windows的id,如test-win
function getWinCfg(winId) {
	 var jdsMenu=new JDSMenu();
	 var winStr=winId.substring(0,winId.length-4);
	 var obj;
	 
	if (eval('jdsMenu.'+winStr).winInnerCfg){
	   obj=eval('jdsMenu.'+winStr+'.winInnerCfg');
	   }	
	 
	   if (obj.url){
	    Ext.namespace("EVAL");
	   	functionName = "evalJs";	
        var myAjax = getMyAjax('routelog', context+obj.url,'winId='+winId);
		return obj;
	   }else{
	   	if (obj && obj.items && obj.type){
	     var item=obj.items[0];
	     var _item;
	
	      if(obj.type=='grid'){
	        _item=eval("createGridByData(item)");
	      }else if(obj.type=='iframe'){
	        _item=eval("createIframePanel(item)")
	      }else if(obj.type=='htmlpanel'){    
	         _item=eval("createHtmlPanel(item)")  
	      }else if(obj.type=='DataView'){
	         _item=eval("creatDataView(item)");
	      }else if('panel'){
	         _item=item;
	      }
	       obj.items[0]=_item;
	    }else{
	       obj={};
	    }
		
	   }
	  return obj;

}






