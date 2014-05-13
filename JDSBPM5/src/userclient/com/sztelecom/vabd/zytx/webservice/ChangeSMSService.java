package com.sztelecom.vabd.zytx.webservice;

import java.util.ArrayList;
import java.util.List;

import com.sztelecom.vabd.zytx.common.util.Md5;

/**
 * @Title: Web Service SMS服务相关接口
 * @Description: 卓越通信
 * @Copyright: Copyright (c) 2007
 * @Company: 深圳电信增值业务部
 * @author chenwy
 * @version 1.0
 */
public class ChangeSMSService  {

	/**
	 * 发送短信接口服务
	 * @param uc,用户UC帐号
	 * @param pw，用户密码
	 * @param rand，随机数
	 * @param destAddresses，接受方地址组
	 * @param message，短消息内容
	 * @return 各短信目前服务器接受处理结果信息
	 */
	public static String sendMsg(String uc, String pw, String rand, String[]destAddresses, String message, int msgId, String connId, String isReturn)
	{
		
		return com.sztelecom.vabd.zytx.common.SMSService.sendMsg(uc, pw, rand,destAddresses, message, isReturn,  msgId, connId);
	}

	//测试

	


	public String phoneTixing(String[] addr,String sms,String PASSWORD,String ID,String connId,String rand){
		
		
		//分配的测试ID号码
		// String ID= "07682803799";
		//分配的共性办公应用系统
		//String ID= "07682281952";
		//公文交换
		//String ID= "07682283132";
		//密码
	     //String PASSWORD="ABCabc-123";

	    //到服务器取随机数
		//String rand = Register.getRandom();
		//String pswmd5 = rand+PASSWORD+PASSWORD;
		//String md5 = Md5.MD5(pswmd5);
		//设置回调地址，状态报告和回复信息会从此回调地址发回来,如果不需要回复和状态，这里的URL可以随便设置一个
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
		//发送短信
		String str = ChangeSMSService.sendMsg(ID,Md5.MD5(rand+PASSWORD+PASSWORD),rand,  addr, sms, msgId, connId, "1");
		System.out.println("短信提醒结果:"+str);
		return str;
		
		
	}
	public static void main(String[] args)
	{
		//分配的ID号码
		String ID= "07682803799";
		//密码
	     String PASSWORD="ABCabc-123";
	    //到服务器取随机数
		String rand = Register.getRandom();
		String pswmd5 = rand+PASSWORD+PASSWORD;
		String md5 = Md5.MD5(pswmd5);
		//设置回调地址，状态报告和回复信息会从此回调地址发回来,如果不需要回复和状态，这里的URL可以随便设置一个
		String connId = Register.setCallBackAddr(ID, md5,rand, "http://172.16.100.30:8080/testWS1/WebUCCompanion/EventCallBackServiceImpl.jws");
	    //发送的号码列表
		String addr[] = new String[]{"18998733335"};
		String sms = "您睡觉姿势不对，请起来重睡。  >_<";
		int msgId = 10000005;
		rand = Register.getRandom();

		//发送短信
		String str = ChangeSMSService.sendMsg(ID, Md5.MD5(rand+PASSWORD+PASSWORD),rand, addr, sms, msgId, connId, "1");
	}

}
