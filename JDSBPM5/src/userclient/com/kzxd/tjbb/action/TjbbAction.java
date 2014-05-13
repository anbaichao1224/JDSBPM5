
package com.kzxd.tjbb.action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.itjds.common.org.base.OrgManagerFactory;
import net.itjds.common.org.base.Person;

import org.apache.struts2.ServletActionContext;

import com.kzxd.tjbb.entity.DanWeiBean;
import com.kzxd.tjbb.entity.MingXiBean;
import com.kzxd.tjbb.entity.NeiRongBean;
import com.kzxd.tjbb.entity.ShouLiYeWuBean;
import com.kzxd.tjbb.entity.TjbbBean;
import com.kzxd.tjbb.entity.YueDuBean;
import com.kzxd.tjbb.service.TjbbMsg;
import com.opensymphony.xwork2.ActionSupport;

public class TjbbAction extends ActionSupport {
	
	private TjbbMsg tjMsgimpl;
	private TjbbBean tjbbBean;
	private List<TjbbBean> tjbblist;
	private List<TjbbBean> shangbaolist;
	private List<YueDuBean> yueduList;
	private List nianList;
	private List<MingXiBean> mingxiList;
	private List<DanWeiBean> danweilist;
	private List types;
	private MingXiBean mingxiBean;
	private YueDuBean yueduhejiBean;
	private YueDuBean nianduhejiBean;
	private ShouLiYeWuBean yuedushouliyewu;
	private ShouLiYeWuBean niandushouliyewu;
	private ShouLiYeWuBean niandushouliyewuBean;
	private List niandushouliyewuList;
	
	private String uuid;
	private String tjdw;
	private String sbdw;
	private String sbsx;
	private String zxjs;
	private String sljs;
	private String xmbllj;
	private String cnsj;
	private Date bjsj;
	private String zxsx;
	private Date sbsj;
	private String bjzt;
	
	private String start;
	private String limit;
	
	private String yuefen;
	private String bumen;
	
	private String zxdw;
	private Date sdate;
	private Date edate;
	private String niandu;
	
	private String updateuuid;
	
	public String execute() throws Exception {

		
		return "success";
	}
	
