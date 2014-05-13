Ext.namespace('JDS.exp.mainPanel');

JDS.exp.mainPanel.MainPanel = function(win) {
  this.id = "mainPanel_" + win.extId;
  this.mainAreaId = win.mainAreaId;
  this.win=win;
  this.view;
  this.ds;
  this.init();
}
JDS.exp.mainPanel.MainPanel.prototype.init = function() {
  var itemsAreaEl = Ext.get(this.mainAreaId),
      hide = itemsAreaEl.unmask.createDelegate(itemsAreaEl);
  
  var url = '/explorer/toJsonForTree.action';
  this.ds = new JDS.exp.store(null, itemsAreaEl, hide);
//  this.destinationChooser = new Explorer.destinationChooser();

  this.view = new JDS.exp.view({
    panel:this,
    renderTo: Ext.get(this.mainAreaId).createChild({tag : 'div',cls : 'viewClass'}),
    store : this.ds
  });
  
  this.grid = new JDS.exp.view.Viewer.GridStyle({
    ds : this.ds
    ,
    panel:this
  });

  this.itemsMainPanel = new Ext.Panel({
    columnWidth: 1,
    border : true,
    autoScroll : true,
    items : this.view
//    ,
//    html : {
//      tag :'div',
//      id : this.id+"gridViewHolder"
//    }
  });

}