package net.itjds.usm2.define.annotation;

public @interface MethodChinaName {
	public String cname() default "��";

	public String returnStr() default "";

	public boolean display() default true;
}
