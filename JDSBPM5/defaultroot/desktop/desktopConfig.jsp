<%@ page contentType="text/html; charset=UTF-8"%>

<%@ taglib uri="/struts-tags" prefix="ww"%>




 {
	desktopConfig:{ 
		   launchers:{ 
				shortcut:<ww:property value="#shortcut=desktop.property.shortcut, (#shortcut ==null||#shortcut=='') ? '[]':#shortcut"  escape="false"/>,
				autorun:<ww:property value="#autorun= desktop.property.autorun, (#autorun==null||#autorun=='') ? '[]':#autorun"  escape="false"/>,
				
				quickstart:<ww:property value="#quickStart=desktop.property.quickStart,(#quickStart==null || #quickStart=='') ? '[]':#quickStart"  escape="false"/>
				
				},
				'styles': {
	             'theme': {
						'id': '<ww:property value="desktop.cssBean.id"  escape="false"/>',
						'name':'<ww:property value="desktop.cssBean.name"  escape="false"/>',
						'pathtofile': context+'<ww:property value="desktop.cssBean.pathtofile"  escape="false"/>'
					},
					'transparency':'<ww:property value='desktop.wallpaperBean.transparency'  escape="false"/>',
				    'wallpaperid': '<ww:property value="desktop.wallpaperBean.id"  escape="false"/>',
					'wallpapername':'<ww:property value="desktop.wallpaperBean.name"  escape="false"/>',
					'wallpaperposition':'<ww:property value="desktop.property.wallPaperPosition"  escape="false"/>',
				    'backgroundcolor': '<ww:property value="desktop.property.backGroundColor"  escape="false"/>',
				    'fontcolor':'<ww:property value="desktop.property.fontColor"  escape="false"/>',
					'wallpaperfile': context+'<ww:property value="desktop.wallpaperBean.pathtofile"  escape="false"/>'
				}
	   },
	   openerwin:<ww:property value="#openerwin= desktop.property.openerwin, (#openerwin==null||#openerwin=='') ? '[]':#openerwin"  escape="false"/>

	   ,
	  startMenuCfg: <ww:property value="(startMenu==null || startMenu=='') ? '[]': startMenu" escape="false"/>
	   ,
      moduleCfg:{
		 userDefModules:[
		   
		 <ww:iterator value="userDefModules.{? (#this.module.moduleStatus==0 && module.path=='DESK')}" status="rowstatus">
		    {
		    id:"<ww:property value="module.ID" escape="false"/>",
		    text:"<ww:property value="module.name" escape="false"/>",
		    url:"<ww:property value="module.url" escape="false"/>",
		    path:"<ww:property value="module.path" escape="false"/>",
		    iconCls:"<ww:property value="(module.cls==null || module=='') ? module.ID : module.cls" escape="false"/>",
		
		    icon:"<ww:property value="module.icon" escape="false"/>",
		    thumb:"<ww:property value="thumb" escape="false"/>",
		    creatTime:"<ww:property value="module.creatTime" escape="false"/>",
		    lastModified:"<ww:property value="module.lastModified" escape="false"/>",
		    winConfig:"<ww:property value="module.winConfig" escape="false"/>"
		    }
		     <ww:if test ="#rowstatus.count < userDefModules.size">
		      , 
		      </ww:if>
		     </ww:iterator>    
		 ],
		 systemModules:[
		 <ww:iterator value="systemModules" status="rowstatus"> 
		    {
		    id:"<ww:property value="(module.enName==null || module.enName=='')? module.ID:module.enName" escape="false"/>",
		    uuid:"<ww:property value="module.ID" />",
		    text:"<ww:property value="module.name" escape="false"/>",
		    icon:"<ww:property value="icon==null?'':icon" />",
		    iconCls:"<ww:property value="module.ID" />",
		    thumb:"<ww:property value="thumb" escape="false"/>",
		    gbkUrl:"<ww:property value="url" escape="false"/>",
		    winCfg:<ww:property value="winCfg" escape="false"/>,
		    url:"<ww:property value="usmTypeUrl" escape="false"/>"
		 
		    }
		     <ww:if test ="#rowstatus.count < systemModules.size">
		      , 
		      </ww:if>
		     </ww:iterator>    
		 ]
	 }
	 
	}


