<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page
	import="java.util.Map,com.opensymphony.xwork2.util.OgnlValueStack"%>
<%@ taglib uri="/struts-tags" prefix="ww"%>
<%
response.setHeader("Pragma","No-cache"); 
response.setHeader("Cache-Control","no-cache"); 
response.setDateHeader("Expires", 0); 
String contextpath = request.getContextPath() + "/";
%> 	

{


 commentList:[
 
		
         
		 <ww:iterator value="commentList" status="rowstatus">
		    {
		     comment:'<ww:property value="commenttext" escape="false"/>',
		     formname:'<ww:property value="comment.formname" escape="false"/>',
		     creatdate:'<ww:property value="comment.createdate" escape="false"/>',
		     uuid:'<ww:property value="comment.uuid" escape="false"/>',
		     personname:'<ww:property value="person.name" escape="false"/>',
		     personname1:'<ww:property value="position" escape="false"/>',
		     proxyperson:'<ww:property value="comment.proxyperson" escape="false"/>',
		     isEdit:'<ww:property value="edit" escape="false"/>',
		     personid:'<ww:property value="person.ID" escape="false"/>'
		    }
		     <ww:if test ="#rowstatus.count < commentList.size">
		      , 
		      </ww:if>
		     </ww:iterator>   
		      <ww:if test ="commentList.size()>0 && commentList.{? #this.edit}.size()==0">
		      ,
		      </ww:if>
		   <ww:if test ="commentList.{? #this.edit}.size()==0 ">
		    
		    {
		     comment:'&nbsp;',
		     formname:'',
             //creatdate:new Date().toLocaleString(),
		     uuid:'',
		    // personname:'<ww:property value="$currPerson.name"/>',
		     proxyperson:'',
		      <ww:if test="activityInst.state!='ENDREAD' && activityInst.state!='READ'" >
		      isEdit:'true',
		      </ww:if>
		     personid:'<ww:property value="$currPerson.ID"/>'
		    }
		   </ww:if>  
		 ],
	index:"<ww:property value="$currPerson.index"/>",
	activityDefId:'<ww:property value="$currActivityInst.activityDef.activityDefId"/>'
}

