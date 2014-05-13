package kzxd.documentmodel.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import kzxd.documentmodel.entity.KZXDDocumentModel;
import kzxd.documentmodel.servers.KzxdDocumentServer;
import kzxd.documentmodel.servers.impl.KzxdDocumentServerImpl;
import net.itjds.common.org.base.Org;
import net.itjds.common.org.base.Person;
import net.itjds.userclient.common.BPMActionBase;

import org.apache.struts2.ServletActionContext;

public class DocumentModelUpload extends BPMActionBase{

	private List<KZXDDocumentModel> mlists = new ArrayList<KZXDDocumentModel>();
	
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		HttpServletRequest request=ServletActionContext.getRequest();
		KzxdDocumentServer server = new KzxdDocumentServerImpl();
		Person person = getCurrPerson();
		List<Org> orgs = person.getOrgList();
		String[] org = new String[orgs.size()];
		for(int i = 0; i < orgs.size(); i++) {
			Org o = orgs.get(i);
			org[i] = o.getName();
		}
		mlists = server.findByDeptnull(org);
		request.setAttribute("mlists", mlists);
		request.setAttribute("orgs", orgs);
		return SUCCESS;
	}
	
}
