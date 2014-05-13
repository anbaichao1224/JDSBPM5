//form表单提交验证
	function checkForm(formname,stime,etime){
		var v = "";
	 for(var   i=0;i<eval("document."+formname+".length");i++)  
	{ 
		 v = eval("document."+formname+".item(i).name"); 
		 
		 if(eval("document."+formname+".item(i).value")==""&&eval("document."+formname+".item(i).type")!="hidden"){
		 	alert("表单信息填写不完整");
		 	return false;
		 }
	}
	 	if(stime!=''&&etime!=''){
			if(stime>etime){
				alert("结束时间不能早于开始时间");
				return false;
			}
	 	}
	 return true;
	}