<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML><HEAD><TITLE>报表定义工具</TITLE>
<META http-equiv=Content-Type content="text/html; charset=utf-8">
     <link rel="STYLESHEET" type="text/css" href="..\css\designer\default.css">
	 <script src="..\scripts\designer\js\dhtmlXAll.js" type="text/javascript"></script>
</HEAD>
<BODY   onresize="resizeTabbar()" >
<TABLE  id=td01 cellSpacing=0 cellPadding=0 width="100%" border=1  ><tr><td>
    <TABLE  id=td03 cellSpacing=0 cellPadding=0 width="100%" border=0  >
        <TBODY>
           <tr>
            <TD height="20" colspan=4 > <div id="xpstyle" style="width:100%;"/></TD>
          </TR>
          <TR> 
            <TD height="20" colspan=4 > <div id="toolbar_zone1" style="width:100%;border :1px solid Silver;"/></TD>
          </TR>
          <TR> 
            <TD > <INPUT style="border:1px solid #F3F3F3;height:18px" id=tdinfo size=8 name=expression_str_tdid> </TD>
            <TD width="40" align="left"><font size="2" face="Courier New, Courier, mono"> <BUTTON  style="height:18px;margin-left:3px;line-height:16px;border:1px solid #F3F3F3;	background-Color: #F3F3F3;" onclick=openEditor();>FW</BUTTON></font></TD>
            <TD > <input  style="border:1px solid #F3F3F3;height:18px" id=expression_str_4edit   size=110 name=expression_str_4edit > 
            </TD>
            <TD  align="left" > <BUTTON  style="height:18px;margin-left:3px;line-height:16px;border:1px solid #F3F3F3;	background-Color: #F3F3F3;" onclick=openEditor();>编辑</BUTTON></TD>
          </TR>
        </TBODY>
      </TABLE>
</td>
</tr>
    <tr>
        <td>
         <div  id="a_tabbar" class="dhtmlxTabBar" imgpath="../imgs/dhtmlxTabbar/" skinstyle="modern" mode="bottom"  skinColors="#FCFBFC,#F4F3EE" >
           <jsp:include page="business.jsp" flush="true"/>
        </div>
	    </td>
    </tr>
</table>

<INPUT id=serverAddr type=hidden value="" name=serverAddr>
<INPUT id=formId type=hidden value=ResourceTaxDelare name=formId>


<SCRIPT type=text/javascript>

//右键菜单函数初始化函数,要在windowf生成前调用
 
    initRightMenu();

    window['test111']=new dhtmlXGridFromTable('test111');
    var windowf = window['test111'];

    $('a_tabbar').style.width = (document.body.offsetWidth-30)+"px";
    $('a_tabbar').style.height = (document.body.offsetHeight-400)+"px";
    var a_tabbar= new dhx_Form_tabbars('a_tabbar');
    var xml_prefix = "../scripts/designer/config/";
//    $('a_tabbar').style.width = (document.body.offsetWidth-30)+"px";
//    $('a_tabbar').style.height = (document.body.offsetHeight-400)+"px";

    aMenuBar3=new dhtmlXMenuBarObject(document.getElementById('xpstyle'),'100%',23,"Demo menu");
    aMenuBar3.setOnClickHandler(onMenuButtonClick);
    aMenuBar3.setGfxPath("../images/designer/formDef/");
    aMenuBar3.loadXML(xml_prefix + "_menuutf8.xml");
    aMenuBar3.showBar();

    aToolBar1=new dhtmlXToolbarObject(document.getElementById('toolbar_zone1'),'100%',16,"Demo toolbar");
    aToolBar1.setOnClickHandler(onButtonClick);
    aToolBar1.loadXML(xml_prefix + "_toolbarutf8.xml");
    aToolBar1.showBar();

    function resizeTabbar(){
            a_tabbar.enableAutoReSize(true,true);
            window.setTimeout(new function(){$('a_tabbar').style.width = (document.body.offsetWidth-30)+"px"},250) ;
            window.setTimeout(new function(){$('a_tabbar').style.height = (document.body.offsetHeight-85)+"px"},250) ;
    }
 
    function a_tabbarOnInit(idn,ido)
    {
        winId=idn.substring(0,idn.length-3);
        if (!window[winId].obj  ){
            window[winId]=new dhtmlXGridFromTable(winId);
             doOk();
          }
            windowf=window[winId];
           // return true;
    };

   function protocolIt(str){
		alert(str);
	}
	function doOnRowSelected(id){
		return true;
	}

	function CellDbOnClick(cell){

		return true;
	}


function CellOnClick(cell){
  try
	{
        if(null != cell.debugexpression && 'undefined' != cell.debugexpression)
		{
			$("expression_str_4edit").value = cell.debugexpression;	
		}
		else
		{
			$("expression_str_4edit").value='';
			}
		$("expression_str_tdid").value = cell.id;		
	}catch(e){}

		return true;
	}



	function doOnCheck(rowId,cellInd,state){
		//protocolIt("User clicked on checkbox or radiobutton on row "+rowId+" and cell with index "+cellInd+".State changed to "+state);
		return true;
	}
	function doOnEnter(rowId,cellInd){
		//protocolIt("User pressed Enter on row with id "+rowId+" and cell index "+cellInd);
	}
	function doBeforeRowDeleted(rowId){

    }
	function onMenuButtonClick(itemId,itemValue)
		{ };

 	function onButtonClick(itemId,itemValue)
		{
              eval('menu'+itemId+"()");
        };
    function menuselectbolck(){
        if (windowf._selectionObj){
            windowf.disableBlockSelection();
            windowf._selectionObj=null;
        } else{
        windowf.enableBlockSelection();
        }
    }

</SCRIPT>
</BODY></HTML>