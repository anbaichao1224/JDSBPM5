package net.itjds.fdt.dao.fawen;

import java.sql.Timestamp;
import java.sql.Connection;

import net.itjds.j2ee.dao.DAO;
import net.itjds.j2ee.dao.annotation.DBField;
import net.itjds.j2ee.dao.annotation.DBTable;
import net.itjds.bpm.data.xmlproxy.MapdaoBeanAnnotation;
import net.itjds.common.mapdao.MapDAO;
import net.itjds.j2ee.dao.MethodChinaName;


@MapdaoBeanAnnotation(id="Fdtnmwsxtsp",
		name="网上协同审批",
		expressionArr="GetSampleMapdao(request,\"Fdtnmwsxtsp\")",
		desc="网上协同审批 by DAOTools ",
		flowType="fawen",
		dataType="action"
		)
public class FdtnmwsxtspMapDAO extends MapDAO {

	private FdtOaWsxtspDAO fdtOaWsxtspDAO;

	@MethodChinaName(cname="FDT_OA_WSXTSP")
	public FdtOaWsxtspDAO getFdtOaWsxtspDAO() {
		return fdtOaWsxtspDAO; 
	}

	public void setFdtOaWsxtspDAO (FdtOaWsxtspDAO fdtOaWsxtspDAO) {
		this.fdtOaWsxtspDAO = fdtOaWsxtspDAO;
	}
}