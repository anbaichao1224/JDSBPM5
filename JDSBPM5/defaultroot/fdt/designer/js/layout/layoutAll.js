var currform;
var viewport;

//window.onerror=function(a,b,c,d){
//
// return true;
//};

var reportPanelInit=function(){
        $('dd').style.height='0px';
		initToolBar();
		initMenubar();
	    viewport = new Ext.Viewport({
	            layout:'border',
				style:"background-color:white",
	            items:[
	                new Ext.BoxComponent({ // raw
	                    region:'north',
	                    items:[
	                        Ext.get('tableTool'),
	                        Ext.get('expressionToolMenu')
	                    ] ,
	                    el: 'north',
	                    height:60
	                }),
					
					{
		                    region:'west',
		                    split:true,
		                    width: 200,
		                    minSize: 175,
		                    maxSize: 400,
		                    collapsible: true,
		                    margins:'0 0 0 0',
		                    id:'layoutTreePanel',
		                    title:'元素列表',
		                    html:'<div id="layoutTree" align="left" style="overflow:auto; height:100%;width:100%;border:1px solid #c3daf9;"></div>',
		                    iconCls:'nav'
	                   },
					   
					   
		                {
		                    region:'center',
		                    collapsible: true,
		                    autoScroll:true,
		                    split:true,
			                items:[
							           {
						                 region:'north',
										 height:1,
										  html:''   
										}
										,
										{
						                    region:'center',
										    id:formId,
										     closable:true,
										     autoLoad: {url: context + "design/fdtformDisplay.action", params: "fileName=" + $('fileName').value , callback:callFormDefFn}
										},
										{
						                   region:'south',
										   height:1,
										   html:''
										}
		                    ]
		                },
			
                        {
                            id:"attShowPanel",
                            region:'south',
                            collapsible: true,
                            split:true,
                            height:200,
							maxSize: 300,
                            autoWidth:true,
                            //autoScroll:true,
                            margins:'0 0 0 0',
                            items:Ext.get('div2')
                        }
	             ]
	        });
	       Ext.getCmp("layoutTreePanel").collapse();
	       Ext.getCmp("attShowPanel").collapse();
	       Ext.getCmp("attShowPanel").getEl().applyStyles("text-align:left");
    };
	
function initToolBar(){
	 var xml_prefix=context+'fdt/config/';		
	atableTool=new dhtmlXToolbarObject(document.getElementById('tableTool'),'100%',16,"报表设计器");
    atableTool.setOnClickHandler(toolbarclickeventhander);
    atableTool.loadXML(xml_prefix+"_toolbarutf8.xml")
    $('tableTool').style.height='0px';
    atableTool.showBar();
}
function initMenubar(){
	 var xml_prefix=context+'fdt/config/';		
	aexpressionToolMenu=new dhtmlXMenuBarObject('expressionToolMenu','100%',16,"报表设计器",null,context+"imgs/formDef/");
    aexpressionToolMenu.setGfxPath("imgs/");
    aexpressionToolMenu.loadXML(xml_prefix+"_menuutf8.xml");
    aexpressionToolMenu.setOnClickHandler(menuclickeventhander);
    aexpressionToolMenu.showBar();
  
}


//显示文件列表tree
function getFiledTreePanel(field){
	if (Ext.getCmp('fileListWin')){
		Ext.getCmp('fileListWin').fileTree.field=field;
		Ext.getCmp('fileListWin').show();
		return;
	}
	
	
	
    var Tree = Ext.tree;
    var height=400;
	var treeType='FDTFORM';
    var fileTree = new Tree.TreePanel({
        animate:true,
        enableDD:false,
		field:field,
		loader: new Tree.TreeLoader(
		{dataUrl:"/fdt/designer/tools/tree.jsp?txtSearch=$&crrentIndex=1&currentExpression=$&esbKeyList="+treeType+"&flowType="+flowType}),
        lines: true,
        containerScroll: true,
        height:height,
        autoScroll:true,
        autoWidth:true,
        rootVisible:false
    });
    var fileRoot = new Tree.AsyncTreeNode({
    text: '选择映射字段',
    leaf : false,
    cls : 'folder',
	id:'$',
	expression:"$",
    draggable:false
    
    });
    fileTree.setRootNode(fileRoot);
             fileTree.on('beforeload', function(node){
		        fileTree.loader.dataUrl = '/fdt/designer/tools/tree.jsp?txtSearch='+node.id 
				                       + "&esbKeyList="+treeType
									   + "&crrentIndex=" + new String(node.id.length) 
									   + "&currentExpression=" + node.attributes.expression
									   +"&flowType="+flowType;
		        });
		        fileTree.on('click',function(node,e)
		        {
		               // e.stopEvent();
					var value=node.attributes.expression.replace('.{**}','');
					var theoremexpressionfiled=AttData.curFormPanel.findById(this.field.id.substring(0,this.field.id.length-4)+"theoremexpression");
					if (theoremexpressionfiled.getValue()==''
					     ||this.field.getValue()==theoremexpressionfiled.getValue()){
						theoremexpressionfiled.setValue(value.replace('{','').replace('}',''));
						setTmpValue(theoremexpressionfiled.name,value.replace('{','').replace('}',''));
					   }
						this.field.setValue(value.replace('{','').replace('}',''));
						setTmpValue(this.field.id,value.replace('{','').replace('}',''));
						var cnnamefiled=AttData.curFormPanel.findById(this.field.id.substring(0,this.field.id.length-4)+"cnname");
						cnnamefiled.setValue(node.attributes.text);
						setTmpValue(cnnamefiled.name,node.attributes.text);
		       });
  
    //fileTree.render();
    
  
    var win = new Ext.Window({
        id:"fileListWin",
        title: '可选映射对象',
        closable:true,
        autoScroll:true,
        width:300,
		fileTree:fileTree,
        autoHeight:true,
        buttons:[{text:'确 定',handler:function(){
								 win.hide();
                            }}],
        plain:true,
        items: [fileTree]
     });
	
    win.show();

    fileTree.getEl().applyStyles("text-align:left");
}
   
  
//重置指定div,销毁其中的对象
function resetObjDiv(obj,divId){
if(obj){
  if(obj.destroy){
    var div=document.getElementById(divId).cloneNode(false);
    var pDiv=document.getElementById(divId).parentNode;
    obj.destroy();
    //obj.getEl().createChild({tag:"div",id:id});
    pDiv.appendChild(div);
  }else{
    document.getElementById(divId).innerHTML="";
  }
}
}


