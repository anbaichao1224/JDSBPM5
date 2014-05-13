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
		name="���İ���",
		expressionArr="GetSampleMapdao(request,\"Fdtywbl\")",
		desc="���İ��� by DAOTools ",
		flowType="fawen",
		dataType="action"
		)
public class FdtywblMapDAO extends MapDAO {

	private FdtOaYwblDAO fdtOaYwblDAO;

	@MethodChinaName(cname="���İ���")
	public FdtOaYwblDAO getFdtOaYwblDAO() {
		return fdtOaYwblDAO; 
	}

	public void setFdtOaYwblDAO (FdtOaYwblDAO fdtOaYwblDAO) {
		this.fdtOaYwblDAO = fdtOaYwblDAO;
	}
}