/**
 * 后台数据获取类
 * @param {Object} cfg
 */
JDS.bpm.Form.ExpressionData = function(cfg){
	Ext.apply(this, cfg);
}
			
Ext.extend(JDS.bpm.Form.ExpressionData, Ext.util.Observable, {
	render:function(){
		this.form.fields.each(function(f){
		  for(var k=0;k<this.updateFields.length;k++){
		
			  if ( this.updateFields[k].name==f.getName()){
				if (f.getXType()=='combo' || f.selectedClass=='x-combo-selected'){
				     var values=eval(this.updateFields[k].value);
		             f.store.loadData(values);
		              f.reset();
				 }else{
				  	f.setValue(this.updateFields[k].value);
				 }
			  }
		  }
		  
		 },this);
	
	},
	load: function(){
		JDS.ajax.Connection.load(this);
	}
  }
)
