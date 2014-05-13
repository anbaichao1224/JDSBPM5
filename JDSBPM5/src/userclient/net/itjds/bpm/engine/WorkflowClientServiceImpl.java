package net.itjds.bpm.engine;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import net.itjds.bpm.client.ActivityDef;
import net.itjds.bpm.client.ActivityInst;
import net.itjds.bpm.client.ActivityInstHistory;
import net.itjds.bpm.client.ProcessDef;
import net.itjds.bpm.client.ProcessDefVersion;
import net.itjds.bpm.client.ProcessInst;
import net.itjds.bpm.client.RouteDef;
import net.itjds.bpm.data.DataMap;
import net.itjds.bpm.engine.database.DbManager;
import net.itjds.bpm.engine.database.DbRouteInst;
import net.itjds.bpm.engine.event.EIActivityEvent;
import net.itjds.bpm.engine.event.EIProcessEvent;
import net.itjds.bpm.engine.inter.EIActivityDef;
import net.itjds.bpm.engine.inter.EIActivityDefManager;
import net.itjds.bpm.engine.inter.EIActivityInst;
import net.itjds.bpm.engine.inter.EIActivityInstHistory;
import net.itjds.bpm.engine.inter.EIActivityInstHistoryManager;
import net.itjds.bpm.engine.inter.EIActivityInstManager;
import net.itjds.bpm.engine.inter.EIProcessDef;
import net.itjds.bpm.engine.inter.EIProcessDefManager;
import net.itjds.bpm.engine.inter.EIProcessInst;
import net.itjds.bpm.engine.inter.EIProcessInstManager;
import net.itjds.bpm.engine.inter.EIRouteDef;
import net.itjds.bpm.engine.inter.EIRouteDefManager;
import net.itjds.bpm.engine.proxy.ActivityDefProxy;
import net.itjds.bpm.engine.proxy.ActivityInstHistoryProxy;
import net.itjds.bpm.engine.proxy.ActivityInstProxy;
import net.itjds.bpm.engine.proxy.ProcessDefProxy;
import net.itjds.bpm.engine.proxy.ProcessInstProxy;
import net.itjds.bpm.engine.proxy.RouteDefProxy;
import net.itjds.bpm.engine.proxy.WorkflowListProxy;
import net.itjds.bpm.engine.query.Condition;
import net.itjds.bpm.engine.query.ConditionKey;
import net.itjds.bpm.engine.query.Filter;
import net.itjds.bpm.engine.query.FilterChain;
import net.itjds.common.logging.Log;
import net.itjds.common.logging.LogFactory;
import net.itjds.common.org.base.Person;
import net.itjds.common.util.UtilTimer;
import net.itjds.userclient.bpmlistener.routefitle.RouteConditionFilter;

