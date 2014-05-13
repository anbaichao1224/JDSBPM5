package com.kzxd.kaoqin.service;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.jds.usm.persistence.model.Personaccount;
import com.kzxd.changepssword.entity.personaccount;
import com.kzxd.kaoqin.pojo.kaoqin;
import com.kzxd.kaoqin.pojo.persona;

public interface KaoQinService {
	
	    public void baoCun(int nn,Date ge,String userName,String personid,boolean isCdOrZt);

		public void toQin(HttpServletResponse response);

		public List<kaoqin> findByTime(int start, int limit, String username,
				String startdate, String enddate);

		public int getcCdCount(String username, String startdate, String enddate);
		public int getZtCount(String username, String startdate, String enddate);

		public int getCount(String username, String startdate, String enddate);

		public List<kaoqin> getRecord(String userName, Date date, String uuid);
		 public void updateisZt(int isZtorcd, Date datekout,kaoqin qn, boolean isCdOrZt);
		 public void updateisCd(int isZtorcd, Date datekint,kaoqin qn, boolean isCdOrZt) ;
		 public persona getperson(String personid);

		
}
