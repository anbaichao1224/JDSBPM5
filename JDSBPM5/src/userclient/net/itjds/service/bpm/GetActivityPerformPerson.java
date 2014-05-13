package net.itjds.service.bpm;

import com.opensymphony.xwork2.ActionContext;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import net.itjds.bpm.client.ActivityInstHistory;
import net.itjds.bpm.client.BPMSessionFactory;
import net.itjds.bpm.engine.BPMException;
import net.itjds.bpm.engine.WorkflowClientService;
import net.itjds.common.expression.function.AbstractFunction;
import net.itjds.common.org.base.Person;
import org.apache.struts2.ServletActionContext;

public class GetActivityPerformPerson extends AbstractFunction
{
  public Person perform(String activityDefId) {
    Person person = null;
    try {
      String activityInstId = getActivityInstId();
      WorkflowClientService client = new BPMSessionFactory().getClientService();
      List hisList = client.getActivityInstHistoryListByProcessInst(client.getActivityInst(activityInstId).getProcessInstId(), null);
      for (int i = hisList.size() - 1; i > -1; i--) {
        ActivityInstHistory his = (ActivityInstHistory)hisList.get(i);
        if (his.getActivityDefId().equals(activityDefId)) {
          List performers = (List)client.getActivityInstHistoryRightAttribute(his.getActivityHistoryId(), "PERFORMER", null);
          if (performers.size()>0){
        	  person = (Person)performers.get(0);
        	  break;
          }
        
          
        }
      }
    } catch (BPMException e) {
      e.printStackTrace();
    }
    return person;
  }

  private String getActivityInstId() {
    HttpServletRequest request = ServletActionContext.getRequest();
    ActionContext context = ActionContext.getContext();
    String param = "activityInstId";
    String value = request.getParameter(param);
    if (value == null) {
      value = (String)request.getAttribute(param);
    }
    if (value == null) {
      value = (String)context.getValueStack().findValue(param, String.class);
    }
    if (value == null) {
      value = (String)context.getSession().get(param);
    }

    return value;
  }
}