function setDivInline(node){
      if(node.style.display!="none"){
        node.style.display="inline";
      }
}   
	
	


	
//把form中的元素替换成ext的对应元素
function handelForm(form,cfg){
      var els=form.select("input,select,textarea,div");
      var len=els.getCount();
      var newFieldsCfg=[];
      var hasFile=false;
	  
	  for(var i=0;i<len;i++){
        var node=els.item(i);
        var dom=node.dom;
        if(dom.id==""){
          dom.id=Ext.id();
        }
        var nothandle=dom.nothandle;
        if(!nothandle||nothandle!="true"){
			if (dom.custype){
				dom.cusType=dom.custype;
			}
          var type=dom.cusType?dom.cusType:(dom.tagName=="INPUT"?dom.type:dom.tagName.toLowerCase());
		  
          if(type && type!="br" && type!="p" && type!="div"){
            var tmp={};
            tmp.id=dom.id;
			tmp.pid=dom.pid;
			tmp.datatype=dom.datatype;
			if (dom.basearr){
				try{
				eval("baseArr="+dom.basearr);
				tmp.name=baseArr.baseArr.name;
				dom.name=baseArr.baseArr.name;
			
				}catch(e){
					tmp.name=dom.name;
				}
			}else{
				tmp.name=dom.name;
			}
            tmp.dom=dom;
            tmp.type=type;
            tmp.node=node;
            tmp.xy=node.getXY();
            tmp.w=node.dom.offsetWidth;
            tmp.h=node.dom.offsetHeight;	
		
            newFieldsCfg.push(tmp);
          }
        }
      }
		  
		  
		  currform = new Ext.form.BasicForm(form.id,{fileUpload:hasFile});
		  
		for(var i=0;i<newFieldsCfg.length;i++){
	        var type=newFieldsCfg[i].type;
	        var pid=newFieldsCfg[i].pid;
			var value=newFieldsCfg[i].value;
			var datatype=newFieldsCfg[i].datatype;
	        var fId=newFieldsCfg[i].id;
	        var name=newFieldsCfg[i].name;
			
	        var nod=newFieldsCfg[i].node;
	        var xy=newFieldsCfg[i].xy;
	        var w=newFieldsCfg[i].w;
	        var h=newFieldsCfg[i].h;
	        var dom=newFieldsCfg[i].dom;
	        var newField=null;
	        var c={pid:pid,value:value,msgTarget:'qtip'};
	        var isInline=false;
			var tempc={};
	        if(cfg){    
	          if(cfg.nameCfg[name]){
	            c=cfg.nameCfg[name];
	          }else if(cfg.idCfg[name]){
	            c=cfg.idCfg[name];
	          }
	      }
				
		if (datatype){		  
		     eval("Ext.applyIf(tempc,"+datatype+") ")  ;
			if (tempc.HtmlEditType){
		  	   type="textarea";
		    }else if (tempc.TextareaType){
		  	   type="textarea";
		    }else if(tempc.DateType){
			type="date";
			}else if(tempc.TextType){
			type="text";
			 }else if(tempc.TimeType){
			type="time";
	        }else if(tempc.NumberType){
			type="number";
			}	
		}
		
			 
	    if(type=="htmleditor"){
		 	if(tempc.HtmlEditType){
				Ext.apply(c,tempc.HtmlEditType);
			}
		  	newField=new Ext.form.HtmlEditor(Ext.apply(c,{applyTo:fId}));
        }else if(type=="number"){
		 	if(tempc.NumberType){
				Ext.apply(c,tempc.NumberType);
			}
		  	newField=new Ext.form.NumberField(Ext.apply(c,{applyTo:fId}));
        }else if(type=="time"){
		 	if(tempc.TimeType){
				Ext.apply(c,tempc.TimeType);
			}
		  	newField=new Ext.form.TimeField(Ext.apply(c,{applyTo:fId}));
		 
        }else if(type=="text"||type=="password"){
			if(tempc.TextType){
				
				Ext.apply(c,tempc.TextType);
			}	
			 newField=new Ext.form.TextField(Ext.apply(c,{applyTo:fId}));
			          
        }else if(type=="date"){
			if(tempc.DateType){
				Ext.apply(c,tempc.DateType);
			}
          newField=new Ext.form.DateField(Ext.apply(c,{applyTo:fId}));
	      setDivInline(newField.getEl().dom.parentNode);
           }else if(type=="select"){
			//处理下拉框
			var select=Ext.get(fId).dom;	
			var selectArr=[];
			var ognlOp=[];
			for (var l = 0; l < select.length; l++) {
				var combo={};
				Ext.apply(combo,{value:select[l].value});
				Ext.apply(combo,{text:select[l].text});
				ognlOp.push("'"+select[l].value+"':'"+select[l].text+"'");
				selectArr.push(combo);
				}
			 var jsonStore = new Ext.data.SimpleStore({
			    fields: [{name: 'text', mapping:'text'},{name: 'value', mapping:'value'}],
			    data: selectArr
		       });
		    
			
			  jsonStore.loadData(selectArr);
			  if (select.multiple || select.multiple=='true' ){
			   
			 
			  }else{
			  	selectWidth=50;
			  	if (select.offsetWidth>50){
					selectWidth=50
				}else if (select.offsetWidth<25){
					selectWidth=25;
				}
				
			   newField = new Ext.form.ComboBox(Ext.apply(c,{  
			   transform:fId,
			   ognlMap:"#{"+ognlOp.join(",")+"}",
			   disabled:select.disabled ? select.disabled:false,
		       store: jsonStore,
		       displayField: 'text',
		       valueField: 'value',
			   width:selectWidth,
		       mode: 'local',
		       typeAhead: true, //自动将第一个搜索到的选项补全输入
		       triggerAction: 'all',
		       emptyText: '全部',
		       selectOnFocus: true,
		       forceSelection: true
		     }));
			 
			  setDivInline(newField.getEl().dom.parentNode);
			  setDivInline(newField.getEl().dom.parentNode.parentNode);
			  setDivInline(newField.getEl().dom.parentNode.parentNode.parentNode);
			  setDivInline(newField.getEl().dom.parentNode.parentNode.parentNode.parentNode);
		     }
			
		 
        }else if(type=="radio"){
          newField=new Ext.form.Radio(Ext.apply(c,{boxLabel:dom.label,applyTo:fId}));
          setDivInline(newField.getEl().dom.parentNode);
        }else if(type=="checkbox"){
          newField=new Ext.form.Checkbox(Ext.apply(c,{boxLabel:dom.label,applyTo:fId}));
          setDivInline(newField.getEl().dom.parentNode);
 	    } else if (type == "textarea") {
	       if (tempc.TextareaType) {
	         Ext.apply(c, tempc.TextareaType);
	       }
	       newField = new Ext.form.TextArea(Ext.apply(c, {applyTo:fId}));
		   
        } else if (type == "zdocument" || type == 'zopinion'
                || type == 'zlabel' 
				|| type == 'zattach'
				||type == 'zmultipleselect'
				||type == 'checkboxlist'
				
				
				|| type == 'linechart' 
				|| type == 'piechart'
				||type == 'barchart'
				||type == 'column'
				
				|| type == 'f_gaugechart' 
				|| type == 'stackedbarchart'
				||type == 'columnchart'
			
				
				
				
				 ||type == 'radiolist'
				 
				 ) {
				
//	       dom.getXType = function() {
//	         return "label";
//	       }
//	       dom.onclick = function() {
//	         fieldonclick.call(dom, null);
//	       }
           newField = new Ext.form.Label(Ext.apply(c, {applyTo:fId}));
	     }else if(type=="type1") {   //在此加其它类型的处理
	       newField = new Ext.form.Label(Ext.apply(c, {value:docunet,applyTo:fId}));
	     }
	    if (newField) {
	      //cellMenu.setContextZone(newField.el.dom, newField.id);
	      newField.el.on("click", fieldonclick, newField);
	      newField.enable();
		 
	      newField.width = w;
	      newField.height = h;
	      currform.add(newField);
	    }
    }
}
    
function fieldonclick(obj){
	var type=this.getXType();
	
	if (type=='checkbox' ||type=='radio'){
		
	type='input';
	}
    if (type=='label'){
        type = this.getEl().dom.cusType;
	}
//	if (Ext.get("okBtn")){
//		  submitForm(true);
//	}
	
	
	editAllAtt(this.pid,type);
}

  
//加载数据配置页面后的回调函数
function callFormDefFn(){
  var body=arguments[0];
  var htmlStr=arguments[2].responseText;
  
  var divArr=arguments[0].select('div',true);
  
  for( k=0;divArr.length>k;k++){
	 	alert(divArr[k].outerHTML);
  } 
 handelForm(arguments[0].child("form"));
 createLayoutTree();

 return;
}





