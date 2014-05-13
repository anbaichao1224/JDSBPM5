<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.*,kzxd.documentmodel.entity.KZXDDocumentModel" %>
<%@ taglib uri="/struts-tags" prefix="ww"%>
<html>
<title>查看文档</title>
<head>
script type="text/javascript">
    function openRemote() {

        if (url.value == "") {
            alert("url地址为空");
            return;
        }
        var ret = pdf.OpenRemotePdf(url.value);
        if (ret != 1) {
            alert("打开文件错误");
        }
    }

    function print() {
        pdf.PrintPDF();
    }
function test(obj, filter) {

    var file = obj.value.match(/[^\/\\]+$/gi)[0];
    var rx = new RegExp('\\.(' + (filter ? filter : '') + ')$', 'gi');
    if (filter && file && !file.match(rx)) {
        alert("只能选择pdf");
        //重新构建input file
        document.body.innerHTML = "<input type='file' id='pdfile' name='pdfile' accept='application/pdf' onchange=\"test(this,'pdf');\" />";
    }
    alert(obj.value);
    var ret = pdf.OpenPdf(obj.value);
    if (ret != 1) {
        alert("打开文件错误");
    }

}

function pdfLoad(){
	
}

</script>
</head>

<body>
<div>
    <input type="file" id="pdfile" name="pdfile" accept="application/pdf" onchange="test(this,'pdf');" title="选择PDF文件并打开"/>
    远程URL:<input type="text" name="url" id="url" />
    <input type="button" value="打开远程文件" onclick="openRemote();" />
    <input type="button" value="打印" onclick="print();" />
    
</div>
<div>
<object id="pdf" classid="clsid:3542AF22-FBBF-4799-9EE7-DB428ED59DE8" width="907" height="615" title="测试">
</object>
</div>
</body>
</html>