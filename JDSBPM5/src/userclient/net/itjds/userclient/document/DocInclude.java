package net.itjds.userclient.document;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import kzxd.zwxg.attachment.FileListAction;

import com.opensymphony.xwork2.ActionContext;

import net.itjds.common.CommonConfig;
import net.itjds.common.database.DBBeanBase;
import net.itjds.common.org.base.Person;
import net.itjds.fdt.define.designer.metadata.bean.DocBasicBean;
import net.itjds.fdt.define.designer.metadata.bean.DocStyleBean;
import net.itjds.fdt.define.designer.metadata.bean.DocumentBean;
import net.itjds.j2ee.dao.DAOFactory;
import net.itjds.j2ee.util.UUID;
import net.itjds.userclient.common.BPMActionBase;
import net.itjds.userclient.common.util.CatalogUtil;
import net.itjds.userclient.document.BpmDocumentDAO;


public class DocInclude extends BPMActionBase{
	
	public int filenum;
	
	public String processInstId;
	
	private String cnname;
	
	private String docid;
	
	private String disabled;//禁用
	
	private String disname;//是否显示中文名称
	
	private String disicon;//是否显示图标
	
	private String iconD;//有正文时图标
	
	private String iconN;//没有正文时图标
	
	private String iconW;//图标宽
	
	private String iconH;//图标高
	
	private Person person;
	
	private String taoHong;
	
	private String displayName;
	
	

	private String filefileName;
	private File[] files;

	private List<DisplayNameBean> dpnList;
	
	//附件数
	private int fjnum;
	
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
				
				if ((docbasic.getTaoHong() != null)
						&& (!("".equals(docbasic.getTaoHong())))) {
					this.taoHong = docbasic.getTaoHong();
				}
				
			}
		}
		if (this.getActivityInst()==null){
			docbean.getDocbasic().setReadonly("true");
		}
		getActivityDoc();
		
		FileListAction flAction = new FileListAction();
		flAction.execute();
		fjnum = flAction.getTotalCount();
	
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
	    		System.out.println(this.getActivityInst().getActivityInstId());
	    		docdao.setActivityinstid(this.getActivityInst().getActivityInstId());
			}else if(this.getActivityInstHistory()!=null){
				docdao.setActivityinstid(this.getActivityInstHistory().getActivityInstId());
			}    	

	    	files = factory.find();//根据活动ID查看是否有文件
	    	dpnList = new ArrayList();	    	
	    	if(files != null && files.size()>0)
	    	{
	    		for(int i=0;i<files.size();i++){
	    			DisplayNameBean dpnBean = new DisplayNameBean();
		    		filenum = files.size(); 
		    		docid = ((BpmDocumentDAO)files.get(i)).getUuid();
		    		displayName = ((BpmDocumentDAO)files.get(i)).getDisplayname();
		    		dpnBean.setDocuuid(docid);
		    		dpnBean.setDisplayName(displayName);
		    		dpnList.add(dpnBean);
	    		}
	    	}else
	    	{
		    		factory = new DAOFactory(conn,docdao);
		    		
			    	docdao.setActivityinstid(this.getProcessInst().getProcessInstId());//如果该活动没有正文,说明是分散路由,从流程数据中取出一份
			    	files = factory.find();
			    	if(files != null && files.size()>0)
			    	{   
						
						Calendar c = Calendar.getInstance();
						int year = c.get(Calendar.YEAR);
						int month = c.get(Calendar.MONTH) + 1;
			    		String activeInstPath = year + File.separator + month + File.separator
						+ processInstId + File.separator
						+ this.getActivityInst().getActivityInstId();
			    		String uuid  = new UUID().toString();
				
					    String rootPath = File.separator + "file" + File.separator
						+ "document" + File.separator;
				         String realPath = CommonConfig.getValue("bpm.filePath") + rootPath;
				     	CatalogUtil.createFolder(realPath); 
				     	String realPath1 = CommonConfig.getValue("bpm.filePath");
			
				         
						String uploadActiveInstFolder = CatalogUtil.createFolders(realPath,
								activeInstPath);
						
						
						
			    		for(int i=0;i<files.size();i++)
			    		{
			    			BpmDocumentDAO doc = (BpmDocumentDAO) files.get(i);
			    			String file = realPath1 + doc.getFilepath()+doc.getFilename();
			    			System.out.println(file);
							this.writeFile(uploadActiveInstFolder+ File.separator + doc.getFilename(),file);
						
							factory = new DAOFactory(conn, docdao);
							factory.setDAO(docdao);
							docdao.setConnection(conn);
							docdao.setUuid(new UUID().toString());
							docdao.setProcessinstid(processInstId);
							docdao.setActivityinstid(this.getActivityInst().getActivityInstId());
							docdao.setFilepath(rootPath+activeInstPath+File.separator);
							docdao.setFilename(doc.getFilename());
							docdao.setFiletype(doc.getFiletype());
							docdao.setCreateuser(person.getID());
							docdao.setCreatedate(formatDate());
							docdao.setFileindex(1);
							docdao.setRemark("");
							docdao.setDisplayname(doc.getDisplayname());
							docdao.create();
							conn.commit();
							DisplayNameBean dpnBean = new DisplayNameBean();
			    			filenum = files.size();
			    			docid = docdao.getUuid();
			    			displayName = docdao.getDisplayname();
			    			dpnBean.setDocuuid(docid);
				    		dpnBean.setDisplayName(displayName);
				    		dpnList.add(dpnBean);
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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	}
	
	private static void writeFile(String src, String file) {
		try {
			InputStream in = null;
			OutputStream out = null;
			try {

				in = new BufferedInputStream(new FileInputStream(file));
				out = new BufferedOutputStream(new FileOutputStream(src));
				byte[] buffer = new byte[1024];
				int len = 0;
				while ((len = in.read(buffer)) > 0) {
					out.write(buffer, 0, len);
				}

			} finally {
				if (null != in) {
					in.close();
				}
				if (null != out) {
					out.close();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 格式化日期
	 * */
	public String formatDate() {
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ");
		return sd.format(new Date(System.currentTimeMillis()));

	}
	/**
	 * 获取文件后缀名
	 * */
	public String getFileExt(String filename) {

		return filename.substring(filename.lastIndexOf(".") + 1);
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

	public String getTaoHong() {
		return taoHong;
	}

	public void setTaoHong(String taoHong) {
		this.taoHong = taoHong;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public List<DisplayNameBean> getDpnList() {
		return dpnList;
	}

	public void setDpnList(List<DisplayNameBean> dpnList) {
		this.dpnList = dpnList;
	}

	public int getFjnum() {
		return fjnum;
	}

	public void setFjnum(int fjnum) {
		this.fjnum = fjnum;
	}

	public String getFilefileName() {
		return filefileName;
	}

	public void setFilefileName(String filefileName) {
		this.filefileName = filefileName;
	}

	public File[] getFiles() {
		return files;
	}

	public void setFiles(File[] files) {
		this.files = files;
	}
	

}
