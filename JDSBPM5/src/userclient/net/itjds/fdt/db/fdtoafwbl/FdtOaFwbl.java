package net.itjds.fdt.db.fdtoafwbl;
import net.itjds.usm2.db.*;
import net.itjds.usm2.*;
import java.util.List;

/**
 * <p>
 * Title: USM
 * </p>
 * <p>
 * Description: 局文，内部回函，党组文接口
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
	 * 获取UUID
	 * 
	 * @return UUID
	 */
	public java.lang.String getUuid();
	public void setUuid(java.lang.String uuid);	
	 /**
	 * 获取PROCESSINST_ID
	 * 
	 * @return PROCESSINST_ID
	 */
	public java.lang.String getProcessinstId();
	public void setProcessinstId(java.lang.String processinstId);	
	 /**
	 * 获取ACTIVITYINST_ID
	 * 
	 * @return ACTIVITYINST_ID
	 */
	public java.lang.String getActivityinstId();
	public void setActivityinstId(java.lang.String activityinstId);	
	 /**
	 * 获取文种
	 * 
	 * @return 文种
	 */
	public java.lang.String getTypeType();
	public void setTypeType(java.lang.String typeType);	
	 /**
	 * 获取字号
	 * 
	 * @return 字号
	 */
	public java.lang.String getTypeWord();
	public void setTypeWord(java.lang.String typeWord);	
	 /**
	 * 获取年号
	 * 
	 * @return 年号
	 */
	public java.lang.Integer getTypeYear();
	public void setTypeYear(java.lang.Integer typeYear);	
	 /**
	 * 获取文件号
	 * 
	 * @return 文件号
	 */
	public java.lang.Integer getTypeNum();
	public void setTypeNum(java.lang.Integer typeNum);	
	 /**
	 * 获取密级
	 * 
	 * @return 密级
	 */
	public java.lang.String getMiji();
	public void setMiji(java.lang.String miji);	
	 /**
	 * 获取保密期限
	 * 
	 * @return 保密期限
	 */
	public java.lang.Integer getBaomiqixian();
	public void setBaomiqixian(java.lang.Integer baomiqixian);	
	 /**
	 * 获取缓级
	 * 
	 * @return 缓级
	 */
	public java.lang.String getHuanji();
	public void setHuanji(java.lang.String huanji);	
	 /**
	 * 获取定密依据
	 * 
	 * @return 定密依据
	 */
	public java.lang.String getDingmiyiju();
	public void setDingmiyiju(java.lang.String dingmiyiju);	
	 /**
	 * 获取签发
	 * 
	 * @return 签发
	 */
	public java.lang.String getQianfa();
	public void setQianfa(java.lang.String qianfa);	
	 /**
	 * 获取会签
	 * 
	 * @return 会签
	 */
	public java.lang.String getHuiqian();
	public void setHuiqian(java.lang.String huiqian);	
	 /**
	 * 获取标题
	 * 
	 * @return 标题
	 */
	public java.lang.String getBiaoti();
	public void setBiaoti(java.lang.String biaoti);	
	 /**
	 * 获取主送
	 * 
	 * @return 主送
	 */
	public java.lang.String getZhusong();
	public void setZhusong(java.lang.String zhusong);	
	 /**
	 * 获取抄报
	 * 
	 * @return 抄报
	 */
	public java.lang.String getChaobao();
	public void setChaobao(java.lang.String chaobao);	
	 /**
	 * 获取抄送
	 * 
	 * @return 抄送
	 */
	public java.lang.String getChaosong();
	public void setChaosong(java.lang.String chaosong);	
	 /**
	 * 获取拟稿单位
	 * 
	 * @return 拟稿单位
	 */
	public java.lang.String getNigaodanwei();
	public void setNigaodanwei(java.lang.String nigaodanwei);	
	 /**
	 * 获取拟稿
	 * 
	 * @return 拟稿
	 */
	public java.lang.String getNigao();
	public void setNigao(java.lang.String nigao);	
	 /**
	 * 获取核稿
	 * 
	 * @return 核稿
	 */
	public java.lang.String getHegao();
	public void setHegao(java.lang.String hegao);	
	 /**
	 * 获取印刷
	 * 
	 * @return 印刷
	 */
	public java.lang.String getYinshua();
	public void setYinshua(java.lang.String yinshua);	
	 /**
	 * 获取校对
	 * 
	 * @return 校对
	 */
	public java.lang.String getJiaodui();
	public void setJiaodui(java.lang.String jiaodui);	
	 /**
	 * 获取份数
	 * 
	 * @return 份数
	 */
	public java.lang.Integer getFenshu();
	public void setFenshu(java.lang.Integer fenshu);	
	 /**
	 * 获取主题词
	 * 
	 * @return 主题词
	 */
	public java.lang.String getZhutici();
	public void setZhutici(java.lang.String zhutici);	
	 /**
	 * 获取起草日期
	 * 
	 * @return 起草日期
	 */
	public java.sql.Timestamp getQicaoriqi();
	public void setQicaoriqi(java.sql.Timestamp qicaoriqi);	
    

	

	 
		
	
  }
