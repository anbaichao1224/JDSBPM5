/**
 * 后台数据获取类
 * @param {Object} cfg
 */
JDS.bpm.Form.Comments = function(cfg){
	Ext.apply(this, cfg);
	this.fieldDivId;
	this.activityInstId;
	this.index;
	this.activityDefId;
	this.context;	
	this.commentsGridPanel;

}

JDS.bpm.Form.CommentPanel = function(cfg){
	Ext.apply(this, cfg);
	var temb = 'n';
   var store = new Ext.data.JsonStore({
	   url:context+"initcomments.action",
	     baseParams: {
			 	activityInstHistoryId:this.activityInstHistoryId,
				activityInstId:this.activityInstId,
				fieldName:this.fieldname,
				formName:this.formname,
				personId:personid
				
		},
	    root : 'commentList',
		remoteSort:true,
	    autoLoad : true,
	    fields: [
			{name : 'comment' ,type : 'string',mapping : 'comment'},
			{name : 'formname' ,type : 'string',mapping : 'formname'},
	        {name : 'creatdate', type: 'string' , mapping : 'creatdate'},
			{name : 'personname', type: 'string' , mapping : 'personname'},
			{name : 'personname1', type: 'string' , mapping : 'personname1'},
			{name : 'personid', type: 'string' , mapping : 'personid'},
			{name : 'proxyperson', type: 'string' , mapping : 'proxyperson'},
			{name : 'personid', type: 'string' , mapping : 'personid'},
			{name : 'isEdit', type: 'boolean' , mapping : 'isEdit'},
	        {name : 'uuid', type:'string', mapping : 'uuid'}
	    ]
	});
	
	var wordstr = this.fieldname;
	//var html = '<table width=100%>'+
	//    '<tpl for="."><tr><td>' +
    //    '<DIV id="{personid:this.id}"  class="comment" name="comyjnr">' +
	//          '<span style="color:#000000" name="'+wordstr+'">&nbsp;&nbsp; {.:this.getComments}</span><br/>'+
	//	      '<table><tr><td width=60%></td><td><span style="padding-top:0;color:#000000" name="'+wordstr+'">{.:this.getPersonName}  &nbsp;&nbsp; {.:this.getCreatDate}</span></td></tr></table><br/></DIV>{.:this.getEditView}'+
    //    '</td></tr></tpl><tr><td></td></tr></table>';
    //12-1-6修改开始（上面注释是源代码）
     var html = '<table width=100%>'+
	    '<tpl for="."><tr><td>' +
        '<DIV id="{personid:this.id}"  class="comment" name="comyjnr">' +
	          '<span style="color:#000000" name="'+wordstr+'">{.:this.getPersonName}{.:this.getPersonName1} {.:this.getComments}</span>'+
		      
        '<span style="color:#000000" name="'+wordstr+'">{.:this.getCreatDate}</span>'+'</DIV>{.:this.getEditView}'+'</td></tr></tpl><tr><td></td></tr></table>';
        
     //12-1-6修改结束
     var  tpl= new Ext.XTemplate(html,{
	 	    activityInstId:this.activityInstId,
		    readonly:this.readonly,
			id:function(id){
				   return Ext.id();
			     },
	 		getPersonName:function(comment){
				
				var personName=comment.personname;
				
				if (comment.proxyperson){
					personName="代签人："+comment.proxyperson
				} 
				
				if(personName == null || personName == ""|| personName == " ")
				return personName;
				else return ""+personName+"：";
			},
			getPersonName1:function(comment){
				
				var personName=comment.personname1;
				
				if (comment.proxyperson){
					personName="代签人："+comment.proxyperson
				} 
				
				if(personName == null || personName == ""|| personName == " ")
				return personName;
				else return ""+personName+"：";
			},
			getCreatDate:function(comment){
			 
				var creatdate=comment.creatdate;
				//alert(creatdate);
				if(creatdate == null || creatdate == ""|| creatdate == " ")
				return creatdate;
				else return "("+creatdate+")";
			},
			getComments:function(comment){
				
				if (this.readonly && comment.comment=="&nbsp;" ){
					return "";
				}
				//var commentH = comment.comment.replace("\r\n","<br>");
				//var commentH = comment.comment.nl2br();
				//var reg = new RegExp("\r\n","g")
				//var commentH = comment.comment.replace(reg,"\r\n");
				////alert(commentH);
				//return commentH;
				var printldyj1 = comment.comment;
				printldyj1 = printldyj1.replace(/\n[^>]+/g,"&nbsp;");
				//printldyj1 = printldyj1.replace(/\<p\/>/g,"");
				
				
					return printldyj1;
			},
			getEditView:function(comment){
				
				var view='&nbsp;&nbsp;&nbsp;&nbsp;<img name="imgbi" src="/desktop/resources/images/bi.gif" width=25 height=25 uuid="'+comment.uuid+'"  style="cursor:hand;" />';
				var yibanlivalue = Ext.get("yibanli"+this.activityInstId).dom.value;
	    
				if (!comment.isEdit ||this.readonly || yibanlivalue == 'y'){
					
					view='&nbsp;';
				}
				
				else{
				   // 2012 - 01 -8 意见必须填写 开始
					var cc3 = comment.comment.replace(/<[^>]+>/g,"");//去掉
					cc3 = cc3.replace(/\&nbsp;/g, "");
					
					if(cc3 == null || cc3 == "" || cc3 == " "){
						if( Ext.get("ldyjtemb").dom.value!='y'){
							Ext.get("ldyj"+this.activityInstId).dom.value='n';
						}
						
					}else{
						
						 Ext.get("ldyjtemb").dom.value = 'y';
					}
					// 2012 - 01 -8 意见必须填写 开始结束
				Ext.get("ldyj"+this.activityInstId).dom.value =  Ext.get("ldyjtemb").dom.value;
				}
				
				return view;
			}
			
	 });
	
	this.openCommentDefPanel=function (view,e){
		var img=e.getTarget('img');
	 	if (img){
			var cfg={
	        url:context+"editcomments.action",
	        params: $H({
			 	activityInstId:view.activityInstId,
				uuid:img.uuid,
				fieldname:this.fieldname,
		        formname:this.formname
				}),
			comentView:view,
			fieldname:this.fieldname,
		    formname:this.formname,
			activityInstId:view.activityInstId
	      }
         var SelectPerFormDat=new JDS.bpm.Form.Comments(cfg).load();
		}		
	 }
	 
	 	var view=new Ext.DataView({
				activityInstId:this.activityInstId,
				fieldname:this.fieldname,
				formname:this.formname,
				readonly:this.readonly,
		        store : store,
				height:100,
		        tpl : tpl,
		        autoHeight : true,
				style:"border: 0px solid #ffffff;",
				listeners: {containerclick:this.openCommentDefPanel},
		        itemSelector : 'div.comment',
		        emptyText : '<span>&nbsp;&nbsp;</span><br/><br/>&nbsp;&nbsp;&nbsp;&nbsp;'
				+'<img name="imgbi" src="/desktop/resources/images/bi.gif" width=25 height=25  style="cursor:hand;" />'
    		});
		
	var s = Ext.get("yibanli"+this.activityInstId).dom.value;
	
	if(s == 'y')
	{
	            view=new Ext.DataView({
				activityInstId:this.activityInstId,
				fieldname:this.fieldname,
				formname:this.formname,
				readonly:this.readonly,
		        store : store,
				height:100,
		        tpl : tpl,
		        autoHeight : true,
				style:"border: 0px solid #ffffff;",
				listeners: {containerclick:this.openCommentDefPanel},
		        itemSelector : 'div.comment',
		        emptyText : ''
    		});
			
	}
	JDS.bpm.Form.CommentPanel.superclass.constructor.call(this, {
		style:"border: 0px solid #ffffff;",
		bodyStyle:"border: 0px solid #ffffff;",
		loadMask: true,
	    autoShow : true,
		bodyBorder:false,
		autoHeight : true,
		id:Ext.id(),
		closeAction : 'close',
		items:[
			view
		]


	});
};


