package net.itjds.usm2.define.cartlayoutpanel.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import net.itjds.j2ee.dao.MethodChinaName;
import net.itjds.usm2.define.cartlayoutpanel.mapping.CartItemsBean;

import net.itjds.bpm.data.xmlproxy.manager.ClassMappingAnnotation;
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@ClassMappingAnnotation(clazz=CartItemsBean.class)
public @interface CartItemsDefine {
	
	@MethodChinaName(cname = "标题")
	String title() default "";

	@MethodChinaName(cname = "内容")
	String html() default "";
	

	@MethodChinaName(cname = "宽度")
	double width() default 800;

	@MethodChinaName(cname = "高度")
	double height() default 600;	


}
