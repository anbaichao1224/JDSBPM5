package net.itjds.usm.persistence.action;
import org.apache.struts2.ServletActionContext;
import org.appfuse.webapp.action.BaseAction;
import net.itjds.usm.persistence.service.OrgManager;
import net.itjds.usm.persistence.service.PersonManager;
import net.itjds.usm.persistence.service.UsmLog;
import net.itjds.usm.persistence.model.Org;
import net.itjds.usm.persistence.model.Person;
import net.itjds.usm.util.CacheUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.itjds.common.org.base.OrgManagerFactory;
import net.itjds.common.org.impl.database.DbPerson;
import net.itjds.common.org.impl.database.DbSubSystemCacheManager;
import net.itjds.j2ee.util.UUID;
import net.itjds.usm.orgtree.OrgExtTreeDisplay;
public class DepartAction extends BaseAction{
	private OrgManager orgManager;
    private Org depart;
    private String sysid;
    private String id;
    private String json="";
    private String orgid;
    private String orgidjsonData;
    private String name;
    private String parentorgid;
	private String start;
	private String limit;
    private List<Org> orglist = new ArrayList<Org>(0);
    
    private String txtCheckValue;
   
    public String getTxtCheckValue() {
		return txtCheckValue;
	}

	public void setTxtCheckValue(String txtCheckValue) {
		this.txtCheckValue = txtCheckValue;
	}

	int j=1;
    String result="";
    
    PersonManager personManager; //人员信息管理
	Person personinfo = new Person();
	String ischoose;
	private String childOrgId;

	private String choose;
	
	private OrgExtTreeDisplay orgExtTree;
	private List performers = null;
	
    private Integer orglevel;
    public List getPerformers() {
		return performers;
	}

	public void setPerformers(List performers) {
		this.performers = performers;
	}

	public Person getPersoninfo() {
		return personinfo;
	}

	public void setPersoninfo(Person personinfo) {
		this.personinfo = personinfo;
	}

	public PersonManager getPersonManager() {
		return personManager;
	}

	public void setPersonManager(PersonManager personManager) {
		this.personManager = personManager;
	}

	//添加部门
	public String orgSave()
	{

		UUID uuid = new UUID();
		depart.setOrgid(String.valueOf(uuid));
		depart.setOrgtype(getOrgTypeId(depart.getOrgtype()));
		depart.setStatus(getStatusId(depart.getStatus()));
		depart.setOrglevel(orglevel+1);
		depart.setMemo(depart.getMemo());
		orglist=orgManager.getByCondition("from Org order by  serialindex desc");
		orgManager.save(depart);
		SmallBean bean = new SmallBean();
		bean.setMsm("增加部门："+depart.getCnname());
		bean.setCreatedate(new Date());
		bean.setUuid((new UUID()).toString());
		UsmLog usm = new UsmLog();
		usm.save(bean);
		DbSubSystemCacheManager.getInstance("org").getOrgCache().clear();
		return SUCCESS;
	}
	
	public String orgQuery(){
		String str="";
		String sql="select org from Org org,System system,Sysorg sysorg  where  system.sysid=sysorg.sysid and sysorg.orgid=org.orgid and system.sysid='"+sysid+"'";
		orglist = orgManager.getByCondition(sql);
		json="{totalCount:"+orglist.size()+",data:[";
		for(int i=0;i<orglist.size();i++){
			depart=(Org)orglist.get(i);
			str+="{uuid:'"+depart.getUuid()+"',sysid:'"+sysid+"',orgid:'"+depart.getCnname() +"',flag:'"+ depart.getFax() +"'},";
		}
		if(!"".equals(str)){
		str=str.substring(0,str.length()-1);
		}
		json+=str;
		json+="]}";
		return SUCCESS;
	}
	/**
	 * 取部门
	 * @return
	 */
	public String orgGrid(){
		String str="";
		orglist=orgManager.getAll();
		int count=Integer.parseInt(start)+Integer.parseInt(limit);
		if(count>orglist.size()){
			orglist=orglist.subList(Integer.parseInt(start), orglist.size());
		}else{
			orglist=orglist.subList(Integer.parseInt(start), Integer.parseInt(start)+Integer.parseInt(limit));
		}
		json="{totalCount:"+orgManager.getAll().size()+",data:[";
		for(int i=0;i<orglist.size();i++){
			depart=(Org)orglist.get(i);
			str+="{orgid:'"+depart.getOrgid()+"',cnname:'"+depart.getCnname() +"',desc:'"+ depart.getFax() +"'},";
		}
		if(!"".equals(str)){
		str=str.substring(0,str.length()-1);
		}
		json+=str;
		json+="]}";
		return SUCCESS;
	}
	
