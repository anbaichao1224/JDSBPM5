package net.itjds.usm2.service;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


import net.itjds.bpm.data.xmlproxy.EsbBeanAnnotation;
import net.itjds.bpm.data.xmlproxy.MapdaoBeanAnnotation;
import net.itjds.bpm.data.xmlproxy.manager.ClassMappingAnnotation;
import net.itjds.bpm.data.xmlproxy.manager.EsbBeanFactory;
import net.itjds.bpm.data.xmlproxy.manager.ExpressionTempBean;
import net.itjds.common.expression.function.AbstractFunction;
import net.itjds.common.logging.Log;
import net.itjds.common.logging.LogFactory;
import net.itjds.common.util.ClassUtility;
import net.itjds.usm2.db.util.EsbUtil;
import net.itjds.usm2.define.annotation.ViewPortDefine;
import net.itjds.usm2.define.form.annotation.FormPanelDefine;
import net.itjds.usm2.define.mapping.ExtBean;
import net.itjds.usm2.define.mapping.FieldBean;
import net.itjds.usm2.define.mapping.PanelBean;
import net.sf.cglib.beans.BeanMap;
@EsbBeanAnnotation(id="currViewBean",
		name="获取当前PANEL",
		expressionArr="GetCurrPanelBean(R(\"esbkey\"),R(\"path\"))",
		desc="获取指定PATH的视图定义对象",
		dataType="action"
		)
	
public class GetCurrPanelBean extends AbstractFunction{
	protected transient static final Log log = LogFactory.getLog(
			"GetCurrPanelBean", GetCurrPanelBean.class);
	private String esbkey;
	
