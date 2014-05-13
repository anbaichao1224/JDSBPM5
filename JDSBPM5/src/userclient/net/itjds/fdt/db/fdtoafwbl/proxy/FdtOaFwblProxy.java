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
	 * Description: 局文，内部回函，党组文代理类
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
		 * 获取局文，内部回函，党组文代理对象
		 * 
		 * @return 局文，内部回函，党组文代理对象
		 */
	 	public  FdtOaFwblProxy(EIFdtOaFwbl eiFdtOaFwbl){
	 	   this.eiFdtOaFwbl=eiFdtOaFwbl;
	 	}
	 	
	 
	 	/**
		 * 获取局文，内部回函，党组文对象
		 * 
		 * @return 局文，内部回函，党组文对象
		 */
	 	public Usm getDAO() {
			return eiFdtOaFwbl;
		}
		
		public void setDAO(Usm dao) {
		    this.eiFdtOaFwbl=(EIFdtOaFwbl) dao;
			
		};
	 	
	 	/**
		 * 获取UUID
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
		 * 获取PROCESSINST_ID
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
		 * 获取ACTIVITYINST_ID
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
		 * 获取文种
		 * 
		 * @return 文种
		 */
		@MethodChinaName(cname="文种")
		public java.lang.String getTypeType(){
		  return  this.eiFdtOaFwbl.getTypeType();
		};
		public void setTypeType(java.lang.String typeType){
		      eiFdtOaFwbl.setTypeType(typeType);
	    }
	 	/**
		 * 获取字号
		 * 
		 * @return 字号
		 */
		@MethodChinaName(cname="字号")
		public java.lang.String getTypeWord(){
		  return  this.eiFdtOaFwbl.getTypeWord();
		};
		public void setTypeWord(java.lang.String typeWord){
		      eiFdtOaFwbl.setTypeWord(typeWord);
	    }
	 	/**
		 * 获取年号
		 * 
		 * @return 年号
		 */
		@MethodChinaName(cname="年号")
		public java.lang.Integer getTypeYear(){
		  return  this.eiFdtOaFwbl.getTypeYear();
		};
		public void setTypeYear(java.lang.Integer typeYear){
		      eiFdtOaFwbl.setTypeYear(typeYear);
	    }
	 	/**
		 * 获取文件号
		 * 
		 * @return 文件号
		 */
		@MethodChinaName(cname="文件号")
		public java.lang.Integer getTypeNum(){
		  return  this.eiFdtOaFwbl.getTypeNum();
		};
		public void setTypeNum(java.lang.Integer typeNum){
		      eiFdtOaFwbl.setTypeNum(typeNum);
	    }
	 	/**
		 * 获取密级
		 * 
		 * @return 密级
		 */
		@MethodChinaName(cname="密级")
		public java.lang.String getMiji(){
		  return  this.eiFdtOaFwbl.getMiji();
		};
		public void setMiji(java.lang.String miji){
		      eiFdtOaFwbl.setMiji(miji);
	    }
	 	/**
		 * 获取保密期限
		 * 
		 * @return 保密期限
		 */
		@MethodChinaName(cname="保密期限")
		public java.lang.Integer getBaomiqixian(){
		  return  this.eiFdtOaFwbl.getBaomiqixian();
		};
		public void setBaomiqixian(java.lang.Integer baomiqixian){
		      eiFdtOaFwbl.setBaomiqixian(baomiqixian);
	    }
	 	/**
		 * 获取缓级
		 * 
		 * @return 缓级
		 */
		@MethodChinaName(cname="缓级")
		public java.lang.String getHuanji(){
		  return  this.eiFdtOaFwbl.getHuanji();
		};
		public void setHuanji(java.lang.String huanji){
		      eiFdtOaFwbl.setHuanji(huanji);
	    }
	 	/**
		 * 获取定密依据
		 * 
		 * @return 定密依据
		 */
		@MethodChinaName(cname="定密依据")
		public java.lang.String getDingmiyiju(){
		  return  this.eiFdtOaFwbl.getDingmiyiju();
		};
		public void setDingmiyiju(java.lang.String dingmiyiju){
		      eiFdtOaFwbl.setDingmiyiju(dingmiyiju);
	    }
	 	/**
		 * 获取签发
		 * 
		 * @return 签发
		 */
		@MethodChinaName(cname="签发")
		public java.lang.String getQianfa(){
		  return  this.eiFdtOaFwbl.getQianfa();
		};
		public void setQianfa(java.lang.String qianfa){
		      eiFdtOaFwbl.setQianfa(qianfa);
	    }
	 	/**
		 * 获取会签
		 * 
		 * @return 会签
		 */
		@MethodChinaName(cname="会签")
		public java.lang.String getHuiqian(){
		  return  this.eiFdtOaFwbl.getHuiqian();
		};
		public void setHuiqian(java.lang.String huiqian){
		      eiFdtOaFwbl.setHuiqian(huiqian);
	    }
	 	/**
		 * 获取标题
		 * 
		 * @return 标题
		 */
		@MethodChinaName(cname="标题")
		public java.lang.String getBiaoti(){
		  return  this.eiFdtOaFwbl.getBiaoti();
		};
		public void setBiaoti(java.lang.String biaoti){
		      eiFdtOaFwbl.setBiaoti(biaoti);
	    }
	 	/**
		 * 获取主送
		 * 
		 * @return 主送
		 */
		@MethodChinaName(cname="主送")
		public java.lang.String getZhusong(){
		  return  this.eiFdtOaFwbl.getZhusong();
		};
		public void setZhusong(java.lang.String zhusong){
		      eiFdtOaFwbl.setZhusong(zhusong);
	    }
	 	/**
		 * 获取抄报
		 * 
		 * @return 抄报
		 */
		@MethodChinaName(cname="抄报")
		public java.lang.String getChaobao(){
		  return  this.eiFdtOaFwbl.getChaobao();
		};
		public void setChaobao(java.lang.String chaobao){
		      eiFdtOaFwbl.setChaobao(chaobao);
	    }
	 	/**
		 * 获取抄送
		 * 
		 * @return 抄送
		 */
		@MethodChinaName(cname="抄送")
		public java.lang.String getChaosong(){
		  return  this.eiFdtOaFwbl.getChaosong();
		};
		public void setChaosong(java.lang.String chaosong){
		      eiFdtOaFwbl.setChaosong(chaosong);
	    }
	 	/**
		 * 获取拟稿单位
		 * 
		 * @return 拟稿单位
		 */
		@MethodChinaName(cname="拟稿单位")
		public java.lang.String getNigaodanwei(){
		  return  this.eiFdtOaFwbl.getNigaodanwei();
		};
		public void setNigaodanwei(java.lang.String nigaodanwei){
		      eiFdtOaFwbl.setNigaodanwei(nigaodanwei);
	    }
	 	/**
		 * 获取拟稿
		 * 
		 * @return 拟稿
		 */
		@MethodChinaName(cname="拟稿")
		public java.lang.String getNigao(){
		  return  this.eiFdtOaFwbl.getNigao();
		};
		public void setNigao(java.lang.String nigao){
		      eiFdtOaFwbl.setNigao(nigao);
	    }
	 	/**
		 * 获取核稿
		 * 
		 * @return 核稿
		 */
		@MethodChinaName(cname="核稿")
		public java.lang.String getHegao(){
		  return  this.eiFdtOaFwbl.getHegao();
		};
		public void setHegao(java.lang.String hegao){
		      eiFdtOaFwbl.setHegao(hegao);
	    }
	 	/**
		 * 获取印刷
		 * 
		 * @return 印刷
		 */
		@MethodChinaName(cname="印刷")
		public java.lang.String getYinshua(){
		  return  this.eiFdtOaFwbl.getYinshua();
		};
		public void setYinshua(java.lang.String yinshua){
		      eiFdtOaFwbl.setYinshua(yinshua);
	    }
	 	/**
		 * 获取校对
		 * 
		 * @return 校对
		 */
		@MethodChinaName(cname="校对")
		public java.lang.String getJiaodui(){
		  return  this.eiFdtOaFwbl.getJiaodui();
		};
		public void setJiaodui(java.lang.String jiaodui){
		      eiFdtOaFwbl.setJiaodui(jiaodui);
	    }
	 	/**
		 * 获取份数
		 * 
		 * @return 份数
		 */
		@MethodChinaName(cname="份数")
		public java.lang.Integer getFenshu(){
		  return  this.eiFdtOaFwbl.getFenshu();
		};
		public void setFenshu(java.lang.Integer fenshu){
		      eiFdtOaFwbl.setFenshu(fenshu);
	    }
	 	/**
		 * 获取主题词
		 * 
		 * @return 主题词
		 */
		@MethodChinaName(cname="主题词")
		public java.lang.String getZhutici(){
		  return  this.eiFdtOaFwbl.getZhutici();
		};
		public void setZhutici(java.lang.String zhutici){
		      eiFdtOaFwbl.setZhutici(zhutici);
	    }
	 	/**
		 * 获取起草日期
		 * 
		 * @return 起草日期
		 */
		@MethodChinaName(cname="起草日期")
		public java.sql.Timestamp getQicaoriqi(){
		  return  this.eiFdtOaFwbl.getQicaoriqi();
		};
		public void setQicaoriqi(java.sql.Timestamp qicaoriqi){
		      eiFdtOaFwbl.setQicaoriqi(qicaoriqi);
	    }
	    
	  
		
		
		
		
		
		/**
		 * 获取主键值
		 * 
		 * @return 主键值
		 */
		 public String getPkValue(){
	        return getUuid();
		 }
		 
		 /**
		 * 获取总线中注册的servicekey值
		 * 
		 * @return 总线中注册的servicekey值
		 */
		 public String getServiceKey(){
		    return "FdtOaFwbl";
		 }
	
  }
