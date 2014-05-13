package com.kzxd.kaoqin.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import com.kzxd.kaoqin.pojo.kaoqin;

public class KQUtil {
	
	//************************************************************************
	//************************************************************************
	//*******                  ϵͳ��ʱ�亯��                                  *******                   
	//*******                                                           *******
	//*************************************************************************
	//*************************************************************************/
	//��ȡϵͳ��ǰʱ�䣬����װ��Ϊ�����ַ�����ʽ��������λ�Ĳ�0,��ȷ���룻
	 public static String getCurrent(){
		   
          Calendar c=Calendar.getInstance();
		  c.add(Calendar.SECOND,0);
		  String year= String.valueOf(c.get(Calendar.YEAR));
		  String month= String.valueOf(c.get(Calendar.MONTH)+1);  
		  String date=String.valueOf(c.get(Calendar.DATE));
		  String hourofday=String.valueOf(c.get(Calendar.HOUR_OF_DAY));
		  String minute=String.valueOf(c.get(Calendar.MINUTE));
	      return year+(month.length()==1?("0"+month):month)+( date.length()==1?("0"+ date): date)+( hourofday.length()==1?("0"+ hourofday): hourofday)+(minute.length()==1?("0"+minute):minute);
		
		 }
	   
     public static String getCurrent(Date date){
		 
		 Calendar c=Calendar.getInstance();
		 c.setTime(date);
		 String year= String.valueOf(c.get(Calendar.YEAR));
		 String month= String.valueOf(c.get(Calendar.MONTH)+1);  
		 String datee=String.valueOf(c.get(Calendar.DATE));
		 String hourofday=String.valueOf(c.get(Calendar.HOUR_OF_DAY));
		 String minute=String.valueOf(c.get(Calendar.MINUTE));
		 return year+(month.length()==1?("0"+month):month)+( datee.length()==1?("0"+ datee): datee)+( hourofday.length()==1?("0"+ hourofday): hourofday)+(minute.length()==1?("0"+minute):minute);
		 
		 
	 }  
     //���ʱ�̵��ַ���ֵ 
     public static String getNeedCurrent(Date date){
    	 Calendar c=Calendar.getInstance();
		 c.setTime(date);
		 String hourofday=String.valueOf(c.get(Calendar.HOUR_OF_DAY));
		 String minute=String.valueOf(c.get(Calendar.MINUTE));
    	 
		return( hourofday.length()==1?("0"+ hourofday): hourofday)+(minute.length()==1?("0"+minute):minute);}
     //������ڵ��ַ���ֵ
     public static String getNeedCurrentb(Date date){
    	 Calendar c=Calendar.getInstance();
		 c.setTime(date);
		 String month= String.valueOf(c.get(Calendar.MONTH)+1);  
		 String datee=String.valueOf(c.get(Calendar.DATE));
		return( month.length()==1?("0"+month):month)+(datee.length()==1?("0"+ datee): datee);
    	 
     }
     //��ȡ��ݵ��ַ���ֵ��
     public static String getNeedCurrenty(Date date){
    	 Calendar c=Calendar.getInstance();
		 c.setTime(date);
		 String year= String.valueOf(c.get(Calendar.YEAR));
		 
		return year;
		 
     }
     //��ȡʱ�̵�����ֵ;
     public static int getNeedTime(String s){
    	 int in=Integer.parseInt(s);
		return in;
    	 
    	 
     }
   //���ַ���ת����long����Integer Long Float Double Void 

	 
     public static long stringToLong(String string){
   	    long lon=Long.parseLong(string);
   	    return lon;  
     }
     
   //���������������ڣ��Ƚ����ߵĴ�С
	 public static boolean compareToNumber(long dateb,final long dateg){
		    if(dateb>dateg){
		    
		         return true;
		    }
		    else {
		    
		    	return false;
		    }
		    	
	 }
	 
	//���ַ���ת����date����
	  public static Date switchStringToDate(String sDate) {
	    Date date = null;
	    try {
	      SimpleDateFormat df = new SimpleDateFormat("yyyyMMddhhmm");
	      date = df.parse(sDate);

	    }
	    catch (Exception e) {
	    }
	    return date;
	  }
	 
	 
     //�Ƚ�����ʱ���Ⱥ�
	 public static boolean compareTwoDateBigOrSmall(String fromDate, String toDate) {
		    Date dateFrom = KQUtil.switchStringToDate(fromDate);
		    Date dateTo = KQUtil.switchStringToDate(toDate);
		    if (dateFrom.before(dateTo)) {
		      return true;
		    }
		    else {
		      return false;
		    }
		  }
	 
