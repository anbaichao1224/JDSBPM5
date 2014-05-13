package net.itjds.common.org.util;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.itjds.bpm.engine.BPMException;
import net.itjds.common.logging.Log;
import net.itjds.common.logging.LogFactory;
import net.itjds.common.org.base.Org;
import net.itjds.common.org.base.OrgManagerFactory;
import net.itjds.common.org.base.OrgNotFoundException;
import net.itjds.common.org.base.Person;

public class PersonExtTree
{
  protected static Log log = LogFactory.getLog("org", 
    PersonExtTree.class);

  private List persons = new ArrayList();
  private Org[] orgs;
  private Org topOrg;
  private String topOrgId;
  private boolean viewPerson = true;

  public PersonExtTree(List persons, String topOrgId, boolean viewPerson)
  {
    Map orgMap = new HashMap();
    this.viewPerson = viewPerson;
    this.topOrgId = topOrgId;
    for (int i = 0; i < persons.size(); i++) {
      Org[] orgs = ((Person)persons.get(i)).getOrgs();
      if (orgs != null) {
        for (int j = 0; j < orgs.length; j++) {
          if (!orgMap.containsKey(orgs[j].getID())) {
            addOrg(orgMap, orgs[j]);
          }
        }
      }
    }
    this.orgs = ((Org[])orgMap.values().toArray(new Org[0]));

    this.persons = persons;
  }

