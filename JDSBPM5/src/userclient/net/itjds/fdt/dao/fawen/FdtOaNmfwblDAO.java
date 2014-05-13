package net.itjds.fdt.dao.fawen;

import java.sql.Timestamp;
import java.sql.Connection;

import net.itjds.j2ee.dao.DAO;
import net.itjds.j2ee.dao.annotation.DBField;
import net.itjds.j2ee.dao.annotation.DBTable;
import net.itjds.bpm.data.xmlproxy.MapdaoBeanAnnotation;
import net.itjds.common.mapdao.MapDAO;
import net.itjds.j2ee.dao.MethodChinaName;

@DBTable(tableName = "FDT_OA_NMFWBL", primaryKey = "UUID", cname = "内蒙发文办理", schema = "bpm")
public class FdtOaNmfwblDAO extends DAO {

	public FdtOaNmfwblDAO() {
		super();
	}

	public FdtOaNmfwblDAO(Connection conn) {
		super(conn);
	}

	@DBField(dbFieldName = "ACTIVITYINST_ID", length = 64, dbType = "VARCHAR2")
	private java.lang.String activityinstId;

	@DBField(dbFieldName = "WJBT", length = 3000, dbType = "VARCHAR2")
	private java.lang.String wjbt;

	@DBField(dbFieldName = "CS", length = 3000, dbType = "VARCHAR2")
	private java.lang.String cs;

	@DBField(dbFieldName = "FWH", length = 64, dbType = "VARCHAR2")
	private java.lang.String fwh;

	@DBField(dbFieldName = "HGDW", length = 64, dbType = "VARCHAR2")
	private java.lang.String hgdw;

	@DBField(dbFieldName = "HGR", length = 64, dbType = "VARCHAR2")
	private java.lang.String hgr;

	@DBField(dbFieldName = "HGRDH", length = 64, dbType = "VARCHAR2")
	private java.lang.String hgrdh;

	@DBField(dbFieldName = "JJCD", length = 64, dbType = "VARCHAR2")
	private java.lang.String jjcd;

	@DBField(dbFieldName = "LDQF", length = 3000, dbType = "VARCHAR2")
	private java.lang.String ldqf;

	@DBField(dbFieldName = "MJ", length = 64, dbType = "VARCHAR2")
	private java.lang.String mj;

	@DBField(dbFieldName = "NGDW", length = 64, dbType = "VARCHAR2")
	private java.lang.String ngdw;

	@DBField(dbFieldName = "NGR", length = 64, dbType = "VARCHAR2")
	private java.lang.String ngr;

	@DBField(dbFieldName = "NGRDH", length = 64, dbType = "VARCHAR2")
	private java.lang.String ngrdh;

	@DBField(dbFieldName = "PROCESSINST_ID", length = 64, dbType = "VARCHAR2")
	private java.lang.String processinstId;

	@DBField(dbFieldName = "SHYJ", length = 3000, dbType = "VARCHAR2")
	private java.lang.String shyj;

	@DBField(dbFieldName = "UUID", length = 64, dbType = "VARCHAR2", isNull = false)
	private java.lang.String uuid;

	@DBField(dbFieldName = "ZS", length = 3000, dbType = "VARCHAR2")
	private java.lang.String zs;

	@DBField(dbFieldName = "ZW", length = 3000, dbType = "VARCHAR2")
	private java.lang.String zw;

	@DBField(dbFieldName = "WZ", length = 64, dbType = "VARCHAR2")
	private java.lang.String wz;

	@MethodChinaName(cname = "活动实例ID")
	public java.lang.String getActivityinstId() {
		return activityinstId;
	}

	public void setActivityinstId(java.lang.String newVal) {
		this.activityinstId = newVal;

	}

	@MethodChinaName(cname = "标题")
	public java.lang.String getWjbt() {
		return wjbt;
	}

	public void setWjbt(java.lang.String newVal) {
		this.wjbt = newVal;

	}

	@MethodChinaName(cname = "抄送")
	public java.lang.String getCs() {
		return cs;
	}

	public void setCs(java.lang.String newVal) {
		this.cs = newVal;

	}

	@MethodChinaName(cname = "发文号")
	public java.lang.String getFwh() {
		return fwh;
	}

	public void setFwh(java.lang.String newVal) {
		this.fwh = newVal;

	}

	@MethodChinaName(cname = "核稿单位")
	public java.lang.String getHgdw() {
		return hgdw;
	}

	public void setHgdw(java.lang.String newVal) {
		this.hgdw = newVal;

	}

	@MethodChinaName(cname = "核稿人")
	public java.lang.String getHgr() {
		return hgr;
	}

	public void setHgr(java.lang.String newVal) {
		this.hgr = newVal;

	}

	@MethodChinaName(cname = "核稿人联系电话")
	public java.lang.String getHgrdh() {
		return hgrdh;
	}

	public void setHgrdh(java.lang.String newVal) {
		this.hgrdh = newVal;

	}

	@MethodChinaName(cname = "紧急程度")
	public java.lang.String getJjcd() {
		return jjcd;
	}

	public void setJjcd(java.lang.String newVal) {
		this.jjcd = newVal;

	}

	@MethodChinaName(cname = "领导签发")
	public java.lang.String getLdqf() {
		return ldqf;
	}

	public void setLdqf(java.lang.String newVal) {
		this.ldqf = newVal;

	}

	@MethodChinaName(cname = "密级")
	public java.lang.String getMj() {
		return mj;
	}

	public void setMj(java.lang.String newVal) {
		this.mj = newVal;

	}

	@MethodChinaName(cname = "拟稿单位")
	public java.lang.String getNgdw() {
		return ngdw;
	}

	public void setNgdw(java.lang.String newVal) {
		this.ngdw = newVal;

	}

	@MethodChinaName(cname = "拟稿人")
	public java.lang.String getNgr() {
		return ngr;
	}

	public void setNgr(java.lang.String newVal) {
		this.ngr = newVal;

	}

	@MethodChinaName(cname = "拟稿人联系电话")
	public java.lang.String getNgrdh() {
		return ngrdh;
	}

	public void setNgrdh(java.lang.String newVal) {
		this.ngrdh = newVal;

	}

	@MethodChinaName(cname = "流程实例ID")
	public java.lang.String getProcessinstId() {
		return processinstId;
	}

	public void setProcessinstId(java.lang.String newVal) {
		this.processinstId = newVal;

	}

	@MethodChinaName(cname = "审核意见")
	public java.lang.String getShyj() {
		return shyj;
	}

	public void setShyj(java.lang.String newVal) {
		this.shyj = newVal;

	}

	@MethodChinaName(cname = "UUID")
	public java.lang.String getUuid() {
		return uuid;
	}

	public void setUuid(java.lang.String newVal) {
		this.uuid = newVal;

	}

	@MethodChinaName(cname = "主送")
	public java.lang.String getZs() {
		return zs;
	}

	public void setZs(java.lang.String newVal) {
		this.zs = newVal;

	}

	@MethodChinaName(cname = "正文")
	public java.lang.String getZw() {
		return zw;
	}

	public void setZw(java.lang.String newVal) {
		this.zw = newVal;

	}

	@MethodChinaName(cname = "文种")
	public java.lang.String getWz() {
		return wz;
	}

	public void setWz(java.lang.String newVal) {
		this.wz = newVal;

	}

}
