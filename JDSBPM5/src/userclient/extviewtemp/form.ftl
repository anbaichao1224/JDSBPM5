


var config={
			id : '${path}@Panel',
			loadData:{
			    url:context+'${loadData.url}',
			    httpBaseParams:{
			    path:'${path}',
			    gridId:''
			   }
			},
		//	title:'${title}',
		
			autoScroll:true,
			xtype:'formPanel',
			bodyStyle : 'padding:5 5 5 5',
			labelAlign : '${labelAlign}',
			labelWidth:${labelWidth},
			fileUpload:${fileUpload?string},
			abort:function (){
			  this.win.close();
			},
			save:function(){
				Ext.apply(this.loadData.httpBaseParams,USMContext);
				Ext.apply(this.loadData.httpBaseParams,this.getForm().getValues());
				var baseparams=Ext.urlEncode(this.loadData.httpBaseParams);
			    var url=context+'${updateData.url}';
			    var callback=function(o){
			        o=o.trim();
			       if (o==true || o=='true'){
			           alert('保存成功');
			          viewport.reload({node:{id:this.uuid}});
			          this.win.close();
			       }else if (o=='false'){
			           alert('保存失败');
			       }else{
			            alert(o);
			             this.win.close();
			          viewport.reload({node:{id:this.uuid}});
			         
			       }
			    };
				JDS.ajax.Connection.LoadJsonFromUrl(url,callback,baseparams,this)
			},
			
			
			reload:	function(args){
	              	var url=this.loadData.url;
					var callback=function(o){
				        var data=Ext.decode(o);
				        var dataM=new Ext.util.MixedCollection(); 
				        data.each(function(item){dataM.addAll(item)});
				        	dataM.eachKey(function(key){
							try{
						       this.getForm().findField('${viewPath}.'+key).setValue(dataM.get(key));
						     }catch(e){
						       // JDS.alert(e);
						     }
							},this)	;				    
					}
				    Ext.apply(this.loadData.httpBaseParams,USMContext);
					this.loadData.httpBaseParams.uuid=args.node.id;
					var params=Ext.urlEncode(this.loadData.httpBaseParams);
					JDS.ajax.Connection.LoadJsonFromUrl(url,callback,params,this)
			},
			
			listeners:{
			  render:function(){
			    this.reload({node:{id:this.uuid}});
			  }
			},
			
			frame : true,
			items:[
			new Ext.form.FieldSet({
                title: '${title}',
                autoHeight: true,
                defaultType: 'textfield',
                items: [
				<#list items as item>
					<#if item.xtype == 'TextField'>
						{
						     xtype:'${item.xtype.type}' ,
						   <#if item.vtype=='telephone'>
						        regex: /${item.vtype.type}/, 
  								regexText: '国内电话 匹配形式如 0511-4405222 或 021-87888822', 
						       </#if>
						       <#if item.vtype=='ip'>
						        regex: /${item.vtype.type}/, 
  								regexText: '匹配ip地址 000.000.000.000', 
						       </#if>
						       <#if item.vtype=='zipcode'>
						        regex: /${item.vtype.type}/, 
  								regexText: '中国邮政编码为6位数字', 
						       </#if>
						        <#if item.vtype=='chinese'>
						        regex: /${item.vtype.type}/, 
  								regexText: '只能输入中文', 
						       </#if>
						        <#if item.vtype=='idcard'>
						        regex: /${item.vtype.type}/, 
  								regexText: '中国的身份证为15位或18位', 
						       </#if>
						        <#if item.vtype=='qq'>
						        regex: /${item.vtype.type}/, 
  								regexText: '腾讯QQ号从10000开始的数字', 
						       </#if>
						       <#if item.vtype=='email'||item.vtype=='url'||item.vtype=='alphanumal'||item.vtype=='pha'>
						        vtype : '${item.vtype}', 
						       </#if>
						        <#if item.vtype=='regex'>
						        regex: /${item.regex}/, 
  								regexText: '${item.regexText}', 
						       </#if>
								fieldLabel : '${item.fieldLabel}',
								width:${item.width},
								inputType:'${item.inputType}',
								name:'${viewPath}.${item.id}',
 								 allowBlank :${item.allowBlank?string},
 								 maxLength:${item.maxLength?c},
 								 validateOnBlur : true,
 								 readOnly:${item.readOnly?string},
 								 msgTarget : 'side'
 								
	        			}
					</#if>
					<#if item.xtype == 'NumberField'>
						{
						      xtype:'${item.xtype.type}' ,
						       <#if item.vtype=='email'||item.vtype=='url'||item.vtype=='alphanumal'||item.vtype=='pha'>
						        vtype : '${item.vtype}', 
						       </#if>
						        <#if item.vtype=='regex'>
						        regex: /${item.regex}/, 
  								regexText: '${item.regexText}', 
						       </#if>
								fieldLabel : '${item.fieldLabel}',
								width:${item.width},
								name:'${viewPath}.${item.id}',
								allowBlank :${item.allowBlank?string},
								 readOnly:${item.readOnly?string},
								 maxLength:${item.maxLength?c},
								validateOnBlur : true
	        			}
					</#if>
					<#if item.xtype == 'DateField'>
					{
								format : 'Y-m-d',
								xtype:'${item.xtype.type}' ,
								fieldLabel : '${item.fieldLabel}',
								width:${item.width},
								name:'${viewPath}.${item.id}',
								allowBlank :${item.allowBlank?string},
								readOnly:${item.readOnly?string},
								maxLength:${item.maxLength?c},
								validateOnBlur : true
	        			}
					</#if>
					<#if item.xtype == 'ComboBox'>
					   <#if item.model=='local'>
						{
							 xtype:'${item.xtype.type}' ,
								//emptyText : '请选择',
								fieldLabel : '${item.fieldLabel}',
								width:${item.width},
							   
								name:'${viewPath}.${item.id}',
								store:new Ext.data.SimpleStore({ 
									fields:['code','value'],
									data:${item.item.type}
									}),
								allowBlank :${item.allowBlank?string},
								 readOnly:${item.readOnly?string},
								valueField : 'code',
						        displayField : 'value',
						        value:'${item.value}',
						        mode : 'local',
								forceSelection : true,
						        triggerAction : 'all',
								validateOnBlur : true
	        			}
	        			</#if>
					</#if>
					<#if item.xtype == 'ComboBox'>
					   <#if item.model=='remote'>
						{
								//emptyText : '请选择',
								xtype:'${item.xtype.type}' ,
								fieldLabel : '${item.fieldLabel}',
								width:${item.width},
								name:'$${viewPath}.${item.id}',
								store:new Ext.data.Store({ 
									proxy : new Ext.data.HttpProxy({
									url : '${item.store}',method : 'POST'}),
									reader : new Ext.data.JsonReader({id : 'code',fields : ['code', 'value']})
									}),
								 allowBlank :${item.allowBlank?string},
								 readOnly:${item.readOnly?string},
								valueField : 'code',
						        displayField : 'value',
						        mode : 'remote',
								forceSelection : true,
						        triggerAction : 'all',
								validateOnBlur : true
	        			}
	        			</#if>
					</#if>
					<#if item.xtype == 'TextArea'>
					{
						        <#if item.vtype=='regex'>
						        regex: /${item.regex}/, 
  								regexText: '${item.regexText}', 
						       </#if>
								fieldLabel : '${item.fieldLabel}',
								width:${item.width},
								xtype:'${item.xtype.type}' ,
								name:'${viewPath}.${item.id}',
							    allowBlank :${item.allowBlank?string},
							    maxLength:${item.maxLength?c},
								validateOnBlur : true
	        			}
					</#if>
					<#if item.xtype == 'Checkbox'>
						{
								xtype:'${item.xtype.type}' ,
								fieldLabel : '${item.fieldLabel}',
								boxLabel:'${item.boxLabel}',
								width:${item.width},
								name:'${viewPath}.${item.id}',
							    allowBlank :${item.allowBlank?string},
							    maxLength:${item.maxLength?c},
								validateOnBlur : true
	        			}
					</#if>
					<#if item.xtype == 'HTMLEditor'>
						{
								xtype:'${item.xtype.type}' ,
								fieldLabel : '${item.fieldLabel}',
								width:${item.width},
							    enableAlignments:true,
							    enableColors:true,
							    enableFont:true,
							    enableFontSize:true,
							    enableFormat:true,
							    enableLinks:true,
							    enableLists:true,
							    enableSourceEdit:true,
								validateOnBlur : true
	        			}
					</#if>
					<#if item.xtype == 'Radio'>
					{
								xtype:'${item.xtype.type}' ,
								fieldLabel : '${item.fieldLabel}',
								boxLabel:'${item.boxLabel}',
								width:${item.width},
							    name:'${viewPath}.${item.id}',
							    allowBlank :${item.allowBlank?string},
							    maxLength:${item.maxLength?c},
								validateOnBlur : true
	        			}
					</#if>
					<#if item.xtype == 'FileuploadField'>
						{
							xtype:'textfield',   
		       				fieldLabel : '${item.fieldLabel}', 
							name:'${servicekey}.${item.id}',
				 			height : 20,  
				 			width:${item.width},
				 			allowBlank :${item.allowBlank?string},
							validateOnBlur : true,
				            inputType : 'file'
	        			}
					</#if>
					<#if item.xtype == 'Panel'>
						{
								xtype : 'panel',
								fieldLabel : '${item.fieldLabel}',
								border:false, 				   
  						        bodyStyle : 'padding:2,12,7,52;border:1px', 
               		            html:'<img id="img" src="${item.src}" border="0" width="80" height="80" />' 
               		            
	        			}
					</#if>
					<#if item.xtype == 'Hidden'>
						{
								xtype : 'hidden',
								id:'${viewPath}.${item.id}',
							    name:'$${viewPath}.${item.id}',
								fieldLabel : '${item.fieldLabel}'
								
	        			}
					</#if>
					
	        		<#if item_has_next>,</#if>
	        	</#list>
			]})],
			buttons : [
				<#list buttons as item>
				<#if item.type!='none'>
						{
							text : '${item.type}',
							handler:function(form){
							 try{
							    formPanel.${item}();
							 }catch(e){
							   JDS.alert(e);
							 }
							},
							scope:formPanel
						}
					<#if item_has_next>,</#if></#if>
				</#list>
				
			]
		}
		
Ext.apply(config,arguments[0]);		
config.loadData.httpBaseParams.uuid=config.uuid;	
config.loadData.httpBaseParams.gridId=config.gridId;			
var formPanel= new Ext.form.FormPanel(config);
return formPanel;
	