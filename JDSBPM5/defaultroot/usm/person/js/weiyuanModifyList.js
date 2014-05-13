
 
Ext.onReady(function(){

    var newFormWin;

    Ext.QuickTips.init();
    

    var xg = Ext.grid;
    var p = "";
    var store = new Ext.data.Store({
      proxy: new Ext.data.HttpProxy({   
            url: '/usm/orgPersonListGrid.do?orgid='+orgid+''  //从此页获取数据
        }), 
         reader: new Ext.data.JsonReader({
            root: 'data',
            totalProperty: 'totalCount',
            id: 'personid',
            fields: [
            {name: 'personid', type: 'string'},
            {name: 'enname', type: 'string'},
            {name: 'password', type: 'string'},
            {name: 'accountstat', type: 'string'},
            {name: 'accountttl', type: 'string'},
            {name: 'passquestion', type: 'string'},
            {name: 'sex', type: 'string'},
            {name: 'passanswer', type: 'string'},
            {name: 'email1', type: 'string'},
            {name: 'birthday', type: 'string'},
            {name: 'zip', type: 'string'},
            {name: 'city', type: 'string'},
            {name: 'country', type: 'string'},
            {name: 'nation', type: 'string'},
            {name: 'nativeplace', type: 'string'},
            {name: 'job', type: 'string'},
            {name: 'duty', type: 'string'},
            {name: 'cnname', type: 'string'},
            {name: 'officetel', type: 'string'},
            {name: 'photo', type: 'string'},
            {name: 'officefax', type: 'string'},
            {name: 'hometel', type: 'string'},
            {name: 'homefax', type: 'string'},
            {name: 'otherinfo', type: 'string'},
            {name: 'homeadd', type: 'string'},
            {name: 'officeadd', type: 'string'},
            {name: 'connectaddr', type: 'string'},
            {name: 'school', type: 'string'},
            {name: 'mobile', type: 'string'},
            {name: 'type', type: 'string'},
            {name: 'sex', type: 'string'},
            {name: 'marry', type: 'string'},
            {name: 'politicalstat', type: 'string'},
            {name: 'lastedulevel', type: 'string'},
            {name: 'lastdegree', type: 'string'},
            {name: 'indextype', type: 'string'}
            ]
        })
    });
      
      var sm = new Ext.grid.CheckboxSelectionModel(); // add checkbox column
    var cm = new Ext.grid.ColumnModel([sm,
     new Ext.grid.RowNumberer(
	     {
		     header:'序号',
		     width:32,
		     align:'center'
	     }
        ),{
           id: 'personid', // id assigned so we can apply custom css (e.g. .x-grid-col-topic b { color:#333 })
           header: "人员编号",
           dataIndex: 'personid',
           align:'center',
		   //hidden :false,
           width: 60
        },{
           header: "姓名",
           dataIndex: 'cnname',
           align:'center',
           width: 100
        },{
           header: "英文",
           dataIndex: 'enname',
           align:'center',
           width: 100
        },{
           header: "密码",
           dataIndex: 'password',
           align:'center',
           hidden :true,
           width: 100
        },{
           header: "账号状态",
           dataIndex: 'accountstat',
           align:'center',
           hidden :true,
           width: 100
        },{
           header: "账号有效期",
           dataIndex: 'accountttl',
           align:'center',
           hidden :true,
           width: 100
        },{
           header: "密码问题",
           dataIndex: 'passquestion',
           align:'center',
           hidden :true,
           width: 100
        },{
           header: "问题回答",
           dataIndex: 'passanswer',
           align:'center',
           hidden :true,
           width: 100
        },{
           header: "邮箱",
           dataIndex: 'email1',
           align:'center',
           hidden :true,
           width: 100
        },{
           header: "出生日期",
           dataIndex: 'birthday',
           align:'center',
           hidden :true,
           width: 100
        },{
           header: "邮编",
           dataIndex: 'zip',
           align:'center',
           hidden :true,
           width: 100
        },{
           header: "城市",
           dataIndex: 'city',
           align:'center',
           hidden :true,
           width: 100
        },{
           header: "国籍",
           dataIndex: 'country',
           align:'center',
           hidden :true,
           width: 100
        },{
           header: "民族",
           dataIndex: 'nation',
           align:'center',
           hidden :true,
           width: 60
        },{
           header: "祖籍",
           dataIndex: 'nativeplace',
           align:'center',
           hidden :true,
           width: 60
        },{
           header: "工作",
           dataIndex: 'job',
           align:'center',
           hidden :true,
           width: 60
        },{
           header: "职务",
           dataIndex: 'duty',
           align:'center',
           hidden :true,
           width: 60
        },{
           header: "办公电话",
           dataIndex: 'officetel',
           
           align:'center',
           width: 60
        },{
           header: "类型",
           dataIndex: 'type',
           hidden :true,
           align:'center',
           width: 60
        },{
           header: "办公传真",
           dataIndex: 'officefax',
           hidden :true,
           align:'center',
           width: 60
        },{
           header: "家庭电话",
           dataIndex: 'hometel',
           hidden :true,
           align:'center',
           width: 60
        },{
           header: "家庭传真",
           dataIndex: 'homefax',
           hidden :true,
           align:'center',
           width: 60
        },{
           header: "其它信息",
           dataIndex: 'otherinfo',
           hidden :true,
           align:'center',
           width: 60
        },{
           header: "家庭地址",
           dataIndex: 'homeadd',
           hidden :true,
           align:'center',
           width: 60
        },{
           header: "办公地址",
           dataIndex: 'officeadd',
           hidden :true,
           align:'center',
           width: 60
        },{
           header: "联系地址",
           dataIndex: 'connectaddr',
           hidden :true,
           align:'center',
           width: 60
        },{
           header: "毕业学校",
           dataIndex: 'school',
           hidden :true,
           align:'center',
           width: 60
        },{
           header: "手机",
           dataIndex: 'mobile',
           align:'center',
           width: 60
        },{
           header: "相片",
           dataIndex: 'photo',
           align:'center',
           hidden :true,
           width: 60
        },{
           header: "性别",
           dataIndex: 'sex',
           align:'center',
           hidden :true,
           width: 60
        },{
           header: "婚否",
           dataIndex: 'marry',
           align:'center',
           hidden :true,
           width: 60
        },{
           header: "政治面貌",
           dataIndex: 'politicalstat',
           align:'center',
           hidden :true,
           width: 60
        },{
           header: "最高学位",
           dataIndex: 'lastdegree',
           align:'center',
           hidden :true,
           width: 60
        },{
           header: "最高学历",
           dataIndex: 'lastedulevel',
           align:'center',
           hidden :true,
           width: 60
        },{
           header: "排序",
           dataIndex: 'indextype',
           align:'center',
           hidden :false,
           width: 60
        }
        
        ]);
      cm.defaultSortable = true;
      //配置视图信息
      var view=new Ext.grid.GridView({forceFit:true,sortAscText :'正序', sortDescText :'倒序'});
      view.columnsText='列显示/隐藏';
      
  
    ////////////////////////////////////////////////////////////////////////////////////////
    // Grid 1
    ////////////////////////////////////////////////////////////////////////////////////////
      var name = new Ext.form.TextField({   
                width:175, 
                name: 'name',
                allowBlank:true 
            }); 
      var persongrid = new Ext.grid.GridPanel({
        id:'button-grid',
        store:store,
	    cm:cm,
	    sm:sm,
        view:view,  
        viewConfig: {
            forceFit:true
        },
        buttonAlign:'center',
        tbar:['按姓名：',name, {// 查询按钮   
                    id : 'newModelButton',    
                    icon : "/usm/img/search.png",   
                    cls : "x-btn-text-icon",   
                    handler :querydepartMessage     
            },{
            text:'新增',
            tooltip:'新增',
            iconCls:'add',
            handler:function(){
            if(parentorgid=='10000001')
            {
                Ext.Msg.alert("提示", "此组织不能添加人员，请在您的部门下建人员");
            }
            else
            {
	             createPersonWindow(orgid);
		         store.reload();
		         //w.show();
	            }
            }
        },'-',{
            text:'删除',
            tooltip:'删除',
            iconCls:'remove',
            handler:showDeleteMessage
     
        }],
        
         bbar: new Ext.PagingToolbar({
            pageSize: 10,
            store: store,
            displayInfo: true,
            displayMsg: '当前显示 {0} - {1}条记录 /共 {2}条记录',
            emptyMsg: "当前没有可以显示的数据"
        }),     
        
       width:900,
        height:470,
        frame:true,
        title:'委员信息列表',
        iconCls:'icon-grid',
        renderTo: document.body
    });
    
   
    persongrid.render();
  store.load({params:{start:0, limit:10}});

    persongrid.on("rowdblclick", function(persongrid) {
        loadFormData(persongrid);
    });

    // 载入被选择的数据行的表单数据
    var loadFormData = function(persongrid) {
        var _record = persongrid.getSelectionModel().getSelected();
        if (!_record) {
            Ext.Msg.alert('修改操作', '请选择要修改的一项！');
        } else {
          
        	 
           Ext.getCmp("personid").value=_record.get('personid');
           Ext.getCmp("cnname").value=_record.get('cnname');
           Ext.getCmp("enname").value=_record.get('enname');
           Ext.getCmp("password").value=_record.get('password');
           Ext.getCmp("accountstat").value=_record.get('accountstat');
           Ext.getCmp("accountttl").value=_record.get('accountttl');
           Ext.getCmp("sex").value=_record.get('sex');
           Ext.getCmp("marry").value=_record.get('marry');
           Ext.getCmp("politicalstat").value=_record.get('politicalstat');
           Ext.getCmp("lastedulevel").value=_record.get('lastedulevel');
           Ext.getCmp("lastdegree").value=_record.get('lastdegree');
          
           Ext.getCmp("photo").value=_record.get('photo');
           Ext.getCmp("indextype").value=_record.get('indextype');
           
            if(Ext.getCmp("lastedulevel").value=='0')
           {
               Ext.getCmp("lastedulevel").value = "无";
           }else if(Ext.getCmp("lastedulevel").value=='1')
           {
           	   Ext.getCmp("lastedulevel").value = "小学";
           }else if(Ext.getCmp("lastedulevel").value=='2')
           {
           	   Ext.getCmp("lastedulevel").value = "初中";
           }else if(Ext.getCmp("lastedulevel").value=='3')
           {
           	   Ext.getCmp("lastedulevel").value = "高中(中专、技校)";
           }else if(Ext.getCmp("lastedulevel").value=='4')
           {
           	   Ext.getCmp("lastedulevel").value = "大专";
           }else if(Ext.getCmp("lastedulevel").value=='5')
           {
           	   Ext.getCmp("lastedulevel").value = "本科";
           }else if(Ext.getCmp("lastedulevel").value=='6')
           {
           	   Ext.getCmp("lastedulevel").value = "研究生";
           }else if(Ext.getCmp("lastedulevel").value=='7')
           {
           	   Ext.getCmp("lastedulevel").value = "博士";
           }
           
           
           
            if(Ext.getCmp("lastdegree").value=='0')
           {
               Ext.getCmp("lastdegree").value = "保密";
           }else if(Ext.getCmp("lastdegree").value=='1')
           {
           	   Ext.getCmp("lastdegree").value = "男";
           }else if(Ext.getCmp("lastdegree").value=='2')
           {
           	   Ext.getCmp("lastdegree").value = "女";
           }
           
           
           
           if(Ext.getCmp("sex").value=='0')
           {
               Ext.getCmp("sex").value = "无学位";
           }else if(Ext.getCmp("sex").value=='1')
           {
           	   Ext.getCmp("sex").value = "学士";
           }else if(Ext.getCmp("sex").value=='2')
           {
           	   Ext.getCmp("sex").value = "硕士";
           }else if(Ext.getCmp("sex").value=='3')
           {
           	   Ext.getCmp("sex").value = "博士(后)";
           }
           
           
           
            if(Ext.getCmp("accountstat").value=='0')
           {
               Ext.getCmp("accountstat").value = "临时账号";
           }else if(Ext.getCmp("accountstat").value=='1')
           {
           	   Ext.getCmp("accountstat").value = "普通账号(连续120天不登陆，即失效（被禁止）)";
           }else if(Ext.getCmp("accountstat").value=='2')
           {
           	   Ext.getCmp("accountstat").value = "永久";
           } 
           
           if(Ext.getCmp("marry").value=='0')
           {
               Ext.getCmp("marry").value = "保密";
           }else if(Ext.getCmp("marry").value=='1')
           {
           	   Ext.getCmp("marry").value = "已婚";
           }else if(Ext.getCmp("marry").value=='2')
           {
           	   Ext.getCmp("marry").value = "未婚";
           }else if(Ext.getCmp("marry").value=='3')
           {
           	   Ext.getCmp("marry").value = "离异";
           }
           
           if(Ext.getCmp("politicalstat").value=='0')
           {
               Ext.getCmp("politicalstat").value = "群众";
           }else if(Ext.getCmp("politicalstat").value=='1')
           {
           	   Ext.getCmp("politicalstat").value = "团员";
           }else if(Ext.getCmp("politicalstat").value=='2')
           {
           	   Ext.getCmp("politicalstat").value = "党员";
           }else if(Ext.getCmp("politicalstat").value=='3')
           {
           	   Ext.getCmp("politicalstat").value = "其它";
           }
           
           
           if(_record.get('passquestion')=='null')
           {
           	 Ext.getCmp("passquestion").value='';
           }
           else
           {
           	 Ext.getCmp("passquestion").value=_record.get('passquestion');
           }
           
           
           if(_record.get('passanswer')=='null')
           {
           	 Ext.getCmp("passanswer").value=''; 
           }
           else
           {
           	 Ext.getCmp("passanswer").value=_record.get('passanswer'); 
           }           
            
           if(_record.get('email1')=='null')
           {
           	 Ext.getCmp("email1").value='';
           }
           else
           {
           	Ext.getCmp("email1").value=_record.get('email1');
           }
		   
           Ext.getCmp("birthday").value=_record.get('birthday');
            if(_record.get('zip')=='null')
           {
           	 Ext.getCmp("zip").value='';
           }
           else
           {
             Ext.getCmp("zip").value=_record.get('zip');
           }
           
            if(_record.get('city')=='null')
           {
           	 Ext.getCmp("city").value='';
           }
           else
           {
              Ext.getCmp("city").value=_record.get('city');
           }
          
          if(_record.get('country')=='null')
           {
           	 Ext.getCmp("country").value='';
           }
           else
           {
             Ext.getCmp("country").value=_record.get('country');
           }
           if(_record.get('nation')=='null')
           {
           	 Ext.getCmp("nation").value='';
           }
           else
           {
             Ext.getCmp("nation").value=_record.get('nation');
           }
           
           if(_record.get('nativeplace')=='null')
           {
           	 Ext.getCmp("nativeplace").value='';
           }
           else
           {
             Ext.getCmp("nativeplace").value=_record.get('nativeplace');
           }
           
           if(_record.get('job')=='null')
           {
           	 Ext.getCmp("job").value='';
           }
           else
           {
             Ext.getCmp("job").value=_record.get('job');
           }
           
           if(_record.get('duty')=='null')
           {
           	  Ext.getCmp("duty").value='';
           }
           else
           {
              Ext.getCmp("duty").value=_record.get('duty');
           }
          
           if(_record.get('officetel')=='null')
           {
           	  Ext.getCmp("officetel").value='';
           }
           else
           {
              Ext.getCmp("officetel").value=_record.get('officetel');
           }
           
            if(_record.get('mobile')=='null')
           {
           	  Ext.getCmp("mobile").value='';
           }
           else
           {
              Ext.getCmp("mobile").value=_record.get('mobile');
           }
           
           if(_record.get('officefax')=='null')
           {
           	   Ext.getCmp("officefax").value='';
           }
           else
           {
               Ext.getCmp("officefax").value=_record.get('officefax');
           }
           
           	   //Ext.getCmp("hometel").value=_record.get('hometel');
              //alert(_record.get('hometel'));
           if(_record.get('hometel')=='null')
           {
           	   //alert(Ext.getCmp("hometel").value+"llllll");
           	   Ext.getCmp("hometel").value='';
           }
           else
           {
                 Ext.getCmp("hometel").value=_record.get('hometel');
           }
           
            if(_record.get('homefax')=='null')
           {
           	    Ext.getCmp("homefax").value='';
           }
           else
           {
                Ext.getCmp("homefax").value=_record.get('homefax');
           }
           if(_record.get('homeadd')=='null')
           {
           		Ext.getCmp("homeadd").value='';           	   
           }
           else
           {
           	     Ext.getCmp("homeadd").value=_record.get('homeadd');
                 
           }
           
           if(_record.get('officeadd')=='null')
           {
           	    
           	    Ext.getCmp("officeadd").value='';
           }
           else
           {
                  Ext.getCmp("officeadd").value=_record.get('officeadd');
           }
           
            if(_record.get('connectaddr')=='null')
           {
           	    Ext.getCmp("connectaddr").value='';
           }
           else
           {
                Ext.getCmp("connectaddr").value=_record.get('connectaddr');
           }
           
            if(_record.get('school')=='null')
           {
           	    
           	    Ext.getCmp("school").value=''; 
           }
           else
           {
                Ext.getCmp("school").value=_record.get('school'); 
           }
             
          myFormWin();	
          
          document.getElementById("img").src=_record.get('photo');
        }
    };

    var myFormWin = function() {
        
        if (!newFormWin) {
            newFormWin = new Ext.Window( {
                layout : 'fit',
                width : 700,
                height : 560,
                //closeAction : 'hide',
                maximizable:true,
                autoScroll:true,
                plain : true,
                title : '修改人员信息',
                items:fsf,
                listeners:{"beforedestroy":function(obj){		
            	//alert("想关闭我，这是不可能的!");		
            	//obj.show();	
            	window.location="weiyuanModifyList.jsp?orgid="+orgid; 	
            	return false;	
            	}}
            });
        }
        newFormWin.show('New1');
    };    
    
    function toggleDetails(btn, pressed){
        var view = persongrid.getView();
        view.showPreview = pressed;
        view.refresh();
    }    
    
     var fsf = new Ext.FormPanel({
        labelWidth: 95, 
		labelAlign: 'right',  
		fileUpload: true, 
		title: '',    
		buttonAlign:'center',    
		bodyStyle:'padding:5px 5px 0',   
		anchor:'100%',  
		frame:true,    
        items: [{
            collapsible: true,
            checkboxToggle:false,
            collapsed: true,
            xtype:'fieldset',
            title:'基本信息',
            autoHeight:true,
            collapsed:false,
            width:650,
            checkboxName:'jiben',
            //defaults: {width: 350},
            layout:'column',
            border:true,
            labelSeparator:'：',
            items:[                  
                   {       
                columnWidth:.2,            
                layout: 'form',            
                border:false,
		               items: [    
		               
                {      title:'',
               		   html:'<img id="img" border="0" width="100" height="120" src="/upload/no.jpg"/>'
	                           
	                   }
	                   ] }  ,               
            
                      {  
               columnWidth:.4,            
               layout: 'form',            
               border:false,            
               items: [    
	             {                
		               xtype: 'fileuploadfield',
		               id: 'photo',
		               emptyText: '请选择相片',             
		               fieldLabel: '相片',                
		               name: 'file',
		               anchor:'100%',
			           buttonCfg: {
			                text: '浏览',
			                iconCls: 'upload-icon'
			            }
	                   },   		  
               		  {       
               		   id:'personid',     
		               xtype:'textfield',                
		               fieldLabel: '人员编号',                
		               name: 'personinfo.personid',
		               readOnly:true,                 
		               anchor:'82%'
	                   },{       
               		   id:'cnname',     
		               xtype:'textfield',                
		               fieldLabel: '姓名',                
		               name: 'personinfo.cnname',
		               allowBlank:false,//不允许为空
		               blankText:'中文名不能为空',//错误提示内容  
		               checkboxToggle:true,
		               regex: /^[\u4E00-\u9FA5]+$/, 
          			   regexText: '只能输入汉字' ,		                              
		               anchor:'82%',
		              // validator:CheckUserName,//指定验证器  
                       invalidText:'用户名已经被注册！'  
	                   },{ 
	                   id:'enname',               
		               xtype:'textfield',                
		               fieldLabel: '英文名字',                
		               name: 'personinfo.enname',
		               allowBlank:false,//不允许为空
		               blankText:'英文名不能为空',//错误提示内容   
		               regex:/^[a-zA-Z]+$/, 
          			   regexText: '只能输入英文字母',                
		              anchor:'82%'
	                   },
	                   {
	                    id:'password',
	                    xtype:'textfield',	
	                    fieldLabel: '密码',	
	                    name: 'personaccount.password',
	                    inputType:'password', 
		                anchor:'82%'
	                   },          
		                new Ext.form.ComboBox({
	                    id:'accountstat',
	                    fieldLabel: '账号状态',
	                    name: 'personaccount.accountstat',
	                    //hiddenName:&apos;hyear&apos;,//提交到后台的input的name，重要
	                     //readOnly : true,//是否只读 
	                    
	                    store: new Ext.data.SimpleStore({   ////填充数据
	                        fields: ['accountstat'],
	                        data: [['临时账号'], ['普通账号(连续120天不登陆，即失效（被禁止）)'],['永久']]
	                    }),	                     
	                    displayField:'accountstat',
	                    emptyText:'选择账号状态……',
	                    mode: 'local',   //数据模式，local代表本地数据  
	                    allowBlank:false,
	                    triggerAction:'all',
	                    selectOnFocus:true,
	                    anchor:'82%'
	                   }),{  
	                    id:'accountttl',              
		                xtype:'textfield',                
		                fieldLabel: '账号有效期(天)',                
		                name: 'personaccount.accountttl',
		                regex:/^[0-9]+$/, 
          			    regexText: '只能输入数字',                  
		                anchor:'82%'
	                  },
                     {
	                    id:'passquestion',
	                    xtype:'textfield',
	                    fieldLabel: '密码问题',
	                    name: 'personaccount.passquestion',
	                    anchor:'82%'
                      },{
                        id:'passanswer',
                        xtype:'textfield',
	                    fieldLabel: '问题答案',
	                    name: 'personaccount.passanswer',
	                    anchor:'82%'
                      }
	                   ]
                    },{
                		columnWidth:.3,
                        layout: 'form',
                        border:false, 
                       items: [
	                  new Ext.form.ComboBox({
	                  	id:'sex',
	                    fieldLabel: '性别',
	                    name: 'personinfo.sex',  
	                    store: new Ext.data.SimpleStore({   ////填充数据
	                        fields: ['sex'],
	                        data: [['保密'], ['男'],['女']]
	                    }),	                     
	                    displayField:'sex',
	                    triggerAction:'all',
	                    emptyText:'选择性别状态……',
	                    mode: 'local',   //数据模式，local代表本地数据  
	                    triggerAction:'all',
	                    selectOnFocus:true,
	                    anchor:'100%'
	                   }),
	                   new Ext.form.DateField({
	                    id:'birthday',
	                    xtype:'textfield',
                        fieldLabel: '出生年月',
                        format:'Y-m-d h:i:s',
                        name: 'personinfo.birthday',
                        anchor:'100%'
                        }),{                
		               id:'job',
		               xtype:'textfield',                
		               fieldLabel: '职务名称',                
		               name: 'personinfo.job',                
		               anchor:'100%'
	                   },{                
		               id:'duty',
		               xtype:'textfield',                
		               fieldLabel: '党派团体职务',                
		               name: 'personinfo.duty',                
		              anchor:'100%'
	                   },
	                   
	                   new Ext.form.ComboBox({
	                    id:'marry',
	                    fieldLabel: '婚否',
	                    name: 'personinfo.marry',  
	                    store: new Ext.data.SimpleStore({   ////填充数据
	                        fields: ['marry'],
	                        data: [['保密'], ['已婚'],['未婚'],['离异']]
	                    }),	  
	                    triggerAction:'all',                   
	                    displayField:'marry',
	                    emptyText:'选择婚否状态……',
	                    mode: 'local',   //数据模式，local代表本地数据  	                    
	                    selectOnFocus:true,
	                    anchor:'100%'
	                   }),
	                    new Ext.form.ComboBox({
	                    id:'politicalstat',
	                    fieldLabel: '政治面貌',
	                    name: 'personinfo.politicalstat',  
	                    store: new Ext.data.SimpleStore({   ////填充数据
	                        fields: ['politicalstat'],
	                        data: [['群众'], ['团员'],['党员'],['其它']]
	                    }),	 
	                    triggerAction:'all',                    
	                    displayField:'politicalstat',
	                    emptyText:'选择政治面貌状态……',
	                    mode: 'local',   //数据模式，local代表本地数据  
	                    
	                    selectOnFocus:true,
	                    anchor:'100%'
	                   }),
	                   
                       new Ext.form.ComboBox({
                        id:'lastedulevel',
                    	fieldLabel: '文化程度',
                    	name: 'personinfo.lastedulevel',
                   		store: new Ext.data.SimpleStore({
                        fields: ['lastedulevel'],
                        data: [['无'],['小学'], ['初中'],['高中(中专、技校)'],['大专'],['本科'],['研究生'],['博士']]
                       }),
                        triggerAction:'all',
	                    displayField:'lastedulevel',
	                    emptyText:'选择最高学历……',
	                    mode: 'local',
	                   
	                    selectOnFocus:true,
	                    anchor:'100%'
                      }),
                        new Ext.form.ComboBox({
                        id:'lastdegree',
                    	fieldLabel: '最高学位',
                    	name: 'personinfo.lastdegree',
                   		store: new Ext.data.SimpleStore({
                        fields: ['lastdegree'],
                        data: [['无学位'],['学士'], ['硕士'],['博士(后)']]
                       }),
                        triggerAction:'all',
	                    displayField:'lastdegree',
	                    emptyText:'选择最高学位……',
	                    mode: 'local',
	                   
	                    selectOnFocus:true,
	                   anchor:'100%'
                      })     
	                   ]

            }]

        },
        { 
           xtype:"fieldset",
          //checkboxToggle:true,//关键参数，其他和以前的一样
          checkboxName:"other",
          title:"联系信息",
          collapsed: true,
           width:650,
           height:100,
            //xtype:'fieldset',
            collapsible: true,
            checkboxToggle:false,//关键参数，其他和以前的一样            
            //title:'选项信息',
            autoHeight:true,
           // defaults: {width: 400},
            layout:'column',
            border:true,
            labelSeparator:'：',
            items:[
                      {  
               columnWidth:.5,            
               layout: 'form',            
               border:false,           
               items: [
	                   {
	                    id:'hometel',
	                    xtype:'textfield',                
		                fieldLabel: '家庭电话',                
		                name: 'personinfo.hometel',
		                 regex:/^((0[1-9]{3})?(0[12][0-9])?[-])?\d{6,8}$/,    
		                regexText:'请输入正确的家庭电话号码',                
		                anchor:'82%'
	                   },{
	                    id:'homefax',
	                    xtype:'textfield',
	                    fieldLabel: '通信地址邮编',
	                    regex:/^([0-9]{6})$/,
		                regexText:'请输入正确的邮编',
	                    name: 'personinfo.homefax',
	                    anchor:'82%'
                      },{
                        id:'officetel',
                        xtype:'textfield',                
		                fieldLabel: '单位电话',
		                regex:/^((0[1-9]{3})?(0[12][0-9])?[-])?\d{6,8}$/,    
		                regexText:'请输入正确的电话号码',                 
		                name: 'personinfo.officetel',                
		                anchor:'82%'
	                    
	                   },{
                        id:'officefax',
                        xtype:'textfield',
	                    fieldLabel: '办公传真号',
	                    regex:/^((0[1-9]{3})?(0[12][0-9])?[-])?\d{6,8}$/,    
		                regexText:'请输入正确的办公传真号',
	                    name: 'personinfo.officefax',
	                    anchor:'82%'
                      }, {
                        id:'mobile',
                        xtype:'textfield',
	                    fieldLabel: '手机',
	                    name: 'personinfo.mobile',
	                    regex:/(^0?[1][35][0-9]{9}$)/,
	                    regexText:'请输入正确的手机号',
	                    anchor:'82%'
                      },
                       {
                        id:'email1',
                        xtype:'textfield',
	                    fieldLabel: '邮箱',
	                    name: 'personinfo.email1',
	                    anchor:'82%'
                      }]
                    },{
                		columnWidth:.4,
                        layout: 'form',
                        border:false, 
                     items: [
                     
                     {
                        id:'nativeplace',
                        xtype:'textfield',
	                    fieldLabel: '单位邮编',
	                    name: 'personinfo.nativeplace',
	                    anchor:'100%'
                      },{
                        id:'nation',
                        xtype:'textfield',
	                    fieldLabel: '民族',
	                    name: 'personinfo.nation',
	                    anchor:'100%'
                      },{
                        id:'country',
                        xtype:'textfield',
	                    fieldLabel: '国籍',
	                    name: 'personinfo.country',
	                    anchor:'100%'
                      },{
                        id:'city',
                        xtype:'textfield',
	                    fieldLabel: '通信地址',
	                    name: 'personinfo.city',
	                    anchor:'100%'
                      },
                      {
                        id:'zip',
                        xtype:'textfield',
	                    fieldLabel: '住址邮编',
	                    name: 'personinfo.zip',
	                    regex:/^([0-9]{6})$/,
	                    regexText:'请输入正确的邮编',
	                   anchor:'100%'
                      },
                      {
                        id:'indextype',
                        xtype:'textfield',
	                    fieldLabel: '排序',
	                    name: 'personinfo.indextype',
	                    anchor:'100%'
                      }
                    
	                 ]

            }]
        },{
        
            xtype:"fieldset",
         //checkboxToggle:true,//关键参数，其他和以前的一样
          checkboxName:"otherIfno",
          title:"其它信息",
          collapsed: true,
           width:650,

            //xtype:'fieldset',
            collapsible: true,
            checkboxToggle:false,//关键参数，其他和以前的一样            
           // title:'选项信息',
            autoHeight:true,           
            //layout:'column',
            border:true,
            labelSeparator:'：',
            items:[
              
                   
            		{  
                       id:'homeadd',       
		               xtype:'textfield',                
		               fieldLabel: '家庭地址',                
		               name: 'personinfo.homeadd',                
		               anchor:'100%'
	               },{          
		               id:'connectaddr',   
		               xtype:'textfield',                
		               fieldLabel: '单位地址',                
		               name: 'personinfo.connectaddr',                
		               anchor:'100%'
	               },{          
		               id:'officeadd', 
		               xtype:'textfield',                
		               fieldLabel: '工作单位',                
		               name: 'personinfo.officeadd',                
		               anchor:'100%'
	               },{          
		               id:'school', 
		               xtype:'textfield',                
		               fieldLabel: '毕业院校',                
		               name: 'personinfo.school',                
		               anchor:'100%'
	               }]

        }
        
        ],

 buttons: [{
            text: '修改',
            handler: function(){
             var _record = persongrid.getSelectionModel().getSelected();
                if(fsf.form.isValid()) {
                    this.disabled=true;    
                    flag = false;
                    fsf.getForm().submit({ 
                        url: '/usm/personInfoModify.do?personid=' + _record.get('personid'),
                        method: 'POST',
                        waitTitle: '请稍等……', 
                        waitMsg: '正在上传数据……',  
                        success: function() {
                            Ext.Msg.alert("成功", "操作成功！");
                            persongrid.getView().refresh(); 
                            this.disabled=false;
                        },
                        failure: function() {
                            Ext.Msg.alert("出错啦", "数据保存失败！");
                            this.disabled=false;
                        }
                    });
                  
                }
            }
        }, {
            text: '重置',
            handler: function() {
                fsf.form.reset();
            }
        }
        ]

    });
    
   
      
    function showDeleteMessage(){
        Ext.MessageBox.confirm('消息', '确认删除？',doDel);
    }
    function doDel(btn){
        if(btn=='yes'){
            var row=persongrid.getSelections();
            var jsonData = "";
            for(var i=0,len=row.length;i<len;i++){
                var ss = row[i].get("personid");
                jsonData = jsonData + ss + ",";
            }
            if(jsonData.length == 0 ){
                 Ext.MessageBox.alert('消息', '请选择要删除的数据。');
            }else{
		               Ext.Ajax.request({
		                //请求地址
		                method: 'POST',      
		                url: '../personPointDel.do?orgid='+orgid+'&personidjsonData=' + jsonData,
		                //成功时回调      
		                success: function(response, options){
		                   Ext.MessageBox.alert('消息', '删除成功');
                           store.reload();
		                },
		                //成功时回调      
		                failure: function(response, options){
		                    Ext.MessageBox.show({
                               title:'消息',
                               msg: '删除成功！',
                               buttons: Ext.Msg.OK,
                               icon: Ext.MessageBox.ERROR
                           });
		                }
		            });

               
            }
        }
        
    }
         function querydepartMessage()
         { 
	        var name = Ext.get('name').dom.value;
	    	store.load({params:{start:0, limit:10,name:name}}); 
         }
         //fsf.render(document.body);
         //document.getElementById("img").src = photo;   
                  
    
});

function createPersonWindow(orgid){
  var win=new Ext.Window({
		            title:"添加委员信息",
		            width:735,
		            height:480,
		            //maximizable:true,
				  	//closeAction:'close',
				  	closable:true,
				  	collapsible:true,
				  	autoScroll:true,
				  	items:new Ext.Panel({
				        title: '',        
					  	height:435,
				        width:700,
				        html: '<iframe scrolling="yes" frameborder="0" src="/usm/person/weiyuanInsertwindow.jsp?orgid='+orgid+'" height=100% width=100%></iframe>'
				    })
		        	});
		        
		         win.show();
}