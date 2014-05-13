package net.itjds.usm2.define.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import net.itjds.j2ee.dao.MethodChinaName;
import net.itjds.usm2.define.mapping.ToolbarBean;

import net.itjds.bpm.data.xmlproxy.manager.ClassMappingAnnotation;

@Retention(RetentionPolicy.RUNTIME)
@ClassMappingAnnotation(clazz=ToolbarBean.class)
public @interface ToolbarDefine {
	@MethodChinaName(cname = "ÿҳ��ʾ��¼��")
	int  pageSize() default 19;
	
	
}
