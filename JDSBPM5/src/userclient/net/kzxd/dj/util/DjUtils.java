package net.kzxd.dj.util;

import java.io.IOException;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

public class DjUtils {

	public static String parseXML(String xmlmodel) {
		StringBuffer jsonradio = new StringBuffer();
		SAXBuilder builder = new SAXBuilder();
		try {
			Document document = builder
					.build("E:\\work\\workspace\\JDSBPM4\\src\\userclient\\swdj.xml");
			Element root = document.getRootElement();
			Element msyk = root.getChild(xmlmodel);
			List list = msyk.getChildren("pmodel");
			for (int i = 0; i < list.size(); i++) {
				Element e = (Element) list.get(i);
				Element e1 = root.getChild(e.getValue());
				if (e1 != null) {
					jsonradio.append("{boxLabel:'"
							+ e1.getChild("pname").getValue()
							+ "',name:'process',inputValue:'"
							+ e1.getChild("pid").getValue() + "'}");
				}
				if(i != list.size() - 1) {
					jsonradio.append(",");
				}
			}

		} catch (JDOMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return jsonradio.toString();
	}

	public static void main(String[] rags) {
		String radio = parseXML("dj-msyk");
	}
}
