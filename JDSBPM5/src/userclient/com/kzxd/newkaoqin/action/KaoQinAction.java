package com.kzxd.newkaoqin.action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.itjds.common.org.base.OrgManagerFactory;
import net.itjds.common.org.base.Person;

import org.apache.struts2.ServletActionContext;

import com.kzxd.newkaoqin.entity.ChuangKouTianShuBean;
import com.kzxd.newkaoqin.entity.GzrdyBean;
import com.kzxd.newkaoqin.entity.KaoQinBaoBiaoBean;
import com.kzxd.newkaoqin.entity.KaoQinBean;
import com.kzxd.newkaoqin.entity.KaoQinShiJianBean;
import com.kzxd.newkaoqin.service.GzrdyMsg;
import com.kzxd.newkaoqin.service.KaoQinMsg;
import com.kzxd.newkaoqin.service.KaoQinShiJianMsg;
import com.kzxd.newkaoqin.service.QingJiaMingXiMsg;

public class KaoQinAction {

	private KaoQinMsg kqMsgImpl;
	private KaoQinShiJianMsg kqsjMsgImpl;
	private GzrdyMsg gzrdyMsgImpl;
	private QingJiaMingXiMsg qjmxMsgImpl;
	
	private KaoQinShiJianBean kqsjsdBean;
	private KaoQinBean kqBean;
	private KaoQinBaoBiaoBean kqbbBean;
	
	private List<KaoQinBean> kqList;
	private List<KaoQinShiJianBean> kqsjList;
	private List<KaoQinBaoBiaoBean> kaoqinbaobiaoList;
	
	private String start;
	private String limit;
	
	//查询条件
	private Date sdate;
	private Date edate;
	private String stime;
	private String etime;
	private String kqzt;
	
	//时间设定
	private Date ksrq;
	private Date jsrq;
	private String swqdsj;
	private String swqtsj;
	private String xwqdsj;
	private String xwqtsj;
	
	//考勤
	private String zt;
	private String bz;
	
	private String uuid;
	
	public String kaoQinTimeSheDingAdd(){
		Date newswqdsj=formatDate("2012-06-10 "+swqdsj);
		Date newswqtsj=formatDate("2012-06-10 "+swqtsj);
		Date newxwqdsj=formatDate("2012-06-10 "+xwqdsj);
		Date newxwqtsj=formatDate("2012-06-10 "+xwqtsj);
		try{
			KaoQinShiJianBean kqsjBean1=kqsjMsgImpl.getByRq(ksrq);
			KaoQinShiJianBean kqsjBean2=kqsjMsgImpl.getByRq(jsrq);
			
			if(kqsjBean1==null && kqsjBean2==null){
				KaoQinShiJianBean kqsjBean=new KaoQinShiJianBean();
				kqsjBean.setKsrq(ksrq);
				kqsjBean.setJsrq(jsrq);		
				kqsjBean.setSwqdsj(newswqdsj);
				kqsjBean.setSwqtsj(newswqtsj);
				kqsjBean.setXwqdsj(newxwqdsj);
				kqsjBean.setXwqtsj(newxwqtsj);
			
				kqsjMsgImpl.add(kqsjBean);
				return "success";
			}else{
				return "shedingyichang";
			}
		}catch(Exception e){
			e.printStackTrace();
			return "error";
		}	
		
	}
	
	public String KaoQinSave1(){
		
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		String personId = session.getAttribute("personId").toString();
		try{
			Person person = OrgManagerFactory.getOrgManager().getPersonByID(personId);
			String ck=person.getName();
			Date newTime=new Date();			
			String KaoQinTime="";
			KaoQinTime=(newTime.getYear()+1900)+"-"+(newTime.getMonth()+1)+"-"+newTime.getDate()+
						" "+newTime.getHours()+":"+newTime.getMinutes()+":"+newTime.getSeconds();
			Date rq=formatDate2(KaoQinTime);			
			String czlx="签到";
			String sjlx="上午";
			Date czsj=formatDate(KaoQinTime);
			
			kqBean=kqMsgImpl.findByRq(personId,rq, czlx, sjlx);
			if(kqBean==null){
				int Hours=czsj.getHours();
				int Min=czsj.getMinutes();
				int Sec=czsj.getSeconds();
				int total1=Hours*3600+Min*60+Sec;
				
				kqsjsdBean=kqsjMsgImpl.getByRq(rq);
				Date getswqdsj=kqsjsdBean.getSwqdsj();
				int Hours2=getswqdsj.getHours();
				int Min2=getswqdsj.getMinutes();
				int Sec2=getswqdsj.getSeconds();
				int total2=Hours2*3600+Min2*60+Sec2;
				
				Date getswqtsj=kqsjsdBean.getSwqtsj();
				int Hours3=getswqtsj.getHours();
				int Min3=getswqtsj.getMinutes();
				int Sec3=getswqtsj.getSeconds();
				int total3=Hours3*3600+Min3*60+Sec3;
				
				if(total1<=total2){
					String zt="正常";
					kqBean = new KaoQinBean();
					kqBean.setCk(ck);
					kqBean.setRq(rq);
					kqBean.setCzlx(czlx);
					kqBean.setSjlx(sjlx);
					kqBean.setCzsj(czsj);
					kqBean.setZt(zt);
					kqBean.setSjdx(total1);
					kqBean.setPersonid(personId);
					kqMsgImpl.add(kqBean);
				}else if(total1>total3){
					return "bushishangwuqiandaoshijian";
				}else{
					String zt="迟到";
					kqBean = new KaoQinBean();
					kqBean.setCk(ck);
					kqBean.setRq(rq);
					kqBean.setCzlx(czlx);
					kqBean.setSjlx(sjlx);
					kqBean.setCzsj(czsj);
					kqBean.setZt(zt);
					kqBean.setSjdx(total1);
					kqBean.setPersonid(personId);
					kqMsgImpl.add(kqBean);
				}
				return "success";
			}else{
				return "notnull";
			}
		}catch(Exception e){
			e.printStackTrace();
			return "error";
		}
		
	}
	
