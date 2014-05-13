package net.itjds.usm2.define.grid.mapping;

import java.util.ArrayList;
import java.util.List;

import net.itjds.usm2.define.data.mapping.AjaxDataBean;
import net.itjds.usm2.define.grid.annotation.GridRowDefine;
import net.itjds.usm2.define.mapping.PanelBean;

public class GridPanelBean extends PanelBean{
	private String namespace;
	private String title;
	
	private String listServiceKey;
	

	private String autoExpandColumn;

	private int autoExpandMax;

	private int autoExpandMin;
	
	private int pageSize;
	
	private Object[] searchBeans;
	
	private AjaxDataBean saveSort;
	
	private AjaxDataBean rebuild;

	private String cm;
	
	private Object[] buttons;

	private String colModel;
	


	private String columns;

	private boolean disableSelection;

	private boolean enableColumnHide;

	private boolean enableColumnMove;

	private boolean enableColumnResize;

	private boolean enalbeDragDrop;

	private boolean enableHdMenu;

	private boolean enableRowHeightSync;

	private int maxHeight;

	private int minColumnWidth;

	private String sm;

	private String selModel;

	private String store;

	private boolean stripeRows;

	private boolean trackMouseOver;
	
	private AjaxDataBean deleteData;
	
	private String groupField;
	
	private AjaxDataBean restartTelephone;
	
	private AjaxDataBean importPerson;
	
	private AjaxDataBean exportPerson;
	
	private AjaxDataBean  loadData;
	
	private AjaxDataBean  editRowData;
	private AjaxDataBean  addRowData;
	private AjaxDataBean  deleteRowData;
	
	Object[] multiHeader;
	
	Object[] multiFootHeader;
	
	
	private List<ColumnBean> columnBeans=new ArrayList();
	



	public List<ColumnBean> getColumnBeans() {
		return columnBeans;
	}

	public void setColumnBeans(List<ColumnBean> columnBeans) {
		this.columnBeans = columnBeans;
	}

	public String getAutoExpandColumn() {
		return autoExpandColumn;
	}

	public void setAutoExpandColumn(String autoExpandColumn) {
		this.autoExpandColumn = autoExpandColumn;
	}

	public int getAutoExpandMax() {
		return autoExpandMax;
	}

	public void setAutoExpandMax(int autoExpandMax) {
		this.autoExpandMax = autoExpandMax;
	}

	public int getAutoExpandMin() {
		return autoExpandMin;
	}

	public void setAutoExpandMin(int autoExpandMin) {
		this.autoExpandMin = autoExpandMin;
	}

	public String getCm() {
		return cm;
	}

	public void setCm(String cm) {
		this.cm = cm;
	}

	public String getColModel() {
		return colModel;
	}

	public void setColModel(String colModel) {
		this.colModel = colModel;
	}

	public String getColumns() {
		return columns;
	}

	public void setColumns(String columns) {
		this.columns = columns;
	}

	public boolean isDisableSelection() {
		return disableSelection;
	}

	public void setDisableSelection(boolean disableSelection) {
		this.disableSelection = disableSelection;
	}

	public boolean isEnableColumnHide() {
		return enableColumnHide;
	}

	public void setEnableColumnHide(boolean enableColumnHide) {
		this.enableColumnHide = enableColumnHide;
	}

	public boolean isEnableColumnMove() {
		return enableColumnMove;
	}

	public void setEnableColumnMove(boolean enableColumnMove) {
		this.enableColumnMove = enableColumnMove;
	}

	public boolean isEnableColumnResize() {
		return enableColumnResize;
	}

	public void setEnableColumnResize(boolean enableColumnResize) {
		this.enableColumnResize = enableColumnResize;
	}

	public boolean isEnableHdMenu() {
		return enableHdMenu;
	}

	public void setEnableHdMenu(boolean enableHdMenu) {
		this.enableHdMenu = enableHdMenu;
	}

	public boolean isEnableRowHeightSync() {
		return enableRowHeightSync;
	}

	public void setEnableRowHeightSync(boolean enableRowHeightSync) {
		this.enableRowHeightSync = enableRowHeightSync;
	}

	public boolean isEnalbeDragDrop() {
		return enalbeDragDrop;
	}

