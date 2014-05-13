<%@ page import="java.io.File" %>
<%@ page import="java.io.BufferedReader" %>
<%@ page import="java.io.InputStreamReader" %>
<%@ page import="java.io.FileInputStream" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2009-7-6
  Time: 15:23:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


    <%
    String path=request.getParameter("path");
    String type=request.getParameter("type");
    String codeType=request.getParameter("codeType");
    String pathType = request.getParameter("pathType");
//      System.out.println("==="+path+"---"+type+"***"+codeType);
    if(codeType==null||codeType.equals("")){
      codeType="gbk";
    }
      if(type==null||type.equals("")){
        type="xml";
      }
    //path="E:\\mywork\\tcyx\\jdsbpm3.1\\defaultroot\\test.htmla";
    %>
<html>
<head><title>显示和编辑</title>

  <script src='js/ext/adapter/ext/ext-base.js'></script>
  <script src='js/ext/ext-all.js'></script>
  <script src='js/editarea/edit_area_full.js'></script>
  <script language="javascript">
    function trim(str){
      if(!str)return "";
      return str.replace(/^\s+|\s+$/g,"");
    }

    var path = "<%=path.replace('\\','/')%>";
    var type = "<%=type%>";
    var codeType = "<%=codeType%>";
	var pathType = "<%=pathType%>";
    function savefn(id, str) {
      var saveUrl = "/explorer/writeFile.action";
      var params = {
        content:str,
        path:path,
        codeType:codeType,
        pathType:pathType
      };
      Ext.Ajax.request({
        url:saveUrl,
        method:"post",
        params:params,
        success:function(r){
          if(trim(r.responseText)=="ok"){
            alert("保存成功");
          }
        },
        failure:function(){alert("保存出错");}
      });
    }

    function showAndEdit(id,str, type) {
      type = type || "xml";
      document.getElementById(id).value = str;
//      alert(str)
     // return 
      editAreaLoader.init({
        id: id  // id of the textarea to transform
       ,start_highlight: true  // if start with highlight
        ,allow_resize: "both"
        ,allow_toggle: true
        ,word_wrap: true
        ,toolbar: "search, go_to_line, |, undo, redo, |, highlight, reset_highlight,save"
        ,language: "zh"
        ,syntax: type
        ,save_callback:"savefn"
      });
    }



    function init() {
      var url="/explorer/readFile.action";
      var id = "txt1";
      Ext.Ajax.request({
        url: url,
        method:"post",
        params:{
          path:path,
          codeType:codeType,
        pathType:pathType
        },
        success: function(res) {
          showAndEdit(id, trim(res.responseText), type);
        },
        failure:function(){
          alert("打开文件出错");
        }
      });
    }
  </script>
</head>
<body onload="init()">
<textarea id="txt1" style="height: 90%; width: 100%;"></textarea>
</body>
</html>

