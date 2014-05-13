package net.itjds.usm.persistence.action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.appfuse.webapp.action.BaseAction;

import net.itjds.common.database.DBBeanBase;
import net.itjds.common.org.impl.database.DbPerson;
import net.itjds.j2ee.util.UUID;
import com.opensymphony.xwork2.ActionContext;
import net.itjds.usm.persistence.model.Org;
import net.itjds.usm.persistence.model.Orgperson;
import net.itjds.usm.persistence.model.Person;
import net.itjds.usm.persistence.model.Personaccount;

import net.itjds.usm.persistence.service.OrgManager;
import net.itjds.usm.persistence.service.OrgpersonManager;
import net.itjds.usm.persistence.service.PersonManager;
import net.itjds.usm.persistence.service.PersonaccountManager;
import net.itjds.usm.persistence.service.PersonextaccountManager;
import net.itjds.usm.persistence.service.UsmLog;
import net.itjds.usm.util.CacheUtil;

@SuppressWarnings("serial")
public class PersonAction extends BaseAction {

	PersonextaccountManager personextaccountManager; // ��Ա�˺Ź���
	PersonManager personManager; // ��Ա��Ϣ����
	Person personinfo = new Person();
	String json;
	String personid;
	String personidjsonData;
	String orgid;
	String parentorgid;
	String name;
	String start;
	String limit;
	String pname;
	String newOrgid;
	private String userid;
	private String birthday;
	private String new_pass;
	PersonaccountManager personaccountManager;
	Personaccount personaccount = new Personaccount();
	List<Personaccount> personAccountList = new ArrayList<Personaccount>(0);
	List<Person> personList = new ArrayList<Person>(0);

	// ��Ա�������м��
	OrgpersonManager orgpersonManager;
	Orgperson orgperson = new Orgperson();

	// ����
	OrgManager orgManager;
	Org depart = new Org();
	List<Org> orglist = new ArrayList<Org>(0);

	private String txtCheckValue;

	private File file;
	private String fileFileName;
	private String fileContentType;

	String result = "";
	int j = 1;

	public String getTxtCheckValue() {
		return txtCheckValue;
	}

	public void setTxtCheckValue(String txtCheckValue) {
		this.txtCheckValue = txtCheckValue;
	}

	// ָ����Ա��ϸ��Ϣ��
	public String personDetailInfo() {
		personinfo = personManager.get(personid);
		personaccount = personaccountManager.get(personid);
		return SUCCESS;
	}

	public String getPersonDetailInfo() {

		personinfo = personManager.get(personid);
		personaccount = personaccountManager.get(personid);
		return SUCCESS;
	}

