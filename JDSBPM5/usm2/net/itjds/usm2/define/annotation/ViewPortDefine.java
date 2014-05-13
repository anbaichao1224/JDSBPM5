package net.itjds.usm2.define.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import net.itjds.j2ee.dao.MethodChinaName;
import net.itjds.usm2.define.mapping.ViewPortBean;

import net.itjds.bpm.data.xmlproxy.manager.ClassMappingAnnotation;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@ClassMappingAnnotation(clazz=ViewPortBean.class)
public @interface ViewPortDefine {

	@MethodChinaName(cname = "标题")
	String title() default "";
	
	@MethodChinaName(cname = "顶级节点id")
	String topNodeId() default "";
	
	@MethodChinaName(cname = "布局")
	String layout() default "border";
	
	@MethodChinaName(cname = "默认窗口模板")
	String ftlUrl()default "main.ftl";
	
}
