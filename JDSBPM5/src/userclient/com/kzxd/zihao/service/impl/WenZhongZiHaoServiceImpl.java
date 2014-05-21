package com.kzxd.zihao.service.impl;

import kzxd.ttinfo.action.WenZhongAction;
import kzxd.ttinfo.dao.WenZhongDAO;
import net.itjds.j2ee.util.UUID;

import org.appfuse.service.impl.GenericManagerImpl;
import org.springframework.transaction.annotation.Transactional;

import com.kzxd.utils.DateUtil;
import com.kzxd.zihao.dao.WenZhongZiHaoDao;
import com.kzxd.zihao.dao.ZiHaoDao;
import com.kzxd.zihao.entity.WenZhongZiHao;
import com.kzxd.zihao.entity.ZiHao;
import com.kzxd.zihao.service.WenZhongZiHaoService;


@Transactional
public class WenZhongZiHaoServiceImpl extends GenericManagerImpl<WenZhongZiHao, String> implements WenZhongZiHaoService{

	private WenZhongZiHaoDao wzzhDao;
	
	private ZiHaoDao zhDao;
	
	public WenZhongZiHaoServiceImpl(WenZhongZiHaoDao wzzhDao){
		super(wzzhDao);
		this.wzzhDao = wzzhDao;
	}
	
	/**
	 * 根据流程实例id 得到zihao，若没有则向数据库中插入一条
	 * @param actid
	 * @return
	 */
	public ZiHao getZiHao(String actid){
		 ZiHao zihao = this.zhDao.getByActid(actid);
		    if (zihao == null)
		    {
		      zihao = new ZiHao();
		      WenZhongAction wzaction = new WenZhongAction();
		      WenZhongDAO wzdao = wzaction.getByEname("wu");
		      if (wzdao != null) {
		        zihao.setWenzhongid(wzdao.getUuid());
		      }
		      zihao.setActid(actid);
		      zihao.setYear(DateUtil.getYear());
		      zihao.setWenzhong("wu");
		      zihao.setZihao(Integer.valueOf(this.zhDao.getMaxZiHao("wu", DateUtil.getYear()).intValue() + 1));
		      this.zhDao.save(zihao);
		    }

		    return zihao;
	}
	
