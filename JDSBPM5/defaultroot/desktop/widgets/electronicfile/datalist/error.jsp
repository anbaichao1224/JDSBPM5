<%@ page language="java" contentType="text/html;charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>    
<html>
<script type="text/javascript">
	var context="/";
</script>
<script type="text/javascript" src="/js/extAll.js"></script>
<script type="text/javascript">
	//alert('<%=request.getAttribute("errorstr")%>');
	Ext.onReady(function(){
	if('<%=request.getAttribute("isapplicant")%>'=='Y'){
		if(confirm('<%=request.getAttribute("errorstr")%>')){
			applicant('<%=request.getAttribute("dataId")%>');
			Ext.getCmp('addPepodom').close();
		}else{
			parent.Ext.getCmp("addtype").close();
		}
	}else{
		alert('<%=request.getAttribute("errorstr")%>');
		parent.Ext.getCmp("addtype").close();
	}
	});
	
		function applicant(valueid){
			var form = new Ext.FormPanel({
     											 				frame:true,
     											 				width:300,
     											 				url:'/pepodom_save.action',
     											 				items:[
     											 					{
     											 						xtype:'datefield',
     											 						fieldLabel:'开始日期',
     											 						name:'starttime',
     											 						format:'Y-m-d',
     											 						allowBlank:false,
     											 						blankText:'不能为空',
     											 						anchor:'90%'
     											 					},
     											 					{
     											 						xtype:'datefield',
     											 						fieldLabel:'结束日期',
     											 						name:'endtime',
     											 						format:'Y-m-d',
     											 						allowBlank:false,
     											 						blankText:'不能为空',
     											 						anchor:'90%'
     											 					},
     											 					new Ext.form.Hidden({
     											 						name:'rollid',
     											 						fieldLabel:'案卷id',
     											 						value:valueid
     											 					})
     											 				],
     											 				buttons:[{text:"确定",handler:login,formBind:true},{text:"取消",handler:reset}]	 
													 		});
													 		function login(){
																form.getForm().submit({
																	success:function(){
																		//alert("1");
																		//store.reload();
																		Ext.getCmp('room-list').getStore().reload();
																		Ext.getCmp('addPepodom').close();
																	}
																});
																//store.reload();
																Ext.getCmp('room-list').getStore().reload();
															}
															function reset(){
																form.form.reset();
															}
													 		var win = new Ext.Window({
													 			id:'addPepodom',
													 			title:'申请信息',
													 			width:'310',
													 			height:'200',
													 			items:form
													 		});
													 		win.show();
	}

	
</script>
</html>