public class WorkflowClientServiceImpl
  implements WorkflowClientService, Serializable
{
  private static final Log logger = LogFactory.getLog(
    "bpm", WorkflowClientServiceImpl.class);
  private static final int TIMER_LEVEL = 5;
  private String systemCode;
  private ConnectInfo connInfo;
  private BPMSessionHandle sessionHandle;
  private WorkflowServer workflowServer;
  private WorkflowEngine workflowEngine;
  private RightEngine rightEngine;
  private DataEngine dataEngine;
  private FileEngine fileEngine;

  WorkflowClientServiceImpl(BPMSessionHandle sessionHandle, String systemCode)
    throws BPMException
  {
    this.sessionHandle = sessionHandle;
    this.systemCode = systemCode;
    this.workflowServer = WorkflowServer.getInstance();
    this.workflowEngine = WorkflowEngine.getEngine(systemCode);
    this.rightEngine = WorkflowServer.getRigthEngine(systemCode);
    this.dataEngine = WorkflowServer.getDataEngine(systemCode);
    this.fileEngine = WorkflowServer.getFileEngin(systemCode);
  }

  private void fireProcessEvent(EIProcessInst inst, int eventID)
    throws BPMException
  {
    fireProcessEvent(inst, eventID, null);
  }

  private void fireProcessEvent(EIProcessInst inst, int eventID, Map eventContext)
    throws BPMException
  {
    eventContext = fillInUserID(eventContext);
    EIProcessEvent event = new EIProcessEvent(inst, eventID);
    event.setClientService(this);
    event.setContextMap(eventContext);
    EventControl.dispatchProcessEvent(event, getSystemCode());
  }

  private void fireActivityEvent(EIActivityInst inst, int eventID)
    throws BPMException
  {
    fireActivityEvent(inst, eventID, null);
  }

  private void fireActivityEvent(EIActivityInst inst, int eventID, Map eventContext)
    throws BPMException
  {
    eventContext = fillInUserID(eventContext);
    EIActivityEvent event = new EIActivityEvent(inst, eventID);
    event.setClientService(this);
    event.setContextMap(eventContext);
    EventControl.dispatchActivityEvent(event, getSystemCode());
  }

  private void fireActivityEvent(EIActivityInst[] insts, int eventID)
    throws BPMException
  {
    fireActivityEvent(insts, eventID, null);
  }

  private void fireActivityEvent(EIActivityInst[] insts, int eventID, Map eventContext)
    throws BPMException
  {
    eventContext = fillInUserID(eventContext);
    EIActivityEvent event = new EIActivityEvent(insts, eventID);
    event.setClientService(this);
    event.setContextMap(eventContext);
    EventControl.dispatchActivityEvent(event, getSystemCode());
  }

  public String getSystemCode() {
    return this.systemCode;
  }

  public BPMSessionHandle getSessionHandle() {
    return this.sessionHandle;
  }

  public void connect(ConnectInfo connInfo)
    throws BPMException
  {
    this.connInfo = connInfo;
    this.workflowServer.connect(this);
  }

  public ReturnType disconnect()
    throws BPMException
  {
    this.workflowServer.disconnect(this.connInfo, this.sessionHandle);
    this.connInfo = null;
    this.sessionHandle = null;
    return new ReturnType(1);
  }

  public ConnectInfo getConnectInfo() {
    return this.connInfo;
  }

  public List<ProcessDefVersion> getProcessDefVersionList(Condition condition, Filter filter, Map ctx)
    throws BPMException
  {
    UtilTimer timer = new UtilTimer();
    try {
      checkLogined();
      Condition resultCon = condition;
      Filter resultFilter = filter;

      String inSQL = "SELECT BPM_PROCESSDEF.PROCESSDEF_ID FROM BPM_PROCESSDEF WHERE BPM_PROCESSDEF.SYSTEMCODE='" + 
        this.systemCode + "'";
      Condition sysCon = new Condition(
        ConditionKey.PROCESSDEF_VERSION_PROCESSDEF_ID, 
        10, inSQL);
      if (resultCon == null)
        resultCon = sysCon;
      else {
        resultCon.addCondition(sysCon, 100);
      }

      Map rightCtx = fillInUserID(ctx);

      Filter rightFilter = this.rightEngine.getProcessDefListFilter(rightCtx);
      if (rightFilter != null) {
        FilterChain filterChain = new FilterChain();
        filterChain.addFilter(resultFilter);
        filterChain.addFilter(rightFilter);
        resultFilter = filterChain;

        if ((rightFilter instanceof Condition)) {
          resultCon.addCondition((Condition)rightFilter, 
            100);
        }

      }

      List processDefList = this.workflowEngine.getProcessDefVersionList(
        resultCon, resultFilter);
      logger.debug(timer.timerString(5, "getProcessDefList"));
      return new WorkflowListProxy(processDefList, getSystemCode());
    } catch (Exception e) {
      throw new BPMException("getProcessDefList error.", e, 
        1003);
    }
  }

  public List<ProcessDef> getProcessDefList(Condition condition, Filter filter, Map ctx)
    throws BPMException
  {
    UtilTimer timer = new UtilTimer();
    try {
      checkLogined();
      Condition resultCon = condition;
      Filter resultFilter = filter;

      Condition sysCon = new Condition(
        ConditionKey.PROCESSDEF_SYSTEMCODE, 1, 
        this.systemCode);
      if (resultCon == null)
        resultCon = sysCon;
      else {
        resultCon.addCondition(sysCon, 100);
      }

      Map rightCtx = fillInUserID(ctx);

      Filter rightFilter = this.rightEngine.getProcessDefListFilter(rightCtx);
      if (rightFilter != null) {
        FilterChain filterChain = new FilterChain();
        filterChain.addFilter(resultFilter);
        filterChain.addFilter(rightFilter);
        resultFilter = filterChain;

        if ((rightFilter instanceof Condition)) {
          resultCon.addCondition((Condition)rightFilter, 
            100);
        }

      }

      List processDefList = this.workflowEngine.getProcessDefList(resultCon, 
        resultFilter);
      logger.debug(timer.timerString(5, "getProcessDefList"));
      return new WorkflowListProxy(processDefList, getSystemCode());
    } catch (Exception e) {
      throw new BPMException("getProcessDefList error.", e, 
        1003);
    }
  }

  public ProcessDef getProcessDef(String processDefID)
    throws BPMException
  {
    checkLogined();
    EIProcessDef eiProcessDef = EIProcessDefManager.getInstance()
      .loadByKey(processDefID);

    if (eiProcessDef == null) {
      return null;
    }
    return new ProcessDefProxy(eiProcessDef, getSystemCode());
  }

  public ActivityDef getActivityDef(String activityDefID)
    throws BPMException
  {
    checkLogined();
    EIActivityDef eiActivityDef = EIActivityDefManager.getInstance()
      .loadByKey(activityDefID);

    if (eiActivityDef == null) {
      return null;
    }
    return new ActivityDefProxy(eiActivityDef, getSystemCode());
  }

  public RouteDef getRouteDef(String routeDefId)
    throws BPMException
  {
    checkLogined();
    EIRouteDef eiRouteDef = EIRouteDefManager.getInstance().loadByKey(
      routeDefId);

    if (eiRouteDef == null) {
      return null;
    }
    return new RouteDefProxy(eiRouteDef, getSystemCode());
  }

  public List<ProcessInst> getProcessInstList(Condition condition, Filter filter, Map ctx)
    throws BPMException
  {
    UtilTimer timer = new UtilTimer();
    try {
      checkLogined();
      Condition resultCon = condition;
      Filter resultFilter = filter;

      String inSQL = "SELECT BPM_PROCESSDEF.PROCESSDEF_ID FROM BPM_PROCESSDEF WHERE BPM_PROCESSDEF.SYSTEMCODE='" + 
        this.systemCode + "'";
      Condition sysCon = new Condition(
        ConditionKey.PROCESSINST_PROCESSDEF_ID, 10, inSQL);
      if (resultCon == null)
        resultCon = sysCon;
      else {
        resultCon.addCondition(sysCon, 100);
      }

      Map rightCtx = fillInUserID(ctx);
      Filter rightFilter = this.rightEngine.getProcessInstListFilter(rightCtx);
      if (rightFilter != null) {
        FilterChain filterChain = new FilterChain();
        filterChain.addFilter(resultFilter);
        filterChain.addFilter(rightFilter);
        resultFilter = filterChain;

        if ((rightFilter instanceof Condition)) {
          resultCon.addCondition((Condition)rightFilter, 
            100);
        }
      }
      List processInstList = this.workflowEngine.getProcessInstList(resultCon, 
        resultFilter);
      logger.debug(timer.timerString(5, "getProcessInstList"));
      return new WorkflowListProxy(processInstList, getSystemCode());
    } catch (Exception e) {
      throw new BPMException("getProcessInstList error.", e, 
        1004);
    }
  }

  public List<ActivityInst> getActivityInstList(Condition condition, Filter filter, Map ctx)
    throws BPMException
  {
    UtilTimer timer = new UtilTimer();
    try {
      checkLogined();
      Condition resultCon = condition;
      Filter resultFilter = filter;

      String inSQL = "SELECT BPM_PROCESSDEF_VERSION.PROCESSDEF_VERSION_ID FROM BPM_PROCESSDEF, BPM_PROCESSDEF_VERSION WHERE BPM_PROCESSDEF.PROCESSDEF_ID=BPM_PROCESSDEF_VERSION.PROCESSDEF_ID AND BPM_PROCESSDEF.SYSTEMCODE='" + 
        this.systemCode + "'";
      Condition sysCon = new Condition(
        ConditionKey.ACTIVITYINST_PROCESSDEF_ID, 10, 
        inSQL);

      if (resultCon == null)
        resultCon = sysCon;
      else {
        resultCon.addCondition(sysCon, 100);
      }

      Condition splitCon = new Condition(
        ConditionKey.ACTIVITYINST_DEALMETHOD, 2, 
        "SPLITED");
      resultCon.addCondition(splitCon, 100);

      Map rightCtx = fillInUserID(ctx);
      Filter rightFilter = this.rightEngine
        .getActivityInstListFilter(rightCtx);

      if (rightFilter != null) {
        FilterChain filterChain = new FilterChain();
        filterChain.addFilter(resultFilter);
        filterChain.addFilter(rightFilter);
        resultFilter = filterChain;

        if ((rightFilter instanceof Condition)) {
          resultCon.addCondition((Condition)rightFilter, 
            100);
        }

      }

      List activityInstList = this.workflowEngine.getActivityInstList(
        resultCon, resultFilter);
      logger.debug(timer.timerString(5, "getActivityInstList"));
      return new WorkflowListProxy(activityInstList, getSystemCode());
    } catch (Exception e) {
      throw new BPMException("getActivityInstList error.", e, 
        1005);
    }
  }

  public List<ActivityInstHistory> getLastActivityInstHistoryListByActvityInst(String actvityInstId, Map ctx)
    throws BPMException
  {
    checkLogined();
    List list = this.workflowEngine
      .getLastActivityInstHistoryListByActvityInst(actvityInstId,true);
    return new WorkflowListProxy(list, getSystemCode());
  }

  public List<ActivityInstHistory> getActivityInstHistoryListByActvityInst(String actvityInstId, Map ctx)
    throws BPMException
  {
    checkLogined();
    List list = this.workflowEngine
      .getActivityInstHistoryListByActvityInst(actvityInstId);
    return new WorkflowListProxy(list, getSystemCode());
  }

  public List<ActivityInst> getActivityInstListByOutActvityInstHistory(String activityInstHistoryId, Map ctx)
    throws BPMException
  {
    checkLogined();
    List routeList = this.workflowEngine.getActivityInstHistoryOutRoute(activityInstHistoryId);
    List activityInstList = new ArrayList();
    for (int k = 0; k < routeList.size(); k++) {
      DbRouteInst routeInst = (DbRouteInst)routeList.get(k);
      ActivityInst inst = getActivityInst(routeInst.getToActivityId());
      if (inst != null) {
        activityInstList.add(inst);
      }
    }
    return activityInstList;
  }

  public List<ActivityInstHistory> getActivityInstHistoryListByProcessInst(String processInstId, Map ctx)
    throws BPMException
  {
    checkLogined();
    List list = this.workflowEngine
      .getActivityInstHistoryListByProcessInst(processInstId);
    return new WorkflowListProxy(list, getSystemCode());
  }

  public ProcessInst getProcessInst(String processInstID)
    throws BPMException
  {
    checkLogined();
    EIProcessInst eiProcessInst = EIProcessInstManager.getInstance()
      .loadByKey(processInstID);
    if (eiProcessInst == null) {
      return null;
    }
    return new ProcessInstProxy(eiProcessInst, getSystemCode());
  }

  public ReturnType updateProcessInstName(String processInstId, String name)
    throws BPMException
  {
    int length = name.getBytes().length;
    if (length >= 100) {
      return new ReturnType(-1, "名称长度不能超过100个字节！");
    }
    EIProcessInst eiProcessInst = EIProcessInstManager.getInstance()
      .loadByKey(processInstId);
    eiProcessInst.setName(name);
    EIProcessInstManager.getInstance().save(eiProcessInst);
    return new ReturnType(1);
  }

  public ReturnType updateProcessInstUrgency(String processInstId, String urgency)
    throws BPMException
  {
    int length = urgency.getBytes().length;
    if (length >= 20) {
      return new ReturnType(-1, "紧急程度长度不能超过20个字节！");
    }
    EIProcessInst eiProcessInst = EIProcessInstManager.getInstance()
      .loadByKey(processInstId);
    eiProcessInst.setUrgency(urgency);
    EIProcessInstManager.getInstance().save(eiProcessInst);
    return new ReturnType(1);
  }

  public ActivityInst getActivityInst(String activityInstID)
    throws BPMException
  {
    checkLogined();
    EIActivityInst eiActivityInst = EIActivityInstManager.getInstance()
      .loadByKey(activityInstID);
    if (eiActivityInst == null) {
      return null;
    }
    return new ActivityInstProxy(eiActivityInst, getSystemCode());
  }

  public ActivityInstHistory getActivityInstHistory(String activityInstHistoryID)
    throws BPMException
  {
    checkLogined();
    EIActivityInstHistory eiActivityInstHistory = 
      EIActivityInstHistoryManager.getInstance().loadByKey(activityInstHistoryID);
    if (eiActivityInstHistory == null) {
      return null;
    }
    return new ActivityInstHistoryProxy(eiActivityInstHistory, getSystemCode());
  }

  public ProcessInst newProcess(String processDefId, String processInstName, String processUrgency, Map ctx)
    throws BPMException
  {
    UtilTimer timer = new UtilTimer();
    checkLogined();
    beginTransaction();
    Map rightCtx = fillInUserID(ctx);
    try
    {
      EIProcessInst eiProcessInst = this.workflowEngine.createProcessInst(
        processDefId, processInstName, processUrgency);

      ReturnType returnType = this.rightEngine.hasRightToStartProcess(
        eiProcessInst.getProcessInstId(), rightCtx);
      if (!returnType.isSucess()) {
        throw new BPMException("Create process instance error.", 
          2000);
      }

      ReturnType rt = this.rightEngine.createProcessInst(
        eiProcessInst.getProcessInstId(), rightCtx);
      ReturnType rtd = this.dataEngine.createProcessInst(eiProcessInst.getProcessInstId(), rightCtx);
      ReturnType rtf = this.fileEngine.createProcessInst(
        eiProcessInst.getProcessInstId(), rightCtx);
      if ((rt.mainCode() == -1) || 
        (rtd.mainCode() == -1) || 
        (rtf.mainCode() == -1)) {
        rollbackTransaction();
        if (rt.toString() == null) {
          throw new BPMException("Create process instance error.", 
            2000);
        }
        throw new BPMException(rt.toString(), 
          2000);
      }

      fireProcessEvent(eiProcessInst, 1001);
      EIActivityInst eiActivityInst = this.workflowEngine
        .startProcessInst(eiProcessInst.getProcessInstId());
      rt = this.rightEngine.startProcessInst(eiProcessInst.getProcessInstId(), 
        eiActivityInst.getActivityInstId(), rightCtx);
      rtd = this.dataEngine.startProcessInst(eiProcessInst.getProcessInstId(), eiActivityInst.getActivityInstId(), ctx);
      rtf = this.fileEngine.startProcessInst(eiProcessInst.getProcessInstId(), 
        eiActivityInst.getActivityInstId(), rightCtx);
      if ((rt.mainCode() == -1) || 
        (rtd.mainCode() == -1) || 
        (rtf.mainCode() == -1)) {
        rollbackTransaction();
        if (rt.toString() == null) {
          throw new BPMException("Start process instance error.", 
            2001);
        }
        throw new BPMException(rt.toString(), 
          2001);
      }

      fireActivityEvent(eiActivityInst, 2001);
      fireProcessEvent(eiProcessInst, 1002);

      commitTransaction();
      ProcessInst processInst = new ProcessInstProxy(eiProcessInst, getSystemCode());
      logger.debug(timer.timerString(5, "newProcess"));
      return processInst;
    } catch (BPMException bpme) {
      rollbackTransaction();
      throw bpme;
    } catch (Exception e) {
      rollbackTransaction();
      throw new BPMException("newProcess error.", e, 
        2002);
    }
  }

  public ProcessInst newProcess(String processDefId, String processInstName, String processUrgency, String initType, Map ctx)
    throws BPMException
  {
    UtilTimer timer = new UtilTimer();
    checkLogined();
    Map rightCtx = fillInUserID(ctx);
    try
    {
      EIProcessInst eiProcessInst = this.workflowEngine.createProcessInst(
        processDefId, processInstName, processUrgency, initType);

      if (!"AUTO".equals(initType))
      {
        ReturnType returnType = this.rightEngine.hasRightToStartProcess(
          eiProcessInst.getProcessInstId(), rightCtx);
        if (!returnType.isSucess()) {
          throw new BPMException("Create process instance error.", 
            2000);
        }
      }

      ReturnType rt = this.rightEngine.createProcessInst(
        eiProcessInst.getProcessInstId(), initType, rightCtx);
      ReturnType rtd = this.dataEngine.createProcessInst(
        eiProcessInst.getProcessInstId(), initType, rightCtx);

      ReturnType rtf = this.fileEngine.createProcessInst(
        eiProcessInst.getProcessInstId(), initType, rightCtx);
      if ((rt.mainCode() == -1) || 
        (rtd.mainCode() == -1) || 
        (rtf.mainCode() == -1)) {
        rollbackTransaction();
        if (rt.toString() == null) {
          throw new BPMException("Create process instance error.", 
            2000);
        }
        throw new BPMException(rt.toString(), 
          2000);
      }

      fireProcessEvent(eiProcessInst, 1001);
      EIActivityInst eiActivityInst = this.workflowEngine
        .startProcessInst(eiProcessInst.getProcessInstId());

      if ("AUTO".equals(initType))
      {
        Map tempCtx = new HashMap();
        tempCtx.putAll(ctx);
        tempCtx.put("OARightConstants.ACTIVITYINST_ID", 
          eiActivityInst.getActivityInstId());
        tempCtx.put("OARightConstants.PROCESSINST_ID", 
          eiActivityInst.getProcessInstId());
        List users = this.rightEngine.getPerformerCandidate(
          eiActivityInst.getActivityDefId(), tempCtx);
        if (users.isEmpty()) {
          throw new BPMException(
            "Start process instance error, Can't find process starter!");
        }
        rightCtx.put("RightEngine.USERS", users);
      }

      rt = this.rightEngine.startProcessInst(eiProcessInst.getProcessInstId(), 
        eiActivityInst.getActivityInstId(), rightCtx);
      rtd = this.dataEngine.startProcessInst(eiProcessInst.getProcessInstId(), eiActivityInst.getActivityInstId(), ctx);
      rtf = this.fileEngine.startProcessInst(eiProcessInst.getProcessInstId(), 
        eiActivityInst.getActivityInstId(), rightCtx);
      if ((rt.mainCode() == -1) || 
        (rtd.mainCode() == -1) || 
        (rtf.mainCode() == -1))
      {
        rollbackTransaction();
        if (rt.toString() == null) {
          throw new BPMException("Start process instance error.", 
            2001);
        }
        throw new BPMException(rt.toString(), 
          2001);
      }

      fireActivityEvent(eiActivityInst, 2001);
      if ("AUTO".equals(initType))
      {
        fireProcessEvent(eiProcessInst, 1002, ctx);
      }
      else fireProcessEvent(eiProcessInst, 1002);

      commitTransaction();
      ProcessInst processInst = new ProcessInstProxy(eiProcessInst, getSystemCode());
      logger.debug(timer.timerString(5, "newProcess"));
      return processInst;
    } catch (BPMException bpme) {
      rollbackTransaction();
      throw bpme;
    } catch (Exception e) {
      rollbackTransaction();
      throw new BPMException("newProcess error.", e, 
        2002);
    }
  }

  public List<RouteDef> getNextRoutes(String startActivityInstID, Condition condition, Filter routeFilter, Map ctx)
    throws BPMException
  {
    checkLogined();
    Map rightCtx = fillInUserID(ctx);

    ActivityInst activityInst = getActivityInst(startActivityInstID);
    rightCtx.put("OARightConstants.ACTIVITYINST_ID", startActivityInstID);
    rightCtx.put("OARightConstants.PROCESSINST_ID", 
      activityInst.getProcessInstId());
    RouteConditionFilter routeConditionFilter = new RouteConditionFilter(
      rightCtx);
    FilterChain filterChain = new FilterChain();
    if (routeFilter != null) {
      filterChain.addFilter(routeFilter);
    }
    filterChain.addFilter(routeConditionFilter);

    List routeList = this.workflowEngine.getNextRoutes(startActivityInstID, 
      condition, filterChain);
    return new WorkflowListProxy(routeList, getSystemCode());
  }

  public ReturnType display(String activityInstId) throws BPMException
  {
    ActivityInst activityInst = getActivityInst(activityInstId);

    if ("notStarted".equals(activityInst.getState()))
    {
      List performers = (List)getActivityInstRightAttribute(
        activityInstId, 
        "PERFORMER", 
        null);
      if ((performers != null) && (performers.size() == 1)) {
        String performSequence = (String)getActivityDefRightAttribute(
          activityInst.getActivityDefId(), 
          "PERFORMSEQUENCE", 
          null);

        if (("AUTOSIGN"
          .equals(performSequence)) && 
          (canSignReceive(activityInstId, null))) {
          signReceive(activityInstId, null);
        }
      }
    }

    EIActivityInst eiActivityInst = EIActivityInstManager.getInstance().loadByKey(activityInstId);
    fireActivityEvent(eiActivityInst, 2024);
    return new ReturnType(1);
  }

  public ActivityInst copyActivityInstByHistory(String activityHistoryInstId, Map ctx) throws BPMException {
    EIActivityInst inst = this.workflowEngine.getSplitActivityInst(activityHistoryInstId, false);
    ctx = fillInUserID(ctx);

    this.rightEngine.copyActivityInstByHistory(inst.getActivityInstId(), activityHistoryInstId, ctx);

    return new ActivityInstProxy(inst, this.systemCode);
  }

  public ActivityInst newActivityInstByActivityDefId(String processInstId, String activityDefId, Map ctx)
    throws BPMException
  {
    ActivityInst activityInst = new ActivityInstProxy(this.workflowEngine.newActivityInstByActivityDefId(processInstId, activityDefId), getSystemCode());
    ctx = fillInUserID(ctx);
    List users = new ArrayList();
    users.add(ctx.get("RightEngine.USERID"));

    ctx.put("OARightConstants.PERFORMERS", users);
    this.workflowEngine.routeTo(activityInst.getActivityInstId(), 
      activityDefId);
    this.rightEngine.routeTo(activityInst.getActivityInstId(), 
      activityDefId, ctx);
    this.dataEngine.routeTo(activityInst.getActivityInstId(), 
      activityDefId, ctx);

    this.fileEngine.routeTo(activityInst.getActivityInstId(), 
      activityDefId, ctx);

    return activityInst;
  }

  private EIActivityInst signle(String startActivityInstID, String nextActivityDefID, Map ctx, Map eventContext)
    throws BPMException
  {
    List performers = new ArrayList((List)
      ctx.get("OARightConstants.PERFORMERS"));
    EIActivityInst inst = EIActivityInstManager.getInstance().loadByKey(startActivityInstID);
    fireActivityEvent(inst, 2002, eventContext);

    EIActivityInst nextInst = inst;

    this.rightEngine.routeTo(inst.getActivityInstId(), 
      nextActivityDefID, ctx);
    this.dataEngine.routeTo(inst.getActivityInstId(), 
      nextActivityDefID, ctx);
    this.fileEngine.routeTo(inst.getActivityInstId(), 
      nextActivityDefID, ctx);
    nextInst = this.workflowEngine.routeTo(inst.getActivityInstId(), 
      nextActivityDefID);

    ActivityDef activityDef = getActivityDef(nextActivityDefID);

    if (activityDef == null) {
      throw new BPMException("The activity definition not found!");
    }
    String join = activityDef.getJoin();

    if ((join != null) && (join.equals("AND"))) {
      List activityInsts = this.workflowEngine.combinableActivityInsts(
        inst.getActivityInstId());
      if (activityInsts.size() > 1)
      {
        String suspendOrCombine = this.workflowEngine.suspendOrCombine(
          inst.getActivityInstId());

        if ("SUSPEND"
          .equals(suspendOrCombine)) {
          this.workflowEngine.suspendActivityInst(startActivityInstID);
          this.rightEngine.suspendActivityInst(startActivityInstID, ctx);
          this.fileEngine.suspendActivityInst(startActivityInstID, ctx);
          this.dataEngine.suspendActivityInst(startActivityInstID, ctx);
        }
        else if ("COMBINE"
          .equals(suspendOrCombine)) {
          List activityInstIds = new ArrayList();
          for (int k = 0; k < activityInsts.size(); k++) {
            activityInstIds.add(
              ((EIActivityInst)activityInsts.get(k)).getActivityInstId());
          }
          String[] activityInstIdArr = (String[])activityInstIds.toArray(new String[0]);
          this.rightEngine.combineActivityInsts(activityInstIdArr, ctx);
          this.fileEngine.combineActivityInsts(activityInstIdArr, ctx);
          this.dataEngine.combineActivityInsts(activityInstIdArr, ctx);
          nextInst = this.workflowEngine.combineActivityInsts(activityInstIdArr);

          if (!inst.getActivityDefId().equals(nextInst.getActivityDef()))
          {
            nextInst = this.workflowEngine.routeTo(nextInst.getActivityInstId(), 
              nextActivityDefID);
          }
        }
      }
    }

    fireActivityEvent(nextInst, 2003, eventContext);
    return nextInst;
  }

  private Map multiple(String startActivityInstID, String nextActivityDefID, Map ctx, EIActivityInstHistory newHistory, Map eventContext)
    throws BPMException
  {
    List performers = new ArrayList((List)
      ctx.get("OARightConstants.PERFORMERS"));

    String performSequence = (String)getActivityDefRightAttribute(nextActivityDefID, "PERFORMSEQUENCE", null);

    EIActivityInst eiActivityInst = EIActivityInstManager.getInstance()
      .loadByKey(startActivityInstID);

    if (performSequence.equals("FIRST")) {
      signle(startActivityInstID, nextActivityDefID, ctx, eventContext);
    }
    else {
      List copyActivityInsts = this.workflowEngine.splitActivityInst(
        startActivityInstID, performers.size(), 
        newHistory.getActivityHistoryId());
      if (!eiActivityInst.getReceiveMethod().equals("RESEND")) {
        EIActivityInst mainInst = this.workflowEngine.routeTo(startActivityInstID, 
          nextActivityDefID);
        Map mainMap = new HashMap();
        mainMap.putAll(ctx);
        List users = new ArrayList();
        users.add(this.connInfo.getUserID());
        mainMap.put("OARightConstants.PERFORMERS", users);
      }

      String[] ids = new String[copyActivityInsts.size()];
      for (int k = 0; k < copyActivityInsts.size(); k++) {
        EIActivityInst inst = (EIActivityInst)copyActivityInsts.get(k);
        ids[k] = inst.getActivityInstId();
      }

      this.rightEngine.splitActivityInst(startActivityInstID, ids, ctx);
      this.dataEngine.splitActivityInst(startActivityInstID, ids, ctx);
      this.fileEngine.splitActivityInst(startActivityInstID, ids, ctx);
      for (int i = 0; i < copyActivityInsts.size(); i++) {
        Map map = new HashMap();
        map.putAll(ctx);
        map = fillInUserID(map);
        List personList = new ArrayList();
        personList.add(performers.get(i));

        EIActivityInst inst = (EIActivityInst)copyActivityInsts.get(i);
        fireActivityEvent(inst, 2002, eventContext);
        EIActivityInst nextInst = this.workflowEngine.routeTo(inst.getActivityInstId(), 
          nextActivityDefID);

        map.put("OARightConstants.PERFORMERS", personList);
        map.put("OARightConstants.ACTIVITYINST_ID", nextInst.getActivityInstId());

        this.rightEngine.routeTo(inst.getActivityInstId(), 
          nextActivityDefID, map);
        this.dataEngine.routeTo(inst.getActivityInstId(), 
          nextActivityDefID, map);
        this.fileEngine.routeTo(inst.getActivityInstId(), 
          nextActivityDefID, map);
        fireActivityEvent(nextInst, 2003);

        if (performSequence.equals("SEQUENCE")) {
          this.workflowEngine.suspendActivityInst(inst.getActivityInstId());
        }

      }

      List personList = new ArrayList();
      personList.add(performers.get(0));
      ctx.put("OARightConstants.PERFORMERS", personList);
    }

    return ctx;
  }

  private Map joinSign(String startActivityInstID, String nextActivityDefID, Map ctx, EIActivityInstHistory newHistory)
    throws BPMException
  {
    Map eventContext = new HashMap();
    eventContext.put("ctx", ctx);
    eventContext.put("ActivityEvent.CONTEXT_ACTIVITYINSTHISTORY", 
      newHistory.getActivityHistoryId());
    EIActivityInst eiActivityInst = EIActivityInstManager.getInstance()
      .loadByKey(startActivityInstID);
    fireActivityEvent(eiActivityInst, 2008, eventContext);
    List performers = new ArrayList((List)
      ctx.get("OARightConstants.PERFORMERS"));

    List copyActivityInsts = this.workflowEngine.splitActivityInst(
      startActivityInstID, performers.size(), 
      newHistory.getActivityHistoryId());
    String[] ids = new String[copyActivityInsts.size()];

    for (int k = 0; k < copyActivityInsts.size(); k++) {
      EIActivityInst inst = (EIActivityInst)copyActivityInsts.get(k);
      ids[k] = inst.getActivityInstId();
    }

    fireActivityEvent(
      (EIActivityInst[])copyActivityInsts.toArray(new EIActivityInst[0]), 2009, eventContext);
    for (int k = 0; k < performers.size(); k++)
    {
      String orgId = (String)performers.get(k);

      if (orgId.startsWith("org")) {
        orgId = orgId.substring(3, orgId.length());
        Map cctx = fillInUserID(new HashMap());
        cctx.put("orgs", orgId);

        List personList = (List)getActivityDefRightAttribute(nextActivityDefID, "PERFORMERSELECTEDID", cctx);
        List personlist = new ArrayList();
        for (int p = 0; p < personList.size(); p++) {
          personlist.add(((Person)personList.get(p)).getID());
        }

        Map multipleMap = fillInUserID(new HashMap());
        multipleMap.putAll(ctx);
        multipleMap.put("OARightConstants.PERFORMERS", personlist);

        EIActivityInst inst = (EIActivityInst)copyActivityInsts.get(k);
        fireActivityEvent(inst, 2002);
        EIActivityInst nextInst = this.workflowEngine.routeTo(inst.getActivityInstId(), 
          nextActivityDefID);
        this.rightEngine.routeTo(inst.getActivityInstId(), 
          nextActivityDefID, multipleMap);
        this.dataEngine.routeTo(inst.getActivityInstId(), 
          nextActivityDefID, multipleMap);
        this.fileEngine.routeTo(inst.getActivityInstId(), 
          nextActivityDefID, multipleMap);
        fireActivityEvent(nextInst, 2003);
      }
    }

    String orgId = (String)performers.get(0);
    if (orgId.startsWith("org")) {
      orgId = orgId.substring(3, orgId.length());
    }
    ctx.put("orgs", orgId);
    List personList = (List)getActivityDefRightAttribute(nextActivityDefID, "PERFORMERSELECTEDID", ctx);
    List personlist = new ArrayList();

    for (int p = 0; p < personList.size(); p++) {
      personlist.add(((Person)personList.get(p)).getID());
    }

    ctx.put("OARightConstants.PERFORMERS", personlist);

    return ctx;
  }

  private ReturnType routeTo2(String startActivityInstID, String nextActivityDefID, Map ctx, EIActivityInstHistory newHistory, Map eventContext)
    throws BPMException
  {
    EIActivityInst eiActivityInst = EIActivityInstManager.getInstance().loadByKey(startActivityInstID);
    String performType = (String)getActivityDefRightAttribute(nextActivityDefID, "PERFORMTYPE", null);
    if (performType.equals("MULTIPLE")) {
      List performers = new ArrayList((List)
        ctx.get("OARightConstants.PERFORMERS"));
      multiple(startActivityInstID, nextActivityDefID, ctx, newHistory, eventContext);
    } else if (performType.equals("JOINTSIGN")) {
      joinSign(startActivityInstID, nextActivityDefID, ctx, newHistory);
    } else {
      signle(startActivityInstID, nextActivityDefID, ctx, eventContext);
    }
    return new ReturnType(1);
  }

  public ReturnType routeTo(String startActivityInstID, String[] nextActivityDefIDs, Map[] ctx)
    throws BPMException
  {
    checkLogined();

    for (int i = 0; i < ctx.length; i++) {
      ctx[i] = fillInUserID(ctx[i]);
    }

    EIActivityInst eiActivityInst = EIActivityInstManager.getInstance()
      .loadByKey(startActivityInstID);

    if (eiActivityInst == null) {
      throw new BPMException("Activity instance not found:" + 
        startActivityInstID);
    }

    checkCanPerform(eiActivityInst.getActivityInstId(), ctx[0]);

    ReturnType ret = null;

    fireActivityEvent(eiActivityInst, 2020);

    EIActivityInstHistory newHistory = null;

    if (eiActivityInst.getReceiveMethod().equals("RESEND")) {
      fireActivityEvent(eiActivityInst, 2020);
      List actList = this.workflowEngine.getLastSplitActivityInstHistoryByActvityInst(startActivityInstID);
      newHistory = (EIActivityInstHistory)actList.get(actList.size() - 1);
    } else {
      newHistory = this.workflowEngine.saveActivityHistoryInst(startActivityInstID);
    }

    Map eventContext = new HashMap();
    eventContext.put("ctx", ctx);
    eventContext.put("ActivityEvent.CONTEXT_ACTIVITYINSTHISTORY", 
      newHistory.getActivityHistoryId());

    fireActivityEvent(eiActivityInst, 2006, eventContext);

    ret = this.rightEngine.saveActivityHistoryInst(startActivityInstID, 
      newHistory.getActivityHistoryId(), ctx[0]);
    if (!ret.isSucess()) {
      return ret;
    }
    ret = this.dataEngine.saveActivityHistoryInst(startActivityInstID, 
      newHistory.getActivityHistoryId(), ctx[0]);
    if (!ret.isSucess()) {
      return ret;
    }
    ret = this.fileEngine.saveActivityHistoryInst(startActivityInstID, 
      newHistory.getActivityHistoryId(), ctx[0]);
    if (!ret.isSucess()) {
      return ret;
    }
    fireActivityEvent(eiActivityInst, 2007, eventContext);

    fireActivityEvent(eiActivityInst, 2021, eventContext);

    if (nextActivityDefIDs.length == 1)
    {
      if ("LAST"
        .equalsIgnoreCase(nextActivityDefIDs[0])) {
        return completeProcessInst(eiActivityInst.getProcessInstId(), 
          ctx[0]);
      }
    }

    List copyActivityInsts = new ArrayList();
    if (nextActivityDefIDs.length > 1)
    {
      fireActivityEvent(eiActivityInst, 2008, eventContext);

      copyActivityInsts = this.workflowEngine.splitActivityInst(
        startActivityInstID, nextActivityDefIDs.length, 
        newHistory.getActivityHistoryId());

      String[] ids = new String[nextActivityDefIDs.length];

      for (int i = 0; i < copyActivityInsts.size(); i++) {
        EIActivityInst inst = (EIActivityInst)copyActivityInsts.get(i);
        ids[i] = inst.getActivityInstId();
      }

      ret = this.rightEngine.splitActivityInst(startActivityInstID, ids, 
        ctx[0]);
      if (!ret.isSucess()) {
        return ret;
      }
      ret = this.dataEngine.splitActivityInst(startActivityInstID, ids, 
        ctx[0]);
      if (!ret.isSucess()) {
        return ret;
      }
      ret = this.fileEngine.splitActivityInst(startActivityInstID, ids, 
        ctx[0]);
      if (!ret.isSucess()) {
        return ret;
      }
      for (int i = 0; i < nextActivityDefIDs.length; i++) {
        ret = routeTo2(ids[i], nextActivityDefIDs[i], ctx[i], newHistory, eventContext);
        if (!ret.isSucess()) {
          return ret;
        }
      }

      fireActivityEvent(
        (EIActivityInst[])copyActivityInsts.toArray(new EIActivityInst[copyActivityInsts.size()]), 2009, eventContext);
    } else {
      ret = routeTo2(startActivityInstID, nextActivityDefIDs[0], ctx[0], newHistory, eventContext);
      if (!ret.isSucess()) {
        return ret;
      }

    }

    if (eiActivityInst.getReceiveMethod().equals("RESEND")) {
      eiActivityInst.setReceiveMethod("SEND");
    }

    EIProcessInst processInst = eiActivityInst.getProcessInst();
    if (processInst.getState().equalsIgnoreCase(
      "notStarted")) {
      this.workflowEngine.updateProcessState(
        eiActivityInst.getProcessInstId(), 
        "running");
    }

    return new ReturnType(1);
  }

  public boolean canTakeBack(String activityInstID, Map ctx)
    throws BPMException
  {
    checkLogined();
    Map rightCtx = fillInUserID(ctx);

    ActivityDef activityDef = getActivityInst(activityInstID).getActivityDef();
    String canTackback = (String)getActivityDefRightAttribute(activityDef.getActivityDefId(), "CANTAKEBACK", null);

    if (getActivityInst(activityInstID).getDealMethod().equals("SPLITED")) {
      return false;
    }
    if (((canTackback == null) || (canTackback.equals("")) || (!canTackback.equals("YES"))) && 
      (!this.workflowEngine.canTakeBack(activityInstID))) {
      return false;
    }

    if (!this.rightEngine.canTakeBack(activityInstID, rightCtx)) {
      return false;
    }
    return true;
  }

  public ReturnType takeBack(String activityInstID, Map ctx)
    throws BPMException
  {
    if (!canTakeBack(activityInstID, ctx)) {
      resumeActivityInst(activityInstID, ctx);
      new ReturnType(1);
    }

    checkLogined();
    Map rightCtx = fillInUserID(ctx);

    EIActivityInst eiActivityInst = EIActivityInstManager.getInstance()
      .loadByKey(activityInstID);

    if (eiActivityInst == null) {
      throw new BPMException("Activity instance not found:" + 
        activityInstID);
    }

    String suspendOrCombine = this.workflowEngine.forecastSuspendOrCombine(
      eiActivityInst.getActivityInstId(), eiActivityInst.getActivityDefId());
    rightCtx.put("suspendOrCombine", suspendOrCombine);
    List activityInsts = this.workflowEngine.combinableActivityInsts(eiActivityInst.getActivityInstId());
    rightCtx.put("combinableActivityInsts", activityInsts);
    String performSequence = (String)getActivityDefRightAttribute(eiActivityInst.getActivityDefId(), "PERFORMSEQUENCE", null);
    rightCtx.put("PERFORMSEQUENCE", performSequence);

    fireActivityEvent(eiActivityInst, 2022);

    ReturnType rt = this.rightEngine.tackBack(activityInstID, rightCtx);
    if (!rt.isSucess()) {
      return rt;
    }

    rt = this.dataEngine.tackBack(activityInstID, rightCtx);
    if (!rt.isSucess()) {
      return rt;
    }

    rt = this.fileEngine.tackBack(activityInstID, rightCtx);
    if (!rt.isSucess()) {
      return rt;
    }

    rt = this.workflowEngine.tackBack(activityInstID, rightCtx);
    if (!rt.isSucess()) {
      return rt;
    }

    fireActivityEvent(eiActivityInst, 2023);
    return new ReturnType(1);
  }

  public boolean canRouteBack(String activityInstID, Map ctx)
    throws BPMException
  {
    checkLogined();

    Map rightCtx = fillInUserID(ctx);
    if (!this.workflowEngine.canRouteBack(activityInstID)) {
      return false;
    }
    if (!this.rightEngine.canRouteBack(activityInstID, rightCtx)) {
      return false;
    }

    return true;
  }

  public List<ActivityInstHistory> getCanTackBackActivityInstList(String activityInstID, Filter routeFilter, Map ctx)
    throws BPMException
  {
    checkLogined();

    Map rightCtx = fillInUserID(ctx);

    ActivityInst activityInst = getActivityInst(activityInstID);
    rightCtx.put("OARightConstants.ACTIVITYINST_ID", activityInstID);
    rightCtx.put("OARightConstants.PROCESSINST_ID", 
      activityInst.getProcessInstId());

    FilterChain filterChain = new FilterChain();
    if (routeFilter != null) {
      filterChain.addFilter(routeFilter);
    }

    List routeList = this.workflowEngine.getRouteBacks(activityInstID, 
      filterChain);
    return new WorkflowListProxy(routeList, getSystemCode());
  }

  public List<ActivityInstHistory> getRouteBackActivityHistoryInstList(String activityInstID, Filter routeFilter, Map ctx)
    throws BPMException
  {
    checkLogined();

    Map rightCtx = fillInUserID(ctx);

    ActivityInst activityInst = getActivityInst(activityInstID);
    rightCtx.put("OARightConstants.ACTIVITYINST_ID", activityInstID);
    rightCtx.put("OARightConstants.PROCESSINST_ID", 
      activityInst.getProcessInstId());

    FilterChain filterChain = new FilterChain();
    if (routeFilter != null) {
      filterChain.addFilter(routeFilter);
    }

    List routeList = this.workflowEngine.getRouteBacks(activityInstID, 
      filterChain);
    return new WorkflowListProxy(routeList, getSystemCode());
  }

  public ReturnType routeBack(String fromActivityInstID, String toActivityInstHistoryID, Map ctx)
    throws BPMException
  {
    checkLogined();

    Map rightCtx = fillInUserID(ctx);
    EIActivityInst eiActivityInst = EIActivityInstManager.getInstance()
      .loadByKey(fromActivityInstID);

    if (eiActivityInst == null) {
      throw new BPMException("Activity instance not found:" + 
        fromActivityInstID);
    }
    checkCanPerform(eiActivityInst.getActivityInstId(), rightCtx);

    EIActivityInstHistory history = 
      EIActivityInstHistoryManager.getInstance().loadByKey(toActivityInstHistoryID);
    if (history == null) {
      throw new BPMException("The history activity instance not found!");
    }

    ReturnType ret = null;

    fireActivityEvent(eiActivityInst, 2020);

    EIActivityInstHistory newHistory = this.workflowEngine
      .saveActivityHistoryInst(fromActivityInstID);

    ret = this.rightEngine.saveActivityHistoryInst(fromActivityInstID, 
      newHistory.getActivityHistoryId(), ctx);
    if (!ret.isSucess()) {
      return ret;
    }

    ret = this.dataEngine.saveActivityHistoryInst(fromActivityInstID, 
      newHistory.getActivityHistoryId(), rightCtx);
    if (!ret.isSucess()) {
      return ret;
    }
    ret = this.fileEngine.saveActivityHistoryInst(fromActivityInstID, 
      newHistory.getActivityHistoryId(), rightCtx);
    if (!ret.isSucess()) {
      return ret;
    }

    Map eventContext = new HashMap();
    eventContext.put("ActivityEvent.CONTEXT_ACTIVITYINSTHISTORY", 
      newHistory.getActivityHistoryId());
    fireActivityEvent(eiActivityInst, 2021, eventContext);

    List activityInsts = this.workflowEngine.forecastCombinableActivityInsts(
      eiActivityInst.getActivityInstId(), history.getActivityDefId());
    boolean bCombine = false;
    if (activityInsts.size() != 0) {
      String suspendOrCombine = this.workflowEngine.forecastSuspendOrCombine(
        eiActivityInst.getActivityInstId(), 
        history.getActivityDefId());
      if (!suspendOrCombine.equals("SUSPEND"))
      {
        if (suspendOrCombine
          .equals("COMBINE"))
        {
          bCombine = true;

          fireActivityEvent(
            (EIActivityInst[])activityInsts.toArray(new EIActivityInst[0]), 2010);
        }
      }
    }

    fireActivityEvent(eiActivityInst, 2002);

    ReturnType rt = this.rightEngine.routeBack(fromActivityInstID, toActivityInstHistoryID, 
      rightCtx);
    if (!ret.isSucess()) {
      return ret;
    }
    rt = this.dataEngine.routeBack(fromActivityInstID, toActivityInstHistoryID, 
      rightCtx);
    if (!rt.isSucess()) {
      return rt;
    }
    rt = this.fileEngine.routeBack(fromActivityInstID, toActivityInstHistoryID, 
      rightCtx);
    if (!rt.isSucess()) {
      return rt;
    }
    EIActivityInst actInst = this.workflowEngine.routeBack(fromActivityInstID, 
      toActivityInstHistoryID);

    fireActivityEvent(actInst, 2003);

    if (bCombine) {
      fireActivityEvent(actInst, 2011);
    }
    return new ReturnType(1);
  }

  public boolean canSignReceive(String activityInstID, Map ctx)
    throws BPMException
  {
    checkLogined();

    Map rightCtx = fillInUserID(ctx);
    if (!this.workflowEngine.canSignReceive(activityInstID)) {
      return false;
    }
    if (!this.rightEngine.canSignReceive(activityInstID, rightCtx)) {
      return false;
    }

    return true;
  }

  public ReturnType signReceive(String activityInstID, Map ctx)
    throws BPMException
  {
    checkLogined();

    Map rightCtx = fillInUserID(ctx);
    EIActivityInst eiActivityInst = EIActivityInstManager.getInstance()
      .loadByKey(activityInstID);

    if (eiActivityInst == null) {
      throw new BPMException("Activity instance not found:" + 
        activityInstID);
    }
    if (!canSignReceive(eiActivityInst.getActivityInstId(), rightCtx)) {
      throw new BPMException("User has no right to perform!");
    }

    fireActivityEvent(eiActivityInst, 2004);

    ReturnType rt = this.rightEngine.signReceive(activityInstID, rightCtx);
    if (!rt.isSucess())
      return rt;
    rt = this.dataEngine.signReceive(activityInstID, rightCtx);
    if (!rt.isSucess())
      return rt;
    rt = this.fileEngine.signReceive(activityInstID, rightCtx);
    if (!rt.isSucess()) {
      return rt;
    }
    rt = this.workflowEngine.signReceive(activityInstID);
    if (!rt.isSucess()) {
      return rt;
    }
    fireActivityEvent(eiActivityInst, 2005);
    return new ReturnType(1);
  }

  public ReturnType suspendActivityInst(String activityInstID, Map ctx)
    throws BPMException
  {
    checkLogined();
    ctx = fillInUserID(ctx);
    EIActivityInst eiActivityInst = EIActivityInstManager.getInstance()
      .loadByKey(activityInstID);

    fireActivityEvent(eiActivityInst, 2016);

    ReturnType rt = this.rightEngine.suspendActivityInst(activityInstID, ctx);
    if (!rt.isSucess())
      return rt;
    rt = this.dataEngine.suspendActivityInst(activityInstID, ctx);
    if (!rt.isSucess())
      return rt;
    rt = this.fileEngine.suspendActivityInst(activityInstID, ctx);
    if (!rt.isSucess())
      return rt;
    rt = this.workflowEngine.suspendActivityInst(activityInstID);
    if (!rt.isSucess()) {
      return rt;
    }

    fireActivityEvent(eiActivityInst, 2017);
    return new ReturnType(1);
  }

  public ReturnType resumeActivityInst(String activityInstID, Map ctx)
    throws BPMException
  {
    checkLogined();
    ctx = fillInUserID(ctx);
    EIActivityInst eiActivityInst = EIActivityInstManager.getInstance()
      .loadByKey(activityInstID);

    fireActivityEvent(eiActivityInst, 2018);

    ReturnType rt = this.rightEngine.resumeActivityInst(activityInstID, ctx);
    if (!rt.isSucess())
      return rt;
    rt = this.dataEngine.resumeActivityInst(activityInstID, ctx);
    if (!rt.isSucess())
      return rt;
    rt = this.fileEngine.resumeActivityInst(activityInstID, ctx);
    if (!rt.isSucess())
      return rt;
    rt = this.workflowEngine.resumeActivityInst(activityInstID);
    if (!rt.isSucess()) {
      return rt;
    }
    fireActivityEvent(eiActivityInst, 2019);
    return new ReturnType(1);
  }

  public ReturnType suspendProcessInst(String processInstID, Map ctx)
    throws BPMException
  {
    checkLogined();
    ctx = fillInUserID(ctx);
    EIProcessInst eiProcessInst = EIProcessInstManager.getInstance()
      .loadByKey(processInstID);

    fireProcessEvent(eiProcessInst, 1005);

    ReturnType rt = this.rightEngine.suspendProcessInst(processInstID, ctx);
    if (!rt.isSucess())
      return rt;
    rt = this.dataEngine.suspendProcessInst(processInstID, ctx);
    if (!rt.isSucess())
      return rt;
    rt = this.fileEngine.suspendProcessInst(processInstID, ctx);
    if (!rt.isSucess())
      return rt;
    rt = this.workflowEngine.suspendProcessInst(processInstID);
    if (!rt.isSucess()) {
      return rt;
    }

    fireProcessEvent(eiProcessInst, 1006);
    return new ReturnType(1);
  }

  public ReturnType resumeProcessInst(String processInstID, Map ctx)
    throws BPMException
  {
    checkLogined();
    ctx = fillInUserID(ctx);
    EIProcessInst eiProcessInst = EIProcessInstManager.getInstance()
      .loadByKey(processInstID);

    fireProcessEvent(eiProcessInst, 1007);

    ReturnType rt = this.rightEngine.resumeProcessInst(processInstID, ctx);
    if (!rt.isSucess())
      return rt;
    rt = this.dataEngine.resumeProcessInst(processInstID, ctx);
    if (!rt.isSucess())
      return rt;
    rt = this.fileEngine.resumeProcessInst(processInstID, ctx);
    if (!rt.isSucess())
      return rt;
    rt = this.workflowEngine.resumeProcessInst(processInstID);
    if (!rt.isSucess()) {
      return rt;
    }

    fireProcessEvent(eiProcessInst, 1008);
    return new ReturnType(1);
  }

  public ReturnType abortProcessInst(String processInstID, Map ctx)
    throws BPMException
  {
    checkLogined();
    ctx = fillInUserID(ctx);
    EIProcessInst eiProcessInst = EIProcessInstManager.getInstance()
      .loadByKey(processInstID);

    fireProcessEvent(eiProcessInst, 1009);

    ReturnType rt = this.rightEngine.abortProcessInst(processInstID, ctx);
    if (!rt.isSucess())
      return rt;
    rt = this.dataEngine.abortProcessInst(processInstID, ctx);
    if (!rt.isSucess())
      return rt;
    rt = this.fileEngine.abortProcessInst(processInstID, ctx);
    if (!rt.isSucess())
      return rt;
    rt = this.workflowEngine.abortProcessInst(processInstID);
    if (!rt.isSucess()) {
      return rt;
    }

    fireProcessEvent(eiProcessInst, 1010);
    return new ReturnType(1);
  }

  public ReturnType completeProcessInst(String processInstID, Map ctx)
    throws BPMException
  {
    checkLogined();
    ctx = fillInUserID(ctx);
    EIProcessInst eiProcessInst = EIProcessInstManager.getInstance()
      .loadByKey(processInstID);

    fireProcessEvent(eiProcessInst, 1011);

    List activityInsts = eiProcessInst.getActivityInstList();
    if (activityInsts != null) {
      for (Iterator iter = activityInsts.iterator(); iter.hasNext(); ) {
        EIActivityInst activityInst = (EIActivityInst)iter.next();
        if (!canEndRead(activityInst.getActivityInstId(), ctx)) {
          String activityInstId = activityInst.getActivityInstId();
          EIActivityInstHistory his = this.workflowEngine.saveActivityHistoryInst(activityInstId);
          ReturnType rt = this.rightEngine.saveActivityHistoryInst(activityInstId, his.getActivityHistoryId(), ctx);
          if (!rt.isSucess()) {
            return rt;
          }
          rt = this.workflowEngine.deleteActivityInst(activityInstId);
          if (!rt.isSucess()) {
            return rt;
          }
        }

      }

    }

    ReturnType rt = this.rightEngine.completeProcessInst(processInstID, ctx);
    if (!rt.isSucess()) {
      return rt;
    }
    rt = this.dataEngine.completeProcessInst(processInstID, ctx);
    if (!rt.isSucess()) {
      return rt;
    }
    rt = this.fileEngine.completeProcessInst(processInstID, ctx);
    if (!rt.isSucess()) {
      return rt;
    }
    rt = this.workflowEngine.completeProcessInst(processInstID);
    if (!rt.isSucess()) {
      return rt;
    }

    fireProcessEvent(eiProcessInst, 1012);
    return new ReturnType(1);
  }

  public ReturnType deleteProcessInst(String processInstID, Map ctx)
    throws BPMException
  {
    checkLogined();
    ctx = fillInUserID(ctx);
    EIProcessInst eiProcessInst = EIProcessInstManager.getInstance()
      .loadByKey(processInstID);

    fireProcessEvent(eiProcessInst, 1013);

    ReturnType rt = this.rightEngine.deleteProcessInst(processInstID, ctx);
    if (!rt.isSucess()) {
      return rt;
    }
    rt = this.dataEngine.deleteProcessInst(processInstID, ctx);
    if (!rt.isSucess()) {
      return rt;
    }
    rt = this.fileEngine.deleteProcessInst(processInstID, ctx);
    if (!rt.isSucess()) {
      return rt;
    }
    rt = this.workflowEngine.deleteProcessInst(processInstID);
    if (!rt.isSucess()) {
      return rt;
    }

    fireProcessEvent(eiProcessInst, 1014);
    return new ReturnType(1);
  }

  public void beginTransaction()
    throws BPMException
  {
    checkLogined();
    try {
      DbManager.getInstance().beginTransaction();
    } catch (SQLException sqle) {
      throw new BPMException(
        "Failed to beging transaction of client service.", sqle, 
        100000);
    }
  }

  public void commitTransaction()
    throws BPMException
  {
    checkLogined();
    try {
      DbManager.getInstance().endTransaction(true);
    } catch (SQLException sqle) {
      throw new BPMException(
        "Failed to commit transaction of client service.", sqle, 
        100001);
    }
  }

  public void rollbackTransaction()
    throws BPMException
  {
    checkLogined();
    try {
      DbManager.getInstance().endTransaction(false);
    } catch (SQLException sqle) {
      throw new BPMException(
        "Failed to rollback transaction of client service", sqle, 
        100002);
    }
  }

  private void checkLogined() throws BPMException {
    if (this.sessionHandle == null) {
      throw new BPMException("Not logined error!", 
        200);
    }
    WorkflowServer.activeSession(this.sessionHandle);
  }

  private void checkCanPerform(String activityInstId, Map ctx)
    throws BPMException
  {
    EIActivityInst eiActivityInst = EIActivityInstManager.getInstance()
      .loadByKey(activityInstId);

    if (eiActivityInst.getReceiveMethod().equals("RESEND")) {
      return;
    }
    String personId = this.connInfo.getUserID();
    Map tmpCtx = fillInUserID(ctx);
    tmpCtx.put("OARightConstants.PERMISSION", 
      "PERMISSION_PERFORM");
    boolean permission = queryPermissionToActivityInst(activityInstId, 
      tmpCtx);
    if (!permission)
      throw new BPMException("User has no right to perform!");
  }

  private Map fillInUserID(Map ctx)
  {
    Map result = ctx;
    if (result == null) {
      result = new HashMap();
    }

    result.put("RightEngine.USERID", this.connInfo.getUserID());
    return result;
  }

  public Object getProcessDefVersionRightAttribute(String processDefVersionId, String attName, Map ctx)
    throws BPMException
  {
    checkLogined();
    Map ctxRight = fillInUserID(ctx);
    return this.rightEngine.getProcessDefVersionRightAttribute(
      processDefVersionId, attName, ctxRight);
  }

  public Object getActivityDefRightAttribute(String activityDefId, String attName, Map ctx)
    throws BPMException
  {
    checkLogined();
    Map ctxRight = fillInUserID(ctx);
    return this.rightEngine.getActivityDefRightAttribute(activityDefId, attName, 
      ctxRight);
  }

  public Object getRouteDefRightAttribute(String routeDefId, String attName, Map ctx)
    throws BPMException
  {
    checkLogined();
    Map ctxRight = fillInUserID(ctx);
    return this.rightEngine.getRouteDefRightAttribute(routeDefId, attName, 
      ctxRight);
  }

  public Object getActivityInstRightAttribute(String activityInstId, String attName, Map ctx)
    throws BPMException
  {
    checkLogined();
    ctx = fillInUserID(ctx);
    return this.rightEngine.getActivityInstRightAttribute(activityInstId, 
      attName, ctx);
  }

  public Object getActivityInstHistoryRightAttribute(String activityInstHistoryId, String attName, Map ctx)
    throws BPMException
  {
    checkLogined();
    ctx = fillInUserID(ctx);
    return this.rightEngine.getActivityInstHistoryRightAttribute(
      activityInstHistoryId, attName, ctx);
  }

  public boolean queryPermissionToActivityInst(String activityInstId, Map ctx)
    throws BPMException
  {
    checkLogined();
    Map ctxRight = fillInUserID(ctx);
    return this.rightEngine.queryPermissionToActivityInst(activityInstId, 
      ctxRight);
  }

  public List queryAllPermissionToActivityInst(String activityInstId, Map ctx)
    throws BPMException
  {
    checkLogined();
    ctx = fillInUserID(ctx);
    List list = this.rightEngine.queryAllPermissionToActivityInst(
      activityInstId, ctx);
    return list;
  }

  public DataEngine getMapDAODataEngine() {
    return this.dataEngine;
  }

  public FileEngine getfileEngine() {
    return this.fileEngine;
  }

  public void setMapDAODataEngine(DataEngine daoataEngine) {
    this.dataEngine = daoataEngine;
  }
  public void setfileEngine(FileEngine fileEngine) {
    this.fileEngine = fileEngine;
  }

  public boolean canEndRead(String activityInstID, Map ctx) throws BPMException {
    Map ctxRight = fillInUserID(ctx);
    ActivityInst activityInst = getActivityInst(activityInstID);
    if (((activityInst.getState().equals("notStarted")) || 
      (activityInst.getState().equals("READ"))) && 
      (activityInst.getReceiveMethod().equals("READ")))
    {
      return true;
    }
    return false;
  }

  public ReturnType endRead(String activityInstID, Map ctx) throws BPMException {
    Map ctxRight = fillInUserID(ctx);
    EIActivityInstHistory activityInstHistory = this.workflowEngine.endRead(activityInstID, ctxRight);
    ReturnType ret = this.rightEngine.endRead(activityInstID, activityInstHistory.getActivityHistoryId(), ctxRight);
    ReturnType dret = this.dataEngine.endRead(activityInstID, activityInstHistory.getActivityHistoryId(), ctxRight);
    ReturnType fret = this.fileEngine.endRead(activityInstID, activityInstHistory.getActivityHistoryId(), ctxRight);
    if ((ret.isSucess()) && (dret.isSucess()) && (fret.isSucess())) {
      return new ReturnType(1);
    }
    return new ReturnType(-1);
  }

  public ReturnType deleteHistory(String activityInstHistoryID, Map ctx) throws BPMException {
    Map ctxRight = fillInUserID(ctx);
    String userId = (String)ctxRight.get("RightEngine.USERID");
    ReturnType wret = this.workflowEngine.deleteHistory(activityInstHistoryID, ctxRight);
    ReturnType rret = this.rightEngine.deleteHistory(activityInstHistoryID, ctxRight);
    ReturnType dret = this.dataEngine.deleteHistory(activityInstHistoryID, ctxRight);
    ReturnType fret = this.fileEngine.deleteHistory(activityInstHistoryID, ctxRight);
    getActivityInstHistory(activityInstHistoryID).setPersonAttribute(userId, "STATS", "DELETE");

    if ((wret.isSucess()) && (rret.isSucess()) && (dret.isSucess()) && (fret.isSucess())) {
      return new ReturnType(1);
    }
    return new ReturnType(-1);
  }

  public ReturnType restoreHistory(String activityInstHistoryID, Map ctx) throws BPMException
  {
    Map ctxRight = fillInUserID(ctx);
    String userId = (String)ctxRight.get("RightEngine.USERID");
    ReturnType wret = this.workflowEngine.restoreHistory(activityInstHistoryID, ctxRight);

    getActivityInstHistory(activityInstHistoryID).setPersonAttribute(userId, "STATS", "NORMAL");

    ReturnType rret = this.rightEngine.restoreHistory(activityInstHistoryID, ctxRight);
    if ((wret.isSucess()) && (rret.isSucess())) {
      return new ReturnType(1);
    }
    return new ReturnType(-1);
  }
  public ReturnType clearHistory(String activityInstHistoryID, Map ctx) throws BPMException {
    Map ctxRight = fillInUserID(ctx);
    String userId = (String)ctxRight.get("RightEngine.USERID");
    ReturnType wret = this.workflowEngine.clearHistory(activityInstHistoryID, ctxRight);
    ReturnType rret = this.rightEngine.clearHistory(activityInstHistoryID, ctxRight);
    ReturnType dret = this.dataEngine.clearHistory(activityInstHistoryID, ctxRight);
    ReturnType fret = this.fileEngine.clearHistory(activityInstHistoryID, ctxRight);

    getActivityInstHistory(activityInstHistoryID).setPersonAttribute(userId, "STATS", "CLEAR");
    if ((wret.isSucess()) && (rret.isSucess()) && (dret.isSucess()) && (fret.isSucess())) {
      return new ReturnType(1);
    }
    return new ReturnType(-1);
  }

  public ReturnType copyTo(String activityHistoryInstId, List readers)
    throws BPMException
  {
    List activityInstList = this.workflowEngine.copyActivityInst(activityHistoryInstId, readers.size());
    ReturnType ret = null;
    ret = this.dataEngine.copyTo(activityInstList, readers);
    if (!ret.isSucess()) {
      return ret;
    }
    ret = this.fileEngine.copyTo(activityInstList, readers);
    if (!ret.isSucess()) {
      return ret;
    }
    ret = this.rightEngine.copyTo(activityInstList, readers);
    if (!ret.isSucess()) {
      return ret;
    }
    return ret;
  }

  public void updateActivityInstMapDAO(String activityInstId, String processInstId, DataMap formdata)
    throws BPMException
  {
    EIActivityInst eiActivityInst = EIActivityInstManager.getInstance().loadByKey(activityInstId);
    Map eventContext = fillInUserID(null);
    fireActivityEvent(eiActivityInst, 2006, eventContext);
    this.dataEngine.updateActivityInstMapDAO(activityInstId, formdata, (String)eventContext.get("RightEngine.USERID"));
    this.fileEngine.updateActivityInstMapDAO(activityInstId, formdata, (String)eventContext.get("RightEngine.USERID"));
    fireActivityEvent(eiActivityInst, 2007, eventContext);
  }

  public ReturnType addPersonTagToHistory(String activityInstHistoryID, String tagName, Map ctx) throws BPMException {
    Map ctxRight = fillInUserID(ctx);
    return this.rightEngine.addPersonTagToHistory(activityInstHistoryID, tagName, ctxRight);
  }
  public ReturnType deletePersonTagToHistory(String activityInstHistoryID, String tagName, Map ctx) throws BPMException {
    Map ctxRight = fillInUserID(ctx);
    return this.rightEngine.deletePersonTagToHistory(activityInstHistoryID, tagName, ctxRight);
  }

  public List<ActivityInstHistory> getActivityInstHistoryList(Condition condition, Filter filter, Map ctx) throws BPMException {
    UtilTimer timer = new UtilTimer();
    try {
      checkLogined();
      Condition resultCon = condition;
      Filter resultFilter = filter;

      Map rightCtx = fillInUserID(ctx);
      Filter rightFilter = this.rightEngine
        .getActivityInstHistoryListFilter(rightCtx);
      if (rightFilter != null) {
        FilterChain filterChain = new FilterChain();
        filterChain.addFilter(resultFilter);
        filterChain.addFilter(rightFilter);
        resultFilter = filterChain;

        if ((rightFilter instanceof Condition))
        {
          if (resultCon == null)
            resultCon = (Condition)rightFilter;
          else {
            resultCon.addCondition((Condition)rightFilter, 100);
          }
        }

      }

      if (!getSystemCode().equals("cmailorg")) {
        Condition splitCon = new Condition(
          ConditionKey.ACTIVITYHISTORY_DEALMETHOD, 2, 
          "SPLITED");
        resultCon.addCondition(splitCon, 100);
      }

      List activityInstHistoryList = this.workflowEngine.getActivityInstHistoryList(
        resultCon, resultFilter);
      logger.debug(timer.timerString(5, "getActivityInstHistoryList"));
      return new WorkflowListProxy(activityInstHistoryList, getSystemCode());
    } catch (Exception e) {
      throw new BPMException("getActivityInstList error.", e, 
        1005);
    }
  }

  public boolean canPerform(String activityInstID, Map ctx) throws BPMException {
    ctx = fillInUserID(ctx);
    ctx.put("OARightConstants.PERMISSION", "PERMISSION_PERFORM");
    boolean isCanPerform = queryPermissionToActivityInst(activityInstID, 
      ctx);
    return isCanPerform;
  }
}