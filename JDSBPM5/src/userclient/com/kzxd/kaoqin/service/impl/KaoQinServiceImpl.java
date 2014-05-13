package com.kzxd.kaoqin.service.impl;

import java.io.IOException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import net.itjds.usm.annotation.PropertyChinaName;
import net.itjds.usm.persistence.model.Personaccount;


import com.kzxd.kaoqin.dao.KaoQinDao;
import com.kzxd.kaoqin.pojo.kaoqin;
import com.kzxd.kaoqin.pojo.persona;
import com.kzxd.kaoqin.service.KaoQinService;
import com.kzxd.kaoqin.util.KQUtil;

public class KaoQinServiceImpl implements KaoQinService {
	//根据personId获取personaccount记录
	  public persona getperson(String personid){ 
		  StringBuffer bf=new StringBuffer();
		  List<String> list=new ArrayList();
		 
			
		  bf.append("from persona c  where ");
		  if(personid!=null&&personid.length()>0){
			  bf.append("c.personid=?");
			  list.add(personid);  
		  }  
		
		  persona mma= kaoqindao.findBypersonid(bf.toString(), list).get(0);
		
		return mma ;
	  }
	   
	  
	  
	 
	  
	  
	  
	  
	  
	//向前台返回json数据
   public void toQin(HttpServletResponse response){
    	 try {   
    		 	            // 返回成功标识   
    	                   response.setContentType("text/html; charset=utf-8");
    		 	            response.getWriter().println("{success:true}");   
    		 	           
    		 	            response.getWriter().flush();   
    		 	        } catch (IOException e) {   
    		 	            e.printStackTrace();   
    		 	        } finally {   
    		 	            try {   
    		 	                response.getWriter().close();   
    		             } catch (IOException e) {   
    		                 e.printStackTrace();   
    			            }   
    		         }   

   }	

    public void updateisCd(int isZtorcd, Date datekint,kaoqin qn, boolean isCdOrZt) {
    	  kaoqin qqn=qn;
    	 
    		  qqn.setIsCd(isZtorcd);
    		  qqn.setQdTime(datekint);
    	
    	  kaoqindao.update(qqn);
  	}
    public void updateisZt(int isZtorcd, Date datekout,kaoqin qn, boolean isCdOrZt) {
  	  kaoqin qqn=qn;
  	 
		  qqn.setIsZt(isZtorcd);
		  qqn.setQtTime(datekout); 
	 	  kaoqindao.update(qqn);
  	
 
	}
	   public void baoCun(int nn,Date ge,String userName,String personid, boolean isCdOrZt){
		   kaoqin kql=new kaoqin();
			 if(isCdOrZt){
				 kql.setIsCd(nn); 
				 kql.setQdTime(ge);
				 }
			 else{
				 kql.setIsZt(nn);
				 kql.setQtTime(ge);
			 }
			
			 kql.setUsername(userName);
			
		
			
			 kql.setPersonid(personid);
			
		   kaoqindao.save(kql);
		   
	   }
	   //查询已存入的签到数据的记录
	  public List<kaoqin> getRecord(String username,Date te ,String personid){
		
		  List<kaoqin> mk=null;
		  String time= KQUtil.getNeedCurrenty(te)+KQUtil.getNeedCurrentb(te);
		  String timestart=time+" 00:00:00";
		  String timeend=time+" 23:59:59";
		  StringBuffer hql = new StringBuffer();  
		  List<String> list = new ArrayList<String>();
		  hql.append("from kaoqin c  where 1=1 ");
		  if (username != null &&username.length() > 0) {
			  hql.append(" and c.username = ? ");
			  list.add( username );}
			if(personid!=null&&personid.length()>0){  
			  hql.append("and c.personid=?");
			  list.add(personid);
			  hql.append("and c.qdTime >= to_date(?,'yyyy-mm-dd hh24:mi:ss')");//获得今天的签dao记录
			 list.add(timestart);
			 hql.append("and c.qdTime <= to_date(?,'yyyy-mm-dd hh24:mi:ss')");
			 list.add(timeend);
		  }
			
		List<kaoqin>kq= kaoqindao.findByUsernname(hql.toString(), list);
		
		        if(kq.size()>0){
		        	
		        mk=kq;	
		    
		        }
		    
	
		        else{
		        	mk=null;
		        	
		        }
		
		return mk;
	  } 
	   public int getCount(String username, String startdate, String enddate){
		   StringBuffer hql = new StringBuffer();
			List<String> list = new ArrayList<String>();
			
					hql.append("select count(*) from kaoqin kq where ");
			if (username != null &&username.length() > 0) {
				hql.append(" kq.username like ?");
				list.add("%" + username + "%");
			}
			if (startdate != null && startdate.length() > 0) {
				
						hql.append(" and kq.qdTime >= to_date(?,'yyyy-mm-dd hh24:mi:ss')");
				list.add(startdate);
			}
			if (enddate != null && enddate.length() > 0) {
				
						hql.append(" and kq.qdTime <= to_date(?,'yyyy-mm-dd hh24:mi:ss')");
						
				
				list.add(enddate);
			
			} 
			return kaoqindao.getCountt(hql.toString(), list);//换成getCount0也行
		}
	   
