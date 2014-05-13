package net.kzxd.dj.bean;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FdtOaDjBean {
	
	private String fsr;

		public String getFsr() {
			return fsr;
		}
		public void setFsr(String fsr) {
			this.fsr = fsr;
		}
		

	private String uuid;
	private String docbt;
	private Date rdate;
	private String department;
	private String classification;
	private String emergency;
	private String sn;
	private String attname;
	private String atturl;
	private String djdel;
	private String modeltype;
	private String xmlmodel;
	private String lwddate;//
	private String lwbh;
	private String djr;
	private String flag;
	private String jbluuid;
	private Date cdate;
	private String  processinstid;
	private String ldbh;
	private String bmid;
	private String ryid;
	
	public String getRyid() {
		return ryid;
	}
	public void setRyid(String ryid) {
		this.ryid = ryid;
	}
	public String getBmid() {
		return bmid;
	}
	public void setBmid(String bmid) {
		this.bmid = bmid;
	}
	public String getProcessinstid() {
		return processinstid;
	}
	public void setProcessinstid(String processinstid) {
		this.processinstid = processinstid;
	}
	public Date getCdate() {
		return cdate;
	}
	public void setCdate(Date cdate) {
		this.cdate = cdate;
	}
	public String getLwbh() {
		return lwbh;
	}

	public void setLwbh(String lwbh) {
		this.lwbh = lwbh;
	}

	public String getDjr() {
		return djr;
	}

	public void setDjr(String djr) {
		this.djr = djr;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getJbluuid() {
		return jbluuid;
	}

	public void setJbluuid(String jbluuid) {
		this.jbluuid = jbluuid;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getDocbt() {
		return docbt;
	}

	public void setDocbt(String docbt) {
		this.docbt = docbt;
	}

	public Date getRdate() {
		return rdate;
	}

	public void setRdate(Date rdate) {
		this.rdate = rdate;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getClassification() {
		return classification;
	}

	public void setClassification(String classification) {
		this.classification = classification;
	}

	public String getEmergency() {
		return emergency;
	}

	public void setEmergency(String emergency) {
		this.emergency = emergency;
	}

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public String getAttname() {
		return attname;
	}

	public void setAttname(String attname) {
		this.attname = attname;
	}

	public String getAtturl() {
		return atturl;
	}

	public void setAtturl(String atturl) {
		this.atturl = atturl;
	}

	public String getDjdel() {
		return djdel;
	}

	public void setDjdel(String djdel) {
		this.djdel = djdel;
	}

	public String getModeltype() {
		return modeltype;
	}

	public void setModeltype(String modeltype) {
		this.modeltype = modeltype;
	}

	public String getXmlmodel() {
		return xmlmodel;
	}

	public void setXmlmodel(String xmlmodel) {
		this.xmlmodel = xmlmodel;
	}
	
	public String getLwddate() {
		return lwddate;
	}

	public void setLwddate(String lwddate) {
		this.lwddate = lwddate;
	}

	public String getProperty(FdtOaDj fod,String fname){
		String fieldName = zhfield(fname);
		if(!fieldName.equals("")){
		try {
			//Field field = fod.getClass().getDeclaredField(fieldName);
			Field[] fields = fod.getClass().getDeclaredFields();
			Field.setAccessible(fields, true);
			for(int i=0;i<fields.length;i++){
				if(fieldName.equals(fields[i].getName())){
					try {
						if(fieldName.equals("rdate")){
							return formatDate((Date)fields[i].get(fod));
						}
						
						return fields[i].get(fod).toString();
					} catch (IllegalArgumentException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		}
		return "";
	}
	
	public String zhfield(String fieldName){
		if(fieldName.equals("wjbt")){
			return "docbt";
		}else if(fieldName.equals("lwbh")){
			return "sn";
		}else if(fieldName.equals("lwjg")){
			return "department";
		}else if(fieldName.equals("swrq")){
			return "rdate";
		}else{
			return "";
		}
	}
	
	public String formatDate(Date date){
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		return format.format(date);
	}
	public String getLdbh() {
		return ldbh;
	}
	public void setLdbh(String ldbh) {
		this.ldbh = ldbh;
	}
}
