package net.itjds.usm2.define.enums;

public enum ElementVtype {
	regex("regex"),//Ϊ�Զ�������֤����
	none("none"),//����֤
	alpha("alpha"), //��ĸ
	alphanum("alphanum"),//��ĸ���� 
	email("email"),
	url("url"),
	telephone("\\d{3}-\\d{8}|\\d{4}-\\d{7}"),//���ڵ绰 ƥ����ʽ�� 0511-4405222 �� 021-87888822 
    ip("\\d+\\.\\d+\\.\\d+\\.\\d+"),//IP��ַ
	zipcode("[1-9]\\d{5}(?!\\d)"),//��������
	chinese("[\u4e00-\u9fa5]"),//ֻ����������
	idcard("\\d{15}|\\d{18}"),//���֤
	qq("[1-9][0-9]{4,}[0-9]*$");//qq����
	private String type;

	public String getType() {
		return type;
	}

	ElementVtype(String type) {
		this.type = type;
	}
}
