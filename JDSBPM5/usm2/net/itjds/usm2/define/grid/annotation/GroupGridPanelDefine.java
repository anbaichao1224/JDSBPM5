package net.itjds.usm2.define.grid.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import net.itjds.j2ee.dao.MethodChinaName;
import net.itjds.usm2.define.annotation.ButtonDefine;
import net.itjds.usm2.define.annotation.SelectItem;
import net.itjds.usm2.define.data.annotation.AjaxDataDefine;
import net.itjds.usm2.define.grid.mapping.GridPanelBean;

import net.itjds.bpm.data.xmlproxy.manager.ClassMappingAnnotation;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@ClassMappingAnnotation(clazz = GridPanelBean.class)
public @interface GroupGridPanelDefine {
	@MethodChinaName(cname = "表格标题")
	String title() default "";
	
	@MethodChinaName(cname = "表格样式")
	String bodyStyle() default "";
	
	@MethodChinaName(cname = "保存排序")
	AjaxDataDefine saveSort() default  @AjaxDataDefine(url="") ;
	
	@MethodChinaName(cname = "重新生成")
	AjaxDataDefine rebuild() default  @AjaxDataDefine(url="") ;
	
	@MethodChinaName(cname = "动作按钮")
	ButtonDefine[] buttons() default {};
	
	@MethodChinaName(cname = "自动充满表格未用空间的列")
	String autoExpandColumn() default "";

	@MethodChinaName(cname = "自动扩充列的最大宽度")
	int autoExpandMax() default 1000;

	@MethodChinaName(cname = "自动扩充列的最小宽度")
	int autoExpandMin() default 50;

	@MethodChinaName(cname = "设置表格的列模式")
	String cm() default "";

	@MethodChinaName(cname = "设置表格的列模式")
	String colModel() default "";

	@MethodChinaName(cname = "设置表格的宽度")
	double width() default 800;

	@MethodChinaName(cname = "设置表格的高度")
	double height() default 600;

	@MethodChinaName(cname = "是否禁止表格选择")
	@SelectItem(values = { "true", "false" }, texts = { "是", "否" })
	boolean disableSelection() default false;

	@MethodChinaName(cname = "是否允许通过标题中的上下文菜单隐藏列")
	@SelectItem(values = { "true", "false" }, texts = { "是", "否" })
	boolean enableColumnHide() default true;

	@MethodChinaName(cname = "是否允许拖放表格列")
	@SelectItem(values = { "true", "false" }, texts = { "是", "否" })
	boolean enableColumnMove() default true;

	@MethodChinaName(cname = "是否允许改变表格列宽")
	@SelectItem(values = { "true", "false" }, texts = { "是", "否" })
	boolean enableColumnResize() default true;

	@MethodChinaName(cname = "是否允许拖放表格中的行")
	@SelectItem(values = { "true", "false" }, texts = { "是", "否" })
	boolean enalbeDragDrop() default false;

	@MethodChinaName(cname = "是否允许显示表头中的上下文菜单")
	@SelectItem(values = { "true", "false" }, texts = { "是", "否" })
	boolean enableHdMenu() default true;

	@MethodChinaName(cname = "是否同步行高")
	@SelectItem(values = { "true", "false" }, texts = { "是", "否" })
	boolean enableRowHeightSync() default true;

	@MethodChinaName(cname = "表格的最大高度")
	int maxHeight() default 50;

	@MethodChinaName(cname = "表格列的最小宽度")
	int minColumnWidth() default 25;

	@MethodChinaName(cname = "表格的选择模式")
	String sm() default "";// 数组

	@MethodChinaName(cname = "表格的选择模式")
	String selModel() default "";

	@MethodChinaName(cname = "表格的数据集")
	String store() default "store";
	
	
	 String groupField();

	@MethodChinaName(cname = "查询条件")
	SearchItemDefine[] searchBeans() default {};

	@MethodChinaName(cname = "表格是否隔行换色")
	@SelectItem(values = { "true", "false" }, texts = { "是", "否" })
	boolean stripeRows() default false;

	@MethodChinaName(cname = "是否高亮显示鼠标所在行")
	@SelectItem(values = { "true", "false" }, texts = { "是", "否" })
	boolean trackMouseOver() default true;

	@MethodChinaName(cname = "默认窗口模板")
	String ftlUrl() default "groupgrid.ftl";
	
	AjaxDataDefine loadData() default @AjaxDataDefine(
			url="expression.jsp?expression=$JSONGroupGrid"
	); 
	
	AjaxDataDefine editRowData() default @AjaxDataDefine(
			url="expression.jsp?expression=$currFormPanel"
	); 
	AjaxDataDefine addRowData() default @AjaxDataDefine(
			url="expression.jsp?expression=$currFormPanel"
	); 
	AjaxDataDefine deleteRowData() default @AjaxDataDefine(
			url="expression.jsp?expression=$deleteGridRow"
	); 
	
	

	@MethodChinaName(cname = "字段排序")
	String[] fieldsIndex()default{}; 
	@MethodChinaName(cname = "每页显示条数")
	int pageSize()default 20; 

}