	/**
	 * 用户选择文种的时候的操作
	 * @param actid
	 * @param wenzhong
	 * @param uuid
	 * @return
	 */
	@Transactional
	public ZiHao getNewZiHao(String actid,String wenzhong,String uuid,String year) {
		String wenzhongO = "";
		Integer zihaoO = 0;
		Integer zihao = zhDao.getMaxZiHao(wenzhong,year);
		zihao = zihao + 1;
		ZiHao zihaoEntity = new ZiHao();
		
		if(uuid==null || uuid.equals("")){
			//zihaoEntity.setUuid((new UUID()).toString());
			zihaoEntity.setActid(actid);
			
			zihaoEntity.setWenzhongid(wenzhong);//zihaoEntity.setWenzhong(wenzhong);
			zihaoEntity.setZihao(zihao);
			zihaoEntity.setYear(year);
			zhDao.save(zihaoEntity);
			
		}else{
			zihaoEntity = zhDao.get(uuid);
			wenzhongO = zihaoEntity.getWenzhongid();//wenzhongO = zihaoEntity.getWenzhong();
			zihaoO = zihaoEntity.getZihao();
			
			WenZhongZiHao wzzh = wzzhDao.getByWenZhongAndZiHao(wenzhongO, zihaoO,year);
			if(wzzh!=null){
				wzzh.setYiyong("n");
				
			}else{
				wzzh = new WenZhongZiHao();
				wzzh.setWenzhongid(wenzhong);//wzzh.setWenzhong(wenzhongO);
				wzzh.setYiyong("n");
				wzzh.setZihao(zihaoO);
				wzzh.setYear(year);
			}
			wzzhDao.save(wzzh);
			zihaoEntity.setActid(actid);
			zihaoEntity.setWenzhongid(wenzhong);//zihaoEntity.setWenzhong(wenzhong);
			zihaoEntity.setZihao(zihao);
			zhDao.update(zihaoEntity);
		}
		return zihaoEntity;
	}
    /**
     * zihao中的zihao 一定要大于maxzihao
     * @param zihaoU
     * @param maxzihao
     */
	@Transactional
	public void saveWzzh(ZiHao zihaoU,Integer maxzihao,Integer yuanMax,String year){
		
		Integer zi = zihaoU.getZihao();
		if(zihaoU.getZihao()!=maxzihao){
			for(int i = maxzihao;i<zi;i++){
				WenZhongZiHao wzzhU = this.wzzhDao.getByWenZhongAndZiHao(zihaoU.getWenzhong(), i,year);
				if(wzzhU == null){
					wzzhU = new WenZhongZiHao();
					wzzhU.setWenzhongid(zihaoU.getWenzhongid());//wzzhU.setWenzhong(zihaoU.getWenzhong());
					
					wzzhU.setYiyong("n");
					wzzhU.setZihao(i);
					wzzhU.setYear(year);
					wzzhDao.save(wzzhU);
				}
				
			}
		}
		
		WenZhongZiHao wzzhU = this.wzzhDao.getByWenZhongAndZiHao(zihaoU.getWenzhong(), zihaoU.getZihao(),year);
		if(wzzhU == null){
			wzzhU = new WenZhongZiHao();
			wzzhU.setWenzhongid(zihaoU.getWenzhongid());//wzzhU.setWenzhong(zihaoU.getWenzhong());
			wzzhU.setYiyong("y");
			wzzhU.setYear(year);
			wzzhU.setZihao(zihaoU.getZihao());
		}else{
			wzzhU.setYiyong("y");
		}
		wzzhDao.save(wzzhU);
		
		
		zhDao.update(zihaoU);
	}
	/**
	 * 当自己字号输入比系统给的字号要小时候，判断当前字号是否已经被应用过
	 * @param zi
	 * @param wenzhong
	 * @return
	 */
	public WenZhongZiHao getYiYong(Integer zi,String wenzhong,String year){
		
		WenZhongZiHao wzzh = wzzhDao.getByWenZhongAndZiHao(wenzhong, zi,year);
		
		return wzzh;
	}
	 /**
     * zihao中的zihao 小于maxzihao
     * @param zihaoU
     * @param maxzihao
     */
	@Transactional
	public void saveWzzh(WenZhongZiHao wzzhU,ZiHao zihaoU,Integer  maxzihao,String year){
		WenZhongZiHao wzzhU1 = this.wzzhDao.getByWenZhongAndZiHao(zihaoU.getWenzhong(), maxzihao,year);
		if(wzzhU1 == null){
			wzzhU1 = new WenZhongZiHao();
			wzzhU1.setWenzhongid(zihaoU.getWenzhongid());//wzzhU1.setWenzhong(zihaoU.getWenzhong());
			wzzhU1.setYiyong("n");
			wzzhU1.setZihao(zihaoU.getZihao());
			wzzhU1.setYear(year);
		}else{
			wzzhU1.setYiyong("n");
		}
		wzzhDao.save(wzzhU1);
		wzzhU.setYiyong("y");
		wzzhDao.save(wzzhU);
		zhDao.save(zihaoU);
	}
	
	public WenZhongZiHaoDao getWzzhDao() {
		return wzzhDao;
	}
	public void setWzzhDao(WenZhongZiHaoDao wzzhDao) {
		this.wzzhDao = wzzhDao;
	}

	public ZiHaoDao getZhDao() {
		return zhDao;
	}

	public void setZhDao(ZiHaoDao zhDao) {
		this.zhDao = zhDao;
	}
	public String selZihao(Integer zi, String wenzhong, String year)
	  {
	    String wzzh = this.wzzhDao.getByZiHao(wenzhong, zi, year);

	    return wzzh;
	  }
	
	
	

}
