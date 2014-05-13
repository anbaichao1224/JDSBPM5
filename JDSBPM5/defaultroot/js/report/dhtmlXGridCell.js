/*
Copyright Scand LLC http://www.scbr.com
This version of Software is free for using in GNU GPL applications. For other use or to get Professional Edition please contact info@scbr.com to obtain license
*/ 

/**
*	@desc: dhtmlxGrid cell object constructor (shouldn't be accesed directly. Use cells and cells2 methods of the grid instead)
*	@type: public
*	@returns: dhtmlxGrid cell
*/


function dhtmlXGridCellObject(obj){
	/**
	*	@desc: desctructor, clean used memory
	*	@type: public
	*/
    this.destructor=function(){
        this.cell.obj=null;
        this.cell=null;
        this.grid=null;
        this.base=null;
        return null;
    }
	this.cell = obj;
	/**
	*	@desc: gets Value of cell
	*	@type: public
	*/
	this.getValue = function(){
                        if ((this.cell.firstChild)&&(this.cell.firstChild.tagName=="TEXTAREA" ||this.cell.firstChild.tagName=="INPUT"))
                        return this.cell.firstChild.value;
                        else
						return this.cell.innerHTML._dhx_trim();//innerText;
					}
	this.getId = function(){
                      return this.cell.id;
					}
					
	this.getCnId = function(){
                      return this.cell.cnId;
					}

	this.getExpression = function(){
                      return this.cell.theoremExpression;
					}
	this.getDebugexpression = function(){
                      return this.cell.debugexpression;
					}

					
	this.getTitle = function(){
	    editTitle(this);
		if (this.cell.getAttribute("tit") != null)
		{
			return this.cell.tit;
		}
		
		
		return this.getValue();
	}		
            

	/**
	*	@desc: gets math formula of cell if any
	*	@type: public
	*/
	this.getMathValue = function(){
                    if (this.cell._val)
						return this.cell._val;//innerText;
                    else return  this.getValue();
					}
	/**
	*	@desc: determ. font style if it was set
	*	@returns: font name only if it was set for the cell
	*	@type: public
	*/
	this.getFont = function(){
						arOut = new Array(3);
						if(this.cell.style.fontFamily)
							arOut[0] = this.cell.style.fontFamily
						if(this.cell.style.fontWeight=='bold' || this.cell.parentNode.style.fontWeight=='bold')
							arOut[1] = 'bold';
						if(this.cell.style.fontStyle=='italic' || this.cell.parentNode.style.fontWeight=='italic')
							arOut[1] += 'italic';
						if(this.cell.style.fontSize)
							arOut[2] = this.cell.style.fontSize
						else
							arOut[2] = "";
						return arOut.join("-")
					}
	/**
	*	@desc: determ. cell's text color
	*	@returns: cell's text color
	*	@type: public
	*/
	this.getTextColor = function(){
							if(this.cell.style.color)
								return this.cell.style.color
							else
								return "#000000";
						}
	/**
	*	@desc: determ. cell's background color
	*	@returns: cell's background color
	*	@type: public
	*/
	this.getBgColor = function(){
							if(this.cell.bgColor)
								return this.cell.bgColor
							else
								return "#FFFFFF";
						}
	/**
	*	@desc: determines horisontal align od the cell
	*	@returns: horisontal align of cell content
	*	@type: public
	*/
	this.getHorAlign = function(){
							if(this.cell.style.textAlign)
								return this.cell.style.textAlign;
							else if(this.cell.align)
								return this.cell.align
							else
								return "left";
						}
	/**
	*	@desc: gets width of the cell in pixel
	*	@returns: width of the cell in pixels
	*	@type: public
	*/
	this.getWidth = function(){
		return this.cell.scrollWidth;
	}

	/**
	*	@desc: sets font family to the cell
	*	@param: val - string in format: Arial-bold(italic,bolditalic,underline)-12px
	*	@type: public
	*/
	this.setFont = function(val){
						fntAr = val.split("-");
						this.cell.style.fontFamily = fntAr[0];
						this.cell.style.fontSize = fntAr[fntAr.length-1]
						if(fntAr.length==3){
							if(/bold/.test(fntAr[1]))
								this.cell.style.fontWeight = "bold";
							if(/italic/.test(fntAr[1]))
								this.cell.style.fontStyle = "italic";
							if(/underline/.test(fntAr[1]))
								this.cell.style.textDecoration = "underline";

						}

					}
	/**
	*	@desc: sets text color to the cell
	*	@param: val - color value (name or hex)
	*	@type: public
	*/
	this.setTextColor = function(val){
							this.cell.style.color = val;
						}
	/**
	*	@desc: sets background color to the cell
	*	@param: val - color value (name or hex)
	*	@type: public
	*/
	this.setBgColor = function(val){
							if(val=="")
								val = null;
							this.cell.bgColor = val;
						}
	/**
	*	@desc: sets horisontal align to the cell
	*	@param: val - value in single-letter or full format(exmp: r or right)
	*	@type: public
	*/
	this.setHorAlign = function(val){
							if(val.length==1){
								if(val=='c')
									this.cell.style.textAlign = 'center'
								else if(val=='l')
									this.cell.style.textAlign = 'left';
								else
									this.cell.style.textAlign = 'right';
							}else
								this.cell.style.textAlign = val
						}
	/**
	*	@desc: determines whether cell value was changed
	*	@returns: true if cell value was changed, otherwise - false
	*	@type: public
	*/
	this.wasChanged = function(){
							if(this.cell.wasChanged)
								return true;
							else
								return false;
						}
	/**
	*	@desc: determines whether first child of the cell is checkbox or radio
	*	@returns: true if first child of the cell is input element of type radio or checkbox
	*	@type: public
	*/
	this.isCheckbox = function(){
							var ch = this.cell.firstChild;
							if(ch && ch.tagName=='INPUT'){
								type = ch.type;
								if(type=='radio' || type=='checkbox')
									return true;
								else
									return false;
							}else
								return false;
						}
	/**
	*	@desc: determines whether radio or checkbox inside is checked
	*	@returns: true if first child of the cell is checked
	*	@type: public
	*/
	this.isChecked = function(){
							if(this.isCheckbox()){
								return this.cell.firstChild.checked;
							}
						}
	/**
	*	@desc: determines whether cell content (radio,checkbox) is disabled
	*	@returns: true if first child of the cell is disabled
	*	@type: public
	*/
	this.isDisabled = function(){
							if(this.isCheckbox()){
								return this.cell.firstChild.disabled;
							}
						}
	/**
	*	@desc: checks checkbox or radion
	*	@param: fl - true or false
	*	@type: public
	*/
	this.setChecked = function(fl){
							if(this.isCheckbox()){
								if(fl!='true' && fl!=1)
									fl = false;
								this.cell.firstChild.checked = fl;
							}
						}
	/**
	*	@desc: disables radio or checkbox
	*	@param: fl - true or false
	*	@type: public
	*/
	this.setDisabled = function(fl){
							if(this.isCheckbox()){
								if(fl!='true' && fl!=1)
									fl = false;
								this.cell.firstChild.disabled = fl;
                                if (this.disabledF) this.disabledF(fl);
							}
						}
}