  public String getChildTree(String orgId)
  {
    if ((orgId == null) || (orgId.equals("toproot"))) {
      return getTopTree();
    }

    Org org = null;
    try {
      if (orgId.startsWith("org")) {
        orgId = orgId.substring(3, orgId.length());
      }
      org = OrgManagerFactory.getOrgManager().getOrgByID(orgId);
    } catch (OrgNotFoundException e) {
      e.printStackTrace();
    }
    ExtNode childNode = new ExtNode();
    if ((org == null) || (!isOrgInOrgs(org, this.orgs)))
    {
      childNode.setId(org.getID());
      childNode.setText("读取错误");
      childNode.setCnName("读取错误");
      childNode.setUid("orgerr");
      childNode.setLeaf("true");
    } else {
      childNode.setId(org.getID());
      childNode.setText(org.getName());
      childNode.setCnName(org.getName());
      childNode.setUid(org.getID());

      if (this.viewPerson)
      {
        addPersonItem(childNode, org);
      }

      if (childNode.getChildren().size() > 0)
        childNode.setLeaf("true");
      else {
        childNode.setLeaf("false");
      }
      if (org.getChildren().length > 0) {
        Org[] ccorgs = org.getChildren();
        List list = new ArrayList();
        for (int k = 0; k < ccorgs.length; k++) {
          list.add(ccorgs[k]);
        }

        CommonComparator comparator = new CommonComparator();
        comparator.setFields_user(new String[] { "index" });
        Collections.sort(list, comparator);

        for (int j = 0; j < list.size(); j++) {
          Org ccorg = (Org)list.get(j);
          if (isOrgInOrgs(ccorg, this.orgs)) {
            addChildNode(childNode, ccorg, this.orgs);
          }
        }
      }

    }

    ExtTree tree = new ExtTree();
    tree.setNode(childNode);
    StringBuffer layoutJson = new StringBuffer();
    tree.serializChild(layoutJson);
    return layoutJson.toString();
  }
  public String getChildTreeBf(String orgId,String[] addr) throws BPMException
  {
    if ((orgId == null) || (orgId.equals("toproot"))) {
      return getTopTreeBf( addr);
    }

    Org org = null;
    try {
      if (orgId.startsWith("org")) {
        orgId = orgId.substring(3, orgId.length());
      }
      org = OrgManagerFactory.getOrgManager().getOrgByID(orgId);
    } catch (OrgNotFoundException e) {
      e.printStackTrace();
    }
    ExtNode childNode = new ExtNode();
    if ((org == null) || (!isOrgInOrgs(org, this.orgs)))
    {
      childNode.setId(org.getID());
      childNode.setText("读取错误");
      childNode.setCnName("读取错误");
      childNode.setUid("orgerr");
      childNode.setLeaf("true");
    } else {
      childNode.setId(org.getID());
      childNode.setText(org.getName());
      childNode.setCnName(org.getName());
      childNode.setUid(org.getID());

      if (this.viewPerson)
      {
        addPersonItem(childNode, org);
      }

      if (childNode.getChildren().size() > 0)
        childNode.setLeaf("true");
      else {
        childNode.setLeaf("false");
      }
      if (org.getChildren().length > 0) {
        Org[] ccorgs = org.getChildren();
        List list = new ArrayList();
        for (int k = 0; k < ccorgs.length; k++) {
          list.add(ccorgs[k]);
        }

        CommonComparator comparator = new CommonComparator();
        comparator.setFields_user(new String[] { "index" });
        Collections.sort(list, comparator);

        for (int j = 0; j < list.size(); j++) {
          Org ccorg = (Org)list.get(j);
          if (isOrgInOrgs(ccorg, this.orgs)) {
            addChildNode(childNode, ccorg, this.orgs);
          }
        }
      }

    }

    ExtTree tree = new ExtTree();
    tree.setNode(childNode);
    StringBuffer layoutJson = new StringBuffer();
    tree.serializChildBf(layoutJson,addr);
    return layoutJson.toString();
  }
  private String getTopTreeBf(String[] addr) throws BPMException
  {
    String rootStr = "点击选择组织机构";
    ExtNode root = new ExtNode();
    root.setId(this.topOrg.getID());
    root.setLeaf("false");
    root.setText(this.topOrg.getName());
    root.setCnName(this.topOrg.getName());
    root.setUid(this.topOrg.getID());
    if (this.orgs.length == 0) {
      rootStr = "没有设定阅办人";
      root.setText(rootStr);
      root.setCnName(rootStr);
      root.setLeaf("true");
    } else {
      List list = new ArrayList();
      for (int k = 0; k < this.topOrg.getChildren().length; k++) {
        list.add(this.topOrg.getChildren()[k]);
      }

      CommonComparator comparator = new CommonComparator();
      comparator.setFields_user(new String[] { "index" });
      Collections.sort(list, comparator);
      for (int k = 0; k < list.size(); k++) {
        addChildNode(root, (Org)list.get(k), this.orgs);
      }
    }
    ExtTree tree = new ExtTree();
    tree.setNode(root);
    StringBuffer layoutJson = new StringBuffer();
    tree.serializChildBf(layoutJson,addr);
    return layoutJson.toString();
  }
  private String getTopTree()
  {
    String rootStr = "点击选择组织机构";
    ExtNode root = new ExtNode();
    root.setId(this.topOrg.getID());
    root.setLeaf("false");
    root.setText(this.topOrg.getName());
    root.setCnName(this.topOrg.getName());
    root.setUid(this.topOrg.getID());
    if (this.orgs.length == 0) {
      rootStr = "没有设定阅办人";
      root.setText(rootStr);
      root.setCnName(rootStr);
      root.setLeaf("true");
    } else {
      List list = new ArrayList();
      for (int k = 0; k < this.topOrg.getChildren().length; k++) {
        list.add(this.topOrg.getChildren()[k]);
      }

      CommonComparator comparator = new CommonComparator();
      comparator.setFields_user(new String[] { "index" });
      Collections.sort(list, comparator);
      for (int k = 0; k < list.size(); k++) {
        addChildNode(root, (Org)list.get(k), this.orgs);
      }
    }
    ExtTree tree = new ExtTree();
    tree.setNode(root);
    StringBuffer layoutJson = new StringBuffer();
    tree.serializChild(layoutJson);
    return layoutJson.toString();
  }

  private void addOrg(Map map, Org org)
  {
    if (org != null) {
      map.put(org.getID(), org);
      Org parent = org.getParent();

      if (parent != null) {
        if ((this.topOrgId != null) && (parent.getID().equals(this.topOrgId))) {
          this.topOrg = parent;
          map.put(parent.getID(), parent);
        }
        else if (!map.containsKey(parent.getID())) {
          addOrg(map, parent);
        }
      }
      else
      {
        this.topOrg = org;
      }
    }
  }

