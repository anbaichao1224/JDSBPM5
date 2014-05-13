package kzxd.lucenetest;

import java.util.List;

import kzxd.electronicfile.entity.RollPepodomBean;

import com.kzxd.index.entity.RecordData;
import com.opensymphony.xwork2.Action;

public class PepodomSearchAction implements Action {

	public String execute() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	public String searchlist(){
		datalist = new PepodomSearchDemo().serchMethod(uid,filetype,personid,ispass);
		totalCount = datalist.size();
		return "";
	}
	
	private String uid;
	private String filetype;
	private String personid;
	private String endtime;
	private String starttime;
	private String ispass;
	private List<RollPepodomBean> datalist;

	private int totalCount;
	public List<RollPepodomBean> getDatalist() {
		return datalist;
	}

	public void setDatalist(List<RollPepodomBean> datalist) {
		this.datalist = datalist;
	}

	
	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getFiletype() {
		return filetype;
	}

	public void setFiletype(String filetype) {
		this.filetype = filetype;
	}

	public String getPersonid() {
		return personid;
	}

	public void setPersonid(String personid) {
		this.personid = personid;
	}

	public String getEndtime() {
		return endtime;
	}

	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public String getStarttime() {
		return starttime;
	}

	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}

	public String getIspass() {
		return ispass;
	}

	public void setIspass(String ispass) {
		this.ispass = ispass;
	}
	
	
	
	
	
	
	
}