/**
*	@desc: sets value to the cell
*	@param: val - new value
*	@type: public
*/
dhtmlXGridCellObject.prototype.setValue = function(val){
						if((typeof(val)!="number") && val.toString()._dhx_trim()==""){
							val="&nbsp;"
                            this.cell._clearCell=true;
                            }
						this.setCValue(val);
				}

dhtmlXGridCellObject.prototype.setCValue = function(val,val2){
						this.cell.innerHTML = val;
//#__pro_feature:21092006{
//#on_cell_changed:23102006{
                        if (this.grid._onCCH)
							this.grid._onCCH(this.cell.parentNode.idd,this.cell._cellIndex, val2||val);
//#}
//#}
}


/**
*	@desc: sets text representation of cell ( setLabel doesn't triger math calculations as setValue do)
*	@param: val - new value
*	@type: public
*/
dhtmlXGridCellObject.prototype.setLabel = function(val){
						this.cell.innerHTML = val;
				}

/**
*	@desc: geth math code of ExCell
*	@param: val - new value
*	@type: public
*/
dhtmlXGridCellObject.prototype.getMath = function(val){
                    if (this._val) return this.val;
                    else
                        return this.getValue();
				}

/**
*	@desc: dhtmlxGrid cell editor constructor (base for all eXcells). Shouldn't be accessed directly
*	@returns: dhtmlxGrid cell editor object
*	@type: public
*/
function eXcell(){
	this.obj = null;//editor
	//this.cell = null//cell to get value from
	this.val = null;//current value (before edit)
	/**
	*	@desc: occures on space for example 
	*	@type: private
	*/
	this.changeState = function(){return false}
	/**
	*	@desc: opens editor
	*	@type: private
	*/
	
	this.edit = function(){this.val = this.getValue()}//
	/**
	*	@desc: return value to cell, closes editor
	*	@returns: if cell's value was changed (true) or not
	*	@type: private
	*/
	this.detach = function(){return false}//
	/**
	*	@desc: gets position (left-right) of element
	*	@param: oNode - element to get position of
	*	@type: private
	*	@topic: 8
	*/
	this.getPosition = function(oNode){
			   var oCurrentNode=oNode;
			   var iLeft=0;
			   var iTop=0;
			   while(oCurrentNode.tagName!="BODY"){
			      iLeft+=oCurrentNode.offsetLeft;
				  iTop+=oCurrentNode.offsetTop;
			      oCurrentNode=oCurrentNode.offsetParent;
			   }
			   return new Array(iLeft,iTop);
			}
	
}
eXcell.prototype = new dhtmlXGridCellObject;

/**
*	@desc: simple text editor
*	@returns: dhtmlxGrid cell editor object
*	@type: public
*/
function eXcell_ed(cell){
	if (cell){
		this.cell = cell;
		this.grid = this.cell.parentNode.grid;
     
	}
	this.edit = function(){
					this.cell.atag=((!this.grid.multiLine)&&(_isKHTML||_isMacOS||_isFF))?"INPUT":"TEXTAREA";
					this.val = this.getValue();
					this.obj = document.createElement(this.cell.atag);
					this.obj.style.height = (this.cell.offsetHeight-(_isIE?4:2))+"px";
                    this.obj.className="dhx_combo_edit";
				   	this.obj.wrap = "soft";
					this.obj.style.textAlign = this.cell.align;
					this.obj.onclick = function(e){(e||event).cancelBubble = true}
					this.obj.onmousedown = function(e){(e||event).cancelBubble = true}
					this.obj.value = this.val
					this.cell.innerHTML = "";
					this.cell.appendChild(this.obj);
				  	if (_isFF) {
						this.obj.style.overflow="visible";
						if ((this.grid.multiLine)&&(this.obj.offsetHeight>=18)&&(this.obj.offsetHeight<40)){
							this.obj.style.height="36px";
							this.obj.style.overflow="scroll";
						}
					}
                    this.obj.onselectstart=function(e){  if (!e) e=event; e.cancelBubble=true; return true;  };
					this.obj.focus();
                    this.obj.select();
				}
	this.getValue = function(){
        if ((this.cell.firstChild)&&((this.cell.atag)&&(this.cell.firstChild.tagName==this.cell.atag)))
            return this.cell.firstChild.value;
        else
    		return this.cell.innerHTML.toString()._dhx_trim();
	}

	this.detach = function(){
					this.setValue(this.obj.value);

					
					return ("'"+this.val)!=("'"+this.getValue());
				}
        
  	


}
eXcell_ed.prototype = new eXcell;

/**
*	@desc: pure text editor ( HTML not supported )
*	@returns: dhtmlxGrid cell editor object
*	@type: public
*/
function eXcell_edtxt(cell){
	try{
		this.cell = cell;
		this.grid = this.cell.parentNode.grid;
	}catch(er){}
	this.getValue = function(){
        if ((this.cell.firstChild)&&((this.cell.atag)&&(this.cell.firstChild.tagName==this.cell.atag)))
            return this.cell.firstChild.value;
        else
    		return (_isIE?this.cell.innerText:this.cell.textContent);
	}
	this.setValue = function(val){
						if(!val || val.toString()._dhx_trim()=="")
							val=" ";
						this.setCTxtValue(val);
	}
}
eXcell_edtxt.prototype = new eXcell_ed;

//#__pro_feature:21092006{
//#data_format:12052006{
/**
*	@desc: simple numeric text editor
*	@returns: dhtmlxGrid cell editor object
*	@type: public
*	@edition: professional
*/
function eXcell_edn(cell){
	if (cell){
		this.cell = cell;
		this.grid = this.cell.parentNode.grid;
	}
	this.getValue = function(){
		//this.grid.editStop();
        if ((this.cell.firstChild)&&(this.cell.firstChild.tagName=="TEXTAREA"))
            return this.cell.firstChild.value;
        else
		return this.grid._aplNFb(this.cell.innerHTML.toString()._dhx_trim(),this.cell._cellIndex);
	}

	this.detach = function(){
                    var tv=this.obj.value;
					this.setValue(tv);
					return this.val!=this.getValue();
				}
}
eXcell_edn.prototype = new eXcell_ed;
eXcell_edn.prototype.setValue = function(val){
						if(!val || val.toString()._dhx_trim()=="")
							val="0"
						this.setCValue(this.grid._aplNF(val,this.cell._cellIndex));
				}

//#}
//#}

