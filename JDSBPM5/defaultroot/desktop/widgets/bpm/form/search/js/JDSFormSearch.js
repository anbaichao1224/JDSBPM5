
	
JDS.bpm.Form.SearchAction= {
	search:function (activityInstId) {
		var winId=JDS.util.String.replaceAll(activityInstId,'-','');
		var evalJs = function(o){
			Ext.namespace("EVAL");
			eval(o);
		}
		$(winId+'BPM_UpdateType').value='search';
		var str="";			
		var winId=JDS.util.String.replaceAll(activityInstId,'-','');
		
	  try{		
		//循环个表单值组合为STRING
		for(var i=0;formTab.items.length>i;i++ )	{
		var jdsForm= formTab.getComponent(i).form;
			try{	
				 if (!jdsForm.extForm.isValid() ){
				 	   jdsForm.extForm.items.each(function(f){
				           if(!f.validate()){
				             f.markInvalid();
				           }
			            });
					// warningMsg('校验失败请检查表单['+formTab.getComponent(i).title+']');
					return ;
				}else{
					 str=jdsForm.extForm.getValues(true)+"&"+str;
				}
			}catch(e){	
			      JDS.Msg.warningMsg('校验失败请检查表单['+formTab.getComponent(i).title+']');
				  return false;
			}	
		  }	  	
		}catch(e){		
		   Ext.MessageBox.hide();		   
		   alert('表单数据序列化错误，请检查表单定义是否正确！');
		  return false;
		}
		
		str=str+"&startTime="+Ext.get('startTime').dom.value;
		str=str+"&endTime="+Ext.get('endTime').dom.value;
		str=str+"&fileTile="+Ext.get('fileTile').dom.value;

	var store = new Ext.data.Store({
		url: context+'searchupdate.action?'+str ,
		remoteSort:true,
		autoLoad:true,
        reader: new Ext.data.JsonReader({
            root: 'data',
            totalProperty: 'totalCount',
            id: 'processid',
            fields: [
            {name: 'processid', type: 'string'},
            {name: 'fileTile', type: 'string'},
            {name: 'priprocessDefNamece',type: 'string'},
            {name: 'startTime',type: 'string'},
			{name: 'endTime',type: 'string'},
			{name: 'lastActivityInstId',type: 'string'}
             ]
        })
    });
		
    store.setDefaultSort('processid', 'desc');

	store.on("beforeload", beforeload);
	
	
	function beforeload(store, options){
			store.proxy.getConnection().extraParams=Ext.urlDecode(str);
		}
    var sm = new Ext.grid.CheckboxSelectionModel(); // add checkbox column
    
    var cm = new Ext.grid.ColumnModel([
	   new Ext.grid.RowNumberer(),
	   {
           id: 'processid', // id assigned so we can apply custom css (e.g. .x-grid-col-topic b { color:#333 })
           header: "编号",
           dataIndex: 'processid',
           align:'center',
           hidden:true,
           width: 60
        },{
           header: "公文标题",
           dataIndex: 'fileTile',
           align:'center',
           width: 200
        },{
           header: "流程名称",
           dataIndex: 'priprocessDefNamece',
           align:'center',
           width: 100
        },{
           header: "开始时间",
           dataIndex: 'startTime',
           align:'center',
           width: 60
        },{
           header: "结束时间",
           dataIndex: 'endTime',
           align:'center',
           width: 60
        }
        ]);
     cm.defaultSortable = true;
     
     var grid=new Ext.grid.GridPanel({
		     	id:'resgrid',
		        width:710,
		        height:400,
		        store: store,
		        cm: cm,
		        sm: sm,
		        trackMouseOver:false,
		        loadMask: true,
		        autoShow : true,
		        viewConfig: {
		            forceFit:true,
		            enableRowBody:true,
		            showPreview:true
		        },
		        bbar: new Ext.PagingToolbar({
		            pageSize: 20,
		            store: store,
		            displayInfo: true,
		            displayMsg: '当前显示{0} - {1}  条记录 /共 {2}条记录',
		            emptyMsg: "无显示数据"
		        })
		        
		        });
       grid.on("cellclick",function cellclick(grid, rowIndex, columnIndex, e) {
       	var record = grid.getStore().getAt(rowIndex);   //Get the Record
		    var id = record.get('lastActivityInstId');
	  		openWin(context+"demodisplay.action?activityInstId="+id);
			}
		);
	
		var defaultCfg = {
			        title: "综合查询",
			        width: 740,
			        height: 480,
			        shim: true,
			        animCollapse: false,
			        border: false,
			        constrainHeader: true,
			        layout: 'fit',
					items:[grid]
			    };
    		
			var dispwin ;
			try{
				 dispwin = getCurDesktop().createWindow(defaultCfg);
			}catch(e){
				dispwin = new Ext.Window(defaultCfg);
			}
		
			dispwin.show();	
	}

}
	
	