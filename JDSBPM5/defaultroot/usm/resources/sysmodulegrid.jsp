
<%@ include file="/usm/common/taglibs.jsp"%>
<%
String contextpath = request.getContextPath() + "/";
%>
<html>
<head>
	<script type="text/javascript">
	 var sysid="${sysid}";
	</script>
    <title>Grid</title>
     <link rel="stylesheet" type="text/css" href="<%=contextpath%>js/ext/resources/css/ext-all.css" />
     <script src="<%=contextpath%>js/ext/adapter/ext/ext-base.js"></script>
     <script src='<%=contextpath%>js/ext/ext-all.js'></script>
	 <script type="text/javascript">
        Ext.BLANK_IMAGE_URL = '/usm/resources/images/default/s.gif';
     </script>
     <script type="text/javascript" src="<%=contextpath%>usm/js/sysmodulegrid.js" defer="defer"></script>
    <style type="text/css">
    .search {
        background-image: url(usm/images/plugin.gif) !important;
     }
     .remove {
        background-image: url(usm/images/delete.gif) !important;
     }
     .edit {
        background-image: url(usm/images/edit.gif) !important;
     }
     .add {
        background-image: url(usm/images/add.gif) !important;
     }
     .preview {
        background-image: url(usm/images/preview.png) !important;
     }
    </style>
</head>

<body>  
<script type="text/javascript">
      var strarr=[
		<s:iterator id="li" value="modulelist" status="rowstatus">
			 {boxLabel: '<s:property value="#li.memo"/>',name: 'txtCheckValue',inputValue: '<s:property value="#li.moduleid"/>'
			 <s:iterator id="li2" value="sysmodulelist">
		       <s:if test="moduleid==[1].moduleid">    
		        ,checked:true
		       </s:if>  
	     	 </s:iterator> 
	     	 }
			  <s:if test ="#rowstatus.count < modulelist.size">
		      , 
		      </s:if>
		</s:iterator>
 	];
 	var cnt = '<s:property value="modulelist.size"/>'  
    if (cnt>4){
       cnt = 4;
    }
    var str=[{xtype: 'checkboxgroup',columns:cnt,fieldLabel:'&#x6A21;&#x5757;&#x5217;&#x8868;',itemCls:'x-check-group-alt',width:640,items:strarr},new Ext.form.Hidden({name:'sysid',value:'<s:property value="sysid"/>'}),new Ext.form.Hidden({name:'nodeid',value:'<s:property value="nodeid"/>'}),new Ext.form.Hidden({name:'template',value:'<s:property value="template"/>'})];

</script>
    <div id="form-ct"></div>  
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
