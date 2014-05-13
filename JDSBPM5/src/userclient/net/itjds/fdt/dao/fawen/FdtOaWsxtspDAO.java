package net.itjds.fdt.dao.fawen;

import java.sql.Timestamp;
import java.sql.Connection;

import net.itjds.j2ee.dao.DAO;
import net.itjds.j2ee.dao.annotation.DBField;
import net.itjds.j2ee.dao.annotation.DBTable;
import net.itjds.bpm.data.xmlproxy.MapdaoBeanAnnotation;
import net.itjds.common.mapdao.MapDAO;
import net.itjds.j2ee.dao.MethodChinaName;

    
	@DBTable(tableName = "FDT_OA_WSXTSP", primaryKey="UUID",cname="FDT_OA_WSXTSP" ,schema="bpm" )
	public class FdtOaWsxtspDAO extends DAO {
	
		public FdtOaWsxtspDAO () {
		   super();
	    }
	    public FdtOaWsxtspDAO (Connection conn) {
		   super(conn);
	    }
		
		@DBField(dbFieldName = "UUID",length = 64,dbType="VARCHAR2",isNull=false)
		private java.lang.String uuid;
		
		@DBField(dbFieldName = "NGDW",length = 64,dbType="VARCHAR2")
		private java.lang.String ngdw;
		
		@DBField(dbFieldName = "NGSJ",length = 7,dbType="DATE")
		private java.sql.Timestamp ngsj;
		
		@DBField(dbFieldName = "NGR",length = 64,dbType="VARCHAR2")
		private java.lang.String ngr;
		
		@DBField(dbFieldName = "FXFWJYFFS",length = 64,dbType="VARCHAR2")
		private java.lang.String fxfwjyffs;
		
		@DBField(dbFieldName = "WJBH",length = 64,dbType="VARCHAR2")
		private java.lang.String wjbh;
		
		@DBField(dbFieldName = "HGDW",length = 64,dbType="VARCHAR2")
		private java.lang.String hgdw;
		
		@DBField(dbFieldName = "HGR",length = 64,dbType="VARCHAR2")
		private java.lang.String hgr;
		
		@DBField(dbFieldName = "HGYJ",length = 1000,dbType="VARCHAR2")
		private java.lang.String hgyj;
		
		@DBField(dbFieldName = "BT",length = 64,dbType="VARCHAR2")
		private java.lang.String bt;
		
		@DBField(dbFieldName = "CBDWLDQS",length = 64,dbType="VARCHAR2")
		private java.lang.String cbdwldqs;
		
		@DBField(dbFieldName = "LDQS",length = 64,dbType="VARCHAR2")
		private java.lang.String ldqs;
		
		@DBField(dbFieldName = "HQ",length = 64,dbType="VARCHAR2")
		private java.lang.String hq;
		
		@DBField(dbFieldName = "MJ",length = 64,dbType="VARCHAR2")
		private java.lang.String mj;
		
		@DBField(dbFieldName = "ACTIVITYINST_ID",length = 64,dbType="VARCHAR2")
		private java.lang.String activityinstId;
		
		@DBField(dbFieldName = "PROCESSINST_ID",length = 64,dbType="VARCHAR2")
		private java.lang.String processinstId;
		
		@DBField(dbFieldName = "APPNAME",length = 64,dbType="VARCHAR2")
		private java.lang.String appname;
		
		@DBField(dbFieldName = "APPORG",length = 64,dbType="VARCHAR2")
		private java.lang.String apporg;
		
		@DBField(dbFieldName = "CARDID",length = 64,dbType="VARCHAR2")
		private java.lang.String cardid;
		
		@DBField(dbFieldName = "APPDATE",length = 64,dbType="VARCHAR2")
		private java.lang.String appdate;
		
		@DBField(dbFieldName = "MOBILEPHONE",length = 64,dbType="VARCHAR2")
		private java.lang.String mobilephone;
		
		@DBField(dbFieldName = "PHONE",length = 64,dbType="VARCHAR2")
		private java.lang.String phone;
		
		@DBField(dbFieldName = "EMAIL",length = 64,dbType="VARCHAR2")
		private java.lang.String email;
		
		@DBField(dbFieldName = "ADDRESS",length = 64,dbType="VARCHAR2")
		private java.lang.String address;
		
		@DBField(dbFieldName = "NAME",length = 64,dbType="VARCHAR2")
		private java.lang.String name;
		
		@DBField(dbFieldName = "XMMC",length = 64,dbType="VARCHAR2")
		private java.lang.String xmmc;
		
		@DBField(dbFieldName = "DEPARTMENT",length = 64,dbType="VARCHAR2")
		private java.lang.String department;
		
		@DBField(dbFieldName = "STATUS",length = 64,dbType="VARCHAR2")
		private java.lang.String status;
		
		@DBField(dbFieldName = "APPLICATIONID",length = 64,dbType="VARCHAR2")
		private java.lang.String applicationid;
		
		@DBField(dbFieldName = "BSNUM",length = 64,dbType="VARCHAR2")
		private java.lang.String bsnum;
		
		@DBField(dbFieldName = "ID",length = 64,dbType="VARCHAR2")
		private java.lang.String id;
   
		@MethodChinaName(cname="UUID")
		public java.lang.String getUuid(){
		   return uuid; 
		}
		public void setUuid(java.lang.String  newVal){
			this.uuid = newVal;
			
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
		@MethodChinaName(cname="拟稿人")
		public java.lang.String getNgr(){
		   return ngr; 
		}
		public void setNgr(java.lang.String  newVal){
			this.ngr = newVal;
			
		}
		@MethodChinaName(cname="发行范围及印发份数")
		public java.lang.String getFxfwjyffs(){
		   return fxfwjyffs; 
		}
		public void setFxfwjyffs(java.lang.String  newVal){
			this.fxfwjyffs = newVal;
			
		}
		@MethodChinaName(cname="文件编号")
		public java.lang.String getWjbh(){
		   return wjbh; 
		}
		public void setWjbh(java.lang.String  newVal){
			this.wjbh = newVal;
			
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
		@MethodChinaName(cname="核稿意见")
		public java.lang.String getHgyj(){
		   return hgyj; 
		}
		public void setHgyj(java.lang.String  newVal){
			this.hgyj = newVal;
			
		}
		@MethodChinaName(cname="标题")
		public java.lang.String getBt(){
		   return bt; 
		}
		public void setBt(java.lang.String  newVal){
			this.bt = newVal;
			
		}
		@MethodChinaName(cname="承办单位领导签署")
		public java.lang.String getCbdwldqs(){
		   return cbdwldqs; 
		}
		public void setCbdwldqs(java.lang.String  newVal){
			this.cbdwldqs = newVal;
			
		}
		@MethodChinaName(cname="领导签署")
		public java.lang.String getLdqs(){
		   return ldqs; 
		}
		public void setLdqs(java.lang.String  newVal){
			this.ldqs = newVal;
			
		}
		@MethodChinaName(cname="会（审）签")
		public java.lang.String getHq(){
		   return hq; 
		}
		public void setHq(java.lang.String  newVal){
			this.hq = newVal;
			
		}
		@MethodChinaName(cname="密级")
		public java.lang.String getMj(){
		   return mj; 
		}
		public void setMj(java.lang.String  newVal){
			this.mj = newVal;
			
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
		@MethodChinaName(cname="申请人姓名")
		public java.lang.String getAppname(){
		   return appname; 
		}
		public void setAppname(java.lang.String  newVal){
			this.appname = newVal;
			
		}
		@MethodChinaName(cname="申请单位名称")
		public java.lang.String getApporg(){
		   return apporg; 
		}
		public void setApporg(java.lang.String  newVal){
			this.apporg = newVal;
			
		}
		@MethodChinaName(cname="申请人身份证号")
		public java.lang.String getCardid(){
		   return cardid; 
		}
		public void setCardid(java.lang.String  newVal){
			this.cardid = newVal;
			
		}
		@MethodChinaName(cname="申请时间")
		public java.lang.String getAppdate(){
		   return appdate; 
		}
		public void setAppdate(java.lang.String  newVal){
			this.appdate = newVal;
			
		}
		@MethodChinaName(cname="申请人手机号码")
		public java.lang.String getMobilephone(){
		   return mobilephone; 
		}
		public void setMobilephone(java.lang.String  newVal){
			this.mobilephone = newVal;
			
		}
		@MethodChinaName(cname="申请人固定电话")
		public java.lang.String getPhone(){
		   return phone; 
		}
		public void setPhone(java.lang.String  newVal){
			this.phone = newVal;
			
		}
		@MethodChinaName(cname="申请人邮箱")
		public java.lang.String getEmail(){
		   return email; 
		}
		public void setEmail(java.lang.String  newVal){
			this.email = newVal;
			
		}
		@MethodChinaName(cname="申请人地址")
		public java.lang.String getAddress(){
		   return address; 
		}
		public void setAddress(java.lang.String  newVal){
			this.address = newVal;
			
		}
		@MethodChinaName(cname="事项名称")
		public java.lang.String getName(){
		   return name; 
		}
		public void setName(java.lang.String  newVal){
			this.name = newVal;
			
		}
		@MethodChinaName(cname="申请审批的项目的具体名称")
		public java.lang.String getXmmc(){
		   return xmmc; 
		}
		public void setXmmc(java.lang.String  newVal){
			this.xmmc = newVal;
			
		}
		@MethodChinaName(cname="部门组织机构代码")
		public java.lang.String getDepartment(){
		   return department; 
		}
		public void setDepartment(java.lang.String  newVal){
			this.department = newVal;
			
		}
		@MethodChinaName(cname="0:在办1:办结2:作废3:补交挂起4:特别程序5:暂存6:已归档;")
		public java.lang.String getStatus(){
		   return status; 
		}
		public void setStatus(java.lang.String  newVal){
			this.status = newVal;
			
		}
		@MethodChinaName(cname="申请人id")
		public java.lang.String getApplicationid(){
		   return applicationid; 
		}
		public void setApplicationid(java.lang.String  newVal){
			this.applicationid = newVal;
			
		}
		@MethodChinaName(cname="业务流水号（业务流水号，供申请人查询用）")
		public java.lang.String getBsnum(){
		   return bsnum; 
		}
		public void setBsnum(java.lang.String  newVal){
			this.bsnum = newVal;
			
		}
		@MethodChinaName(cname="事项id")
		public java.lang.String getId(){
		   return id; 
		}
		public void setId(java.lang.String  newVal){
			this.id = newVal;
			
		}


	}
