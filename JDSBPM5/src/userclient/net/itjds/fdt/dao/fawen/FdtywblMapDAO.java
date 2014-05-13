package net.itjds.fdt.dao.fawen;

import java.sql.Timestamp;
import java.sql.Connection;

import net.itjds.j2ee.dao.DAO;
import net.itjds.j2ee.dao.annotation.DBField;
import net.itjds.j2ee.dao.annotation.DBTable;
import net.itjds.bpm.data.xmlproxy.MapdaoBeanAnnotation;
import net.itjds.common.mapdao.MapDAO;
import net.itjds.j2ee.dao.MethodChinaName;


@MapdaoBeanAnnotation(id="Fdtywbl",
		name="阅文办理",
		expressionArr="GetSampleMapdao(request,\"Fdtywbl\")",
		desc="阅文办理 by DAOTools ",
		flowType="fawen",
		dataType="action"
		)
public class FdtywblMapDAO extends MapDAO {

	private FdtOaYwblDAO fdtOaYwblDAO;

	@MethodChinaName(cname="阅文办理")
	public FdtOaYwblDAO getFdtOaYwblDAO() {
		return fdtOaYwblDAO; 
	}

	public void setFdtOaYwblDAO (FdtOaYwblDAO fdtOaYwblDAO) {
		this.fdtOaYwblDAO = fdtOaYwblDAO;
	}
}