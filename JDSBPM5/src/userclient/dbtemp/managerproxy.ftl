package ${package}.db.${tempbeanid}.database;

import net.itjds.j2ee.dao.annotation.DBTable;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import ${package}.db.${tempbeanid}.*;
import ${package}.db.${tempbeanid}.inner.*;
import net.itjds.usm2.db.*;
import net.itjds.usm2.db.org.proxy.*;
<#list tableList as item>		  
import ${package}.db.${item.className?lower_case}.*;

	
	@MapdaoBeanAnnotation(id="${tempbean.id}UsmService",
		name="${tempbean.name}",
		expressionArr="${tempbean.id}UsmServiceImpl()",
		desc="${tempbean.desc} by DAOTools ",
		<#if tempbean.flowType??>
		flowType="${tempbean.flowType}",
		</#if>
		dataType="${tempbean.dataType}"
		)	
    public ${table.className}UsmServiceImpl()  {
    
    private static final Log log = LogFactory.getLog(USMConstants.CONFIG_KEY,
			${table.className}UsmServiceImpl.class);
		
    private static EI${table.className}Manager  singleton = new Db${table.className}OrgManager() ;

	public int delete(Usm pObject) throws USMException {
		return singleton.delete((EIRoOrg) pObject);
	}

	public int deleteByKey(String uuid) throws USMException {
		return singleton.deleteByKey(uuid);
	}

	public int deleteByWhere(String where) throws USMException {
		return singleton.deleteByWhere(where);
	}

	public UsmProxy getUsmByKey(String pkName) {
		Usm eiObj=null;
		try {
			eiObj = singleton.loadByKey(pkName);
		} catch (USMException e) {
			e.printStackTrace();
		}
	    Usm defaultproxy=this.getProxyInstance(this.defaultProxyClass, eiObj);
		UsmProxy usmProxy=(UsmProxy) ActionContext.getContext().getValueStack().findValue("$newUsmProxy");
		if (usmProxy!=null){
			usmProxy=(UsmProxy) this.getProxyInstance(usmProxy.getClass(), defaultproxy);
			return usmProxy;
		}else{
			return defaultproxy;
		}
	}

	public List<UsmProxy> getUsmWhere(String where) {
	   List<Usm> usms  =null;
		try {
			usms = singleton.loadByWhere(where);
		} catch (USMException e) {
			e.printStackTrace();
		}
		return new USMListProxy(usms);
	}

	
	public List<UsmProxy> getUsmWhere(String where,long start,long limit) {
	   List<Usm> usms  =null;
		try {
			usms = singleton.loadByWhere(where,start,limit);
		} catch (USMException e) {
			e.printStackTrace();
		}
		return new USMListProxy(usms);
	}
	

	public UsmProxy save(Usm pObject) throws USMException {
		EI${table.className} usm=singleton.save((EI${table.className})pObject);
		return pObject;
	}

	public List save(List pObjects) throws USMException {
		EI${table.className}[] usms=new EI${table.className}[pObjects.size()];
		for(int k=0;k<usms.length;k++){
			usms[k]=(EI${table.className}) pObjects.get(k);
		}
		singleton.save(usms);
	
		return pObjects;
	}
	
	public Object create() throws USMException {
		Object eiObj=singleton.createRoOrg();
		  Object defaultproxy=this.getProxyInstance(this.defaultProxyClass, eiObj);
			Object usmProxy=ActionContext.getContext().getValueStack().findValue("$newUsmProxy");
			if (usmProxy!=null){
				usmProxy=this.getProxyInstance(usmProxy.getClass(), defaultproxy);
				return usmProxy;
			}else{
				return defaultproxy;
			}
		
	}
	
	private  Object getProxyInstance(Class clazz,Object eiObj){
		Object defaultproxy=null;
		Constructor con=null;
		try {
			con = clazz.getConstructor(eiObj.getClass().getInterfaces()[0]);
			defaultproxy= con.newInstance(eiObj);
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return defaultproxy;
	}
	
	
    }
		
