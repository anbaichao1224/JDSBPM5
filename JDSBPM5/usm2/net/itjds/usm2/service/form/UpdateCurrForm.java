package net.itjds.usm2.service.form;



import net.itjds.bpm.data.xmlproxy.EsbBeanAnnotation;
import net.itjds.bpm.data.xmlproxy.MapdaoBeanAnnotation;
import net.itjds.common.expression.function.AbstractFunction;
import net.itjds.usm2.USMException;
import net.itjds.usm2.UsmProxy;
import net.itjds.usm2.db.UsmService;
import net.itjds.usm2.db.util.EsbUtil;


@EsbBeanAnnotation(
		id = "UpdateCurrForm", 
		name = "������", 
		expressionArr = "UpdateCurrForm($currFormData())", 
		desc = "�����ݿ���µ�ǰ������", 
		dataType = "action"
)

public class UpdateCurrForm extends AbstractFunction{

	public String perform(Object obj){
		UsmProxy usm=(UsmProxy) obj;
		UsmService service=(UsmService) EsbUtil.parExpression("$"+usm.getServiceKey()+"UsmService");
		try {
			service.save(usm);
		} catch (USMException e) {
			e.printStackTrace();
		}
		return "����ɹ�";
	}
	
}
