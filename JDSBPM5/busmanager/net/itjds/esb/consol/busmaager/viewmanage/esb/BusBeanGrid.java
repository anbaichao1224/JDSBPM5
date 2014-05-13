package net.itjds.esb.consol.busmaager.viewmanage.esb;


import java.util.List;

import net.itjds.bpm.data.xmlproxy.manager.BaseTableTempBean;
import net.itjds.bpm.data.xmlproxy.manager.ExpressionTempBean;
import net.itjds.bpm.data.xmlproxy.manager.MapDAOTempBean;
import net.itjds.bpm.data.xmlproxy.manager.USMTempBean;
import net.itjds.usm2.define.annotation.ButtonDefine;
import net.itjds.usm2.define.data.annotation.AjaxDataDefine;
import net.itjds.usm2.define.data.annotation.WinConfigDefine;
import net.itjds.usm2.define.enums.ElementButton;
import net.itjds.usm2.define.enums.ElementFieldType;
import net.itjds.usm2.define.grid.annotation.ColumnDefine;
import net.itjds.usm2.define.grid.annotation.GridPanelDefine;
import net.itjds.usm2.define.grid.annotation.SearchItemDefine;;

@GridPanelDefine(
		
		title = "资源列表",
		editRowData=@AjaxDataDefine(
				expression="$currFormPanel",
				viewId="currBusFormView",
				winConfig=@WinConfigDefine(width=500,height=400,title="模块信息")
		),
		
		buttons = { @ButtonDefine(text=ElementButton.query,iconCls="zoom",hidden = false,handler = "query()"),
					@ButtonDefine(text=ElementButton.rebuild,iconCls="mobility",hidden = false,handler = "rebuild()"),
					@ButtonDefine(text=ElementButton.serialindex,iconCls="serialindex",hidden = false,handler = "serialindex()"),
					@ButtonDefine(otherText="添加模块",text=ElementButton.createfield,iconCls="add",hidden = false,
							 ajax= @AjaxDataDefine(
			        					expression="$currFormPanel",
			        					viewId="currBusFormView",
			        					winConfig=@WinConfigDefine(width=500,height=400,title="添加模块")
			        			)
							),
							 @ButtonDefine(text=ElementButton.delete,iconCls="remove",hidden = false,handler = "remove()")
				         	   
        },
        rebuild = 	 @AjaxDataDefine(
				url="/update.action?expression=$UsmData.rebuild()"
		),
		fieldsIndex = { 
			"uuid",
			"title",
			"mainTableName",
			"esbkey", 
			"schema",
			"expressionArr",
		    "dataType",
		   "desc"

})
public class BusBeanGrid {
	public String mainTableName;

	public String packageName;

	public String esbkey;

	public String schema;

	public String expressionArr;

	public String dataType;

	public String desc;

	private ExpressionTempBean bean;

	public BusBeanGrid(Object bean) {
		this.bean = (ExpressionTempBean) bean;
	}

	
	
	
	  @ColumnDefine(
				mapping = "uuid",
				dataIndex = "uuid",
				header = "总线名称", 
				width=150,
				type = ElementFieldType.Hidden,			
				search = @SearchItemDefine(fieldLabel="总线名称",xtype=ElementFieldType.TextField,hidden=true)
		)
		public java.lang.String getUuid() {
			return bean.getId();
		}

		public void setUuid(java.lang.String uuid) {
			bean.setName(uuid);
		}
	@ColumnDefine(
			mapping = "title",
			dataIndex = "title",
			header = "总线名称", 
			width=150,
			type = ElementFieldType.TextField,			
			search = @SearchItemDefine(fieldLabel="总线名称",xtype=ElementFieldType.TextField,hidden=true)
	)
	public java.lang.String getTitle() {
		return bean.getName();
	}

	public void setTitle(java.lang.String title) {
		bean.setName(title);
	}


  @ColumnDefine(
			mapping = "mainTableName",
			dataIndex = "mainTableName",
			header = "对应主表", 
			width=100,
			type = ElementFieldType.TextField,			
			search = @SearchItemDefine(fieldLabel="总线名称",xtype=ElementFieldType.TextField,hidden=true)
	)
	public String getMainTableName() {
		if (bean != null) {
			if (bean instanceof USMTempBean) {
				mainTableName = ((USMTempBean) bean).getMaintablename();
			} else if (bean instanceof MapDAOTempBean) {
				List tableNames=((MapDAOTempBean) bean).getTableNames();
				if (tableNames==null ||tableNames.isEmpty()){
				}else{
					mainTableName = ((MapDAOTempBean) bean).getTableNames().get(0);
				}
				
				
			}
		}
		return mainTableName;
	}

	public void setMainTableName(String mainTableName) {
		this.mainTableName = mainTableName;
	}
	 @ColumnDefine(
				mapping = "esbkey",
				dataIndex = "esbkey",
				header = "总线注册标识", 
				width=100,
				type = ElementFieldType.TextField,			
				search = @SearchItemDefine(fieldLabel="总线名称",xtype=ElementFieldType.TextField,hidden=true)
		)
	public String getEsbkey() {
		return bean.getId();
	}

	public void setEsbkey(String esbkey) {
		this.esbkey = esbkey;
	}
	 @ColumnDefine(
				mapping = "expressionArr",
				dataIndex = "expressionArr",
				header = "加载方法", 
				width=150,
				type = ElementFieldType.TextField,			
				search = @SearchItemDefine(fieldLabel="加载方法",xtype=ElementFieldType.TextField,hidden=true)
		)
	public String getExpressionArr() {
		return bean.getExpressionArr();
	}

	public void setExpressionArr(String expressionArr) {
		this.expressionArr = expressionArr;
	}
	 @ColumnDefine(
				mapping = "dataType",
				dataIndex = "dataType",
				header = "加载类型", 
				width=100,
				type = ElementFieldType.TextField,			
				search = @SearchItemDefine(fieldLabel="加载类型",xtype=ElementFieldType.TextField,hidden=true)
		)
	public String getDataType() {
		return bean.getDataType();
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	 @ColumnDefine(
				mapping = "packageName",
				dataIndex = "packageName",
				header = "包路径", 
				width=200,
				type = ElementFieldType.TextField,			
				search = @SearchItemDefine(fieldLabel="包路径",xtype=ElementFieldType.TextField,hidden=true)
		)
	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	 @ColumnDefine(
				mapping = "schema",
				dataIndex = "schema",
				header = "数据库标识", 
				width=100,
				type = ElementFieldType.TextField,			
				search = @SearchItemDefine(fieldLabel="数据库标识",xtype=ElementFieldType.TextField,hidden=true)
		)
	public String getSchema() {

		if (bean instanceof BaseTableTempBean) {
			BaseTableTempBean tableBean = (BaseTableTempBean) bean;
			schema = tableBean.getSchema();
		}
		return schema;
	}

	public void setSchema(String schema) {
		this.schema = schema;
	}
		 @ColumnDefine(
				mapping = "desc",
				dataIndex = "desc",
				header = "描述", 
				width=250,
				type = ElementFieldType.TextField,			
				search = @SearchItemDefine(fieldLabel="描述",xtype=ElementFieldType.TextField,hidden=true)
		)

	public String getDesc() {
		return bean.getDesc();
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

}