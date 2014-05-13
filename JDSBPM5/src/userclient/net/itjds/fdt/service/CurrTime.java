package net.itjds.fdt.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import net.itjds.bpm.data.xmlproxy.EsbBeanAnnotation;
import net.itjds.j2ee.dao.MethodChinaName;



@EsbBeanAnnotation(id="CurrTime",
		name="当前日期",
		expressionArr="CurrTime()",
		desc="当前日期，时间",
		dataType="action")
		

    public class CurrTime{
    	
	  @MethodChinaName(cname="取得当前日期年(yyyy)")
    	public String getYear(){
    		Date cur = new Date();
    		SimpleDateFormat format = new SimpleDateFormat("yyyy");
    		String val = format.format(cur);
    		return val;
    	}
	  @MethodChinaName(cname="取得当前日期月(MM)")
    	public String getMonth(){
    		Date cur = new Date();
    		SimpleDateFormat format = new SimpleDateFormat("MM");
    		String val = format.format(cur);
    		return val;
    	}
	  @MethodChinaName(cname="取得当前日期日(dd)")
    	public String getDay(){
    		Date cur = new Date();
    		SimpleDateFormat format = new SimpleDateFormat("dd");
    		String val = format.format(cur);
    		return val;
    	}
	  @MethodChinaName(cname="取得当前日期(yyyy-MM-dd HH:mm:ss)")
    	public String getCurrTimeToS(){
    		Date cur = new Date(System.currentTimeMillis());
    		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    		String val = format.format(cur);
    		return val;
    	}
	  @MethodChinaName(cname="取得当前日期(yyyy-MM-dd)")
    	public String getCurrDate(){
    		Date cur = new Date(System.currentTimeMillis());
    		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    		String val = format.format(cur);
    		return val;
    	}
	  
	  @MethodChinaName(cname="取得当前日期($R('fs'))")
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