	public void setEnalbeDragDrop(boolean enalbeDragDrop) {
		this.enalbeDragDrop = enalbeDragDrop;
	}

	public int getMaxHeight() {
		return maxHeight;
	}

	public void setMaxHeight(int maxHeight) {
		this.maxHeight = maxHeight;
	}

	public int getMinColumnWidth() {
		return minColumnWidth;
	}

	public void setMinColumnWidth(int minColumnWidth) {
		this.minColumnWidth = minColumnWidth;
	}

	public String getSelModel() {
		return selModel;
	}

	public void setSelModel(String selModel) {
		this.selModel = selModel;
	}

	public String getSm() {
		return sm;
	}

	public void setSm(String sm) {
		this.sm = sm;
	}

	public String getStore() {
		return store;
	}

	public void setStore(String store) {
		this.store = store;
	}

	public boolean isStripeRows() {
		return stripeRows;
	}

	public void setStripeRows(boolean stripeRows) {
		this.stripeRows = stripeRows;
	}

	public boolean isTrackMouseOver() {
		return trackMouseOver;
	}

	public void setTrackMouseOver(boolean trackMouseOver) {
		this.trackMouseOver = trackMouseOver;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	
	public AjaxDataBean getLoadData() {
		return loadData;
	}

	public void setLoadData(AjaxDataBean loadData) {
		this.loadData = loadData;
	}

	public String getNamespace() {
		return namespace;
	}

	public void setNamespace(String namespace) {
		this.namespace = namespace;
	}

	public Object[] getSearchBeans() {
		return searchBeans;
	}

	public void setSearchBeans(Object[] searchBeans) {
		this.searchBeans = searchBeans;
	}

	public Object[] getButtons() {
		return buttons;
	}

	public void setButtons(Object[] buttons) {
		this.buttons = buttons;
	}

	public AjaxDataBean getDeleteData() {
		return deleteData;
	}

	public void setDeleteData(AjaxDataBean deleteData) {
		this.deleteData = deleteData;
	}



	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public AjaxDataBean getRestartTelephone() {
		return restartTelephone;
	}

	public void setRestartTelephone(AjaxDataBean restartTelephone) {
		this.restartTelephone = restartTelephone;
	}
	

	public AjaxDataBean getImportPerson() {
		return importPerson;
	}

	public void setImportPerson(AjaxDataBean importPerson) {
		this.importPerson = importPerson;
	}

	public AjaxDataBean getExportPerson() {
		return exportPerson;
	}

	public void setExportPerson(AjaxDataBean exportPerson) {
		this.exportPerson = exportPerson;
	}

	public AjaxDataBean getAddRowData() {
		return addRowData;
	}

	public void setAddRowData(AjaxDataBean addRowData) {
		this.addRowData = addRowData;
	}

	public AjaxDataBean getDeleteRowData() {
		return deleteRowData;
	}

	public void setDeleteRowData(AjaxDataBean deleteRowData) {
		this.deleteRowData = deleteRowData;
	}

	public AjaxDataBean getEditRowData() {
		return editRowData;
	}

	public void setEditRowData(AjaxDataBean editRowData) {
		this.editRowData = editRowData;
	}

	public AjaxDataBean getSaveSort() {
		return saveSort;
	}

	public void setSaveSort(AjaxDataBean saveSort) {
		this.saveSort = saveSort;
	}

	public AjaxDataBean getRebuild() {
		return rebuild;
	}

	public void setRebuild(AjaxDataBean rebuild) {
		this.rebuild = rebuild;
	}

	public String getListServiceKey() {
		return listServiceKey;
	}

	public Object[] getMultiFootHeader() {
		return multiFootHeader;
	}

	public void setMultiFootHeader(Object[] multiFootHeader) {
		this.multiFootHeader = multiFootHeader;
	}

	public Object[] getMultiHeader() {
		return multiHeader;
	}

	public void setMultiHeader(Object[] multiHeader) {
		this.multiHeader = multiHeader;
	}

	public void setListServiceKey(String listServiceKey) {
		this.listServiceKey = listServiceKey;
	}

	public String getGroupField() {
		return groupField;
	}

	public void setGroupField(String groupField) {
		this.groupField = groupField;
	}

	

}
