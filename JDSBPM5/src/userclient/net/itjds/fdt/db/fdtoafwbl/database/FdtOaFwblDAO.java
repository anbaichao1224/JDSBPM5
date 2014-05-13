package net.itjds.fdt.db.fdtoafwbl.database;

import java.io.Serializable;

import net.itjds.common.cache.CacheSizes;
import net.itjds.common.cache.Cacheable;
import net.itjds.bpm.data.xmlproxy.EsbBeanAnnotation;
import net.itjds.j2ee.dao.annotation.DBField;
import net.itjds.j2ee.dao.annotation.DBTable;
import net.itjds.usm2.constants.USMConstants;

import net.itjds.common.logging.Log;
import net.itjds.common.logging.LogFactory;
import net.itjds.j2ee.dao.MethodChinaName;

import net.itjds.fdt.db.fdtoafwbl.inner.*;


	@EsbBeanAnnotation(
		id = "FdtOaFwbl", 
		name = "DAO映射对象(FdtOaFwbl)", 
		expressionArr = "GetCurrData(\"FdtOaFwbl\",R(\"FdtOaFwbl.uuid\"))", 
		desc = "获取局文，内部回函，党组文数据库映射对象", 
		dataType = "action"
    )	
   
	@DBTable(tableName = "FDT_OA_FWBL", primaryKey="UUID",cname="局文，内部回函，党组文" ,schema="org" )
	public class FdtOaFwblDAO implements EIFdtOaFwbl , Cacheable, Serializable{
	
	   private static final Log log = LogFactory.getLog(USMConstants.CONFIG_KEY,
			FdtOaFwblDAO.class);
		@DBField(dbFieldName = "UUID",length = 64,dbType="VARCHAR2",isNull=false)
		private java.lang.String uuid;
		private boolean uuid_is_modified = false;
		private boolean uuid_is_initialized = false;		
		
		@DBField(dbFieldName = "PROCESSINST_ID",length = 64,dbType="VARCHAR2")
		private java.lang.String processinstId;
		private boolean processinstId_is_modified = false;
		private boolean processinstId_is_initialized = false;		
		
		@DBField(dbFieldName = "ACTIVITYINST_ID",length = 64,dbType="VARCHAR2")
		private java.lang.String activityinstId;
		private boolean activityinstId_is_modified = false;
		private boolean activityinstId_is_initialized = false;		
		
		@DBField(dbFieldName = "TYPE_TYPE",length = 64,dbType="VARCHAR2")
		private java.lang.String typeType;
		private boolean typeType_is_modified = false;
		private boolean typeType_is_initialized = false;		
		
		@DBField(dbFieldName = "TYPE_WORD",length = 64,dbType="VARCHAR2")
		private java.lang.String typeWord;
		private boolean typeWord_is_modified = false;
		private boolean typeWord_is_initialized = false;		
		
		@DBField(dbFieldName = "TYPE_YEAR",length = 22,dbType="NUMBER")
		private java.lang.Integer typeYear;
		private boolean typeYear_is_modified = false;
		private boolean typeYear_is_initialized = false;		
		
		@DBField(dbFieldName = "TYPE_NUM",length = 22,dbType="NUMBER")
		private java.lang.Integer typeNum;
		private boolean typeNum_is_modified = false;
		private boolean typeNum_is_initialized = false;		
		
		@DBField(dbFieldName = "MIJI",length = 64,dbType="VARCHAR2")
		private java.lang.String miji;
		private boolean miji_is_modified = false;
		private boolean miji_is_initialized = false;		
		
		@DBField(dbFieldName = "BAOMIQIXIAN",length = 22,dbType="NUMBER")
		private java.lang.Integer baomiqixian;
		private boolean baomiqixian_is_modified = false;
		private boolean baomiqixian_is_initialized = false;		
		
		@DBField(dbFieldName = "HUANJI",length = 64,dbType="VARCHAR2")
		private java.lang.String huanji;
		private boolean huanji_is_modified = false;
		private boolean huanji_is_initialized = false;		
		
		@DBField(dbFieldName = "DINGMIYIJU",length = 150,dbType="VARCHAR2")
		private java.lang.String dingmiyiju;
		private boolean dingmiyiju_is_modified = false;
		private boolean dingmiyiju_is_initialized = false;		
		
		@DBField(dbFieldName = "QIANFA",length = 1000,dbType="VARCHAR2")
		private java.lang.String qianfa;
		private boolean qianfa_is_modified = false;
		private boolean qianfa_is_initialized = false;		
		
		@DBField(dbFieldName = "HUIQIAN",length = 3000,dbType="VARCHAR2")
		private java.lang.String huiqian;
		private boolean huiqian_is_modified = false;
		private boolean huiqian_is_initialized = false;		
		
		@DBField(dbFieldName = "BIAOTI",length = 500,dbType="VARCHAR2")
		private java.lang.String biaoti;
		private boolean biaoti_is_modified = false;
		private boolean biaoti_is_initialized = false;		
		
		@DBField(dbFieldName = "ZHUSONG",length = 500,dbType="VARCHAR2")
		private java.lang.String zhusong;
		private boolean zhusong_is_modified = false;
		private boolean zhusong_is_initialized = false;		
		
		@DBField(dbFieldName = "CHAOBAO",length = 500,dbType="VARCHAR2")
		private java.lang.String chaobao;
		private boolean chaobao_is_modified = false;
		private boolean chaobao_is_initialized = false;		
		
		@DBField(dbFieldName = "CHAOSONG",length = 500,dbType="VARCHAR2")
		private java.lang.String chaosong;
		private boolean chaosong_is_modified = false;
		private boolean chaosong_is_initialized = false;		
		
		@DBField(dbFieldName = "NIGAODANWEI",length = 100,dbType="VARCHAR2")
		private java.lang.String nigaodanwei;
		private boolean nigaodanwei_is_modified = false;
		private boolean nigaodanwei_is_initialized = false;		
		
		@DBField(dbFieldName = "NIGAO",length = 64,dbType="VARCHAR2")
		private java.lang.String nigao;
		private boolean nigao_is_modified = false;
		private boolean nigao_is_initialized = false;		
		
		@DBField(dbFieldName = "HEGAO",length = 64,dbType="VARCHAR2")
		private java.lang.String hegao;
		private boolean hegao_is_modified = false;
		private boolean hegao_is_initialized = false;		
		
		@DBField(dbFieldName = "YINSHUA",length = 64,dbType="VARCHAR2")
		private java.lang.String yinshua;
		private boolean yinshua_is_modified = false;
		private boolean yinshua_is_initialized = false;		
		
		@DBField(dbFieldName = "JIAODUI",length = 64,dbType="VARCHAR2")
		private java.lang.String jiaodui;
		private boolean jiaodui_is_modified = false;
		private boolean jiaodui_is_initialized = false;		
		
		@DBField(dbFieldName = "FENSHU",length = 22,dbType="NUMBER")
		private java.lang.Integer fenshu;
		private boolean fenshu_is_modified = false;
		private boolean fenshu_is_initialized = false;		
		
		@DBField(dbFieldName = "ZHUTICI",length = 100,dbType="VARCHAR2")
		private java.lang.String zhutici;
		private boolean zhutici_is_modified = false;
		private boolean zhutici_is_initialized = false;		
		
		@DBField(dbFieldName = "QICAORIQI",length = 7,dbType="DATE")
		private java.sql.Timestamp qicaoriqi;
		private boolean qicaoriqi_is_modified = false;
		private boolean qicaoriqi_is_initialized = false;		
		
	   	private boolean _isNew = true;
	   
	
		public boolean isNew() {
			return _isNew;
		}
	
		public void setIsNew(boolean isNew) {
			this._isNew = isNew;
		}
		   
   
	   	
	   	public boolean isUuidModified() {
			return uuid_is_modified;
		}
	   	
		public boolean isUuidInitialized() {
			return uuid_is_initialized;
		}
	   	
	   	@MethodChinaName(cname="UUID")
		public java.lang.String getUuid(){
		   return uuid; 
		}
		public void setUuid(java.lang.String  newVal){
			if ((newVal != null && newVal.equals(this.uuid) == true)
					|| (newVal == null && this.uuid == null))
				return;
			this.uuid = newVal;
			uuid_is_modified = true;
			uuid_is_initialized = true;
		}
	   	
	   	public boolean isProcessinstIdModified() {
			return processinstId_is_modified;
		}
	   	
		public boolean isProcessinstIdInitialized() {
			return processinstId_is_initialized;
		}
	   	
	   	@MethodChinaName(cname="PROCESSINST_ID")
		public java.lang.String getProcessinstId(){
		   return processinstId; 
		}
		public void setProcessinstId(java.lang.String  newVal){
			if ((newVal != null && newVal.equals(this.processinstId) == true)
					|| (newVal == null && this.processinstId == null))
				return;
			this.processinstId = newVal;
			processinstId_is_modified = true;
			processinstId_is_initialized = true;
		}
	   	
	   	public boolean isActivityinstIdModified() {
			return activityinstId_is_modified;
		}
	   	
		public boolean isActivityinstIdInitialized() {
			return activityinstId_is_initialized;
		}
	   	
	   	@MethodChinaName(cname="ACTIVITYINST_ID")
		public java.lang.String getActivityinstId(){
		   return activityinstId; 
		}
		public void setActivityinstId(java.lang.String  newVal){
			if ((newVal != null && newVal.equals(this.activityinstId) == true)
					|| (newVal == null && this.activityinstId == null))
				return;
			this.activityinstId = newVal;
			activityinstId_is_modified = true;
			activityinstId_is_initialized = true;
		}
	   	
	   	public boolean isTypeTypeModified() {
			return typeType_is_modified;
		}
	   	
		public boolean isTypeTypeInitialized() {
			return typeType_is_initialized;
		}
	   	
	   	@MethodChinaName(cname="文种")
		public java.lang.String getTypeType(){
		   return typeType; 
		}
		public void setTypeType(java.lang.String  newVal){
			if ((newVal != null && newVal.equals(this.typeType) == true)
					|| (newVal == null && this.typeType == null))
				return;
			this.typeType = newVal;
			typeType_is_modified = true;
			typeType_is_initialized = true;
		}
	   	
	   	public boolean isTypeWordModified() {
			return typeWord_is_modified;
		}
	   	
		public boolean isTypeWordInitialized() {
			return typeWord_is_initialized;
		}
	   	
	   	@MethodChinaName(cname="字号")
		public java.lang.String getTypeWord(){
		   return typeWord; 
		}
		public void setTypeWord(java.lang.String  newVal){
			if ((newVal != null && newVal.equals(this.typeWord) == true)
					|| (newVal == null && this.typeWord == null))
				return;
			this.typeWord = newVal;
			typeWord_is_modified = true;
			typeWord_is_initialized = true;
		}
	   	
	   	public boolean isTypeYearModified() {
			return typeYear_is_modified;
		}
	   	
		public boolean isTypeYearInitialized() {
			return typeYear_is_initialized;
		}
	   	
	   	@MethodChinaName(cname="年号")
		public java.lang.Integer getTypeYear(){
		   return typeYear; 
		}
		public void setTypeYear(java.lang.Integer  newVal){
			if ((newVal != null && newVal.equals(this.typeYear) == true)
					|| (newVal == null && this.typeYear == null))
				return;
			this.typeYear = newVal;
			typeYear_is_modified = true;
			typeYear_is_initialized = true;
		}
	   	
	   	public boolean isTypeNumModified() {
			return typeNum_is_modified;
		}
	   	
		public boolean isTypeNumInitialized() {
			return typeNum_is_initialized;
		}
	   	
	   	@MethodChinaName(cname="文件号")
		public java.lang.Integer getTypeNum(){
		   return typeNum; 
		}
		public void setTypeNum(java.lang.Integer  newVal){
			if ((newVal != null && newVal.equals(this.typeNum) == true)
					|| (newVal == null && this.typeNum == null))
				return;
			this.typeNum = newVal;
			typeNum_is_modified = true;
			typeNum_is_initialized = true;
		}
	   	
	   	public boolean isMijiModified() {
			return miji_is_modified;
		}
	   	
		public boolean isMijiInitialized() {
			return miji_is_initialized;
		}
	   	
	   	@MethodChinaName(cname="密级")
		public java.lang.String getMiji(){
		   return miji; 
		}
		public void setMiji(java.lang.String  newVal){
			if ((newVal != null && newVal.equals(this.miji) == true)
					|| (newVal == null && this.miji == null))
				return;
			this.miji = newVal;
			miji_is_modified = true;
			miji_is_initialized = true;
		}
	   	
	   	public boolean isBaomiqixianModified() {
			return baomiqixian_is_modified;
		}
	   	
		public boolean isBaomiqixianInitialized() {
			return baomiqixian_is_initialized;
		}
	   	
	   	@MethodChinaName(cname="保密期限")
		public java.lang.Integer getBaomiqixian(){
		   return baomiqixian; 
		}
		public void setBaomiqixian(java.lang.Integer  newVal){
			if ((newVal != null && newVal.equals(this.baomiqixian) == true)
					|| (newVal == null && this.baomiqixian == null))
				return;
			this.baomiqixian = newVal;
			baomiqixian_is_modified = true;
			baomiqixian_is_initialized = true;
		}
	   	
	   	public boolean isHuanjiModified() {
			return huanji_is_modified;
		}
	   	
		public boolean isHuanjiInitialized() {
			return huanji_is_initialized;
		}
	   	
	   	@MethodChinaName(cname="缓级")
		public java.lang.String getHuanji(){
		   return huanji; 
		}
		public void setHuanji(java.lang.String  newVal){
			if ((newVal != null && newVal.equals(this.huanji) == true)
					|| (newVal == null && this.huanji == null))
				return;
			this.huanji = newVal;
			huanji_is_modified = true;
			huanji_is_initialized = true;
		}
	   	
	   	public boolean isDingmiyijuModified() {
			return dingmiyiju_is_modified;
		}
	   	
		public boolean isDingmiyijuInitialized() {
			return dingmiyiju_is_initialized;
		}
	   	
	   	@MethodChinaName(cname="定密依据")
		public java.lang.String getDingmiyiju(){
		   return dingmiyiju; 
		}
		public void setDingmiyiju(java.lang.String  newVal){
			if ((newVal != null && newVal.equals(this.dingmiyiju) == true)
					|| (newVal == null && this.dingmiyiju == null))
				return;
			this.dingmiyiju = newVal;
			dingmiyiju_is_modified = true;
			dingmiyiju_is_initialized = true;
		}
	   	
	   	public boolean isQianfaModified() {
			return qianfa_is_modified;
		}
	   	
		public boolean isQianfaInitialized() {
			return qianfa_is_initialized;
		}
	   	
	   	@MethodChinaName(cname="签发")
		public java.lang.String getQianfa(){
		   return qianfa; 
		}
		public void setQianfa(java.lang.String  newVal){
			if ((newVal != null && newVal.equals(this.qianfa) == true)
					|| (newVal == null && this.qianfa == null))
				return;
			this.qianfa = newVal;
			qianfa_is_modified = true;
			qianfa_is_initialized = true;
		}
	   	
	   	public boolean isHuiqianModified() {
			return huiqian_is_modified;
		}
	   	
		public boolean isHuiqianInitialized() {
			return huiqian_is_initialized;
		}
	   	
	   	@MethodChinaName(cname="会签")
		public java.lang.String getHuiqian(){
		   return huiqian; 
		}
		public void setHuiqian(java.lang.String  newVal){
			if ((newVal != null && newVal.equals(this.huiqian) == true)
					|| (newVal == null && this.huiqian == null))
				return;
			this.huiqian = newVal;
			huiqian_is_modified = true;
			huiqian_is_initialized = true;
		}
	   	
	   	public boolean isBiaotiModified() {
			return biaoti_is_modified;
		}
	   	
		public boolean isBiaotiInitialized() {
			return biaoti_is_initialized;
		}
	   	
	   	@MethodChinaName(cname="标题")
		public java.lang.String getBiaoti(){
		   return biaoti; 
		}
		public void setBiaoti(java.lang.String  newVal){
			if ((newVal != null && newVal.equals(this.biaoti) == true)
					|| (newVal == null && this.biaoti == null))
				return;
			this.biaoti = newVal;
			biaoti_is_modified = true;
			biaoti_is_initialized = true;
		}
	   	
	   	public boolean isZhusongModified() {
			return zhusong_is_modified;
		}
	   	
		public boolean isZhusongInitialized() {
			return zhusong_is_initialized;
		}
	   	
	   	@MethodChinaName(cname="主送")
		public java.lang.String getZhusong(){
		   return zhusong; 
		}
		public void setZhusong(java.lang.String  newVal){
			if ((newVal != null && newVal.equals(this.zhusong) == true)
					|| (newVal == null && this.zhusong == null))
				return;
			this.zhusong = newVal;
			zhusong_is_modified = true;
			zhusong_is_initialized = true;
		}
	   	
	   	public boolean isChaobaoModified() {
			return chaobao_is_modified;
		}
	   	
		public boolean isChaobaoInitialized() {
			return chaobao_is_initialized;
		}
	   	
	   	@MethodChinaName(cname="抄报")
		public java.lang.String getChaobao(){
		   return chaobao; 
		}
		public void setChaobao(java.lang.String  newVal){
			if ((newVal != null && newVal.equals(this.chaobao) == true)
					|| (newVal == null && this.chaobao == null))
				return;
			this.chaobao = newVal;
			chaobao_is_modified = true;
			chaobao_is_initialized = true;
		}
	   	
	   	public boolean isChaosongModified() {
			return chaosong_is_modified;
		}
	   	
		public boolean isChaosongInitialized() {
			return chaosong_is_initialized;
		}
	   	
	   	@MethodChinaName(cname="抄送")
		public java.lang.String getChaosong(){
		   return chaosong; 
		}
		public void setChaosong(java.lang.String  newVal){
			if ((newVal != null && newVal.equals(this.chaosong) == true)
					|| (newVal == null && this.chaosong == null))
				return;
			this.chaosong = newVal;
			chaosong_is_modified = true;
			chaosong_is_initialized = true;
		}
	   	
	   	public boolean isNigaodanweiModified() {
			return nigaodanwei_is_modified;
		}
	   	
		public boolean isNigaodanweiInitialized() {
			return nigaodanwei_is_initialized;
		}
	   	
	   	@MethodChinaName(cname="拟稿单位")
		public java.lang.String getNigaodanwei(){
		   return nigaodanwei; 
		}
		public void setNigaodanwei(java.lang.String  newVal){
			if ((newVal != null && newVal.equals(this.nigaodanwei) == true)
					|| (newVal == null && this.nigaodanwei == null))
				return;
			this.nigaodanwei = newVal;
			nigaodanwei_is_modified = true;
			nigaodanwei_is_initialized = true;
		}
	   	
	   	public boolean isNigaoModified() {
			return nigao_is_modified;
		}
	   	
		public boolean isNigaoInitialized() {
			return nigao_is_initialized;
		}
	   	
	   	@MethodChinaName(cname="拟稿")
		public java.lang.String getNigao(){
		   return nigao; 
		}
		public void setNigao(java.lang.String  newVal){
			if ((newVal != null && newVal.equals(this.nigao) == true)
					|| (newVal == null && this.nigao == null))
				return;
			this.nigao = newVal;
			nigao_is_modified = true;
			nigao_is_initialized = true;
		}
	   	
	   	public boolean isHegaoModified() {
			return hegao_is_modified;
		}
	   	
		public boolean isHegaoInitialized() {
			return hegao_is_initialized;
		}
	   	
	   	@MethodChinaName(cname="核稿")
		public java.lang.String getHegao(){
		   return hegao; 
		}
		public void setHegao(java.lang.String  newVal){
			if ((newVal != null && newVal.equals(this.hegao) == true)
					|| (newVal == null && this.hegao == null))
				return;
			this.hegao = newVal;
			hegao_is_modified = true;
			hegao_is_initialized = true;
		}
	   	
	   	public boolean isYinshuaModified() {
			return yinshua_is_modified;
		}
	   	
		public boolean isYinshuaInitialized() {
			return yinshua_is_initialized;
		}
	   	
	   	@MethodChinaName(cname="印刷")
		public java.lang.String getYinshua(){
		   return yinshua; 
		}
		public void setYinshua(java.lang.String  newVal){
			if ((newVal != null && newVal.equals(this.yinshua) == true)
					|| (newVal == null && this.yinshua == null))
				return;
			this.yinshua = newVal;
			yinshua_is_modified = true;
			yinshua_is_initialized = true;
		}
	   	
	   	public boolean isJiaoduiModified() {
			return jiaodui_is_modified;
		}
	   	
		public boolean isJiaoduiInitialized() {
			return jiaodui_is_initialized;
		}
	   	
	   	@MethodChinaName(cname="校对")
		public java.lang.String getJiaodui(){
		   return jiaodui; 
		}
		public void setJiaodui(java.lang.String  newVal){
			if ((newVal != null && newVal.equals(this.jiaodui) == true)
					|| (newVal == null && this.jiaodui == null))
				return;
			this.jiaodui = newVal;
			jiaodui_is_modified = true;
			jiaodui_is_initialized = true;
		}
	   	
	   	public boolean isFenshuModified() {
			return fenshu_is_modified;
		}
	   	
		public boolean isFenshuInitialized() {
			return fenshu_is_initialized;
		}
	   	
	   	@MethodChinaName(cname="份数")
		public java.lang.Integer getFenshu(){
		   return fenshu; 
		}
		public void setFenshu(java.lang.Integer  newVal){
			if ((newVal != null && newVal.equals(this.fenshu) == true)
					|| (newVal == null && this.fenshu == null))
				return;
			this.fenshu = newVal;
			fenshu_is_modified = true;
			fenshu_is_initialized = true;
		}
	   	
	   	public boolean isZhuticiModified() {
			return zhutici_is_modified;
		}
	   	
		public boolean isZhuticiInitialized() {
			return zhutici_is_initialized;
		}
	   	
	   	@MethodChinaName(cname="主题词")
		public java.lang.String getZhutici(){
		   return zhutici; 
		}
		public void setZhutici(java.lang.String  newVal){
			if ((newVal != null && newVal.equals(this.zhutici) == true)
					|| (newVal == null && this.zhutici == null))
				return;
			this.zhutici = newVal;
			zhutici_is_modified = true;
			zhutici_is_initialized = true;
		}
	   	
	   	public boolean isQicaoriqiModified() {
			return qicaoriqi_is_modified;
		}
	   	
		public boolean isQicaoriqiInitialized() {
			return qicaoriqi_is_initialized;
		}
	   	
	   	@MethodChinaName(cname="起草日期")
		public java.sql.Timestamp getQicaoriqi(){
		   return qicaoriqi; 
		}
		public void setQicaoriqi(java.sql.Timestamp  newVal){
			if ((newVal != null && newVal.equals(this.qicaoriqi) == true)
					|| (newVal == null && this.qicaoriqi == null))
				return;
			this.qicaoriqi = newVal;
			qicaoriqi_is_modified = true;
			qicaoriqi_is_initialized = true;
		}
	   
	    public boolean isModified() {
	      return
	      uuid_is_modified||
	      
	      processinstId_is_modified||
	      
	      activityinstId_is_modified||
	      
	      typeType_is_modified||
	      
	      typeWord_is_modified||
	      
	      typeYear_is_modified||
	      
	      typeNum_is_modified||
	      
	      miji_is_modified||
	      
	      baomiqixian_is_modified||
	      
	      huanji_is_modified||
	      
	      dingmiyiju_is_modified||
	      
	      qianfa_is_modified||
	      
	      huiqian_is_modified||
	      
	      biaoti_is_modified||
	      
	      zhusong_is_modified||
	      
	      chaobao_is_modified||
	      
	      chaosong_is_modified||
	      
	      nigaodanwei_is_modified||
	      
	      nigao_is_modified||
	      
	      hegao_is_modified||
	      
	      yinshua_is_modified||
	      
	      jiaodui_is_modified||
	      
	      fenshu_is_modified||
	      
	      zhutici_is_modified||
	      
	      qicaoriqi_is_modified||
	      qicaoriqi_is_modified;
	   }
	   
	   public void resetIsModified() {
		 uuid_is_modified = false;
		 processinstId_is_modified = false;
		 activityinstId_is_modified = false;
		 typeType_is_modified = false;
		 typeWord_is_modified = false;
		 typeYear_is_modified = false;
		 typeNum_is_modified = false;
		 miji_is_modified = false;
		 baomiqixian_is_modified = false;
		 huanji_is_modified = false;
		 dingmiyiju_is_modified = false;
		 qianfa_is_modified = false;
		 huiqian_is_modified = false;
		 biaoti_is_modified = false;
		 zhusong_is_modified = false;
		 chaobao_is_modified = false;
		 chaosong_is_modified = false;
		 nigaodanwei_is_modified = false;
		 nigao_is_modified = false;
		 hegao_is_modified = false;
		 yinshua_is_modified = false;
		 jiaodui_is_modified = false;
		 fenshu_is_modified = false;
		 zhutici_is_modified = false;
		 qicaoriqi_is_modified = false;
	   }
	   
	   public void copy(FdtOaFwblDAO bean) {
			 setUuid(bean.getUuid());
			 setProcessinstId(bean.getProcessinstId());
			 setActivityinstId(bean.getActivityinstId());
			 setTypeType(bean.getTypeType());
			 setTypeWord(bean.getTypeWord());
			 setTypeYear(bean.getTypeYear());
			 setTypeNum(bean.getTypeNum());
			 setMiji(bean.getMiji());
			 setBaomiqixian(bean.getBaomiqixian());
			 setHuanji(bean.getHuanji());
			 setDingmiyiju(bean.getDingmiyiju());
			 setQianfa(bean.getQianfa());
			 setHuiqian(bean.getHuiqian());
			 setBiaoti(bean.getBiaoti());
			 setZhusong(bean.getZhusong());
			 setChaobao(bean.getChaobao());
			 setChaosong(bean.getChaosong());
			 setNigaodanwei(bean.getNigaodanwei());
			 setNigao(bean.getNigao());
			 setHegao(bean.getHegao());
			 setYinshua(bean.getYinshua());
			 setJiaodui(bean.getJiaodui());
			 setFenshu(bean.getFenshu());
			 setZhutici(bean.getZhutici());
			 setQicaoriqi(bean.getQicaoriqi());
		}
		
	 public int getCachedSize() {
		int size = 0;
		size += CacheSizes.sizeOfObject(uuid);
		size += CacheSizes.sizeOfObject(processinstId);
		size += CacheSizes.sizeOfObject(activityinstId);
		size += CacheSizes.sizeOfObject(typeType);
		size += CacheSizes.sizeOfObject(typeWord);
		size += CacheSizes.sizeOfObject(typeYear);
		size += CacheSizes.sizeOfObject(typeNum);
		size += CacheSizes.sizeOfObject(miji);
		size += CacheSizes.sizeOfObject(baomiqixian);
		size += CacheSizes.sizeOfObject(huanji);
		size += CacheSizes.sizeOfObject(dingmiyiju);
		size += CacheSizes.sizeOfObject(qianfa);
		size += CacheSizes.sizeOfObject(huiqian);
		size += CacheSizes.sizeOfObject(biaoti);
		size += CacheSizes.sizeOfObject(zhusong);
		size += CacheSizes.sizeOfObject(chaobao);
		size += CacheSizes.sizeOfObject(chaosong);
		size += CacheSizes.sizeOfObject(nigaodanwei);
		size += CacheSizes.sizeOfObject(nigao);
		size += CacheSizes.sizeOfObject(hegao);
		size += CacheSizes.sizeOfObject(yinshua);
		size += CacheSizes.sizeOfObject(jiaodui);
		size += CacheSizes.sizeOfObject(fenshu);
		size += CacheSizes.sizeOfObject(zhutici);
		size += CacheSizes.sizeOfObject(qicaoriqi);
	
		return size;
	}
	
	public String toString() {
	return "\n[FdtOaFwbl] "
				+ "\n - FDT_OA_FWBL.UUID = "
				+ (uuid_is_initialized ? ("["
						+ uuid.toString() + "]") : "not initialized")
				+ ""
					+ "\n - FDT_OA_FWBL.PROCESSINST_ID = "
				+ (processinstId_is_initialized ? ("["
						+ processinstId.toString() + "]") : "not initialized")
				+ ""
					+ "\n - FDT_OA_FWBL.ACTIVITYINST_ID = "
				+ (activityinstId_is_initialized ? ("["
						+ activityinstId.toString() + "]") : "not initialized")
				+ ""
					+ "\n - FDT_OA_FWBL.TYPE_TYPE = "
				+ (typeType_is_initialized ? ("["
						+ typeType.toString() + "]") : "not initialized")
				+ ""
					+ "\n - FDT_OA_FWBL.TYPE_WORD = "
				+ (typeWord_is_initialized ? ("["
						+ typeWord.toString() + "]") : "not initialized")
				+ ""
					+ "\n - FDT_OA_FWBL.TYPE_YEAR = "
				+ (typeYear_is_initialized ? ("["
						+ typeYear.toString() + "]") : "not initialized")
				+ ""
					+ "\n - FDT_OA_FWBL.TYPE_NUM = "
				+ (typeNum_is_initialized ? ("["
						+ typeNum.toString() + "]") : "not initialized")
				+ ""
					+ "\n - FDT_OA_FWBL.MIJI = "
				+ (miji_is_initialized ? ("["
						+ miji.toString() + "]") : "not initialized")
				+ ""
					+ "\n - FDT_OA_FWBL.BAOMIQIXIAN = "
				+ (baomiqixian_is_initialized ? ("["
						+ baomiqixian.toString() + "]") : "not initialized")
				+ ""
					+ "\n - FDT_OA_FWBL.HUANJI = "
				+ (huanji_is_initialized ? ("["
						+ huanji.toString() + "]") : "not initialized")
				+ ""
					+ "\n - FDT_OA_FWBL.DINGMIYIJU = "
				+ (dingmiyiju_is_initialized ? ("["
						+ dingmiyiju.toString() + "]") : "not initialized")
				+ ""
					+ "\n - FDT_OA_FWBL.QIANFA = "
				+ (qianfa_is_initialized ? ("["
						+ qianfa.toString() + "]") : "not initialized")
				+ ""
					+ "\n - FDT_OA_FWBL.HUIQIAN = "
				+ (huiqian_is_initialized ? ("["
						+ huiqian.toString() + "]") : "not initialized")
				+ ""
					+ "\n - FDT_OA_FWBL.BIAOTI = "
				+ (biaoti_is_initialized ? ("["
						+ biaoti.toString() + "]") : "not initialized")
				+ ""
					+ "\n - FDT_OA_FWBL.ZHUSONG = "
				+ (zhusong_is_initialized ? ("["
						+ zhusong.toString() + "]") : "not initialized")
				+ ""
					+ "\n - FDT_OA_FWBL.CHAOBAO = "
				+ (chaobao_is_initialized ? ("["
						+ chaobao.toString() + "]") : "not initialized")
				+ ""
					+ "\n - FDT_OA_FWBL.CHAOSONG = "
				+ (chaosong_is_initialized ? ("["
						+ chaosong.toString() + "]") : "not initialized")
				+ ""
					+ "\n - FDT_OA_FWBL.NIGAODANWEI = "
				+ (nigaodanwei_is_initialized ? ("["
						+ nigaodanwei.toString() + "]") : "not initialized")
				+ ""
					+ "\n - FDT_OA_FWBL.NIGAO = "
				+ (nigao_is_initialized ? ("["
						+ nigao.toString() + "]") : "not initialized")
				+ ""
					+ "\n - FDT_OA_FWBL.HEGAO = "
				+ (hegao_is_initialized ? ("["
						+ hegao.toString() + "]") : "not initialized")
				+ ""
					+ "\n - FDT_OA_FWBL.YINSHUA = "
				+ (yinshua_is_initialized ? ("["
						+ yinshua.toString() + "]") : "not initialized")
				+ ""
					+ "\n - FDT_OA_FWBL.JIAODUI = "
				+ (jiaodui_is_initialized ? ("["
						+ jiaodui.toString() + "]") : "not initialized")
				+ ""
					+ "\n - FDT_OA_FWBL.FENSHU = "
				+ (fenshu_is_initialized ? ("["
						+ fenshu.toString() + "]") : "not initialized")
				+ ""
					+ "\n - FDT_OA_FWBL.ZHUTICI = "
				+ (zhutici_is_initialized ? ("["
						+ zhutici.toString() + "]") : "not initialized")
				+ ""
					+ "\n - FDT_OA_FWBL.QICAORIQI = "
				+ (qicaoriqi_is_initialized ? ("["
						+ qicaoriqi.toString() + "]") : "not initialized")
				+ ""
		;		
	}
							
}

