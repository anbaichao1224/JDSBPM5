package net.itjds.fdt.db.fdtoafwbl;
import net.itjds.usm2.db.*;
import net.itjds.usm2.*;
import java.util.List;

/**
 * <p>
 * Title: USM
 * </p>
 * <p>
 * Description: ���ģ��ڲ��غ��������Ľӿ�
 * </p>
 * <p>
 * Copyright: Copyright (c) 2008
 * </p>
 * <p>
 * Company: www.justdos.net
 * </p>
 * 
 * @author usm2
 * @version 1.0
 */
	public interface FdtOaFwbl extends UsmProxy{
	
	 /**
	 * ��ȡUUID
	 * 
	 * @return UUID
	 */
	public java.lang.String getUuid();
	public void setUuid(java.lang.String uuid);	
	 /**
	 * ��ȡPROCESSINST_ID
	 * 
	 * @return PROCESSINST_ID
	 */
	public java.lang.String getProcessinstId();
	public void setProcessinstId(java.lang.String processinstId);	
	 /**
	 * ��ȡACTIVITYINST_ID
	 * 
	 * @return ACTIVITYINST_ID
	 */
	public java.lang.String getActivityinstId();
	public void setActivityinstId(java.lang.String activityinstId);	
	 /**
	 * ��ȡ����
	 * 
	 * @return ����
	 */
	public java.lang.String getTypeType();
	public void setTypeType(java.lang.String typeType);	
	 /**
	 * ��ȡ�ֺ�
	 * 
	 * @return �ֺ�
	 */
	public java.lang.String getTypeWord();
	public void setTypeWord(java.lang.String typeWord);	
	 /**
	 * ��ȡ���
	 * 
	 * @return ���
	 */
	public java.lang.Integer getTypeYear();
	public void setTypeYear(java.lang.Integer typeYear);	
	 /**
	 * ��ȡ�ļ���
	 * 
	 * @return �ļ���
	 */
	public java.lang.Integer getTypeNum();
	public void setTypeNum(java.lang.Integer typeNum);	
	 /**
	 * ��ȡ�ܼ�
	 * 
	 * @return �ܼ�
	 */
	public java.lang.String getMiji();
	public void setMiji(java.lang.String miji);	
	 /**
	 * ��ȡ��������
	 * 
	 * @return ��������
	 */
	public java.lang.Integer getBaomiqixian();
	public void setBaomiqixian(java.lang.Integer baomiqixian);	
	 /**
	 * ��ȡ����
	 * 
	 * @return ����
	 */
	public java.lang.String getHuanji();
	public void setHuanji(java.lang.String huanji);	
	 /**
	 * ��ȡ��������
	 * 
	 * @return ��������
	 */
	public java.lang.String getDingmiyiju();
	public void setDingmiyiju(java.lang.String dingmiyiju);	
	 /**
	 * ��ȡǩ��
	 * 
	 * @return ǩ��
	 */
	public java.lang.String getQianfa();
	public void setQianfa(java.lang.String qianfa);	
	 /**
	 * ��ȡ��ǩ
	 * 
	 * @return ��ǩ
	 */
	public java.lang.String getHuiqian();
	public void setHuiqian(java.lang.String huiqian);	
	 /**
	 * ��ȡ����
	 * 
	 * @return ����
	 */
	public java.lang.String getBiaoti();
	public void setBiaoti(java.lang.String biaoti);	
	 /**
	 * ��ȡ����
	 * 
	 * @return ����
	 */
	public java.lang.String getZhusong();
	public void setZhusong(java.lang.String zhusong);	
	 /**
	 * ��ȡ����
	 * 
	 * @return ����
	 */
	public java.lang.String getChaobao();
	public void setChaobao(java.lang.String chaobao);	
	 /**
	 * ��ȡ����
	 * 
	 * @return ����
	 */
	public java.lang.String getChaosong();
	public void setChaosong(java.lang.String chaosong);	
	 /**
	 * ��ȡ��嵥λ
	 * 
	 * @return ��嵥λ
	 */
	public java.lang.String getNigaodanwei();
	public void setNigaodanwei(java.lang.String nigaodanwei);	
	 /**
	 * ��ȡ���
	 * 
	 * @return ���
	 */
	public java.lang.String getNigao();
	public void setNigao(java.lang.String nigao);	
	 /**
	 * ��ȡ�˸�
	 * 
	 * @return �˸�
	 */
	public java.lang.String getHegao();
	public void setHegao(java.lang.String hegao);	
	 /**
	 * ��ȡӡˢ
	 * 
	 * @return ӡˢ
	 */
	public java.lang.String getYinshua();
	public void setYinshua(java.lang.String yinshua);	
	 /**
	 * ��ȡУ��
	 * 
	 * @return У��
	 */
	public java.lang.String getJiaodui();
	public void setJiaodui(java.lang.String jiaodui);	
	 /**
	 * ��ȡ����
	 * 
	 * @return ����
	 */
	public java.lang.Integer getFenshu();
	public void setFenshu(java.lang.Integer fenshu);	
	 /**
	 * ��ȡ�����
	 * 
	 * @return �����
	 */
	public java.lang.String getZhutici();
	public void setZhutici(java.lang.String zhutici);	
	 /**
	 * ��ȡ�������
	 * 
	 * @return �������
	 */
	public java.sql.Timestamp getQicaoriqi();
	public void setQicaoriqi(java.sql.Timestamp qicaoriqi);	
    

	

	 
		
	
  }
