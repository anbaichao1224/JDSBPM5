package net.itjds.fdt.service;


import java.util.HashMap;
import java.util.Map;


import net.itjds.bpm.data.xmlproxy.EsbBeanAnnotation;
import net.itjds.common.util.DateUtility;
import net.itjds.j2ee.dao.MethodChinaName;



@EsbBeanAnnotation(id="kclxservice",
		name="考察路线字典表",
		expressionArr="KclxService()",
		desc="考察路线字典表",
		dataType="action")
		

    public class KclxService{
	

    	
	  @MethodChinaName(cname="旅游景点")
    	public Map getLyjd(String cheshi){
    		Map lyjdMap=new HashMap();
    		if (cheshi==null ||cheshi.equals("北京")){
    			lyjdMap.put("01", "颐和园");
        		lyjdMap.put("02", "故宫");
        		lyjdMap.put("03", "八达岭长城");
    		}else{
    			lyjdMap.put("01", "颐和园1");
        		lyjdMap.put("02", "故宫1");
        		lyjdMap.put("03", "八达岭长城1");
    		}
    	
    		
    		return lyjdMap;
    	}
	  
	   	
	  @MethodChinaName(cname="城市")
    	public Map getChengshi(){
    		Map lyjdMap=new HashMap();
    		lyjdMap.put("01", "北京");
    		lyjdMap.put("02", "上海");
    	
    		return lyjdMap;
    	}
	  
	  
	  @MethodChinaName(cname="取得编号")
  	public String getBh(){
  		String datenum=DateUtility.getCurrentYear()+DateUtility.getCurrentMonth();
  		String bh=datenum+"001";
  		return bh;
  	}
	 

    }
