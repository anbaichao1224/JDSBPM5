package net.itjds.usm2.service.json;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import net.itjds.bpm.data.xmlproxy.EsbBeanAnnotation;
import net.itjds.bpm.data.xmlproxy.MapdaoBeanAnnotation;
import net.itjds.common.expression.function.AbstractFunction;
import net.itjds.usm2.db.util.EsbUtil;
import net.itjds.usm2.define.tree.StaticTreeNode;
import net.itjds.usm2.define.tree.TreeNode;
import net.itjds.usm2.define.tree.annotation.TreeNodeDefine;
import net.sf.json.JSONArray;


@EsbBeanAnnotation(
		id = "JSONTree", 
		name = "TREE格式化为JSON", 
		expressionArr = "GetJSONTree($currViewData())", 
		desc = "将当前传入的TREE节点对象格式化为JSON输出", 
		dataType = "action"
)

public class GetJSONTree extends  AbstractFunction{
	
	public List<TreeNode> getChildNodeList(Object obj){
		List<TreeNode> children = new ArrayList<TreeNode>();
		TreeNode objNode=null;
		if (obj instanceof TreeNode){
			 objNode=(TreeNode)obj; 
		}
	
		  Method[] methods= obj.getClass().getMethods();
		   for(int k=0;k<methods.length;k++){
			   Method method=methods[k];
			   TreeNodeDefine node= method.getAnnotation(TreeNodeDefine.class);
			   if (node!=null){
				
				List listobj=EsbUtil.getProxyList(obj,method.getName());
				for(int f=0;f<listobj.size();f++){
					TreeNode cnode=(TreeNode) listobj.get(f);
					if (cnode instanceof StaticTreeNode){
						StaticTreeNode snode=(StaticTreeNode)cnode;
						List childNodeList=getChildNodeList(snode);
						if (childNodeList.size()>0){
							    snode.setLeaf(false);
							 
								snode.setChildren(getChildNodeList(snode));
						}
						
						   snode.setIndex(f);
						children.add(snode);
					}else{
						children.add(cnode);
					}
					
				}
				
				
			} 
		   }
		return children;
	}
	public String perform(Object obj){
		List<TreeNode> children=this.getChildNodeList(obj);
				String json=null;
				try{
					 json=JSONArray.fromObject(children).toString();
				}catch(Exception e){
					e.printStackTrace();
				}
				return json;
			}

	
}