	public String KaoQinSave2(){
		
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		String personId = session.getAttribute("personId").toString();
		try{
			Person person = OrgManagerFactory.getOrgManager().getPersonByID(personId);
			String ck=person.getName();
			Date newTime=new Date();
			String KaoQinTime="";
			KaoQinTime=(newTime.getYear()+1900)+"-"+(newTime.getMonth()+1)+"-"+newTime.getDate()+
						" "+newTime.getHours()+":"+newTime.getMinutes()+":"+newTime.getSeconds();
			Date rq=formatDate2(KaoQinTime);
			String czlx="签退";
			String sjlx="上午";
			Date czsj=formatDate(KaoQinTime);
			
			kqBean=kqMsgImpl.findByRq(personId,rq, czlx, sjlx);
			if(kqBean==null){
				int Hours=czsj.getHours();
				int Min=czsj.getMinutes();
				int Sec=czsj.getSeconds();
				int total1=Hours*3600+Min*60+Sec;
				
				kqsjsdBean=kqsjMsgImpl.getByRq(rq);
				Date getswqtsj=kqsjsdBean.getSwqtsj();
				int Hours2=getswqtsj.getHours();
				int Min2=getswqtsj.getMinutes();
				int Sec2=getswqtsj.getSeconds();
				int total2=Hours2*3600+Min2*60+Sec2;
				int total3=total2+3600;
				
				Date getswqdsj=kqsjsdBean.getSwqdsj();
				int Hours4=getswqdsj.getHours();
				int Min4=getswqdsj.getMinutes();
				int Sec4=getswqdsj.getSeconds();
				int total4=Hours4*3600+Min4*60+Sec4;
				
				if(total1>=total2 && total1<=total3){
					String zt="正常";
					kqBean = new KaoQinBean();
					kqBean.setCk(ck);
					kqBean.setRq(rq);
					kqBean.setCzlx(czlx);
					kqBean.setSjlx(sjlx);
					kqBean.setCzsj(czsj);
					kqBean.setZt(zt);
					kqBean.setSjdx(total1);
					kqBean.setPersonid(personId);
					kqMsgImpl.add(kqBean);
				}else if(total1>total3 || total1<total4){
					return "bushishangwuqiantuishijian";
				}else{
					String zt="早退";
					kqBean = new KaoQinBean();
					kqBean.setCk(ck);
					kqBean.setRq(rq);
					kqBean.setCzlx(czlx);
					kqBean.setSjlx(sjlx);
					kqBean.setCzsj(czsj);
					kqBean.setZt(zt);
					kqBean.setSjdx(total1);
					kqBean.setPersonid(personId);
					kqMsgImpl.add(kqBean);
				}
			}else{
				int Hours=czsj.getHours();
				int Min=czsj.getMinutes();
				int Sec=czsj.getSeconds();
				int total1=Hours*3600+Min*60+Sec;
				
				kqsjsdBean=kqsjMsgImpl.getByRq(rq);
				Date getswqtsj=kqsjsdBean.getSwqtsj();
				int Hours2=getswqtsj.getHours();
				int Min2=getswqtsj.getMinutes();
				int Sec2=getswqtsj.getSeconds();
				int total2=Hours2*3600+Min2*60+Sec2;
				
				int total3=total2+3600;
				
				if(total1>=total2 && total1<=total3){
					String zt="正常";
					kqBean.setCzsj(czsj);
					kqBean.setZt(zt);
					kqBean.setSjdx(total1);
					kqMsgImpl.updateDate(kqBean);
				}else if(total1>total3){
					return "bushishangwuqiantuishijian";
				}else{
					String zt="早退";
					kqBean.setCzsj(czsj);
					kqBean.setZt(zt);
					kqBean.setSjdx(total1);
					kqMsgImpl.updateDate(kqBean);
				}
			}
			return "success";
		}catch(Exception e){
			e.printStackTrace();
			return "error";
		}
	}
	
	public String KaoQinSave3(){
		
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		String personId = session.getAttribute("personId").toString();
		try{
			Person person = OrgManagerFactory.getOrgManager().getPersonByID(personId);
			String ck=person.getName();
			Date newTime=new Date();
			String KaoQinTime="";
			KaoQinTime=(newTime.getYear()+1900)+"-"+(newTime.getMonth()+1)+"-"+newTime.getDate()+
						" "+newTime.getHours()+":"+newTime.getMinutes()+":"+newTime.getSeconds();
			Date rq=formatDate2(KaoQinTime);
			String czlx="签到";
			String sjlx="下午";
			Date czsj=formatDate(KaoQinTime);
			
			kqBean=kqMsgImpl.findByRq(personId,rq, czlx, sjlx);
			if(kqBean==null){
				int Hours=czsj.getHours();
				int Min=czsj.getMinutes();
				int Sec=czsj.getSeconds();
				int total1=Hours*3600+Min*60+Sec;
				
				kqsjsdBean=kqsjMsgImpl.getByRq(rq);
				Date getxwqdsj=kqsjsdBean.getXwqdsj();
				int Hours2=getxwqdsj.getHours();
				int Min2=getxwqdsj.getMinutes();
				int Sec2=getxwqdsj.getSeconds();
				int total2=Hours2*3600+Min2*60+Sec2;
				
				int total3=total2-3600;
				
				Date getxwqtsj=kqsjsdBean.getXwqtsj();
				int Hours4=getxwqtsj.getHours();
				int Min4=getxwqtsj.getMinutes();
				int Sec4=getxwqtsj.getSeconds();
				int total4=Hours4*3600+Min4*60+Sec4;
				
				if(total1>=total3 && total1<=total2){
					String zt="正常";
					kqBean = new KaoQinBean();
					kqBean.setCk(ck);
					kqBean.setRq(rq);
					kqBean.setCzlx(czlx);
					kqBean.setSjlx(sjlx);
					kqBean.setCzsj(czsj);
					kqBean.setZt(zt);
					kqBean.setSjdx(total1);
					kqBean.setPersonid(personId);
					kqMsgImpl.add(kqBean);
				}else if(total1>total2 && total1<=total4){
					String zt="迟到";
					kqBean = new KaoQinBean();
					kqBean.setCk(ck);
					kqBean.setRq(rq);
					kqBean.setCzlx(czlx);
					kqBean.setSjlx(sjlx);
					kqBean.setCzsj(czsj);
					kqBean.setZt(zt);
					kqBean.setSjdx(total1);
					kqBean.setPersonid(personId);
					kqMsgImpl.add(kqBean);
				}else{
					return "bushixiawuqiandaoshijian";
				}
				return "success";
			}else{
				return "notnull";
			}
		}catch(Exception e){
			e.printStackTrace();
			return "error";
		}
		
	}
	
