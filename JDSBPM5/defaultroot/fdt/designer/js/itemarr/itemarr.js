
var errInvalids=new Ext.util.MixedCollection();
var warInvalids=new Ext.util.MixedCollection();

Ext.form.MyColorField = Ext.extend(Ext.form.TriggerField,  {
    triggerClass : 'x-form-color-trigger',



    defaultAutoCreate : {tag: "input", type: "text", size: "6",width:"100",readOnly:"true", autocomplete: "off"},

    initComponent : function(){
        Ext.form.MyColorField.superclass.initComponent.call(this);
    },

    // private
    validateValue : function(value){
        return true;
    },

    validateBlur : function(){
        return !this.menu || !this.menu.isVisible();
    },

    getValue : function(){
        var v=Ext.form.MyColorField.superclass.getValue.call(this) || "";
        //if(v.indexOf("#")==0){
        //  v=v.substring(1);
        //}
        return v;
    },

    setValue : function(color){
        if(color.length>0&&color.indexOf("#")<0){
          color="#"+color;
        }
        Ext.form.MyColorField.superclass.setValue.call(this, color);
    },

    // private
    onDestroy : function(){
        if(this.wrap){
            this.wrap.remove();
        }
        Ext.form.MyColorField.superclass.onDestroy.call(this);
    },

    // private
    menuListeners : {
        select: function(m, d){
            this.setValue(d);
        },
        show : function(){ // retain focus styling
            this.onFocus();
        },
        hide : function(){
            this.focus.defer(10, this);
            var ml = this.menuListeners;
            this.menu.un("select", ml.select,  this);
            this.menu.un("show", ml.show,  this);
            this.menu.un("hide", ml.hide,  this);
        }
    },

    // private
    // Implements the default empty TriggerField.onTriggerClick function to display the MyColorPicker
    onTriggerClick : function(){
        if(this.disabled){
            return;
        }
        if(this.menu == null){
            var v=this.getValue().toUpperCase();
            this.menu = new Ext.menu.ColorMenu({
              //allowReselect: true,
              value:v
            });
        }
        Ext.apply(this.menu.palette,  {
            //minWidth  : 100

        });
        this.menu.on(Ext.apply({}, this.menuListeners, {
            scope:this
        }));
        //this.menu.palette.setValue(this.getValue() ||"");
        this.setValue(this.getValue() ||"");

        this.menu.show(this.el, "tl-bl?");
    },

    beforeBlur : function(){
        var v = this.getRawValue();
        if(v){
            this.setValue(v);
        }
    }

    /**
     * @cfg {Boolean} grow @hide
     */
    /**
     * @cfg {Number} growMin @hide
     */
    /**
     * @cfg {Number} growMax @hide
     */
    /**
     * @hide
     * @method autoSize
     */
});
Ext.reg('mycolorfield', Ext.form.MyColorField);




/*****************************************************************************/


