package com.kzxd.index.action;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.itjds.userclient.common.BPMActionBase;

import org.apache.struts2.ServletActionContext;

import com.kzxd.index.entity.RecordData;
import com.kzxd.index.service.recordMsg;
import com.kzxd.index.service.impl.recordMsgImpl;
import com.kzxd.index.util.LuceneDemo;
import com.kzxd.index.util.PdfToText;
import com.opensymphony.xwork2.Action;

public class searchAction extends BPMActionBase implements Action{
	
	private recordMsg rMsg;

	public recordMsg getrMsg() {
		return rMsg;
	}

	public void setrMsg(recordMsg rMsg) {
		this.rMsg = rMsg;
	}

	//查找列表
	   public String execute() throws Exception {
			
		    HttpServletRequest request=ServletActionContext.getRequest();
		    request.setCharacterEncoding("UTF-8");
		    String queryString = request.getParameter("title");
			String rollid = "0103";
		   // String filePath = "e:\\index\\ww.txt"; //pdf生成txt路径
			File indexPath = new File("e:\\search\\luceneIndex"); //索引文件路径
			String rootPath = " e:\\index\\ww";   
		    String pdffile = rootPath + ".pdf";    //档案库pdf文件路径 
		    //String filePath = rootPath + ".txt";  //用xpdf生成的txt文件路径    
		    String filePath = "e:\\index\\bb.txt"; 
		    String title ="123";
		    String dkeyword = "111";
		    String createtime = "10-8-9";
		    String overperson = "小胖";
		    PdfToText xpdf = new PdfToText();
		    //xpdf.toTest(pdffile,filePath);//生成txt
		    LuceneDemo demo = new LuceneDemo();
		    datalist = demo.search(queryString,filePath,indexPath,rollid,title,dkeyword,createtime,overperson);//查询索引文件
		    totalCount = datalist.size();
  			return "success";
			   
		}
	   
	 //权限查询
		public String applicantcheck(){
			HttpServletRequest request=ServletActionContext.getRequest();
			HttpSession session = request.getSession(true);
			String personId = session.getAttribute("personId").toString();
			Date endtime = new Date();
			RecordData data = new RecordData();
		    List<RecordData> datalist = new ArrayList<RecordData>();
		    rMsg = new recordMsgImpl();
		    datalist = rMsg.find(personId, 2, endtime);
		    return "treelist";
		}
    
		private List<RecordData> datalist;
		private int totalCount;
		private int start;
		private int limit;
		
		public List<RecordData> getDatalist() {
			return datalist;
		}

		public void setDatalist(List<RecordData> datalist) {
			this.datalist = datalist;
		}

		public int getTotalCount() {
			return totalCount;
		}

		public void setTotalCount(int totalCount) {
			this.totalCount = totalCount;
		}

		public int getStart() {
			return start;
		}

		public void setStart(int start) {
			this.start = start;
		}

		public int getLimit() {
			return limit;
		}

		public void setLimit(int limit) {
			this.limit = limit;
		}
		
		
}