	public String KaoQinSave4(){
		
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		String personId = session.getAttribute("personId").toString();
		try{
			Person person = OrgManagerFactory.getOrgManager().getPersonByID(personId);
			String ck=person.getName();
			Date newTime=new Date();
			String KaoQinTime="";
			KaoQinTime=(newTime.getYear()+1900)+"-"+(newTime.getMonth()+1)+"-"+newTime.getDate()+
						" "+newTime.getHours()+":"+newTime.getMinutes()+":"+newTime.getSeconds();
			Date rq=formatDate2(KaoQinTime);
			String czlx="签退";
			String sjlx="下午";
			Date czsj=formatDate(KaoQinTime);
			
			kqBean=kqMsgImpl.findByRq(personId,rq, czlx, sjlx);
			if(kqBean==null){
				int Hours=czsj.getHours();
				int Min=czsj.getMinutes();
				int Sec=czsj.getSeconds();
				int total1=Hours*3600+Min*60+Sec;
				
				kqsjsdBean=kqsjMsgImpl.getByRq(rq);
				Date getxwqtsj=kqsjsdBean.getXwqtsj();
				int Hours2=getxwqtsj.getHours();
				int Min2=getxwqtsj.getMinutes();
				int Sec2=getxwqtsj.getSeconds();
				int total2=Hours2*3600+Min2*60+Sec2;
				
				Date getxwqdsj=kqsjsdBean.getXwqdsj();
				int Hours3=getxwqdsj.getHours();
				int Min3=getxwqdsj.getMinutes();
				int Sec3=getxwqdsj.getSeconds();
				int total3=Hours3*3600+Min3*60+Sec3;
				
				if(total1>=total2){
					String zt="正常";
					kqBean = new KaoQinBean();
					kqBean.setCk(ck);
					kqBean.setRq(rq);
					kqBean.setCzlx(czlx);
					kqBean.setSjlx(sjlx);
					kqBean.setCzsj(czsj);
					kqBean.setZt(zt);
					kqBean.setSjdx(total1);
					kqBean.setPersonid(personId);
					kqMsgImpl.add(kqBean);
				}else if(total1>total3 && total1<total2){
					String zt="早退";
					kqBean = new KaoQinBean();
					kqBean.setCk(ck);
					kqBean.setRq(rq);
					kqBean.setCzlx(czlx);
					kqBean.setSjlx(sjlx);
					kqBean.setCzsj(czsj);
					kqBean.setZt(zt);
					kqBean.setSjdx(total1);
					kqBean.setPersonid(personId);
					kqMsgImpl.add(kqBean);
				}else{
					return "bushixiawuqiantuishijian";
				}
				
			}else{
				int Hours=czsj.getHours();
				int Min=czsj.getMinutes();
				int Sec=czsj.getSeconds();
				int total1=Hours*3600+Min*60+Sec;
				
				kqsjsdBean=kqsjMsgImpl.getByRq(rq);
				Date getxwqtsj=kqsjsdBean.getXwqtsj();
				int Hours2=getxwqtsj.getHours();
				int Min2=getxwqtsj.getMinutes();
				int Sec2=getxwqtsj.getSeconds();
				int total2=Hours2*3600+Min2*60+Sec2;
				
				Date getxwqdsj=kqsjsdBean.getXwqdsj();
				int Hours3=getxwqdsj.getHours();
				int Min3=getxwqdsj.getMinutes();
				int Sec3=getxwqdsj.getSeconds();
				int total3=Hours3*3600+Min3*60+Sec3;
				
				if(total1>=total2){
					String zt="正常";
					kqBean.setCzsj(czsj);
					kqBean.setZt(zt);
					kqBean.setSjdx(total1);
					kqMsgImpl.updateDate(kqBean);
				}else if(total1>total3 && total1<total2){
					String zt="早退";
					kqBean.setCzsj(czsj);
					kqBean.setZt(zt);
					kqBean.setSjdx(total1);
					kqMsgImpl.updateDate(kqBean);
				}else{
					return "bushixiawuqiantuishijian";
				}
			}
			return "success";
		}catch(Exception e){
			e.printStackTrace();
			return "error";
		}
		
	}
	
