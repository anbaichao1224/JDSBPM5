/*
 * Ext JS Library 2.0.1
 * Copyright(c) 2006-2008, Ext JS, LLC.
 * licensing@extjs.com
 * 
 * http://extjs.com/license
 */


Ext.onReady(function(){

    Ext.QuickTips.init();
    
    var xg = Ext.grid;

    // shared reader
     var reader = new Ext.data.ArrayReader({}, [
       {name: 'orgid'},
       {name: 'cnname'},
       {name: 'enname'},
       {name: 'status'},
       {name: 'orgtype'},
       {name: 'intel'},
       {name: 'desc'}
    ]);
    

    
    
   var store = new Ext.data.Store({
        // load using script tags for cross domain, if the data in on the same domain as
        // this page, an HttpProxy would be better
        proxy: new Ext.data.ScriptTagProxy({
            url: 'http://extjs.com/forum/topics-browse-remote.php'
        }),

        // create reader that reads the Topic records
         reader: new Ext.data.JsonReader
        ({
            root: 'data',
            totalProperty: 'totalCount'
       
        }, [
            {name:'ORGID', mapping: 'ORGID',type:'string'},
            {name:'CNNAME', mapping: 'CNNAME',type:'string'},
            {name:'ENNAME', mapping: 'ENNAME',type:'string'},
            {name:'ORGLEVEL', mapping: 'ORGLEVEL', type:'string'},
            {name:'MEMO', mapping: 'MEMO',type:'string'},
            {name:'PARENTORGID', mapping: 'PARENTORGID',type:'string'},
            {name:'STATUS', mapping: 'STATUS',type:'string'},
            {name:'ORGTYPE', mapping: 'ORGTYPE',type:'string'},
            {name:'INTEL', mapping: 'INTEL',type:'string'},
            {name:'OUTTEL', mapping: 'OUTTEL',type:'string'},
            {name:'NIGHTTEL', mapping: 'NIGHTTEL',type:'string'},
            {name:'FAX', mapping: 'FAX',type:'string'},
            {name:'SERIALINDEX', mapping: 'SERIALINDEX',type:'string'}   
        ]),
        sortInfo:{field: 'CNNAME', direction: "ASC"},
      //  groupField:'BookSeries'

        // turn on remote sorting
        remoteSort: true
    });
    
    
        /*
    * ================  Simple form  =======================
    */
     var simple = new Ext.FormPanel({
       labelWidth: 75, // label settings here cascade unless overridden
       url:'save-form.php',
       frame:true,
       title: '查询条件',
       bodyStyle:'padding:5px 5px 0',
       width:1100,
       height:80,
        layout:'table',
       items: [new Ext.form.Radio
       ({   
                       labelSeparator:'',   
                       name:'radio',
                       inputValue:'r1', 
                       hiddenName:'CNNAME',  
                       checked:true,   //为true 被选中，false 不被选中   
                       boxLabel:'按部门名称：'  
                   }),
                    new   Ext.form.TextField({ 
                       width:175, 
                         name: 'CNNAME',
                         allowBlank:true
              
               }),
                    new Ext.form.Radio({   
                       labelSeparator:'',   
                       name:'radio',
                       inputValue:'r2',
                       hiddenName:'ENNAME',    
                       checked:false,   //为true 被选中，false 不被选中   
                       boxLabel:'按英文名称：'  
                   }),
                    new   Ext.form.TextField({ 
                       name:   'ENNAME ', 
                       width:175, 
                       allowBlank:true  
               }), 
                   new Ext.form.Radio({   
                       labelSeparator:'',   
                       name:'radio',
                       inputValue:'r3',
                       hiddenName:'ORGTYPE',    
                       checked:false,   
                       boxLabel:'按部门类型：'  
                   }),
                     new   Ext.form.TextField({ 
                       fieldLabel:   'years ', 
                       name:   'first ', 
                       width:175, 
                       allowBlank:true 
               }),
               new Ext.Button({
                   id:'btnSave',
                   text:'查 询',
                   handler:doSave,
                  disabled:false
               })
       ]
   });

   simple.render(document.body);
   
   function doSave()
  {
      simple.getForm().submit
      ({
      	url:'../webpage/ProjectList.aspx',    
      	method:'POST'
      });
      store.load();      
      Ext.MessageBox.hide();
      Ext.MessageBox.alert('提示', '成功！');
   }

      //配置视图信息
      var view=new Ext.grid.GridView({forceFit:true,sortAscText :'正序', sortDescText :'倒序'});
      view.columnsText='列显示/隐藏';
      






    var sm = new xg.CheckboxSelectionModel();
    ////////////////////////////////////////////////////////////////////////////////////////
    // Grid 1
    ////////////////////////////////////////////////////////////////////////////////////////
  
    var persongrid = new xg.GridPanel({
        id:'button-grid',
        view:view,
        store: new Ext.data.Store({
            reader: reader,
            data: xg.dummyData,
            width:600
        }),
       cm: new xg.ColumnModel([
            new Ext.grid.RowNumberer(),//自动行号
            sm,//添加的地方
           {header: "部门ID", width: 70, sortable: true, dataIndex: 'orgid'},
           {header: "部门名称", width: 70, sortable: true, dataIndex: 'cnname'},
           {header: "英文名称", width: 70, sortable: true, dataIndex: 'enname'},       
           {header: "状态", width: 20, sortable: true, dataIndex: 'status'},
           {header: "部门类型", width: 20, sortable: true, dataIndex: 'orgtype'},
           {header: "内线电话", width: 20, sortable: true, dataIndex: 'intel'}        
        ]),
           
        viewConfig: {
            forceFit:true
        },
        buttonAlign:'center',

        // inline toolbars
        tbar:[{
            text:'新增',
            tooltip:'新增',
            iconCls:'add',
            handler:function(){
           // window.location="departaddwindow.jsp"; 
            var w=new Ext.Window({
	            title:"添加岗位基本信息",
	            width:750,
	            height:315,
	            maximizable:true,
			  	closeAction:'close',
			  	closable:true,
			  	collapsible:true,
			  //	plain: true,
			  //	buttonAlign:'center',
			  	autoScroll:true,
			  	items:new Ext.Panel({
			        title: '',        
				  	height:250,
			        width:720,
			        html: '<iframe scrolling="yes" frameborder="0" src="/usm/positionadd.jsp" height=100% width=100%></iframe>'
			    })
	        	});
	         w.show();
           
            }
        }, '-', {
            text:'修改',
            tooltip:'修改',
            iconCls:'option',
            handler:function(){
            window.location="departmodify.jsp"; 
            }
            
        },'-',{
            text:'删除',
            tooltip:'删除',
            iconCls:'remove',
            handler:function(){
            alert('删除成功');
            }
        }],
         bbar: new Ext.PagingToolbar({
            pageSize: 2,
            store: store,
            displayInfo: true,
            displayMsg: '当前显示 {0} - {1}条记录 /共 {2}条记录',
            emptyMsg: "无记录"
        }),
        sm:sm,
        
       
        width:1100,
        height:300,
        frame:true,
        title:'部门基本信息列表',
        iconCls:'icon-grid',
        renderTo: document.body
    });
    

    
    xg.addListener('cellclick', cellclick);
    function cellclick(grid, rowIndex, columnIndex, e) {
     
    };
     
    function toggleDetails(btn, pressed){
        var view = persongrid.getView();
        view.showPreview = pressed;
        view.refresh();
    } 
});





// Array data for the grids
Ext.grid.dummyData = [
    ['陈列','男','010-6443324','1324599595','dfd@163.com','7/21 12:00am'],
    ['陈列','男','010-6443324','1324599595','dfd@163.com','7/21 12:00am'],
    ['陈列','男','010-6443324','1324599595','dfd@163.com','7/21 12:00am']
];


