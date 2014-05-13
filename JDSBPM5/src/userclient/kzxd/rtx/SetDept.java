package kzxd.rtx;
import rtx.RTXSvrApi;   
public class SetDept
 {
	//2007 测试成功
    public static void main(String[] args) {

    	String deptId= "5tyuu";
    	String DetpInfo = "修改SDK测试部门为TestDept";
    	String DeptName = "SDK测试部门";
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
