package net.itjds.worklist.list.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import sun.misc.ConditionLock;

import net.itjds.bpm.client.ActivityInst;
import net.itjds.bpm.client.ActivityInstHistory;
import net.itjds.bpm.client.ProcessInst;
import net.itjds.bpm.engine.BPMException;
import net.itjds.bpm.engine.WorkflowClientService;
import net.itjds.bpm.engine.inter.EIActivityInstHistory;
import net.itjds.bpm.engine.query.AbstractFilter;
import net.itjds.bpm.engine.query.Condition;
import net.itjds.bpm.engine.query.ConditionKey;
import net.itjds.bpm.engine.query.Filter;
import net.itjds.bpm.engine.query.FilterChain;
import net.itjds.worklist.list.support.RuleManager;
import net.itjds.worklist.list.support.rules.Rule;
import net.itjds.userclient.bpm.search.SearchMapDAODataMap;
import net.itjds.userclient.common.BPMUserClientUtil;
import net.itjds.usm2.db.util.EsbUtil;
import net.sf.cglib.beans.BeanMap;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;

public abstract class BPMClientBaseBinding implements Action {
	protected Integer start ;
	protected Integer totalCount;
	protected Integer limit;
	protected String type;
	
	protected String ctype;
	protected String state;
	protected Date startTime;
	protected Date endTime;
	protected String fileTitle;
	protected String status="";
	
	protected SearchMapDAODataMap mapdaoMap;
	protected StringBuffer InjectHistoryData(List<ActivityInstHistory> list) throws BPMException, InstantiationException, IllegalAccessException, ClassNotFoundException{
		
		List<BeanMap> curList = new ArrayList();
		int listSize = list.size();
		this.totalCount = listSize;
		int len = Math.min(listSize, start + limit);
		RuleManager ruleManager = RuleManager.getRuleManager();
		ruleManager.refresh();
		for (int i = start; i < len; i++) {
			ActivityInstHistory aih= list.get(i);
			BeanMap proxy = BeanMap.create(Class.forName(type).newInstance());
			proxy.put("activityInstHistory", aih);
			Iterator<String> keys = proxy.keySet().iterator();
			while (keys.hasNext()) {
				String key = keys.next();
				if (ruleManager.existRule(key)) {
					Rule rule = ruleManager.getRule(key);
					proxy.put(key, rule.invoke(aih));
				}
			}
			curList.add(proxy);
		}
		StringBuffer json = new StringBuffer();
		json.append("{");
		json.append("totalCount:" + this.totalCount + ",");
		json.append("datas:[");
		boolean fp = false;
		for (BeanMap proxy : curList) {
			Iterator it = proxy.keySet().iterator();
			json.append("{");
			boolean f = false;
			while (it.hasNext()) {
				String key = (String) it.next();
				Object o = proxy.get(key);
				json.append(key + ":\"" + o + "\",");
				f = true;
			}
			if (f)
				json.deleteCharAt(json.length() - 1);
			json.append("},");
			fp = true;
		}
		if (fp)
			json.deleteCharAt(json.length() - 1);
		json.append("]");
		json.append("}");
		return json;
	}
	
protected StringBuffer InjectProcessInstData(List<ProcessInst> list) throws BPMException, InstantiationException, IllegalAccessException, ClassNotFoundException{
		
		List<BeanMap> curList = new ArrayList();
		int listSize = list.size();
		this.totalCount = listSize;
		int len = Math.min(listSize, start + limit);
		RuleManager ruleManager = RuleManager.getRuleManager();
		ruleManager.refresh();
		for (int i = start; i < len; i++) {
			ProcessInst pi = list.get(i);
			WorkflowClientService client=(WorkflowClientService) ActionContext.getContext().getValueStack().findValue("$BPMC");
			Condition processContion=new Condition(ConditionKey.ACTIVITYHISTORY_PROCESSINST_ID,Condition.EQUALS,pi.getProcessInstId());
			Filter filter=new Filter(){

				public boolean filterObject(Object obj, String systemCode) {
//					EIActivityInstHistory aih = (EIActivityInstHistory) obj;
//					if (aih.getDealMethod().equals(ActivityInst.DEALMETHOD_SPLITED)){
//						return false;
//					}
					return true;
				}
				
			};
	
			
	
			List<ActivityInstHistory> listHistory=client.getActivityInstHistoryList(processContion, filter, null);
				
			if(listHistory.isEmpty())
				continue;
			ActivityInstHistory aih = null;
			
			for(int k=listHistory.size()-1;k>=0;k--){
				aih = listHistory.get(k);
				if (aih.getActivityInst()!=null){
					break;
				}
				
			}
	

			
			
			
			BeanMap proxy = BeanMap.create(Class.forName(type).newInstance());
			proxy.put("activityInstHistory", aih);
			Iterator<String> keys = proxy.keySet().iterator();
			while (keys.hasNext()) {
				String key = keys.next();
				if (ruleManager.existRule(key)) {
					Rule rule = ruleManager.getRule(key);
					proxy.put(key, rule.invoke(aih));
				}
			}
			curList.add(proxy);
		}
		StringBuffer json = new StringBuffer();
		json.append("{");
		json.append("totalCount:" + this.totalCount + ",");
		json.append("datas:[");
		boolean fp = false;
		for (BeanMap proxy : curList) {
			Iterator it = proxy.keySet().iterator();
			json.append("{");
			boolean f = false;
			while (it.hasNext()) {
				String key = (String) it.next();
				Object o = proxy.get(key);
				json.append(key + ":\"" + o + "\",");
				f = true;
			}
			if (f)
				json.deleteCharAt(json.length() - 1);
			json.append("},");
			fp = true;
		}
		if (fp)
			json.deleteCharAt(json.length() - 1);
		json.append("]");
		json.append("}");
		return json;
	}