/**
*	@desc: checkbox editor
*	@returns: dhtmlxGrid cell editor object
*	@type: public
*/
function eXcell_ch(cell){
	if(cell){
		this.cell = cell;
		this.grid = this.cell.parentNode.grid;
		this.cell.obj = this;
	}

    this.disabledF=function(fl){
        if ((fl==true)||(fl==1))
            this.cell.innerHTML=this.cell.innerHTML.replace("item_chk0.","item_chk0_dis.").replace("item_chk1.","item_chk1_dis.");
        else
            this.cell.innerHTML=this.cell.innerHTML.replace("item_chk0_dis.","item_chk0.").replace("item_chk1_dis.","item_chk1.");
    }

	this.changeState = function(){
					//nb:
                    if ((!this.grid.isEditable)||(this.cell.parentNode._locked)||(this.isDisabled())) return;
						if(this.grid.callEvent("onEditCell",[0,this.cell.parentNode.idd,this.cell._cellIndex])){
							this.val = this.getValue()
							if(this.val=="1")
								this.setValue("0")
							else
								this.setValue("1")

							this.cell.wasChanged=true;
							//nb:
							this.grid.callEvent("onEditCell",[1,this.cell.parentNode.idd,this.cell._cellIndex]);
							this.grid.callEvent("onCheckbox",[this.cell.parentNode.idd,this.cell._cellIndex,(this.val!='1')]);
						}else{//preserve editing (not tested thoroughly for this editor)
							this.editor=null;
						}
				}
	this.getValue = function(){
						try{
							return this.cell.chstate.toString();
						}catch(er){
							return null;
						}
					}

	this.isCheckbox = function(){
						return true;
					}
	this.isChecked = function(){
						if(this.getValue()=="1")
							return true;
						else
							return false;
					}
	this.setChecked = function(fl){
	this.setValue(fl.toString())
	}
	this.detach = function(){
						return this.val!=this.getValue();
					}
}
eXcell_ch.prototype = new eXcell;
eXcell_ch.prototype.setValue = function(val){
                        this.cell.style.verticalAlign = "middle";//nb:to center checkbox in line
						//val can be int
						if (val){
	                        val=val.toString()._dhx_trim();
							if ((val=="false")||(val=="0")) val="";
							}
						if(val){
							val = "1";
							this.cell.chstate = "1";
						}else{
							val = "0";
							this.cell.chstate = "0"
						}
						var obj = this;
						this.setCValue("<img src='"+this.grid.imgURL+"item_chk"+val+".gif' onclick='new eXcell_ch(this.parentNode).changeState(); (arguments[0]||event).cancelBubble=true; '>",this.cell.chstate);
					}



/**
*	@desc: radio editor
*	@returns: dhtmlxGrid cell editor object
*	@type: public
*/
function eXcell_ra(cell){
	this.base = eXcell_ch;
	this.base(cell)
    this.grid = cell.parentNode.grid;

    this.disabledF=function(fl){
        if ((fl==true)||(fl==1))
            this.cell.innerHTML=this.cell.innerHTML.replace("radio_chk0.","radio_chk0_dis.").replace("radio_chk1.","radio_chk1_dis.");
        else
            this.cell.innerHTML=this.cell.innerHTML.replace("radio_chk0_dis.","radio_chk0.").replace("radio_chk1_dis.","radio_chk1.");
    }

	this.changeState = function(){
                        if ((!this.grid.isEditable)||(this.cell.parentNode._locked)) return;
						if(this.grid.callEvent("onEditCell",[0,this.cell.parentNode.idd,this.cell._cellIndex])!=false){
							this.val = this.getValue()
							if(this.val=="1")
								this.setValue("0")
							else
								this.setValue("1")
							this.cell.wasChanged=true;
							//nb:
							this.grid.callEvent("onEditCell",[1,this.cell.parentNode.idd,this.cell._cellIndex]);
							this.grid.callEvent("onCheckbox",[this.cell.parentNode.idd,this.cell._cellIndex,(this.val!='1')]);
						}else{//preserve editing (not tested thoroughly for this editor)
							this.editor=null;
						}
				}

}
eXcell_ra.prototype = new eXcell_ch;
eXcell_ra.prototype.setValue = function(val){
						this.cell.style.verticalAlign = "middle";//nb:to center checkbox in line
						if (val){
	                        val=val.toString()._dhx_trim();
							if ((val=="false")||(val=="0")) val="";
							}
						if(val){
							val = "1";
							this.cell.chstate = "1";
							var cell=this.cell;
							this.grid.forEachRow(function(id){
									var z=this.cells(id,cell._cellIndex);
									if ((z.isChecked())&&(id!==cell.parentNode.idd)){
    									z.setValue("0")
                                        this.callEvent("onEditCell",[1,id,cell._cellIndex]);
									}										
								});							
						}else{
							val = "0";
							this.cell.chstate = "0"
						}
						var obj = this;
						this.setCValue("<img src='"+this.grid.imgURL+"radio_chk"+val+".gif' onclick='this.parentNode.obj.changeState()'>",this.cell.chstate);
					}
/**
*	@desc: multilene popup editor
*	@returns: dhtmlxGrid cell editor object
*	@type: public
*/
function eXcell_txt(cell){
	if (cell){
		this.cell = cell;
		this.grid = this.cell.parentNode.grid;
	}
	this.edit = function(){
					this.val = this.getValue()
					this.obj = document.createElement("TEXTAREA");
                    this.obj.className="dhx_textarea";

					this.obj.onclick = function(e){(e||event).cancelBubble = true}
					var arPos = this.grid.getPosition(this.cell);//,this.grid.objBox
                    if (!this.cell._clearCell)
    					this.obj.value = this.val;

					this.obj.style.display = "";
					this.obj.style.textAlign = this.cell.align;
					if (_isFF){
						var z_ff=document.createElement("DIV");
						z_ff.appendChild(this.obj);
						z_ff.style.overflow="auto";
						z_ff.className="dhx_textarea";
						this.obj.style.margin="0px 0px 0px 0px";
					    this.obj.style.border="0px";
	  					this.obj=z_ff;
						}
					document.body.appendChild(this.obj);//nb:
					this.obj.onkeydown=function(e){
						var ev=(e||event);
						if (ev.keyCode==9) {
							globalActiveDHTMLGridObject.entBox.focus();
							globalActiveDHTMLGridObject.doKey({keyCode:ev.keyCode,shiftKey:ev.shiftKey,srcElement:"0"});
							return false;
							}
					}

					this.obj.style.left = arPos[0]+"px";
					this.obj.style.top = arPos[1]+this.cell.offsetHeight+"px";
					if(this.cell.scrollWidth<200)
						var pw=200;
					else
						var pw=this.cell.scrollWidth;
					this.obj.style.width = pw+(_isFF?18:16)+"px"
					if (_isFF){
						 this.obj.firstChild.style.width = parseInt(this.obj.style.width)+"px";
						 this.obj.firstChild.style.height = this.obj.offsetHeight-3+"px";
						 }

					   this.obj.focus();
					   if (_isFF) this.obj.firstChild.focus();
					   else this.obj.focus()

				}
	this.detach = function(){
					var a_val="";
					if (_isFF) a_val=this.obj.firstChild.value;
					else a_val=this.obj.value;
                    if (a_val=="") {
                        this.cell._clearCell=true;
                        }
                    else this.cell._clearCell=false;
					this.setValue(a_val);
					//isIE()?this.obj.removeNode(true):this.grid.objBox.removeChild(this.obj);
					document.body.removeChild(this.obj);

					return this.val!=this.getValue();
				}
	this.getValue = function(){
                        if (this.cell.firstChild){
							if (this.cell.firstChild.tagName=="TEXTAREA")
		                        return this.obj.firstChild.value;
							else
							if (this.cell.firstChild.tagName=="DIV")
		                        return this.obj.firstChild.firstChild.value;
						}
						
                        if ((!this.grid.multiLine))
							return this.cell._brval;
						else
							return this.cell.innerHTML.replace(/<br[^>]*>/gi,"\n")._dhx_trim();//innerText;
					}
}
eXcell_txt.prototype = new eXcell;

