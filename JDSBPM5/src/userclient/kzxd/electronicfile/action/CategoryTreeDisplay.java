package kzxd.electronicfile.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import net.itjds.common.org.base.Org;
import net.itjds.common.org.base.OrgManagerFactory;
import net.itjds.usm.orgtree.ExtTreeJson;
import net.sf.json.JSONArray;

import kzxd.electronicfile.dao.RecordCategoryDAO;
import kzxd.electronicfile.dao.RollDAO;
import kzxd.electronicfile.entity.CategoryTreeBean;
import kzxd.rtx.ImportData;

public class CategoryTreeDisplay {
	
	public String getChildTree(String cId,boolean bool,String choose){
		CategoryTreeBean treebean = new CategoryTreeBean();
		treebean.setId("electronicroot");
		treebean.setName("电子目录");
		treebean.setText("电子目录"); 
		List tlist = new ArrayList();
		if(bool){
			//查询根目录下的案卷
			addRollItem(treebean);
		}
		List list = new RecordCategoryAction().findByPid("electronicroot");
		treebean.setLeaf(false);
		
		for(int i=0;i<list.size();i++){
			addChildren(treebean,(RecordCategoryDAO)list.get(i),bool);
		}
		
		tlist.add(treebean);
		JSONArray  ja = JSONArray.fromObject(tlist);
		return ja.toString();
		
	}
	
	public CategoryTreeBean addChildren(CategoryTreeBean parentbean,RecordCategoryDAO rdao,boolean bool){
		CategoryTreeBean childbean = new CategoryTreeBean();
		childbean.setId(rdao.getUuid());
		childbean.setName(rdao.getCategoryname());
		childbean.setText(rdao.getCategoryname());
		parentbean.getChildren().add(childbean);
		
		childbean.setLeaf(false);
		List list = new RecordCategoryAction().findByPid(rdao.getUuid());
		if(list!=null&&list.size()>0){
			for(int i=0;i<list.size();i++){
				addChildren(childbean,(RecordCategoryDAO)list.get(i),bool);
			}
			
		}
		if(bool){
			//查询此目录下的所有已封卷案卷
			addRollItem(childbean);
		}
		return parentbean;
	}
	
	public void addRollItem(CategoryTreeBean parentbean){
		List list = new RollManagerAction().findByCid(parentbean.getId());
		if(list!=null&&list.size()>0){
			for(int i=0;i<list.size();i++){
				RollDAO rolldao = (RollDAO)list.get(i);
				CategoryTreeBean childbean = new CategoryTreeBean();
				childbean.setId(rolldao.getUuid());
				childbean.setName(rolldao.getRollname());
				childbean.setText(rolldao.getRollname());
				childbean.setLeaf(true);
				//parentbean.addChild(childbean);
				parentbean.getChildren().add(childbean);
				//childbean.setParentbean(parentbean);
			}
		}
		
	}
	
	
}
