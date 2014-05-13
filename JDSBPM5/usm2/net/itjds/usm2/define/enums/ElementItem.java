package net.itjds.usm2.define.enums;

public enum ElementItem {
	none("none"),
	yesno(" [[1,'��'],[0,'��']]"),
	allowPushMessages("[[1,'Both'],[2,'Critical'],[3,'Normal'],[4,'None']]"),
	isCnuses("[[0,'������'],[1,'����']]"),
	isEnuses("[[0,'Disabled'],[1,'Enabled']]"),
	sex(" [[0,'��'],[1,'Ů'],[2,'����']]"),
	degree("[[1,'Сѧ'],[2,'����'],[3,'����'],[4,'����'],[5,'˶ʿ'],[6,'��ʿ'],[7,'��ʿ��']]"),
	accountStatus("[[0,'��ʱ�˺�'],[1,'��ͨ�˺�(��ͨ�˺�(����120�첻��½����ʧЧ������ֹ��))'],[2,'����']]"),
	departStatus("[[0,'����'],[1,'����'],[2,'ɾ��']]"),
	isyes(" [['true','��'],['false','��']]"),
	align("[['left','�������'],['right','���Ҷ���'],['center','���ж���']]"),
	xtype("[['hidden','Hidden'],['numberfield','NumberField'],['textfield','TextField'],['textarea','TextArea'],['timefield','TimeField'],['datefield','DateField'],['none','None']]"),
	vtype("[['alpha','��ĸ'],['alphanum','��ĸ����'],['email','�ʼ�'],['url','url����']]");
	private String type;

	public String getType() {
		return type;
	}

	ElementItem(String type) {
		this.type = type;
	}
}
