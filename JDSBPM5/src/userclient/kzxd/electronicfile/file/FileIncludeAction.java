package kzxd.electronicfile.file;



import java.util.UUID;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;

import net.itjds.common.CommonConfig;
import net.itjds.common.org.base.Person;
import net.itjds.fdt.define.designer.metadata.bean.AttachBasicBean;
import net.itjds.fdt.define.designer.metadata.bean.AttachBean;
import net.itjds.fdt.define.designer.metadata.bean.AttachStyleBean;
import net.itjds.userclient.common.BPMActionBase;

public class FileIncludeAction implements Action {
	
	private int filenum;
	private String sxxxid;
	private String attid;
	private  Person person;
	private String filePath;
	private String attDivId;
	private String add;//是否允许新增
  
    private String del;//是否允许删除
    
    private String open;//是否允许打开表单时直接打开

	private String disabled;//是否禁用
    
	private String notable;//不显示列表
    
	private String onlyfname;//只显示文件名
    
	private String filecount; //每页显示多少条
	
	private String width;//单元格的宽度
	    
	private String height;//单元格的行高

	private String background_color;//背景颜色
	    
	private String text_align;//对齐方式
	    
	private String vertical_align;//上下对齐方式
	
   
	public String execute() throws Exception {
		// TODO Auto-generated method stub
//		获取后台定义属性
        this.filePath = CommonConfig.getValue("bpm.fileServer");
        
         person=(Person) ActionContext.getContext()
    	.getValueStack().findValue("$currPerson");
//        
		AttachBean attBean = (AttachBean) ActionContext.getContext()
		.getValueStack().findValue("$attInject.getAttBean()");
		if(attBean != null)
		{
			AttachBasicBean abb = attBean.getAttbasic();
			AttachStyleBean asb = attBean.getStyle();
			if(abb != null)
			{
				if(abb.getAttid() != null && !"".equals(abb.getAttid()))
				{
					this.attid = abb.getAttid();
				}
				if(abb.getAdd() != null && !"".equals(abb.getAdd()))
				{
					this.add = abb.getAdd();
				}
				if(abb.getDel() != null && !"".equals(abb.getDel()))
				{
					this.del = abb.getDel();
				}
				if(abb.getOpen() != null && !"".equals(abb.getOpen()))
				{
					this.open = abb.getOpen();
				}
				if(abb.getDisabled() != null && !"".equals(abb.getDisabled()))
				{
					this.disabled = abb.getDisabled();
				}
				if(abb.getNotable() != null && !"".equals(abb.getNotable()))
				{
					this.notable = abb.getNotable();
				}
				if(abb.getOnlyfname() != null && !"".equals(abb.getOnlyfname()))
				{
					this.onlyfname = abb.getOnlyfname();
				}
				if(abb.getFilecount() != null && !"".equals(abb.getFilecount()))
				{
					this.filecount = abb.getFilecount();
				}
			}
			if(asb != null)
			{
				if(attBean.getStyle().getWidth() != null && !"".equals(attBean.getStyle().getWidth()))
				{
					this.width = attBean.getStyle().getWidth();
				}
				if(attBean.getStyle().getHeight() != null && !"".equals(attBean.getStyle().getHeight()))
				{
					this.height = attBean.getStyle().getHeight();
				}
				if(attBean.getStyle().getBackground_color() != null && !"".equals(attBean.getStyle().getBackground_color()))
				{
					this.background_color = attBean.getStyle().getBackground_color();
				}
				if(attBean.getStyle().getText_align() != null && !"".equals(attBean.getStyle().getText_align()))
				{
					this.text_align = attBean.getStyle().getText_align();
				}
				if(attBean.getStyle().getVertical_align() != null && !"".equals(attBean.getStyle().getVertical_align()))
				{
					this.vertical_align = attBean.getStyle().getVertical_align();
				}
			}
		}
		return SUCCESS;
	}