function editorTipToCn(obj){
  var tips=obj.buttonTips;
  tips.bold.text="粗体";
  tips.italic.text="斜体";
  tips.underline.text="下划线";
  tips.increasefontsize.text="加大字体";
  tips.decreasefontsize.text="减小字体";
  tips.backcolor.text="背景颜色";
  tips.forecolor.text="文本颜色";
  tips.justifyleft.text="左对齐";
  tips.justifycenter.text="中对齐";
  tips.justifyright.text="右对齐";
  tips.insertunorderedlist.text="无序列表";
  tips.insertorderedlist.text="数字列表";
  tips.createlink.text="超链接";
  tips.sourceedit.text="切换源代码编辑模式";

  obj.fontFamilies.push("宋体");
}


    /*
     生成对应的Field
    */
    function getField(name,att){
      if(att.parentName){
        name=att.parentName+"."+name;
      }
      if(AttData.isOnlyMod){
        if((typeof AttData.modData[name])!="undefined"){
          att.value=AttData.modData[name];
        }
      }


      var type=att.type;
	 
      var rtn=getFieldImpl(name,att);
      if(rtn){
        if(att.subType){
          rtn.subType=name+".subType";
          rtn.subObj=att.subType;
        }
        if(!rtn.width){
          rtn.width=100;
        }
      }
      return rtn;
    }

    /*
    左边的属性列表
    */
    function getLeftPanel(atts,config){
      var data=[];
      for(var a in atts){
        var tmp=[];
        tmp[0]=a+"";
        tmp[1]=atts[a].label;
        data.push(tmp);
      }
      var store = new Ext.data.SimpleStore({
        fields: [
          {name: 'id'},
          {name: 'name'}
        ]
      });
      store.loadData(data);
     
	
      var typeCfg={
          cell:"单元格",
		  textfield:"文本框",
		  select:"选择框",
		  datefield:"日期框",
		  combo:"下拉框",
		  checkbox:"选择框",
		  radio:"单选框",
		 textarea:"文本域",
		  
          table:"表格",
          row:"行",
          column:'列',
          rowcell:'行单元格',
          columncell:'列单元格',
          group:'块'
      };
      if(!config){
        config={
          id:"leftPanel",
          region: 'west',

          split: true,
          width: 150,
          collapsible: true,
          margins:'3 0 3 3',
          cmargins:'3 3 3 3',

          store: store,
          sm: new Ext.grid.RowSelectionModel({singleSelect:true}),
          enableColumnHide :false,
          columns: [
              {header: "id", width: 0, sortable: true, dataIndex: 'id',hidden:true},
              {id:"name",header: "名字", sortable: true, dataIndex: 'name'}
          ],
          autoExpandColumn: 'name',
		 
          title:typeCfg[AttData.accessorRequest.type]+'[ '+AttData.accessorRequest.id[0]+' ]属性'
        };
      }
      var grid = new Ext.grid.GridPanel(config);
      return grid;
    }

    /*
    单击左边列表的处理函数
    */
    function rowclickFn(grid,rowindex,e){
      var recode = grid.getSelectionModel().getSelected();
      modForm(recode.get("id"));
    }

    /*
    右边的form外面板
    */
    function getRightPanel(config){
      if(!config){
        config={
          id:"rightPanel",
          autoScroll:true,
          margins:'3 0 3 3',
          cmargins:'3 3 3 3',
          region:"center",
          tbar:[
              '-',
              {
                text:'<b>确 定</b>',
                handler:function(){
                    setTimeout(function(){
                        Ext.get("okBtn").dom.click();
                    },10);
                }
              },
              '-'
          ],
          autoHeight:true,
        	html: '<div id="'+AttData.formDivId+'" style="height:100%"></div>'
        };
      }
      return new Ext.Panel(config);
    }

    /*
    form面板
    */
    function getFormPanel(id,atts){
      
      var pan=null;
      var config={
        frame:true,
        id:"rightForm",
        region:"center",
        bodyStyle:'padding:5px 5px 0',
        items:[{}],
        onSubmit: Ext.emptyFn,
        submit: function() {
            //this.getForm().getEl().dom.submit();
            alert("submit");
        }
      };

      if(atts.type=='subobj'){
        var cfg=[{
            layout:'column',
            items:[{
                columnWidth:.5,
                layout: 'form',
                items: []
            },{
                columnWidth:.5,
                layout: 'form',
                items: []
            }]
        }];
        
        var sub=atts.sub;
        var n=0;
        for(var a in sub){
          var f=getField(a,sub[a]);
          if(f){
            cfg[0].items[n%2].items.push(f);
            n++;
          }
        };
        config.items=cfg;
        pan=new Ext.FormPanel(config);
      
      }else{
        pan=new Ext.FormPanel(config);
        var f=getField(id,atts);
        if(f){
          pan.add(f);
        }
      }
      pan.addButton({
        text: 'Submit',
        id:"okBtn",
        hidden:true,
        handler: function(){
          submitForm();
        }
      });
	  
	
	
      return pan;
    }

/*
删除旧的form
*/
function removeForm(){
  if(AttData.curFormPanel){
      AttData.curFormPanel.destroy();
	  try{
	  	 submitForm(true);
	  }catch(e){
	  	
	  }
      //resetObjDiv(AttData.curFormPanel,AttData.formDivId);
  }
  return ;
  /*
  var formDivId=AttData.formDivId;
  var formDiv=Ext.get(formDivId);
  if(formDiv){
    formDiv=formDiv.dom;
  }
  if(formDiv&&formDiv.childNodes[0]){
    formDiv.removeChild(formDiv.childNodes[0]);
  }
  */
}

/*
把form加到右边
*/
function addForm(f){
  var formDivId=AttData.formDivId;
  if(f){
    f.render(formDivId);
  }
  //curFormPanel=f;
}

