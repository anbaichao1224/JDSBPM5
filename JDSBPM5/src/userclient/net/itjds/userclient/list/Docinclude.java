package net.itjds.userclient.list;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import com.opensymphony.xwork2.ActionContext;
import net.itjds.common.database.DBBeanBase;
import net.itjds.common.org.base.Person;
import net.itjds.fdt.define.designer.metadata.bean.DocBasicBean;
import net.itjds.fdt.define.designer.metadata.bean.DocStyleBean;
import net.itjds.fdt.define.designer.metadata.bean.DocumentBean;
import net.itjds.j2ee.dao.DAOFactory;
import net.itjds.userclient.common.BPMActionBase;
import net.itjds.userclient.document.BpmDocumentDAO;


public class Docinclude extends BPMActionBase{
	
	public int filenum;
	
	public String processInstId;
	
	private String cnname;
	
	private String docid;
	
	private String fileName;
	
	private String disabled;//禁用
	
	private String disname;//是否显示中文名称
	
	private String disicon;//是否显示图标
	
	private String iconD;//有正文时图标
	
	private String iconN;//没有正文时图标
	
	private String iconW;//图标宽
	
	private String iconH;//图标高
	
	private Person person;
	
	
	public String execute() throws Exception {
	
		this.person=(Person) ActionContext.getContext()
	    	.getValueStack().findValue("$currPerson");
		this.processInstId = this.getProcessInst().getProcessInstId();
		
		
		//获取后台定义属性
		DocumentBean docbean = (DocumentBean) ActionContext.getContext()
		.getValueStack().findValue("$docInject.getDocBean()");
	
		if(docbean != null)
		{
			DocBasicBean docbasic = docbean.getDocbasic();
			DocStyleBean style =docbean.getStyle();
			if(docbean.getCnname() != null && !"".equals(docbean.getCnname()))
			{
				this.cnname = docbean.getCnname();
			}
			if(style != null)
			{
				if(style.getDisname() != null && !"".equals(style.getDisname()))
				{
					this.disname = style.getDisname();
				}
				if(style.getDisicon() != null && !"".equals(style.getDisicon()))
				{
					this.disicon = style.getDisicon();
				}
				if(style.getIconD() != null && !"".equals(style.getIconD()))
				{
					this.iconD = style.getIconD();
				}
				if(style.getIconN() != null && !"".equals(style.getIconN()))
				{
					this.iconN = style.getIconN();
				}
				if(style.getIconH() != null && !"".equals(style.getIconH()))
				{
					this.iconH = style.getIconH();
				}
				if(style.getIconW() != null && !"".equals(style.getIconW()))
				{
					this.iconW = style.getIconW();
				}
			}
			if(docbasic != null)
			{
				if(docbasic.getDisabled() != null && !"".equals(docbasic.getDisabled()))
				{
					this.disabled = docbasic.getDisabled();
				}
			}
		}
		if (this.getActivityInst()==null){
			docbean.getDocbasic().setReadonly("true");
		}
		getActivityDoc();
	
		return SUCCESS;
	}

	//查看该活动有没有正文
	
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
	    		filenum = files.size(); 
	    		fileName = getUuidDoc();
	    	}else
	    	{
		    		factory = new DAOFactory(conn,docdao);
		    		
			    	docdao.setActivityinstid(this.getProcessInst().getProcessInstId());//如果该活动没有正文,说明是分散路由,从流程数据中取出一份
			    	files = factory.find();
			    	if(files != null && files.size()>0)
			    	{
			    		for(int i=0;i<files.size();i++)
			    		{
			    			filenum = files.size();
//			    			docid = ((BpmDocumentDAO)files.get(i)).getUuid();
			    			
			    		}
			    	}
		    	
	    	}
	    
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
	
	public String getUuidDoc()
	{
		BpmDocumentDAO docdao = new BpmDocumentDAO();
		DBBeanBase dbbase = new DBBeanBase("bpm", true); 
	    Connection conn = null;
	    DAOFactory factory = null;
	    List files ;
	    String docName="";
	    try{
	    	
	    	conn=dbbase.getConn();
	    	factory = new DAOFactory(conn,docdao);
	    	docdao.setUuid(docid); 	

	    	files = factory.find();//根据活动ID查看是否有文件
	    	if(files != null && files.size()>0)
	    	{
	    		docName = ((BpmDocumentDAO)files.get(0)).getFilename();
	    	}
	    
	    }catch(Exception e){
	    	e.printStackTrace();	    	
	    }finally{
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
	    
	    return docName;
	}
	
	
	/**
	 * @return the filenum
	 */
	public int getFilenum() {
		return filenum;
	}

	/**
	 * @return the processInstId
	 */
	public String getProcessInstId() {
		return processInstId;
	}

	/**
	 * @param filenum the filenum to set
	 */
	public void setFilenum(int filenum) {
		this.filenum = filenum;
	}

	/**
	 * @param processInstId the processInstId to set
	 */
	public void setProcessInstId(String processInstId) {
		this.processInstId = processInstId;
	}

	/**
	 * @return the cnname 
	 */
	public String getCnname() {
		return cnname;
	}

	/**
	 * @return the disicon 
	 */
	public String getDisicon() {
		return disicon;
	}

	/**
	 * @return the disname 
	 */
	public String getDisname() {
		return disname;
	}

	/**
	 * @return the iconD 
	 */
	public String getIconD() {
		return iconD;
	}

	/**
	 * @return the iconH 
	 */
	public String getIconH() {
		return iconH;
	}

	/**
	 * @return the iconN 
	 */
	public String getIconN() {
		return iconN;
	}

	/**
	 * @return the iconW 
	 */
	public String getIconW() {
		return iconW;
	}

	/**
	 * @param cnname the cnname to set
	 */
	public void setCnname(String cnname) {
		this.cnname = cnname;
	}

	/**
	 * @param disicon the disicon to set
	 */
	public void setDisicon(String disicon) {
		this.disicon = disicon;
	}

	/**
	 * @param disname the disname to set
	 */
	public void setDisname(String disname) {
		this.disname = disname;
	}

	/**
	 * @param iconD the iconD to set
	 */
	public void setIconD(String iconD) {
		this.iconD = iconD;
	}

	/**
	 * @param iconH the iconH to set
	 */
	public void setIconH(String iconH) {
		this.iconH = iconH;
	}

	/**
	 * @param iconN the iconN to set
	 */
	public void setIconN(String iconN) {
		this.iconN = iconN;
	}

	/**
	 * @param iconW the iconW to set
	 */
	public void setIconW(String iconW) {
		this.iconW = iconW;
	}

	/**
	 * @return the disabled 
	 */
	public String getDisabled() {
		return disabled;
	}

	/**
	 * @param disabled the disabled to set
	 */
	public void setDisabled(String disabled) {
		this.disabled = disabled;
	}

	/**
	 * @return the docid 
	 */
	public String getDocid() {
		return docid;
	}

	/**
	 * @param docid the docid to set
	 */
	public void setDocid(String docid) {
		this.docid = docid;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}


}
