<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="ww"%>
<%
	String path = request.getContextPath();
	String contextpath = request.getContextPath() + "/";
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";

	//rtx.RTXSvrApi rtxApi = new rtx.RTXSvrApi(); 
	//String account = (String)request.getAttribute("account");//通过loginAction 获取rtx账号
	//String ip = rtxApi.getServerIP(); //服务器ip地址
	//String key= rtxApi.getSessionKey(account); //获取sessionKey
%>

<html>
	<head>
		<title>测试系统</title>
		<SCRIPT LANGUAGE="JavaScript" type="text/javascript">
	var context='<%=contextpath%>';
	
	var clientUrl="";
	var personid='<ww:property value="person.ID"/>';
	var currUserName='<ww:property value="person.name"/>';
	var currUserOrgName='<ww:property value="person.orgs[0]"/>';
	var expjspath='<ww:property value="$getExplorerJs.path" escape="false"/>';
	var updateVersion='<ww:property value="updateVersion"/>';
    </SCRIPT>

		<script
			src="<%=contextpath%>desktop/widgets/dockmenu/jquery-1.2.6.min.js"
			type='text/javascript'></script>
		<script
			src="<%=contextpath%>desktop/widgets/dockmenu/jquery.dock-test.js"
			type='text/javascript'></script>

		<script type="text/javascript" src="<%=contextpath%>js/extAll.js"></script>
		<script type="text/javascript"
			src="<%=contextpath%>desktop/baosongbiaodan/biaodanform.js"></script>
		<script src="<%=contextpath%>js/JDS.js" type="text/javascript"></script>
		<script id="script_desktop" language="javascript"
			src="<%=contextpath%>desktop/js/deskTopAll.js"></script>
		<script id="script_Jame"
			src="<%=contextpath%>desktop/widgets/jame/js/JameAll.js"
			type="text/javascript"></script>
		<script src="<%=contextpath%>desktop/widgets/worklist/js/WorkList.js"
			type="text/javascript"></script>
		<script
			src="<%=contextpath%>desktop/widgets/bpm/form/display/js/Form.js"
			type="text/javascript"></script>
		<!-- 添加的JS -->
		<script src="<%=contextpath%>desktop/baosongbiaodan/biaodanform.js"
			type="text/javascript"></script>
		<script type="text/javascript"
			src="/desktop/widgets/columntreemetting/js/mettingindex.js"></script>
		<!-- 归档获取表单数据填入模版-->
		<script type="text/javascript"
			src="/desktop/widgets/electronicfile/datalist/js/createword.js"></script>

		<script LANGUAGE="JavaScript" type="text/javascript">
		/*function RtxSycn(){
    		if(''!=''){
		 try{ 
         
        var account = "";
        //var key = "";
        var ip=""; 

       var RTXCRoot = RTXAX.GetObject("KernalRoot");    //客户端SDK 
  
        RTXCRoot.LoginSessionKey(ip,8100,account,key); 
       }catch(e){ 
       } 
       }
	} */

	Ext.onReady(function(){
		loadMsgref();
	})
	
	function closeMsgref(){
		Ext.getCmp('ajaxref').close();
	}
	
	function loadMsgref(){
		if(Ext.getCmp('ajaxref')!=null){
			Ext.getCmp('ajaxref').close();
		}
//修改
	Ext.Ajax.request({
				url:'tixing_count.action',
				method:'post',
				success:function(o){
						if(o.responseText>0){
                                      document.getElementById('bkgroundID').src="desktop/ti.mp3";
							var win = new Ext.Window({
 							id:'ajaxref',
 							title:'待办事项提醒',
 							// maximizable:true,//窗口最大化
                                        minimizable:true,//窗口最小化
                                        layout: 'fit',   
                                        plain:true,
 							   listeners:{
							   minimize:function(){
        					   if(this.minimizable){
           			 			this.close();	
       						 }
   							 }
							}, 
 							width:300,
 							height:200,
							html:'<iframe width="100%" height="100%" src="'+context+'desktop/widgets/tixing/windowPromptGrid.jsp"></iframe>'
 							});
 							win.setPagePosition(Ext.getBody().getWidth()-300,Ext.getBody().getHeight()-200);
 							win.show();
						}
				}
		});
 		setTimeout(loadMsgref, 1000 * 600);
 		//setTimeout(closeMsgref, 1000 * 10);
	}
	