	public String  orgtreesuccess()
	{
		return SUCCESS;
	}
	
   public String getOrgtreeJson(){
	   if (orgExtTree==null){
			this.orgExtTree=new OrgExtTreeDisplay(OrgManagerFactory.getOrgManager().getTopOrgs()[0].getID(),null);	
	   }
	   String treeStr="";
	   if(name==null){
		   treeStr=orgExtTree.getChildTree(childOrgId,true,choose);
	   }else{
		   if(name.equals("person")){
			   treeStr=orgExtTree.getChildTree(childOrgId,true,choose);
		   }else{
			   treeStr=orgExtTree.getChildTree(childOrgId,false,choose);
		   }
	   }
	   return treeStr;
	
	}
	
	 public String departInfo()
	 {
		 depart= orgManager.get(parentorgid);
		 return SUCCESS;
	 }
	 
	 public String getDepartDetailInfo()
	 {
		 
		 depart= orgManager.get(orgid);
		 return SUCCESS;
	 }
	 
	 
	 //查询部门信息,并在grid显示各别部门信息字段
	 public String getDepartInfo()
	 {
			try{
				if("".equals(name)||name ==null)
				{
				orglist = orgManager.getAll();
				}
				else
				{
					String sql = "from Org where cnname like '%"+name+"%'";
					orglist = orgManager.getByCondition(sql);
				}
				
				int count=Integer.parseInt(start)+Integer.parseInt(limit);
				if(count>orglist.size()){
					orglist=orglist.subList(Integer.parseInt(start), orglist.size());
				}else{
					orglist=orglist.subList(Integer.parseInt(start), Integer.parseInt(start)+Integer.parseInt(limit));
				}

					String str="";
					json="{totalCount:"+orgManager.getAll().size()+",data:[";
					for(int i=0;i<orglist.size();i++)
					{
						depart= (Org)orglist.get(i);
						String orgtype="0";
						if (depart.getOrgtype()!=null){
							orgtype=depart.getOrgtype();
						}
						if(orgtype.equals("0"))
						{
							depart.setOrgtype("普通部门");
						}
						if(orgtype.equals("1"))
						{
							depart.setOrgtype("实体部门");
						}
						
						
						String status="0";
						if (depart.getStatus()!=null){
							status=depart.getStatus();
						}
						if(status.equals("0"))
						{
							depart.setStatus("正常");
						}
						
						if(status.equals("1"))
						{
							depart.setStatus("禁用");
						}
						if(status.equals("2"))
						{
							depart.setStatus("删除");
						}
						str+="{orgid:'"+depart.getOrgid()+"',parentorgid:'"+depart.getParentorgid()+"',cnname:'"+depart.getCnname() +"',enname:'"+depart.getEnname() +"'," +
								"status:'"+depart.getStatus()+"'," +
								"orgtype:'"+ depart.getOrgtype() +"',intel:'"+ depart.getIntel() +"',memo:'"+ depart.getMemo() +"'," +
								"orglevel:'"+ depart.getOrglevel() +"',outtel:'"+ depart.getOuttel()+"',nighttel:'"+ depart.getNighttel()+"'," +
								"fax:'"+ depart.getFax()+"',serialindex:'"+ depart.getSerialindex()+"'},";	
					}
					
					if(!"".equals(str)){
						str=str.substring(0,str.length()-1);
					}
					json+=str.replaceAll("null", "");
					json+="]}";
					return SUCCESS;
			}catch(Exception ex){
				System.out.println(ex.getLocalizedMessage());
				return ERROR;
			}
		}
	 /**
	  * 部门排序
	  * @return
	  */
	 public String departSaveSort(){
			String arr[]=txtCheckValue.split(",");
			for(int i=0;i<arr.length;i++){
					depart=orgManager.get(arr[i]);
					depart.setSerialindex(i+10001);
					orgManager.save(depart);
					 CacheUtil.clearCache(arr[i]);
			}
			return SUCCESS;
		}	
	 
