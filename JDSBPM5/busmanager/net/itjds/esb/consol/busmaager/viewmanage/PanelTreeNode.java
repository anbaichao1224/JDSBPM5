package net.itjds.esb.consol.busmaager.viewmanage;

import java.util.ArrayList;
import java.util.List;

import net.itjds.usm2.define.grid.mapping.ColumnBean;
import net.itjds.usm2.define.mapping.FieldBean;
import net.itjds.usm2.define.mapping.PanelBean;
import net.itjds.usm2.define.tree.StaticTreeNode;
import net.itjds.usm2.define.tree.annotation.TreeNodeDefine;


public class PanelTreeNode extends StaticTreeNode {
	
	public PanelBean panelBean;
	private FieldBean fieldBean;

 	public PanelTreeNode(PanelBean panelBean){
 	     this.panelBean=panelBean;
 	     
 	     this.setText(panelBean.getName());
   	     this.setId(panelBean.getPath());
   	     this.setPanelName(convertedToLowercase(panelBean.getClass().getSimpleName()));
  		 List items=panelBean.getItems();
   		 this.setLeaf(true);
   		  if (items.size()>0){
   			  if (items.get(0) instanceof PanelBean){
   				 this.setLeaf(false);
   			  }
   		  }
   	    
  	}
 	
 	public PanelTreeNode(FieldBean fieldBean){
	     this.fieldBean=fieldBean;
	     if (fieldBean instanceof ColumnBean){
	    	 ColumnBean columnBean=(ColumnBean)fieldBean;
	         this.setText(columnBean.getHeader());
	     }else{
	    	 this.setText(fieldBean.getName()+"("+fieldBean.getTitle()+")");
	     }
	
  	     this.setId(fieldBean.getPath());
  	     this.setPanelName(convertedToLowercase(fieldBean.getClass().getSimpleName()));
  		 this.setLeaf(true);  	    
 	}
 	
	@TreeNodeDefine()
 	public List<PanelTreeNode> childPanel(){
		List child=new ArrayList();
		if (panelBean!=null){
			List items=panelBean.getItems();
			for(int k=0;k<items.size();k++){
				child.add(items.get(k));
			}
		}
 		return child;
 	}
	
	public String convertedToLowercase(String panelName)
	{
		String firstName=panelName.substring(0, 1);
		String name=panelName.substring(1);
		String newPanelName=firstName.toLowerCase()+name;
		return newPanelName;
	}


 
}
