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
		   //很明显该类是一个单例,先获取产生DocumentBuilder工厂
		   //的工厂,在通过这个工厂产生一个DocumentBuilder,
		   //DocumentBuilder就是用来产生Document的
		   DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
		   DocumentBuilder db=dbf.newDocumentBuilder();  
		   //这个Document就是一个XML文件在内存中的镜像
		   InputStream inputStream = ReadXmlAction.class.getClassLoader().getResourceAsStream(xmlFile);
		   doc=db.parse(inputStream);
		}
		//该方法负责把XML文件的内容显示出来
		public String viewXML(String wname) throws Exception{
			String xmlFile = "word/bookmarkxml.xml";
		   this.init(xmlFile);
		   //在xml文件里,只有一个根元素,先把根元素拿出来看看
		   String bookmarks = "";
		   if(wname!=null){
			   wname = wname.substring(wname.indexOf("$")+1, wname.lastIndexOf("."));
		   }
		   NodeList nodeList=doc.getElementsByTagName(wname);
		  if(nodeList!=null){
		   Node fatherNode=nodeList.item(0);
		   //把父节点的属性拿出来
		   NamedNodeMap attributes=fatherNode.getAttributes();
		   NodeList childNodes = fatherNode.getChildNodes();
		   for(int j=0;j<childNodes.getLength();j++){
		    Node childNode=childNodes.item(j);
		    //如果这个节点属于Element ,再进行取值
		    if(childNode instanceof Element){
		     bookmarks += childNode.getFirstChild().getNodeValue()+",";
		    }
		   }
		  
		  }
		  bookmarks = bookmarks.substring(0, bookmarks.lastIndexOf(","));
		  return bookmarks;
		}
		
		
}
