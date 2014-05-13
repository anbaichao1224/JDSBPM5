package kzxd.lucenetest;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import net.itjds.common.org.base.OrgManagerFactory;
import net.itjds.common.org.base.Person;
import net.itjds.common.org.base.PersonNotFoundException;
import net.itjds.common.org.base.PersonRole;

import kzxd.electronicfile.entity.RollPepodomBean;

import com.opensymphony.xwork2.Action;

public class SearchAction implements Action {

	public String execute() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	public String searchlist(){
		if(seartype.equals("2")){
			title = filetitle;
		}else if(seartype.equals("1")){
			title = rolltitle;
			miji = rollnum;
			jjcd = yearnum;
		}
		datalist = new SearchDemo().serchMethod(seartype,title,docvalue,miji,jjcd,yzh);
		totalCount = datalist.size();
		return SUCCESS;
	}
	
	
	public String getRole() throws PersonNotFoundException{
		HttpServletRequest request = ServletActionContext.getRequest();
		String personId = request.getSession().getAttribute("personId").toString();
		Person person = (Person)OrgManagerFactory.getOrgManager().getPersonByID(personId);
		List<PersonRole> rlist = person.getRoleList();
		isadmin = "n";
		for(PersonRole pr:rlist){
			if(pr.getName().equals("档案管理员")){
				isadmin = "y";
			}
		}
		return "search";
	}
	
	private String title;
	private String filetitle;
	private String miji;
	private String createdate;
	private String jjcd;
	private String yzh;
	private String rolltitle;
	private String rollnum;
	private String yearnum;
	private String docvalue;
	private String isadmin;//是否为管理员
	private String seartype="1";//标识搜索类型（普通/高级）
	private List<RollPepodomBean> datalist;

	private int totalCount;
	public List<RollPepodomBean> getDatalist() {
		return datalist;
	}

	public void setDatalist(List<RollPepodomBean> datalist) {
		this.datalist = datalist;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public String getMiji() {
		return miji;
	}

	public void setMiji(String miji) {
		this.miji = miji;
	}

	public String getCreatedate() {
		return createdate;
	}

	public void setCreatedate(String createdate) {
		this.createdate = createdate;
	}

	public String getJjcd() {
		return jjcd;
	}

	public void setJjcd(String jjcd) {
		this.jjcd = jjcd;
	}

	public String getYzh() {
		return yzh;
	}

	public void setYzh(String yzh) {
		this.yzh = yzh;
	}

	public String getFiletitle() {
		return filetitle;
	}

	public void setFiletitle(String filetitle) {
		this.filetitle = filetitle;
	}

	public String getRolltitle() {
		return rolltitle;
	}

	public void setRolltitle(String rolltitle) {
		this.rolltitle = rolltitle;
	}

	public String getRollnum() {
		return rollnum;
	}

	public void setRollnum(String rollnum) {
		this.rollnum = rollnum;
	}

	public String getYearnum() {
		return yearnum;
	}

	public void setYearnum(String yearnum) {
		this.yearnum = yearnum;
	}

	public String getSeartype() {
		return seartype;
	}

	public void setSeartype(String seartype) {
		this.seartype = seartype;
	}

	public String getIsadmin() {
		return isadmin;
	}

	public void setIsadmin(String isadmin) {
		this.isadmin = isadmin;
	}

	public String getDocvalue() {
		return docvalue;
	}

	public void setDocvalue(String docvalue) {
		this.docvalue = docvalue;
	}
	
	
	
	
	
	
	
}
