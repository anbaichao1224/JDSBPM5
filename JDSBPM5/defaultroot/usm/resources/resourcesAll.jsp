<%@ taglib uri="/struts-tags" prefix="s"%>
<%
	String contextpath = request.getContextPath() + "/";
%>
<html>
	<head>

		<title></title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">

		<link rel="stylesheet" type="text/css"
			href="<%=contextpath%>usm/resources/css/gridview.css" />
		<link rel="stylesheet" type="text/css"
			href="<%=contextpath%>usm/resources/css/file-upload.css" />
		<link rel="stylesheet" type="text/css"
			href="<%=contextpath%>js/ext/resources/css/ext-all.css" />
		<script src="<%=contextpath%>js/ext/adapter/ext/ext-base.js"></script>
		<script src='<%=contextpath%>js/ext/ext-all.js'></script>

		<script type="text/javascript"
			src="<%=contextpath%>usm/resources/resourcesAll.js" defer="defer"></script>
		<script type="text/javascript"
			src="<%=contextpath%>usm/js/FileUploadField.js"></script>
		<script type="text/javascript"
			src="<%=contextpath%>usm/resources/examples.js"></script>
		<link rel="stylesheet" type="text/css"
			href="<%=contextpath%>usm/resources/examples.css" />
	</head>

	<body>

	</body>

	<script language="JavaScript">   
  <!--     
  var NS4 = (document.layers);       
  var IE4 = (document.all);   
  var win = window;         
  var n = 0;   
  function findIt(val){   
  if (val!="")   
    findInPage(val);   
  }     
  function findInPage(str){   
    var txt,i,found;   
    if(str == "")   
       return false;   
    if(NS4){   
         if(!win.find(str)){   
           while(win.find(str,false,true))   
                n++;
         }   
         else   
           n++;
    }      
    if(IE4){   
        txt=win.document.body.createTextRange();   
        for(i=0;i<=n&&(found=txt.findText(str))!=false;i++)
        {   
           txt.moveStart("character",1);   
           txt.moveEnd("textedit");   
        }     
        if(found){   
  			txt.moveStart("character",-1);   
  			txt.findText(str);   
 		    txt.select();   
  			txt.scrollIntoView();   
            n++;   
        }else{   
            if(n>0){   
                 n=0;   
                 findInPage(str);   
            }   
        }   
    }      
  }   
  //   -->   
  </script>
</html>