/*
替换右边的form
*/
function modForm(id){
  var atts=AttData.attData[id];
  if(atts){
    removeForm();
    var curFormPanel=getFormPanel(id,atts);
    AttData.curFormPanel=curFormPanel;
    addForm(curFormPanel);
  }
}

/*
数据改变的处理,主要用来处理子类型的问题
*/
function handleValueChange(){
  try{
  var subType=this.subType;
  var name="";
  if(this.valueName){
    name=this.valueName;
  }else{
    name=this.id;
  }
  var value=document.getElementById(name).value;


  if(subType){
    var subCfg=this.subObj;
    var values=subCfg;
    var subId=name+"SubType";
    //alert(subId+"-=---"+AttData.curFormPanel.findById(subId));
    var curFormPanel=AttData.curFormPanel;
    if(curFormPanel.findById(subId)){
      curFormPanel.remove(subId);
      var tmp=curFormPanel.findById(subId);
      if(tmp){
        tmp.parentSet.remove(tmp);
      }
    }

    var cfg=null;
    for(var i=0;i<values.length;i++){
      if(values[i].value==value){
        cfg=values[i];
        break;
      }
    }

    if(cfg){
      var items=curFormPanel.getForm().items;
      var idx=items.indexOf(this);
      var set=getSubSet(subId,cfg);

      if(idx<0){//子类型中的元素
        var s=this.setId;
        var o=curFormPanel.findById(s);
        idx=o.items.indexOf(this);
        set.parentSet=o;
        o.insert(idx+1,set);
      }else{
        curFormPanel.insert(idx+2,set);
      }
      curFormPanel.render();
    }
  }
  }catch(e){
    //alert(e.message);
  }
}

/*
获取子类型的FieldSet
*/
function getSubSet(subId,cfg){
  var fields=[];
  for(var a in cfg){
    if(typeof cfg[a]=="object"){
      var f=getField(a,cfg[a]);
      if(f){
        f.setId=subId;
        fields[fields.length]=f;
      }
    }
  }
  return new Ext.form.FieldSet({
            id:subId,
            xtype:'fieldset',
            title: cfg.label,
            collapsible: true,
            autoHeight:true,
            autoWidth:true,
            defaultType: 'textfield',
            items :fields
        });
}


/* Field--- start -------------------------------------*/
function gethiddenField(name,att){
  return new Ext.form.Hidden({name:name,value:att.value});
}

function gettextField(name,att){

 var field= findFieldByPid(AttData.accessorRequest.id);
   if(att.value==""){
	   switch (name){
		case "textareaAttribute.basearr.defaultText" : 
		att.value=(field.getValue());
		break;
		case "inputAttribute.basearr.defaultText" : 
		att.value=(field.getValue());
		break;
		case "selectAttribute.basearr.defaultText" : 
		att.value=(field.getValue());
		break;
		}
	  }	  
  var rtn=new Ext.form.TextField({
            fieldLabel:att.label,
            style:"type:"+"text",
            name:name,
            id:name,
			 width:190,
            value:att.value
          });
 
  rtn.on("change",function(){setTmpValue(name,rtn.getValue());});
  return rtn;
}

function getnumberField(name,att){
  /*
  var rtn=gettextField(name,att);
  rtn.regex=/^\d*$/;
  rtn.regexText="输入值只能是数字";
  */

  var cfg={
            fieldLabel:att.label,
            style:"type:"+"text",
            name:name,
            id:name,
			 width:190,
            decimalPrecision :att.dcount?att.dcount:"0"
          };
		  
 var field= findFieldByPid(AttData.accessorRequest.id);
	  if(att.value!=""){
	      cfg.value=att.value;
	  }else{
	   switch (name){
		case "textareaAttribute.basearr.size" : 
		cfg.value=(field.width);
		break;
		case "inputAttribute.basearr.size" : 
		cfg.value=(field.width);
		break;
		case "selectAttribute.basearr.size" : 
		cfg.value=(field.width);
		break;
		}
	  }
 
  var rtn=new Ext.form.NumberField(cfg);
  rtn.on("change",function(){setTmpValue(name,rtn.getValue());});
  return rtn;
}

