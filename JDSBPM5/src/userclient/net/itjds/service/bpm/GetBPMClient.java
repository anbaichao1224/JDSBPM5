package net.itjds.service.bpm;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;

import net.itjds.bpm.client.BPMSessionFactory;
import net.itjds.bpm.engine.BPMException;
import net.itjds.bpm.engine.WorkflowClientService;
import net.itjds.common.expression.ParseException;
import net.itjds.common.expression.function.AbstractFunction;


public class GetBPMClient extends AbstractFunction {

 
	public WorkflowClientService perform() throws ParseException
    {
    	WorkflowClientService client=null;
    	try {
			client=new BPMSessionFactory().getClientService("oa");
			
		} catch (BPMException e) {
			throw new ParseException("session失效");
		}
		if (client==null){
		   String personId=	ActionContext.getContext().getValueStack().findString("$R(\"personId\")");
			try {
				client=new BPMSessionFactory().newClientService(personId, "oa");
			} catch (BPMException e) {
				e.printStackTrace();
			}
		}
		
		if (client==null){
				throw new ParseException("session失效,请重新登录！");
			}
    	 return client;
    }
}