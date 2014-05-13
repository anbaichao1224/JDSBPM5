package net.itjds.usm2.service;


import java.lang.reflect.Constructor;
import java.util.Map;

import net.itjds.bpm.data.xmlproxy.EsbBeanAnnotation;
import net.itjds.bpm.data.xmlproxy.MapdaoBeanAnnotation;
import net.itjds.bpm.data.xmlproxy.manager.EsbBeanFactory;
import net.itjds.bpm.data.xmlproxy.manager.ExpressionTempBean;
import net.itjds.common.expression.function.AbstractFunction;
import net.itjds.common.util.ClassUtility;
import net.itjds.usm2.UsmProxy;
import net.itjds.usm2.db.UsmService;
import net.itjds.usm2.db.util.EsbUtil;


@EsbBeanAnnotation(
		id = "currUsmService", 
		name = "获取当前USMService", 
		expressionArr = "GetCurrUsmService(R(\"servicekey\"),R(\"esbkey\"))", 
		desc = "获取当前USMService", 
		dataType = "action"
)

public class GetCurrUsmService extends  AbstractFunction{
	
 public UsmService perform(String servicekey,String esbkey){
		UsmService service=null;
		if (servicekey==null){
			EsbBeanFactory factory = EsbBeanFactory.newInstance();
		   	Map esbBeanMap=factory.getIdMap();
		   	ExpressionTempBean bean= (ExpressionTempBean) esbBeanMap.get(esbkey);
		  try {
			Class clazz= ClassUtility.loadClass(bean.getMainClass());
			Constructor[] constructors = clazz.getConstructors();
			for(int k=0;k<constructors.length;k++){
				Constructor constructor=constructors[k];
				Class[] parameters=constructor.getParameterTypes();
				if (parameters.length==1 && UsmProxy.class.isAssignableFrom(parameters[0])){
					UsmProxy usmProxy=(UsmProxy) parameters[0].newInstance();
					servicekey=usmProxy.getServiceKey();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	
		service=(UsmService) EsbUtil.parExpression("$"+servicekey+"UsmService");
		
		return service;
	}

}
