package net.itjds.fdt.view.fdtoafwbl;

import net.itjds.common.util.DateUtility;
import net.itjds.usm2.define.data.annotation.AjaxDataDefine;
import net.itjds.usm2.define.data.annotation.HttpBaseParams;
import net.itjds.usm2.define.enums.*;
import net.itjds.usm2.define.annotation.ButtonDefine;
import net.itjds.usm2.define.grid.annotation.ColumnDefine;
import net.itjds.usm2.define.form.annotation.AddFormDataDefine;
import net.itjds.usm2.define.grid.annotation.GridPanelDefine;
import net.itjds.usm2.define.grid.annotation.SearchItemDefine;
import net.itjds.usm2.db.UsmService;
import net.itjds.fdt.db.fdtoafwbl.*;
import com.opensymphony.xwork2.ActionContext;

@GridPanelDefine(
		title = "局文，内部回函，党组文",
		buttons = {@ButtonDefine(text=ElementButton.query,iconCls="zoom",hidden = false,handler = "query()"),
				   @ButtonDefine(text=ElementButton.add,iconCls="add",hidden = false,handler = "window.open(context+'fdt/display/formshow.jsp?action=/formTemp/fdtform/fdtoafwbl.html.jsp')"),

				   @ButtonDefine(text=ElementButton.editstyle,iconCls="serialindex",hidden = false,handler = "window.open(context+'fdt/formeditor/edit.action?fileName=*fdtoafwbl.html')"),
				   @ButtonDefine(text=ElementButton.bindfield,iconCls="edit",hidden = false,handler = "window.open(context+'fdt/designer/fdtDesigner.action?fileName=*fdtoafwbl.html')"),
					
				   @ButtonDefine(text=ElementButton.delete,iconCls="remove",hidden = false,handler = "remove()")},
		editRowData=@AjaxDataDefine(
				url="/expression.jsp?expression=$currFormPanel&viewId=currFdtOaFwblPopForm"
		),
		addRowData=@AjaxDataDefine(
				url="/expression.jsp?expression=$currFormPanel&viewId=currFdtOaFwblPopForm"
		),
		fieldsIndex={
		
//				"uuid",//UUID
//			
//				"processinstId",//PROCESSINST_ID
//			
//				"activityinstId",//ACTIVITYINST_ID
			
				"typeType",//文种
			
				"typeWord",//字号
			
				"typeYear",//年号
			
				"typeNum",//文件号
			
				"miji",//密级
			
				"baomiqixian",//保密期限
			
				"huanji",//缓级
			
				"dingmiyiju",//定密依据
			
				"qianfa",//签发
			
				"huiqian",//会签
			
				"biaoti",//标题
			
				"zhusong",//主送
			
				"chaobao",//抄报
			
				"chaosong",//抄送
			
				"nigaodanwei",//拟稿单位
			
				"nigao",//拟稿
			
				"hegao",//核稿
			
				"yinshua",//印刷
			
				"jiaodui",//校对
			
				"fenshu",//份数
			
				"zhutici",//主题词
			
				"qicaoriqiStr",//起草日期字符串
			
		}		
)
public class FdtOaFwblGrid {
	UsmService service;
 	private FdtOaFwbl fdtOaFwbl;
 	public FdtOaFwblGrid(FdtOaFwbl fdtOaFwbl){
 	      this.fdtOaFwbl=(FdtOaFwbl)fdtOaFwbl;
 	      this.service = (UsmService) ActionContext.getContext().getValueStack().findValue("$FdtOaFwblUsmService");
 	}
 	
