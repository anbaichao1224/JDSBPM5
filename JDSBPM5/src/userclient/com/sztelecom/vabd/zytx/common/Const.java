package com.sztelecom.vabd.zytx.common;

/**
 * @Title: ��س���
 * @Description: ׿Խͨ��
 * @Copyright: Copyright (c) 2007
 * @Company: ���ڵ�����ֵҵ��
 * @author л�࿥
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

	//����ҵ�����ͣ�
	public static int  CTD_SERVICE = 100;//�������ҵ������;
	public static int CTC_SERVICE = 101;//�෽����ҵ�����ͣ�
	public static int SMS_SERVICE = 102;//���ŷ���ҵ�����ͣ�
	public static int FAX_SERVICE = 103;//���紫��ҵ�����ͣ�

	public static int MAX_DEST_NUMS = 254;//Ⱥ�������Ŀ��
	public static int MAX_CONTENT = 150;//������󳤶�(�ֽ���)��

	//������ŵĸ��¼�����ID
	public static int ID_SMS_EVENT_SEND = 100;//���ŷ����¼�״̬��

	public static int E_SMS_SUCCESS = 0;//���ŷ����¼��ɹ�״̬��
	//�������ͨ�ô��������
	public static int E_SYSTEM_EXCEPTION = -1;//ϵͳ�쳣
	public static int E_INVALID_PASSWD = -2;//���ܺ��������֤��ͨ��;
	public static int E_INVALID_UCNO = -3;//�����е�׿Խͨ�ź��벻����
	public static int E_ILLEGAL_UCNO = -4;//�û����ͷǷ������û�û��Ȩ����ɸò�����
	public static int E_RANDOM_TIMEOUT = -5;//�������ʱ����disable ��

	//��������ύʱУ��ͨ�ô������
	public static int E_SMS_ILLEGALNUM = -100;//���Ϸ�������ֵ. �绰����λ��������
	public static int E_SMS_MAXNUM = -101;//Ⱥ���û�����;
	public static int E_SMS_MAXCONTENT = -102;//�������ݹ���;
	public static int E_SMS_CONTENTNULL = -103;//�������ݲ���Ϊ��;
	public static int E_SMS_NOTSUPPORT = -104;//���к���δ֧�ֶ��ŷ���;
	//	����ƽ̨���Ͷ���ʱͨ�ô������
	public static int E_SMS_DESTADDR = -105;//Ŀ���û�������; //E_INVLDUSER
	public static int E_SMS_OTHER = -106;//����δ֪����;


	/**
	 *0�����ͳɹ�101��ϵͳ����102��Ŀ���û����ɴ�103��Ŀ���û�������
	 * -1 ϵͳ�쳣��
		-2 ���ܺ��������֤��ͨ��
		-3 �����е�׿Խͨ�ź��벻����
		-4 �û����ͷǷ������û�û��Ȩ����ɸò�����
		-5 �������ʱ����disable ��
		-6 ����ߺ�������б�Ϊ��
		-7 ͨ��ID ������
		-8 ׿Խͨ���ʺ������޶�㣻
		-9 ׿Խͨ���ʺ��Ѵ����ͬʱʹ������
		-10 ���û���ѯ��CTD ���в����ڡ�
		-12 ϵͳ��ʱ
		-25 �������Ϸ���
		-29 ���в�����
		-30 ���鲻���ڡ�
		-31 ׿Խͨ�ź����ָ���᳡������
		-33 ׿Խͨ�ź���Ƿ���CTD ����
		-34 ������׿Խͨ�ź��벻����
		-35 ������ߺ��벻�ڻ�����
		-36 ������æ����������иò���
		-37 ���鲻���ڻ������δ������
		-38 ����
		-39 ����
		-40 ���û�û�л����б�
		-41 ����
		-42 ����
		-45 ������ֶγ��Ȳ��Ϸ���������ַ����޷�ת��Long ��
		-43 ����
		-44 ����
		-46 CTC ���������Ͳ���ȷ
		-47 �û�״̬����ȷ���Ǽ���״̬��
		-48 �����˲��ܱ��߳�
		-78 ��׿Խͨ�ź�����ȨWeb ����
		��-100������ŷ���������
		-100���Ϸ�������ֵ. �绰����λ��������
		-101Ⱥ���û�����;
		-102�������ݹ���;
		-103�������ݲ���Ϊ��;
		-104���к���δ֧�ֶ��ŷ���
		-105Ŀ���û�������
		-106����δ֪����
 */

}
