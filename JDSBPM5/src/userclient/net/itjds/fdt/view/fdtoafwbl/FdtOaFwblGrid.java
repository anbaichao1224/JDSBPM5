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
		title = "���ģ��ڲ��غ���������",
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
			
				"typeType",//����
			
				"typeWord",//�ֺ�
			
				"typeYear",//���
			
				"typeNum",//�ļ���
			
				"miji",//�ܼ�
			
				"baomiqixian",//��������
			
				"huanji",//����
			
				"dingmiyiju",//��������
			
				"qianfa",//ǩ��
			
				"huiqian",//��ǩ
			
				"biaoti",//����
			
				"zhusong",//����
			
				"chaobao",//����
			
				"chaosong",//����
			
				"nigaodanwei",//��嵥λ
			
				"nigao",//���
			
				"hegao",//�˸�
			
				"yinshua",//ӡˢ
			
				"jiaodui",//У��
			
				"fenshu",//����
			
				"zhutici",//�����
			
				"qicaoriqiStr",//��������ַ���
			
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
			header = "����", 
			type = ElementFieldType.TextField,
			tableName = "FDT_OA_FWBL",
 			columnName = "TYPE_TYPE",			
			search = @SearchItemDefine(fieldLabel="����",id="TYPE_TYPE",xtype=ElementFieldType.TextField,hidden=true)
	)
	public java.lang.String getTypeType(){
		return "<input type=button onclick='alert()' value='ת������'/>";
	}
    @ColumnDefine(
			mapping = "typeWord",
			dataIndex = "typeWord",
			header = "�ֺ�", 
			type = ElementFieldType.TextField,
			tableName = "FDT_OA_FWBL",
 			columnName = "TYPE_WORD",			
			search = @SearchItemDefine(fieldLabel="�ֺ�",id="TYPE_WORD",xtype=ElementFieldType.TextField,hidden=true)
	)
	public java.lang.String getTypeWord(){
		return fdtOaFwbl.getTypeWord();
	}
    @ColumnDefine(
			mapping = "typeYear",
			dataIndex = "typeYear",
			header = "���", 
			type = ElementFieldType.NumberField,
			tableName = "FDT_OA_FWBL",
 			columnName = "TYPE_YEAR",			
			search = @SearchItemDefine(fieldLabel="���",id="TYPE_YEAR",xtype=ElementFieldType.NumberField,hidden=true)
	)
	public java.lang.Integer getTypeYear(){
		return fdtOaFwbl.getTypeYear();
	}
    @ColumnDefine(
			mapping = "typeNum",
			dataIndex = "typeNum",
			header = "�ļ���", 
			type = ElementFieldType.NumberField,
			tableName = "FDT_OA_FWBL",
 			columnName = "TYPE_NUM",			
			search = @SearchItemDefine(fieldLabel="�ļ���",id="TYPE_NUM",xtype=ElementFieldType.NumberField,hidden=true)
	)
	public java.lang.Integer getTypeNum(){
		return fdtOaFwbl.getTypeNum();
	}
    @ColumnDefine(
			mapping = "miji",
			dataIndex = "miji",
			header = "�ܼ�", 
			type = ElementFieldType.TextField,
			tableName = "FDT_OA_FWBL",
 			columnName = "MIJI",			
			search = @SearchItemDefine(fieldLabel="�ܼ�",id="MIJI",xtype=ElementFieldType.TextField,hidden=true)
	)
	public java.lang.String getMiji(){
		return fdtOaFwbl.getMiji();
	}
    @ColumnDefine(
			mapping = "baomiqixian",
			dataIndex = "baomiqixian",
			header = "��������", 
			type = ElementFieldType.NumberField,
			tableName = "FDT_OA_FWBL",
 			columnName = "BAOMIQIXIAN",			
			search = @SearchItemDefine(fieldLabel="��������",id="BAOMIQIXIAN",xtype=ElementFieldType.NumberField,hidden=true)
	)
	public java.lang.Integer getBaomiqixian(){
		return fdtOaFwbl.getBaomiqixian();
	}
    @ColumnDefine(
			mapping = "huanji",
			dataIndex = "huanji",
			header = "����", 
			type = ElementFieldType.TextField,
			tableName = "FDT_OA_FWBL",
 			columnName = "HUANJI",			
			search = @SearchItemDefine(fieldLabel="����",id="HUANJI",xtype=ElementFieldType.TextField,hidden=true)
	)
	public java.lang.String getHuanji(){
		return fdtOaFwbl.getHuanji();
	}
    @ColumnDefine(
			mapping = "dingmiyiju",
			dataIndex = "dingmiyiju",
			header = "��������", 
			type = ElementFieldType.TextArea,
			tableName = "FDT_OA_FWBL",
 			columnName = "DINGMIYIJU",			
			search = @SearchItemDefine(fieldLabel="��������",id="DINGMIYIJU",xtype=ElementFieldType.TextArea,hidden=true)
	)
	public java.lang.String getDingmiyiju(){
		return fdtOaFwbl.getDingmiyiju();
	}
    @ColumnDefine(
			mapping = "qianfa",
			dataIndex = "qianfa",
			header = "ǩ��", 
			type = ElementFieldType.TextArea,
			tableName = "FDT_OA_FWBL",
 			columnName = "QIANFA",			
			search = @SearchItemDefine(fieldLabel="ǩ��",id="QIANFA",xtype=ElementFieldType.TextArea,hidden=true)
	)
	public java.lang.String getQianfa(){
		return fdtOaFwbl.getQianfa();
	}
    @ColumnDefine(
			mapping = "huiqian",
			dataIndex = "huiqian",
			header = "��ǩ", 
			type = ElementFieldType.TextArea,
			tableName = "FDT_OA_FWBL",
 			columnName = "HUIQIAN",			
			search = @SearchItemDefine(fieldLabel="��ǩ",id="HUIQIAN",xtype=ElementFieldType.TextArea,hidden=true)
	)
	public java.lang.String getHuiqian(){
		return fdtOaFwbl.getHuiqian();
	}
    @ColumnDefine(
			mapping = "biaoti",
			dataIndex = "biaoti",
			header = "����", 
			type = ElementFieldType.TextArea,
			tableName = "FDT_OA_FWBL",
 			columnName = "BIAOTI",			
			search = @SearchItemDefine(fieldLabel="����",id="BIAOTI",xtype=ElementFieldType.TextArea,hidden=true)
	)
	public java.lang.String getBiaoti(){
		return fdtOaFwbl.getBiaoti();
	}
    @ColumnDefine(
			mapping = "zhusong",
			dataIndex = "zhusong",
			header = "����", 
			type = ElementFieldType.TextArea,
			tableName = "FDT_OA_FWBL",
 			columnName = "ZHUSONG",			
			search = @SearchItemDefine(fieldLabel="����",id="ZHUSONG",xtype=ElementFieldType.TextArea,hidden=true)
	)
	public java.lang.String getZhusong(){
		return fdtOaFwbl.getZhusong();
	}
    @ColumnDefine(
			mapping = "chaobao",
			dataIndex = "chaobao",
			header = "����", 
			type = ElementFieldType.TextArea,
			tableName = "FDT_OA_FWBL",
 			columnName = "CHAOBAO",			
			search = @SearchItemDefine(fieldLabel="����",id="CHAOBAO",xtype=ElementFieldType.TextArea,hidden=true)
	)
	public java.lang.String getChaobao(){
		return fdtOaFwbl.getChaobao();
	}
    @ColumnDefine(
			mapping = "chaosong",
			dataIndex = "chaosong",
			header = "����", 
			type = ElementFieldType.TextArea,
			tableName = "FDT_OA_FWBL",
 			columnName = "CHAOSONG",			
			search = @SearchItemDefine(fieldLabel="����",id="CHAOSONG",xtype=ElementFieldType.TextArea,hidden=true)
	)
	public java.lang.String getChaosong(){
		return fdtOaFwbl.getChaosong();
	}
    @ColumnDefine(
			mapping = "nigaodanwei",
			dataIndex = "nigaodanwei",
			header = "��嵥λ", 
			type = ElementFieldType.TextField,
			tableName = "FDT_OA_FWBL",
 			columnName = "NIGAODANWEI",			
			search = @SearchItemDefine(fieldLabel="��嵥λ",id="NIGAODANWEI",xtype=ElementFieldType.TextField,hidden=true)
	)
	public java.lang.String getNigaodanwei(){
		return fdtOaFwbl.getNigaodanwei();
	}
    @ColumnDefine(
			mapping = "nigao",
			dataIndex = "nigao",
			header = "���", 
			type = ElementFieldType.TextField,
			tableName = "FDT_OA_FWBL",
 			columnName = "NIGAO",			
			search = @SearchItemDefine(fieldLabel="���",id="NIGAO",xtype=ElementFieldType.TextField,hidden=true)
	)
	public java.lang.String getNigao(){
		return fdtOaFwbl.getNigao();
	}
    @ColumnDefine(
			mapping = "hegao",
			dataIndex = "hegao",
			header = "�˸�", 
			type = ElementFieldType.TextField,
			tableName = "FDT_OA_FWBL",
 			columnName = "HEGAO",			
			search = @SearchItemDefine(fieldLabel="�˸�",id="HEGAO",xtype=ElementFieldType.TextField,hidden=true)
	)
	public java.lang.String getHegao(){
		return fdtOaFwbl.getHegao();
	}
    @ColumnDefine(
			mapping = "yinshua",
			dataIndex = "yinshua",
			header = "ӡˢ", 
			type = ElementFieldType.TextField,
			tableName = "FDT_OA_FWBL",
 			columnName = "YINSHUA",			
			search = @SearchItemDefine(fieldLabel="ӡˢ",id="YINSHUA",xtype=ElementFieldType.TextField,hidden=true)
	)
	public java.lang.String getYinshua(){
		return fdtOaFwbl.getYinshua();
	}
    @ColumnDefine(
			mapping = "jiaodui",
			dataIndex = "jiaodui",
			header = "У��", 
			type = ElementFieldType.TextField,
			tableName = "FDT_OA_FWBL",
 			columnName = "JIAODUI",			
			search = @SearchItemDefine(fieldLabel="У��",id="JIAODUI",xtype=ElementFieldType.TextField,hidden=true)
	)
	public java.lang.String getJiaodui(){
		return fdtOaFwbl.getJiaodui();
	}
    @ColumnDefine(
			mapping = "fenshu",
			dataIndex = "fenshu",
			header = "����", 
			type = ElementFieldType.NumberField,
			tableName = "FDT_OA_FWBL",
 			columnName = "FENSHU",			
			search = @SearchItemDefine(fieldLabel="����",id="FENSHU",xtype=ElementFieldType.NumberField,hidden=true)
	)
	public java.lang.Integer getFenshu(){
		return fdtOaFwbl.getFenshu();
	}
    @ColumnDefine(
			mapping = "zhutici",
			dataIndex = "zhutici",
			header = "�����", 
			type = ElementFieldType.TextField,
			tableName = "FDT_OA_FWBL",
 			columnName = "ZHUTICI",			
			search = @SearchItemDefine(fieldLabel="�����",id="ZHUTICI",xtype=ElementFieldType.TextField,hidden=true)
	)
	public java.lang.String getZhutici(){
		return fdtOaFwbl.getZhutici();
	}
	
    @ColumnDefine(
			mapping = "qicaoriqiStr",
			dataIndex = "qicaoriqiStr",
			header = "�������", 
			type = ElementFieldType.TextField,			
 			tableName = "FDT_OA_FWBL",
 			columnName = "QICAORIQI"
	)
	public String getQicaoriqiStr(){
		return DateUtility.formatDate(fdtOaFwbl.getQicaoriqi(),"yyyy-MM-dd");
	}
}