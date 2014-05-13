package net.itjds.usm2.service.json;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.itjds.bpm.data.xmlproxy.EsbBeanAnnotation;
import net.itjds.bpm.data.xmlproxy.MapdaoBeanAnnotation;
import net.itjds.common.expression.function.AbstractFunction;
import net.itjds.usm2.db.util.EsbUtil;
import net.itjds.usm2.define.tree.StaticTreeNode;
import net.itjds.usm2.define.tree.TreeNode;
import net.itjds.usm2.define.tree.annotation.TreeNodeDefine;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


@EsbBeanAnnotation(
		id = "JSONMap", 
		name = "MAP(字典表)转换为JSON", 
		expressionArr = "GetJSONMap(R(\"key\"),R(\"value\"))", 
		desc = "MAP(字典表)转换为JSO", 
		dataType = "action"
)

public class GetJSONMap extends  AbstractFunction{
	
	
	public String perform(String key,String value){
	String json="";
	if (key!=null &&!key.equals("")){
		Map map=new HashMap();
    	map.put("key", key);
    	map.put("value", value);
    	json=JSONObject.fromObject(map).toString();
	}
    	
    	return json;
	}
	
}
