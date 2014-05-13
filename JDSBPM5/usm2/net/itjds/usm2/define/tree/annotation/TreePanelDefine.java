package net.itjds.usm2.define.tree.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import net.itjds.j2ee.dao.MethodChinaName;
import net.itjds.usm2.define.annotation.ButtonDefine;
import net.itjds.usm2.define.annotation.SelectItem;
import net.itjds.usm2.define.data.annotation.AjaxDataDefine;
import net.itjds.usm2.define.enums.ElementButton;
import net.itjds.usm2.define.enums.ElementCheckModel;
import net.itjds.usm2.define.tree.mapping.TreePanelBean;


import net.itjds.bpm.data.xmlproxy.manager.ClassMappingAnnotation;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@ClassMappingAnnotation(clazz=TreePanelBean.class)
public @interface TreePanelDefine {
	@MethodChinaName(cname = "类型")
	String xtype() default "";
	
	@MethodChinaName(cname = "树的名称")
	String name() default "";
	
	@MethodChinaName(cname = "动画效果")
	@SelectItem(values = { "true", "false" }, texts = { "是", "否" })
	boolean animate() default false;

	@MethodChinaName(cname = "标题")
	String title() default "";

	@MethodChinaName(cname = "显示区域")
	String el() default "";

	@MethodChinaName(cname = "样式")
	String bodyStyle() default "";

	@MethodChinaName(cname = "宽度")
	int width() default 0;

	@MethodChinaName(cname = "高度")
	int height() default 0;

	@MethodChinaName(cname = "指示线")
	@SelectItem(values = { "true", "false" }, texts = { "是", "否" })
	boolean lines() default true;

	@MethodChinaName(cname = "边框")
	@SelectItem(values = { "true", "false" }, texts = { "是", "否" })
	boolean border() default false;

	@MethodChinaName(cname = "勾选模式")
	@SelectItem(values = { "multiple", "single", "cascade" }, texts = { "多选","单选", "级联多选" })
	ElementCheckModel checkModel() default ElementCheckModel.single;

	@MethodChinaName(cname = "节点可勾选")
	@SelectItem(values = { "true", "false" }, texts = { "是", "否" })
	boolean onlyLeafCheckable() default false;

	@MethodChinaName(cname = "显示根节点")
	@SelectItem(values = { "true", "false" }, texts = { "是", "否" })
	boolean rootVisible() default false;

	@MethodChinaName(cname = "自动显示滚动条")
	@SelectItem(values = { "true", "false" }, texts = { "是", "否" })
	boolean autoScroll() default true;

	@MethodChinaName(cname = "节点加载器")
	String loader() default "loader";

	@MethodChinaName(cname = "根节点")
	String root() default "root";
	@MethodChinaName(cname = "数据加载")
	TreeLoaderDefine treeLoaderBean();
	@MethodChinaName(cname = "数据节点")
	TreeNodeDefine treeNodeBean();
	
	TreeListener[] listeners();
	@MethodChinaName(cname = "默认窗口模板")
	String ftlUrl()default "tree.ftl";
	
	@MethodChinaName(cname = "动作按钮")
	ButtonDefine[] buttons() default {};
	
	@MethodChinaName(cname = "修改操作")
	AjaxDataDefine updateData()default @AjaxDataDefine(url="");
	
	@MethodChinaName(cname = "删除操作")
	AjaxDataDefine deleteData()default @AjaxDataDefine(url="");
}