function getselectField(name,att){
  var store = new Ext.data.JsonStore({
      data:att.values,
      fields:["key","text"]
    });
  var rtn=new Ext.form.ComboBox({
      id:name+"Select",
      valueName:name,
      hiddenName:name,
      value:att.value,
      fieldLabel: att.label,
      store:store,
      valueField:'key',
      displayField:'text',
      typeAhead: true,
      mode: 'local',
      triggerAction: 'all',
      emptyText:'请选择...',
      selectOnFocus:true,
      allowBlank:true,
      forceSelection:true,
      resizable:true,
	  width:190,
      editable:false
  });
  rtn.on("select",handleValueChange);
  rtn.on("render",handleValueChange);//初始化后,如果有当前值,判断是否有相关子选项

  rtn.on("change",function(){setTmpValue(name,rtn.getValue());});
  return rtn;
}

function getdateField(name,att){
  var rtn=new Ext.form.DateField({
          fieldLabel:att.label,
          id:name,
          name: name,
          width:190,
          //format:"Y-m-d",// H:i:s",
          value:att.value
          //,allowBlank:false
      });
  if(att.format){
    rtn.format=att.format;
  }else{
    rtn.format="Y-m-d";
  }

  rtn.on("change",function(){setTmpValue(name,rtn.formatDate(rtn.getValue()));});
  return rtn;
};
//时间处理框
function gettimeField(name,att){
		//att.value = StringUtil.format(att.value);
  var rtn=new Ext.form.TimeField({
          fieldLabel:att.label,
          id:name,
          name: name,
          //format : "g:i A",
          minValue: '0:00am',
          maxValue: '12:00pm',
		  width:190,
          value:att.value
          //,allowBlank:false
      });
  if(att.format){
    rtn.format=att.format;
  }else{
    rtn.format="g:i A";
  }
  rtn.on("change",function(){setTmpValue(name,rtn.formatDate(rtn.getValue()));});
  return rtn;
};

//选择时间格式选择
function getformatTimeField(name,att){
	var dtimecom=new Ext.form.ComboBox({
		                id:name+"Select",
                        valueName:name,
                        hiddenName:name,
                        value:att.value,
                        fieldLabel: '间隔时间',
                        hiddenName:'formatType',
                        store: new Ext.data.SimpleStore({
                            fields: ['abbr', 'state'],
                            data : [
							['1','1分钟'],
							['5','5分钟'],
							['10','10分钟'],
							['15','15分钟']							
							]
                         }),					
                        valueField:'abbr',
                        displayField:'state',
                        typeAhead: true,
                        mode: 'local',
						value:att.value,
                        triggerAction: 'all',
                        emptyText:'请选择显示最小间隔',
                        selectOnFocus:true,
                        width:190
                    });	
	dtimecom.on("change",function(){setTmpValue(name,dtimecom.getValue());});
   return 	dtimecom;
}
//日期格式化
function getformatDateField(name,att){
	  var datecom=new Ext.form.ComboBox({
                        fieldLabel: '展示样式',
                        hiddenName:'formatType',
						 id:name+"Select",
                        valueName:name,
                        hiddenName:name,
                        value:att.value,
                        store: new Ext.data.SimpleStore({
                            fields: ['abbr', 'state'],
                            data : [
							['Y年m月d日','2008年01月10日'],
							['Y年m月','2008年01月'],
							['Y-m-d','2008-01-10'],
							['Y ,j F , g:i a','January 10, 2008, 3:05 pm']							
							]
                         }),					
                        valueField:'abbr',
                        displayField:'state',
                        typeAhead: true,
                        mode: 'local',
                        triggerAction: 'all',
                        emptyText:'请选择格式化方式',
                        selectOnFocus:true,
                        width:190
                    });	
	datecom.on("change",function(){setTmpValue(name,datecom.getValue());});
   return 	datecom;
}
//颜色选择框
function getcolorField(name,att){
  var rtn=new Ext.form.MyColorField({
          fieldLabel:att.label,
          id:name,
          name: name,
		   width:190,
          value:att.value
  });
  rtn.on("change",function(){setTmpValue(name,rtn.getValue());});
  return rtn;
}
//html编辑器
function gethtmleditField(name,att){

  var rtn= new Ext.form.HtmlEditor({
            id:name,
            name:name,
            fieldLabel:att.label,
            value:att.value,
            hideLabel:true,
            autoWidth:true,   
            height:300
          });
  editorTipToCn(rtn);
  rtn.on("sync",function(){setTmpValue(name,rtn.getValue());});
  return rtn;
}

