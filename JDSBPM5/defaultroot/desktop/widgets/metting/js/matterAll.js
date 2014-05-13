
//表单验证
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
		 
		 if(eval("document."+formname+".item(i).value")==""&&eval("document."+formname+".item(i).type")!="hidden"){
		 	alert("表单信息填写不完整");
		 	return false;
		 }
	}
	 	var sxkssj = document.getElementById("hykssj").value;
		var sxjssj = document.getElementById("hyjssj").value;
		if(sxjssj<sxkssj){
			alert("结束时间不能早于开始时间");
			return false;
		}
		var matterdiv = document.getElementById("matterids").value;
		if(matterdiv==""){
			alert("您还没有添加事项，或是您使用的常用事项信息没有填写完整！");
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

function mattersave(mlx){
	var sarray = new Array();
	var sarray = [['sxssjd','选择事项阶段'],['sxlx','事项类型必选'],['sxkssj','开始时间必填'],['sxjssj','结束时间必填'],['sxmc','事项名称必填'],['personname','办理人必选'],['sxnr','事项内容必填']];
	if(document.getElementById("sxlx").value=="1"){
		sarray = [['sxssjd','选择事项阶段'],['sxlx','事项类型必选'],['sxkssj','开始时间必填'],['sxjssj','结束时间必填'],['sxmc','事项名称必填'],['personname','办理人必选'],['sxnr','事项内容必填'],['processdefid','请选择所用流程']];
		
	}
	
	if(form_validate(sarray,'')){
	var sxkssj = document.getElementById("sxkssj").value;
	var sxjssj = document.getElementById("sxjssj").value;
	if(sxjssj<sxkssj){
		alert("结束时间不能早于开始时间");
		return;
	}
	document.getElementById("mstatus").value=mlx;
	var uid = document.getElementById("uuid").value;
	var sxmc = document.getElementById("sxmc").value;
	Ext.Ajax.request({
		url:'savematter.action',
		method:'post',
		form:'addmatter',
		success:function(o){
			var res = o.responseText;
			var showdiv = window.parent.document.getElementById("cymatter");
			if(bsta==""&&id!=""){
				var idd='m'+id;
				//alert(idd);
				window.parent.document.getElementById(idd).innerHTML="<a href=javascript:void(); onclick=\"matterinfodel('"+res+"','matterinfo')\" class=\"formisnull\">[删除]</a>&nbsp;&nbsp;<a href=javascript:void(0) onclick=\"updateMatter('bpmmatter_matterInfoById.action?uuid="+newid+"')\">"+sxmc+"</a><div style='float:right'></div>";
				window.parent.document.getElementById(idd).id=newid;
				var mids = window.parent.document.getElementById("matterids").value;
				//alert("befor:"+mids);
				if(mids.indexOf(res)<0){
					if(mids==""){
						mids=res;
					}else{
						mids+=","+res;
					}
					window.parent.document.getElementById("matterids").value=mids;
					//alert("after:"+mids);
				}
			}else if(id==""){
				showdiv.innerHTML +="<div id='"+res+"'><a href=javascript:void(); onclick=\"matterinfodel('"+res+"','matterinfo')\" class=\"formisnull\">[删除]</a>&nbsp;&nbsp;<a href=javascript:void(0) onclick=\"updateMatter('bpmmatter_matterInfoById.action?uuid="+res+"')\">"+sxmc+"</a></div>";
				var mids = window.parent.document.getElementById("matterids").value;
				if(mids.indexOf(res)<0){
					if(mids==""){
						mids=res;
					}else{
						mids+=","+res;
					}
					window.parent.document.getElementById("matterids").value=mids;
				}
			}else if(uid!=""){
				window.parent.document.getElementById(uid).innerHTML="<a href=javascript:void(); onclick=\"matterinfodel('"+res+"','matterinfo')\" class=\"formisnull\">[删除]</a>&nbsp;&nbsp;<a href=javascript:void(0) onclick=updateMatter('bpmmatter_matterInfoById.action?uuid="+uid+"')>"+sxmc+"</a>";
			}
			
			//alert(showdiv.innerHTML);
			winclose();
		}
	});
	}
}
//更改事项类型
function sxlxchange(){
 	if(document.getElementById("sxlx").value=="1"){
 		document.getElementById("processtr").style.display="block";
 		document.getElementById("sxfj").style.display="none";
 	}else{
 		document.getElementById("processtr").style.display="none";
 		document.getElementById("sxfj").style.display="block";
 	}
 	document.getElementById("personname").value="";
 	document.getElementById("personid").value="";
 	parent.Ext.get('updateMatterInfo').height='600';
 }
//办理人启动流程
function startprocess(){
		var sxid = document.getElementById("sxxxid").value;
		var sxlx = document.getElementById("sxlx").value;
		Ext.Ajax.request({
			url:"saveBlInfo.action",
			params:{sxxxid:sxid,sxlx:sxlx},
			method:"post",
			success:function(){
				window.top.startProcess(sxid,processid);
			}
		});
}

function winclose(){
	var win =parent.Ext.getCmp('updateMatterInfo');
			win.close();
}
