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
function CreateButton(){
	//if(activityInstHistoryId != null && activityInstHistoryId != "")
	//{
	document.write('<TABLE BORDER=0 width = 500 bordercolor="#999999" >');	
	document.write('<tr >');
	document.write('<td align="center"><INPUT type=BUTTON class="op1" VALUE="编辑保存" onclick="handler();"></td>');
	document.write('<td align="center"><INPUT type=BUTTON VALUE="显示痕迹" onclick="showRevisions();"></td>');
	
	document.write('<td align="center"><INPUT type=BUTTON VALUE="显示审阅窗口" onclick="showPanelRevisions();"></td>');

	document.write('</tr>');
	document.write('</TABLE>');
	//}
}

function handler(){	
  
	     document.all.oframe.save();//保存到本地
            //上传
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
			   	alert("保存失败: 错误["+fileLoad.ErrorStr+"]");
			}else{
				alert("保存成功");
				document.all.oframe.Open("C:\\jdsbpm4\\document.doc");
			}
			
	}

function showPanelRevisions(){
		if(gongwenform.showPanelButton.value == "隐藏审阅窗口"){
			gongwenform.showPanelButton.value = "显示审阅窗口";
			document.all.oframe.OpenPanelRevisions();
		}else{
			gongwenform.showPanelButton.value = "隐藏审阅窗口";
			document.all.oframe.OpenPanelRevisions();
		}
}

function showRevisions(){

		if(gongwenform.showButton.value == "隐藏痕迹"){
			gongwenform.showButton.value = "显示痕迹";
			document.all.oframe.ShowRevisions = false;
		}else{
			gongwenform.showButton.value = "隐藏痕迹";
			document.all.oframe.ShowRevisions = true;
		}

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










	
