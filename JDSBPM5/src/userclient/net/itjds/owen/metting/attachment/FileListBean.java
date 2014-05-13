package net.itjds.owen.metting.attachment;

public class FileListBean {
	
	private String uuid;

	private String createdate;
	
	private String creatorid;

	private String creatorname;

	private String filepath;

	private String filename;

	private String sxxxid;

	private String filetype;
	
	private Integer isToPdf;
	

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
	 * @return the uuid
	 */
	public String getUuid() {
		return uuid;
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
	 * @param uuid the uuid to set
	 */
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getCreatedate() {
		return createdate;
	}

	public void setCreatedate(String createdate) {
		this.createdate = createdate;
	}

	public String getCreatorid() {
		return creatorid;
	}

	public void setCreatorid(String creatorid) {
		this.creatorid = creatorid;
	}

	public String getCreatorname() {
		return creatorname;
	}

	public void setCreatorname(String creatorname) {
		this.creatorname = creatorname;
	}

	public String getSxxxid() {
		return sxxxid;
	}

	public void setSxxxid(String sxxxid) {
		this.sxxxid = sxxxid;
	}

	public Integer getIsToPdf() {
		return isToPdf;
	}

	public void setIsToPdf(Integer isToPdf) {
		this.isToPdf = isToPdf;
	}
	
	

}
