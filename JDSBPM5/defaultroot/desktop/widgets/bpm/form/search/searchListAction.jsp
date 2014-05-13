<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page
	import="java.util.Map,com.opensymphony.xwork2.util.OgnlValueStack"%>
<%@ taglib uri="/struts-tags" prefix="ww"%>
<%--
,'流程类型:',new Ext.form.ComboBox({
	                  	id:'sex',
	                    fieldLabel: '性别',
	                    name: 'googletype_name', 
	                    hiddenName:'googletype', 
	                    store: new Ext.data.SimpleStore({   ////填充数据
	                        fields: ['value','text'],
	                        data: [<ww:property value="googletype"/>]
	                    }),	      
	                    valueField:'value',               
	                    displayField:'text',
	                    forceSelection: true,
	                    triggerAction:'all',
	                    emptyText:'请选择流程类型……',
	                    mode: 'local',   //数据模式，local代表本地数据  
	                    triggerAction:'all',
	                    selectOnFocus:true,
	                    width:140
	                   })
--%>
<%
response.setHeader("Pragma","No-cache"); 
response.setHeader("Cache-Control","no-cache"); 
response.setDateHeader("Expires", 0); 
String contextpath = request.getContextPath() + "/";
%> 	
		   function querydepartMessage(){
		   	 var googleKey = Ext.get('googleKey').dom.value;
		   
		   	 var googletype="shan";
		   	 var startTime=Ext.get('startTime').dom.value;
		   	 var endTime=Ext.get('endTime').dom.value;
	    	 store.load({params:{start:0, limit:10,googleKey:googleKey,googletype:googletype,startTime:startTime,endTime:endTime}}); 
		  }
	
var store = new Ext.data.Store({
       proxy: new Ext.data.HttpProxy({   
            url: "searchlist.action"   //从此页获取数据
        }),
        reader: new Ext.data.JsonReader({
            root: 'data',
            totalProperty: 'totalCount',
            id: 'processid',
            fields: [
            {name: 'processid', type: 'string'},
            {name: 'fileTile', type: 'string'},
            {name: 'priprocessDefNamece',type: 'string'},
            {name: 'startTime',type: 'string'},
            {name: 'endTime',type: 'string'}
            ]
        })
    });
    store.setDefaultSort('processid', 'desc');

     var sm = new Ext.grid.CheckboxSelectionModel(); // add checkbox column
    var cm = new Ext.grid.ColumnModel([new Ext.grid.RowNumberer(),{
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
           width: 100
        },{
           header: "流程名称",
           dataIndex: 'priprocessDefNamece',
           align:'center',
           width: 60
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
		        title:'列表',
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
		        tbar:['标题:', new Ext.form.TextField({   
                width:175, 
                name: 'googleKey',
                allowBlank:true 
            }),'开始日期:',{
        				xtype:'datefield',
        				width:80,
        				format: 'Y-m-d',
                name: 'startTime'
            },'结束日期:',{
        				xtype:'datefield',
        				width:80,
        				format: 'Y-m-d',
                name: 'endTime'
            },{  
                    id : 'newModelButton',    
                    icon : context+"js/desktop/resources/images/user/search.jpg",   
                    cls : "x-btn-text-icon",   
                    handler :querydepartMessage     
            }],
		        bbar: new Ext.PagingToolbar({
		            pageSize: 10,
		            store: store,
		            displayInfo: true,
		            displayMsg: '当前显示{0} - {1}  条记录 /共 {2}条记录',
		            emptyMsg: "无显示数据"
		         
		        })
		        
		        });
     grid.on("cellclick",function cellclick(grid, rowIndex, columnIndex, e) {
       	var record = grid.getStore().getAt(rowIndex);   //Get the Record
		    var comments = record.get(grid.getColumnModel().getDataIndex(2));
		    var id = record.get(grid.getColumnModel().getDataIndex(1));
	  		proformWork(id);
			}
		);
function getsearchlist (){
     var searchlist={
         buttons: [
                 {
                text:'关闭',
                handler: function(){
                       JDSDesk.getDesktop().getManager().getActive().close();
                   }
                 }
                 ],
                 
        buttonAlign:'center',
        
         items:[grid]
    };
          
            
	
return searchlist;

}



function EVAL.getPanel(){
 return getsearchlist();
}

