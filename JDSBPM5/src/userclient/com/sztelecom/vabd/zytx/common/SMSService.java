package com.sztelecom.vabd.zytx.common;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.log4j.Logger;

import sun.misc.BASE64Encoder;



/**
 * @Title: Web Service SMS������ؽӿ�
 * @Description: ׿Խͨ��
 * @Copyright: Copyright (c) 2007
 * @Company: ���ڵ�����ֵҵ��
 * @author chenwy
 * @version 1.0
 */

public class SMSService {

	/**
	 * ���Ͷ��Žӿڷ���
	 * @param uc,�û�UC�ʺ�
	 * @param pw���û�����
	 * @param rand�������
	 * @param destAddresses�����ܷ���ַ��
	 * @param message������Ϣ����
	 * @return ������Ŀǰ���������ܴ�������Ϣ
	 */
	private static Logger log = Logger.getLogger(SMSService.class.getName());
	public static String sendMsg(String uc, String pw, String rand, String[]callee, String message,
			String isReturn, int msgid, String connID ){

		log.info("into SMSService......");
		String ret = "-1";
		Service service = new Service();
		Call call;
		try {
			call = (Call) service.createCall();
			call.setTargetEndpointAddress(new java.net.URL(
					Const.HWWebServiceSMSURL));
			call.setOperationName("sendSMS");
			call.getMessageContext().setUsername(Const.HWWebServiceUser);// �û�����
			call.getMessageContext().setPassword(Const.HWWebServiceUserPasswd);// ����

			//��Ϣ����base64����
			BASE64Encoder en = new BASE64Encoder();

			String msg= en.encode(message.getBytes());
			log.info(msg);
			ret = (String) call.invoke(new java.lang.Object[] { uc, pw, rand, callee, isReturn,  msg, Integer.valueOf(msgid), connID});
			//����ָ�����ŷ��ͣ�
			log.info("uc="+uc);
			log.info("pw="+pw);
			log.info("rand="+rand);
			log.info("callee="+callee[0]);
			log.info("isReturn="+isReturn);
			log.info("msg="+msg);
			log.info("msgid="+msgid);
			log.info("connID="+connID);

			if (Integer.parseInt(ret) == 0)
			{
				// ���óɹ�,����session��Ϣ.
				log.info("���ŵ��÷���ɹ�,����ֵ=" + ret+",msg="+msg);
			}
			else
				log.info("���ŵ��÷���ʧ��,����ֵ=" + ret);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}

}
