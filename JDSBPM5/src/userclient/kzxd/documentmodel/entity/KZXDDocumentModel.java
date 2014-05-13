package kzxd.documentmodel.entity;

/**
 * 
 * @author 李洋
 * KZXD_DOCUMENTMODEL表对应的实体类
 * 
 */
public class KZXDDocumentModel {
	
	private String UUID;   //模版ID
	private String modelName;  //模版名称
	private String modelPath;  //模版路径
	private String modelType;  //模版类型
	private String modelUploadDept;  //模版归属部门
	private int modelOder;
	
	//提供的set get方法
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
