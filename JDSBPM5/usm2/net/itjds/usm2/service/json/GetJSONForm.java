package net.itjds.usm2.service.json;



import net.itjds.bpm.data.xmlproxy.EsbBeanAnnotation;
import net.itjds.bpm.data.xmlproxy.MapdaoBeanAnnotation;
import net.itjds.common.expression.function.AbstractFunction;

import net.sf.json.JSONArray;


@EsbBeanAnnotation(
		id = "JSONForm", 
		name = "FORM格式化为JSON", 
		expressionArr = "GetJSONForm($currViewData())", 
		desc = "将当前传入的FORM对象格式化为JSON输出", 
		dataType = "action"
)

public class GetJSONForm extends  AbstractFunction{
	public String perform(Object obj){
		return JSONArray.fromObject(obj).toString();
			}
	
}
