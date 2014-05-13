package net.itjds.fdt.view.fdtoafwbl;

import net.itjds.bpm.data.xmlproxy.EsbBeanAnnotation;
import net.itjds.bpm.data.xmlproxy.MapdaoBeanAnnotation;
import net.itjds.fdt.db.fdtoafwbl.*;
import net.itjds.usm2.define.annotation.PanelItemDefine;
import net.itjds.usm2.define.annotation.ViewPortDefine;

@ViewPortDefine(title = "���İ���", layout = "border")

@EsbBeanAnnotation(id="FdtOaFwblViewport",
		name="���İ���",
		expressionArr="FdtOaFwblViewport(R(\"where\"))",
		desc="���İ��� by DAOTools ",
		dataType="context"
		)
		

public class FdtOaFwblViewport {
	
	private FdtOaFwblCartPanel cartPanel;
	private String where ;

	public FdtOaFwblViewport(String where){
		this.where=where;
	}
	

	
	@PanelItemDefine( region = "center",title = "���İ���")
	public FdtOaFwblCartPanel getFdtOaFwblCartPanel() {
	   if (cartPanel==null){
			cartPanel=new FdtOaFwblCartPanel(where);
		}
		return cartPanel;
	}
}