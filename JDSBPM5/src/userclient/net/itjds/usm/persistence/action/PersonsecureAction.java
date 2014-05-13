package net.itjds.usm.persistence.action;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import net.itjds.usm.persistence.model.Person;
import net.itjds.usm.persistence.model.Personextaccount;
import net.itjds.usm.persistence.model.Personsecure;
import net.itjds.usm.persistence.service.PersonManager;
import net.itjds.usm.persistence.service.PersonextaccountManager;
import net.itjds.usm.persistence.service.PersonsecureManager;

import org.appfuse.webapp.action.BaseAction;

public class PersonsecureAction extends BaseAction {
	private PersonsecureManager personsecureManager;
	private PersonManager personManager;
	private Personsecure personsecure = new Personsecure();
	private Person person = new Person();
	private List<Personsecure> list = new ArrayList<Personsecure>(0);
	private List<Person> personlist = new ArrayList<Person>(0);
	private String json;
	private String txtCheckValue;
	private String sysid;

	public String getSysid() {
		return sysid;
	}

	public void setSysid(String sysid) {
		this.sysid = sysid;
	}

	public String getJson() {
		return json;
	}

	public void setJson(String json) {
		this.json = json;
	}

	public String personsecureRemove() {
		String arr[] = txtCheckValue.split(",");
		for (int i = 0; i < arr.length; i++) {
			personsecureManager.remove(arr[i]);
		}
		return SUCCESS;
	}

	/**
	 * 保存系统管理员
	 * 
	 * @return
	 */
	public String personsecureSave() {
		String arr[] = txtCheckValue.split(",");
		for (int i = 0; i < arr.length; i++) {
			Personsecure m = new Personsecure();
			m.setAdminid(arr[i]);
			m.setSysid(sysid);
			m.setWebmasterid(arr[i]);
			m.setCreatetime(new Timestamp(System.currentTimeMillis()));
			personsecureManager.save(m);
		}
		return SUCCESS;
	}

	/**
	 * 取系统管理员
	 * 
	 * @return
	 */
	public String personsecurejson() {
		// list=personextaccountManager.getAll();
		list = personsecureManager
				.getByCondition("from Personsecure p where p.sysid='" + sysid
						+ "'");
		String str = "";
		json = "{totalCount:" + list.size() + ",data:[";
		for (int i = 0; i < list.size(); i++) {
			personsecure = (Personsecure) list.get(i);
			person = personManager.get(personsecure.getAdminid());
			str += "{uuid:'" + personsecure.getUuid() + "',webadminid:'"
					+ personsecure.getWebmasterid() + "',adminid:'"
					+ person.getCnname() + "',createtime:'"
					+ personsecure.getCreatetime().toString() + "'},";
		}
		if (!"".equals(str)) {
			str = str.substring(0, str.length() - 1);
		}
		json += str;
		json += "]}";
		return SUCCESS;
	}

	public PersonsecureManager getPersonsecureManager() {
		return personsecureManager;
	}

	public void setPersonsecureManager(PersonsecureManager personsecureManager) {
		this.personsecureManager = personsecureManager;
	}

	public Personsecure getPersonsecure() {
		return personsecure;
	}

	public void setPersonsecure(Personsecure personsecure) {
		this.personsecure = personsecure;
	}

	public List<Personsecure> getList() {
		return list;
	}

	public void setList(List<Personsecure> list) {
		this.list = list;
	}

	public String getTxtCheckValue() {
		return txtCheckValue;
	}

	public void setTxtCheckValue(String txtCheckValue) {
		this.txtCheckValue = txtCheckValue;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public List<Person> getPersonlist() {
		return personlist;
	}

	public void setPersonlist(List<Person> personlist) {
		this.personlist = personlist;
	}

	public PersonManager getPersonManager() {
		return personManager;
	}

	public void setPersonManager(PersonManager personManager) {
		this.personManager = personManager;
	}

}
