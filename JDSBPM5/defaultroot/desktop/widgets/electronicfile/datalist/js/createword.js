	function printwordjszq(wordhand,allBookMarks,obj){
			//alert(wordhand);
			var bt = Ext.query("[name='"+wordhand+"bt']");
			if(Ext.get(bt[0])!=null){
			bt = Ext.get(bt[0]).dom.value;
			
			}else{
				bt = Ext.query("[name='"+wordhand+"wjbt']");
				if(Ext.get(bt[0])!=null){
					bt = Ext.get(bt[0]).dom.value;
					
				}
			}
			//var wordhands = Ext.query("[name='wordhand']");
			//var wordhand = Ext.get(wordhands[0].dom.value);
			for(i = 0 ; i < allBookMarks.length ; i++){
				//alert(allBookMarks[i]);
			     var strhtml = allBookMarks[i];
			     var strinput = "";
			    // alert(strhtml);
			 	 var objs = Ext.query("[name='"+wordhand+strhtml+"']"); 
			 	 
				if(strhtml == "ldps"||strhtml == "cbdwldyj"||strhtml == "qfr"||strhtml == "ldsyyj"
				   ||strhtml == "sgr"||strhtml == "ygdwhq"||strhtml == "ywqm"||strhtml == "bgsyj"
				   ||strhtml == "zbyj"||strhtml == "xbyj"||strhtml == "cyyj"||strhtml == "nbyj"
				   ||strhtml == "ldqy"||strhtml == "ldsyyj"||strhtml == "szqf"||strhtml == "hgyj"
				   ||strhtml == "ngbmldyj"||strhtml == "ldsyyj"||strhtml == "szyq"||strhtml == "cybmqm"
				   ||strhtml == "hldys"||strhtml == "hzys"||strhtml == "lbmqmb"||strhtml == "fgldyj"
				   ||strhtml == "cbbsyj"||strhtml == "cbdwldqf"||strhtml == "ksqy"||strhtml == "blqk"
				   ||strhtml == "ldyw"||strhtml == "ngksldps"||strhtml == "fhbmldpq"||strhtml == "qf"
				   ||strhtml == "hg"||strhtml == "bgssg"||strhtml == "blyj"||strhtml == "yjyj"
				   ||strhtml == "thyj"||strhtml == "tqyj"||strhtml == "zbdwsg"||strhtml == "zbdwrq"
				   ||strhtml == "bgshg"||strhtml == "bgsnbyj"||strhtml == "bmsh"||strhtml == "ldsq"||strhtml == "shyj"){
					for( s = 0 ; s < objs.length ;s++){
					   strinput = strinput + Ext.get(objs[s]).dom.innerText;
					   //var yushu = (s+1)%2;
					   //if(yushu == 0){
					     //strinput = strinput +"  "+"\r\n";
					  // }

					if(strinput!=""){
					  	  strinput = strinput +"  "+"\r\n";
					  // }
					   strinput = strinput.replace(/\&nbsp;/g, ""); 
					  }
					   
					}
			    }else if(strhtml=="dj"||strhtml=="mmcd"||strhtml=="jjcd"||strhtml=="mj"|| strhtml=="swbmldyj"|| strhtml=="bmbfzryj"|| strhtml=="miji"||strhtml=="qsjjjcd"||strhtml=="qsjmj"||strhtml=="yjjjcd"||strhtml=="yjmj"||strhtml=="mmdj"){
			    //var cc1 = $(".formc select[@name='"+strhand+strhtml+"'] option[@selected]").text(); 
			   // alert(cc1)
			            if(Ext.get(objs[0])!=null){
					    strinput = Ext.get(objs[0]).dom.value;}
					    if(strinput == "pt")strinput="普通";
						if(strinput == "yjjjcdpt")strinput="普通";
                        if(strinput == "yjmjpt")strinput="普通";
                        if(strinput == "yjmjjm")strinput="机密";
                        if(strinput == "yjjjcdjj")strinput="急";
                        if(strinput == "yjjmjuem")strinput="绝密";
                        if(strinput == "yjjjcdtj")strinput="特急";
                        if(strinput =="mm")strinput="秘密";
                        if(strinput =="jm")strinput="机密";
                        if(strinput =="juem")strinput="机密";
                        if(strinput =="juemi")strinput="绝密";
                        if(strinput =="yb")strinput="一般";
                        if(strinput =="pj")strinput="平急";
                        
                        if(strinput =="tj")strinput="特急";
                        if(strinput =="jj")strinput="加急";
                        if(strinput =="tt")strinput="特提";
                        
			    }else if(strhtml=="fwzh"){
			     	/*if(Ext.get(objs[0])!=null){
					    strinput = Ext.get(objs[0]).dom.value;
					 }*/
					// strinput = Ext.get('iframeupss').dom.contentWindow.document.getElementById("wenzhong").value;
			    	var ye = Ext.get('iframeupss').dom.contentWindow.document.getElementById("year").value;
			    	var zihao = Ext.get('iframeupss').dom.contentWindow.document.getElementById("zihao").value;
			    	
			    	/*alert(strinput);
			    	if(strinput=="wu")strinput="无";
			    	if(strinput=="neiwangzi")strinput="内网字";
			    	if(strinput=="neiguomijufamidian")strinput="内国密局发密电";
			    	if(strinput=="neiwangbanfa")strinput="内网办发";
			    	if(strinput=="neijizi")strinput="内机字";
			    	if(strinput=="neijifa")strinput="内机发";
			    	if(strinput=="neimibanfa")strinput="内密办发";
			    	if(strinput=="neimifa")strinput="内密发";
			    	if(strinput=="neidangbanxinxibanfa")strinput="内党办信息办发";
			    	if(strinput=="neiguomijufa")strinput="内国密局发";
			    	if(strinput=="neiguomifa")strinput="内国密发";
			    	if(strinput=="neiwangfa")strinput="内网发";*/
			    	var wzobj = Ext.get('iframeupss').dom.contentWindow.document.getElementById("wenzhong");
			    	for(i=0;i<wzobj.length;i++){
   						if(wzobj[i].selected==true){
    						strinput = wzobj[i].innerText;      //关键是通过option对象的innerText属性获取到选项文本『注』要兼容Firefox需使用innerHTML属性
   						}
					}
			    	
			    	strinput = strinput+ye+zihao;
			    	
			    }else{
			           if(Ext.get(objs[0])!=null)
			           strinput = Ext.get(objs[0]).dom.value;
			    }
			    //alert("input:"+strinput);
			    /*range = WordApplication.ActiveDocument.Bookmarks(strhtml).Range;
		  		range.InsertBefore(strinput);*/
		  		obj.setFieldValue(strhtml,strinput,'::GETMARK::');
			    strinput = "";
			}
			return bt;
	}
	
	function updateBtn(){
		var objs = Ext.query("[name='adddata.addbtn']");
		//alert(objs.length);
		if(Ext.get(objs[0])!=null){
			//alert(Ext.get(objs[0]).dom.value);
			Ext.get(objs[0]).dom.value = '查看处理笺';
		}
	}
	
	function getWordname(){
		var wordhands = Ext.query("[name='wordhand']");
		//alert("wordhands:"+wordhands.length);
		if(Ext.get(wordhands[0])!=null){
			var wordhand = Ext.get(wordhands[0]).dom.value;
			var iseles = Ext.query("[name='"+wordhand+"iselectronic']");
			if(Ext.get(iseles[0])!=null){
			// alert(Ext.get(iseles[0]).dom.value);
		}
			
		}
		return wordhand;
	}
	
	function gettitle(){
		var wordhand = getWordname();
		
		var objs = Ext.query("[name='"+wordhand+"bt']");
		//var tit = Ext.query("[name='dbean.title']");
		//alert(objs.length);
		if(Ext.get(objs[0])!=null){
			//alert(Ext.get(objs[0]).dom.value);
			return Ext.get(objs[0]).dom.value;
		}else{
			objs = Ext.query("[name='"+wordhand+"wjbt']");
				if(Ext.get(objs[0])!=null){
					return  Ext.get(objs[0]).dom.value;
				}
		}
	}
	
	function saveData(){
		var wname = getWordname();
		var iseles = Ext.query("[name='"+wname+"iselectronic']");
		if(Ext.get(iseles[0])!=null){
			 Ext.get(iseles[0]).dom.value = "1";
			 //alert(Ext.get(iseles[0]).dom.value);
			JDS.bpm.Form.Action.save(this.module);
		}
	}
	
	
	function getFileValue(nametype){
		var wname = getWordname();
		var objs;
		if(nametype=='date'){
			objs = Ext.query("[name='"+wname+"swrq']");
			if(Ext.get(objs[0])==null){
			objs = Ext.query("[name='"+wname+"ngsj']");
		}
		}else if(nametype=='cwdw'){
		objs = Ext.query("[name='"+wname+"ngdw']");
		if(Ext.get(objs[0])==null){
			objs = Ext.query("[name='"+wname+"lwdw']");
		}
		if(Ext.get(objs[0])==null){
			objs = Ext.query("[name='"+wname+"lwjg']");
		}
		}else{
			objs = Ext.query("[name='"+wname+nametype+"']");
		}
		
		if(Ext.get(objs[0])!=null){
			return Ext.get(objs[0]).dom.value;
			
		}
	}
	
	function getYjkValue(name){
		var wname = getWordname();
		var objs = Ext.query("[name='"+wname+name+"']"); 
		var strinput = "";
		for( s = 0 ; s < objs.length ;s++){
					   strinput = strinput + Ext.get(objs[s]).dom.innerText;
					   //var yushu = (s+1)%2;
					  // if(yushu == 0){
					     strinput = strinput +"  "+"\r\n";
					  // }
					   strinput = strinput.replace(/\&nbsp;/g, ""); 
					}
		//alert(strinput);
		return strinput;
	}
	function getWenzhong(){
	if(Ext.get('iframeupss')!=null){
	
	
		 var strinput = Ext.get('iframeupss').dom.contentWindow.document.getElementById("wenzhong").value;    
		return strinput;
		}else{
			return "";
		}
	}
	
	function getValueByName(name){
		var wname = getWordname();
		var iseles = Ext.query("[name='"+wname+name+"']"); 
		if(Ext.get(iseles[0])!=null){
			return  Ext.get(iseles[0]).dom.value;
			 //alert(Ext.get(iseles[0]).dom.value);
		}
		return '';
	}
   //tianjia
	function docidvalue(did){
		var iseles = Ext.query("[name='docid']");
		if(Ext.get(iseles[0])!=null){
			 Ext.get(iseles[0]).dom.value = did;
			 //alert(Ext.get(iseles[0]).dom.value);
		}
	}
	function showpic(){
	
		var win = new Ext.Window({
			title:'选择红头',
			width:200,
			height:Ext.getBody().getHeight()-50,
			html:"<iframe src='ttManagerAction_selectlist.action'></ifrmae>",
			buttons:[{ text: "确定", handler: function() { 
						
					}
			}]
			
		});
		win.show();
		
	}
	
	
	function cancelPrompt(uid){
		alert(Ext.get('windowprompt-list').getStore());
		return;
		if(confirm("确定取消提醒？")){
										
									Ext.Ajax.request({
										url:'/prompt/promptperson_cancel.action',
										params:{promIds:uid},
										method:'post',
										success:function(){
											Ext.get('windowprompt-list').getStore().reload();
										}
									});
									}
	}
	
	function openPrompt(uid,title,content,begindate){
	var form = new Ext.FormPanel({
	 	frame:true,
	 	width:550,
	 	labelWidth:100,
	 	labelAlign:'right',
	 	url:'/prompt/prompt_save.action',
	 	items:[{
	 		xtype:'textfield',
	 		fieldLabel:'标题',
	 		name:'prombean.title',
	 		id:'title',
	 		allowBlank:false,
	 		emptyText:'不能为空',
	 		msgTarget:'side',
	 		value:title
	 	},{
	 		xtype:'textarea',
	 		fieldLabel:'内容',
	 		allowBlank:false,
	 		emptyText:'不能为空',
	 		msgTarget:'side',
	 		name:'prombean.content',
	 		id:'content',
	 		value:content,
	 		anchor:'90%'
	 	},{
	 		xtype:'datefield',
	 		fieldLabel:'开始提醒时间',
	 		format:'Y-m-d',
	 		name:'prombean.begindate',
	 		id:'begindate',
	 		value:begindate,
	 		anchor:'50%'
	 	},{
	 		xtype:'textfield',
	 		name:'prombean.uuid',
	 		hidden:true,
	 		hideLabel:true,
	 		value:uid
	 		//value:''
	 	},{
	 		xtype:'datefield',
	 		name:'prombean.createdate',
	 		id:'createdate',
	 		format:'Y-m-d H:i:s',
	 		hidden:true,
	 		hideLabel:true
	 	},{
	 		xtype:'textfield',
	 		name:'personIds',
	 		id:'personIds',
	 		hidden:true,
	 		hideLabel:true
	 	}]
	 	
	 });
	 var win=new Ext.Window({
		 id:'promptwin',
	     width:578,
	     modal:true,
	     height:250,
	     title:'提醒信息',
	     closable:true,
		 collapsible:true,
	     autoScroll:true,
	     bodyStyle:'padding:5px 5px',
	     items:form
	 });
	 
	 
	 win.show();
}
	