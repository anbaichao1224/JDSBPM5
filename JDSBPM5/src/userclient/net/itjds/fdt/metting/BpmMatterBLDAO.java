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


public class BpmMatterBLDAO extends DAO {

	String uuid;

	String sxxxid;
	
	String personid;
	
	String personname;
	
	String hyid;
	
	String bldate;
	
	String mettingid;
	
	String parentid;

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String _uuid) {
		firePropertyChange("uuid", uuid, _uuid);
		uuid = _uuid;
	}

	

	public String getSxxxid() {
		return sxxxid;
	}

	public void setSxxxid(String _sxxxid) {
		firePropertyChange("sxxxid", sxxxid, _sxxxid);
		this.sxxxid = _sxxxid;
	}

	public String getPersonid() {
		return personid;
	}

	public void setPersonid(String _personid) {
		firePropertyChange("personid", personid, _personid);
		this.personid = _personid;
	}

	public String getPersonname() {
		return personname;
	}

	public void setPersonname(String _personname) {
		firePropertyChange("personname", personname, _personname);
		this.personname = _personname;
	}

	
	public String getBldate() {
		return bldate;
	}

	public void setBldate(String _bldate) {
		firePropertyChange("bldate", bldate, _bldate);
		this.bldate = _bldate;
	}


	public String getHyid() {
		return hyid;
	}

	public void setHyid(String _hyid) {
		firePropertyChange("hyid", hyid, _hyid);
		this.hyid = _hyid;
	}
	

	public String getMettingid() {
		return mettingid;
	}

	public void setMettingid(String _mettingid) {
		firePropertyChange("mettingid", mettingid, _mettingid);
		this.mettingid = _mettingid;
	}

	public String getParentid() {
		return parentid;
	}

	public void setParentid(String _parentid) {
		firePropertyChange("parentid", parentid, _parentid);
		this.parentid = _parentid;
	}

	protected void setupFields() throws DAOException {
		addField("uuid", "UUID");
		addField("sxxxid", "SXXXID");
		addField("personid", "PERSONID");
		addField("personname", "PERSONNAME");
		addField("bldate", "BLDATE");
		addField("hyid","HYID");
		addField("mettingid","METTING_ID");
		addField("parentid","PARENT_ID");
		setTableName("FDT_OA_MATTERBL");
		addKey("UUID");
	}

	/*public void addc(String property,String ralation,Object value) throws DAOException{
		
			super.addCondition(property, ralation, value);
	}
	String getfll(){
		StringBuffer clause = new StringBuffer();
        if(_relations != null)
            clause.append(getRelationClause());
        if(_conditions != null)
        {
            if(clause.length() > 0)
                clause.append(" and ");
            clause.append("(");
            clause.append(getConditionClauseNoParas()).append(") ");
        }
        String custWhereClause = getWhereClause();
        if(custWhereClause != null && custWhereClause.length() > 0)
        {
            if(clause.length() != 0)
                clause.append(" and ");
            clause.append('(').append(custWhereClause).append(") ");
        } else
        {
            StringBuffer nullAttributes = null;
            if(_nullAttributesForSearch != null)
                nullAttributes = new StringBuffer(_nullAttributesForSearch.replace(',', ' '));
            for(Iterator enu = _allFields.iterator(); enu.hasNext();)
            {
                String fieldName = (String)enu.next();
                String propName = (String)_dbFieldToPropertyMappings.get(fieldName);
                Method m = null;
                Object value = null;
                try
                {
                    m = getGetMethod(propName);
                    value = m.invoke(this, null);
                }
                catch(Exception exception) { }
                if(value != null)
                {
                    if(clause.length() > 0)
                        clause.append(" and ");
                    clause.append(fieldName).append("=? ");
                } else
                if(nullAttributes != null && nullAttributes.append(" ").toString().indexOf((new StringBuilder(String.valueOf(propName))).append(" ").toString()) >= 0 && clause.length() > 0)
                    clause.append(" and ").append(fieldName).append(" is null ");
            }

        }
        if(clause.length() > 0)
            return clause.toString();
        else
            return null;
	}
	
	public List findself() throws DAOException{
		
		PreparedStatement st;
        ResultSet result;
        StringBuffer sql;
        st = null;
        result = null;
        
        DBBeanBase dbbase;
		Connection conn;
		dbbase = new DBBeanBase("bpm", true);
		// uuid = (new UUID()).toString();
		conn = null;
		conn = dbbase.getConn();
        sql = new StringBuffer();
        List list;
        try
        {
            sql = new StringBuffer();
            sql.append("SELECT ").append("uuid,sxxxid,personid,personname,bldate");
            sql.append(" FROM ").append(getTableName());
            String whereClause = getfll();
            if(whereClause != null)
                sql.append(" WHERE ").append(whereClause);
            String orderBy = getOrderBy();
            if(_orderByFields != null)
                sql.append(" order by ").append(_orderByFields);
            else
            if(orderBy != null)
                sql.append(" ").append(orderBy);
            String sSql = sql.toString();
            st =conn.prepareStatement(sSql);
            //prepareFullWhereClause(st, 1);
            result = st.executeQuery();
            //traceSQL("SQL_SUCCESS", sql.toString(), null);
            //result.last();
            int c = result.getRow();
            List dbBeans = buildBeanList(result);
            list = dbBeans;
        }
        catch(SQLException e)
        {
            traceSQL("SQL_FAIL", sql.toString(), e);
            e.fillInStackTrace();
            throw new DAOException((new StringBuilder("find failed! msg: ")).append(e.getMessage()).toString(), e);
        }
        try
        {
            result.close();
            st.close();
        }
        catch(Exception exception1) { }
        return list;
        
	}
	
	public List buildBeanList(ResultSet result)
    throws DAOException, SQLException
{
    PageScrollParas paras = null;
    if(needPageScroll())
        paras = (PageScrollParas)SystemUtil.getCurThreadAttribute("_j2ee_pagescroll_paras");
    List dbBeans = new ArrayList();
    int curRow = 0;
    int count = 2147483647;


    while(result.next()) 
        curRow++;
    return dbBeans;
}
*/
    	public String getadd(){
		return super.getConditionClauseNoParas();
	}
	public BpmMatterBLDAO(Connection conn) {
		super(conn);
	}

	public BpmMatterBLDAO() {
		super();
	}

}
