package net.itjds.usm2.define.enums;

public enum ElementButton {
	none("none"),
	delete("ɾ��"),
	update("�޸�"),
	cancel("ȡ��"),
	resend("����"),
	abort("�˳�"),
	query("��ѯ"),
	add("����"),
	save("����"),
	serialindex("��������"),

	editstyle("�༭����ʽ"),
	importPerson("����"),
	synLDAP("ͬ��LDAP����"),
	rebuild("��������"),
	bindfield("�������"),
	createfield("�����ֶ�");
	private String type;

	public String getType() {
		return type;
	}
	ElementButton(String type){
		this.type = type;
	}
	
}
