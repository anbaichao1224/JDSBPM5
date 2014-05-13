package net.itjds.usm2.define.enums;

public enum ElementButton {
	none("none"),
	delete("删除"),
	update("修改"),
	cancel("取消"),
	resend("重置"),
	abort("退出"),
	query("查询"),
	add("新增"),
	save("保存"),
	serialindex("保存排序"),

	editstyle("编辑表单样式"),
	importPerson("导入"),
	synLDAP("同步LDAP数据"),
	rebuild("重新生成"),
	bindfield("编绑定数据"),
	createfield("增加字段");
	private String type;

	public String getType() {
		return type;
	}
	ElementButton(String type){
		this.type = type;
	}
	
}
