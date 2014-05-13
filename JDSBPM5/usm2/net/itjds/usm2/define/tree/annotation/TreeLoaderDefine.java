package net.itjds.usm2.define.tree.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import net.itjds.j2ee.dao.MethodChinaName;
import net.itjds.usm2.define.annotation.SelectItem;
import net.itjds.usm2.define.tree.mapping.TreeLoaderBean;

import net.itjds.bpm.data.xmlproxy.manager.ClassMappingAnnotation;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@ClassMappingAnnotation(clazz=TreeLoaderBean.class)
public @interface TreeLoaderDefine {
	@MethodChinaName(cname = "TreeLoader唯一名称")
	String name() default "";
	
	@MethodChinaName(cname = "设置子节点的基本属性对象")
	String baseAttrs() default "{uiProvider: Ext.tree.TreeCheckNodeUI}";
	
	@MethodChinaName(cname = "设置基本的请求参数")
	String baseParams() default "";
	
	@MethodChinaName(cname = "获取子节点的URL地址")
	String dataUrl() default "";
	
	@MethodChinaName(cname = "获取子节点的URL地址")
	String url() default "";
	
	@MethodChinaName(cname = "设置加载前是否移除已存在的子节点")
	@SelectItem(values = { "true", "false" }, texts = { "是", "否" })
	boolean clearOnload() default true;
	
	@MethodChinaName(cname = "设置在第一次加载子节点后是否递归加载所有子节点")
	@SelectItem(values = { "true", "false" }, texts = { "是", "否" })
	boolean preloadChildren() default false;
	
	@MethodChinaName(cname = "设置请求方式")
	String requestMethod() default "POST";
	
	@MethodChinaName(cname = "设置加载器创建子节点的UI实现类")
	String uiProviders() default "";
}
