/*jadclipse*/// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.

package net.itjds.owen.metting.action;

import java.io.File;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

import javax.servlet.http.HttpServletResponse;
import net.itjds.bpm.engine.WorkflowClientService;
import net.itjds.bpm.engine.query.*;
import net.itjds.common.database.DBBeanBase;
import net.itjds.common.org.base.Org;
import net.itjds.common.org.base.OrgManagerFactory;
import net.itjds.common.org.base.OrgNotFoundException;
import net.itjds.common.org.base.Person;
import net.itjds.common.org.base.PersonNotFoundException;
import net.itjds.fdt.metting.BpmMatterDAO;
import net.itjds.fdt.metting.BpmMatterInfoDAO;
import net.itjds.fdt.metting.BpmMettingDAO;
import net.itjds.fdt.metting.MatterInfoListBean;
import net.itjds.fdt.metting.MatterListBean;
import net.itjds.fdt.metting.MettingBean;
import net.itjds.j2ee.dao.DAOFactory;
import net.itjds.userclient.attachment.BpmAttachmentDAO;
import net.itjds.userclient.attachment.FileListBean;
import net.itjds.userclient.common.BPMActionBase;
import net.itjds.userclient.common.BPMUserClientUtil;
import net.itjds.usm.orgtree.ExtNodeBean;
import net.itjds.usm.orgtree.ExtTreeJson;
import net.itjds.worklist.list.action.BPMClientBaseBinding;
import net.sf.json.JSONArray;

import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

// Referenced classes of package net.itjds.worklist.list.action:
//            BPMClientBaseBinding

public class MettingExtTreeDisplay {

	String[][] hyjds = {{"hqxtgz","会前协调工作"},{"hqzbgz","会前准备工作"},{"hyqjgz","会议期间工作",},{"hhgz","会后工作"}};
	public String getChildTree(String mid, boolean bool, String choose) {

		if (mid == null) {
			// return this.getTopTree(bool,choose);
		}

		Org org = null;
		
		ExtNodeBean childNode = new ExtNodeBean();
		
			childNode.setId("toproot");
			childNode.setText("组织机构");
			childNode.setCnName("组织机构");
			//childNode.setOrglevel(0);
			childNode.setUid("toproot");
			if (bool) {
				//addPersonItem(childNode, org);
				addPersonItem(childNode,"","");
			}
			childNode.setLeaf("true");
			List mlist = new BpmMettingAction().getAllMetting();
			for(int j=0;j<mlist.size();j++){
				addChildNode(childNode,(BpmMettingDAO)mlist.get(j),true,bool,mid);
			}
		ExtTreeJson tree = new ExtTreeJson();
		tree.setNode(childNode);
		StringBuffer layoutJson = new StringBuffer();
		tree.serializChild(layoutJson, choose);
		return layoutJson.toString();
	}

	protected void addPersonItem(ExtNodeBean parentnode, String ID,String mid) {
		List MatterInfoList = new BpmMatterInfoAction().getByhyjd(ID,mid);
		if(!ID.equals("")){
		for (int i = 0; MatterInfoList.size() > i; i++) {
			BpmMatterInfoDAO person = (BpmMatterInfoDAO) MatterInfoList.get(i);
			ExtNodeBean inputNode = new ExtNodeBean();
			inputNode.setId(person.getUuid());
			inputNode.setText(person.getSxmc());
			inputNode.setCnName(person.getSxmc());
			inputNode.setUid("person" + person.getUuid());
			inputNode.setIcon("/usm/img/personicon.jpg");
			inputNode.setLeaf("true");
			parentnode.addChild(inputNode);
			inputNode.setParent(parentnode);

		}
		}
	}

	private void addChildNode(ExtNodeBean parentnode,BpmMettingDAO org,boolean child,boolean bool,String mid) {	
	
	ExtNodeBean inputNode = new ExtNodeBean();
	inputNode.setId(org.getUuid());
	inputNode.setText(org.getHymc());
	inputNode.setCnName(org.getHymc());
	inputNode.setOrglevel(0);
	inputNode.setUid("org"+org.getUuid());
	parentnode.addChild(inputNode);
	inputNode.setParent(parentnode);
	inputNode.setLeaf("false");	
	boolean b = false;
	//child 是什么意思？
	for (int k = 0; k < hyjds.length; k++) {
		addChildNodeself(inputNode,hyjds[k][0],hyjds[k][1], true, bool,mid);
	}
	//return parentnode;


}
	
