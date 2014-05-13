package com.sztelecom.vabd.zytx.common;

/**
 * @Title: 相关常量
 * @Description: 卓越通信
 * @Copyright: Copyright (c) 2007
 * @Company: 深圳电信增值业务部
 * @author 谢亨骏
 * @version 1.0
 */
public class Const {
	public static String CallBackURL = "http://202.105.212.146";

	public static String HWWebServiceRegisterURL= "http://202.105.212.146:8080/jboss-net/services/Register";

	public static String HWWebServiceCTDCallURL;// =
												// "http://58.61.151.152:8080/jboss-net/services/CTDCall";

	public static String HWWebServiceCTCCallURL;// =
												// "http://58.61.151.152:8080/jboss-net/services/CTCCall";
	public static String HWWebServiceSMSURL="http://202.105.212.146:8080/jboss-net/services/SendSMS";

	public static String HWWebServiceUser = "test";

	public static String HWWebServiceUserPasswd = "test";

	//各种业务类型；
	public static int  CTD_SERVICE = 100;//点击拨号业务类型;
	public static int CTC_SERVICE = 101;//多方会议业务类型；
	public static int SMS_SERVICE = 102;//短信服务业务类型；
	public static int FAX_SERVICE = 103;//网络传真业务类型；

	public static int MAX_DEST_NUMS = 254;//群发最大数目；
	public static int MAX_CONTENT = 150;//短信最大长度(字节数)；

	//定义短信的各事件处理ID
	public static int ID_SMS_EVENT_SEND = 100;//短信发送事件状态；

	public static int E_SMS_SUCCESS = 0;//短信发送事件成功状态；
	//定义各种通用错误参数；
	public static int E_SYSTEM_EXCEPTION = -1;//系统异常
	public static int E_INVALID_PASSWD = -2;//加密后的密码验证不通过;
	public static int E_INVALID_UCNO = -3;//请求中的卓越通信号码不存在
	public static int E_ILLEGAL_UCNO = -4;//用户类型非法（该用户没有权限完成该操作）
	public static int E_RANDOM_TIMEOUT = -5;//随机串超时，被disable 了

	//定义短信提交时校验通用错误参数
	public static int E_SMS_ILLEGALNUM = -100;//不合法的输入值. 电话号码位数不够等
	public static int E_SMS_MAXNUM = -101;//群发用户过多;
	public static int E_SMS_MAXCONTENT = -102;//短信内容过长;
	public static int E_SMS_CONTENTNULL = -103;//短信内容不能为空;
	public static int E_SMS_NOTSUPPORT = -104;//主叫号码未支持短信服务;
	//	定义平台发送短信时通用错误参数
	public static int E_SMS_DESTADDR = -105;//目标用户不存在; //E_INVLDUSER
	public static int E_SMS_OTHER = -106;//其他未知错误;


	/**
	 *0－传送成功101—系统错误102—目标用户不可达103—目标用户不存在
	 * -1 系统异常。
		-2 加密后的密码验证不通过
		-3 请求中的卓越通信号码不存在
		-4 用户类型非法（该用户没有权限完成该操作）
		-5 随机串超时，被disable 了
		-6 与会者号码号码列表为空
		-7 通道ID 不存在
		-8 卓越通信帐号余额或限额不足；
		-9 卓越通信帐号已达最大同时使用数；
		-10 该用户查询的CTD 呼叫不存在。
		-12 系统超时
		-25 参数不合法。
		-29 呼叫不存在
		-30 会议不存在。
		-31 卓越通信号码非指定会场主持人
		-33 卓越通信号码非发起CTD 号码
		-34 主持人卓越通信号码不存在
		-35 该与会者号码不在会议中
		-36 事务正忙，不允许进行该操作
		-37 会议不存在或会议尚未结束。
		-38 保留
		-39 保留
		-40 该用户没有会议列表
		-41 保留
		-42 保留
		-45 随机数字段长度不合法，或随机字符串无法转成Long 型
		-43 保留
		-44 保留
		-46 CTC 主持人类型不正确
		-47 用户状态不正确（非激活状态）
		-48 主持人不能被踢出
		-78 该卓越通信号码无权Web 接入
		从-100定义短信服务错误代码
		-100不合法的输入值. 电话号码位数不够等
		-101群发用户过多;
		-102短信内容过长;
		-103短信内容不能为空;
		-104主叫号码未支持短信服务
		-105目标用户不存在
		-106其他未知错误
 */

}
