package net.itjds.usm2.service.form;


import net.itjds.bpm.data.xmlproxy.EsbBeanAnnotation;
import net.itjds.bpm.data.xmlproxy.MapdaoBeanAnnotation;

import net.itjds.common.expression.function.AbstractFunction;

import net.itjds.usm2.db.UsmService;
import net.itjds.usm2.db.util.EsbUtil;


@EsbBeanAnnotation(
		id = "currFormUsmService", 
		name = "��ȡ��ǰUSMServiceKey", 
		expressionArr = "GetFormUsmService($currFormUsmServiceKey())", 
		desc = "��ȡ��ǰUSMServiceKey", 
		dataType = "action"
)

public class GetFormUsmService extends  AbstractFunction{
	
 public UsmService perform(String servicekey){
		UsmService service=(UsmService) EsbUtil.parExpression("$"+servicekey+"UsmService");
		return service;
	}

}
