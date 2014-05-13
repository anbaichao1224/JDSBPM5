package net.itjds.fdt.dao.fawen;

import java.sql.Timestamp;
import java.sql.Connection;

import net.itjds.j2ee.dao.DAO;
import net.itjds.j2ee.dao.annotation.DBField;
import net.itjds.j2ee.dao.annotation.DBTable;
import net.itjds.bpm.data.xmlproxy.MapdaoBeanAnnotation;
import net.itjds.common.mapdao.MapDAO;
import net.itjds.j2ee.dao.MethodChinaName;


@MapdaoBeanAnnotation(id="Fdtspcs",
		name="��������",
		expressionArr="GetSampleMapdao(request,\"Fdtspcs\")",
		desc="�������� by DAOTools ",
		flowType="fawen",
		dataType="action"
		)
public class FdtspcsMapDAO extends MapDAO {

	private FdtOaSpcsDAO fdtOaSpcsDAO;

	@MethodChinaName(cname="FDT_OA_SPCS")
	public FdtOaSpcsDAO getFdtOaSpcsDAO() {
		return fdtOaSpcsDAO; 
	}

	public void setFdtOaSpcsDAO (FdtOaSpcsDAO fdtOaSpcsDAO) {
		this.fdtOaSpcsDAO = fdtOaSpcsDAO;
	}
}