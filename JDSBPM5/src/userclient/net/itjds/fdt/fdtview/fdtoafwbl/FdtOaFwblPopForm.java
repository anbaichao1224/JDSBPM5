package net.itjds.fdt.fdtview.fdtoafwbl;
import net.itjds.j2ee.dao.MethodChinaName;
import net.itjds.common.util.DateUtility;
import net.itjds.bpm.data.xmlproxy.EsbBeanAnnotation;
import net.itjds.bpm.data.xmlproxy.MapdaoBeanAnnotation;
import net.itjds.usm2.define.data.annotation.AjaxDataDefine;
import net.itjds.usm2.define.data.annotation.HttpBaseParams;
import net.itjds.usm2.define.enums.*;
import net.itjds.usm2.define.form.annotation.FormFieldDefine;
import net.itjds.usm2.define.form.annotation.FormPanelDefine;
import net.itjds.fdt.db.fdtoafwbl.*;

@FormPanelDefine(
		labelWidth = 0,
		labelAlign = ElementAlign.left,
	
		buttons = {ElementButton.save,ElementButton.abort},
		
		loadData=@AjaxDataDefine(
				url="/expression.jsp?expression=$JSONForm($currFdtOaFwblPopForm)"
		),
		updateData=@AjaxDataDefine(
				url="/update.action?expression=$UpdateCurrForm($currFormData)"
		),
		fieldsIndex={
				"uuid",//UUID
			
				"processinstId",//PROCESSINST_ID
			
				"activityinstId",//ACTIVITYINST_ID
			
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
			
			
				"qicaoriqi"//�������
			
		}		
)

@EsbBeanAnnotation(id="currFdtOaFwblPopForm",
		name="�����༭����",
	
		expressionArr="FdtOaFwblPopForm($FdtOaFwbl())",
		desc="�����༭����",
		dataType="context"
)

public class FdtOaFwblPopForm{
 	public FdtOaFwbl fdtOaFwbl;
 	public FdtOaFwblPopForm(FdtOaFwbl fdtOaFwbl){
 	      this.fdtOaFwbl=(FdtOaFwbl)fdtOaFwbl;
 	}
 
 
     @FormFieldDefine(id = "$currFdtOaFwbl.Uuid", 
 					 name = "$currFdtOaFwbl.Uuid", 
 					 fieldLabel = "UUID",
 					 xtype = ElementFieldType.TextField,
 					 model = "local",
 					 vtype = ElementVtype.none, 
 					 validateOnBlur = true, 
 					 allowBlank = false,
 					 maxLength = 64
 	)
 	@MethodChinaName(cname="UUID")
	public java.lang.String getUuid(){
		return fdtOaFwbl.getUuid();
	}
	
	public void setUuid(java.lang.String uuid){
		 fdtOaFwbl.setUuid(uuid);
	}
	
     @FormFieldDefine(id = "$currFdtOaFwbl.ProcessinstId", 
 					 name = "$currFdtOaFwbl.ProcessinstId", 
 					 fieldLabel = "PROCESSINST_ID",
 					 xtype = ElementFieldType.TextField,
 					 model = "local",
 					 vtype = ElementVtype.none, 
 					 validateOnBlur = true, 
 					 allowBlank = true,
 					 maxLength = 64
 	)
 	@MethodChinaName(cname="PROCESSINST_ID")
	public java.lang.String getProcessinstId(){
		return fdtOaFwbl.getProcessinstId();
	}
	
	public void setProcessinstId(java.lang.String processinstId){
		 fdtOaFwbl.setProcessinstId(processinstId);
	}
	
     @FormFieldDefine(id = "$currFdtOaFwbl.ActivityinstId", 
 					 name = "$currFdtOaFwbl.ActivityinstId", 
 					 fieldLabel = "ACTIVITYINST_ID",
 					 xtype = ElementFieldType.TextField,
 					 model = "local",
 					 vtype = ElementVtype.none, 
 					 validateOnBlur = true, 
 					 allowBlank = true,
 					 maxLength = 64
 	)
 	@MethodChinaName(cname="ACTIVITYINST_ID")
	public java.lang.String getActivityinstId(){
		return fdtOaFwbl.getActivityinstId();
	}
	
