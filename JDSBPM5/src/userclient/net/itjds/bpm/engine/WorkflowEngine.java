package net.itjds.bpm.engine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import net.itjds.bpm.engine.inter.EIActivityDef;
import net.itjds.bpm.engine.inter.EIActivityDefManager;
import net.itjds.bpm.engine.inter.EIActivityInst;
import net.itjds.bpm.engine.inter.EIActivityInstHistory;
import net.itjds.bpm.engine.inter.EIActivityInstHistoryManager;
import net.itjds.bpm.engine.inter.EIActivityInstManager;
import net.itjds.bpm.engine.inter.EIAttributeInst;
import net.itjds.bpm.engine.inter.EIProcessDef;
import net.itjds.bpm.engine.inter.EIProcessDefManager;
import net.itjds.bpm.engine.inter.EIProcessDefVersion;
import net.itjds.bpm.engine.inter.EIProcessDefVersionManager;
import net.itjds.bpm.engine.inter.EIProcessInst;
import net.itjds.bpm.engine.inter.EIProcessInstManager;
import net.itjds.bpm.engine.inter.EIRouteDef;
import net.itjds.bpm.engine.inter.EIRouteDefManager;
import net.itjds.bpm.engine.inter.EIRouteInst;
import net.itjds.bpm.engine.inter.EIRouteInstManager;
import net.itjds.bpm.engine.query.Condition;
import net.itjds.bpm.engine.query.Filter;
import net.itjds.bpm.engine.util.TimeUtility;
import net.itjds.common.logging.Log;
import net.itjds.common.logging.LogFactory;
import net.itjds.common.util.UUIDGenerator;
import net.itjds.userclient.bpmlistener.routefitle.RouteConditionFilter;

public class WorkflowEngine
{
  private Log log = LogFactory.getLog("bpm", 
    WorkflowEngine.class);

  EIProcessDefManager processDefMgr = null;

  EIProcessDefVersionManager processDefVerMgr = null;

  EIActivityDefManager activityDefMgr = null;

  EIRouteDefManager routeDefMgr = null;

  EIProcessInstManager processInstMgr = null;

  EIActivityInstManager activityInstMgr = null;

  EIRouteInstManager routeInstMgr = null;

  EIActivityInstHistoryManager activityInstHistoryMgr = null;

  EventControl eventControl = null;
  private String systemCode;
  private static WorkflowEngine engine = null;

  public static WorkflowEngine getEngine(String systemCode)
  {
    if (engine == null) {
      synchronized (WorkflowEngine.class) {
        if (engine == null) {
          engine = new WorkflowEngine(systemCode);
        }
      }
    }
    return engine;
  }

  protected WorkflowEngine(String systemCode)
  {
    this.processDefMgr = EIProcessDefManager.getInstance();
    this.processDefVerMgr = EIProcessDefVersionManager.getInstance();
    this.activityDefMgr = EIActivityDefManager.getInstance();
    this.routeDefMgr = EIRouteDefManager.getInstance();
    this.processInstMgr = EIProcessInstManager.getInstance();
    this.activityInstMgr = EIActivityInstManager.getInstance();
    this.routeInstMgr = EIRouteInstManager.getInstance();
    this.activityInstHistoryMgr = EIActivityInstHistoryManager.getInstance();
    this.eventControl = EventControl.getInstance();
    this.systemCode = systemCode;
  }

  public List<EIProcessDefVersion> getProcessDefVersionList(Condition condition, Filter filter)
    throws BPMException
  {
    String where = "";
    if (condition != null) {
      String conditionStr = condition.makeConditionString();
      if ((conditionStr != null) && (!conditionStr.equals(""))) {
        where = " where " + conditionStr;
      }
    }

    List processDefList = this.processDefVerMgr.loadByWhere(where);
    Iterator it = processDefList.iterator();
    List result;
    if (filter == null) {
      result = processDefList;
    } else {
      result = new ArrayList();
      while (it.hasNext()) {
        EIProcessDefVersion ver = (EIProcessDefVersion)it.next();
        if (filter.filterObject(ver, this.systemCode)) {
          result.add(ver);
        }
      }
    }
    return result;
  }

  public List<EIProcessDef> getProcessDefList(Condition condition, Filter filter)
    throws BPMException
  {
    String where = "";
    if (condition != null) {
      String conditionStr = condition.makeConditionString();
      if ((conditionStr != null) && (!conditionStr.equals(""))) {
        where = " where " + conditionStr;
      }
    }

    List processDefList = this.processDefMgr.loadByWhere(where);
    Iterator it = processDefList.iterator();
    List result;
    if (filter == null) {
      result = processDefList;
    } else {
      result = new ArrayList();
      while (it.hasNext()) {
        EIProcessDef def = (EIProcessDef)it.next();
        if (filter.filterObject(def, this.systemCode)) {
          result.add(def);
        }
      }
    }
    return result;
  }

  public List<EIProcessDef> getProcessInstList(Condition condition, Filter filter)
    throws BPMException
  {
    String where = "";
    if (condition != null) {
      String conditionStr = condition.makeConditionString();
      if ((conditionStr != null) && (!conditionStr.equals("")))
      {
        where = " where " + conditionStr;
      }
    }

    List processInstList = this.processInstMgr.loadByWhere(where);
    Iterator it = processInstList.iterator();
    List result;
    if (filter == null) {
      result = processInstList;
    } else {
      result = new ArrayList();
      while (it.hasNext()) {
        EIProcessInst inst = (EIProcessInst)it.next();
        if (filter.filterObject(inst, this.systemCode)) {
          result.add(inst);
        }
      }
    }
    return result;
  }

  public List<EIActivityInstHistory> getActivityInstHistoryList(Condition condition, Filter filter)
    throws BPMException
  {
    String where = "";
    if (condition != null) {
      String conditionStr = condition.makeConditionString();
      if ((conditionStr != null) && (!conditionStr.equals(""))) {
        where = " where " + conditionStr;
      }
    }

    List activityInstHistoryList = this.activityInstHistoryMgr.loadByWhere(where);
    Iterator it = activityInstHistoryList.iterator();
    List result;
    if (filter == null) {
      result = activityInstHistoryList;
    } else {
      result = new ArrayList();
      while (it.hasNext()) {
        EIActivityInstHistory inst = (EIActivityInstHistory)it.next();
        if (filter.filterObject(inst, this.systemCode)) {
          result.add(inst);
        }
      }
    }
    return result;
  }