　　function loadMsg(){
　　　　　　Ext.Ajax.request({ 
　　　　　　　　url: '/prompt/prompt_getPromList.action', 
　　　　　　　　success: function(o){
					
					var jsonobject = Ext.util.JSON.decode(o.responseText); 
 					
 					for(var i=0;i<jsonobject.length;i++){
 						//alert(jsonobject[i].uuid);
 						if(Ext.getCmp(i+'ajaxref')!=null){
 							
 							Ext.getCmp(i+'ajaxref').close();
 						}
 						
 						var uid = jsonobject[i].uuid;
 						var temp = i;
 						
 						var win = new Ext.Window({
 							id:i+'ajaxref',
 							title:jsonobject[i].creator+'提醒您：'+jsonobject[i].title,
 							width:300,
 							height:150,
							html:jsonobject[i].content,
							buttons:[{
								text:'确定',
								handler:function(){
									Ext.getCmp(temp+'ajaxref').close();
								}
							},{
								text:'取消提醒',
								handler:function(){
									if(confirm("确定取消提醒？")){
										
									Ext.Ajax.request({
										url:'/prompt/promptperson_cancel.action',
										params:{promIds:uid},
										method:'post',
										success:function(){
											win.close();
										}
									});
									}
									
									
								}
							}]
 						});
 						
 							win.setPagePosition(Ext.getBody().getWidth()-300,Ext.getBody().getHeight()-(150*(i%3+1)));
 						
 						
 						win.show();
 						
 					}
				}, 
　　　　　　　　method: 'POST'
　　　　　}); 
	setTimeout(loadMsg, 1000 * 20);

　　　　}　　 
    </script>
		<script type="text/javascript">
		var date=new Date();
	   	var d=date.getDay();
	   	var x=(d=='0'?'日':(d=='6'?'六':(d=='5'?'五':(d=='4'?'四':(d=='3'?'三':(d=='2'?'二':'一'))))));
	   
	</script>
	
	<body>
	<!--
	onbeforeunload="RunOnBeforeUnload()" onunload="RunOnUnload()"> 
	-->
<script language="javascript"> 
function RunOnBeforeUnload() { 
//window.event.returnValue = ''; 
} 
function RunOnUnload() { 
 Ext.Ajax.request({
			 	url:'clear.action',
			 	method:'post',
			 	params:{
			 	'personId':'',
			 	'event':''
			 	}
			 })
}

</script> 
               <BGSOUND ID="bkgroundID" LOOP=0 VOLUME="-600" SRC="" mce_SRC="desktop/ti.mp3">
		<div id="x-desktop">
			<dl id="x-shortcuts"></dl>


			<div id="Bar">
				<!-- <a href="javascript:openWinById('jame')"  id='bar1' name="即时沟通" title="即时沟通">
