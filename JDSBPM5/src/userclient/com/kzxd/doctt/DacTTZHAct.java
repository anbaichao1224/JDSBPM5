package com.kzxd.doctt;

import java.util.Map;


import org.appfuse.webapp.action.BaseAction;



public class DacTTZHAct extends BaseAction{ 
	
	
	private Map<String, String> taotouPerperties;
	
	private String activityInstId;
	
	private String personId;
	
	private String uuid;
	
	private String processInstId;
	
	private String mubanid;
	

	
	public Map<String, String> getTaotouPerperties() {
		return taotouPerperties;
	}
	public void setTaotouPerperties(Map<String, String> taotouPerperties) {
		this.taotouPerperties = taotouPerperties;
	}
	
	
	
	/**
	 * ��ͷ��ת 
	 * @return
	 */
	public String tottdoc(){
		
		

		
		
		/*if(wenzhong.equals("wu")){
			zihao.setWenzhong(" ");
		}else if(wenzhong.equals("neiwangzi")){
			zihao.setWenzhong("������");
		}else if(wenzhong.equals("neiguomijufadian")){
			zihao.setWenzhong("�ڹ��ַܾ��ܵ�");
		}else if(wenzhong.equals("neiwangbanfa")){
			zihao.setWenzhong("�����취");
		}else if(wenzhong.equals("neijizi")){
			zihao.setWenzhong("�ڻ���");
		}else if(wenzhong.equals("neijifa")){
			zihao.setWenzhong("�ڻ���");
		}else if(wenzhong.equals("neimibanfa")){
			zihao.setWenzhong("���ܰ췢");
		}else if(wenzhong.equals("neimifa")){
			zihao.setWenzhong("���ܷ�");
		}else if(wenzhong.equals("neidangbanxinxibanfa")){
			zihao.setWenzhong("�ڵ�����Ϣ�췢");
		}else if(wenzhong.equals("neiguomijufa")){
			zihao.setWenzhong("�ڹ��ַܾ�");
		}else if(wenzhong.equals("neiguomifa")){
			zihao.setWenzhong("�ڹ��ܷ�");
		}else if(wenzhong.equals("neiwangfa")){
			zihao.setWenzhong("������");
		}*/
		taotouPerperties = TaoTouConfigReader.getTaotouProperties();
		
		return "success";
	}
	public String getActivityInstId() {
		return activityInstId;
	}
	public void setActivityInstId(String activityInstId) {
		this.activityInstId = activityInstId;
	}
	public String getPersonId() {
		return personId;
	}
	public void setPersonId(String personId) {
		this.personId = personId;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getProcessInstId() {
		return processInstId;
	}
	public void setProcessInstId(String processInstId) {
		this.processInstId = processInstId;
	}
	public String getMubanid() {
		return mubanid;
	}
	public void setMubanid(String mubanid) {
		this.mubanid = mubanid;
	}
	
	
	
	

}
