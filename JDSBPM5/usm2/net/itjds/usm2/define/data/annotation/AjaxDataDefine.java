package net.itjds.usm2.define.data.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


import net.itjds.j2ee.dao.MethodChinaName;
import net.itjds.usm2.define.data.mapping.AjaxDataBean;

import net.itjds.bpm.data.xmlproxy.manager.ClassMappingAnnotation;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@ClassMappingAnnotation(clazz=AjaxDataBean.class)
public @interface AjaxDataDefine {
	@MethodChinaName(cname = "Url 地址")
	String url() default "/expression.jsp" ;
	@MethodChinaName(cname = "打开页面总线值")
	String viewId() default "" ;
	@MethodChinaName(cname = "表达式")
	String expression() default "" ;
	@MethodChinaName(cname = "参数")
	HttpBaseParams[] params() default {};
	WinConfigDefine winConfig() default @WinConfigDefine();
}