	public void setActivityinstId(java.lang.String activityinstId){
		 fdtOaFwbl.setActivityinstId(activityinstId);
	}
	
     @FormFieldDefine(id = "$currFdtOaFwbl.TypeType", 
 					 name = "$currFdtOaFwbl.TypeType", 
 					 fieldLabel = "����",
 					 xtype = ElementFieldType.TextField,
 					 model = "local",
 					 vtype = ElementVtype.none, 
 					 validateOnBlur = true, 
 					 allowBlank = true,
 					 maxLength = 64
 	)
 	@MethodChinaName(cname="����")
	public java.lang.String getTypeType(){
		return fdtOaFwbl.getTypeType();
	}
	
	public void setTypeType(java.lang.String typeType){
		 fdtOaFwbl.setTypeType(typeType);
	}
	
     @FormFieldDefine(id = "$currFdtOaFwbl.TypeWord", 
 					 name = "$currFdtOaFwbl.TypeWord", 
 					 fieldLabel = "�ֺ�",
 					 xtype = ElementFieldType.TextField,
 					 model = "local",
 					 vtype = ElementVtype.none, 
 					 validateOnBlur = true, 
 					 allowBlank = true,
 					 maxLength = 64
 	)
 	@MethodChinaName(cname="�ֺ�")
	public java.lang.String getTypeWord(){
		return fdtOaFwbl.getTypeWord();
	}
	
	public void setTypeWord(java.lang.String typeWord){
		 fdtOaFwbl.setTypeWord(typeWord);
	}
	
     @FormFieldDefine(id = "$currFdtOaFwbl.TypeYear", 
 					 name = "$currFdtOaFwbl.TypeYear", 
 					 fieldLabel = "���",
 					 xtype = ElementFieldType.NumberField,
 					 model = "local",
 					 vtype = ElementVtype.none, 
 					 validateOnBlur = true, 
 					 allowBlank = true,
 					 maxLength = 22
 	)
 	@MethodChinaName(cname="���")
	public java.lang.Integer getTypeYear(){
		return fdtOaFwbl.getTypeYear();
	}
	
	public void setTypeYear(java.lang.Integer typeYear){
		 fdtOaFwbl.setTypeYear(typeYear);
	}
	
     @FormFieldDefine(id = "$currFdtOaFwbl.TypeNum", 
 					 name = "$currFdtOaFwbl.TypeNum", 
 					 fieldLabel = "�ļ���",
 					 xtype = ElementFieldType.NumberField,
 					 model = "local",
 					 vtype = ElementVtype.none, 
 					 validateOnBlur = true, 
 					 allowBlank = true,
 					 maxLength = 22
 	)
 	@MethodChinaName(cname="�ļ���")
	public java.lang.Integer getTypeNum(){
		return fdtOaFwbl.getTypeNum();
	}
	
	public void setTypeNum(java.lang.Integer typeNum){
		 fdtOaFwbl.setTypeNum(typeNum);
	}
	
     @FormFieldDefine(id = "$currFdtOaFwbl.Miji", 
 					 name = "$currFdtOaFwbl.Miji", 
 					 fieldLabel = "�ܼ�",
 					 xtype = ElementFieldType.TextField,
 					 model = "local",
 					 vtype = ElementVtype.none, 
 					 validateOnBlur = true, 
 					 allowBlank = true,
 					 maxLength = 64
 	)
 	@MethodChinaName(cname="�ܼ�")
	public java.lang.String getMiji(){
		return fdtOaFwbl.getMiji();
	}
	
	public void setMiji(java.lang.String miji){
		 fdtOaFwbl.setMiji(miji);
	}
	
