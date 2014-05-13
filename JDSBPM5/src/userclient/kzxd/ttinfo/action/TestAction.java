package kzxd.ttinfo.action;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.Action;

public class TestAction implements Action {

	public String execute() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public void getMsg() throws IOException{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().write("{success:true}");
		
	}
	public void putMsg() throws IOException{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().write("{success:true}");
	}
}
