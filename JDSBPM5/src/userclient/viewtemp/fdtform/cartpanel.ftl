package ${package}.view.${tempbeanid};
import java.util.List;

import ${package}.db.${table.className?lower_case}.impl.${table.className}UsmServiceImpl;
import net.itjds.usm2.USMException;

<#list tableList as item>
import ${package}.db.${item.otherTable.className?lower_case}.*;
<#if item.otherTable.className != table.className>
import ${package}.view.${item.otherTable.className?lower_case}.*;
</#if> 
</#list>

import net.itjds.usm2.define.cartlayoutpanel.annotation.CartItemsDefine;
import net.itjds.usm2.define.cartlayoutpanel.annotation.CartPanelDefine;

@CartPanelDefine(activeItem = 0,xtype = "CartPanel")
public class ${table.className}CartPanel {
	
	private List daoList; 
	
	private String where;
		
	public ${table.className}CartPanel(String where){
		this.where=where;
	}

	@CartItemsDefine(title = "${table.cnname}")
	public List<${table.className}Grid> get${table.className}ChildList() {
			if (daoList==null){
			 ${table.className}UsmServiceImpl service=new ${table.className}UsmServiceImpl();
			  try {
				   if (where==null){
				      daoList = service.loadAll();
				   }else{
				      daoList=service.getUsmWhere("where "+where);
				   }
				} catch (USMException e) {
					e.printStackTrace();
				}
		    }
		   return daoList;
	}
	
	
}