  public List<EIActivityInst> getActivityInstList(Condition condition, Filter filter)
    throws BPMException
  {
    String where = "";
    if (condition != null) {
      String conditionStr = condition.makeConditionString();
      if ((conditionStr != null) && (!conditionStr.equals(""))) {
        where = " where " + conditionStr;
      }
    }

    List activityInstList = this.activityInstMgr.loadByWhere(where);
    Iterator it = activityInstList.iterator();
    List result;
    if (filter == null) {
      result = activityInstList;
    } else {
      result = new ArrayList();
      while (it.hasNext()) {
        EIActivityInst inst = (EIActivityInst)it.next();
        if (filter.filterObject(inst, this.systemCode)) {
          result.add(inst);
        }
      }
    }
    return result;
  }

  public EIProcessInst createProcessInst(String defId, String instName, String urgency)
    throws BPMException
  {
    return createProcessInst(defId, instName, urgency, 
      "MANUAL");
  }

  public EIProcessInst createProcessInst(String defId, String instName, String urgency, String initType)
    throws BPMException
  {
    this.log.debug("creating process instance, defId=" + defId);
    EIProcessDefVersion defVer = this.processDefVerMgr
      .getActiveProcessDefVersion(defId);
    if (defVer == null) {
      throw new BPMException("can't found the Process Definition " + 
        defId);
    }

    EIProcessInst inst = this.processInstMgr.createProcessInstance();
    inst.setProcessDefId(defId);
    inst.setProcessDefVersionId(defVer.getProcessDefVersionId());
    inst.setProcessInstId(UUIDGenerator.genUUID());
    inst.setName(instName);
    inst.setCopyNumber(0);
    inst.setRunStatus("NORMAL");

    if ("AUTO".equalsIgnoreCase(initType))
      inst.setState("running");
    else {
      inst.setState("notStarted");
    }
    inst.setUrgency(urgency);

    this.processInstMgr.save(inst);

    inst = this.processInstMgr.loadByKey(inst.getProcessInstId());
    return inst;
  }

  public EIActivityInst newActivityInstByActivityDefId(String processInstId, String activityDefId)
    throws BPMException
  {
    EIActivityInst actInst = this.activityInstMgr.createActivityInstance();
    EIProcessInst inst = this.processInstMgr.loadByKey(processInstId);
    String verId = inst.getProcessDefVersionId();

    EIActivityDef actDef = this.activityDefMgr
      .loadByKey(activityDefId);
    if (actDef == null) {
      return null;
    }

    actInst.setActivityInstId(UUIDGenerator.genUUID());
    actInst.setActivityDefId(actDef.getActivityDefId());
    actInst.setProcessDefId(verId);
    actInst.setProcessInstId(processInstId);
    actInst.setArrivedTime(new Date());
    actInst.setDealMethod("NORMAL");
    actInst.setReceiveMethod(actDef.getRouteBackMethod());
    actInst.setState("notStarted");
    actInst.setRunStatus("NORMAL");
    this.activityInstMgr.save(actInst);
    return actInst;
  }

  public EIActivityInst startProcessInst(String processInstId)
    throws BPMException
  {
    this.log.debug("starting process instance " + processInstId);

    EIProcessInst inst = this.processInstMgr.loadByKey(processInstId);
    if (inst == null) {
      throw new BPMException("The process instance not found, Id is " + 
        processInstId);
    }
    EIActivityInst actInst = this.activityInstMgr.createActivityInstance();
    String verId = inst.getProcessDefVersionId();

    EIActivityDef actDef = 
      getFirstActivityDefInProcess(verId);
    if (actDef == null) {
      return null;
    }

    actInst.setActivityInstId(UUIDGenerator.genUUID());
    actInst.setActivityDefId(actDef.getActivityDefId());
    actInst.setProcessDefId(verId);
    actInst.setProcessInstId(inst.getProcessInstId());
    actInst.setArrivedTime(new Date());
    actInst.setDealMethod("NORMAL");
    actInst.setReceiveMethod("SEND");
    actInst.setState("notStarted");
    actInst.setRunStatus("processNotStarted");

    this.activityInstMgr.save(actInst);

    Date start = new Date(System.currentTimeMillis());
    EIProcessDefVersion procDefVer = inst.getProcessDefVersion();
    inst.setStartTime(start);
    if (procDefVer.getLimit() != 0) {
      inst.setLimitTime(
        TimeUtility.roll(start, procDefVer.getLimit(), 
        procDefVer.getDurationUnit()));
    }
    this.processInstMgr.save(inst);

    actInst = this.activityInstMgr.loadByKey(actInst.getActivityInstId());
    return actInst;
  }

  public EIActivityDef getFirstActivityDefInProcess(String processDefVersionId)
    throws BPMException
  {
    EIProcessDefVersion eiProcessDefVersion = this.processDefVerMgr.loadByKey(processDefVersionId);
    RouteConditionFilter routeConditionFilter = new RouteConditionFilter(new HashMap());
    if (eiProcessDefVersion == null) {
      throw new BPMException("The eiProcessDefVersion  not found, Id is " + 
        processDefVersionId);
    }

    EIActivityDef eiActivityDef = this.activityDefMgr.getFirstActivityDefInProcess(eiProcessDefVersion.getProcessDefVersionId());

    if (eiActivityDef.getImplementation().equals("Tool")) {
      String where = " where FROMACTIVITYDEF_ID = '" + 
        eiActivityDef.getActivityDefId() + "' and ROUTEDIRECTION='" + 
        "FORWARD" + "'";

      where = where + " order by ROUTEORDER";
      List routeList = this.routeDefMgr.loadByWhere(where);

      Iterator it = routeList.iterator();
      List result = new ArrayList();

      if (routeConditionFilter == null) {
        result = routeList;
      } else {
        result = new ArrayList();
        while (it.hasNext()) {
          EIRouteDef routeDef = (EIRouteDef)it.next();
          if (routeConditionFilter.filterObject(routeDef, this.systemCode)) {
            result.add(routeDef);
          }
        }
      }

      if (result.size() == 0) {
        throw new BPMException("The startActivity  not found, the express par no ActivityDef can start");
      }
      eiActivityDef = ((EIRouteDef)result.get(0)).getToActivityDef();
    }

    return eiActivityDef;
  }

