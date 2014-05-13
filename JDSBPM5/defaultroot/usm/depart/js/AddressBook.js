
Ext.onReady(function(){
    var newFormWin;
    var currentRowNumber;
    Ext.QuickTips.init();
    var xg = Ext.grid;
    var store = new Ext.data.Store({
        proxy: new Ext.data.HttpProxy({   
            url: '/getAllPersonInfo.do'   //从此页获取数据
        }),
         reader: new Ext.data.JsonReader({
            root: 'data',
            totalProperty: 'totalCount',
            pageSize:60,
            fields: [
            {name:'bumen',type:'string'},{name:'room',type:'string'},{name:'personid',type:'string'},{name:'name',type:'string'},{name:'officetel',type:'string'},{name:'mobile',type:'string'}
          ]
        })
    });
    store.load( {params:{start:0, limit:120}});
    //store.load();
    var sm = new Ext.grid.CheckboxSelectionModel(); // add checkbox column
    var cm = new Ext.grid.ColumnModel([sm,
     new Ext.grid.RowNumberer(
	     {
		     header:'序号',
		     width:32,
		     align:'center'
	     }
        )
        ,{header:'部门',dataIndex:'bumen',width:60}
        ,{header:'房间',dataIndex:'room',width:40}
        ,{header:'人员编号',dataIndex:'personid',hidden:true,width:20},
        	//renderer:
        	//function(value,metadata,record,rowIndex){
        	//	return idClike(value);
        	//},width:20},
        {
        header:'名字',dataIndex:'name',width:60
        ,renderer:
        function(value){
        		return "<span  onmouseover=\"this.style.cursor='hand'\"  style='color:blue;' >"+value+"</span>";
        	}}
		,{header:'办公电话',dataIndex:'officetel',width:60}
		,{header:'手机',dataIndex:'mobile',width:60}
        
        ]);
       
        cm.defaultSortable = false;
         //配置视图信息
		var view=new Ext.grid.GridView(
			{
			forceFit:true,
			sortAscText :'正序', 
			sortDescText :'倒序'});
		view.columnsText='列显示/隐藏';
   

       var name = new Ext.form.TextField({   
                width:175,
                id:'name',
                name: 'name',
                allowBlank:true 
            }); 
           
      var departgrid = new Ext.grid.GridPanel({
        //id:'button-grid',
        width:300,
        store:store,
	    cm:cm,
	    sm:sm,
	    //view:view,  
	    loadMask: true,//装载动画	       
        viewConfig: {
            forceFit:true,
            enableRowBody:true,
            showPreview:true
        },
        // inline toolbars
        /*
        tbar:['按部门名称：',name, {// 查询按钮   
                    id : 'newModelButton',    
                    icon : "/usm/usm/img/search.jpg",   
                    cls : "x-btn-text-icon",   
                    handler :querydepartMessage    
            
        }],
        */
         bbar: new Ext.PagingToolbar({
            pageSize: 60,
            store: store,
            cls:'x-btn-text-icon details',
            displayInfo: true,
            displayMsg: '当前显示 {0} - {1}条记录 /共 {2}条记录',
            emptyMsg: "当前没有可以显示的数据"
            
        }), 
        width:780,
        height:550,
        //frame:true,
        title:'所有信息',
        //iconCls:'icon-grid',
        renderTo: document.body
    });
    
    departgrid.render();
    departgrid.getView().refresh();
    //el:指定html元素用于显示grid
  

    departgrid.on("rowdblclick", function(departgrid) {
    	var _record = departgrid.getSelectionModel().getSelected();
    	//alert(_record.get('personid'));
    	var id=_record.get('personid');
    	openPersonDetail(id);
    });
         function querydepartMessage()
         {
	
	         var name = Ext.get('name').dom.value;
    	     store.load({params:{start:0, limit:12,name:name}});
	        
          }
          function click(value){
          	//alert(value);
          	var _record = departgrid.getSelectionModel().getSelected();
    		var id=_record.get('personid');
    		openPersonDetail(id);
          }
          
      function openPersonDetail(personid)
      {
          var win = new Ext.Window( {
                layout : 'fit',
                width : 700,
                height : 300,
                maximizable:true,
                autoScroll:true,
                plain : true,
                title : '人员详细信息',
                items:new Ext.Panel({
			        title: '',        
				  	height:300,
			        width:700,
			        html: '<iframe scrolling="yes" frameborder="0" src="/getPersonDetail.do?personid='+personid+'" height=100% width=100%></iframe>'
			    })
            });
      
        win.show();
      }
    
});