  private boolean isOrgInOrgs(Org org, Org[] orgs)
  {
    for (int i = 0; i < orgs.length; i++) {
      if (org.getID().equals(orgs[i].getID())) {
        return Boolean.TRUE.booleanValue();
      }
    }
    return false;
  }

  protected void addPersonItem(ExtNode parentnode, Org org) {
    CommonComparator comparator = new CommonComparator();
    comparator.setFields_user(new String[] { "index" });
    Collections.sort(this.persons, comparator);

    for (int i = 0; this.persons.size() > i; i++) {
      Person person = (Person)this.persons.get(i);

      if ((person.getAccount().equals("yanpinga")) || 
        (person.getAccount().equals("yanpingb")) || 
        (person.getAccount().equals("yanpingc")) || 
        (!isPersonInOrg(org, person.getID()))) continue;
      ExtNode inputNode = new ExtNode();
      inputNode.setId(person.getID());
      inputNode.setText(person.getName());
      inputNode.setCnName(org.getName());
      inputNode.setUid(person.getID());

      inputNode.setLeaf("true");
      parentnode.addChild(inputNode);
      inputNode.setParent(parentnode);
    }
  }

  private boolean isPersonInOrg(Org org, String personId)
  {
    String[] personIds = org.getPersonIds();
    for (int i = 0; i < personIds.length; i++) {
      if (personId.equalsIgnoreCase(personIds[i])) {
        return Boolean.TRUE.booleanValue();
      }
    }
    return false;
  }

  private ExtNode addChildNode(ExtNode parentnode, Org org, Org[] orgs)
  {
    if (isOrgInOrgs(org, orgs)) {
      ExtNode inputNode = new ExtNode();
      inputNode.setId("org" + org.getID());
      inputNode.setText(org.getName());
      inputNode.setCnName(org.getName());
      inputNode.setUid(org.getID());

      inputNode.setLeaf("false");
      parentnode.addChild(inputNode);
      inputNode.setParent(parentnode);
      if (this.viewPerson)
      {
        addPersonItem(inputNode, org);
      }

    }

    return parentnode;
  }

  class CommonComparator
    implements Comparator
  {
    String[] fields_user = null;

    CommonComparator() {
    }
    public String[] getFields_user() { return this.fields_user; }

    public void setFields_user(String[] fields_user) {
      this.fields_user = fields_user;
    }

    public int compare(Object obj1, Object obj2)
    {
      if ((this.fields_user == null) || (this.fields_user.length <= 0))
      {
        return 2;
      }
      for (int i = 0; i < this.fields_user.length; )
      {
        if (compareField(obj1, obj2, this.fields_user[i]))
        {
          return 1;
        }

        return -1;
      }

      return 0;
    }

    private boolean compareField(Object o1, Object o2, String fieldName)
    {
      try
      {
        String value1 = getFieldValueByName(fieldName, o1).toString();
        String value2 = getFieldValueByName(fieldName, o2).toString();

        if (Integer.parseInt(value1) > Integer.parseInt(value2))
        {
          return true;
        }
      }
      catch (Exception e) {
        PersonExtTree.log.info("---------对象的该属性不存在或者不允许在此安全级别上反射该属性，详情请查阅JAVA DOC--------");
        e.printStackTrace();
      }
      return false;
    }

    private Object getFieldValueByName(String fieldName, Object obj)
    {
      try
      {
        String Letter = fieldName.substring(0, 1).toUpperCase();
        String methodStr = "get" + Letter + fieldName.substring(1);
        Method method = obj.getClass().getMethod(methodStr, new Class[0]);

        Object value = method.invoke(obj, new Object[0]);
        return value;
      }
      catch (Exception e) {
        PersonExtTree.log.info("---------该" + fieldName + "属性不存在----------------------");
      }return null;
    }
  }
}