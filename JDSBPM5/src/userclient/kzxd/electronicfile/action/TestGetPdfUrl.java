package kzxd.electronicfile.action;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletResponse;

import net.itjds.userclient.usm.action.downLoadElectronicfileAction;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.Action;

public class TestGetPdfUrl implements Action {
	
	
	public String execute() throws Exception {
		OutputStream os = null;
		InputStream inputStream = null;
		HttpServletResponse response = ServletActionContext.getResponse();
		try {
			os = response.getOutputStream();
			String name = "";
			
			inputStream = new BufferedInputStream(new FileInputStream("D:\\test.pdf"));
			byte[] buff = new byte[1024];
			int n = 0;
			
			while((n = inputStream.read(buff)) > 0){
				os.write(buff, 0, n);
			}
			
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			
			if(os != null){
				try {
					os.flush();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				try {
					os.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				os = null;
			}
			
			if(inputStream != null){
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				inputStream = null;
			}
		}
		return null;
	}

	private void writeResponse(int status) {
		
	}
}
