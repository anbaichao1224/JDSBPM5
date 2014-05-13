package net.itjds.usm.persistence.model;


import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.io.Serializable;
import net.itjds.usm.annotation.PropertyChinaName;


@Entity
@Table(name = "RO_Org")
public class Org implements Serializable {


    @PropertyChinaName(cname="部门类型")
    private String orgtype;

	public String getOrgtype(){
		return orgtype;
	}
	
	public void setOrgtype( String orgtype ){
		this.orgtype = orgtype;
	}
	

    @PropertyChinaName(cname="部门名称")
    private String cnname;

	@Column(length=3000)
	public String getCnname(){
		return cnname;
	}
	
	public void setCnname( String cnname ){
		this.cnname = cnname;
	}
	

    @PropertyChinaName(cname="内线电话")
    private Integer intel;

	public Integer getIntel(){
		return intel;
	}
	
	public void setIntel( Integer intel ){
		this.intel = intel;
	}
	

    @PropertyChinaName(cname="父级部门ID")
    private String parentorgid;

	@Column(length=3000)
	public String getParentorgid(){
		return parentorgid;
	}
	
	public void setParentorgid( String parentorgid ){
		this.parentorgid = parentorgid;
	}
	

    @PropertyChinaName(cname="UUID")
    private String uuid;

	@Id @GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy="uuid")
	@Column(length=32)
	public String getUuid(){
		return uuid;
	}
	
	public void setUuid( String uuid ){
		this.uuid = uuid;
	}
	

    @PropertyChinaName(cname="英文名称")
    private String enname;

	@Column(length=3000)
	public String getEnname(){
		return enname;
	}
	
	public void setEnname( String enname ){
		this.enname = enname;
	}
	

    @PropertyChinaName(cname="部门ID")
    private String orgid;

	@Column(length=3000)
	public String getOrgid(){
		return orgid;
	}
	
	public void setOrgid( String orgid ){
		this.orgid = orgid;
	}
	

    @PropertyChinaName(cname="备注")
    private String memo;

	@Column(length=3000)
	public String getMemo(){
		return memo;
	}
	
	public void setMemo( String memo ){
		this.memo = memo;
	}
	

    @PropertyChinaName(cname="排序")
    private Integer serialindex;

	public Integer getSerialindex(){
		return serialindex;
	}
	
	public void setSerialindex( Integer serialindex ){
		this.serialindex = serialindex;
	}
	

    @PropertyChinaName(cname="部门级别")
    private Integer orglevel;

	public Integer getOrglevel(){
		return orglevel;
	}
	
	public void setOrglevel( Integer orglevel ){
		this.orglevel = orglevel;
	}
	

    @PropertyChinaName(cname="夜间值班电话")
    private String nighttel;

	public String getNighttel(){
		return nighttel;
	}
	
	public void setNighttel( String nighttel ){
		this.nighttel = nighttel;
	}
	

    @PropertyChinaName(cname="外线电话")
    private String outtel;

	public String getOuttel(){
		return outtel;
	}
	
	public void setOuttel( String outtel ){
		this.outtel = outtel;
	}
	

    @PropertyChinaName(cname="状态")
    private String status;

	@Column(length=3000)
	public String getStatus(){
		return status;
	}
	
	public void setStatus( String status ){
		this.status = status;
	}
	

    @PropertyChinaName(cname="传真")
    private String fax;

	public String getFax(){
		return fax;
	}
	
	public void setFax( String fax ){
		this.fax = fax;
	}
	//rtx 对应部门id
	@PropertyChinaName(cname="rtx部门Id")
	private Integer rtxorgid;

	public Integer getRtxorgid() {
		return rtxorgid;
	}

	public void setRtxorgid(Integer rtxorgid) {
		this.rtxorgid = rtxorgid;
	}
	
}