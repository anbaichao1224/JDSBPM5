package net.itjds.usm2.service.json;



import net.itjds.bpm.data.xmlproxy.EsbBeanAnnotation;
import net.itjds.bpm.data.xmlproxy.MapdaoBeanAnnotation;
import net.itjds.common.expression.function.AbstractFunction;

import net.sf.json.JSONArray;


@EsbBeanAnnotation(
		id = "JSONForm", 
		name = "FORM��ʽ��ΪJSON", 
		expressionArr = "GetJSONForm($currViewData())", 
		desc = "����ǰ�����FORM�����ʽ��ΪJSON���", 
		dataType = "action"
)

public class GetJSONForm extends  AbstractFunction{
	public String perform(Object obj){
		return JSONArray.fromObject(obj).toString();
			}
	
}
