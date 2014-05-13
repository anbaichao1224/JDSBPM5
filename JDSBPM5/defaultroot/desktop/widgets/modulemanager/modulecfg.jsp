<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="ww"%>

{
success:true,
    data:{
    id:"<ww:property value="module.ID" escape="false"/>",
     module:"<ww:property value="module.ID" escape="false"/>",
    text:"<ww:property value="module.name" escape="false"/>",
    url:"<ww:property value="module.url" escape="false"/>",
    path:"<ww:property value="module.path" escape="false"/>",
    iconCls:"<ww:property value="module.cls" escape="false"/>",
    //icon:"/desktop/resources/images/user/fawendefaultWorkList/fawendefaultWorkList_shortcut.gif",
    icon:"<ww:property value="module.icon" escape="false"/>",
    thumb:"<ww:property value="module.thumb" escape="false"/>",
    createTime:"<ww:property value="module.createTime" escape="false"/>",
    lastModified:"<ww:property value="module.lastModified" escape="false"/>",
    winConfig:"<ww:property value="module.winConfig" escape="false"/>"
    }
}		 
