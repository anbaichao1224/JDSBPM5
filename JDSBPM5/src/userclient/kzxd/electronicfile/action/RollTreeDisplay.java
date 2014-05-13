package kzxd.electronicfile.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import net.itjds.usm.orgtree.ExtTreeJson;
import net.sf.json.JSONArray;

import kzxd.electronicfile.dao.RecordCategoryDAO;
import kzxd.electronicfile.dao.RecordDataDAO;
import kzxd.electronicfile.dao.RollDAO;
import kzxd.electronicfile.entity.CategoryTreeBean;

public class RollTreeDisplay {
	
	public String getChildTree(String cId,boolean bool,String choose){
		
		CategoryTreeBean treebean = new CategoryTreeBean();
		treebean.setId("electronicroot");
		treebean.setName("电子档案目录");
		treebean.setText("电子档案目录");
		List tlist = new ArrayList();
		if(bool){
			//读取根目录下的案卷
			addRollItem(treebean);
		}
		List list = new RecordCategoryAction().findByPid(cId);
		if(list!=null&&list.size()>0){
			treebean.setLeaf(false);
		}else{
			treebean.setLeaf(true);
		}
		for(int i=0;i<list.size();i++){
			addChildren(treebean,(RollDAO)list.get(i),bool);
		}
		
		tlist.add(treebean);
		JSONArray  ja = JSONArray.fromObject(tlist);
		return ja.toString();
		
	}
	
	public CategoryTreeBean addChildren(CategoryTreeBean parentbean,RollDAO rdao,boolean bool){
		CategoryTreeBean childbean = new CategoryTreeBean();
		childbean.setId(rdao.getUuid());
		childbean.setName(rdao.getRollname());
		childbean.setText(rdao.getRollname());
		parentbean.getChildren().add(childbean);
		
		List list = new RecordCategoryAction().findByPid(rdao.getUuid());
		if(list!=null&&list.size()>0){
			childbean.setLeaf(false);
			for(int i=0;i<list.size();i++){
				addChildren(childbean,(RollDAO)list.get(i),bool);
			}
			
		}else{
			childbean.setLeaf(true);
		}
		if(bool){
			//读取此目录下的案卷
			addRollItem(childbean);
		}
		return parentbean;
	}
	
	public void addRollItem(CategoryTreeBean parentbean){
		List list = new RollManagerAction().findByCid(parentbean.getId());
		if(list!=null&&list.size()>0){
			for(int i=0;i<list.size();i++){
				RecordDataDAO ddao = (RecordDataDAO)list.get(i);
				CategoryTreeBean childbean = new CategoryTreeBean();
				childbean.setId(ddao.getUuid());
				childbean.setName(ddao.getTitle());
				childbean.setText(ddao.getTitle());
				childbean.setLeaf(true);
				//parentbean.addChild(childbean);
				parentbean.getChildren().add(childbean);
				//childbean.setParentbean(parentbean);
			}
		}
		
	}
	
	
}
