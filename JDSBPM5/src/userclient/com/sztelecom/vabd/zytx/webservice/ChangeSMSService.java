package com.sztelecom.vabd.zytx.webservice;

import java.util.ArrayList;
import java.util.List;

import com.sztelecom.vabd.zytx.common.util.Md5;

/**
 * @Title: Web Service SMS������ؽӿ�
 * @Description: ׿Խͨ��
 * @Copyright: Copyright (c) 2007
 * @Company: ���ڵ�����ֵҵ��
 * @author chenwy
 * @version 1.0
 */
public class ChangeSMSService  {

	/**
	 * ���Ͷ��Žӿڷ���
	 * @param uc,�û�UC�ʺ�
	 * @param pw���û�����
	 * @param rand�������
	 * @param destAddresses�����ܷ���ַ��
	 * @param message������Ϣ����
	 * @return ������Ŀǰ���������ܴ�������Ϣ
	 */
	public static String sendMsg(String uc, String pw, String rand, String[]destAddresses, String message, int msgId, String connId, String isReturn)
	{
		
		return com.sztelecom.vabd.zytx.common.SMSService.sendMsg(uc, pw, rand,destAddresses, message, isReturn,  msgId, connId);
	}

	//����

	


	public String phoneTixing(String[] addr,String sms,String PASSWORD,String ID,String connId,String rand){
		
		
		//����Ĳ���ID����
		// String ID= "07682803799";
		//����Ĺ��԰칫Ӧ��ϵͳ
		//String ID= "07682281952";
		//���Ľ���
		//String ID= "07682283132";
		//����
	     //String PASSWORD="ABCabc-123";

	    //��������ȡ�����
		//String rand = Register.getRandom();
		//String pswmd5 = rand+PASSWORD+PASSWORD;
		//String md5 = Md5.MD5(pswmd5);
		//���ûص���ַ��״̬����ͻظ���Ϣ��Ӵ˻ص���ַ������,�������Ҫ�ظ���״̬�������URL�����������һ��
		//String connId = Register.setCallBackAddr(ID, md5,rand, "http://172.16.100.30:8080/testWS1/WebUCCompanion/EventCallBackServiceImpl.jws");
	   /* String addr="";
	    int dhhsSize = dhhs.size();
	    String addr[] = new String[dhhsSize];
	    for(int i = 0; i<dhhsSize; i++){
	    	addr[i] = dhhs.get(i);
	    }*/
	   // String addr[] = new String[]{"18239473000"};
	   
		int msgId = 10000005;
		//rand = Register.getRandom();
		//���Ͷ���
		String str = ChangeSMSService.sendMsg(ID,Md5.MD5(rand+PASSWORD+PASSWORD),rand,  addr, sms, msgId, connId, "1");
		System.out.println("�������ѽ��:"+str);
		return str;
		
		
	}
	public static void main(String[] args)
	{
		//�����ID����
		String ID= "07682803799";
		//����
	     String PASSWORD="ABCabc-123";
	    //��������ȡ�����
		String rand = Register.getRandom();
		String pswmd5 = rand+PASSWORD+PASSWORD;
		String md5 = Md5.MD5(pswmd5);
		//���ûص���ַ��״̬����ͻظ���Ϣ��Ӵ˻ص���ַ������,�������Ҫ�ظ���״̬�������URL�����������һ��
		String connId = Register.setCallBackAddr(ID, md5,rand, "http://172.16.100.30:8080/testWS1/WebUCCompanion/EventCallBackServiceImpl.jws");
	    //���͵ĺ����б�
		String addr[] = new String[]{"18998733335"};
		String sms = "��˯�����Ʋ��ԣ���������˯��  >_<";
		int msgId = 10000005;
		rand = Register.getRandom();

		//���Ͷ���
		String str = ChangeSMSService.sendMsg(ID, Md5.MD5(rand+PASSWORD+PASSWORD),rand, addr, sms, msgId, connId, "1");
	}

}
