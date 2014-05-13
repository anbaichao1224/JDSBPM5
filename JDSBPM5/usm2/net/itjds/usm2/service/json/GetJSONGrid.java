package net.itjds.usm2.service.json;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.itjds.bpm.data.xmlproxy.EsbBeanAnnotation;
import net.itjds.bpm.data.xmlproxy.MapdaoBeanAnnotation;
import net.itjds.common.expression.function.AbstractFunction;
import net.itjds.usm2.db.util.EsbUtil;

import net.sf.cglib.beans.BeanMap;
import net.sf.json.JSONObject;


@EsbBeanAnnotation(
		id = "JSONGrid", 
		name = "Grid格式化为JSON", 
		expressionArr = "GetJSONGrid($currViewData())", 
		desc = "将当前传入的GRID列表数据格式化为JSON输出", 
		dataType = "action"
)

public class GetJSONGrid extends  AbstractFunction{
		public String perform(List  childnodes){
			int start=Integer.parseInt(EsbUtil.getHttpParamsByName("start"));
			int limit = Integer.parseInt(EsbUtil.getHttpParamsByName("limit"));
			int end = start + limit;
			List grids=childnodes;
			int totalCount = grids.size();
			if (totalCount>limit){
				if(totalCount < end){
					end = totalCount;
				}
				grids=childnodes.subList(start,end);
			}
			List usms=new ArrayList();
			for(int k=0;k<grids.size();k++){
				Object obj=grids.get(k);
				Map objMap=new HashMap();
				objMap.putAll(BeanMap.create(obj));
				objMap.put("path", EsbUtil.getHttpParamsByName("path")+"["+childnodes.indexOf(obj)+"]");
				usms.add(objMap);
			}
			
		
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("results", totalCount);
			map.put("data",usms);
			return JSONObject.fromObject(map).toString();
		}
}
