<%@ page contentType="text/html;charset=gb2312" language="java"%>
<%@include file="../shared/inc/App_Variable.jsp"%>
<%
//ȡ�õ�ǰ·���������APP_ROOT_PATH��
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
<title>�ļ��ϴ�</title>
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
//����ƶ���ͼƬ��
function over(the,imgsrc){
	the.className="img_over";
	the.src='toolbar/images/'+imgsrc+'_over.gif';
}
//����Ƴ�ͼƬ��
function out(the,imgsrc){
	the.className="img";
	the.src='toolbar/images/'+imgsrc+'.gif';
}
//�����ͼƬ�ϰ���
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
		<TD width=22><img src="toolbar/images/toup.gif" class="img" onmousedown="down(this,'toup');" onmouseout="out(this,'toup');" onmouseover="over(this,'toup');" onmouseup="over(this,'toup');" border=1 onclick="window.history.go(-1)" alt="����"></TD>
		<TD width=6 align=center><img src="toolbar/images/tiao.gif"></TD>
		<TD width=4></TD>
		<TD width=46><nobr>�ϴ���:</TD>
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
	&nbsp;&nbsp;<b><font color=red><%=upload.uploadFileCount%></font></b> ���ļ��ϴ��ɹ���<br>
	&nbsp;&nbsp;������������1M����<br>
	&nbsp;&nbsp;<input type='button' value=' �� �� ' class=bt onclick="window.history.go(-1)">
	&nbsp;&nbsp;<input type='button' value=' �� �� ' class=bt onclick="window.close()">
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

        //�����趨�����ϴ����ļ���С            
        try{
            ContentFactory holder=new ContentFactory();
			holder.setContentFactory(request,(1024*1024));

            if (holder==null)
            {
                //���ܲ��� multipart/form-data ��ʽ���룬���������֧��
                out.println("��ȷ��ҳ���б��ı���Ϊ multipart/form-data ��ʽ");
                out.println("��ȷ�ϣ���������������֧�ָ��ֱ��뷽ʽ");
                return;
            }
            Enumeration fields=holder.getFileParameterNames();
            Enumeration names=holder.getParameterNames();                
            
			String NamesStr="<table border=0 align=center width=100% cellpadding=0 cellspacing=0>\n";
			NamesStr+="<tr>\n";
			NamesStr+=" <td width=40% >�ֶ���</td>\n";
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
			FilesStr+=" <td width=20% ><nobr>�ֶ���</td>\n";
			FilesStr+=" <td width=35% ><nobr>Content-Type</td>";
			FilesStr+=" <td width=35% ><nobr>�ļ���</td>\n";
			FilesStr+=" <td width=60 ><nobr>�ļ���С</td>\n</tr>\n";			

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
            out.println("���ص�����̫��");
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
/**��ű������ݵ��࣬�ṩ������ ServletRequest �еĲ��� get ����
*������� html ҳ��Ĵ���(form)��ָ�� enctype="multipart/form-data"��
*�ſ�����ȷ��ʹ�������
**/
public class ContentFactory 
{
    //��Ա����
    private Hashtable values;        //���name=value������value�������һ������
    private Hashtable files;           //����ļ����ݵġ�

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
     *����һ�� FileHolder ʵ������ʵ��������ͨ���ֶ���Ϊ name �� file �ؼ����ص��ļ���Ϣ��
     *�������������ֶλ����ύҳ��ʱ��û��ѡ�����ص��ļ����򷵻� null��
     * <p>��� Html ҳ���д��ڲ�ֹһ���ֶ���Ϊ name �� file �ؼ���
     * ����ֵ����  {@link #getFileParameterValues} �еĵ�һ��Ԫ�ء�
     *
     * @param name 	һ�� <code>String</code> ����Ӧ�� Html ҳ�洰���� file �ؼ�
     *��name ���ԡ�
     *
     * @return		����һ�� FileHolder ʵ������ʵ��������ͨ���ֶ���Ϊ name �� file �ؼ����ص��ļ���Ϣ��
     *�������������ֶλ����ύҳ���ǣ�û��ѡ�����ص��ļ����򷵻� null��
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
     * ����һ�� �� String ���󹹳ɵ� Enumeration �������� Html ҳ��
     *���������� file �ؼ��� name ���ԡ�
     *���������û�� file �ؼ����򷵻�һ���յ� Enumeration
     * @return		     ����һ�� �� String ���󹹳ɵ� Enumeration �������� Html ҳ��
     *���������� file �ؼ��� name ���ԡ�
     *���������û�� file �ؼ����򷵻�һ���յ� Enumeration
     */
    public Enumeration getFileParameterNames() 
    {
        return files.keys();
    }
   /**
     *����һ�� FileHolder ���飬���������������ͨ���ֶ���Ϊ name �� file �ؼ����ص��ļ���Ϣ��
     *�������������ֶλ����ύҳ��ʱ��û��ѡ���κ����ص��ļ����򷵻�һ�� ��Ԫ�ص����飨���� null )��
     * @param name 	һ�� <code>String</code> ����Ӧ�� Html ҳ�洰���� file �ؼ�
     *��name ���ԡ�
     *
     * @return		����һ�� FileHolder ���飬���������������ͨ���ֶ���Ϊ name �� file �ؼ����ص��ļ���Ϣ��
     *�������������ֶλ����ύҳ��ʱ��û��ѡ���κ����ص��ļ����򷵻�һ�� ��Ԫ�ص����飨���� null )��
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
    //------------->Factory ����
    /**
    **���ظ��ݵ�ǰ�������ɵ�һ�� ContentFactory ʵ��
    **@param request �ύ������
    **@return ���ظ��ݵ�ǰ�������ɵ�һ�� ContentFactory ʵ������� request
    ���ݰ������ݲ����� mutilpart/form-data �ͱ��룬�򷵻� null��
    **@throws ContentFactoryException ���ύ�����ݺ��ļ�̫��ʱ�׳���
    **���� Content-Length �жϣ�Ĭ�ϵ����ֵΪ 1024* 1024��
    **/
    //public ContentFactory getContentFactory(HttpServletRequest request) throws ContentFactoryException,IOException
	public void setContentFactory(HttpServletRequest request) throws ContentFactoryException,IOException
    {
        // default maxLength is 1MB.
        setContentFactory(request,1024*1024);
    }
        /**
    **���ظ��ݵ�ǰ�������ɵ�һ�� ContentFactory ʵ��
    **@param request �ύ������
    **@param maxLength ���ݰ�����󳤶ȣ�Ĭ��Ϊ1024*1024
    **@return ���ظ��ݵ�ǰ�������ɵ�һ�� ContentFactory ʵ������� request
    ���ݰ������ݲ����� mutilpart/form-data �ͱ��룬�򷵻� null��
    **@throws ContentFactoryException ���ύ�����ݺ��ļ�̫��ʱ�׳���
    **���� Content-Length �жϣ�Ĭ�ϵ����ֵΪ 1024* 1024��
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
            ContentFactoryException e=new ContentFactoryException("�ϴ�����̫�࣬�벻Ҫѡ��̫����ļ�");
            throw e;
        }
        if(contentType==null || !contentType.startsWith("multipart/form-data")) { 
            return;
			//return null;
        }
        //get out the boudary from content-type
        int start=contentType.indexOf("boundary=");
        //����Ӧ��
        int boundaryLen=new String("boundary=").length();
        String boundary=contentType.substring(start+boundaryLen);
        boundary="--"+boundary;
        //���ֽڱ�ʾ������ String  �� byte ����ĳ��Ȳ�һ��
        boundaryLen=bytesLen(boundary);

        //��request �е����ݶ���һ��byte����
        byte buffer[] = new byte[contentLength]; 
        int once = 0; 
        int total = 0; 
        DataInputStream in = new DataInputStream(request.getInputStream()); 
        while ((total<contentLength) && (once>=0)) { 
            once = in.read(buffer,total,contentLength); 
            total += once; 
        }
        //��buffer�е����ݽ��в��
        int pos1=0;                  //pos1 ��¼ ��buffer ����һ�� boundary ��λ��
        //pos0,pos1 ���� subBytes ����������
        int   pos0=byteIndexOf(buffer,boundary,0);   //pos0 ��¼ boundary �ĵ�һ���ֽ���buffer �е�λ��
        do
        {
            pos0+=boundaryLen;                                 //��¼boundary�����һ���ֽڵ��±�
            pos1=byteIndexOf(buffer,boundary,pos0);
            if (pos1==-1)
                break;
            //
            pos0+=2;          //���ǵ�boundary����� \r\n

            parse(subBytes(buffer,pos0,pos1-2),values,files);      //���ǵ�boundary����� \r\n
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
            //����tokens �еĵڶ���Ԫ�أ�˵���Ǹ��ļ����ݶ�
            //1.�õ��ֶ���
            String name =subBytesString(buffer,position[0]+bytesLen(tokens[0]),position[1]);
            //2.�õ��ļ���
            String file= subBytesString(buffer,position[1]+bytesLen(tokens[1]),position[2]);
            if (file.equals("")) return;
            file=new File(file).getName();     //this is the way to get the name from a path string
            //3.�õ� Content-Type
            String contentType=subBytesString(buffer,position[3]+bytesLen(tokens[3]),position[4]);
           //4.�õ��ļ�����
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
            //ͬʱ�� name ���Ժ� file ������Ϊ��ͨ�ֶΣ�����values;
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
            //������tokens �еĵڶ���Ԫ�أ�˵���Ǹ� name/value �͵����ݶ�
            //����û��tokens[1]�� tokens[3]
            //name �� tokens[0] �� tokens[2] ֮��
            //value �� tokens[4]֮��
            //1.�õ�name
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
   /**�ֽ������е� indexof �������� String ���е� indexOf����
    **@para source Դ�ֽ�����
    **@para search Ŀ���ַ���
    **@para start ��������ʼ��
    **@return ����ҵ�������search�ĵ�һ���ֽ���buffer�е��±꣬û���򷵻�-1
    **/
    private int byteIndexOf (byte[] source,String search,int start)
    {
        return byteIndexOf(source,search.getBytes(),start);
    }
    
   /**�ֽ������е� indexof �������� String ���е� indexOf����
    **@para source Դ�ֽ�����
    **@para search Ŀ���ֽ�����
    **@para start ��������ʼ��
    **@return ����ҵ�������search�ĵ�һ���ֽ���buffer�е��±꣬û���򷵻�-1
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
    // ��source���ҵ�search�ĵ�һ��Ԫ��
    searchForFirst:
        for (i=start;i<=max ; i++)
        {
            if (source[i]==search[0])
            {
                //�ҵ���search�еĵ�һ��Ԫ�غ󣬱Ƚ�ʣ��Ĳ����Ƿ����
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
    **���ڴ�һ���ֽ���������ȡһ���ֽ�����
    **������ String ���substring()
    **/
    private byte[] subBytes(byte[] source,int from,int end)
    {
        byte[] result=new byte[end-from];
        System.arraycopy(source,from,result,0,end-from);
        return result;
    }
    /**
    **���ڴ�һ���ֽ���������ȡһ���ַ���
    **������ String ���substring()
    **/
    private String subBytesString(byte[] source,int from,int end)
    {
        return new String(subBytes(source,from,end));
    }
    /**
    **�����ַ���Sת��Ϊ�ֽ������ĳ���
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
**�����ص�����̫��ʱ�׳���
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
/**һ������ļ���Ϣ���࣬�����ļ������ƣ� String ����
**�ֶ����� String ���� Content-Type��String�� �����ݣ�byte[]��
**���ṩ��һ��ֱ�ӽ��ļ����ݱ��浽һ���ļ��ĺ��� void saveTo(File f)
**���Ե��� ��{@link ContentFactory}�е��ʵ����������ɸ����ʵ����
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
    /**���ļ������ݴ浽ָ�����ļ��У�
    **<b>�����������������ļ��Ƿ��д���Ƿ��Ѿ����ڡ�</b>
    **@param file  Ŀ���ļ�
    **@throws �� I/O �����б��׳��� IOException
    **/
    public void saveTo(File file) throws IOException
    {
        BufferedOutputStream out=new BufferedOutputStream(new FileOutputStream(file));
        out.write(buffer);
        out.close();
    }
    /**���ļ������ݴ浽ָ�����ļ��У�
    **<b>�����������������ļ��Ƿ��д���Ƿ��Ѿ����ڡ�</b>
    **@param name Ŀ���ļ���
    **@throws �� I/O �����б��׳��� IOException
    **/
    public void saveTo(String name) throws IOException
    {
        saveTo(new File(name));
    }
   /**
    **����һ���ļ����ݵ��ֽ�����
    **@return һ�������ļ����ݵ��ֽ�����
   **/
    public byte[] getBytes()
    {
        return buffer;    
    }
   /**
    **���ظ��ļ����ļ�����ǰ�ڿͻ��˵�����
    **@return ���ļ����ļ�����ǰ�ڿͻ��˵�����
   **/
    public String getFileName()
    {
        return fileName;
    }

   /**
    **���ظ��ļ���С���ߴ磩
    **@return ���ظ��ļ���С���ߴ磩
   **/
    public long getFileSize()
    {
        return fileSize;
    }
    
   /**
    **���ظ��ļ��� Content-Type
    **@return ���ļ��� Content-Type
   **/
    public String getContentType()
    {
        return contentType;
    }
   /**
    **�������ظ��ļ�ʱ��Html ҳ�洰���� file �ؼ��� name ����
    **@return �������ظ��ļ�ʱ��Html ҳ�洰���� file �ؼ��� name ����
   **/ 
    public String getParameterName()
    {
        return parameterName;
    }
}
%>