function findFieldByPid(pid){
	var field;
	currform.items.each(function(f){
           if(f.pid==pid){
			field=f;
           return f;
           }
		});
	return field;
}

//表达式编辑窗口
function getexpressionField(name,att){
  var width=300;
  var h=40;

   var field= findFieldByPid(AttData.accessorRequest.id);	
  var txt=new Ext.form.TextArea({
          id:name,
          name:name,
          width:width,
          height:h,
          value:att.value ? att.value: field.ognlMap
        });
		setTmpValue(name,txt.getValue())

  txt.on("change",function(){setTmpValue(name,txt.getValue());});

  var btn=new Ext.Button({
              //template:tpl,
              text:"编辑数据来源",
              width:20,
              id:name+"editbtn"
            });

  var okFn=function(str){
    txt.setValue(str);
    setTmpValue(txt.name,str);
  };
  btn.on('click',function(){
      openExpressionEditor(txt.getValue(),okFn);
  });



  var rtn=new Ext.Panel({
          //title:att.label,
          autoScroll :true,
         width:width+170,
          height:h+30,
          items:[txt,btn]
  });
  return rtn;
}

//映射对象
function getnameField(name,att){

  var width=300;
  var h=40;
    var txt=new Ext.form.TextArea({
          id:name,
		  allowBlank:false,
		  msgTarget:'qtip',
		  blankText:'您还没有选择映射字段，请选择映射字段！',
		  //invalidText:'您还没有选择映射字段，请选择映射字段！',
          name:name,
          width:width,
          height:h,
          value:att.value
        });
 // txt.on("invalid",function(){errInvalids.add(name,txt)});
 // txt.on("valid",function(){errInvalids.removeKey(name)});
 // txt.on("change",function(){setTmpValue(name,txt.getValue());});
  //txt.on("click",function(){getFiledTreePanel(txt);})
  var btn=new Ext.Button({
              //template:tpl,
              text:"选择映射对象",
              width:20,
              id:name+"editbtn"
            });
  btn.on('click',function(){
  	getFiledTreePanel(txt);
     // openExpressionEditor(txt,txt.getValue());
  });



  var rtn=new Ext.Panel({
         // title:att.label,
          autoScroll :true,
          width:width,
          height:h+30,
          autoScroll:false,
          items:[txt,btn]
  });
  return rtn;
}



function getFieldImpl(name,att){
  return eval("get"+att.type+"Field(name,att)");
}
/* Field--- end -------------------------------------*/



function createAttPanel(data){
	
  Ext.namespace("AttData");
  try{
  	 submitForm(true);
  }catch(e){
  	
  }
 
  AttData.isOnlyMod=true;
  AttData.modData={};
  AttData.formDivId="rightFormDiv";
  AttData.curFormPanel=null; //当前的form面板,对应不同属性生成不同的form
    if(AttData.win)
    {
      resetObjDiv(AttData.win,"div2")
    }


  AttData.accessorRequest = data.accessorRequest;
  AttData.attData=data.data;
  var left=getLeftPanel(AttData.attData);
  left.addListener('rowclick', rowclickFn);

  var right=getRightPanel();
    var winParam={       
	
          autoScroll:true,        
          autoWidth:true,
          height:400,
          layout: 'border',
          el:'div2',
          items: [left,right]
        
    };


  AttData.win = new Ext.Panel(winParam);
  AttData.win.render();

  left.getSelectionModel().selectFirstRow() ;
  var id=left.getSelectionModel().getSelected().get("id");
  var curFormPanel=getFormPanel(id,AttData.attData[id]);
  
  AttData.curFormPanel=curFormPanel;
  addForm(curFormPanel);

  return;
}



