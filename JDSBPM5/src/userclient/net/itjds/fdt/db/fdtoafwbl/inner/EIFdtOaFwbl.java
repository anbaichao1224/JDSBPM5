package net.itjds.fdt.db.fdtoafwbl.inner;
import net.itjds.usm2.*;
import net.itjds.j2ee.dao.MethodChinaName;
import net.itjds.j2ee.dao.MethodExpressionChinaName;

/**
 * <p>
 * Title: USM
 * </p>
 * <p>
 * Description: FDT_OA_FWBL接口
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
public interface EIFdtOaFwbl extends Usm{
	/**
	 * 获取UUID
	 * 
	 * @return UUID
	 */
	@MethodChinaName(cname = "获取UUID")
	public java.lang.String getUuid();
	public void setUuid(java.lang.String  newVal);
	/**
	 * 获取PROCESSINST_ID
	 * 
	 * @return PROCESSINST_ID
	 */
	@MethodChinaName(cname = "获取PROCESSINST_ID")
	public java.lang.String getProcessinstId();
	public void setProcessinstId(java.lang.String  newVal);
	/**
	 * 获取ACTIVITYINST_ID
	 * 
	 * @return ACTIVITYINST_ID
	 */
	@MethodChinaName(cname = "获取ACTIVITYINST_ID")
	public java.lang.String getActivityinstId();
	public void setActivityinstId(java.lang.String  newVal);
	/**
	 * 获取文种
	 * 
	 * @return 文种
	 */
	@MethodChinaName(cname = "获取文种")
	public java.lang.String getTypeType();
	public void setTypeType(java.lang.String  newVal);
	/**
	 * 获取字号
	 * 
	 * @return 字号
	 */
	@MethodChinaName(cname = "获取字号")
	public java.lang.String getTypeWord();
	public void setTypeWord(java.lang.String  newVal);
	/**
	 * 获取年号
	 * 
	 * @return 年号
	 */
	@MethodChinaName(cname = "获取年号")
	public java.lang.Integer getTypeYear();
	public void setTypeYear(java.lang.Integer  newVal);
	/**
	 * 获取文件号
	 * 
	 * @return 文件号
	 */
	@MethodChinaName(cname = "获取文件号")
	public java.lang.Integer getTypeNum();
	public void setTypeNum(java.lang.Integer  newVal);
	/**
	 * 获取密级
	 * 
	 * @return 密级
	 */
	@MethodChinaName(cname = "获取密级")
	public java.lang.String getMiji();
	public void setMiji(java.lang.String  newVal);
	/**
	 * 获取保密期限
	 * 
	 * @return 保密期限
	 */
	@MethodChinaName(cname = "获取保密期限")
	public java.lang.Integer getBaomiqixian();
	public void setBaomiqixian(java.lang.Integer  newVal);
	/**
	 * 获取缓级
	 * 
	 * @return 缓级
	 */
	@MethodChinaName(cname = "获取缓级")
	public java.lang.String getHuanji();
	public void setHuanji(java.lang.String  newVal);
	/**
	 * 获取定密依据
	 * 
	 * @return 定密依据
	 */
	@MethodChinaName(cname = "获取定密依据")
	public java.lang.String getDingmiyiju();
	public void setDingmiyiju(java.lang.String  newVal);
	/**
	 * 获取签发
	 * 
	 * @return 签发
	 */
	@MethodChinaName(cname = "获取签发")
	public java.lang.String getQianfa();
	public void setQianfa(java.lang.String  newVal);
	/**
	 * 获取会签
	 * 
	 * @return 会签
	 */
	@MethodChinaName(cname = "获取会签")
	public java.lang.String getHuiqian();
	public void setHuiqian(java.lang.String  newVal);
	/**
	 * 获取标题
	 * 
	 * @return 标题
	 */
	@MethodChinaName(cname = "获取标题")
	public java.lang.String getBiaoti();
	public void setBiaoti(java.lang.String  newVal);
	/**
	 * 获取主送
	 * 
	 * @return 主送
	 */
	@MethodChinaName(cname = "获取主送")
	public java.lang.String getZhusong();
	public void setZhusong(java.lang.String  newVal);
	/**
	 * 获取抄报
	 * 
	 * @return 抄报
	 */
	@MethodChinaName(cname = "获取抄报")
	public java.lang.String getChaobao();
	public void setChaobao(java.lang.String  newVal);
	/**
	 * 获取抄送
	 * 
	 * @return 抄送
	 */
	@MethodChinaName(cname = "获取抄送")
	public java.lang.String getChaosong();
	public void setChaosong(java.lang.String  newVal);
	/**
	 * 获取拟稿单位
	 * 
	 * @return 拟稿单位
	 */
	@MethodChinaName(cname = "获取拟稿单位")
	public java.lang.String getNigaodanwei();
	public void setNigaodanwei(java.lang.String  newVal);
	/**
	 * 获取拟稿
	 * 
	 * @return 拟稿
	 */
	@MethodChinaName(cname = "获取拟稿")
	public java.lang.String getNigao();
	public void setNigao(java.lang.String  newVal);
	/**
	 * 获取核稿
	 * 
	 * @return 核稿
	 */
	@MethodChinaName(cname = "获取核稿")
	public java.lang.String getHegao();
	public void setHegao(java.lang.String  newVal);
	/**
	 * 获取印刷
	 * 
	 * @return 印刷
	 */
	@MethodChinaName(cname = "获取印刷")
	public java.lang.String getYinshua();
	public void setYinshua(java.lang.String  newVal);
	/**
	 * 获取校对
	 * 
	 * @return 校对
	 */
	@MethodChinaName(cname = "获取校对")
	public java.lang.String getJiaodui();
	public void setJiaodui(java.lang.String  newVal);
	/**
	 * 获取份数
	 * 
	 * @return 份数
	 */
	@MethodChinaName(cname = "获取份数")
	public java.lang.Integer getFenshu();
	public void setFenshu(java.lang.Integer  newVal);
	/**
	 * 获取主题词
	 * 
	 * @return 主题词
	 */
	@MethodChinaName(cname = "获取主题词")
	public java.lang.String getZhutici();
	public void setZhutici(java.lang.String  newVal);
	/**
	 * 获取起草日期
	 * 
	 * @return 起草日期
	 */
	@MethodChinaName(cname = "获取起草日期")
	public java.sql.Timestamp getQicaoriqi();
	public void setQicaoriqi(java.sql.Timestamp  newVal);

}