function eXcell_txttxt(cell){
	if (cell){
		this.cell = cell;
		this.grid = this.cell.parentNode.grid;
	}
	this.getValue = function(){
        if ((this.cell.firstChild)&&(this.cell.firstChild.tagName=="TEXTAREA"))
            return this.cell.firstChild.value;
        else
    		return (_isIE?this.cell.innerText:this.cell.textContent);
	}
	this.setValue = function(val){
						if(!val || val.toString()._dhx_trim()=="")
							val=" ";
						this.setCTxtValue(val);
	}
}

eXcell_txttxt.prototype = new eXcell_txt;



eXcell_txt.prototype.setValue = function(val){
						if(!val || val.toString()._dhx_trim()==""){
							val="&nbsp;"
                            this.cell._clearCell=true;
                            }
						else this.cell._clearCell=false;

						this.cell._brval=val;
  						if ((!this.grid.multiLine))
							this.setCValue(val,val);
						else
							this.setCValue(val.replace(/\n/g,"<br/>"),val);
				}

/**
*	@desc: combobox editor
*	@returns: dhtmlxGrid cell editor object
*	@type: public
*/
function eXcell_co(cell){
     	if (cell){
		   if (cell.id.indexOf('HEAD')>-1)
	     	{
						cell.style.backgroundColor=tdBgColor;
					
		    }
		cell.style.backgroundImage="url('/report/imgs/sort.gif')";
		this.cell = cell;
		this.grid = this.cell.parentNode.grid;
		this.combo = (this.cell._combo||this.grid.getCombo(this.cell._cellIndex));
		this.editable = true
   }
    this.shiftNext=function(){

        var z=this.list.options[this.list.selectedIndex+1];
        if (z) z.selected=true;
        this.obj.value=this.list.options[this.list.selectedIndex].text;

        return true;
    }
    this.shiftPrev=function(){

        var z=this.list.options[this.list.selectedIndex-1];
        if (z) z.selected=true;
        this.obj.value=this.list.options[this.list.selectedIndex].text;

       return true;
    }

	this.edit = function(){
						this.val = this.getValue();
						this.text = this.getText()._dhx_trim();
						var arPos = this.grid.getPosition(this.cell)//,this.grid.objBox)

						this.obj = document.createElement("TEXTAREA");
                        this.obj.className="dhx_combo_edit";
						this.obj.style.height=(this.cell.offsetHeight-4)+"px";

							this.obj.wrap = "soft";
							this.obj.style.textAlign = this.cell.align;
							this.obj.onclick = function(e){(e||event).cancelBubble = true}
							this.obj.value = this.text
							this.obj.onselectstart=function(e){if(!e)e=event;e.cancelBubble=true;return true;};							

                            this.list =  document.createElement("SELECT");
                        	this.list.editor_obj = this;
                            this.list.className='dhx_combo_select';
                            this.list.style.width=this.cell.offsetWidth+"px";
							this.list.style.left = arPos[0]+"px";//arPos[0]
							this.list.style.top = arPos[1]+this.cell.offsetHeight+"px";//arPos[1]+this.cell.offsetHeight;
							this.list.onclick = function(e){
														var ev = e||window.event;
														var cell = ev.target||ev.srcElement
                                                        //tbl.editor_obj.val=cell.combo_val;
                                                        if (cell.tagName=="OPTION") cell=cell.parentNode;
														cell.editor_obj.setValue(cell.value);
                                                        cell.editor_obj.editable=false;
														cell.editor_obj.grid.editStop();
													}
							var comboKeys = this.combo.getKeys();
							var fl=false
                            var selOptId=0;
							for(var i=0;i<comboKeys.length;i++){
									var val = this.combo.get(comboKeys[i])
                                    this.list.options[this.list.options.length]=new Option(val,comboKeys[i]);
									if(comboKeys[i]==this.val){
                                        selOptId=this.list.options.length-1;
                                        fl=true;
                                        }
                                    }

							if(fl==false){//if no such value in combo list
      						   this.list.options[this.list.options.length]=new Option(this.text,this.val===null?"":this.val);
                               selOptId=this.list.options.length-1;
							}
						document.body.appendChild(this.list)//nb:this.grid.objBox.appendChild(this.listBox);
                        this.list.size="6";						
                        this.cstate=1;
						if(this.editable){
							this.cell.innerHTML = "";
						}
                        else {
                            this.obj.style.width="1px";
                            this.obj.style.height="1px";
                            }
							this.cell.appendChild(this.obj);
                            this.list.options[selOptId].selected=true;
							//fix for coro - FF scrolls grid in incorrect position
							if ((!_isFF)||(this.editable)){
   								this.obj.focus();
	   						   	this.obj.focus();
							}
                            if (!this.editable){
                                this.obj.style.visibility="hidden";
                                //this.list.focus();
                            }
					}

	this.getValue = function(){
						return ((this.cell.combo_value==window.undefined)?"":this.cell.combo_value);
					}
	this.detach = function(){
						if(this.val!=this.getValue()){
							this.cell.wasChanged = true;
			   }
						if(this.list.parentNode!=null){
                        if (this.editable)
							if(this.obj.value._dhx_trim()!=this.text){
								this.setValue(this.obj.value)
							}else{
								this.setValue(this.val)
							}
                        else
                            this.setValue(this.list.value)
						}
							if(this.list.parentNode)
								this.list.parentNode.removeChild(this.list);
							if(this.obj.parentNode)
								this.obj.parentNode.removeChild(this.obj);

						return this.val!=this.getValue();
					}
}
eXcell_co.prototype = new eXcell;
eXcell_co.prototype.getText = function(){ return this.cell.innerHTML; }
eXcell_co.prototype.setValue = function(val){
						if (typeof(val)=="object"){
							var optCol=this.grid.xmlLoader.doXPath("./option",val);
                            if (optCol.length)
                            	this.cell._combo=new dhtmlXGridComboObject();
                                for (var j=0;j<optCol.length; j++)
									this.cell._combo.put(optCol[j].getAttribute("value"),optCol[j].firstChild?optCol[j].firstChild.data:"");
							val=val.firstChild.data;	
						}
						if ((val||"").toString()._dhx_trim()=="")
							val=null

                        if (val!==null)
  						    this.setCValue((this.cell._combo||this.grid.getCombo(this.cell._cellIndex)).get(val) || val,val);
                        else
                            this.setCValue("&nbsp;",val);

						this.cell.combo_value = val;
					}
/**
*	@desc: selectbox editor
*	@returns: dhtmlxGrid cell editor object
*	@type: public
*/
function eXcell_coro(cell){
	this.base = eXcell_co;
	this.base(cell)
	this.editable = false;
    this.edit = function(){};
    this.detach = function(){};
}
eXcell_coro.prototype = new eXcell_co;
function eXcell_cotxt(cell){
	this.base = eXcell_co;
	this.base(cell)
}   
eXcell_cotxt.prototype = new eXcell_co;   
eXcell_cotxt.prototype.getText = function(){ return (_isIE?this.cell.innerText:this.cell.textContent); }
eXcell_cotxt.prototype.setValue = function(val){
						if (typeof(val)=="object"){
							var optCol=this.grid.xmlLoader.doXPath("./option",val);
                            if (optCol.length)
                            	this.cell._combo=new dhtmlXGridComboObject();
                                for (var j=0;j<optCol.length; j++)
									this.cell._combo.put(optCol[j].getAttribute("value"),optCol[j].firstChild?optCol[j].firstChild.data:"");
							val=val.firstChild.data;	
						}
						if ((val||"").toString()._dhx_trim()=="")
							val=null

                        if (val!==null)
  						    this.setCTxtValue((this.cell._combo||this.grid.getCombo(this.cell._cellIndex)).get(val) || val,val);
                        else
                            this.setCTxtValue(" ",val);

						this.cell.combo_value = val;
}

