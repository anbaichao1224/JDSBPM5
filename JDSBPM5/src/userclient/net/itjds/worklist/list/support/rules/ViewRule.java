package net.itjds.worklist.list.support.rules;

import net.itjds.bpm.client.ActivityInst;
import net.itjds.bpm.client.ActivityInstHistory;
import net.itjds.bpm.data.xmlproxy.EsbBeanAnnotation;
import net.itjds.bpm.engine.BPMException;
import net.itjds.worklist.list.support.GridUtils;
import net.itjds.worklist.list.support.annotation.RuleDefine;

@EsbBeanAnnotation(id = "worklist.view", name = "查看", expressionArr = "ViewRule()", desc = "点击打开", dataType = "action")
@RuleDefine(key = "view", mapping = "net.itjds.worklist.list.support.rules.ViewRule", name = "查看")
public class ViewRule implements Rule {

	public Object invoke(ActivityInst ai) {
		String view = "";
		try {
			view = ai.getActivityDef().getName();
		} catch (BPMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};

		//view = GridUtils.HTML_OpenWinDisplay(view, ai.getActivityInstId());
		view = "<a href='#' onclick=lcrzshow('"+ai.getActivityInstId()+"')>"+view+"</a>";
		return view;
	}

	public Object invoke(ActivityInstHistory aih) {
		String view = "";
		String activityInstId = "";
		try{
			view = aih.getActivityDef().getName();
			activityInstId = aih.getActivityInstId();
		} catch (BPMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};

		//view = GridUtils.HTML_OpenWinDisplayHistory(view, aih
		//		.getActivityHistoryId());
		view = "<a href='#' onclick=lcrzshow('"+activityInstId+"')>"+view+"</a>";
		return view;
	}

}
