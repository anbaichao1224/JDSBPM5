
/*
 * function detail(){ //location.href='getHistory.action?processInstId=<ww:property
 * value="matterInfoListBean.activityinstid"/>'; window.top.prodetail('<ww:property
 * value="matterInfoListBean.activityinstid"/>'); }
 */
// 表单验证
function fcheck(sarray){
	var msg = "";
	for(var i=0;i<sarray.length;i++){
			var val = document.getElementById(sarray[i][0]).value;
			if(val==null||val==""){
				msg+=sarray[i][1]+"\n";
			}
	}
	if(msg!==""){
		alert(msg);
		return false;
	}
	return true;
}

function fvalidate(formname)
{   
	
	var v = "";
	 for(var   i=0;i<eval("document."+formname+".length");i++)  
	{ 
		 v = eval("document."+formname+".item(i).name"); 
		 
		 if(eval("document."+formname+".item(i).value")==""&&eval("document."+formname+".item(i).type")!="hidden"&&!eval("document."+formname+".item(i).disabled")){
		 	alert("表单信息填写不完整"+eval("document."+formname+".item(i).name")+eval("document."+formname+".item(i).value"));
		 	return false;
		 }
	}
	 	var sxkssj = document.getElementById("kssj").value;
		var sxjssj = document.getElementById("jssj").value;
		if(sxkssj=="请选择"||sxjssj=="请选择"){
			alert("请选择时间");
			return false;
		}
		if(sxjssj<sxkssj){
			alert("结束时间不能早于开始时间");
			return false;
		}
	 return true;
}

function form_validate(sarray,formname){
	if(formname!=""){
		return fvalidate(formname);
	}else{
		return fcheck(sarray);
	}
}