	protected StringBuffer InjectActivityInstData(List<ActivityInst> list) throws InstantiationException, IllegalAccessException, ClassNotFoundException{
		
		List<BeanMap> curList = new ArrayList();
		int listSize = list.size();
		this.totalCount = listSize;
		int len = Math.min(listSize, start+limit);
		RuleManager ruleManager = RuleManager.getRuleManager();
		ruleManager.refresh();
		for(int i = start ; i < len;i++){
			ActivityInst ai = list.get(i);
			BeanMap proxy = BeanMap.create(Class.forName(type).newInstance());
			proxy.put("activityInst", ai);
			
			Iterator<String> keys = proxy.keySet().iterator();
			while(keys.hasNext()){
				String key = keys.next();
				if(ruleManager.existRule(key)){
					Rule rule = ruleManager.getRule(key);
					proxy.put(key, rule.invoke(ai));
				}
			}
			curList.add(proxy);
		}
		StringBuffer json = new StringBuffer();
		json.append("{");
		json.append("totalCount:"+this.totalCount+",");
		json.append("datas:[");
		boolean fp = false;
		for(BeanMap proxy:curList){
			Iterator it = proxy.keySet().iterator();
			json.append("{");
			boolean f = false;
			while(it.hasNext()){
				String key = (String)it.next();
				Object o = proxy.get(key);
				json.append(key+":\""+o+"\",");
				f =true;
			}
			if(f)json.deleteCharAt(json.length()-1);
			json.append("},");
			fp = true;
		}
		if(fp)json.deleteCharAt(json.length()-1);
		json.append("]");
		json.append("}");
		
		return json;
	}
	protected StringBuffer InjectUsmInstData(List<ProcessInst> list) throws BPMException, InstantiationException, IllegalAccessException, ClassNotFoundException{
		List<BeanMap> curList = new ArrayList();
		int listSize = list.size();
		this.totalCount = listSize;
		int len = Math.min(listSize, start + limit);
		RuleManager ruleManager = RuleManager.getRuleManager();
		ruleManager.refresh();
		for (int i = start; i < len; i++) {
			ProcessInst pi = list.get(i);
			List<ActivityInst> aiList = pi.getActivityInstList();
			BeanMap proxy = BeanMap.create(Class.forName(type).newInstance());
			if(aiList.isEmpty()){
				List<ActivityInstHistory> listHistory = pi.getActivityInstHistoryListByProcessInst();
				if(listHistory.isEmpty())
					continue;
				ActivityInstHistory aih = null;
				if(listHistory.size()>1)
					aih = listHistory.get(listHistory.size()-2);
				else
					aih = listHistory.get(listHistory.size()-1);
				proxy.put("activityInstHistory", aih);
				Iterator<String> keys = proxy.keySet().iterator();
				while (keys.hasNext()) {
					String key = keys.next();
					if (ruleManager.existRule(key)) {
						Rule rule = ruleManager.getRule(key);
						proxy.put(key, rule.invoke(aih));
					}
				}
			}else{
				ActivityInst ai = aiList.get(0);
				proxy.put("activityInst", ai);
				Iterator<String> keys = proxy.keySet().iterator();
				while(keys.hasNext()){
					String key = keys.next();
					if(ruleManager.existRule(key)){
						Rule rule = ruleManager.getRule(key);
						proxy.put(key, rule.invoke(ai));
					}
				}
			}
			curList.add(proxy);
		}
		StringBuffer json = new StringBuffer();
		json.append("{");
		json.append("totalCount:" + this.totalCount + ",");
		json.append("datas:[");
		boolean fp = false;
		for (BeanMap proxy : curList) {
			Iterator it = proxy.keySet().iterator();
			json.append("{");
			boolean f = false;
			while (it.hasNext()) {
				String key = (String) it.next();
				Object o = proxy.get(key);
				json.append(key + ":\"" + o + "\",");
				f = true;
			}
			if (f)
				json.deleteCharAt(json.length() - 1);
			json.append("},");
			fp = true;
		}
		if (fp)
			json.deleteCharAt(json.length() - 1);
		json.append("]");
		json.append("}");
		return json;
	}
	public Integer getStart() {
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCtype() {
		return ctype;
	}

	public void setCtype(String ctype) {
		this.ctype = ctype;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getFileTitle() {
		return fileTitle;
	}

	public void setFileTitle(String fileTitle) {
		this.fileTitle = fileTitle;
	}

	public SearchMapDAODataMap getMapdaoMap() {
		if (mapdaoMap==null){
			mapdaoMap=new SearchMapDAODataMap();
		}
		return mapdaoMap;
	}

	public void setMapdaoMap(SearchMapDAODataMap mapdaoMap) {
		this.mapdaoMap = mapdaoMap;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}


}
