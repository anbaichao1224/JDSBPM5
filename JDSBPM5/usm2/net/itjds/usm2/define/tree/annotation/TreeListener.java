package net.itjds.usm2.define.tree.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import net.itjds.j2ee.dao.MethodChinaName;
import net.itjds.usm2.define.enums.ElementEvent;
import net.itjds.usm2.define.tree.mapping.TreeListenerBean;

import net.itjds.bpm.data.xmlproxy.manager.ClassMappingAnnotation;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@ClassMappingAnnotation(clazz=TreeListenerBean.class)
public @interface TreeListener {
	@MethodChinaName(cname = "�¼�����")
	ElementEvent eventname() ;
	@MethodChinaName(cname = "ִ�з���")
	String function() default "funciton(){}";

}