     @FormFieldDefine(id = "$currFdtOaFwbl.Baomiqixian", 
 					 name = "$currFdtOaFwbl.Baomiqixian", 
 					 fieldLabel = "��������",
 					 xtype = ElementFieldType.NumberField,
 					 model = "local",
 					 vtype = ElementVtype.none, 
 					 validateOnBlur = true, 
 					 allowBlank = true,
 					 maxLength = 22
 	)
 	@MethodChinaName(cname="��������")
	public java.lang.Integer getBaomiqixian(){
		return fdtOaFwbl.getBaomiqixian();
	}
	
	public void setBaomiqixian(java.lang.Integer baomiqixian){
		 fdtOaFwbl.setBaomiqixian(baomiqixian);
	}
	
     @FormFieldDefine(id = "$currFdtOaFwbl.Huanji", 
 					 name = "$currFdtOaFwbl.Huanji", 
 					 fieldLabel = "����",
 					 xtype = ElementFieldType.TextField,
 					 model = "local",
 					 vtype = ElementVtype.none, 
 					 validateOnBlur = true, 
 					 allowBlank = true,
 					 maxLength = 64
 	)
 	@MethodChinaName(cname="����")
	public java.lang.String getHuanji(){
		return fdtOaFwbl.getHuanji();
	}
	
	public void setHuanji(java.lang.String huanji){
		 fdtOaFwbl.setHuanji(huanji);
	}
	
     @FormFieldDefine(id = "$currFdtOaFwbl.Dingmiyiju", 
 					 name = "$currFdtOaFwbl.Dingmiyiju", 
 					 fieldLabel = "��������",
 					 xtype = ElementFieldType.TextArea,
 					 model = "local",
 					 vtype = ElementVtype.none, 
 					 validateOnBlur = true, 
 					 allowBlank = true,
 					 maxLength = 150
 	)
 	@MethodChinaName(cname="��������")
	public java.lang.String getDingmiyiju(){
		return fdtOaFwbl.getDingmiyiju();
	}
	
	public void setDingmiyiju(java.lang.String dingmiyiju){
		 fdtOaFwbl.setDingmiyiju(dingmiyiju);
	}
	
     @FormFieldDefine(id = "$currFdtOaFwbl.Qianfa", 
 					 name = "$currFdtOaFwbl.Qianfa", 
 					 fieldLabel = "ǩ��",
 					 xtype = ElementFieldType.TextArea,
 					 model = "local",
 					 vtype = ElementVtype.none, 
 					 validateOnBlur = true, 
 					 allowBlank = true,
 					 maxLength = 1000
 	)
 	@MethodChinaName(cname="ǩ��")
	public java.lang.String getQianfa(){
		return fdtOaFwbl.getQianfa();
	}
	
	public void setQianfa(java.lang.String qianfa){
		 fdtOaFwbl.setQianfa(qianfa);
	}
	
     @FormFieldDefine(id = "$currFdtOaFwbl.Huiqian", 
 					 name = "$currFdtOaFwbl.Huiqian", 
 					 fieldLabel = "��ǩ",
 					 xtype = ElementFieldType.TextArea,
 					 model = "local",
 					 vtype = ElementVtype.none, 
 					 validateOnBlur = true, 
 					 allowBlank = true,
 					 maxLength = 3000
 	)
 	@MethodChinaName(cname="��ǩ")
	public java.lang.String getHuiqian(){
		return fdtOaFwbl.getHuiqian();
	}
	
	public void setHuiqian(java.lang.String huiqian){
		 fdtOaFwbl.setHuiqian(huiqian);
	}
	
     @FormFieldDefine(id = "$currFdtOaFwbl.Biaoti", 
 					 name = "$currFdtOaFwbl.Biaoti", 
 					 fieldLabel = "����",
 					 xtype = ElementFieldType.TextArea,
 					 model = "local",
 					 vtype = ElementVtype.none, 
 					 validateOnBlur = true, 
 					 allowBlank = true,
 					 maxLength = 500
 	)
 	@MethodChinaName(cname="����")
	public java.lang.String getBiaoti(){
		return fdtOaFwbl.getBiaoti();
	}
	