 	public FdtOaFwblGrid(){
 	    
 	}
 
 
    @ColumnDefine(
			mapping = "uuid",
			dataIndex = "uuid",
			header = "UUID", 
			type = ElementFieldType.TextField,
			tableName = "FDT_OA_FWBL",
 			columnName = "UUID",			
			search = @SearchItemDefine(fieldLabel="UUID",id="UUID",xtype=ElementFieldType.TextField,hidden=true)
	)
	public java.lang.String getUuid(){
		return fdtOaFwbl.getUuid();
	}
    @ColumnDefine(
			mapping = "processinstId",
			dataIndex = "processinstId",
			header = "PROCESSINST_ID", 
			type = ElementFieldType.TextField,
			tableName = "FDT_OA_FWBL",
 			columnName = "PROCESSINST_ID",			
			search = @SearchItemDefine(fieldLabel="PROCESSINST_ID",id="PROCESSINST_ID",xtype=ElementFieldType.TextField,hidden=true)
	)
	public java.lang.String getProcessinstId(){
		return fdtOaFwbl.getProcessinstId();
	}
    @ColumnDefine(
			mapping = "activityinstId",
			dataIndex = "activityinstId",
			header = "ACTIVITYINST_ID", 
			type = ElementFieldType.TextField,
			tableName = "FDT_OA_FWBL",
 			columnName = "ACTIVITYINST_ID",			
			search = @SearchItemDefine(fieldLabel="ACTIVITYINST_ID",id="ACTIVITYINST_ID",xtype=ElementFieldType.TextField,hidden=true)
	)
	public java.lang.String getActivityinstId(){
		return fdtOaFwbl.getActivityinstId();
	}
    @ColumnDefine(
			mapping = "typeType",
			dataIndex = "typeType",
			header = "文种", 
			type = ElementFieldType.TextField,
			tableName = "FDT_OA_FWBL",
 			columnName = "TYPE_TYPE",			
			search = @SearchItemDefine(fieldLabel="文种",id="TYPE_TYPE",xtype=ElementFieldType.TextField,hidden=true)
	)
	public java.lang.String getTypeType(){
		return "<input type=button onclick='alert()' value='转入流程'/>";
	}
    @ColumnDefine(
			mapping = "typeWord",
			dataIndex = "typeWord",
			header = "字号", 
			type = ElementFieldType.TextField,
			tableName = "FDT_OA_FWBL",
 			columnName = "TYPE_WORD",			
			search = @SearchItemDefine(fieldLabel="字号",id="TYPE_WORD",xtype=ElementFieldType.TextField,hidden=true)
	)
	public java.lang.String getTypeWord(){
		return fdtOaFwbl.getTypeWord();
	}
    @ColumnDefine(
			mapping = "typeYear",
			dataIndex = "typeYear",
			header = "年号", 
			type = ElementFieldType.NumberField,
			tableName = "FDT_OA_FWBL",
 			columnName = "TYPE_YEAR",			
			search = @SearchItemDefine(fieldLabel="年号",id="TYPE_YEAR",xtype=ElementFieldType.NumberField,hidden=true)
	)
	public java.lang.Integer getTypeYear(){
		return fdtOaFwbl.getTypeYear();
	}
    @ColumnDefine(
			mapping = "typeNum",
			dataIndex = "typeNum",
			header = "文件号", 
			type = ElementFieldType.NumberField,
			tableName = "FDT_OA_FWBL",
 			columnName = "TYPE_NUM",			
			search = @SearchItemDefine(fieldLabel="文件号",id="TYPE_NUM",xtype=ElementFieldType.NumberField,hidden=true)
	)
	public java.lang.Integer getTypeNum(){
		return fdtOaFwbl.getTypeNum();
	}
    @ColumnDefine(
			mapping = "miji",
			dataIndex = "miji",
			header = "密级", 
			type = ElementFieldType.TextField,
			tableName = "FDT_OA_FWBL",
 			columnName = "MIJI",			
			search = @SearchItemDefine(fieldLabel="密级",id="MIJI",xtype=ElementFieldType.TextField,hidden=true)
	)
	public java.lang.String getMiji(){
		return fdtOaFwbl.getMiji();
	}
    @ColumnDefine(
			mapping = "baomiqixian",
			dataIndex = "baomiqixian",
			header = "保密期限", 
			type = ElementFieldType.NumberField,
			tableName = "FDT_OA_FWBL",
 			columnName = "BAOMIQIXIAN",			
			search = @SearchItemDefine(fieldLabel="保密期限",id="BAOMIQIXIAN",xtype=ElementFieldType.NumberField,hidden=true)
	)
	public java.lang.Integer getBaomiqixian(){
		return fdtOaFwbl.getBaomiqixian();
	}
    @ColumnDefine(
			mapping = "huanji",
			dataIndex = "huanji",
			header = "缓级", 
			type = ElementFieldType.TextField,
			tableName = "FDT_OA_FWBL",
 			columnName = "HUANJI",			
			search = @SearchItemDefine(fieldLabel="缓级",id="HUANJI",xtype=ElementFieldType.TextField,hidden=true)
	)
	public java.lang.String getHuanji(){
		return fdtOaFwbl.getHuanji();
	}
    @ColumnDefine(
			mapping = "dingmiyiju",
			dataIndex = "dingmiyiju",
			header = "定密依据", 
			type = ElementFieldType.TextArea,
			tableName = "FDT_OA_FWBL",
 			columnName = "DINGMIYIJU",			
			search = @SearchItemDefine(fieldLabel="定密依据",id="DINGMIYIJU",xtype=ElementFieldType.TextArea,hidden=true)
	)
	public java.lang.String getDingmiyiju(){
		return fdtOaFwbl.getDingmiyiju();
	}
    @ColumnDefine(
			mapping = "qianfa",
			dataIndex = "qianfa",
			header = "签发", 
			type = ElementFieldType.TextArea,
			tableName = "FDT_OA_FWBL",
 			columnName = "QIANFA",			
			search = @SearchItemDefine(fieldLabel="签发",id="QIANFA",xtype=ElementFieldType.TextArea,hidden=true)
	)
	public java.lang.String getQianfa(){
		return fdtOaFwbl.getQianfa();
	}
    @ColumnDefine(
			mapping = "huiqian",
			dataIndex = "huiqian",
			header = "会签", 
			type = ElementFieldType.TextArea,
			tableName = "FDT_OA_FWBL",
 			columnName = "HUIQIAN",			
			search = @SearchItemDefine(fieldLabel="会签",id="HUIQIAN",xtype=ElementFieldType.TextArea,hidden=true)
	)
	public java.lang.String getHuiqian(){
		return fdtOaFwbl.getHuiqian();
	}
    @ColumnDefine(
			mapping = "biaoti",
			dataIndex = "biaoti",
			header = "标题", 
			type = ElementFieldType.TextArea,
			tableName = "FDT_OA_FWBL",
 			columnName = "BIAOTI",			
			search = @SearchItemDefine(fieldLabel="标题",id="BIAOTI",xtype=ElementFieldType.TextArea,hidden=true)
	)
	public java.lang.String getBiaoti(){
		return fdtOaFwbl.getBiaoti();
	}
    @ColumnDefine(
			mapping = "zhusong",
			dataIndex = "zhusong",
			header = "主送", 
			type = ElementFieldType.TextArea,
			tableName = "FDT_OA_FWBL",
 			columnName = "ZHUSONG",			
			search = @SearchItemDefine(fieldLabel="主送",id="ZHUSONG",xtype=ElementFieldType.TextArea,hidden=true)
	)
	public java.lang.String getZhusong(){
		return fdtOaFwbl.getZhusong();
	}
    @ColumnDefine(
			mapping = "chaobao",
			dataIndex = "chaobao",
			header = "抄报", 
			type = ElementFieldType.TextArea,
			tableName = "FDT_OA_FWBL",
 			columnName = "CHAOBAO",			
			search = @SearchItemDefine(fieldLabel="抄报",id="CHAOBAO",xtype=ElementFieldType.TextArea,hidden=true)
	)
	public java.lang.String getChaobao(){
		return fdtOaFwbl.getChaobao();
	}
    @ColumnDefine(
			mapping = "chaosong",
			dataIndex = "chaosong",
			header = "抄送", 
			type = ElementFieldType.TextArea,
			tableName = "FDT_OA_FWBL",
 			columnName = "CHAOSONG",			
			search = @SearchItemDefine(fieldLabel="抄送",id="CHAOSONG",xtype=ElementFieldType.TextArea,hidden=true)
	)
	public java.lang.String getChaosong(){
		return fdtOaFwbl.getChaosong();
	}
    @ColumnDefine(
			mapping = "nigaodanwei",
			dataIndex = "nigaodanwei",
			header = "拟稿单位", 
			type = ElementFieldType.TextField,
			tableName = "FDT_OA_FWBL",
 			columnName = "NIGAODANWEI",			
			search = @SearchItemDefine(fieldLabel="拟稿单位",id="NIGAODANWEI",xtype=ElementFieldType.TextField,hidden=true)
	)
	public java.lang.String getNigaodanwei(){
		return fdtOaFwbl.getNigaodanwei();
	}
    @ColumnDefine(
			mapping = "nigao",
			dataIndex = "nigao",
			header = "拟稿", 
			type = ElementFieldType.TextField,
			tableName = "FDT_OA_FWBL",
 			columnName = "NIGAO",			
			search = @SearchItemDefine(fieldLabel="拟稿",id="NIGAO",xtype=ElementFieldType.TextField,hidden=true)
	)
	public java.lang.String getNigao(){
		return fdtOaFwbl.getNigao();
	}
    @ColumnDefine(
			mapping = "hegao",
			dataIndex = "hegao",
			header = "核稿", 
			type = ElementFieldType.TextField,
			tableName = "FDT_OA_FWBL",
 			columnName = "HEGAO",			
			search = @SearchItemDefine(fieldLabel="核稿",id="HEGAO",xtype=ElementFieldType.TextField,hidden=true)
	)
	public java.lang.String getHegao(){
		return fdtOaFwbl.getHegao();
	}
    @ColumnDefine(
			mapping = "yinshua",
			dataIndex = "yinshua",
			header = "印刷", 
			type = ElementFieldType.TextField,
			tableName = "FDT_OA_FWBL",
 			columnName = "YINSHUA",			
			search = @SearchItemDefine(fieldLabel="印刷",id="YINSHUA",xtype=ElementFieldType.TextField,hidden=true)
	)
	public java.lang.String getYinshua(){
		return fdtOaFwbl.getYinshua();
	}
    @ColumnDefine(
			mapping = "jiaodui",
			dataIndex = "jiaodui",
			header = "校对", 
			type = ElementFieldType.TextField,
			tableName = "FDT_OA_FWBL",
 			columnName = "JIAODUI",			
			search = @SearchItemDefine(fieldLabel="校对",id="JIAODUI",xtype=ElementFieldType.TextField,hidden=true)
	)
	public java.lang.String getJiaodui(){
		return fdtOaFwbl.getJiaodui();
	}
    @ColumnDefine(
			mapping = "fenshu",
			dataIndex = "fenshu",
			header = "份数", 
			type = ElementFieldType.NumberField,
			tableName = "FDT_OA_FWBL",
 			columnName = "FENSHU",			
			search = @SearchItemDefine(fieldLabel="份数",id="FENSHU",xtype=ElementFieldType.NumberField,hidden=true)
	)
	public java.lang.Integer getFenshu(){
		return fdtOaFwbl.getFenshu();
	}
    @ColumnDefine(
			mapping = "zhutici",
			dataIndex = "zhutici",
			header = "主题词", 
			type = ElementFieldType.TextField,
			tableName = "FDT_OA_FWBL",
 			columnName = "ZHUTICI",			
			search = @SearchItemDefine(fieldLabel="主题词",id="ZHUTICI",xtype=ElementFieldType.TextField,hidden=true)
	)
	public java.lang.String getZhutici(){
		return fdtOaFwbl.getZhutici();
	}
	
    @ColumnDefine(
			mapping = "qicaoriqiStr",
			dataIndex = "qicaoriqiStr",
			header = "起草日期", 
			type = ElementFieldType.TextField,			
 			tableName = "FDT_OA_FWBL",
 			columnName = "QICAORIQI"
	)
	public String getQicaoriqiStr(){
		return DateUtility.formatDate(fdtOaFwbl.getQicaoriqi(),"yyyy-MM-dd");
	}
}