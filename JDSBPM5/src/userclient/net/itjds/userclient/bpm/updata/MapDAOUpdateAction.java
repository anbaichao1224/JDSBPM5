
package net.itjds.userclient.bpm.updata;
import java.util.ArrayList;
import java.util.List;
import net.itjds.bpm.engine.BPMException;
import net.itjds.common.mapdao.AutoFillList;
import net.itjds.userclient.bpm.updata.Cell;
import net.itjds.userclient.common.BPMActionBase;
import net.itjds.userclient.common.UserClientException;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;

/**
 * <p>
 * Title: BPM工作流管理系统
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * </p>
 * <p>
 * Copyright: Copyright (c) 2008
 * </p>
 * <p>
 * Company: www.justdos.net
 * </p>
 * 
 * @author wenzhangli
 * @version 2.0
 */
public class MapDAOUpdateAction  extends BPMActionBase {

	private List<Cell>  updataList;
	private List<String>  rawUpdataList;
		//是否在列表中显示
	private List<String>  rawUpdataProcessList;
	private List<Cell> cellList;
	public MapDAOUpdateAction()  {
		super();	
	}
		public String execute() throws Exception {
		try {
			return Action.NONE;
		} catch (Exception e) {
			e.printStackTrace();			
		//	this.getClient().rollbackTransaction();
			return this.ERROR;
		}
	}
	
		
	public String updataCell() throws BPMException, UserClientException{
		rawUpdataProcessList=this.getRawUpdataProcessList();
		for(int k=0;k<rawUpdataProcessList.size();k++){
			String cellName=rawUpdataProcessList.get(k);
			if (cellName != null  &&!cellName.equals("")){
			Object obj=	ActionContext.getContext().getValueStack().findValue(rawUpdataProcessList.get(k));
			String cellValue=null;
			if (obj instanceof String[]){
				cellValue=((String[])obj)[0];
			}else{
				cellValue=ActionContext.getContext().getValueStack().findString(rawUpdataProcessList.get(k));	
			}
		
			 if( getActivityInst()!=null){
					getActivityInst().getProcessInst().setAttribute("fileTitle", cellValue);

					getActivityInst().getProcessInst().setAttribute(cellName.substring(cellName.lastIndexOf(".")+1, cellName.length()), cellValue);
			 }

			}
		}
		
		List valueList = this.getCellList();
		List updataList = new ArrayList();
		List udvalueList = this.getRawUpdataList();
			
		for (int j = 0; j < udvalueList.size(); j++) {
			String key = (String) udvalueList.get(j);
				for (int i = 0; i < valueList.size(); i++) {
					Cell cell = (Cell) valueList
							.get(i);
					String str = cell.getExpress();
					if (str != null && str.indexOf(key) > -1 && !str.equals(key)) {
						if (!udvalueList.contains(cell.getKey())) {
							udvalueList.add(cell.getKey());
						}
						if (!updataList.contains(cell)) {
							int index = this.getIndex(updataList,
									cell);
							if (index == 0) {
								updataList.add(cell);
							} else {
								updataList.set(index, cell);
							}
						}
					}
				}
		this.setUpdataList(updataList);
		udvalueList.clear();	
		}
		return "updata"; 
	}
	
	public String putExpress(){
		return Action.NONE;
	}
	
	public List<Cell> getUpdataList() {
		if (this.updataList==null){
			updataList=new AutoFillList<Cell>(Cell.class);
		}
		return updataList;
	}
	
	
	public int getIndex(List updateList, Cell updateCell) {
		for (int k = 0; k < updateList.size(); k++) {
			Cell cell = (Cell) updateList
					.get(k);
			if (cell.getKey().equals(updateCell.getKey())) {
				return k;
			}
		}
		return 0;
	}

	public List<Cell> getCellList() {
		cellList=(List<Cell>) ActionContext.getContext().get("cellList");
		if (cellList==null){
			cellList=new AutoFillList<Cell>(Cell.class);
			ActionContext.getContext().put("cellList", cellList);
		}
		return cellList;
	}
	public void setCellList(List<Cell> cellList) {
		this.cellList = cellList;
	}
	public List<String> getRawUpdataList() {
		if (this.rawUpdataList==null){
			rawUpdataList=new ArrayList<String>();
			rawUpdataList.add("");
		}
		return rawUpdataList;
	}
	
	public List<String> getRawUpdataProcessList() {
		if (this.rawUpdataProcessList==null){
			rawUpdataProcessList=new ArrayList<String>();
			rawUpdataProcessList.add("");
		}
		return rawUpdataList;
	}
	
	public void setRawUpdataList(List<String> rawUpdataList) {
		this.rawUpdataList = rawUpdataList;
	}
	public void setUpdataList(List<Cell> updataList) {
		this.updataList = updataList;
	}
	public void setRawUpdataProcessList(List<String> rawUpdataProcessList) {
		this.rawUpdataProcessList = rawUpdataProcessList;
	}
	
}	
	


