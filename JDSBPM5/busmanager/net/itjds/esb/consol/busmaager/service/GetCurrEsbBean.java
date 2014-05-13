package net.itjds.esb.consol.busmaager.service;


import java.util.List;
import java.util.Map;




import net.itjds.bpm.data.xmlproxy.EsbBeanAnnotation;
import net.itjds.bpm.data.xmlproxy.MapdaoBeanAnnotation;
import net.itjds.bpm.data.xmlproxy.manager.EsbBean;
import net.itjds.bpm.data.xmlproxy.manager.EsbBeanFactory;
import net.itjds.common.expression.function.AbstractFunction;
import net.itjds.common.logging.Log;
import net.itjds.common.logging.LogFactory;

@EsbBeanAnnotation(id="currEsbBean",
		name="获取当前ESBBEAN对象",
		expressionArr="GetCurrEsbBean(R(\"uuid\"))",
		desc="获取当前ESBBEAN对象 ",
		dataType="action"
		)
	
public class GetCurrEsbBean extends AbstractFunction{
	protected transient static final Log log = LogFactory.getLog(
			"GetCurrPanelBean", GetCurrEsbBean.class);
	private String esbkey;
	public  EsbBean perform(String key) {
		
		 EsbBeanFactory factory=EsbBeanFactory.newInstance();
	
		return factory.getEsbBeanListBean().getEsbBeanMap().get(key);
	}
	
	
	

}
