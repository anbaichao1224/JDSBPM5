package com.kzxd.filter;

public class User {
  public static int USERNO=0;
  private String id;
  private String name;
  private String password;
  private String ip;
  
  public String getIp() {
	return ip;
}

public void setIp(String ip) {
	this.ip = ip;
}

public String getId() {
    return id;
  }
  
  public void setId(String id) {
    this.id = id;
  }
  
  public String getName() {
    return name;
  }
  
  public void setName(String name) {
    this.name = name;
  }
  
  public String getPassword() {
    return password;
  }
  
  public void setPassword(String password) {
    this.password = password;
  }
  
  public String toString(){
    return "name:"+name;
  }
}