	public void setBiaoti(java.lang.String biaoti){
		 fdtOaFwbl.setBiaoti(biaoti);
	}
	
     @FormFieldDefine(id = "$currFdtOaFwbl.Zhusong", 
 					 name = "$currFdtOaFwbl.Zhusong", 
 					 fieldLabel = "����",
 					 xtype = ElementFieldType.TextArea,
 					 model = "local",
 					 vtype = ElementVtype.none, 
 					 validateOnBlur = true, 
 					 allowBlank = true,
 					 maxLength = 500
 	)
 	@MethodChinaName(cname="����")
	public java.lang.String getZhusong(){
		return fdtOaFwbl.getZhusong();
	}
	
	public void setZhusong(java.lang.String zhusong){
		 fdtOaFwbl.setZhusong(zhusong);
	}
	
     @FormFieldDefine(id = "$currFdtOaFwbl.Chaobao", 
 					 name = "$currFdtOaFwbl.Chaobao", 
 					 fieldLabel = "����",
 					 xtype = ElementFieldType.TextArea,
 					 model = "local",
 					 vtype = ElementVtype.none, 
 					 validateOnBlur = true, 
 					 allowBlank = true,
 					 maxLength = 500
 	)
 	@MethodChinaName(cname="����")
	public java.lang.String getChaobao(){
		return fdtOaFwbl.getChaobao();
	}
	
	public void setChaobao(java.lang.String chaobao){
		 fdtOaFwbl.setChaobao(chaobao);
	}
	
     @FormFieldDefine(id = "$currFdtOaFwbl.Chaosong", 
 					 name = "$currFdtOaFwbl.Chaosong", 
 					 fieldLabel = "����",
 					 xtype = ElementFieldType.TextArea,
 					 model = "local",
 					 vtype = ElementVtype.none, 
 					 validateOnBlur = true, 
 					 allowBlank = true,
 					 maxLength = 500
 	)
 	@MethodChinaName(cname="����")
	public java.lang.String getChaosong(){
		return fdtOaFwbl.getChaosong();
	}
	
	public void setChaosong(java.lang.String chaosong){
		 fdtOaFwbl.setChaosong(chaosong);
	}
	
     @FormFieldDefine(id = "$currFdtOaFwbl.Nigaodanwei", 
 					 name = "$currFdtOaFwbl.Nigaodanwei", 
 					 fieldLabel = "��嵥λ",
 					 xtype = ElementFieldType.TextField,
 					 model = "local",
 					 vtype = ElementVtype.none, 
 					 validateOnBlur = true, 
 					 allowBlank = true,
 					 maxLength = 100
 	)
 	@MethodChinaName(cname="��嵥λ")
	public java.lang.String getNigaodanwei(){
		return fdtOaFwbl.getNigaodanwei();
	}
	
	public void setNigaodanwei(java.lang.String nigaodanwei){
		 fdtOaFwbl.setNigaodanwei(nigaodanwei);
	}
	
     @FormFieldDefine(id = "$currFdtOaFwbl.Nigao", 
 					 name = "$currFdtOaFwbl.Nigao", 
 					 fieldLabel = "���",
 					 xtype = ElementFieldType.TextField,
 					 model = "local",
 					 vtype = ElementVtype.none, 
 					 validateOnBlur = true, 
 					 allowBlank = true,
 					 maxLength = 64
 	)
 	@MethodChinaName(cname="���")
	public java.lang.String getNigao(){
		return fdtOaFwbl.getNigao();
	}
	
	public void setNigao(java.lang.String nigao){
		 fdtOaFwbl.setNigao(nigao);
	}
	
     @FormFieldDefine(id = "$currFdtOaFwbl.Hegao", 
 					 name = "$currFdtOaFwbl.Hegao", 
 					 fieldLabel = "�˸�",
 					 xtype = ElementFieldType.TextField,
 					 model = "local",
 					 vtype = ElementVtype.none, 
 					 validateOnBlur = true, 
 					 allowBlank = true,
 					 maxLength = 64
 	)
 	@MethodChinaName(cname="�˸�")
	public java.lang.String getHegao(){
		return fdtOaFwbl.getHegao();
	}
	
