package net.itjds.fdt.dao.fawen;

import java.sql.Timestamp;
import java.sql.Connection;

import net.itjds.j2ee.dao.DAO;
import net.itjds.j2ee.dao.annotation.DBField;
import net.itjds.j2ee.dao.annotation.DBTable;
import net.itjds.bpm.data.xmlproxy.MapdaoBeanAnnotation;
import net.itjds.common.mapdao.MapDAO;
import net.itjds.j2ee.dao.MethodChinaName;

    
	@DBTable(tableName = "FDT_OA_QJT", primaryKey="UUID",cname="FDT_OA_QJT" ,schema="bpm" )
	public class FdtOaQjtDAO extends DAO {
	
		public FdtOaQjtDAO () {
		   super();
	    }
	    public FdtOaQjtDAO (Connection conn) {
		   super(conn);
	    }
		
		@DBField(dbFieldName = "UUID",length = 64,dbType="VARCHAR2",isNull=false)
		private java.lang.String uuid;
		
		@DBField(dbFieldName = "ACTIVITYINST_ID",length = 64,dbType="VARCHAR2")
		private java.lang.String activityinstId;
		
		@DBField(dbFieldName = "PROCESSINST_ID",length = 64,dbType="VARCHAR2")
		private java.lang.String processinstId;
		
		@DBField(dbFieldName = "CKMC",length = 200,dbType="VARCHAR2")
		private java.lang.String ckmc;
		
		@DBField(dbFieldName = "SQRQ",length = 7,dbType="DATE")
		private java.sql.Timestamp sqrq;
		
		@DBField(dbFieldName = "XM",length = 200,dbType="VARCHAR2")
		private java.lang.String xm;
		
		@DBField(dbFieldName = "QJLX",length = 200,dbType="VARCHAR2")
		private java.lang.String qjlx;
		
		@DBField(dbFieldName = "QJSJKS",length = 7,dbType="DATE")
		private java.sql.Timestamp qjsjks;
		
		@DBField(dbFieldName = "QJSJJS",length = 7,dbType="DATE")
		private java.sql.Timestamp qjsjjs;
		
		@DBField(dbFieldName = "GJT",length = 200,dbType="VARCHAR2")
		private java.lang.String gjt;
		
		@DBField(dbFieldName = "SY",length = 500,dbType="VARCHAR2")
		private java.lang.String sy;
		
		@DBField(dbFieldName = "CKFZRYJ",length = 500,dbType="VARCHAR2")
		private java.lang.String ckfzryj;
		
		@DBField(dbFieldName = "XTDBKFZRYJ",length = 500,dbType="VARCHAR2")
		private java.lang.String xtdbkfzryj;
		
		@DBField(dbFieldName = "ZXLDYJ",length = 500,dbType="VARCHAR2")
		private java.lang.String zxldyj;
   
		@MethodChinaName(cname="UUID")
		public java.lang.String getUuid(){
		   return uuid; 
		}
		public void setUuid(java.lang.String  newVal){
			this.uuid = newVal;
			
		}
		@MethodChinaName(cname="活动实例ID")
		public java.lang.String getActivityinstId(){
		   return activityinstId; 
		}
		public void setActivityinstId(java.lang.String  newVal){
			this.activityinstId = newVal;
			
		}
		@MethodChinaName(cname="流程实例ID")
		public java.lang.String getProcessinstId(){
		   return processinstId; 
		}
		public void setProcessinstId(java.lang.String  newVal){
			this.processinstId = newVal;
			
		}
		@MethodChinaName(cname="窗口名称")
		public java.lang.String getCkmc(){
		   return ckmc; 
		}
		public void setCkmc(java.lang.String  newVal){
			this.ckmc = newVal;
			
		}
		@MethodChinaName(cname="申请时间")
		public java.sql.Timestamp getSqrq(){
		   return sqrq; 
		}
		public void setSqrq(java.sql.Timestamp  newVal){
			this.sqrq = newVal;
			
		}
		@MethodChinaName(cname="姓名")
		public java.lang.String getXm(){
		   return xm; 
		}
		public void setXm(java.lang.String  newVal){
			this.xm = newVal;
			
		}
		@MethodChinaName(cname="请假类型")
		public java.lang.String getQjlx(){
		   return qjlx; 
		}
		public void setQjlx(java.lang.String  newVal){
			this.qjlx = newVal;
			
		}
		@MethodChinaName(cname="请假时间开始")
		public java.sql.Timestamp getQjsjks(){
		   return qjsjks; 
		}
		public void setQjsjks(java.sql.Timestamp  newVal){
			this.qjsjks = newVal;
			
		}
		@MethodChinaName(cname="请假时间结束")
		public java.sql.Timestamp getQjsjjs(){
		   return qjsjjs; 
		}
		public void setQjsjjs(java.sql.Timestamp  newVal){
			this.qjsjjs = newVal;
			
		}
		@MethodChinaName(cname="共几天")
		public java.lang.String getGjt(){
		   return gjt; 
		}
		public void setGjt(java.lang.String  newVal){
			this.gjt = newVal;
			
		}
		@MethodChinaName(cname="事由")
		public java.lang.String getSy(){
		   return sy; 
		}
		public void setSy(java.lang.String  newVal){
			this.sy = newVal;
			
		}
		@MethodChinaName(cname="窗口负责人意见")
		public java.lang.String getCkfzryj(){
		   return ckfzryj; 
		}
		public void setCkfzryj(java.lang.String  newVal){
			this.ckfzryj = newVal;
			
		}
		@MethodChinaName(cname="协调督办科负责人意见")
		public java.lang.String getXtdbkfzryj(){
		   return xtdbkfzryj; 
		}
		public void setXtdbkfzryj(java.lang.String  newVal){
			this.xtdbkfzryj = newVal;
			
		}
		@MethodChinaName(cname="中心领导意见")
		public java.lang.String getZxldyj(){
		   return zxldyj; 
		}
		public void setZxldyj(java.lang.String  newVal){
			this.zxldyj = newVal;
			
		}

	}
