//ȫ�ֱ�������ƹ��¼�ʹ��
var functionName = "";
var mydiv = "dd";

//���װ��

function loadReportAjax(reset)
{
	var s = window.location.host;
	functionName = "loadReportAjax";
	var str = parReportParameters();
	if(reset != null)
	{
			str = str + "&reset=" + reset;
	}
	else
	{
		str = str + "&reset=false";
	}

	str = encodeURI(str);
	var url = "http://" + s + $F('serverAddr') + "/reportCodeGenAction.html";
  var myAjax = getMyAjax(mydiv, url, str);
}
function loadReportAjaxOnloading()
{
	parent.magicAlert(parent.operate_div.innerHTML, false);
}
function loadReportAjaxOnsuccess()
{
		parent.codeCreate.style.display="none";
		parent.resetCodeCreate.style.display="none";
		parent.reportCreate.style.display="";	
}
function loadReportAjaxOncomplete()
{
		 parent.magicDone();
}
function loadReportAjaxOnfailure()
{
	parent.reportCreate.style.display="none";
	alert('Failure to create the report code!');
}


function loadAjax()
{	
  functionName = "loadAjax";
    var str = parParameters();
	var url=getUrlByActionNameStr('ExpressionVOCurrAction.html');
    var myAjax = getMyAjax(mydiv, url, str);
}

//���������

function updateCell(cell)
{
    functionName = "updateCell";
    if (cell != null && ("'"+cell.val)!=("'"+cell.getValue()))
    {
		var val = cell.getValue();
        if (cell.cell.getAttribute("colType") == "text"
            ||cell.cell.getAttribute("colType") == "label"
            ||cell.cell.getAttribute("colType") == "co"
            )
        {
                val="`str:`"+cell.getValue();
        }

        var str = "updateExpression." + cell.getId() + ".value=" + val;	
        //str = str + "&updateExpression." + cell.getId() + ".id=" + cell.cell.idd;
        str = str + "&formId=" + $F('formId') + "&termDate=" + $F('termDate') + "&reportType=" + $F('reportType') + "&reportMetaInfoCode=" + $F('reportId');
		var url=getUrlByActionNameStr('ExpressionVOUpdateCellAction.html');
		//alert(str);
		 var myAjax = getMyAjax(mydiv, url, str);
    }
   
}


//���������

function updateCellAll()
{
//    functionName = "updateCell";
//
//		//var val = cell.getValue();
//		 var str = parParameters();
//       // var str = "updateExpression." + cell.getId() + ".value=" + val;
//       // str = str + "&formId=" + $F('formId') + "&termDate=" + $F('termDate') + "&reportType=" + $F('reportType');
//		var url=getUrlByActionNameStr('ExpressionUpdateRowCurrAction.html');
//		document.getElementById("div1").innerText=str;
//		 var myAjax = getMyAjax(mydiv, url, str);
    return true;
    }

//��ȡ����
function loadImg2Db()
{
    functionName = "loadImg2Db";	
    var str = "";
    str = str + "formId=" + $F('formId') + "&termDate=" + $F('termDate') + "&reportType=" + $F('reportType');
	var url=getUrlByActionNameStr('ExpressionVOHisAction.html');	
    var myAjax = getMyAjax(mydiv, url, str);
}

function saveAjax()
{
    functionName = "saveAjax";	
    var str = "";
	str = str + "formId=" + $F('formId') + "&termDate=" + $F('termDate') + "&reportType=" + $F('reportType') + "&reportCode=" + $F("reportCode") + "&reportMetaInfoCode=" + $F('reportId');
	var url=getUrlByActionNameStr('ExpressionVOSaveAction.html');	
    var myAjax = getMyAjax(mydiv, url, str);
}
function saveAjaxOncomplete(){

}

//���ڵ���
function onloading() {
    eval(functionName + "Onloading()");
}
//��ݵ������
function onloaded() {
    eval(functionName + "Onloaded()");
}
//ִ�нű�
function oninteractive() {
    eval(functionName + "Oninteractive()");
}
//���ý���
function oncomplete() {
    eval(functionName + "Oncomplete()");
	
}
//���óɹ�
function onsuccess() {
    eval(functionName + "Onsuccess()");
}
//����ʧ��
function onfailure() {
    eval(functionName + "Onfailure()");
}

