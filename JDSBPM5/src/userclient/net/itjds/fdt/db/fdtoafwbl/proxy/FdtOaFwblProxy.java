package net.itjds.fdt.db.fdtoafwbl.proxy;

import net.itjds.usm2.*;
import net.itjds.fdt.*;
import net.itjds.fdt.db.fdtoafwbl.*;
import net.itjds.fdt.db.fdtoafwbl.proxy.*;
import net.itjds.fdt.db.fdtoafwbl.inner.*;
import net.itjds.usm2.db.*;
import java.util.List;
import java.util.HashSet;
import net.itjds.j2ee.dao.MethodChinaName;
import net.itjds.usm2.db.util.EsbUtil;

	/**
	 * <p>
	 * Title: USM
	 * </p>
	 * <p>
	 * Description: ���ģ��ڲ��غ��������Ĵ�����
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
	public class FdtOaFwblProxy implements FdtOaFwbl{
	 	
	 	EIFdtOaFwbl eiFdtOaFwbl;
	 	
	 	
	 	/**
		 * ��ȡ���ģ��ڲ��غ��������Ĵ������
		 * 
		 * @return ���ģ��ڲ��غ��������Ĵ������
		 */
	 	public  FdtOaFwblProxy(EIFdtOaFwbl eiFdtOaFwbl){
	 	   this.eiFdtOaFwbl=eiFdtOaFwbl;
	 	}
	 	
	 
	 	/**
		 * ��ȡ���ģ��ڲ��غ��������Ķ���
		 * 
		 * @return ���ģ��ڲ��غ��������Ķ���
		 */
	 	public Usm getDAO() {
			return eiFdtOaFwbl;
		}
		
		public void setDAO(Usm dao) {
		    this.eiFdtOaFwbl=(EIFdtOaFwbl) dao;
			
		};
	 	
	 	/**
		 * ��ȡUUID
		 * 
		 * @return UUID
		 */
		@MethodChinaName(cname="UUID")
		public java.lang.String getUuid(){
		  return  this.eiFdtOaFwbl.getUuid();
		};
		public void setUuid(java.lang.String uuid){
		      eiFdtOaFwbl.setUuid(uuid);
	    }
	 	/**
		 * ��ȡPROCESSINST_ID
		 * 
		 * @return PROCESSINST_ID
		 */
		@MethodChinaName(cname="PROCESSINST_ID")
		public java.lang.String getProcessinstId(){
		  return  this.eiFdtOaFwbl.getProcessinstId();
		};
		public void setProcessinstId(java.lang.String processinstId){
		      eiFdtOaFwbl.setProcessinstId(processinstId);
	    }
	 	/**
		 * ��ȡACTIVITYINST_ID
		 * 
		 * @return ACTIVITYINST_ID
		 */
		@MethodChinaName(cname="ACTIVITYINST_ID")
		public java.lang.String getActivityinstId(){
		  return  this.eiFdtOaFwbl.getActivityinstId();
		};
		public void setActivityinstId(java.lang.String activityinstId){
		      eiFdtOaFwbl.setActivityinstId(activityinstId);
	    }
	 	/**
		 * ��ȡ����
		 * 
		 * @return ����
		 */
		@MethodChinaName(cname="����")
		public java.lang.String getTypeType(){
		  return  this.eiFdtOaFwbl.getTypeType();
		};
		public void setTypeType(java.lang.String typeType){
		      eiFdtOaFwbl.setTypeType(typeType);
	    }
	 	/**
		 * ��ȡ�ֺ�
		 * 
		 * @return �ֺ�
		 */
		@MethodChinaName(cname="�ֺ�")
		public java.lang.String getTypeWord(){
		  return  this.eiFdtOaFwbl.getTypeWord();
		};
		public void setTypeWord(java.lang.String typeWord){
		      eiFdtOaFwbl.setTypeWord(typeWord);
	    }
	 	/**
		 * ��ȡ���
		 * 
		 * @return ���
		 */
		@MethodChinaName(cname="���")
		public java.lang.Integer getTypeYear(){
		  return  this.eiFdtOaFwbl.getTypeYear();
		};
		public void setTypeYear(java.lang.Integer typeYear){
		      eiFdtOaFwbl.setTypeYear(typeYear);
	    }
	 	/**
		 * ��ȡ�ļ���
		 * 
		 * @return �ļ���
		 */
		@MethodChinaName(cname="�ļ���")
		public java.lang.Integer getTypeNum(){
		  return  this.eiFdtOaFwbl.getTypeNum();
		};
		public void setTypeNum(java.lang.Integer typeNum){
		      eiFdtOaFwbl.setTypeNum(typeNum);
	    }
	 	/**
		 * ��ȡ�ܼ�
		 * 
		 * @return �ܼ�
		 */
		@MethodChinaName(cname="�ܼ�")
		public java.lang.String getMiji(){
		  return  this.eiFdtOaFwbl.getMiji();
		};
		public void setMiji(java.lang.String miji){
		      eiFdtOaFwbl.setMiji(miji);
	    }
	 	/**
		 * ��ȡ��������
		 * 
		 * @return ��������
		 */
		@MethodChinaName(cname="��������")
		public java.lang.Integer getBaomiqixian(){
		  return  this.eiFdtOaFwbl.getBaomiqixian();
		};
		public void setBaomiqixian(java.lang.Integer baomiqixian){
		      eiFdtOaFwbl.setBaomiqixian(baomiqixian);
	    }
	 	/**
		 * ��ȡ����
		 * 
		 * @return ����
		 */
		@MethodChinaName(cname="����")
		public java.lang.String getHuanji(){
		  return  this.eiFdtOaFwbl.getHuanji();
		};
		public void setHuanji(java.lang.String huanji){
		      eiFdtOaFwbl.setHuanji(huanji);
	    }
	 	/**
		 * ��ȡ��������
		 * 
		 * @return ��������
		 */
		@MethodChinaName(cname="��������")
		public java.lang.String getDingmiyiju(){
		  return  this.eiFdtOaFwbl.getDingmiyiju();
		};
		public void setDingmiyiju(java.lang.String dingmiyiju){
		      eiFdtOaFwbl.setDingmiyiju(dingmiyiju);
	    }
	 	/**
		 * ��ȡǩ��
		 * 
		 * @return ǩ��
		 */
		@MethodChinaName(cname="ǩ��")
		public java.lang.String getQianfa(){
		  return  this.eiFdtOaFwbl.getQianfa();
		};
		public void setQianfa(java.lang.String qianfa){
		      eiFdtOaFwbl.setQianfa(qianfa);
	    }
	 	/**
		 * ��ȡ��ǩ
		 * 
		 * @return ��ǩ
		 */
		@MethodChinaName(cname="��ǩ")
		public java.lang.String getHuiqian(){
		  return  this.eiFdtOaFwbl.getHuiqian();
		};
		public void setHuiqian(java.lang.String huiqian){
		      eiFdtOaFwbl.setHuiqian(huiqian);
	    }
	 	/**
		 * ��ȡ����
		 * 
		 * @return ����
		 */
		@MethodChinaName(cname="����")
		public java.lang.String getBiaoti(){
		  return  this.eiFdtOaFwbl.getBiaoti();
		};
		public void setBiaoti(java.lang.String biaoti){
		      eiFdtOaFwbl.setBiaoti(biaoti);
	    }
	 	/**
		 * ��ȡ����
		 * 
		 * @return ����
		 */
		@MethodChinaName(cname="����")
		public java.lang.String getZhusong(){
		  return  this.eiFdtOaFwbl.getZhusong();
		};
		public void setZhusong(java.lang.String zhusong){
		      eiFdtOaFwbl.setZhusong(zhusong);
	    }
	 	/**
		 * ��ȡ����
		 * 
		 * @return ����
		 */
		@MethodChinaName(cname="����")
		public java.lang.String getChaobao(){
		  return  this.eiFdtOaFwbl.getChaobao();
		};
		public void setChaobao(java.lang.String chaobao){
		      eiFdtOaFwbl.setChaobao(chaobao);
	    }
	 	/**
		 * ��ȡ����
		 * 
		 * @return ����
		 */
		@MethodChinaName(cname="����")
		public java.lang.String getChaosong(){
		  return  this.eiFdtOaFwbl.getChaosong();
		};
		public void setChaosong(java.lang.String chaosong){
		      eiFdtOaFwbl.setChaosong(chaosong);
	    }
	 	/**
		 * ��ȡ��嵥λ
		 * 
		 * @return ��嵥λ
		 */
		@MethodChinaName(cname="��嵥λ")
		public java.lang.String getNigaodanwei(){
		  return  this.eiFdtOaFwbl.getNigaodanwei();
		};
		public void setNigaodanwei(java.lang.String nigaodanwei){
		      eiFdtOaFwbl.setNigaodanwei(nigaodanwei);
	    }
	 	/**
		 * ��ȡ���
		 * 
		 * @return ���
		 */
		@MethodChinaName(cname="���")
		public java.lang.String getNigao(){
		  return  this.eiFdtOaFwbl.getNigao();
		};
		public void setNigao(java.lang.String nigao){
		      eiFdtOaFwbl.setNigao(nigao);
	    }
	 	/**
		 * ��ȡ�˸�
		 * 
		 * @return �˸�
		 */
		@MethodChinaName(cname="�˸�")
		public java.lang.String getHegao(){
		  return  this.eiFdtOaFwbl.getHegao();
		};
		public void setHegao(java.lang.String hegao){
		      eiFdtOaFwbl.setHegao(hegao);
	    }
	 	/**
		 * ��ȡӡˢ
		 * 
		 * @return ӡˢ
		 */
		@MethodChinaName(cname="ӡˢ")
		public java.lang.String getYinshua(){
		  return  this.eiFdtOaFwbl.getYinshua();
		};
		public void setYinshua(java.lang.String yinshua){
		      eiFdtOaFwbl.setYinshua(yinshua);
	    }
	 	/**
		 * ��ȡУ��
		 * 
		 * @return У��
		 */
		@MethodChinaName(cname="У��")
		public java.lang.String getJiaodui(){
		  return  this.eiFdtOaFwbl.getJiaodui();
		};
		public void setJiaodui(java.lang.String jiaodui){
		      eiFdtOaFwbl.setJiaodui(jiaodui);
	    }
	 	/**
		 * ��ȡ����
		 * 
		 * @return ����
		 */
		@MethodChinaName(cname="����")
		public java.lang.Integer getFenshu(){
		  return  this.eiFdtOaFwbl.getFenshu();
		};
		public void setFenshu(java.lang.Integer fenshu){
		      eiFdtOaFwbl.setFenshu(fenshu);
	    }
	 	/**
		 * ��ȡ�����
		 * 
		 * @return �����
		 */
		@MethodChinaName(cname="�����")
		public java.lang.String getZhutici(){
		  return  this.eiFdtOaFwbl.getZhutici();
		};
		public void setZhutici(java.lang.String zhutici){
		      eiFdtOaFwbl.setZhutici(zhutici);
	    }
	 	/**
		 * ��ȡ�������
		 * 
		 * @return �������
		 */
		@MethodChinaName(cname="�������")
		public java.sql.Timestamp getQicaoriqi(){
		  return  this.eiFdtOaFwbl.getQicaoriqi();
		};
		public void setQicaoriqi(java.sql.Timestamp qicaoriqi){
		      eiFdtOaFwbl.setQicaoriqi(qicaoriqi);
	    }
	    
	  
		
		
		
		
		
		/**
		 * ��ȡ����ֵ
		 * 
		 * @return ����ֵ
		 */
		 public String getPkValue(){
	        return getUuid();
		 }
		 
		 /**
		 * ��ȡ������ע���servicekeyֵ
		 * 
		 * @return ������ע���servicekeyֵ
		 */
		 public String getServiceKey(){
		    return "FdtOaFwbl";
		 }
	
  }
