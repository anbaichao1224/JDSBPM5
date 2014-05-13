package net.itjds.worklist.list.support.rules;

import net.itjds.bpm.client.ActivityInst;
import net.itjds.bpm.client.ActivityInstHistory;
import net.itjds.bpm.data.xmlproxy.EsbBeanAnnotation;
import net.itjds.bpm.engine.BPMException;
import net.itjds.worklist.list.support.GridUtils;
import net.itjds.worklist.list.support.annotation.RuleDefine;

@EsbBeanAnnotation(id="worklist.mark", name="new 标记", expressionArr="MarkRule()", desc="new 标记", dataType="action")
@RuleDefine(key="mark", mapping="net.itjds.worklist.list.support.rules.MarkRule", name="new 标记")
public class MarkRule
  implements Rule
{
  public Object invoke(ActivityInst ai)
  {
    String mark = "";
    try {
      String dwdj1 = ai.getProcessInst().getAttribute("mark");
      if ((dwdj1 == null) || (dwdj1.equals("")))
        mark = "";
      else
        mark = GridUtils.filterJsEnter(dwdj1);
    }
    catch (BPMException e)
    {
      e.printStackTrace();
    }
    return mark;
  }

  public Object invoke(ActivityInstHistory aih)
  {
    String mark = "";
    try {
      String dwdj1 = aih.getProcessInst().getAttribute("mark");
      if ((dwdj1 == null) || (dwdj1.equals("")))
        mark = "";
      else
        mark = GridUtils.filterJsEnter(dwdj1);
    }
    catch (BPMException e)
    {
      e.printStackTrace();
    }
    return mark;
  }
}