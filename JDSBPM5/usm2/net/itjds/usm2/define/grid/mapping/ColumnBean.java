package net.itjds.usm2.define.grid.mapping;

import net.itjds.usm2.define.enums.ElementAlign;
import net.itjds.usm2.define.enums.ElementFieldType;
import net.itjds.usm2.define.mapping.FieldBean;

public class ColumnBean extends FieldBean {
	private String header;

	private String dataIndex;

	private String mapping;

	private ElementFieldType type;
	
	private Boolean searchField;

	private Boolean sortable;

	private Boolean hidden;
	
	SearchItemBean search;
	

	private String tooltip;

	private ElementAlign align;

	private String renderer;
	private String inputType;
	private String tableName;
	private String columnName;

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	public String getDataIndex() {
		return dataIndex;
	}

	public void setDataIndex(String dataIndex) {
		this.dataIndex = dataIndex;
	}


	public void setWidth(Integer width) {
		this.width = width;
	}

	public Boolean getSortable() {
		return sortable;
	}

	public void setSortable(Boolean sortable) {
		this.sortable = sortable;
	}

	public Boolean getHidden() {
		return hidden;
	}

	public void setHidden(Boolean hidden) {
		this.hidden = hidden;
	}

	public String getTooltip() {
		return tooltip;
	}

	public void setTooltip(String tooltip) {
		this.tooltip = tooltip;
	}

	public ElementAlign getAlign() {
		return align;
	}

	public void setAlign(ElementAlign align) {
		this.align = align;
	}

	public String getRenderer() {
		return renderer;
	}

	public void setRenderer(String renderer) {
		this.renderer = renderer;
	}

	public String getMapping() {
		return mapping;
	}

	public void setMapping(String mapping) {
		this.mapping = mapping;
	}

	public ElementFieldType getType() {
		return type;
	}

	public void setType(ElementFieldType type) {
		this.type = type;
	}

	public Boolean getSearchField() {
		return searchField;
	}

	public void setSearchField(Boolean searchField) {
		this.searchField = searchField;
	}

	public SearchItemBean getSearch() {
		return search;
	}

	public void setSearch(SearchItemBean search) {
		this.search = search;
	}

	public String getInputType() {
		return inputType;
	}

	public void setInputType(String inputType) {
		this.inputType = inputType;
	}
}
