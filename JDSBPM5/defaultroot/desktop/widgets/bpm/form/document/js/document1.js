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
function CreateButton(){
	//if(activityInstHistoryId != null && activityInstHistoryId != "")
	//{
	document.write('<TABLE BORDER=0 width = 500 bordercolor="#999999" >');	
	document.write('<tr >');
	document.write('<td align="center"><INPUT type=BUTTON class="op1" VALUE="�༭����" onclick="handler();"></td>');
	document.write('<td align="center"><INPUT type=BUTTON VALUE="��ʾ�ۼ�" onclick="showRevisions();"></td>');
	
	document.write('<td align="center"><INPUT type=BUTTON VALUE="��ʾ���Ĵ���" onclick="showPanelRevisions();"></td>');

	document.write('</tr>');
	document.write('</TABLE>');
	//}
}

function handler(){	
  
	     document.all.oframe.save();//���浽����
            //�ϴ�
	     	document.getElementById('oframe').close();
	     	
	     	var fileLoad = document.all.fileLoad;
	     	fileLoad.UpUrlPath = contextfile+"selfsave.action";
	     
	    	fileLoad.AddField('activityInstId',activityInstId);
	    	fileLoad.AddField('personId',personid);
	    	fileLoad.AddField('filefileName',processId+'.doc');
	    	fileLoad.AddField('processInstId',processId);
	    	if(uuid!=null&&uuid.length>1){
	    		
	    	fileLoad.AddField('uuid',uuid);
	    	}
	    	
	     	fileLoad.AddFile('C:\\jdsbpm4\\'+'document.doc','files');

			var result = fileLoad.HttpUpLoad();
		
			if(result != 0){
			   	alert("����ʧ��: ����["+fileLoad.ErrorStr+"]");
			}else{
				alert("����ɹ�");
				document.all.oframe.Open("C:\\jdsbpm4\\document.doc");
			}
			
	}

function showPanelRevisions(){
		if(gongwenform.showPanelButton.value == "�������Ĵ���"){
			gongwenform.showPanelButton.value = "��ʾ���Ĵ���";
			document.all.oframe.OpenPanelRevisions();
		}else{
			gongwenform.showPanelButton.value = "�������Ĵ���";
			document.all.oframe.OpenPanelRevisions();
		}
}

function showRevisions(){

		if(gongwenform.showButton.value == "���غۼ�"){
			gongwenform.showButton.value = "��ʾ�ۼ�";
			document.all.oframe.ShowRevisions = false;
		}else{
			gongwenform.showButton.value = "���غۼ�";
			document.all.oframe.ShowRevisions = true;
		}

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










	
