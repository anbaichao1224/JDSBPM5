<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/struts-tags" prefix="ww" %>

<%

String contextpath = request.getContextPath() + "/";

%>

  <script>  
    	context='<%=contextpath%>';
	
    //鼠标移动到图片上
	function over(the,imgsrc){
		the.className="img_over";
		the.src='images/'+imgsrc+'_over.gif';
	}
	//鼠标移出图片上
	function out(the,imgsrc){
		the.className="img";
		the.src='images/'+imgsrc+'.gif';
	}
	//鼠标在图片上按下
	function down(the,imgsrc){
		the.className="img_down";
		//the.src='images/'+imgsrc+'_down.gif';
	}
	function over_(the){the.className="img_over";}
	function out_(the){the.className="img";}
	function down_(the){the.className="img_down";}
	      
            
    </script>
 

  <TABLE 	style="font:12px Arial,sans-serif;background-color : #F3F3F3;border-bottom : solid 1px #F3F3F3;border-left : solid 1px #F3F3F3;border-right : solid 1px #FFFFFF;border-top : solid 1px #ffffff;" CELLPADDING="0" CELLSPACING="0" BORDER="0" width="100%">
	  <TR valign="middle" height=22>
		<TD width=2></TD>
		<TD width=3><img src="desktop/widgets/explorer/images/toolbar.gif"></TD>
		<TD width=4></TD>
		<TD width=46><nobr>地址(<u>D</u>)</TD>
		<TD width=2></TD>
		<TD>
		  <div style="border:2 inset white;width:100%; height:22px;background-color:#FFFFFF;font:12px Arial,sans-serif;">
		  <TABLE CELLPADDING="1" CELLSPACING="0" BORDER="0" width="100%" height="100%">
		  <TR>
		  	<TD width=16><img src="desktop/widgets/explorer/images/ie.gif"></TD>
		  	<TD id=URL><a style="font:12px Arial,sans-serif;">http://<%=request.getServerName()%>:<%=request.getServerPort()%></a></TD>
		  </TR>
		  </TABLE>
		  </div>
		</TD>
		<TD width=2></TD>
		<TD width=49><img src="desktop/widgets/explorer/images/goto.gif" class="img" onmousedown="down(this,'goto');" onmouseout="out(this,'goto');" onmouseover="over(this,'goto');" onmouseup="over(this,'goto');" border=1></TD>
		<TD width=4></TD>
	  </TR>
	</TABLE>
