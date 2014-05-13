<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="/struts-tags" prefix="ww"%>
<%@ page import="java.util.*" %>
<%
	String path = request.getContextPath();

	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
			String ngsj =(String)request.getAttribute("ngsj");
		
%>
<html>
	<head>
		<base href="<%=basePath%>"></base>
		<script>var context='<%=path%>';</script>
		<meta http-equiv="pragma" content="no-cache" />
		<meta http-equiv="cache-control" content="no-cache" />
		<script type="text/javascript" src="<%=path%>js/extAll.js"></script>
		<script type="text/javascript" src="<%=path%>/js/ext/TabCloseMenu.js"></script>
		<script type="text/javascript" src="/hq/notice.js" defer="defer"></script>
		<script type="text/javascript"
			src="<%=path%>desktop/js/CreateGrid.js"></script>
		<script src="/js/JDS.js" type="text/javascript"></script>
				<script type="text/javascript">

		</script>
		<style type="text/css">
		.tableClass{  border-collapse: collapse;  }  .tdClass{     border:1px solid #000000; }  .STYLE2 {font-size: medium}
        .STYLE3 {
	font-size: medium;
	font-weight: bold;
}
        </style>
	</head>

    <body>
        ﻿
        <style type="text/css"> .STYLE4 {font-size: 16px}<!-- .STYLE1 {font-size: xx-large} .STYLE2 {font-size: xx-large} --> </style>
         <form id="swhqjbl" name="swhqjbl" action="HqYewuAction_swaddjbl.action" method="post" >
       <input type="hidden" name="yewuuuid" id="yewuuuid" value='<ww:property value="swhqy.uuid"></ww:property>' />
		<input type="hidden" name="uuid" id="uuid" value='<ww:property value="swhqy.uuid"></ww:property>' />
			<input type="hidden" name="djuuid" value='<ww:property value="djuuid"></ww:property>' />
		<table border="0" cellspacing="0" cellpadding="0" width="1000" align="center" height="75">
		 <tbody>
                <tr>
                   <td width="979"><div class="STYLE2" align="center">
                  <p align="center" class="STYLE2">收 文 呈 批 表</p>
              </div></td>
                </tr>
          </tbody>
    </table>
        <table class="tableClass" border="1" bordercolor="#000000" width="1000" align="center" height="1363" style="width: 1050px; height: 1363px">
            <tbody>
                <tr>
                    <td height="40" width="200">
                  <div align="center"><strong>原文编号</strong></div>                  </td>
                  <td height="32" width="111"><input name="ywbh" type="text" class="STYLE4" id="ywbh" value='<ww:property value="swhqy.ywbh"/>' size="15" readonly="true"></td>
                    <td height="32" width="105">
                  <div align="center"><strong>发方总号</strong></div>                  </td>
                  <td width="149"><input name="ffzh" type="text" class="STYLE4" id="ffzh" value='<ww:property value="swhqy.ffzh"/>' size="15" readonly="true"></td>
                    <td height="32" width="90">
                  <div align="center"><strong>密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 级</strong></div>                  </td>
                    <td width="71" style="text-align: center">&nbsp;明&nbsp; 文</td>
                    <td width="134">
                  <div align="center"><strong>收文编号</strong></div>                  </td>
                  <td width="141"><input name="swbh" type="text" class="STYLE4" id="swbh" value='<ww:property value="swhqy.swbh"/>' size="15" readonly="true"></td>
                </tr>
                <tr>
                    <td height="41">
                    <div align="center"><strong>
                    <div align="center"><strong>来文单位</strong></div>
                    </strong></div>                    </td>
                    <td colspan="3"><input name="ywdw" type="text" class="STYLE4" id="ywdw" value='<ww:property value="swhqy.ywdw"/>' size="56" readonly="true"></td>
                    <td>
                    <div align="center"><strong>紧急程度</strong></div>                  </td>
                    <td style="text-align: center"><input name="jjcd" type="text" class="STYLE4" id="jjcd" value='<ww:property value="swhqy.jjcd"/>' size="5" readonly="true"></td>
                    <td>
                    <div align="center"><strong>收文日期</strong></div>                  </td>
                    <td><input name="ngsj" type="text" class="STYLE4" id="ngsj" value="<%=ngsj%>" size="15" readonly="true"></td>
                </tr>
                <tr>
                    <td height="40">
                    <div align="center"><strong>文件标题</strong></div>                    </td>
                    <td colspan="7"><input name="wjbt" type="text" class="STYLE4" id="wjbt" value='<ww:property value="swhqy.wjbt"/>' size="110" readonly="true"></td>
                </tr>
                  <tr>
                <td class="tdClass" height="52" align="center"><strong>指定办理人</strong></td>
                 <td colspan="7" ><input size="50" name="sendrange" type="text"   class="STYLE4"   id="sendrange" onkeydown="return false;"  size="30"/>
             <input type="hidden"  name="personid" id="personid"/>
             <input name="choice" size="70" class="STYLE4"  type="button" id="choice" value="选择" onClick="createPersonPositionWindow('5015','')" /></td>
                </tr>
                <tr>
                    <td height="40">
                    <div align="center"><strong>已&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 发</strong></div>                  </td>
                  <td colspan="7"><input name="yf" type="text" class="STYLE4" id="yf" value='<ww:property value="swhqy.yf"/>' size="110" readonly="true"></td>
                </tr>
                <tr>
                    <td height="250" valign="top">
                    <p>&nbsp;</p>
                    <p>&nbsp;<br />
                    <br />
                    &nbsp;</p>
                    <p align="center"><strong>拟</strong></p><br>
                    <p align="center"><strong>办</strong></p><br>
                    <p align="center"><strong>意</strong></p><br>
                    <p align="center"><strong>见 </strong></p><br>
                    <p>&nbsp;</p>                    </td>
                    <td height="353" valign="top" colspan="7">
                    <p>&nbsp;<textarea name="nbyj" cols="85" rows="15" class="STYLE4" readonly="true"><ww:property value="swhqy.nbyj"></ww:property></textarea><br><br />
                    &nbsp;</p>
                    <table border="0" cellspacing="0" cellpadding="0" width="945" height="38">
                        <tbody>
                            <tr>
                                <td height="38" width="492" align="right"><strong>经办人：</strong></td>
                                <td width="177">&nbsp;<input name="jbr" type="text" class="STYLE4" id="jbr" value='<ww:property value="swhqy.jbr"/>' size="15" readonly="true"></td>
                                <td width="88" align="right"><strong>核对人：</strong></td>
                                <td width="188">&nbsp;<input name="hdr" type="text" class="STYLE4" id="hdr" value='<ww:property value="swhqy.hdr"/>' size="15" readonly="true"></td>
                            </tr>
                        </tbody>
                    </table>
                     </td>
                </tr>
                <tr>
                    <td height="200" valign="top">
                    <p>&nbsp;</p>
                    <p align="center"><strong><br />
                    审</strong></p><br>
                    <p align="center"><strong>核</strong></p><br>
                    <p align="center"><strong>意</strong></p><br>
                    <p align="center"><strong>见</strong></p><br>                    </td>
                    <td height="183" valign="top" colspan="7">
                     <ww:iterator value="shyj" status="status">
                  	 <ww:property value="shyj[#status.index]"/><br>
                  </ww:iterator>
                    </td>
                </tr>
                <tr>
                    <td height="300" valign="top">
                    <p align="center">&nbsp;</p>
                    <p align="center">&nbsp;</p>
                    <p align="center"><strong><br />
                    领</strong></p><br>
                    <p align="center"><strong>导</strong></p><br>
                    <p align="center"><strong>批</strong></p><br>
                    <p align="center"><strong>示</strong></p><br>                    </td>
                    <td height="183" valign="top" colspan="7">
                     <ww:iterator value="ldsq" status="status">
                  	 <ww:property value="ldsq[#status.index]"/><br>
                  </ww:iterator></td>
                    </td>
                </tr>
                <tr>
                    <td height="200" valign="top">
                    <p>&nbsp;</p>
                    <p align="center"><strong>阅</strong></p><br>
                    <p align="center"><strong>文</strong></p><br>
                    <p align="center"><strong>签</strong></p><br>
                    <p align="center"><strong>名</strong></p><br>                    </td>
                    <td height="183" valign="top" colspan="7">
                      <ww:iterator value="hqyj" status="status">
                  	 <ww:property value="hqyj[#status.index]"/><br>
                  </ww:iterator></td>
                    </td>
                </tr>
                    </tr>
              <tr>
					<td height="150" align="center">
						<div align="center">
							<p class="STYLE4">
								正					</p><br>
							<p class="STYLE4">
								文					 </p>
						<p>&nbsp;</p></div></td>
					<td colspan="7" style="width:50px">
						
						<ww:action name="zhengwen" executeResult="true"></ww:action>			
					</td>
			  </tr>
				
                  <tr>
					<td height="150" align="center">
						<div align="center">
							<p class="STYLE4">
								附							</p><br>
							<p class="STYLE4">
								件							</p>
						 <p>&nbsp;</p></div></td>
					<td colspan="7" style="width:50px">
						
						<ww:action name="hqdocexchangefileinclude" executeResult="true"></ww:action>			
					</td>
				</tr>
                
            </tbody>
          </table>
        </div>
       
    </body>
</html>




	<c:forEach items="${menuList}" var="menu">
				<c:if test="${menu.moduleId == module.id }">
				 	<div class="nav-item">
				 		<img src="${menu.menuPA}"/><a href="${menu.resourceContent}" class="ziTi1" target="mainFrame">${menu.menuName}</a>
				 	</div>
			 	</c:if>
		 	</c:forEach>
