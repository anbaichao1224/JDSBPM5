<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
	<br style="line-height:30%">
	<span class="txt">&nbsp;&nbsp;<font size="4">共${pageObj.totalCount}条记录&nbsp;&nbsp;第${pageObj.currPageNumber}/${pageObj.lastPageNumber}页 &nbsp;</font></span>
	<c:choose>
		<c:when test="${pageObj.currPageNumber == pageObj.firstPageNumber}">
			<input class="button" type="button" value="首页" disabled="disabled"/>
		</c:when>
		<c:otherwise>
			<input class="button" type="submit" name="goToPage" onclick="this.form.pageNo.value=${pageObj.firstPageNumber};" value="首页" />
		</c:otherwise>
	</c:choose>
	
	<c:choose>
		<c:when test="${pageObj.currPageNumber <= pageObj.firstPageNumber}">
			<input class="button" type="button" value="上一页" disabled="disabled"/>
		</c:when>
		<c:otherwise>
			<input class="button" type="submit" name="goToPage" onclick="this.form.pageNo.value=${pageObj.previousPageNumber };" value="上一页"/>
		</c:otherwise>
	</c:choose>
	
	<c:choose>
		<c:when test="${pageObj.currPageNumber >= pageObj.lastPageNumber}">
			<input class="button" type="button" value="下一页" disabled="disabled"/>
		</c:when>
		<c:otherwise>
			<input class="button" type="submit" name="goToPage" onclick="this.form.pageNo.value=${pageObj.nextPageNumber };" value="下一页"/>
		</c:otherwise>
	</c:choose>
	
	<c:choose>
		<c:when test="${pageObj.currPageNumber == pageObj.lastPageNumber}">
			<input class="button" type="button" value="末页" disabled="disabled"/>
		</c:when>
		<c:otherwise>
			<input class="button" type="submit" name="goToPage" onclick="this.form.pageNo.value=${pageObj.lastPageNumber };" value="末页"/>
		</c:otherwise>
	</c:choose>
	
	<c:if test="${param.toPage == 1}">
	<input id="toPage" class="button" type="submit" name="goToPage" onclick="this.form.pageNo.value=this.form._toPageNo.value;" value="跳至"/>第<input type="text" size="2" name="_toPageNo" value="${pageObj.currPageNumber }" onkeydown="if (event.keyCode==13) toPage.click();" />页	
	</c:if>
	
	<p/>

	<c:if test="${param.showCount == 1}">
	共查到 ${pageObj.totalCount} 条记录。 第${pageObj.currPageNumber}/${pageObj.lastPageNumber}页。
	</c:if>
	<p/>