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
//�����ť
function CreateButton()
{
	//if(activityInstHistoryId != null && activityInstHistoryId != "")
	//{
	document.write('<TABLE BORDER=0 width = 500 bordercolor="#999999" >');	
	document.write('<tr >');
	document.write('<td align="center"><INPUT type=BUTTON class="op1" VALUE="�����ĵ�" onclick="TANGER_OCX_SaveEditToServer();"></td>');
	document.write('<td align="center"><INPUT type=BUTTON VALUE="��ʾ�ۼ�" onclick="TANGER_OCX_ShowRevisions(true);"></td>');
	document.write('<td align="center"><INPUT type=BUTTON VALUE="���غۼ�" onclick="TANGER_OCX_ShowRevisions(false);"></td>');
	document.write('<td align="center"><INPUT type=BUTTON VALUE="��д��ע" onclick="DoHandDraw();"></td>');
	document.write('<%--<td align="center"><INPUT type=BUTTON VALUE="��дǩ��" onclick="DoHandSign();"></td>--%>');
	document.write('<td align="center"><INPUT type=BUTTON VALUE="��ӡ�ļ�" onclick="TANGER_OCX_PrintDoc();"></td>');
	document.write('<td align="center"><INPUT type=BUTTON VALUE="�ر��ļ�" onclick="self.close()"></td>');
	document.write('<%--<td >������<input type="file"  size=20 name="attach1"></td>DoHandSign()--%>');
	document.write('</tr>');
	document.write('</TABLE>');
	//}
}
//����ؼ�
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
	document.write('<param name="Caption" value="��ӭʹ��">');
	document.write('<param name="Titlebar" value="0"> ');
	document.write('<param name="ProductCaption" value="�������"> ');
	document.write('<param name="ProductKey" value="A49595A1614F98BC06248336F05917329E40F8CB"> ');
	document.write('<SPAN STYLE="color:red">���ܴ��ļ����뽫��վ����ӵ�������վ��,��������ActiveX�ؼ���</SPAN>  '); 
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
					if(confirm("�Ƿ񱣴浱ǰ�ĵ�?"))
					{
						TANGER_OCX_SaveEditToServer();
					}
			}
	}
//����ΪV1.7��������ʾ��

//�ӱ�������ͼƬ���ĵ�ָ��λ��
function AddPictureFromLocal()
{
	if(TANGER_OCX_bDocOpen)
	{	
    TANGER_OCX_OBJ.AddPicFromLocal(
	"", //·��
	true,//�Ƿ���ʾѡ���ļ�
	true,//�Ƿ񸡶�ͼƬ
	100,//����Ǹ���ͼƬ���������ߵ�Left ��λ��
	100); //����Ǹ���ͼƬ������ڵ�ǰ����Top
	};	
}

//��URL����ͼƬ���ĵ�ָ��λ��
function AddPictureFromURL(URL)
{
	if(TANGER_OCX_bDocOpen)
	{
    TANGER_OCX_OBJ.AddPicFromURL(
	URL,//URL ע�⣻URL���뷵��Word֧�ֵ�ͼƬ���͡�
	true,//�Ƿ񸡶�ͼƬ
	150,//����Ǹ���ͼƬ���������ߵ�Left ��λ��
	150);//����Ǹ���ͼƬ������ڵ�ǰ����Top
	};
}

//�ӱ�������ӡ���ĵ�ָ��λ��
function AddSignFromLocal()
{

   if(TANGER_OCX_bDocOpen)
   {
      TANGER_OCX_OBJ.AddSignFromLocal(
	"�����û�",//��ǰ��½�û�
	"",//ȱʡ�ļ�
	true,//��ʾѡ��
	0,//left
	0)  //top
   }
}

//��URL����ӡ���ĵ�ָ��λ��
function AddSignFromURL(URL)
{
   if(TANGER_OCX_bDocOpen)
   {
      TANGER_OCX_OBJ.AddSignFromURL(
	"�����û�",//��ǰ��½�û�
	URL,//URL
	50,//left
	50)  //top
   }
}