function eXcell_corotxt(cell){
	this.base = eXcell_co;
	this.base(cell)
	this.editable = false;
}
eXcell_corotxt.prototype = new eXcell_cotxt;

/**
*	@desc: color picker editor
*	@returns: dhtmlxGrid cell editor object
*	@type: public
*/
function eXcell_cp(cell){
	try{
		this.cell = cell;
		this.grid = this.cell.parentNode.grid;
	}catch(er){}
	this.edit = function(){
						this.val = this.getValue()
						this.obj = document.createElement("SPAN");
						this.obj.style.border = "1px solid black";
						this.obj.style.position = "absolute";
						var arPos = this.grid.getPosition(this.cell);//,this.grid.objBox
						this.colorPanel(4,this.obj)
						document.body.appendChild(this.obj);//this.grid.objBox.appendChild(this.obj);
						this.obj.style.left = arPos[0]+"px";
						this.obj.style.top = arPos[1]+this.cell.offsetHeight+"px";
					}
	this.toolDNum = function(value){
						if(value.length==1)
							value = '0'+value;
						return value;
					}
	this.colorPanel = function(index,parent){
						var tbl = document.createElement("TABLE");
						parent.appendChild(tbl)
						tbl.cellSpacing = 0;
						tbl.editor_obj = this;
						tbl.style.cursor = "default";
						tbl.style.cursor = "table-layout:fixed";
						tbl.onclick = function(e){
											var ev = e||window.event
											var cell = ev.target||ev.srcElement;
											var ed = cell.parentNode.parentNode.parentNode.editor_obj
											ed.setValue(cell.style.backgroundColor)
											ed.grid.editStop();
										}
						var cnt = 256/index;
						for(var j=0;j<=(256/cnt);j++){
							var r = tbl.insertRow(j);
							for(var i=0;i<=(256/cnt);i++){
								for(var n=0;n<=(256/cnt);n++){
									R = new Number(cnt*j)-(j==0?0:1)
									G = new Number(cnt*i)-(i==0?0:1)
									B = new Number(cnt*n)-(n==0?0:1)
									var rgb = this.toolDNum(R.toString(16))+""+this.toolDNum(G.toString(16))+""+this.toolDNum(B.toString(16));
									var c = r.insertCell(i);
										c.width = "10px";
										c.innerHTML = "&nbsp;";//R+":"+G+":"+B;//
										c.title = rgb.toUpperCase()
										c.style.backgroundColor = "#"+rgb
										if(this.val!=null && "#"+rgb.toUpperCase()==this.val.toUpperCase()){
											c.style.border = "2px solid white"
										}
								}
							}
						}
					}
	this.getValue = function(){
						return this.cell.firstChild.style?this.cell.firstChild.style.backgroundColor:"";//this.getBgColor()
					}
	this.getRed	  =	function(){
						return Number(parseInt(this.getValue().substr(1,2),16))
					}
	this.getGreen	= function(){
						return Number(parseInt(this.getValue().substr(3,2),16))
					}
	this.getBlue	= function(){
						return Number(parseInt(this.getValue().substr(5,2),16))
					}				
	this.detach = function(){
					if(this.obj.offsetParent!=null)
						document.body.removeChild(this.obj);
					//this.obj.removeNode(true)
					return this.val!=this.getValue();
				}
}
eXcell_cp.prototype = new eXcell;
eXcell_cp.prototype.setValue = function(val){
	this.setCValue("<div style='width:100%;height:"+(this.cell.offsetHeight-2)+";background-color:"+(val||"")+";border:0px;'>&nbsp;</div>",val);
					}


/**
*	@desc: image editor
*	@returns: dhtmlxGrid cell editor object
*	@type: public
*/
/*
	The corresponding  cell value in XML should be a "^" delimited list of following values:
	1st - image src
	2nd - image alt text (optional)
	3rd - link (optional)
	4rd - target (optional, default is _self)
*/
function eXcell_img(cell){
	try{
		this.cell = cell;
		this.grid = this.cell.parentNode.grid;
	}catch(er){}
	this.getValue = function(){
		 if(this.cell.firstChild.tagName=="IMG")
		 	return this.cell.firstChild.src+(this.cell.titFl!=null?"^"+this.cell.tit:"");
		 else if(this.cell.firstChild.tagName=="A"){
		 	var out = this.cell.firstChild.firstChild.src+(this.cell.titFl!=null?"^"+this.cell.tit:"");
		 	out+="^"+this.cell.lnk;
			if(this.cell.trg)
				out+="^"+this.cell.trg
			return out;
		 }
	}
	this.getTitle = function(){
		return this.cell.tit
	}
}
eXcell_img.prototype = new eXcell;
eXcell_img.prototype.setValue = function(val){
					var title = val;
					if(val.indexOf("^")!=-1){
						var ar = val.split("^");
						val = ar[0]
						title = ar[1];
						//link
						if(ar.length>2){
							this.cell.lnk = ar[2]
							if(ar[3])
								this.cell.trg = ar[3]
						}
						this.cell.titFl = "1";
					}
					this.setCValue("<img src='"+(val||"")._dhx_trim()+"' border='0'>",val);
					if(this.cell.lnk){
						this.cell.innerHTML = "<a href='"+this.cell.lnk+"' target='"+this.cell.trg+"'>"+this.cell.innerHTML+"</a>"
					}
					this.cell.tit = title;
				}



//extended simple editor (money oriented)
function eXcell_price(cell){
	this.base = eXcell_ed;
	this.base(cell)
	
	this.getValue = function(){
		if(this.cell.childNodes.length>1)
			return this.cell.childNodes[1].innerHTML.toString()._dhx_trim()
		else
			return "0";
	}
}
eXcell_price.prototype = new eXcell_ed;
eXcell_price.prototype.setValue = function(val){
		if(isNaN(Number(val))){
			if(!(val||"") || (val||"")._dhx_trim()!="")
				val = 0;//alert("Value must be an integer")
			val = this.val || 0;
		}
		if(val>0){
			var color = "green";
			this.setCValue("<span>�??/span><span style='padding-right:2px;color:"+color+";'>"+val+"</span>",val);
		}else{
			this.setCValue("<div align='center' style='color:red;'>&nbsp;</div>",0);
		}

	}


/**
*	@desc: text editor with additional formatting ( dynamic of sales )
*	@returns: dhtmlxGrid cell editor object
*	@type: public
*/
function eXcell_dyn(cell){
	this.base = eXcell_ed;
	this.base(cell)
	this.getValue = function(){
		try
		{
		var val=_unFormatNumber(this.cell.firstChild.childNodes[0].innerHTML.toString()._dhx_trim());	
		return val;
	}catch(e){}
	}
	

}