  public ReturnType startActivityInst(String activityInstId)
    throws BPMException
  {
    this.log.debug("starting Activity " + activityInstId);
    EIActivityInst actInst = this.activityInstMgr.loadByKey(activityInstId);
    if (actInst == null) {
      throw new BPMException("The activity instance not found, Id is " + 
        activityInstId);
    }
    actInst.setStartTime(new Date());

    Date start = new Date(System.currentTimeMillis());
    EIActivityDef actDef = actInst.getActivityDef();
    if (actDef == null) {
      throw new BPMException("The activity definition not found.");
    }
    actInst.setStartTime(start);
    if (actDef.getLimit() != 0) {
      actInst.setLimitTime(
        TimeUtility.roll(start, actDef.getLimit(), 
        actDef.getDurationUnit()));
    }

    if (actDef.getAlertTime() != 0) {
      actInst.setAlertTime(
        TimeUtility.roll(start, actDef.getLimit() - actDef.getAlertTime(), 
        actDef.getDurationUnit()));
    }
    actInst.setState("running");

    this.activityInstMgr.instantiateExtAttribute(actInst);
    this.activityInstMgr.save(actInst);

    EIProcessInst procInst = actInst.getProcessInst();
    if ((procInst != null) && 
      ("notStarted".equals(procInst.getState()))) {
      updateProcessState(procInst.getProcessInstId(), 
        "running");
    }

    return new ReturnType(1);
  }

  public ReturnType updateProcessState(String processInstId, String state)
    throws BPMException
  {
    EIProcessInst inst = this.processInstMgr.loadByKey(processInstId);
    if (inst == null) {
      throw new BPMException("The specified process instance not found: " + 
        processInstId);
    }
    String oldProcessState = inst.getState();
    if ((state != null) && (!state.equals(oldProcessState))) {
      inst.setState(state);
      this.processInstMgr.save(inst);
    }

    return new ReturnType(1);
  }

  public EIActivityInstHistory saveActivityHistoryInst(String activityInstId)
    throws BPMException
  {
    EIActivityInst from = this.activityInstMgr.loadByKey(activityInstId);
    if (from == null) {
      throw new BPMException("The activity instance not found, Id is " + 
        activityInstId);
    }
    EIActivityInstHistory history = null;

    history = this.activityInstHistoryMgr.saveActivityInstAsHistory(from);

    List routeInsts = this.routeInstMgr.getRouteInsts(from);
    for (int i = 0; i < routeInsts.size(); i++) {
      EIRouteInst routeInst = (EIRouteInst)routeInsts.get(i);
      if (routeInst != null) {
        routeInst.setToActivityId(history.getActivityHistoryId());
        String direction = from.getReceiveMethod();
        if (direction != null) {
          if (direction.equals("SEND")) {
            routeInst
              .setRouteDirection("FORWARD");
          }
          else if (direction
            .equals("BACK")) {
            routeInst
              .setRouteDirection("BACK");
          }
          else if (direction
            .equals("SPECIAL")) {
            routeInst
              .setRouteDirection("SPECIAL");
          }
        }
        routeInst.setRouteType("HISTORY");
        routeInst.setRouteTime(new Date(System.currentTimeMillis()));

        this.routeInstMgr.save(routeInst);
      }

    }

    if (!from.getDealMethod().equals("SPLITED")) {
      from.setDealMethod("NORMAL");
      from.setReceiveMethod("SEND");
      from.setRunStatus("NORMAL");
      from.setArrivedTime(new Date());
      from.setState("notStarted");
      this.activityInstMgr.save(from);
    }

    EIRouteInst routeInst = this.routeInstMgr.createRouteInst();
    routeInst.setRouteInstId(UUIDGenerator.genUUID());
    routeInst.setProcessInstId(from.getProcessInstId());
    routeInst.setFromActivityId(history.getActivityHistoryId());
    routeInst.setToActivityId(from.getActivityInstId());
    routeInst.setRouteType("ACTIVITY");
    this.routeInstMgr.save(routeInst);

    return history;
  }

  public EIActivityInst getSplitActivityInst(String activityHistoryInstId, boolean isnew) throws BPMException
  {
    EIActivityInstHistory his = this.activityInstHistoryMgr.loadByKey(activityHistoryInstId);

    if (!his.getDealMethod().equals("SPLITED")) {
      List historyList = getLastActivityInstHistoryListByActvityInst(his.getActivityInstId(), true);
      for (int i = 0; i < historyList.size(); i++) {
        EIActivityInstHistory ahis = (EIActivityInstHistory)historyList.get(i);
        if (his.getDealMethod().equals("SPLITED"));
        activityHistoryInstId = his.getActivityHistoryId();
        his = ahis;
      }
    }

    EIActivityInst inst = null;

    if (isnew) {
      inst = (EIActivityInst)copyActivityInst(activityHistoryInstId, 1).get(0);
    } else {
      inst = this.activityInstMgr.loadByKey(his.getActivityInstId());
      inst.setCanTakeBack("NO");
      inst.setState("notStarted");
      inst.setRunStatus("NORMAL");
      inst.setReceiveMethod("RESEND");
      inst.setDealMethod("NORMAL");
    }

    return inst;
  }

  public List<EIActivityInst> splitActivityInst(String activityInstId, int count, String activityHistoryInstId)
    throws BPMException
  {
    List result = new ArrayList();

    if (count > 0) {
      EIActivityInst original = this.activityInstMgr.loadByKey(activityInstId);
      if (original == null) {
        throw new BPMException(
          "The activity instance not found, Id is " + 
          activityInstId);
      }

      for (int i = 0; i < count; i++) {
        EIActivityInst inst = this.activityInstMgr
          .copyActivityInst(original);
        result.add(inst);

        EIRouteInst routeInst = this.routeInstMgr.createRouteInst();
        routeInst.setRouteInstId(UUIDGenerator.genUUID());
        routeInst.setProcessInstId(inst.getProcessInstId());
        routeInst.setFromActivityId(activityHistoryInstId);
        routeInst.setToActivityId(inst.getActivityInstId());
        routeInst.setRouteType("ACTIVITY");
        this.routeInstMgr.save(routeInst);
      }

      EIActivityInstHistory history = this.activityInstHistoryMgr.loadByKey(activityHistoryInstId);
      history.setDealMethod("SPLITED");
      this.activityInstHistoryMgr.save(history);

      original.setCanTakeBack("NO");
      saveActivityHistoryInst(activityInstId);
      original.setDealMethod("SPLITED");
      original.setState("suspended");
      this.activityInstMgr.save(original);

      EIProcessInst procInst = original.getProcessInst();
      procInst.setCopyNumber(count);
      this.processInstMgr.save(procInst);
    }
    else
    {
      throw new BPMException("The copy number should be bigger than 0!");
    }
    return result;
  }

