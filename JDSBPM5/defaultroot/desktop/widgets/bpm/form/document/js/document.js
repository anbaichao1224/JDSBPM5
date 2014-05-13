var TANGER_OCX_bDocOpen = false;
var TANGER_OCX_filename;
var TANGER_OCX_actionURL; //For auto generate form fiields
var TANGER_OCX_OBJ; //The Control

function TANGER_OCX_SetDocUser(cuser)
{
	with(TANGER_OCX_OBJ.ActiveDocument.Application)
	{
	//	UserName = cuser;
	}	
}
//输出按钮
function CreateButton()
{
	//if(activityInstHistoryId != null && activityInstHistoryId != "")
	//{
	document.write('<TABLE BORDER=0 width = 500 bordercolor="#999999" >');	
	document.write('<tr >');
	document.write('<td align="center"><INPUT type=BUTTON class="op1" VALUE="保存文档" onclick="TANGER_OCX_SaveEditToServer();"></td>');
	document.write('<td align="center"><INPUT type=BUTTON VALUE="显示痕迹" onclick="TANGER_OCX_ShowRevisions(true);"></td>');
	document.write('<td align="center"><INPUT type=BUTTON VALUE="隐藏痕迹" onclick="TANGER_OCX_ShowRevisions(false);"></td>');
	document.write('<td align="center"><INPUT type=BUTTON VALUE="手写批注" onclick="DoHandDraw();"></td>');
	document.write('<%--<td align="center"><INPUT type=BUTTON VALUE="手写签名" onclick="DoHandSign();"></td>--%>');
	document.write('<td align="center"><INPUT type=BUTTON VALUE="打印文件" onclick="TANGER_OCX_PrintDoc();"></td>');
	document.write('<td align="center"><INPUT type=BUTTON VALUE="关闭文件" onclick="self.close()"></td>');
	document.write('<%--<td >附件：<input type="file"  size=20 name="attach1"></td>DoHandSign()--%>');
	document.write('</tr>');
	document.write('</TABLE>');
	//}
}
//输出控件
function TANGER_OCX_Load()
{
	document.write('<object id="TANGER_OCX" classid="clsid:{C9BC4DFF-4248-4a3c-8A49-63A7D317F404}" ');
	var codebase = "codebase="+context+"desktop/widgets/bpm/form/ocx/OfficeControl.cab#version=4,0,1,8";
	document.write(codebase+' width="100%" height="100%">'); 
	document.write('<param name="BorderStyle" value="0">'); 
	document.write('<param name="BorderColor" value="14402205"> '); 
	document.write('<param name="TitlebarColor" value="53668"> '); 
	document.write('<param name="TitlebarTextColor" value="0"> '); 
	document.write('<param name="MenubarColor" value="13160660">');
	document.write('<param name="Caption" value="欢迎使用">');
	document.write('<param name="Titlebar" value="0"> ');
	document.write('<param name="ProductCaption" value="天诚永信"> ');
	document.write('<param name="ProductKey" value="A49595A1614F98BC06248336F05917329E40F8CB"> ');
	document.write('<SPAN STYLE="color:red">不能打开文件。请将此站点添加到可信任站点,并且启用ActiveX控件。</SPAN>  '); 
	document.write('</object>  '); 
	
}
function TANGER_OCX_init()
{
	TANGER_OCX_OBJ.Titlebar = false;
	TANGER_OCX_OBJ.Menubar=false;
}
function winClose()
	{
		if(!TANGER_OCX_OBJ.ActiveDocument.Saved)
			{
					if(confirm("是否保存当前文档?"))
					{
						TANGER_OCX_SaveEditToServer();
					}
			}
	}
//以下为V1.7新增函数示例

//从本地增加图片到文档指定位置
function AddPictureFromLocal()
{
	if(TANGER_OCX_bDocOpen)
	{	
    TANGER_OCX_OBJ.AddPicFromLocal(
	"", //路径
	true,//是否提示选择文件
	true,//是否浮动图片
	100,//如果是浮动图片，相对于左边的Left 单位磅
	100); //如果是浮动图片，相对于当前段落Top
	};	
}

