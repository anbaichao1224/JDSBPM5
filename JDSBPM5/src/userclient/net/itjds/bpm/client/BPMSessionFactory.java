package net.itjds.bpm.client;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import net.itjds.bpm.engine.AdminService;
import net.itjds.bpm.engine.BPMException;
import net.itjds.bpm.engine.BPMSessionHandle;
import net.itjds.bpm.engine.ConnectInfo;
import net.itjds.bpm.engine.WorkflowClientService;
import net.itjds.bpm.engine.WorkflowServer;
import net.itjds.common.org.base.OrgManager;
import net.itjds.common.org.base.OrgManagerFactory;
import net.itjds.common.org.base.PersonNotFoundException;
import net.itjds.common.org.impl.database.DbPerson;
import net.itjds.common.util.UUIDGenerator;
import org.apache.struts2.ServletActionContext;

public class BPMSessionFactory
{
  private static Map handleMap = new HashMap();

  public static BPMSessionHandle getSessionHandle(HttpServletRequest request)
  {
    HttpSession session = request.getSession(true);
    String sessionId = session.getId();
    BPMSessionHandle sessionHandle = 
      (BPMSessionHandle)handleMap
      .get(sessionId);
    if (sessionHandle == null) {
      if (session.getAttribute("sessionHandle") != null) {
        sessionHandle = (BPMSessionHandle)request.getSession().getAttribute("sessionHandle");
      }
      else {
        sessionHandle = newSessionHandle();
      }

      handleMap.put(sessionId, sessionHandle);
    }

    return sessionHandle;
  }

  private static BPMSessionHandle newSessionHandle()
  {
    return new BPMSessionHandle(UUIDGenerator.genUUID());
  }

  public WorkflowClientService newClientService(BPMSessionHandle sessionHandle, String systemCode)
    throws BPMException
  {
    WorkflowClientService client = WorkflowServer.getInstance().newWorkflowClientService(
      sessionHandle, systemCode);

    return client;
  }

  public WorkflowClientService newClientService(String personId, String systemCode)
    throws BPMException
  {
    BPMSessionHandle sessionHandle = null;
    WorkflowClientService service = null;
    OrgManager orgManager = OrgManagerFactory.getOrgManager();
    DbPerson person = null;
    HttpServletRequest request = ServletActionContext.getRequest();
    HttpSession session = request.getSession(true);
    try {
      person = (DbPerson)orgManager.getPersonByID(personId);
    } catch (PersonNotFoundException e) {
      throw new BPMException("personId [" + personId + 
        "] is exits");
    }
    if (person.getIP() == null) {
      person.setIP(getIpAddr(ServletActionContext.getRequest()));
      person.setStatus("available");
      ServletActionContext.getRequest().setAttribute("personId", person.getID());
    }

    if (!person.getIP().equals(
      getIpAddr(ServletActionContext.getRequest()))) {
      person.setIP(getIpAddr(ServletActionContext.getRequest()));
      ServletActionContext.getRequest().setAttribute("personId", person.getID());
    }

    person.setIP(getIpAddr(ServletActionContext.getRequest()));
    ServletActionContext.getRequest().setAttribute("personId", person.getID());

    if (request != null) {
      sessionHandle = getSessionHandle(request);
      if (sessionHandle == null) {
        sessionHandle = new BPMSessionHandle(UUIDGenerator.genUUID());
        session.setAttribute("sessionHandle", sessionHandle);
        handleMap.put(request.getSession().getId(), sessionHandle);
      }
    } else {
      sessionHandle = newSessionHandle();
    }
    request.getSession(true).setAttribute("personId", person.getID());
    service = newClientService(sessionHandle, systemCode);
    service.connect(
      new ConnectInfo(person.getID(), person.getID(), 
      person.getPassword()));
    return service;
  }

  private WorkflowClientService getClientService(BPMSessionHandle sessionHandle, String systemCode)
    throws BPMException
  {
    HttpServletRequest request = ServletActionContext.getRequest();
    HttpSession session = request.getSession(true);

    WorkflowClientService service = null;
    if (request != null)
    {
      if (session == null) {
        throw new BPMException("Session not available!");
      }
      if (systemCode == null) {
        systemCode = (String)session.getAttribute("systemCode");
      }
      if (systemCode == null) {
        systemCode = (String)request.getAttribute("systemCode");
      }
      if (systemCode == null) {
        systemCode = "oa";
      }
      if (sessionHandle == null) {
        sessionHandle = getSessionHandle(request);
      }

    }

    if (sessionHandle == null) {
      String personId = request.getParameter("personId");
      if (personId == null) {
        personId = (String)ServletActionContext.getRequest()
          .getSession().getAttribute("personId");
      }
      if (personId == null) {
        personId = (String)request.getAttribute("personId");
      }

      if (personId == null) {
        personId = (String)request.getSession(true).getAttribute("personId");
      }
      if (personId == null) {
        throw new BPMException("personId is null or notLogin");
      }

      service = newClientService(personId, systemCode);
    } else {
      try {
        service = WorkflowServer.getInstance().getWorkflowClientService(
          sessionHandle, systemCode);
      }
      catch (BPMException localBPMException)
      {
      }

    }

    return service;
  }

  public WorkflowClientService getClientService()
    throws BPMException
  {
    return getClientService(null);
  }

  public WorkflowClientService getClientService(String systemCode) throws BPMException
  {
    HttpServletRequest request = ServletActionContext.getRequest();

    BPMSessionHandle sessionHandle = getSessionHandle(request);
    if (sessionHandle == null)
    {
      throw new BPMException("Session not available!");
    }

    return getClientService(sessionHandle, systemCode);
  }

  public static WorkflowClientService getClientService(HttpServletRequest request, String systemCode)
    throws BPMException
  {
    if (systemCode == null) {
      systemCode = (String)request.getAttribute("systemCode");
      if ((systemCode == null) && (request.getSession() != null)) {
        systemCode = (String)request.getSession().getAttribute("systemCode");
      }
      if (systemCode == null) {
        systemCode = "oa";
      }
    }
    BPMSessionHandle sessionHandle = getSessionHandle(request);
    if (sessionHandle == null) {
      throw new BPMException("Session not available!");
    }

    return WorkflowServer.getInstance().getWorkflowClientService(
      sessionHandle, systemCode);
  }

  public AdminService getAdminService(WorkflowClientService client)
    throws BPMException
  {
    return WorkflowServer.getInstance().getAdminService(client);
  }

  public static String getIpAddr(HttpServletRequest request) {
    String ip = request.getHeader("X-Forwarded-For");
    if ((ip == null) || (ip.length() == 0) || ("unknown".equalsIgnoreCase(ip))) {
      ip = request.getHeader("Proxy-Client-IP");
    }
    if ((ip == null) || (ip.length() == 0) || ("unknown".equalsIgnoreCase(ip))) {
      ip = request.getHeader("WL-Proxy-Client-IP");
    }
    if ((ip == null) || (ip.length() == 0) || ("unknown".equalsIgnoreCase(ip))) {
      ip = request.getHeader("HTTP_CLIENT_IP");
    }
    if ((ip == null) || (ip.length() == 0) || ("unknown".equalsIgnoreCase(ip))) {
      ip = request.getHeader("HTTP_X_FORWARDED_FOR");
    }
    if ((ip == null) || (ip.length() == 0) || ("unknown".equalsIgnoreCase(ip))) {
      ip = request.getRemoteAddr();
    }
    return ip;
  }
}