package com.kzxd.zihao.service;

import org.appfuse.service.GenericManager;
import org.springframework.transaction.annotation.Transactional;

import com.kzxd.zihao.entity.WenZhongZiHao;
import com.kzxd.zihao.entity.ZiHao;

public interface WenZhongZiHaoService  extends GenericManager<com.kzxd.zihao.entity.WenZhongZiHao, String>{
	/**
	 * 用户选择文种的时候的操作
	 * @param actid
	 * @param wenzhong
	 * @param uuid
	 * @return
	 */
	public com.kzxd.zihao.entity.ZiHao getNewZiHao(String actid,String wenzhong,String uuid,String year);
	
	/**
	 * 当自己字号输入比系统给的字号要小时候，判断当前字号是否已经被应用过
	 * @param zi
	 * @param wenzhong
	 * @return
	 */
	public WenZhongZiHao getYiYong(Integer zi,String wenzhong,String year);
	/**
     *当字号小于maxzihao
     * @param zihaoU
     * @param maxzihao
     */
	public void saveWzzh(WenZhongZiHao wzzhU,ZiHao zihaoU,Integer yuanMax,String year);
	 /**
     *  zihao中的zihao 一定要大于maxzihao
     * @param zihaoU
     * @param maxzihao
     */
	public void saveWzzh(ZiHao zihaoU,Integer maxzihao,Integer yuanMax,String year);
	/**
	 * 根据流程实例id 得到zihao，若没有则向数据库中插入一条
	 * @param actid
	 * @return
	 */
	public ZiHao getZiHao(String actid);
	
	
	 public abstract String selZihao(Integer paramInteger, String paramString1, String paramString2);
}