//从URL增加图片到文档指定位置
function AddPictureFromURL(URL)
{
	if(TANGER_OCX_bDocOpen)
	{
    TANGER_OCX_OBJ.AddPicFromURL(
	URL,//URL 注意；URL必须返回Word支持的图片类型。
	true,//是否浮动图片
	150,//如果是浮动图片，相对于左边的Left 单位磅
	150);//如果是浮动图片，相对于当前段落Top
	};
}

//从本地增加印章文档指定位置
function AddSignFromLocal()
{

   if(TANGER_OCX_bDocOpen)
   {
      TANGER_OCX_OBJ.AddSignFromLocal(
	"匿名用户",//当前登陆用户
	"",//缺省文件
	true,//提示选择
	0,//left
	0)  //top
   }
}

//从URL增加印章文档指定位置
function AddSignFromURL(URL)
{
   if(TANGER_OCX_bDocOpen)
   {
      TANGER_OCX_OBJ.AddSignFromURL(
	"匿名用户",//当前登陆用户
	URL,//URL
	50,//left
	50)  //top
   }
}


//开始手写签名
function DoHandSign()
{
//   alert(TANGER_OCX_key);
    if(TANGER_OCX_bDocOpen)
    {	
        TANGER_OCX_OBJ.SetReadOnly(false);
        var TANGER_OCX_Username = TANGER_OCX_OBJ.ActiveDocument.Application.UserName;
        var TANGER_OCX_key = 
    	TANGER_OCX_OBJ.DoHandSign2(TANGER_OCX_Username); 
    }  
}
//开始手工绘图，可用于手工批示
function DoHandDraw()
{
	if(TANGER_OCX_bDocOpen)
	{	
	    TANGER_OCX_OBJ.SetReadOnly(false);
    	TANGER_OCX_OBJ.DoHandDraw2();
	}
}
//开始手写签名
function DoHandSign1()
{
   if(TANGER_OCX_bDocOpen)
   {	
	TANGER_OCX_OBJ.DoHandSign(
	"匿名用户",//当前登陆用户 必须
	0,//笔型0－实线 0－4 //可选参数
	0x000000ff, //颜色 0x00RRGGBB//可选参数
	2,//笔宽//可选参数
	100,//left//可选参数
	50); //top//可选参数
	}
}
//开始手工绘图，可用于手工批示
function DoHandDraw1()
{
	if(TANGER_OCX_bDocOpen)
	{	
	TANGER_OCX_OBJ.DoHandDraw(
	0,//笔型0－实线 0－4 //可选参数
	0x00ff0000,//颜色 0x00RRGGBB//可选参数
	3,//笔宽//可选参数
	200,//left//可选参数
	50);//top//可选参数
	}
}
//检查签名结果
function DoCheckSign()
{
	if(TANGER_OCX_bDocOpen)
	{		
	var ret = TANGER_OCX_OBJ.DoCheckSign
	(
	/*可选参数 IsSilent 缺省为FAlSE，表示弹出验证对话框,否则，只是返回验证结果到返回值*/
	);//返回值，验证结果字符串
	//alert(ret);
	}	
}
//此函数用来加入一个自定义的文件头部
function TANGER_OCX_AddDocHeader( strHeader )
{
	var i,cNum = 30;
	var lineStr = "";
	try
	{
		for(i=0;i<cNum;i++) lineStr += "_";  //生成下划线
		with(TANGER_OCX_OBJ.ActiveDocument.Application)
		{
			Selection.HomeKey(6,0); // go home
			Selection.TypeText(strHeader);
			Selection.TypeParagraph(); 	//换行
			Selection.TypeText(lineStr);  //插入下划线
			// Selection.InsertSymbol(95,"",true); //插入下划线
			Selection.TypeText("★★");
			Selection.TypeText(lineStr);  //插入下划线
			Selection.TypeParagraph();
			//Selection.MoveUp(5, 2, 1); //上移两行，且按住Shift键，相当于选择两行
			Selection.HomeKey(6,1);  //选择到文件头部所有文本
			Selection.ParagraphFormat.Alignment = 1; //居中对齐
			with(Selection.Font)
			{
				NameFarEast = "宋体";
				Name = "宋体";
				Size = 12;
				Bold = false;
				Italic = false;
				Underline = 0;
				UnderlineColor = 0;
				StrikeThrough = false;
				DoubleStrikeThrough = false;
				Outline = false;
				Emboss = false;
				Shadow = false;
				Hidden = false;
				SmallCaps = false;
				AllCaps = false;
				Color = 255;
				Engrave = false;
				Superscript = false;
				Subscript = false;
				Spacing = 0;
				Scaling = 100;
				Position = 0;
				Kerning = 0;
				Animation = 0;
				DisableCharacterSpaceGrid = false;
				EmphasisMark = 0;
			}
			Selection.MoveDown(5, 3, 0); //下移3行
		}
	}
	catch(err){
		//alert("错误：" + err.number + ":" + err.description);
	}
	finally{
	}
}
function strtrim(value)
{
	return value.replace(/^\s+/,'').replace(/\s+$/,'');
}