	/**
	 * @return the attid 
	 */
	public String getAttid() {
		return attid;
	}

	/**
	 * @return the background_color 
	 */
	public String getBackground_color() {
		return background_color;
	}

	/**
	 * @return the del 
	 */
	public String getDel() {
		return del;
	}

	/**
	 * @return the disabled 
	 */
	public String getDisabled() {
		return disabled;
	}

	/**
	 * @return the filecount 
	 */
	public String getFilecount() {
		return filecount;
	}

	/**
	 * @return the filenum 
	 */
	public int getFilenum() {
		return filenum;
	}

	/**
	 * @return the height 
	 */
	public String getHeight() {
		return height;
	}

	/**
	 * @return the notable 
	 */
	public String getNotable() {
		return notable;
	}

	/**
	 * @return the onlyfname 
	 */
	public String getOnlyfname() {
		return onlyfname;
	}

	/**
	 * @return the open 
	 */
	public String getOpen() {
		return open;
	}

	/**
	 * @return the text_align 
	 */
	public String getText_align() {
		return text_align;
	}

	public String getSxxxid() {
		return sxxxid;
	}

	public void setSxxxid(String sxxxid) {
		this.sxxxid = sxxxid;
	}

	/**
	 * @return the vertical_align 
	 */
	public String getVertical_align() {
		return vertical_align;
	}

	/**
	 * @return the width 
	 */
	public String getWidth() {
		return width;
	}

	/**
	 * @param attid the attid to set
	 */
	public void setAttid(String attid) {
		this.attid = attid;
	}

	/**
	 * @param background_color the background_color to set
	 */
	public void setBackground_color(String background_color) {
		this.background_color = background_color;
	}

	/**
	 * @param del the del to set
	 */
	public void setDel(String del) {
		this.del = del;
	}

	/**
	 * @param disabled the disabled to set
	 */
	public void setDisabled(String disabled) {
		this.disabled = disabled;
	}

	/**
	 * @param filecount the filecount to set
	 */
	public void setFilecount(String filecount) {
		this.filecount = filecount;
	}

	/**
	 * @param filenum the filenum to set
	 */
	public void setFilenum(int filenum) {
		this.filenum = filenum;
	}

	/**
	 * @param height the height to set
	 */
	public void setHeight(String height) {
		this.height = height;
	}

	/**
	 * @param notable the notable to set
	 */
	public void setNotable(String notable) {
		this.notable = notable;
	}

	/**
	 * @param onlyfname the onlyfname to set
	 */
	public void setOnlyfname(String onlyfname) {
		this.onlyfname = onlyfname;
	}

	/**
	 * @param open the open to set
	 */
	public void setOpen(String open) {
		this.open = open;
	}

	/**
	 * @param text_align the text_align to set
	 */
	public void setText_align(String text_align) {
		this.text_align = text_align;
	}

	/**
	 * @param vertical_align the vertical_align to set
	 */
	public void setVertical_align(String vertical_align) {
		this.vertical_align = vertical_align;
	}

	/**
	 * @param width the width to set
	 */
	public void setWidth(String width) {
		this.width = width;
	}

	/**
	 * @return the add 
	 */
	public String getAdd() {
		return add;
	}

	/**
	 * @param add the add to set
	 */
	public void setAdd(String add) {
		this.add = add;
	}

	
	/**
	 * @return the person 
	 */
	public Person getPerson() {
		return person;
	}

	/**
	 * @param person the person to set
	 */
	public void setPerson(Person person) {
		this.person = person;
	}

	/**
	 * @return the filePath 
	 */
	public String getFilePath() {
		return filePath;
	}

	/**
	 * @param filePath the filePath to set
	 */
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	/**
	 * @return the attDivId 
	 */
	public String getAttDivId() {
		return UUID.randomUUID().toString();
	}

	/**
	 * @param attDivId the attDivId to set
	 */
	public void setAttDivId(String attDivId) {
		this.attDivId = attDivId;
	}

}