  public List<EIActivityInst> copyActivityInst(String activityHistoryInstId, int count)
    throws BPMException
  {
    List result = new ArrayList();
    if (count > 0) {
      EIActivityInstHistory hisOriginal = this.activityInstHistoryMgr.loadByKey(activityHistoryInstId);
      EIActivityInst original = hisOriginal.getActivityInst();
      if (original == null) {
        throw new BPMException(
          "The activity instance not found, Id is " + 
          activityHistoryInstId);
      }
      boolean splited = false;
      if (original.getDealMethod().equals("SPLITED")) {
        splited = true;
      }

      original.setCanTakeBack("NO");
      original.setState("notStarted");
      original.setRunStatus("NORMAL");
      if (!splited)
        original.setReceiveMethod("READ");
      else {
        original.setReceiveMethod("SEND");
      }
      original.setDealMethod("NORMAL");

      for (int i = 0; i < count; i++) {
        EIActivityInst inst = this.activityInstMgr
          .copyActivityInst(original);
        result.add(inst);

        EIRouteInst routeInst = this.routeInstMgr.createRouteInst();
        routeInst.setRouteInstId(UUIDGenerator.genUUID());
        routeInst.setProcessInstId(inst.getProcessInstId());
        routeInst.setFromActivityId(activityHistoryInstId);
        routeInst.setToActivityId(inst.getActivityInstId());
        routeInst.setRouteType("ACTIVITY");
        this.routeInstMgr.save(routeInst);
      }

      if (splited) {
        original.setCanTakeBack("NO");
        original.setState("suspended");
        original.setRunStatus("NORMAL");
        original.setReceiveMethod("SEND");
        original.setDealMethod("SPLITED");
      }

      this.activityInstMgr.save(original);
    } else {
      throw new BPMException("The copy number should be bigger than 0!");
    }
    return result;
  }

  public List<EIRouteDef> getNextRoutes(String activityInstID, Condition condition, Filter routeFilter)
    throws BPMException
  {
    EIActivityInst actInst = this.activityInstMgr.loadByKey(activityInstID);
    if (actInst == null) {
      throw new BPMException("The activity instance not found, Id is " + 
        activityInstID);
    }

    String where = " where FROMACTIVITYDEF_ID = '" + 
      actInst.getActivityDefId() + "' and ROUTEDIRECTION='" + 
      "FORWARD" + "'";
    if (condition != null) {
      String conditionStr = condition.makeConditionString();
      if ((conditionStr != null) && (!conditionStr.equals(""))) {
        where = where + " and " + conditionStr;
      }
    }

    where = where + " order by ROUTEORDER";
    List routeList = this.routeDefMgr.loadByWhere(where);

    Iterator it = routeList.iterator();
    List result = new ArrayList();

    if (routeFilter == null) {
      result = routeList;
    } else {
      result = new ArrayList();
      while (it.hasNext()) {
        EIRouteDef routeDef = (EIRouteDef)it.next();
        if (routeFilter.filterObject(routeDef, this.systemCode)) {
          result.add(routeDef);
        }

      }

    }

    return result;
  }

  public List<EIActivityInst> forecastCombinableActivityInsts(String actInstId, String actDefId)
    throws BPMException
  {
    EIActivityInst activityInst = this.activityInstMgr.loadByKey(actInstId);
    if (activityInst == null) {
      throw new BPMException("The activity instance not found! Id is:" + 
        actInstId);
    }
    EIProcessInst processInst = this.processInstMgr.loadByKey(
      activityInst.getProcessInstId());
    if (processInst == null) {
      throw new BPMException("The process instance not found!");
    }
    List activityInsts = null;
    int copies = processInst.getCopyNumber();
    if (copies <= 1) {
      return new ArrayList();
    }
    EIActivityDef actDef = this.activityDefMgr.loadByKey(actDefId);
    if (actDef == null) {
      throw new BPMException("The activity definition not found!");
    }

    String join = actDef.getJoin();
    if ((join != null) && (!join.equals("AND"))) {
      return new ArrayList();
    }
    String where = " WHERE PROCESSINST_ID='" + 
      processInst.getProcessInstId() + "' order by ACTIVITYINST_ID";
    return this.activityInstMgr.loadByWhere(where);
  }

  public List combinableActivityInsts(String actInstId)
    throws BPMException
  {
    EIActivityInst activityInst = this.activityInstMgr.loadByKey(actInstId);

    if (activityInst == null) {
      throw new BPMException("The activity instance not found! Id is:" + 
        actInstId);
    }
    EIProcessInst processInst = this.processInstMgr.loadByKey(
      activityInst.getProcessInstId());
    if (processInst == null) {
      throw new BPMException("The process instance not found!");
    }
    List activityInsts = null;

    EIActivityDef actDef = activityInst.getActivityDef();
    if (actDef == null) {
      throw new BPMException("The activity definition not found!");
    }

    String join = actDef.getJoin();

    if ((join != null) && (!join.equals("AND"))) {
      return new ArrayList();
    }

    List hisList = getLastSplitActivityInstHistoryByActvityInst(actInstId);

    if (hisList.size() == 0)
      activityInsts = new ArrayList();
    else {
      activityInsts = getActivityInstListByOutActvityInstHistory(((EIActivityInstHistory)hisList.get(hisList.size() - 1)).getActivityHistoryId());
    }

    return activityInsts;
  }

  @Deprecated
  public String forecastSuspendOrCombine(String actInstId, String actDefId)
    throws BPMException
  {
    return suspendOrCombine(actInstId, actDefId);
  }

  public String suspendOrCombine(String actInstId)
    throws BPMException
  {
    return suspendOrCombine(actInstId, null);
  }

