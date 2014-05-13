package com.kzxd.filter;

import javax.servlet.http.HttpSession;

public class SessionAndUser {
  private String      userID;
  private String      sid;
  private HttpSession session;
  private String ip;
  public String getIp() {
	return ip;
}

public void setIp(String ip) {
	this.ip = ip;
}

public String getUserID() {
    return userID;
  }
  
  public void setUserID(String userID) {
    this.userID = userID;
  }
  
  public String getSid() {
    return sid;
  }
  
  public void setSid(String sid) {
    this.sid = sid;
  }
  
  public HttpSession getSession() {
    return session;
  }
  
  public void setSession(HttpSession session) {
    this.session = session;
  }
  
}
