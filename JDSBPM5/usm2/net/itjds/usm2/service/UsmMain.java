package net.itjds.usm2.service;

import java.io.IOException;

import freemarker.template.TemplateException;


import net.itjds.bpm.data.xmlproxy.EsbBeanAnnotation;
import net.itjds.bpm.data.xmlproxy.MapdaoBeanAnnotation;

import net.itjds.common.expression.function.AbstractFunction;
import net.itjds.common.logging.Log;
import net.itjds.common.logging.LogFactory;
import net.itjds.usm2.define.mapping.PanelBean;
@EsbBeanAnnotation(id="usmMain",
		name="视图控制器",
		expressionArr="UsmMain(R(\"ftl\"),$currViewBean(),R(\"panelname\"))",
		desc="视图控制器将VIEWPORT对象转换为",
		dataType="action"
		)
	
public class UsmMain extends AbstractFunction{
	protected transient static final Log log = LogFactory.getLog(
			"UsmMain", UsmMain.class);
	
	public String  perform(String ftlurl,PanelBean panelBean,String panelname) {
	
		if (ftlurl==null||ftlurl.equals("")){
			ftlurl=panelBean.getFtlUrl();
		}else{
			panelBean.setFtlUrl(ftlurl);
		}
		
		panelBean.setId(panelname==null?"panel"+Long.toString(System.currentTimeMillis()):panelname);
		panelBean.setName(panelname==null?"panel"+Long.toString(System.currentTimeMillis()):panelname);
		String str=null;
		try {
			str = panelBean.getExtStr();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TemplateException e) {
			e.printStackTrace();
		}
		return str;	
		
	}
	
	

}