Ext.extend(JDS.bpm.Form.CommentPanel, Ext.Panel, {
});

			
Ext.extend(JDS.bpm.Form.Comments, Ext.util.Observable, {
	render:function(){
		this.commentsPanel= this.getCommentsPanel(); 
		this.uuid=this.commentdata.uuid;
		
		
	    
		
		this.commentsPanel.show();
	
		//Ext.getCmp("daiqianren").setValue(this.commentdata.proxyperson);
		//Ext.getCmp("editDate").setValue(this.commentdata.createdate);
		Ext.getCmp("comments").setValue(this.commentdata.comment);
		Ext.getCmp("comments").focus(true);
		
		if(this.commentdata.proxyperson != null && this.commentdata.proxyperson.length> 0){
			Ext.getCmp("shifoudaiqian").expand(false);
		}
		//Ext.getCmp("comments").toggleSourceEdit(true);
		//Ext.getCmp("comments").toggleSourceEdit(false);
		
	},	
	
	selections: function (grid){
		var selections=grid.getSelections();
		   if (selections.length==0){
		          alertMsg('没有任何选择项');
		   return;
		   }
		var gridIdList=new Array();
		  for(var i=0;i<selections.length;i++){
		   if (selections[i].get('id').length>0 ){     
		    gridIdList[gridIdList.length]= selections[i].get('id');    
		   }else{
		    Ext.getCmp(gridId).getSelectionModel().deselectRow(selections[i].get('index'));   
		   }
		  };
		  
		return gridIdList;
	},
	getCommentsGrid:function(){
		var store=new Ext.data.Store({
					url: context+'commentsDeflist.action?activityInstId='+this.activityInstId ,
					remoteSort:true,
					autoLoad:true,
			        reader: new Ext.data.JsonReader({
			            root: 'data',
			            totalProperty: 'totalCount',
			            fields: [
			            {name: 'id', type: 'string'},
			            {name: 'text', type: 'string'},
			            {name: 'desc',type: 'string'}
			             ]
			        })
			    });
		this.commentsGridPanel=new Ext.grid.GridPanel({
			
		   trackMouseOver:false,
	       loadMask: true,
	       autoShow : true,
           viewConfig: {
	            forceFit:true,
	            enableRowBody:true,
	            showPreview:true
	        },
	        width:500,
	        height:150,
	        title:'',
			store : store,
	        bbar: new Ext.PagingToolbar({
	            pageSize: 15,
	            store: store,
	            displayInfo: true,
	            displayMsg: '{0} - {1}  条记录 /共 {2}条记录',
	            emptyMsg: "无显示数据"
	        }),
			hideHeaders:true,
			
	        cm: new Ext.grid.ColumnModel([
			   new Ext.grid.RowNumberer(),
	           new Ext.grid.CheckboxSelectionModel({singleSelect:true}),
			   {header: "ID", width: 30, sortable: true, hidden:true, dataIndex: 'text'},
	           {header: "意见内容", width: 30, sortable: true, dataIndex: 'text'}
	        ]),
			tbar: [{
				text: '保存为常用批示',
				tooltip: '保存批示',
				handler: function(){
					
					var sst = Ext.get("comments").dom.innerText;
					var cc1 = sst.replace(/\n[^>]+/g,"&nbsp;");
					cc1 = cc1.replace(/\&nbsp;/g, "");
					
					if(cc1 == null || cc1 == "" || cc1 == " "){     
					alert("请输入常用批示！");
					return ;
					}
					var evalJs=function(o){
						this.commentsGridPanel.store.reload();
					}
					var params= $H({
					  comments:Ext.getCmp("comments").getValue()
					})
					
					JDS.ajax.Connection.LoadJsonFromUrl(context + "saveCommentsDef.action", evalJs, params,this);
				},
                   scope: this,
				iconCls: 'add'
			}, '-', {
				text: '删除批示',
				tooltip: '删除批示',
				iconCls: 'option',
                 scope: this,
				handler: function(){
					var evalJs=function(o){
						this.commentsGridPanel.store.reload();
					}
					var params= $H({
					  id:this.selections(this.commentsGridPanel).join()
					})
					
					JDS.ajax.Connection.LoadJsonFromUrl(context + "removeCommentsDef.action", evalJs, params,this);
				}
				
			}]
		  
	    });
		
	      this.commentsGridPanel.on("cellclick",function cellclick(grid, rowIndex, columnIndex, e) {
	         	var record = this.getStore().getAt(rowIndex);   //Get the Record
			    var comments = record.get(this.getColumnModel().getDataIndex(2));
			    var id = record.get(this.getColumnModel().getDataIndex(1));
		  		Ext.getCmp("comments").setValue(comments);
		  		Ext.getCmp("commentsid").setValue(id);
				}
		);
		
	
		return this.commentsGridPanel;
	},
	

	
	getCommentsPanel: function(){
		var commentsGridPanel=this.getCommentsGrid();
		var commentslistPanel =  new Ext.Window({
			autoScroll: true,
			manager: getCurDesktop().getManager(),
            minimizable: true,
            maximizable: true,
			    width:550,
				shim:false,
		        height:450,
		        modal: true,
			items: [{
				xtype: 'fieldset',
				checkboxToggle: true,
				title: '常用批示',
				autoHeight: true,
				collapsed: false,
				items: [commentsGridPanel]
			}, {

					id: 'comments',
					xtype: 'htmleditor',
					fontFamilies : ['仿宋','黑体','宋体','方正小标宋简体','Times New Roman','方正魏碑简体','方正仿宋简体','方正行楷简体'],
					defaultFont: '仿宋',
					cls:'x-html-font',
					//xtype: 'textarea',
					enableFont:false,
					enableLinks:false,
					enableSourceEdit:false,
					enableLists:false,
					enableAlignments:false,
					enableColors:false,
					enableFontSize:false, 
					enableFormat:false, 
					
					value: '',
					name: 'comments',

					width: 520,
					height: 200
			}],
			
			buttons: [{
				text: '新建',
				scope: this,
				handler: function(){
					this.uuid="";
					Ext.getCmp("daiqianren").setValue("");
					Ext.getCmp("comments").setValue("");
					Ext.getCmp("editDate").setValue("");
				}
			},
			{
				text: '确定',
				scope: this,
				handler: function(){
					 var comments = Ext.getCmp("comments").getValue();
					// var proxyperson = Ext.getCmp("daiqianren").getValue();
					 /*
					var cc2 = comments.replace(/<[^>]+>/g,"");//去掉
					cc2 = cc2.replace(/\&nbsp;/g, "");
					if(cc2 ==null || cc2=="" || cc2 == " "){
						
						return;
					}else{
						Ext.get("ldyj"+this.activityInstId).dom.value='y';
					}*/
					Ext.Ajax.request({
						method: 'post',
						url: context+'saveComments.action',
						params: { 
						      'bpmCommentsDAO.comments': comments,
							   activityInstId:this.activityInstId,
							    'bpmCommentsDAO.uuid':this.uuid,
								'bpmCommentsDAO.formname':this.formname,
								//'bpmCommentsDAO.proxyperson':proxyperson,
								'bpmCommentsDAO.fieldname':this.fieldname
								//'bpmCommentsDAO.createdate':Ext.getCmp("editDate").getRawValue()
							   },
						success: function(response, options){
						    
							var responseArray = Ext.util.JSON.decode(response.responseText);
							if (responseArray.success == true) {
								this.comentView.store.reload();
								JDSDesk.getDesktop().getManager().getActive().close();
								
								var x=document.getElementsByTagName("input");
								x[0].focus();
							}
							if (responseArray.error == true) {
								Ext.Msg.alert('提示', '保存错误');
								var x=document.getElementsByTagName("input");
								x[0].focus();
							}
						}  
						,scope: this

					});					
				}
			}, {
				text: '删除',
				scope: this,
				handler: function(){
					Ext.Ajax.request({
						method: 'post',
						url: context+'delComments.action',
						params: { 
						      'uuid':this.uuid
							},
						success: function(response, options){
						    
							var responseArray = Ext.util.JSON.decode(response.responseText);
							if (responseArray.success == true) {
								Ext.get("ldyj"+this.activityInstId).dom.value = 'n';
								this.comentView.store.reload();
								JDSDesk.getDesktop().getManager().getActive().close();
								var x=document.getElementsByTagName("input");
								x[0].focus();
							}
							if (responseArray.error == true) {
								Ext.Msg.alert('提示', '删除错误');
								JDSDesk.getDesktop().getManager().getActive().close();
								var x=document.getElementsByTagName("input");
								x[0].focus();
							}
						}  
						,scope: this

					});
				}
					
			}, {
				text: '取消',
				handler: function(){
					JDSDesk.getDesktop().getManager().getActive().close();
					var x=document.getElementsByTagName("input");
					x[0].focus();
				}
			}],
			buttonAlign: 'center'
			
			
		})
		return commentslistPanel;
	},
	
	
	load: function(){
		JDS.ajax.Connection.load(this);
	}
  }
)
