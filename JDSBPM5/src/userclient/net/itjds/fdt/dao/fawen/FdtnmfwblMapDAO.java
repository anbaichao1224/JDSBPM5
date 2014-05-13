package net.itjds.fdt.dao.fawen;

import java.sql.Timestamp;
import java.sql.Connection;

import net.itjds.j2ee.dao.DAO;
import net.itjds.j2ee.dao.annotation.DBField;
import net.itjds.j2ee.dao.annotation.DBTable;
import net.itjds.bpm.data.xmlproxy.MapdaoBeanAnnotation;
import net.itjds.common.mapdao.MapDAO;
import net.itjds.j2ee.dao.MethodChinaName;


@MapdaoBeanAnnotation(id="Fdtnmfwbl",
		name="内蒙发文办理",
		expressionArr="GetSampleMapdao(request,\"Fdtnmfwbl\")",
		desc="内蒙发文办理 by DAOTools ",
		flowType="fawen",
		dataType="action"
		)
public class FdtnmfwblMapDAO extends MapDAO {

	private FdtOaNmfwblDAO fdtOaNmfwblDAO;

	@MethodChinaName(cname="内蒙发文办理")
	public FdtOaNmfwblDAO getFdtOaNmfwblDAO() {
		return fdtOaNmfwblDAO; 
	}

	public void setFdtOaNmfwblDAO (FdtOaNmfwblDAO fdtOaNmfwblDAO) {
		this.fdtOaNmfwblDAO = fdtOaNmfwblDAO;
	}
}