function TANGER_OCX_doFormOnSubmit()
{
	var form = document.forms[0];
  	if (form.onsubmit)
	{
    	var retVal = form.onsubmit();
     	if (typeof retVal == "boolean" && retVal == false)
       	return false;
	}
	return true;
}

//允许或禁止显示修订工具栏和工具菜单（保护修订）
function TANGER_OCX_EnableReviewBar(boolvalue)
{
	TANGER_OCX_OBJ.ActiveDocument.CommandBars("Reviewing").Enabled = boolvalue;
	TANGER_OCX_OBJ.ActiveDocument.CommandBars("Track Changes").Enabled = boolvalue;
	//TANGER_OCX_OBJ.IsShowToolMenu = boolvalue;	//关闭或打开工具菜单
}

//打开或者关闭修订模式
function TANGER_OCX_SetReviewMode(boolvalue)
{
	TANGER_OCX_OBJ.ActiveDocument.TrackRevisions = boolvalue;
}

//进入或退出痕迹保留状态，调用上面的两个函数
function TANGER_OCX_SetMarkModify(boolvalue)
{
	TANGER_OCX_SetReviewMode(boolvalue);
//	TANGER_OCX_EnableReviewBar(!boolvalue);
}

//显示/不显示修订文字
function TANGER_OCX_ShowRevisions(boolvalue)
{
	TANGER_OCX_OBJ.ActiveDocument.ShowRevisions = boolvalue;
}

//打印/不打印修订文字
function TANGER_OCX_PrintRevisions(boolvalue)
{
	TANGER_OCX_OBJ.ActiveDocument.PrintRevisions = boolvalue;
}

function TANGER_OCX_SaveToServer()
{
	if(!TANGER_OCX_bDocOpen)
	{
		alert("没有打开的文档。");
		return;
	}
	
	TANGER_OCX_filename = prompt("附件另存为：","新文档.doc");
	if ( (!TANGER_OCX_filename))
	{
		TANGER_OCX_filename ="";
		return;
	}
	else if (strtrim(TANGER_OCX_filename)=="")
	{
		alert("您必须输入文件名！");
		return;
	}
	//alert(TANGER_OCX_filename);
	TANGER_OCX_SaveDoc();
}


//设置页面布局
function TANGER_OCX_ChgLayout()
{
 	try
	{
		TANGER_OCX_OBJ.showdialog(5); //设置页面布局
	}
	catch(err){
		alert("错误：" + err.number + ":" + err.description);
	}
	finally{
	}
}

