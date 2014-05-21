package net.itjds.worklist.list.action;

import java.io.OutputStream;
import java.io.Writer;
import java.util.Iterator;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import net.itjds.bpm.data.xmlproxy.ExpressionTempAnnotationProxy;
import net.itjds.bpm.data.xmlproxy.manager.EsbBeanFactory;
import net.itjds.bpm.data.xmlproxy.manager.ExpressionTempBean;
import net.itjds.worklist.list.support.GridDefFactory;
import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.Action;
public class BPMClientViewAction implements Action {
	private String type;
	private	Map param;
	
	public String execute() throws Exception {
		GridDefFactory factory = GridDefFactory.getListDefFactory();
		HttpServletResponse response = ServletActionContext.getResponse();
		OutputStream os = response.getOutputStream();
		ExpressionTempAnnotationProxy esb = (ExpressionTempAnnotationProxy)EsbBeanFactory.newInstance().getManagerMap().get("worklist");
		ExpressionTempBean bean = (ExpressionTempBean) esb.getExpressionTempBeanInst(type);
		String classType = bean.getClazz();
		String processDefVersionIds = GetprocessDefVersionIds();
		if(processDefVersionIds!=null && processDefVersionIds.equals("4AEDDF30-F319-11E0-AADD-FE6F1E787648")){//���ģ�AllCompleteDispatchList�������Ѱ죩 AllEndList�����İ�ᣩ    AllWaitList�����Ĵ��죩  DraftDefaultList�������ٰ죩��
			Writer w = factory.build(classType,param,"jsTemplateDispatchList.ftl");
			os.write( w.toString().getBytes("UTF-8"));
		}else{//�����Ѱ�
			Writer w = factory.build(classType,param,"jsTemplate.ftl");
			os.write( w.toString().getBytes("UTF-8"));
		}


		os.flush();
		return null;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	public Map getParam() {
		return param;
	}

	public void setParam(Map param) {
		this.param = param;
	}
	//ͨ��·�����̻�ȡid
	public String GetprocessDefVersionIds() {
		String[] val ={"null"};
			if(param != null && param.size() > 0){
				Iterator keys = param.keySet().iterator();
				while(keys.hasNext()){
					String key = (String)keys.next();
					if(key.equals("processDefVersionIds")){
						val = (String[])param.get(key);
						break;
					}
				}
			}
		return val[0];
	}
}