<img src="<%=contextpath%>desktop/widgets/dockmenu/images/jsxx_50.png" alt="即时沟通" border="0" /> </a> -->


				<a href="javascript:openWinById('JDSPreferences')" name="控制面板"
					title="Google compres"> <img
						src="<%=contextpath%>desktop/widgets/dockmenu/images/ghbj_50.png"
						alt="Froogle_alt" border="0" /> </a>

			</div>
			<div id="jame-container">
				<div id="Clockshow" class="showclock">
					<embed src="<%=contextpath%>desktop/resources/images/showclock.swf"
						width=200 height=100 autostart=true wmode=transparent></embed>
					<br>
					<center>
						<span style="font-weight: bold; color: #FFFFFF;"> <script
								type="text/javascript">			
				
			</script>
						</span>
					</center>
				</div>
			</div>


		</div>

		<div id="ux-taskbar">
			<div id="ux-taskbar-start"></div>
			<div id="ux-taskbar-panel-wrap">
				<div id="ux-quickstart-panel"></div>
				<div id="ux-taskbuttons-panel"></div>
				<div id="ux-clock-panel"></div>
				
			
			</div>
			<!-- jyc2012-11-10修改 -->
			<div id="dengluxinxi" style="text-align:right; margin-top:7px;>
				<table >
					<tr style="size-color:#ffffff">
	         			<td><span style="font-weight: bold; color: #FFFFFF;">欢迎</span></td>
	         			<td><span style="font-weight: bold; color: #FFF000;">&nbsp;&nbsp;</span></td>
						<td><span style="font-weight: bold; color: #FFFFFF;"> 
	         			<script type="text/javascript">document.write(currUserName);</script> </span></td>
						<td><span style="font-weight: bold; color: #FFFFFF;"><</span></td>
						<td><span style="font-weight: bold; color: #FFFFFF;"> 
	         			<script type="text/javascript">document.write(currUserOrgName);</script> </span></td>
	         			<td><span style="font-weight: bold; color: #FFFFFF;">></span></td>
					</tr> 
				</table> 
			</div>
			<div class="x-clear"></div>
		</div>
		<div class="txt" id="selectPersonAjax" style="display: none"></div>
		<div class="txt" id="routelog" style="display: none"></div>
		<div class="txt" id="evalwin" style="display: none"></div>


		<div id=explorer>
			<div id="north_area">
				<div class="menuEl">
					
				</div>
				<div class="toolbarEl"></div>
				<table id="query_makeup"
					style="width: 100%; align: left; height: 20px; border: 0px; background: #ececec url(/js/ext/resources/images/default/toolbar/bg.gif) repeat-y top left;">
					<tr calss="iebar_tr" style="height: 20px;">
						<td class="left_buttonsEl"
							style="width:400px; align: left; height: 20px;"></td>
						<td class="uribarEl"
							style="width: 500px; align: left; height: 20px;"></td>
						<td class="right_buttonsEl" align="left"></td>
					</tr>
				</table>
			</div>
			<div id="itemsArea" class="itemsArea"></div>
			<div id="loading_mask">
				&#160;
			</div>
		</div>

		<div id=ieexplorer>
			<div id="ienorth_area">
				<table id="query_makeup"
					style="width: 100%; align: left; height: 20px; border: 0px; background: #ececec url(/js/ext/resources/images/default/toolbar/bg.gif) repeat-y top left;">
					<tr calss="iebar_tr" style="height: 20px;">
						<td class="left_buttonsEl"
							style="width: 100px; align: left; height: 20px;"></td>
						<td class="uribarEl"
							style="width: 500px; align: left; height: 20px;"></td>
						<td class="left_buttonsEl" align="left"></td>
					</tr>
				</table>

				<div class="iemenuEl"></div>

			</div>
		</div>




		<!-- <OBJECT id="RTXAX" data="data:application/x-oleobject;base64,fajuXg4WLUqEJ7bDM/7aTQADAAAaAAAAGgAAAA==" classid="clsid:5EEEA87D-160E-4A2D-8427-B6C333FEDA4D" VIEWASTEXT></OBJECT> 
    <script>RtxSycn();</script> -->
	</body>


</html>
<script>

function openKzxdWin(url){
	var _width = 1000;
			var _height = 580;
			var testwin = new Ext.Window({
					title : '添加登记信息',
					layout : 'fit',
					width : _width,
					minWidth : 350,
					height : _height,
					minHeight : 200,
					html:"<iframe id='djiframe' name='djiframe' width='" + (_width-10)  + "' height='" + (_height - 50) + "' src='" + url + "'></iframe>"
			});
		testwin.show();
}

function showaddwin(xmlmodel,grid){
			var _width = 900;
			var _height = Ext.getBody().getHeight()-40;
			var addwin = new Ext.Window({
					title : '添加登记信息',
					layout : 'fit',
					width : _width,
					minWidth : 350,
					height : _height,
					minHeight : 200,
					y:0,
					html:"<iframe id='djiframe' name='djiframe' width='" + (_width-10)  + "' height='" + (_height - 50) + "' src='/SwdjAction_addpage.action?xmlmodel=" + xmlmodel + "'></iframe>",
					tbar:[
						{
							id : 'djsave',
							icon : "/usm/img/save.gif",
							text : '登  记',
							cls : "x-btn-text-icon",
							handler : function(){
								alert('submit');
								var fn = Ext.get('djiframe').dom.contentWindow.document.getElementById("addDj");
									
								fn.submit();
								grid.getStore().reload();
								addwin.close();
							}
						},{
							id : 'djreset',
							icon : "/usm/img/depart.gif",
							text : '重  置',
							cls : "x-btn-text-icon",
							handler : function(){
								var fn = Ext.get('djiframe').dom.contentWindow.document.getElementById("addDj");
								fn.reset();
							}
						}
					]
			});
			addwin.show();
}