  public String suspendOrCombine(String actInstId, String activityDefId) throws BPMException
  {
    EIActivityInst activityInst = this.activityInstMgr.loadByKey(actInstId);
    if (activityInst == null) {
      throw new BPMException("The activity instance not found!");
    }
    EIProcessInst processInst = this.processInstMgr.loadByKey(
      activityInst.getProcessInstId());
    if (processInst == null) {
      throw new BPMException("The process instance not found!");
    }
    if (activityDefId == null) {
      activityDefId = activityInst.getActivityDefId();
    }

    List hisList = getLastSplitActivityInstHistoryByActvityInst1(actInstId);
    List activityInsts = new ArrayList();

    if (hisList.size() > 0) {
    	for(int i=0; i<hisList.size(); i++){
    		activityInsts = getActivityInstListByOutActvityInstHistory(((EIActivityInstHistory)hisList.get(i)).getActivityHistoryId());
    		for (Iterator iter = activityInsts.iterator(); iter.hasNext(); ) {
		      EIActivityInst inst = (EIActivityInst)iter.next();

		      if ((!inst.getActivityDefId()
		        .equals(activityDefId)) && (!inst.getDealMethod().equals("SPLITED"))) {
		        return "SUSPEND";
		      }
		    }
    	}
    }


    return "COMBINE";
  }

  public EIActivityInst routeTo(String fromActivityInstId, String toActivityDefId)
    throws BPMException
  {
    EIActivityInst inst = this.activityInstMgr.loadByKey(fromActivityInstId);
    if (inst == null) {
      throw new BPMException("The activity instance not found!");
    }
    EIActivityDef toDef = this.activityDefMgr.loadByKey(toActivityDefId);
    if (toDef == null) {
      throw new BPMException("The activity definition not found!");
    }

    if ("LAST".equals(toActivityDefId))
    {
      completeProcessInst(inst.getProcessInstId());
      return null;
    }

    EIProcessInst processInst = inst.getProcessInst();
    inst.setActivityDefId(toActivityDefId);
    inst.setArrivedTime(new Date());
    if (!inst.getDealMethod().equals("SPLITED"))
    {
      inst.setActivityDefId(toActivityDefId);
      inst.setArrivedTime(new Date());
      inst.setDealMethod("NORMAL");
      inst.setReceiveMethod("SEND");
      inst.setRunStatus("NORMAL");
      inst.setState("notStarted");
    }
    if (processInst == null) {
      throw new BPMException("The process instance not found!");
    }

    this.activityInstMgr.save(inst);

    return inst;
  }

  public boolean canRouteBack(String activityInstId)
    throws BPMException
  {
    boolean can = false;
    EIActivityInst inst = this.activityInstMgr.loadByKey(activityInstId);

    if (!"running".equals(inst.getState())) {
      return false;
    }
    EIActivityDef def = inst.getActivityDef();
    String canRouteBack = def.getCanRouteBack();

    if ("YES".equals(canRouteBack)) {
      can = true;
    }

    return can;
  }

  public List<EIActivityInstHistory> getRouteBacks(String activityInstId, Filter routeFilter)
    throws BPMException
  {
    EIActivityInst inst = this.activityInstMgr.loadByKey(activityInstId);
    if (inst == null) {
      throw new BPMException("The activity instance not found!");
    }
    EIActivityDef def = inst.getActivityDef();
    if (def == null) {
      throw new BPMException("The activity definition not found!");
    }

    List result = new ArrayList();
    String routeBackMethod = def.getRouteBackMethod();
    if (routeBackMethod == null) {
      routeBackMethod = "ANY";
    }

    if ("LAST".equals(routeBackMethod)) {
      List routeInsts = this.routeInstMgr.getRouteInsts(inst);
      for (Iterator iter = routeInsts.iterator(); iter.hasNext(); ) {
        EIRouteInst routeInst = (EIRouteInst)iter.next();
        String activityInstHistoryId = routeInst.getFromActivityId();
        try {
          EIActivityInstHistory history = this.activityInstHistoryMgr
            .loadByKey(activityInstHistoryId);
          result.add(history);
        }
        catch (BPMException localBPMException)
        {
        }
      }
    }
    else if ("ANY".equals(routeBackMethod))
    {
      result = getActivityInstHistoryListByActvityInst(activityInstId);
    }
    else if ("SPECIFY".equals(routeBackMethod))
    {
      List routeDefs = this.routeDefMgr
        .loadByWhere(" where FROMACTIVITYDEF_ID='" + 
        def.getActivityDefId() + 
        "' and ROUTEDIRECTION='" + 
        "BACKWARD" + "'");

      List historys = getActivityInstHistoryListByActvityInst(activityInstId);
      Map map = new HashMap();
      for (Iterator it = routeDefs.iterator(); it.hasNext(); )
      {
        EIRouteDef routeDef = (EIRouteDef)it.next();
        if ((routeFilter == null) || 
          (routeFilter.filterObject(routeDef, this.systemCode)))
        {
          String toActivityDefId = routeDef.getToActivityDefId();
          for (Iterator iter = historys.iterator(); iter
            .hasNext(); )
          {
            EIActivityInstHistory history = 
              (EIActivityInstHistory)iter
              .next();
            if (toActivityDefId.equals(
              history.getActivityDefId())) {
              map.put(toActivityDefId, history);
              break;
            }
          }
        }
      }
      List historyList = new ArrayList(map.values());

      result.addAll(historyList);
    }

    return result;
  }

  public EIActivityInst routeBack(String activityInstId, String activityInstHistoryId)
    throws BPMException
  {
    EIActivityInst inst = this.activityInstMgr.loadByKey(activityInstId);
    if (inst == null) {
      throw new BPMException("The activity instance not found!");
    }
    EIActivityInstHistory history = this.activityInstHistoryMgr
      .loadByKey(activityInstHistoryId);
    if (history == null) {
      throw new BPMException("The history activity instance not found!");
    }
    EIActivityDef toDef = history.getActivityDef();
    if (toDef == null) {
      throw new BPMException("The activity definition not found!");
    }

    inst.setActivityDefId(toDef.getActivityDefId());
    inst.setArrivedTime(new Date());
    inst.setDealMethod("NORMAL");
    inst.setReceiveMethod("BACK");
    inst.setRunStatus("NORMAL");
    inst.setState("notStarted");

    EIProcessInst processInst = inst.getProcessInst();
    if (processInst == null) {
      throw new BPMException("The process instance not found!");
    }
    if (processInst.getCopyNumber() > 1) {
      EIActivityDef actDef = inst.getActivityDef();
      if (actDef == null) {
        throw new BPMException("The activity definition not found!");
      }
      String join = actDef.getJoin();
      if ((join != null) && (join.equals("AND"))) {
        inst.setState("suspended");
        this.activityInstMgr.save(inst);
      }

    }

    List activityInsts = combinableActivityInsts(inst.getActivityInstId());
    if (activityInsts.size() > 1)
    {
      String suspendOrCombine = suspendOrCombine(inst.getActivityInstId());
      if ("SUSPEND".equals(suspendOrCombine)) {
        inst.setState("suspended");
      }
      else if ("COMBINE"
        .equals(suspendOrCombine)) {
        return combineActivityInsts(activityInsts);
      }
    }
    this.activityInstMgr.save(inst);

    return inst;
  }

