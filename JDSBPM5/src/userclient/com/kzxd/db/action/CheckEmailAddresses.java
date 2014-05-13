package com.kzxd.db.action;

import java.util.List;

public class CheckEmailAddresses {

	  @SuppressWarnings({ "unchecked", "unused" })
	private List emailAddresses;
	  
	  @SuppressWarnings("unchecked")
	public void setEmailAddresses(List emailAddresses) {
	    this.emailAddresses = emailAddresses;
	  }
	  
	  public void run() {
	    // iterate over all email addresses and archive them
	  }

}
