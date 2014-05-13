<%@ page contentType="text/html; charset=GBK"%>
<%
String contextpath = request.getContextPath() + "/";
String nodeid=request.getParameter("nodeid");
%>
<html>
<head>

    <title>Forms</title>

             <link rel="stylesheet" type="text/css" href="../resources/css/ext-all.css" />
		 <script type="text/javascript" src="../adapter/ext/ext-base.js"></script>
		 <script type="text/javascript" src="../ext-all.js"></script>
		<script type="text/javascript">
		
    //创建 Store
    var store = new Ext.data.Store({
        proxy: new Ext.data.HttpProxy({   
            url: "indexmanagergrid.action"   //从此页获取数据
        }),
        reader: new Ext.data.JsonReader({
            root: 'data',
            totalProperty: 'totalCount',
            id: 'id',
            fields: [
            {name: 'id', type: 'string'},
            {name: 'name', type: 'string'},
            {name: 'org', type: 'string'},
             {name: 'ip', type: 'string'}
            ]
        })
        
        // turn on remote sorting
       // remoteSort: true
    });
    store.setDefaultSort('id', 'desc');

     var sm = new Ext.grid.CheckboxSelectionModel(); // add checkbox column
    var cm = new Ext.grid.ColumnModel([sm,{
           id: 'id', // id assigned so we can apply custom css (e.g. .x-grid-col-topic b { color:#333 })
           header: "编号",
           dataIndex: 'id',
           align:'center',
           hidden:true,
           width: 60
        },{
           header: "用户名称",
           dataIndex: 'name',
           align:'name',
           width: 100
        },{
           header: "所在部门",
           dataIndex: 'org',
           align:'center',
           width: 100
        },{
           header: "IP地址",
           dataIndex: 'ip',
           align:'center',
           width: 100
        }
        ]);
     cm.defaultSortable = true;
    var grid=new Ext.grid.GridPanel({
		     	id:'resgrid',
		        width:760,
		        height:500,
		        title:'所有用户',
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
		            pageSize: 10,
		            store: store,
		            displayInfo: true,
		            displayMsg: '当前显示{0} - {1}  条记录 /共 {2}条记录',
		            emptyMsg: "无显示数据"
		         
		        })
	});
	
	 /*
      * 列表右键菜单
      */
      var personid;
      var username;
        function onItemCheck(item, checked){
    	if(checked){
      	//	alert(item.text);
      		Ext.Ajax.request({ 
					method:'post', 
					url:"changeIp.action?n="+Math.random(), 
					params:{personId:personid,ip:item.id},
					success : function(result, action)
								{ 
								Ext.Msg.alert('消息','操作成功');
							  	}, 
				    failure : function(form, action) 
				    { 
					Ext.Msg.alert('消息','操作失败!');
					} 
					}); 
      	}
      }
          function doDel(btn){
        if(btn=='yes'){
        		Ext.Ajax.request({ 
					method:'post', 
					url:"managerLogout.action?n="+Math.random(), 
					params:{personId:personid},
					success : function(result, action)
								{ 
								Ext.Msg.alert('消息','操作成功');
							  	}, 
				    failure : function(form, action) 
				    { 
					Ext.Msg.alert('消息','操作失败!');
					} 
					}); 
        }}
         function showResultText(btn, text){
         if(btn=='ok'){
            	Ext.Ajax.request({ 
					method:'post', 
					url:"managerSendMessage.action?n="+Math.random(), 
					params:{body:text,personId:personid},
					success : function(result, action)
								{ 
								Ext.Msg.alert('消息','发送成功');
							  	}, 
				    failure : function(form, action) 
				    { 
					Ext.Msg.alert('消息','发送失败!');
					} 
					}); 
         }
       // var person="{9BFD4C69-816A-494F-A119-DE66EC20F6CB}";//陈国才
    
    	};
    	
            grid.addListener('rowcontextmenu', rightClickFn);

            var rightClick =new Ext.menu.Menu();
            function rightClickFn(client, rowIndex, e) {
                e.preventDefault();
                var record = grid.getStore().getAt(rowIndex);   //Get the Record
		
		   	    var id = record.get(grid.getColumnModel().getDataIndex(1));
		   	    username = record.get(grid.getColumnModel().getDataIndex(2));
		  		personid=id;
		  		
		  		//刷选当前人在哪个IP地址
		  		Ext.Ajax.request({ 
					method:'post', 
					url:"indexmanagerPersonByComputer.action?n="+Math.random(), 
					params:{personId:personid},
					success : function(result, action)
								{ 
								
								var json = Ext.decode(result.responseText);
								rightClick.removeAll();
				rightClick.add({
                    id:'rMenu1',
                    text : '发送消息',
                    handler:function(){
			        Ext.MessageBox.show({
			           title: '消息',
			           msg: '消息的内容:',
			           width:300,
			           buttons: Ext.MessageBox.OKCANCEL,
			           multiline: true,
			           fn: showResultText,
			           animEl: 'mb3'
			       });

                    }
                },{
                    id:'rMenu2',
                    text : '强制下线',
                    handler:function(){
                    	Ext.MessageBox.confirm('消息', '确认强制下线'+username+'？',doDel);
 
                    }
                },{
                    id:'rMenu3',
                    text : '服务器分配',
                    menu: json
                });
           

			
							  	}, 
				    failure : function(form, action) 
				    { 
					Ext.Msg.alert('消息','发送失败!');
					} 
					}); 
                rightClick.showAt(e.getXY());
            }

	
	
Ext.onReady(function(){
store.load({params:{start:0, limit:10,serverid:'<%=nodeid%>'}});
	grid.render(document.body);
	});
</script>
</head>
<body>
</body>
</html>