	 //查询用户组下的无选择的所有部门
	 public String getUserGroupNotDepartInfo()
	 {
		String sql="";	
		 try{
				if("".equals(name)||name ==null)
				{
				    sql = "select o.orgid,o.cnname,o.enname,o.status,o.orgtype,o.intel,o.memo,o.orglevel,o.outtel,o.nighttel,o.fax,o.serialindex from ro_org o " +
                          "";
					orglist = orgManager.getBySQL(sql);
				}
				else
				{
					sql = "select o.orgid,o.cnname,o.enname,o.status,o.orgtype,o.intel,o.memo,o.orglevel,o.outtel,o.nighttel,o.fax,o.serialindex from ro_org o "+
						"";
					orglist = orgManager.getBySQL(sql);
				}
				
				int count=Integer.parseInt(start)+Integer.parseInt(limit);
				if(count>orglist.size()){
					orglist=orglist.subList(Integer.parseInt(start), orglist.size());
				}else{
					orglist=orglist.subList(Integer.parseInt(start), Integer.parseInt(start)+Integer.parseInt(limit));
				}

					String str="";
					json="{totalCount:"+orgManager.getBySQL(sql).size()+",data:["; 
					for(Iterator it=orglist.iterator();it.hasNext();){
						Object[] obj = (Object[])it.next();   
						
						str+="{orgid:'"+obj[0]+"',cnname:'"+obj[1] +"',enname:'"+obj[2] +"'," +
								"status:'"+obj[3]+"'," +
								"orgtype:'"+ obj[4] +"',intel:'"+ obj[5] +"',memo:'"+ obj[6] +"'," +
								"orglevel:'"+ obj[7] +"',outtel:'"+ obj[8]+"',nighttel:'"+ obj[9] +"'," +
								"fax:'"+ obj[10]+"',serialindex:'"+ obj[11]+"'},";	
						
					}
					if(!"".equals(str)){
						str=str.substring(0,str.length()-1);
					}
					json+=str;
					json+="]}";
					return SUCCESS;
			}catch(Exception ex){
				System.out.println(ex.getLocalizedMessage());
				return ERROR;
			}
		}
	 
	 //查询角色下的无选择的所有部门
	 public String getRoleNotDepartInfo()
	 {
		String sql="";	
		 try{
				if("".equals(name)||name ==null)
				{
				    sql = "select o.orgid,o.cnname,o.enname,o.status,o.orgtype,o.intel,o.memo,o.orglevel,o.outtel,o.nighttel,o.fax,o.serialindex from ro_org o " +
                          "where  not exists(select  orgr.roleid from ro_orgrole orgr, ro_role r where orgr.roleid = r.roleid and orgr.orgid = o.orgid)";
					orglist = orgManager.getBySQL(sql);
				}
				else
				{
					sql = "select o.orgid,o.cnname,o.enname,o.status,o.orgtype,o.intel,o.memo,o.orglevel,o.outtel,o.nighttel,o.fax,o.serialindex from ro_org o "+
						" where o.cnname like '%"+name+"%' and  not exists(select  orgr.roleid from ro_orgrole orgr, ro_role r where orgr.roleid = r.roleid and orgr.orgid = o.orgid)";
					orglist = orgManager.getBySQL(sql);
				}
				
				int count=Integer.parseInt(start)+Integer.parseInt(limit);
				if(count>orglist.size()){
					orglist=orglist.subList(Integer.parseInt(start), orglist.size());
				}else{
					orglist=orglist.subList(Integer.parseInt(start), Integer.parseInt(start)+Integer.parseInt(limit));
				}

					String str="";
					json="{totalCount:"+orgManager.getBySQL(sql).size()+",data:["; 
					for(Iterator it=orglist.iterator();it.hasNext();){
						Object[] obj = (Object[])it.next();   
						
						str+="{orgid:'"+obj[0]+"',cnname:'"+obj[1] +"',enname:'"+obj[2] +"'," +
								"status:'"+obj[3]+"'," +
								"orgtype:'"+ obj[4] +"',intel:'"+ obj[5] +"',memo:'"+ obj[6] +"'," +
								"orglevel:'"+ obj[7] +"',outtel:'"+ obj[8]+"',nighttel:'"+ obj[9] +"'," +
								"fax:'"+ obj[10]+"',serialindex:'"+ obj[11]+"'},";	
						
					}
					if(!"".equals(str)){
						str=str.substring(0,str.length()-1);
					}
					json+=str;
					json+="]}";
					return SUCCESS;
			}catch(Exception ex){
				System.out.println(ex.getLocalizedMessage());
				return ERROR;
			}
		}
	 
	 
	 