	public void setHegao(java.lang.String hegao){
		 fdtOaFwbl.setHegao(hegao);
	}
	
     @FormFieldDefine(id = "$currFdtOaFwbl.Yinshua", 
 					 name = "$currFdtOaFwbl.Yinshua", 
 					 fieldLabel = "ӡˢ",
 					 xtype = ElementFieldType.TextField,
 					 model = "local",
 					 vtype = ElementVtype.none, 
 					 validateOnBlur = true, 
 					 allowBlank = true,
 					 maxLength = 64
 	)
 	@MethodChinaName(cname="ӡˢ")
	public java.lang.String getYinshua(){
		return fdtOaFwbl.getYinshua();
	}
	
	public void setYinshua(java.lang.String yinshua){
		 fdtOaFwbl.setYinshua(yinshua);
	}
	
     @FormFieldDefine(id = "$currFdtOaFwbl.Jiaodui", 
 					 name = "$currFdtOaFwbl.Jiaodui", 
 					 fieldLabel = "У��",
 					 xtype = ElementFieldType.TextField,
 					 model = "local",
 					 vtype = ElementVtype.none, 
 					 validateOnBlur = true, 
 					 allowBlank = true,
 					 maxLength = 64
 	)
 	@MethodChinaName(cname="У��")
	public java.lang.String getJiaodui(){
		return fdtOaFwbl.getJiaodui();
	}
	
	public void setJiaodui(java.lang.String jiaodui){
		 fdtOaFwbl.setJiaodui(jiaodui);
	}
	
     @FormFieldDefine(id = "$currFdtOaFwbl.Fenshu", 
 					 name = "$currFdtOaFwbl.Fenshu", 
 					 fieldLabel = "����",
 					 xtype = ElementFieldType.NumberField,
 					 model = "local",
 					 vtype = ElementVtype.none, 
 					 validateOnBlur = true, 
 					 allowBlank = true,
 					 maxLength = 22
 	)
 	@MethodChinaName(cname="����")
	public java.lang.Integer getFenshu(){
		return fdtOaFwbl.getFenshu();
	}
	
	public void setFenshu(java.lang.Integer fenshu){
		 fdtOaFwbl.setFenshu(fenshu);
	}
	
     @FormFieldDefine(id = "$currFdtOaFwbl.Zhutici", 
 					 name = "$currFdtOaFwbl.Zhutici", 
 					 fieldLabel = "�����",
 					 xtype = ElementFieldType.TextField,
 					 model = "local",
 					 vtype = ElementVtype.none, 
 					 validateOnBlur = true, 
 					 allowBlank = true,
 					 maxLength = 100
 	)
 	@MethodChinaName(cname="�����")
	public java.lang.String getZhutici(){
		return fdtOaFwbl.getZhutici();
	}
	
	public void setZhutici(java.lang.String zhutici){
		 fdtOaFwbl.setZhutici(zhutici);
	}
	
	
	@FormFieldDefine(id = "$currFdtOaFwbl.Qicaoriqi", 
 					 name = "$currFdtOaFwbl.Qicaoriqi", 
 					 fieldLabel = "�������",
 					 xtype = ElementFieldType.Hidden,
 					 model = "local",
 					 vtype = ElementVtype.none, 
 					 validateOnBlur = true, 
 					 allowBlank = true,
 					 maxLength = 7
 	)
 	@MethodChinaName(cname="�������")
	public java.util.Date  getQicaoriqi(){
		return new java.util.Date (fdtOaFwbl.getQicaoriqi().getTime());
	}
	
	public void setQicaoriqi(java.util.Date  qicaoriqi){
		 fdtOaFwbl.setQicaoriqi(new java.sql.Timestamp(qicaoriqi.getTime()));
	}
	
	
	
}