//打印文档
function TANGER_OCX_PrintDoc()
{
	try
	{
		TANGER_OCX_OBJ.printout(true);
	}
	catch(err){
		alert("错误：" + err.number + ":" + err.description);
	}
	finally{
	}
}

function TANGER_OCX_SaveEditToServer()
{

	if(!TANGER_OCX_bDocOpen)
	{
		alert("没有打开的文档。");
		return;
	}
	
	TANGER_OCX_filename = filename;
	if ( (!TANGER_OCX_filename))
	{
		TANGER_OCX_filename ="";
		return;
	}
	else if (strtrim(TANGER_OCX_filename)=="")
	{
		alert("您必须输入文件名！");
		return;
	}
	//alert(TANGER_OCX_filename);
	TANGER_OCX_SaveDoc();
}

//允许或禁止文件－>新建菜单
function TANGER_OCX_EnableFileNewMenu(boolvalue)
{
	TANGER_OCX_OBJ.EnableFileCommand(0) = boolvalue;
}
//允许或禁止文件－>打开菜单
function TANGER_OCX_EnableFileOpenMenu(boolvalue)
{
	TANGER_OCX_OBJ.EnableFileCommand(1) = boolvalue;
}
//允许或禁止文件－>关闭菜单
function TANGER_OCX_EnableFileCloseMenu(boolvalue)
{
	TANGER_OCX_OBJ.EnableFileCommand(2) = boolvalue;
}
//允许或禁止文件－>保存菜单
function TANGER_OCX_EnableFileSaveMenu(boolvalue)
{
	TANGER_OCX_OBJ.EnableFileCommand(3) = boolvalue;
}
//允许或禁止文件－>另存为菜单
function TANGER_OCX_EnableFileSaveAsMenu(boolvalue)
{
	TANGER_OCX_OBJ.EnableFileCommand(4) = boolvalue;
}
//允许或禁止文件－>打印菜单
function TANGER_OCX_EnableFilePrintMenu(boolvalue)
{
	TANGER_OCX_OBJ.EnableFileCommand(5) = boolvalue;
}
//允许或禁止文件－>打印预览菜单
function TANGER_OCX_EnableFilePrintPreviewMenu(boolvalue)
{
	TANGER_OCX_OBJ.EnableFileCommand(6) = boolvalue;
}

function TANGER_OCX_OpenDoc()
{
	
	TANGER_OCX_OBJ = document.all.item("TANGER_OCX");
	try{
			if(saveAs)//如果有正文
			{
					TANGER_OCX_OBJ.BeginOpenFromURL(contextfile+"servlet/FileDisplay?type=doc&uuid=" + uuid);
			}else//如果没有,新建一个
			{
				TANGER_OCX_OBJ.CreateNew("Word.Document");
			}
		
	}catch(e)
	{
		//alert(e);
		alert("打开正文时出错!");
	}
	TANGER_OCX_init();
}


//createXMLHttpRequest
function createXMLHttpRequest()
{
    var xmlHttp = null;
    if (window.ActiveXObject)
    {
        xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
    }

    else if (window.XMLHttpRequest)
    {
        xmlHttp = new XMLHttpRequest();
    }
    return xmlHttp
}
function TANGER_OCX_OnDocumentOpened(str, obj)
{
	TANGER_OCX_bDocOpen = true;	
	TANGER_OCX_SetMarkModify(true);	
	TANGER_OCX_ShowRevisions(false);
	TANGER_OCX_OBJ = document.all.item("TANGER_OCX");
	TANGER_OCX_OBJ.ActiveDocument.Application.UserName=username;//给痕迹保留提供用户名
}

function TANGER_OCX_OnDocumentClosed()
{
	//TANGER_OCX_OBJ.ActiveDocument.Application.UserName="";
   TANGER_OCX_bDocOpen = false;
}

function TANGER_OCX_AcceptAllRevisions()
{
//	alert();
	TANGER_OCX_OBJ.ActiveDocument.AcceptAllRevisions();
}


