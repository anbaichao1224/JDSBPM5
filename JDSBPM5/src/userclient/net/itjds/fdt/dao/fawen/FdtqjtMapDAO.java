package net.itjds.fdt.dao.fawen;

import java.sql.Timestamp;
import java.sql.Connection;

import net.itjds.j2ee.dao.DAO;
import net.itjds.j2ee.dao.annotation.DBField;
import net.itjds.j2ee.dao.annotation.DBTable;
import net.itjds.bpm.data.xmlproxy.MapdaoBeanAnnotation;
import net.itjds.common.mapdao.MapDAO;
import net.itjds.j2ee.dao.MethodChinaName;


@MapdaoBeanAnnotation(id="Fdtqjt",
		name="ว๋ผูฬ๕",
		expressionArr="GetSampleMapdao(request,\"Fdtqjt\")",
		desc="ว๋ผูฬ๕ by DAOTools ",
		flowType="fawen",
		dataType="action"
		)
public class FdtqjtMapDAO extends MapDAO {

	private FdtOaQjtDAO fdtOaQjtDAO;

	@MethodChinaName(cname="FDT_OA_QJT")
	public FdtOaQjtDAO getFdtOaQjtDAO() {
		return fdtOaQjtDAO; 
	}

	public void setFdtOaQjtDAO (FdtOaQjtDAO fdtOaQjtDAO) {
		this.fdtOaQjtDAO = fdtOaQjtDAO;
	}
}