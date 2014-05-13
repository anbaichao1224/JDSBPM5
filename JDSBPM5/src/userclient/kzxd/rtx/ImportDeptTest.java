package kzxd.rtx;

import rtx.RTXSvrApi;

public class ImportDeptTest {
	public static void main(String[] args) {
		RTXSvrApi  RtxsvrapiObj = new RTXSvrApi();
		RtxsvrapiObj.deleteDept("6","1");
	}
}
