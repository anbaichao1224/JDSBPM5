package com.sztelecom.vabd.zytx.webservice;

import com.sztelecom.vabd.zytx.common.util.Md5;
import java.io.PrintStream;
import java.util.List;

public class SMSOAService
{
  public static String sendMsg(String uc, String pw, String rand, String[] destAddresses, String message, int msgId, String connId, String isReturn)
  {
    return com.sztelecom.vabd.zytx.common.SMSService.sendMsg(uc, pw, rand, destAddresses, message, isReturn, msgId, connId);
  }

  public String phoneTixing(List<String> dhhs)
  {
    String ID = "07682281952";

    String PASSWORD = "ABCabc-123";

    String rand = Register.getRandom();
    String pswmd5 = rand + PASSWORD + PASSWORD;
    String md5 = Md5.MD5(pswmd5);
    String connId = Register.setCallBackAddr(ID, md5, rand, "http://172.16.100.30:8080/testWS1/WebUCCompanion/EventCallBackServiceImpl.jws");
    int dhhsSize = dhhs.size();
    String[] addr = new String[dhhsSize];
    for (int i = 0; i < dhhsSize; i++) {
      addr[i] = ((String)dhhs.get(i));
    }
    String sms = "您有一条新公文,请注意查收。";
    int msgId = 10000005;
    rand = Register.getRandom();

    String str = sendMsg(ID, Md5.MD5(rand + PASSWORD + PASSWORD), rand, addr, sms, msgId, connId, "1");
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
    String sms = "您睡觉姿势不对，请起来重睡。  >_<";
    int msgId = 10000005;
    rand = Register.getRandom();

    String str = sendMsg(ID, Md5.MD5(rand + PASSWORD + PASSWORD), rand, addr, sms, msgId, connId, "1");
  }
}