  public boolean canTakeBack(String activityInstId)
    throws BPMException
  {
    if ((activityInstId == null) || (activityInstId.equals(""))) {
      return false;
    }
    EIActivityInst inst = this.activityInstMgr.loadByKey(activityInstId);
    if (inst == null) {
      throw new BPMException("The activity instance not found!");
    }

    boolean can = false;
    String canTackBack = inst.getCanTakeBack();

    if (("notStarted".equals(inst.getState())) && 
      (!"SPLITED".equals(inst.getDealMethod())))
    {
      if ((canTackBack == null) || 
        (!canTackBack.equals("NO"))) {
        can = true;
      }
    }

    return can;
  }

  public ReturnType tackBack(String activityInstId, Map ctx)
    throws BPMException
  {
    EIActivityInst inst = this.activityInstMgr.loadByKey(activityInstId);

    if (inst == null) {
      throw new BPMException("The activity instance not found!");
    }

    List routeList = this.routeInstMgr.loadByWhere(" where TOACTIVITY_ID='" + 
      activityInstId + "'");
    if ((routeList == null) || (routeList.size() != 1)) {
      throw new BPMException(
        "can not found the corresponding route instance!");
    }
    EIRouteInst routeInst = (EIRouteInst)routeList.get(0);

    String previousActivityInstHistoryId = routeInst.getFromActivityId();

    this.routeInstMgr.deleteByWhere(" where TOACTIVITY_ID='" + activityInstId + "'");

    String performSequence = (String)ctx.get("PERFORMSEQUENCE");
    if (!performSequence.equals("MEANWHILE"));
    List routeInsts = this.routeInstMgr.loadByWhere(" where FROMACTIVITY_ID='" + previousActivityInstHistoryId + "'");
    if (routeInsts.size() > 0)
    {
      deleteActivityInst(activityInstId);
      this.activityInstHistoryMgr.deleteByWhere("where ACTIVITYINST_ID='" + activityInstId + "'");
    }

    EIActivityInstHistory instHistory = this.activityInstHistoryMgr.loadByKey(previousActivityInstHistoryId);
    if (routeInsts.size() == 1)
    {
      EIActivityInst splitActivityInst = this.activityInstMgr.loadByKey(instHistory.getActivityInstId());
      if (splitActivityInst != null) {
        splitActivityInst.setArrivedTime(new Date());
        splitActivityInst.setDealMethod("NORMAL");
        splitActivityInst.setReceiveMethod("SEND");
        splitActivityInst.setRunStatus("NORMAL");
        splitActivityInst.setState("notStarted");
        splitActivityInst.setActivityDefId(instHistory.getActivityDefId());

        splitActivityInst.setCanTakeBack("YES");
        this.activityInstMgr.save(splitActivityInst);
      }

    }

    List routeActivityList = this.routeInstMgr.loadByWhere(" where FROMACTIVITY_ID='" + 
      previousActivityInstHistoryId + "' AND ROUTETYPE='ACTIVITY'");

    if (routeActivityList.size() == 0)
    {
      List hisList = getLastSplitActivityInstHistoryByActvityInst(instHistory.getActivityInstId());
      if (hisList.size() > 0)
      {
        List activityInsts = (List)ctx.get("combinableActivityInsts");

        String suspendOrCombine = (String)ctx.get("suspendOrCombine");

        if (activityInsts.size() != 0) {
          if (suspendOrCombine.equals("SUSPEND"))
          {
            inst.setState("suspended");
            inst.setRunStatus("NORMAL");
          }
          else if (suspendOrCombine
            .equals("COMBINE")) {
            inst = combineActivityInsts(activityInsts);
            inst.setArrivedTime(new Date());
            inst.setDealMethod("NORMAL");
            inst.setReceiveMethod("SEND");
            inst.setRunStatus("NORMAL");
            inst.setState("notStarted");

            inst.setCanTakeBack("YES");
          }
          this.activityInstMgr.save(inst);
        }
        else {
          EIActivityInstHistory his = (EIActivityInstHistory)hisList.get(hisList.size() - 1);
          this.routeInstMgr.deleteByWhere(" where FROMACTIVITY_ID='" + his.getActivityHistoryId() + "'");
          this.activityInstHistoryMgr.delete(his);
          EIProcessInst eiProcessInst = this.processInstMgr.loadByKey(his.getProcessInstId());
          List list = eiProcessInst.getActivityInstList();
          for (int k = 0; k < list.size(); k++) {
            EIActivityInst activityInst = (EIActivityInst)list.get(k);
            if ((activityInst.getState().equals("suspended")) && (!activityInst.getDealMethod().equals("SPLITED"))) {
              activityInst.setArrivedTime(new Date());
              activityInst.setDealMethod("NORMAL");
              activityInst.setReceiveMethod("SEND");
              activityInst.setRunStatus("NORMAL");
              activityInst.setState("notStarted");

              activityInst.setCanTakeBack("YES");
              this.activityInstMgr.save(activityInst);
            }

          }

        }

      }

    }

    return new ReturnType(1);
  }

  public EIActivityInstHistory endRead(String activityInstID, Map ctx)
    throws BPMException
  {
    EIActivityInst actInst = this.activityInstMgr.loadByKey(activityInstID);
    if (actInst == null) {
      throw new BPMException("The activity instance not found!");
    }
    actInst.setState("ENDREAD");
    actInst.setReceiveMethod("READ");
    this.activityInstMgr.save(actInst);
    EIActivityInstHistory activityInstHistory = this.activityInstHistoryMgr.saveActivityInstAsHistory(actInst);

    return activityInstHistory;
  }

  public ReturnType clearHistory(String activityInstHistoryID, Map ctx)
    throws BPMException
  {
    EIActivityInstHistory history = this.activityInstHistoryMgr
      .loadByKey(activityInstHistoryID);
    history.setRunStatus("CLEAR");
    this.activityInstHistoryMgr.save(history);
    return new ReturnType(1);
  }

