package net.itjds.esb.consol.busmaager.service;





import java.util.Map;

import net.itjds.bpm.data.xmlproxy.EsbBeanAnnotation;
import net.itjds.bpm.data.xmlproxy.manager.EsbBeanFactory;

import net.itjds.common.expression.function.AbstractFunction;
import net.itjds.common.logging.Log;
import net.itjds.common.logging.LogFactory;
import net.itjds.usm2.db.util.EsbUtil;

@EsbBeanAnnotation(id="currUSMTempBean",
		name="获取当前USMBEAN对象",
		expressionArr="GetCurrUSMTempBean(R(\"uuid\"),R(\"gridId\"))",
		desc="获取当前USMBEAN对象 ",
		dataType="action"
		)
	
public class GetCurrUSMTempBean extends AbstractFunction{
	protected transient static final Log log = LogFactory.getLog(
			"GetCurrPanelBean", GetCurrUSMTempBean.class);
	
	public  Object perform(String key,String gridId) {
	
			Map esbContextMap = EsbUtil.getContextMap();
			 Object obj=null;
		 if (gridId!=null&&!gridId.equals("")){
			 if (esbContextMap.containsKey(gridId)){
				   obj=  esbContextMap.get(gridId);
			  }else{
				  if (gridId.startsWith("$")){
					  obj=EsbUtil.parExpression(gridId);
					  esbContextMap.put(gridId, obj);
				 }else{
					 obj=this.getBean(gridId);
				 }
			  }
			 
			
		 }else{		
			obj=getBean(key);
		 }
		
	
		return  obj;
	}
	
	private Object getBean(String key){
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