	public String KaoQinList(){
		HttpServletResponse response=ServletActionContext.getResponse();
		try{
			int index=Integer.parseInt(start);
			int pageSize=Integer.parseInt(limit);
			
			String sql="";
			
			if(sdate==null){
				if(edate==null){
					if(stime.equals("")){
						if(etime.equals("")){
							if(kqzt.equals("")){
								sql="from KaoQinBean bean";
							}else{
								sql="from KaoQinBean bean where bean.zt='"+kqzt+"'";
							}
						}else{
							if(kqzt.equals("")){
								Date endTime=formatDate("2012-06-10 "+etime);
								int Hours2=endTime.getHours();
								int Min2=endTime.getMinutes();
								int Sec2=endTime.getSeconds();
								int total2=Hours2*3600+Min2*60+Sec2;

								sql="from KaoQinBean bean where bean.sjdx<"+total2;
							}else{
								Date endTime=formatDate("2012-06-10 "+etime);
								int Hours2=endTime.getHours();
								int Min2=endTime.getMinutes();
								int Sec2=endTime.getSeconds();
								int total2=Hours2*3600+Min2*60+Sec2;

								sql="from KaoQinBean bean where bean.sjdx<"+total2+" and bean.zt='"+kqzt+"'";
							}
						}
					}else{
						if(etime.equals("")){
							if(kqzt.equals("")){
								Date startTime=formatDate("2012-06-10 "+stime);
								int Hours1=startTime.getHours();
								int Min1=startTime.getMinutes();
								int Sec1=startTime.getSeconds();
								int total1=Hours1*3600+Min1*60+Sec1;

								sql="from KaoQinBean bean where bean.sjdx>"+total1;
							}else{
								Date startTime=formatDate("2012-06-10 "+stime);
								int Hours1=startTime.getHours();
								int Min1=startTime.getMinutes();
								int Sec1=startTime.getSeconds();
								int total1=Hours1*3600+Min1*60+Sec1;

								sql="from KaoQinBean bean where bean.sjdx>"+total1+" and bean.zt='"+kqzt+"'";
							}
						}else{
							if(kqzt.equals("")){
								Date startTime=formatDate("2012-06-10 "+stime);
								Date endTime=formatDate("2012-06-10 "+etime);
								int Hours1=startTime.getHours();
								int Min1=startTime.getMinutes();
								int Sec1=startTime.getSeconds();
								int total1=Hours1*3600+Min1*60+Sec1;
								int Hours2=endTime.getHours();
								int Min2=endTime.getMinutes();
								int Sec2=endTime.getSeconds();
								int total2=Hours2*3600+Min2*60+Sec2;

								sql="from KaoQinBean bean where bean.sjdx>"+total1+" and bean.sjdx<"+total2;
							}else{
								Date startTime=formatDate("2012-06-10 "+stime);
								Date endTime=formatDate("2012-06-10 "+etime);
								
								int Hours1=startTime.getHours();
								int Min1=startTime.getMinutes();
								int Sec1=startTime.getSeconds();
								int total1=Hours1*3600+Min1*60+Sec1;
								
								int Hours2=endTime.getHours();
								int Min2=endTime.getMinutes();
								int Sec2=endTime.getSeconds();
								int total2=Hours2*3600+Min2*60+Sec2;

								sql="from KaoQinBean bean where bean.sjdx>"+total1+" and bean.sjdx<"+total2+" and bean.zt='"+kqzt+"'";
							}
						}
					}
				}else{
					if(stime.equals("")){
						if(etime.equals("")){
							if(kqzt.equals("")){
								String endDate=(edate.getYear()+1900)+"-"+(edate.getMonth()+1)+"-"+edate.getDate();
								sql="from KaoQinBean bean where bean.rq<= to_date('" + endDate + "','yyyy-mm-dd')";
							}else{
								String endDate=(edate.getYear()+1900)+"-"+(edate.getMonth()+1)+"-"+edate.getDate();
								sql="from KaoQinBean bean where bean.rq<= to_date('" + endDate + "','yyyy-mm-dd')"+" and bean.zt='"+kqzt+"'";
							}
						}else{
							if(kqzt.equals("")){
								Date endTime=formatDate("2012-06-10 "+etime);
								int Hours2=endTime.getHours();
								int Min2=endTime.getMinutes();
								int Sec2=endTime.getSeconds();
								int total2=Hours2*3600+Min2*60+Sec2;
								
								String endDate=(edate.getYear()+1900)+"-"+(edate.getMonth()+1)+"-"+edate.getDate();
								sql="from KaoQinBean bean where bean.rq<= to_date('" + endDate + "','yyyy-mm-dd')"+" and bean.sjdx<"+total2;
							}else{
								Date endTime=formatDate("2012-06-10 "+etime);
								
								int Hours2=endTime.getHours();
								int Min2=endTime.getMinutes();
								int Sec2=endTime.getSeconds();
								int total2=Hours2*3600+Min2*60+Sec2;
								
								String endDate=(edate.getYear()+1900)+"-"+(edate.getMonth()+1)+"-"+edate.getDate();
								sql="from KaoQinBean bean where bean.rq<= to_date('" + endDate + "','yyyy-mm-dd')"+" and bean.sjdx<"+total2+" and bean.zt='"+kqzt+"'";
							}
						}
					}else{
						if(etime.equals("")){
							if(kqzt.equals("")){
								Date startTime=formatDate("2012-06-10 "+stime);
								
								int Hours1=startTime.getHours();
								int Min1=startTime.getMinutes();
								int Sec1=startTime.getSeconds();
								int total1=Hours1*3600+Min1*60+Sec1;
								
								String endDate=(edate.getYear()+1900)+"-"+(edate.getMonth()+1)+"-"+edate.getDate();
								sql="from KaoQinBean bean where bean.rq<= to_date('" + endDate + "','yyyy-mm-dd')"+" and bean.sjdx>"+
									total1;
							}else{
								Date startTime=formatDate("2012-06-10 "+stime);
								
								int Hours1=startTime.getHours();
								int Min1=startTime.getMinutes();
								int Sec1=startTime.getSeconds();
								int total1=Hours1*3600+Min1*60+Sec1;
								
								String endDate=(edate.getYear()+1900)+"-"+(edate.getMonth()+1)+"-"+edate.getDate();
								sql="from KaoQinBean bean where bean.rq<= to_date('" + endDate + "','yyyy-mm-dd')"+" and bean.sjdx>"+
									total1+" and bean.zt='"+kqzt+"'";
							}
						}else{
							if(kqzt.equals("")){
								Date startTime=formatDate("2012-06-10 "+stime);
								Date endTime=formatDate("2012-06-10 "+etime);
								
								int Hours1=startTime.getHours();
								int Min1=startTime.getMinutes();
								int Sec1=startTime.getSeconds();
								int total1=Hours1*3600+Min1*60+Sec1;
								
								int Hours2=endTime.getHours();
								int Min2=endTime.getMinutes();
								int Sec2=endTime.getSeconds();
								int total2=Hours2*3600+Min2*60+Sec2;
								
								String endDate=(edate.getYear()+1900)+"-"+(edate.getMonth()+1)+"-"+edate.getDate();
								sql="from KaoQinBean bean where bean.rq<= to_date('" + endDate + "','yyyy-mm-dd')"+" and bean.sjdx>"+
									total1+" and bean.sjdx<"+total2;
							}else{
								Date startTime=formatDate("2012-06-10 "+stime);
								Date endTime=formatDate("2012-06-10 "+etime);
								
								int Hours1=startTime.getHours();
								int Min1=startTime.getMinutes();
								int Sec1=startTime.getSeconds();
								int total1=Hours1*3600+Min1*60+Sec1;
								
								int Hours2=endTime.getHours();
								int Min2=endTime.getMinutes();
								int Sec2=endTime.getSeconds();
								int total2=Hours2*3600+Min2*60+Sec2;
								
								String endDate=(edate.getYear()+1900)+"-"+(edate.getMonth()+1)+"-"+edate.getDate();
								sql="from KaoQinBean bean where bean.rq<= to_date('" + endDate + "','yyyy-mm-dd')"+" and bean.sjdx>"+
									total1+" and bean.sjdx<"+total2+" and bean.zt='"+kqzt+"'";
							}
						}
					}
				}
			}else{
				if(edate==null){
					if(stime.equals("")){
						if(etime.equals("")){
							if(kqzt.equals("")){
								String startDate=(sdate.getYear()+1900)+"-"+(sdate.getMonth()+1)+"-"+sdate.getDate();
								sql="from KaoQinBean bean where bean.rq>= to_date('" + startDate + "','yyyy-mm-dd')";
							}else{
								String startDate=(sdate.getYear()+1900)+"-"+(sdate.getMonth()+1)+"-"+sdate.getDate();
								sql="from KaoQinBean bean where bean.rq>= to_date('" + startDate + "','yyyy-mm-dd')"+" and bean.zt='"+kqzt+"'";
							}
						}else{
							if(kqzt.equals("")){
								Date endTime=formatDate("2012-06-10 "+etime);
								
								int Hours2=endTime.getHours();
								int Min2=endTime.getMinutes();
								int Sec2=endTime.getSeconds();
								int total2=Hours2*3600+Min2*60+Sec2;
								
								String startDate=(sdate.getYear()+1900)+"-"+(sdate.getMonth()+1)+"-"+sdate.getDate();
								sql="from KaoQinBean bean where bean.rq>= to_date('" + startDate + "','yyyy-mm-dd')"+" and bean.sjdx<"+total2;
							}else{
								Date endTime=formatDate("2012-06-10 "+etime);
								int Hours2=endTime.getHours();
								int Min2=endTime.getMinutes();
								int Sec2=endTime.getSeconds();
								int total2=Hours2*3600+Min2*60+Sec2;
								
								String startDate=(sdate.getYear()+1900)+"-"+(sdate.getMonth()+1)+"-"+sdate.getDate();
								sql="from KaoQinBean bean where bean.rq>= to_date('" + startDate + "','yyyy-mm-dd')"+" and bean.sjdx<"+total2+" and bean.zt='"+kqzt+"'";
							}
						}
					}else{
						if(etime.equals("")){
							if(kqzt.equals("")){
								Date startTime=formatDate("2012-06-10 "+stime);
								
								int Hours1=startTime.getHours();
								int Min1=startTime.getMinutes();
								int Sec1=startTime.getSeconds();
								int total1=Hours1*3600+Min1*60+Sec1;
								
								String startDate=(sdate.getYear()+1900)+"-"+(sdate.getMonth()+1)+"-"+sdate.getDate();
								sql="from KaoQinBean bean where bean.rq>= to_date('" + startDate + "','yyyy-mm-dd')"+" and bean.sjdx>"+total1;
							}else{
								Date startTime=formatDate("2012-06-10 "+stime);
								int Hours1=startTime.getHours();
								int Min1=startTime.getMinutes();
								int Sec1=startTime.getSeconds();
								int total1=Hours1*3600+Min1*60+Sec1;
								
								String startDate=(sdate.getYear()+1900)+"-"+(sdate.getMonth()+1)+"-"+sdate.getDate();
								sql="from KaoQinBean bean where bean.rq>= to_date('" + startDate + "','yyyy-mm-dd')"+" and bean.sjdx>"+total1+" and bean.zt='"+kqzt+"'";
							}
						}else{
							if(kqzt.equals("")){
								Date startTime=formatDate("2012-06-10 "+stime);
								Date endTime=formatDate("2012-06-10 "+etime);
								
								int Hours1=startTime.getHours();
								int Min1=startTime.getMinutes();
								int Sec1=startTime.getSeconds();
								int total1=Hours1*3600+Min1*60+Sec1;
								
								int Hours2=endTime.getHours();
								int Min2=endTime.getMinutes();
								int Sec2=endTime.getSeconds();
								int total2=Hours2*3600+Min2*60+Sec2;
								
								String startDate=(sdate.getYear()+1900)+"-"+(sdate.getMonth()+1)+"-"+sdate.getDate();
								sql="from KaoQinBean bean where bean.rq>= to_date('" + startDate + "','yyyy-mm-dd')"+" and bean.sjdx>"+
									total1+" and bean.sjdx<"+total2;
							}else{
								Date startTime=formatDate("2012-06-10 "+stime);
								Date endTime=formatDate("2012-06-10 "+etime);
								
								int Hours1=startTime.getHours();
								int Min1=startTime.getMinutes();
								int Sec1=startTime.getSeconds();
								int total1=Hours1*3600+Min1*60+Sec1;
								
								int Hours2=endTime.getHours();
								int Min2=endTime.getMinutes();
								int Sec2=endTime.getSeconds();
								int total2=Hours2*3600+Min2*60+Sec2;
								
								String startDate=(sdate.getYear()+1900)+"-"+(sdate.getMonth()+1)+"-"+sdate.getDate();
								sql="from KaoQinBean bean where bean.rq>= to_date('" + startDate + "','yyyy-mm-dd')"+" and bean.sjdx>"+
									total1+" and bean.sjdx<"+total2+" and bean.zt='"+kqzt+"'";
							}
						}
					}
				}else{
					if(stime.equals("")){
						if(etime.equals("")){
							if(kqzt.equals("")){
								
								String startDate=(sdate.getYear()+1900)+"-"+(sdate.getMonth()+1)+"-"+sdate.getDate();
								String endDate=(edate.getYear()+1900)+"-"+(edate.getMonth()+1)+"-"+edate.getDate();
								sql="from KaoQinBean bean where bean.rq>= to_date('" + startDate + "','yyyy-mm-dd')"+" and bean.rq<= to_date('" + endDate + "','yyyy-mm-dd')";
							}else{
								
								String startDate=(sdate.getYear()+1900)+"-"+(sdate.getMonth()+1)+"-"+sdate.getDate();
								String endDate=(edate.getYear()+1900)+"-"+(edate.getMonth()+1)+"-"+edate.getDate();
								sql="from KaoQinBean bean where bean.rq>= to_date('" + startDate + "','yyyy-mm-dd')"+" and bean.rq<= to_date('" + endDate + "','yyyy-mm-dd')"+" and bean.zt='"+kqzt+"'";
							}
						}else{
							if(kqzt.equals("")){
								Date endTime=formatDate("2012-06-10 "+etime);
								int Hours2=endTime.getHours();
								int Min2=endTime.getMinutes();
								int Sec2=endTime.getSeconds();
								int total2=Hours2*3600+Min2*60+Sec2;
								String startDate=(sdate.getYear()+1900)+"-"+(sdate.getMonth()+1)+"-"+sdate.getDate();
								String endDate=(edate.getYear()+1900)+"-"+(edate.getMonth()+1)+"-"+edate.getDate();
								sql="from KaoQinBean bean where bean.rq>= to_date('" + startDate + "','yyyy-mm-dd')"+" and bean.rq<= to_date('" + endDate + "','yyyy-mm-dd')"+" and bean.sjdx<"+total2;
							}else{
								Date endTime=formatDate("2012-06-10 "+etime);
								int Hours2=endTime.getHours();
								int Min2=endTime.getMinutes();
								int Sec2=endTime.getSeconds();
								int total2=Hours2*3600+Min2*60+Sec2;
								
								String startDate=(sdate.getYear()+1900)+"-"+(sdate.getMonth()+1)+"-"+sdate.getDate();
								String endDate=(edate.getYear()+1900)+"-"+(edate.getMonth()+1)+"-"+edate.getDate();
								sql="from KaoQinBean bean where bean.rq>= to_date('" + startDate + "','yyyy-mm-dd')"+" and bean.rq<= to_date('" + endDate + "','yyyy-mm-dd')"+" and bean.sjdx<"+total2+" and bean.zt='"+kqzt+"'";
							}
						}
					}else{
						if(etime.equals("")){
							if(kqzt.equals("")){
								Date startTime=formatDate("2012-06-10 "+stime);
								int Hours1=startTime.getHours();
								int Min1=startTime.getMinutes();
								int Sec1=startTime.getSeconds();
								int total1=Hours1*3600+Min1*60+Sec1;
								
								String startDate=(sdate.getYear()+1900)+"-"+(sdate.getMonth()+1)+"-"+sdate.getDate();
								String endDate=(edate.getYear()+1900)+"-"+(edate.getMonth()+1)+"-"+edate.getDate();
								sql="from KaoQinBean bean where bean.rq>= to_date('" + startDate + "','yyyy-mm-dd')"+" and bean.rq<= to_date('" + endDate + "','yyyy-mm-dd')"+" and bean.sjdx>"+total1;
							}else{
								Date startTime=formatDate("2012-06-10 "+stime);
								
								int Hours1=startTime.getHours();
								int Min1=startTime.getMinutes();
								int Sec1=startTime.getSeconds();
								int total1=Hours1*3600+Min1*60+Sec1;
								
								String startDate=(sdate.getYear()+1900)+"-"+(sdate.getMonth()+1)+"-"+sdate.getDate();
								String endDate=(edate.getYear()+1900)+"-"+(edate.getMonth()+1)+"-"+edate.getDate();
								sql="from KaoQinBean bean where bean.rq>= to_date('" + startDate + "','yyyy-mm-dd')"+" and bean.rq<= to_date('" + endDate + "','yyyy-mm-dd')"+" and bean.sjdx>"+
								total1+" and bean.zt='"+kqzt+"'";
							}
						}else{
							if(kqzt.equals("")){
								Date startTime=formatDate("2012-06-10 "+stime);
								Date endTime=formatDate("2012-06-10 "+etime);
								
								int Hours1=startTime.getHours();
								int Min1=startTime.getMinutes();
								int Sec1=startTime.getSeconds();
								int total1=Hours1*3600+Min1*60+Sec1;
								
								int Hours2=endTime.getHours();
								int Min2=endTime.getMinutes();
								int Sec2=endTime.getSeconds();
								int total2=Hours2*3600+Min2*60+Sec2;
								
								String startDate=(sdate.getYear()+1900)+"-"+(sdate.getMonth()+1)+"-"+sdate.getDate();
								String endDate=(edate.getYear()+1900)+"-"+(edate.getMonth()+1)+"-"+edate.getDate();
								sql="from KaoQinBean bean where bean.rq>= to_date('" + startDate + "','yyyy-mm-dd')"+" and bean.rq<= to_date('" + endDate + "','yyyy-mm-dd')"+" and bean.sjdx>"+
									total1+" and bean.sjdx<"+total2;
							}else{
								Date startTime=formatDate("2012-06-10 "+stime);
								Date endTime=formatDate("2012-06-10 "+etime);
								
								int Hours1=startTime.getHours();
								int Min1=startTime.getMinutes();
								int Sec1=startTime.getSeconds();
								int total1=Hours1*3600+Min1*60+Sec1;
								
								int Hours2=endTime.getHours();
								int Min2=endTime.getMinutes();
								int Sec2=endTime.getSeconds();
								int total2=Hours2*3600+Min2*60+Sec2;
								
								String startDate=(sdate.getYear()+1900)+"-"+(sdate.getMonth()+1)+"-"+sdate.getDate();
								String endDate=(edate.getYear()+1900)+"-"+(edate.getMonth()+1)+"-"+edate.getDate();
								sql="from KaoQinBean bean where bean.rq>= to_date('" + startDate + "','yyyy-mm-dd')"+" and bean.rq<= to_date('" + endDate + "','yyyy-mm-dd')"+" and bean.sjdx>"+
									total1+" and bean.sjdx<"+total2+" and bean.zt='"+kqzt+"'";
							}
						}
					}
				}
			};
			kqList=kqMsgImpl.findAllKaoQin(sql);

			int totalCount = kqList.size();
			String json="{totalProperty:"+totalCount+",root:[";
			if(totalCount<pageSize+index){
				for(int i=index;i<totalCount;i++){
					   json+="{ck:'"+kqList.get(i).getCk()+"',uuid:'"+kqList.get(i).getUuid()
					   +"',rq:'"+kqList.get(i).getRq()+"',czlx:'"+kqList.get(i).getCzlx()+"',zt:'"+kqList.get(i).getZt()
					   +"',sjlx:'"+kqList.get(i).getSjlx()+"',bz:'"+kqList.get(i).getBz()+"',czsj:'"+kqList.get(i).getCzsj()+"'}";
						if(i!=(totalCount-1)){
							json+=",";
						}
				}
			}else{
				for(int i=index;i<pageSize+index;i++){
					json+="{ck:'"+kqList.get(i).getCk()+"',uuid:'"+kqList.get(i).getUuid()
					   +"',rq:'"+kqList.get(i).getRq()+"',czlx:'"+kqList.get(i).getCzlx()+"',zt:'"+kqList.get(i).getZt()
					   +"',sjlx:'"+kqList.get(i).getSjlx()+"',bz:'"+kqList.get(i).getBz()+"',czsj:'"+kqList.get(i).getCzsj()+"'}";
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
	
	public String KaoQinTimeList(){
		HttpServletResponse response=ServletActionContext.getResponse();
		
		try{
			int index=Integer.parseInt(start);
			int pageSize=Integer.parseInt(limit);
			
			kqsjList=kqsjMsgImpl.findAll();
			
			int totalCount = kqsjList.size();
			String json="{totalProperty:"+totalCount+",root:[";
			if(totalCount<pageSize+index){
				for(int i=index;i<totalCount;i++){
					   json+="{ksrq:'"+kqsjList.get(i).getKsrq()+"',uuid:'"+kqsjList.get(i).getUuid()
					   +"',jsrq:'"+kqsjList.get(i).getJsrq()+"',swqdsj:'"+kqsjList.get(i).getSwqdsj()
					   +"',swqtsj:'"+kqsjList.get(i).getSwqtsj()+"',xwqdsj:'"+kqsjList.get(i).getXwqdsj()
					   +"',xwqtsj:'"+kqsjList.get(i).getXwqtsj()+"'}";
						if(i!=(totalCount-1)){
							json+=",";
						}
				}
			}else{
				for(int i=index;i<pageSize+index;i++){
					   json+="{ksrq:'"+kqsjList.get(i).getKsrq()+"',uuid:'"+kqsjList.get(i).getUuid()
					   +"',jsrq:'"+kqsjList.get(i).getJsrq()+"',swqdsj:'"+kqsjList.get(i).getSwqdsj()
					   +"',swqtsj:'"+kqsjList.get(i).getSwqtsj()+"',xwqdsj:'"+kqsjList.get(i).getXwqdsj()
					   +"',xwqtsj:'"+kqsjList.get(i).getXwqtsj()+"'}";
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
	
	public String updateKaoQinTimeByUuid(){
		HttpServletResponse response=ServletActionContext.getResponse();
		Date newswqdsj=formatDate("2012-06-10 "+swqdsj);
		Date newswqtsj=formatDate("2012-06-10 "+swqtsj);
		Date newxwqdsj=formatDate("2012-06-10 "+xwqdsj);
		Date newxwqtsj=formatDate("2012-06-10 "+xwqtsj);
		try{
			kqsjsdBean = kqsjMsgImpl.getByUuid(uuid);
			kqsjsdBean.setKsrq(ksrq);
			kqsjsdBean.setJsrq(jsrq);
			kqsjsdBean.setSwqdsj(newswqdsj);
			kqsjsdBean.setSwqtsj(newswqtsj);
			kqsjsdBean.setXwqdsj(newxwqdsj);
			kqsjsdBean.setXwqtsj(newxwqtsj);

			kqsjMsgImpl.update(kqsjsdBean);
		
			 response.getWriter().print("{success:true}");
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	public String updateKaoQinByUuid(){
		HttpServletResponse response=ServletActionContext.getResponse();
		try{
			kqBean = kqMsgImpl.findByUuid(uuid);
			kqBean.setZt(zt);
			kqBean.setBz(bz);

			kqMsgImpl.updateDate(kqBean);
		
			 response.getWriter().print("{success:true}");
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;		
	}
	
	public String KaoQinBaoBiao(){
		List<GzrdyBean> gzrdylist=gzrdyMsgImpl.findAllByTime(sdate, edate);
		int gzr=gzrdylist.size();
		List<ChuangKouTianShuBean> ckqjlist=qjmxMsgImpl.findAllByDate(sdate, edate);
		for(int i=0;i<ckqjlist.size();i++){
			int qjts=ckqjlist.get(i).getTs();
			int sbts=gzr-qjts;
			ckqjlist.get(i).setTs(sbts);
		}
		kaoqinbaobiaoList=kqMsgImpl.findKaoQinBaoBiao(sdate, edate, ckqjlist);
		
		for(int i=0;i<kaoqinbaobiaoList.size();i++){
			String personid=kaoqinbaobiaoList.get(i).getPersonid();
			kqbbBean =kqMsgImpl.findByPersonid(personid);
			kaoqinbaobiaoList.get(i).setCkmc(kqbbBean.getCkmc());
		}
		int gjhj=0;
		int bsjhj=0;
		int qqhj=0;
		int cdhj=0;
		int zthj=0;
		for(int i=0;i<kaoqinbaobiaoList.size();i++){
			gjhj=gjhj+kaoqinbaobiaoList.get(i).getGj();
			bsjhj=bsjhj+kaoqinbaobiaoList.get(i).getBsj();
			qqhj=qqhj+kaoqinbaobiaoList.get(i).getQq();
			cdhj=cdhj+kaoqinbaobiaoList.get(i).getCd();
			zthj=zthj+kaoqinbaobiaoList.get(i).getZt();
		}
		kqbbBean =new KaoQinBaoBiaoBean();
		kqbbBean.setGjhj(gjhj);
		kqbbBean.setBsjhj(bsjhj);
		kqbbBean.setQqhj(qqhj);
		kqbbBean.setCdhj(cdhj);
		kqbbBean.setZthj(zthj);
		return "success";		
	}
	
	private Date formatDate2(String date){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {			
			return sdf.parse(date);			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;		
	}
	
	private Date formatDate(String date){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			return sdf.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;		
	}

	public KaoQinMsg getKqMsgImpl() {
		return kqMsgImpl;
	}

	public void setKqMsgImpl(KaoQinMsg kqMsgImpl) {
		this.kqMsgImpl = kqMsgImpl;
	}

	public KaoQinShiJianMsg getKqsjMsgImpl() {
		return kqsjMsgImpl;
	}

	public void setKqsjMsgImpl(KaoQinShiJianMsg kqsjMsgImpl) {
		this.kqsjMsgImpl = kqsjMsgImpl;
	}

	public KaoQinShiJianBean getKqsjsdBean() {
		return kqsjsdBean;
	}

	public void setKqsjsdBean(KaoQinShiJianBean kqsjsdBean) {
		this.kqsjsdBean = kqsjsdBean;
	}

	public Date getKsrq() {
		return ksrq;
	}

	public void setKsrq(Date ksrq) {
		this.ksrq = ksrq;
	}

	public Date getJsrq() {
		return jsrq;
	}

	public void setJsrq(Date jsrq) {
		this.jsrq = jsrq;
	}

	public String getSwqdsj() {
		return swqdsj;
	}

	public void setSwqdsj(String swqdsj) {
		this.swqdsj = swqdsj;
	}

	public String getSwqtsj() {
		return swqtsj;
	}

	public void setSwqtsj(String swqtsj) {
		this.swqtsj = swqtsj;
	}

	public String getXwqdsj() {
		return xwqdsj;
	}

	public void setXwqdsj(String xwqdsj) {
		this.xwqdsj = xwqdsj;
	}

	public String getXwqtsj() {
		return xwqtsj;
	}

	public void setXwqtsj(String xwqtsj) {
		this.xwqtsj = xwqtsj;
	}

	public KaoQinBean getKqBean() {
		return kqBean;
	}

	public void setKqBean(KaoQinBean kqBean) {
		this.kqBean = kqBean;
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

	public List<KaoQinBean> getKqList() {
		return kqList;
	}

	public void setKqList(List<KaoQinBean> kqList) {
		this.kqList = kqList;
	}

	public List<KaoQinShiJianBean> getKqsjList() {
		return kqsjList;
	}

	public void setKqsjList(List<KaoQinShiJianBean> kqsjList) {
		this.kqsjList = kqsjList;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
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

	public String getStime() {
		return stime;
	}

	public void setStime(String stime) {
		this.stime = stime;
	}

	public String getEtime() {
		return etime;
	}

	public void setEtime(String etime) {
		this.etime = etime;
	}

	public String getKqzt() {
		return kqzt;
	}

	public void setKqzt(String kqzt) {
		this.kqzt = kqzt;
	}

	public String getZt() {
		return zt;
	}

	public void setZt(String zt) {
		this.zt = zt;
	}

	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

	public GzrdyMsg getGzrdyMsgImpl() {
		return gzrdyMsgImpl;
	}

	public void setGzrdyMsgImpl(GzrdyMsg gzrdyMsgImpl) {
		this.gzrdyMsgImpl = gzrdyMsgImpl;
	}

	public QingJiaMingXiMsg getQjmxMsgImpl() {
		return qjmxMsgImpl;
	}

	public void setQjmxMsgImpl(QingJiaMingXiMsg qjmxMsgImpl) {
		this.qjmxMsgImpl = qjmxMsgImpl;
	}

	public KaoQinBaoBiaoBean getKqbbBean() {
		return kqbbBean;
	}

	public void setKqbbBean(KaoQinBaoBiaoBean kqbbBean) {
		this.kqbbBean = kqbbBean;
	}

	public List<KaoQinBaoBiaoBean> getKaoqinbaobiaoList() {
		return kaoqinbaobiaoList;
	}

	public void setKaoqinbaobiaoList(List<KaoQinBaoBiaoBean> kaoqinbaobiaoList) {
		this.kaoqinbaobiaoList = kaoqinbaobiaoList;
	}
	
}