	private ExtNodeBean addChildNodeself(ExtNodeBean parentnode,String ID,String name,boolean child,boolean bool,String mid) {	
		ExtNodeBean inputNode = new ExtNodeBean();
		inputNode.setId(ID);
		inputNode.setText(name);
		inputNode.setCnName(name);
		inputNode.setOrglevel(0);
		inputNode.setUid("org"+ID);
		parentnode.addChild(inputNode);
		inputNode.setParent(parentnode);
		inputNode.setLeaf("false");	
		boolean b = false;
		//child 是什么意思？
		if(bool){
			addPersonItem(inputNode,ID,mid);
		}
	return parentnode;
	}
	
	//待办列表
	public String getlbChildTree(String orgId,boolean bool,String choose,List<MettingBean> mbeanlist) {	
		
		  if (orgId==null ){
			  //return this.getTopTree(bool,choose);
		  }
		
		  //Org org=null;
	        //try {
				//org = OrgManagerFactory.getOrgManager().getOrgByID(orgId);
			//} catch (OrgNotFoundException e) {
				//e.printStackTrace();
				//return this.getTopTree(bool,choose);
			//}
		ExtNodeBean childNode = new ExtNodeBean();
		
		childNode.setId("toproot");
		childNode.setText("组织机构");
		childNode.setCnName("组织机构");
		//childNode.setOrglevel(0);
		childNode.setUid("toproot");
		if (bool) {
			//addPersonItem(childNode, org);
			addPersonItem(childNode,new MettingBean());
		}
		/*if (org.getChildren().length>0){
			childNode.setLeaf("true");	
		}else{
			childNode.setLeaf("false");	
		}*/
		childNode.setLeaf("true");
	    		
	    		for(int k=0;k<mbeanlist.size();k++){
	    			addChildNode(childNode,mbeanlist.get(k),true,bool);
	    		}
	    		
			
		    ExtTreeJson tree = new ExtTreeJson();
	        tree.setNode(childNode);
	        StringBuffer layoutJson = new StringBuffer();
	        tree.serializChild(layoutJson,choose);
	        return layoutJson.toString();
		}
	
	protected void addPersonItem(ExtNodeBean parentnode,MettingBean org) {
		List<MatterInfoListBean> matterinfos=org.getMatterInfos();
		if(matterinfos!=null){
		for(int i=0;matterinfos.size()>i;i++){
			//Person person=(Person) persons.get(i);
			MatterInfoListBean matterinfo = matterinfos.get(i);
			ExtNodeBean inputNode = new ExtNodeBean();
			inputNode.setId(matterinfo.getUuid());	
			inputNode.setText(matterinfo.getSxmc());
			inputNode.setCnName(matterinfo.getSxmc());
			inputNode.setUid("person"+matterinfo.getUuid());
			inputNode.setIcon("/usm/img/personicon.jpg");
			inputNode.setLeaf("true");
			parentnode.addChild(inputNode);
			inputNode.setParent(parentnode);

		}
		}
	}
	
	private ExtNodeBean addChildNode(ExtNodeBean parentnode,MettingBean org,boolean child,boolean bool) {	
		ExtNodeBean inputNode = new ExtNodeBean();
		inputNode.setId(org.getId());
		inputNode.setText(org.getName());
		inputNode.setCnName(org.getName());
		inputNode.setOrglevel(0);
		inputNode.setUid("org"+org.getId());
		parentnode.addChild(inputNode);
		inputNode.setParent(parentnode);
		if (org.getChildren().size() == 0 && org.getMatterInfos().size() == 0) {
			inputNode.setLeaf("true");
		} else {
			inputNode.setLeaf("false");
		}
		boolean b = false;
		//child 是什么意思？
		if (child){
			for(int k=0;k<org.getChildren().size();k++){
				
                //判断当前子部门是否还有子部门
				if(org.getChildren().get(k).getChildren().size()>0){
					b = true;
				}else{
					b=false;
				}
    			addChildNode(inputNode,org.getChildren().get(k),b,bool);	
    		}
		}
		
		if(bool){
			addPersonItem(inputNode,org);
		}
		return parentnode;

	}
}