<#if obj.datatype.curObjId??> 
<#if obj.datatype.curObjId=='ZiHao'>
	<#if	obj.basearr.readonly??>
	 	<iframe id="iframeupss"  width="470" scrolling="no" frameborder=0 height="44" src="/tozihao.do?actid=<ww:property value="activityInstId"/>&readonly=1"></iframe>
	<#else>
		 	<iframe id="iframeupss"  width="470" scrolling="no" frameborder=0 height="44" src="/tozihao.do?actid=<ww:property value="activityInstId"/>&readonly=0"></iframe>
		</#if>
	<#else>
	<input 
<#list zatts as att>
	<#if att.name??  && att.value??> ${att.name}="${att.value}" </#if>
</#list> 
<#if obj.datatype.curObjId??> 
 custype="${obj.datatype.curObjId}" 
<#else> custype="TextType" </#if> 
<#if obj.basearr.theoremexpression??> value="<#if obj.datatype.curObjId?? && obj.datatype.curObjId=='DateType'><ww:date  format="yyyy-MM-dd" name="${obj.basearr.theoremexpression}"/><#else><ww:property value="${obj.basearr.theoremexpression}"/></#if>"</#if>  
/>
</#if>
<#else>
<input 
<#list zatts as att>
	<#if att.name??  && att.value??> ${att.name}="${att.value}" </#if>
</#list> 
<#if obj.datatype.curObjId??> 
 custype="${obj.datatype.curObjId}" 
<#else> custype="TextType" </#if> 
<#if obj.basearr.theoremexpression??> value="<#if obj.datatype.curObjId?? && obj.datatype.curObjId=='DateType'><ww:date  format="yyyy-MM-dd" name="${obj.basearr.theoremexpression}"/><#else><ww:property value="${obj.basearr.theoremexpression}"/></#if>"</#if>  
/>
</#if>