/*
点确定后的处理
*/
function submitForm(noreturn){
		if (errInvalids.getCount()>0){
				errMag="字段校验出现错误：<br>"
				errInvalids.each(
					function(f){
						errMag=errMag+f.invalidText+'<br>'
					}
				);
				Ext.MessageBox.show({
			           title: '数据校验出现错误',
			           msg: errMag,
			           width:300,
			           buttons:  {           
			           yes : "确定"
					  
			        },

			          icon: Ext.MessageBox.ERROR  
			       });
				   
				   return 
			}
		
    var url = context+'design/fdtfileUpdate.action';
    var par = getAllValueStr(AttData.attData);
	
    var ids=AttData.accessorRequest.id;
	var type=AttData.accessorRequest.type;
    var idStr="";
    for(var i=0;i<ids.length;i++){
        idStr+="&accessorRequest.id["+i+"]="+ids[i];
    }
    par = par + "&formId=" + $F('formId') + idStr;
	par = par + "&fileName=" + $('fileName').value ;
    par = par+'&activityDefId='+$('activityDefId').value;
    par = par + "&accessorRequest.method=post";
	par = par + "&accessorRequest.type="+type;
	
    Ext.Ajax.request(
    {
        url: url,
        params:par,
        success: function(rs){
            if(rs.responseText=="OK"){
                attPanelStyleHandler(AttData.modData,ids);
				if (!noreturn){
					 alert("操作成功");
				}
               
            }else{
                alert("操作失败"+rs.responseText.substring(0,100));
            }
        },
        failure: function(){alert("操作失败");}
    });
}

//为后台解析而处理key格式
function keyEncode(key){
  return "accessorRequest.parameters['"+key+"']";
}



StringUtil={	
    replaceAll:function (s,oldkey,newkey){
	
		var ss=s.replace(oldkey,newkey);
		
		if (ss.indexOf(oldkey)>-1){
			ss=StringUtil.replaceAll(ss,oldkey,newkey);
		}
	
		return ss;
	},
	format:function (string){
			if (typeof(string)!='string'){
				string=string+' '
			return string.trim();
		}
		
		string=StringUtil.replaceAll(string,"\\'","'");
		string=StringUtil.replaceAll(string,"'","\"");	
		string=StringUtil.replaceAll(string,"\"","\\'");
	
		return string;
		
	}
}




//取得所有的key,value组成的串
function getAllValueStr(obj){
  var all=[];
  if(AttData.isOnlyMod){
    obj=AttData.modData
	
    for(var a in obj){
	//	  all.push(keyEncode(a)+"="+encodeURIComponent(obj[a]));
      all.push(keyEncode(a)+"="+encodeURIComponent(StringUtil.format(obj[a])));
    }
  }else{
    for(var a in obj){
      var key=a;
      if(obj[a].parentName){
        key=obj[a].parentName+"."+a;
      }
      key=keyEncode(key);
      all.push(key+"="+encodeURIComponent(StringUtil.format(obj[a].value)));
	  // all.push(keyEncode(a)+"="+StringUtil.format(obj[a]));
      if(obj[a].subType){
        all.push(getSubValueStr(obj[a]));
      }
    }
  }

  return all.join("&");
}

function getSubValueStr(obj){
  var os=obj.subType;
  var arr=[];
  for(var i=0;i<os.length;i++){
    for(var a in os[i]){
      if(typeof os[i][a]=="object"){
        var key=a;
        if(os[i][a].parentName){
          key=os[i][a].parentName+"."+a;
        }
        key=keyEncode(key);
        arr.push(key+"="+encodeURIComponent(StringUtil.format(os[i][a].value)));
        if(os[i][a].subType){
          arr.push(getSubValueStr(os[i][a]));
        }
      }
    }
  }
  alert(arr.join("&"));
  return arr.join("&");
}

//如果有值改变则缓存改变后的值
function setTmpValue(id,value){
  if(AttData.isOnlyMod){
    AttData.modData[id]=StringUtil.format(value);	
  }else{
    var obj=getObjByLongId(id,AttData.attData);
    if(obj){
      obj.value=StringUtil.format(value);
    }else{
      alert("id["+id+"]错误");
    }
  }

}

//通过全id名得到对应的对象,id如: id1.sub1.sub11
function getObjByLongId(id,obj){
  var ids=id.split(".");
  var rtn=null;
  var idx=1;
  while(idx<ids.length){
    var o=obj[ids[idx]];
    if(o&&o.subType&&idx<ids.length-1){
      var os=o.subType;
      var tmp=null;
      for(var i=0;i<os.length;i++){
        tmp=os[i][ids[idx+1+1]];
        if(tmp){
          o=os[i];
          if(os[i].value==ids[idx+1]){
            obj=o;
          }

          break;
        }
      }
      /*
      if(tmp){
        obj=o;
      }
      */
    }
    idx++;
    idx++;
    rtn=o;
  }
  return rtn;
}

function extInit(){
  Ext.BLANK_IMAGE_URL=context+'js/ext/resources/images/default/s.gif';
  Ext.form.Field.prototype.msgTarget = 'side';
  Ext.QuickTips.init();
}



