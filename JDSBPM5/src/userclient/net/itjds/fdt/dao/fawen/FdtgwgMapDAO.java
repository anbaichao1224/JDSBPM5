package net.itjds.fdt.dao.fawen;

import java.sql.Timestamp;
import java.sql.Connection;

import net.itjds.j2ee.dao.DAO;
import net.itjds.j2ee.dao.annotation.DBField;
import net.itjds.j2ee.dao.annotation.DBTable;
import net.itjds.bpm.data.xmlproxy.MapdaoBeanAnnotation;
import net.itjds.common.mapdao.MapDAO;
import net.itjds.j2ee.dao.MethodChinaName;


@MapdaoBeanAnnotation(id="Fdtgwg",
		name="公文稿",
		expressionArr="GetSampleMapdao(request,\"Fdtgwg\")",
		desc="公文稿 by DAOTools ",
		flowType="fawen",
		dataType="action"
		)
public class FdtgwgMapDAO extends MapDAO {

	private FdtOaGwgDAO fdtOaGwgDAO;

	@MethodChinaName(cname="FDT_OA_GWG")
	public FdtOaGwgDAO getFdtOaGwgDAO() {
		return fdtOaGwgDAO; 
	}

	public void setFdtOaGwgDAO (FdtOaGwgDAO fdtOaGwgDAO) {
		this.fdtOaGwgDAO = fdtOaGwgDAO;
	}
}