//来文
function showlwaddwin(xmlmodel){
			var _width = 900;
			var _height = 580;
			var lwaddwin = new Ext.Window({
					title : '添加登记信息',
					layout : 'fit',
					width : _width,
					minWidth : 350,
					height : _height,
					minHeight : 200,
					html:"<iframe id='djiframe' name='djiframe' width='" + (_width-10)  + "' height='" + (_height - 50) + "' src='/LwdjAction_addpage.action?xmlmodel=" + xmlmodel + "'></iframe>",
					tbar:[
						{
							id : 'djsave',
							icon : "/usm/img/save.gif",
							text : '登  记',
							cls : "x-btn-text-icon",
							handler : function(){
								var fn = Ext.get('djiframe').dom.contentWindow.document.getElementById("addDj");
								fn.submit({
									success:function(){
										Ext.Msg.alert('信息','登记信息添加成功!');
									},
									failure:function(){
										Ext.Msg.alert('信息','操作失败!');
									}
								});
								lwaddwin.close();
							}
						},{
							id : 'djreset',
							icon : "/usm/img/depart.gif",
							text : '重  置',
							cls : "x-btn-text-icon",
							handler : function(){
								var fn = Ext.get('djiframe').dom.contentWindow.document.getElementById("addDj");
								fn.reset();
							}
						}
					]
			});
			lwaddwin.show();
}

function showupdatewin(uuid,xmlmodel){
			var _width = 900;
			var _height = 580;
			var updatewin = new Ext.Window({
					title : '修改登记信息',
					layout : 'fit',
					width : _width,
					minWidth : 350,
					height : _height,
					minHeight : 200,
					html:"<iframe id='djiframe' name='djiframe' width='" + (_width-30)  + "' height='" + (_height - 60) + "' src='/SwdjAction_updatePage.action?xmlmodel=" + xmlmodel + "&uuid=" + uuid + "'></iframe>",
					tbar:[
						{
							id : 'djsave',
							icon : "/usm/img/save.gif",
							text : '修  改',
							cls : "x-btn-text-icon",
							handler : function(){
								var fn = Ext.get('djiframe').dom.contentWindow.document.getElementById("updateDj");
								fn.submit({
									success:function(){
										Ext.Msg.alert('信息','登记信息添加成功!');
									},
									failure:function(){
										Ext.Msg.alert('信息','操作失败!');
									}
								});
								updatewin.close();
							}
						}
					]
			});
			updatewin.show();
}

//来文
function showlwupdatewin(uuid,xmlmodel){
			var _width = 900;
			var _height = 580;
			var updatewin = new Ext.Window({
					title : '修改登记信息',
					layout : 'fit',
					width : _width,
					minWidth : 350,
					height : _height,
					minHeight : 200,
					html:"<iframe id='djiframe' name='djiframe' width='" + (_width-10)  + "' height='" + (_height - 50) + "' src='/SwdjAction_updatePage.action?xmlmodel=" + xmlmodel + "&uuid=" + uuid + "'></iframe>",
					tbar:[
						{
							id : 'djsave',
							icon : "/usm/img/save.gif",
							text : '修  改',
							cls : "x-btn-text-icon",
							handler : function(){
								var fn = Ext.get('djiframe').dom.contentWindow.document.getElementById("updateDj");
								fn.submit({
									success:function(){
										Ext.Msg.alert('信息','登记信息添加成功!');
									},
									failure:function(){
										Ext.Msg.alert('信息','操作失败!');
									}
								});
								lwupdatewin.close();
							}
						}
					]
			});
			lwupdatewin.show();
}

function getDelegate(){
		var html;
		
		html = "<div><ul style='text-align:center;'>";
		<ww:iterator value="delegateList">
			html +="<li  style='list-style:none; float:left; width:30%;padding: 3px;'>";
			html +="<img src='<%=contextpath%>desktop/widgets/dockmenu/images/jsxx_50.png' height='40' width='40'><br/>";
			html +='<a href=<ww:property value="url"/> target=_blank><ww:property value="person.name"/></a><br/>';
			html +='<br/>';
			html +="</li>";
		</ww:iterator>
		html+="</ul></div>";
		return html;
}

	var barMove=false;
