

  Ext.onReady(function(){
     Ext.QuickTips.init();			
	var fieldtypedata = [
        ['datefield', '日期'],
		['textfield', '单行文本'],
	    ['numberfield', '数字'],
        ['timefield', '时间'],
        ['combobox', '下拉框'],
		['htmledit', 'HTML编辑器'],
		['textarea', '多行文本']     
    ];
	
	  var defaultproperty= [{	  	 
                    fieldLabel: '最大值',
                    name: 'maxValue',
                   allowBlank:false
                },{
                    fieldLabel: '最小值',
                    name: 'mixValue'
                },{
                    fieldLabel: '格式化方式',
                    name: 'formatType'
                }
            ];
		
        var checkListener= [{	
		            xtype: 'textarea',
					anchor: '100% -275' ,		
                    fieldLabel: '自定义校验规则',
                    name: 'maxValue',
                    allowBlank:false
                },{
                    fieldLabel: '数据输入监听',
					xtype: 'textarea',
					anchor: '100% -275' ,		
                    name: 'mixValue'
                },{
					xtype: 'textarea',
					anchor: '100% -275' ,		
                    fieldLabel: '数据输出监听',
                    name: 'format'
                }
            ];
			
      var typcom=new Ext.form.ComboBox({
	  	               id:'def_xtype',
                        fieldLabel: '字段类型',
                        hiddenName:'state',
                        store: new Ext.data.SimpleStore({
                            fields: ['abbr', 'state'],
                            data : fieldtypedata // from states.js
                         }),					
                        valueField:'abbr',
                        displayField:'state',
                        typeAhead: true,
                        mode: 'local',
                        triggerAction: 'all',
                        emptyText:'请选择字段类型',
                        selectOnFocus:true,
                        width:190
                    });		
		
			
    var fieldtypechange = function (field,newValue,oldValue){		
	   Ext.getCmp('typetabpanel').remove('testproperty');
	   Ext.getCmp('typetabpanel').add(getTypeTabPanel(field.getRawValue(),newValue));
	};
		typcom.on('change',fieldtypechange);	

   function getTypeTabPanel(rawtype,propertykey){
	  var datecom=new Ext.form.ComboBox({
                        fieldLabel: '展示样式',
                        hiddenName:'formatType',
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
						
	var dtimecom=new Ext.form.ComboBox({
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
                        triggerAction: 'all',
                        emptyText:'请选择显示最小间隔',
                        selectOnFocus:true,
                        width:190
                    });		
	var datefieldproperty= [ datecom,
	        new Ext.form.DateField({
                fieldLabel: '最大值',
                name: 'maxValue',
				format:'Y-m-d'
             
             }), new Ext.form.DateField({
                fieldLabel: '最小值',
				format:'Y-m-d',
                name: 'mixValue'
             })
            ];
		
		var textfieldproperty= [
		    new Ext.form.TimeField({
                fieldLabel: '最大值',
                name: 'maxValue',
                minValue: '0:00am',
                maxValue: '12:00pm'
             }), new Ext.form.TimeField({
                fieldLabel: '最小值',
                name: 'mixValue',
                minValue: '0:00am',
                maxValue: '12:00pm'
             }),{
                    fieldLabel: '格式化方式',
                    name: 'format'
                }, {
                    fieldLabel: 'Email',
                    name: 'email',
                    vtype:'email'
                }
            ];
		var numberfieldproperty= [{	  	 
                    fieldLabel: '最大值',
                    name: 'maxValue',					
                    allowBlank:false
                },{
                    fieldLabel: '最小值',
                    name: 'mixValue'
                },{
                    fieldLabel: '格式化方式',
                    name: 'format'
                }, dtimecom
            ];
			var timefieldproperty= [new Ext.form.TimeField({
                fieldLabel: '最大值',
                name: 'maxValue',
				format : "g:i A",
                minValue: '0:00am',
                maxValue: '12:00pm'
             }), new Ext.form.TimeField({
                fieldLabel: '最小值',
                name: 'mixValue',
                minValue: '0:00am',
                maxValue: '12:00pm'
             }), dtimecom,{
                    fieldLabel: '格式化方式',
                    name: 'formatType'
                }
            ];
		var comboboxproperty= [{
	  	 
                    fieldLabel: '最大值',
                    name: 'maxValue',					
                    allowBlank:false
                },{
                    fieldLabel: '最小值',
                    name: 'mixValue'
                },{
                    fieldLabel: '格式化方式',
                    name: 'format'
                }, {
                    fieldLabel: 'Email',
                    name: 'email',
                    vtype:'email'
                }
            ];
	    var htmleditproperty= [{	  	 
                    fieldLabel: '最大值',
                    name: 'maxValue',					
                    allowBlank:false
                },{
                    fieldLabel: '最小值',
                    name: 'mixValue'
                },{
                    fieldLabel: '格式化方式',
                    name: 'format'
                }, {
                    fieldLabel: 'Email',
                    name: 'email',
                    vtype:'email'
                }
            ];
			var textareaproperty= [{
	  	 
                    fieldLabel: '最大值',
                    name: 'maxValue',
					
                    allowBlank:false
                },{
                    fieldLabel: '最小值',
                    name: 'mixValue'
                },{
                    fieldLabel: '格式化方式',
                    name: 'format'
                }, {
                    fieldLabel: 'Email',
                    name: 'email',
                    vtype:'email'
                }
            ];
	var typeTabPanel ={
                title:rawtype+'属性',
				id:'testproperty',
                layout:'form',
                defaults: {width: 230},
                defaultType: 'textfield',
                items:eval(propertykey+'property')
            }		;
return	typeTabPanel;
}
		
		
		
			
   var tab2 = new Ext.FormPanel({
        title: '字段属性',
        bodyStyle:'padding:5px',
        items: [{
		   id :'typetabpanel',
           xtype:'tabpanel',
           plain:true,
           activeTab: 0,
           height:400,
		   autoScroll: true,
           defaults:{bodyStyle:'padding:10px'},
            items:[{
                title:'字段属性',
                layout:'form',
                defaults: {width: 230},
                defaultType: 'textfield',
               items: [typcom, {
			   	    id:'def_id',
                    xtype:'textfield',
                    fieldLabel: '字段ID',
					readOnly :true,
                    name: 'first',
                    anchor:'95%'
                },{
                    id:'def_name',
					xtype: 'textarea',
					//xtype:'textfield',
                    fieldLabel: '绑定字段',
                    name: 'last',
                    anchor:'95%'
                },
				{
                    id:'def_lenght',
					xtype:'textfield',
                    fieldLabel: '字段长度',
                    name: 'company',
                    anchor:'95%'
                }	,
				{
                    id:'def_allowBlank',
					xtype:'checkbox',
					//autoWidth :'true',
                    fieldLabel: '是否为空',
                    name: 'company',
                    width:30
                }				
				]
            },{
                title:'特有属性',
				id:'testproperty',
                layout:'form',
                defaults: {width: 230},
                defaultType: 'textfield',
                items:defaultproperty
            },{
                title:'高级属性',
                layout:'form',
				  labelAlign: 'top',
                defaults: {width: 230},
                defaultType: 'textfield',
                items:checkListener
            },{
                cls:'x-plain',
                title:'表达式',
                layout:'fit',
                items:Ext.get('expression')			
            }]
        }]
    });
        
        
         
		

        
        
        
    Ext.state.Manager.setProvider(new Ext.state.CookieProvider());
        var viewport = new Ext.Viewport({
         split:true,
         collapsible: true,
         autoScroll:true,
         layout:'border',		
        items:[
		      {
			  	region:'north',
                split:true,
				id:formId+'_def',
                collapsible: false,
                autoScroll:true,
                //layout:'border',
                minSize: 175,
				height:500,
                maxSize: 500,
                margins:'0 0 0 0',
			   autoLoad: {url: context+"formDisplay.action", params: "formId="+formId, callback:callFormDefFn}    },
            {
                region:'center',
                split:true,
                collapsible: true,
                autoScroll:true,
                layout:'border',
                items:[
                    {                       
                    region:'west',
                    id:'west-panel',
                    title:'业务对象',
                    split:true,                 
                    width: 200,
                    minSize: 175,
                    maxSize: 400,
                    collapsible: true,
                    margins:'0 0 0 5',
                    layout:'accordion',
                    layoutConfig:{
                        animate:true
                    },
                    items: [{
                        title:'工作流内部对象',
                        html:'<div id="BPMINNERTREE" style="overflow:auto; height:100%;border:1px solid #c3daf9;"></div>',
                        border:false,
						 defaults:{autoScroll: true},
                        iconCls:'nav'
                    },{
                        title:'组织机构对象',
                        html:'<div id="ORGTREE" style="overflow:auto; height:100%;border:1px solid #c3daf9;"></div>',
                        border:false,
						 defaults:{autoScroll: true},
                        iconCls:'settings'
                    },{
                        title:'用户扩展对象',
                        html:'<div id="USERDEFTREE" style="overflow:auto; height:100%;border:1px solid #c3daf9;"></div>',
                        border:false,
						 defaults:{autoScroll: true},
                        iconCls:'settings'
                    },{
                        title:'MAPDAO数据对象',
                        html:'<div id="MAPDAOTREE" style="overflow:auto; height:100%;border:1px solid #c3daf9;"></div>',
                        border:false,
						 defaults:{autoScroll: true},
                        iconCls:'settings'
                    },{
                        title:'SQLMAP数据库映射对象',
                        html:'<div id="SQLMAPTREE" style="overflow:auto; height:100%;border:1px solid #c3daf9;"></div>',
                        border:false,
						defaults:{autoScroll: true},
                        iconCls:'settings'
                    }]
                    },
                    {       
                       region:'center',                
                       autoHeight:true,
                       items:tab2
                    }
                ],
                margins:'0 0 0 0'
            }          
         ]
        });
		
       var Tree = Ext.tree;  
	   var BPMINNERTree=getTree("BPMINNERTREE","BPMINNER"); 
       var ORGTree=getTree("ORGTREE","ORG"); 
       var USERDEFTree=getTree("USERDEFTREE","USERDEF"); 
       var MAPDAOTree=getTree("MAPDAOTREE","MAPDAO"); 
       var MAPDAOTree=getTree("SQLMAPTREE","SQLMAP"); 
        
     function getTree(currel,treeType){
       
        var tree = new Tree.TreePanel({
        el:currel,
        animate:true,
        enableDD:false,
        loader: new Tree.TreeLoader({dataUrl:"objectTree.action?txtSearch=$&crrentIndex=1&currentExpression=$&treeType="+treeType}),
        lines: true,
        selModel: new Ext.tree.MultiSelectionModel(),
        containerScroll: true,
        autoHeight:true,
        rootVisible:false
        });
        var root = new Tree.AsyncTreeNode({
        text: '对象',
        leaf : false,
        cls : 'folder',
        draggable:false,
        id:'$',
        expression:"$"
        });
        tree.setRootNode(root);
        tree.on('beforeload', function(node){
        tree.loader.dataUrl = 'objectTree.action?txtSearch='+node.id + "&treeType="+treeType+"&crrentIndex=" + new String(node.id.length) + "&currentExpression=" + node.attributes.expression;
        });
        tree.on('click',function(node,e)
        {
                e.stopEvent();
                HtmlEdit.document.body.innerText = node.attributes.expression.replace('.{**}','');
            });

        tree.render();
    }

        });






 function doSearch(reload) {
	    var r = HtmlEdit.document.selection.createRange();
	    var str=r.text;
	    if(!str){
	      str=HtmlEdit.document.body.innerText;
	    }
        $('search_suggest').style.display = 'none';
      
        loadAjaxSearch(str,reload);
    }
    function debugExpression()
    {
        new Ajax.Updater(
            '',
            'fileSave.action',
            {
               asynchronous:true,
               method: 'post',
               onComplete:fillCellAtts,
               parameters: '&fileName=' + $F('currentpagename')
            }
        );
    }
    function searchExpression()
    {
        Ext.getCmp('expressionEditor').activate(1);
        $('dd').innerHTML = '';
        doSearch(true);
    }
    function editExpression()
    {
        new Ajax.Updater(
            '',    
            'fileEdit.action',
            {
               asynchronous:true,
               //evalScripts: true,
               method: 'post',
               onComplete:fillCellAtts,
               parameters: '&extGenID=' + $F('currentelementextid') + '&fileName=' + $F('currentpagename') + '&elementVO.id=' + $F('currentelementid') + '&elementVO.tagName=' + $F('currentTagName') + '&elementVO.cusType=' + $F('element_type') + '&elementVO.expression=' + escape(HtmlEdit.document.body.innerText)
            }
        );
    }
    function toolbareventhander(itemId,itemValue)
    {
            eval(itemId + '()');
    }
    function fillCellAtts(res)
    {
        var cellw = eval('(' + res.responseText + ')');
        Ext.getCmp(cellw.extGenID).expression = cellw.expression;
        Ext.getCmp(cellw.extGenID).cusType = cellw.cusType;
    }

    function fillEditorAtts(res)
    {
        var cellw = eval('(' + res.responseText + ')');
        if(cellw.expression)
        {
            HtmlEdit.document.body.innerHTML = cellw.expression;
        }
        else
        {
            HtmlEdit.document.body.innerHTML = '';
        }
        if(cellw.cusType)
        {
            $('element_type').value = cellw.cusType;
        }
        else
        {
            $('element_type').value = '';
        }
        $('currentelementid').value = cellw.id;
        $('currentelementextid').value = cellw.extGenID;
        $('currentTagName').value = cellw.tagName;
    }

    function menu_save()
    {
        debugExpression();    
    }

    function menu_create()
    {
        openFileWin();
    }
	
	
    var winFile;
    function openFileWin()
    {
        fileuploadfrm.document.body.innerHTML = '<form id="fileform" name="fileform" enctype="multipart/form-data" method="post" action="fileCreate.action"><br>请选择文件：<input type="file" size="40" id="uploadFile" name="uploadFile"><input id="fileName" name="fileName" value="test.html" type="hidden"></form>';
        if(!winFile)
        {
            $('win_file').style.display = '';
            winFile = new Ext.Window({
            title:'查找文件',
            id:'win',
            layout:'fit',
            closeAction:'hide',
            plain: true,
            //autoWidth:true,
            width: 500,
            height:80,
            minWidth: 500,
            minHeight: 80,
            maxWidth:500,
            maxHeight:100,
            resizable:false,    
            el:'win_file',
            buttons: [
                {
                    text:'确认',
                    handler:uploadFile
                },
                {
                    text: '取消',
                    handler: function(){
                        winFile.hide();
                }
            }]
        });
        }
        winFile.show();
    }

    function menu_open()
    {
        openFileWin4open();
    }

    var winFileOpen;
    function openFileWin4open()
    {
        //alert('asa');
        if(!winFileOpen)
        {
            showOpenTree();
            winFileOpen = new Ext.Window({
                title:'打开文件',
                id:'winopen',
                layout:'fit',
                closeAction:'hide',
                plain: false,
                width: 400,
                height:200,
                el:'win_fileopen',
                items:Ext.getCmp('win_fileopentree'),
                buttons: [
                    {
                        text:'确认',
                        handler:openFileHandleFaced
                    },
                    {
                        text: '取消',
                        handler: function(){
                            winFileOpen.hide();
                        }
                }]
            });
        }
        winFileOpen.show();
    }

    function uploadFile()
    {
        fileuploadfrm.document.fileform.submit();        
        winFile.hide();
    }


    function showOpenTree()
    {
        var fileTree = new Ext.tree.TreePanel({
        el:"win_fileopentree",
        id:'win_fileopentree',
        animate:true,
        enableDD:false,
        loader: new Ext.tree.TreeLoader({dataUrl:"fileList.action"}),
        lines: true,
        containerScroll: true,
        autoHeight:true,
        rootVisible:true
        });
        var fileRoot = new Ext.tree.AsyncTreeNode({
        text: 'templates',
        leaf : false,
        cls : 'folder',
        draggable:false,
        id:'templates'
        });
        fileTree.setRootNode(fileRoot);
        fileTree.on('beforeload', function(node){
            fileTree.loader.dataUrl = 'fileList.action';
        });
        fileTree.on('dblclick',openFileHandle);

        fileTree.render();
        fileTree.expandAll();
    }

    function openFileHandleFaced()
    {
        try
        {
            var root = Ext.getCmp('win_fileopentree').getRootNode();
            var childern = root.childNodes;
            var node;
            for(var i = 0;i < childern.length;i++)
            {
                if(childern[i].isSelected())
                {
                    node = childern[i];
                    break;
                }
            }
            openFileHandle(node,null);
        }catch(e){}

    }
	
    function openFileHandle(node,e)
    {        
        try
        {
            e.stopEvent();    
        }catch(e){}
        if(!node.leaf)
        {
            return;
        }
        openForm(node.id,node.text);
    }




  
	 
	









 function setDivInline(node){
      if(node.style.display!="none"){
        node.style.display="inline";
      }
    }   
    //把form中的元素替换成ext的对应元素
    function handelForm(form,cfg){
      if(!form){
        return;
      }
  
      var els=form.select("input,select,textarea");
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
          var type=dom.cusType?dom.cusType:(dom.tagName=="INPUT"?dom.type:dom.tagName.toLowerCase());
          if(type=="file"){
            hasFile=true;
          }
          if(type && type!="br" && type!="p" && type!="div"){
            var tmp={};		
            tmp.id=dom.id;
            tmp.name=dom.name;
            tmp.type=type;
            tmp.node=node;
            tmp.xy=node.getXY();
            tmp.w=node.dom.offsetWidth;
            tmp.h=node.dom.offsetHeight;		
            newFieldsCfg.push(tmp);
          }
        }
      }
    
      var fm2 = new Ext.form.BasicForm(form.id,{fileUpload:hasFile});

      for(var i=0;i<newFieldsCfg.length;i++){
        var type=newFieldsCfg[i].type;
        var fId=newFieldsCfg[i].id;
        var name=newFieldsCfg[i].name;
        var nod=newFieldsCfg[i].node;
        var xy=newFieldsCfg[i].xy;
        var w=newFieldsCfg[i].w;
        var h=newFieldsCfg[i].h;
        var newField=null;
		
        var c={};
        var isInline=false;
      if(cfg){    
          if(cfg.nameCfg[name]){
            c=cfg.nameCfg[name];
          }else if(cfg.idCfg[name]){
            c=cfg.idCfg[name];
          }
		  
        }
  c.autoHeight =true;
        if(type=="text"||type=="password" ){		
		  c.autoHeight =true;
          newField=new Ext.form.TextField(Ext.apply(c,{applyTo:fId}));
        }else if(type=="date"){
          newField=new Ext.form.DateField(Ext.apply(c,{applyTo:fId,format: 'Y-m-d'}));
          setDivInline(newField.getEl().dom.parentNode);
        }else if(type=="select"){
          newField=new Ext.form.ComboBox(Ext.apply(c,{triggerAction: 'all',transform:fId}));
          setDivInline(newField.getEl().dom.parentNode);
          newField.on("change",function (){alert(this.value);});
        }else if(type=="radio"){
          newField=new Ext.form.Radio(Ext.apply(c,{applyTo:fId}));
          setDivInline(newField.getEl().dom.parentNode);
        }else if(type=="checkbox"){
          newField=new Ext.form.Checkbox(Ext.apply(c,{applyTo:fId}));
          setDivInline(newField.getEl().dom.parentNode);
       }else if(type=="password"){
          newField=new Ext.form.TextField({applyTo:fId});
          setDivInline(newField.getEl().dom.parentNode);
        }else if(type=="button"||type=="submit"||type=="reset"){
          /*
          var tmpId=fId+"-btnDiv";
          var div=document.createElement("<div>");
          div.id=tmpId;
          nod.dom.parentNode.appendChild(div);
          var tpl=null;
          var btn=new Ext.Button({template:tpl,id:fId+"-btn",text:nod.dom.value,applyTo:tmpId,handler:function(){var id=this.id.replace(/-btn$/,"");document.getElementById(id).click();}});
          nod.dom.style.display="none";
          */
        }else if(type=="file"){
          //fm2.fileUpload=true;
        }else if(type=="textarea"){
          newField=new Ext.form.TextArea(Ext.apply(c,{applyTo:fId}));
        }
        if(newField){
		newField.on("focus",focusFild);
        newField.width=w;
        newField.height=h;
        fm2.add(newField);
        }
      }
    }
    
    
    
    
    
    
