package net.itjds.common.org.util;

import net.itjds.bpm.engine.BPMException;

public class ExtTree
{
  public static final String TOPID = "toproot";
  ExtNode node;

  public void serializ(StringBuffer layoutJson)
  {
    layoutJson.append("[");
    this.node.serializ(layoutJson);
    layoutJson.deleteCharAt(layoutJson.length() - 1);
    layoutJson.append("]");
  }

  public void serializChild(StringBuffer layoutJson)
  {
    layoutJson.append("[");
    this.node.serializChild(layoutJson);
    layoutJson.deleteCharAt(layoutJson.length() - 1);
    layoutJson.append("]");
  }
  public void serializChildBf(StringBuffer layoutJson,String[] addr) throws BPMException
  {
    layoutJson.append("[");
    this.node.serializChildBf(layoutJson,addr);
    layoutJson.deleteCharAt(layoutJson.length() - 1);
    layoutJson.append("]");
  }
  public ExtNode getNode() {
    return this.node;
  }

  public void setNode(ExtNode node) {
    this.node = node;
  }
}