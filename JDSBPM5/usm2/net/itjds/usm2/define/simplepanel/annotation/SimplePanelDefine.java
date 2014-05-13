package net.itjds.usm2.define.simplepanel.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import net.itjds.j2ee.dao.MethodChinaName;
import net.itjds.usm2.define.enums.ElementPanelLayout;
import net.itjds.usm2.define.simplepanel.mapping.SimplePanelBean;

import net.itjds.bpm.data.xmlproxy.manager.ClassMappingAnnotation;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@ClassMappingAnnotation(clazz=SimplePanelBean.class)
public @interface SimplePanelDefine {
	@MethodChinaName(cname = "标题")
	String title() default "";
	
	@MethodChinaName(cname = "宽度")
	int width() default 800;

	@MethodChinaName(cname = "高度")
	int height() default 600;	
	
	@MethodChinaName(cname = "高度")
	String html() default "";	
	
	@MethodChinaName(cname = "布局方式")
	ElementPanelLayout layout() default ElementPanelLayout.fit;	
	
	@MethodChinaName(cname = "位置")
	String  region();
	
	@MethodChinaName(cname = "默认窗口模板")
	String ftlUrl()default "simplepanel.ftl";
	@MethodChinaName(cname = "样式")
	String bodyStyle() default "";	
	
}
