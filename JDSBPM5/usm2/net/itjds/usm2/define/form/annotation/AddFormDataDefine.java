package net.itjds.usm2.define.form.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


import net.itjds.j2ee.dao.MethodChinaName;
import net.itjds.usm2.define.annotation.ButtonDefine;
import net.itjds.usm2.define.data.annotation.HttpBaseParams;
import net.itjds.usm2.define.form.mapping.AddFormDataBean;

import net.itjds.bpm.data.xmlproxy.manager.ClassMappingAnnotation;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@ClassMappingAnnotation(clazz=AddFormDataBean.class)
public @interface AddFormDataDefine {
	@MethodChinaName(cname = "Url µØÖ·")
	String panelClazz()default"" ;
	
	@MethodChinaName(cname = "²ÎÊý")
	HttpBaseParams[] initparams() default {};
	
	
}
