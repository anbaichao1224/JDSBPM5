package net.itjds.usm2.service;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import ognl.OgnlContext;

import net.itjds.bpm.data.xmlproxy.EsbBeanAnnotation;
import net.itjds.bpm.data.xmlproxy.MapdaoBeanAnnotation;
import net.itjds.common.expression.function.AbstractFunction;

import net.itjds.usm2.db.USMListProxy;
import net.itjds.usm2.db.util.ClassUtil;
import net.itjds.usm2.db.util.EsbUtil;
import net.itjds.usm2.define.tree.TreeNode;
import net.itjds.usm2.define.tree.annotation.TreeNodeDefine;



@EsbBeanAnnotation(
		id = "currViewData", 
		name = "获取活动视图对象", 
		expressionArr = "GetActivityViewData(R(\"path\"))", 
	
		desc = "获取当前活动视图数据对象", 
		dataType = "action"
)

public class GetActivityViewData extends  AbstractFunction{
	
	public Object perform(String path){
		Object obj=null;
		
		Map esbContextMap = EsbUtil.getContextMap();
		if (esbContextMap.containsKey(path)){;
		  obj= esbContextMap.get(path);
		}else{
			obj=EsbUtil.parExpression(path);
			if (path.lastIndexOf(".")>-1 && List.class.isAssignableFrom(obj.getClass())){
				Object parentObj=EsbUtil.parExpression(path.substring(0,path.lastIndexOf(".")));
				String fieldName=path.substring(path.lastIndexOf(".")+1,path.length());
				String methodName = "get" + fieldName.substring(0, 1).toUpperCase()+ fieldName.substring(1);
				obj=EsbUtil.getProxyList(parentObj,methodName);
				
			}
		}
			esbContextMap.put(path, obj);

		return obj;
	}

}
