<%@ page contentType="text/html; charset=GBK"%>
<%@ page	import="java.util.Map,com.opensymphony.xwork2.util.OgnlValueStack,net.itjds.common.CommonConfig;"%>
<%@ taglib uri="/struts-tags" prefix="ww"%>
 function EVAL.getPanel(){
 var saveDoc = new Ext.Action({
        text: '保存', 
        handler: function(){ 
        framedoc.window.TANGER_OCX_SaveEditToServer();   
        }
       // iconCls: 'saveButton'
    });

 var printDoc = new Ext.Action({
        text: '打印',
        handler: function(){ 
           framedoc.window.TANGER_OCX_PrintDoc();
        }
      //  iconCls: 'printButton'
    });
    
    var chgLayout = new Ext.Action({
        text: '页面布局',
        handler: function(){ 
           framedoc.window.TANGER_OCX_ChgLayout();
        }
      //  iconCls: 'printButton'
    });
    
    var showRevisions = new Ext.Action({
        text: '显示痕迹',
        handler: function(){ 
         framedoc.window.TANGER_OCX_ShowRevisions(true);
           
        }
      //  iconCls: 'printButton'
    });
    var hidRevisions = new Ext.Action({
        text: '隐藏痕迹',
        handler: function(){ 
            framedoc.window.TANGER_OCX_ShowRevisions(false);
        }
      //  iconCls: 'printButton'
    });
    
     var acceptAllRevisions = new Ext.Action({
        text: '接受所有修订',
        handler: function(){ 
            framedoc.window.TANGER_OCX_AcceptAllRevisions();
        }
      //  iconCls: 'printButton'
    });
    
    var doHandDraw = new Ext.Action({
        text: '手写批注',
        handler: function(){ 
           framedoc.window.DoHandDraw();
        }
       // iconCls: 'printButton'
    });
    
    var doHandSign = new Ext.Action({
        text: '手写签名',
        handler: function(){ 
           framedoc.window.DoHandSign();
        }
        //iconCls: 'printButton'
    });
    
     var toolbars = new Ext.Action({
        text: '菜单显示',
        handler: function(){ 
           framedoc.window.TANGER_OCX_OBJ.Toolbars=!framedoc.window.TANGER_OCX_OBJ.Toolbars;
        }
    });
    
    var winClose = new Ext.Action({
        text: '保存并退出',
        handler: function(){ 
        //  getCurDesktop().getWindow(winId).close();
        framedoc.winClose();
        getCurDesktop().getManager().getActive().close();
        }
     //   iconCls: 'printButton'
    });
var panel={
		title:"正文",
		width:800,
		height:600,
			manager: getCurDesktop().getManager(),
                minimizable: true,
                maximizable: true,
		tbar:[
		 <ww:if test ="savedoc == null ||  savedoc">		
		saveDoc,'-',
		</ww:if>
		chgLayout,'-',
		 <ww:if test ="printdoc == null ||  printdoc">
		printDoc,'-',
		</ww:if>
		 <ww:if test ="showRevisions == null ||  showRevisions">
		 showRevisions,'-',
		 </ww:if>
		  <ww:if test ="hidRevisions == null ||  hidRevisions">
		 hidRevisions,'-',
		 </ww:if>
		  <ww:if test ="acceptAllRevisions == null ||  acceptAllRevisions">
		 acceptAllRevisions,'-',
		 </ww:if>
		  <ww:if test ="handDraw == null ||  handDraw">
		 doHandDraw,'-',
		 </ww:if>
		  <ww:if test ="toolbars == null ||  toolbars">
		 toolbars,'-',
		 </ww:if>
		 winClose],
		html:'<iframe id="framedoc" name="framedoc" src="<%=request.getContextPath() %>/openDocumentAction.action?activityInstId=<ww:property value="activityInstId"/>&docid=<ww:property value="docid"/>" style="width:100%;height:100%"></iframe>'			
		};
		
	return panel;										
	}										
											