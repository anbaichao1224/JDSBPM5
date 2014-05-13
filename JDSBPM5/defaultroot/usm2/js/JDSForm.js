
/**
 *
 * @param {Object} config
 */
JDS.usm2.FormPanel = function(config){
    Ext.apply(this, config);
    this.xtype = 'Module';
    Ext.app.Module.superclass.constructor.call(this);
      this.getXtype = function(){
        return this.xtype;
    };
    this.init();
    
}

Ext.extend(Ext.app.Module, Ext.form.FormPanel, {
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
		  
});