	   //ת�����ڸ�ʽ
		public static String formatDate(Date date){
		   SimpleDateFormat simledateformat=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		   //Date date=new Date();
		   String mmt=simledateformat.format(date); 
		return mmt;
		}
      
	 //
	 /**
	     *********************************************************************
	     *****           �����󣬶�������ת����json��ʽ                       *****
	     *********************************************************************
	     
	   */
	//���������װ����json��ʽ
     public static String arrayToJson(List<kaoqin> list,int count){ //����ʱ��<T>ָ������
   	int countp = list.size();
   	StringBuffer stringbuffer=new StringBuffer(); 
	stringbuffer.append("{totalProperty :" + count + ", root :[");
   	if(countp<=0){
   		return null;
   	}
   	else{
		for(int i=0;i<countp;i++){
			kaoqin kao=null;
			kaoqin  kaoi=list.get(i);//����ʱǿ��ת�����Լ���������
			    
			   
			    	
			   
			String no=objectToJson(kaoi);
			   
			stringbuffer.append(no); 
			
			if(i<(countp-1)){
			stringbuffer.append(",");
			
			} 	
			else{
				break;
			}   	   
		 }
		stringbuffer.append("]}");
   	}
   	  
		return stringbuffer.toString() ;
   	   }
     
     //
     
   //������ת����json��ʽ
     public static String objectToJson( Object obj){
   	
   	 Map map=reflect( obj);
   
   	  StringBuffer stringbuffer=new StringBuffer(); 
			//stringbuffer.append("{'uuid':'");
			//stringbuffer.append(map.get("uuid")+"',");//����ʱӦ������Ҫ�޸�,����ͨ�������ȡ��
			stringbuffer.append("{isCd:'");
			stringbuffer.append(map.get("isCd").equals(1)?"��"+"',":"��" +"',");
			stringbuffer.append("isZt:'");
			stringbuffer.append(map.get("isZt").equals(1)?"��"+"',":"��"+"',");
			stringbuffer.append("qdTime:'");
			stringbuffer.append(map.get("qdTime").toString().substring(0, map.get("qdTime").toString().lastIndexOf("."))+"',");
			stringbuffer.append("qtTime:'");
			stringbuffer.append(map.get("qtTime").toString().substring(0,map.get("qtTime").toString().lastIndexOf("."))+"',");
			stringbuffer.append("username:'");
			stringbuffer.append(map.get("username")+"',");
			stringbuffer.append("persionid:'");
			stringbuffer.append(map.get("personid")+"'}");
			String mmo=stringbuffer.toString();	
		return mmo;
     }
     
     
     //ͨ�������ȡʵ������ֶκ��ֶ�ֵ
     public static Map reflect(Object obj) {  
  	   Map<String,Object> map=new Hashtable();
  	      if (obj == null) return null;  
  	         Field[] fields = obj.getClass().getDeclaredFields();  
  	        for (int j = 0; j < fields.length; j++) { 
  	        	fields[j].setAccessible(true);  
  	        	            // �ֶ���   
  	        
  	                   // �ֶ�ֵ   
  	         if (fields[j].getType().getName().equals(  
  	        		                  java.lang.String.class.getName())) {
  	        	 try {
  	        		
  	        		
  	        			map.put(fields[j].getName(),  fields[j].get(obj));
	    	        	
					} catch (IllegalArgumentException e) {
						
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						
						e.printStackTrace();
					}  		
						
					}
  	         if(fields[j].getType().getName().equals(  
  	        	                java.lang.Integer.class.getName())  
  	        	                 || fields[j].getType().getName().equals("int") ){
  	        	 try {
  	        		
  	        	
  	        		 map.put(fields[j].getName(),  fields[j].get(obj));
  	        		
					} catch (IllegalArgumentException e) {
				
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						
						e.printStackTrace();
					}
  	         }
  	         if(fields[j].getType().getName().equals( java.util.Date.class.getName())){
  	        	 
  	        	 try {
  	        	
  	        		
  	        		 map.put(fields[j].getName(),  fields[j].get(obj));
  	        	
					} catch (IllegalArgumentException e) {
						
						e.printStackTrace();
					} catch (IllegalAccessException e) {
					
						e.printStackTrace();
					}
  	         }
  	         }
				return map;
  	        }
     
   /**  private void print(String str) {
 		super.getResponse().setContentType("text/html; charset=utf-8");
 		PrintWriter out = null;
 		try {
 			out = super.getResponse().getWriter();
 			out.print(str);
 		} catch (IOException e) {
 			e.printStackTrace();
 		} finally {
 			if (out != null)
 				out.close();
 		}

 	}*/
     /************************************************************************************
     *************************************************************************************
     *******                              ����excel                               ********                             
     *******                                                                      *******
     *************************************************************************************
     *************************************************************************************/
     
     
     
}
