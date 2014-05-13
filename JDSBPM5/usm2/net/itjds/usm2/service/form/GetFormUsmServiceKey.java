package net.itjds.usm2.service.form;


import java.lang.reflect.Constructor;


import net.itjds.bpm.data.xmlproxy.EsbBeanAnnotation;
import net.itjds.bpm.data.xmlproxy.MapdaoBeanAnnotation;

import net.itjds.common.expression.function.AbstractFunction;
import net.itjds.common.util.ClassUtility;
import net.itjds.usm2.UsmProxy;
import net.itjds.usm2.db.util.ClassUtil;
import net.itjds.usm2.db.util.EsbUtil;


@EsbBeanAnnotation(
		id = "currFormUsmServiceKey", 
		name = "获取当前USMService", 
		expressionArr = "GetFormUsmServiceKey(R(\"path\"))", 
		desc = "获取当前USMService", 
		dataType = "action"
)

public class GetFormUsmServiceKey extends  AbstractFunction{
	
 public String perform(String path){
	 
	 Object parentObj =EsbUtil.parExpression(path.substring(0, path.lastIndexOf(".")));
	 String servicekey=null;
		String fieldName =path.substring(path.lastIndexOf(".")+1,path.length());
		String methodName = "get" + fieldName.substring(0, 1).toUpperCase()+ fieldName.substring(1);
		Class innerClazz = ClassUtil.findClassByKey(parentObj.getClass(), methodName);
			Constructor[] constructors = innerClazz.getConstructors();
			for(int k=0;k<constructors.length;k++){
				Constructor constructor=constructors[k];
				Class[] parameters=constructor.getParameterTypes();
				if (parameters.length==1 && UsmProxy.class.isAssignableFrom(parameters[0])){
					UsmProxy usmProxy=null;
					try {
						if (!parameters[0].isInterface()){
							usmProxy = (UsmProxy) parameters[0].newInstance();
						}else{
							String realUsmProxyName= parameters[0].getPackage().getName()+".proxy."+parameters[0].getSimpleName()+"Proxy";
							Class clazz=ClassUtility.loadClass(realUsmProxyName);
							Constructor[] usmconstructors = clazz.getConstructors();
							for(int j=0;j<usmconstructors.length;j++){
								Constructor usmconstructor=usmconstructors[j];
								if (usmconstructor.getParameterTypes().length==1){
									usmProxy=(UsmProxy) usmconstructor.newInstance(new Object[]{null});
								}
								
							}
							
						}
						
					} catch (Exception e) {
						e.printStackTrace();
					}
					servicekey=usmProxy.getServiceKey();
				}
			}
		
		
		
		return servicekey;
	}

}
