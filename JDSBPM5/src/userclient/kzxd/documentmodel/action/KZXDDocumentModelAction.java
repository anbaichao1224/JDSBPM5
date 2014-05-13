package kzxd.documentmodel.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import kzxd.documentmodel.entity.KZXDDocumentModel;
import kzxd.documentmodel.servers.KzxdDocumentServer;
import kzxd.documentmodel.servers.impl.KzxdDocumentServerImpl;
import net.itjds.userclient.common.BPMActionBase;

public class KZXDDocumentModelAction extends BPMActionBase{

	private List<KZXDDocumentModel> mlists = new ArrayList<KZXDDocumentModel>();
	
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		KzxdDocumentServer server = new KzxdDocumentServerImpl();
		HttpServletRequest request=ServletActionContext.getRequest();
		request.setCharacterEncoding("utf-8");
		String currUserOrgName = new String(request.getParameter("currUserOrgName").getBytes("ISO8859-1"),"UTF-8");
		String[] org = new String[1];
		org[0] = currUserOrgName;
		mlists = server.findByDept(org);
		request.setAttribute("mlists", mlists);
		return "success";
	}
	
	

	public List<KZXDDocumentModel> getMlists() {
		return mlists;
	}

	public void setMlists(List<KZXDDocumentModel> mlists) {
		this.mlists = mlists;
	}
	
}
