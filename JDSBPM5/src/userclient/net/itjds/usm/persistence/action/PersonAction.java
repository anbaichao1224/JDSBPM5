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

	PersonextaccountManager personextaccountManager; // 人员账号管理
	PersonManager personManager; // 人员信息管理
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

	// 人员及部门中间表
	OrgpersonManager orgpersonManager;
	Orgperson orgperson = new Orgperson();

	// 部门
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

	// 指定人员详细信息表单
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
				.findValue("$currPerson"); // 加入 修改密码后可即时登录
		p.setPassword(personaccount.getPassword());

		// new
		// RtxUser().addUser(personaccount.getUserid(),personaccount.getUserid(),personaccount.getPassword(),personid,"",personinfo.getCnname(),personinfo.getSex());

		return SUCCESS;
	}

	// 人员信息增加
	public String personSave() {

		try {
			Date date = new Date();
			InetAddress address = InetAddress.getLocalHost(); // 获得本机ip
			// 用户账号信息
			String uuid = new UUID().toString();
			personaccount.setPersonid(uuid);
			personaccount.setLastloginip(address.getHostAddress());// 最后登陆ip
			personaccount.setLastlogindate(new Timestamp(System
					.currentTimeMillis()));// 最后登陆时间
			personaccount.setAccountstat(getPersonAccountstatId(personaccount
					.getAccountstat())); // 存入账号状态ID
			personaccount.setCreatetime(new Timestamp(System
					.currentTimeMillis()));
			personaccount.setPassword(new_pass);
			personaccountManager.save(personaccount);

			// 人员基本信息
			personinfo.setPersonid(String.valueOf(uuid));
			personinfo.setSex(getPersonsexId(personinfo.getSex()));// 存入姓别ID
			personinfo.setMarry(getPersonMarryId(personinfo.getMarry())); // 存入婚否ID
			personinfo.setPoliticalstat(getPersonPoliticalstatId(personinfo
					.getPoliticalstat()));// 存入政治面貌
			personinfo.setLastedulevel(getPersonLastedulevelId(personinfo
					.getLastedulevel()));// 存入最高学历
			personinfo.setLastdegree(getPersonLastdegreeId(personinfo
					.getLastdegree()));// 存入最高学位
			personinfo.setHousehao(personinfo.getHousehao()); // 房间号

			personManager.save(personinfo);

			// 人员部门表
			CacheUtil.clearCache(orgperson.getOrgid());
			CacheUtil.clearCache(orgperson.getPersonid());
			orgperson.setPersonid(String.valueOf(uuid));
			orgpersonManager.save(orgperson);
			Org org = orgManager.get(orgperson.getOrgid());
			SmallBean bean = new SmallBean();
			bean.setMsm("增加人员："+personinfo.getCnname());
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

	// 查询用户账号表Personaccount
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

	// 查询人员及账号表,并在grid显示各别人员信息字段
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

	// 删除指定行
	public String personPointDel() {

		String value = personidjsonData;
		String[] names = value.split("\\,");
		for (int i = 0; i < names.length; i++) {
			// System.out.println(names[i]);
			Orgperson orgperson = orgpersonManager.get(names[i]);
			CacheUtil.clearCache(orgperson.getOrgid());
			CacheUtil.clearCache(orgperson.getPersonid());
			// 删除rtx数据 在删除数据之前删除 zhongqun 2011-12-27 add
			// new RtxUser().delUser(names[i]);
			personManager.remove(names[i]);
			personaccountManager.remove(names[i]);
			orgpersonManager.remove(names[i]);

			continue;
		}
		return SUCCESS;

	}

	/**
	 * 部门下人员排序
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

	// 人员信息修改
	public String personInfoModify() {

		try {
			Person per = personManager.get(personid);
			CacheUtil.clearCache(personid);
			Personaccount perac = personaccountManager.get(personid);// 人员id
			per.setCnname(personinfo.getCnname()); // 中文名
			per.setEnname(personinfo.getEnname()); // 英文名
			per.setOfficetel(personinfo.getOfficetel()); // 办公电话
			per.setMobile(personinfo.getMobile()); // 手机
			per.setEmail1(personinfo.getEmail1()); // email

			per.setBirthday(birthday); // 出生日期
			per.setZip(personinfo.getZip()); // 邮编
			per.setCity(personinfo.getCity()); // 城市
			per.setCountry(personinfo.getCountry()); // 国籍
			per.setNation(personinfo.getNation()); // 民族
			per.setNativeplace(personinfo.getNativeplace()); // 祖籍
			per.setJob(personinfo.getJob()); // 工作
			per.setDuty(personinfo.getDuty()); // 职务

			per.setHometel(personinfo.getHometel()); // 家庭电话
			per.setHomefax(personinfo.getHomefax()); // 家庭传真
			per.setOfficefax(personinfo.getOfficefax()); // 办公传真
			// per.setOtherinfo(personinfo.getOtherinfo()); //其它信息
			// per.setIndextype(personinfo.getIndextype()); //排序
			per.setHomeadd(personinfo.getHomeadd()); // 家庭住址
			per.setOfficeadd(personinfo.getOfficeadd()); // 办公地址
			per.setConnectaddr(personinfo.getConnectaddr()); // 联系地址
			per.setSchool(personinfo.getSchool()); // 毕业学校
			per.setHousehao(personinfo.getHousehao()); // 房间号
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
				lastlevel = "无";
			}
			if ("无".equals(lastlevel)) {
				per.setLastdegree("0"); // 最高学位

			} else if ("小学".equals(lastlevel)) {
				per.setLastedulevel("1");

			} else if ("初中".equals(lastlevel)) {
				per.setLastedulevel("2");
			} else if ("高中(中专、技校)".equals(lastlevel)) {
				per.setLastedulevel("3");

			} else if ("大专".equals(lastlevel)) {
				per.setLastedulevel("4");

			} else if ("本科".equals(lastlevel)) {
				per.setLastedulevel("5");

			} else if ("研究生".equals(lastlevel)) {
				per.setLastedulevel("6");
			} else if ("博士".equals(lastlevel)) {
				per.setLastedulevel("7");
			}
			String lastgree = personinfo.getLastdegree();
			if (lastgree == null) {
				lastgree = "无学位";
			}
			if ("无学位".equals(lastgree)) {
				per.setLastdegree("0");
			} else if ("学士".equals(lastgree)) {
				per.setLastdegree("1");

			} else if ("硕士".equals(lastgree)) {
				per.setLastdegree("2");

			} else if ("博士(后)".equals(lastgree)) {
				per.setLastdegree("3");

			}
			String sex = personinfo.getSex();
			if (sex == null) {
				sex = "保密";
			}
			if ("保密".equals(sex)) {
				per.setSex("0");

			} else if ("男".equals(sex)) {
				per.setSex("1");

			} else if ("女".equals(sex)) {
				per.setSex("2");

			}

			String accountstat = personaccount.getAccountstat();
			if (accountstat == null) {
				accountstat = "永久";
			}
			if ("临时账号".equals(accountstat)) {
				perac.setAccountstat("1");
			} else if ("普通账号(连续120天不登陆，即失效（被禁止）)".equals(accountstat)) {
				perac.setAccountstat("2");
			} else if ("永久".equals(accountstat)) {
				perac.setAccountstat("3");
			} else if ("禁用".equals(accountstat)) {
				perac.setAccountstat("4");
			}

			String marry = personinfo.getMarry();
			if (marry == null) {
				marry = "保密";
			}
			if ("保密".equals(marry)) {
				per.setMarry("0");
			} else if ("已婚".equals(marry)) {
				per.setMarry("1");
			} else if ("未婚".equals(marry)) {
				per.setMarry("2");
			} else if ("离异".equals(marry)) {
				per.setMarry("3");

			}

			String politicalstat = personinfo.getPoliticalstat();
			if (politicalstat == null) {
				politicalstat = "群众";
			}
			if ("群众".equals(politicalstat)) {
				per.setPoliticalstat("0");

			} else if ("团员".equals(politicalstat)) {
				per.setPoliticalstat("1");

			} else if ("党员".equals(politicalstat)) {
				per.setPoliticalstat("2");

			} else if ("其它".equals(politicalstat)) {
				per.setPoliticalstat("3");

			}
			personManager.save(per);

			/** perac.setAccountstat(personaccount.getAccountstat()); //账号状态* */
			perac.setAccountttl(personaccount.getAccountttl()); // 账号有效期
			perac.setUserid(personaccount.getUserid());// 账号
			perac.setPassword(personaccount.getPassword()); // 密码
			perac.setPassquestion(personaccount.getPassquestion()); // 密码问题
			perac.setPassanswer(personaccount.getPassanswer()); // 问题答案
			perac.setRtxaccount(personaccount.getRtxaccount());
			personaccountManager.save(perac);
			SmallBean bean = new SmallBean();
			bean.setMsm("修改人员："+personinfo.getCnname());
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

	// 部门下的人员列表
	public String orgPersonListGrid() {
		try {
			String sql = "";
			// zhognqun 修改sql语句，添加一列查询ro_personaccount.rtxaccount
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

	// 人员调职
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
			bean.setMsm("调动人员："+personinfo.getCnname()+"到"+org.getCnname());
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

	// 查询岗位下还没有分配的所有人员
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

	// 查询 职级下还没有分配的所有人员
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

	// 查询 职位下还没有分配的所有人员
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

	// 查询角色下还没有分配的所有人员
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

	// 查询用户组下还没有分配的所有人员
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
		if (sex.equals("保密")) {
			sexId = "0";
		}
		if (sex.equals("男")) {
			sexId = "1";
		}
		if (sex.equals("女")) {
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
		if (accountstat.equals("临时账号")) {
			accountstatId = "1";
		}
		if (accountstat.equals("普通账号(连续120天不登陆，即失效（被禁止）)")) {
			accountstatId = "2";
		}
		if (accountstat.equals("永久")) {
			accountstatId = "3";
		}
		if (accountstat.equals("禁用")) {
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
		if (marry.equals("保密")) {
			marryId = "0";
		}
		if (marry.equals("已婚")) {
			marryId = "1";
		}
		if (marry.equals("未婚")) {
			marryId = "2";
		}
		if (marry.equals("离异")) {
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
		if (politicalstat.equals("群众")) {
			politicalstatId = "0";
		}
		if (politicalstat.equals("团员")) {
			politicalstatId = "1";
		}
		if (politicalstat.equals("党员")) {
			politicalstatId = "2";
		}
		if (politicalstat.equals("其它")) {
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
		if (lastedulevel.equals("无")) {
			lastedulevelId = "0";
		}
		if (lastedulevel.equals("小学")) {
			lastedulevelId = "1";
		}
		if (lastedulevel.equals("初中")) {
			lastedulevelId = "2";
		}
		if (lastedulevel.equals("高中(中专、技校)")) {
			lastedulevelId = "3";
		}
		if (lastedulevel.equals("大专")) {
			lastedulevelId = "4";
		}
		if (lastedulevel.equals("本科")) {
			lastedulevelId = "5";
		}
		if (lastedulevel.equals("研究生")) {
			lastedulevelId = "6";
		}
		if (lastedulevel.equals("博士")) {
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
		if (lastdegree.equals("无学位")) {
			lastdegreeId = "0";
		}
		if (lastdegree.equals("学士")) {
			lastdegreeId = "1";
		}
		if (lastdegree.equals("硕士")) {
			lastdegreeId = "2";
		}
		if (lastdegree.equals("博士(后)")) {
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