/******************************************************************form2ext**************************************/
function focusFild(obj)
{
		Ext.getCmp('def_xtype').setValue(obj.getXType());
		Ext.getCmp('def_id').setValue(obj.getItemId());
	    Ext.getCmp('def_name').setValue(obj.getName());
		Ext.getCmp('def_lenght').setValue(obj.width);
}


    //加载数据配置页面后的回调函数
function callFormDefFn(){
      var body=arguments[0];
      var text=arguments[2].responseText;
      var pat1=/<attCfg>([^<]+)<\/attCfg>/;
      var pat2=/<html>((?:.|\s)+?)<\/html>/;
      var pat3=/<tabCfg>((?:.|\s)+?)<\/tabCfg>/;
      var cfgStr="";
      var htmlStr="";
      var tabStr="";
      if(pat1.test(text)){
        cfgStr=RegExp.$1;
      }
      if(pat2.test(text)){
        htmlStr=RegExp.$1;
      }
      if(pat3.test(text)){
        tabStr=RegExp.$1;
      }
      var cfg=null;
      var tabCfg=null;
   
      if(cfgStr){
        cfg=Ext.decode(cfgStr);
      }
	  
      if(htmlStr==""){
        htmlStr=text;
      }
      if(tabStr){
        tabCfg=Ext.decode(tabStr);;
      }
      body.dom.innerHTML=htmlStr;
      handelForm(arguments[0].child("form"),cfg);
      
      var tbarCfg=[{
                    flag:"add",
                    text:'添加',
                    tooltip:'加一行',
                    handler:handleBarBtnClick,
                    iconCls:'add'
                }, '-', {
                    flag:"del",
                    text:'删除',
                    tooltip:'删除当前行',
                    handler:handleBarBtnClick,
                    iconCls:'option'
                },'-',{
                    flag:"mod",
                    text:'修改',
                    tooltip:'修改当前行',
                    handler:handleBarBtnClick,
                    iconCls:'remove'
                },'-',{
                    flag:"save",
                    text:'保存',
                    tooltip:'保存当前数据',
                    handler:handleBarBtnClick,
                    iconCls:'remove'
                },'-',{
                    flag:"ref",
                    text:'刷新',
                    tooltip:'刷新',
                    handler:handleBarBtnClick,
                    iconCls:'ref'
                }];
      if(tabCfg){
        if(!tabCfg.selfCfg){
          tabCfg.selfCfg={};
        }
        
        
        var formGridPanelSaveUrl=tabCfg.selfCfg.saveUrl;
        var formGridPanelId=tabCfg.selfCfg.id;
        
        tabCfg.selfCfg.tbar=tbarCfg;
        var grid=createGridByData(tabCfg);
      }
      return;
    
    
      //grid的按钮单击
      function handleBarBtnClick(){
        var flag=this.flag;
        var p=Ext.getCmp(formGridPanelId);
        var hasSelected=p.getSelectionModel().hasSelection();
        if(flag=="add"){
          openFormWin(p,"add");
  
        }else if(flag=="mod"){
          if(hasSelected){
            openFormWin(p,"mod");
          }else{
            Ext.Msg.alert("提示","请选择一条记录");
          }
        }else if(flag=="del"){
          if(hasSelected){
            var r=Ext.Msg.confirm("确认","真的要删除选中记录吗?",
                            function(btn){
                              if(btn=="yes"){
                                var selected=this.getSelectionModel().getSelected();
                                this.getStore().remove(selected);
                              }
                            },p);
          }else{
            Ext.Msg.alert("提示","请选择一条记录");
          }
        }else if(flag=="save"){
          var rs=p.getStore();
          var count=rs.getCount();
          var rtn=[];
          for(var i=0;i<count;i++){
            rtn.push(rs.getAt(i).data);
          }
          /*
          new Ext.Ajax.request({
                      url:formGridPanelSaveUrl,
                      params:"value="+Ext.encode(rtn)
                    });
                    */
          
          alert(Ext.encode(rtn));
          alert(formGridPanelSaveUrl);
          
        }else if(flag=="ref"){
          p.getStore().reload();
        }
        return;
      }
    }
    
  //添加或修改列表数据
  function openFormWin(p,flag){
    var record={};
    var data={};
    if(flag=="mod"){
      record=p.getSelectionModel().getSelected();
      data=record.data
    }
    var pan=new Ext.FormPanel({
          frame:true,
          autoScroll:true,
          id:"colEditForm_"+p.id,
          bodyStyle:'padding:5px 5px 0',
          items:[{}]
        });
    var cm=p.getColumnModel();
    var fWidth=120;
    for (var i=0,len=cm.getColumnCount();i<len;i++){
      var txt=cm.getColumnHeader(i);
      var colName=cm.getDataIndex(i);
      if(!colName||colName==""){//是sm
        continue;
      }
      var type=cm.config[i].ctype;
	  
      if(!type||type=="float"||type=="int"||type=="text"){
        type="text";
      }
      var field=null;
      if(type=="text"){
        field=new Ext.form.TextField({
          fieldLabel:txt,
          width:fWidth,
          style:"type:"+"text",
          name:colName,
          id:colName,
		  value:data[colName]
        })
      }else if(type=="date"){
        field=new Ext.form.DateField({
                fieldLabel:txt,
                name:colName,
                id:colName,
                width:fWidth,
                format: 'Y-m-d',
				value:data[colName]
            });
      }else if(type=="select"){
        var selData=[];
        var sel=document.getElementById(colName+"-select");
        var ops=sel.options;
        //alert(ops[0].text);
        //return;
        for(var j=0;j<ops.length;j++){
          var tmp={};
          tmp.key=ops[j].key;
          tmp.text=ops[j].text;
          if(!tmp.key){
            tmp.key=tmp.text;
          }
          selData.push(tmp);
        }
        var store = new Ext.data.JsonStore({
          data:selData,
          fields:["key","text"]
        });
        field=new Ext.form.ComboBox({
                fieldLabel:txt,
                
                valueName:colName,
                hiddenName:colName,
                
                store:store,
                valueField:'key',
                displayField:'text',
                mode: 'local',
                
                id:colName+"combo",
                width:fWidth,
                typeAhead: true,
                triggerAction: 'all',
                listClass: 'x-combo-list-small'
                ,value:data[colName]
            });
      }
      pan.add(field);
    }
    
    var winCfg={id:flag+"GridWin",
      title: flag=="add"?"添加":"修改",
      closable:true,
      autoScroll:true,
    
      width:800,
      height:450,
      plain:true,

      items: [
        pan
        //{}
      ],
      buttons:[{
        text:"确定",
        handler:function(){
          var val=pan.getForm().getValues();
          alert(Ext.encode(val));
          if(flag=="add"){
            var re=new Ext.data.Record(val);
            p.getStore().add(re);
          }else if(flag=="mod"){
            for(var k in val){
              record.set(k,val[k]);
            }
          }
          win.close();
        }
      },{
        text:"关闭",
        handler:function(){
          win.close();
        }
      }
      ]
    };
    var win=new Ext.Window(winCfg);
    win.show();
  }
  










