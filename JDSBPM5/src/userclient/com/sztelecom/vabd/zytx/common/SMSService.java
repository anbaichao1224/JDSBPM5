package com.sztelecom.vabd.zytx.common;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.log4j.Logger;

import sun.misc.BASE64Encoder;



/**
 * @Title: Web Service SMS服务相关接口
 * @Description: 卓越通信
 * @Copyright: Copyright (c) 2007
 * @Company: 深圳电信增值业务部
 * @author chenwy
 * @version 1.0
 */

public class SMSService {

	/**
	 * 发送短信接口服务
	 * @param uc,用户UC帐号
	 * @param pw，用户密码
	 * @param rand，随机数
	 * @param destAddresses，接受方地址组
	 * @param message，短消息内容
	 * @return 各短信目前服务器接受处理结果信息
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
			call.getMessageContext().setUsername(Const.HWWebServiceUser);// 用户名。
			call.getMessageContext().setPassword(Const.HWWebServiceUserPasswd);// 密码

			//信息内容base64编码
			BASE64Encoder en = new BASE64Encoder();

			String msg= en.encode(message.getBytes());
			log.info(msg);
			ret = (String) call.invoke(new java.lang.Object[] { uc, pw, rand, callee, isReturn,  msg, Integer.valueOf(msgid), connID});
			//用于指定短信发送；
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
				// 调用成功,保存session信息.
				log.info("短信调用服务成功,返回值=" + ret+",msg="+msg);
			}
			else
				log.info("短信调用服务失败,返回值=" + ret);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}

}
