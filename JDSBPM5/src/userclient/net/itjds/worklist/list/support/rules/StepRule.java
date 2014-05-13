package net.itjds.worklist.list.support.rules;

import net.itjds.bpm.client.ActivityInst;
import net.itjds.bpm.client.ActivityInstHistory;
import net.itjds.bpm.data.xmlproxy.EsbBeanAnnotation;
import net.itjds.bpm.engine.BPMException;
import net.itjds.worklist.list.support.GridUtils;
import net.itjds.worklist.list.support.annotation.RuleDefine;

@EsbBeanAnnotation(id="worklist.step",
		name="��ǰ����",
		expressionArr="StepRule()",
		desc="��ǰ����",
		dataType="action")
		
@RuleDefine(key = "step", 
		mapping = "net.itjds.worklist.list.support.rules.StepRule", 
		name = "��ǰ����")
public class StepRule implements Rule {

	public Object invoke(ActivityInst ai) {
		String step1 = "";
		try {
			step1 = ai.getActivityDef().getName();
			if (step1.equals("���Ľ�����ת")){
				
				String step = "���Ľ���";
				return step;
			}
			else if(step1.equals("���")){
				String step = "���";
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
			
			if (step1.equals("���Ľ�����ת")){
				
				String step = "���Ľ���";
				return step;
			}
			else if(step1.equals("���")){
				String step = "���";
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
