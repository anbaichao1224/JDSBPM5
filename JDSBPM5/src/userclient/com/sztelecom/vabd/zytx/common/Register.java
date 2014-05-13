package com.sztelecom.vabd.zytx.common;


import javax.xml.rpc.ParameterMode;
import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.axis.encoding.XMLType;
import org.apache.log4j.Logger;

import com.sztelecom.vabd.zytx.common.Const;



/**
 * @Title: 注册接口
 * @Description:
 * @Copyright: Copyright (c) 2007
 * @Company: 深圳电信局增值业务部
 * @author 谢亨骏
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
				call.getMessageContext().setUsername(Const.HWWebServiceUser);// 用户名。
				call.getMessageContext().setPassword(Const.HWWebServiceUserPasswd);// 密码
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
			// 处理网络传真,短信注册回调信息
			if ((rand.length() == 21)
					&& (rand.substring(0, 3).equals("103"))) {
				// 添加鉴权认证
				log.info("进入短信、网络传真服务验证......");



			}// 处理CTD,CTC注册回调信息
			else {
				log.info("进入CTD,CTC,SMS地址回调......");
				call = (Call) service.createCall();
				call.setTargetEndpointAddress(new java.net.URL(
						Const.HWWebServiceRegisterURL));
				call.setOperationName("setCallBackAddr");
				call.addParameter("uc", XMLType.XSD_STRING, ParameterMode.IN);
				call.addParameter("pw", XMLType.XSD_STRING, ParameterMode.IN);
				call.addParameter("rand", XMLType.XSD_STRING, ParameterMode.IN);
				call.addParameter("url", XMLType.XSD_STRING, ParameterMode.IN);
				call.setReturnType(XMLType.XSD_STRING);
				call.getMessageContext().setUsername(Const.HWWebServiceUser);// 用户名。
				call.getMessageContext().setPassword(
						Const.HWWebServiceUserPasswd);// 密码
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
