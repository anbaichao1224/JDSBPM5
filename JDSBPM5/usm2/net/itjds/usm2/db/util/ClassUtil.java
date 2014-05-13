package net.itjds.usm2.db.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import ognl.OgnlContext;
import ognl.OgnlException;
import ognl.OgnlRuntime;



import net.itjds.bpm.data.xmlproxy.manager.ClassMappingAnnotation;

import net.itjds.common.logging.Log;
import net.itjds.common.logging.LogFactory;

import net.itjds.usm2.define.annotation.ViewPortDefine;
import net.itjds.usm2.define.mapping.ExtBean;
import net.itjds.usm2.define.mapping.PanelBean;
import net.sf.cglib.beans.BeanMap;

public class ClassUtil {
	protected transient static final Log log = LogFactory.getLog(
			"ClassUtil", ClassUtil.class);
	
	/**
	 * 将eiObj转换为指定的UsmView类型
	 * @param clazz
	 * @param eiObj
	 * @return UsmView
	 */
	public  static Object getUsmViewInstance(Class clazz,Object eiObj){
		Object defaultview=null;
		try {
			defaultview=OgnlRuntime.callConstructor(new OgnlContext(), clazz.getName(), new Object[]{eiObj});
		} catch (OgnlException e1) {
			e1.printStackTrace();
		}
	
		return defaultview;
	}
	

	public  static Class findClassByKey(Class clazz, String methodName) {
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
	 public static PanelBean getViewTempBean(Class clazz) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException, InstantiationException, SecurityException, NoSuchMethodException{
		 Annotation annotation =clazz.getAnnotation(ViewPortDefine.class);
		 PanelBean panelBean=(PanelBean) getExtBean(annotation);
		 List childItems=getChildPanelItems(clazz,panelBean);
		 if (childItems.size()>0){
			 panelBean.setItems(childItems);
		 }
			return panelBean;
			
		}
	 
	
	 public static PanelBean getTempBean(Class clazz) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException, InstantiationException, SecurityException, NoSuchMethodException{
		 Annotation[] annotations =clazz.getAnnotations();
		 PanelBean panelBean=(PanelBean) getExtBean(annotations[0]);
		 panelBean.setClazz(clazz);
		 List childItems=getChildPanelItems(clazz,panelBean);
		 if (childItems.size()>0){
			 panelBean.setItems(childItems);
		 }
			return panelBean;
			
		}
	 
	
	 
	 
	 public static ExtBean getExtBean(Annotation annotation) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException, InstantiationException{
			Class enumType=annotation.annotationType();
			Map<String, Object> valueMap=new HashMap<String, Object>();
			ClassMappingAnnotation classMappingAnnotation=annotation.annotationType().getAnnotation(ClassMappingAnnotation.class);
			if (classMappingAnnotation!=null){
				for (int k=0;k<enumType.getDeclaredMethods().length;k++){
					Method method=enumType.getDeclaredMethods()[k];
					if (method.getReturnType().isAnnotation() && method.getReturnType().getAnnotation(ClassMappingAnnotation.class)!=null){
					 	Annotation childAnnotation=(Annotation) method.invoke(annotation, null);
						valueMap.put(method.getName(), getExtBean(childAnnotation));
					}else if (method.getReturnType().isArray()){	
						Object[] objs= (Object[]) method.invoke(annotation, null);
						Object[] beans=new Object[objs.length];
						if (objs.length>0 ){
							for(int f=0;f<objs.length;f++){
								if (objs[f] instanceof Annotation){
									Annotation obj=(Annotation) objs[f];
									if (obj.annotationType().getAnnotation(ClassMappingAnnotation.class)!=null){
										Object panel=getExtBean(obj);
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
			ExtBean panelBean= (ExtBean) classMappingAnnotation.clazz().newInstance();		
		
			BeanMap beanMap=BeanMap.create(panelBean);
			beanMap.putAll(valueMap);
			return panelBean;
	 }
	 

	 public static ExtBean getFieldBean(Field field) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException, InstantiationException{
		    Annotation[] annotations =field.getAnnotations();
		    ExtBean panelBean=getExtBean(annotations[0]);
			return panelBean;
		}
	 
	 public static ExtBean getMethodBean(Method method) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException, InstantiationException{
		    Annotation[] annotations =method.getAnnotations();
		    ExtBean panelBean=getExtBean(annotations[0]);
			return panelBean;
		}
	 

		
	 public static List<ExtBean> getChildPanelItems(Class clazz,PanelBean panelBean) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException, InstantiationException, SecurityException, NoSuchMethodException{
		Field[] fields = clazz.getDeclaredFields();
		List<ExtBean> panelList=new ArrayList<ExtBean>();

		Object[] fieldNames= panelBean.getFieldsIndex();
		if (fieldNames!=null && fieldNames.length>0){
			for(int k=0;k<fieldNames.length;k++){
				String fieldName=(String)fieldNames[k];
				String methodName = "get" + fieldName.substring(0, 1).toUpperCase()+ fieldName.substring(1);
				ExtBean panel=getChildBeanByMethodName(clazz, methodName);
				if (panel!=null){
					panelList.add(panel);
				}
			}
		}else{
			Method[] methods = clazz.getDeclaredMethods();
			for (Method method : methods) {
					String methodName = method.getName();
					if (methodName.startsWith("get")){
						ExtBean panel=getChildBeanByMethodName(clazz, methodName);
						if (panel!=null){
							panelList.add(panel);
						}
					}
					
				}
			
		}
		
		
		
		return panelList;
	}
	
	 public static ExtBean  getChildBeanByMethodName(Class clazz ,String methodName) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException, InstantiationException, SecurityException, NoSuchMethodException{
		ExtBean panel=null;
		
		Method method=clazz.getMethod(methodName, null);
		if (method.getAnnotations().length>0){
			Class innerClass = ClassUtil.findClassByKey(clazz,methodName);
			ExtBean fieldPanelBean 	=getMethodBean(method);
			Map childMap=new HashMap();
			if (innerClass.getAnnotations().length>0){
				childMap= BeanMap.create(fieldPanelBean);
				panel=getTempBean(innerClass);
			}else{
				panel=fieldPanelBean;
			}
			Map panelBeanMap= BeanMap.create(panel);
			childMap.put("name", method.getName().substring(3).substring(0, 1).toLowerCase()+method.getName().substring(3).substring(1));
			childMap.put("id", method.getName().substring(3).substring(0, 1).toLowerCase()+method.getName().substring(3).substring(1));
			panelBeanMap.put("clazz", innerClass.getName());
			panelBeanMap.put("innerClass", innerClass);
			panelBeanMap.putAll(childMap);
		}
		return panel;
	}
	

	
	
}