eXcell_dyn.prototype = new eXcell_ed;
eXcell_dyn.prototype.setValue = function(val){
		if(!val || isNaN(Number(val))){
			val = '0';
		}
		if(val>0){
			var color = "black";
			var img = "dyn_up.gif";
		}else if (val==0){
			var color = "black";
			var img = "dyn_.gif";
		}else{
			var color = "black";
			var img = "dyn_down.gif";
		}
		var vval=val;
		if(val && !isNaN(Number(val))){
			vval=_formatNumber(val,2,true);
		}
		this.setCValue("<div style='position:relative;padding-right:2px; width:100%;'><span style='width:100%;color:"+color+";'>"+vval+"</span></div>",val);
	}


/**
*	@desc: readonly editor
*	@returns: dhtmlxGrid cell editor object
*	@type: public
*/
function eXcell_ro(cell){
	//alert(cell.innerHTML);

	this.cell = cell;
    this.grid = this.cell.parentNode.grid;
	this.edit = function(){}
    this.cell.style.backgroundColor="#F3F3F3";
	this.isDisabled = function(){ return true; }
}
eXcell_ro.prototype = new eXcell;

/**
*	@desc: readonly editor
*	@returns: dhtmlxGrid cell editor object
*	@type: public
*/
function eXcell_INDEX(cell){
	//alert(cell.innerHTML);

	this.cell = cell;
    this.grid = this.cell.parentNode.grid;
	this.edit = function(){};
	this.cell.className="INDEX";
	this.isDisabled = function(){ return true; }
}
eXcell_ro.prototype = new eXcell;



/**
*	@desc: readonly pure text editor
*	@returns: dhtmlxGrid cell editor object
*	@type: public
*/
function eXcell_rotxt(cell){
	this.cell = cell;
    this.grid = this.cell.parentNode.grid;
	this.edit = function(){}
	this.isDisabled = function(){ return true; }
	this.setValue = function(val){
						if(!val || val.toString()._dhx_trim()=="")
							val=" ";
						this.setCTxtValue(val);
	}
}
eXcell_rotxt.prototype = new eXcell;



//disabled
function eXcell_dis(cell){
	this.cell = cell;
    this.grid = this.cell.parentNode.grid;
	this.edit = function(){
        
    }
    this.setValue=function(val){
    
      this.setCValue("&nbsp;");
      this.cell.style.backgroundColor = tddisableBgColor;
    }
}
eXcell_dis.prototype = new eXcell;

/*
HTML Link eXcell v.1.0  for dhtmlxGrid 
(c)Scand LLC 2005


The corresponding  cell value in XML should be a "^" delimited list of following values:
1st - Link Text (or "img:image_src")
2nd - URL (optional)
3rd - target (optional, default is _blank)

Samples:
<cell>Stephen King</cell>
<cell>Stephen King^http://www.stephenking.com/</cell>
<cell>Stephen King^http://www.stephenking.com/^_self</cell>
*/

function eXcell_link(cell){
	this.cell = cell;
    this.grid = this.cell.parentNode.grid;
	this.edit = function(){}
	this.getValue = function(){
		if(this.cell.firstChild.getAttribute)
			return this.cell.firstChild.innerHTML+"^"+this.cell.firstChild.getAttribute("href")
		else
			return "";
	}
	this.setValue = function(val){
		var valsAr = val.split("^");
		if(valsAr.length==1)
			valsAr[1] = "";
		else{
			if(valsAr.length>1){
				valsAr[1] = "href='"+valsAr[1]+"'";
				if(valsAr.length==3)
					valsAr[1]+= " target='"+valsAr[2]+"'";
				else
					valsAr[1]+= " target='_blank'";
			}
		}
		
		this.setCValue("<a "+valsAr[1]+" onclick='(isIE()?event:arguments[0]).cancelBubble = true;'>"+valsAr[0]+"</a>",valsAr);
	}
}

eXcell_link.prototype = new eXcell;
eXcell_link.prototype.getTitle=function(){
    return this.cell.firstChild.getAttribute("href");
}


/**
	*	@desc: combobox object constructor (shouldn't be accessed directly - instead please use getCombo(...) method of the grid)
	*	@type: public
	*	@returns: combobox for dhtmlxGrid
	*/
function dhtmlXGridComboObject(){
	this.keys = new dhtmlxArray();
	this.values = new dhtmlxArray();
	/**
	*	@desc: puts new combination of key and value into combobox
	*	@type: public
	*	@param: key - object to use as a key (should be a string in the case of combobox)
	*	@param: value - object value of combobox line
	*/
	this.put = function(key,value){
					for(var i=0;i<this.keys.length;i++){
						if(this.keys[i]==key){
							this.values[i]=value;
							return true;
						}
					}
					this.values[this.values.length] = value;
					this.keys[this.keys.length] = key;
				}
	/**
	*	@desc: gets value corresponding to the given key
	*	@type: public
	*	@param: key - object to use as a key (should be a string in the case of combobox)
	*	@returns: value correspond. to given key or null if no such key
	*/
	this.get = function(key){
					for(var i=0;i<this.keys.length;i++){
						//alert("this.keys[i]="+this.keys[i]+"key="+key);
						if(this.keys[i]==key){
							return this.values[i];
						}
					}
					return null;
				}
	/**
	*	@desc: clears combobox
	*	@type: public
	*/
	this.clear = function(){
					/*for(var i=0;i<this.keys.length;i++){
							this.keys._dhx_removeAt(i);
							this.values._dhx_removeAt(i);
					}*/
					this.keys = new dhtmlxArray();
					this.values = new dhtmlxArray();
				}
	/**
	*	@desc: remove pair of key-value from combobox with given key 
	*	@type: public
	*	@param: key - object to use as a key
	*/
	this.remove = function(key){
					for(var i=0;i<this.keys.length;i++){
						if(this.keys[i]==key){
							this.keys._dhx_removeAt(i);
							this.values._dhx_removeAt(i);
							return true;
						}
					}
				}
	/**
	*	@desc: gets the size of combobox 
	*	@type: public
	*	@returns: current size of combobox
	*/
	this.size = function(){
					var j=0;
					for(var i=0;i<this.keys.length;i++){
						if(this.keys[i]!=null)
							j++;
					}
					return j;
				}
	/**
	*	@desc: gets array of all available keys present in combobox
	*	@type: public
	*	@returns: array of all available keys
	*/
	this.getKeys = function(){
					var keyAr = new Array(0);
					for(var i=0;i<this.keys.length;i++){
						if(this.keys[i]!=null)
							keyAr[keyAr.length] = this.keys[i];
					}
					return keyAr;
				}

	/**
	*	@desc: save curent state
	*	@type: public
	*/
	this.save = function(){
                    this._save=new Array();
                    for(var i=0;i<this.keys.length;i++)
				   	    this._save[i]=[this.keys[i],this.values[i]];
				}


	/**
	*	@desc: restore saved state
	*	@type: public
	*/
	this.restore = function(){
                    if (this._save){
                        this.keys[i]=new Array();
                        this.values[i]=new Array();
                        for(var i=0;i<this._save.length;i++){
					       this.keys[i]=this._save[i][0];
                           this.values[i]=this._save[i][1];
                           }
                    }
				}
    return this;
}
function Hashtable(){
    this.keys = new dhtmlxArray();
	this.values = new dhtmlxArray();
    return this;
    }
