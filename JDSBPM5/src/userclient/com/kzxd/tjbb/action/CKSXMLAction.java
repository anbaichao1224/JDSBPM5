package com.kzxd.tjbb.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import net.itjds.common.org.base.OrgManagerFactory;
import net.itjds.common.org.base.Person;

import org.apache.struts2.ServletActionContext;

import com.kzxd.tjbb.entity.CKSXMLBean;
import com.kzxd.tjbb.service.CKSXMLMsg;
import com.opensymphony.xwork2.ActionSupport;

public class CKSXMLAction extends ActionSupport {

	private CKSXMLBean cksxmlbean;
	private List<CKSXMLBean> cksxmllist;
	private List types;
	private CKSXMLMsg cksxmlmsgimpl;
	
	private String fwck;
	private String cksx;
	private String sfns;
	
	private String start;
	private String limit;
	
	private String deluuid;
	
	public String finAllByBuMen(){
		
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		String personId = session.getAttribute("personId").toString();
		types= new ArrayList();
		try{
			Person person = OrgManagerFactory.getOrgManager().getPersonByID(personId);
			String bumen=person.getName();
			cksxmllist= cksxmlmsgimpl.finAllByBuMen(bumen);
			for(int i=0;i<cksxmllist.size();i++){
				String shixiang=cksxmllist.get(i).getCksx();
				CodeName type= new CodeName(shixiang,shixiang);
				types.add(type);
			}

		}catch(Exception e){
			e.printStackTrace();
		}
		return "success";
		
	}
	
	public String cksxmlAdd(){
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		String personId = session.getAttribute("personId").toString();
		
		CKSXMLBean cksxmlbean=new CKSXMLBean();
		cksxmlbean.setFwck(fwck);
		cksxmlbean.setCksx(cksx);
		cksxmlbean.setSfns(sfns);
		cksxmlbean.setPersonid(personId);
		
		try{
			cksxmlmsgimpl.add(cksxmlbean);
			 return "success";
		}catch(Exception e){
			e.printStackTrace();
			return "error";
		}
		
	}
	
	public String shiXiangFindByBuMen(){
		
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response=ServletActionContext.getResponse();
		HttpSession session = request.getSession();
		String personId = session.getAttribute("personId").toString();
		try{
			Person person = OrgManagerFactory.getOrgManager().getPersonByID(personId);
			String bumen=person.getName();
			int index=Integer.parseInt(start);
			int pageSize=Integer.parseInt(limit);
			cksxmllist= cksxmlmsgimpl.shiXiangFindByPersonid(personId);
			int totalCount = cksxmllist.size();
			String json="{totalProperty:"+totalCount+",root:[";
			if(totalCount<pageSize+index){
				for(int i=index;i<totalCount;i++){
					   json+="{uuid:'"+cksxmllist.get(i).getUuid()+"',cksx:'"+cksxmllist.get(i).getCksx()
					   		+"',sfns:'"+cksxmllist.get(i).getSfns()+"'}";
						if(i!=(totalCount-1)){
							json+=",";
						}
				}
			}else{
				for(int i=index;i<pageSize+index;i++){
					   json+="{uuid:'"+cksxmllist.get(i).getUuid()+"',cksx:'"+cksxmllist.get(i).getCksx()
				   		+"',sfns:'"+cksxmllist.get(i).getSfns()+"'}";
				   if(i!=(pageSize+index-1)){
					   json+=",";
				   }
				}
			}
			json+="]}";
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().write(json);

		}catch(Exception e){
			e.printStackTrace();
		}
		
		return null;
		
	}
	
	public String delCksxml(){
		HttpServletResponse response=ServletActionContext.getResponse();
		String uid[]=deluuid.split(",");
		try{
			for(int i=0;i<uid.length;i++){
				String id=uid[i];
				CKSXMLBean bean=cksxmlmsgimpl.getByUuid(id);
				cksxmlmsgimpl.delete(bean);
			}
			 response.getWriter().print("{success:true}");
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
		
	}
	
	public String huoQuXiaLaValue(){
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response=ServletActionContext.getResponse();
		HttpSession session = request.getSession();
		String personId = session.getAttribute("personId").toString();
		try{
			Person person = OrgManagerFactory.getOrgManager().getPersonByID(personId);
			String bumen=person.getName();
			cksxmllist= cksxmlmsgimpl.shiXiangFindByPersonid(personId);
			int totalCount = cksxmllist.size();
			String json="{root:[";
				for(int i=0;i<totalCount;i++){
					   json+="{value:'"+cksxmllist.get(i).getCksx()+"',text:'"+cksxmllist.get(i).getCksx()
					   		+"'}";
						if(i!=(totalCount-1)){
							json+=",";
						}
				}
			json+="]}";
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().write(json);

		}catch(Exception e){
			e.printStackTrace();
		}
		
		return null;
		
	}

	public CKSXMLBean getCksxmlbean() {
		return cksxmlbean;
	}

	public void setCksxmlbean(CKSXMLBean cksxmlbean) {
		this.cksxmlbean = cksxmlbean;
	}

	public List<CKSXMLBean> getCksxmllist() {
		return cksxmllist;
	}

	public void setCksxmllist(List<CKSXMLBean> cksxmllist) {
		this.cksxmllist = cksxmllist;
	}

	public List getTypes() {
		return types;
	}

	public void setTypes(List types) {
		this.types = types;
	}

	public CKSXMLMsg getCksxmlmsgimpl() {
		return cksxmlmsgimpl;
	}

	public void setCksxmlmsgimpl(CKSXMLMsg cksxmlmsgimpl) {
		this.cksxmlmsgimpl = cksxmlmsgimpl;
	}

	public String getFwck() {
		return fwck;
	}

	public void setFwck(String fwck) {
		this.fwck = fwck;
	}

	public String getCksx() {
		return cksx;
	}

	public void setCksx(String cksx) {
		this.cksx = cksx;
	}

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getLimit() {
		return limit;
	}

	public void setLimit(String limit) {
		this.limit = limit;
	}

	public String getDeluuid() {
		return deluuid;
	}

	public void setDeluuid(String deluuid) {
		this.deluuid = deluuid;
	}

	public String getSfns() {
		return sfns;
	}

	public void setSfns(String sfns) {
		this.sfns = sfns;
	}
	
}
