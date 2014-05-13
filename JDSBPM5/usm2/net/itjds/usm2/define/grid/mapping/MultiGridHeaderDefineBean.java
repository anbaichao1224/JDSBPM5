package net.itjds.usm2.define.grid.mapping;

import net.itjds.usm2.define.enums.ElementAlign;
import net.itjds.usm2.define.grid.annotation.MultiGridHeaderDefine;
import net.itjds.usm2.define.mapping.ExtBean;

public class MultiGridHeaderDefineBean  implements ExtBean{
	


	
	
	
	String header;
	
	String mapping;
	
	String expression;

	
	String dataIndex;

	
	ElementAlign align;
	
	
	int colspan;
	
	
	int rowspan;


	public ElementAlign getAlign() {
		return align;
	}


	public void setAlign(ElementAlign align) {
		this.align = align;
	}


	public int getColspan() {
		return colspan;
	}


	public void setColspan(int colspan) {
		this.colspan = colspan;
	}


	public String getDataIndex() {
		return dataIndex;
	}


	public void setDataIndex(String dataIndex) {
		this.dataIndex = dataIndex;
	}


	public String getHeader() {
		return header;
	}


	public void setHeader(String header) {
		this.header = header;
	}


	public int getRowspan() {
		return rowspan;
	}


	public void setRowspan(int rowspan) {
		this.rowspan = rowspan;
	}


	public String getId() {
		// TODO Auto-generated method stub
		return null;
	}


	public void setId(String id) {
		// TODO Auto-generated method stub
		
	}


	public String getMapping() {
		return mapping;
	}


	public void setMapping(String mapping) {
		this.mapping = mapping;
	}


	public String getExpression() {
		return expression;
	}


	public void setExpression(String expression) {
		this.expression = expression;
	}


	public Class getClazz() {
		// TODO Auto-generated method stub
		return null;
	}


	public void setClazz(Class clazz) {
		// TODO Auto-generated method stub
		
	}


}
