<%@ page language="java" contentType="text/html;charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>    
<html>

<s:if test="hasActionErrors()">
<s:iterator value="actionErrors" id="error">
    <s:property value="error"/>
</s:iterator>
</s:if>
</html>