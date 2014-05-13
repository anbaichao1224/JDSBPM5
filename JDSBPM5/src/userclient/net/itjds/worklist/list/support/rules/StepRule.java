package net.itjds.worklist.list.support.rules;

import net.itjds.bpm.client.ActivityInst;
import net.itjds.bpm.client.ActivityInstHistory;
import net.itjds.bpm.data.xmlproxy.EsbBeanAnnotation;
import net.itjds.bpm.engine.BPMException;
import net.itjds.worklist.list.support.GridUtils;
import net.itjds.worklist.list.support.annotation.RuleDefine;

@EsbBeanAnnotation(id="worklist.step",
		name="当前步骤",
		expressionArr="StepRule()",
		desc="当前步骤",
		dataType="action")
		
@RuleDefine(key = "step", 
		mapping = "net.itjds.worklist.list.support.rules.StepRule", 
		name = "当前步骤")
public class StepRule implements Rule {

	public Object invoke(ActivityInst ai) {
		String step1 = "";
		try {
			step1 = ai.getActivityDef().getName();
			if (step1.equals("公文交换跳转")){
				
				String step = "公文交换";
				return step;
			}
			else if(step1.equals("编号")){
				String step = "编号";
				return step;
				
			}else{
				return "";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
		
		
		
	}

	public Object invoke(ActivityInstHistory aih) {
		String step1 = "";
		try {
			
			step1 = aih.getActivityDef().getName();
			
			if (step1.equals("公文交换跳转")){
				
				String step = "公文交换";
				return step;
			}
			else if(step1.equals("编号")){
				String step = "编号";
				return step;
				
			}else{
				return "";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	

}