Hashtable.prototype = new dhtmlXGridComboObject;

function editEdCell(cell){
	
	try
	{
		if(null != cell.getDebugexpression() && 'undefined' != cell.getDebugexpression())
		{
			$("expression_str_4edit").value = cell.getDebugexpression();	
			$("expression_str_tdid").value = cell.getId();	
			
		}
		else
		{
			$("expression_str_4edit").value='';
			}
		
	}catch(e){}
};
function editTitle(cell){
}

/******************************************************/






function _formatNumber(num,dotNum,seqFlag){
  if(num==""||num==" "||num=="&nbsp;"){
    return "&nbsp;";
  }
  num=new Number(num);
  if(isNaN(num)){
    num=0;
  }
  if(num<1){
    num=Math.round(num*Math.pow(10,dotNum))/Math.pow(10,dotNum);
  }
  var rtn=0;
  var right="",left="";
  if(dotNum<=0){
    left=Math.round(num)+"";
  }else{
    num=num+"";
    var idx=num.indexOf(".");
    var right="",left="";
    if(idx==-1){
      left=num;
    }else{
      left=num.substring(0,idx);
      right=num.substring(idx+1);
    }
    if(right.length>dotNum){
      right=Math.round(parseFloat("0."+right)*Math.pow(10,dotNum));
      if(right>=Math.pow(10,dotNum)){
        right=right-Math.pow(10,dotNum);
        left=(parseInt(left)+1)+"";
      }
      right=right+"";
      while(right.length<dotNum){
        right="0"+right;
      }
    }else{
      while(right.length<dotNum){
        right+="0";
      }
    }
  }
  if(seqFlag){
    var flag="";
    if(left.substring(0,1)=="-"||left.substring(0,1)=="+"){
      flag=left.substring(0,1);
      left=left.substring(1);
    }
    
    var idx=left.length;
    var tmp="";
    while(idx>3){
      idx-=3;
      tmp=left.substring(idx,idx+3)+","+tmp;
    }
    tmp=left.substring(0,idx)+","+tmp;
    left=flag+tmp.substring(0,tmp.length-1);
  }
  if(dotNum<=0){
    return left;
  }else{
    return left+"."+right;
  }
}


function _unFormatNumber(val){
  val=val+"";
  val=val.replace(/,/g,"");
  if(val.substring(val.length-1)=="%"){
    val=Math.round(parseFloat(val.substring(0,val.length-1))*1000);
    val=val/100/1000;
  }
  var n=new Number(val);
  if(isNaN(n)){
    n=0;
  }
  return n;
}

function eXcell_edAll(cell){
  try{
    this.cell = cell;
    this.grid = this.cell.parentNode.grid;
  }catch(er){}
  this._getValue = function(){
    var val="";
    if ((this.cell.firstChild)&&((this.cell.atag)&&(this.cell.firstChild.tagName==this.cell.atag))){
      val= this.cell.firstChild.value;
    }else{
      val= (_isIE?this.cell.innerText:this.cell.textContent);
    }
    return val;
  }
  this._setValue = function(val){
    if(!val || val.toString()._dhx_trim()=="")
      val=" ";
    
    this.setCValue(val);
  }
}
eXcell_edAll.prototype = new eXcell_ed;

/////////////////////////////////////////////
//var tdBgColor="#f9f9FF";
var tdBgColor="#ffffff";
//var tddisableBgColor="#F3F3F3";

function eXcell_float(cell){
  try{
    this.cell = cell;
    this.grid = this.cell.parentNode.grid;
    this.setHorAlign("r");
  }catch(er){}

  if(this.cell){
    this.cell.style.backgroundColor=tdBgColor;
  }
}
eXcell_float.prototype = new eXcell_dyn;



function eXcell_number(cell){
  try{
    this.cell = cell;
    this.grid = this.cell.parentNode.grid;
    this.setHorAlign("r");
  }catch(er){}
  if(this.cell){
    this.cell.style.backgroundColor=tdBgColor;
  }

}
eXcell_number.prototype = new eXcell_dyn;



function eXcell_int(cell){
  try{
    this.cell = cell;
    this.grid = this.cell.parentNode.grid;
    this.setHorAlign("r");
  }catch(er){}
  this.getValue = function(){
    var val=this._getValue();
    return _unFormatNumber(val);
  }
  this.setValue = function(val){
    this._setValue(_formatNumber(val,0,true));
  }
  if(this.cell){
    this.cell.style.backgroundColor=tdBgColor;
  }
}
eXcell_int.prototype = new eXcell_edAll;



function eXcell_percent(cell){
  try{
    this.cell = cell;
    this.grid = this.cell.parentNode.grid;
    this.setHorAlign("c");
  }catch(er){}
  this.getValue = function(){
      var val=this._getValue();
      return _unFormatNumber(val);
  }
  this.setValue = function(val){
    if(val==""||val==" "||val=="&nbsp;"){
      return "&nbsp;";
    }
    val=val+"";
    if(val.substring(val.length-1)=="%"){
      val=_unFormatNumber(val);
    }
    var n=Math.round(parseFloat(val)*100*1000);
    n=n/1000;
    if(isNaN(n)){
      n=0;
    }
    this._setValue(n+"%");
  }
  if(this.cell){
    this.cell.style.backgroundColor=tdBgColor;
  }
}
eXcell_percent.prototype = new eXcell_edAll;



function eXcell_rowHeader(cell){
  try{
    this.cell = cell;
    this.grid = this.cell.parentNode.grid;
    this.setHorAlign("l");
  }catch(er){}
}
eXcell_rowHeader.prototype = new eXcell_ed;



function eXcell_text(cell){
  try{
    this.cell = cell;
    this.grid = this.cell.parentNode.grid;
  }catch(er){}
  if(this.cell){
    this.cell.style.backgroundColor=tdBgColor;
  }
  
  this.getValue = function(){
    var val=this._getValue();
    return val;
  }
  this.setValue = function(val){

    this._setValue(val);
  }
}
eXcell_text.prototype = new eXcell_edAll;



function eXcell_colHeader(cell){
  try{
    this.cell = cell;
    this.grid = this.cell.parentNode.grid;
    this.setHorAlign("c");
  }catch(er){}
}
eXcell_colHeader.prototype = new eXcell_ed;



function eXcell_label(cell){
  try{
    this.cell = cell;
    this.grid = this.cell.parentNode.grid;
    this.setHorAlign("l");
  }catch(er){}
  
  this.getValue = function(){
    var val=this._getValue();
    return val;
  }
  this.setValue = function(val){

    this._setValue(val);
  }
}
eXcell_label.prototype = new eXcell_edAll;



function eXcell_date(cell){
  try{
    this.cell = cell;
    this.grid = this.cell.parentNode.grid;
    this.setHorAlign("c");
  }catch(er){}
  if(this.cell){
    this.cell.style.backgroundColor=tdBgColor;
  }
}
eXcell_date.prototype = new eXcell_edAll;

//----------------readonly


