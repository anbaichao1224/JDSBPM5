package kzxd.documentmodel.entity;

/**
 * 
 * @author ����
 * KZXD_DOCUMENTMODEL���Ӧ��ʵ����
 * 
 */
public class KZXDDocumentModel {
	
	private String UUID;   //ģ��ID
	private String modelName;  //ģ������
	private String modelPath;  //ģ��·��
	private String modelType;  //ģ������
	private String modelUploadDept;  //ģ���������
	private int modelOder;
	
	//�ṩ��set get����
	public String getUUID() {
		return UUID;
	}
	public void setUUID(String uuid) {
		UUID = uuid;
	}
	
	public String getModelName() {
		return modelName;
	}
	public void setModelName(String modelName) {
		this.modelName = modelName;
	}
	public String getModelPath() {
		return modelPath;
	}
	public void setModelPath(String modelPath) {
		this.modelPath = modelPath;
	}
	public String getModelType() {
		return modelType;
	}
	public void setModelType(String modelType) {
		this.modelType = modelType;
	}
	public String getModelUploadDept() {
		return modelUploadDept;
	}
	public void setModelUploadDept(String modelUploadDept) {
		this.modelUploadDept = modelUploadDept;
	}
	public int getModelOder() {
		return modelOder;
	}
	public void setModelOder(int modelOder) {
		this.modelOder = modelOder;
	}
	

}
