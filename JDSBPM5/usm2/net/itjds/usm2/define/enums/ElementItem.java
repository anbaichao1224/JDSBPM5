package net.itjds.usm2.define.enums;

public enum ElementItem {
	none("none"),
	yesno(" [[1,'是'],[0,'否']]"),
	allowPushMessages("[[1,'Both'],[2,'Critical'],[3,'Normal'],[4,'None']]"),
	isCnuses("[[0,'不可用'],[1,'可用']]"),
	isEnuses("[[0,'Disabled'],[1,'Enabled']]"),
	sex(" [[0,'男'],[1,'女'],[2,'保密']]"),
	degree("[[1,'小学'],[2,'初中'],[3,'高中'],[4,'本科'],[5,'硕士'],[6,'博士'],[7,'博士后']]"),
	accountStatus("[[0,'临时账号'],[1,'普通账号(普通账号(连续120天不登陆，即失效（被禁止）))'],[2,'永久']]"),
	departStatus("[[0,'正常'],[1,'禁用'],[2,'删除']]"),
	isyes(" [['true','是'],['false','否']]"),
	align("[['left','居左对齐'],['right','居右对齐'],['center','居中对齐']]"),
	xtype("[['hidden','Hidden'],['numberfield','NumberField'],['textfield','TextField'],['textarea','TextArea'],['timefield','TimeField'],['datefield','DateField'],['none','None']]"),
	vtype("[['alpha','字母'],['alphanum','字母数字'],['email','邮件'],['url','url连接']]");
	private String type;

	public String getType() {
		return type;
	}

	ElementItem(String type) {
		this.type = type;
	}
}
