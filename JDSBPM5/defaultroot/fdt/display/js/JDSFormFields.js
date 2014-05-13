
/**
 * 前端字段转换
 * @param {Object} field
 */
JDS.bpm.Form.Field={
	updateCell : function(field){
		  var isSend=false 
		 field.form.fields.each(function(f){
		  var theoremexpression=f.getExpression();
			if (theoremexpression.indexOf(field.getName())>-1 && theoremexpression!=field.getExpression()){
			     isSend=true;
			}
		 });
		 
		
		
		 if (isSend ||(field.isInstProcess && field.isInstProcess !='false')){
		 	var str = "rawUpdataList[0]=" + field.getName() + "&" + field.getName() + "=" + field.getValue() + "&activityInstId=" + field.getActivityInstId();
		    str = field.form.expressArr.join("&")+"&"+str;
			 if (field.isInstProcess && field.isInstProcess !='false'){
	  	      str =str+ "&rawUpdataProcessList[0]="+field.getName();
	         }
			
			var cfg={
	        url:context + "updatacell.action",
	        params: str,
			form:field.form
		   }
           var ExpressionData=new JDS.bpm.Form.ExpressionData(cfg).load();		
		}
	},
	
	
	putExpression:function(newField){
		var theoremexpression=newField.getExpression();
			if (theoremexpression && newField.getName()){
				type=newField.getEl().dom.custype;
				var name=newField.getName();
				if (name.indexOf("hidden")==0){
					name=name.substring(6,name.lenght);
					type="combo";
				}
				var k=newField.form.expressArr.length;
				var expressStr="cellList["+k+"].type="+type+"&cellList["+k+"].key="+name+"&cellList["+k+"].express="+encodeURIComponent(theoremexpression);
			    newField.form.expressArr.push(expressStr);
		}
				  
	},
	
	setDivInline : function(node){
		if (node.style.display != "none") {
			node.style.display = "inline"
		}
	},
	
	TextField : Ext.extend(Ext.form.TextField, {
	 initComponent : function(){
	    JDS.bpm.Form.Field.TextField.superclass.initComponent.call(this);
	}
    }),
	

	HtmlEditorField : Ext.extend(Ext.form.HtmlEditor, {	  
	 }),
	 Radio : Ext.extend(Ext.form.Radio, {	  
	 }),
	  Checkbox : Ext.extend(Ext.form.Checkbox, {	  
	 }),
	
	NumberField : Ext.extend(Ext.form.NumberField, {	
	 }),
	 
	TextArea : Ext.extend(Ext.form.TextArea, {
	 }),
	TimeField : Ext.extend(Ext.form.TimeField, {
		
	 }) ,
	DateField : Ext.extend(Ext.form.DateField, {
		initComponent : function(){
		    JDS.bpm.Form.Field.DateField.superclass.initComponent.call(this);
			if (this.readonly){
				this.setDisabled(true);
			}
	
		}
	 }),
/**	 
	MultipleComboBox : Ext.extend(Ext.ux.ItemSelector, {
		xtype:"itemselector",
	    dataFields:["code", "desc"],
		msWidth:300,
		msHeight:150,
		valueField:"code",
		displayField:"desc",
		imagePath:context+"js/ext/multiselect",
		toLegend:"已选项",
		fromLegend:"待选项",	
		initComponent : function(){
		    JDS.bpm.Form.Field.MultipleComboBox.superclass.initComponent.call(this);
		}
	 }),
*/
	ComboBox : Ext.extend(Ext.form.ComboBox, {
	   displayField: 'text',
	   valueField: 'value',
	   mode: 'local',
	   typeAhead: true, 
	   triggerAction: 'all',
	   emptyText: '全部',
	   selectOnFocus: true,
	   forceSelection: true,
	   readonly:false,
	   initComponent : function(){
		    JDS.bpm.Form.Field.ComboBox.superclass.initComponent.call(this);
			JDS.bpm.Form.Field.setDivInline(this.getEl().dom.parentNode);
			JDS.bpm.Form.Field.setDivInline(this.getEl().dom.parentNode.parentNode);
			JDS.bpm.Form.Field.setDivInline(this.getEl().dom.parentNode.parentNode.parentNode);
			JDS.bpm.Form.Field.setDivInline(this.getEl().dom.parentNode.parentNode.parentNode.parentNode);
		}
	 }),
	 
	 
	 CommentField : Ext.extend(JDS.bpm.Form.CommentPanel, {
	   initComponent : function(){
	   			if(this.dom.value != '')
	   			{
	   				this.dom.outerHTML=this.dom.value;
	   			}else
	   			{
				    JDS.bpm.Form.Field.CommentField.superclass.initComponent.call(this);
					var el =  Ext.DomHelper.insertBefore(this.dom, {tag: "div"});
						el.parentNode.id=Ext.id();
			        Ext.removeNode(this.dom); // remove it
			        this.render(el.parentNode);
				}
			  }
	 }),
	
	
	CheckboxGroup : Ext.extend(Ext.form.CheckboxGroup, {
		  xtype: 'checkboxgroup',
		  initComponent : function(){
			    JDS.bpm.Form.Field.CheckboxGroup.superclass.initComponent.call(this);
				var el =  Ext.DomHelper.insertBefore(this.select, {tag: "div"});
					el.parentNode.id=Ext.id();
		        Ext.removeNode(this.select); // remove it
		        this.render(el.parentNode);
				if (this.readonly){
					this.setReadOnly();
				}
				JDS.bpm.Form.Field.setDivInline(this.getEl().dom.parentNode);
			
		  },
		  
		setValue : function(value){   
	        this.items.each(function(f){   
	            if(value.indexOf(f.inputValue) != -1){   
	                f.setValue(true);   
	            }else{   
	                f.setValue(false);   
	            }   
	        });   
	    },   
		setReadOnly : function(){   
	        this.items.each(function(f){   
			  f.disable();
	        });   
	    },   
		
	    //以value1,value2的形式拼接group内的值   
	    getValue : function(){   
	        var re = "";   
	        this.items.each(function(f){   
	            if(f.getValue() == true){   
	                re += f.inputValue + ",";   
	            }   
	        });   
	        return re.substr(0,re.length - 1);   
	    },   
	    //在Field类中定义的getName方法不符合CheckBoxGroup中默认的定义，因此需要重写该方法使其可以被BasicForm找到   
	    getName : function(){   
	        return this.name;   
	    }   		
	 }),
		 
	RadioGroup : Ext.extend(Ext.form.RadioGroup, {
		    xtype: 'radiogroup',
			initComponent : function(){
			    JDS.bpm.Form.Field.RadioGroup.superclass.initComponent.call(this);
				var el =  Ext.DomHelper.insertAfter(this.select, {tag: "div"});
				el.parentNode.id=Ext.id();
	            Ext.removeNode(this.select); // remove it
	            this.render(el.parentNode);
				if (this.readonly){
					this.setReadOnly();
				}
				
				//JDS.bpm.Form.Field.setDivInline(this.getEl().dom.parentNode);
			},
			setReadOnly : function(){   
		        this.items.each(function(f){   
				  f.disable();
		        });   
	       }   
	})	 
}

