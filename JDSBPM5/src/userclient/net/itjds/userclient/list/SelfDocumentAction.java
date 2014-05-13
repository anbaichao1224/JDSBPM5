package net.itjds.userclient.list;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import net.itjds.common.database.DBBeanBase;
import net.itjds.fdt.define.designer.metadata.bean.DocBasicBean;
import net.itjds.fdt.define.designer.metadata.bean.DocumentBean;
import net.itjds.j2ee.dao.DAOFactory;
import net.itjds.userclient.common.BPMActionBase;
import net.itjds.userclient.document.BpmDocumentDAO;

import com.opensymphony.xwork2.ActionContext;

public class SelfDocumentAction extends BPMActionBase {
	private String processInstId;
	private String docid;
	private String printdoc;
	private String savedoc;
	private String showRevisions;
	private String hidRevisions;
	private String markModify;
	private String acceptAllRevisions;
	private String handDraw;
	private String handSign;
	private String addSign;
	private String menubar;
	private String titlebar;
	private String toolbars;
	private String statusbar;
	private String taoHong;
	private String open;
	private String docuuid;
	private String filenum;
	private int fn;
	private String yiban;
	
	private String displayName;
    //套红模版
	private String taoHongId;
	
	public String execute() throws Exception {
		DocumentBean docbean = (DocumentBean) ActionContext.getContext()
				.getValueStack().findValue("$docInject.getDocBean()");
		if (docbean != null) {

			DocBasicBean docbasic = docbean.getDocbasic();
			if (docbasic != null) {
				if ((docbasic.getPrintdoc() != null)
						&& (!("".equals(docbasic.getPrintdoc())))) {
					this.printdoc = docbasic.getPrintdoc();
				}
				if ((docbasic.getSavedoc() != null)
						&& (!("".equals(docbasic.getSavedoc())))) {
					this.savedoc = docbasic.getSavedoc();
				}
				if ((docbasic.getShowRevisions() != null)
						&& (!("".equals(docbasic.getShowRevisions())))) {
					this.showRevisions = docbasic.getShowRevisions();
				}
				if ((docbasic.getHidRevisions() != null)
						&& (!("".equals(docbasic.getHidRevisions())))) {
					this.hidRevisions = docbasic.getHidRevisions();
				}
				if ((docbasic.getMarkModify() != null)
						&& (!("".equals(docbasic.getMarkModify())))) {
					this.markModify = docbasic.getMarkModify();
				}
				if ((docbasic.getAcceptAllRevisions() != null)
						&& (!("".equals(docbasic.getAcceptAllRevisions())))) {
					this.acceptAllRevisions = docbasic.getAcceptAllRevisions();
				}
				if ((docbasic.getHandDraw() != null)
						&& (!("".equals(docbasic.getHandDraw())))) {
					this.handDraw = docbasic.getHandDraw();
				}
				if ((docbasic.getToolbars() != null)
						&& (!("".equals(docbasic.getToolbars())))) {
					this.toolbars = docbasic.getToolbars();
				}
				if ((docbasic.getOpen() != null)
						&& (!("".equals(docbasic.getOpen())))) {
					this.open = docbasic.getOpen();
				}
				if ((docbasic.getTaoHong() != null)
						&& (!("".equals(docbasic.getTaoHong())))) {
					this.taoHong = docbasic.getTaoHong();
				}
			}
		}
		getActivityDoc();
		return "success";
	}
	
