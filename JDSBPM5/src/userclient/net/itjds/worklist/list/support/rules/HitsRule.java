package net.itjds.worklist.list.support.rules;

import net.itjds.bpm.client.ActivityInst;
import net.itjds.bpm.client.ActivityInstHistory;
import net.itjds.bpm.data.xmlproxy.EsbBeanAnnotation;
import net.itjds.bpm.engine.BPMException;
import net.itjds.worklist.list.support.GridUtils;
import net.itjds.worklist.list.support.annotation.RuleDefine;

@EsbBeanAnnotation(id="worklist.hits", name="�����", expressionArr="HitsRule()", desc="�����", dataType="action")
@RuleDefine(key="hits", mapping="net.itjds.worklist.list.support.rules.HitsRule", name="�����")
public class HitsRule
  implements Rule
{
  public Object invoke(ActivityInst ai)
  {
    String hits = "";
    try {
      String dwdj1 = ai.getProcessInst().getAttribute("hits");
      if ((dwdj1 == null) || (dwdj1.equals("")))
        hits = "";
      else
        hits = GridUtils.filterJsEnter(dwdj1);
    }
    catch (BPMException e)
    {
      e.printStackTrace();
    }
    return hits;
  }

  public Object invoke(ActivityInstHistory aih)
  {
    String hits = "";
    try {
      String dwdj1 = aih.getProcessInst().getAttribute("hits");
      if ((dwdj1 == null) || (dwdj1.equals("")))
        hits = "";
      else
        hits = GridUtils.filterJsEnter(dwdj1);
    }
    catch (BPMException e)
    {
      e.printStackTrace();
    }
    return hits;
  }
}