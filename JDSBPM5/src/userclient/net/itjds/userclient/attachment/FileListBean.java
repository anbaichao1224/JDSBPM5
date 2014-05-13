package net.itjds.userclient.attachment;

public class FileListBean {
	
	private String uuid;

	private String processinstid;

	private String activityinstid;

	private String filepath;

	private String filename;

	private String fileuploaduser;

	private String fileuploaddate;

	private Integer fileindex;

	private String remark;

	private String filetype;
	
	private Integer isToPdf;
	
	

	public Integer getIsToPdf() {
		return isToPdf;
	}

	public void setIsToPdf(Integer isToPdf) {
		this.isToPdf = isToPdf;
	}

	/**
	 * @return the activityinstid
	 */
	public String getActivityinstid() {
		return activityinstid;
	}

	/**
	 * @return the fileindex
	 */
	public Integer getFileindex() {
		return fileindex;
	}

	/**
	 * @return the filename
	 */
	public String getFilename() {
		return filename;
	}

	/**
	 * @return the filepath
	 */
	public String getFilepath() {
		return filepath;
	}

	/**
	 * @return the filetype
	 */
	public String getFiletype() {
		return filetype;
	}

	/**
	 * @return the fileuploaddate
	 */
	public String getFileuploaddate() {
		return fileuploaddate;
	}

	/**
	 * @return the fileuploaduser
	 */
	public String getFileuploaduser() {
		return fileuploaduser;
	}

	/**
	 * @return the processinstid
	 */
	public String getProcessinstid() {
		return processinstid;
	}

	/**
	 * @return the remark
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * @return the uuid
	 */
	public String getUuid() {
		return uuid;
	}

	/**
	 * @param activityinstid the activityinstid to set
	 */
	public void setActivityinstid(String activityinstid) {
		this.activityinstid = activityinstid;
	}

	/**
	 * @param fileindex the fileindex to set
	 */
	public void setFileindex(Integer fileindex) {
		this.fileindex = fileindex;
	}

	/**
	 * @param filename the filename to set
	 */
	public void setFilename(String filename) {
		this.filename = filename;
	}

	/**
	 * @param filepath the filepath to set
	 */
	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}

	/**
	 * @param filetype the filetype to set
	 */
	public void setFiletype(String filetype) {
		this.filetype = filetype;
	}

	/**
	 * @param fileuploaddate the fileuploaddate to set
	 */
	public void setFileuploaddate(String fileuploaddate) {
		this.fileuploaddate = fileuploaddate;
	}

	/**
	 * @param fileuploaduser the fileuploaduser to set
	 */
	public void setFileuploaduser(String fileuploaduser) {
		this.fileuploaduser = fileuploaduser;
	}

	/**
	 * @param processinstid the processinstid to set
	 */
	public void setProcessinstid(String processinstid) {
		this.processinstid = processinstid;
	}

	/**
	 * @param remark the remark to set
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * @param uuid the uuid to set
	 */
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

}
