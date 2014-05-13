package net.itjds.usm2.action;


import net.itjds.usm2.db.DbManager;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class UpdateAction extends ActionSupport {

	private String expression;
	public String jsonStr;

	public String update() throws Exception {
		DbManager.getInstance().beginTransaction();
		jsonStr=ActionContext.getContext().getValueStack().findString(expression);
	
		DbManager.getInstance().endTransaction(true);
		return Action.SUCCESS;
	}

	public String getExpression() {
		return expression;
	}
	
	public void setExpression(String expression) {
		this.expression = expression;
	}

	public String getJsonStr() {
		return jsonStr;
	}

	public void setJsonStr(String jsonStr) {
		this.jsonStr = jsonStr;
	}
}
