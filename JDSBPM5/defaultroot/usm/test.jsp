<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib uri="http://www.opensymphony.com/oscache" prefix="cache" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>


输出对象输出第一个酒店名称【   $Hotel.{name}   】
<s:property value="$Hotel.{name}" escape="true"/>
输出CODE==6000的酒店名称[  $Hotel.{? #this.code=='60000}[0].name  ]
<s:property value="$Hotel.{? #this.code=='60000}[0].name'" escape="true"/>
属性映射
<s:property value="$CurrTime.CurrTimeToS" escape="true"/>
方法调用
<s:property value="$CurrTime.getCurrTime()" escape="true"/>

