package net.itjds.fdt.dao.fawen;

import java.sql.Timestamp;
import java.sql.Connection;

import net.itjds.j2ee.dao.DAO;
import net.itjds.j2ee.dao.annotation.DBField;
import net.itjds.j2ee.dao.annotation.DBTable;
import net.itjds.bpm.data.xmlproxy.MapdaoBeanAnnotation;
import net.itjds.common.mapdao.MapDAO;
import net.itjds.j2ee.dao.MethodChinaName;


@MapdaoBeanAnnotation(id="Fdtnmswbl",
		name="收文办理",
		expressionArr="GetSampleMapdao(request,\"Fdtnmswbl\")",
		desc="收文办理 by DAOTools ",
		flowType="fawen",
		dataType="action"
		)
public class FdtnmswblMapDAO extends MapDAO {

	private FdtOaNmswblDAO fdtOaNmswblDAO;

	@MethodChinaName(cname="收文办理")
	public FdtOaNmswblDAO getFdtOaNmswblDAO() {
		return fdtOaNmswblDAO; 
	}

	public void setFdtOaNmswblDAO (FdtOaNmswblDAO fdtOaNmswblDAO) {
		this.fdtOaNmswblDAO = fdtOaNmswblDAO;
	}
}