//��ʼ��дǩ��
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
//��ʼ�ֹ���ͼ���������ֹ���ʾ
function DoHandDraw()
{
	if(TANGER_OCX_bDocOpen)
	{	
	    TANGER_OCX_OBJ.SetReadOnly(false);
    	TANGER_OCX_OBJ.DoHandDraw2();
	}
}
//��ʼ��дǩ��
function DoHandSign1()
{
   if(TANGER_OCX_bDocOpen)
   {	
	TANGER_OCX_OBJ.DoHandSign(
	"�����û�",//��ǰ��½�û� ����
	0,//����0��ʵ�� 0��4 //��ѡ����
	0x000000ff, //��ɫ 0x00RRGGBB//��ѡ����
	2,//�ʿ�//��ѡ����
	100,//left//��ѡ����
	50); //top//��ѡ����
	}
}
//��ʼ�ֹ���ͼ���������ֹ���ʾ
function DoHandDraw1()
{
	if(TANGER_OCX_bDocOpen)
	{	
	TANGER_OCX_OBJ.DoHandDraw(
	0,//����0��ʵ�� 0��4 //��ѡ����
	0x00ff0000,//��ɫ 0x00RRGGBB//��ѡ����
	3,//�ʿ�//��ѡ����
	200,//left//��ѡ����
	50);//top//��ѡ����
	}
}
//���ǩ�����
function DoCheckSign()
{
	if(TANGER_OCX_bDocOpen)
	{		
	var ret = TANGER_OCX_OBJ.DoCheckSign
	(
	/*��ѡ���� IsSilent ȱʡΪFAlSE����ʾ������֤�Ի���,����ֻ�Ƿ�����֤���������ֵ*/
	);//����ֵ����֤����ַ���
	//alert(ret);
	}	
}
//�˺�����������һ���Զ�����ļ�ͷ��
function TANGER_OCX_AddDocHeader( strHeader )
{
	var i,cNum = 30;
	var lineStr = "";
	try
	{
		for(i=0;i<cNum;i++) lineStr += "_";  //�����»���
		with(TANGER_OCX_OBJ.ActiveDocument.Application)
		{
			Selection.HomeKey(6,0); // go home
			Selection.TypeText(strHeader);
			Selection.TypeParagraph(); 	//����
			Selection.TypeText(lineStr);  //�����»���
			// Selection.InsertSymbol(95,"",true); //�����»���
			Selection.TypeText("���");
			Selection.TypeText(lineStr);  //�����»���
			Selection.TypeParagraph();
			//Selection.MoveUp(5, 2, 1); //�������У��Ұ�סShift�����൱��ѡ������
			Selection.HomeKey(6,1);  //ѡ���ļ�ͷ�������ı�
			Selection.ParagraphFormat.Alignment = 1; //���ж���
			with(Selection.Font)
			{
				NameFarEast = "����";
				Name = "����";
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
			Selection.MoveDown(5, 3, 0); //����3��
		}
	}
	catch(err){
		//alert("����" + err.number + ":" + err.description);
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

//������ֹ��ʾ�޶��������͹��߲˵��������޶���
function TANGER_OCX_EnableReviewBar(boolvalue)
{
	TANGER_OCX_OBJ.ActiveDocument.CommandBars("Reviewing").Enabled = boolvalue;
	TANGER_OCX_OBJ.ActiveDocument.CommandBars("Track Changes").Enabled = boolvalue;
	//TANGER_OCX_OBJ.IsShowToolMenu = boolvalue;	//�رջ�򿪹��߲˵�
}

//�򿪻��߹ر��޶�ģʽ
function TANGER_OCX_SetReviewMode(boolvalue)
{
	TANGER_OCX_OBJ.ActiveDocument.TrackRevisions = boolvalue;
}

//������˳��ۼ�����״̬�������������������
function TANGER_OCX_SetMarkModify(boolvalue)
{
	TANGER_OCX_SetReviewMode(boolvalue);
//	TANGER_OCX_EnableReviewBar(!boolvalue);
}

//��ʾ/����ʾ�޶�����
function TANGER_OCX_ShowRevisions(boolvalue)
{
	TANGER_OCX_OBJ.ActiveDocument.ShowRevisions = boolvalue;
}

//��ӡ/����ӡ�޶�����
function TANGER_OCX_PrintRevisions(boolvalue)
{
	TANGER_OCX_OBJ.ActiveDocument.PrintRevisions = boolvalue;
}

function TANGER_OCX_SaveToServer()
{
	if(!TANGER_OCX_bDocOpen)
	{
		alert("û�д򿪵��ĵ���");
		return;
	}
	
	TANGER_OCX_filename = prompt("�������Ϊ��","���ĵ�.doc");
	if ( (!TANGER_OCX_filename))
	{
		TANGER_OCX_filename ="";
		return;
	}
	else if (strtrim(TANGER_OCX_filename)=="")
	{
		alert("�����������ļ�����");
		return;
	}
	//alert(TANGER_OCX_filename);
	TANGER_OCX_SaveDoc();
}


//����ҳ�沼��
function TANGER_OCX_ChgLayout()
{
 	try
	{
		TANGER_OCX_OBJ.showdialog(5); //����ҳ�沼��
	}
	catch(err){
		alert("����" + err.number + ":" + err.description);
	}
	finally{
	}
}

//��ӡ�ĵ�
function TANGER_OCX_PrintDoc()
{
	try
	{
		TANGER_OCX_OBJ.printout(true);
	}
	catch(err){
		alert("����" + err.number + ":" + err.description);
	}
	finally{
	}
}

function TANGER_OCX_SaveEditToServer()
{

	if(!TANGER_OCX_bDocOpen)
	{
		alert("û�д򿪵��ĵ���");
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
		alert("�����������ļ�����");
		return;
	}
	//alert(TANGER_OCX_filename);
	TANGER_OCX_SaveDoc();
}

//������ֹ�ļ���>�½��˵�
function TANGER_OCX_EnableFileNewMenu(boolvalue)
{
	TANGER_OCX_OBJ.EnableFileCommand(0) = boolvalue;
}
//������ֹ�ļ���>�򿪲˵�
function TANGER_OCX_EnableFileOpenMenu(boolvalue)
{
	TANGER_OCX_OBJ.EnableFileCommand(1) = boolvalue;
}
//������ֹ�ļ���>�رղ˵�
function TANGER_OCX_EnableFileCloseMenu(boolvalue)
{
	TANGER_OCX_OBJ.EnableFileCommand(2) = boolvalue;
}
//������ֹ�ļ���>����˵�
function TANGER_OCX_EnableFileSaveMenu(boolvalue)
{
	TANGER_OCX_OBJ.EnableFileCommand(3) = boolvalue;
}
//������ֹ�ļ���>���Ϊ�˵�
function TANGER_OCX_EnableFileSaveAsMenu(boolvalue)
{
	TANGER_OCX_OBJ.EnableFileCommand(4) = boolvalue;
}
//������ֹ�ļ���>��ӡ�˵�
function TANGER_OCX_EnableFilePrintMenu(boolvalue)
{
	TANGER_OCX_OBJ.EnableFileCommand(5) = boolvalue;
}
//������ֹ�ļ���>��ӡԤ���˵�
function TANGER_OCX_EnableFilePrintPreviewMenu(boolvalue)
{
	TANGER_OCX_OBJ.EnableFileCommand(6) = boolvalue;
}

function TANGER_OCX_OpenDoc()
{
	
	TANGER_OCX_OBJ = document.all.item("TANGER_OCX");
	try{
			if(saveAs)//���������
			{
					TANGER_OCX_OBJ.BeginOpenFromURL(contextfile+"servlet/FileDisplay?type=doc&uuid=" + uuid);
			}else//���û��,�½�һ��
			{
				TANGER_OCX_OBJ.CreateNew("Word.Document");
			}
		
	}catch(e)
	{
		//alert(e);
		alert("������ʱ����!");
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
	TANGER_OCX_OBJ.ActiveDocument.Application.UserName=username;//���ۼ������ṩ�û���
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
		alert("û�д򿪵��ĵ���");
		return;
	}

	try
	{//����ʵ��������
	 	if(!TANGER_OCX_doFormOnSubmit())return; //
	 	var url;
	 	if(saveAs) //������
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
			alert("����ɹ���");
		}else{
			alert(retHTML);
		}
	}
	catch(err){
		alert("���ܱ��浽URL��" + err.number + ":" + err.description);
	}
	finally{
	}
}
function TANGER_OCX_SaveAsHTML()
{
	var newwin,newdoc;

	if(!TANGER_OCX_bDocOpen)
	{
		alert("û�д򿪵��ĵ���");
		return;
	}
	try
	{
		//���ÿؼ���PublishAsHTMLToURL����
		var retHTML = TANGER_OCX_OBJ.PublishAsHTMLToURL
			(
				"uploadhtmls.jsp",
				"HTMLFILES", //�ļ�����������,����ѡ,��������ļ����Դ����ϴ�
				"",
				document.forms[0].htmlfile.value
				//�˴�ʡ���˵�5��������HTML FORM����������id.����,�����ύ������
				//ֻ�ύ���е�html�ļ���ص��ļ�
			);
		newwin = window.open("","_blank","left=200,top=200,width=400,height=300,status=0,toolbar=0,menubar=0,location=0,scrollbars=1,resizable=1",false);
		newdoc = newwin.document;
		newdoc.open();
		newdoc.write("<center><hr>"+retHTML+"<hr><input type=button VALUE='�رմ���' onclick='window.close()'></center>");
		newdoc.close();	
		newwin.focus();
		if(window.opener) //��������ڴ��ڣ�ˢ�²��رյ�ǰ����
		{
			window.opener.location.reload();
		}
	}
	catch(err){
		alert("���ܱ��浽URL��" + err.number + ":" + err.description);
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
	
