package net.itjds.usm2.service.grid;

import java.util.List;



import net.itjds.bpm.data.xmlproxy.EsbBeanAnnotation;
import net.itjds.bpm.data.xmlproxy.MapdaoBeanAnnotation;

import net.itjds.common.expression.function.AbstractFunction;
import net.itjds.common.logging.Log;
import net.itjds.common.logging.LogFactory;

import net.itjds.usm2.USMException;
import net.itjds.usm2.db.UsmService;
@EsbBeanAnnotation(id="deleteGridRow",
		name = "删除指定记录ID[;]分割", 
		expressionArr = "DeleteGridRow($currFormUsmService(),Split(R(\"gridIds\"),\";\"))", 
		desc="删除指定记录ID[;]分割",
		dataType="action"
		)
	
public class DeleteGridRow extends AbstractFunction{
	protected transient static final Log log = LogFactory.getLog(
			"DeleteGridRow", DeleteGridRow.class);
	
	public String  perform(UsmService service ,List<String> gridIds) {
		for(int k=0;k<gridIds.size();k++){
			try {
				service.deleteByKey( gridIds.get(k));
			} catch (USMException e) {
				e.printStackTrace();
			}
		}
		return gridIds.size()+"条记录被删除！";	
	}
	
	

}
