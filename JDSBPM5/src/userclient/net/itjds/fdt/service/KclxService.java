package net.itjds.fdt.service;


import java.util.HashMap;
import java.util.Map;


import net.itjds.bpm.data.xmlproxy.EsbBeanAnnotation;
import net.itjds.common.util.DateUtility;
import net.itjds.j2ee.dao.MethodChinaName;



@EsbBeanAnnotation(id="kclxservice",
		name="����·���ֵ��",
		expressionArr="KclxService()",
		desc="����·���ֵ��",
		dataType="action")
		

    public class KclxService{
	

    	
	  @MethodChinaName(cname="���ξ���")
    	public Map getLyjd(String cheshi){
    		Map lyjdMap=new HashMap();
    		if (cheshi==null ||cheshi.equals("����")){
    			lyjdMap.put("01", "�ú�԰");
        		lyjdMap.put("02", "�ʹ�");
        		lyjdMap.put("03", "�˴��볤��");
    		}else{
    			lyjdMap.put("01", "�ú�԰1");
        		lyjdMap.put("02", "�ʹ�1");
        		lyjdMap.put("03", "�˴��볤��1");
    		}
    	
    		
    		return lyjdMap;
    	}
	  
	   	
	  @MethodChinaName(cname="����")
    	public Map getChengshi(){
    		Map lyjdMap=new HashMap();
    		lyjdMap.put("01", "����");
    		lyjdMap.put("02", "�Ϻ�");
    	
    		return lyjdMap;
    	}
	  
	  
	  @MethodChinaName(cname="ȡ�ñ��")
  	public String getBh(){
  		String datenum=DateUtility.getCurrentYear()+DateUtility.getCurrentMonth();
  		String bh=datenum+"001";
  		return bh;
  	}
	 

    }
