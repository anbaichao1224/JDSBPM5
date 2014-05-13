<%@ page language="java" errorPage="/error.jsp" pageEncoding="utf8" %>
<%
String contextpath = request.getContextPath() + "/";
%>
<html>
<head>

    <link rel="stylesheet" type="text/css" href="<%=contextpath%>js/ext/resources/css/ext-all.css" />
    <script src="<%=contextpath%>js/ext/adapter/ext/ext-base.js"></script>
    <script src='<%=contextpath%>js/ext/ext-all.js'></script>
<!--     <script type="text/javascript" src="js/RowExpander.js"></script>
-->    <script type="text/javascript" src="js/departaddressList.js" defer="defer"></script>
    <link rel="stylesheet" type="text/css" href="../css/grid-examples.css" />
    <style type="text/css">
        body .x-panel {
            
        }
        .icon-grid {
            background-image:url(../img/grid.png) !important;
        }
        #button-grid .x-panel-body {
            border:1px solid #99bbe8;
            border-top:0 none;
        }
        .add {
            background-image:url(../img/add.gif) !important;
        }
        .option {
            background-image:url(../img/plugin.gif) !important;
        }
        .remove {
            background-image:url(../img/delete.gif) !important;
        }
        .save {
            background-image:url(../img/save.gif) !important;
        }
    </style>
    
    <script type="text/javascript">
    
   
    
    
    </script>
    
    <script type="text/javascript">

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
			        html: '<iframe scrolling="yes" frameborder="0" src="/usm/getPersonDetail.do?personid='+personid+'" height=100% width=100%></iframe>'
			    })
            });
      
        win.show();
      }
    
   </script>
    
  <script type="text/javascript">
	  function openPersnonList(orgid)
	  {
		    function personNameClike(personid,cnname){
	          return " <a href=javascript:openPersonDetail('"+personid+"')  style='text-decoration: none;color: #blue' >" + cnname +"</a>";
	        }
      
      
      
	  var store2 = new Ext.data.Store({
      proxy: new Ext.data.HttpProxy({   
      url: '/usm/orgPersonListGrid.do?orgid='+orgid+''  //从此页获取数据
      }), 
        reader: new Ext.data.JsonReader({
            root: 'data',
            totalProperty: 'totalCount',
            id: 'personid',
            fields: [
            {name: 'personid', type: 'string'},
            {name: 'cnname', type: 'string'},
            {name: 'enname', type: 'string'},
            {name: 'orgname', type: 'string'},
            {name: 'officetel', type: 'string'},
            {name: 'mobile', type: 'string'},
            {name: 'orgid', type: 'string'}
            ]
        })
    });
    store2.setDefaultSort('personid', 'desc');
    var sm2= new Ext.grid.CheckboxSelectionModel(); // add checkbox column
      var cm2 = new Ext.grid.ColumnModel([sm2, new Ext.grid.RowNumberer(
	     {
		     header:'序号',
		     width:32,
		     align:'center'
	     }
        ),{
           id:'personid',
           header: "人员编号",
           dataIndex: 'personid',
           hidden :true,
           align:'center',
           width: 60
        },{
           header: "姓名",
           dataIndex: 'cnname',
           align:'center',
           renderer: function(value,metadata,record,rowIndex){
			   return personNameClike(record.id,value);
			  },
           width: 100
        },{
           header: "英文",
           dataIndex: 'enname',
           hidden :true,
           align:'center',
           width: 100
        },{
           header: "部门id",
           dataIndex: 'orgid',
           align:'center',
           hidden :false,
           width: 100
        },{
           header: "办公电话",
           dataIndex: 'officetel',
           align:'center',
           width: 60
        },{
           header: "手机",
           dataIndex: 'mobile',
           align:'center',
           width: 60
        }
        ]);
     cm2.defaultSortable = true;    
	      
	      
	  //配置视图信息
      var viewperson=new Ext.grid.GridView({forceFit:true,sortAscText :'正序', sortDescText :'倒序'});
      viewperson.columnsText='列显示/隐藏';
        //persongrid2
      var pname = new Ext.form.TextField({   
                width:175, 
                name: 'pname',
                allowBlank:true 
            }); 
	     var persongrid2 = new Ext.grid.GridPanel({
                id:'grid2',
		        width:500,
		        height:250,
		        title:'',
		        view:viewperson,
		        store: store2,
		        cm: cm2,
		        sm: sm2,
		        trackMouseOver:false,
		        loadMask: true,
		        autoShow : true,
		        viewConfig: {
		            forceFit:true,
		            enableRowBody:true,
		            showPreview:true
		        },
		        tbar:['按姓名：', pname, {// 查询按钮   
                    id : 'newModelButton',    
                    icon : "/usm/img/search.png",   
                    cls : "x-btn-text-icon",   
                    handler :querypnameMessage 
	            }],
		        bbar: new Ext.PagingToolbar({
		            pageSize: 10,
		            store: store2,
		            displayInfo: true,
		            displayMsg: '当前显示{0} - {1}  条记录 /共 {2}条记录',
		            emptyMsg: "无显示数据"
		         
		        })
		        
		        });
		        

        store2.load({params:{start:0, limit:15}}); 
	    var win = new Ext.Window({
                layout : 'fit',
                width : 600,
                height : 300,
                //closeAction : 'hide',
                maximizable:true,
                autoScroll:true,
                plain : true,
                title : '人员详细信息',
                items:persongrid2      
                  
       });
      
       win.show(); 
       
       
        var fsf = new Ext.FormPanel({
     labelWidth: 95, 
		labelAlign: 'right',  
		fileUpload: true, 
		title: '',    
		buttonAlign:'center',    
		bodyStyle:'padding:5px 5px 0',   
		anchor:'100%',  
		frame:true,    
        items: [ {       
               		   id:'personid',     
		               xtype:'textfield',                
		               fieldLabel: '人员编号',                
		               name: 'personinfo.personid',
		               readOnly:true,                 
		               anchor:'82%'
	             },{       
               		   id:'orgid',     
		               xtype:'textfield',                
		               fieldLabel: '部门号',                
		               name: 'depart.orgid',
		               readOnly:true,                 
		               anchor:'82%'
	             }]
	                   
     
     }); 
       
        persongrid2.on("rowdblclick", function(persongrid2) {
        loadFormData(persongrid2);
        
    });
       
        var loadFormData = function(persongrid) {
            myFormWin();
        };
        
        var newFormWin;
        var myFormWin = function() {
        var _record = persongrid2.getSelectionModel().getSelected();
        var personid=_record.get('personid');
         var orgid=_record.get('orgid');
        
        if (!newFormWin) {
            newFormWin = new Ext.Window( {
                layout : 'fit',
                width : 700,
                height : 400,
                //closeAction : 'hide',
                maximizable:true,
                autoScroll:true,
                plain : true,
                title : '人员详细信息',
                items:new Ext.Panel({
			        title: '',        
				  	height:350,
			        width:700,
			        html: '<iframe scrolling="yes" frameborder="0" src="/usm/getPersonDetail.do?personid='+personid+'" height=100% width=100%></iframe>'
			    }),
			    listeners:{"beforedestroy":function(obj){		
            	openPersnonList(orgid);
            	return false;	
            	}}
            });
        }
        newFormWin.show('New1');
    }; 
    
    
    
       
       
            function querypnameMessage()
    {
             var name = Ext.get('pname').dom.value;
    	     store2.load({params:{start:0, limit:10,name:name}});
    }
}
</script>
</head>
<body>
<script type="text/javascript" src="<%=contextpath%>usm/resources/examples.js"></script><!-- EXAMPLES -->

</body>
</html>
