package net.itjds.usm2.service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.jdbc.core.ParameterMapper;

import com.opensymphony.xwork2.ActionContext;


import net.itjds.bpm.data.xmlproxy.EsbBeanAnnotation;
import net.itjds.bpm.data.xmlproxy.MapdaoBeanAnnotation;
import net.itjds.common.expression.function.AbstractFunction;
import net.itjds.usm2.USMException;
import net.itjds.usm2.UsmProxy;
import net.itjds.usm2.db.UsmService;
import net.itjds.usm2.db.util.EsbUtil;



@EsbBeanAnnotation(
		id = "currData", 
		name = "获取当前数据库映射对象", 
		expressionArr = "GetCurrData(R(\"servicekey\"),R(\"uuid\"))", 
	
		desc = "获取当前数据库映射对象", 
		dataType = "action"
)

public class GetCurrData extends  AbstractFunction{
	
	public UsmProxy perform(String servicekey,String uuid){
		if (uuid==null ||uuid.equals("")){
			uuid=EsbUtil.getHttpParamsByName("uuid");
			HttpServletRequest request= ServletActionContext.getRequest();		
		}
		UsmService service=(UsmService) EsbUtil.parExpression("$"+servicekey+"UsmService");
	
		
		Map esbContextMap = EsbUtil.getContextMap();
		String usmKey=servicekey+"["+uuid+"]";
		UsmProxy proxy=null;
		if (esbContextMap.containsKey(usmKey)){;
			proxy=  (UsmProxy) esbContextMap.get(usmKey);
		}else{
			try {
				if (uuid==null ||uuid.equals("")){
					uuid=UUID.randomUUID().toString();
				}
				proxy = service.getUsmProxyByKey(uuid);
				
			} catch (USMException e) {
				e.printStackTrace();
			}
		}
		Map paramsmap=(Map) ActionContext.getContext().getValueStack().findValue("currdataParamsmap");
		if  (paramsmap==null){
			paramsmap=new HashMap();
			ActionContext.getContext().getValueStack().set("currdataParamsmap",paramsmap);
		}
		paramsmap.put(servicekey+".uuid", proxy.getPkValue());
		esbContextMap.put(usmKey, proxy);
		return proxy;
	}

}
