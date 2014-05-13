package net.itjds.fdt.metting;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import net.itjds.common.database.DBBeanBase;
import net.itjds.j2ee.dao.DAO;
import net.itjds.j2ee.dao.DAOException;
import net.itjds.j2ee.dao.PageScrollParas;
import net.itjds.j2ee.util.SystemUtil;


public class BpmMettingInstDAO extends BpmMettingTreeDAO {

	

	@Override
	protected void setupFields() throws DAOException {
		addField("uuid", "UUID");
		addField("createdate", "CREATEDATE");
		addField("creatorid", "CREATORID");
		addField("creator", "CREATOR");
		addField("xxmc", "XXMC");
		addField("mtypeid","MTYPEID");
		addField("parentid","PARENTID");
		addField("kssj","KSSJ");
		addField("jssj","JSSJ");
		addField("sxlx","SXLX");
		addField("blrmc","BLRMC");
		addField("blrid","BLRID");
		addField("blstatus","BLSTATUS");
		addField("sxnr","SXNR");
		addField("isdelete","ISDELETE");
		addField("processdefid","PROCESSDEF_ID");
		addField("processinstid","PROCESSINST_ID");
		addField("activityinstid","ACTIVITYINST_ID");
		addField("mettingid","METTING_ID");
		addField("mettingname","METTING_NAME");
		addField("isopen","ISOPEN");
		setTableName("FDT_OA_METTINGINST");
		addKey("UUID");
	}
	
}
