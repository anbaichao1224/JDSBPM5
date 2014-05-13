package net.itjds.fdt.dao.fawen;

import java.sql.Timestamp;
import java.sql.Connection;

import net.itjds.j2ee.dao.DAO;
import net.itjds.j2ee.dao.annotation.DBField;
import net.itjds.j2ee.dao.annotation.DBTable;
import net.itjds.bpm.data.xmlproxy.MapdaoBeanAnnotation;
import net.itjds.common.mapdao.MapDAO;
import net.itjds.j2ee.dao.MethodChinaName;

    
	@DBTable(tableName = "FDT_OA_GWG", primaryKey="UUID",cname="FDT_OA_GWG" ,schema="bpm" )
	public class FdtOaGwgDAO extends DAO {
	
		public FdtOaGwgDAO () {
		   super();
	    }
	    public FdtOaGwgDAO (Connection conn) {
		   super(conn);
	    }
		
		@DBField(dbFieldName = "ACTIVITYINST_ID",length = 64,dbType="VARCHAR2")
		private java.lang.String activityinstId;
		
		@DBField(dbFieldName = "PROCESSINST_ID",length = 64,dbType="VARCHAR2")
		private java.lang.String processinstId;
		
		@DBField(dbFieldName = "UUID",length = 64,dbType="VARCHAR2")
		private java.lang.String uuid;
		
		@DBField(dbFieldName = "HGR",length = 200,dbType="VARCHAR2")
		private java.lang.String hgr;
		
		@DBField(dbFieldName = "HGDW",length = 200,dbType="VARCHAR2")
		private java.lang.String hgdw;
		
		@DBField(dbFieldName = "NGR",length = 200,dbType="VARCHAR2")
		private java.lang.String ngr;
		
		@DBField(dbFieldName = "BH",length = 200,dbType="VARCHAR2")
		private java.lang.String bh;
		
		@DBField(dbFieldName = "JJCD",length = 200,dbType="VARCHAR2")
		private java.lang.String jjcd;
		
		@DBField(dbFieldName = "YFFS",length = 200,dbType="VARCHAR2")
		private java.lang.String yffs;
		
		@DBField(dbFieldName = "YFSJ",length = 7,dbType="DATE")
		private java.sql.Timestamp yfsj;
		
		@DBField(dbFieldName = "CS",length = 200,dbType="VARCHAR2")
		private java.lang.String cs;
		
		@DBField(dbFieldName = "BT",length = 100,dbType="VARCHAR2")
		private java.lang.String bt;
		
		@DBField(dbFieldName = "BWXH",length = 100,dbType="VARCHAR2")
		private java.lang.String bwxh;
		
		@DBField(dbFieldName = "NGDW",length = 200,dbType="VARCHAR2")
		private java.lang.String ngdw;
		
		@DBField(dbFieldName = "NGSJ",length = 7,dbType="DATE")
		private java.sql.Timestamp ngsj;
		
		@DBField(dbFieldName = "HGSJ",length = 7,dbType="DATE")
		private java.sql.Timestamp hgsj;
		
		@DBField(dbFieldName = "WJLX",length = 200,dbType="VARCHAR2")
		private java.lang.String wjlx;
		
		@DBField(dbFieldName = "MJ",length = 200,dbType="VARCHAR2")
		private java.lang.String mj;
		
		@DBField(dbFieldName = "GKFS",length = 200,dbType="VARCHAR2")
		private java.lang.String gkfs;
		
		@DBField(dbFieldName = "ZS",length = 200,dbType="VARCHAR2")
		private java.lang.String zs;
		
		@DBField(dbFieldName = "NBYJ",length = 3000,dbType="VARCHAR2")
		private java.lang.String nbyj;
		
		@DBField(dbFieldName = "SHYJ",length = 3000,dbType="VARCHAR2")
		private java.lang.String shyj;
		
		@DBField(dbFieldName = "LDSQ",length = 3000,dbType="VARCHAR2")
		private java.lang.String ldsq;
		
		@DBField(dbFieldName = "QF",length = 2000,dbType="VARCHAR2")
		private java.lang.String qf;
		
		@DBField(dbFieldName = "XD",length = 200,dbType="VARCHAR2")
		private java.lang.String xd;
		
		@DBField(dbFieldName = "DZ",length = 200,dbType="VARCHAR2")
		private java.lang.String dz;
		
		@DBField(dbFieldName = "BZ",length = 2000,dbType="VARCHAR2")
		private java.lang.String bz;
		
		
   
		@MethodChinaName(cname="ACTIVITYINST_ID")
		public java.lang.String getActivityinstId(){
		   return activityinstId; 
		}
		public void setActivityinstId(java.lang.String  newVal){
			this.activityinstId = newVal;
			
		}
		@MethodChinaName(cname="PROCESSINST_ID")
		public java.lang.String getProcessinstId(){
		   return processinstId; 
		}
		public void setProcessinstId(java.lang.String  newVal){
			this.processinstId = newVal;
			
		}
		@MethodChinaName(cname="UUID")
		public java.lang.String getUuid(){
		   return uuid; 
		}
		public void setUuid(java.lang.String  newVal){
			this.uuid = newVal;
			
		}
		@MethodChinaName(cname="核稿人")
		public java.lang.String getHgr(){
		   return hgr; 
		}
		public void setHgr(java.lang.String  newVal){
			this.hgr = newVal;
			
		}
		@MethodChinaName(cname="核稿单位")
		public java.lang.String getHgdw(){
		   return hgdw; 
		}
		public void setHgdw(java.lang.String  newVal){
			this.hgdw = newVal;
			
		}
		@MethodChinaName(cname="拟稿人")
		public java.lang.String getNgr(){
		   return ngr; 
		}
		public void setNgr(java.lang.String  newVal){
			this.ngr = newVal;
			
		}
		@MethodChinaName(cname="文件编号")
		public java.lang.String getBh(){
		   return bh; 
		}
		public void setBh(java.lang.String  newVal){
			this.bh = newVal;
			
		}
		@MethodChinaName(cname="紧急程度")
		public java.lang.String getJjcd(){
		   return jjcd; 
		}
		public void setJjcd(java.lang.String  newVal){
			this.jjcd = newVal;
			
		}
		@MethodChinaName(cname="已发份数")
		public java.lang.String getYffs(){
		   return yffs; 
		}
		public void setYffs(java.lang.String  newVal){
			this.yffs = newVal;
			
		}
		@MethodChinaName(cname="已发时间")
		public java.sql.Timestamp getYfsj(){
		   return yfsj; 
		}
		public void setYfsj(java.sql.Timestamp  newVal){
			this.yfsj = newVal;
			
		}
		@MethodChinaName(cname="抄送")
		public java.lang.String getCs(){
		   return cs; 
		}
		public void setCs(java.lang.String  newVal){
			this.cs = newVal;
			
		}
		@MethodChinaName(cname="标题")
		public java.lang.String getBt(){
		   return bt; 
		}
		public void setBt(java.lang.String  newVal){
			this.bt = newVal;
			
		}
		@MethodChinaName(cname="编号")
		public java.lang.String getBwxh(){
		   return bwxh; 
		}
		public void setBwxh(java.lang.String  newVal){
			this.bwxh = newVal;
			
		}
		@MethodChinaName(cname="拟稿单位")
		public java.lang.String getNgdw(){
		   return ngdw; 
		}
		public void setNgdw(java.lang.String  newVal){
			this.ngdw = newVal;
			
		}
		@MethodChinaName(cname="拟稿时间")
		public java.sql.Timestamp getNgsj(){
		   return ngsj; 
		}
		public void setNgsj(java.sql.Timestamp  newVal){
			this.ngsj = newVal;
			
		}
		@MethodChinaName(cname="核稿时间")
		public java.sql.Timestamp getHgsj(){
		   return hgsj; 
		}
		public void setHgsj(java.sql.Timestamp  newVal){
			this.hgsj = newVal;
			
		}
		@MethodChinaName(cname="文件类型")
		public java.lang.String getWjlx(){
		   return wjlx; 
		}
		public void setWjlx(java.lang.String  newVal){
			this.wjlx = newVal;
			
		}
		@MethodChinaName(cname="密级")
		public java.lang.String getMj(){
		   return mj; 
		}
		public void setMj(java.lang.String  newVal){
			this.mj = newVal;
			
		}
		@MethodChinaName(cname="公开方式")
		public java.lang.String getGkfs(){
		   return gkfs; 
		}
		public void setGkfs(java.lang.String  newVal){
			this.gkfs = newVal;
			
		}
		@MethodChinaName(cname="主送")
		public java.lang.String getZs(){
		   return zs; 
		}
		public void setZs(java.lang.String  newVal){
			this.zs = newVal;
			
		}
		@MethodChinaName(cname="拟办意见")
		public java.lang.String getNbyj(){
		   return nbyj; 
		}
		public void setNbyj(java.lang.String  newVal){
			this.nbyj = newVal;
			
		}
		@MethodChinaName(cname="审核意见")
		public java.lang.String getShyj(){
		   return shyj; 
		}
		public void setShyj(java.lang.String  newVal){
			this.shyj = newVal;
			
		}
		@MethodChinaName(cname="领导审签")
		public java.lang.String getLdsq(){
		   return ldsq; 
		}
		public void setLdsq(java.lang.String  newVal){
			this.ldsq = newVal;
			
		}
		@MethodChinaName(cname="签发")
		public java.lang.String getQf(){
		   return qf; 
		}
		public void setQf(java.lang.String  newVal){
			this.qf = newVal;
			
		}
		@MethodChinaName(cname="校对")
		public java.lang.String getXd(){
		   return xd; 
		}
		public void setXd(java.lang.String  newVal){
			this.xd = newVal;
			
		}
		@MethodChinaName(cname="打字")
		public java.lang.String getDz(){
		   return dz; 
		}
		public void setDz(java.lang.String  newVal){
			this.dz = newVal;
			
		}
		
		@MethodChinaName(cname="备注")
		public java.lang.String getBz(){
		   return bz; 
		}
		public void setBz(java.lang.String  newVal){
			this.bz = newVal;
			
		}
		
	


	}
