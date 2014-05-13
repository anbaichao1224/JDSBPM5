<%@ page contentType="text/html;charset=gb2312" language="java"%>
<%@include file="../shared/inc/App_Variable.jsp"%>
<%
//取得当前路径（相对于APP_ROOT_PATH）
try{
	APP_CUR_PATH = request.getParameter("CurPath");
}catch(Exception e){
	APP_CUR_PATH = "";
}
if(APP_CUR_PATH!=null){
	APP_CUR_PATH=APP_CUR_PATH.trim();
}else{
	APP_CUR_PATH = "";
}
APP_CUR_PATH = new String(APP_CUR_PATH.getBytes("ISO8859_1"),"GBK");
%>

<html>
<head>
<title>文件上传</title>
<meta HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=gb2312">

<!-- no cache headers -->
<META content=no-cache http-equiv=Pragma>
<META http-equiv=no-cache>
<META content=-1 http-equiv=Expires>
<META content=no-cache http-equiv=Cache-Control>
<!-- end no cache headers -->

<LINK REL="stylesheet" TYPE="text/css" HREF="<%=SYS_BASE_PATH%>treeview/shared/css/ie4.css" />
<LINK REL="stylesheet" TYPE="text/css" HREF="<%=SYS_BASE_PATH%>treeview/shared/css/ie5.css" />
<LINK REL="stylesheet" TYPE="text/css" HREF="<%=SYS_BASE_PATH%>treeview/shared/css/ie.css" />
<style>
BODY
{
	font-family:verdana,arial,helvetica;
	background-color:buttonface;
	margin:0;
	border:0;
}
input {height:17px;font-size:12px}
.tx1 { height: 18px; font-size: 9pt; border: 1px solid; border-color: black black #000000; color: #0000FF}
-->
</style>

</head>

<SCRIPT LANGUAGE="JavaScript">
<!--
//鼠标移动到图片上
function over(the,imgsrc){
	the.className="img_over";
	the.src='toolbar/images/'+imgsrc+'_over.gif';
}
//鼠标移出图片上
function out(the,imgsrc){
	the.className="img";
	the.src='toolbar/images/'+imgsrc+'.gif';
}
//鼠标在图片上按下
function down(the,imgsrc){
	the.className="img_down";
	//the.src='toolbar/images/'+imgsrc+'_down.gif';
}

function opener_refresh(){
	window.opener.refresh();
}
//-->
</SCRIPT>

<body scroll=no onload="opener_refresh()">
<table align=center WIDTH="100%" CELLPADDING="0" CELLSPACING="0" BORDER="1" bordercolor=buttonface bordercolorlight="#808080" bordercolordark="#FFFFFF">
  <TR height=22>
	<TD>
	<!--# ToolBar the First Line Start-->
	<TABLE CELLPADDING="0" CELLSPACING="0" BORDER="0">
	  <TR>
		<TD width=2></TD>
		<TD width=3><img src="toolbar/images/toolbar.gif"></TD>
		<TD width=4></TD>
		<TD width=4><nobr><%=SYS_NAME%> <%=SYS_VER%> </TD>
		<TD width=6 align=center><img src="toolbar/images/tiao.gif"></TD>		
	  </TR>
	</TABLE>
	<!--# ToolBar the First Line End-->
	</TD>
  </TR>
 <TR height=22>
 <TR>
	<TD>

	<TABLE CELLPADDING="0" CELLSPACING="0" BORDER="0" width="100%">
	  <TR valign="middle" height=22>
		<TD width=2></TD>
		<TD width=3><img src="toolbar/images/toolbar.gif"></TD>
		<TD width=22><img src="toolbar/images/toup.gif" class="img" onmousedown="down(this,'toup');" onmouseout="out(this,'toup');" onmouseover="over(this,'toup');" onmouseup="over(this,'toup');" border=1 onclick="window.history.go(-1)" alt="后退"></TD>
		<TD width=6 align=center><img src="toolbar/images/tiao.gif"></TD>
		<TD width=4></TD>
		<TD width=46><nobr>上传到:</TD>
		<TD width=2></TD>
		<TD>
		  <div style="border:2 inset white;width:100%; height:22px;background-color:#FFFFFF">
		  <TABLE CELLPADDING="0" CELLSPACING="0" BORDER="0" width="100%" height="100%">
		  <TR>
		  	<TD width=1></TD>
			<TD width=18><img src="toolbar/images/ie.gif"></TD>
		  	<TD id=URL valign="bottom"><input type="text" value="http://<%=request.getServerName()%>:<%=request.getServerPort()%><%=APP_BASE_PATH+APP_CUR_PATH%>" style="border:0;width:400px;height:16px;font-size:9pt"></TD>
		  </TR>
		  </TABLE>
		  </div>
		</TD>
		<TD width=1></TD>
	  </TR>
	</TABLE>

	</TD>
  </TR>
<tr>
 <td bgcolor="#F1F0F1" height=342 valign="top">
  <%
	UpLoad upload=new UpLoad();
	upload.PATHSTRING=APP_REAL_PATH;
	upload.URLSTRING=APP_BASE_PATH+APP_CUR_PATH;
	upload.doPost(request,response);
	%>
	<br>
	&nbsp;&nbsp;<b><font color=red><%=upload.uploadFileCount%></font></b> 个文件上传成功！<br>
	&nbsp;&nbsp;数据总量限于1M以内<br>
	&nbsp;&nbsp;<input type='button' value=' 返 回 ' class=bt onclick="window.history.go(-1)">
	&nbsp;&nbsp;<input type='button' value=' 关 闭 ' class=bt onclick="window.close()">
  <hr size=2 width="100%">
  <div align=center>
    &copy; 2001-2003 COPY BY <a href="mailto:Yoinn@163.com">YOINN</a> ALL RIGHTS RESERVE<br>
	HOMEPAGE: <a href="http://www.3may.net/zl/" target="_blank">http://www.3may.net/zl/</a>
	&nbsp;&nbsp;EMAIL: <a href="mailto:Yoinn@21cn.com">@21cn</a> or <a href="mailto:Yoinn@163.com">@163</a>
  </div>
 </td>
</tr>
</table>
</body>
</html>
<%!public class UpLoad
{ 
    public String PATHSTRING="F:\\zl.jsp/myjsp/upload/files/"; 
    public String URLSTRING="/upload/files/";
	public int uploadFileCount=0;
                                                           
    public void doPost(HttpServletRequest request,HttpServletResponse response) 
            throws IOException, ServletException
    { 
        response.setContentType("text/html;charset=gb2312");
        PrintWriter out = response.getWriter();

        //这里设定允许上传的文件大小            
        try{
            ContentFactory holder=new ContentFactory();
			holder.setContentFactory(request,(1024*1024));

            if (holder==null)
            {
                //可能不是 multipart/form-data 形式编码，或浏览器不支持
                out.println("请确认页面中表单的编码为 multipart/form-data 形式");
                out.println("如确认，可能你的浏览器不支持该种编码方式");
                return;
            }
            Enumeration fields=holder.getFileParameterNames();
            Enumeration names=holder.getParameterNames();                
            
			String NamesStr="<table border=0 align=center width=100% cellpadding=0 cellspacing=0>\n";
			NamesStr+="<tr>\n";
			NamesStr+=" <td width=40% >字段名</td>\n";
			NamesStr+=" <td width=60% >Value</td>\n";
			NamesStr+="</tr>\n";
            if(names.hasMoreElements())
            {
                //String n=(String)names.nextElement();
                //String[] values=holder.getParameterValues(n);
                //for(int i=0;i<values.length;i++)
                //{
                    NamesStr+="<tr>\n";
					NamesStr+=" <td>path</td>\n";
					NamesStr+=" <td>"+holder.getParameter("filepath")+"</td>\n";
					NamesStr+="<tr>\n";
                //}                
            }
            NamesStr+="</table>";

			PATHSTRING+=holder.getParameter("filepath");
			URLSTRING=holder.getParameter("filepath");

			String FilesStr="<table border=1 align=center width=100%  cellpadding=2 cellspacing=0>\n";
			FilesStr+="<tr>\n";
			FilesStr+=" <td width=20% ><nobr>字段名</td>\n";
			FilesStr+=" <td width=35% ><nobr>Content-Type</td>";
			FilesStr+=" <td width=35% ><nobr>文件名</td>\n";
			FilesStr+=" <td width=60 ><nobr>文件大小</td>\n</tr>\n";			

            while(fields.hasMoreElements())
            {                    
                String field=(String)fields.nextElement();
                FileHolder[] file=holder.getFileParameterValues(field);

                for (int i=0;i<file.length ;i++ )
                {
                    uploadFileCount++;
					String fileName=file[i].getFileName();
                    File f=new File(PATHSTRING+fileName);
                    file[i].saveTo(f);
                    FilesStr+="<tr>\n";
					FilesStr+=" <td>"+file[i].getParameterName()+ "</td>\n";
                    FilesStr+=" <td>"+file[i].getContentType()+"</td>";
                    FilesStr+=" <td><a href=\""+URLSTRING+fileName+"\" target='_blank'>"+fileName+"</td>";
					FilesStr+=" <td>"+file[i].getFileSize()+"</td>\n</tr>\n";
                }

            }
            FilesStr+="</table>";
            
            out.println(FilesStr);
        }catch(ContentFactoryException e)
        {
            out.println("上载的数据太多");
        }
    }
}

%>
<%@ page import="java.io.*"%>
<%@ page import="java.util.*"%>
<%!

//@filename ContentFactory.java
//package upload;
//import java.io.*;
//import javax.servlet.*;
//import javax.servlet.http.*;
//import java.util.*;
/**存放报文内容的类，提供类似于 ServletRequest 中的部分 get 方法
*你必须在 html 页面的窗体(form)中指定 enctype="multipart/form-data"。
*才可以正确的使用这个类
**/
public class ContentFactory 
{
    //成员变量
    private Hashtable values;        //存放name=value，其中value存放在另一个类中
    private Hashtable files;           //存放文件内容的。

    private ContentFactory()
    {
        this.values=new Hashtable();
        this.files=new Hashtable();
    }

	private ContentFactory(Hashtable values,Hashtable files)
    {
        this.values=values;
        this.files=files;
    }
   /**
     * Returns the value of a request parameter as a <code>String</code>,
     * or <code>null</code> if the parameter does not exist. 
     * parameters are contained in the posted form data.
     *
     *<p>
     * <b>This method can only be used with a from encoding by multipart/form-data
     *To deal with an normal encoding form, you may use the same method declared
     *in interface ServletRequest. </b>
     *
     * <p>You should only use this method when you are sure the
     * parameter has only one value. If the parameter might have
     * more than one value, use {@link #getParameterValues}.
     *
     * <p>If you use this method with a multivalued
     * parameter, the value returned is equal to the first value
     * in the array returned by <code>getParameterValues</code>.
     *
     * @param name 	a <code>String</code> specifying the 
     *			name of the parameter
     *
     * @return		a <code>String</code> representing the 
     *			single value of the parameter, 
     *if the input type is file, then the filename will returned.
     * @see 		#getParameterValues
     *
     */
    public String getParameter(String name)
    {
        Vector v=(Vector)values.get(name);
        if(v!=null)
            return (String)v.elementAt(0);
        return null;
    }
    /**
     *
     * Returns an <code>Enumeration</code> of <code>String</code>
     * objects containing the names of the parameters contained
     * in this request. If the request has 
     * no parameters, the method returns an 
     * empty <code>Enumeration</code>. 
     *
     *<p>
     * <b>This method can only be used with a from encoding by multipart/form-data
     * To deal with an normal encoding form, you may use the same method declared
     *in interface ServletRequest. </b>
     *
     * @return		an <code>Enumeration</code> of <code>String</code>
     *			objects, each <code>String</code> containing
     * 			the name of a request parameter; or an 
     *			empty <code>Enumeration</code> if the
     *			request has no parameters 
     *
     */
    public Enumeration getParameterNames()
    {
        return values.keys();
    }
    /**
     * Returns an array of <code>String</code> objects containing 
     * all of the values the given request parameter has, or 
     * <code>null</code> if the parameter does not exist.
     *
     *<p>
     * <b>This method can only be used with a from encoding by multipart/form-data</b>
     * <b>To deal with an normal encoding form, you may use the same method declared
     *in interface ServletRequest. </b>
     *
     * <p>If the parameter has a single value, the array has a length
     * of 1.
     *
     * @param name	a <code>String</code> containing the name of 
     *			the parameter whose value is requested
     *
     * @return		an array of <code>String</code> objects 
     *			containing the parameter's values
     *
     * @see		#getParameter
     *
     */
    public String[] getParameterValues(String name)
    {
        Vector v=(Vector)values.get(name);
        if(v!=null)
        {
            String[] result=new String[v.size()];
            v.toArray(result);
            return result;
        }
            
        return new String[0];
    }

   /**
     *返回一个 FileHolder 实例，该实例包含了通过字段名为 name 的 file 控件上载的文件信息，
     *如果不存在这个字段或者提交页面时，没有选择上载的文件，则返回 null。
     * <p>如果 Html 页面中存在不止一个字段名为 name 的 file 控件，
     * 返回值等于  {@link #getFileParameterValues} 中的第一个元素。
     *
     * @param name 	一个 <code>String</code> ，对应于 Html 页面窗体中 file 控件
     *的name 属性。
     *
     * @return		返回一个 FileHolder 实例，该实例包含了通过字段名为 name 的 file 控件上载的文件信息，
     *如果不存在这个字段或者提交页面是，没有选择上载的文件，则返回 null。
     *
     * @see 		#getFileParameterValues
     *
     */
    public FileHolder getFileParameter(String name)
    {
        Vector v=(Vector)files.get(name);
        if(v!=null) return (FileHolder)v.elementAt(0);
        return null;
    }
    /**
     * 返回一个 由 String 对象构成的 Enumeration ，包含了 Html 页面
     *窗体中所有 file 控件的 name 属性。
     *如果窗体中没有 file 控件，则返回一个空的 Enumeration
     * @return		     返回一个 由 String 对象构成的 Enumeration ，包含了 Html 页面
     *窗体中所有 file 控件的 name 属性。
     *如果窗体中没有 file 控件，则返回一个空的 Enumeration
     */
    public Enumeration getFileParameterNames() 
    {
        return files.keys();
    }
   /**
     *返回一个 FileHolder 数组，该数组包含了所有通过字段名为 name 的 file 控件上载的文件信息，
     *如果不存在这个字段或者提交页面时，没有选择任何上载的文件，则返回一个 零元素的数组（不是 null )。
     * @param name 	一个 <code>String</code> ，对应于 Html 页面窗体中 file 控件
     *的name 属性。
     *
     * @return		返回一个 FileHolder 数组，该数组包含了所有通过字段名为 name 的 file 控件上载的文件信息，
     *如果不存在这个字段或者提交页面时，没有选择任何上载的文件，则返回一个 零元素的数组（不是 null )。
     *
     * @see 		#getFileParameter
     */
    public FileHolder[] getFileParameterValues(String name)
    {
        Vector v=(Vector)files.get(name);
        if(v!=null)
        {
            FileHolder[] result=new FileHolder[v.size()];
            v.toArray(result);
            return result;
        }
        return new FileHolder[0];
    }
    //------------->Factory 部分
    /**
    **返回根据当前请求生成的一个 ContentFactory 实例
    **@param request 提交的请求
    **@return 返回根据当前请求生成的一个 ContentFactory 实例，如果 request
    数据包的内容不是以 mutilpart/form-data 型编码，则返回 null。
    **@throws ContentFactoryException 当提交的数据和文件太大时抛出，
    **根据 Content-Length 判断，默认的许可值为 1024* 1024。
    **/
    //public ContentFactory getContentFactory(HttpServletRequest request) throws ContentFactoryException,IOException
	public void setContentFactory(HttpServletRequest request) throws ContentFactoryException,IOException
    {
        // default maxLength is 1MB.
        setContentFactory(request,1024*1024);
    }
        /**
    **返回根据当前请求生成的一个 ContentFactory 实例
    **@param request 提交的请求
    **@param maxLength 数据包的最大长度，默认为1024*1024
    **@return 返回根据当前请求生成的一个 ContentFactory 实例，如果 request
    数据包的内容不是以 mutilpart/form-data 型编码，则返回 null。
    **@throws ContentFactoryException 当提交的数据和文件太大时抛出，
    **根据 Content-Length 判断，默认的许可值为 1024* 1024。
    **/
    //public static ContentFactory getContentFactory(HttpServletRequest request,int maxLength) throws ContentFactoryException,IOException
	public void setContentFactory(HttpServletRequest request,int maxLength) throws ContentFactoryException,IOException
    {
		Hashtable values=new Hashtable();
        Hashtable files=new Hashtable();
        String contentType=request.getContentType();
        int contentLength = request.getContentLength(); 
        
        if (contentLength>maxLength)
        {
            ContentFactoryException e=new ContentFactoryException("上传数据太多，请不要选择太大的文件");
            throw e;
        }
        if(contentType==null || !contentType.startsWith("multipart/form-data")) { 
            return;
			//return null;
        }
        //get out the boudary from content-type
        int start=contentType.indexOf("boundary=");
        //这里应该
        int boundaryLen=new String("boundary=").length();
        String boundary=contentType.substring(start+boundaryLen);
        boundary="--"+boundary;
        //用字节表示，以免 String  和 byte 数组的长度不一致
        boundaryLen=bytesLen(boundary);

        //把request 中的数据读入一个byte数组
        byte buffer[] = new byte[contentLength]; 
        int once = 0; 
        int total = 0; 
        DataInputStream in = new DataInputStream(request.getInputStream()); 
        while ((total<contentLength) && (once>=0)) { 
            once = in.read(buffer,total,contentLength); 
            total += once; 
        }
        //对buffer中的数据进行拆分
        int pos1=0;                  //pos1 记录 在buffer 中下一个 boundary 的位置
        //pos0,pos1 用于 subBytes 的两个参数
        int   pos0=byteIndexOf(buffer,boundary,0);   //pos0 记录 boundary 的第一个字节在buffer 中的位置
        do
        {
            pos0+=boundaryLen;                                 //记录boundary后面第一个字节的下标
            pos1=byteIndexOf(buffer,boundary,pos0);
            if (pos1==-1)
                break;
            //
            pos0+=2;          //考虑到boundary后面的 \r\n

            parse(subBytes(buffer,pos0,pos1-2),values,files);      //考虑到boundary后面的 \r\n
            pos0=pos1;  
        }while(true);

		this.values=values;
        this.files=files;

        //return new ContentFactory(values,files);
    }
    private void parse(byte[] buffer,Hashtable values,Hashtable files)
    {
            /* this is a smiple to parse
            [boundary] 
            Content-Disposition: form-data; name="file3"; filename="C:\Autoexec.bat"
            Content-Type: application/octet-stream

            @echo off
            prompt $d $t [ $p ]$_$$

            [boundary]
            Content-Disposition: form-data; name="Submit"

            Submit
            [boundary]
            */
        String[] tokens={"name=\"","\"; filename=\"", "\"\r\n","Content-Type: ","\r\n\r\n"};
           //                          0           1                               2          3                         4
        int[] position=new int[tokens.length];

        for (int i=0;i<tokens.length ;i++ )
        {
            position[i]=byteIndexOf(buffer,tokens[i],0);
        }
        if (position[1]>0 && position[1]<position[2])
        {
            //包含tokens 中的第二个元素，说明是个文件数据段
            //1.得到字段名
            String name =subBytesString(buffer,position[0]+bytesLen(tokens[0]),position[1]);
            //2.得到文件名
            String file= subBytesString(buffer,position[1]+bytesLen(tokens[1]),position[2]);
            if (file.equals("")) return;
            file=new File(file).getName();     //this is the way to get the name from a path string
            //3.得到 Content-Type
            String contentType=subBytesString(buffer,position[3]+bytesLen(tokens[3]),position[4]);
           //4.得到文件内容
            byte[] b=subBytes(buffer,position[4]+bytesLen(tokens[4]),buffer.length);
            FileHolder f=new FileHolder(b,contentType,file,name);
            Vector v=(Vector)files.get(name);
            if (v==null)
            {
                v=new Vector();
            }
            if (!v.contains(f))
            {
                v.add(f);                    
            }
            files.put(name,v);
            //同时将 name 属性和 file 属性作为普通字段，存入values;
            v=(Vector)values.get(name);
            if (v==null)
            {
                v=new Vector();
            }
            if (!v.contains(file))
            {
                v.add(file);
            }
            values.put(name,v);
        }else
        {
//            String[] tokens={"name=\"","\"; filename=\"", "\"\r\n","Content-Type: ","\r\n\r\n"}
//             index                      0           1                               2          3                         4
            //不包含tokens 中的第二个元素，说明是个 name/value 型的数据段
            //所以没有tokens[1]和 tokens[3]
            //name 在 tokens[0] 和 tokens[2] 之间
            //value 在 tokens[4]之后
            //1.得到name
            String name =subBytesString(buffer,position[0]+bytesLen(tokens[0]),position[2]);
            String value= subBytesString(buffer,position[4]+bytesLen(tokens[4]),buffer.length);
            Vector v=(Vector)values.get(name);
            if (v==null)
            {
                v=new Vector();
            }
            if (!v.contains(value))
            {
                v.add(value);
            }
            values.put(name,v);
        }
    }
   /**字节数组中的 indexof 函数，与 String 类中的 indexOf类似
    **@para source 源字节数组
    **@para search 目标字符串
    **@para start 搜索的起始点
    **@return 如果找到，返回search的第一个字节在buffer中的下标，没有则返回-1
    **/
    private int byteIndexOf (byte[] source,String search,int start)
    {
        return byteIndexOf(source,search.getBytes(),start);
    }
    
   /**字节数组中的 indexof 函数，与 String 类中的 indexOf类似
    **@para source 源字节数组
    **@para search 目标字节数组
    **@para start 搜索的起始点
    **@return 如果找到，返回search的第一个字节在buffer中的下标，没有则返回-1
    **/
    private int byteIndexOf (byte[] source,byte[] search,int start)
    {
        int i;
        if (search.length==0)
        {
            return 0;
        }
        int max=source.length-search.length;
        if (max<0)
            return -1;
        if (start>max)
            return -1;
        if (start<0)
            start=0;
    // 在source中找到search的第一个元素
    searchForFirst:
        for (i=start;i<=max ; i++)
        {
            if (source[i]==search[0])
            {
                //找到了search中的第一个元素后，比较剩余的部分是否相等
                int k=1;
                while(k<search.length)
                {
                    if (source[k+i]!=search[k])
                    {
                        continue searchForFirst;
                    }
                    k++;
                }
                return i;
            }
        }
        return -1;
    }
    /**
    **用于从一个字节数组中提取一个字节数组
    **类似于 String 类的substring()
    **/
    private byte[] subBytes(byte[] source,int from,int end)
    {
        byte[] result=new byte[end-from];
        System.arraycopy(source,from,result,0,end-from);
        return result;
    }
    /**
    **用于从一个字节数组中提取一个字符串
    **类似于 String 类的substring()
    **/
    private String subBytesString(byte[] source,int from,int end)
    {
        return new String(subBytes(source,from,end));
    }
    /**
    **返回字符串S转换为字节数组后的长度
    **/
    private int bytesLen(String s)
    {
        return s.getBytes().length;
    }
}

///////////////////////////////////////////////////////////////

//ContentFactoryException.java
//package upload;
/**
**在上载的内容太多时抛出。
**/
public class ContentFactoryException extends Exception 
{
    ContentFactoryException()
    {
        super();
    }
    ContentFactoryException(String s)
    {
        super(s);
    }
}

///////////////////////////////////////////////////////////////

//FileHolder.java
//package upload;
//import java.io.*;
/**一个存放文件信息的类，包括文件的名称（ String ），
**字段名（ String ）， Content-Type（String） 和内容（byte[]）
**还提供了一个直接将文件内容保存到一个文件的函数 void saveTo(File f)
**可以调用 类{@link ContentFactory}中的适当方法，生成该类的实例。
** @see 		ContentFactory
** @see ContentFactory#getFileParameter
** @see ContentFactory#getFileParameterValues
**/
public class FileHolder 
{
    String contentType;
    byte[] buffer;
    String fileName;
	long fileSize;
    String parameterName;
    FileHolder(byte[] buffer,String contentType,String fileName,String parameterName)
    {
        this.buffer=buffer;
        this.contentType=contentType;
        this.fileName=fileName;
		this.fileSize=buffer.length;
        this.parameterName=parameterName;
    }
    /**把文件的内容存到指定的文件中，
    **<b>这个方法不会检查这个文件是否可写、是否已经存在。</b>
    **@param file  目的文件
    **@throws 在 I/O 操作中被抛出的 IOException
    **/
    public void saveTo(File file) throws IOException
    {
        BufferedOutputStream out=new BufferedOutputStream(new FileOutputStream(file));
        out.write(buffer);
        out.close();
    }
    /**把文件的内容存到指定的文件中，
    **<b>这个方法不会检查这个文件是否可写、是否已经存在。</b>
    **@param name 目的文件名
    **@throws 在 I/O 操作中被抛出的 IOException
    **/
    public void saveTo(String name) throws IOException
    {
        saveTo(new File(name));
    }
   /**
    **返回一个文件内容的字节数组
    **@return 一个代表文件内容的字节数组
   **/
    public byte[] getBytes()
    {
        return buffer;    
    }
   /**
    **返回该文件在文件上载前在客户端的名称
    **@return 该文件在文件上载前在客户端的名称
   **/
    public String getFileName()
    {
        return fileName;
    }

   /**
    **返回该文件大小（尺寸）
    **@return 返回该文件大小（尺寸）
   **/
    public long getFileSize()
    {
        return fileSize;
    }
    
   /**
    **返回该文件的 Content-Type
    **@return 该文件的 Content-Type
   **/
    public String getContentType()
    {
        return contentType;
    }
   /**
    **返回上载该文件时，Html 页面窗体中 file 控件的 name 属性
    **@return 返回上载该文件时，Html 页面窗体中 file 控件的 name 属性
   **/ 
    public String getParameterName()
    {
        return parameterName;
    }
}
%>