function eXcell_rofloat(cell){
  try{
    this.cell = cell;
    this.grid = this.cell.parentNode.grid;
    this.setHorAlign("r");
  }catch(er){}
  this.edit = function(){}
  this.isDisabled = function(){ return true; }
}
eXcell_rofloat.prototype = new eXcell_float;



function eXcell_ronumber(cell){
  try{
    this.cell = cell;
    this.grid = this.cell.parentNode.grid;
    this.setHorAlign("r");
  }catch(er){}
  this.edit = function(){}
  this.isDisabled = function(){ return true; }
}
eXcell_ronumber.prototype = new eXcell_number;



function eXcell_roint(cell){
  try{
    this.cell = cell;
    this.grid = this.cell.parentNode.grid;
    this.setHorAlign("r");
  }catch(er){}
  this.edit = function(){}
  this.isDisabled = function(){ return true; }
}
eXcell_roint.prototype = new eXcell_int;



function eXcell_ropercent(cell){
  try{
    this.cell = cell;
    this.grid = this.cell.parentNode.grid;
    this.setHorAlign("c");
  }catch(er){}
  this.edit = function(){}
  this.isDisabled = function(){ return true; }
}
eXcell_ropercent.prototype = new eXcell_percent;



function eXcell_rorowHeader(cell){
  try{
    this.cell = cell;
    this.grid = this.cell.parentNode.grid;
    this.setHorAlign("l");
  }catch(er){}
  this.edit = function(){}
  this.isDisabled = function(){ return true; }
}
eXcell_rorowHeader.prototype = new eXcell_rowHeader;



function eXcell_rotext(cell){
  try{
    this.cell = cell;
    this.grid = this.cell.parentNode.grid;
  }catch(er){}
  this.edit = function(){}
  this.isDisabled = function(){ return true; }
  
  this.getValue = function(){
    var val=this._getValue();
    return val;
  }
  this.setValue = function(val){
 
    this._setValue(val);
  }
}
eXcell_rotext.prototype = new eXcell_text;



function eXcell_rocolHeader(cell){
  try{
    this.cell = cell;
    this.grid = this.cell.parentNode.grid;
    this.setHorAlign("c");
  }catch(er){}
  this.edit = function(){}
  this.isDisabled = function(){ return true; }
}
eXcell_rocolHeader.prototype = new eXcell_colHeader;



function eXcell_rolabel(cell){
  try{
    this.cell = cell;
    this.grid = this.cell.parentNode.grid;    
    this.setHorAlign("l");
  }catch(er){}
  this.edit = function(){}
  this.isDisabled = function(){ return true; }
  
  this.getValue = function(){
    var val=this._getValue();
    return val;
  }
  this.setValue = function(val){
 
    this._setValue(val);
  }
}
eXcell_rolabel.prototype = new eXcell_edAll;



function eXcell_rodate(cell){
  try{
    this.cell = cell;
    this.grid = this.cell.parentNode.grid;
    this.setHorAlign("c");
  }catch(er){}
  this.edit = function(){}
  this.isDisabled = function(){ return true; }
}
eXcell_rodate.prototype = new eXcell_date;
/******************************************************/
function eXcell_cnmoney(cell){
  try{
    this.cell = cell;
    this.grid = this.cell.parentNode.grid;
    this.setHorAlign("l");
  }catch(er){}
  this.setMoney=function(val){
    this.cell.money=val;
  }
  this.getMoney=function(){
    return this.cell.money;
  }
  
  //this.getValue=function(){
  //  return this.getMoney();
  //}
}
eXcell_cnmoney.prototype = new eXcell_ed;
eXcell_cnmoney.prototype.setValue=function(val){
    this.setMoney(val);
    if(val=="&nbsp;"||val==" "||val==""){
      this.setCValue(val);
    }else{
      this.setCValue(toCnNumber(val));
    }
  }

function eXcell_rocnmoney(cell){
  try{
    this.cell = cell;
    this.grid = this.cell.parentNode.grid;
    this.setHorAlign("l");
  }catch(er){}
  this.edit = function(){}
  this.isDisabled = function(){ return true; }
}
eXcell_rocnmoney.prototype = new eXcell_cnmoney;

function toCnNumber(num){
  var money_prefix="\u91d1\u989d\u5408\u8ba1\uff08\u4eba\u6c11\u5e01\uff09\u5927\u5199\uff1a";
  var n=new Number(num);
  if(isNaN(n)){
    return num;
  }
  num=_formatNumber(num,2,false);
  if(n>=100000000000){
    return num+" \u592a\u5927\u4e86";
  }
  if(n<0.01){
    n=0;
    return money_prefix+"\u96f6\u5706\u6574";
  }
  var numStr=num+"";
  var ns=numStr.split(".");
  var left=leftToCn(ns[0]);
  var right=rightToCn(ns[1]);
  var rtn="";
  if(right==""){
    rtn=left+"\u6574";
  }else {
    rtn=left+right;
  }
  return money_prefix+rtn;
}
function rightToCn(str){
  if(!str||str=="0"||str==""){
    return "";
  }
  if(str.length>2){
    str=str.substring(0,2);
  }
  if(str=="00"){
    return "";
  }

  var rtn="";
  if(str.charAt(0)=="0"){
    rtn="\u96f6"+cnNum[parseInt(str.charAt(1)+"")]+"\u5206";
  }else {
    rtn=cnNum[parseInt(str.charAt(0)+"")]+"\u89d2";
    if(str.length>1){
      rtn+=cnNum[parseInt(str.charAt(1)+"")]+"\u5206";
    }else {
      rtn+="\u6574";
    }
  }
  return rtn;
}
var cnNum=["\u96f6","\u58f9","\u8d30","\u53c1","\u8086","\u4f0d","\u9646","\u67d2","\u634c","\u7396"];
var cnUnit1=["","\u62fe","\u4f70","\u4edf"];
var cnUnit2=["","\u4e07","\u4ebf"];
function leftToCn(str){
  if(str=="0"){
    return "\u96f6\u5706";
  }
  var rtn=str.replace(/\d/g,getCnNum);
  rtn=rtn.replace(/\u96f6\u4edf|\u96f6\u4f70|\u96f6\u62fe/g,"\u96f6");
//rtn=rtn.replace(/\u96f6{2,}/g,"\u96f6");
  rtn=rtn.replace(/\u96f6+\u4ebf/g,"\u4ebf\u96f6");
  rtn=rtn.replace(/\u96f6+\u4e07/g,"\u4e07\u96f6");
  rtn=rtn.replace(/\u4ebf\u4e07/g,"\u4ebf\u96f6");
  rtn=rtn.replace(/\u96f6{2,}/g,"\u96f6");
  rtn=rtn.replace(/\u96f6+$/,"");
  return rtn+"\u5706";
}
    
    function getCnNum(){
      var n=arguments[0];
      var idx=arguments[1];
      var str=arguments[2];
      n=parseInt(n);
      idx=parseInt(idx);
      var len=str.length;
      var rtn="";
      rtn+=cnNum[n];
      n=len-idx-1;
      var tmp=n%4;
      if(tmp==0){
        rtn+=cnUnit2[(n/4)%3];
      }else{
        rtn+=cnUnit1[n%4];
      }
      return rtn;
    }