	public String passwordModify() {
		StringBuffer sql = new StringBuffer("");
		sql.append("update ro_personaccount pa set pa.password='");
		sql.append(personaccount.getPassword());
		sql.append("' where pa.personid='");
		sql.append(personaccount.getPersonid());
		sql.append("' and pa.uuid ='");
		sql.append(personaccount.getUuid());
		sql.append("'");
		CacheUtil.clearCache(personaccount.getPersonid());
		CacheUtil.clearCache(personaccount.getUserid());
		int flag = 0;
		DBBeanBase dbbase = new DBBeanBase("org");
		Connection conn = null;
		Statement stmt = null;
		conn = dbbase.getConn();
		try {
			stmt = conn.createStatement();
			flag = stmt.executeUpdate(sql.toString());
			if (flag == 0) {
				conn.rollback();
				stmt.close();
				conn.close();
				dbbase.close();
			} else {
				stmt.close();
				conn.close();
				dbbase.close();
			}
		} catch (SQLException e) {
			// dbbase.close();
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null) {
					try {
						stmt.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if (conn != null) {
						try {
							conn.close();
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
				} catch (Exception e) {

				} finally {
					if (dbbase != null) {
						dbbase.close();
					}
				}
			}
		}
		personinfo = personManager.get(personaccount.getPersonid());

		personaccount = personaccountManager.get(personaccount.getPersonid());
		DbPerson p = (DbPerson) ActionContext.getContext().getValueStack()
				.findValue("$currPerson"); // ���� �޸������ɼ�ʱ��¼
		p.setPassword(personaccount.getPassword());

		// new
		// RtxUser().addUser(personaccount.getUserid(),personaccount.getUserid(),personaccount.getPassword(),personid,"",personinfo.getCnname(),personinfo.getSex());

		return SUCCESS;
	}

	// ��Ա��Ϣ����
	public String personSave() {

		try {
			Date date = new Date();
			InetAddress address = InetAddress.getLocalHost(); // ��ñ���ip
			// �û��˺���Ϣ
			String uuid = new UUID().toString();
			personaccount.setPersonid(uuid);
			personaccount.setLastloginip(address.getHostAddress());// ����½ip
			personaccount.setLastlogindate(new Timestamp(System
					.currentTimeMillis()));// ����½ʱ��
			personaccount.setAccountstat(getPersonAccountstatId(personaccount
					.getAccountstat())); // �����˺�״̬ID
			personaccount.setCreatetime(new Timestamp(System
					.currentTimeMillis()));
			personaccount.setPassword(new_pass);
			personaccountManager.save(personaccount);

			// ��Ա������Ϣ
			personinfo.setPersonid(String.valueOf(uuid));
			personinfo.setSex(getPersonsexId(personinfo.getSex()));// �����ձ�ID
			personinfo.setMarry(getPersonMarryId(personinfo.getMarry())); // ������ID
			personinfo.setPoliticalstat(getPersonPoliticalstatId(personinfo
					.getPoliticalstat()));// ����������ò
			personinfo.setLastedulevel(getPersonLastedulevelId(personinfo
					.getLastedulevel()));// �������ѧ��
			personinfo.setLastdegree(getPersonLastdegreeId(personinfo
					.getLastdegree()));// �������ѧλ
			personinfo.setHousehao(personinfo.getHousehao()); // �����

			personManager.save(personinfo);

			// ��Ա���ű�
			CacheUtil.clearCache(orgperson.getOrgid());
			CacheUtil.clearCache(orgperson.getPersonid());
			orgperson.setPersonid(String.valueOf(uuid));
			orgpersonManager.save(orgperson);
			Org org = orgManager.get(orgperson.getOrgid());
			SmallBean bean = new SmallBean();
			bean.setMsm("������Ա��"+personinfo.getCnname());
			bean.setCreatedate(new Date());
			bean.setUuid((new UUID()).toString());
			UsmLog usm = new UsmLog();
			usm.save(bean);
			return SUCCESS;
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			return ERROR;
		}
	}

	// ��ѯ�û��˺ű�Personaccount
	public String getPersonAcountInfo() {
		try {
			personAccountList = personaccountManager.getAll();
			String str = "";
			json = "{totalCount:" + personAccountList.size() + ",data:[";
			for (int i = 0; i < personAccountList.size(); i++) {
				personaccount = (Personaccount) personAccountList.get(i);
				str += "{uuid:'" + personaccount.getUuid() + "',userid:'"
						+ personaccount.getUserid() + "',createtime:'"
						+ personaccount.getCreatetime().toString() + "'},";
			}
			str = str.substring(0, str.length() - 1);
			json += str;
			json += "]}";
			return SUCCESS;
		} catch (Exception ex) {
			System.out.println(ex.getLocalizedMessage());
			return ERROR;
		}
	}

	public String personFlag() {

		String a = "";
		DBBeanBase dbbase;
		Connection conn;
		dbbase = new DBBeanBase("bpm", true);
		conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			conn = dbbase.getConn();
			String sql = "select userid from ro_personaccount where userid=?";
			pst = conn.prepareStatement(sql);
			pst.setString(1, userid);
			rs = pst.executeQuery();
			while (rs.next()) {
				a = rs.getString("userid");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (a == null || a.equals("")) {
			return null;
		} else {
			return ERROR;
		}

	}

	// ��ѯ��Ա���˺ű�,����grid��ʾ������Ա��Ϣ�ֶ�
	public String getPersonInfo() {
		try {
			String sql = "";
			if ("".equals(name) || name == null) {
				sql = "select ro_person.uuid, ro_person.personid, ro_org.orgid, ro_person.cnname,"
						+ " ro_person.enname,ro_personaccount.password, "
						+ "ro_personaccount.accountstat, ro_personaccount.accountttl,"
						+ "ro_org.cnname a,ro_person.email1,ro_person.zip,ro_person.city,"
						+ "ro_person.country,ro_person.nation,ro_person.nativeplace,"
						+ "ro_person.officetel,ro_person.mobile,ro_person.photo,ro_person.type,"
						+ "ro_person.officefax,ro_person.hometel,ro_person.homefax,"
						+ "ro_person.homeadd,ro_person.officeadd,ro_person.connectaddr,"
						+ "ro_person.school,ro_person.marry, "
						+ "ro_person.politicalstat,ro_person.lastedulevel,ro_person.lastdegree, "
						+ "ro_person.sex,"
						+ "ro_personaccount.passquestion,ro_personaccount.passanswer,"
						+ "ro_person.birthday,ro_person.duty,ro_person.job,ro_person.househao "
						+ "from ro_person,ro_orgperson,ro_org,ro_personaccount "
						+ "where ro_person.personid = ro_orgperson.personid and ro_org.orgid = ro_orgperson.orgid and "
						+ "ro_personaccount.personid = ro_person.personid";
				personList = personManager.getBySQL(sql);
			} else {

				sql = "select ro_person.uuid, ro_person.personid, ro_org.orgid, ro_person.cnname,"
						+ " ro_person.enname,ro_personaccount.password, "
						+ "ro_personaccount.accountstat, ro_personaccount.accountttl,"
						+ "ro_org.cnname a,ro_person.email1,ro_person.zip,ro_person.city,"
						+ "ro_person.country,ro_person.nation,ro_person.nativeplace,"
						+ "ro_person.officetel,ro_person.mobile,ro_person.photo,ro_person.type,"
						+ "ro_person.officefax,ro_person.hometel,ro_person.homefax,"
						+ "ro_person.homeadd,ro_person.officeadd,ro_person.connectaddr,"
						+ "ro_person.school,ro_person.marry, "
						+ "ro_person.politicalstat,ro_person.lastedulevel,ro_person.lastdegree,"
						+ "ro_person.sex,"
						+ "ro_personaccount.passquestion,ro_personaccount.passanswer,"
						+ "ro_person.birthday,ro_person.duty,ro_person.job,ro_person.househao "
						+ "from ro_person,ro_orgperson,ro_org,ro_personaccount "
						+ "where ro_person.personid = ro_orgperson.personid and ro_org.orgid = ro_orgperson.orgid and"
						+ " ro_personaccount.personid = ro_person.personid and ro_person.cnname like '%"
						+ name + "%'";
				personList = personManager.getBySQL(sql);
			}
			String str = "";
			int count = Integer.parseInt(start) + Integer.parseInt(limit);
			if (count > personList.size()) {
				personList = personList.subList(Integer.parseInt(start),
						personList.size());
			} else {
				personList = personList.subList(Integer.parseInt(start),
						Integer.parseInt(start) + Integer.parseInt(limit));
			}
			json = "{totalCount:" + personManager.getBySQL(sql).size()
					+ ",data:[";
			for (Iterator iter = personList.iterator(); iter.hasNext();) {

				Object[] obj = (Object[]) iter.next();
				str += "{uuid:'" + obj[0] + "',personid:'" + obj[1]
						+ "',orgid:'" + obj[2] + "',cnname:'" + obj[3] + "',"
						+ "enname:'" + obj[4] + "',password:'" + obj[5]
						+ "',accountstat:'" + obj[6] + "',accountttl:'"
						+ obj[7] + "'," + "email1:'" + obj[9] + "',zip:'"
						+ obj[10] + "'" + ",city:'" + obj[11] + "',country:'"
						+ obj[12] + "',nation:'" + obj[13] + "',nativeplace:'"
						+ obj[14] + "'," + "officetel:'" + obj[15]
						+ "',mobile:'" + obj[16] + "'," + "photo:'" + obj[17]
						+ "',type:'" + obj[18] + "',officefax:'" + obj[19]
						+ "',hometel:'" + obj[20] + "'," + "homefax:'"
						+ obj[21] + "',homeadd:'" + obj[22] + "',officeadd:'"
						+ obj[23] + "',connectaddr:'" + obj[24] + "',"
						+ "school:'" + obj[25] + "',marry:'" + obj[26]
						+ "',politicalstat:'" + obj[27] + "',lastedulevel:'"
						+ obj[28] + "'," + "lastdegree:'" + obj[29] + "',sex:'"
						+ obj[30] + "',passquestion:'" + obj[31]
						+ "',passanswer:'" + obj[32] + "'," + "birthday:'"
						+ obj[33] + "',duty:'" + obj[34] + "',job:'" + obj[35]
						+ "',househao:'" + obj[36] + "'" + "},";
			}
			if (!"".equals(str)) {
				str = str.substring(0, str.length() - 1);
			}
			json += str.replaceAll("null", "");
			json += "]}";
			return SUCCESS;
		} catch (Exception ex) {
			System.out.println(ex.getLocalizedMessage());
			return ERROR;
		}
	}

	// ɾ��ָ����
	public String personPointDel() {

		String value = personidjsonData;
		String[] names = value.split("\\,");
		for (int i = 0; i < names.length; i++) {
			// System.out.println(names[i]);
			Orgperson orgperson = orgpersonManager.get(names[i]);
			CacheUtil.clearCache(orgperson.getOrgid());
			CacheUtil.clearCache(orgperson.getPersonid());
			// ɾ��rtx���� ��ɾ������֮ǰɾ�� zhongqun 2011-12-27 add
			// new RtxUser().delUser(names[i]);
			personManager.remove(names[i]);
			personaccountManager.remove(names[i]);
			orgpersonManager.remove(names[i]);

			continue;
		}
		return SUCCESS;

	}

	/**
	 * ��������Ա����
	 * 
	 * @return
	 */
	public String personSaveSort() {
		String arr[] = txtCheckValue.split(",");
		for (int i = 0; i < arr.length; i++) {
			personinfo = personManager.get(arr[i]);
			CacheUtil.clearCache(personinfo.getPersonid());

			personinfo.setIndextype(i + 10001);
			personManager.save(personinfo);
		}

		return SUCCESS;
	}

	// ��Ա��Ϣ�޸�
	public String personInfoModify() {

		try {
			Person per = personManager.get(personid);
			CacheUtil.clearCache(personid);
			Personaccount perac = personaccountManager.get(personid);// ��Աid
			per.setCnname(personinfo.getCnname()); // ������
			per.setEnname(personinfo.getEnname()); // Ӣ����
			per.setOfficetel(personinfo.getOfficetel()); // �칫�绰
			per.setMobile(personinfo.getMobile()); // �ֻ�
			per.setEmail1(personinfo.getEmail1()); // email

			per.setBirthday(birthday); // ��������
			per.setZip(personinfo.getZip()); // �ʱ�
			per.setCity(personinfo.getCity()); // ����
			per.setCountry(personinfo.getCountry()); // ����
			per.setNation(personinfo.getNation()); // ����
			per.setNativeplace(personinfo.getNativeplace()); // �漮
			per.setJob(personinfo.getJob()); // ����
			per.setDuty(personinfo.getDuty()); // ְ��

			per.setHometel(personinfo.getHometel()); // ��ͥ�绰
			per.setHomefax(personinfo.getHomefax()); // ��ͥ����
			per.setOfficefax(personinfo.getOfficefax()); // �칫����
			// per.setOtherinfo(personinfo.getOtherinfo()); //������Ϣ
			// per.setIndextype(personinfo.getIndextype()); //����
			per.setHomeadd(personinfo.getHomeadd()); // ��ͥסַ
			per.setOfficeadd(personinfo.getOfficeadd()); // �칫��ַ
			per.setConnectaddr(personinfo.getConnectaddr()); // ��ϵ��ַ
			per.setSchool(personinfo.getSchool()); // ��ҵѧУ
			per.setHousehao(personinfo.getHousehao()); // �����
			if (fileFileName == null || "".equals(fileFileName)) {
				// System.out.print("...........");
			} else {

				String targetDirectory = this.getRequest().getRealPath(
						"/desktop/widgets/jdsexplorer/resouces/images");
				java.io.File target = new java.io.File(targetDirectory,
						fileFileName);
				copy(file, target);
				per.setPhoto("/desktop/widgets/jdsexplorer/resouces/images/"
						+ fileFileName);
			}
			String lastlevel = personinfo.getLastedulevel();
			if (lastlevel == null) {
				lastlevel = "��";
			}
			if ("��".equals(lastlevel)) {
				per.setLastdegree("0"); // ���ѧλ

			} else if ("Сѧ".equals(lastlevel)) {
				per.setLastedulevel("1");

			} else if ("����".equals(lastlevel)) {
				per.setLastedulevel("2");
			} else if ("����(��ר����У)".equals(lastlevel)) {
				per.setLastedulevel("3");

			} else if ("��ר".equals(lastlevel)) {
				per.setLastedulevel("4");

			} else if ("����".equals(lastlevel)) {
				per.setLastedulevel("5");

			} else if ("�о���".equals(lastlevel)) {
				per.setLastedulevel("6");
			} else if ("��ʿ".equals(lastlevel)) {
				per.setLastedulevel("7");
			}
			String lastgree = personinfo.getLastdegree();
			if (lastgree == null) {
				lastgree = "��ѧλ";
			}
			if ("��ѧλ".equals(lastgree)) {
				per.setLastdegree("0");
			} else if ("ѧʿ".equals(lastgree)) {
				per.setLastdegree("1");

			} else if ("˶ʿ".equals(lastgree)) {
				per.setLastdegree("2");

			} else if ("��ʿ(��)".equals(lastgree)) {
				per.setLastdegree("3");

			}
			String sex = personinfo.getSex();
			if (sex == null) {
				sex = "����";
			}
			if ("����".equals(sex)) {
				per.setSex("0");

			} else if ("��".equals(sex)) {
				per.setSex("1");

			} else if ("Ů".equals(sex)) {
				per.setSex("2");

			}

			String accountstat = personaccount.getAccountstat();
			if (accountstat == null) {
				accountstat = "����";
			}
			if ("��ʱ�˺�".equals(accountstat)) {
				perac.setAccountstat("1");
			} else if ("��ͨ�˺�(����120�첻��½����ʧЧ������ֹ��)".equals(accountstat)) {
				perac.setAccountstat("2");
			} else if ("����".equals(accountstat)) {
				perac.setAccountstat("3");
			} else if ("����".equals(accountstat)) {
				perac.setAccountstat("4");
			}

			String marry = personinfo.getMarry();
			if (marry == null) {
				marry = "����";
			}
			if ("����".equals(marry)) {
				per.setMarry("0");
			} else if ("�ѻ�".equals(marry)) {
				per.setMarry("1");
			} else if ("δ��".equals(marry)) {
				per.setMarry("2");
			} else if ("����".equals(marry)) {
				per.setMarry("3");

			}

			String politicalstat = personinfo.getPoliticalstat();
			if (politicalstat == null) {
				politicalstat = "Ⱥ��";
			}
			if ("Ⱥ��".equals(politicalstat)) {
				per.setPoliticalstat("0");

			} else if ("��Ա".equals(politicalstat)) {
				per.setPoliticalstat("1");

			} else if ("��Ա".equals(politicalstat)) {
				per.setPoliticalstat("2");

			} else if ("����".equals(politicalstat)) {
				per.setPoliticalstat("3");

			}
			personManager.save(per);

			/** perac.setAccountstat(personaccount.getAccountstat()); //�˺�״̬* */
			perac.setAccountttl(personaccount.getAccountttl()); // �˺���Ч��
			perac.setUserid(personaccount.getUserid());// �˺�
			perac.setPassword(personaccount.getPassword()); // ����
			perac.setPassquestion(personaccount.getPassquestion()); // ��������
			perac.setPassanswer(personaccount.getPassanswer()); // �����
			perac.setRtxaccount(personaccount.getRtxaccount());
			personaccountManager.save(perac);
			SmallBean bean = new SmallBean();
			bean.setMsm("�޸���Ա��"+personinfo.getCnname());
			bean.setCreatedate(new Date());
			bean.setUuid((new UUID()).toString());
			UsmLog usm = new UsmLog();
			usm.save(bean);
			return SUCCESS;
		} catch (Exception ex) {
			ex.printStackTrace(System.err);
			return ERROR;
		}

	}

	// �����µ���Ա�б�
	public String orgPersonListGrid() {
		try {
			String sql = "";
			// zhognqun �޸�sql��䣬���һ�в�ѯro_personaccount.rtxaccount
			if ("".equals(name) || name == null) {

				sql = "select ro_person.personid, ro_person.cnname, ro_person.enname,ro_personaccount.password, ro_personaccount.accountstat,"
						+ "ro_personaccount.accountttl,ro_personaccount.passquestion,ro_personaccount.passanswer,ro_person.email1,ro_person.birthday, "
						+ "ro_person.zip, ro_person.city,ro_person.country,ro_person.nation,ro_person.nativeplace,ro_person.job,ro_person.duty,ro_person.officetel, "
						+ "ro_person.mobile, ro_person.photo,ro_person.type, ro_person.officefax,ro_person.hometel,ro_person.homefax,"
						+ "ro_person.otherinfo,ro_person.homeadd,ro_person.officeadd,ro_person.connectaddr,ro_person.school,ro_person.sex,ro_personaccount.userid,"
						+ "ro_person.marry, ro_person.politicalstat,ro_person.lastedulevel,ro_person.lastdegree, ro_person.househao, ro_org.orgid,ro_person.indextype "
						+ "from ro_orgperson, ro_org, ro_person,ro_personaccount where  ro_person.personid = ro_orgperson.personid  and "
						+ "ro_orgperson.orgid=ro_org.orgid and ro_personaccount.personid = ro_person.personid and ro_org.orgid='"
						+ orgid + "'";
				personList = personManager.getBySQL(sql);
			} else {
				sql = "select ro_person.personid, ro_person.cnname, ro_person.enname,ro_personaccount.password, ro_personaccount.accountstat,"
						+ "ro_personaccount.accountttl,ro_personaccount.passquestion,ro_personaccount.passanswer,ro_person.email1,ro_person.birthday, "
						+ "ro_person.zip,ro_person.city,ro_person.country,ro_person.nation,ro_person.nativeplace,ro_person.job,ro_person.duty,"
						+ "ro_person.officetel, ro_person.mobile, ro_person.photo,ro_person.type, ro_person.officefax,ro_person.hometel,ro_person.homefax, "
						+ "ro_person.otherinfo,ro_person.homeadd,ro_person.officeadd,ro_person.connectaddr,ro_person.school,ro_person.sex,ro_personaccount.userid,ro_person.marry,"
						+ " ro_person.politicalstat,ro_person.lastedulevel,ro_person.lastdegree, ro_person.househao, ro_org.orgid,ro_person.indextype "
						+ "from ro_orgperson, ro_org, ro_person,ro_personaccount where  ro_person.personid = ro_orgperson.personid  and "
						+ "ro_orgperson.orgid=ro_org.orgid and ro_personaccount.personid = ro_person.personid and ro_org.orgid='"
						+ orgid
						+ "'"
						+ "and ro_person.cnname like '%"
						+ name
						+ "%'";
				personList = personManager.getBySQL(sql);
			}

			int count = Integer.parseInt(start) + Integer.parseInt(limit);

			if (count > personList.size()) {
				personList = personList.subList(Integer.parseInt(start),
						personList.size());
			} else {
				personList = personList.subList(Integer.parseInt(start),
						Integer.parseInt(start) + Integer.parseInt(limit));
			}
			String str = "";
			String accountstat = "0";
			json = "{totalCount:" + personManager.getBySQL(sql).size()
					+ ",data:[";
			for (Iterator iter = personList.iterator(); iter.hasNext();) {
				Object[] obj = (Object[]) iter.next();
				str += "{personid:'" + obj[0] + "',cnname:'" + obj[1]
						+ "',enname:'" + obj[2] + "',password:'" + obj[3]
						+ "',accountstat:'" + obj[4] + "'," + "accountttl:'"
						+ obj[5] + "',passquestion:'" + obj[6]
						+ "',passanswer:'" + obj[7] + "',email1:'" + obj[8]
						+ "',birthday:'" + obj[9] + "'," + "zip:'" + obj[23]
						+ "',city:'" + obj[11] + "',country:'" + obj[12]
						+ "',nation:'" + obj[13] + "',nativeplace:'" + obj[14]
						+ "',job:'" + obj[15] + "',duty:'" + obj[16] + "',"
						+ "officetel:'" + obj[17] + "',mobile:'" + obj[18]
						+ "',photo:'" + obj[19] + "',type:'" + obj[20]
						+ "',officefax:'" + obj[21] + "',hometel:'" + obj[22]
						+ "'," + "homefax:'" + obj[23] + "',otherinfo:'"
						+ obj[24] + "',homeadd:'" + obj[25] + "',officeadd:'"
						+ obj[26] + "',connectaddr:'" + obj[27] + "',"
						+ "school:'" + obj[28] + "',sex:'" + obj[29]
						+ "',userid:'" + obj[30] + "',marry:'" + obj[31]
						+ "',politicalstat:'" + obj[32] + "',"
						+ "lastedulevel:'" + obj[33] + "',lastdegree:'"
						+ obj[34] + "',househao:'" + obj[35] + "',orgid:'"
						+ obj[36] + "',indextype:'" + obj[37] + "'},";
			}
			if (!"".equals(str)) {
				str = str.substring(0, str.length() - 1);
			}
			json += str.replace("null", "");
			json += "]}";
			return SUCCESS;
		} catch (Exception ex) {
			System.out.println(ex.getLocalizedMessage());
			return ERROR;
		}
	}

	// ��Ա��ְ
	public String changeDept() {
		String value = this.personidjsonData;
		String[] names = value.split("\\,");
		Org org = (Org) this.orgManager.get(this.newOrgid);
		if (org == null)
			return "error";
		for (int i = 0; i < names.length; i++) {
			Personaccount personAccount = (Personaccount) this.personaccountManager
					.get(names[i]);
			Person personinfo = (Person) this.personManager.get(personAccount
					.getPersonid());
			if ((personAccount == null) || (personinfo == null))
				continue;
			Orgperson orgperson = (Orgperson) this.orgpersonManager
					.get(names[i]);
			CacheUtil.clearCache(orgperson.getOrgid());
			CacheUtil.clearCache(orgperson.getPersonid());
			SmallBean bean = new SmallBean();
			bean.setMsm("������Ա��"+personinfo.getCnname()+"��"+org.getCnname());
			bean.setCreatedate(new Date());
			bean.setUuid((new UUID()).toString());
			UsmLog usm = new UsmLog();
			usm.save(bean);
			orgperson.setOrgid(this.newOrgid);
			this.orgpersonManager.save(orgperson);

			Integer localInteger = org.getRtxorgid();
		}
		
		return "success";
	}

	// ��ѯ��λ�»�û�з����������Ա
	public String getPositionNotPersonInfo() {
		try {
			String sql = "";
			if ("".equals(name) || name == null) {
				sql = "select p.uuid, p.personid, o.orgid, p.cnname, p.enname,pa.password,  o.cnname a, p.officetel,p.mobile  "
						+ " from ro_person p,ro_orgperson op ,ro_org o,ro_personaccount pa "
						+ " where p.personid = op.personid and o.orgid = op.orgid and pa.personid = p.personid"
						+ " and not exists(select pp.positionid from ro_positionperson pp, ro_position po where pp.positionid = po.positionid and pp.personid = p.personid)";
				personList = personManager.getBySQL(sql);
			} else {

				sql = "select p.uuid, p.personid, o.orgid, p.cnname, p.enname,pa.password,  o.cnname a, p.officetel,p.mobile"
						+ " from ro_person p,ro_orgperson op ,ro_org o,ro_personaccount pa  "
						+ " where p.personid = op.personid and o.orgid = op.orgid and pa.personid = p.personid and p.cnname like '%"
						+ name
						+ "%' "
						+ " and not exists(select pp.positionid from ro_positionperson pp, ro_position po where pp.positionid = po.positionid and pp.personid = p.personid)";
				personList = personManager.getBySQL(sql);

			}
			String str = "";
			int count = Integer.parseInt(start) + Integer.parseInt(limit);
			if (count > personList.size()) {
				personList = personList.subList(Integer.parseInt(start),
						personList.size());
			} else {
				personList = personList.subList(Integer.parseInt(start),
						Integer.parseInt(start) + Integer.parseInt(limit));
			}
			json = "{totalCount:" + personManager.getBySQL(sql).size()
					+ ",data:[";
			for (Iterator iter = personList.iterator(); iter.hasNext();) {
				Object[] obj = (Object[]) iter.next();
				str += "{uuid:'" + obj[0] + "',personid:'" + obj[1]
						+ "',orgid:'" + obj[2] + "',cnname:'" + obj[3] + "',"
						+ "enname:'" + obj[4] + "',password:'" + obj[5]
						+ "',orgname:'" + obj[6] + "',officetel:'" + obj[7]
						+ "',mobile:'" + obj[8] + "'},";
			}
			if (!"".equals(str)) {
				str = str.substring(0, str.length() - 1);
			}
			json += str;
			json += "]}";
			return SUCCESS;
		} catch (Exception ex) {
			System.out.println(ex.getLocalizedMessage());
			return ERROR;
		}
	}

	// ��ѯ ְ���»�û�з����������Ա
	public String getDutyLevelNotPersonInfo() {
		try {
			String sql = "";
			if ("".equals(name) || name == null) {
				sql = "select p.uuid, p.personid, o.orgid, p.cnname, p.enname,pa.password,  o.cnname a, p.officetel,p.mobile  "
						+ " from ro_person p,ro_orgperson op ,ro_org o,ro_personaccount pa "
						+ " where p.personid = op.personid and o.orgid = op.orgid and pa.personid = p.personid"
						+ " and not exists(select  pl.levelid from ro_personlevel pl, ro_level l where pl.levelid = l.levelid and pl.personid = p.personid)";
				personList = personManager.getBySQL(sql);
			} else {

				sql = "select p.uuid, p.personid, o.orgid, p.cnname, p.enname,pa.password,  o.cnname a, p.officetel,p.mobile"
						+ " from ro_person p,ro_orgperson op ,ro_org o,ro_personaccount pa  "
						+ " where p.personid = op.personid and o.orgid = op.orgid and pa.personid = p.personid and p.cnname like '%"
						+ name
						+ "%' "
						+ " and not exists(select  pl.levelid from ro_personlevel pl, ro_level l where pl.levelid = l.levelid and pl.personid = p.personid)";
				personList = personManager.getBySQL(sql);
			}
			String str = "";
			int count = Integer.parseInt(start) + Integer.parseInt(limit);
			if (count > personList.size()) {
				personList = personList.subList(Integer.parseInt(start),
						personList.size());
			} else {
				personList = personList.subList(Integer.parseInt(start),
						Integer.parseInt(start) + Integer.parseInt(limit));
			}
			json = "{totalCount:" + personManager.getBySQL(sql).size()
					+ ",data:[";
			for (Iterator iter = personList.iterator(); iter.hasNext();) {
				Object[] obj = (Object[]) iter.next();
				str += "{uuid:'" + obj[0] + "',personid:'" + obj[1]
						+ "',orgid:'" + obj[2] + "',cnname:'" + obj[3] + "',"
						+ "enname:'" + obj[4] + "',password:'" + obj[5]
						+ "',orgname:'" + obj[6] + "',officetel:'" + obj[7]
						+ "',mobile:'" + obj[8] + "'},";
			}
			if (!"".equals(str)) {
				str = str.substring(0, str.length() - 1);
			}
			json += str;
			json += "]}";
			return SUCCESS;
		} catch (Exception ex) {
			System.out.println(ex.getLocalizedMessage());
			return ERROR;
		}
	}

	// ��ѯ ְλ�»�û�з����������Ա
	public String getDutyNotPersonInfo() {
		try {
			String sql = "";
			if ("".equals(name) || name == null) {
				sql = "select p.uuid, p.personid, o.orgid, p.cnname, p.enname,pa.password,  o.cnname a, p.officetel,p.mobile  "
						+ " from ro_person p,ro_orgperson op ,ro_org o,ro_personaccount pa "
						+ " where p.personid = op.personid and o.orgid = op.orgid and pa.personid = p.personid"
						+ " and not exists(select  pd.dutyid from ro_personduty pd, ro_duty d where pd.dutyid = d.dutyid and pd.personid = p.personid)";
				personList = personManager.getBySQL(sql);
			} else {

				sql = "select p.uuid, p.personid, o.orgid, p.cnname, p.enname,pa.password,  o.cnname a, p.officetel,p.mobile"
						+ " from ro_person p,ro_orgperson op ,ro_org o,ro_personaccount pa  "
						+ " where p.personid = op.personid and o.orgid = op.orgid and pa.personid = p.personid and p.cnname like '%"
						+ name
						+ "%' "
						+ " and not exists(select  pd.dutyid from ro_personduty pd, ro_duty d where pd.dutyid = d.dutyid and pd.personid = p.personid)";
				personList = personManager.getBySQL(sql);
			}
			String str = "";
			int count = Integer.parseInt(start) + Integer.parseInt(limit);
			if (count > personList.size()) {
				personList = personList.subList(Integer.parseInt(start),
						personList.size());
			} else {
				personList = personList.subList(Integer.parseInt(start),
						Integer.parseInt(start) + Integer.parseInt(limit));
			}
			json = "{totalCount:" + personManager.getBySQL(sql).size()
					+ ",data:[";
			for (Iterator iter = personList.iterator(); iter.hasNext();) {
				Object[] obj = (Object[]) iter.next();
				str += "{uuid:'" + obj[0] + "',personid:'" + obj[1]
						+ "',orgid:'" + obj[2] + "',cnname:'" + obj[3] + "',"
						+ "enname:'" + obj[4] + "',password:'" + obj[5]
						+ "',orgname:'" + obj[6] + "',officetel:'" + obj[7]
						+ "',mobile:'" + obj[8] + "'},";
			}
			if (!"".equals(str)) {
				str = str.substring(0, str.length() - 1);
			}
			json += str;
			json += "]}";
			return SUCCESS;
		} catch (Exception ex) {
			System.out.println(ex.getLocalizedMessage());
			return ERROR;
		}
	}

	// ��ѯ��ɫ�»�û�з����������Ա
	public String getRoleNotPersonInfo() {
		try {
			String sql = "";
			if ("".equals(name) || name == null) {
				sql = "select p.uuid, p.personid, o.orgid, p.cnname, p.enname,pa.password,  o.cnname a, p.officetel,p.mobile  "
						+ " from ro_person p,ro_orgperson op ,ro_org o,ro_personaccount pa "
						+ " where p.personid = op.personid and o.orgid = op.orgid and pa.personid = p.personid"
						+ " ";
				personList = personManager.getBySQL(sql);
			} else {

				sql = "select p.uuid, p.personid, o.orgid, p.cnname, p.enname,pa.password,  o.cnname a, p.officetel,p.mobile"
						+ " from ro_person p,ro_orgperson op ,ro_org o,ro_personaccount pa  "
						+ " where p.personid = op.personid and o.orgid = op.orgid and pa.personid = p.personid and p.cnname like '%"
						+ name + "%' " + " ";
				personList = personManager.getBySQL(sql);
			}
			String str = "";
			int count = Integer.parseInt(start) + Integer.parseInt(limit);
			if (count > personList.size()) {
				personList = personList.subList(Integer.parseInt(start),
						personList.size());
			} else {
				personList = personList.subList(Integer.parseInt(start),
						Integer.parseInt(start) + Integer.parseInt(limit));
			}
			json = "{totalCount:" + personManager.getBySQL(sql).size()
					+ ",data:[";
			for (Iterator iter = personList.iterator(); iter.hasNext();) {
				Object[] obj = (Object[]) iter.next();
				str += "{uuid:'" + obj[0] + "',personid:'" + obj[1]
						+ "',orgid:'" + obj[2] + "',cnname:'" + obj[3] + "',"
						+ "enname:'" + obj[4] + "',password:'" + obj[5]
						+ "',orgname:'" + obj[6] + "',officetel:'" + obj[7]
						+ "',mobile:'" + obj[8] + "'},";
			}
			if (!"".equals(str)) {
				str = str.substring(0, str.length() - 1);
			}
			json += str;
			json += "]}";
			return SUCCESS;
		} catch (Exception ex) {
			System.out.println(ex.getLocalizedMessage());
			return ERROR;
		}
	}

	// ��ѯ�û����»�û�з����������Ա
	public String getGrpuNotPersonInfo() {
		try {
			String sql = "";
			if ("".equals(name) || name == null) {
				sql = "select p.uuid, p.personid, o.orgid, p.cnname, p.enname,pa.password,  o.cnname a, p.officetel,p.mobile  "
						+ " from ro_person p,ro_orgperson op ,ro_org o,ro_personaccount pa "
						+ " where p.personid = op.personid and o.orgid = op.orgid and pa.personid = p.personid"
						+ " and not exists(select  gu.grpid from ro_grpuser gu, ro_usergroup u where gu.grpid = u.grpid and gu.personid = p.personid)";
				personList = personManager.getBySQL(sql);
			} else {

				sql = "select p.uuid, p.personid, o.orgid, p.cnname, p.enname,pa.password,  o.cnname a, p.officetel,p.mobile"
						+ " from ro_person p,ro_orgperson op ,ro_org o,ro_personaccount pa  "
						+ " where p.personid = op.personid and o.orgid = op.orgid and pa.personid = p.personid and p.cnname like '%"
						+ name
						+ "%' "
						+ " and not exists(select  gu.grpid from ro_grpuser gu, ro_usergroup u where gu.grpid = u.grpid and gu.personid = p.personid)";
				personList = personManager.getBySQL(sql);
			}
			String str = "";
			int count = Integer.parseInt(start) + Integer.parseInt(limit);
			if (count > personList.size()) {
				personList = personList.subList(Integer.parseInt(start),
						personList.size());
			} else {
				personList = personList.subList(Integer.parseInt(start),
						Integer.parseInt(start) + Integer.parseInt(limit));
			}
			json = "{totalCount:" + personManager.getBySQL(sql).size()
					+ ",data:[";
			for (Iterator iter = personList.iterator(); iter.hasNext();) {
				Object[] obj = (Object[]) iter.next();
				str += "{uuid:'" + obj[0] + "',personid:'" + obj[1]
						+ "',orgid:'" + obj[2] + "',cnname:'" + obj[3] + "',"
						+ "enname:'" + obj[4] + "',password:'" + obj[5]
						+ "',orgname:'" + obj[6] + "',officetel:'" + obj[7]
						+ "',mobile:'" + obj[8] + "'},";
			}
			if (!"".equals(str)) {
				str = str.substring(0, str.length() - 1);
			}
			json += str;
			json += "]}";
			return SUCCESS;
		} catch (Exception ex) {
			System.out.println(ex.getLocalizedMessage());
			return ERROR;
		}
	}

	public List<Personaccount> getPersonAccountList() {
		return personAccountList;
	}

	public void setPersonAccountList(List<Personaccount> personAccountList) {
		this.personAccountList = personAccountList;
	}

	public String getJson() {
		return json;
	}

	public void setJson(String json) {
		this.json = json;
	}

	public List<Person> getPersonList() {
		return personList;
	}

	public void setPersonList(List<Person> personList) {
		this.personList = personList;
	}

	public String getPersonid() {
		return personid;
	}

	public void setPersonid(String personid) {
		this.personid = personid;
	}

	public String getPersonidjsonData() {
		return personidjsonData;
	}

	public void setPersonidjsonData(String personidjsonData) {
		this.personidjsonData = personidjsonData;
	}

	public Org getDepart() {
		return depart;
	}

	public void setDepart(Org depart) {
		this.depart = depart;
	}

	public List<Org> getOrglist() {
		return orglist;
	}

	public void setOrglist(List<Org> orglist) {
		this.orglist = orglist;
	}

	public OrgManager getOrgManager() {
		return orgManager;
	}

	public void setOrgManager(OrgManager orgManager) {
		this.orgManager = orgManager;
	}

	public PersonextaccountManager getPersonextaccountManager() {
		return personextaccountManager;
	}

	public void setPersonextaccountManager(
			PersonextaccountManager personextaccountManager) {
		this.personextaccountManager = personextaccountManager;
	}

	public PersonManager getPersonManager() {
		return personManager;
	}

	public void setPersonManager(PersonManager personManager) {
		this.personManager = personManager;
	}

	public Personaccount getPersonaccount() {
		return personaccount;
	}

	public void setPersonaccount(Personaccount personaccount) {
		this.personaccount = personaccount;
	}

	public Person getPersoninfo() {
		return personinfo;
	}

	public void setPersoninfo(Person personinfo) {
		this.personinfo = personinfo;
	}

	public PersonaccountManager getPersonaccountManager() {
		return personaccountManager;
	}

	public void setPersonaccountManager(
			PersonaccountManager personaccountManager) {
		this.personaccountManager = personaccountManager;
	}

	public String getPersonsexId(String sex) {
		if (sex == null) {
			return "0";
		}
		String sexId = "0";
		if (sex == null) {
			sexId = "0";
		}
		if (sex.equals("����")) {
			sexId = "0";
		}
		if (sex.equals("��")) {
			sexId = "1";
		}
		if (sex.equals("Ů")) {
			sexId = "2";
		}
		return sexId;
	}

	public String getPersonAccountstatId(String accountstat) {
		if (accountstat == null) {
			return "0";
		}
		String accountstatId = "0";
		if (accountstat == null) {
			accountstatId = "1";
		}
		if (accountstat.equals("��ʱ�˺�")) {
			accountstatId = "1";
		}
		if (accountstat.equals("��ͨ�˺�(����120�첻��½����ʧЧ������ֹ��)")) {
			accountstatId = "2";
		}
		if (accountstat.equals("����")) {
			accountstatId = "3";
		}
		if (accountstat.equals("����")) {
			accountstatId = "4";
		}
		return accountstatId;

	}

	public String getPersonMarryId(String marry) {
		if (marry == null) {
			return "0";
		}
		String marryId = "0";
		if (marry == null) {
			marryId = "0";
		}
		if (marry.equals("����")) {
			marryId = "0";
		}
		if (marry.equals("�ѻ�")) {
			marryId = "1";
		}
		if (marry.equals("δ��")) {
			marryId = "2";
		}
		if (marry.equals("����")) {
			marryId = "3";
		}
		return marryId;

	}

	public String getPersonPoliticalstatId(String politicalstat) {
		if (politicalstat == null) {
			return "0";
		}
		String politicalstatId = "0";
		if (politicalstat == null) {
			politicalstatId = "3";
		}
		if (politicalstat.equals("Ⱥ��")) {
			politicalstatId = "0";
		}
		if (politicalstat.equals("��Ա")) {
			politicalstatId = "1";
		}
		if (politicalstat.equals("��Ա")) {
			politicalstatId = "2";
		}
		if (politicalstat.equals("����")) {
			politicalstatId = "3";
		}
		return politicalstatId;

	}

	public String getPersonLastedulevelId(String lastedulevel) {
		if (lastedulevel == null) {
			return "0";
		}
		String lastedulevelId = "0";
		if (lastedulevel == null) {
			lastedulevelId = "0";
		}
		if (lastedulevel.equals("��")) {
			lastedulevelId = "0";
		}
		if (lastedulevel.equals("Сѧ")) {
			lastedulevelId = "1";
		}
		if (lastedulevel.equals("����")) {
			lastedulevelId = "2";
		}
		if (lastedulevel.equals("����(��ר����У)")) {
			lastedulevelId = "3";
		}
		if (lastedulevel.equals("��ר")) {
			lastedulevelId = "4";
		}
		if (lastedulevel.equals("����")) {
			lastedulevelId = "5";
		}
		if (lastedulevel.equals("�о���")) {
			lastedulevelId = "6";
		}
		if (lastedulevel.equals("��ʿ")) {
			lastedulevelId = "7";
		}
		return lastedulevelId;

	}

	public String getPersonLastdegreeId(String lastdegree) {
		if (lastdegree == null) {
			return "0";
		}
		String lastdegreeId = "0";
		if (lastdegree == null) {
			lastdegreeId = "0";
		}
		if (lastdegree.equals("��ѧλ")) {
			lastdegreeId = "0";
		}
		if (lastdegree.equals("ѧʿ")) {
			lastdegreeId = "1";
		}
		if (lastdegree.equals("˶ʿ")) {
			lastdegreeId = "2";
		}
		if (lastdegree.equals("��ʿ(��)")) {
			lastdegreeId = "3";
		}
		return lastdegreeId;

	}

	public String getOrgid() {
		return orgid;
	}

	public void setOrgid(String orgid) {
		this.orgid = orgid;
	}

	public Orgperson getOrgperson() {
		return orgperson;
	}

	public void setOrgperson(Orgperson orgperson) {
		this.orgperson = orgperson;
	}

	public OrgpersonManager getOrgpersonManager() {
		return orgpersonManager;
	}

	public void setOrgpersonManager(OrgpersonManager orgpersonManager) {
		this.orgpersonManager = orgpersonManager;
	}

	public String getParentorgid() {
		return parentorgid;
	}

	public void setParentorgid(String parentorgid) {
		this.parentorgid = parentorgid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getFileContentType() {
		return fileContentType;
	}

	public void setFileContentType(String fileContentType) {
		this.fileContentType = fileContentType;
	}

	public String getFileFileName() {
		return fileFileName;
	}

	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}

	private void copy(File src, File dst) {
		try {
			InputStream in = null;
			OutputStream out = null;
			try {
				in = new BufferedInputStream(new FileInputStream(src));
				out = new BufferedOutputStream(new FileOutputStream(dst));
				byte[] buffer = new byte[16 * 1024];
				while (in.read(buffer) > 0) {
					out.write(buffer);
				}
			} finally {
				if (null != in) {
					in.close();
				}
				if (null != out) {
					out.close();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getNew_pass() {
		return new_pass;
	}

	public void setNew_pass(String new_pass) {
		this.new_pass = new_pass;
	}

	public String getNewOrgid() {
		return newOrgid;
	}

	public void setNewOrgid(String newOrgid) {
		this.newOrgid = newOrgid;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

}