  public ReturnType deleteHistory(String activityInstHistoryID, Map ctx)
    throws BPMException
  {
    return new ReturnType(1);
  }

  public ReturnType restoreHistory(String activityInstHistoryID, Map ctx)
    throws BPMException
  {
    return new ReturnType(1);
  }

  public boolean canSignReceive(String activityInstID)
    throws BPMException
  {
    EIActivityInst inst = this.activityInstMgr.loadByKey(activityInstID);
    if (inst == null) {
      throw new BPMException("The activity instance not found!");
    }
    boolean can = false;
    if (("notStarted".equals(inst.getState())) && 
      (!"SPLITED".equals(inst.getDealMethod()))) {
      can = true;
    }
    return can;
  }

  public ReturnType signReceive(String activityInstID)
    throws BPMException
  {
    return startActivityInst(activityInstID);
  }

  public EIActivityInst combineActivityInsts(List<EIActivityInst> activityInsts)
    throws BPMException
  {
    List activityInstIds = new ArrayList();
    for (int k = 0; k < activityInsts.size(); k++) {
      activityInstIds.add(
        ((EIActivityInst)activityInsts.get(k)).getActivityInstId());
    }
    String[] activityInstIdArr = 
      (String[])activityInstIds
      .toArray(new String[0]);
    return combineActivityInsts(activityInstIdArr);
  }

  public EIActivityInst combineActivityInsts(String[] activityInsts)
    throws BPMException
  {
    if ((activityInsts == null) || (activityInsts.length < 1)) {
      throw new BPMException("can not combine activity instances!");
    }
    EIActivityInst inst = this.activityInstMgr.loadByKey(activityInsts[0]);

    if (inst == null) {
      throw new BPMException("Activity instance not found!");
    }
    if (!inst.getDealMethod().equals("SPLITED")) {
      return inst;
    }

    EIProcessInst processInst = inst.getProcessInst();
    if (processInst == null) {
      throw new BPMException("The process instance not found!");
    }
    int index = 0;

    inst.setArrivedTime(new Date());
    inst.setDealMethod("NORMAL");
    inst.setReceiveMethod("SEND");
    inst.setRunStatus("NORMAL");
    inst.setState("notStarted");

    inst.setCanTakeBack("YES");

    int i = 0; for (int n = activityInsts.length; i < n; i++) {
      if (i != index) {
        EIActivityInst tempInst = this.activityInstMgr.loadByKey(activityInsts[i]);
        List attributes = tempInst.getAllAttribute();
        for (Iterator iter = attributes.iterator(); iter.hasNext(); ) {
          EIAttributeInst attribute = (EIAttributeInst)iter.next();
          inst.setAttribute(attribute.getName(), attribute);
        }

        deleteActivityInstAndResetRoute(tempInst.getActivityInstId(), 
          inst.getActivityInstId());
      }

    }

    this.activityInstMgr.save(inst);

    EIActivityDef fristActivityDef = getFirstActivityDefInProcess(inst.getProcessInst().getProcessDefVersionId());

    if (inst.getActivityDefId().equals(fristActivityDef.getActivityDefId())) {
      updateProcessState(
        inst.getProcessInstId(), 
        "notStarted");
    }
    processInst.setCopyNumber(1);
    this.processInstMgr.save(processInst);
    return inst;
  }

  public ReturnType suspendActivityInst(String activityInstID)
    throws BPMException
  {
    EIActivityInst actInst = this.activityInstMgr.loadByKey(activityInstID);
    if (actInst == null) {
      throw new BPMException("The activity instance not found!");
    }
    actInst.setState("suspended");
    this.activityInstMgr.save(actInst);

    return new ReturnType(1);
  }

  public ReturnType resumeActivityInst(String activityInstID)
    throws BPMException
  {
    EIActivityInst actInst = this.activityInstMgr.loadByKey(activityInstID);
    if (actInst == null) {
      throw new BPMException("The activity instance not found!");
    }
    actInst.setState("running");
    this.activityInstMgr.save(actInst);

    return new ReturnType(1);
  }

  public ReturnType suspendProcessInst(String processInstID)
    throws BPMException
  {
    return updateProcessState(processInstID, "suspended");
  }

  public ReturnType resumeProcessInst(String processInstID)
    throws BPMException
  {
    return updateProcessState(processInstID, "running");
  }

  public ReturnType abortProcessInst(String processInstID)
    throws BPMException
  {
    List activityInsts = this.activityInstMgr.loadByProcessInstId(processInstID);
    if (activityInsts == null) {
      return new ReturnType(-1);
    }
    for (Iterator iter = activityInsts.iterator(); iter.hasNext(); ) {
      EIActivityInst activityInst = (EIActivityInst)iter.next();
      String activityInstId = activityInst.getActivityInstId();
      saveActivityHistoryInst(activityInstId);
      deleteActivityInst(activityInstId);
    }

    return updateProcessState(processInstID, "aborted");
  }

  public ReturnType completeProcessInst(String processInstID)
    throws BPMException
  {
    EIProcessInst procInst = this.processInstMgr.loadByKey(processInstID);
    if (procInst == null) {
      throw new BPMException("The process instance '" + processInstID + 
        "' not found!");
    }
    procInst.setEndTime(new Date(System.currentTimeMillis()));
    return updateProcessState(processInstID, "completed");
  }

  public ReturnType deleteProcessInst(String processInstID)
    throws BPMException
  {
    String where = " where PROCESSINST_ID='" + processInstID + "'";
    this.activityInstMgr.deleteByWhere(where);
    this.activityInstHistoryMgr.deleteByWhere(where);
    this.routeInstMgr.deleteByWhere(where);

    this.processInstMgr.deleteByKey(processInstID);

    return new ReturnType(1);
  }

  public List<EIActivityInstHistory> getLastSplitActivityInstHistoryByActvityInst(String activityInstId)
    throws BPMException
  {
    List hisList = getLastActivityInstHistoryListByActvityInst(activityInstId, true);
    List splitList = new ArrayList();
    for (int i = 0; i < hisList.size(); i++) {
      EIActivityInstHistory his = (EIActivityInstHistory)hisList.get(i);
      if (his.getDealMethod().equals("SPLITED")) {
        splitList.add(his);
      }
    }
    return splitList;
  }
  
