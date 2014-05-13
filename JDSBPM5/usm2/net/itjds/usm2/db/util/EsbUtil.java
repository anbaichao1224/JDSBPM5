package net.itjds.usm2.db.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.itjds.common.cache.Cache;
import net.itjds.common.cache.CacheManager;
import net.itjds.common.cache.CacheManagerFactory;
import net.itjds.usm2.constants.USMConstants;
import net.itjds.usm2.db.USMListProxy;
import net.itjds.usm2.define.mapping.PanelBean;

import com.opensymphony.xwork2.ActionContext;

public class EsbUtil {
	
	/**
	 * 获取指定名称的HTTP参数
	 * @param name
	 * @return
	 */
	public static String getHttpParamsByName(String name){
		String expression="$R('"+name+"')";
		return (String) parExpression(expression);
	}
	/**
	 * 执行表达式
	 * @param expression
	 * @return
	 */
	public static Object parExpression(String expression){
		return ActionContext.getContext().getValueStack().findValue(expression);
	}
	/**
	 * 执行表达式
	 * @param expression
	 * @return
	 */
	public static Object getParentObj(String expression){
		Object parentObj=null;
		if (expression.indexOf(".")>0){
			 parentObj=EsbUtil.parExpression(expression.substring(0,expression.lastIndexOf(".")));
		}
		return parentObj;
	}
	
	public synchronized  static  List getProxyList(Object parentObj ,String methodName){
		
		List obj=null;
		 Method[] methods= parentObj.getClass().getMethods();
		   for(int k=0;k<methods.length;k++){
			   Method method=methods[k];
				  if ( method.getName().equals(methodName)){
					
						try {
							obj=(List)  method.invoke(parentObj, null);
						} catch (Exception e) {
							e.printStackTrace();
						}
					   List newList=new ArrayList();
					   List objList=(List)obj;
						Class innerClass=ClassUtil.findClassByKey(parentObj.getClass(), method.getName());
						if (objList!=null && objList.size()>0){
							 for(int j=0;j<objList.size();j++){
							   Object childObj=null;
								   if (obj instanceof USMListProxy){
										USMListProxy proxyList=(USMListProxy) obj;
										childObj=proxyList.get(j,innerClass);
										newList.add(childObj);
									}else{
										childObj=objList.get(j);	
										if (childObj!=null && !childObj.getClass().equals(innerClass)){
											USMListProxy list=new USMListProxy(obj,innerClass);
											newList.add(list.get(j));
										}else{
											newList.add(childObj);
										}
								   }										
										
						}
															  
				      }
					obj=newList;
			     } 
		   }
		   
	  return obj;
	}
	
	
	public static Class getCurrClass(){
		return (Class) EsbUtil.parExpression("$newUsmProxy");
	}
	public static PanelBean getCurrPanelBean(String esbkey){
		return (PanelBean) EsbUtil.parExpression("$currViewBean(\""+esbkey+"\",null)");
	}
	

	public static  Map getContextMap(){
		Map  contextMap=ActionContext.getContext().getContextMap();
		Map esbContextMap =null;
		if (contextMap.containsKey(USMConstants.CURRUSM_ESB_CONTEXT_KEY)){
			esbContextMap=(Map) contextMap.get(USMConstants.CURRUSM_ESB_CONTEXT_KEY);
		}else{
			esbContextMap=new HashMap();
			contextMap.put(USMConstants.CURRUSM_ESB_CONTEXT_KEY, esbContextMap);
		}
		return esbContextMap;
	}
	
	
	public static boolean clearCache(String uuid){
		 Map<String,CacheManager> cacheManagerMap=CacheManagerFactory.getInstance().getCacheManagerMap();
	     Iterator<String> it=cacheManagerMap.keySet().iterator();
	     Map cacheMap = null;
	     for(;it.hasNext();){
	      String key=it.next();
	       CacheManager subCacheManager=cacheManagerMap.get(key);
	        cacheMap = subCacheManager.getAllCache();
				if (key.equals("org")){
					Iterator<String> cit=cacheMap.keySet().iterator();
					for(;cit.hasNext();){
						String ckey=cit.next();
						Cache cache=(Cache) cacheMap.get(ckey);
							if (cache.containsKey(uuid)){
								cache.remove(uuid);
							}						
					}
				}
			
	     }
	     return true;
	}
	

}
