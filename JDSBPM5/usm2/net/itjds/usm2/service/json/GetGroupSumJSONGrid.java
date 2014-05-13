package net.itjds.usm2.service.json;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;

import net.itjds.bpm.data.xmlproxy.EsbBeanAnnotation;
import net.itjds.bpm.data.xmlproxy.MapdaoBeanAnnotation;
import net.itjds.common.expression.function.AbstractFunction;
import net.itjds.usm2.db.util.EsbUtil;
import net.itjds.usm2.define.grid.mapping.ColumnBean;
import net.itjds.usm2.define.grid.mapping.GridPanelBean;

import net.sf.cglib.beans.BeanMap;
import net.sf.json.JSONObject;


@EsbBeanAnnotation(
		id = "JSONSumGroupGrid", 
		name = "分组输出列表数据（汇总行）", 
		expressionArr = "GetGroupSumJSONGrid($currViewData())", 
		desc = "分组输出列表数据（汇总行）", 
		dataType = "action"
)

public class GetGroupSumJSONGrid extends  AbstractFunction{
	
	
	
	
	/**
	 * 主方法，直接调用
	 * @param childnodes
	 * @return
	 */
	
		public String perform(List  childnodes){
			int start=Integer.parseInt(EsbUtil.getHttpParamsByName("start"));
			String groupId=EsbUtil.getHttpParamsByName("groupField");
			
			//int limit =120;// Integer.parseInt(EsbUtil.getHttpParamsByName("limit"));
			int limit =childnodes.size();
			int end = start + limit;
			List grids=childnodes;
//		
			int totalCount = grids.size();
			if (totalCount>limit){
				if(totalCount < end){
					end = totalCount;
				}
				grids=childnodes.subList(start,end);
			}
			
			if (groupId==null ||groupId.equals("")){
				groupId=this.getCurrPanelBean().getGroupField();
			}
			List usms=new ArrayList();
			for(int k=0;k<grids.size();k++){
				Object obj=grids.get(k);
				Map objMap=new HashMap();
				objMap.putAll(BeanMap.create(obj));
				objMap.put("path", EsbUtil.getHttpParamsByName("path")+"["+childnodes.indexOf(obj)+"]");
				usms.add(objMap);
				
			}
			if (usms.size()>0){
				usms=groupList(usms,groupId);
			}
			
			
			
			 totalCount = usms.size();
		
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("results", totalCount);
			map.put("data",usms);
			map.put("groupField",groupId);
			return JSONObject.fromObject(map).toString();
		}
	
	
	/***
	 * 添加小计列
	 * @param list  已经分组后的List
	 * @param key 分组列名称
	 * @param fristCol 第一列
	 * @return
	 */
	public List getSumList(List list,String key,String fristCol){
		List newList=new ArrayList();
		Map sumMap=new HashMap();
		Map beanMap=(Map) list.get(0);
		
		Iterator<String> it=beanMap.keySet().iterator();
		for(;it.hasNext();){
			String fieldName=it.next();
			if( beanMap.get(fieldName) instanceof Number) {
				ActionContext.getContext().getValueStack().setValue("list", list);
				String expression="$Sum(list.{"+fieldName+"})";
				sumMap.put(fieldName, EsbUtil.parExpression(expression));
			}else{
				Object o = beanMap.get(fieldName);
				if(o != null)
				sumMap.put(fieldName, o.toString());
			}
			
		}
		newList.add(sumMap);
		return newList;
		
	} 

	/***
	 * 添加小计列
	 * @param list  已经分组后的List
	 * @param key 分组列名称
	 * @param fristCol 第一列
	 * @return
	 */
	public List getAllSumList(List list,String key,String fristCol){
		List newList=new ArrayList();
		Map sumMap=new HashMap();
		Map beanMap=(Map) list.get(0);
		
		Iterator<String> it=beanMap.keySet().iterator();
		for(;it.hasNext();){
			String fieldName=it.next();
			if (fieldName.equals(key)){
				sumMap.put(fieldName, beanMap.get(key).toString());
			
			}else if( beanMap.get(fieldName) instanceof Number) {
					ActionContext.getContext().getValueStack().setValue("list", list);
					String expression="$Sum(list.{"+fieldName+"})";
					sumMap.put(fieldName, EsbUtil.parExpression(expression));
				
			
			}
		
				sumMap.put(key, "总计");
		
		}
		
		newList.add(sumMap);
		newList.addAll(list);
		return newList;
		
	}   
	
	public GridPanelBean getCurrPanelBean(){
		GridPanelBean gpanel=(GridPanelBean)  EsbUtil.parExpression("$currViewBean(R(\"esbkey\"),'"+EsbUtil.getHttpParamsByName("path")+"')");
		return gpanel;
	}
	
	/**
	 * 取表格第一列名称
	 * @return
	 */
	public String getFristCol(){
		GridPanelBean gpanel=getCurrPanelBean();
		String fristCol="";
	    for(int k=gpanel.getItems().size()-1;k>0;k--){
	    	ColumnBean fieldbean=(ColumnBean) gpanel.getItems().get(k);
	    	if (!fieldbean.getHidden()){
	    		fristCol=fieldbean.getName();
	    		continue;
	    	}
	    }
	    return fristCol;
	}
	
	/***
	 * 分组方法
	 * @param grids  全部的数据集
	 * @param key 分组列名称
	 * @return 返回分组后的List
	 */
	
	
	public List groupList(List<Map> grids ,String key){
	    //	List groupList=new ArrayList();
	    	List sumGroupList=new ArrayList();
	    	Map<Object,List> keyMap=new HashMap<Object,List>();
	    	for(int k=0;k<grids.size();k++){
	    		Map beanMap=grids.get(k);
	    		if (beanMap.containsKey(key)){
	    			Object value =beanMap.get(key);
	    			if (keyMap.containsKey(value)){
	    				List subList=keyMap.get(value);
	    				subList.add(beanMap);
	    			}else{
	    				List subList=new ArrayList();
	    				subList.add(beanMap);
	    				keyMap.put(value, subList);
	    				//groupList.add(subList);
	    			}
	    		 }
	    		
	    	}
	       String fristCol=getFristCol();
	   	Iterator it=keyMap.keySet().iterator();
	    	for(;it.hasNext();){
	    		Object value=it.next();
	    		List subList=(List) keyMap.get(value);
	    		subList=getSumList(subList,key, fristCol);
	    	
	    		sumGroupList.addAll(subList);
	    	}
	    	
	    	sumGroupList=getAllSumList(sumGroupList,key, fristCol);
			return sumGroupList;
	    	
	    }

}
