package net.itjds.usm2.define.cartlayoutpanel.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import net.itjds.j2ee.dao.MethodChinaName;
import net.itjds.usm2.define.annotation.SelectItem;
import net.itjds.usm2.define.cartlayoutpanel.mapping.CartPanelBean;

import net.itjds.bpm.data.xmlproxy.manager.ClassMappingAnnotation;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@ClassMappingAnnotation(clazz=CartPanelBean.class)
public @interface CartPanelDefine {
	@MethodChinaName(cname = "类型")
	String xtype() default "cart";
	
	@MethodChinaName(cname = "宽度")
	double width() default 800;

	@MethodChinaName(cname = "高度")
	double height() default 600;
	
	@MethodChinaName(cname = "设置默认激活页")
	int activeItem() default 0;
	
	@MethodChinaName(cname = "标签超宽时自动出现滚动按钮")
	@SelectItem(values = { "true", "false" }, texts = { "是", "否" })
	boolean enableTabScroll() default true;
	
	@MethodChinaName(cname = "字段排序")
	String[] fieldsIndex()default{}; 
	@MethodChinaName(cname = "默认窗口模板")
	String ftlUrl()default "cart.ftl";
}