	 //按部门名称查询	
	 public String DepartNameQuery()
	 {
		 try{
		 String sql = "select o from Org  o where o.cnname='"+name+"'";
		 orglist = (List<Org>)orgManager.getByCondition(sql);
		   String str="";
			json="{totalCount:"+orglist.size()+",data:[";
			for(int i=0;i<orglist.size();i++)
			{
				depart  = (Org)orglist.get(i);
				str+="{orgid:'"+depart.getOrgid()+"',cnname:'"+depart.getCnname() +"',enname:'"+depart.getEnname() +"'," +
						"status:'"+depart.getStatus()+"'," +
						"orgtype:'"+ depart.getOrgtype() +"',intel:'"+ depart.getIntel() +"',memo:'"+ depart.getMemo() +"'," +
						"orglevel:'"+ depart.getOrglevel() +"',outtel:'"+ depart.getOuttel()+"',nighttel:'"+ depart.getNighttel()+"'," +
						"fax:'"+ depart.getFax()+"',serialindex:'"+ depart.getSerialindex()+"'},";	
			}
			if(!"".equals(str)){
				str=str.substring(0,str.length()-1);
			}
			json+=str;
			json+="]}";
			return SUCCESS;
			}catch(Exception ex){
				System.out.println(ex.getLocalizedMessage());
				return ERROR;
			}
	 }

		//删除指定行
	  public String departPointDel()
		{
		
			
			String value = orgidjsonData;
			String[] names = value.split("\\,");
			for (int i = 0; i < names.length; i++) { 
			    orgManager.remove(names[i]);
			    CacheUtil.clearCache(names[i]);
			    continue;
			  }
			DbSubSystemCacheManager.getInstance("org").getOrgCache().clear();
			return SUCCESS;
			
		}
		
		//部门信息修改
		public String departModify()
		{
			try{	
				
				Org or = orgManager.get(orgid);
				or.setCnname(depart.getCnname());
				or.setEnname(depart.getEnname());
				or.setOrgtype(getOrgTypeId(depart.getOrgtype()));
				or.setStatus(getStatusId(depart.getStatus()));
				or.setIntel(depart.getIntel());
				or.setMemo(depart.getMemo());
				or.setOrglevel(depart.getOrglevel());
				or.setOuttel(depart.getOuttel());
				or.setNighttel(depart.getNighttel());
				or.setFax(depart.getFax());
				orgManager.save(or);
				 CacheUtil.clearCache(orgid);
			//	DbSubSystemCacheManager.getInstance("org").orgCache.clear();
			return SUCCESS;
			}catch(Exception ex){
				System.out.println(ex.getLocalizedMessage());
				return ERROR;
			}
			
		}
		