  public List<EIActivityInstHistory> getLastSplitActivityInstHistoryByActvityInst1(String activityInstId)
  throws BPMException
	{
	 // List hisList = getLastActivityInstHistoryListByActvityInst(activityInstId, true);
	  EIActivityInst actInst = this.activityInstMgr.loadByKey(activityInstId);
	    if (actInst == null) {
	      throw new BPMException("The activity instance '" + activityInstId + 
	        "' not found!");
	    }
	  List hisList = this.getActivityInstHistoryListByProcessInst(actInst.getProcessInstId());
	  List splitList = new ArrayList();
	  for (int i = 0; i < hisList.size(); i++) {
	    EIActivityInstHistory his = (EIActivityInstHistory)hisList.get(i);
	    if (his.getDealMethod().equals("SPLITED")) {
	      splitList.add(his);
	    }
	  }
	  return splitList;
	}

  public List<EIActivityInstHistory> getLastActivityInstHistoryListByActvityInst(String activityInstId)
    throws BPMException
  {
    return getLastActivityInstHistoryListByActvityInst(activityInstId, false);
  }

  public List<EIActivityInstHistory> getLastActivityInstHistoryListByActvityInst(String activityInstId, boolean noSplit)
    throws BPMException
  {
    EIActivityInst actInst = this.activityInstMgr.loadByKey(activityInstId);
    if (actInst == null) {
      throw new BPMException("The activity instance '" + activityInstId + 
        "' not found!");
    }

    List routeInsts = this.routeInstMgr.getRouteInsts(actInst);
    Map historyMap = new HashMap();
    for (Iterator it = routeInsts.iterator(); it.hasNext(); ) {
      EIRouteInst routeInst = (EIRouteInst)it.next();
      String dealMethod = routeInst.getFromActivityHistory().getDealMethod();
      if ((noSplit) || (!dealMethod.equals("SPLITED"))) {
        addActivityInstHistory(historyMap, 
          routeInst.getFromActivityHistory());
      }
    }

    List result = new ArrayList(historyMap.values());
    Collections.sort(result, new ActivityInstHistoryEndTimeComparator());
    return result;
  }

  public List<EIActivityInstHistory> getActivityInstHistoryListByActvityInst(String activityInstId)
    throws BPMException
  {
    return this.activityInstHistoryMgr.loadByWhere(" where ACTIVITYINST_ID='" + 
      activityInstId + "'  order by ENDTIME");
  }

  private void addActivityInstHistory(Map historyMap, EIActivityInstHistory history)
    throws BPMException
  {
    if (history != null) {
      historyMap.put(history.getActivityHistoryId(), history);
      List routes = this.routeInstMgr.loadByWhere(" where TOACTIVITY_ID='" + 
        history.getActivityHistoryId() + "'");
      for (Iterator it = routes.iterator(); it.hasNext(); ) {
        EIRouteInst routeInst = (EIRouteInst)it.next();
        addActivityInstHistory(historyMap, 
          routeInst.getFromActivityHistory());
      }
    }
  }

  public List<EIActivityInstHistory> getActivityInstHistoryListByProcessInst(String processInstId)
    throws BPMException
  {
    return this.activityInstHistoryMgr.loadByWhere(" where PROCESSINST_ID='" + 
      processInstId + "' order by ENDTIME desc");
  }

  public List<EIRouteInst> getRouteInsts(String processInstId)
    throws BPMException
  {
    return this.routeInstMgr.loadByWhere(" where PROCESSINST_ID='" + processInstId + 
      "' order by ROUTETIME");
  }

  public List<EIRouteInst> getActivityInstInRoute(String activityInstId)
    throws BPMException
  {
    return this.routeInstMgr.loadByWhere(" where TOACTIVITY_ID='" + activityInstId + 
      "' order by ROUTETIME");
  }

  public List<EIActivityInst> getActivityInstListByOutActvityInstHistory(String activityInstHistoryId)
    throws BPMException
  {
    List routeList = getActivityInstHistoryOutRoute(activityInstHistoryId);
    List activityInstList = new ArrayList();

    for (int k = 0; k < routeList.size(); k++) {
      EIRouteInst routeInst = (EIRouteInst)routeList.get(k);
      if (routeInst.getRouteType().equals("HISTORY")) {
        EIActivityInstHistory history = this.activityInstHistoryMgr.loadByKey(routeInst.getToActivityId());
        EIActivityInst inst = history.getActivityInst();
        if (inst != null)
          activityInstList.add(inst);
      }
      else
      {
        EIActivityInst inst = this.activityInstMgr.loadByKey(routeInst.getToActivityId());
        if (inst != null) {
          activityInstList.add(inst);
        }
      }
    }
    return activityInstList;
  }

  public List<EIRouteInst> getActivityInstHistoryOutRoute(String activityInstHistoryId)
    throws BPMException
  {
    return this.routeInstMgr.loadByWhere(" where FROMACTIVITY_ID='" + activityInstHistoryId + 
      "' order by ROUTETIME");
  }

  ReturnType deleteActivityInst(String activityInstID)
    throws BPMException
  {
    String where = " where TOACTIVITY_ID='" + activityInstID + 
      "' and ROUTETYPE='" + "ACTIVITY" + "'";
    this.routeInstMgr.deleteByWhere(where);

    this.activityInstMgr.deleteByKey(activityInstID);

    return new ReturnType(1);
  }

  private void deleteActivityInstAndResetRoute(String deleteActivityInstId, String actInstId) throws BPMException
  {
    String where = " where TOACTIVITY_ID='" + deleteActivityInstId + 
      "' and ROUTETYPE='" + "ACTIVITY" + "'";
    List routeInsts = this.routeInstMgr.loadByWhere(where);
    for (Iterator it = routeInsts.iterator(); it.hasNext(); ) {
      EIRouteInst routeInst = (EIRouteInst)it.next();
      routeInst.setToActivityId(actInstId);
      this.routeInstMgr.save(routeInst);
    }

    this.activityInstMgr.deleteByKey(deleteActivityInstId);
  }
  private class ActivityInstHistoryEndTimeComparator implements Comparator {
    private ActivityInstHistoryEndTimeComparator() {
    }
    public int compare(Object o1, Object o2) { Date time1 = ((EIActivityInstHistory)o1).getEndTime();
      Date time2 = ((EIActivityInstHistory)o2).getEndTime();
      if ((time1 == null) || (time2 == null)) {
        return 0;
      }
      if (time1.getTime() == time2.getTime()) {
        return 0;
      }
      if (time1.getTime() > time2.getTime()) {
        return 1;
      }
      return -1;
    }
  }
}