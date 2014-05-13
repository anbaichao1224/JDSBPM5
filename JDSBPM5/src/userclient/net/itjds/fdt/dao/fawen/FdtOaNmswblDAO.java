package net.itjds.fdt.dao.fawen;

import java.sql.Timestamp;
import java.sql.Connection;

import net.itjds.j2ee.dao.DAO;
import net.itjds.j2ee.dao.annotation.DBField;
import net.itjds.j2ee.dao.annotation.DBTable;
import net.itjds.bpm.data.xmlproxy.MapdaoBeanAnnotation;
import net.itjds.common.mapdao.MapDAO;
import net.itjds.j2ee.dao.MethodChinaName;

    
	@DBTable(tableName = "FDT_OA_NMSWBL", primaryKey="UUID",cname="收文办理" ,schema="bpm" )
	public class FdtOaNmswblDAO extends DAO {
	
		public FdtOaNmswblDAO () {
		   super();
	    }
	    public FdtOaNmswblDAO (Connection conn) {
		   super(conn);
	    }
		
		@DBField(dbFieldName = "ACTIVITYINST_ID",length = 64,dbType="VARCHAR2")
		private java.lang.String activityinstId;
		
		@DBField(dbFieldName = "CLJG",length = 3000,dbType="VARCHAR2")
		private java.lang.String cljg;
		
		@DBField(dbFieldName = "DJ",length = 64,dbType="VARCHAR2")
		private java.lang.String dj;
		
		@DBField(dbFieldName = "FS",length = 200,dbType="VARCHAR2")
		private java.lang.String fs;
		
		@DBField(dbFieldName = "LDPS",length = 3000,dbType="VARCHAR2")
		private java.lang.String ldps;
		
		@DBField(dbFieldName = "LWDW",length = 200,dbType="VARCHAR2")
		private java.lang.String lwdw;
		
		@DBField(dbFieldName = "NBYJ",length = 3000,dbType="VARCHAR2")
		private java.lang.String nbyj;
		
		@DBField(dbFieldName = "PROCESSINST_ID",length = 64,dbType="VARCHAR2")
		private java.lang.String processinstId;
		
		@DBField(dbFieldName = "SWRQ",length = 7,dbType="DATE")
		private java.sql.Timestamp swrq;
		
		@DBField(dbFieldName = "UUID",length = 64,dbType="VARCHAR2",isNull=false)
		private java.lang.String uuid;
		
		@DBField(dbFieldName = "WJBT",length = 1000,dbType="VARCHAR2")
		private java.lang.String wjbt;
		
		@DBField(dbFieldName = "YBQM",length = 3000,dbType="VARCHAR2")
		private java.lang.String ybqm;
		
		@DBField(dbFieldName = "MJ",length = 64,dbType="VARCHAR2")
		private java.lang.String mj;
		
		@DBField(dbFieldName = "FWZH",length = 64,dbType="VARCHAR2")
		private java.lang.String fwzh;
		
		@DBField(dbFieldName = "YZH",length = 64,dbType="VARCHAR2")
		private java.lang.String yzh;
		
		@DBField(dbFieldName = "CWRQ",length = 7,dbType="DATE")
		private java.sql.Timestamp cwrq;
   
		@MethodChinaName(cname="活动实例ID")
		public java.lang.String getActivityinstId(){
		   return activityinstId; 
		}
		public void setActivityinstId(java.lang.String  newVal){
			this.activityinstId = newVal;
			
		}
		@MethodChinaName(cname="处理结果")
		public java.lang.String getCljg(){
		   return cljg; 
		}
		public void setCljg(java.lang.String  newVal){
			this.cljg = newVal;
			
		}
		@MethodChinaName(cname="等级")
		public java.lang.String getDj(){
		   return dj; 
		}
		public void setDj(java.lang.String  newVal){
			this.dj = newVal;
			
		}
		@MethodChinaName(cname="份数")
		public java.lang.String getFs(){
		   return fs; 
		}
		public void setFs(java.lang.String  newVal){
			this.fs = newVal;
			
		}
		@MethodChinaName(cname="领导批示")
		public java.lang.String getLdps(){
		   return ldps; 
		}
		public void setLdps(java.lang.String  newVal){
			this.ldps = newVal;
			
		}
		@MethodChinaName(cname="来文单位")
		public java.lang.String getLwdw(){
		   return lwdw; 
		}
		public void setLwdw(java.lang.String  newVal){
			this.lwdw = newVal;
			
		}
		@MethodChinaName(cname="拟办意见")
		public java.lang.String getNbyj(){
		   return nbyj; 
		}
		public void setNbyj(java.lang.String  newVal){
			this.nbyj = newVal;
			
		}
		@MethodChinaName(cname="流程实例ID")
		public java.lang.String getProcessinstId(){
		   return processinstId; 
		}
		public void setProcessinstId(java.lang.String  newVal){
			this.processinstId = newVal;
			
		}
		@MethodChinaName(cname="收文日期")
		public java.sql.Timestamp getSwrq(){
		   return swrq; 
		}
		public void setSwrq(java.sql.Timestamp  newVal){
			this.swrq = newVal;
			
		}
		@MethodChinaName(cname="UUID")
		public java.lang.String getUuid(){
		   return uuid; 
		}
		public void setUuid(java.lang.String  newVal){
			this.uuid = newVal;
			
		}
		@MethodChinaName(cname="文件标题")
		public java.lang.String getWjbt(){
		   return wjbt; 
		}
		public void setWjbt(java.lang.String  newVal){
			this.wjbt = newVal;
			
		}
		@MethodChinaName(cname="阅办签名")
		public java.lang.String getYbqm(){
		   return ybqm; 
		}
		public void setYbqm(java.lang.String  newVal){
			this.ybqm = newVal;
			
		}
		@MethodChinaName(cname="密级")
		public java.lang.String getMj(){
		   return mj; 
		}
		public void setMj(java.lang.String  newVal){
			this.mj = newVal;
			
		}
		@MethodChinaName(cname="发文字号")
		public java.lang.String getFwzh(){
		   return fwzh; 
		}
		public void setFwzh(java.lang.String  newVal){
			this.fwzh = newVal;
			
		}
		@MethodChinaName(cname="运转号")
		public java.lang.String getYzh(){
		   return yzh; 
		}
		public void setYzh(java.lang.String  newVal){
			this.yzh = newVal;
			
		}
		@MethodChinaName(cname="成文日期")
		public java.sql.Timestamp getCwrq(){
		   return cwrq; 
		}
		public void setCwrq(java.sql.Timestamp  newVal){
			this.cwrq = newVal;
			
		}


	}
