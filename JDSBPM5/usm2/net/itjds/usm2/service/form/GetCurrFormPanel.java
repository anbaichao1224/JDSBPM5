package net.itjds.usm2.service.form;

import java.io.IOException;

import freemarker.template.TemplateException;


import net.itjds.bpm.data.xmlproxy.EsbBeanAnnotation;
import net.itjds.bpm.data.xmlproxy.MapdaoBeanAnnotation;

import net.itjds.common.expression.function.AbstractFunction;
import net.itjds.common.logging.Log;
import net.itjds.common.logging.LogFactory;

import net.itjds.usm2.define.mapping.PanelBean;
@EsbBeanAnnotation(id="currFormPanel",
		name="弹出窗口表单定义转换",
		expressionArr="GetCurrFormPanel($currFormPanelBean())",
		desc="弹出窗口表单定义转换 ",
		dataType="action"
		)
	
public class GetCurrFormPanel extends AbstractFunction{
	protected transient static final Log log = LogFactory.getLog(
			"gridEditPanel", GetCurrFormPanel.class);
	
	public String  perform(PanelBean panelBean) {
	
		panelBean.setId("panel"+Long.toString(System.currentTimeMillis()));
		panelBean.setName("panel"+Long.toString(System.currentTimeMillis()));
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