	private static Map<String ,ExtBean > runtimeTypeMap=new HashMap();
	public ExtBean  perform(String key,String path) {
		PanelBean panelBean=null;

		EsbBeanFactory factory = EsbBeanFactory.newInstance();
	   	Map esbBeanMap=factory.getIdMap();
	
		if (runtimeTypeMap.containsKey(path)){
			return runtimeTypeMap.get(path);
		}
		
		this.esbkey=key;
		try {
			if (path==null||path.equals("")){
				if (key!=null&&!key.equals("")){
				
				   	ExpressionTempBean bean= (ExpressionTempBean) esbBeanMap.get(key);
						if (bean!=null){
							panelBean = getViewTempBean(ClassUtility.loadClass(bean.getMainClass()),"$"+esbkey );
							runtimeTypeMap.put(key, panelBean);
						}
				}		
			}else{
				if (!path.startsWith("$")){
					path="$"+path;
				}
				String fieldName=path.substring(path.lastIndexOf(".")+1);
				String methodName = "get" + fieldName.substring(0, 1).toUpperCase()+ fieldName.substring(1);
				if (path.indexOf(".")>-1){
					String parentClassName=path.substring(0, path.lastIndexOf("."));
					panelBean=getTempBean(findClassByKey(EsbUtil.parExpression(parentClassName).getClass(),methodName),path);
					panelBean.setPath(path);
				}else{

					return panelBean;
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (panelBean!=null){
			panelBean.setEsbkey(this.esbkey);
		}
		
		return panelBean;
	
		
	}
	
	
	 private PanelBean getViewTempBean(Class clazz,String path) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException, InstantiationException, SecurityException, NoSuchMethodException{
		 Annotation annotation =clazz.getAnnotation(ViewPortDefine.class);
		if (annotation==null){
			annotation=clazz.getAnnotation(FormPanelDefine.class);
		}
		 PanelBean panelBean=(PanelBean) getExtBean(annotation, path);
		 runtimeTypeMap.put(path, panelBean);
		 List childItems=getChildPanelItems(clazz,panelBean, path);
		 if (childItems.size()>0){
			 panelBean.setItems(childItems);
		 }
			return panelBean;
			
		}
	 
	
	 public PanelBean getTempBean(Class clazz,String path) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException, InstantiationException, SecurityException, NoSuchMethodException{
		 Annotation[] annotations =clazz.getAnnotations();
	
		 for(int k=0;k<annotations.length;k++){
        	 Annotation annotation =clazz.getAnnotations()[k];
        	
 			ClassMappingAnnotation classMappingAnnotation=annotation.annotationType().getAnnotation(ClassMappingAnnotation.class);
 			if( ExtBean.class.isAssignableFrom(classMappingAnnotation.clazz())){
 				PanelBean panelBean=(PanelBean) getExtBean(annotations[k],path);
 				
 				 panelBean.setClazz(clazz);
 				 runtimeTypeMap.put(path, panelBean);
 				 List childItems=getChildPanelItems(clazz,panelBean,path);
 				 if (childItems.size()>0){
 					 panelBean.setItems(childItems);
 				 }
 				 return panelBean;
 			}
		 }
		
			return null;
			
		}
	 
	 
	 private String getFieldNameByMethodName(String methodName){
		 
		 if (methodName.startsWith("get")){
			 methodName=methodName.substring(3).substring(0, 1).toLowerCase()+methodName.substring(3).substring(1);
		 }
		 return methodName;
	 }
	 
	
	 
	 
	 private ExtBean getExtBean(Annotation annotation,String path) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException, InstantiationException{
			Class enumType=annotation.annotationType();
			Map<String, Object> valueMap=new HashMap<String, Object>();
			ClassMappingAnnotation classMappingAnnotation=annotation.annotationType().getAnnotation(ClassMappingAnnotation.class);
			if (classMappingAnnotation!=null){
				for (int k=0;k<enumType.getDeclaredMethods().length;k++){
					Method method=enumType.getDeclaredMethods()[k];
					if (method.getReturnType().isAnnotation() && method.getReturnType().getAnnotation(ClassMappingAnnotation.class)!=null){
					 	Annotation childAnnotation=(Annotation) method.invoke(annotation, null);
						valueMap.put(method.getName(), getExtBean(childAnnotation,path));
					}else if (method.getReturnType().isArray()){	
						Object[] objs= (Object[]) method.invoke(annotation, null);
						Object[] beans=new Object[objs.length];
						if (objs.length>0 ){
							for(int f=0;f<objs.length;f++){
								if (objs[f] instanceof Annotation){
									Annotation obj=(Annotation) objs[f];
									if (obj.annotationType().getAnnotation(ClassMappingAnnotation.class)!=null){
										Object panel=getExtBean(obj,path);
										beans[f]=panel;
									}else{
										beans[f]=obj;
									}									
								}else{
									beans[f]=objs[f];
								}
							}
							valueMap.put(method.getName(), beans);
						}
						
					}else{
						valueMap.put(method.getName(), method.invoke(annotation, null));
					}
					
				
					
				}
		   }
			
			ExtBean panelBean= null;
			if( ExtBean.class.isAssignableFrom(classMappingAnnotation.clazz())){
				 panelBean= (ExtBean) classMappingAnnotation.clazz().newInstance();		
				
				BeanMap beanMap=BeanMap.create(panelBean);
				
				try{
					for(Iterator it=valueMap.keySet().iterator();it.hasNext();){
						String key=(String) it.next();
						beanMap.put(key,valueMap.get(key));
					}
				
					
				}catch(Exception e){
					
				}
				 runtimeTypeMap.put(path, panelBean);
			}
			
			
			return panelBean;
	 }
	 

	
	public List<ExtBean> getChildPanelItems(Class clazz,PanelBean panelBean,String path) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException, InstantiationException, SecurityException, NoSuchMethodException{
		Field[] fields = clazz.getDeclaredFields();
		
		List<ExtBean> panelList=new ArrayList<ExtBean>();
		
		Object[] fieldNames= panelBean.getFieldsIndex();
		if (fieldNames!=null && fieldNames.length>0){
			for(int k=0;k<fieldNames.length;k++){
				String fieldName=(String)fieldNames[k];
				String methodName = "get" + fieldName.substring(0, 1).toUpperCase()+ fieldName.substring(1);
				ExtBean panel=this.getChildBeanByMethodName(clazz, methodName,path);
				if (panel!=null){
					panelList.add(panel);
				}
			}
		}else{
			Method[] methods = clazz.getDeclaredMethods();
			for (Method method : methods) {
					String methodName = method.getName();
					if (methodName.startsWith("get")){
						ExtBean panel=this.getChildBeanByMethodName(clazz, methodName,path);
						if (panel!=null){
							panelList.add(panel);
						}
					}
					
				}
			
		}
		// runtimeTypeMap.put(path, panelList);
		return panelList;
	}
	
	private ExtBean  getChildBeanByMethodName(Class clazz ,String methodName,String path) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException, InstantiationException, SecurityException, NoSuchMethodException{
		ExtBean panel=null;
		
		Method method=clazz.getMethod(methodName, null);
		if (method.getAnnotations().length>0){
			Class innerClass = findClassByKey(clazz,methodName);
			path=path+"."+this.getFieldNameByMethodName(method.getName());
			ExtBean fieldPanelBean 	=getMethodBean(method,path);
			
			Map childMap=new HashMap();
			if (innerClass.getAnnotations().length>0){
				childMap= BeanMap.create(fieldPanelBean);
				panel=getTempBean(innerClass,path);
			}else{
				panel=fieldPanelBean;
			}
			Map panelBeanMap= BeanMap.create(panel);
			childMap.put("name", method.getName().substring(3).substring(0, 1).toLowerCase()+method.getName().substring(3).substring(1));
			childMap.put("id", method.getName().substring(3).substring(0, 1).toLowerCase()+method.getName().substring(3).substring(1));
			panelBeanMap.put("clazz", innerClass);
			panelBeanMap.put("esbkey", this.esbkey);
			panelBeanMap.putAll(childMap);
		}
		 runtimeTypeMap.put(path, panel);
		
		return panel;
	}
	
	
//	 public ExtBean getFieldBean(Field field,String path) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException, InstantiationException{
//		    Annotation[] annotations =field.getAnnotations();
//		    ExtBean panelBean=getExtBean(annotations[0],path);
//		    runtimeTypeMap.put(path, panelBean);
//		    
//			return panelBean;
//		}
//	 
	 public ExtBean getMethodBean(Method method,String path) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException, InstantiationException{
		    Annotation[] annotations =method.getAnnotations();
		    ExtBean panelBean=getExtBean(annotations[0],path);
		    if(panelBean instanceof FieldBean){
		    	((FieldBean)panelBean).setPath(path);
		    }
			return panelBean;
		}
	 

		
		
	
	

	private  Class findClassByKey(Class clazz, String methodName) {
			Class classzz = null;
			Method[] methods =clazz.getDeclaredMethods();
			for (int i = 0; i < methods.length; i++) {
				Method method = methods[i];
				if (method.getName().equals(methodName)) {
					if (!List.class.isAssignableFrom(method.getReturnType())){
						classzz=method.getReturnType();
					}else{
					try {
						Type type = method.getGenericReturnType();
						classzz = (Class) ((ParameterizedType) type)
								.getActualTypeArguments()[0];
					} catch (SecurityException e) {					
						log.error("SecurityException fieldName=" + methodName + "  in class"
								+ clazz.getName());
						
					} 
					}
				}
			}
			return classzz;
		}
	
	
	

}
