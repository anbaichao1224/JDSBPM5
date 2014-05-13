package com.sztelecom.vabd.zytx.common;


import javax.xml.rpc.ParameterMode;
import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.axis.encoding.XMLType;
import org.apache.log4j.Logger;

import com.sztelecom.vabd.zytx.common.Const;



/**
 * @Title: ע��ӿ�
 * @Description:
 * @Copyright: Copyright (c) 2007
 * @Company: ���ڵ��ž���ֵҵ��
 * @author л�࿥
 * @version 1.0
 */

public class Register {
	private static Logger log = Logger.getLogger(Register.class.getName());

	public static String getRandom() {
		log.info("into getRandom......");
		String ret = "-1";
			Service service = new Service();
			Call call;
			try {
				call = (Call) service.createCall();
				call.setTargetEndpointAddress(new java.net.URL(
						Const.HWWebServiceRegisterURL));
				call.setOperationName("getRandom");
				call.setReturnType(XMLType.XSD_STRING);
				call.getMessageContext().setUsername(Const.HWWebServiceUser);// �û�����
				call.getMessageContext().setPassword(Const.HWWebServiceUserPasswd);// ����
				ret = (String) call.invoke(new java.lang.Object[] {});
				log.info("random = " + ret);
			} catch (Exception e) {
				e.printStackTrace();
			}
		return ret;
	}

	public static String setCallBackAddr(String uc, String pw, String rand,
			String url) {
		log.info("into setCallbackAddr......");
		String ret = "-1";
		Service service = new Service();
		Call call;
		try {
			// �������紫��,����ע��ص���Ϣ
			if ((rand.length() == 21)
					&& (rand.substring(0, 3).equals("103"))) {
				// ��Ӽ�Ȩ��֤
				log.info("������š����紫�������֤......");



			}// ����CTD,CTCע��ص���Ϣ
			else {
				log.info("����CTD,CTC,SMS��ַ�ص�......");
				call = (Call) service.createCall();
				call.setTargetEndpointAddress(new java.net.URL(
						Const.HWWebServiceRegisterURL));
				call.setOperationName("setCallBackAddr");
				call.addParameter("uc", XMLType.XSD_STRING, ParameterMode.IN);
				call.addParameter("pw", XMLType.XSD_STRING, ParameterMode.IN);
				call.addParameter("rand", XMLType.XSD_STRING, ParameterMode.IN);
				call.addParameter("url", XMLType.XSD_STRING, ParameterMode.IN);
				call.setReturnType(XMLType.XSD_STRING);
				call.getMessageContext().setUsername(Const.HWWebServiceUser);// �û�����
				call.getMessageContext().setPassword(
						Const.HWWebServiceUserPasswd);// ����
				String myUrl = Const.CallBackURL;
				ret = (String) call.invoke(new java.lang.Object[] { uc, pw,
						rand, myUrl });

			}
			log.info("connID = " + ret);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;



	}
}
