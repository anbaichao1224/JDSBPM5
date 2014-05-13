
Ext.form.Field.prototype.theoremexpression;;
Ext.form.Field.prototype.activityInstId;;
Ext.form.Field.prototype.isInstProcess=false;
Ext.form.Field.prototype.getExpression=function(){
	  	return this.theoremexpression;
}	
Ext.form.Field.prototype.getActivityInstId=function(){
	  	return this.activityInstId;
}


Ext.form.Field.prototype.isInstProcess=function(){
	  	return this.isInstProcess;
}

JDS.bpm.Form=function(form,formModule,print){
	
	
	if(!form){
	    return;
	} 

	this.print=print;
	this.formname=form.dom.formname;
	this.formpath=form.dom.path;
	
	this.fieldNodes=form.select("input,select,textarea");
	this.activityInstId=formModule.activityInstId;
	this.extForm=new Ext.form.BasicForm(form);
	this.expressArr=[];
	this.fields=[];
	
	hiddenInput=new Ext.util.MixedCollection();
	
	/**
	 * 
	 * @param {Object} name
	 */
	this.selectFieldByName=function(name){
		if (hiddenInput.containsKey()){
			return hiddenInput.item(name);
		}else{
			var inputNode=form.select("input");
			inputNode.each(
				function(f,sorce){
					hiddenInput.add(f.dom.name,f.dom);
				}
			)
		}
		return hiddenInput.item(name);
		
	}
	
	/**
	 * 
	 * @param {Object} fieldCfg
	 */	
	this.changeRadioGroup = function(fieldCfg){
	  var cfg=this.getSelectCfg(fieldCfg);
	  
	 return new JDS.bpm.Form.Field.RadioGroup(cfg);	
	}
	
	
	/**
	 * 
	 * @param {Object} fieldCfg
	 */	
	this.changeCheckboxGroup = function(fieldCfg){
	  var cfg=this.getSelectCfg(fieldCfg);
	  return new JDS.bpm.Form.Field.CheckboxGroup(cfg);;
	}
	
	
	
	/**
	 * 
	 * @param {Object} fieldCfg
	 */
	this.getSelectCfg=function(fieldCfg){
		 var selectArr=[];
		
		 var select=fieldCfg.node.dom;
		 
	   for (var l = 0; l < select.length; l++) {
			var combo={};
			Ext.apply(combo,{
				inputValue: select[l].value,
				boxLabel: select[l].text,
				name:select.name,
				checked: select[l].selected
			});				
			selectArr.push(combo);
		};
		
		
	
		
	  Ext.apply(fieldCfg,{
	  	     columns: fieldCfg.column ?parseInt(fieldCfg.column):'auto'  
		     ,name: select.name     
		     ,disabled:fieldCfg.readonly ? fieldCfg.disabled:false
			 ,width:fieldCfg.boxwidth ? parseInt(fieldCfg.boxwidth):'auto'
		     ,height:fieldCfg.boxheight ? parseInt(fieldCfg.boxheight):'auto'
		     ,items:selectArr
			 ,select:select
		});
		
			
	
		return fieldCfg;
	}
	
	/**
	 * 
	 * @param {Object} fieldCfg
	 */
	this.changeSelect=function(fieldCfg){
	    var select=fieldCfg.node.dom;
	    var selectArr=[];
		for (var l = 0; l < select.length; l++) {
				var combo={};
				Ext.apply(combo,{
					value: select[l].value,
					text: select[l].text
				});
				selectArr.push(combo);
		}
		var jsonStore = new Ext.data.SimpleStore({
			    fields: [{name: 'text', mapping:'text'},{name: 'value', mapping:'value'}],
			    data: selectArr
		       });
	   jsonStore.loadData(selectArr);
	 
	   Ext.apply(fieldCfg,{
		   	select:select
		   ,store:jsonStore
		   ,disabled:select.disabled?select.disabled:false
		   ,width:select.offsetWidth>50?select.offsetWidth:50
		   });
		 
     return new JDS.bpm.Form.Field.ComboBox(fieldCfg);
	}
	
	/**
	 * 
	 * @param {Object} fieldCfg
	 */
	this.changeMultipleSelect=function(fieldCfg){
     	var select=fieldCfg.node.dom;
		var selectArr=[];
		for (var l = 0; l < select.length; l++) {
			var combo={};
			Ext.apply(combo,{
				value: select[l].value,
				text: select[l].text
			});
			selectArr.push(combo);
		}
		
		var jsonStore = new Ext.data.SimpleStore({
			    fields: [{name: 'text', mapping:'text'},{name: 'value', mapping:'value'}],
			    data: selectArr
		       });
	   jsonStore.loadData(selectArr);
	   Ext.apply(fieldCfg,{
		   	select:select
		   ,store:jsonStore
		   ,disabled:select.disabled?select.disabled:false
		   ,width:select.offsetWidth>50?select.offsetWidth:50
		   });
	 return new JDS.bpm.Form.Field.MultipleComboBox(fieldCfg);
	   
	}	  
	

	for(var i=0;i<this.fieldNodes.getCount();i++){
	    var node=this.fieldNodes.item(i);
	    var dom=node.dom;
		dom.id=Ext.id();
	    var tagName= dom.tagName.toLowerCase();
	    var nothandle=dom.nothandle;
	    if(!nothandle||nothandle!="true"){	
			var fieldCfg={
				activityInstId:this.activityInstId,
				node:node,
				id:Ext.id(),
				pid:dom.pid,
				xy:node.getXY(),
				width:dom.offsetWidth,
				height:dom.offsetHeight,
				value:dom.value,
				name:dom.name,
				theoremexpression:dom.theoremexpression?dom.theoremexpression:dom.name,
				msgTarget:'qtip',
				applyTo:tagName=='select'? undefined :dom.id,
				transform:tagName!='select'? undefined:dom.id
			};
			if (dom.datatype){	
				eval("var extConfig="+dom.datatype+"."+dom.custype); 
				Ext.applyIf(fieldCfg, extConfig); 
				
		    }
			 
			if (dom.basearr){
				try{
				eval("var baseConfig="+dom.basearr+".basearr"); 
				Ext.applyIf(fieldCfg, baseConfig); 
				}catch(e){
					
				}
			
				
		    }
				
	      if(tagName){
			  var newField;
			  switch (tagName) {
			  	case "text":
					if (!this.print){
						  newField = new JDS.bpm.Form.Field.HtmlEditorField(fieldCfg,this.activityInstId);
					}else{
						dom.outerHTML=fieldCfg.value;
					}
				   break;
			  	case "textarea":
				 var custype="textareatype";
				  if (dom.custype){
				  	custype= dom.custype;
				  }
				
				     switch (custype.toLowerCase()) {
					 	case "textareatype":
						 if (!this.print){
						 	newField = new JDS.bpm.Form.Field.TextArea(fieldCfg);
							}else{
								dom.outerHTML=fieldCfg.value;
							}
					 		
					 		break;
					 	case "htmledittype":
						 if (!this.print){
						 	newField = new JDS.bpm.Form.Field.HtmlEditorField(fieldCfg);
							}else{
								dom.outerHTML=fieldCfg.value;
							}
					 		break;
						case "opiniontype":
					 		var opinionfield = new JDS.bpm.Form.Field.CommentField({activityInstId:this.activityInstId,
								dom:dom,
								activityInstHistoryId:formModule.baseField.get('activityInstHistoryId'),
								fieldname:fieldCfg.name,
								readonly:fieldCfg.readOnly?fieldCfg.readOnly:false,
								formname:this.formname
							});
							
					 		break;
						default:
							newField = new JDS.bpm.Form.Field.TextArea(fieldCfg);
					 }
				   break;
				case "input":
				    var custype=dom.custype ? dom.custype: dom.type;
				    switch (custype.toLowerCase()) {
					    case "texttype":	 
						
							if (!this.print){
							
								  newField=new JDS.bpm.Form.Field.TextField(fieldCfg);
								
							}else{
								dom.outerHTML=fieldCfg.value;
							}
						
						break;
						case "radio":	
						 newField=new JDS.bpm.Form.Field.Radio(fieldCfg);
						 break;
						case "checkbox":	 
						 newField=new JDS.bpm.Form.Field.Checkbox(fieldCfg);
						break;
						case "numbertype":	 
							if (!this.print){
								 newField=new JDS.bpm.Form.Field.NumberField(fieldCfg);
							}else{
								dom.outerHTML=fieldCfg.value;
							}
						
						break;
						case "datetype":	 
						if (!this.print){
							    newField=new JDS.bpm.Form.Field.DateField(fieldCfg);
						       JDS.bpm.Form.Field.setDivInline(newField.getEl().dom.parentNode);
							}else{
								dom.outerHTML=fieldCfg.value;
							}
						 
						case "hidden":
							if (hiddenInput.containsKey()) {
								hiddenInput.add(fieldCfg.name, fieldCfg.node.dom);
							}
						break;	
				
						default:
						
						 newField=new JDS.bpm.Form.Field.TextField(fieldCfg);	
						
					}
				  break;
				
				case "select": 
				     
					 var select=fieldCfg.node.dom;
					 var hiddeFileld=this.selectFieldByName('hidden'+select.name);
				   var custype="select";
				  if (!hiddeFileld){
				  	break;
				  }
				    if (hiddeFileld.custype){
						custype=hiddeFileld.custype;
					}
					 var custype=hiddeFileld.custype;
					
					 if (hiddeFileld.basearr){
					 	try{
						eval("var baseConfig="+hiddeFileld.basearr+".basearr"); 
						Ext.applyIf(fieldCfg, baseConfig); 
						}catch(e){
							alert(hiddeFileld.basearr);
				           }
				      }	
					
					  if (hiddeFileld.groupLayout ){
							eval("var extConfig="+hiddeFileld.groupLayout+".groupLayout"); 
							Ext.applyIf(fieldCfg, extConfig); 
					    } 
					if (hiddeFileld.grouplayout ){
						
							eval("var extConfig="+hiddeFileld.grouplayout+".grouplayout"); 
							Ext.applyIf(fieldCfg, extConfig); 
					    } 
						 
					 switch (custype.toLowerCase()) {
						   case "select": 
						 
//						   	if (!this.print) {
							  newField=this.changeSelect(fieldCfg);
//							}else{
//								JDS.bpm.Form.Field.setDivInline(dom.parentNode);
//								JDS.bpm.Form.Field.setDivInline(dom.parentNode.parentNode);
//								dom.outerHTML=" "+fieldCfg.value+" ";
//							}
						    
							break;
							case "multipleselect": 
							  newField=this.changeMultipleSelect(fieldCfg);
							break;
							case "radiolist": 
							  newField=this.changeRadioGroup(fieldCfg);
							break;
							case "checkboxlist": 
						
							  newField=this.changeCheckboxGroup(fieldCfg);
							break;
							default:
							newField=this.changeSelect(fieldCfg);
							break;
					 }
					  break;
			  };
	  
			  if (newField){
			  	     newField.form=this;
				  		this.extForm.add(newField);
			        this.fields.push(newField);
					newField.on("change", function (){JDS.bpm.Form.Field.updateCell(this);}); 
					JDS.bpm.Form.Field.putExpression(newField);
					try{
				
					
					}catch(e){
						
					}
				  
			   }
				  
		  }
	    };
		
	  }
}



/**
 * 
 */	
JDS.bpm.Form.FormCallFn = {
	callFormFn: function(){
		var body = arguments[0];
		var text = arguments[2].responseText;
		
		var form = new JDS.bpm.Form(arguments[0].child("form"),this.module);
		
		
		
		try {
			var scripts= arguments[0].select("script");
			  
	         scripts.each(function(node){
	      	try {eval(node.dom.innerHTML)}catch(e){};
	     	});
			//text.evalScripts();
		}catch (e) {
			//JDS.alert(e);
		}
		this.module.winel.unmask();
		this.module.allForm.add(this.formId,form)
		
		var formTab=this.module.subPanel;
		if (formTab.getXType() == 'tabpanel') {
		  if (this.module.allForm.getCount()<formTab.items.length){
		   formTab.activate(this.module.allForm.getCount()); 
		  }
		  
			
		}
		form.formId=this.formId;
	}

};




	
