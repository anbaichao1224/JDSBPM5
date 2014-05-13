package kzxd.electronicfile.file;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.opensymphony.xwork2.Action;

import net.itjds.common.database.DBBeanBase;
import net.itjds.j2ee.dao.DAOFactory;
import net.itjds.userclient.common.ActionBase;

public class FileDisplayAction implements Action {

	String filename;
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		
		return SUCCESS;
	}
	
	//zhongqun 添加查找处理笺的方法
	public BpmMAttachmentDAO findClj(String rid){
	Connection conn = null;
    DAOFactory factory = null;
    int size = 0; 
    try{
	  
	    DBBeanBase dbbase = new DBBeanBase("bpm",true);
    	conn = dbbase.getConn();
    	BpmMAttachmentDAO  attdao = 	new BpmMAttachmentDAO();
	    	factory = new DAOFactory(conn,attdao);
	    	attdao.setRecordid(rid);   //取得当前数据下的处理笺
	    	attdao.setCategorytype("clj");
	    	List list = factory.find();
	    	if(list!=null&&list.size()>0){
	    		return (BpmMAttachmentDAO)list.get(0);
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
    return null;
	}
	/**
	 * @return the filename
	 */
	public String getFilename() {
		return filename;
	}
	/**
	 * @param filename the filename to set
	 */
	public void setFilename(String filename) {
		this.filename = filename;
	}

}
