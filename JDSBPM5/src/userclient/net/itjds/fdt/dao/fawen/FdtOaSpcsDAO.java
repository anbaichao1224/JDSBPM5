package net.itjds.fdt.dao.fawen;

import java.sql.Timestamp;
import java.sql.Connection;

import net.itjds.j2ee.dao.DAO;
import net.itjds.j2ee.dao.annotation.DBField;
import net.itjds.j2ee.dao.annotation.DBTable;
import net.itjds.bpm.data.xmlproxy.MapdaoBeanAnnotation;
import net.itjds.common.mapdao.MapDAO;
import net.itjds.j2ee.dao.MethodChinaName;

    
	@DBTable(tableName = "FDT_OA_SPCS", primaryKey="UUID",cname="FDT_OA_SPCS" ,schema="bpm" )
	public class FdtOaSpcsDAO extends DAO {
	
		public FdtOaSpcsDAO () {
		   super();
	    }
	    public FdtOaSpcsDAO (Connection conn) {
		   super(conn);
	    }
		
		@DBField(dbFieldName = "UUID",length = 64,dbType="VARCHAR2",isNull=false)
		private java.lang.String uuid;
		
		@DBField(dbFieldName = "ACTIVITYINST_ID",length = 64,dbType="VARCHAR2")
		private java.lang.String activityinstId;
		
		@DBField(dbFieldName = "PROCESSINST_ID",length = 64,dbType="VARCHAR2")
		private java.lang.String processinstId;
		
		@DBField(dbFieldName = "LDQF",length = 1000,dbType="VARCHAR2")
		private java.lang.String ldqf;
		
		@DBField(dbFieldName = "SHYJ",length = 1000,dbType="VARCHAR2")
		private java.lang.String shyj;
		
		@DBField(dbFieldName = "HGDW",length = 200,dbType="VARCHAR2")
		private java.lang.String hgdw;
		
		@DBField(dbFieldName = "HGR",length = 200,dbType="VARCHAR2")
		private java.lang.String hgr;
		
		@DBField(dbFieldName = "HGRLXDH",length = 200,dbType="VARCHAR2")
		private java.lang.String hgrlxdh;
		
		@DBField(dbFieldName = "NGDW",length = 200,dbType="VARCHAR2")
		private java.lang.String ngdw;
		
		@DBField(dbFieldName = "NGR",length = 200,dbType="VARCHAR2")
		private java.lang.String ngr;
		
		@DBField(dbFieldName = "NGRLXDH",length = 200,dbType="VARCHAR2")
		private java.lang.String ngrlxdh;
		
		@DBField(dbFieldName = "MJ",length = 100,dbType="VARCHAR2")
		private java.lang.String mj;
		
		@DBField(dbFieldName = "JJCD",length = 100,dbType="VARCHAR2")
		private java.lang.String jjcd;
		
		@DBField(dbFieldName = "FWZH",length = 100,dbType="VARCHAR2")
		private java.lang.String fwzh;
		
		@DBField(dbFieldName = "BT",length = 1000,dbType="VARCHAR2")
		private java.lang.String bt;
		
		@DBField(dbFieldName = "ZS",length = 1000,dbType="VARCHAR2")
		private java.lang.String zs;
		
		@DBField(dbFieldName = "CS",length = 1000,dbType="VARCHAR2")
		private java.lang.String cs;
   
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
		@MethodChinaName(cname="领导签发")
		public java.lang.String getLdqf(){
		   return ldqf; 
		}
		public void setLdqf(java.lang.String  newVal){
			this.ldqf = newVal;
			
		}
		@MethodChinaName(cname="审核意见")
		public java.lang.String getShyj(){
		   return shyj; 
		}
		public void setShyj(java.lang.String  newVal){
			this.shyj = newVal;
			
		}
		@MethodChinaName(cname="核稿单位")
		public java.lang.String getHgdw(){
		   return hgdw; 
		}
		public void setHgdw(java.lang.String  newVal){
			this.hgdw = newVal;
			
		}
		@MethodChinaName(cname="核稿人")
		public java.lang.String getHgr(){
		   return hgr; 
		}
		public void setHgr(java.lang.String  newVal){
			this.hgr = newVal;
			
		}
		@MethodChinaName(cname="核稿人联系电话")
		public java.lang.String getHgrlxdh(){
		   return hgrlxdh; 
		}
		public void setHgrlxdh(java.lang.String  newVal){
			this.hgrlxdh = newVal;
			
		}
		@MethodChinaName(cname="拟稿单位")
		public java.lang.String getNgdw(){
		   return ngdw; 
		}
		public void setNgdw(java.lang.String  newVal){
			this.ngdw = newVal;
			
		}
		@MethodChinaName(cname="拟稿人")
		public java.lang.String getNgr(){
		   return ngr; 
		}
		public void setNgr(java.lang.String  newVal){
			this.ngr = newVal;
			
		}
		@MethodChinaName(cname="拟稿人联系电话")
		public java.lang.String getNgrlxdh(){
		   return ngrlxdh; 
		}
		public void setNgrlxdh(java.lang.String  newVal){
			this.ngrlxdh = newVal;
			
		}
		@MethodChinaName(cname="密级")
		public java.lang.String getMj(){
		   return mj; 
		}
		public void setMj(java.lang.String  newVal){
			this.mj = newVal;
			
		}
		@MethodChinaName(cname="紧急程度")
		public java.lang.String getJjcd(){
		   return jjcd; 
		}
		public void setJjcd(java.lang.String  newVal){
			this.jjcd = newVal;
			
		}
		@MethodChinaName(cname="发文字号")
		public java.lang.String getFwzh(){
		   return fwzh; 
		}
		public void setFwzh(java.lang.String  newVal){
			this.fwzh = newVal;
			
		}
		@MethodChinaName(cname="标题")
		public java.lang.String getBt(){
		   return bt; 
		}
		public void setBt(java.lang.String  newVal){
			this.bt = newVal;
			
		}
		@MethodChinaName(cname="主送")
		public java.lang.String getZs(){
		   return zs; 
		}
		public void setZs(java.lang.String  newVal){
			this.zs = newVal;
			
		}
		@MethodChinaName(cname="抄送")
		public java.lang.String getCs(){
		   return cs; 
		}
		public void setCs(java.lang.String  newVal){
			this.cs = newVal;
			
		}


	}
