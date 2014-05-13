package net.itjds.worklist.list.support.rules;

import net.itjds.bpm.client.ActivityInst;
import net.itjds.bpm.client.ActivityInstHistory;
import net.itjds.bpm.data.xmlproxy.EsbBeanAnnotation;
import net.itjds.bpm.engine.BPMException;
import net.itjds.common.org.base.Person;
import net.itjds.worklist.list.support.GridUtils;
import net.itjds.worklist.list.support.annotation.RuleDefine;

@EsbBeanAnnotation(id="worklist.impending",
		name="紧急程度",
		expressionArr="ImpendingRule()",
		desc="紧急程度",
		dataType="action")
		
@RuleDefine(key = "impending", 
		mapping = "net.itjds.worklist.list.support.rules.ImpendingRule", 
		name = "紧急程度")
public class ImpendingRule implements Rule {

	public Object invoke(ActivityInst ai) {
		String impending = "";
		try {
			String jjcd = ai.getProcessInst().getAttribute("impending");
			if(jjcd == null || jjcd.equals("")){
				impending = "平急";
			}else{
				impending = GridUtils.filterJsEnter(jjcd);
			}
				
		} catch (BPMException e) {
			e.printStackTrace();
		}
		
		return impending;
	}

	public Object invoke(ActivityInstHistory aih) {
		String impending = "";
		try {
			String jjcd = aih.getProcessInst().getAttribute("impending");
			if(jjcd == null || jjcd.equals("")){
				impending = "平急";
			}else{
				impending = GridUtils.filterJsEnter(jjcd);
			}
				
		} catch (BPMException e) {
			e.printStackTrace();
		}
		
		return impending;
	}
	

}