		//子部门列表
		public String childDepartListGrid()
		{
			String sql="";
			try
			{
				if("".equals(name)||name ==null)
				{
					sql = "select o from Org o where o.parentorgid='"+parentorgid+"'";
				    orglist = orgManager.getByCondition(sql);
				}
				else
				{
					sql = "select o from Org o where o.parentorgid='"+parentorgid+"' and  o.cnname like '%"+name+"%'";
					orglist = orgManager.getByCondition(sql);
				}
				
				
				String str="";
				int count=Integer.parseInt(start)+Integer.parseInt(limit);
				
				if(count>orglist.size()){
					orglist=orglist.subList(Integer.parseInt(start), orglist.size());
				}else{
					orglist=orglist.subList(Integer.parseInt(start), Integer.parseInt(start)+Integer.parseInt(limit));
				}
				
				json="{totalCount:"+orgManager.getByCondition(sql).size()+",data:[";
				for(int i=0;i<orglist.size();i++)
				{
					depart= (Org)orglist.get(i);
					String orgtype="0";
					if (depart.getOrgtype()!=null){
						orgtype=depart.getOrgtype();
					}
					if(orgtype.equals("0"))
					{
						depart.setOrgtype("普通部门");
					}
					if(orgtype.equals("1"))
					{
						depart.setOrgtype("实体部门");
					}
					
					
					String status="0";
					if (depart.getStatus()!=null){
						status=depart.getStatus();
					}
					if(status.equals("0"))
					{
						depart.setStatus("正常");
					}
					
					if(status.equals("1"))
					{
						depart.setStatus("禁用");
					}
					if(status.equals("2"))
					{
						depart.setStatus("删除");
					}
					str+="{orgid:'"+depart.getOrgid()+"',cnname:'"+depart.getCnname() +"',enname:'"+depart.getEnname() +"'," +
							"status:'"+depart.getStatus()+"'," +
							"orgtype:'"+ depart.getOrgtype() +"',intel:'"+ depart.getIntel() +"',memo:'"+ depart.getMemo() +"'," +
							"orglevel:'"+ depart.getOrglevel() +"',parentorgid:'"+ depart.getParentorgid()+"',outtel:'"+ depart.getOuttel()+"',nighttel:'"+ depart.getNighttel()+"'," +
							"fax:'"+ depart.getFax()+"',serialindex:'"+ depart.getSerialindex()+"'},";	
				}
				
				if(!"".equals(str)){
					str=str.substring(0,str.length()-1);
				}
				json+=str.replaceAll("null", "");
				json+="]}";
				return SUCCESS;
			}catch(Exception ex){
				System.out.println(ex.getLocalizedMessage());
				return ERROR;
			}
		}
		
		
	 
	 public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public OrgManager getOrgManager() {
			return orgManager;
		}

		public void setOrgManager(OrgManager orgManager) {
			this.orgManager = orgManager;
		}

		public Org getDepart() {
			return depart;
		}

		public void setDepart(Org depart) {
			this.depart = depart;
		}

		public List<Org> getListorg() {
			return orglist;
		}

		public void setListorg(List<Org> listorg) {
			this.orglist = listorg;
		}

		public String getStatusId(String status){
			 String statusId = "0";
			 if(status.equals("正常"))
			 {
				 statusId = "0";
			 }
			 if(status.equals("禁用"))
			 {
				 statusId = "1";
			 }
			 if(status.equals("删除"))
			 {
				 statusId = "2";
			 }
			 return statusId;
		}
		

		public String getOrgTypeId(String orgtype){
			 String orgtypeId = "0";
			 if(orgtype.equals("普通部门"))
			 {
				 orgtypeId = "0";
			 }
			 if(orgtype.equals("实体部门"))
			 {
				 orgtypeId = "1";
			 }
			 return orgtypeId;
		}

		public String getJson() {
			return json;
		}

		public void setJson(String json) {
			this.json = json;
		}
		public String getOrgid() {
			return orgid;
		}
		public void setOrgid(String orgid) {
			this.orgid = orgid;
		}
		public String getOrgidjsonData() {
			return orgidjsonData;
		}
		public void setOrgidjsonData(String orgidjsonData) {
			this.orgidjsonData = orgidjsonData;
		}




		public List<Org> getOrglist() {
			return orglist;
		}


		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public void setOrglist(List<Org> orglist) {
			this.orglist = orglist;
		}


		public String getParentorgid() {
			return parentorgid;
		}


		public void setParentorgid(String parentorgid) {
			this.parentorgid = parentorgid;
		}

		public String getSysid() {
			return sysid;
		}

		public void setSysid(String sysid) {
			this.sysid = sysid;
		}

		public String getLimit() {
			return limit;
		}

		public void setLimit(String limit) {
			this.limit = limit;
		}

		public String getStart() {
			return start;
		}

		public void setStart(String start) {
			this.start = start;
		}

		public String getIschoose() {
			return ischoose;
		}

		public void setIschoose(String ischoose) {
			this.ischoose = ischoose;
		}

		public String getChildOrgId() {
			return childOrgId;
		}

		public void setChildOrgId(String childOrgId) {
			this.childOrgId = childOrgId;
		}

		public Integer getOrglevel() {
			return orglevel;
		}
		public void setOrglevel(Integer orglevel) {
			this.orglevel = orglevel;
		}

		public String getChoose() {
			return choose;
		}

		public void setChoose(String choose) {
			this.choose = choose;
		}
		

	
	



}