jQuery("#Bar").addDockEffect(confObject);
	
	Ext.dd.DDBar=Ext.extend(Ext.dd.DD,  {	
			endDrag:function (e){
			 	barMove=true;
			 	setTimeout("barMove=false",100);         
			}
			
		})	
	
 var bardd=	new Ext.dd.DDBar(Ext.id(Ext.get('Clockshow')), 'DesktopShortcuts');
</script>
<script language="JavaScript" type="text/javascript">
	//function openshudao(actid){
		//openUrlWin("/desktop/liucheng/shudao/shudao.jsp?ActivityInstId="+actid,"流程疏导");
	//}
		function openshudao(actid){
		//openUrlWin("/desktop/liucheng/shudao/shudao.jsp?ActivityInstId="+actid,"流程疏导");
		var win = new Ext.Window(
			{
				id :'shudaoshudao',
				title :'流程疏导',
				width :930,
				height:Ext.getBody().getHeight()-30,
				y:0,
	//			autoHeight: true,
//				bodyStyle:'width:100%',   
				loadMask:true,
				closeAction :'close',
				closable :true,
				collapsible :false,
			    autoScroll :false,
				maximizable:true,
				minimizable:true ,

			    html:'<iframe id="my_Form" name="my_Form" style="width:100%;height:100%" src="/desktop/liucheng/shudao/shudao.jsp?ActivityInstId='+actid+'"></iframe>'
			
			});

	
				
  		
       win.show();
		
	}
	function writebh(bh){
	
		var objs = Ext.query("[name='$Fdtgwg.fdtOaGwgDAO.bh']");
		Ext.get(objs[0]).dom.value=bh;
	}
	
	function shudaoclose(){
	 
	   Ext.getCmp("shudaoshudao").close();
	}
	
	function bhwindowclose(){
		Ext.getCmp('bhwindows').close();
	}
	


	
	
	function printwordjs(){
		var handobjs = Ext.query("[name='wordhand']");
	  	
	  	for(var handindex = 0 ; handindex < handobjs.length ; handindex++){

//	  		alert(handobjs.length);
		  	
	  		var bastpath = '<%=basePath%>';
	  		var WordApplication = new ActiveXObject("Word.Application");
			WordApplication.Application.Visible = true;
	  		var strhand = Ext.get(handobjs[handindex]).dom.value;
		 	var strinput = "";
		 	var wordURL = bastpath + "/word/"+strhand+".doc";
			var wordopen = WordApplication.Documents.open(wordURL);
			var allBookMarks = wordopen.BookMarks;
			var range = WordApplication.Range;
			for(i = 1 ; i <= allBookMarks.Count ; i++){
			     var strhtml = allBookMarks(i).Name;
			     //alert(strhtml);
			 	 var objs = Ext.query("[name='"+strhand+strhtml+"']"); 
				if(strhtml == "ldps"||strhtml == "bz"||strhtml == "cbdwldyj"||strhtml == "qfr"||strhtml == "ldsyyj"
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
					     strinput = strinput +" "+"\r\n";
					   //}
					   strinput = strinput.replace(/\&nbsp;/g, ""); 
					   //strinput = strinput.replaceAll("<[^>]*]","");
					}
			    }else if(strhtml=="dj"||strhtml=="mmcd"||strhtml=="jjcd"||strhtml=="mj"||strhtml=="mmdj"|| strhtml=="swbmldyj"|| strhtml=="bmbfzryj"|| strhtml=="miji"||strhtml=="qsjjjcd"||strhtml=="qsjmj"||strhtml=="yjjjcd"||strhtml=="yjmj"){
			    //var cc1 = $(".formc select[@name='"+strhand+strhtml+"'] option[@selected]").text(); 
			   // alert(cc1)
			           //alert( "下拉框"+Ext.get(objs[0]).dom.innerText);
			            if(Ext.get(objs[0])!=null){
					    strinput = Ext.get(objs[0]).dom.value;}
						if(strinput == "yjjjcdpt")strinput="普通";
						if(strinput == "pt")strinput="普通";
						
                        if(strinput == "yjmjpt")strinput="普通";
                        						if(strinput == "yjmjjm")strinput="机密";
                        if(strinput == "yjjjcdjj")strinput="急";
                        						if(strinput == "yjjmjuem")strinput="绝密";
                        if(strinput == "yjjjcdtj")strinput="特急";
                        if(strinput =="yb")strinput="一般";
                        if(strinput =="mm")strinput="秘密";
                        if(strinput =="jm")strinput="机密";
                        if(strinput =="juem")strinput="机密";
                        if(strinput =="juemi")strinput="绝密";
                        if(strinput =="yb")strinput="一般";
                        if(strinput =="pj")strinput="平急";
                        if(strinput =="jj")strinput="紧急";
                        if(strinput =="tj")strinput="特急";
                        if(strinput =="tt")strinput="特提";
                        
			    }else if(strhtml=="fwzh"){
			    	//iframeupss
			    	if(Ext.get('iframeupss')!=null){
			    	
			    	//strinput = Ext.get('iframeupss').dom.contentWindow.document.getElementById("wenzhong").value;
			    	var ye = Ext.get('iframeupss').dom.contentWindow.document.getElementById("year").value;
			    	var zihao = Ext.get('iframeupss').dom.contentWindow.document.getElementById("zihao").value;
			    	
			    	//alert(strinput+" "+ye+" "+zihao);
			    	
			    	/* var objs = Ext.query("[name='"+strhand+strhtml+"']"); 
			    	 if(Ext.get(objs[0])!=null){
					    strinput = Ext.get(objs[0]).dom.value;}*/
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
			    	if(strinput=="neiwangfa")strinput="内网发";
					*/
					var wzobj = Ext.get('iframeupss').dom.contentWindow.document.getElementById("wenzhong");
			    	for(i=0;i<wzobj.length;i++){
   						if(wzobj[i].selected==true){
    						strinput = wzobj[i].innerText;      //关键是通过option对象的innerText属性获取到选项文本『注』要兼容Firefox需使用innerHTML属性
   						}
					}
			    	
			    	strinput = strinput+ye+zihao;
					//strinput =strinput+ye+zihao;
					}
			    }else{
			           if(Ext.get(objs[0])!=null)
			           //alert(Ext.get(objs[0]).dom.innerText);
			           strinput = Ext.get(objs[0]).dom.value;
			    }
			    
			    range = WordApplication.ActiveDocument.Bookmarks(strhtml).Range;
		  		range.InsertBefore(strinput);
			    strinput = "";
			}
	  	}
	}
	function getSg(frameId){
      return document.frames[frameId]["sGraph"];
    }
	function lcrzshow(actid){
	
	var url=context+'routelogView.action?activityInstId='+actid+'';
	    var p = createIframePanel({
				url:url,
	      title: ''
			});
			var routeLogGridCfg={
					  selfCfg:{
					    title:"查看流程日志",
					    width:600,
					    id:"routeLogGridCfg"
					  },
					  metaData:{
					    dataType:"json",
					    hasRowNum:true,
					    paging:{
					      totalProperty:"totalCount",
					      root:"datas",
					      pageSize:10
					    },   
					    
					   cols:[
					       {text:"index",name:'index',isDisplay:'false'},
					       {text:"processInstId",name: 'processInstId',isDisplay:'false'},
					       {text:"办理人 / 所属部门",name: 'performPerson',width:150},
					       {text:"办理步骤",name: 'activityInstName',sortable:true,width:100},    
					      // {text:"当前状态",name: 'state',width:100},
					       {text:"开始时间",name: 'startName',width:130},
					       {text:"结束时间",name: 'endTime',width:180}
					       //{text:"查看", name: 'action'}    
					    ]
					  },
					  dataUrl:"routelog.action?activityInstId="+actid
					};
	    var cfg={
	      title:"历程列表",
	      maximizable:true,
	      width:Ext.getBody().getWidth(),
	      y:0,
	      height:Ext.getBody().getHeight()-30,
	      items:createGridByData(routeLogGridCfg),
	       
	      listeners: {
	        resize: function(obj,w,h) {
	          p.setSize(obj.getInnerWidth()-2,obj.getInnerHeight()-2);
	        }
	      }
	    };
	    var window =createBaseWin(cfg);
	    window.show();
			
	}

</script>