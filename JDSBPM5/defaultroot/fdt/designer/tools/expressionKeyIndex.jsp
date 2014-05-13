<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/struts-tags" prefix="ww" %>
<script>

    var sum = '<ww:property value="$searchTemplateList.size()"/>';
    oninit(sum, '<ww:property value="$R('objName')"/>');
    var sss = $('search_suggest');    
    sss.innerHTML = '';
    var suggest = "";
    <ww:iterator value="$searchTemplateList" status="routs">
    suggest += '<div style="OVERFLOW: auto;clip:rect();" onmouseover="javascript:suggestOver(this);" ';
    suggest += 'onmouseout="javascript:suggestOut(this);" ';
    suggest += 'onclick="javascript:setSearch(' + '<ww:property value="objName"/>' + ',' + 'id<ww:property value="#routs.index"/>' + ');" ';
    suggest += 'onmousedown="javascript:setSearch(' + '<ww:property value="objName"/>' + ',' + 'id<ww:property value="#routs.index"/>' + ');" ';
    suggest += 'class="suggest_link">' + '<table style="scroll:auto;height:15;margin:0;" ><tr><td align="left" valign="top">' + '<ww:property value="key"/>' + '</td><td align="left">------</td>';
    suggest += '<td align="right">' + '<ww:property value="chineseName"/>' + '<input type="hidden" name="id<ww:property value="#routs.index"/>" value="<ww:property value="(value==null || value=='') ? key : value"/>">' + '</td></tr></table>' + '</div>';
    </ww:iterator>
    sss.innerHTML = suggest;
    if (sum < 1) {
        document.getElementById('search_suggest').style.display = 'none';
    }
  
</script>



