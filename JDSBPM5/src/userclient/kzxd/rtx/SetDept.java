package kzxd.rtx;
import rtx.RTXSvrApi;   
public class SetDept
 {
	//2007 ���Գɹ�
    public static void main(String[] args) {

    	String deptId= "5tyuu";
    	String DetpInfo = "�޸�SDK���Բ���ΪTestDept";
    	String DeptName = "SDK���Բ���";
    	String ParentDeptId = "1";
    	
    	int iRet = -1;
    	
    	RTXSvrApi  RtxsvrapiObj = new RTXSvrApi();   
    	String[] ids = RtxsvrapiObj.getChildDepts("1000");
    	for(String id:ids){
    	}
    	if( RtxsvrapiObj.Init())
    	{   
    		iRet = RtxsvrapiObj.setDept(deptId,DetpInfo,DeptName,ParentDeptId);
    		
    		if (iRet == 0)
    		{
    			
    		}
    		else 
    		{
    		}

	    }	
    	RtxsvrapiObj.UnInit();
    	
    }
}
