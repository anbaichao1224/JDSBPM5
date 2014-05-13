<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="/struts-tags" prefix="ww" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta name="robots" content="noindex, nofollow" />

    <title>表单编辑</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
   
    <script type="text/javascript" src="fckeditor.js"></script>
    <script type="text/javascript">

        window.onload = function()
        {
            var oFCKeditor = new FCKeditor('formcontent') ;
            oFCKeditor.BasePath = '';
            oFCKeditor.Width = '100%';
            oFCKeditor.Height = '95%';
            oFCKeditor.ReplaceTextarea();
        }
        function saveAndGo(url){
       
        }
        //Save();
    </script>
</head>
<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<form action="posteddata.jsp" method="post" target="postdata">
    <div align="center">
    <textarea name="formcontent" rows="2" cols="30">
    <ww:property value="xml" />
    </textarea>
    </div>
    <input type="hidden" name="path" value="<ww:property value="fileName" escape="false"/>"/>
    <%--<input type="submit" value="Submit"/>--%>
</form>
<iframe name="postdata" id="postdata" height="0" width="0" src="posteddata.jsp"></iframe>
</body>
</html>
</html>