	public List<kaoqin> findByTime(int start, int limit, String username,
			String startdate, String enddate) {
		StringBuffer hql = new StringBuffer();
		List<String> list = new ArrayList<String>();
		hql.append("from kaoqin kq  where" );
		if (username != null && username.length() > 0) {
			hql.append(" kq.username like ?");
			list.add("%" + username + "%");
		}
		if (startdate != null && startdate.length() > 0) {
			
					hql.append(" and kq.qdTime >= to_date(?,'yyyy-mm-dd hh24:mi:ss')");//签到时间 
			list.add(startdate);
		}
		if (enddate != null && enddate.length() > 0) {
			hql
					.append(" and kq.qdTime  <= to_date(?,'yyyy-mm-dd hh24:mi:ss')");
			list.add(enddate);
		}
		hql.append(" order by kq.qdTime DESC");
		return kaoqindao.findBytime(hql.toString(), start, limit, list);
	}
//早退次数
	public int getZtCount(String username, String startdate, String enddate) {
		StringBuffer hql = new StringBuffer();
		List<String> list = new ArrayList<String>();
		
				hql.append("select count(*) from kaoqin kq where ");
		if (username != null &&username.length() > 0) {
			hql.append(" kq.username like ?");
			list.add("%" + username + "%");
		}
		if (startdate != null && startdate.length() > 0) {
			
					hql.append(" and kq.qtTime >= to_date(?,'yyyy-mm-dd hh24:mi:ss')");
			list.add(startdate);
		}
		if (enddate != null && enddate.length() > 0) {
			
					hql.append(" and kq.qtTime<= to_date(?,'yyyy-mm-dd hh24:mi:ss')");
					
			
			list.add(enddate);
			hql.append(" and kq.isQd=0");
			
		} 
		return kaoqindao.getCountt(hql.toString(), list);
	}

	//迟到次数查询
	public int getcCdCount(String username, String startdate, String enddate) {
		
		StringBuffer hql = new StringBuffer();
		List<String> list = new ArrayList<String>();
		hql.append("select count(*) from kaoqin kq  where ");
		if (username != null &&username.length() > 0) {
			hql.append(" kq.username like ?");
			list.add("%" + username + "%");
		}
		if (startdate != null && startdate.length() > 0) {
			
					hql.append(" and kq.qdTime >= to_date(?,'yyyy-mm-dd hh24:mi:ss')");
			list.add(startdate);
		}
		if (enddate != null && enddate.length() > 0) {
			
					hql.append(" and kq.qdTime <= to_date(?,'yyyy-mm-dd hh24:mi:ss')");
					
			
			list.add(enddate);
			hql.append(" and kq.isCd=0");
		} 
		return kaoqindao.getCounto(hql.toString(), list);
	}
	
	
	
	

	private KaoQinDao kaoqindao; 

	public KaoQinDao getKaoqindao() {
		return kaoqindao;
	}

	public void setKaoqindao(KaoQinDao kaoqindao) {
		this.kaoqindao = kaoqindao;
	}


	
	
}
