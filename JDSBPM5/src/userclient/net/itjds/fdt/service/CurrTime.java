package net.itjds.fdt.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import net.itjds.bpm.data.xmlproxy.EsbBeanAnnotation;
import net.itjds.j2ee.dao.MethodChinaName;



@EsbBeanAnnotation(id="CurrTime",
		name="��ǰ����",
		expressionArr="CurrTime()",
		desc="��ǰ���ڣ�ʱ��",
		dataType="action")
		

    public class CurrTime{
    	
	  @MethodChinaName(cname="ȡ�õ�ǰ������(yyyy)")
    	public String getYear(){
    		Date cur = new Date();
    		SimpleDateFormat format = new SimpleDateFormat("yyyy");
    		String val = format.format(cur);
    		return val;
    	}
	  @MethodChinaName(cname="ȡ�õ�ǰ������(MM)")
    	public String getMonth(){
    		Date cur = new Date();
    		SimpleDateFormat format = new SimpleDateFormat("MM");
    		String val = format.format(cur);
    		return val;
    	}
	  @MethodChinaName(cname="ȡ�õ�ǰ������(dd)")
    	public String getDay(){
    		Date cur = new Date();
    		SimpleDateFormat format = new SimpleDateFormat("dd");
    		String val = format.format(cur);
    		return val;
    	}
	  @MethodChinaName(cname="ȡ�õ�ǰ����(yyyy-MM-dd HH:mm:ss)")
    	public String getCurrTimeToS(){
    		Date cur = new Date(System.currentTimeMillis());
    		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    		String val = format.format(cur);
    		return val;
    	}
	  @MethodChinaName(cname="ȡ�õ�ǰ����(yyyy-MM-dd)")
    	public String getCurrDate(){
    		Date cur = new Date(System.currentTimeMillis());
    		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    		String val = format.format(cur);
    		return val;
    	}
	  
	  @MethodChinaName(cname="ȡ�õ�ǰ����($R('fs'))")
  	public String getCurrDateByFs(String fs){
		  if (fs==null){
			  fs= "yyyy-MM-dd";
			 }
  		Date cur = new Date(System.currentTimeMillis());
  		SimpleDateFormat format = new SimpleDateFormat();
  		String val = format.format(cur);
  		return val;
  	}

    }
