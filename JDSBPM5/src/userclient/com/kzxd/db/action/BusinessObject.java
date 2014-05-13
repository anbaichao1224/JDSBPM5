package com.kzxd.db.action;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.xml.namespace.QName;
import javax.xml.rpc.ServiceException;

import net.itjds.j2ee.util.UUID;

import org.apache.axis.client.Call;

import org.apache.axis.client.Service;


public class BusinessObject {

	@SuppressWarnings("unchecked")
	public void doIt() {

		InetAddress address;
		String ip = "";
		try {
			address = InetAddress.getLocalHost();
			ip = address.getHostAddress().toString();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String message = "您有台服务器连接数据库出现异常，IP为：" + ip;
		String url = "http://" + ip + "/services/OADataBaseService";
		Service service = new Service();
		Call call;
		Integer result;
		String sql = "select count(*) from tbl_amqp_temp";
		ress = this.StartQuery(sql);
		int GwjhAmqp = 0;
		try {
			while (ress.next()) {
				GwjhAmqp = ress.getInt(1);
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		this.CloseAll();
		System.out.println("中心服务器调度表条数：" + GwjhAmqp);
		if (GwjhAmqp > 50) {
			String phone = this.getPhone();
			String[] phones = null;
			List<String> addrs = new ArrayList<String>(0);
			if (phone != null && phone.length() > 0) {
				phones = phone.split(",");
				for (int i = 0; i < phones.length; i++) {

					addrs.add(phones[i]);

				}

			}
		

		}

		try {
			call = (Call) service.createCall();
			call.setTargetEndpointAddress(url);
			System.out.println(url);
			call.setOperationName(new QName(url, "Chaxun"));
			try {
				result = (Integer) call.invoke(new Object[] {});

				if (result == -1) {
					String phone = this.getPhone();
					String[] phones = null;
					List<String> addrs = new ArrayList<String>(0);
					if (phone != null && phone.length() > 0) {
						phones = phone.split(",");
						for (int i = 0; i < phones.length; i++) {

							addrs.add(phones[i]);

						}

					}
				
				}
			} catch (RemoteException e) {
				System.out.println("与数据库断开连接！");
				// TODO Auto-generated catch block
				e.printStackTrace();
				String phone = this.getPhone();
				String[] phones = null;
				List<String> addrs = new ArrayList<String>(0);
				if (phone != null && phone.length() > 0) {
					phones = phone.split(",");
					for (int i = 0; i < phones.length; i++) {

						addrs.add(phones[i]);

					}

				}
			
			}
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public int getDeptId() {
		InputStream inputStream = getClass().getClassLoader()
				.getResourceAsStream("glyts.properties");
		Properties p = new Properties();
		try {
			p.load(inputStream);
			String deptid = p.getProperty("deptId");
			if (deptid != null)
				return Integer.parseInt(deptid);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return -1;
	}

	public String getSmsId() {
		InputStream inputStream = getClass().getClassLoader()
				.getResourceAsStream("glyts.properties");
		Properties p = new Properties();
		try {
			p.load(inputStream);
			String smsid = p.getProperty("smsid");
			return smsid;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public String getPassword() {
		InputStream inputStream = getClass().getClassLoader()
				.getResourceAsStream("glyts.properties");
		Properties p = new Properties();
		try {
			p.load(inputStream);
			String password = p.getProperty("password");
			return password;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public String getPhone() {
		InputStream inputStream = getClass().getClassLoader()
				.getResourceAsStream("glyts.properties");
		Properties p = new Properties();
		try {
			p.load(inputStream);
			String phone = p.getProperty("phone");
			return phone;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/*
	 * 监控单位---查找监控单位
	 */

	public ResultSet StartQuery(String sql) {
		try {
			conn = DriverManager.getConnection(url, "root", "root");
			stmt = conn.createStatement();
			ress = stmt.executeQuery(sql);

		} catch (SQLException ex) {
			System.err.println(ex.getMessage());
		}
		return ress;
	}

	// 关闭数据库连接
	public void CloseAll() {
		try {
			ress.close();
			stmt.close();
			conn.close();
		} catch (SQLException ex) {
			System.err.println(ex.getMessage());
		}
	}

	private String url = "jdbc:mysql://19.177.51.88/docexchange?useUnicode=true&amp;characterEncoding=UTF-8";
	private Connection conn = null;
	private Statement stmt = null;
	private ResultSet ress = null;
}