	public void getActivityDoc()
	{
		BpmDocumentDAO docdao = new BpmDocumentDAO();
		DBBeanBase dbbase = new DBBeanBase("bpm", true); 
	    Connection conn = null;
	    DAOFactory factory = null;
	    List files ;
	    try{
	    	
	    	conn=dbbase.getConn();
	    	factory = new DAOFactory(conn,docdao);
	    	if (this.getActivityInst()!=null){
	    		docdao.setActivityinstid(this.getActivityInst().getActivityInstId());
			}else if(this.getActivityInstHistory()!=null){
				docdao.setActivityinstid(this.getActivityInstHistory().getActivityInstId());
			}    	

	    	files = factory.find();//根据活动ID查看是否有文件
	    	if(files != null && files.size()>0)
	    	{
	    		fn = files.size(); 
//	    		docid = ((BpmDocumentDAO)files.get(0)).getUuid();
//	    		displayName = ((BpmDocumentDAO)files.get(0)).getDisplayname();
	    	}else
	    	{
		    		factory = new DAOFactory(conn,docdao);
		    		
			    	docdao.setActivityinstid(this.getProcessInst().getProcessInstId());//如果该活动没有正文,说明是分散路由,从流程数据中取出一份
			    	files = factory.find();
			    	if(files != null && files.size()>0)
			    	{
			    		for(int i=0;i<files.size();i++)
			    		{
			    			fn = files.size();
//			    			docid = ((BpmDocumentDAO)files.get(i)).getUuid();
//			    			displayName = ((BpmDocumentDAO)files.get(i)).getDisplayname();
			    		}
			    	}
		    	
	    	}
	    	filenum = String.valueOf(fn);
	    
	    }catch(Exception e)
	    {
	    	e.printStackTrace();
	    	
	    }finally
	    {
	    	try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			try {
				dbbase.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
	    }
	}

	/*
	 * 2011-11-7 zhongqun update 
	 * 根据活动实例id查询正文
	 */
	public List getProcessInstIdDoc(String processInstId,String activityInstId)
	{
		BpmDocumentDAO docdao = new BpmDocumentDAO();
		DBBeanBase dbbase = new DBBeanBase("bpm", true); 
	    Connection conn = null;
	    DAOFactory factory = null;
	    List files = null;
	    try{
	    	
	    	conn=dbbase.getConn();
	    	factory = new DAOFactory(conn,docdao);
	    	if (this.getActivityInst()!=null){
	    		docdao.setActivityinstid(this.getActivityInst().getActivityInstId());
			}else if(this.getActivityInstHistory()!=null){
				docdao.setActivityinstid(this.getActivityInstHistory().getActivityInstId());
			}    	
	    	//(this.getProcessInst().getCopyNumber()>1)
	    	files = factory.find();//根据活动ID查看是否有文件
	    	if(files != null && files.size()>0)
	    	{
	    		fn = files.size(); 
	    		docid = ((BpmDocumentDAO)files.get(0)).getUuid();
	    	}else
	    	{
		    		factory = new DAOFactory(conn,docdao);
		    		
			    	docdao.setActivityinstid(this.getProcessInst().getProcessInstId());//如果该活动没有正文,说明是分散路由,从流程数据中取出一份
			    	files = factory.find();
	    	}
	    
	    }catch(Exception e)
	    {
	    	e.printStackTrace();
	    	
	    }finally
	    {
	    	try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	    return files;
	}
	/*
	 * 2011-11-7 zhongqun update 
	 * 根据流程实例id查询正文
	 */
	public List getProcessInstIdDoc1(String processInstId)
	{
		BpmDocumentDAO docdao = new BpmDocumentDAO();
		DBBeanBase dbbase = new DBBeanBase("bpm", true); 
	    Connection conn = null;
	    DAOFactory factory = null;
	    List files = null;
	    try{
	    	
	    	conn=dbbase.getConn();
	    	factory = new DAOFactory(conn,docdao);
	        docdao.setActivityinstid(processInstId);
	    	files = factory.find();//根据活动ID查看是否有文件
		    factory = new DAOFactory(conn,docdao);
		    docdao.setActivityinstid(this.getProcessInst().getProcessInstId());//如果该活动没有正文,说明是分散路由,从流程数据中取出一份
		    files = factory.find();
	    	
	    }catch(Exception e)
	    {
	    	e.printStackTrace();
	    	
	    }finally
	    {
	    	try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	    return files;
	}
	public String getHidRevisions() {
		return this.hidRevisions;
	}

	public void setHidRevisions(String hidRevisions) {
		this.hidRevisions = hidRevisions;
	}

	public String getAcceptAllRevisions() {
		return this.acceptAllRevisions;
	}

	public String getAddSign() {
		return this.addSign;
	}

	public String getDocid() {
		return this.docid;
	}

	public String getHandDraw() {
		return this.handDraw;
	}

	public String getHandSign() {
		return this.handSign;
	}

	public String getMarkModify() {
		return this.markModify;
	}

	public String getMenubar() {
		return this.menubar;
	}

	public String getOpen() {
		return this.open;
	}

	public String getPrintdoc() {
		return this.printdoc;
	}

	public String getSavedoc() {
		return this.savedoc;
	}

	public String getShowRevisions() {
		return this.showRevisions;
	}

	public String getStatusbar() {
		return this.statusbar;
	}

	public String getTaoHong() {
		return this.taoHong;
	}

	public String getTitlebar() {
		return this.titlebar;
	}

	public String getToolbars() {
		return this.toolbars;
	}

	public void setAcceptAllRevisions(String acceptAllRevisions) {
		this.acceptAllRevisions = acceptAllRevisions;
	}

	public void setAddSign(String addSign) {
		this.addSign = addSign;
	}

	public void setDocid(String docid) {
		this.docid = docid;
	}

	public void setHandDraw(String handDraw) {
		this.handDraw = handDraw;
	}

	public void setHandSign(String handSign) {
		this.handSign = handSign;
	}

	public void setMarkModify(String markModify) {
		this.markModify = markModify;
	}

	public void setMenubar(String menubar) {
		this.menubar = menubar;
	}

	public void setOpen(String open) {
		this.open = open;
	}

	public void setPrintdoc(String printdoc) {
		this.printdoc = printdoc;
	}

	public void setSavedoc(String savedoc) {
		this.savedoc = savedoc;
	}

	public void setShowRevisions(String showRevisions) {
		this.showRevisions = showRevisions;
	}

	public void setStatusbar(String statusbar) {
		this.statusbar = statusbar;
	}

	public void setTaoHong(String taoHong) {
		this.taoHong = taoHong;
	}

	public void setTitlebar(String titlebar) {
		this.titlebar = titlebar;
	}

	public void setToolbars(String toolbars) {
		this.toolbars = toolbars;
	}

	public String getProcessInstId() {
		return this.processInstId;
	}

	public void setProcessInstId(String processInstId) {
		this.processInstId = processInstId;
	}

	public String getDocuuid() {
		return docuuid;
	}

	public void setDocuuid(String docuuid) {
		this.docuuid = docuuid;
	}

	public String getFilenum() {
		return filenum;
	}

	public void setFilenum(String filenum) {
		this.filenum = filenum;
	}

	public String getYiban() {
		return yiban;
	}

	public void setYiban(String yiban) {
		this.yiban = yiban;
	}

	public String getTaoHongId() {
		return taoHongId;
	}

	public void setTaoHongId(String taoHongId) {
		this.taoHongId = taoHongId;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	
	
}