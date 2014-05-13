package net.itjds.usm2.define.enums;

public enum ElementVtype {
	regex("regex"),//为自定正则验证类型
	none("none"),//不验证
	alpha("alpha"), //字母
	alphanum("alphanum"),//字母数字 
	email("email"),
	url("url"),
	telephone("\\d{3}-\\d{8}|\\d{4}-\\d{7}"),//国内电话 匹配形式如 0511-4405222 或 021-87888822 
    ip("\\d+\\.\\d+\\.\\d+\\.\\d+"),//IP地址
	zipcode("[1-9]\\d{5}(?!\\d)"),//邮政编码
	chinese("[\u4e00-\u9fa5]"),//只能输入中文
	idcard("\\d{15}|\\d{18}"),//身份证
	qq("[1-9][0-9]{4,}[0-9]*$");//qq号码
	private String type;

	public String getType() {
		return type;
	}

	ElementVtype(String type) {
		this.type = type;
	}
}
