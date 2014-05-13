package net.itjds.esb.consol.busmaager.service;


import java.lang.reflect.InvocationTargetException;

import java.util.List;





import net.itjds.bpm.data.xmlproxy.EsbBeanAnnotation;

import net.itjds.common.expression.function.AbstractFunction;
import net.itjds.common.logging.Log;
import net.itjds.common.logging.LogFactory;

import net.itjds.usm2.define.mapping.FieldBean;
import net.itjds.usm2.define.mapping.PanelBean;
@EsbBeanAnnotation(id="currFieldBean",
		name="获取当前FieldBean字段属性",
		expressionArr="GetCurrFieldBean(R(\"gridId\"),GetCurrPanelBean(R(\"esbkey\"),R(\"uuid\")))",
		desc="获取当前FieldBean字段属性 ",
		dataType="action"
		)
	
public class GetCurrFieldBean extends AbstractFunction{
	protected transient static final Log log = LogFactory.getLog(
			"GetCurrPanelBean", GetCurrFieldBean.class);
	
	public FieldBean  perform(String key,PanelBean parentPanel) throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException {
		
		List<FieldBean> fieldList=parentPanel.getItems();
	
		for(int k=0;fieldList.size()>k;k++){
		
			if (fieldList.get(k).getId().equals(key)){
				return fieldList.get(k);
			}
		}
				return null;
		
	}
	
}
