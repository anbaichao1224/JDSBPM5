package kzxd.ttinfo.dao;

import java.util.Date;

import net.itjds.j2ee.dao.DAO;
import net.itjds.j2ee.dao.DAOException;

public class TtInfoDAO extends DAO {
	
	private String uuid;
	private String ttmc;
	private String ttpath;
	private Integer ttindex;
	private String remark;
	private Date createdate;
	private String creator;
	private String filename;
	private String filetype;
	private String wenzhong;
	private String wenzhongid;
	
	
	public String getWenzhong() {
		return wenzhong;
	}
	public void setWenzhong(String _wenzhong) {
		firePropertyChange("wenzhong",wenzhong,_wenzhong);
		this.wenzhong = _wenzhong;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String _uuid) {
		firePropertyChange("uuid", uuid, _uuid);
		this.uuid = _uuid;
	}
	public String getTtmc() {
		return ttmc;
	}
	public void setTtmc(String _ttmc) {
		firePropertyChange("ttmc", ttmc, _ttmc);
		this.ttmc = _ttmc;
	}
	public String getTtpath() {
		return ttpath;
	}
	public void setTtpath(String _ttpath) {
		firePropertyChange("ttpath", ttpath, _ttpath);
		this.ttpath = _ttpath;
	}
	
	public String getRemark() {
		return remark;
	}
	public void setRemark(String _remark) {
		firePropertyChange("remark", remark, _remark);
		this.remark = _remark;
	}
	public Date getCreatedate() {
		return createdate;
	}
	public void setCreatedate(Date _createdate) {
		firePropertyChange("createdate", createdate, _createdate);
		this.createdate = _createdate;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String _creator) {
		firePropertyChange("creator", creator, _creator);
		this.creator = _creator;
	}
	public Integer getTtindex() {
		return ttindex;
	}
	public void setTtindex(Integer _ttindex) {
		firePropertyChange("ttindex", ttindex, _ttindex);
		this.ttindex = _ttindex;
	}
	
	
	public String getFilename() {
		return filename;
	}
	public void setFilename(String _filename) {
		firePropertyChange("filename", filename, _filename);
		this.filename = _filename;
	}
	public String getFiletype() {
		return filetype;
	}
	public void setFiletype(String _filetype) {
		firePropertyChange("filetype", filetype, _filetype);
		this.filetype = _filetype;
	}
	
	public String getWenzhongid() {
		return wenzhongid;
	}
	public void setWenzhongid(String _wenzhongid) {
		firePropertyChange("wenzhongid", wenzhongid, _wenzhongid);
		this.wenzhongid = _wenzhongid;
	}
	@Override
	protected void setupFields() throws DAOException {
		addField("uuid", "UUID");
		addField("ttmc", "TTMC");
		addField("ttpath", "TTPATH");
		addField("ttindex", "TTINDEX");
		addField("createdate", "CREATEDATE");
		addField("creator", "CREATOR");
		addField("remark", "REMARK");
		addField("filename", "FILENAME");
		addField("filetype", "FILETYPE");
		addField("wenzhong","WENZHONG");
		addField("wenzhongid","WENZHONGID");
		setTableName("FDT_OA_TTINFO");
		addKey("UUID");
	}
	
	
}
