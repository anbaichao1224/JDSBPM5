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
			
			
				"qicaoriqi"//起草日期
			
		}		
)

@EsbBeanAnnotation(id="currFdtOaFwblPopForm",
		name="弹出编辑窗口",
	
		expressionArr="FdtOaFwblPopForm($FdtOaFwbl())",
		desc="弹出编辑窗口",
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
 					 fieldLabel = "文种",
 					 xtype = ElementFieldType.TextField,
 					 model = "local",
 					 vtype = ElementVtype.none, 
 					 validateOnBlur = true, 
 					 allowBlank = true,
 					 maxLength = 64
 	)
 	@MethodChinaName(cname="文种")
	public java.lang.String getTypeType(){
		return fdtOaFwbl.getTypeType();
	}
	
	public void setTypeType(java.lang.String typeType){
		 fdtOaFwbl.setTypeType(typeType);
	}
	
     @FormFieldDefine(id = "$currFdtOaFwbl.TypeWord", 
 					 name = "$currFdtOaFwbl.TypeWord", 
 					 fieldLabel = "字号",
 					 xtype = ElementFieldType.TextField,
 					 model = "local",
 					 vtype = ElementVtype.none, 
 					 validateOnBlur = true, 
 					 allowBlank = true,
 					 maxLength = 64
 	)
 	@MethodChinaName(cname="字号")
	public java.lang.String getTypeWord(){
		return fdtOaFwbl.getTypeWord();
	}
	
	public void setTypeWord(java.lang.String typeWord){
		 fdtOaFwbl.setTypeWord(typeWord);
	}
	
     @FormFieldDefine(id = "$currFdtOaFwbl.TypeYear", 
 					 name = "$currFdtOaFwbl.TypeYear", 
 					 fieldLabel = "年号",
 					 xtype = ElementFieldType.NumberField,
 					 model = "local",
 					 vtype = ElementVtype.none, 
 					 validateOnBlur = true, 
 					 allowBlank = true,
 					 maxLength = 22
 	)
 	@MethodChinaName(cname="年号")
	public java.lang.Integer getTypeYear(){
		return fdtOaFwbl.getTypeYear();
	}
	
	public void setTypeYear(java.lang.Integer typeYear){
		 fdtOaFwbl.setTypeYear(typeYear);
	}
	
     @FormFieldDefine(id = "$currFdtOaFwbl.TypeNum", 
 					 name = "$currFdtOaFwbl.TypeNum", 
 					 fieldLabel = "文件号",
 					 xtype = ElementFieldType.NumberField,
 					 model = "local",
 					 vtype = ElementVtype.none, 
 					 validateOnBlur = true, 
 					 allowBlank = true,
 					 maxLength = 22
 	)
 	@MethodChinaName(cname="文件号")
	public java.lang.Integer getTypeNum(){
		return fdtOaFwbl.getTypeNum();
	}
	
	public void setTypeNum(java.lang.Integer typeNum){
		 fdtOaFwbl.setTypeNum(typeNum);
	}
	
     @FormFieldDefine(id = "$currFdtOaFwbl.Miji", 
 					 name = "$currFdtOaFwbl.Miji", 
 					 fieldLabel = "密级",
 					 xtype = ElementFieldType.TextField,
 					 model = "local",
 					 vtype = ElementVtype.none, 
 					 validateOnBlur = true, 
 					 allowBlank = true,
 					 maxLength = 64
 	)
 	@MethodChinaName(cname="密级")
	public java.lang.String getMiji(){
		return fdtOaFwbl.getMiji();
	}
	
	public void setMiji(java.lang.String miji){
		 fdtOaFwbl.setMiji(miji);
	}
	
     @FormFieldDefine(id = "$currFdtOaFwbl.Baomiqixian", 
 					 name = "$currFdtOaFwbl.Baomiqixian", 
 					 fieldLabel = "保密期限",
 					 xtype = ElementFieldType.NumberField,
 					 model = "local",
 					 vtype = ElementVtype.none, 
 					 validateOnBlur = true, 
 					 allowBlank = true,
 					 maxLength = 22
 	)
 	@MethodChinaName(cname="保密期限")
	public java.lang.Integer getBaomiqixian(){
		return fdtOaFwbl.getBaomiqixian();
	}
	
	public void setBaomiqixian(java.lang.Integer baomiqixian){
		 fdtOaFwbl.setBaomiqixian(baomiqixian);
	}
	
     @FormFieldDefine(id = "$currFdtOaFwbl.Huanji", 
 					 name = "$currFdtOaFwbl.Huanji", 
 					 fieldLabel = "缓级",
 					 xtype = ElementFieldType.TextField,
 					 model = "local",
 					 vtype = ElementVtype.none, 
 					 validateOnBlur = true, 
 					 allowBlank = true,
 					 maxLength = 64
 	)
 	@MethodChinaName(cname="缓级")
	public java.lang.String getHuanji(){
		return fdtOaFwbl.getHuanji();
	}
	
	public void setHuanji(java.lang.String huanji){
		 fdtOaFwbl.setHuanji(huanji);
	}
	
     @FormFieldDefine(id = "$currFdtOaFwbl.Dingmiyiju", 
 					 name = "$currFdtOaFwbl.Dingmiyiju", 
 					 fieldLabel = "定密依据",
 					 xtype = ElementFieldType.TextArea,
 					 model = "local",
 					 vtype = ElementVtype.none, 
 					 validateOnBlur = true, 
 					 allowBlank = true,
 					 maxLength = 150
 	)
 	@MethodChinaName(cname="定密依据")
	public java.lang.String getDingmiyiju(){
		return fdtOaFwbl.getDingmiyiju();
	}
	
	public void setDingmiyiju(java.lang.String dingmiyiju){
		 fdtOaFwbl.setDingmiyiju(dingmiyiju);
	}
	
     @FormFieldDefine(id = "$currFdtOaFwbl.Qianfa", 
 					 name = "$currFdtOaFwbl.Qianfa", 
 					 fieldLabel = "签发",
 					 xtype = ElementFieldType.TextArea,
 					 model = "local",
 					 vtype = ElementVtype.none, 
 					 validateOnBlur = true, 
 					 allowBlank = true,
 					 maxLength = 1000
 	)
 	@MethodChinaName(cname="签发")
	public java.lang.String getQianfa(){
		return fdtOaFwbl.getQianfa();
	}
	
	public void setQianfa(java.lang.String qianfa){
		 fdtOaFwbl.setQianfa(qianfa);
	}
	
     @FormFieldDefine(id = "$currFdtOaFwbl.Huiqian", 
 					 name = "$currFdtOaFwbl.Huiqian", 
 					 fieldLabel = "会签",
 					 xtype = ElementFieldType.TextArea,
 					 model = "local",
 					 vtype = ElementVtype.none, 
 					 validateOnBlur = true, 
 					 allowBlank = true,
 					 maxLength = 3000
 	)
 	@MethodChinaName(cname="会签")
	public java.lang.String getHuiqian(){
		return fdtOaFwbl.getHuiqian();
	}
	
	public void setHuiqian(java.lang.String huiqian){
		 fdtOaFwbl.setHuiqian(huiqian);
	}
	
     @FormFieldDefine(id = "$currFdtOaFwbl.Biaoti", 
 					 name = "$currFdtOaFwbl.Biaoti", 
 					 fieldLabel = "标题",
 					 xtype = ElementFieldType.TextArea,
 					 model = "local",
 					 vtype = ElementVtype.none, 
 					 validateOnBlur = true, 
 					 allowBlank = true,
 					 maxLength = 500
 	)
 	@MethodChinaName(cname="标题")
	public java.lang.String getBiaoti(){
		return fdtOaFwbl.getBiaoti();
	}
	
	public void setBiaoti(java.lang.String biaoti){
		 fdtOaFwbl.setBiaoti(biaoti);
	}
	
     @FormFieldDefine(id = "$currFdtOaFwbl.Zhusong", 
 					 name = "$currFdtOaFwbl.Zhusong", 
 					 fieldLabel = "主送",
 					 xtype = ElementFieldType.TextArea,
 					 model = "local",
 					 vtype = ElementVtype.none, 
 					 validateOnBlur = true, 
 					 allowBlank = true,
 					 maxLength = 500
 	)
 	@MethodChinaName(cname="主送")
	public java.lang.String getZhusong(){
		return fdtOaFwbl.getZhusong();
	}
	
	public void setZhusong(java.lang.String zhusong){
		 fdtOaFwbl.setZhusong(zhusong);
	}
	
     @FormFieldDefine(id = "$currFdtOaFwbl.Chaobao", 
 					 name = "$currFdtOaFwbl.Chaobao", 
 					 fieldLabel = "抄报",
 					 xtype = ElementFieldType.TextArea,
 					 model = "local",
 					 vtype = ElementVtype.none, 
 					 validateOnBlur = true, 
 					 allowBlank = true,
 					 maxLength = 500
 	)
 	@MethodChinaName(cname="抄报")
	public java.lang.String getChaobao(){
		return fdtOaFwbl.getChaobao();
	}
	
	public void setChaobao(java.lang.String chaobao){
		 fdtOaFwbl.setChaobao(chaobao);
	}
	
     @FormFieldDefine(id = "$currFdtOaFwbl.Chaosong", 
 					 name = "$currFdtOaFwbl.Chaosong", 
 					 fieldLabel = "抄送",
 					 xtype = ElementFieldType.TextArea,
 					 model = "local",
 					 vtype = ElementVtype.none, 
 					 validateOnBlur = true, 
 					 allowBlank = true,
 					 maxLength = 500
 	)
 	@MethodChinaName(cname="抄送")
	public java.lang.String getChaosong(){
		return fdtOaFwbl.getChaosong();
	}
	
	public void setChaosong(java.lang.String chaosong){
		 fdtOaFwbl.setChaosong(chaosong);
	}
	
     @FormFieldDefine(id = "$currFdtOaFwbl.Nigaodanwei", 
 					 name = "$currFdtOaFwbl.Nigaodanwei", 
 					 fieldLabel = "拟稿单位",
 					 xtype = ElementFieldType.TextField,
 					 model = "local",
 					 vtype = ElementVtype.none, 
 					 validateOnBlur = true, 
 					 allowBlank = true,
 					 maxLength = 100
 	)
 	@MethodChinaName(cname="拟稿单位")
	public java.lang.String getNigaodanwei(){
		return fdtOaFwbl.getNigaodanwei();
	}
	
	public void setNigaodanwei(java.lang.String nigaodanwei){
		 fdtOaFwbl.setNigaodanwei(nigaodanwei);
	}
	
     @FormFieldDefine(id = "$currFdtOaFwbl.Nigao", 
 					 name = "$currFdtOaFwbl.Nigao", 
 					 fieldLabel = "拟稿",
 					 xtype = ElementFieldType.TextField,
 					 model = "local",
 					 vtype = ElementVtype.none, 
 					 validateOnBlur = true, 
 					 allowBlank = true,
 					 maxLength = 64
 	)
 	@MethodChinaName(cname="拟稿")
	public java.lang.String getNigao(){
		return fdtOaFwbl.getNigao();
	}
	
	public void setNigao(java.lang.String nigao){
		 fdtOaFwbl.setNigao(nigao);
	}
	
     @FormFieldDefine(id = "$currFdtOaFwbl.Hegao", 
 					 name = "$currFdtOaFwbl.Hegao", 
 					 fieldLabel = "核稿",
 					 xtype = ElementFieldType.TextField,
 					 model = "local",
 					 vtype = ElementVtype.none, 
 					 validateOnBlur = true, 
 					 allowBlank = true,
 					 maxLength = 64
 	)
 	@MethodChinaName(cname="核稿")
	public java.lang.String getHegao(){
		return fdtOaFwbl.getHegao();
	}
	
	public void setHegao(java.lang.String hegao){
		 fdtOaFwbl.setHegao(hegao);
	}
	
     @FormFieldDefine(id = "$currFdtOaFwbl.Yinshua", 
 					 name = "$currFdtOaFwbl.Yinshua", 
 					 fieldLabel = "印刷",
 					 xtype = ElementFieldType.TextField,
 					 model = "local",
 					 vtype = ElementVtype.none, 
 					 validateOnBlur = true, 
 					 allowBlank = true,
 					 maxLength = 64
 	)
 	@MethodChinaName(cname="印刷")
	public java.lang.String getYinshua(){
		return fdtOaFwbl.getYinshua();
	}
	
	public void setYinshua(java.lang.String yinshua){
		 fdtOaFwbl.setYinshua(yinshua);
	}
	
     @FormFieldDefine(id = "$currFdtOaFwbl.Jiaodui", 
 					 name = "$currFdtOaFwbl.Jiaodui", 
 					 fieldLabel = "校对",
 					 xtype = ElementFieldType.TextField,
 					 model = "local",
 					 vtype = ElementVtype.none, 
 					 validateOnBlur = true, 
 					 allowBlank = true,
 					 maxLength = 64
 	)
 	@MethodChinaName(cname="校对")
	public java.lang.String getJiaodui(){
		return fdtOaFwbl.getJiaodui();
	}
	
	public void setJiaodui(java.lang.String jiaodui){
		 fdtOaFwbl.setJiaodui(jiaodui);
	}
	
     @FormFieldDefine(id = "$currFdtOaFwbl.Fenshu", 
 					 name = "$currFdtOaFwbl.Fenshu", 
 					 fieldLabel = "份数",
 					 xtype = ElementFieldType.NumberField,
 					 model = "local",
 					 vtype = ElementVtype.none, 
 					 validateOnBlur = true, 
 					 allowBlank = true,
 					 maxLength = 22
 	)
 	@MethodChinaName(cname="份数")
	public java.lang.Integer getFenshu(){
		return fdtOaFwbl.getFenshu();
	}
	
	public void setFenshu(java.lang.Integer fenshu){
		 fdtOaFwbl.setFenshu(fenshu);
	}
	
     @FormFieldDefine(id = "$currFdtOaFwbl.Zhutici", 
 					 name = "$currFdtOaFwbl.Zhutici", 
 					 fieldLabel = "主题词",
 					 xtype = ElementFieldType.TextField,
 					 model = "local",
 					 vtype = ElementVtype.none, 
 					 validateOnBlur = true, 
 					 allowBlank = true,
 					 maxLength = 100
 	)
 	@MethodChinaName(cname="主题词")
	public java.lang.String getZhutici(){
		return fdtOaFwbl.getZhutici();
	}
	
	public void setZhutici(java.lang.String zhutici){
		 fdtOaFwbl.setZhutici(zhutici);
	}
	
	
	@FormFieldDefine(id = "$currFdtOaFwbl.Qicaoriqi", 
 					 name = "$currFdtOaFwbl.Qicaoriqi", 
 					 fieldLabel = "起草日期",
 					 xtype = ElementFieldType.Hidden,
 					 model = "local",
 					 vtype = ElementVtype.none, 
 					 validateOnBlur = true, 
 					 allowBlank = true,
 					 maxLength = 7
 	)
 	@MethodChinaName(cname="起草日期")
	public java.util.Date  getQicaoriqi(){
		return new java.util.Date (fdtOaFwbl.getQicaoriqi().getTime());
	}
	
	public void setQicaoriqi(java.util.Date  qicaoriqi){
		 fdtOaFwbl.setQicaoriqi(new java.sql.Timestamp(qicaoriqi.getTime()));
	}
	
	
	
}
