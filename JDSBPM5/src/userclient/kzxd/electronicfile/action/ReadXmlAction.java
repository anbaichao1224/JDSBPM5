package kzxd.electronicfile.action;

import java.io.File;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import net.itjds.userclient.usm.action.downLoadElectronicfileAction;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ReadXmlAction {
	private Document doc=null;


	public void init(String xmlFile) throws Exception{
		   //�����Ը�����һ������,�Ȼ�ȡ����DocumentBuilder����
		   //�Ĺ���,��ͨ�������������һ��DocumentBuilder,
		   //DocumentBuilder������������Document��
		   DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
		   DocumentBuilder db=dbf.newDocumentBuilder();  
		   //���Document����һ��XML�ļ����ڴ��еľ���
		   InputStream inputStream = ReadXmlAction.class.getClassLoader().getResourceAsStream(xmlFile);
		   doc=db.parse(inputStream);
		}
		//�÷��������XML�ļ���������ʾ����
		public String viewXML(String wname) throws Exception{
			String xmlFile = "word/bookmarkxml.xml";
		   this.init(xmlFile);
		   //��xml�ļ���,ֻ��һ����Ԫ��,�ȰѸ�Ԫ���ó�������
		   String bookmarks = "";
		   if(wname!=null){
			   wname = wname.substring(wname.indexOf("$")+1, wname.lastIndexOf("."));
		   }
		   NodeList nodeList=doc.getElementsByTagName(wname);
		  if(nodeList!=null){
		   Node fatherNode=nodeList.item(0);
		   //�Ѹ��ڵ�������ó���
		   NamedNodeMap attributes=fatherNode.getAttributes();
		   NodeList childNodes = fatherNode.getChildNodes();
		   for(int j=0;j<childNodes.getLength();j++){
		    Node childNode=childNodes.item(j);
		    //�������ڵ�����Element ,�ٽ���ȡֵ
		    if(childNode instanceof Element){
		     bookmarks += childNode.getFirstChild().getNodeValue()+",";
		    }
		   }
		  
		  }
		  bookmarks = bookmarks.substring(0, bookmarks.lastIndexOf(","));
		  return bookmarks;
		}
		
		
}