function getMyAjax(mydiv, url, str) {
    var myAjax = new Ajax.Updater(
            mydiv,
            url,
    {
        method: 'post',
        parameters: str,
        evalScripts: true,
    //���ڵ���
        onLoading:onloading,
    //��ݵ������
        onLoaded: onloaded,
    //ִ�нű�
        onInteractive:oninteractive,
    //���ý���
        onComplete:oncomplete,
    //���óɹ�
        onSuccess:onsuccess,
    //����ʧ��
        onFailure:onfailure
    });
    return myAjax;
}

function parParameters() {
    var pars = new Array();
    var expression = new Array();
    var str = "";
    for (var jj = 1; jj < windowf.obj.rows.length; jj++) {
        var rrow = windowf.obj.rows[jj];
        for (var i = 1; i < rrow.cells.length; i++) {
            var cell = rrow.cells[i];             
            cell = windowf.cells4(cell);
            var val = cell.getValue();
            if (cell.getValue() != '&nbsp;' && cell.getValue() != 0 && cell.getValue() != '#REF!')
            {
			if (cell.cell.getAttribute("colType") == "text"		
			||cell.cell.getAttribute("colType") == "label"
			||cell.cell.getAttribute("colType") == "co"
			){
				val="`str:`"+cell.getValue();
			}
				val=encodeURIComponent(val);
              pars[pars.length] = "expression." + cell.getId() + ".value=" + val;
             }

            if (cell.getExpression() && cell.getExpression() != null && cell.getExpression() != "" && cell.getExpression() != "&nbsp;"
                  && (cell.getId().indexOf("HEAD") == "-1") && (cell.getId().indexOf("FOOT") == "-1" )
				   )
            {				
                expression[expression.length] = "expression." + cell.getId() + ".expression=" + encodeURIComponent(cell.getExpression());
            }
            
        }
    }

    var str = "";
    if (expression.length == 0)
    {
        str = pars.join("&");
    } else {
        var str = pars.join("&") + "&" + expression.join("&");
    }
    str = str + "&formId=" + $F('formId') + "&termDate=" + $F('termDate') + "&reportType=" + $F('reportType') + "&reportMetaInfoCode=" + $F('reportId');
	str = str + "&expression.FOOTStart=" + windowf.FOOTStart + "&expression.HEADEnd=" + windowf.HEADEnd + "&expression.hasNumberLine=" + windowf.hasNumberLine;
    return str;
}

function parReportParameters() {   
    var expression = new Array();
    var str = "";
    for (var jj = 2; jj < windowf.obj.rows.length; jj++) {
        var rrow = windowf.obj.rows[jj];
        for (var i = 1; i < rrow.cells.length; i++) {
            var cell = rrow.cells[i];
             if(cell.idd)
			{	   	
				expression[expression.length]="expression."+cell.id+".id="+cell.idd;
			}	     
        }
    }
    var str = "";
    if (expression.length != 0)
    {
   		str = str + expression.join("&");
    }
    str = str + "&rowHeader=" + windowf.colEndIndex;
    str = str + "&columnHeader=" + windowf.rowEndIndex;
    str = str + "&formId=" + $F('formId') + "&termDate=" + $F('termDate') + "&reportType=" + $F('reportType') + "&reportMetaInfoCode=" + $F('reportId');
    return str;
}


 

function getUrlStr() {
    var s = window.location.host;
    var context = '/express';
    try {
        context = $F('serverAddr');
    } catch(e) {
    }
   var url = "http://" + s + context + "/ExpressionDemoAction.html";

    return url;
}
function getUrlByActionNameStr(action) {
    var s = window.location.host;
    
    var context = '/express';
    try {
        context = $F('serverAddr');
    } catch(e) {
    }
    var url = "http://" + s + context + "/"+action;
    return url;
}

function setCellReadOnly() {
    for (var jj = 1; jj < windowf.obj.rows.length; jj++) {
        var rrow = windowf.obj.rows[jj];
        for (var i = 1; i < rrow.cells.length; i++) {
	        var cell = rrow.cells[i];
		if (cell._cellType == "float"
			||cell._cellType == "int"
			||cell._cellType == "text"
			||cell._cellType == "percent"
			||cell._cellType == "rowHeader"
			||cell._cellType == "colHeader"
			||cell._cellType == "label"
			||cell._cellType == "date"
			||cell._cellType == "number"
			||cell._cellType == "cnmoney"){
			cell._cellType = 'ro'+cell._cellType;				
		
		}else if (cell._cellType.indexOf('ro')!=0){
			cell._cellType="ro";	
		}
        }
    }   

   



}

