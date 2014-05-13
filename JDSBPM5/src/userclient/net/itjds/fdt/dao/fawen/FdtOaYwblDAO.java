package net.itjds.fdt.dao.fawen;

import java.sql.Timestamp;
import java.sql.Connection;

import net.itjds.j2ee.dao.DAO;
import net.itjds.j2ee.dao.annotation.DBField;
import net.itjds.j2ee.dao.annotation.DBTable;
import net.itjds.bpm.data.xmlproxy.MapdaoBeanAnnotation;
import net.itjds.common.mapdao.MapDAO;
import net.itjds.j2ee.dao.MethodChinaName;

    
	@DBTable(tableName = "FDT_OA_YWBL", primaryKey="UUID",cname="阅文办理" ,schema="bpm" )
	public class FdtOaYwblDAO extends DAO {
	
		public FdtOaYwblDAO () {
		   super();
	    }
	    public FdtOaYwblDAO (Connection conn) {
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
		
		@DBField(dbFieldName = "LWBH",length = 64,dbType="VARCHAR2")
		private java.lang.String lwbh;
		
		@DBField(dbFieldName = "LWJG",length = 200,dbType="VARCHAR2")
		private java.lang.String lwjg;
		
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
		@MethodChinaName(cname="来文编号")
		public java.lang.String getLwbh(){
		   return lwbh; 
		}
		public void setLwbh(java.lang.String  newVal){
			this.lwbh = newVal;
			
		}
		@MethodChinaName(cname="来文机关")
		public java.lang.String getLwjg(){
		   return lwjg; 
		}
		public void setLwjg(java.lang.String  newVal){
			this.lwjg = newVal;
			
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


	}