function TANGER_OCX_SaveDoc()
{
	var newwin,newdoc;

	if(!TANGER_OCX_bDocOpen)
	{
		alert("没有打开的文档。");
		return;
	}

	try
	{//保存实例的正文
	 	if(!TANGER_OCX_doFormOnSubmit())return; //
	 	var url;
	 	if(saveAs) //有正文
	 	{
			url = contextfile+"saveAsDocumentAction.action?uuid="+uuid+"&activityInstId="+activityInstId+"&personId="+personid;
		}else
		{
			url = contextfile+"saveDocumentAction.action?activityInstId="+activityInstId+"&personId="+personid;
		}
		var retHTML = TANGER_OCX_OBJ.SaveToURL(url,"files","",filename,"myForm"); //
		 
	/*	newwin = window.open("","_blank","left=200,top=200,width=400,height=300,status=0,toolbar=0,menubar=0,location=0,scrollbars=1,resizable=1",false);
		newdoc = newwin.document;
		newdoc.open();
		newdoc.write("<html><head><title>return source</title></head><body><center><hr>")
		newdoc.write(retHTML+"<hr>");
		newdoc.write("<input type=button VALUE='close window' onclick='window.close()'>");
		newdoc.write('</center></body></html>');
		newdoc.close();
		if(window.opener) //
		{
			window.opener.location.reload();
		}
		*/
		//window.close();
		if(retHTML!=null && retHTML.length>15) 
		{
			window.parent.Ext.get("docimg").dom.parentNode.href="javascript:openFormWin('"+context+"documentAction.action?activityInstId="+activityInstId+"&docid="+retHTML+"','','')";
			window.parent.Ext.get("docimg").dom.src=""+context+"desktop/widgets/bpm/form/document/img/word.bmp";
			alert("保存成功！");
		}else{
			alert(retHTML);
		}
	}
	catch(err){
		alert("不能保存到URL：" + err.number + ":" + err.description);
	}
	finally{
	}
}
function TANGER_OCX_SaveAsHTML()
{
	var newwin,newdoc;

	if(!TANGER_OCX_bDocOpen)
	{
		alert("没有打开的文档。");
		return;
	}
	try
	{
		//调用控件的PublishAsHTMLToURL函数
		var retHTML = TANGER_OCX_OBJ.PublishAsHTMLToURL
			(
				"uploadhtmls.jsp",
				"HTMLFILES", //文件输入域名称,可任选,所有相关文件都以此域上传
				"",
				document.forms[0].htmlfile.value
				//此处省略了第5个参数，HTML FORM得索引或者id.这样,不会提交表单数据
				//只提交所有得html文件相关得文件
			);
		newwin = window.open("","_blank","left=200,top=200,width=400,height=300,status=0,toolbar=0,menubar=0,location=0,scrollbars=1,resizable=1",false);
		newdoc = newwin.document;
		newdoc.open();
		newdoc.write("<center><hr>"+retHTML+"<hr><input type=button VALUE='关闭窗口' onclick='window.close()'></center>");
		newdoc.close();	
		newwin.focus();
		if(window.opener) //如果父窗口存在，刷新并关闭当前窗口
		{
			window.opener.location.reload();
		}
	}
	catch(err){
		alert("不能保存到URL：" + err.number + ":" + err.description);
	}
	finally{
	}
}

function getDate()
{
  var d,s,t;
  d=new Date();
  s=d.getFullYear().toString(10)+"-";
  t=d.getMonth()+1;
  s+=(t>9?"":"0")+t+"-";
  t=d.getDate();
  s+=(t>9?"":"0")+t+"_";
  t=d.getHours();
  s+=(t>9?"":"0")+t+"_";
  t=d.getMinutes();
  s+=(t>9?"":"0")+t+"_";
  t=d.getSeconds();
  s+=(t>9?"":"0")+t;
  return s;
}
	
