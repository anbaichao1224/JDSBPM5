<%@ page import="net.sf.json.JSONArray" %>
<%@ page import="net.sf.json.JSONObject" %>
<%@ page import="java.util.Random" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  String param=request.getParameter("param");
  if(param==null){
    param="";
  }
  JSONArray arr=new JSONArray();
  int count=10;
  java.util.Random rand=new Random();
  for (int i = 0; i < count; i++) {
    JSONObject tmp = new JSONObject();
    tmp.put("name", "测试" + i+param);
    tmp.put("val1",500+rand.nextInt(1000)+0.5);
    tmp.put("val2",1000+rand.nextInt(1000));
    tmp.put("val3",1500+rand.nextInt(1000));
    arr.add(tmp);
  }
  out.println(arr.toString());
%>