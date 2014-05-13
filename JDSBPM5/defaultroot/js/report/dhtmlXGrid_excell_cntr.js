/*
Copyright Scand LLC http://www.scbr.com
To use this component please contact info@scbr.com to obtain license
*/

/*
Counter eXcell v.1.0  for dhtmlxGrid 
(c)Scand LLC 2005


The corresponding  cell value in XML can contain any value (it is ignored)

*/
/**
*	@desc: auto counter editor
*	@returns: dhtmlxGrid cell editor object
*	@type: public
*/
function eXcell_cntr(cell){
	this.cell = cell;
    this.grid = this.cell.parentNode.grid;
	if ((this.grid.setOnOpenEndHandler)&&(!this.grid._ex_cntr_ready)){
		this.grid._ex_cntr_ready=true;
		this.grid.setOnOpenEndHandler(function(id){
			this.resetCounter(0);
		  /*	var ch=this.getSubItems(id).split(",");
			var val=this.cellsthis.rowsAr[this.rowsAr[id].parent_id]
			for (var i=0; i<ch.length; i++){
				if (ch[i])
					this.cells(ch[i],0).setValueA(i,1);
					}
			return true;    */
		});
	}

	this.edit = function(){}
	this.getValue = function(){
		return this.cell.parentNode.rowIndex;
	}
	this.setValue = function(val){
		this.cell.style.paddingRight = "2px";
		var cell=this.cell;
		window.setTimeout(function(){
			var val=cell.parentNode.rowIndex;
			if (val<0) val=cell.parentNode.grid.rowsCol._dhx_find(cell.parentNode)+1;
			cell.innerHTML = val;
			if (cell.parentNode.grid._fake) cell.parentNode.grid._fake.cells(cell.parentNode.idd,cell._cellIndex).setValue(val);
			cell=null;
		},100);
	}
}
dhtmlXGridObject.prototype.resetCounter=function(ind){
	for (var i=0; i<this.rowsCol.length; i++)
		this.rowsCol[i].cells[ind].innerHTML=i+1;
}
eXcell_cntr.prototype = new eXcell;
