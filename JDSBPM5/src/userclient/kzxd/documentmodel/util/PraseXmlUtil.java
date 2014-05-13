package kzxd.documentmodel.util;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


/**
 * 
 * @author 李洋
 *	用于读取jdbc链接读取配置文件中的参数
 *
 */
public class PraseXmlUtil {
	
	public static String[] getContent(){
		String[] jdbccontent = new String[4];
		DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = null;
		try {
			docBuilder = docBuilderFactory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Document doc = null;
		try {
			try {
				doc = docBuilder.parse (new File("..\\conf\\jdbc_config.xml"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        doc.getDocumentElement ().normalize ();
        NodeList cfglist = doc.getElementsByTagName("jdbc");
		int configlength = cfglist.getLength();
		for(int i = 0; i < configlength; i++) {
			Node cfgnode = cfglist.item(i);
			if(cfgnode.getNodeType() == Node.ELEMENT_NODE){
				Element cfgelt = (Element)cfgnode;
                NodeList driverList = cfgelt.getElementsByTagName("driver");
                Element driverelt = (Element)driverList.item(0);
                NodeList driver = driverelt.getChildNodes();
                jdbccontent[0] = ((Node)driver.item(0)).getNodeValue().trim();
                NodeList urlList = cfgelt.getElementsByTagName("url");
                Element urlelt = (Element)urlList.item(0);
                NodeList url = urlelt.getChildNodes();
                jdbccontent[1] = ((Node)url.item(0)).getNodeValue().trim();
                NodeList usernameList = cfgelt.getElementsByTagName("username");
                Element usernameelt = (Element)usernameList.item(0);
                NodeList username = usernameelt.getChildNodes();
                jdbccontent[2] = ((Node)username.item(0)).getNodeValue().trim();
                NodeList passwordList = cfgelt.getElementsByTagName("password");
                Element passwordelt = (Element)passwordList.item(0);
                NodeList password = passwordelt.getChildNodes();
                jdbccontent[3] = ((Node)password.item(0)).getNodeValue().trim();
			}
		}
        return jdbccontent;
	}
}
