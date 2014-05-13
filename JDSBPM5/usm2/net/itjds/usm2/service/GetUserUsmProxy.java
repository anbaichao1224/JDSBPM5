package net.itjds.usm2.service;

import net.itjds.bpm.data.xmlproxy.EsbBeanAnnotation;
import net.itjds.bpm.data.xmlproxy.MapdaoBeanAnnotation;
import net.itjds.common.expression.function.AbstractFunction;
import net.itjds.common.logging.Log;
import net.itjds.common.logging.LogFactory;
import net.itjds.common.util.ClassUtility;

@EsbBeanAnnotation(
		id = "newUsmProxy", 
		name = "获取当前USM对象", 
		expressionArr = "GetUserUsmProxy(R(\"clazz\"))", 
		desc = "获取当前USM对象", 
		dataType = "action"
)
public class GetUserUsmProxy extends AbstractFunction{
	protected transient static final Log log = LogFactory.getLog(
			"GetUserUsmProxy", GetUserUsmProxy.class);
	public Class perform(String className) {
		Class usmProxy=null;
		try {
			if (className!=null){
				usmProxy =  ClassUtility.loadClass(className);
			}
		
		} catch (Exception e) {
			log.warn("usmProxy ["+className+"] load err");
			//e.printStackTrace();
		}
		return usmProxy;
		
	}

}
