package net.itjds.usm2.define.tree.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import net.itjds.j2ee.dao.MethodChinaName;
import net.itjds.usm2.define.annotation.SelectItem;
import net.itjds.usm2.define.tree.mapping.TreeNodeBean;

import net.itjds.bpm.data.xmlproxy.manager.ClassMappingAnnotation;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@ClassMappingAnnotation(clazz=TreeNodeBean.class)
public @interface TreeNodeDefine {
	
	@MethodChinaName(cname = "设置是否允许当前节点具有子节点")
	@SelectItem(values = { "true", "false" }, texts = { "是", "否" })
	boolean allowChildren() default true;
	
	@MethodChinaName(cname = "查询条件")
	String where() default "";
	
	@MethodChinaName(cname = "")
	String servicekey() default "";
	
	@MethodChinaName(cname = "设置当前节点的选中状态")
	@SelectItem(values = { "true", "false" }, texts = { "是", "否" })
	boolean checked() default true;
	
	@MethodChinaName(cname = "添加到节点的样式")
	String cls() default "";
	
	@MethodChinaName(cname = "节点是否禁用")
	@SelectItem(values = { "true", "false" }, texts = { "是", "否" })
	boolean disabled() default false;
	
	@MethodChinaName(cname = "设置当不含子节点时是否显示加减图标")
	@SelectItem(values = { "true", "false" }, texts = { "是", "否" })
	boolean expandable() default false;
	
	@MethodChinaName(cname = "设置是否展开节点")
	@SelectItem(values = { "true", "false" }, texts = { "是", "否" })
	boolean expanded() default false;
	
	@MethodChinaName(cname = "设置节点的链接属性")
	String href() default "#";
	
	@MethodChinaName(cname = "设置节点的链接的目标框架")
	String hrefTarget() default "";
	
	@MethodChinaName(cname = "设置节点图标对应的路径")
	String icon() default "";
	
	@MethodChinaName(cname = "设置节点图标的样式")
	String iconCls() default "";
	
	@MethodChinaName(cname = "设置节点上的提示信息")
	String qtip() default "";
	
	@MethodChinaName(cname = "设置是否通过单击方式展开节点")
	@SelectItem(values = { "true", "false" }, texts = { "是", "否" })
	boolean singleClickExpand() default true;
	
	@MethodChinaName(cname = "设置节点上的文本信息")
	String text() default "";
	
	@MethodChinaName(cname = "ID")
	String id() default "";
	@MethodChinaName(cname = "panelName")
	String panelName() default "";
	
	@MethodChinaName(cname = "URL参数名称")
	String parameterName() default "";
}
