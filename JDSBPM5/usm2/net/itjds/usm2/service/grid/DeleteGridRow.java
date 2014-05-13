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
		name = "ɾ��ָ����¼ID[;]�ָ�", 
		expressionArr = "DeleteGridRow($currFormUsmService(),Split(R(\"gridIds\"),\";\"))", 
		desc="ɾ��ָ����¼ID[;]�ָ�",
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
		return gridIds.size()+"����¼��ɾ����";	
	}
	
	

}
