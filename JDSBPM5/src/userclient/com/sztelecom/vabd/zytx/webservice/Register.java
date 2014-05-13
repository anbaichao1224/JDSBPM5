package com.sztelecom.vabd.zytx.webservice;

/**
 * @Title: Web Service 注册接口
 * @Description:
 * @Copyright: Copyright (c) 2007
 * @Company: 深圳电信局增值业务部
 * @author 谢亨骏
 * @version 1.0
 */

public class Register {

	public static String getRandom() {
		return com.sztelecom.vabd.zytx.common.Register.getRandom();
	}

	public static String setCallBackAddr(String uc, String pw, String rand,
			String url) {
		return com.sztelecom.vabd.zytx.common.Register.setCallBackAddr(uc, pw,
				rand, url);
	}
}
