package com.sztelecom.vabd.zytx.webservice;

import com.sztelecom.vabd.zytx.common.util.Md5;
import java.io.PrintStream;
import java.util.List;

public class SMSService
{
  public static String sendMsg(String uc, String pw, String rand, String[] destAddresses, String message, int msgId, String connId, String isReturn)
  {
    return com.sztelecom.vabd.zytx.common.SMSService.sendMsg(uc, pw, rand, destAddresses, message, isReturn, msgId, connId);
  }

  public String phoneTixing(String[] dhhs,String smsid,String password,String sms,String rand,String connId)
  {
    /*String rand = Register.getRandom();
    String pswmd5 = rand + password + password;
    String md5 = Md5.MD5(pswmd5);
    String connId = Register.setCallBackAddr(smsid, md5, rand, "http://172.16.100.30:8080/testWS1/WebUCCompanion/EventCallBackServiceImpl.jws");*/
   
  /*  int dhhsSize = dhhs.size();
    String[] addr = new String[dhhsSize];
    for (int i = 0; i < dhhsSize; i++) {
    	//if(dhhs.get(i)!=null&&dhhs.get(i).equals("")){
    		addr[i] = ((String)dhhs.get(i));
    	//}
      
    }
*/
    //String sms = "����һ���¹���,��ע����ա�";
    
    int msgId = 10000005;
    rand = Register.getRandom();
    String str = sendMsg(smsid, Md5.MD5(rand + password + password), rand, dhhs, sms, msgId, connId, "1");
    if(Integer.valueOf(str) != 0)
    {
    	System.out.println("��������ʧ�ܣ��绰����Ϊ"+dhhs);
    }
    return null;
  }

  public static void main(String[] args)
  {
    String ID = "07682281952";
    String PASSWORD = "ABCabc-123";
    String rand = Register.getRandom();
    String pswmd5 = rand + PASSWORD + PASSWORD;
    String md5 = Md5.MD5(pswmd5);
    String connId = Register.setCallBackAddr(ID, md5, rand, "http://172.16.100.30:8080/testWS1/WebUCCompanion/EventCallBackServiceImpl.jws");
    String[] addr = { "18998733335" };
    String sms = "��˯�����Ʋ��ԣ���������˯��  >_<";
    int msgId = 10000005;
    rand = Register.getRandom();
    String str = sendMsg(ID, Md5.MD5(rand + PASSWORD + PASSWORD), rand, addr, sms, msgId, connId, "1");
  }
}