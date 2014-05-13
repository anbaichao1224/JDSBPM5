package net.itjds.esb.consol.busmaager.service;





import java.util.Map;

import net.itjds.bpm.data.xmlproxy.EsbBeanAnnotation;
import net.itjds.bpm.data.xmlproxy.MapdaoBeanAnnotation;

import net.itjds.bpm.data.xmlproxy.manager.EsbBeanFactory;

import net.itjds.common.expression.function.AbstractFunction;
import net.itjds.common.logging.Log;
import net.itjds.common.logging.LogFactory;
import net.itjds.usm2.db.util.EsbUtil;

@EsbBeanAnnotation(id="currBusBean",
		name="获取当前USMBEAN对象",
		expressionArr="GetCurrBusBean(R(\"uuid\"))",
		desc="获取当前USMBEAN对象 ",
		dataType="action"
		)
	
public class GetCurrBusBean extends AbstractFunction{
	protected transient static final Log log = LogFactory.getLog(
			"GetCurrPanelBean", GetCurrBusBean.class);
	public Object perform(String key){
		 Object obj=null;
		 EsbBeanFactory factory=EsbBeanFactory.newInstance();
		 if (factory.getEsbBeanListBean().getEsbBeanMap().containsKey(key)){
			 obj= factory.getEsbBeanListBean().getEsbBeanMap().get(key);
		 }else{
			  obj=factory.getIdMap().get(key);
		 }
		 return obj;
	}
	
	
	

}
