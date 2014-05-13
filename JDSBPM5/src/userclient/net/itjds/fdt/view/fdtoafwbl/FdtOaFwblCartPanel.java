package net.itjds.fdt.view.fdtoafwbl;
import java.util.List;

import net.itjds.fdt.db.fdtoafwbl.impl.FdtOaFwblUsmServiceImpl;
import net.itjds.usm2.USMException;


import net.itjds.usm2.define.cartlayoutpanel.annotation.CartItemsDefine;
import net.itjds.usm2.define.cartlayoutpanel.annotation.CartPanelDefine;

@CartPanelDefine(activeItem = 0,xtype = "CartPanel")
public class FdtOaFwblCartPanel {
	
	private List daoList; 
	
	private String where;
		
	public FdtOaFwblCartPanel(String where){
		this.where=where;
	}

	@CartItemsDefine(title = "局文，内部回函，党组文")
	public List<FdtOaFwblGrid> getFdtOaFwblChildList() {
			if (daoList==null){
			 FdtOaFwblUsmServiceImpl service=new FdtOaFwblUsmServiceImpl();
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
