<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="ww"%>
<html>
<head>
<title></title>
<%
String contextpath = request.getContextPath() + "/";
%>
</head>
    <link rel="stylesheet" type="text/css" href="<%=contextpath%>js/ext/resources/css/ext-all.css" />
    <script src="<%=contextpath%>js/ext/adapter/ext/ext-base.js"></script>
    <script src="<%=contextpath%>js/ext/ext-all.js"></script>
<body>
</body>
<script type="text/javascript">
 var simple = new Ext.FormPanel({
        labelWidth: 75, // label settings here cascade unless overridden
        frame:true,
        bodyStyle:'padding:5px 5px 0',
        width: 790,
        defaults: {width: 230},
        items: [{
            checkboxToggle:true,
            xtype:'fieldset',
            title:'基本配置',
            autoHeight:true,
            collapsed:false,
            width:600,
            checkboxName:'jiben',
            items:[
                 {
                fieldLabel: 'ID',
                    name: 'bean.id',
                    xtype:'textfield',
                    allowBlank:false,//不允许为空
                    width:200
                },{
                fieldLabel: '名称',
                    name: 'bean.name',
                    allowBlank:false,//不允许为空
                    xtype:'textfield',
                    width:200
                },{fieldLabel: 'URL',
                    name: 'bean.url',
                    allowBlank:false,//不允许为空
                    xtype:'textfield',
                    width:200
                }, new Ext.form.ComboBox({
	                    fieldLabel: '类型',
	                    name: 'state', 
	                    allowBlank:false,//不允许为空
	                    hiddenName:'bean.type', 
	                    store: new Ext.data.SimpleStore({
	                        fields: ['value','text'],
	                        data: [['main','main'],['sub','sub']]
	                    }),	      
	                    valueField:'value',               
	                    displayField:'text',
	                    forceSelection: true,
	                    triggerAction:'all',
	                    emptyText:'请选择类型……',
	                    mode: 'local',   //数据模式，local代表本地数据  
	                    triggerAction:'all',
	                    selectOnFocus:true,
	                    width:140
	                   }),{
                    fieldLabel: '最大连接',
                    allowBlank:false,//不允许为空
                    name: 'bean.maxconnection',
                    xtype:'textfield',
                    value:50,
                    width:200
                },{
                    fieldLabel: '最小连接',
                    xtype:'textfield',
                    allowBlank:false,//不允许为空
                    width:200,
                    value:50,
                    name: 'bean.minconnection'
                },
            	{
                    fieldLabel: '连接超时',
                    xtype:'textfield',
                    allowBlank:false,//不允许为空
                    width:200,
                    value:200,
                    name: 'bean.timeout'
                },{
	                xtype:'textarea',
                    fieldLabel: '描述',
                    allowBlank:false,//不允许为空
                    name: 'bean.desc',
                    width:200
                },
                new Ext.form.ComboBox({
	                    fieldLabel: '状态',
	                    name: 'state', 
	                    allowBlank:false,//不允许为空
	                    hiddenName:'bean.status', 
	                    store: new Ext.data.SimpleStore({   ////填充数据
	                        fields: ['value','text'],
	                        data: [['STOP','停止'],['RUN','运行']]
	                    }),	      
	                    valueField:'value',               
	                    displayField:'text',
	                    forceSelection: true,
	                    triggerAction:'all',
	                    emptyText:'请选择状态……',
	                    mode: 'local',   //数据模式，local代表本地数据  
	                    triggerAction:'all',
	                    selectOnFocus:true,
	                    width:140
	                   }),
                new Ext.form.ComboBox({
	                    fieldLabel: '表达式',
	                    name: 'userexpression',
	                    allowBlank:false,//不允许为空 
	                    hiddenName:'bean.userexpression', 
	                    store: new Ext.data.SimpleStore({   ////填充数据
	                        fields: ['value','text'],
	                        data: [['true','true'],['false','false']]
	                    }),	      
	                    valueField:'value',               
	                    displayField:'text',
	                    forceSelection: true,
	                    triggerAction:'all',
	                    emptyText:'是否支持表达式……',
	                    mode: 'local',   //数据模式，local代表本地数据  
	                    triggerAction:'all',
	                    selectOnFocus:true,
	                    width:140
	                   })
          		 
            ]}
               ],

        buttons: [{
            text: '确定',
            handler: function(){
             if(simple.getForm().isValid()) {
            simple.getForm().submit({url:'insertComputer.action',method:'POST',waitMsg:'正在保存数据...',success:function(form,action){ 
				Ext.Msg.alert('操作','操作成功');
				
			}, failure:function(form,action){ 
				Ext.MessageBox.alert('错误', '操作失败'); 
			}
            });
            }else{
            Ext.MessageBox.alert('错误', '请填写完表单'); 
            }
	        }
	        },{
            text: '取消',
            handler:function(){ 
             simple.getForm().reset();
			} 
			}
	 ]
    });

Ext.onReady(function(){
	simple.render(document.body);
});
</script>
</html>


