package net.itjds.usm2.service.form;

import java.util.Map;

import net.itjds.bpm.data.xmlproxy.EsbBeanAnnotation;
import net.itjds.bpm.data.xmlproxy.MapdaoBeanAnnotation;

import net.itjds.common.expression.function.AbstractFunction;
import net.itjds.common.logging.Log;
import net.itjds.common.logging.LogFactory;

import net.itjds.usm2.USMException;
import net.itjds.usm2.db.UsmService;
import net.itjds.usm2.db.util.EsbUtil;
@EsbBeanAnnotation(id="currFormData",
		name = "获取当前行的数据集", 
		expressionArr = "GetCurrFormData($currFormUsmService(),R(\"gridId\"),R(\"uuid\"))", 
		desc="获取当前行的数据集",
		dataType="action"
		)
	
public class GetCurrFormData extends AbstractFunction{
	protected transient static final Log log = LogFactory.getLog(
			"currFormData", GetCurrFormData.class);
	
	public Object  perform(UsmService service ,String gridId,String uuid) {
		Object usmProxy=null;
		Map esbContextMap = EsbUtil.getContextMap();
		
		if (gridId==null||gridId.equals("")){
			gridId=uuid;
		}
	
		if (esbContextMap.containsKey(gridId) &&esbContextMap.get(gridId)!=null &&!esbContextMap.get(gridId).equals("")){
		   usmProxy=  esbContextMap.get(gridId);
		}else{
			try {
				if (gridId.startsWith("$")){
					usmProxy=EsbUtil.parExpression(gridId);
				}else{
					if (gridId.equals("new")){
						try {
							if (service!=null){
								usmProxy=service.create();
							}
							
						} catch (USMException e) {
							e.printStackTrace();
						}
						
					}else{
						usmProxy = service.getUsmProxyByKey(gridId);
					}
				}
				
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			esbContextMap.put(gridId, usmProxy);
		}
		return usmProxy;
	}

}