	public String findAllByTjdw(){
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response=ServletActionContext.getResponse();
		HttpSession session = request.getSession();
		String personId = session.getAttribute("personId").toString();
		
		try{
			bjzt="草稿";
			Person person = OrgManagerFactory.getOrgManager().getPersonByID(personId);
			String tjdwname=person.getName();
			int index=Integer.parseInt(start);
			int pageSize=Integer.parseInt(limit);
			if(zxdw==null){
				if(sdate==null){
					if(edate==null){
						tjbblist = tjMsgimpl.findAllByPersonid(personId,bjzt);						
					}
				}
			}else if(zxdw.equals("")){
					if(sdate==null){
						if(edate==null){
							tjbblist = tjMsgimpl.findAllByPersonid(personId,bjzt);
						}else{
							tjbblist = tjMsgimpl.findAllByPersonid7(personId, bjzt, edate);
						}
					}else{
						if(edate==null){
							tjbblist = tjMsgimpl.findAllByPersonid8(personId, bjzt, sdate);
						}else{
							tjbblist = tjMsgimpl.findAllByPersonid6(personId,bjzt, sdate, edate);
						}
					}
			}else{
				if(sdate==null){
					if(edate==null){
						tjbblist = tjMsgimpl.findAllByPersonid4(personId, bjzt, zxdw);
					}else{
						tjbblist = tjMsgimpl.findAllByPersonid5(personId, bjzt, zxdw, edate);
					}
				}else{
					if(edate==null){
						tjbblist = tjMsgimpl.findAllByPersonid3(personId, bjzt, zxdw, sdate);
					}else{
						tjbblist = tjMsgimpl.findAllByPersonid2(personId, bjzt, zxdw, sdate, edate);
					}
				}
			};

			int totalCount = tjbblist.size();
			String json="{totalProperty:"+totalCount+",root:[";
			if(totalCount<pageSize+index){
				for(int i=index;i<totalCount;i++){
					   json+="{sbdw:'"+tjbblist.get(i).getSbdw()+"',uuid:'"+tjbblist.get(i).getUuid()
					   +"',sbsx:'"+tjbblist.get(i).getSbsx()+"',sljs:'"+tjbblist.get(i).getSljs()
					   +"',xmbllj:'"+tjbblist.get(i).getXmbllj()+"',sbsj:'"+tjbblist.get(i).getSbsj()
					   +"',cnsj:'"+tjbblist.get(i).getCnsj()+"',bjsj:'"+tjbblist.get(i).getBjsj()+"'}";
						if(i!=(totalCount-1)){
							json+=",";
						}
				}
			}else{
				for(int i=index;i<pageSize+index;i++){
					json+="{sbdw:'"+tjbblist.get(i).getSbdw()+"',uuid:'"+tjbblist.get(i).getUuid()
					   +"',sbsx:'"+tjbblist.get(i).getSbsx()+"',sljs:'"+tjbblist.get(i).getSljs()
					   +"',xmbllj:'"+tjbblist.get(i).getXmbllj()+"',sbsj:'"+tjbblist.get(i).getSbsj()
					   +"',cnsj:'"+tjbblist.get(i).getCnsj()+"',bjsj:'"+tjbblist.get(i).getBjsj()+"'}";
				   if(i!=(pageSize+index-1)){
					   json+=",";
				   }
				}
			}
			json+="]}";
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().write(json);
			}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	public String FindAllHuiZong(){
		HttpServletResponse response=ServletActionContext.getResponse();
		try{
			bjzt="上报";
			int index=Integer.parseInt(start);
			int pageSize=Integer.parseInt(limit);
			if(tjdw==null){
				if(sdate==null){
					if(edate==null){
						tjbblist = tjMsgimpl.findAllByPersonid12(bjzt);						
					}
				}
			}else if(tjdw.equals("")){
					if(sdate==null){
						if(edate==null){
							tjbblist = tjMsgimpl.findAllByPersonid12(bjzt);
						}else{
							tjbblist = tjMsgimpl.findAllByPersonid72(bjzt, edate);
						}
					}else{
						if(edate==null){
							tjbblist = tjMsgimpl.findAllByPersonid82(bjzt, sdate);
						}else{
							tjbblist = tjMsgimpl.findAllByPersonid62(bjzt, sdate, edate);
						}
					}
			}else{
				if(sdate==null){
					if(edate==null){
						tjbblist = tjMsgimpl.findAllByPersonid42(bjzt, tjdw);
					}else{
						tjbblist = tjMsgimpl.findAllByPersonid52(bjzt, tjdw, edate);
					}
				}else{
					if(edate==null){
						tjbblist = tjMsgimpl.findAllByPersonid32(bjzt, tjdw, sdate);
					}else{
						tjbblist = tjMsgimpl.findAllByPersonid22(bjzt, tjdw, sdate, edate);
					}
				}
			};

			int totalCount = tjbblist.size();
			String json="{totalProperty:"+totalCount+",root:[";
			if(totalCount<pageSize+index){
				for(int i=index;i<totalCount;i++){
					   json+="{sbdw:'"+tjbblist.get(i).getSbdw()+"',tjdw:'"+tjbblist.get(i).getTjdw()+"',uuid:'"+tjbblist.get(i).getUuid()
					   +"',sbsx:'"+tjbblist.get(i).getSbsx()+"',sljs:'"+tjbblist.get(i).getSljs()
					   +"',xmbllj:'"+tjbblist.get(i).getXmbllj()+"',sbsj:'"+tjbblist.get(i).getSbsj()
					   +"',cnsj:'"+tjbblist.get(i).getCnsj()+"',bjsj:'"+tjbblist.get(i).getBjsj()+"'}";
						if(i!=(totalCount-1)){
							json+=",";
						}
				}
			}else{
				for(int i=index;i<pageSize+index;i++){
					json+="{sbdw:'"+tjbblist.get(i).getSbdw()+"',tjdw:'"+tjbblist.get(i).getTjdw()+"',uuid:'"+tjbblist.get(i).getUuid()
					   +"',sbsx:'"+tjbblist.get(i).getSbsx()+"',sljs:'"+tjbblist.get(i).getSljs()
					   +"',xmbllj:'"+tjbblist.get(i).getXmbllj()+"',sbsj:'"+tjbblist.get(i).getSbsj()
					   +"',cnsj:'"+tjbblist.get(i).getCnsj()+"',bjsj:'"+tjbblist.get(i).getBjsj()+"'}";
				   if(i!=(pageSize+index-1)){
					   json+=",";
				   }
				}
			}
			json+="]}";
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().write(json);

			}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	public String HuoQuChuangKouXiaLa(){
		HttpServletResponse response=ServletActionContext.getResponse();
		try{
			danweilist= tjMsgimpl.danWeiXiaLa();
			int totalCount2 = danweilist.size();
			String json2="{root2:[";
				for(int i=0;i<totalCount2;i++){
						json2+="{value:'"+danweilist.get(i).getPersonid()+"',text:'"+danweilist.get(i).getDw()
					   		+"'}";
						if(i!=(totalCount2-1)){
							json2+=",";
						}
				}
			json2+="]}";
			
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().write(json2);
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	public String FindAllByTjdwShangBao(){
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response=ServletActionContext.getResponse();
		HttpSession session = request.getSession();
		String personId = session.getAttribute("personId").toString();
		
		try{
			bjzt="上报";
			Person person = OrgManagerFactory.getOrgManager().getPersonByID(personId);
			String tjdwname=person.getName();
			int index=Integer.parseInt(start);
			int pageSize=Integer.parseInt(limit);
			if(zxdw==null){
				if(sdate==null){
					if(edate==null){
						tjbblist = tjMsgimpl.findAllByPersonid(personId,bjzt);						
					}
				}
			}else if(zxdw.equals("")){
					if(sdate==null){
						if(edate==null){
							tjbblist = tjMsgimpl.findAllByPersonid(personId,bjzt);
						}else{
							tjbblist = tjMsgimpl.findAllByPersonid7(personId, bjzt, edate);
						}
					}else{
						if(edate==null){
							tjbblist = tjMsgimpl.findAllByPersonid8(personId, bjzt, sdate);
						}else{
							tjbblist = tjMsgimpl.findAllByPersonid6(personId,bjzt, sdate, edate);
						}
					}
			}else{
				if(sdate==null){
					if(edate==null){
						tjbblist = tjMsgimpl.findAllByPersonid4(personId, bjzt, zxdw);
					}else{
						tjbblist = tjMsgimpl.findAllByPersonid5(personId, bjzt, zxdw, edate);
					}
				}else{
					if(edate==null){
						tjbblist = tjMsgimpl.findAllByPersonid3(personId, bjzt, zxdw, sdate);
					}else{
						tjbblist = tjMsgimpl.findAllByPersonid2(personId, bjzt, zxdw, sdate, edate);
					}
				}
			};			
			int totalCount = tjbblist.size();
			String json="{totalProperty:"+totalCount+",root:[";
			if(totalCount<pageSize+index){
				for(int i=index;i<totalCount;i++){
					   json+="{sbdw:'"+tjbblist.get(i).getSbdw()+"',uuid:'"+tjbblist.get(i).getUuid()
					   +"',sbsx:'"+tjbblist.get(i).getSbsx()+"',sljs:'"+tjbblist.get(i).getSljs()
					   +"',xmbllj:'"+tjbblist.get(i).getXmbllj()+"',sbsj:'"+tjbblist.get(i).getSbsj()
					   +"',cnsj:'"+tjbblist.get(i).getCnsj()+"',bjsj:'"+tjbblist.get(i).getBjsj()+"'}";
						if(i!=(totalCount-1)){
							json+=",";
						}
				}
			}else{
				for(int i=index;i<pageSize+index;i++){
					json+="{sbdw:'"+tjbblist.get(i).getSbdw()+"',uuid:'"+tjbblist.get(i).getUuid()
					   +"',sbsx:'"+tjbblist.get(i).getSbsx()+"',sljs:'"+tjbblist.get(i).getSljs()
					   +"',xmbllj:'"+tjbblist.get(i).getXmbllj()+"',sbsj:'"+tjbblist.get(i).getSbsj()
					   +"',cnsj:'"+tjbblist.get(i).getCnsj()+"',bjsj:'"+tjbblist.get(i).getBjsj()+"'}";
				   if(i!=(pageSize+index-1)){
					   json+=",";
				   }
				}
			}
			json+="]}";
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().write(json);
			}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	public String FindAllByTjdwZiXun(){
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response=ServletActionContext.getResponse();
		HttpSession session = request.getSession();
		String personId = session.getAttribute("personId").toString();
		
		try{
			Person person = OrgManagerFactory.getOrgManager().getPersonByID(personId);
			String tjdwname=person.getName();
			int index=Integer.parseInt(start);
			int pageSize=Integer.parseInt(limit);
			
			shangbaolist=tjMsgimpl.ziXunFindAllByPersonid(personId);
			
			int totalCount = shangbaolist.size();
			String json="{totalProperty:"+totalCount+",root:[";
			if(totalCount<pageSize+index){
				for(int i=index;i<totalCount;i++){
					   json+="{sbdw:'"+shangbaolist.get(i).getSbdw()+"',uuid:'"+shangbaolist.get(i).getUuid()
					   +"',zxsx:'"+shangbaolist.get(i).getZxsx()+"',zxjs:'"+shangbaolist.get(i).getZxjs()
					   +"',sbsj:'"+shangbaolist.get(i).getSbsj()+"'}";
						if(i!=(totalCount-1)){
							json+=",";
						}
				}
			}else{
				for(int i=index;i<pageSize+index;i++){
					   json+="{sbdw:'"+shangbaolist.get(i).getSbdw()+"',uuid:'"+shangbaolist.get(i).getUuid()
					   +"',zxsx:'"+shangbaolist.get(i).getZxsx()+"',zxjs:'"+shangbaolist.get(i).getZxjs()
					   +"',sbsj:'"+shangbaolist.get(i).getSbsj()+"'}";
				   if(i!=(pageSize+index-1)){
					   json+=",";
				   }
				}
			}
			json+="]}";
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().write(json);
			}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	public String FindAllByTjdwZiXunHuiZong(){
		HttpServletResponse response=ServletActionContext.getResponse();
		
		try{
			int index=Integer.parseInt(start);
			int pageSize=Integer.parseInt(limit);
			
			shangbaolist=tjMsgimpl.ziXunHuiZongFindAllByTjdw();
			
			int totalCount = shangbaolist.size();
			String json="{totalProperty:"+totalCount+",root:[";
			if(totalCount<pageSize+index){
				for(int i=index;i<totalCount;i++){
					   json+="{sbdw:'"+shangbaolist.get(i).getSbdw()+"',uuid:'"+shangbaolist.get(i).getUuid()
					   +"',zxsx:'"+shangbaolist.get(i).getZxsx()+"',zxjs:'"+shangbaolist.get(i).getZxjs()
					   +"',sbsj:'"+shangbaolist.get(i).getSbsj()+"',tjdw:'"+shangbaolist.get(i).getTjdw()+"'}";
						if(i!=(totalCount-1)){
							json+=",";
						}
				}
			}else{
				for(int i=index;i<pageSize+index;i++){
					   json+="{sbdw:'"+shangbaolist.get(i).getSbdw()+"',uuid:'"+shangbaolist.get(i).getUuid()
					   +"',zxsx:'"+shangbaolist.get(i).getZxsx()+"',zxjs:'"+shangbaolist.get(i).getZxjs()
					   +"',sbsj:'"+shangbaolist.get(i).getSbsj()+"',tjdw:'"+shangbaolist.get(i).getTjdw()+"'}";
				   if(i!=(pageSize+index-1)){
					   json+=",";
				   }
				}
			}
			json+="]}";
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().write(json);
			}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	public String add(){
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		String personId = session.getAttribute("personId").toString();
		bjzt="上报";
		TjbbBean tjb=new TjbbBean();
		tjb.setTjdw(tjdw);
		tjb.setSbdw(sbdw);
		tjb.setSbsx(sbsx);
		tjb.setSljs(sljs);
		tjb.setXmbllj(xmbllj);
		tjb.setCnsj(cnsj);
		tjb.setBjsj(bjsj);
		tjb.setSbsj(sbsj);
		tjb.setBjzt(bjzt);
		tjb.setPersonid(personId);
		try{
			tjMsgimpl.add(tjb);
			 return "success";
		}catch(Exception e){
			e.printStackTrace();
			return "error";
		}
	}
	
	public String add2(){
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		String personId = session.getAttribute("personId").toString();
		TjbbBean tjb=new TjbbBean();
		tjb.setTjdw(tjdw);
		tjb.setSbdw(sbdw);
		tjb.setZxsx(zxsx);
		tjb.setZxjs(zxjs);
		tjb.setSbsj(sbsj);
		tjb.setPersonid(personId);
		try{
			tjMsgimpl.add(tjb);
			 return "success";
		}catch(Exception e){
			e.printStackTrace();
			return "error";
		}
	}
	
	public String save(){
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		String personId = session.getAttribute("personId").toString();
		bjzt="草稿";
		TjbbBean tjb=new TjbbBean();
		tjb.setTjdw(tjdw);
		tjb.setSbdw(sbdw);
		tjb.setSbsx(sbsx);
		tjb.setSljs(sljs);
		tjb.setXmbllj(xmbllj);
		tjb.setCnsj(cnsj);
		tjb.setBjsj(bjsj);
		tjb.setSbsj(sbsj);
		tjb.setBjzt(bjzt);
		tjb.setPersonid(personId);
		try{
			tjMsgimpl.add(tjb);
			 return "success";
		}catch(Exception e){
			e.printStackTrace();
			return "error";
		}
	}
	
	public String updateTjbbByUuid(){
		HttpServletResponse response=ServletActionContext.getResponse();
		try{
			tjbbBean = tjMsgimpl.getByUuid(uuid);
			tjbbBean.setSbdw(sbdw);
			tjbbBean.setSbsx(sbsx);
			tjbbBean.setSljs(sljs);
			tjbbBean.setXmbllj(xmbllj);
			tjbbBean.setSbsj(sbsj);
			tjbbBean.setCnsj(cnsj);
			tjbbBean.setBjsj(bjsj);
			tjMsgimpl.updateTjbbByUuid(tjbbBean);
			 response.getWriter().print("{success:true}");
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
		
	}
	
	public String findAllByYue(){
		Date date=new Date();
		int y=date.getYear();
		int year=y+1900;
		String nian=year+"";
		String starttime=nian+"-"+"01"+"-"+"01";
		Date nst=formatDate(starttime);
		yueduList=tjMsgimpl.findAllByYue(sdate, edate, nst);
		for(int i=0;i<yueduList.size();i++){
			String personid=yueduList.get(i).getPersonid();
			MingXiBean mxBean =tjMsgimpl.findByPersonid(personid);
			yueduList.get(i).setTjdw(mxBean.getDw());
		}
		yueduhejiBean=tjMsgimpl.yueDuHeJi(sdate, edate, nst);
		
		return "success";
	}
	
	public String findAllByNian(){
		String nian=niandu;
		String yiyuestarttime=nian+"-"+"01"+"-"+"01";
		Date yiyst=formatDate(yiyuestarttime);
		String yiyueendtime=nian+"-"+"01"+"-"+"31";
		Date yiyet=formatDate(yiyueendtime);
		String eryuestarttime=nian+"-"+"01"+"-"+"31";
		Date eryst=formatDate(eryuestarttime);
		String eryueendtime=nian+"-"+"03"+"-"+"01";
		Date eryet=formatDate(eryueendtime);
		String sanyuestarttime=nian+"-"+"03"+"-"+"01";
		Date sanyst=formatDate(sanyuestarttime);
		String sanyueendtime=nian+"-"+"03"+"-"+"31";
		Date sanyet=formatDate(sanyueendtime);
		String siyuestarttime=nian+"-"+"04"+"-"+"01";
		Date siyst=formatDate(siyuestarttime);
		String siyueendtime=nian+"-"+"04"+"-"+"30";
		Date siyet=formatDate(siyueendtime);
		String wuyuestarttime=nian+"-"+"05"+"-"+"01";
		Date wuyst=formatDate(wuyuestarttime);
		String wuyueendtime=nian+"-"+"05"+"-"+"31";
		Date wuyet=formatDate(wuyueendtime);
		String liuyuestarttime=nian+"-"+"06"+"-"+"01";
		Date liuyst=formatDate(liuyuestarttime);
		String liuyueendtime=nian+"-"+"06"+"-"+"30";
		Date liuyet=formatDate(liuyueendtime);
		String qiyuestarttime=nian+"-"+"07"+"-"+"01";
		Date qiyst=formatDate(qiyuestarttime);
		String qiyueendtime=nian+"-"+"07"+"-"+"31";
		Date qiyet=formatDate(qiyueendtime);
		String bayuestarttime=nian+"-"+"08"+"-"+"01";
		Date bayst=formatDate(bayuestarttime);
		String bayueendtime=nian+"-"+"08"+"-"+"31";
		Date bayet=formatDate(bayueendtime);
		String jiuyuestarttime=nian+"-"+"09"+"-"+"01";
		Date jiuyst=formatDate(jiuyuestarttime);
		String jiuyueendtime=nian+"-"+"09"+"-"+"30";
		Date jiuyet=formatDate(jiuyueendtime);
		String shiyuestarttime=nian+"-"+"10"+"-"+"01";
		Date shiyst=formatDate(shiyuestarttime);
		String shiyueendtime=nian+"-"+"10"+"-"+"31";
		Date shiyet=formatDate(shiyueendtime);
		String shiyiyuestarttime=nian+"-"+"11"+"-"+"01";
		Date shiyiyst=formatDate(shiyiyuestarttime);
		String shiyiyueendtime=nian+"-"+"11"+"-"+"30";
		Date shiyiyet=formatDate(shiyiyueendtime);
		String shieryuestarttime=nian+"-"+"12"+"-"+"01";
		Date shieryst=formatDate(shieryuestarttime);
		String shieryueendtime=nian+"-"+"12"+"-"+"31";
		Date shieryet=formatDate(shieryueendtime);

		YueDuBean yuedubean1=tjMsgimpl.findAllByNian(bumen, yiyet, yiyst);
		YueDuBean yuedubean2=tjMsgimpl.findAllByNian2(bumen, eryet, eryst);
		YueDuBean yuedubean3=tjMsgimpl.findAllByNian(bumen, sanyet, sanyst);
		YueDuBean yuedubean4=tjMsgimpl.findAllByNian(bumen, siyet, siyst);
		YueDuBean yuedubean5=tjMsgimpl.findAllByNian(bumen, wuyet, wuyst);
		YueDuBean yuedubean6=tjMsgimpl.findAllByNian(bumen, liuyet, liuyst);
		YueDuBean yuedubean7=tjMsgimpl.findAllByNian(bumen, qiyet, qiyst);
		YueDuBean yuedubean8=tjMsgimpl.findAllByNian(bumen, bayet, bayst);
		YueDuBean yuedubean9=tjMsgimpl.findAllByNian(bumen, jiuyet, jiuyst);
		YueDuBean yuedubean10=tjMsgimpl.findAllByNian(bumen, shiyet, shiyst);
		YueDuBean yuedubean11=tjMsgimpl.findAllByNian(bumen, shiyiyet, shiyiyst);
		YueDuBean yuedubean12=tjMsgimpl.findAllByNian(bumen, shieryet, shieryst);

		nianList=new ArrayList();
		
		nianList.add(yuedubean1);
		nianList.add(yuedubean2);
		nianList.add(yuedubean3);
		nianList.add(yuedubean4);
		nianList.add(yuedubean5);
		nianList.add(yuedubean6);
		nianList.add(yuedubean7);
		nianList.add(yuedubean8);
		nianList.add(yuedubean9);
		nianList.add(yuedubean10);
		nianList.add(yuedubean11);
		nianList.add(yuedubean12);
		nianduhejiBean=tjMsgimpl.nianDuHeJi(bumen, yiyst, shieryet);
		return "success";
	}
	
	public String shangBaoTjbb(){
		HttpServletResponse response=ServletActionContext.getResponse();
		String updateid[]=updateuuid.split(",");
		bjzt="上报";
		try{
			for(int i=0;i<updateid.length;i++){
				String id=updateid[i];
				TjbbBean bean=tjMsgimpl.getByUuid(id);
				bean.setBjzt(bjzt);
				tjMsgimpl.updateTjbbByUuid(bean);
			}
		response.getWriter().print("{success:true}");
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	public String tuiHuiTjbb(){
		HttpServletResponse response=ServletActionContext.getResponse();
		String updateid[]=updateuuid.split(",");
		bjzt="草稿";
		try{
			for(int i=0;i<updateid.length;i++){
				String id=updateid[i];
				TjbbBean bean=tjMsgimpl.getByUuid(id);
				bean.setBjzt(bjzt);
				tjMsgimpl.updateTjbbByUuid(bean);
			}
		response.getWriter().print("{success:true}");
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	public String chuangKouMingXiTJ(){

		mingxiList=tjMsgimpl.findMingXi(sdate, edate);
		List<NeiRongBean> jdnrList=tjMsgimpl.findMingXiJdnr(sdate, edate);
		List<NeiRongBean> bjnrList=tjMsgimpl.findMingXiBjnr(sdate, edate);
		mingxiBean=tjMsgimpl.zongJiMingXi(sdate, edate);
		
		for(int i=0;i<mingxiList.size();i++){
			List jieDanNeiRong=new ArrayList();
			for(int j=0;j<jdnrList.size();j++){
				if(mingxiList.get(i).getPersonid().equals(jdnrList.get(j).getPersonid())){
					if(jdnrList.get(j).getSx()!=null){
						jieDanNeiRong.add(jdnrList.get(j).getSx()+"："+jdnrList.get(j).getSl());
					}
				}
			}
			if(jieDanNeiRong.size()>0){
				int t=jieDanNeiRong.size();
				String[] jdnr = new String[t];
				for(int n=0;n<t;n++){
					String aaa=(String) jieDanNeiRong.get(n);
					jdnr[n]=aaa;
				}
				mingxiList.get(i).setJdnr(jdnr);
			}
		}
		
		for(int i=0;i<mingxiList.size();i++){
			List banJieNeiRong=new ArrayList();
			for(int j=0;j<bjnrList.size();j++){
				if(mingxiList.get(i).getPersonid().equals(bjnrList.get(j).getPersonid())){
					if(bjnrList.get(j).getSx()!=null){
						banJieNeiRong.add(bjnrList.get(j).getSx()+"："+bjnrList.get(j).getSl());
					}
				}
			}
			if(banJieNeiRong.size()>0){
				int t=banJieNeiRong.size();
				String[] bjnr = new String[t];
				for(int n=0;n<t;n++){
					String aaa=(String) banJieNeiRong.get(n);
					bjnr[n]=aaa;
				}
				mingxiList.get(i).setBjnr(bjnr);
			}
		}
		for(int i=0;i<mingxiList.size();i++){
			String personid=mingxiList.get(i).getPersonid();
			MingXiBean mxBean =tjMsgimpl.findByPersonid(personid);
			mingxiList.get(i).setDw(mxBean.getDw());
		}
		return "success";		
	}
	
	public String DanWeiXiaLa(){
		types= new ArrayList();
		try{
			danweilist= tjMsgimpl.danWeiXiaLa();
			for(int i=0;i<danweilist.size();i++){
				String danwei=danweilist.get(i).getDw();
				String personid=danweilist.get(i).getPersonid();
				CodeName type= new CodeName(personid,danwei);
				types.add(type);
			}

		}catch(Exception e){
			e.printStackTrace();
		}
		return "success";
		
	}
	
	public String YueDuShouLiYeWu(){
		yuedushouliyewu=tjMsgimpl.yueDuYeWu(sdate, edate);
		
		mingxiList=tjMsgimpl.findMingXi(sdate, edate);
		List<NeiRongBean> bjnrList=tjMsgimpl.findMingXiBjnr(sdate, edate);
		for(int i=0;i<mingxiList.size();i++){
			List banJieNeiRong=new ArrayList();
			for(int j=0;j<bjnrList.size();j++){
				if(mingxiList.get(i).getPersonid().equals(bjnrList.get(j).getPersonid())){
					if(bjnrList.get(j).getSx()!=null){
						banJieNeiRong.add(bjnrList.get(j).getSx()+"："+bjnrList.get(j).getSl());
					}
				}
			}
			if(banJieNeiRong.size()>0){
				int t=banJieNeiRong.size();
				String[] bjnr = new String[t];
				for(int n=0;n<t;n++){
					String aaa=(String) banJieNeiRong.get(n);
					bjnr[n]=aaa;
				}
				mingxiList.get(i).setBjnr(bjnr);
			}
		}
		for(int i=0;i<mingxiList.size();i++){
			String personid=mingxiList.get(i).getPersonid();
			MingXiBean mxBean =tjMsgimpl.findByPersonid(personid);
			mingxiList.get(i).setDw(mxBean.getDw());
		}
		return "success";
	}
	
	public String NianDuShouLiYeWu(){
		Date yst=sdate;
		Date yet=edate;
		int startNian=sdate.getYear();
		int endNian=edate.getYear();
		niandushouliyewuList=new ArrayList();
		
		for(int i=startNian;i<endNian+1;i++){
			String nstime=i+"-01-01";
			Date nst=formatDate(nstime);
			String netime=i+"-12-31";
			Date net=formatDate(netime);
			if(i==startNian){
				niandushouliyewu=tjMsgimpl.nianDuYeWu(yst, net);
			}else if(i==endNian){
				niandushouliyewu=tjMsgimpl.nianDuYeWu(nst, yet);
			}else{
				niandushouliyewu=tjMsgimpl.nianDuYeWu(nst, net);
			}
			niandushouliyewu.setNian(i+1900);
			
			niandushouliyewuList.add(niandushouliyewu);
		}
		
		niandushouliyewuBean=tjMsgimpl.nianDuYeWu(yst, yet);
		
		return "success";
	}
	
	private Date formatDate(String date){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			return sdf.parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
	public List<TjbbBean> getTjbblist() {
		return tjbblist;
	}

	public void setTjbblist(List<TjbbBean> tjbblist) {
		this.tjbblist = tjbblist;
	}

	public TjbbMsg getTjMsgimpl() {
		return tjMsgimpl;
	}

	public void setTjMsgimpl(TjbbMsg tjMsgimpl) {
		this.tjMsgimpl = tjMsgimpl;
	}

	public TjbbBean getTjbbBean() {
		return tjbbBean;
	}

	public void setTjbbBean(TjbbBean tjbbBean) {
		this.tjbbBean = tjbbBean;
	}

	public String getTjdw() {
		return tjdw;
	}

	public void setTjdw(String tjdw) {
		this.tjdw = tjdw;
	}

	public String getSbdw() {
		return sbdw;
	}

	public void setSbdw(String sbdw) {
		this.sbdw = sbdw;
	}

	public String getSbsx() {
		return sbsx;
	}

	public void setSbsx(String sbsx) {
		this.sbsx = sbsx;
	}

	public String getZxjs() {
		return zxjs;
	}

	public void setZxjs(String zxjs) {
		this.zxjs = zxjs;
	}

	public String getSljs() {
		return sljs;
	}

	public void setSljs(String sljs) {
		this.sljs = sljs;
	}

	public String getXmbllj() {
		return xmbllj;
	}

	public void setXmbllj(String xmbllj) {
		this.xmbllj = xmbllj;
	}
	

	public String getCnsj() {
		return cnsj;
	}

	public void setCnsj(String cnsj) {
		this.cnsj = cnsj;
	}

	public Date getBjsj() {
		return bjsj;
	}

	public void setBjsj(Date bjsj) {
		this.bjsj = bjsj;
	}

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getLimit() {
		return limit;
	}

	public void setLimit(String limit) {
		this.limit = limit;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getYuefen() {
		return yuefen;
	}

	public void setYuefen(String yuefen) {
		this.yuefen = yuefen;
	}

	public String getZxdw() {
		return zxdw;
	}

	public void setZxdw(String zxdw) {
		this.zxdw = zxdw;
	}

	public List getYueduList() {
		return yueduList;
	}

	public void setYueduList(List yueduList) {
		this.yueduList = yueduList;
	}

	public String getBumen() {
		return bumen;
	}

	public void setBumen(String bumen) {
		this.bumen = bumen;
	}

	public List getNianList() {
		return nianList;
	}

	public void setNianList(List nianList) {
		this.nianList = nianList;
	}

	public String getZxsx() {
		return zxsx;
	}

	public void setZxsx(String zxsx) {
		this.zxsx = zxsx;
	}

	public Date getSbsj() {
		return sbsj;
	}

	public void setSbsj(Date sbsj) {
		this.sbsj = sbsj;
	}

	public String getBjzt() {
		return bjzt;
	}

	public void setBjzt(String bjzt) {
		this.bjzt = bjzt;
	}

	public Date getSdate() {
		return sdate;
	}

	public void setSdate(Date sdate) {
		this.sdate = sdate;
	}

	public Date getEdate() {
		return edate;
	}

	public void setEdate(Date edate) {
		this.edate = edate;
	}

	public String getUpdateuuid() {
		return updateuuid;
	}

	public void setUpdateuuid(String updateuuid) {
		this.updateuuid = updateuuid;
	}

	public String getNiandu() {
		return niandu;
	}

	public void setNiandu(String niandu) {
		this.niandu = niandu;
	}

	public List getTypes() {
		return types;
	}

	public void setTypes(List types) {
		this.types = types;
	}

	public List<DanWeiBean> getDanweilist() {
		return danweilist;
	}

	public void setDanweilist(List<DanWeiBean> danweilist) {
		this.danweilist = danweilist;
	}

	public MingXiBean getMingxiBean() {
		return mingxiBean;
	}

	public void setMingxiBean(MingXiBean mingxiBean) {
		this.mingxiBean = mingxiBean;
	}

	public List<MingXiBean> getMingxiList() {
		return mingxiList;
	}

	public void setMingxiList(List<MingXiBean> mingxiList) {
		this.mingxiList = mingxiList;
	}

	public YueDuBean getYueduhejiBean() {
		return yueduhejiBean;
	}

	public void setYueduhejiBean(YueDuBean yueduhejiBean) {
		this.yueduhejiBean = yueduhejiBean;
	}

	public YueDuBean getNianduhejiBean() {
		return nianduhejiBean;
	}

	public void setNianduhejiBean(YueDuBean nianduhejiBean) {
		this.nianduhejiBean = nianduhejiBean;
	}

	public ShouLiYeWuBean getYuedushouliyewu() {
		return yuedushouliyewu;
	}

	public void setYuedushouliyewu(ShouLiYeWuBean yuedushouliyewu) {
		this.yuedushouliyewu = yuedushouliyewu;
	}

	public ShouLiYeWuBean getNiandushouliyewu() {
		return niandushouliyewu;
	}

	public void setNiandushouliyewu(ShouLiYeWuBean niandushouliyewu) {
		this.niandushouliyewu = niandushouliyewu;
	}

	public List getNiandushouliyewuList() {
		return niandushouliyewuList;
	}

	public void setNiandushouliyewuList(List niandushouliyewuList) {
		this.niandushouliyewuList = niandushouliyewuList;
	}

	public ShouLiYeWuBean getNiandushouliyewuBean() {
		return niandushouliyewuBean;
	}

	public void setNiandushouliyewuBean(ShouLiYeWuBean niandushouliyewuBean) {
		this.niandushouliyewuBean = niandushouliyewuBean;
	}

	public List<TjbbBean> getShangbaolist() {
		return shangbaolist;
	}

	public void setShangbaolist(List<TjbbBean> shangbaolist) {
		this.shangbaolist = shangbaolist;
	}

}
