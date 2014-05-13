package net.itjds.usm2.service.form;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
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
import net.itjds.usm2.define.mapping.ExtBean;
import net.itjds.usm2.define.mapping.FieldBean;
import net.itjds.usm2.define.mapping.PanelBean;
import net.sf.cglib.beans.BeanMap;
@EsbBeanAnnotation(id="currFormPanelBean",
		name="取得当前的FORM数据",
		expressionArr="GetCurrFormPanelBean(R(\"viewId\"),R(\"path\"))",
		desc="部门数据 by DAOTools ",
		dataType="action"
		)
	
public class GetCurrFormPanelBean extends AbstractFunction{
	protected transient static final Log log = LogFactory.getLog(
			"currFormPanelBean", GetCurrFormPanelBean.class);

	public PanelBean  perform(String viewId,String path) {
		PanelBean panelBean=null;
		Object panel=EsbUtil.parExpression(path);
		Class clazz=null;
		if (viewId!=null&&!viewId.equals("")){
			EsbBeanFactory factory = EsbBeanFactory.newInstance();
		   	Map esbBeanMap=factory.getIdMap();
		   	ExpressionTempBean bean= (ExpressionTempBean) esbBeanMap.get(viewId);
		   	if (bean!=null){
		   		try {
					clazz=ClassUtility.loadClass(bean.getMainClass());
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
		   	}
		}else{
			
				clazz=panel.getClass();

		}
		
		try {
			panelBean=getTempBean(clazz,path);
		} catch (Exception e) {
			e.printStackTrace();
		}
			panelBean.setPath(path);
			if (viewId!=null&&!viewId.equals("")){
			panelBean.setViewPath("$"+viewId);
			}else{
				panelBean.setViewPath(path);
			}
			
		
		return panelBean;
	}
	
	
	
	 public PanelBean getTempBean(Class clazz,String path) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException, InstantiationException, SecurityException, NoSuchMethodException{
		 Annotation[] annotations =clazz.getAnnotations();
		 PanelBean panelBean=null;
		 for(int k=0;annotations.length>k;k++){
			 Annotation annotation =annotations[k];
			 if (annotation.annotationType().getAnnotation(ClassMappingAnnotation.class)!=null &&
					 ExtBean.class.isAssignableFrom(annotation.annotationType().getAnnotation(ClassMappingAnnotation.class).clazz())){
				 panelBean=(PanelBean) getExtBean(annotations[k],path);
			 } 
			
		 }
		 
		 
		
		 panelBean.setClazz(clazz);
		 
		 List childItems=getChildPanelItems(clazz,panelBean,path);
		 if (childItems.size()>0){
			 panelBean.setItems(childItems);
		 }
			return panelBean;
			
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
					String filedName=getFieldNameByMethodName(method.getName());
				}
//				for (int k=0;k<enumType.getDeclaredFields().length;k++){
//					Field field=enumType.getDeclaredFields()[k];
//					if (field.getType().isAnnotation() && field.getType().getAnnotation(ClassMappingAnnotation.class)!=null){
//					 	Annotation childAnnotation=(Annotation) method.invoke(annotation, null);
//						valueMap.put(method.getName(), getExtBean(childAnnotation,path));
//					}else if (method.getReturnType().isArray()){	
//						Object[] objs= (Object[]) method.invoke(annotation, null);
//						Object[] beans=new Object[objs.length];
//						if (objs.length>0 ){
//							for(int f=0;f<objs.length;f++){
//								if (objs[f] instanceof Annotation){
//									Annotation obj=(Annotation) objs[f];
//									if (obj.annotationType().getAnnotation(ClassMappingAnnotation.class)!=null){
//										Object panel=getExtBean(obj,path);
//										beans[f]=panel;
//									}else{
//										beans[f]=obj;
//									}									
//								}else{
//									beans[f]=objs[f];
//								}
//							}
//							valueMap.put(method.getName(), beans);
//						}
//						
//					}else{
//						valueMap.put(method.getName(), method.invoke(annotation, null));
//					}
//					String filedName=getFieldNameByMethodName(method.getName());
//				}
//				
		   }
			
			
			ExtBean panelBean= (ExtBean) classMappingAnnotation.clazz().newInstance();		
			
			BeanMap beanMap=BeanMap.create(panelBean);
			beanMap.putAll(valueMap);
			return panelBean;
	 }
	 

	 public ExtBean getFieldBean(Field field,String path) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException, InstantiationException{
		    Annotation[] annotations =field.getAnnotations();
		 
		   
		    ExtBean panelBean=getExtBean(annotations[0],path);
		
		    
			return panelBean;
		}
	 
	 public ExtBean getMethodBean(Method method,String path) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException, InstantiationException{
		    Annotation[] annotations =method.getAnnotations();
		   // path=path+"."+this.getFieldNameByMethodName(method.getName());
		    ExtBean panelBean=getExtBean(annotations[0],path);
		    if(panelBean instanceof FieldBean){
		    	((FieldBean)panelBean).setPath(path);
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
			
			panelBeanMap.putAll(childMap);
		}
		return panel;
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
